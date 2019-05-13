// created on 08.06.2006 at 19:24

//RC Tabele
package ebm;
//import java.awt.*;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.table.*;
//import javax.swing.table.AbstractTableModel;
//import javax.swing.JTable.*;

public class RcTab{
  private Object[][]mdata;
  String[][]tdata;
  private String[]mheaders;
  JTable tableView;
  JScrollPane js;
  public float ums;
  private boolean DEBUG = false;
   public RcTab(Object[][]Data,String[]Headers){
   	ums=0;   
   	mdata=Data;
   	mheaders=Headers;
   	tableView =new JTable(new MyTableModel());
   	String tChar=mheaders[0];
   	TableColumn posColumn =tableView.getColumn(""+mheaders[0]);
   	if(tChar.equals("Buchen") || tChar.equals("buchen")||tChar.equals("pos")||tChar.equals("Pos")){
   		//System.out.print(".");
   		posColumn.setPreferredWidth(40);
   	}else{    
   		posColumn.setPreferredWidth(150);
   		//Buchen oder pos
    DefaultTableCellRenderer posColumnRenderer = new DefaultTableCellRenderer() {
      public void setValue(Object value) {
          int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;    
          setText((value == null) ? "" : value.toString());
      }
    };
   	posColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
   	posColumn.setCellRenderer(posColumnRenderer);   	   		
   	}
   	
   	
   	//Menge oder Rnummer
   	TableColumn numbersColumn = tableView.getColumn(""+mheaders[1]);
   	DefaultTableCellRenderer numberColumnRenderer = new DefaultTableCellRenderer() {
   		public void setValue(Object value) {
   			int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
   			setText((value == null) ? "" : value.toString());
   		}
   	};
   	numberColumnRenderer.setHorizontalAlignment(JLabel.CENTER);
   	numbersColumn.setCellRenderer(numberColumnRenderer);
   	if(mheaders[1].equals("Menge")){
   		numbersColumn.setPreferredWidth(40);
   	}else numbersColumn.setPreferredWidth(180);
   //Bezeichnung	
   TableColumn bezColumn = tableView.getColumn(mheaders[2]);      		       	
   	DefaultTableCellRenderer bezColumnRenderer = new DefaultTableCellRenderer() {
   		public void setValue(Object value) {
   			int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
   			setText((value == null) ? "" : value.toString());
   		}
   	};
   	bezColumnRenderer.setHorizontalAlignment(JLabel.LEFT);
   	bezColumn.setCellRenderer(bezColumnRenderer);   	
   	if(mheaders[2].indexOf("Datum")>-1 || mheaders[2].indexOf("datum")>-1 
   	   || mheaders[2].indexOf("G-R")>-1 || mheaders[2].indexOf("T-R")>-1){
   		bezColumn.setPreferredWidth(100);
   	}else bezColumn.setPreferredWidth(300);
   	
   	//E-Preis oder R-Betrag  oder Kunde
   	TableColumn GeldColumn = tableView.getColumn(mheaders[3]);
   	if(mheaders[3].indexOf("Betrag")>-1 || mheaders[3].indexOf("Total")>-1 
   	   || mheaders[3].indexOf("total")>-1 || mheaders[3].indexOf("Preis")>-1){
   	DefaultTableCellRenderer GeldColumnRenderer = new DefaultTableCellRenderer() {
   	public void setValue(Object value) {
   		int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;   		
   			setText((value == null) ? "" : f(value.toString()));
   		}
   	};
   	GeldColumnRenderer.setHorizontalAlignment(JLabel.CENTER);//RIGHT);
   	GeldColumn.setCellRenderer(GeldColumnRenderer);   	   	
   	   	GeldColumn.setPreferredWidth(80);    	
   	}else GeldColumn.setPreferredWidth(180);    	
   	
   	
   	//E-Preis oder R-Betrag 
   	if(mheaders.length>=5){
   	TableColumn VierColumn = tableView.getColumn(mheaders[4]);   	
   	if(mheaders[4].indexOf("Betrag")>-1 || mheaders[4].indexOf("Total")>-1 || 
   	   mheaders[4].indexOf("total")>-1 || mheaders[4].indexOf("Preis")>-1){
   	DefaultTableCellRenderer VierColumnRenderer = new DefaultTableCellRenderer() {
   	public void setValue(Object value) {
   		int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;   		   		
   			setText((value == null) ? "" : f(value.toString()));
   		}
   	};
   	VierColumnRenderer.setHorizontalAlignment(JLabel.RIGHT);//RIGHT);
   	VierColumn.setCellRenderer(VierColumnRenderer);   	
   	}
   	VierColumn.setPreferredWidth(100);    	
   	}
   	//last col oder Total
   	if(mheaders.length>5){
   		int mhl=mheaders.length-1;   	
   	TableColumn totColumn = tableView.getColumn(mheaders[mhl]);   	
   	if(mheaders[mhl].indexOf("Betrag")>-1 || mheaders[mhl].indexOf("Total")>-1 || 
   	   mheaders[mhl].indexOf("total")>-1 || mheaders[mhl].indexOf("Preis")>-1){   	   	   	
   	   	DefaultTableCellRenderer totColumnRenderer = new DefaultTableCellRenderer() {
   	   		public void setValue(Object value) {  	   			
   	   		   	int cellValue = (value instanceof Number) ? ((Number)value).intValue() : 0;
   	   			setText((value == null) ? "" : f(value.toString()));
   	   		}
   	   	};
   	   	totColumnRenderer.setHorizontalAlignment(JLabel.RIGHT);   	   	
   	   	totColumn.setCellRenderer(totColumnRenderer);
   	   }   	   
   	   totColumn.setPreferredWidth(100);
   	}
}
   class MyTableModel extends AbstractTableModel {  
   	private Object[][]data=mdata;
   	private String[]columnNames=mheaders;          
        public int getColumnCount() {return columnNames.length;}
        public int getRowCount() { return data.length;}
        public String getColumnName(int col) {return columnNames[col];}
        public Object getValueAt(int row, int col) {return data[row][col];}
        /** JTable uses this method to determine the default renderer/
         * editor for each cell.  If we didn't implement this method,
         * then the last column would contain text ("true"/"false"),
         * rather than a check box.    */
        public Class getColumnClass(int c) {return getValueAt(0, c).getClass(); }
        /** Don't need to implement this method unless your table's * editable.*/
        public boolean isCellEditable(int row, int col) {
            //Note that the data/cell address is constant,
            //no matter where the cell appears onscreen.
            if (col >=1) {DEBUG=false; return DEBUG; } else {DEBUG=true; return DEBUG; }
        }
        /* * Don't need to implement this method unless your table's  * data can change.         */
        public void setValueAt(Object value, int row, int col) {
            if (DEBUG) {
                System.out.print("-");
                                   /*"Setting value at " + row + "," + col
                                   + " to " + value
                                   + " (an instance of "
                                   + value.getClass() + ")");*/
            }

            data[row][col] = value;
            fireTableCellUpdated(row, col);

            if (DEBUG) {
                System.out.println(".");//New value of data:");
               // print();
            }
        }        
  }
  
   
   public JTable tab(){
   	return tableView;
   }
   public JScrollPane app(){   	
   	return new javax.swing.JScrollPane(tableView); 	
   }
   public javax.swing.JPanel panel(){   	
   	getdata();
   	javax.swing.JPanel mpanel=new javax.swing.JPanel(new java.awt.BorderLayout());
   	javax.swing.JLabel mlab=new javax.swing.JLabel("Total: "+f(""+ ums));
   	mpanel.add(app());
   	mpanel.add(mlab,java.awt.BorderLayout.PAGE_START);
   	return mpanel;
   }
   /*public void print() {
   	int numRows = tableView.getRowCount();
   	int numCols = tableView.getColumnCount();
   	for (int i=0; i < numRows; i++) {
   		System.out.print("    row " + i + ":");
   		for (int j=0; j < numCols; j++) {
   			System.out.print("  " + mdata[i][j]);
   		}
   		System.out.println();
   	}
   	System.out.println("--------------------------");
   }*/
   public String[][]getdata() {   	
   	int numRows = tableView.getRowCount();
   	int numCols = tableView.getColumnCount();
   	float spalteB=0;
   	tdata=new String[numRows][numCols];
   	for (int i=0; i < numRows; i++) {      		
   		for (int j=0; j < numCols; j++) {  
   			tdata[i][j]=mdata[i][j].toString();   			   			
   			if(mheaders[j].indexOf("Total")>-1 && !DEBUG)spalteB=flo(""+tdata[i][j]);
   		}   		
   		ums+=spalteB;
   	}
   	System.out.println(" getData Ausgang: "+tdata[0].length+ " Umsatz:"+ums);   
   	return tdata;
   }
   float flo(String in){
   	float d=0;   
   	try{
   		d=Float.parseFloat(in);
   	}catch(Exception e){d=0;};
   	return d;
   }
   public String f(String in){
   	float d=0;   
   	try{
   		d=Float.parseFloat(in);
   	}catch(Exception e){d=0;};
   	return new com.units.MyZahl().deci(d);
   } 	
   
}
