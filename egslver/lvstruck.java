// created on 19.10.2007 at 00:27
package egslver;
public class lvstruck{
	public lvstruck(){
		klsdd();
		klbedd();
		klsatzd();
		masdd();
		mabedd();
		masatzd();
		new formhtml();
	}
	String[]klsdd(){
		String[]strEx=new com.search.sucheDate("egslv/resource/klsdd.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{
				"Klient Code","Firmen Bezeichnung",
				"Adresse","Postleitzahl","Ortschaft","Bundsland",
				"Telefon","Fax","Steuer Nummer","IDN","Finanzamt",
				"Versicherungs Ort","Versicherungs Nummer"
			};
			save("egslv/resource/klsdd.dat",strEx);
		}
		return strEx;	
	}
	String[]klbedd(){
		String[]strEx=new com.search.sucheDate("egslv/resource/klbedd.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{
				"Klient Erfassen","Suchen","Bearbeiten","Entfernen","Lohnverrechnung",
				"Monatsabbrechung","Abgaben Finanzamt",
				"Versicherung Beitrag","Abgaben Gemeinde","Beitrag MAV",
				"Brutto Lohne","Netto Lohne"
			};
			save("egslv/resource/klbedd.dat",strEx);
		}
		return strEx;	
	}
	String[]masdd(){
		String[]strEx=new com.search.sucheDate("egslv/resource/masdd.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{
				"Mitarbeiter Code","Titel","Name","Vorname",
				"Adresse","PLZ","Ortschaft","Bundsland",
				"Vollzeit Teilzeit oder Gering ","Beruf",
				"Arbeiter oder Angestellte","Dienst Antritt",
				"Wochen Stunden","Brutto Einkommen",
				"Versicherung Ort","Versicherung Nummer",
				"Versicherung Gruppe","Telefon"
			};
			save("egslv/resource/masdd.dat",strEx);
		}
		return strEx;	
	}
	String[]mabedd(){
		String[]strEx=new com.search.sucheDate("egslv/resource/mabedd.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{
				"MA erfassen","Suchen","Bearbeiten","Entfernen","Lohnverrechnung",
				"Brutto Lohne","Lohnkosten","Klient Wechseln","Neue Klient erfassen",
				"Drucke Formular","Vor Jahr!"
			};
			save("egslv/resource/mabedd.dat",strEx);
		}
		return strEx;	
	}
	String[]masatzd(){
		String[]strEx=new com.search.sucheDate("egslv/resource/masatzd.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{
				"GKK_A","17.20","GKK_D","17.07","GKK_N","0","Kammerumlage","0.50",
				"Wohnbauforderung","0.50","IESG Zuschlag","0","MAV","0",
				"DB","0","DZ","0","Kommunal Steuer","0"
			};
			save("egslv/resource/masatzd.dat",strEx);
		}
		return strEx;	
	}
	String[]klsatzd(){
		String[]strEx=new com.search.sucheDate("egslv/resource/klsatzd.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{
				"GKK_A","20.65","GKK_D","20.78","GKK_N","1.40","Kammerumlage","0",
				"Wohnbauforderung","0.50","IESG Zuschlag","0.55","MAV","1.53",
				"DB","4.50","DZ","0.42","Kommunal Steuer","3.00"
			};
			save("egslv/resource/klsatzd.dat",strEx);
		}
		return strEx;	
	}
	void save(String fn,String[]str){
		com.units.save save=new com.units.save();		
		for(int i=0;i<str.length;i++){						
			System.out.println(str[i]);			
		}
		save.dontsort(fn,str,false);
	}
	public static void main(String[]args){			
		//lvstruck lv=
		new lvstruck();
		/*lv.klsdd();
		lv.klbedd();
		lv.klsatzd();
		lv.masdd();
		lv.mabedd();
		lv.masatzd();*/
	}
	
}
