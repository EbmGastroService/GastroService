// created on 03.11.2004 at 00:20
package com.units;
import com.search.*;
import com.options.ausTeilen;
import javax.swing.*;
import com.printer.*;
/*
import javax.swing.JTextArea;
import javax.swing.JSplitPane;
import javax.swing.JFrame;
import javax.swing.JFrame.*;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.GridLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.Border;
*/
import java.awt.event.*;
import java.awt.*;
import java.awt.print.*;
import java.io.*;
import java.util.*;
import java.text.*;
public class WVL{
	String[] Ware;//=w.lesen();
	int[] Verkaufsanzahl;//= new int[Ware.length];
	String dir,datum;
	Vector mywv;
	public JTextArea ta,tc;
	public JLabel tf;
	private JComponentVista vista; 
 	JButton printListe=new JButton("A-Drucken");
	JButton printBericht=new JButton("B-Drucken");
	public WVL(String dir,String Datum){
		this.dir=dir;
		datum=Datum;
		ta  = new JTextArea(""); 	
		tc=new JTextArea(""); 	
		tf  = new JLabel(""); 	
		mywv=new Vector();
		//tc.setFont(new Font("Courier New",1,12));
		//tc.setBackground(Color.blue);
		//tc.setForeground(Color.orange);
		Ware=getWare();
		Verkaufsanzahl=getVerkauf();
	}
	public String TD(String str){if(str.length() > 7) return str.substring(3,str.length());else return str;}
	public String[] getWare(){String[]wl=new ebm.WD().lesen();Ware= new String[wl.length]; return Ware=wl;}
	public int[] getVerkauf(){Verkaufsanzahl = new int[Ware.length];return Verkaufsanzahl;}
	public void setVerkauf(int[] verkauf){Verkaufsanzahl=verkauf;}
	public void chekWare(){getWare();getVerkauf();}
	public void Wvinit(){getWare();	getVerkauf();}//notwindig für den Start
	
		//Warenverkauf wird um eine Zeile durchsucht und falls vorhanden die Menge d addiert
	public int[] zahlverkauf(String erg,int d){	// gelesene zeile erg( ist waren code) und ihre menge d	
	for (int i=0; i<Ware.length;i++){
			//die ware zeile code, bez.,preis und ich will nur code
			String[] aerg = new ausTeilen().komma(""+Ware[i]);	//warencode zb 155 od 911 
			//aerg[0] ist der Code, aerg[1] ist Bezeichnung
			if(aerg[0].trim().equals(erg)){Verkaufsanzahl[i] = Verkaufsanzahl[i]+d;}// wenn code ist erg code dann erhöhe mit d
		}
		return Verkaufsanzahl;
	}
	//Verkaufsanzahl intialisierung zum 0
	public void vkzero(){for (int i=0; i<Verkaufsanzahl.length;i++){Verkaufsanzahl[i]=0;}}
	//offnet die Tagsverkauf und zählt die neue zeilen dazu
	//daher wird zuerst zeilen vergleich geführt und zusammengefasst falls möglich ist
	//ZB: 2 Cardinale..\n 1 Salami..\n 1 Cardinale.. wird in 3 Cardinale..\n 1 Salami..\n
	//also Abkurzung der Arrayfelder um die doplikaten. 
	public int[] open_warenverkauf(){		
		sucheDate su=new sucheDate(dir+"/warenverkauf.dat");
		String[] str = su.myDaten();	//Zeileninhalt inkl komma in Arrayfelder
		ausTeilen a= new ausTeilen();	//jeder Zeileninhalt wird in Arrayfelder getrint
		for(int i=0;i<str.length;i++){					
			//System.out.println("\nopenverkauf str[i]:"+str[i]);
			if(str[i]!=null){
				String[] aerg = a.komma(""+str[i]);	//aerg[0] ist der Menge, aerg[1] ist Warencode
				zahlverkauf(""+aerg[1].trim(),new Methoden().Int(""+aerg[0].trim()));//prüft ob warecod! und erhöht die menge 			
			}
		}
		return Verkaufsanzahl;		
	}
	//offnet die Tagsverkauf und zählt die neue zeilen dazu
	//daher wird zuerst zeilen vergleich geführt und zusammengefasst falls möglich ist
	//ZB: 2 Cardinale..\n 1 Salami..\n 1 Cardinale.. wird in 3 Cardinale..\n 1 Salami..\n
	//also Abkurzung der Arrayfelder um die doplikaten. 	
	//die zusammengefassten Warenzeile i werden neu berechnet 
	public double warenverkaufswert(int i){// i ist warencode
		double sume=0;		
		ausTeilen a= new ausTeilen();		
			if(Verkaufsanzahl[i]>0){
				if (Ware[i]!=null){
					String[] aerg = a.komma(""+Ware[i]);// ware[i]= code,bez,preis und ich will Preis
					sume = sume+(Verkaufsanzahl[i]* Double.parseDouble(""+aerg[2]));
				}
			}							
		return sume;
	}
	//fügt eine Zeile zum Warenverkaufsregister
	public void save_warenverkauf(String str,boolean b){// str= warencode,menge b= false=file neue schreiben
		String file=dir+"/warenverkauf.dat";		
		new save().file(file,str,b);		
	}
	public void save_TV(String[]str,boolean b){
		//if(str.length>0){
		myDatum md=new myDatum();		
		new save().filefelde(dir+"/Data"+md.J()+"/"+"TV"+TD(datum)+"/Tv"+datum+".dat",str,b);
		//}
	}
	
