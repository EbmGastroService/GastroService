// created on 25.11.2007 at 00:30
//Author Mourad Elbakry
//Teil Buchhaltungs programm

//Einkauf Verkauf Rechnugszahlung
package egsbh;
public class PR{	
	String GegenKonto,KassaBank,Datum,Bez;
	float NettoBetrag,Kassa,Bank;	
	
	public PR(String Datum,String gegenkonto,String Betrag,String KassaBank){		
		this.Datum=Datum;
		Bez=gegenkonto;
		GegenKonto=Gkonto(gegenkonto);						
		NettoBetrag=fd(Betrag);
		this.KassaBank=KassaBank;
		Kassa=ZKonto();		
		Bank=ZKonto();
		LB();
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
	void Jb(String kto,String gkto,String bez){		
		String B=Beleg(bez);		
		new JB(Datum,B,gkto,kto,""+(-NettoBetrag),""+0,Bez);
	}
	void LB(){
		/*
		 "6000,Löhne",
		 "6200,Gehälte",
		 "6410,Mitarbeite Vorsorge Kassa",
		 "6500,Gesetzlicher Sozialaufwand Arbeiter",
		 "6550,Gesetzlicher Sozialaufwand Angestellte",
		 "6600,Kommunalsteuer",
		 "6610,Dienstgebebeitrag zur Familienbeihilfe DB",
		 "6615,Dienstgeber Zuschlag DZ",
		 "6700,Freiwilliger Sozialaufwand",
		 "6888,Gewerblich Sozial Aufwand",		 
		 "3540,Verrechnungskonto Finanzamt Lstr DB DZ",
		 "3600,Verrechnungskonto Sozialversicherung",
		 "3610,Verrechnungskonto Stadt Gemeinde",
		 "3630,Verrechnungskonto Löhne und Gehälte",
		 "3888,Verrechnungskonto Gewerblich Sozial Aufwand",
		 */
		 String[]kto={"6000,3630","6200,3630","6500,3600","6550,3600","6600,3610","6610,3540","6615,3540","6888,3888"};
		for(int i=0;i<kto.length;i++){
			String[]gkto=new com.options.ausTeilen().koma(kto[i]);
			if(GegenKonto.equals(gkto[0])){				
				String kb=KassaBank.toLowerCase();
				if(kb.indexOf("kas")>-1)Jb(gkto[1],"2700","K0");
				else if(kb.indexOf("ofen")>-1)Jb(gkto[0],gkto[1],"ER");
				else Jb(gkto[1],"2800","B0");
			}
		}
		
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
	String Beleg(String bez){
		return codgen(bez);
	}
	float ZKonto(){
		return -(NettoBetrag);
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
			new PR(args[0],args[1],args[2],args[3]);			
		}else new PR("251207","6000","1550","Kassa");
	}
		
	
	
}
