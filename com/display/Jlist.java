// created on 27.06.2003 at 23:33
package com.display;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
public class Jlist{
	String[] data;
	JList dataList;
	JScrollPane sp;	
	JTextField tf;	
	int position;
	String[] Co;
	
  public Jlist(String[] liste,boolean neu ){  	
  	position=0;
  	String[]co1={"OK","Abbrechen","Neue"};
  	String[]co2={"OK","Abbrechen"};
  	if(neu)Co=co1;else Co=co2;
  	data =liste;
 	dataList = new JList(data); 
  	dataList.addListSelectionListener(new ll());
  	tf=new JTextField(data[0].length()); 
  	JPanel jp =new JPanel(new BorderLayout());	  	
    //jp.setLayout(new );  	
  	jp.setBackground(new Color(204,204,204));  	
  	sp = new JScrollPane(dataList); 	
  	tf.setForeground(Color.red); 
  	tf.setBackground(new Color(204,204,204));  	
  	dataList.setBackground(new Color(184,184,14));  	
  	dataList.setForeground(Color.blue);
  	dataList.setFont(new Font("Courier New",1,14));
  	jp.add(new JLabel("<html><font color=blue size=4><b> Bitte Ihre Wahl!!:</b></font><br> "+
  	                  "<br><font color=blue size=3>gehen Sie mit dem Maus auf eine Zeile"+
  	                  "<br> dann [Enter] oder [ok] drucken"+
  	                  "<br>Ihrer Wahl wird dann weiter bearbeitet<br><br></font>"
  	                  //,new ImageIcon("image/Geld01.gif")
  	                  ,2),BorderLayout.PAGE_START);
  	jp.add(sp,BorderLayout.CENTER);
  	jp.add(tf,BorderLayout.PAGE_END);
  	int antwort=JOptionPane.showOptionDialog(null,jp, " Wahlliste",
		   JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,//JOptionPane.INFORMATION_MESSAGE,
		   null, Co, Co[0]);
 	if(antwort==0){ ergebnis();}  	
 	else if(antwort == JOptionPane.CLOSED_OPTION)position=-1;
		
  }
  	class ll implements ListSelectionListener {   
      	public void valueChanged(ListSelectionEvent e) {
    	    if(e.getValueIsAdjusting()) return;      		      		    
        		tf.setText("");
        		Object[] items=dataList.getSelectedValues();
      			position =dataList.getSelectedIndex() ; 		
      			element();
		 }
      	};	
	public int ergebnis(){ 	
		if(position>=0){
		Object str=dataList.getModel().getElementAt(position);    	
 		tf.setText(position+": "+str.toString());//dataList.getModel().getElementAt(position)); 		
		}
 		return position;
		
 	}
 	public String element(){ 	
 		Object str="";
 		if(position>=0){
 			str=dataList.getModel().getElementAt(position);
 			tf.setText(position+": "+str.toString());
 		}
 		return ""+str;		
 	}

 /* public static void main(String[] args) {
		sucheDate su =new sucheDate("Date/wliste.dat");		
	 	String[] str = su.myDaten();	 		
  		Jlist jl =	new Jlist(str);
  		
  		//int erg=Integer.parseInt(new MyOp().eingabe("position"));
  		System.out.println(jl.ergebnis());
  		//jl.dataList.remove(jl.position);
  		//System.out.println(jl.position+"> "+jl.dataList.getModel().getElementAt(jl.position));
		//Console.run(jl,700,500);
  		System.out.println(jl.position+"> "+jl.dataList.getModel().getElementAt(jl.position));
	}
*/
}
