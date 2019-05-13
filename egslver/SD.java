// created on 22.10.2007 at 16:02
/* Lohnverechnung Teilprogram
 * von Mourad El Bakry 
 * Vianna 
 * Austria
 */
 package egslver;
 public class SD{
 	String[]KlientSd;//Klient Stammdaten
 	String[]MaSd;//Mitarbeiter  Stammdaten
 	String[]Klient;//Klient Stammdaten
 	String[]Ma;//Mitarbeiter  Stammdaten
 	String KlientCode;
 	String MaCode;
 	public SD(String klientCode,String maCode){
 		Klient=open("egslv/resource/klsdd.dat");
 		Ma=open("egslv/resource/masdd.dat");
 		if(klientCode.toLowerCase().charAt(0)=='k')klientCode=klientCode.substring(1,klientCode.length());
 		if(maCode.toLowerCase().charAt(0)=='m')maCode=maCode.substring(1,maCode.length());
 		
 		KlientCode=klientCode;
 		MaCode=maCode; 		
 		System.out.println("Klient Code:"+KlientCode+", Mitarbeiter Code:"+MaCode);
 		//KlientCode,Firmenbezeichnung,Adr,Tel,Steuernummer,VersicherungsKonto
 		String[]openDate=open("egslv/k"+KlientCode+"_sd.dat");
 		if(openDate==null){
 			fehler("Klient: k"+KlientCode);
 			KlientSd=test("egslv/k"+KlientCode+"_sd.dat",openDate,Klient);
 			//MaCode,Versicherung Nummer,Dienst Beginn, Brutto,Beruf,Gruppe,PP,FB
 		}
 		KlientSd=open("egslv/k"+KlientCode+"_sd.dat");
 		String[]openDatem=open("egslv/k"+KlientCode+"/sd/m"+MaCode+"_sd.dat");
 		if(openDatem==null){fehler("Mitarbeiter: m"+MaCode);
 			MaSd=test("egslv/k"+KlientCode+"/sd/m"+MaCode+"_sd.dat",openDatem,Ma);
 		}
 		MaSd=open("egslv/k"+KlientCode+"/sd/m"+MaCode+"_sd.dat");
 		zeige(); 		
 	} 	 	
 	public SD(){
 		Klient=open("egslv/resource/klsdd.dat"); 		
 		String[]T=neuDaten(Klient,KlientCode);
 		if(T!=null && T.length>0){ 		
 			KlientCode=T[0]; 			
 			KlientSd=T; 		
 			if(KlientCode.toLowerCase().indexOf("k")>-1)KlientCode=KlientCode.substring(1,KlientCode.length());
 			String file="egslv/k"+KlientCode+"_sd.dat";
 			save(file,T,false);
 			save("egslv/klient.dat",KlientCode); 			
 			//zeige();
 		}
 	}
 	public SD(String klientCode){
 		if(klientCode.toLowerCase().indexOf("k")>-1)klientCode=klientCode.substring(1,klientCode.length());
 		KlientCode=klientCode; 		
 		
 		Klient=open("egslv/resource/klsdd.dat"); 
 		Ma=open("egslv/resource/masdd.dat");
 		KlientSd=test("egslv/k"+KlientCode+"_sd.dat",open("egslv/k"+KlientCode+"_sd.dat"),Klient);
 		String[]T=neuDaten(Ma,MaCode);
 		if(T!=null && T.length>0){ 		
 			MaCode=T[0]; 			 			
 			MaSd=T;
 			String file="egslv/k"+KlientCode+"/sd/m"+MaCode+"_sd.dat";
 			save(file,T,false); 			
 			save("egslv/k"+KlientCode+"_ma.dat",MaCode);
 			//zeige();
 		}
 	}
 	void fehler(String Str){
 		new com.options.MyOp().fehler("Kartei ist nicht vorhanden,<br><font color=blue>"+Str+" neue erfassen!");
 	}
 	void ent(){	KlientCode="";MaCode="";KlientSd=null;MaSd=null;}
 	
 	String[]test(String file,String[]str,String[]wer){
 		if(str.length<=0 ||str==null){ 			
 			String was="";
 			if(wer[0].equals("Mitarbeiter Code"))was=MaCode;else was=KlientCode;
 			str=neuDaten(wer,was);
 			save(file,str,false); 		
 			save("egslv/k"+KlientCode+"_ma.dat",MaCode);
 		} 		
 		return str;
 	} 	
 	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	void save(String file,String[]date,boolean wie){
 		new com.units.save().dontsort(file,date,wie);
 	}
 	
 	void save(String file,String date){
 		new com.units.save().file(file,date,true);
 	}
 	String[] neuDaten(String[]wer,String was){
 		return new BP(wer,was).inhalt();
 	}
 	String[] neuDaten(String wer){
 		return new BP(wer).inhalt();
 	}
 	void zeige(){
 		for (int i=0;i<Ma.length;i++)System.out.println(Ma[i]+":"+MaSd[i]);
 		System.out.println("----------------------------------------------------");
 		for (int i=0;i<Klient.length;i++)System.out.println(Klient[i]+":"+KlientSd[i]);
 	}
 	public static void main(String[]args){
 		if(args.length>0)
 			new SD(args[0],args[1]);
 		else new SD("k0001","m0001");
 	}
 }
 
