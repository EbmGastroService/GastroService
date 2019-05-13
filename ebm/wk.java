package ebm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.event.*;
import javax.swing.border.*;
import java.io.*;
import java.util.*;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
import com.printer.*;
//import ebm.*;
import ver6.*;
public class wk extends JApplet{ 
	String WK_Code;
	String WR_Code="";	
	JTextArea tb;	
	JButton printme;
	JPanel sud;
	JButton ok = new JButton("drucke Rechnung");
	MyOp mo = new MyOp();
	sucheDate su;
	ausTeilen a;
	JTable mytab ;
	JScrollPane sp;
	JList dataList;
	JLabel tf = new JLabel("Rechnugs Total in Euro ",JLabel.CENTER);
	int xy;
	int position;
	long lastmodi;
	
	public wk (String a_WK_Code)  {
		tb  = new JTextArea(5,15);
		sud = new JPanel ();//new GridLayout(4,0));		
		mytab =new JTable();
		sp = new JScrollPane(mytab);
		WK_Code =fName(a_WK_Code);	
		xy = 0;
	//	init();
	}
	public wk (String a_WK_Code , String a_WR_Code){
		WK_Code = a_WK_Code ;
		WR_Code = a_WR_Code;
		xy = 0;
		dataList = new JList(lesen()); 
		dataList.setFont(new Font("Courier New",0,14));
		tb  = new JTextArea(5,15);
		sud = new JPanel ();//new GridLayout(4,0));
		mytab =new JTable();
		sp = new JScrollPane(mytab);
	//	init();
	
	}
	public wk (){
		WK_Code = "0000X00010101";
		WR_Code = "RN010101/1";

		xy = 0;
		dataList = new JList(lesen()); 
		dataList.setFont(new Font("Courier New",0,14));
		tb  = new JTextArea(5,15);
		sud = new JPanel ();//new GridLayout(4,0));
		mytab =new JTable();
		sp = new JScrollPane(mytab);
	//	init();
	
	};
	long getLastmodi()   {return lastmodi;}
                	void setLastmodi(  long modi) {lastmodi=modi;}
                	String getWK_Code()   {return WK_Code;}
                	String getWR_Code()   {return WR_Code;}
                	void setWK_Code(  String a_WK_Code) {WK_Code= a_WK_Code;}
                	void setWR_Code(  String a_WR_Code) {WR_Code= a_WR_Code;}
                	public String[] lesen() {
                		su = new sucheDate("0","gastro/date/kon.dat",0);
                		return(su.myDaten());
                	}
                	public String suchen(){
                		String myzeile="";
                		String mytok="";
                		int x=0;       		                		
                		String str = new MyOp().eingabe("<Html>Rechnung Nr/ KundenNummer Eingeben:"+
                		                 "<br><font size=2>Zb.: N311 oder K2514017</font>");
                		myzeile = new sucheDate(str,"gastro/date/kon.dat",0).wahlListe();
                		System.out.println("gefunden :"+myzeile);
                		return(myzeile);
                	}                	
                	public String[][] der_file(String str) {                		
           				su = new sucheDate(str);
                		long modi=su.filemodi();                		
                		setLastmodi(modi);
                		return(su.teiledaten());
                	}
                	public String[] kundendaten(String erg){
                		String str="";
                		int cod=0;
                         for(int i=0; i<erg.length()-1;i++){
                			if(erg.toLowerCase().charAt(i)=='x') cod=i;
                		}                		
                		str = erg.substring(0,cod);//+" ";
                		su = new sucheDate(str,"gastro/date/kliste.dat",1);
                		a = new ausTeilen();
                		String[] at = a.zeile(su.wahlListe());
                		//System.out.println("kundendaten: "+at[1]+at[2]+at[3]);
                		return(at);
                	}
                	public String fName(){                		
                		String[] v ;                		
                		v= lesen();		
                		String zeile=new Jlist(v,false).element();
                		String[]wcod=new Rcode(zeile,1).genkr;
                		String erg=wcod[0];//Kname(zeile); 0 ist um speichern;
                		setWR_Code(""+wcod[1]);                		
                		tb.append("\n	K-RN: "+ erg+" "+WR_Code);            
                		//System.out.println("fName():"+path);          
                		return erg;
                	}
                	public String fName(String zeile){                		
                		String[]wcod=new Rcode(zeile,1).genkr;
                		String erg=wcod[0];//Kname(zeile); 0 ist um speichern;
                		System.out.println(erg);
                		setWR_Code(""+wcod[1]);                		
                		tb.append("\n	K-RN: "+ erg+" "+WR_Code);                      	
                		return erg;
                	}
                	public String[] kfdaten(String fn){
                	//	System.out.println("kdaten fn:"+fn);                	
                		myDatum md=new myDatum(lastmodi);
                		String[] kdaten = kundendaten(fn);
                		kdaten[0]="K U N D E N D A T E N";
                		if(WK_Code.length()>0)kdaten[1]=WK_Code;else kdaten[1]=fn;//erg;
                		kdaten[2]=kdaten[2]+" "+kdaten[3];//name vorname
                		kdaten[3]=kdaten[4];    		//Adresse
                		kdaten[4]=kdaten[5]+" "+kdaten[6].trim();//Plz Ort
                		kdaten[5]=kdaten[7];//Tel
                		kdaten[6]=kdaten[8];//Tel
                		kdaten[7]=md.d();//Datum
                		kdaten[8]=md.time();//Uhrzeit
                		/*tb.append("\n	Name: "+kdaten[2]);
                		tb.append("\n	Adresse: "+kdaten[4]+","+kdaten[3]);
                		tb.append("\n	Tel: "+kdaten[5]+"	Mobile: "+kdaten[6]);
                		tb.append("\n	Datum: "+kdaten[7]+"	Zeit: "+kdaten[8]);*/
                	
                	//	for(int i=0 ; i<kdaten.length;i++)tb.append(kdaten[i]);
                		return kdaten;
                	}
                	public String[][] zumformular(String fn){                		
 						String erg=	"gastro/k_r/"+fn+".dat";
                		String[][] v = der_file(erg);                	
                		return v;
                	}
                	public String[][] openrechnung(String fn){
                		tb.setText("");                		                		
                		String[][] v1 ;                			                
                		String path,zeile;
                		if (fn==""){
                			zeile=new Jlist(lesen(),false).element();
                			path=fName(zeile);                			
                		} else path=fn;
                		String erg= path;		                		             		
                		path="gastro/k_r/"+path+".dat";	
                		v1= der_file(path);     
                		String[] kdate=kfdaten(erg);
                		myDatum md=new myDatum(lastmodi);                		
                		//kdate[7]=md.d();//Uhrzeit
                		//kdate[8]=md.time();//Uhrzeit
                		
                		//tb.append("\n	K-RN: "+ erg+" "+WR_Code);                		
                		//for(int i=0 ; i<kdate.length;i++){
                		tb.append("\n	Name: "+kdate[2]);
                		tb.append("\n	Adresse: "+kdate[4]+","+kdate[3]);
                		tb.append("\n	Tel: "+kdate[5]+"	Mobile: "+kdate[6]);
                		tb.append("\n	Datum: "+kdate[7]+"	Zeit: "+kdate[8]);
                		umsatz(v1);                		
                		return(v1);                				
                	}
                	public void ZeigeRechnung(){
                		tb.setText("");                		
                		String[][] v1 ;                			                
                		String path=dataList.getModel().getElementAt(position).toString();    	                		
                		String erg=fName(path);
                		//tb.append("\n	K-RN: "+ erg+" "+WR_Code);                		
                		path="gastro/k_r/"+erg+".dat";	
                		v1= der_file(path);             
                		String[] kdate=kfdaten(erg);
                		tb.append("\n	Name: "+kdate[2]);
                		tb.append("\n	Adresse: "+kdate[4]+","+kdate[3]);
                		tb.append("\n	Tel: "+kdate[5]+"	Mobile: "+kdate[6]);
                		tb.append("\n	Datum: "+kdate[7]+"	Zeit: "+kdate[8]);
                		umsatz(v1);                		
                		//tabeleZero();
                		intabelle(v1);         				                		
                	}
                	public void drucken(String fn){
                		String[] kdaten = kfdaten(fn);		
                		String[][] fdaten = openrechnung(fn);//zumformular(fn);                   		
                		String []kopf={"pos","Menge","Bezeichnung","E-Preis","Total"};
                		String[] firma = new firma().data();
                		//DF md = new DF(""+fn);
                		kdaten[1]=WR_Code;//new ausTeilen().koma(path)[0];		
                		//md.druck
                		new DF(fn,firma,kdaten,fdaten,kopf,new papier().getSkale());
                	}
                	public void Drucken(){
                		String path=dataList.getModel().getElementAt(position).toString();                		
                		String erg= fName(path);//new ausTeilen().koma(path)[1];		
                		path="gastro/k_r/"+erg+".dat";	                
                		//System.out.println("Path: "+path+", und Ergebnis"+erg);
                		String[][] fdaten = der_file(path);//zumformular(fn);                	
                		String[] kdaten = kfdaten(path);		
					
                		intabelle(fdaten);     							
                		String []kopf={"pos","Menge","Bezeichnung","E-Preis","Total"};
                		String[] firma = new firma().data();
                		//DF md = new DF(""+erg);
                		kdaten[1]=new ausTeilen().koma(path)[0];
                		//new DF
                		new GF(erg,firma,kdaten,fdaten,kopf,new papier().getSkale());

                	}
					public void formular(String fn){
                		tb.setText("");
                		int x=0;                	                			
                		String[][] fdaten = openrechnung(fn);//zumformular(fn);  
						String[] kdaten = kfdaten(fn);	
                	//	mytab=new JTable(fdaten.length,fdaten[0].length);
                		intabelle(fdaten);     							
                		String []kopf={"pos","Menge","Bezeichnung","E-Preis","Total"};
                		String[] firma = new firma().data();
                		//DF md = new DF(""+fn);
						kdaten[1]=WR_Code;		
                		new GF(fn,firma,kdaten,fdaten,kopf,new papier().getSkale());

                	}     
                	public void formular_zeige(String fn){
                		tb.setText("");
                		int x=0;                	                		
                		String[][]fdaten = openrechnung(fn);
                		String[]kdaten = kfdaten(fn);		                		
                		intabelle(fdaten);     							                		
                	}
                
