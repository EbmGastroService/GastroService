package com.tabel;
import javax.swing.*;
import javax.swing.table.*;
import java.awt.print.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.Dimension;

import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;

public class Report extends JTable implements Printable{
  JFrame frame;
  JTable tableView;
  String Mydata;
  JScrollPane scrollpane;

  public Report( String mydata) {
  	if(mydata != null && mydata!="") Mydata = mydata;  	else Mydata = "gastro/date/wliste.dat";
  	String ftxt="";if(Mydata.toLowerCase().indexOf("gastro/date/")!=-1)ftxt=Mydata.substring(12);else ftxt="form01.pre";
    frame = new JFrame("Drucker Tabelle "+ftxt.substring(0,ftxt.indexOf(".")));
  	//setTitle("drucker formular");
   
	final String[] headers =Erstezeile();	
  	final Object[][] data = umwandler();	
    
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
                return (col==1);}
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
        posColumn.setPreferredWidth(15);
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
        //numbersColumn.setPreferredWidth(30);
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
        //bezColumn.setPreferredWidth(150);
  		
 	//Total
 	if (Geld()>-1){
	TableColumn GeldColumn = tableView.getColumn(headers[Geld()]);
        DefaultTableCellRenderer GeldColumnRenderer = new DefaultTableCellRenderer() {
	    public void setValue(Object value) {
	        int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
	        setForeground((cellValue > 1) ? Color.red : Color.black);
	        setText((value == null) ? "" : f(value.toString()));
	    }
        };
        GeldColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
        GeldColumn.setCellRenderer(GeldColumnRenderer);
        //GeldColumn.setPreferredWidth(100);
 	
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
        //totColumn.setPreferredWidth(100);
 	
 	}
     //JScrollPane 
     scrollpane = new JScrollPane();//
     scrollpane.setViewportView(tableView);
  	int xx=data.length*5;
  	if(xx<200)xx=200;else if(xx>600)xx=600;
     scrollpane.setPreferredSize(new Dimension(headers.length*100, xx));
  	     //setContentPane(scrollpane);
     frame.getContentPane().setLayout(new BorderLayout());
     frame.getContentPane().add(scrollpane,BorderLayout.PAGE_START);//CENTER,scrollpane);     
     
