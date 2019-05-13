// created on 27.06.2003 at 23:33
package com.display;
import com.search.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class JlistApp extends JPanel{
	String[] data;
	JList dataList;
	JScrollPane sp;		
	int position;
	Object ostr;
	
  public JlistApp(String[]liste){  	
  	position=0;
  	data =liste;
 	dataList = new JList(data); 
  	dataList.addListSelectionListener(new ll());
  	
  	setLayout(new BorderLayout());  	  
  	sp = new JScrollPane(dataList);  	
//  	dataList.setBackground(Color.orange);   	  	
  //	sp.setPreferredSize(new Dimension(180, 200));
  	add(sp);  	
  } 
  	
  	class ll implements ListSelectionListener {   
  		public void valueChanged(ListSelectionEvent e) {
    	    if(e.getValueIsAdjusting()) return;      		      		            	
        		Object[] items=dataList.getSelectedValues();
      			position =dataList.getSelectedIndex() ; 
  				ostr=dataList.getModel().getElementAt(position);    		
  			file(ostr.toString());
  		}
  	};

 public String file(String str){ 	
 	String fname=null;
 	String ziel="";
 	for(int i=0;i<str.length();i++){
 		if(str.startsWith(":",i))fname=str.substring(0,i);
 	}
	ziel=zielText(fname);
 	fname="gastro/kdate/tische/"+fname+".dat"; 		//k1t12
		//System.out.println(str+"\n fname"+fname); 
	sucheDate sd=new sucheDate(fname);
 	zumtabele(fname,ziel);
	//new Jlist(sd.myDaten(),false);		
 	return fname;
 }
 
 String[][]openFile(String file){
		return new com.search.sucheDate(file).teiledaten();	
 }
 String zielText(String str){
 	StringBuffer sb = new StringBuffer(str);
 	System.out.println(sb); 
 	int p=sb.indexOf("K");
    sb.insert(p+1, "elner ");
 	p=sb.indexOf("T");
 	sb.insert(p, " ");
 	sb.insert(p+2, "isch ");
	System.out.println("NH: "+sb); 
 	return ""+sb;
 }
 void zumtabele(String fn,String ziel){				
	String[][]str=openFile(fn);
 	if(str.length>0){		
		String titel="Inhalt | "+ziel;
		int pos=100+(50*str.length);int hight=100+(str.length*12);if(hight>700)hight=700;
		String[]headers={"Menge","code","Bezeichnung","E-Preis","Total"};	
		javax.swing.JScrollPane js=new javax.swing.JScrollPane();	
		js.setViewportView(new ebm.RcTab(str,headers).tab());
      	javax.swing.JFrame jf=new com.options.frame(titel,180);	
		jf.getContentPane().add(js,java.awt.BorderLayout.PAGE_START);
	  	//jf.getContentPane().add(new javax.swing.JLabel(label,javax.swing.JLabel.CENTER));
		jf.pack();
		//jf.setSize(500,hight);
		jf.setLocation(pos,hight);
		jf.setVisible(true);    			
		//if(new com.options.MyOp().Frage("Bericht Ausdrucken?")==0)new pit(js,ziel);		
	}
 }

}
 
