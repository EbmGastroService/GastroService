// created on 28.05.2003 at 00:38
package com.display;
import javax.swing.*;
import java.awt.*;
import com.search.*;
public class hilfe{			
	String[] hd = new sucheDate("gastro/Date/hilfa.dat").myDaten();		
	public hilfe(){
		if(hd.length<1){
			new com.units.save().dontsort("gastro/Date/hilfa.dat",htext(),false);
			hd=new sucheDate("gastro/Date/hilfa.dat").myDaten();		
		
		}
			JTextArea jta=new JTextArea("",10,5);
          		jta.setFont(new Font("Courier New",0,14));
          		jta.setForeground(Color.blue);
		for( int i= 0;i<hd.length;i++){
			jta.append("\n "+hd[i]);
		}          	
          		/*jta.setBackground(Color.cyan);
          		jta.setSelectionStart(36) ;
				jta.setSelectionEnd(160) ;
				jta.setSelectedTextColor(Color.red) ;
				jta.setSelectionColor(Color.cyan) ;
				jta.select(65,87);*/
          		JFrame jf = new JFrame(" Gastro Hilfe");                   	
         		jf.getContentPane().add(new JScrollPane(jta),"Center");
         		jf.pack();
  		//	jf.setBounds(50, 100, 500, 420);
		
  			jf.setVisible(true);         	
	}
	String[] htext(){
String[] ht=		
 {"\nR E C H N U N G  E R F A S S U N G ",
  "_________________________________\n",
"\nDrucken Sie [Faktora] und folgen sie",
"\ndie Abfrage Fenstern.",
"\n1- kunde Wählen (kann auch ohne Eingabe ",
"\n	mit [Enter-Taste] oder Leer-Taste",
 "\n  - oder Sie geben Name,Tel.,Mobil,oder",
 "\n  - Code, Adresse ",
"\nSie wedren alles finden 	",
"\n",
"\n2- es scheint eine Wahl Fenster mit alle",
"\n   Kunden, nehmen Sie eine!",
"\n3- nach kunden Auswahl wird eine Abfrage",
"\n   ob ihre wahl Richtig ist [Enter-Drucken]",
"\n4- der Rechnung Erfassung Beginnt",
"\n	-Warencode eingeben",
"\n	-Menge eingeben",
"\n ist eine Ware nicht vorhanden! scheint eine ",
"\n Abfrage Finster zum neue Erfasssung",
"\n -geben sie Waren Code dann Bezeichnung und ",
"\n -den Preis sowie die spalte",
"\n	0-vorspeise 1-Pasta",
"\n	2-Pizza     3-Fisch",
"\n	4-Fleisch   5-Al-Frei",
"\n	6-Alko      7-Spiritus	",
"\n - dann speichern!",
"\n",
"\n5- ist Ihre eingabe nicht korreckt dann",
"\n	- gehen zu Zeile [Bearbeiten]",
"\n	- Sie bekommen eine neue Abfrage",
"\n 	- dabei können Sie die Menge und Text",
"\n	- Sowie den Preis Ändern",
"\n6- Sie können auch eine Zeile samt inhalt auch",
"\n	- löschen drucken Sie zeile [Entfernen]",
"\n7- Vorsicht verwenden Sie nie die [ESC-Taste]",
"\n	Sie werden damit nur Ihre ",
"\n	Eingabe Abbrechen!",
"\n8- um der Rechnung zu drucken ",
"\n	- nach dem Sie Ware und Menge eingegeben",
"\n	- Fragefinster mit dem Maus auf ",
"\n	- [Erfassung-Beenden] clicken!",
"\n",
"\n   - oder [Umschaltrichtungtaste] ",
"\n	(in der 3 Reihe Linksoben)",
"\n   - 2Mal drucken dann [Leere-Taste] drucken",
"\n   - Abfrage Rechnung speichen mit [Enter]drucken",
"\n   - Rechnung ist Abgeschlossen und bereit im drucker",
"\n   - Rechnung Zeile scheint automatisch in der ",
"\n   - Faktore Aktuell Finster in der Rechte Seite",
"\n   - Mousclick auf dieser Zeile zeiget Ihnen der",
"\n   - Inhalt jeder Zeit.",
"\n",
"\n",
"\nA B U C H U N G   O F E N E P O S T E N",
"\n_______________________________________	",
"\n",
"\n1- [Abuchen] drucken",
"\n2- Ofene Posten Zeile auswählen",
"\n3- Mitarbeiter (Code) wählen [Enter]",
"\n4- [OK] drucken",
"\n5- Password [OK] drucken",
"\n6- es scheint der Buchung im Fenster oben",
"\n	-Betrag und Rechnungsdaten",
"\n	-an wenn ist gebucht wurden",
"\n	-Kassbuch Einträge",
"\n7- mit Leerer Taste können Sie dieser Abbuchung",
"\n	weiter tätigen",
"\n",
"\nT A G E S B E R I C H T",
"\n__________________________",
"\n",
"\n1- [Bericht] drucken",
"\n2- Mitarbeiter wählen",
"\n3- [Heute] oder [Monat] [Jahr] drucken",
"\n - Heute> was der Mitarbeiter heute gebucht hat",
"\n - Monat> Sie können Monat nach Monat betrachten",
"\n	- oder drucken",
"\n -Jahr> Ergebnise den ganzen jahren für dieser Ma.",
"\n",
"\nM I T A R B  E T E  R   B E R I C H T ",
"\n____________________________________",
"\n",
"\n1-[F-Bericht] drucken",
"\n2- Mitarbeiter wählen",
"\n3- die gesammt detail scheint am Fenster oben",
"\n",
"\nR E C H N U N G  N A C H D R U C K E N",
"\n______________________________________",
"\n",
"\n1- [Nachdrucken] drucken",
"\n2- [Heutige Rechnung] oder [Rechnugsuchen]",
"\n -[Heutige Rechnung]> eine die Heute getätigte",
"\n   Rechnungen vom Liste wählen.",
"\n -[Rechnugsuchen]>  eine die getätigte",
"\n   Rechnungen vom Liste wählen.",
"\n3- [OK] drucken ist am drucken!",
"\n",
"\n",
"\n",
"\nViel Erfolg wünscht Ihnen EBM-GastroService 2006"};
	return ht;
	}
}