	public String[]open_TV(){//open tagesverkauf
		myDatum md=new myDatum();
		ta.setText("");
		float sume=0;		
		int artanzahl=0;	
		ausTeilen a = new ausTeilen();
		Methoden meth=new Methoden();		
		String file=dir+"/Data"+md.J()+"/"+"TV"+TD(datum)+"/Tv"+datum+".dat";
		sucheDate su=new sucheDate(file);
		String[] str =su.myDaten();							
		str=Artiklnzusammenfassen(str,1);				 			
		for (int i=0; i<str.length;i++){
		 if(meth.nichtnull(str[i])){			
			String[] aerg = a.koma(""+str[i]);			
		 	ta.append(""+a.randstZ(""+meth.fi(""+aerg[0].trim()),4));			 	
		 	if(meth.nichtnull(""+aerg[1]))		 			
		 	ta.append("	"+a.randstZ(""+aerg[1],5));		 					 	
		 	ta.append("  "+a.randstS(""+aerg[2],20));		 			
		 	ta.append("	"+a.randstZ(""+meth.f(""+aerg[3]),10));		 			
		 	ta.append(" "+a.randstZ(""+meth.f(""+aerg[4]),15));	
		 	ta.append("\n");	
			sume = sume+(Float.parseFloat(""+aerg[4].trim()));
			artanzahl=artanzahl+meth.Int(""+aerg[0].trim());
		 }		 		
		}
		if(artanzahl>0){
		ta.append(" _________________________________________________________________\n");	
		ta.append(" "+artanzahl+a.randstS(""+" Artikeln, Umsatz "+datum.replace('_','.')+" ist:",44)+a.randstZ(""+new Methoden().f(""+sume),19)+"\n\n");	
		}
		tf.setText("<html><font size=+1><center>"+"Tagesverkauf, ("+artanzahl+") Artikln in Euro: "+meth.f(""+sume)+
		               "</center></font></html>");
		new save().dontsort(file,str,false);//neue Registrieren
		
		return str;
	}
	//lest die Monatsliste und fasst sie zusammen, dann neue Registrieren
	public String[] open_Monatwarenverkauf(){				
	//	ta.setText("");
		double sume=0;
		int artanzahl=0;		
		myDatum md=new myDatum();
		ausTeilen a= new ausTeilen();		
		String file=dir+"/Data"+md.J()+"/Wv"+TD(datum)+".dat";
		sucheDate su=new sucheDate(file);		
		String[] str = su.myDaten();									
		str=Artiklnzusammenfassen(str,1);		
		//for(int i = 0;i<str.length;i++){if(nichtnull(str[i]))System.out.println("\n"+str[i]);	}	 			
		for (int i=0; i<str.length;i++){
		 if(new Methoden().nichtnull(str[i])){			
			String[] aerg = a.komma(""+str[i]);			
		 	Methoden meth=new Methoden();
			ta.append(""+a.randstZ(""+meth.fi(""+aerg[0].trim()),5));			 	
		 	if(meth.nichtnull(""+aerg[1]))		 			
		 	ta.append("	"+a.randstZ(""+aerg[1],6));		 					 	
		 	ta.append("  "+a.randstS(""+aerg[2],20));		 			
		 	ta.append("	"+a.randstZ(""+meth.f(""+aerg[3]),10));		 			
		 	ta.append(" "+a.randstZ(""+meth.f(""+aerg[4]),15));	
		 	ta.append("\n");	
			sume = sume+(Double.parseDouble(""+aerg[4]));
		 	artanzahl=artanzahl+meth.Int(""+aerg[0].trim());
		 }		 		
		}
			tf.setText("<html><font size=+1><center> Monat Absatz  "+artanzahl+"  Artikeln   Umsatz "+new Methoden().f(""+sume)+
			           "</center></font></html>");
		 ta.append(" ________________________________________________________________________\n");	
		// ta.append("\n "+"Monat Absatz: 	"+artanzahl+"  	Artikeln   Umsatz:	 "+new Methoden().f(""+sume)+"\n");	
		 ta.append(" "+artanzahl+a.randstS(""+" Artikeln  und gesamt Monat Umsatz ist:",44)+a.randstZ(""+new Methoden().f(""+sume),17)+"\n");	
	//	new save().dontsort(file,str,false);//neue Registrieren
		return str;
	}
	//fügte eine 
	public void save_Monatwarenverkauf(boolean b){	//true =add false = entleeren	
		//sucheDate su=new sucheDate("KDate/warenverkauf.dat");
		vkzero();
		myDatum md=new myDatum();
		String[] str = Tagesverkauf();// su.myDaten();
		String file=dir+"/Data"+md.J()+"/Wv"+TD(datum)+".dat";
		new save().dontsort(file,str,b);
	}	
	//offnet die Tagsverkauf und zählt die neue zeilen dazu
	//daher wird zuerst zeilen vergleich geführt und zusammengefasst falls möglich ist
	public void Tv(){				
		open_warenverkauf();		
		Methoden meth=new Methoden();		
		String[]Wv=new String[meth.datalength(Verkaufsanzahl)];
		double sume=0;
		int x=0;
		save_warenverkauf("",false);//neue zusammenfassung
		for (int i=0; i<Ware.length;i++){
			if(Verkaufsanzahl[i]>0){
				double wert = warenverkaufswert(i);
				Wv[x]=(Verkaufsanzahl[i]+","+Ware[i]+","+wert);
				//tap("\n"+elementieren(Verkaufsanzahl[i]+", "+Ware[i])+"    "+f(""+wert));
			//	System.out.println(elementieren(Verkaufsanzahl[i]+", "+Ware[i])+"  "+f(""+wert));
				sume=sume+ wert;
				save_warenverkauf((Verkaufsanzahl[i]+","+Ware[i]+","+wert),true);		
				x++;
			}
		}
		//tf.setText("	Waren Verkaufswert in  "+f(""+sume));
		//return Wv;
	}
	public String[]Tagesverkauf(){			
	//	Tv();//die daten werden zusammengefasst
		//ta.setText("");							
		float sume=0;		
		int artanzahl=0;	
		ausTeilen a = new ausTeilen();
		Methoden meth=new Methoden();		
		String file=dir+"/warenverkauf.dat";
		sucheDate su=new sucheDate(file);
		String[] str =su.myDaten();							
		//String[] str =su.myDaten();							
		//String[]str = k.Tagesverkauf();
		for (int i=0; i<str.length;i++){
		 if(meth.nichtnull(str[i])){			
			String[] aerg = a.komma(""+str[i]);			
		 	ta.append(""+a.randstZ(""+meth.fi(""+aerg[0].trim()),4));			 	
		 	if(meth.nichtnull(""+aerg[1]))		 			
		 	ta.append("	"+a.randstZ(""+aerg[1],5));		 					 	
		 	ta.append("  "+a.randstS(""+aerg[2],20));		 			
		 	ta.append("	"+a.randstZ(""+meth.f(""+aerg[3]),10));		 			
		 	ta.append(" "+a.randstZ(""+meth.f(""+aerg[4]),15));	
		 	ta.append("\n");	
			sume = sume+(Float.parseFloat(""+aerg[4].trim()));
			artanzahl=artanzahl+meth.Int(""+aerg[0].trim());
		 }			
		}
		if(artanzahl>0){
		ta.append(" _________________________________________________________________\n");	
		ta.append(" "+artanzahl+a.randstS(""+" Artikeln, Umsatz "+datum.replace('_','.')+" ist:",44)+a.randstZ(""+new Methoden().f(""+sume),19)+"\n\n");	
		}
		//ta.append("\n_______________________________________________________________________________");	
		// ta.append("\n"+datum+" Tagesverkauf: 	"+artanzahl+"  	Artikeln   Umsatz:	 "+new Methoden().f(""+sume)+"\n");	
		tf.setText("<html><font size=+1><center>"+"Tagesverkauf, ("+artanzahl+") Artikln in Euro: "+meth.f(""+sume)+
		               "</center></font></html>");
		
		return str;
	}
	//ist ein Artikel öfter vorgekommen, dann zähler denen zusammen zu eine Zeile
	// da jeder Zeile enthält Menge,Bezeichnung,Preis und Wert
	// dann muss die mengezusammengefasst und den Wert neue berechnet werden
	public String[] Artiklnzusammenfassen(String[] str,int spalte){
		ausTeilen a = new ausTeilen();
		Methoden meth=new Methoden();
		String[] va = new String[str.length];
		int[] iva = new int[va.length];					
		int n=0;
		for(int x = 0;x<str.length;x++){						
		 if(meth.nichtnull(str[x])){		
		 	String zeile=""+str[x].trim();//zeile zum vergleichen
		 	String[] feld=a.komma(zeile.trim());//teile die zeile in felder
			int menge =meth.Int(""+feld[0].trim());//die Menge die Zeile			 
		 	//vegleiche position x mit alle positionen m ab zeile x
		 	//zeile x gleich zeile m,dann übergebe zum Register va,
		 	//und erhöhe die Menge mit dieser Menge
		 	//setze nach dem tausch str auf null, sonst erhöht sich die Ergbnisse
			for(int m = x;m<str.length;m++){				
			  if(meth.nichtnull(str[m])){			  	
				String[] nfeld=a.komma(""+str[m]);
			  	int nMenge =meth.Int(""+nfeld[0].trim());						  	
				if(nfeld[spalte+1].trim().equals(feld[spalte+1].trim())){
					str[m]=null;			
					iva[n]=iva[n]+nMenge;
					if (spalte==1)//für Monatsabrechnung-sehe open_Monatsabrechnung
					va[n] = iva[n]+","+nfeld[1].trim()+","+nfeld[2].trim()+","+nfeld[3].trim()+","+(iva[n]*Double.parseDouble(""+nfeld[3].trim()));
					if (spalte==0)//für Tischinhalt-sehe tischgechekt()
					va[n] = iva[n]+","+nfeld[1].trim()+","+nfeld[2].trim()+","+(iva[n]*Double.parseDouble(""+nfeld[2].trim()));
					//System.out.println("\nv[n] "+va[x]);
				}
			  }
			}
			n++;
		 }
		}
		n=0;
		//nach dem Vergleich bleibenende werte werden ausgeschnitten und in va übergeben
		for(int x = 0;x<str.length;x++){if(meth.nichtnull(va[x]))n++;}	//bis Welche zeile ist voll		
		for(int x = 0;x<str.length;x++){						
			if(meth.nichtnull(str[x])){va[n++]=str[x];}
		}
		return va;
	}
	public JFrame zeige(String str){		
		myDatum md=new myDatum();
		vkzero();         	
		ta.setText("");
		String lb="Ansicht ";//Tagesabsatz  "+datum.replace('_','.');
		if (str.equals("M")){
			lb+="Monatabsatz   "+TD(datum).replace('_','.');//save_Monatwarenverkauf(true);
			open_Monatwarenverkauf();
		}
		if (str.equals("T")){lb+="Tagesverkauf!";Tagesverkauf();}
		if (str.equals("O")){lb+="Tagesabsatz";open_TV();}
		JFrame jf=new JFrame(lb);		
		jf.getContentPane().setLayout(new BorderLayout());
		
	 ta.setEditable(false);	
	ta.setFont(new Font("Courier New",0,12));//Font.ITALIC, 12));	
	tc.setFont(new Font("Courier New",0, 12));			
	ta.setBounds(0, 0, 500, 520);	
	tc.setBounds(0, 0, 500, 520);		
	//tc.setFont(new Font("Courier New",0,12));	
	//setPreferredSize(new Dimension(590, 540));	
	JScrollPane js=new JScrollPane(ta);	
	JScrollPane jc=new JScrollPane(tc);		
	//tc.setPreferredSize(new Dimension(580, 380));	
	js.setPreferredSize(new Dimension(500, 520));
	jc.setPreferredSize(new Dimension(500, 520));
	printListe.addActionListener( new drucken());
	printBericht.addActionListener( new drucken());			
	JPanel links=new JPanel(new BorderLayout());	
	//links.setPreferredSize(new Dimension(480, 480));
	links.add(js,BorderLayout.PAGE_START);
	links.add(printListe,BorderLayout.PAGE_END);
	JPanel rechts=new JPanel(new BorderLayout());	
	//rechts.setPreferredSize(new Dimension(480, 480));	
	rechts.add(jc,BorderLayout.PAGE_START);	
	rechts.add(printBericht,BorderLayout.PAGE_END);
		
	js.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Verkaufsliste"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));	
	jc.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Umsatz und Ust Verteilung"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));	
	JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,links,rechts); //VERTICAL_SPLIT,js,jc);
    splitPane.setOneTouchExpandable(true);
    splitPane.setResizeWeight(0.5);
    JPanel haupt = new JPanel(new BorderLayout());//new GridLayout(1,0));
    //haupt.add(js,BorderLayout.PAGE_START);//CENTER);
	haupt.add(splitPane,BorderLayout.PAGE_START);	
    haupt.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createTitledBorder("Umsatze und Ust"),
                        BorderFactory.createEmptyBorder(5,5,5,5)));
	//haupt.pack();		
  	haupt.setVisible(true);  	  		
	jf.getContentPane().add(new JLabel(
		               "<html>"+
		               "<font size=+2 font color=red>"+
		               "<center>"+lb+"</center>"+
		               "</font>"+"</html>",JLabel.CENTER),BorderLayout.PAGE_START);	
	jf.getContentPane().add(haupt,BorderLayout.CENTER);				
	jf.getContentPane().add(tf,BorderLayout.PAGE_END); 			
  	jf.pack();	
	jf.setBounds(5, 5,1000, 650);	
	jf.setVisible(true);  	  		
 	jf.setDefaultCloseOperation(2);     				
	return jf;
	}		 
	void pb(){		
		//ta.setBounds(0, 0, 480, 600);			
		PrinterJob pj=PrinterJob.getPrinterJob();
         // pj.setCopies(2);
		  pj.setJobName("UA"+datum);
        //  pj.setPrintable(vista);
		pj.setPageable(vista);
         try{ 
          	if (pj.printDialog()){
            	pj.print();          		
          	}
         }catch (Exception PrintException) {System.err.println("drucker: "+datum);};
		
	}
	 class drucken implements ActionListener {        
      public void actionPerformed ( ActionEvent e ) {            	
      	String t = e.getActionCommand();
      	if(t.equals("A-Drucken")){
      		vista = new JComponentVista(ta,new PageFormat()); 
      		//vista.scaleToFitY();
      		//vista.setScale(0.6, 0.5);
      		vista.scaleToFitX();//true,0.650);
      		pb();
      	}
      	if(t.equals("B-Drucken")){
          vista = new JComponentVista(tc,new PageFormat()); 
      		//vista.scaleToFitX();
      		//vista.setScale(0.5, 0.5);
      		vista.scaleToFit(true,0.650);
          pb();
        }
	 }		
}
}
