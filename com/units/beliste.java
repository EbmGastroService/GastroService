// created on 06.05.2003 at 18:56
package com.units;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class beliste extends JApplet{	
    JFrame f ;
	sucheDate su;
		public beliste(String Titel){
			f = new JFrame(Titel);		
			String[]sicherheit={"gastro/Date/Wliste.dat","gastro/Date/Kliste.dat",
			"gastro/Date/Jliste.dat","gastro/Date/Fliste.dat","gastro/Date/Kbuch.dat","gastro/Date/Wkorb.dat"};
			String[] v = null;			
    	    su = new sucheDate("gastro/Date/Datenliste.dat");  			
  		    //v = su.myDaten(); 
			if(su.zeilenZahl()>0)v = su.myDaten(); else v = sicherheit;
    		JPanel top = new JPanel ();
			JPanel sud = new JPanel ();           				
			top.setLayout(new GridLayout(3,1));
  			top.setBorder(new BevelBorder(BevelBorder.RAISED));			
			top.add(new JLabel("<html><b><font size=+1>" +
			                   "<center>"+"<i>"+
			                   "zum wahl "+"<b>",JLabel.CENTER),"Center");
			top.setBackground(Color.orange);			        
			top.setForeground(Color.white);
    		JButton[] jl = new JButton[3];			
    		for(int i =0; i< 2; i++){    		
				jl[i]= new JButton(v[i]);    			
    			jl[i].addActionListener(new Tabellen());
    			jl[i].setBackground(new Color(i*50,i*5,i*10));
    			jl[i].setForeground(Color.white);
    			top.add(jl[i],"Center");   			    			
    		}    						
		f.getContentPane().add(top,"Center");					
		f.pack();
		f.setBounds(100, 0, 200, 200);
		f.show();
		f.setVisible(true);  
		
		}	
 class Tabellen implements ActionListener {
    public void actionPerformed(ActionEvent e) {    	
        String str = e.getActionCommand();  
    	su = new sucheDate(str);  			
  		 String[] v = su.myDaten();    		    		
    	 new Bearbeite(v,1,str);    	   	 
    }		 
 }
  public static void main(String s[]) {	  	
  	  new beliste(" my tabele ");
  	  //rt.init();  	
	 	//Console.run(new RepTest("my Report test "),10,50);
   }
}
