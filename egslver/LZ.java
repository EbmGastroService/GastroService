// created on 26.10.2007 at 00:21
/* Lohnverechnung Teilprogram
 * von Mourad El Bakry 
 * Vianna 
 * Austria
 */
 package egslver;
 public class LZ{
 	String K,M;
 	String[]Ma; 	
 	String dateLine,datum;
 
 	public LZ(String k,String m,String dat){
 		if(k.toLowerCase().charAt(0)=='k')k=k.substring(1,k.length());
 		if(m.toLowerCase().charAt(0)=='m')m=m.substring(1,m.length());
 		if(dat==""||dat.length()<3)dat=new com.units.myDatum().ist_my();
 		datum=dat;
 		K=k;
 		M=m;
 		Ma=stammDaten("m"); 		 		
 		Teil();
 		new LZPane(stammDaten("k"),stammDaten("m"),datum);
 	} 	
 	String[]stammDaten(String str){
 		SD sd=new SD(K,M);
 		if(str.equals("m"))return sd.MaSd;else
 		if(str.equals("k"))return sd.KlientSd;else return null;
 	}
 	float DN_SZSatz(float brutto){
 		String[]MaSatz=new lvstruck().masatzd();
 		float gkk_satz=0; 
 		float kum_satz=0;
 		float wbf_satz=0; 		
 		float iesg_satz=0;
 		float mav_satz=0; 		 		 		
 		int i=0;
 		if(Ma[16].charAt(0)=='A')gkk_satz=Wert(MaSatz[1])/100;else 
 		if(Ma[16].charAt(0)=='D')gkk_satz=Wert(MaSatz[3])/100;else 
 		if(Ma[16].charAt(0)=='N'||Ma[16].charAt(0)=='M'){gkk_satz=Wert(MaSatz[5])/100;i=5;};
 		gkk_satz=(float)N25(brutto,(double)gkk_satz);
 		if(i==0){
 		kum_satz=0;
 		wbf_satz=Wert(MaSatz[9])/100; 		
 		iesg_satz=Wert(MaSatz[11])/100;
 		mav_satz=Wert(MaSatz[13])/100; 		 		 		
 		}
 		float gesamt=gkk_satz+wbf_satz+kum_satz+iesg_satz+mav_satz; 		 		
 		return gesamt;
 	} 
 	double N25(float brutto,double satz){
 		double nSatz=0;
 		if(satz>0.014 && brutto<1100)nSatz=satz-0.03;else
 		if(brutto>1100 && brutto<1280)nSatz=satz-0.02;else
 		if(brutto>1280 && brutto<1380)nSatz=satz-0.01;else	
 		if(brutto>1380)nSatz=satz; 		
 		System.out.println("GKK_DNA_Satz:"+satz+"Neue Satz: "+nSatz+"\n________");
 		return nSatz;
 	}
 	float DNA(float brutto){
 		String[]MaSatz=new lvstruck().masatzd();
 		float gkk_satz=0; 
 		float kum_satz=0;
 		float wbf_satz=0; 		
 		float iesg_satz=0;
 		float mav_satz=0; 		 		 		
 		int i=0;
 		if(Ma[16].charAt(0)=='A')gkk_satz=Wert(MaSatz[1])/100;else 
 		if(Ma[16].charAt(0)=='D')gkk_satz=Wert(MaSatz[3])/100;else 
 		if(Ma[16].charAt(0)=='N'||Ma[16].charAt(0)=='M'){gkk_satz=Wert(MaSatz[5])/100;i=5;};
 		gkk_satz=(float)N25(brutto,(double)gkk_satz);
 		if(i==0){
 		kum_satz=Wert(MaSatz[7])/100;
 		wbf_satz=Wert(MaSatz[9])/100; 		
 		iesg_satz=Wert(MaSatz[11])/100;
 		mav_satz=Wert(MaSatz[13])/100; 		 		 		
 		}
 		float gkk=gkk_satz*brutto;
 		float wbf=wbf_satz*brutto;
 		float kum=kum_satz*brutto;
 		float iesg=iesg_satz*brutto;
 		float mav=mav_satz*brutto; 		
 		float gesamt=gkk+wbf+kum+iesg+mav; 		 		
 		dateLine=datum+","+M+","+brutto+","+gkk+","+wbf+","+kum+","+iesg+","+mav; 	 	
 		
 		System.out.println("GKK_DNA_BEITRAG:"+dateLine);
 		save("egslv/k"+K+"/dna_"+M+"_"+datum.substring(2,datum.length())+".lz",dateLine); 
 		return gesamt;
 	}
 	float DGA(float brutto){
 		String[]kSatz=new lvstruck().klsatzd();
 		float gkk_satz=0; 
 		float kum_satz=Wert(kSatz[7])/100;
 		float wbf_satz=Wert(kSatz[9])/100; 		
 		float iesg_satz=Wert(kSatz[11])/100;
 		if(Ma[16].charAt(0)=='A')gkk_satz=Wert(kSatz[1])/100;else 
 		if(Ma[16].charAt(0)=='D')gkk_satz=Wert(kSatz[3])/100;else 
 		if(Ma[16].charAt(0)=='N'||Ma[16].charAt(0)=='M'){
 			gkk_satz=Wert(kSatz[5])/100;
 			kum_satz=0;wbf_satz=0;iesg_satz=0;
 		}
 		
 		float mav_satz=Wert(kSatz[13])/100;
 		float DB_satz=Wert(kSatz[15])/100;
		float DZ_satz=Wert(kSatz[17])/100;
		float Komm_satz=Wert(kSatz[19])/100;		
 		
 		float gkk=gkk_satz*brutto;
 		float sz=SZ();
 		float szgkk=gkk_satz*sz;
 		float wbf=wbf_satz*(brutto);
 		float kum=kum_satz*(brutto);
 		float iesg=iesg_satz*(brutto);
 		
 		float mav=mav_satz*(brutto+sz);
 		float DB=DB_satz*(brutto+sz);
 		float DZ=DZ_satz*(brutto+sz);
 		float Komm=Komm_satz*(brutto+sz);
 		
 		float dnkk=DNA(brutto); 		 		
 		float szdnkk=sz*DN_SZSatz(brutto); 		
 		float lstr_satz=LStr_satz();
 		float lstr=(brutto-dnkk)*lstr_satz;
 		float lstrsz=0;
 		float j6=0;
 		float szsatz=0; 		
 		String am=datum.substring(0,2); 
 		float fb=0;
 		if(Wert(am)==5)fb=620;else fb=0;
 		for(int x=1;x<Wert(am);x++)j6+=brutto;
 		j6=(j6/Wert(am))*2; 	
 		if(sz<j6 && sz>1000)szsatz=6;
 		lstrsz=(sz-(szdnkk+fb))*szsatz/100;
 		//System.out.print(datum+"	,"+sz+",	"+j6);
 		if(sz>j6){ 			
 			float l1=sz-(szdnkk+fb);
 			float l2=2000-l1;
 			lstrsz=l1*6/100;
 			lstrsz+=l2*DN_SZSatz(brutto); 			
 		} 		
 		//System.out.println(",>>>>"+lstrsz);
 		
 		if(lstrsz<0)lstrsz=0;
 		float gszkk=szgkk+(sz*iesg_satz)+(sz*kum_satz);
 		float dgkk=gkk+wbf+kum+iesg;
 		float gesamt=dgkk+mav;
 		//			0		1		2		3		4		5	    6		 7			
 		dateLine=datum+","+M+","+brutto+","+gkk+","+wbf+","+kum+","+iesg+","+mav; 
 		//					0		1			2		 3
 		String dateLineV=datum+","+brutto+","+sz+","+dnkk+","+szdnkk+","+
 		//4		 5	      6		 7	    8    	9		  10		11
 		dgkk+","+gszkk+","+mav+","+DB+","+DZ+","+lstr+","+lstrsz+","+Komm;
 		
 		save("egslv/k"+K+"/dga_"+M+"_"+datum.substring(2,datum.length())+".lz",dateLine);
 		save("egslv/k"+K+"/ls/dgg_"+M+"_"+datum.substring(2,datum.length())+".dat",dateLineV); 		
 		return gesamt;
 	}
 	float SZ(){
 		return new SR(Wert(Ma[13]),Ma[11],datum).aliSZ();
 	} 	
 	float aliLo(){
 		float lohn=new SR(Wert(Ma[13]),Ma[11],datum).aliLo(); 	
 		return lohn;
 	} 	
 	float LStr_satz(){
 		return new Lstr(Ma[13],Ma[16],Ma[11]).LStr_satz(); 		
	}
 	void Teil(){ 		
 		float brutto=aliLo(); 		
 		dateLine=""; 		
 		float beitrag1=DGA(brutto); 		 		
 	} 	
 	void save(String file,String str){
 		new com.units.save().file(file,str,true);
 	} 
 	float Wert(String str){
 		float lohn=0;
 		try{lohn=Float.parseFloat(str);}catch(Exception ex){lohn=0;}
 		return lohn;
 	}
 	void zeige(String[]str){
 		for (int i=0;i<str.length;i++)System.out.println(i+": "+str[i]); 		
 	} 	
 	public static void main(String[]args){ 		
 		String[]K=new com.search.sucheDate("egslv/klient.dat").myDaten();
 		if(args.length>0 && args.length>2)new LZ(args[0],args[1],args[2]);else 
 		if(args.length>0 && args.length<2)new LZ(K[0],args[0],args[1]);	
 		else{
 			for(int ki=0;ki<K.length;ki++){
 				String[]kma= new com.search.sucheDate("egslv/k"+K[ki]+"_ma.dat").myDaten();
 				for(int ma=0;ma<kma.length;ma++){
 					for(int m=1;m<13;m++){
 						String datum="";
 							String jahr=new com.units.myDatum().jahr();
 						if(m<10)datum="0"+m+jahr;else datum=m+jahr;
 						new LZ(K[ki],kma[ma],datum);System.out.print("..");
 					}
 				}
 			}
 		}
 	}
 }
