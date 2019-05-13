// created on 13.05.2003 at 00:20
package com.printer;
import com.options.*;
import com.units.*;
import com.printer.*;
//import com.display.*;
import com.search.*;
//import ebm.firma;
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

public class mydruck extends JFrame {	
	JComponentVista vista;
	JPanel panel,kdp; //Rechnug formularpanel, Kofdatenpanel
	JLabel flabel,L_label,R_label;//Firmenlabel,Linke label,Rechte Label
	JTable tb; 
	String[]Firma;
	String[]Kunde;
	Object[][]Rechnung;	
	String[]Kopf;	
	double Skale;
	String Titel;
	public mydruck(String titel){
		super(titel); 		
		Titel=titel;
	}
	
 public void druck(String[]firma,String[]kunde,Object[][]rechnung,String[]kopf,double skale ){ 	
 	Firma=firma;
 	Kunde=kunde;
 	Rechnung=checkdata(rechnung);
 	Kopf=kopf;	
 	Kopf=headersok(Rechnung);	 	
 	Skale= skale; 
    final String[] headers = Kopf;
  	final Object[][] data = Rechnung;
   TableModel dataModel = new AbstractTableModel() {
        public int getColumnCount() { return headers.length; }
        public int getRowCount() { return data.length;}
        public Object getValueAt(int row, int col) {
        	return data[row][col];}
        public String getColumnName(int column) {
         	return headers[column];}
        public Class getColumnClass(int col) {        	
                return getValueAt(0,col).getClass();}
        public boolean isCellEditable(int row, int col) {
                return (col==0);}
        public void setValueAt(Object aValue, int row, int column) {
                data[row][column] = aValue;	
        }       
     };
 	tb = new JTable(dataModel);	  	
 	TableColumn posColumn = tb.getColumn(""+headers[0]);//pos
        DefaultTableCellRenderer posColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" : value.toString());
	    }
        };
        posColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
        posColumn.setCellRenderer(posColumnRenderer);
        posColumn.setPreferredWidth(10);
	
	TableColumn mengColumn = tb.getColumn("Menge");
       DefaultTableCellRenderer mengColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" : value.toString());
	    }
        };
        mengColumnRenderer.setHorizontalAlignment(JLabel.CENTER);
        mengColumn.setCellRenderer(mengColumnRenderer);
        mengColumn.setPreferredWidth(15);	
 	
 	TableColumn bezColumn = tb.getColumn("Bezeichnung");//+headers[2]);
        DefaultTableCellRenderer bezColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
		setFont(new Font("Courier New", 0, 15));
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" : value.toString());
	    }
        };
        //posColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
        bezColumn.setCellRenderer(bezColumnRenderer);
        bezColumn.setPreferredWidth(245);

	TableColumn epColumn = tb.getColumn("E-Preis");       
       DefaultTableCellRenderer epColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" :f(value.toString()));
	    }
        };
        epColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
        epColumn.setCellRenderer(epColumnRenderer);
        epColumn.setPreferredWidth(35);

 	//Total
 	TableColumn numbersColumn = tb.getColumn(headers[headers.length-1]);
        DefaultTableCellRenderer numberColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" :f( value.toString()));
	    }
        };
        numberColumnRenderer.setHorizontalAlignment(JLabel.RIGHT);
        numbersColumn.setCellRenderer(numberColumnRenderer);
        numbersColumn.setPreferredWidth(60);
 		
 	
 	
 	
    //Weise Border
    Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.white);
 
  	//Firmen Label
  	flabel= new JLabel("<html><center><font face='Courier New' size=6><b>"+
  		 Firma[0]+"</b></font><br><font face='Courier New' size=4>"+
  		 Firma[1]+"</font><font face='Courier New' size=6><br>"+
  		 Firma[2]+"</font><br><br>",JLabel.CENTER);
  	myDatum md = new myDatum();
 	String d_datum=md.d();
 	String d_zeit=md.time();
 	if(Kunde.length>7){d_datum=Kunde[7];d_zeit=Kunde[8];}
 	//Linke Label Rechnung Label
  	L_label = new JLabel("<html><b><font face='Courier New' size=4>" +//"<center>"+
  	                    ""+Kunde[1]+"<br>"+d_datum+
  	                       "<br>"+d_zeit+"</font>",JLabel.CENTER);
 	//Rechte Label Kunden daten Label
  	R_label= new JLabel("<html><font face='Courier New' size=4>" +//"<center>"+
  	                   Kunde[0]+"<br>"+Kunde[2]+"<br>"+
  	                      Kunde[3]+"<br>"+Kunde[4]+ 
  	                      //Kunde[5]+
  	                      "  </font>",JLabel.CENTER);
 	tb.setBorder(brd);
 	tb.setBackground(Color.white); 	
