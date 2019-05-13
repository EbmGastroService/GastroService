// created on 05.05.2003 at 22:59
package com.display;
import com.options.*;
import com.units.*;
import com.search.*;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class dieliste extends JApplet{
	String[] Dieliste;
	JTextField ta;
	JButton[] bear=	{new JButton("Bearbeiten"),	new JButton("Entfernen")};		
	DefaultListModel lItems; 
	JList lst;	 	
	String[] wahl= new String[1];
	int position;
	Bearbeite be;
	String Filename;
	
	public dieliste(String[]dielist){
		Dieliste = dielist;		
		position=0;			
		Filename="gastro/Date/storno.dat";
		lItems = new DefaultListModel();
		lst = new JList(lItems);//lItems);
		ta=new JTextField("",40);
		wahl=Dieliste;		
	}
	public dieliste(String[]dielist,String filename ){
		Dieliste = dielist;
		Filename =filename;
		position=0;			
		lItems = new DefaultListModel();
		lst = new JList(lItems);//lItems);
		ta=new JTextField("",40);
		wahl=Dieliste;				
  	}
  	class bel implements  ActionListener {
    	 public void actionPerformed(ActionEvent e) {    	 	    	
    	 	String ziel=e.getActionCommand();
    	 	if(ta.getText().equals("")){    	 		
    	 	 new MyOp().fehler("Keine Wahl zum bearbeiten");
    	 	}else 
    	 	if(ziel.equals("Bearbeiten")){    	 		
    	 		System.out.println("Filename: " + Filename);
      		 	 be = new Bearbeite(wahl,position,Filename);    	 	
    	 	}
    	 	if(ziel.equals("Entfernen")){
    	 		lst.getModel().getElementAt(position);
    	 	}
    	 }
  		};	
	class ll implements ListSelectionListener {   
      	public void valueChanged(ListSelectionEvent e) {
    	    if(e.getValueIsAdjusting()) return;      		      		    
        		ta.setText("");
        		Object[] items=lst.getSelectedValues();
      			position =lst.getSelectedIndex() ;
             	System.out.println("Double clicked on Item " + position);
      			for(int i = 0; i < items.length; i++){  				
      				ta.setText(""+items[i] );      				         				
      			}           		      			
		 }
      	};		
	
	public void init(){	
		ta.setEditable(false);
		Container cp = getContentPane();
		cp.setLayout(new FlowLayout());
		//cp.setBackground(Color.orange);
		Border brd = 
		BorderFactory.createMatteBorder(1, 1, 2, 2, Color.red);
    	lst.setBorder(brd);
    	ta.setBorder(brd);
		for(int i = 0; i <Dieliste.length; i++){
			lItems.addElement(Dieliste[i]);							
		}
		JPanel sud = new JPanel ();//new GridLayout(2,0));
  		sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));
  		sud.setBorder(new BevelBorder(BevelBorder.RAISED));
  		sud.setBackground(Color.orange);		
		JScrollPane scrollpane = new JScrollPane(lst);
		scrollpane.setBorder(new BevelBorder(BevelBorder.LOWERED)); 	
    	scrollpane.setPreferredSize(new Dimension(300, 100));
 		//scrollPane.getViewport().setView(lst);
		sud.add(scrollpane);	
		
		sud.add(new JLabel("<html><b><font size=+1>" +
			                   "<center>"+
			                   "in die Liste Blaetern und [Bearbeiten] drucken"+
			                   "<br>"+" Aendern Sie Oben, dann [Ok] drucken!"+"<b>",JLabel.CENTER));;
		//sud.add(bear);
		//cp.setLayout(new BoxLayout(cp, BoxLayout.Y_AXIS));
		cp.setBackground(Color.orange);		
		cp.add(ta);
		cp.add(sud);
		for(int i=0;i<bear.length;i++){
			bear[i].setBackground(Color.red);
			bear[i].setForeground(Color.yellow);				
			bear[i].addActionListener(new bel());
			cp.add(bear[i]);		
		}
		
		lst.addListSelectionListener(new ll());    
		cp.setBounds(100, 0, 600, 350);
		cp.setVisible(true);	
	}	
	public static void main(String[] args) {
		sucheDate su =new sucheDate("0","gastro/Date/wliste.dat",0);		
	 	String[] str = su.myDaten();	 		
		dieliste b = new dieliste(str);//,"Date/wliste.dat");		
		JFrame jf= new JFrame(" Dieliste");		
		b.init();
		b.start();
		jf.getContentPane().add(b);				
		jf.pack();
		jf.setBounds(100, 0, 600, 500);
		jf.setVisible(true);	
		
	}
}
