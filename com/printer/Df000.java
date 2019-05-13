// created on 25.11.2004 at 17:21
// created on 13.05.2003 at 00:20
//package com.printer;
// created on 25.11.2004 at 17:21
// created on 13.05.2003 at 00:20
//package com.printer;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
import ebm.firma;
import com.printer.JComponentVista;

import java.awt.*;
import java.awt.GridLayout;
import java.awt.print.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.*;
//import java.io.*;
 import java.text.*;
 import javax.swing.table.*;
 import javax.swing.JTable.*;
 import javax.swing.JTextArea;
// import java.util.*;

public class Df000 extends JFrame {	
	JComponentVista vista;
	JPanel kdp;
	JPanel panel=new JPanel(new BorderLayout());
	JTextArea flabel,klabel,rlabel;
	String[]Firma;
	String[]Kunde;
	Object[][]Rechnung;	
	String[]Kopf;	
	double Skale;
	public Df000(String titel){super(titel); 			}
	
 public void druck(String[]firma,String[]kunde,Object[][]rechnung,String[]kopf,double skale ){ 	
 	Firma=firma;
 	Kunde=kunde;
 	Rechnung=rechnung;//checkdata(rechnung);
 	Kopf=kopf;	
 	//Kopf=headersok(Rechnung);	 	
 	Skale= skale; 
 	setFont(new Font("Courier New",1,10));
     //final String[] headers = Kopf;
     //final Object[][] data = Rechnung;
	JTextArea tb=new JTextArea(51,30);
 	tb.setFont(new Font("Courier New",1,14));
	JTextArea jthead=new JTextArea(1,2);
 	String str="";	
 	for(int i=0;i<51;i++)str+="-";
		str+="\n"+new ausTeilen().randstS(""+Kopf[0],3);	
		str+=" "+new ausTeilen().randstS(""+Kopf[1],5);
		str+=" "+new ausTeilen().randstS(""+Kopf[2],24);		
		str+=" "+new ausTeilen().randstS(""+Kopf[3],7);		
		str+=" "+new ausTeilen().randstS(""+Kopf[4],10);		
 		str+="\n";
 	for(int i=0;i<51;i++)str+="-";//jthead.append("\n-------------------------------------------------------------");//+
	str+="\n";//"-------------------------------------------------------------");

	//String str="";
	for(int i=0;i<Rechnung.length;i++){
		str+=new ausTeilen().randstZ(""+Rechnung[i][0],3);	
		str+=new ausTeilen().randstZ(""+Rechnung[i][1],5);
		str+=" "+new ausTeilen().randstS(""+Rechnung[i][2],24);		
		str+=new ausTeilen().randstZ(""+f(""+Rechnung[i][3]),7);		
		double tot=Integer.parseInt(""+Rechnung[i][1])*Double.parseDouble(""+Rechnung[i][3]);
		str+=new ausTeilen().randstZ(""+f(""+tot),10);
		str+="\n";
	}
	tb.append(str);
   
    Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.white); 	

  	flabel= new JTextArea("	 "+Firma[0]+
  	                      "\n	  "+Firma[1]+
  	                      "\n  	"+Firma[2]);
 	flabel.setFont(new Font("Courier New", 1, 18));
 	//flabel.setPreferredSize(new Dimension(480, 80));
  	myDatum md = new myDatum();

  	rlabel = new JTextArea("\nR E C H N U N G\n"+Kunde[1]+"\n"+md.d()+"\n"+md.time());
 	rlabel.setFont(new Font("Serif", 1, 12));
  	klabel= new JTextArea("\n"+Kunde[0]+"\n"+Kunde[2]+"\n"+Kunde[3]+"\n"+ Kunde[4]);
 	klabel.setFont(new Font("Serif", 1, 12));
 	tb.setBorder(brd);
 	tb.setBackground(Color.white); 	
 	kdp =new JPanel(new GridLayout(0,3));
 	JPanel kfp = new JPanel(new BorderLayout());//new BorderLayout());//new GridLayout(0,3));
 	//kfp.setLayout(new BoxLayout(kfp, BoxLayout.X_AXIS));
 	
 	//JPanel kep = new JPanel(new GridLayout(1,3));
 	//kep.setLayout(new BoxLayout(kep, BoxLayout.X_AXIS));
 	panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
 	JPanel kp = new JPanel(new BorderLayout());
 	kp.setBackground(Color.white); 	 	
 	panel.setBackground(Color.white);
 	kdp.setBackground(Color.white);
 	kfp.setBackground(Color.white);
 	//kep.setBackground(Color.white);//
 	rlabel.setBackground(Color.white);//  	 	
 	//kdp.add(new JLabel("<html><b><font size=1>" +"<east>"+
 	                  // "Name:<br> Adresse:<br> Ort,PLZ: <br>"+
 	                  // "Tel.:<br>"+"<b>",JLabel.CENTER));
 	//int i=40;if(Rechnung.length<25)i=750;else i=30;
 	//int x=Rechnung.length+1;
 	//int bereit=485;//580;//480;
 	//int hoch=(x*(i+10))+300;
  	