// 	tb.setShowVerticalLines(false);
 	tb.setShowGrid(false); 	
// 	tb.setShowHorizontalLines(false); 	
 	kdp = new JPanel(new GridLayout(0,3));
 	JPanel kfp = new JPanel(new BorderLayout());//new GridLayout(1,3));
 //	kfp.setLayout(new BoxLayout(kfp, BoxLayout.X_AXIS)); 	
 	panel = new JPanel(new BorderLayout());
 	//panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
 	flabel.setBackground(Color.white);
 	panel.setBackground(Color.white);
 	kdp.setBackground(Color.white);
 	kfp.setBackground(Color.white); 	
 	R_label.setBackground(Color.white);//  	 	
 	L_label.setBackground(Color.white);//  	 	
 	int i=40;if(Rechnung.length<25)i=750;else i=30;
 	int x=Rechnung.length+1;
 	int bereit=480;//480;
 	int hoch=(x*(i+10))+300;//640;
 	kdp.add(R_label); 	
 	kdp.add(new JLabel("<html>"+"<center>"+"<font face='Courier New' size=4>" +"<b>R E C H N U N G</b><br>."+Kunde[5]+"</font>"+"</center>",JLabel.CENTER));
 	kdp.add(L_label);
 	JPanel obenpanel = new JPanel(new BorderLayout());
 	kfp.add(flabel,"Center");//Firmen daten
 	obenpanel.setPreferredSize(new Dimension(480, 155));
 	obenpanel.add(kfp,BorderLayout.PAGE_START);
 	obenpanel.add(kdp,BorderLayout.PAGE_END);
 	
 	panel.add(obenpanel,BorderLayout.PAGE_START);//1
  	//panel.add(kdp); //2
  	//kbd.setBorder(brd);  	
 	tb.setPreferredSize(new Dimension(480, 460));//(x*i)));
 	
 	tb.setFont(new Font("Courier New",0,12));	
 	JScrollPane scrollpane = new JScrollPane(tb);
 	scrollpane.setPreferredSize(new Dimension(480, 460));//(x*i)));
 	scrollpane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	scrollpane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
 	scrollpane.setBorder(brd); 	
 	
 	panel.add(scrollpane,BorderLayout.CENTER);
 	JPanel untenpanel = new JPanel(new BorderLayout()); 	
 	untenpanel.setPreferredSize(new Dimension(480, 135));	
 	untenpanel.setBackground(Color.white); 	
 	JTextArea jta=new JTextArea("");//2,15); 	
 	jta.setFont(new Font("Courier New",0,14));	
 	String mytemp="gastro/Date/Drucker";
 	String verg=""+Kunde[0];
 	if(verg.equals("B E D I E N U N G"))mytemp="gastro/KDate";
 	
 	jta.append("____________________________________________________________________\n"+
 	            new WG(Record(),mytemp,new myDatum().ist()).Ausgabe);
 	//jta.append("____________________________________________________________________");
 		
 	untenpanel.add(jta,BorderLayout.PAGE_START);
 	untenpanel.add(new JLabel("<html>"+//
 	                         // "<width=480>"+
 	                          "<font face='Courier New' size=4>"+
 	                          "<b><left>Ihre Rechnung Summe ist :</left></b>"+
 	                          "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;"+
 	                          "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
 	                          "<b><right>"+f(sume())+"</right></b></font>"+
 	                          "<br><br><center><font face='Courier New' size=2>"+
 	                          "Wir haben von Mo-Fr 11:00-14:00 und 17:00-23:00 Uhr"+
 	           "<br>sowie SA-SO u.Feirtage 11:00-23:00 Uhr ge&ouml;ffnet<br>Guten Apittit"+
 	                          "</font></center>",JLabel.CENTER),BorderLayout.PAGE_END);
 	
 	panel.setPreferredSize(new Dimension(480,750));//bereit,hoch)); 	
 	panel.add(untenpanel,BorderLayout.PAGE_END);
 	getContentPane().add(panel, BorderLayout.PAGE_START);  		
 	
 	setBounds(0, 0, 480,750);//bereit, hoch); 	
 	//setDefaultCloseOperation(2); 	
 	pack();  	 	
 	//setVisible(true);
 	vista = new JComponentVista(panel , new PageFormat()); 
 	vista.scaleToFit(true,Skale); 	
 	pb(); 
 	dispose(); 	 
 }
	
