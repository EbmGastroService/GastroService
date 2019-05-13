// created on 19.10.2004 at 00:47
package ebm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
import com.printer.*;
public class WKA extends JApplet{ 
	String WK_Code;
	JTextArea ta;
	JTextArea tb;
	String FrameTit;
	//tabelle tab = new tabelle();								
    JPanel sud;
	MyOp mo = new MyOp();
	sucheDate su;
	ausTeilen a;
	JTable mytab = new JTable(70 , 5);;
	//Container cp = getContentPane();
	JTextField tf = new JTextField("		Rechnugs Total in Euro ",80);
	int xy;
	JButton printme;
	long lastmodi;
	
	public WKA (String a_WK_Code)  {
		WK_Code = a_WK_Code ;
		xy = 0;
		tb  = new JTextArea(8,30);
	}
	public WKA (String a_WK_Code,String titel)  {
		WK_Code = a_WK_Code ;
		xy = 0;
		FrameTit=titel;
		tb  = new JTextArea(8,30);
	}
	public WKA (){
		WK_Code = gesucht();
		xy = 0;
		//FrameTit=titel;
		tb  = new JTextArea(6,30);
	};
                	String getWK_Code()   {return WK_Code;}
                	void setWK_Code(  String a_WK_Code) {WK_Code= a_WK_Code;}
                	long getLastmodi()   {return lastmodi;}
                	void setLastmodi(  long modi) {lastmodi=modi;}
                	public String[] lesen() {
                		su = new sucheDate("0","Date/Wkorb.dat",0);
                		return(su.myDaten());
                	}
                	public String suchen(){
                		String myzeile="";
                		String mytok="";
                		int x=0;
                		mo= new MyOp();
                		String str=null;
                		str = mo.eingabe("Rechnung Nr/ KondenNummer Eingeben:");
                		su = new sucheDate(str,"Date/Wkorb.dat",x);
                		myzeile=su.wahlListe();
                		return(myzeile);
                	}                	
                	public String[][] der_file(String str) {                		
           				su = new sucheDate(str);
                		long modi=su.filemodi();
                		//System.out.println("der File: "+str+"  ist am "+new myDatum(modi).time()+" erstellt.");
                		setLastmodi(modi);
                		return(su.teiledaten());
                	}
                	public String[] kundendaten(String erg){
                		String str="";
                		int cod=0;
                                for(int i=0; i<erg.length()-1;i++){
                			if(erg.charAt(i)=='X') cod=i;
                		}                		
                		str = erg.substring(0,cod);//+" ";
                		su = new sucheDate(str,"Date/Kliste.dat",1);
                		a = new ausTeilen();
                		String[] at = a.zeile(su.wahlListe());
                		//System.out.println("kundendaten: "+at[1]+at[2]+at[3]);
                		return(at);
                	}
                	public String fName(){                		
                		String[] v ;                		
                		v= lesen();		
                		String path=new Jlist(v,false).element();             
                		String erg= new ausTeilen().koma(path)[1];		
                		
                		tb.append("\n	K-RN: "+ erg);            
                		//System.out.println("fName():"+path);          
                		return erg;
                	}
                	public String[] kfdaten(String fn){
                		//System.out.println("kdaten fn:"+fn); 
                		
                		//0,1............,2.....,3......,4...........,5.......,6........,7.........
                		//0,Familien Name,Vorname,Adresse,Postleitzahl,Ortschaft,Fest Tel.,Mobil Tel.
                		myDatum md=new myDatum(lastmodi);
                		String[] kdaten = kundendaten(fn);
                		kdaten[0]="K U N D E N D A T E N";
                		kdaten[1]=fn;//erg;
                		kdaten[2]=kdaten[2]+" "+kdaten[3];//name vorname
                		kdaten[3]=kdaten[4];    		//Adresse
                		kdaten[4]=kdaten[5]+" "+kdaten[6].trim();//Plz Ort
                		kdaten[5]=kdaten[7];//Tel
                		kdaten[6]=kdaten[8];//Tel
                		kdaten[7]=md.d();//Datum
                		kdaten[8]=md.time();//Uhrzeit
                		//for(int i=0 ; i<kdaten.length;i++)System.out.println("kdaten["+i+"]:"+kdaten[i]);
                		return kdaten;
                	}
                	public String[][] zumformular(String fn){                		
 						String erg=	"k_r/"+fn+".dat";
                		String[][] v = der_file(erg);                	
                		return v;
                	}
                	public String[][] openrechnung(String fn){
                		tb.setText("");                		
                		String[] v ;                		
                		v= lesen();
                		String[][] v1 ;                			                
                		String path;
                		if (fn=="") path=new Jlist(v,false).element();else path=fn;
                		String erg= path;		
                		//tb.append("	RN: "+ erg+"	erfasst um: "+lastmodi);
                		
                		path="k_r/"+path+".dat";	
                		v1= der_file(path);     
                		String[] kdate=kfdaten(erg);
                		myDatum md=new myDatum(lastmodi);                		
                		//kdate[7]=md.d();//Uhrzeit
                		//kdate[8]=md.time();//Uhrzeit
                		tb.append("\n	K-RN: "+ erg);
                		//for(int i=0 ; i<kdate.length;i++){
                		tb.append("\n	Name: "+kdate[2]);
                		tb.append("\n	Adresse: "+kdate[4]+","+kdate[3]);
                		tb.append("\n	Tel: "+kdate[5]+"	Mobile: "+kdate[6]);
                		tb.append("\n	Datum: "+kdate[7]+"	Zeit: "+kdate[8]);
                		//for(int i=0 ; i<kdate.length;i++)System.out.println("OR kdaten["+i+"]:"+kdate[i]);
                		umsatz(v1);                		
                		return(v1);                				
                	}
                	public void formular(String fn){
                		tb.setText("");
                		int x=0;                	                		
                		String[][] fdaten = openrechnung(fn);//zumformular(fn);   
                		String[] kdaten = kfdaten(fn);		
                		//mytab=new JTable(fdaten.length,fdaten[0].length);
                		intabelle(fdaten);     							/*
                		String []kopf={"pos","Menge","Bezeichnung","E-Preis","Total"};
                		String[] firma = new firma().data();
                		mydruck md = new mydruck(""+kdaten[1]);
                		md.druck(firma,kdaten,fdaten,kopf,new papier().getSkale());
                		*/

                	}
                	void drucken(String fn){
                		String[] kdaten = kfdaten(fn);		
                		String[][] fdaten = openrechnung(fn);//zumformular(fn);                   		
                		String []kopf={"pos","Menge","Bezeichnung","E-Preis","Total"};
                		String[] firma = new firma().data();
                		DF md = new DF(""+FrameTit);
                		md.druck(firma,kdaten,fdaten,kopf,new papier().getSkale());
                	}
                
