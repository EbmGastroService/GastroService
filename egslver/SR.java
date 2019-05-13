// created on 14.11.2007 at 23:21
//behandler Lohn und sonderZahlungsbetrag
//Sonder Regel SR wenn begin der Dienst nicht den Anfang des Monats entspricht
// Sonder Zahlung wenn den Beginn nicht den Januer entspricht
 package egslver;
public class SR{
	float Lohn;
	String Datum;
	String Begin;
	public SR(float brutto, String begin,String datum){
		Lohn=brutto;
		Datum=datum;
		Begin=begin;
		System.out.println(aliLo()+" : "+aliSZ());
	}
	float aliLo(){ 		
 		String bt="";
 		String bm="";
 		String bj=""; 		 		
		float lohn=0;
 		if(Begin.length()>9){
 			bt=Begin.substring(0,2); 			
 			bm=Begin.substring(3,5);
 			bj=Begin.substring(8,Begin.length()); 		
 		}else bt="1";
 		String aj=Datum.substring(2,Datum.length());
 		String am=Datum.substring(0,2); 		 		 		
 		if(bj.equals(aj)){
 			float dif=fd(am)-fd(bm);
 			if(dif==0){
 				if(fd(bt)>1)lohn=BT(fd(bt));
 				if(fd(bt)==1)lohn=Lohn;
 			}
 			if(dif<0)lohn=0;
 			if(dif>0)lohn=Lohn;
 		}else lohn=Lohn;
		//System.out.println(lohn+" : ");
		//System.out.println("Begin Tag:"+bt+" Begin Monat:"+bm+",Begin Jahr:"+bj+
		//                   "\nAkt Monat:"+am+", Akt Jahr:"+aj+", SR Lohn:"+lohn);
 		return lohn;
 	}	
 	float BT(float t){
 		float tagwert=Lohn/30;
 		float AAT=30-t;
 		float lohn=tagwert*AAT; 		
 		//System.out.println("Bruchtage des Monat in Wert:"+lohn);
 		return lohn;
 	}
 	
 	float aliSZ(){ 	
 		String bt="";
 		String bm="";
 		String bj="";
 		float lang=0;
 		float sz=Lohn;//aliLo();
 		if(Begin.length()>9){
 			bt=Begin.substring(0,2); 
 			bm=Begin.substring(3,5);
 			bj=Begin.substring(8,Begin.length());
 		}else bt="1"; 		
 		String aj=Datum.substring(2,Datum.length());
 		String am=Datum.substring(0,2); 		
 		if(bj.equals(aj)){
 			lang=1+fd(am)-fd(bm);
 			if(lang<2){
 				lang=0;sz=0;
 			}else{ 				
 				lang=(12-fd(bm))+1;
 				float br=0;
 				if(fd(bt)>1){br=(BT(fd(bt))/12);lang--;}
 				sz=br+((Lohn/12)*lang);
 			}
 		} 		
 		if(fd(am)==5 || fd(am)==11)return sz;else return 0;
 	}
 	public static float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}
 	public static void main(String[]args){
 		if(args.length>0)new SR(fd(args[0]),args[1],args[2]);else
 		new SR(500,"15.04.2007","0607");
 	}
}
