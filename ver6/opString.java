// created on 24.09.2007 at 00:56
package ver6;
public class opString{
	public opString(){}
	public String[] opkeys_d(){
		String[]strEx=new com.search.sucheDate("gastro/resource/opkeys_d.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{"Bezeichnung","Code Nummer","Kunde erfassen","Weiter suchen",				
			};
			save("gastro/resource/opkeys_d.dat",strEx);
		}
		return strEx;		
	}
	public String[] opkeys_e(){
		String[]strEx=new com.search.sucheDate("gastro/resource/opkeys_e.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{"By text","By number","Add customers","Tray again",				
			};
			save("gastro/resource/opkeys_e.dat",strEx);
		}
		return strEx;		
	}
	
	public String[]kd_d(){
		String[]strEx=new com.search.sucheDate("gastro/resource/opkd_d.dat").myDaten();
		if(strEx.length<=0){//0 bis 22
			strEx=new String[]{"Code ","Familien Name ","Vorname","Adresse","Postleitzahl","Ortschaft","Fest Tel.","Mobil Tel.",
			"<html>kann ohne Daten nicht Anfangen<br><b>wollen Sie Weiter?",
			"die Liste ist leer!",
			"Datensatz nicht gefunden",
			"<html><br> Soll gespeichert werden JA/NEIN?",
			"<html>Die suche ist<b> Negativ! <b><br>Wie wollen Sie weiter gehen? ",
			" Leider nicht Vorhanden",
			"<html>Kunden suchen<br>Wie wollen sie?"," eingeben",
			"<html>Der Datensatz <font color=blue> ","</font><br> unter (Suche ",")<br><b>ist ohne Erfolg!",
			"<html><font color=red><br>in Erweiterte Textsuche suchen?",
			"<html><font color=red><br>Weiter Bearbeiten?",
			"<html><body><center><font size=12pt font color=green><b>EbmGastroService [Kunden Datenbank]</b></font></center></body></html>",
			"<html> <font color=blue> suche nach :"
			};
			save("gastro/resource/opkd_d.dat",strEx);
		}
		return strEx;		
	}
	public String[]kd_e(){
		String[]strEx=new com.search.sucheDate("gastro/resource/opkd_e.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"Code ","Famlyname ","Firstname","Adresse","Citycode","City","Telefon","Mobil",
			"<html>Sorry its empty cannt do anything without data<br>Could you tray again?",
			"Its empty!",
			"Cannt found it",
			"<br> Would you like to save it  yes/no?",
			"<html>Search is <b>negativ!</b> How do you like? ",
			 " Leider nicht Vorhanden",
			"<html>search a costumer<br>How do you like?"," .",
			"<html>Date <font color=blue>[ "," ]</font><br> by (Search ",")<br> was<b> negative!</b>",
			"<html><font color=red><br>Would you like to Search with a text?",
			"<html><font color=red><br>Would you like to edite it?",
			"<html><body><center><font size=12pt font color=green><b>EbmGastroService [Customers databank]</b></font></center></body></html>",
			"<html> Search for :"
			};
			save("gastro/resource/opkd_e.dat",strEx);
		}
		return strEx;		
	}
	
	public String[]rc_d(){
		String[]strEx=new com.search.sucheDate("gastro/resource/oprc_d.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"<html><body bgcolor=red><font color=white size=+1>Erfasssung Abbrechen! </font><font color=blue><br>[Ja]-nicht erfassen!<br>[Nein]-erfassen!</font>",
			"\n ist abgebrochen!!",
			"<html><b><font color=green size=+1>Neue Rechnung erstellen</font><br><font color=blue size=+1>Rechnung Nr :",
			"Erfassung Abbrechen?","<html><b><font color=green size=+1>Kunde :</font><br>"," Daten sind gespeichert","Ofene Posten: ",
			"Pos","Menge","Bezeichnung","E-Preis","Total","\n\n\n      N  E  U  E    E  R  F  A  S  S  U  N  G",
			"\nEntfernen eine Position\nArtikelbezeichnung tauschen\n Zb -Schinken +Ananas oder\n die Menge kurigieren\nWie wollen Sie weiter gehen!\n",
			" Rechnung an Kunden Nummer: ","  erfassen","Eingabe Korrekt?\n es wird gespeichert!","\n Rechnung: ","\n ist abgeschlossen",
			"\n ist abgebrochen!!","Rechnug Anzahl: ","  Kassa Total: "," Der Tageserlose hat "," erfasste Buchungen gefunden.",
			"\n\n Wenn sie eine Tabellen Ansicht wollen, dann aktivieren der [Kassabuch]\n unter Menu [Chef] und auf [Tageserlose] drucken",
			"Welche Farbe soll getauscht werden?",
			"<html><body><center><font size=12pt font color=green><b>EbmGastroService [Erfassung]</b></font></center></body></html>",
			"Die(", ") erfassten Buchungen, haben einen Umsatz von (", ")",
			"<html>Suche nach<br><b>Rechnung Nr.</b> mit /xxx wie /1720<br><b>Kunde</b> mi xxx wie 2514<br><b>Datum</b> mit ttmmjj/ wie 010907/ oder 0907/",
			" Die Suche nach "," hat "," erfasste Buchungen gefunden."
			};			
			save("gastro/resource/oprc_d.dat",strEx);
		}	
		return strEx;		
	}
	public String[]rc_e(){
		String[]strEx=new com.search.sucheDate("gastro/resource/oprc_e.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"<html><body bgcolor=red><font color=white size=+1>cancel it! </font><font color=blue><br>[yes]-don´t add this entry!<br>[no]-add it!</font>",
			"\n Its canceled!!",
			"<html><b><font color=green size=+1>add new entry</font><br><font color=blue size=+1>ID number :",
			"Braek it!","<html><b><font color=green size=+1>Costumer :</font><br>"," saved ok!","Op bills : ",
			"Pos","Quant.","Art. name","Price","Total","\n\n\n      N  E  W    E  N  T  R  Y",
			"\ndellete a position\nChange the Art. name\n as -eggs +ananas or\n  change Quantety\nHow do you like it!\n",
			" add entry to a customer ID: "," ","Are you ready to save it?\n it will be saved!","\n Entry: ","\n have done",
			"\n It´s canceled!!","Entry Quant: ","  Cash total: "," Todys turnover have "," adds found.",
			"\n\n Would you like to see the expretion? its easy got to [cashbook]\n under the menue of [Chef] then click on [Turnover]",
			"Which color you like to change?",
			"<html><body><center><font size=12pt font color=green><b>EbmGastroService [Bills entry]</b></font></center></body></html>",
			"Total adds of ( "," ) have amount of ( "," )",
			"<html>Searching for<br><b>Bills number</b> with /xxx like /1500<br><b>Customers </b>with xxx like 2514<br><b>Date</b> with ddmmyy/ like 010907/ or 0907/ ",
			" By searching for "," have "," adds found."
			};
			save("gastro/resource/oprc_e.dat",strEx);	
		}		
		return strEx;		
	}
	void save(String fn,String[]str){
		com.units.save save=new com.units.save();
		save.file(fn,"",false);
		for(int i=0;i<str.length;i++)save.file(fn,str[i],true);
	}
}
