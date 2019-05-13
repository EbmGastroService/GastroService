// created on 16.07.2006 at 00:13

//Author Mourad El bakry
//Vianna
//EditorPane
package com.options;
import javax.swing.*;

import javax.swing.event.*;
import javax.swing.text.html.*;
import java.awt.*;
import java.net.URL;
public class EP{
	JEditorPane editor;
	String url;
	public EP(String text){
		frame f=new com.options.frame("Ebm GastroService 2006|geschlossen in 60 Secunden",60);//Secunden
		if(text.length()<10)text=help();	
		
		try{
			editor = new JEditorPane("text/html",text);
			editor.addHyperlinkListener(new Hyperactive());
			editor.setEditable(false);
		}catch(Throwable t){t.printStackTrace();}
		
		f.setContentPane(new JScrollPane(editor)); 		
		//f.setSize(500,700);
		f.pack();
		f.setVisible(true);		
	}
	public EP(int i,String text){
		frame f=new com.options.frame("EbmGastroService-Seite("+i+")|geschlossen in 60 Secunden",60);//Secunden
		if(text.length()<10)text=help();	
		
		try{
			editor = new JEditorPane("text/html",text);
			editor.addHyperlinkListener(new Hyperactive());
			editor.setEditable(false);
		}catch(Throwable t){t.printStackTrace();}
		
		f.setContentPane(new JScrollPane(editor)); 		
		int x=i*20;
		i=i*200;		
		f.setSize(500,750);
		f.setLocation(i,x);
		f.setVisible(true);		
	}
	public EP(String Url,int i){
		url=Url;	
		try{	
			editor = new JEditorPane(url);
			editor.setPage(url);
			editor.setEditable(false); 
			editor.addHyperlinkListener(new Hyperactive());
		}catch(Throwable t){t.printStackTrace();}
		
		frame f=new com.options.frame("Ebm GastroService 2006|geschlossen in 5 Minuten",300);		
		f.setContentPane(new JScrollPane(editor)); 
		f.pack();
	//	f.setSize(700,600);
		f.setVisible(true);		
	}
	class Hyperactive implements HyperlinkListener { 
        public void hyperlinkUpdate(HyperlinkEvent e) {
 	          if (e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
 		      JEditorPane pane = (JEditorPane) e.getSource();
 		      if (e instanceof HTMLFrameHyperlinkEvent) {
 		          HTMLFrameHyperlinkEvent  evt = (HTMLFrameHyperlinkEvent)e;
 		          HTMLDocument doc = (HTMLDocument)pane.getDocument();
 		          doc.processHTMLFrameHyperlinkEvent(evt);
 		      } else {
 		          try {
 			      pane.setPage(e.getURL());
 		          } catch (Throwable t) {
 			      t.printStackTrace();
 		          }
 		      }
 	          }
 	      }
     }
     String help(){
     	String[]help= new com.search.sucheDate("gastro/date/source/hilp.html").myDaten();
     	
		String helpstr="";
     		if(help==null||help.length==0){     			
     			helpstr=html;new com.units.save().file("gastro/date/source/hilp.html",helpstr,false);
     		}else{     	
     			for(int i=0; i<help.length; i++)
     				helpstr = helpstr+""+help[i];
     		}
     	return helpstr;
     }
    String html= 
"<!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.0 Transitional//EN'>"+
"<HTML><HEAD><meta http-equiv='content-language' content='de'>"+
//"<meta http-equiv=Content-Type content=text/html; charset=ISO-8859-1>"+
"</HEAD>"+
"<BODY style='FONT-SIZE: 14pt' bgColor=#ffffcc>"+
"<P><B><FONT color=#800000 size=7>E</FONT>BM</B> <B><FONT color=#800000 size=7>"+
"G</font>astro<FONT color=#800000 size=7>S</font>ervice&copy;2006</B></P>"+
"<BLOCKQUOTE>"+
"<P><FONT color=#ffff00><U><B><SPAN style='BACKGROUND-COLOR: #800000'>R E C H N "+
"U N G&nbsp; E R F A S S U N G </SPAN></B></U></FONT><FONT color=#000080><SPAN "+
"style='FONT-WEIGHT: 700; BACKGROUND-COLOR: #800000'><BR></SPAN></FONT><BR>Drucken "+
"Sie <FONT color=#800000>[Faktura] </FONT>und folgen sie<BR>die Abfrage "+
"Fenstern.</P>"+
"<P><BR>1 - <FONT color=#008080>Kunde W&auml;hlen</FONT> (kann auch ohne Eingabe "+
"<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; mit <FONT "+
"color=#000080>[Enter-Taste] </FONT>oder Leer-Taste<BR>- oder Sie geben Name, "+
"Tel., Mobil, oder<BR>- Code, Adresse <BR>Sie werden alles finden <BR><BR>2- Es "+
"scheint eine Wahl Fenster mit alle<BR>Kunden, nehmen Sie eine!<BR>&nbsp;</P>"+
"<P>3 - nach <FONT color=#000080>Kunden</FONT> Auswahl wird eine Abfrage<BR>ob "+
"ihre Wahl Richtig ist <FONT color=#000080>[Enter-Drucken]<BR>&nbsp;</FONT></P>"+
"<P>4 - der <FONT color=#008080>Rechnung Erfassung "+
"Beginnt</FONT><BR>&nbsp;&nbsp;&nbsp; - <FONT color=#008080>Warencode "+
"</FONT>eingeben<BR>&nbsp;&nbsp;&nbsp; - <FONT color=#008080>Menge</FONT> "+
"eingeben<BR>&nbsp;&nbsp;&nbsp; - <FONT color=#000080>[Enter] "+
"</FONT>drucken</P>&nbsp;&nbsp;&nbsp; Ist eine <FONT color=#800000>Ware nicht "+
"vorhanden! </FONT>scheint eine <BR>&nbsp;&nbsp;&nbsp; Abfrage Fenster zur neue "+
"Erfassung<BR>&nbsp;&nbsp;&nbsp; - geben sie <FONT color=#000080>Waren "+
"Code</FONT> dann Bezeichnung und "+
"<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; den Preis sowie die "+
"spalte<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 0 - vorspeise 1 - "+
"Pasta<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 2 - "+
"Pizza&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 3 - "+
"Fisch<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 4 - "+
"Fleisch&nbsp;&nbsp;&nbsp;&nbsp; 5 - Alko - "+
"Frei<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 6 - "+
"Alko&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 7 - Spiritus "+
"<BR>&nbsp;&nbsp;&nbsp; - dann <FONT color=#000080>speichern!</FONT><BR><BR>5 - "+
"Ist Ihre <FONT color=#800000>Eingabe nicht korrekt</FONT> "+
"dann<BR>&nbsp;&nbsp;&nbsp; - gehen Sie zu Zeile <FONT "+
"color=#000080>[Bearbeiten]</FONT><BR>&nbsp;&nbsp;&nbsp; - Sie bekommen eine "+
"neue Abfrage<BR>&nbsp;&nbsp;&nbsp; - dabei k&ouml;nnen Sie die Menge und "+
"Text<BR>&nbsp;&nbsp;&nbsp; - Sowie den Preis &auml;ndern "+
"<P></P>"+
"<P><BR>6 - Sie k&ouml;nnen auch eine Zeile samt Inhalt<BR>&nbsp;&nbsp; - l&ouml;schen "+
"drucken Sie Zeile <FONT color=#000080>[Entfernen]</FONT></P>"+
"<P><BR><FONT color=#800000>7 - Vorsicht verwenden Sie nie die "+
"[Esc-Taste]<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Sie werden damit nur "+
"Ihre <BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Eingabe "+
"Abbrechen!</FONT></P>"+
"<P><BR>8 -&nbsp; <FONT color=#008080>Rechnung&nbsp; drucken "+
"</FONT><BR>&nbsp;&nbsp;&nbsp; - nach dem Sie Ware und Menge eingegeben "+
"haben<BR>&nbsp;&nbsp;&nbsp; - Fragefenster mit dem Maus auf "+
"<BR>&nbsp;&nbsp;&nbsp; - [<FONT color=#000080>Erfassungs-Beenden] "+
"</FONT>Klicken!<BR>&nbsp;&nbsp;&nbsp; - oder <FONT color=#000080>[Umschalt "+
"Richtungstaste] </FONT><BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; ( in der "+
"3 Reihe Links oben)<BR>&nbsp;&nbsp;&nbsp; - <FONT color=#000080>2Mal "+
"</FONT>drucken dann <FONT color=#000080>[Leere-Taste] "+
"</FONT>drucken<BR>&nbsp;&nbsp;&nbsp; - Abfrage Rechnung speichern mit<FONT "+
"color=#000080> [Enter] </FONT>drucken<BR>&nbsp;&nbsp;&nbsp; - Rechnung ist "+
"Abgeschlossen und bereit im Drucker<BR>&nbsp;&nbsp;&nbsp; - Rechnung Zeile "+
"scheint automatisch in der <BR>&nbsp;&nbsp;&nbsp; - <FONT "+
"color=#008080>(Faktore Aktuell Fenster),</FONT> in der Rechte "+
"Seite<BR>&nbsp;&nbsp;&nbsp; - Mausklick auf dieser Zeile zeiget Ihnen "+
"der<BR>&nbsp;&nbsp;&nbsp; - Inhalt jeder Zeit.<BR><BR><BR><B><U><FONT "+
"color=#ffff00><SPAN style='BACKGROUND-COLOR: #800000'>A B U C H U N G&nbsp; O "+
"F F E N E&nbsp; P O S T E N</SPAN></FONT><FONT color=#000080><SPAN "+
"style='BACKGROUND-COLOR: #00ffff'><BR></SPAN></FONT></U></B><BR>1 - <FONT "+
"color=#000080>[ Abbuchen] </FONT>drucken<BR>2 - Offene Posten Zeile "+
"ausw&auml;hlen<BR>3 - Mitarbeiter (<FONT color=#008080>Code</FONT>) w&auml;hlen <FONT "+
"color=#000080>[Enter]</FONT><BR>4 - <FONT color=#000080>[OK]</FONT> "+
"drucken<BR>5 - Passwort <FONT color=#000080>[OK] </FONT>drucken<BR>6 - Es "+
"scheint der Buchung im Fenster oben<BR>&nbsp;&nbsp;&nbsp; - Betrag und "+
"Rechnungsdaten.<BR>&nbsp;&nbsp;&nbsp; - An wenn ist gebucht "+
"wurden?<BR>&nbsp;&nbsp;&nbsp; - Kassabuch Eintrag.<BR>7 - Mit <FONT "+
"color=#000080>[Leerer Taste] </FONT>k&ouml;nnen Sie dieser "+
"Abbuchung<BR>&nbsp;&nbsp;&nbsp;&nbsp; weiter t&auml;tigen<BR><BR><B><FONT "+
"color=#ffff00><SPAN style='BACKGROUND-COLOR: #800000'>T A G E S B E R I C H "+
"T</SPAN></FONT><FONT color=#000080><SPAN "+
"style='BACKGROUND-COLOR: #00ffff'><BR></SPAN></FONT></B><BR>1- <FONT "+
"color=#000080>[Bericht] </FONT>drucken<BR>2- Mitarbeiter w&auml;hlen<BR>3- <FONT "+
"color=#000080>[Heute],</FONT> <FONT color=#000080>[Monat]</FONT> oder<FONT "+
"color=#000080> [Jahr] </FONT>drucken<BR>&nbsp;&nbsp;&nbsp; - <FONT "+
"color=#008080>Heute:</FONT> was der Mitarbeiter heute gebucht "+
"hat<BR>&nbsp;&nbsp;&nbsp; - <FONT color=#008080>Monat:</FONT> Sie k&ouml;nnen Monat "+
"nach Monat betrachten<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; oder "+
"drucken<BR>&nbsp;&nbsp;&nbsp; -<FONT color=#008080> Jahr:</FONT> Ergebnisse "+
"den ganzen Jahren f&uuml;r den Mitarbeiter.<BR><BR><FONT color=#ffff00><SPAN "+
"style='FONT-WEIGHT: 700; BACKGROUND-COLOR: #800000'>M I T A R B E T E R&nbsp; "+
"B E R I C H T <BR></SPAN></FONT><BR>1 - <FONT color=#000080>[F-Bericht]</FONT> "+
"drucken<BR>2 - Mitarbeiter w&auml;hlen<BR>3 - Die gesamt detail scheint am Fenster "+
"oben<BR><BR><FONT color=#ffff00>"+
"<SPAN style='FONT-WEIGHT: 700; BACKGROUND-COLOR: #800000'>"+
"R E C H N U N G&nbsp; N A C H D R U C K E N<BR></SPAN></FONT>"+
"<BR>1 - <FONT color=#000080>[Nachdrucken] "+
"</FONT>drucken<BR>2 - <FONT color=#000080>[Heutige Rechnung] </FONT>oder "+
"<FONT color=#000080>[Rechnung suchen]</FONT><BR>&nbsp;&nbsp; -<FONT color=#000080> "+
"[Heutige Rechnung] </FONT>eine die Heute "+
"get&auml;tigte<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Rechnungen vom Liste "+
"w&auml;hlen.<BR>&nbsp;&nbsp; - <FONT color=#000080>[Rechnung suchen] </FONT>eine "+
"die get&auml;tigte<BR>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; Rechnungen vom "+
"Liste w&auml;hlen.<BR>3 - <FONT color=#000080>[OK]</FONT> drucken ist am "+
"drucken!<BR><BR>Viel Erfolg w&uuml;nscht Ihnen "+
"<FONT color=#800000 size=5>E</FONT><FONT size=4>bm</FONT> <FONT color=#800000 size=5>G</FONT>"+
"<FONT size=4>astro</FONT><FONT color=#800000 size=5>S</FONT><FONT "+
"size=4>ervice</FONT>&copy;2006</P>"+
"<P><FONT color=#ffff00 size=3><SPAN style='BACKGROUND-COLOR: #800000'>Copyright&copy;Mourad El Bakry, Vianna "+
"2003-2006</SPAN></FONT>"+
"</P></BLOCKQUOTE></BODY></HTML>'";

}