                	public String gesucht(){
                		String ergebnis=suchen(); 
                		if(ergebnis!=""){
                			tb.setText("");
                			if(ergebnis.toLowerCase().charAt(0)=='r'){
                				String erg=fName(ergebnis);
                				setWR_Code(getWR_Code());                		
                				formular(""+erg);
                			}
                		}
                		return ergebnis;
                	}                	
                	public void umsatz(String[][] v1){
                		float sum=0;
                		for (int i=0; i<v1.length;i++){
                			if (v1[i]!= null) {                 				
                				sum=sum+Float.parseFloat(""+v1[i][4]);
                			}
                		}    
                		tf.setText("Rechnung Total Euro :"+doub(sum));                		
                	}
                String doub(String str){
                		return new MyZahl().deci(Double.parseDouble(str));
                	}
                String doub(double str){
                		return new MyZahl().deci(str);
                	}
               public JTable tabeleZero(String[][]inhalt){                	               	
                	return new JTable(inhalt.length,inhalt[0].length);
                }         
                public JPanel codegesucht(){
                	F_init();
                	JPanel jp=new JPanel();
                	jp.setLayout(new BorderLayout());
                	jp.setOpaque(true);
                	jp.setBackground(new ebm.myColor("hg").getColor());
                	formular_zeige(getWK_Code());
                	JLabel rlab=new JLabel(WR_Code,JLabel.CENTER);
                	rlab.setOpaque(true);
                	rlab.setBackground(Color.white);
                	rlab.setForeground(Color.blue);      
                	sp.setPreferredSize(new Dimension(400, (mytab.getRowCount()+1)*20));
                	jp.add(rlab,BorderLayout.PAGE_START);
                	jp.add(sud,BorderLayout.PAGE_END);
                	frame jf=new com.options.frame("60 Secunden Ansicht: "+WR_Code+" KN: "+getWK_Code(),60);//60 secunden
                	jf.getContentPane().add(jp,BorderLayout.PAGE_START);
                	jf.getContentPane().add( printme,BorderLayout.PAGE_END );                	
                	jf.pack();//
                	//jf.setSize(500,500);
                	jf.setVisible(true);                	
                	return jp;
                }
     
    
	class druck implements ActionListener {        
      public void actionPerformed ( ActionEvent e ) {
      				drucken(getWK_Code());
      }
    }  
       
