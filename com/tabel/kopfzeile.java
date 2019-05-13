// created on 08.03.2004 at 15:59
//für Tabelen kopfzeile
package com.tabel;
import com.units.save;
public class kopfzeile{	
	public kopfzeile(){
			String[]kz={".. ,.. ,.. ,.. ,..",
			"wliste,Waren Code,Artikel Bezeichnung,E-Preis",
			"wgliste,Waren Code,Artikel Bezeichnung,E-Preis,Gruppen Spalte",
			"kliste,Kunden Code,Nachname,Vorname,Adressen Bezeichnung,Postleitzahl,Ortschaft,Festnetztelefon,Mobiletelefon",
			"jliste,Rechnung  Code,Datum,Kunden Code,R-Betrag,T1,T2",
			"fliste,Rechnung  Code,Datum,Kunden Code,R-Betrag,Total,FahrerNr,Buch +/- ",
			"kbuch,Rechnung  Code,Datum,Kunden Code,R-Betrag,Total,FahrerNr,Buch +/-",
			"kon,Rechnung Datum Nummer KundenCode",			
			"form01,Rechnung Code,gebucht am,Kunden Code,R-Betrag,Total,FahrerNr,Buch +/- ",
			"vk,Menge,Artikel Bezeichnung,E-Preis,Betrag,Total",			
			"00000,Menge ,Waren Code,Artikel Bezeichnung,E-Preis, Total",			
			"wkiste2,Anzahl Bestellungen,Kunden Nummer,Nachname,Vorname,Adressen Bezeichnung,Postleitzahl,Ortschaft,Festnetztelefon,Mobiletelefon"
			};
			String[]v = new com.search.sucheDate("gastro/date/source/kopfzeile.dat").myDaten();
			if(v.length!=kz.length)
			new save().dontsort("gastro/date/source/kopfzeile.dat",kz,false);		
	}
}