public String f(String str){return new MyZahl().deci(Double.parseDouble(str));}
public Object fo(Object str){str=new MyZahl().deci(Double.parseDouble(str.toString()));return str;}
		public void  pb(){//actionPerformed(ActionEvent evt) {
			//remove(printButton);						
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
			return new sucheDate("gastro/Date/Drucker/"+Titel+".dat").myDaten();
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
		//stellt der Tabele.headerslnge und Value um der daten länge
		//wenn die daten länge > die headerslnge dan soll die header abgekrtzt
		//wenn die daten länge<diehäderslnge dann soll die länge der headers verlngt
		public String[]headersok(Object[][] str){
			int dif = Kopf.length - str[0].length;
			String[] nKopf=null;
 			if(dif>0){ 						//ist der haedrs grosse als die daten
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
	
/* public static void main(String[] args) {
 	String[] firma =//new firma().data();
 	{" Pizzaria Restaurante Muratti","Tel.: 0676 354 68 24","1180 Wien; Lacknergasse 100/4"};
   	String[] kopf={"Pos","Menge","Bezeichnung","E-Preis","Total","M","K"};
   	Object[][] rechnung = //null;
 	{
   		{"1","2","Tortellini","5.70","10.40"},
   		{"2","2","Pollo","6.40","12.80"},
   		{"3","2","Tonno","6.40","12.80"},   		
   		{"4","2","Capriciose","6.40","12.80"},
   		{"5","2","Spaghetti Carbonara","7.20","12.80"},
   		{"6","2","Spaghetti Diavolo","6.40","12.80"},
   		{"7","2","Capriciose","6.40","12.80"},
   		{"8","2","Spaghetti Carbonara","7.20","12.80"},
   		{"9","2","Spaghetti Diavolo","6.40","12.80"},
   	    {"10","2","Capriciose","6.40","12.80"},
   		{"11","2","Spaghetti Carbonara","7.20","12.80"},
   		{"12","2","Spaghetti Diavolo","6.40","12.80"},
   		{"13","2","Capriciose","6.40","12.80"},
   		{"14","2","Spaghetti Carbonara","7.20","12.80"},
   		{"15","2","Spaghetti Diavolo","6.40","12.80"},
   		{"16","2","Spaghetti Carbonara","7.20","12.80"},
   		{"17","2","Spaghetti Diavolo","6.40","12.80"},
   		{"18","2","Capriciose","6.40","12.80"},
   		{"19","2","Spaghetti Carbonara","7.20","12.80"},
   		{"20","2","Spaghetti Diavolo","6.40","12.80"},

   		{"21","2","Spaghetti Diavolo","6.40","12.80"},
   		{"22","2","Spaghetti Carbonara","7.20","12.80"},
   		{"23","2","Spaghetti Diavolo","6.40","12.80"},
   		{"24","2","Capriciose","6.40","12.80"},
   		{"25","2","Spaghetti Carbonara","7.20","345.80"},
   		{"26","2","Spaghetti Diavolo","6.40","52.80"},

   		{"27","15","Lasagnia al Forno","6.40","120.80"}   		   		
   	};
 	
 	int i=10;
   	String[]kunde = {"K U N D E N"+i,"1050300/085"+i,"Mourad Elbakry",
   	"Lacknergasse 100/4","1180 Wien","06763546824"}; 
 	mydruck md= new mydruck("Rechnung");
 	md.druck(firma,kunde,rechnung,kopf,new papier().getPapierformat());
   				
   }*/
}