    class ll implements ListSelectionListener {   
      	public void valueChanged(ListSelectionEvent e) {
    	    if(e.getValueIsAdjusting()) return;      		      		    
        		tf.setText("");
        		Object[] items=dataList.getSelectedValues();
      			position =dataList.getSelectedIndex() ; 		
      			ZeigeRechnung();
      			//element();
		 }
    };
       	public void intabelle(String[][] inhalt){
		mytab=tabeleZero(inhalt);
       		float sum=0;       		
			for(int i=0;i<inhalt.length;i++){									
				for(int x=0; x < inhalt[i].length-2;x++){
					mytab.setValueAt(inhalt[i][x],i,x);				
				}
				mytab.setValueAt(doub(inhalt[i][3]),i,3);
				mytab.setValueAt(doub(inhalt[i][4]),i,4);								
				sum=sum+Float.parseFloat(""+inhalt[i][4]);
			}		
			sp.setPreferredSize(new Dimension(400, mytab.getRowCount()*20));
			sp.setViewportView(mytab);
       		sud.validate();
			tf.setText("Rechnung Total Euro: "+doub(sum));                					
       	}
       	class Warenkorb implements ActionListener {
       		public void actionPerformed(ActionEvent e) {
       			gesucht();
       		}
       	}
        public void F_init(){
       		sud = new JPanel ();//new GridLayout(4,0));
       		//tb = new JTextArea(8,15);
       		sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));
       		sud.setBorder(new TitledBorder("Tabelen ergebnissen"));
        	sud.setOpaque(true);
       		sud.setBackground(new ebm.myColor("hg").getColor());
       		tb.setBackground(Color.white);
       		tb.setFont(new Font("",1,14));
       		tf.setFont(new Font("",1,16));
       		mytab.setFont(new Font("",1,14));
       		mytab.setBackground(Color.white);      	
        	sp.setPreferredSize(new Dimension(400, (mytab.getRowCount()+5)*20));        	
        	sp.setOpaque(true);
        	sp.setBackground(Color.white);        		
       		tf.setBackground(new ebm.myColor("hg").getColor());
        	sud.add(new JScrollPane(tb),BorderLayout.PAGE_START);
        	sud.add(sp);
       		sud.add(tf,BorderLayout.PAGE_END);
       		
       		printme=new JButton("Ausdrucken");
       		printme.addActionListener(new druck());
       		getContentPane().setLayout(new BorderLayout());
       		getContentPane().add( sud,BorderLayout.PAGE_START);             		       		
       		getContentPane().setVisible(true);
       		//setDefaultCloseOperation(1); 
       	}
       	public void init(){
       		//	ta = new JTextArea(10,15);                		//tb = new JTextArea(8,8);
       		JPanel top = new JPanel (new GridLayout(1,0));
       		top.add(ok);
       		ok.addActionListener(new Warenkorb());
       		top.setOpaque(true);
       		top.setBackground(new ebm.myColor("hg").getColor());
       		top.setPreferredSize(new Dimension(120, 25));
       		JPanel topP = new JPanel(new BorderLayout());
       		topP.add(top,BorderLayout.PAGE_START);
       		//topP.add(new JScrollPane());
       		topP.setOpaque(true);
       		topP.setBackground(new ebm.myColor("hg").getColor());
       		topP.setBorder(new TitledBorder("Bedinungstasten"));
       		sp.setOpaque(true);
       		sp.setBackground(new ebm.myColor("hg").getColor());
       		mytab.setOpaque(true);
       		mytab.setBackground(new ebm.myColor("hg").getColor());		
       		dataList.setBackground(new ebm.myColor("vg2").getColor());
       		dataList.addListSelectionListener(new ll());
       		//JPanel sud = new JPanel (new  GridLayout(4,2));
       		//JPanel sud = new JPanel ();//new GridLayout(4,0));
       		sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));
       		sud.setBorder(new TitledBorder("Tabelen ergebnissen"));
       		sud.setBackground(new ebm.myColor("hg").getColor());//new Color(255,204,204));
       		tb.setBackground(Color.white);
       		//inzeile(openrechnung());
       		tf.setFont(new Font("Courier New",1,18));
       		tb.setFont(new Font("Courier New",0,14));
       		JPanel east = new JPanel (new BorderLayout());
       		mytab.setFont(new Font("Courier New",0,14));
       		//mytab.setBackground(Color.white);
       		sud.add(new JScrollPane(tb),BorderLayout.PAGE_START);
       		//mytab.setPreferredSize(new Dimension(300, 350));
       		sp.setPreferredSize(new Dimension(400, (mytab.getRowCount()+5)*20));
       		sud.add(sp);
       		tf.setBackground(new ebm.myColor("hg").getColor());//;new Color(255,204,204));
       		sud.add(tf,BorderLayout.PAGE_END);
       		//sud.add( new JPanel(),"East");
       		JScrollPane js=new JScrollPane (dataList);
       		//js.setPreferredSize(new Dimension(200, 500));
       		east.setBorder(new TitledBorder("Rechnungsliste"));
       		east.setBackground(new ebm.myColor("hg").getColor());//new Color(240,120,120));
       		east.add(js);
       		printme=new JButton("Ausdrucken");
       		printme.addActionListener(new druck());
       		//cp = getContentPane();
       		//getContentPane().setTitle("Waren Korb");
       		getContentPane().setLayout(new BorderLayout());
       		//setDefaultCloseOperation(DISPOSE_IN_CLOSE);
       		//setBackground(new Color(250,120,120));
       		
       		getContentPane().add(east,BorderLayout.EAST );
       		getContentPane().add(topP,BorderLayout.WEST );
       		//add( BorderLayout.WEST , new JPanel ());
       		//getContentPane().add( top,BorderLayout.PAGE_START);
       		getContentPane().add( sud,BorderLayout.CENTER);
       		getContentPane().setVisible(true);
       		
       		//add( BorderLayout.SOUTH ,new JPanel());
       		
       	}
       	public static void main(String[] args) {
       		//	Console.run(new wk(), 700, 450);
       		new wk("R120706N15K0000X00013E").codegesucht();
       		//	new wk("0000X00292D030806N501").codegesucht();   		
       	}
}

