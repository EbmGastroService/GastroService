// created on 30.11.2006 at 21:49
package ver6;
public class actionString{
	public actionString(){};
	public String[]program_D(){
		String[]strEx=new com.search.sucheDate("gastro/resource/program_d.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"Musik","Editor","Taschenrechner","Neue Kunde","Neue Ware","Hilfe",
			"Drucker Papier","Rechnung erfassen","Fahrer","Waren","Kassa Buch",
			"Neue Passwort","Beenden","Dekorieren","Tabelle","Kunden","Waren Korb",
			"Spalten","EbmGastroService online","Formular Vorschau","Info","Change Firmen Daten"
			};
			new com.units.save().dontsort("gastro/resource/program_d.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]program_E(){
		String[]strEx=new com.search.sucheDate("gastro/resource/program_e.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"Musik","Editor","calculater","add customer","add good","help",
			"choose paper","new bill","driver","goods application","cash book","Weater application",
			"new password","exit","design","tables expression","customers application","goods basket",
			"calculation view","formular view","EbmGastroService online","about us","change organisation data"
			};
			new com.units.save().dontsort("gastro/resource/program_e.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]rcn_tf_d(){
		String[]strEx=new com.search.sucheDate("gastro/resource/rcn_tf_d.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"","	Aktivieren Sie die Funktions Tasten","	clicken Sie mit dem Maus Hier",
			                    "","	Neue Rechnung erstellen auf [Fatura] oder [F2] cliken","","	Hilfer [F1]"
			};
			new com.units.save().dontsort("gastro/resource/rcn_tf_d.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]rcn_tf_e(){
		String[]strEx=new com.search.sucheDate("gastro/resource/rcn_tf_e.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"","	Please touch hier to activate the funktionkeys","	click on with the mouse",
			                    "	New bill will be done with F2","	you get help with F1"
			};
			new com.units.save().dontsort("gastro/resource/rcn_tf_e.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]slide_d(){
		String[]strEx=new com.search.sucheDate("gastro/resource/option_s_d.dat").myDaten();    
		if(strEx.length<=0||strEx.length<13){
			strEx=new String[]{"Rechnung erfassen und drucken",
			"Rechnungen die noch nicht abgebucht sind",
			"Rechnung suchen!",
			"Rechnung nach drucken",
			"Waren Verkauf in detail",
			"Rechnung an Mitarbeiter und Kassebuch",
			"Mitarbeiter Umsatz",
			"Mitarbeiter detailiertes Bericht nur anschauen",
			"Monatsumsatz Monatsergebnissen","Kunde zum Datenbank","Ware zum Datenbank","entferne eine OP Zeile"
			};
			new com.units.save().dontsort("gastro/resource/option_s_d.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]slide_e(){
		String[]strEx=new com.search.sucheDate("gastro/resource/option_s_e.dat").myDaten();    
		if(strEx.length<=0||strEx.length<13){
			strEx=new String[]{"make a bill and print it",
			"see the bills ",
			"search a old bill!",
			"search and print it!",
			"Today´s sales!",
			"close the books",
			"see the calculated sales!",
			"see all",
			"controll and print","new Entry add to DB","new Entry to DB","del a bill"
			};
			new com.units.save().dontsort("gastro/resource/option_s_e.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]items(){
		String[]strEx=new com.search.sucheDate("gastro/resource/items_d.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"Rechnung erfassen","Auswertung","Kassa Buch","Waren Korb",
			"Tabelle","Fahrer","Journal","Kunden","Waren","Update","Kellner Programm","Spalten",
			"Lohnverrechnung","Buchhaltung"
			};
			new com.units.save().dontsort("gastro/resource/items_d.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]items_E(){
		String[]strEx=new com.search.sucheDate("gastro/resource/items_e.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"new bill","filter","cash book","goods basket",
			"tables expression","driver","Journal","customers application","goods application",
			"Update","Weater application","calculation view","Wages","Acounting"
			};
			new com.units.save().dontsort("gastro/resource/items_e.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]menus(){
		String[]strEx=new com.search.sucheDate("gastro/resource/menus.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"Gastro","Service","Chef"};
			new com.units.save().dontsort("gastro/resource/menus.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]syst(){
		String[]strEx=new com.search.sucheDate("gastro/resource/syst.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"Beenden","Neue Passwort","Einstellung","Info",
			"Change Firmen Bezeichnung","Dekorieren"
			};
			new com.units.save().dontsort("gastro/resource/syst.dat",strEx,false);
		}		
		return strEx;
	}
	public String[]syst_E(){
		String[]strEx=new com.search.sucheDate("gastro/resource/syst_e.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"exit","new password","configuration","Info","change organisation data",
			"design","defrag"
			};
			new com.units.save().dontsort("gastro/resource/syst_e.dat",strEx,false);
		}		
		return strEx;
	}
	void zeige(String[]str){
		for(int i=0;i<str.length;i++)
			System.out.println(str[i]);
	}
	public static void main(String[]args){
		actionString as=new actionString();
		System.out.println("_____ITEMS______");
		String[]str=as.items();as.zeige(str);
		System.out.println("_____PROGRAMS______");
		str=as.program_E();as.zeige(str);
		System.out.println("_____MENUS______");
		str=as.menus();as.zeige(str);
	}
}
