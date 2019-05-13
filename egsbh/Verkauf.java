// created on 25.11.2007 at 00:30
//Author Mourad Elbakry
//Teil Buchhaltungs programm

//Einkauf Verkauf Rechnugszahlung
package egsbh;
public class Verkauf{	
	String GegenKonto,KassaBank,ZahlungsKonto,Datum,Wo;
	float NettoBetrag,Vorsteuer,Kassa,Bank,StrDiv;	
	
	public Verkauf(String Datum,String gegenkonto,String Betrag,String BruttoNetto,String KassaBank,String Steuer,String Wo){		
		this.Datum=Datum;
		this.Wo=Wo;
		GegenKonto=Gkonto(gegenkonto);				
		StrDiv=Divi(Steuer);
		NettoBetrag=Netto(Betrag,BruttoNetto);		
		Vorsteuer=Vstr(Steuer);
		this.KassaBank=KassaBank;
		Kassa=ZKonto();		
		Bank=ZKonto();
		Jb();
		zeige();		
	}
	String Gkonto(String gk){
		String[]wl=open("egsbh/kontos.dat");
		if(wl.length<=0)new Kontos();
		String str="";
		for(int i=0;i<wl.length;i++){						
				String text=wl[i].substring(wl[i].indexOf(",")+1,wl[i].length());
			if (text.toLowerCase().equals(gk.toLowerCase())){				
				str=wl[i].substring(0,wl[i].indexOf(","));
			} 
		}
		return str;
	}
	void Jb(){		
		String B=Beleg();
		String konto=KontoNr();
		new JB(Datum,B,konto,GegenKonto,""+NettoBetrag,""+Vorsteuer,Wo);
	}
	String codgen(String code){
		String[]wl=open("egsbh/jb.dat");
		int x=0;	
		for(int i=0;i<wl.length;i++){						
			if (wl[i].toLowerCase().indexOf(code.toLowerCase())>-1) x++;
		}
		String  str=code;//0 2514 length=0 bis 3
		String str1=""+(x+1);//0 1 11 100 999 length=0 bis 2
		int m=str1.length();
		int def=3-m;//1 oder 12 oder 120
		if(def>0){
			for(int l=code.length();l<(code.length()+def);l++)str=str+"0";
		}
		str+=str1;
		return str;
	}
	String Beleg(){
		if(KassaBank.toLowerCase().indexOf("kas")>-1)return codgen("K0");else return codgen("B0");
	}
	String KontoNr(){
		if(KassaBank.toLowerCase().indexOf("kas")>-1)return "2700";else return "2800";
	}
	void zeige(){
		System.out.print(" Buche: \n "+GegenKonto+
		                   " Netto:"+NettoBetrag+"\n\t an "+KontoNr()+" "+KassaBank+": "+ZKonto()+"\n\t an Ust: "+Vorsteuer);
		//if(Kassa>0)System.out.println(Kassa);else System.out.println(Bank);
	}
	float ZKonto(){
		return -(NettoBetrag+Vorsteuer);
	}
	float Divi(String steuer){		
		return (fd(steuer)+100)/fd(steuer);
	}
	float Netto(String betrag,String NB){
		float mbetrag=0;	
		float b=fd(betrag);
		if(NB.toLowerCase().indexOf("br")>-1)mbetrag=b-(b/StrDiv);else mbetrag=b;		
		return mbetrag;
	}
	float Vstr(String steuer){
		return NettoBetrag*fd(steuer)/100;
	}
	public static int Int(String str){
		int ld=0;
		try{
			ld=Integer.parseInt(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}	
	public static float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}	
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
	public static void main(String[]args){
		if(args.length>2){
			new Verkauf(args[0],args[1],args[2],args[3],args[4],args[5],args[6]);			
		}else new Verkauf("251207","1610","550","Brutto","Kassa","10","Metro");
	}
		
	
	
}
