package com.tabel;
import javax.swing.*;
import javax.swing.table.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;

public class myTabel extends JApplet{
	JFrame frame;
	JTable tableView;
	String Mydata;
	int dlength,hlength;
	boolean bo;
  public myTabel(String mydata,boolean bo){ 
  	if(mydata != null && mydata!="") Mydata = mydata; else Mydata = "gastro/date/wliste.dat";   
	final String[] headers =Erstezeile();	
  	final Object[][]data = umwandler();	   
  	dlength=data.length;
  	hlength=headers.length;
  	this.bo=bo;     
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
  	tableView = new JTable(dataModel);  	   	
  	TableColumn posColumn =tableView.getColumn(""+headers[0]);
        DefaultTableCellRenderer posColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" : value.toString());
	    }
        };
        posColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
        posColumn.setCellRenderer(posColumnRenderer);
        posColumn.setPreferredWidth(100);
  		//Total
 	TableColumn numbersColumn = tableView.getColumn(""+headers[headers.length-1]);
        DefaultTableCellRenderer numberColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" : value.toString());
	    }
        };
        numberColumnRenderer.setHorizontalAlignment(JLabel.RIGHT);
        numbersColumn.setCellRenderer(numberColumnRenderer);
        numbersColumn.setPreferredWidth(60);
  	TableColumn bezColumn = tableView.getColumn(headers[gecheckt()]);
        DefaultTableCellRenderer bezColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.blue);
	        setText((value == null) ? "" : value.toString());
	    }
        };
        //posColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
        bezColumn.setCellRenderer(bezColumnRenderer);
        bezColumn.setPreferredWidth(250);
 	//Total
 	if (Geld()>-1){
	TableColumn GeldColumn = tableView.getColumn(headers[Geld()]);
        DefaultTableCellRenderer GeldColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.black : Color.red);
	        setText((value == null) ? "" : f(value.toString()));
	    }
        };
        GeldColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
        GeldColumn.setCellRenderer(GeldColumnRenderer);
        GeldColumn.setPreferredWidth(100);
 	
 	}
 	if (total()>-1){
	TableColumn totColumn = tableView.getColumn(headers[total()]);
        DefaultTableCellRenderer totColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" : f(value.toString()));
	    }
        };
        totColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
        totColumn.setCellRenderer(totColumnRenderer);
        totColumn.setPreferredWidth(100);
 	
 	}
 	tableView.setBackground(new ebm.myColor("vg2").getColor());
  	if(bo)zeige();
  }
  public JScrollPane JS(){
  	JScrollPane scrollpane = new JScrollPane(tableView); 
  	int xx=dlength*5;
    if(xx<200)xx=200;else 
    if(dlength<25 && bo)xx=700;	
    if(xx>700 && bo)xx=700; else xx=500;
    scrollpane.setPreferredSize(new Dimension(hlength*120, xx));     	
  	return scrollpane;  	
  }
     void zeige(){     	
     	String ftxt="";if(Mydata.toLowerCase().indexOf("gastro/date/")!=-1)ftxt=Mydata.substring(12);else ftxt="form01.pre";  
     	frame = new JFrame("EBM-Gastro - Betrachte .."+ftxt.substring(0,ftxt.indexOf("."))+" "+dlength+" Daten in Tabellenformat!");   
     	frame.getContentPane().setLayout(new BorderLayout());
     	frame.getContentPane().add(BorderLayout.CENTER,JS());
     	frame.setLocation(100,100);
     	frame.pack();
     	frame.setVisible(true);     
     }
     public String f(String str){
     	float z=0;
     	try{
     		z=Float.parseFloat(str);
     	}catch(NumberFormatException nfe){z=0;}
     	return new MyZahl().deci(z);
     }
     
     String[]head(String str){
     	String[]mh=new sucheDate("gastro/date/source/kopfzeile.dat").myDaten();
     	if(mh==null ||mh.length<=0)new kopfzeile();
     	String[]heads=new String[0];
     	for(int i=0;i<mh.length;i++){     		
     		String[]wort=new ausTeilen().koma(mh[i]);     		
     		if(str.equals(""+wort[0].trim())){
     			heads=new String[wort.length-1];
     			int l=0;
     			for(int m=1;m<wort.length;m++){     		
     			heads[l]=wort[m];l++;
     			}
     		}
     	}    
     	return heads;
     }
     String[]safty(int leng){
     	String[]str=new String[5];
     	for (int z=0; z<str.length; z++){
     		str[z]="00"+z+",";
     		for (int i=1; i<leng-1; i++){
     			str[z]+="Muster "+i+",";     			
     		}
     		str[z]+="End Muster";
     	}
     	return str;
     }
    
    public Object[][] umwandler(){
    	ausTeilen z = new ausTeilen();    	
     	String[]v = new sucheDate(Mydata).myDaten();    	
    	int leng=Erstezeile().length;    	
    	if(v==null ||v.length<=0)v = safty(leng);    	
    		Object[][]dat = new Object[v.length][leng];    	    		
    			String[] wort= new String [leng] ;    			
    			for (int i=0; i< dat.length; i++){
    				wort=new ausTeilen().koma(""+v[i]); 
    				try{
    				for (int x=0; x<dat[i].length; x++){
    					dat[i][x]=""+wort[x];     				
    				}    				
    				 } catch(Exception ioe){System.err.print("\n");
    				 	System.out.print("Fehler in Zeile: ["+i+"]\n");
    				 	new com.units.save().file("gastro/date/dbfehler.dat","Fehler in Zeile: ["+i+"][ "+v[i]+" ]",false);
    				 }
    			}    		
    		return dat;
    }   
    public String[] Erstezeile(){
		int x=0;int y=0;
		for(int i=0;i<Mydata.length();i++){
			if(Mydata.charAt(i)=='/')x=i;
		}		
		String str = Mydata.substring(x+1,Mydata.length()-4);					
    	String[]v=head(str);    	
		return v;
	}
	public int gecheckt(){
		String[] wort=Erstezeile();
		int x=0;
			for(int i=0;i<wort.length;i++){
				if(wort[i].indexOf("Bezeichnung")>-1 ) x=i;
			}			
		return x;
	}
	public int Geld(){
		String[] wort=Erstezeile();
		int x=0;
			for(int i=0;i<wort.length;i++){
				if(wort[i].trim().equals("E-Preis")|| wort[i].trim().equals("R-Betrag")) x=i;
			}
			if (x>0) return x;else return -1; 
	}
	public int total(){
		String[] wort=Erstezeile();
		int x=0;
			for(int i=0;i<wort.length;i++){
				if(wort[i].trim().equals("Total")) x=i;
			}
			if (x>0) return x;else return -1; 
	}
	
	//frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
   public static void main(String[] args) {
                   //myTabel rp = new myTabel("");rp.umwandler();rp.Erstezeile();
   }
}
