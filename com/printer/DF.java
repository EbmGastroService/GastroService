// created on 25.11.2004 at 17:21
// created on 13.05.2003 at 00:20
package com.printer;
import com.options.ausTeilen;
import com.units.MyZahl;
import com.units.myDatum;
import com.search.*;
import ebm.firma;
//import com.printer.JComponentVista;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.print.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
//import java.io.*;
 import java.text.*;
 //import javax.swing.table.*;
 //import javax.swing.JTable.*;
 import javax.swing.JTextArea;
// import java.util.*;

public class DF{	
	JComponentVista vista;		
	JTextArea klabel,rlabel;
	String[]Firma;
	String[]Kunde;
	Object[][]Rechnung;	
	String[]Kopf;	
	double Skale;
	String titel;	
	JFrame jf;
	JPanel panel;
 public DF(String titel,String[]firma,String[]kunde,Object[][]rechnung,String[]kopf,double skale ){ 	
 	this.titel=titel; 	
 	Firma=firma;
 	Kunde=kunde;
 	Rechnung=rechnung;
 	Kopf=kopf;	 	
 	Skale= skale;  	
 	jf=new JFrame("Ebm GastroService 2011");
 	panel=new JPanel();
 	panel=init();
 	jf.setContentPane(panel);  		
 	jf.pack();
	jf.setBounds(0, 0, 400,600);	
	jf.setDefaultCloseOperation(2); 	 	  	
 	pb(); 	 	
 	close();
 }
 public DF(){ 	 	
 	panel=new JPanel();
 	jf=new com.options.frame("EbmGastroService Formular Vorschau",60);
 	JPanel mpanel=new JPanel();
 	mpanel.setLayout(new BorderLayout());
 	initDate();
 	init();
 	mpanel.add(panel); 
 	//mpanel.setOpaque(true);
 	//mpanel.setBackground(java.awt.Color.blue);
 	//mpanel.setSize(400,600);
 	jf.getContentPane().add(mpanel);
 	//jf.setContentPane(panel);  		
 	jf.pack();
 	//jf.setSize(550,700);
 	jf.setLocation(250,50);
	//jf.setBounds(0, 0, 400,600);	
	//jf.setDefaultCloseOperation(2); 	 	  	
 	jf.setVisible(true);
 	//close();
 }
 JPanel init(){ 	
 	Border brd = BorderFactory.createMatteBorder(1, 1, 1, 1, Color.white); 	 	
	JTextArea tb=new JTextArea(51,30);
 	tb.setFont(new Font("Courier New",1,14));	
 	String str="\n";	 	
		//str+="\n"+new ausTeilen().randstS(""+Kopf[0],3);	
		str+=new ausTeilen().randstS(""+Kopf[1],5);
		str+=new ausTeilen().randstS(" "+Kopf[2],23);		
		str+=""+new ausTeilen().randstS(" "+Kopf[3],8);		
		str+=" "+new ausTeilen().randstS("     "+Kopf[4],11);		
 		str+="\n";
 	for(int i=0;i<51;i++)str+="-"; 
	str+="\n";	
 	//Rechnungstabelle
	for(int i=0;i<Rechnung.length;i++){
		//str+=new ausTeilen().randstZ(""+(Integer.parseInt(""+Rechnung[i][0])+1),3);	
		str+=""+new ausTeilen().randstS(""+Rechnung[i][1],5);
		str+=" "+new ausTeilen().randstS(""+Rechnung[i][2],23);		
		str+=""+new ausTeilen().randstZ(""+f(""+Rechnung[i][3]),8);		
		double tot=Integer.parseInt(""+Rechnung[i][1])*Double.parseDouble(""+Rechnung[i][3]);
		str+=" "+new ausTeilen().randstZ(""+f(""+tot),11);
		str+="\n";
	}
	tb.append(str); 	
 	//Firmen Logo
 	String mylogo="<html><body>"+
		"<center><font size=+2 face='Ms Serif'>"+Firma[0]+"</font>"+//ZurichCalligraphic
		"<br><font size=3>Tel.: "+Firma[2]+"</font>"+
		"<br><font size=2>"+Firma[1]+"<br>"+Firma[3]+"</font></center></body></Html>";
 	JEditorPane flabel = new JEditorPane("text/html",mylogo);
 	
 	JPanel editor= new JPanel(new BorderLayout());
 	editor.setOpaque(true);
 	editor.setBackground(Color.white);
 	editor.add(flabel);  
  	
  	myDatum md = new myDatum();
 	//rlabel.setFont(new Font("Ms Serif", 1, 12));
 	String mdt=md.time();mdt.substring(0,5);
  	rlabel = new JTextArea("\n R E C H N U N G\n "+Kunde[1]+"\n "+md.d()+"\n "+mdt); 	
 //	klabel.setFont(new Font("Ms Serif", 1, 13));
  	klabel= new JTextArea("\nKdnr: ["+kundennummer()+"] Tel.: "+Kunde[5]+
  	"\n"+Kunde[2]+"\n"+Kunde[3]+"\n"+ Kunde[4]);
 
 	
 	JPanel kdp =new JPanel();
 	kdp.setLayout(new BoxLayout(kdp, BoxLayout.X_AXIS));
 	
 	klabel.setPreferredSize(new Dimension(300, 80));
 	
 	kdp.setBackground(Color.white);
 	rlabel.setBackground(Color.white);//  	 	 	
	kdp.add(klabel,BorderLayout.WEST); 	
 	kdp.add(rlabel,BorderLayout.EAST); 	
 	
 	tb.setPreferredSize(new Dimension(400, 380));
 	JScrollPane scrollpane = new JScrollPane(tb);
 	scrollpane.setPreferredSize(new Dimension(400,380));
 	scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
 	scrollpane.setBorder(brd);
 //	jtf.setBorder(brd);
 	JTextArea jta=new JTextArea();
	JTextArea jtb=new JTextArea();
 	jta.setFont(new Font("Courier New", 1, 14));
 	
 	for(int i=0;i<51;i++)jta.append("_");
 	jta.append("\nIhre Rechnung Summe ist :	 	  "+f(sume())); 	
 	
 	String op=new com.search.sucheDate("gastro/source/op.cfg").md();
	String wy=new com.search.sucheDate("gastro/source/wy.cfg").md();		
	String[]wt=new com.search.sucheDate("gastro/source/wt.cfg").myDaten();
 	
 	String endzeile="<html><body><center><font size=2>"+
		new com.units.WG(Record(),"gastro/Date/Drucker",new com.units.myDatum().ist()).Ausgabe+""+
		"<br>Wir haben f&uuml;r Sie "+op+" ge&ouml;fnet"+
		"<br><b>"+wt[0]+"</b> "+wt[1]+"</font>"+
		"<br><font size=3><b>"+wy+"</b></font></center></body></html>";
 	JEditorPane danke=new JEditorPane("text/html",endzeile); 	
 	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 	
 	//_____________________________________________
 	panel.setBackground(Color.white);
 	panel.add(editor);//1 logo
  	panel.add(kdp); //2  kp=Kundendaten /Rechnungsdaten 	 	
	panel.add(scrollpane);//Rechnugszeilen inhalt
 	panel.add(jta); 	//endsumme
 	panel.add(danke);//öffnungszeiten und werbetext sowie dankzeile
 	panel.setVisible(true); 
 	//panel.setPreferredSize(new Dimension(400,600));
 	return panel;
 	
 }
 public void  pb(){
 	vista = new JComponentVista(panel , new PageFormat()); 
 	vista.scaleToFit(true,Skale); 	
 	PrinterJob pj = PrinterJob.getPrinterJob();
 	pj.setJobName("RN_"+Kunde[1].trim());
 	pj.setPageable(vista);
 	try {//if (pj.printDialog()) { 
 		pj.print();
 		//}
 	} catch (PrinterException e) {System.out.println(e);} 	
 }
 void close(){
 	jf.dispose();
 }
	
