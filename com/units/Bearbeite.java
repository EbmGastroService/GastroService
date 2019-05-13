// created on 04.05.2003 at 23:45
package com.units;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;


public class Bearbeite extends JApplet{
	String[] Dieliste;
	int Position;
	String ga;
	JButton[]jb  = {
			new JButton("OK"),
			new JButton("Abbrechen")
		};	
	JPanel jp= new JPanel();
	JTextField[] jt;
	JLabel[] jl;
	JFrame f;
	ActionListener BL;
	ausTeilen at = new ausTeilen();
	String[] wort;		
	String Filename;
	//WindowListener wl;
	
	public Bearbeite(String[] dieliste, int position,String filename){
		Dieliste = dieliste;
		Position = position;		
		Filename = filename;
		f = new JFrame(" Bearbeite "+Dieliste[Position]);
    	ga="";        			
		f.setDefaultCloseOperation(2);
		/*  do nathing 1 verstecken 2 zerstören 3 alles weg
		 * setDefaultCloseOperation(EXIT_ON_CLOSE);	
		 */
		BL =new ActionListener() {
         public void actionPerformed ( ActionEvent e ) {
        	MyOp k= new MyOp();       
        	String ziel =e.getActionCommand();        	       
        	if (ziel.equals("OK")){
        		String t ="<html><b><font size=+1 color=green>" +
			                   "<center>"+"<i>"+
			                   fertige()+"<br>"+" Fertig "+"<b>";        	
        		k.fehler(t);
        		int ein = new MyOp().Frage("Soll diese Arbeit gespeichert?");
    			if (ein==0){
    				save s = new save();
    				new MyOp().fehler(s.dontsort(Filename ,Dieliste , false));    				
    			}
        	}
        	if (ziel.equals("Abbrechen")){
        		String t ="<html><b><font size=+1 color=red>" +
			                   "<center>"+"<i>"+
			                   "keine Aenderung ist stattgefunden!"+
			                   "<br>"+" Fertig "+"<b>";
        		k.fehler(t);
        	}
        	f.dispose();
        }
	  };		
		int i=0;		
		wort = diezeile();			
		jt = new JTextField[wort.length];
	    jl= new JLabel[wort.length];						
		jp.setLayout(new GridLayout(wort.length-1,2));
		for (i=1; i<wort.length; i++){					
			jl[i]= new JLabel("<html><b><font size=+1>" +
			                   "<center>"+"<i>"+
			                   ""+wort[i]+"<b>",JLabel.CENTER);
			jt[i]= new JTextField(wort[i].length());
			jt[i].setText(wort[i]);//.trim());			
			jp.add(jl[i],"West");
			jp.add(jt[i],"East");				
		}
		//jp.setBackground(new Color(10,0,150));		
		jp.setBackground(Color.yellow);				
		JPanel bt = new JPanel(new GridLayout(1,2));
		bt.setBackground(new Color(200,10,100));		
		jb[0].addActionListener(BL);jb[0].setBackground(Color.red);		
		jb[1].addActionListener(BL);jb[1].setBackground(Color.red);		
		bt.add(jb[0],"West");
		bt.add(jb[1],"East");		
		JPanel sud = new JPanel ();//new GridLayout(2,0));
  		sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));
  		sud.setBorder(new BevelBorder(BevelBorder.RAISED));
  		sud.setBackground(new Color(180,10,50));		
		
		JPanel nord = new JPanel ();//new GridLayout(2,0));
  		nord.setLayout(new BoxLayout(nord, BoxLayout.Y_AXIS));
  		nord.setBorder(new BevelBorder(BevelBorder.RAISED));
  		nord.setBackground(Color.yellow);				
		nord.add(jp,"North");		
		JPanel fein = new JPanel ();//new GridLayout(2,0));
		fein.setBackground(Color.yellow);				
		nord.add(fein);		
		dieliste b = new dieliste(Dieliste,Filename);
		b.init();
		b.start();
		
		sud.add(bt,"South");
		f.getContentPane().add(b,"Center");
		f.getContentPane().add(nord,"North");		
		f.getContentPane().add(sud,"South");		
		f.pack();		
		f.setBounds(100, 0, 600, 500);
		f.setVisible(true);		
	}		
	public String[] diezeile(){			
		wort =at.zeile(Dieliste[Position]);
		return(wort);
	}
	public String fertige(){
		String trene=",";
			/*if(new MyOp().Frage("mit Komma trenen ?")== 0){
							trene=" , ";
					}
			*/
        	ga=""+jt[1].getText();
			for (int i=2; i<wort.length; i++){				
        			ga =ga+ trene + jt[i].getText().trim();
			}
        	Dieliste[Position]=ga;
			test(Dieliste);
		return(Dieliste[Position]);		
	}
	public void test(String []neue){			        	
			for (int i=1; i<neue.length; i++){       			
				System.out.println(i+" "+neue[i]);
			}	
	}
	public static void main(String[] args) {
		sucheDate su =new sucheDate("0","gastro/Date/Kliste.dat",0);		
	 	String[] str = su.myDaten();	 		
		Bearbeite b = new Bearbeite(str,14,"gastro/Date/Kliste.dat");	
	}
}