	kdp.add(klabel,BorderLayout.WEST); 	
 	kdp.add(new JTextArea("\nIhre KN:\n"+Kunde[5]+"\n"),BorderLayout.CENTER);
 	kdp.add(rlabel,BorderLayout.EAST);
	kdp.setPreferredSize(new Dimension(480, 80));
 	kfp.setPreferredSize(new Dimension(480, 60));
 	kfp.add(flabel,BorderLayout.CENTER);//Firmen daten
 	panel.add(kfp);//1
  	panel.add(kdp); //2
  	//panel.add(jthead);//new JTextArea(1,1)); //4
  	//JScrollPane scrollpane1 = new JScrollPane(tb);
 	tb.setPreferredSize(new Dimension(480, 480));//((x+5)*i)));
// 	tb.setFont(new Font("Serif", 0, 13));
 	JScrollPane scrollpane = new JScrollPane(tb);
 	scrollpane.setPreferredSize(new Dimension(480,480));//((x+5)*i)));
 	scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
 	scrollpane.setBorder(brd);
 	//scrollpane.pack(); 	
 	JTextArea jta=new JTextArea();
	JTextArea jtb=new JTextArea();
 	jta.setFont(new Font("Courier New", 1, 14));
 	for(int i=0;i<51;i++)jta.append("_");
 	jta.append("\nIhre Rechnung Summe ist :	 	 "+f(sume()));
 	//jta.setPreferredSize(new Dimension(480,150)); 	
 	jtb.setFont(new Font("Roman", 1, 10));
 	//for(int i=0;i<51;i++)//str+="-"; 	jtb.append("_");
 	jtb.append("\n"+new WG(Record(),"gastro/Date/Drucker",new myDatum().ist()).Ausgabe);
 	//jtb.append("________________________________________________________");
 	
 	JTextArea danke=new JTextArea(
 	"		Wir haben von Mo-Fr 11:00-14:00 und 17:00-23:00 Uhr"+
 	"\n		sowie SA-SO u.Feirtage 11:00-23:00 Uhr Offen"+
	"\n			           guten Apittit");
 	
 	danke.setFont(new Font("Courier New", 0, 10));
 	//panel.add(kep);
	panel.add(scrollpane);
 	panel.add(jta);
 	panel.add(jtb);
 	panel.add(danke);
 	panel.setPreferredSize(new Dimension(480,640));//bereit,hoch)); 	
 	getContentPane().add( BorderLayout.PAGE_START , panel);  		
 	setBounds(0, 0, 480,640);//bereit, hoch); 	
 	setDefaultCloseOperation(2); 	
 	pack();  	 	
 	setVisible(true);
 	vista = new JComponentVista(panel , new PageFormat()); 
 	vista.scaleToFit(true,Skale); 	
 	pb();
// 	dispose(); 	 
 }
	
public String f(String str){return new MyZahl().deci(Double.parseDouble(str));}
public Object fo(Object str){str=new MyZahl().deci(Double.parseDouble(str.toString()));return str;}
	public void  pb(){
			PrinterJob pj = PrinterJob.getPrinterJob(); 
			pj.setCopies(1);
			pj.setJobName("RN_"+Kunde[1].trim());
			pj.setPageable(vista); 
			try { 
				//if (pj.printDialog()) { 
					pj.print();					
				//}
			} catch (PrinterException e) {System.out.println(e);}
			
		}
	//} ;	

		public String[]Record(){			
			return new sucheDate("gastro/Date/Drucker/"+Kunde[1]+".dat").myDaten();
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
	
 public static void main(String[] args) {
 	String[] firma =//new ebm.firma().data();
 	{"Pizzaria Ristorante Muratti","Tel.: 0676 354 68 24","1180 Wien Lacknergasse 100/4"};
   	String[] kopf={"Pos","Menge","Bezeichnung","E-Preis","Total"};
   	Object[][] rechnung = //null;
 	{
   		{"1","2","Tortellini","5.70","10.40"},
   		{"2","2","Pollo","6.40","12.80"},
   		{"3","2","Tonno","6.40","12.80"},   		
   		{"4","2","Capriciose","6.40","12.80"},
   		{"5","2","Spaghetti Carbonara","7.20","12.80"},
   		{"6","2","Spaghetti Diavolo","6.40","12.80"},
   		{"7","2","Capriciose","6.40","12.80"},
   		{"8","7","Spaghetti Carbonara","7.20","12.80"},
   		{"9","2","Spaghetti Diavolo","6.40","12.80"},
 	      {"10","2","Capriciose","6.40","12.80"},
   		{"11","2","Spaghetti Carbonara","7.20","12.80"},
   		{"12","2","Spaghetti Diavolo","6.40","12.80"},
   		{"13","2","Capriciose","6.40","12.80"}
   		/*{"14","2","Spaghetti Carbonara","7.20","12.80"},
   		{"15","2","Spaghetti Diavolo","6.40","12.80"},
   		{"16","2","Spaghetti Carbonara","7.20","12.80"},
   		{"17","2","Spaghetti Diavolo","6.40","12.80"},
   		{"18","2","Capriciose","6.40","12.80"},
   		{"19","2","Spaghetti Carbonara","7.20","12.80"},
   		{"20","2","Spaghetti Diavolo","6.40","12.80"},
		{"21","2","Capriciose","6.40","12.80"},
   		{"22","2","Spaghetti Carbonara","7.20","12.80"},
   		{"23","2","Spaghetti Diavolo","6.40","12.80"},
		{"24","2","Capriciose","6.40","12.80"},
   		{"25","2","Spaghetti Carbonara","7.20","12.80"},
   		{"26","2","Spaghetti Diavolo","6.40","12.80"},
   		{"27","15","Lasagnia al Forno","6.40","120.80"}   */

   	};
 	
 	int i=10;
   	String[]kunde = {"K U N D E N"+i,"1050300/085"+i,"Mourad Elbakry",
   	"Lacknergasse 100/4","1180 Wien","06763546824"}; 
 	Df000 md= new Df000("Rechnung");
 	md.druck(firma,kunde,rechnung,kopf,new com.printer.papier().getPapierformat());
   				
   }
}