 String kundennummer(){
 	int cod=0;
 	String str="";
 	String erg=titel;
 	if(erg.length()>0){
 	for(int i=0; i<erg.length();i++){
 		if(erg.charAt(i)=='X') cod=i;
 	}
 	str = erg.substring(0,cod);
 	}else str=erg;
 	return str;
 }
	
public String f(String str){return new MyZahl().deci(Double.parseDouble(str));}
public Object fo(Object str){str=new MyZahl().deci(Double.parseDouble(str.toString()));return str;}
	//} ;	

public String[]Record(){			
	return new sucheDate("gastro/date/drucker/"+titel+".dat").myDaten();
}
public Object[][]checkdata(Object[][]str){			
	Object[][]rec =null;
	if (str==null){
		Object[][] Rec={{"0","0","0","0","0"},};
		rec=Rec;
	}else
		if (str.length>0)rec=str;
	else
		if(str.length<=0){
			rec=new Object[0][5];
			for(int i=0;i<rec.length;i++){
				for(int x=0;x<rec[0].length;x++){
					rec[i][x]="0";
				}
			}
		}
		else
			if(str.length>0){
				for(int i=0;i<rec.length;i++){
					rec[i][3]=fo(rec[i][3]);
					rec[i][4]=fo(rec[i][4]);
				} 				
			}  
			
			return rec;
		}
		//stellt der Tabele.headerslÃ¤nge und Value um der daten lÃ¤nge
		//wenn die daten lÃ¤nge > die headerslÃ¤nge dan soll die header abgekÃ¼rtzt
		//wenn die daten lÃ¤nge<diehÃ¤derslÃ¤nge dann soll die lÃ¤nge der headers verlÃ¤ngt
		public String[]headersok(Object[][] str){
			int dif = Kopf.length - str[0].length;
			String[] nKopf=null;
 			if(dif>0){ 						//ist der haedrs grÃ¶ÃŸe als die daten
 				nKopf = new String[Kopf.length-dif];
 				int nl=Kopf.length-dif;
 				for(int i=0;i<nl;i++){
 					nKopf[i]=Kopf[i]; 			
 				} 		 				
 			}else
     		 if(dif<0){
     			dif=dif*-1;
 				nKopf = new String[Kopf.length+dif];
 				int nl=Kopf.length+dif;
 				for(int i=0;i<Kopf.length;i++){
 					nKopf[i]=Kopf[i]; 			
 				}
 				for(int i=Kopf.length;i<nl;i++){
 					nKopf[i]=""+i; 			
 				} 		 			
     		 }else nKopf=Kopf;
 			return nKopf;
		}
	public String sume(){
		Object[][]Rec = Rechnung; 			
		String str=""; 			
		double sum = 0.000;
 		int x=0;
 		for(int i=0;i<Rec.length;i++){ 			
 			str=""+0.00; 	
 			int l=Rec[i].length; 		 
 			if ((Rec[i][l-2]!= null)&&(Rec[i][l-1]!= null)&&(Rec[i][l-2]!="")&&(Rec[i][l-1]!="")){	 					
 				str=Rec[i][l-1].toString(); 						 					
 			}	 			
 		try{
 			sum = sum +Double.parseDouble(str); 	 	
 			//}catch(IOException ioe){
 		}catch(NumberFormatException ioe){System.err.println("ZahlenFormat"+ioe);} 		
 		}
 		return(""+sum);
	}
 void initDate(){	
	titel="00000X000185"; 	
 	Firma=new ebm.firma().data();
 	Kunde=new String[]{"K u n d e","R101006/01","Muster Kunde",
   	"Musterstr 100/4","0000 City","xxxx xxxxxxxx"}; 
 	Rechnung=new Object[][]
 	{
   		{"2","313","Tortellini della Casa","5.70","10.40"},
   		{"2","132","Pizza Pollo","6.90","12.80"},
   		{"2","112","Pizza Tonno","6.10","12.80"},   	   		
   		{"2","305","Spaghetti Carbonara","7.20","12.80"},
   		{"2","319","Spaghetti Diavolo","5.40","12.80"},
   		{"2","502","Forelle","6.90","12.80"},
   		{"17","306","Spaghetti Bolognise","5.20","12.80"},
   		{"2","302","Spaghetti alio oligo","5.00","12.80"},
 	    {"2","409","Gemischten Salat mit Thunfisch","5.80","12.80"},
   		{"2","610","Winerschnitzel mit Pommes","7.20","12.80"},
   		{"2","265","Zuppe di Pomodoro","2.80","2.80"},   		
   		{"2","701","Tiramaisu","3.50","7.00"},
   		{"10","801","Cola","1.80","18.00"},
   		{"10","90","Zustellgeb.","2.20","22.00"},
   		{"10","807","Wein","8.70","87.00"},
   		
   	};
 	String[][]rch=new String[Rechnung.length][Rechnung[0].length];
 	for(int i=0;i<rch.length;i++){
 		for(int x=0;x<rch[i].length;x++){
 			rch[i][x]=""+Rechnung[i][x];
 		}
 	} 		
 	String[][]rch1=new String[Rechnung.length][Rechnung[0].length];
 	for(int i=0;i<rch1.length;i++){
 		rch1[i][0]=""+i;
 		rch1[i][1]=""+Rechnung[i][0];
 		rch1[i][2]=""+Rechnung[i][2];
 		rch1[i][3]=""+Rechnung[i][3];
 		rch1[i][4]=""+Rechnung[i][4];
 	} 	
 	for(int i=0;i<rch1.length;i++){
 		for(int x=0;x<rch1[i].length;x++){
 			Rechnung[i][x]=rch1[i][x];
 		}
 	} 		
 	
 	new com.units.save().dfilefelde("gastro/k_r/"+titel+".dat",rch1,false);
 	new com.units.save().dfilefelde("gastro/date/drucker/"+titel+".dat",rch,false);
 	Kopf=new String[]{"Pos","Menge","Bezeichnung","E-Preis","Total"};
 	//Skale=new com.printer.papier().getPapierformat(); 	
   }
}
