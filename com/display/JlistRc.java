// created on 20.10.2004 at 01:31
package com.display;
import com.search.*;
import ebm.wk;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;


public class JlistRc extends JPanel{
	String[] data;
	JList dataList;
	JScrollPane sp;		
	int position;
	Object ostr;	
	//Timer timer;
    
  public JlistRc(String[] liste){  	
  	position=0;
  	data =liste;
 	dataList = new JList(data); 
  	dataList.addListSelectionListener(new ll());
  	
  	setLayout(new BorderLayout());  	  
  	sp = new JScrollPane(dataList);  	
  	dataList.setForeground(Color.blue);
  	dataList.setToolTipText("1 Minute Rechnung Ansicht");
  	sp.setPreferredSize(new Dimension(120, 300));
  	add(sp,"Center");  	
  	
                	
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

 public JPanel file(String str){ 	 
 	//String[] wort=new com.options.ausTeilen().koma(str);
 	return new ebm.wk(str).codegesucht();//WK(wort[1],wort[0]).codegesucht();  	
 }


}
 
