//Kassa Buch bekommt von Journal gebuchte Stze
// die Stze enthlten der rechnung nummer, Datum und Betrag und
//KasaSaldo wird entern berechnet.
package ebm;

import javax.swing.JApplet;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
import javax.swing.border.*;
import javax.swing.BoxLayout;
//import java.text.*;
import com.search.sucheDate;
import com.units.MyZahl;
import com.options.MyOp;
import com.options.ausTeilen;
import com.tabel.tabelle;
import com.tabel.myTabel;
public class KB extends JApplet{
        String R_Code;
        String Datum;
        String Kcode;
        double Betrag;
        double Saldo ;        
        JTextArea ta; 
		JPanel top,sud;
		//tabelle tabe;
		myTabel tabe;
	String[] oprc=new ver6.Rb().rc();
	private JMenu menus =  new JMenu("Kasa Buch"); 
    private JMenuItem[] items = { new JMenuItem("Lesen",KeyEvent.VK_L), 
    new JMenuItem("Tageserlose",KeyEvent.VK_T),
    new JMenuItem("Monaterlose",KeyEvent.VK_M),
    new JMenuItem("Suchen",KeyEvent.VK_S)};
	JMenuBar mb;
	JScrollPane js;
//URL url;

  public KB(String a_R_Code, String a_Datum, String a_Kcode, double a_Betrag, double a_Saldo){
  	R_Code = a_R_Code ="";
  	Datum = a_Datum = "";
  	Kcode = a_Kcode = "";
  	Betrag = a_Betrag = 0;
  	Saldo = a_Saldo =0;
  	ta  = new JTextArea("Drucken Sie [Alt+K] dann [S].\nSuchen Sie nach Jahres Ergebnissen dann geben Sie 2003 oder nach Monat ZB. 01_2004"+
  	                    "\noder nach Mitarbeiter Umsatz dann geben Sie Mitarbeiter Name, oder ganz einfach lesen");
  	top = new JPanel ();
  	sud = new JPanel ();
  	mb = new JMenuBar();
  	js=new JScrollPane (ta);  	
  }

  public KB(){
  	R_Code ="RN25_06/0001";
  	Datum = "25_06_2003";
  	Kcode = "1000X00000125_06";
  	Betrag = 0;
  	Saldo = 0;
  	ta  = new JTextArea("Drucken Sie [Alt+K] dann [S].\nSuchen Sie nach Jahres Ergebnissen dann geben Sie 2003 oder nach Monat ZB. 01_2004"+
  	                    "\noder nach Mitarbeiter Umsatz dann geben Sie Mitarbeiter Name, oder ganz einfach lesen");
  	top = new JPanel ();
  	sud = new JPanel ();
  	mb = new JMenuBar();
  	js=new JScrollPane (ta);  	
	/*try{
		play(new URL("audio/1.au|2.au|3.au|4.au|5.au|6.au|7.au|8.au|9.au|0.au"));
	}catch(MalformedURLException mu){System.err.println(mu);}*/  
  }
  String getR_Code()   {return R_Code;}
  String getDatum()   {return Datum;}
  String getKcode()   {return Kcode;}
  double getBetrag()   {return Betrag;}
  double getSaldo()   {return Saldo;}
  void setR_Code( String a_R_Code) {R_Code= a_R_Code;}
  void setDatum(  String a_Datum) {Datum = a_Datum;}
  void setKcode(  String a_Kcode) {Kcode = a_Kcode;}
  void setBetrag( double a_Betrag) {Betrag = a_Betrag;}
  void setSaldo(  double a_Saldo) {Saldo = a_Saldo;}
	