                	public String gesucht(){
                		tb.setText("");
                		String ergebnis=suchen();
                		formular(ergebnis);                		
                		return ergebnis;
                	}
                	public JPanel codegesucht(){
                		init();
                		JPanel jp=new JPanel();
                		jp.setLayout(new BorderLayout());
                		formular(getWK_Code());               
                		JLabel rlab=new JLabel(FrameTit,JLabel.CENTER);
                		rlab.setBackground(Color.white);
                		rlab.setForeground(Color.blue);
                		jp.add(rlab,BorderLayout.PAGE_START);
                		jp.add(sud,"Center");
                		JFrame jf=new JFrame("Ansicht: "+FrameTit+" KN: "+getWK_Code());
                		jf.getContentPane().add(jp,"Center");
                		jf.getContentPane().add( printme,BorderLayout.PAGE_END );
                		jf.pack();
                		jf.setSize(500,500);
                		jf.setVisible(true);
                		jf.setDefaultCloseOperation(2); 
                	return jp;
                	}
             
                	public void gelesen(){                	
                		String[] v = lesen();                		
                		for (int i=0; i<v.length;i++){
                			if (v[i]!= null){                				
                			}
                		}
                	            		
                	}
                	public void umsatz(String[][] v1){
                		float sum=0;
                		for (int i=0; i<v1.length;i++){
                			if (v1[i]!= null) {                 				
                				sum=sum+Float.parseFloat(""+v1[i][4]);
                			}
                		}    
                		tf.setText("	Rechnung Total :"+doub(sum));                		
                	}
                String doub(String str){
                		return new MyZahl().deci(Double.parseDouble(str));
                	}
                String doub(double str){
                		return new MyZahl().deci(str);
                	}
               public void tabeleZero(){                	
                	for(int i=0; i<mytab.getRowCount(); i++){                		
                		for(int x=0; x<5; x++){                		
                			mytab.setValueAt("",i,x); 
                		}               		
                	}                    
                }                         	
       	public void intabelle(String[][] inhalt){
			tabeleZero();
       		float sum=0;
       		int i,x=0;
			for(i=0;i<inhalt.length;i++){									
				for(x=0; x < inhalt[i].length-2;x++){
					mytab.setValueAt(inhalt[i][x],i,x);
				}
				mytab.setValueAt(doub(inhalt[i][3]),i,3);mytab.setValueAt(doub(inhalt[i][4]),i,4);
				sum=sum+Float.parseFloat(""+inhalt[i][4]);
			}		
			tf.setText("	Rechnung Total: "+doub(sum));                					
       	}              
   class druck implements ActionListener {        
      public void actionPerformed ( ActionEvent e ) {
      				drucken(getWK_Code());
      }
    }
       	public void init(){
       		sud = new JPanel ();//new GridLayout(4,0));
       		tb = new JTextArea(8,15);
       		sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));
       		sud.setBorder(new TitledBorder("Tabelen ergebnissen"));
       		sud.setBackground(new Color(255,204,204));
       		tb.setBackground(Color.white);
       		tb.setFont(new Font("Courier New",0,14));
       		tf.setFont(new Font("Courier New",1,16));
       		mytab.setFont(new Font("Courier New",0,14));
       		mytab.setBackground(Color.white);
       		mytab.setPreferredSize(new Dimension(400, 300));
       		JScrollPane sp = new JScrollPane(mytab);
       		sp.setPreferredSize(new Dimension(400, 300));
       		sud.add(new JScrollPane(tb),"North");
       		tf.setBackground(new Color(255,204,204));
       		sud.add(tf,"Center");
       		sud.add(sp,"South");
       		printme=new JButton("Ausdrucken");
       		printme.addActionListener(new druck());
       		getContentPane().setLayout(new BorderLayout());
       		getContentPane().add( sud,BorderLayout.PAGE_START);             		       		
       		getContentPane().setVisible(true);
       		//setDefaultCloseOperation(1); 
       	}
    
}


