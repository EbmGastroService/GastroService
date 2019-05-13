// created on 18.04.2003 at 00:30
package com.tabel;
import com.search.*;
import com.units.save;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class RepTest extends JApplet{	
    JFrame f ;
	JButton[] jl;
	JButton[] jd;
	JPanel top = new JPanel ();
	JPanel sud = new JPanel (); 
	JPanel mit;
		public RepTest(String Titel){
			f = new JFrame(Titel);				    	
    		String[] sicherheit = {
    			"gastro/date/wliste.dat","gastro/date/wgliste.dat","gastro/date/kliste.dat",
			"gastro/date/jliste.dat","gastro/date/fliste.dat",
			"gastro/date/kbuch.dat","gastro/date/kon.dat","gastro/date/wkiste2.dat","gastro/temp/form01.pre"
    		};
			String[] v = new sucheDate("gastro/date/datenliste.dat").myDaten();  			  		    
			if(v.length<sicherheit.length){
				new save().dontsort("gastro/date/datenliste.dat",sicherheit,false);			
				v = new sucheDate("gastro/date/datenliste.dat").myDaten();  			  		    		
			}
			
			String[]toolTip={"Warenliste","Waren Gruppen Liste","Kundenliste","Offener Posten Journal","Kasierer Journal",
			"Kassa Buch","Erfasste Rechnungen","Kunden Statistik-Wie oft haben die bestellt?",
			"Letzten Tabellen Betrachtung beim Kasierer Bericht"};
    		
    		top.setLayout(new GridLayout(v.length+1,0));
  			top.setBorder(new BevelBorder(BevelBorder.RAISED));
			sud.setLayout(new GridLayout(v.length+1,0));
  			sud.setBorder(new BevelBorder(BevelBorder.RAISED));
			top.add(new JLabel("Betrachten"));
			top.setBackground(Color.green);			
           	sud.setBackground(Color.yellow);
			sud.add(new JLabel("Drucken"));
    		jl = new JButton[v.length];
			jd = new JButton[v.length];
    		for(int i =0; i< (v.length); i++){   
    			String myv=""+v[i];
    			String btxt="jliste.dat";
    			if(myv.indexOf("gastro/date/")!=-1)btxt=myv.substring(12);    			
    			if(myv.indexOf("gastro/temp/")!=-1)btxt=myv.substring(12);    			
    			if(btxt.indexOf(".")!=-1) btxt=btxt.substring(0,(btxt.length()-4));
				jl[i]= new JButton(btxt);    			
    			jl[i].setActionCommand("l");
    			jl[i].addActionListener(new Tabellen());
    			jl[i].setToolTipText("Betrachtung von "+toolTip[i]);
    		//	jl[i].setBackground(Color.red);
    			top.add(jl[i]);   			
    			jd[i]= new JButton(btxt);    			
    			jd[i].setActionCommand("d");
    			jd[i].addActionListener(new Tabellen());
    			jd[i].setToolTipText("Ausdruck von "+toolTip[i]);
    		//	jd[i].setBackground(Color.green);
    			sud.add(jd[i]);   			
    		}
    		top.setSize(new Dimension(200,250));
			sud.setSize(new Dimension(200,250));
    	JPanel cp=new JPanel();
		cp.setBackground(Color.orange);	
		cp.add(BorderLayout.NORTH , top);			
		cp.add(BorderLayout.SOUTH, sud);				
		//cp.setSize(new Dimension(200,500));	
		f.setIconImage(getFDImage()); 
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setContentPane(cp);			
		f.pack();
		//f.setSize(new Dimension(200,500));
		//f.show();
		f.setVisible(true);  
		
		}	
		 protected static Image getFDImage() {
        java.net.URL imgURL = RepTest.class.getResource("/gastro/image/ico1.gif");
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return null;
        }
    }
 class Tabellen implements ActionListener {
    public void actionPerformed(ActionEvent e) {  
     JButton target = (JButton)e.getSource(); 	
    	String txt=target.getText();
      String str = e.getActionCommand();  
    	System.out.println(str+", "+txt);
    //	String st="";
		if(txt.equals("wkiste2"))new ver6.Binden();	
    		if(str.equals("l")){    		
    			if(txt.equals("form01")||txt.equals("Form01"))new myTabel("gastro/temp/"+txt+".pre",true);else   			
    			new myTabel("gastro/date/"+txt+".dat",true);   			
    		}else
    			if(str.equals("d")){    			
    				if(txt.equals("form01")||txt.equals("Form01"))new Report("gastro/temp/"+txt+".pre");else   			
    			new Report("gastro/date/"+txt+".dat");   			
    		}
    	//	System.out.println(st);
    }
 }
  public static void main(String s[]) {	  	
  	 RepTest rt = new RepTest(" my tabele ");
  	  //rt.init();  	
	 //Console.run(new RepTest("my Report test "),200,700);
   }
}