     JButton printButton= new JButton();
     printButton.setText("print me!");
  	 printButton.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent evt) {
          PrinterJob pj=PrinterJob.getPrinterJob();
          pj.setCopies(1);
		  pj.setJobName(Mydata.substring(5,Mydata.length()));
          pj.setPrintable(Report.this);
         
          try{ 
          	if (pj.printDialog()){
            	pj.print();
          		frame.dispose();
          	}
          }catch (Exception PrintException) {}
          //new ebm.pit(tableView);
          }
        });        
  	 //printButton.setBackground(Color.red);
     frame.getContentPane().add(printButton,BorderLayout.PAGE_END);//SOUTH,printButton);
  	frame.pack();

     // for faster printing turn double buffering off

    // RepaintManager.currentManager(frame).setDoubleBufferingEnabled(false);  
  	frame.setLocation(120,50);
  	frame.setVisible(true);

     }



     public int print(Graphics g, PageFormat pageFormat, 
        int pageIndex) throws PrinterException {
     	Graphics2D  g2 = (Graphics2D) g;
     	g2.setColor(Color.black);
     	int fontHeight=g2.getFontMetrics().getHeight();
     	int fontDesent=g2.getFontMetrics().getDescent();
     	//leave room for page number
     	float pageHeight =(float) pageFormat.getImageableHeight()-fontHeight;//-fontHeight;
     	float pageWidth = (float)pageFormat.getImageableWidth();
     	float tableWidth = (float) tableView.getColumnModel().getTotalColumnWidth();
     	float scale = 1; 
     	if (tableWidth >= pageWidth) {
			scale =  pageWidth /tableWidth;
		}

     	float headerHeightOnPage=tableView.getTableHeader().getHeight()*scale;
     	float tableWidthOnPage=tableWidth*scale;

     	float oneRowHeight=(tableView.getRowHeight()+tableView.getRowMargin())*scale;
        	int numRowsOnAPage=(int)((pageHeight-headerHeightOnPage)/oneRowHeight);//***** das -1 neu
     	float pageHeightForTable=oneRowHeight*numRowsOnAPage;
     	int totalNumPages= (int)Math.ceil(((float)tableView.getRowCount())/numRowsOnAPage);
     	if(pageIndex>=totalNumPages) {
                      return NO_SUCH_PAGE;
     	}

     	g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
     	g2.drawString("Page: "+(pageIndex+1),(int)pageWidth/2-35,
     	              (int)(pageHeight+fontHeight-fontDesent));//bottom center

     	g2.translate(0f,headerHeightOnPage);
     	g2.translate(0f,-pageIndex*pageHeightForTable);

     	//If this piece of the table is smaller than the size available,
     	//clip to the appropriate bounds.
     	if (pageIndex + 1 == totalNumPages) {
                     int lastRowPrinted = numRowsOnAPage * pageIndex;
                     int numRowsLeft = tableView.getRowCount() - lastRowPrinted;
                     g2.setClip(0, (int)(pageHeightForTable * pageIndex),
                       (int) Math.ceil(tableWidthOnPage),
                       (int) Math.ceil(oneRowHeight * numRowsLeft));
     	}
     	//else clip to the entire area available.
     	else{    
                     g2.setClip(0,(int)(pageHeightForTable*pageIndex),
                                (int) Math.ceil(tableWidthOnPage),
                                (int) Math.ceil(pageHeightForTable));
     	}

     	g2.scale(scale,scale);
     	tableView.paint(g2);
     	g2.scale(1/scale,1/scale);
     	g2.translate(0f,pageIndex*pageHeightForTable);
     	g2.translate(0f, -headerHeightOnPage);
     	g2.setClip(0, 0,(int) Math.ceil(tableWidthOnPage),(int)Math.ceil(headerHeightOnPage));
     	g2.scale(scale,scale);
     	tableView.getTableHeader().paint(g2);//paint header at top

     	return Printable.PAGE_EXISTS;
   }
    	public Object[][] umwandler(){  	
    		ausTeilen z = new ausTeilen();    		
    		String[] v = null;
    		String[] sicherheit={"157,Salami,5.70","159,Tonno,6.10","169,Muratti,8.0"};
    		sucheDate su = new sucheDate(Mydata);		
     		if(su.zeilenZahl()>0) v = su.myDaten();else v = sicherheit;    		
    		int leng=Erstezeile().length;
    		Object[][] dat = new Object[0][leng];    	
    		if (v.length>0){
    		String[] wort= new String [leng] ; 
  			dat = new Object[v.length][wort.length];    	    		
  		//	System.out.println("wort:  "+wort.length+" lesen: "+v.length+"   data :"+dat.length+" HZ ");		
  			for (int i=0; i< dat.length; i++){  		 		  				
  				if (v[i] != null)wort=z.zeile(v[i]);
  				int m=0;
  				for (int x=0; x< wort.length; x++){  			
  					wort[0]=""+(i+1);
  					if(wort[x]==null ) wort[x]="--------";
  					dat[i][m++]=(Object)wort[x];  			
  				}  			  		 
  			}
    		}
  			return(dat);
    	}  
    public String f(String str){
    	return new MyZahl().deci(Double.parseDouble(str));
    }
     String[]head(String str){
     	String[]mh=new sucheDate("gastro/date/source/kopfzeile.dat").myDaten();
     	if(mh==null ||mh.length<=0)new kopfzeile();
     	String[]heads=new String[0];
     	for(int i=0;i<mh.length;i++){     		
     		String[]wort=new ausTeilen().koma(mh[i]);    
     		if(str.equals(""+wort[0].trim())){
     			heads=new String[wort.length];
     			int l=0;
     			wort[0]="Pos";		
     			for(int m=0;m<wort.length;m++){     		
     			heads[m]=wort[m];l++;
     			}
     		}
     	}
     	System.out.println("erstezeile:"+heads.length);	
     	return heads;
     }
	public String[] Erstezeile(){	
		int x=0;int y=0;
		for(int i=0;i<Mydata.length();i++){
			if(Mydata.charAt(i)=='/')x=i;
		}		
		String str = Mydata.substring(x+1,Mydata.length()-4);			
	/*	System.out.println(str);			
		
		if(str.length()>8)str="00000";
		String v = "wliste , Code , Artikel Bezeichnung , E-Preis ";
		if(str!="gastro/date/wliste.dat"){		
    		sucheDate su = new sucheDate(str,"gastro/date/source/kopfzeile.dat",0);
			if(su.zeilenZahl()>0)v = su.wahlListe();else new kopfzeile();
		}
    	//	sucheDate su = new sucheDate(str,"Date/Kopfzeile.dat",0);   		  			
  		//	String v = su.wahlListe();
    	v = v.substring(str.length()+2);
  	//	System.out.println(v);	*/
  		String[]v=head(str);
  		/*ausTeilen z = new ausTeilen();
  	    String[] wort = z.zeile(v);
		wort[0]="Pos";		
		for(int i=0;i<wort.length;i++){
			wort[i]=wort[i].trim();
		}*/
		return v;//(wort);
	}
	public int gecheckt(){
		String[] wort=Erstezeile();
		int x=0;
			for(int i=1;i<wort.length;i++){
					if(wort[i].indexOf("Bezeichnung")>-1 ) x=i;
			}			
		return x;
	}
	public int Geld(){
		String[] wort=Erstezeile();
		int x=0;
			for(int i=1;i<wort.length;i++){
				if(wort[i].trim().equals("E-Preis")|| wort[i].trim().equals("R-Betrag")) x=i;
			}
			if (x>0) return x;else return -1; 
	}
	public int total(){
		String[] wort=Erstezeile();
		int x=0;
			for(int i=1;i<wort.length;i++){
				if(wort[i].trim().equals("Total")) x=i;
			}
			if (x>0) return x;else return -1; 
	}
	
  	
   public static void main(String[] args) {
   	myDatum md = new myDatum();   	
   	String file="";   	
    Report rp = new Report(file);
	//rp.umwandler();
   	//rp.Erstezeile();
   }
}
