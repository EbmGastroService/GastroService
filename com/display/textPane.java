// created on 09.07.2003 at 12:19
//used by myRc Rechnung zeile bearbeiten
package com.display;
import com.search.*;
import com.options.*;
import com.units.myDatum;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
public class textPane{
	String zeile;
	String[] wort;
	String[] Co={"OK","Abbrechen"};
	public textPane(String[] Wort){
		wort=Wort;
		JPanel[]jp= new JPanel[wort.length];
		JPanel jpg= new JPanel();
		jpg.setLayout(new BoxLayout(jpg,BoxLayout.Y_AXIS));	
		//jpg.setLayout(new GridLayout(wort.length+1,1));
		JTextField[]jt = new JTextField[wort.length];
	    JLabel[]jl= new JLabel[wort.length];						
		JEditorPane mylab=new JEditorPane("text/html","<html><body bgcolor=#fffffc>"+
		                                  "<font color=blue size=3>"+
		                        "<u>Hier kann Man die Daten bearbeiten</u><br>"+
		                        "<i>Menge</i> oder <i>Bezeichnug</i> weden sofort"+
		                        "<br>"+" angenommen!</font></body></html>");
		mylab.setEditable(false);
		jpg.add(mylab);
		jpg.add(new JLabel(".............."));
		//jp.setLayout(new BorderLayout());//new GridLayout(wort.length,2));
		for (int i=0; i<wort.length; i++){					
			if(wort[i]!=null){
			jp[i]= new JPanel();	
			jp[i].setLayout(new BoxLayout(jp[i],BoxLayout.X_AXIS));		
			//jp[i].setPreferredSize(new Dimension(355,21));  					
			jl[i]= new JLabel(""+wort[i]);
			jl[i].setPreferredSize(new Dimension(80,20));  	
			jt[i]= new JTextField(wort[i].length());
			jt[i].setFont(new Font("Courier New",1,14));		
			//jt[i].setBackground(Color.yellow);						
			jt[i].setForeground(Color.blue);					
			jt[i].setPreferredSize(new Dimension(300,20));  	
			jt[i].setText(wort[i]);//.trim());			
			if(i==1)jt[i].setEditable(false);
			jp[i].add(jl[i]);
			jp[i].add(jt[i]);		
			jpg.add(jp[i]);	
			}
			
		}		
		if(JOptionPane.showOptionDialog(null,jpg," Editor",
		   JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,//JOptionPane.INFORMATION_MESSAGE,
		   null, Co, Co[0]) == 0) { 
		   	String ga=jt[0].getText().trim();
		   	wort[0]=jt[0].getText().trim();
		   for (int i=1; i<wort.length; i++){
		   		if(wort[i]!=null) ga =ga+","+ jt[i].getText().trim();
		   		wort[i]=jt[i].getText().trim();
			}			
			zeile=ga;
		   }
		//return zeile;
	}
	void testBez(){
		  for (int i=0; i<wort.length; i++){
		  	if(wort[i]!=null)
		   	 wort[i]=""+wort[i].replace(',','.');
		  }
	}
	public String zeile(){return zeile;}
	public String[] worte(){testBez();return wort;}
	
	public static void main(String[] args) {
		sucheDate su =new sucheDate("gastro/Date/wliste.dat");		
	 	String[] str = su.myDaten();	
	 	ausTeilen a=new ausTeilen();
	 	String[] wort = a.komma(""+str[1]);
	 	  for (int i=0; i<wort.length; i++){				
        			System.out.println(wort[i]);
			}
  		textPane tp =	new textPane(wort);
  		System.out.print(tp.zeile());
  		
	}
}
