// created on 10.03.2011 at 15:38
//Abgaben Erlagschein f. Finanzamt GKK
//vom klient/ls/dgg_xxx_YY.dat
package egslver;
public class ES{	
	String K;
	String Monat;
	public ES(String klient,String monat){
		K=klient;
		Monat=monat;
		saveME();		
		Erlagschein();
	}

	String MLk(String M){//Monats Lohnkosten der Mitarbeiter M 		
 		String file="egslv/k"+K+"/ls/dgg_"+M+"_"+Monat.substring(2,Monat.length())+".dat";					
		String[]str=open(file);
		String dateline="";
		for(int i=0;i<str.length;i++){
			String[]ld =new com.options.ausTeilen().koma(str[i]);
			if(ld[0].equals(Monat)){
				dateline=dline(ld,M);//str[i];
			}
		}	
 		return dateline;
 	}
 	String dline(String[]str,String tausch){
 		String dl=tausch;
 		str[0]="";
 		for(int i=1;i<str.length;i++){
 			dl+=","+str[i];
 		}
 		return dl;
 	}
	float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	void Erlagschein(){
 		String file="egslv/k"+K+"/ls/mlk_"+Monat+".dat";						
 		String[]Lkd=open(file);
 		String[]ld =new com.options.ausTeilen().koma(Lkd[0]);
 		float[]sume=new float[ld.length];
 		for(int i=0;i<Lkd.length;i++){
			ld =new com.options.ausTeilen().koma(Lkd[i]);
 			sume[0]=0;
 			for(int d=1;d<ld.length;d++){
 				sume[d]+=fd(""+ld[d]);
 			}
 		}
 		String dateline=Monat;
 		for(int d=1;d<sume.length;d++){
 			dateline+=","+sume[d];
 		}
 		file="egslv/k"+K+"/ls/Smlk_"+Monat+".dat";						
 		new com.units.save().file(file,dateline,false);		
 		//	0		   1		2		 3       4
 		//datum+","+brutto+","+sz+","+dnkk+","+szdnkk+","+
 		// 5		   6	  7   	 8	    9    	10	     11         12
 		//dgkk+","+gszkk+","+mav+","+DB+","+DZ+","+lstr+","+lstrsz+","+Komm;
 		float GKKB=sume[3]+sume[5];
 		float GKKSZB=sume[4]+sume[6];
 		float MAV=sume[7];
 		float GES=GKKB+GKKSZB+MAV;
 		float DB=sume[8];
 		float DZ=sume[9];
 		float LST=sume[10]+sume[11];
 		float Komm=sume[12];
 		String[]ErlagText={
 			"Brutto Lohne:			"+f(""+sume[1])+"",
 			"Brutto SZ:			"+f(""+sume[2])+"",
 			"_________________________________",               
 			"KK Beitrag: 			"+f(""+GKKB)+"",
 			"SZ Beitrag: 			"+f(""+GKKSZB)+"",
 			"MAV Beitrag: 			"+f(""+MAV)+"",
 			"GKK Gesamt Beitrag: 		"+f(""+GES)+"",
 			"_________________________________",
 			"Finanzamt DB Abgaben:		"+f(""+DB)+"",
 			"Finanzamt DZ Abgaben: 		"+f(""+DZ)+"",
 			"Finanzamt Lst Abgaben: 		"+f(""+LST)+"",
 			"_________________________________",
 			"KommenalSteuer Gemeinde: 	"+f(""+Komm)+"" 			
 		};
 		file="egslv/k"+K+"/ls/Erlagschein_"+Monat+".dat";						
 		new com.units.save().dontsort(file,ErlagText,false);		
 		
 	}
 	String f(String str){return new com.units.MyZahl().deci(Float.parseFloat(str));}
	void saveME(){
 		String[]kma= new com.search.sucheDate("egslv/k"+K+"_ma.dat").myDaten();
		String[]kme=new String[kma.length];
 			for(int ma=0;ma<kma.length;ma++){
 				kme[ma]=MLk(kma[ma]);
 			}
 		String file="egslv/k"+K+"/ls/mlk_"+Monat+".dat";						
 		new com.units.save().dontsort(file,kme,false);	
	}
	public static void main(String[]args){
		if(args.length>0)new ES(args[0],args[1]);else new ES("0001","02"); 	 	
	}
}
