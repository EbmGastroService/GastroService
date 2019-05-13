// created on 09.03.2011 at 20:19
//Lohn Abrechnung neue Aufrollen
//Mourad El Bakry
//an hand der vorhandenen Personal Kartei die Daten und Tabellen neue berechnen f. das ganze Jahr im voraus!
package egslver;
public class AR{	
	public AR(){
		Rollen("");
	}	
	public AR(String jahr){		
		Rollen(jahr);
	}	
	public AR(String KlientCode,String MitarbeiterCode,String jahr){		
		Rollen(KlientCode,MitarbeiterCode,jahr);
	}	
	
 	void Rollen(String jahr){// nicht 2011 sonder 11
 		if(jahr.length()<=0)jahr=new com.units.myDatum().jahr();
 		if(jahr.length()>2)jahr=jahr.substring(jahr.length()-2,jahr.length());
 		String[]K=new com.search.sucheDate("egslv/klient.dat").myDaten();
 		for(int ki=0;ki<K.length;ki++){
 			String[]kma= new com.search.sucheDate("egslv/k"+K[ki]+"_ma.dat").myDaten();
 			for(int ma=0;ma<kma.length;ma++){
 				for(int m=1;m<13;m++){
 					String datum="";
 						//String jahr=new com.units.myDatum().jahr();
 					if(m<10)datum="0"+m+jahr;else datum=m+jahr;
 					new LZ(K[ki],kma[ma],datum);System.out.print("..");
 				}
 			}
 		}
 	}
 	void Rollen(String KlientCode,String MitarbeiterCode,String jahr){// nicht 2011 sonder 11
 		if(jahr.length()<=0)jahr=new com.units.myDatum().jahr();
 		if(jahr.length()>2)jahr=jahr.substring(jahr.length()-2,jahr.length()); 		
 		for(int m=1;m<13;m++){
 			String datum="";
 			if(m<10)datum="0"+m+jahr;else datum=m+jahr;
 			new LZ(KlientCode,MitarbeiterCode,datum);
 			System.out.print("..");
 		}
 	}
 	public static void main(String[]args){
		if(args.length>0)new AR(args[0]);else new AR(); 	 	
	}
}