	public String toString () {
		return R_Code+","+Datum+","+Kcode+","+(float)Betrag+","+(float)Saldo;
	}
	String dub(double z){
		return new MyZahl().deci(z);
	}
	public String tokenZeile(String str){
		String gebe="";
		//RN050207/2241,05_02_2007,2352007X02050207,24.3,24.3,F_Jovani,-		
		ausTeilen z=new ausTeilen();
		String[] wort = new ausTeilen().koma(str);		
		setR_Code(wort[0]);//1
		//Datum		
		setDatum(wort[1]); //2
		//Kundencode 
		setKcode(wort[2]);
		// R-betrag		
		float d=0;
		try{d=Float.parseFloat(""+wort[3]);}catch(Exception exc){d=0;}//wort[5]);
		setBetrag(d);//4
		setSaldo(Saldo+d);//5
		wort[4]=(""+getSaldo());
		gebe=wort[0];
		for ( int i =1; i <wort.length; i++){			
				gebe=gebe+","+wort[i];
		}
		return(gebe);
	}
	public String[] lesen(){
		sucheDate su = new sucheDate("gastro/date/kbuch.dat");
		return(su.myDaten());
	}
	public  String rec_sume(int x){
		return "\n "+oprc[44]+x+oprc[45]+dub(Saldo)+oprc[46];
		// Die ("+x+") erfassten Buchungen, haben einen Umsatz von ( "+dub(Saldo)+" )" );
	}
	public String umsatz(){
		MyOp mo = new MyOp();
		String[] ums;
		ums = lesen();
		setSaldo(0);
		int i=0;
		for(i=0; i<ums.length; i++){
			if (ums[i] != null)	tokenZeile(""+ums[i]);
		}return(rec_sume(i));
	}         
	public String suchen() {
		int i =0;
		setSaldo(0);
		MyOp mo = new MyOp();
		sucheDate su = new sucheDate("gastro/date/kbuch.dat");
		i=su.zeilenZahl();//filebig()-2;
		String[]v;//= new String[i];
        Object[] ob ={"RN07_05/0108,07_05_2003,10200X000707_05,+8.4,10.5,F_Mourad,-"};
		if(i>0)ob = new Object[i];
		String zeile;
		int lauf = 0;
		int pos = 0;
		int erg = 0;
		int x=0;
		String str =mo.eingabe(oprc[47]);
		/*"Geben Sie (RN...)oder (..X)"+"\nRechnung Nr./ Kunden Bezeichnung: "+"\nZB. _00035 oder 100X"+"\nJahr ZB. 2004 oder 01_2003");*/
		try{
			String filen=new ver6.basic().getPath().replace('\\','/')+"gastro/date/kbuch.dat";
			FileReader dateiStream = new FileReader(filen);
			BufferedReader ein = new BufferedReader(dateiStream);
			while (true){
				zeile = ein.readLine();
				if (zeile== null) break;
				lauf++;
				pos = zeile.indexOf(str);
				if ( pos > -1) {
					erg = lauf;
					tokenZeile(zeile);
					ob[x++]=zeile;
				}
			}
			ein.close();
		} catch(Exception e){}
		setSaldo(0);
		v=new String[x];
		for(i=0;i<x;i++)v[i]=tokenZeile(""+ob[i]);
		panel(v);
		return " "+oprc[48]+str+oprc[49]+x+oprc[50]+rec_sume(x);		
	}          
	public String[] Tageserlose(String datum){
		String[] myerlose=new sucheDate(datum,"gastro/date/kbuch.dat",0).wahlinarrayJL();
		for(int i=0;i<myerlose.length;i++)myerlose[i]=tokenZeile(""+myerlose[i]);
		return myerlose;
	}
	int intTag(String str){
		int i=0;
		try{
			i=Integer.parseInt(str);
		}catch(Exception numex){i=0;}
		return i;
	}
	public String[]KB_file_T(String file){
		int anzR=0;Saldo=0;
		String[]Tage= new String[32];
		int x=0;
		float sum=0;
		file=file.toLowerCase();
		if(file.indexOf(".datk.dat")>-1)file=file.substring(0,file.length()-5);
		String jfile=file.substring(0,file.length()-11);
		for(int t=1;t<32;t++){
			String d="";
			if(t<10)d="0"+t;else d=""+t;//file=gastro/d2006/KB1106K.dat
			int pos=file.indexOf("kb");
			String d1="";String d2="";
			if(pos>-1){
				d1=file.substring(pos+2,file.length()-5);
				d2=d+"_"+d1.substring(0,2)+"_20"+d1.substring(2,4);//23_03_2007
			}else d2="Fehler";
			String[]str=new sucheDate(d2,file,0).wahlinarrayJL();
			System.out.println("KB file:"+file+",gesuchte Datum:"+d2+"da sind :"+str.length+" Zeilen");
			if(str.length>0 && str!=null){
				Saldo=0;				
				tokenZeile(str[0]);
				String rb=R_Code.substring(9,R_Code.length());
				for(int i=1;i<str.length;i++){
					tokenZeile(str[i]);
				}
				anzR+=str.length;
				sum+=Saldo;
				Tage[x]=Datum+","+rb+" - "+R_Code.substring(9,R_Code.length())+","
				+(str.length)+","+anzR+","+(float)Saldo+","+sum;
				x++;
			}
		}
		String[]mt=new String[x];
		x=0;
		for(int i=0;i<Tage.length;i++){
			if(Tage[i]!=null){
				mt[x]=Tage[i];			
				x++;
			}
		}
		String[]m={"Januar","Febuar","März","April","Mai",
		"Juni","Juli","August","September","Oktober","November","Dezember"};
		String mfile=file.substring(file.length()-9,file.length()-5);
		String afile="m"+mfile+".dat";
		String bfile="e"+mfile+".dat";
		String mm=mfile.substring(0,2);
		int mmf=intTag(mm)-1;
		String[]tatxt={" Ergebnis das Monat [ "+m[mmf]+" ] hat folgendes:",
		" Bestellungen: "+anzR,
		" Umsatz: "+dub(sum),
		" Durchschnitt pro Bestellung: "+dub(sum/anzR),
		" Durchschnitt pro Tag: "+dub(sum/x)
		};
		for(int i=0;i<tatxt.length;i++)ta.append(tatxt[i]+"\n");
		System.out.println("Monatsfile :"+afile+" Inhalt:"+mt.length+",Monatstext e:"+bfile);
		System.out.println("Zum speichern :"+jfile+afile);
		if(sum>0){
			new com.units.save().dontsort(jfile+afile,mt,false);
			new com.units.save().dontsort(jfile+bfile,tatxt,false);
		};
		return mt;
	}
	public String[]KB_file(String jahr){
        if(jahr.toLowerCase().charAt(0)!=('d'))jahr="d"+jahr;
		return new ebm.li("gastro/"+jahr+"/kb?").files();//myd;
	}
	public String[]AlleTage(String Monat){
        List<String> tag=new ArrayList<String>();        
        int[]ml={0,31,29,31,30,31,30,31,31,30,31,30,31};
		String[]mt={"..","Januar","Febuar","Martz","April","Mai",
		"Juni","Juli","August","September","Oktober","November","Dezember"};
		String datum = new com.units.myDatum().ist();//01_07_2006
		String day=datum.substring(0,2);
		String monat=datum.substring(3,5);
		String jahr=datum.substring(6,datum.length());
		String[]jahrT=new myDir().jahr;
		if(jahrT.length>0){
			jahr=new MyOp().wahl(jahrT,"<html><font color = red><u>Welche Jahr?</u>");
			jahr=jahr.substring(1,jahr.length());
		}
		System.out.println("datum: "+datum+", day: "+day+", monat: "+monat+", Jahr: "+jahr);
		int heute=0;
        try{heute=Integer.parseInt(Monat);}catch(Exception numb){}
        String mtt=mt[heute];
		if(Monat!=""){
			try{heute=Integer.parseInt(Monat);}catch(Exception numb){}
			if(heute<10)Monat="0"+Monat;
			monat="_"+Monat+"_";
			day=""+ml[heute];
		}
		try{heute=Integer.parseInt(day);}catch(Exception numb){}             	
		double mysumme=0.0;
		int mybest=0;
		int heutev=1;
		for(int i=1;i<=heute;i++){
			String mydatum=""+i+monat+jahr;
			if(i<10)mydatum="0"+i+monat+jahr;
			setSaldo(0);
			//System.out.println("mydatum: "+mydatum);
			String[]te=Tageserlose(mydatum);//220706
			if(te.length>0){
				mysumme+=Saldo;
				mybest+=te.length;
				heutev++;
				tag.add(mydatum+";"+te.length+";"+dub(Saldo)+";"+dub(mysumme)+";"+mybest);
			}              		
		}
		String[]myErg=new String[tag.size()];
		for(int i=0;i<tag.size();i++){
			myErg[i]=tag.get(i);
		}
		if(mybest==0)mybest=1;
		String[]tatxt={" Ergebnis das Monat [ "+mtt+" ] hat folgendes:",
		" Bestellungen: "+mybest,
		" Umsatz: "+dub(mysumme),
		" Durchschnitt pro Bestellung: "+dub(mysumme/mybest),
		" Durchschnitt pro Tag: "+dub(mysumme/heutev)
		};
		for(int i=0;i<tatxt.length;i++)ta.append(tatxt[i]+"\n");
		if(myErg!=null){
			new com.units.save().dontsort("gastro/d"+jahr+"/m"+Monat+".dat",myErg,false);
			new com.units.save().dontsort("gastro/d"+jahr+"/e"+Monat+".dat",tatxt,false);
		}
		return myErg;
	}
	public String gelesen(){
		ta.setText("");	
		String[] v = lesen() ;               	
		for (int i=0; i<v.length;i++){
			if (v[i]!= null) {
				v[i]=tokenZeile(v[i]);
			}
		}
		if(v.length>1)ta.append("Es sind "+ v.length+" Buchungen erfasst wurden.");
		else ta.append("Es ist "+ v.length+" Buchung erfasst wurden.");		
		panel(v);
		String um=umsatz();
		ta.append("\n"+um);
		return um;
	}
    String[][]inclomen(String[]str){
    	if(str.length>0 &&str[0]!=null){
    		String[]w=new com.options.ausTeilen().koma(str[0]);
    		String[][]nstr=new String[str.length][w.length];
			w=new com.options.ausTeilen().koma(str[0]);			
			for(int i=0;i<nstr.length;i++){
				w=new com.options.ausTeilen().koma(str[i]);				
				for(int x=0;x<nstr[i].length;x++){
					nstr[i][x]=w[x];
				}
			}
			return nstr;
    	}else return new String[0][0];
	}
	public String[]kbfliste(){
		String[]jahrs=new ebm.li("gastro/d2?").ordner();		
		List<String>kbfl=new ArrayList<String>();
		for(int j=0;j<jahrs.length;j++){
			String[]kbf=KB_file(jahrs[j]);
			for(int jf=0;jf<kbf.length;jf++){
				kbfl.add(jahrs[j]+"/"+kbf[jf]);
			}
		}
		String[]kbfs=new String[kbfl.size()];
		for(int j=0;j<kbfs.length;j++)kbfs[j]=kbfl.get(j);
		return kbfs;
	}
	void panel(String[]v){
		new com.units.save().dontsort("gastro/temp/kbuch.dat",v,false);
		tabe=new myTabel("gastro/temp/kbuch.dat",false);
		top.removeAll();
		top.add(tabe.JS());//,BorderLayout.CENTER);
		top.add(new JLabel("<html>status: "+v.length+" Daten gefunden"),BorderLayout.PAGE_END);
		top.validate();		
		validate();		
	}
     class Mykb implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {
        	MyOp k= new MyOp();
        	String fr = e.getActionCommand();
        	if (fr.equals("Lesen")){  
        		setSaldo(0);
                	gelesen();
        	 }
        	if (fr.equals("Suchen")){            	  
        		ta.setText("");
        		ta.append("\n "+suchen());                   
         	}          
         	if (fr.equals("Tageserlose")){
         			setSaldo(0);       
         			ta.setText("");                	
         			String[]te=Tageserlose(new com.units.myDatum().ist1());//220706
         			panel(te);         			
         			ta.append("\n Der Tageserlose hat "+te.length+" erfasste Buchungen gefunden."+rec_sume(te.length)); 
         	}
         	if (fr.equals("Monaterlose")){
         			ta.setText("");                	
         			String[]kbf=kbfliste();
         			if(kbf.length>0){
         			String eingabe=new MyOp().wahl(kbf,"<html><font color = red><u>Monat Ergebnise</u>");         			
         			String filen="gastro/"+eingabe;//+"k.dat";	
         			String[]te=new sucheDate(filen).myDaten();
         			String[]str={"Datum","von bis R","T-R","G-R","T-Betrag","M-Total"};	
         			javax.swing.JScrollPane tab =new RcTab(inclomen(KB_file_T(filen)),str).app();
         			com.options.frame myf=new com.options.frame("Tagesverkauf",100);
         			myf.setContentPane(tab);myf.setVisible(true);myf.pack();
         			setSaldo(0);	
         			for (int i=0; i<te.length;i++){
    	             if (te[i]!= null) {
    	             	 te[i]=tokenZeile(te[i]);
    	             }            	     
         			}
         			panel(te);         				
         			}
         			//ta.append("\n"+" Der Tageserlose hat "+te.length+" erfasste Buchungen gefunden."+rec_sume(te.length)); 
         	}
        }
   	}
            
       public void init(){  	
       	menus.setMnemonic(KeyEvent.VK_K);
       	JPanel keys=new JPanel(new GridLayout(items.length+2,0));
       	menus.setMnemonic(KeyEvent.VK_I);
       	 for(int i = 0; i < items.length; i++) {
       	 	javax.swing.JButton bot=new javax.swing.JButton(items[i].getText());
       	 	bot.setActionCommand(items[i].getActionCommand());	
       	 	bot.addActionListener(new Mykb());
       	 	items[i].addActionListener(new Mykb());
       	 	menus.add(items[i]);
       	 	keys.add(bot);
       	 }
       	mb.add(menus);
       	mb.add(new JLabel("<html><body><center><font size=12pt font color=green><b>EbmGastroService [Kassa Datenbank]</b></font></center></body></html>",JLabel.CENTER));
    	setJMenuBar(mb);        	
	    ta.setFont(new Font("",1,14));       	
       	top.setLayout( new BorderLayout());
       	top.setBorder(new LineBorder(new Color(100,100,100),5,true));//BevelBorder.RAISED));
       	keys.setBorder(new LineBorder(new Color(100,100,100),5,true));
       	top.setBackground(new ebm.myColor("hg").getColor()); 	        
       	ta.setBackground(Color.cyan);  		
       	ta.setBorder(new LineBorder(new Color(100,100,100),5,true));
       	sud.setLayout( new BorderLayout());
  		sud.setBorder(new LineBorder(new Color(160,180,180),7,true));
       	sud.setBackground(new ebm.myColor("hg").getColor()); 	        
       	gelesen();       	
       	java.awt.Dimension s = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	//js.setPreferredSize(new java.awt.Dimension(s.width/4, s.height/2));
       	sud.add(js,BorderLayout.LINE_START);
       	sud.add(new ver6.img().label(null,"logo.gif"),BorderLayout.LINE_END);       
       	sud.setPreferredSize(new java.awt.Dimension(s.width/3, s.height/2));
       	top.setPreferredSize(new java.awt.Dimension(s.width/2, s.height/2));
       	getContentPane().setBackground(new ebm.myColor("hg").getColor()); 	               	
 		getContentPane().add(keys,BorderLayout.LINE_START); 		    	
       	getContentPane().add(sud);//,BorderLayout.LINE_END);//panel);       	
        getContentPane().add(top,BorderLayout.PAGE_END);
       	getContentPane().setVisible(true);
       }


/* public static void main(String[] args) {
//    	KB kb = new KB();    	
  //	kb.init();	  	
 	Console.run(new KB(),700,600);
 	//System.out.println(kb.tokenZeile("RN07_05/0108,07_05_2003,10200X000707_05,+8.4,10.5,F_Mourad,-"));
  	
  }*/


  }
