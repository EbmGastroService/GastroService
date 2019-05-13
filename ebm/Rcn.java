/*
 * @(#)myRc.java  1.0 01/07/03
 *
 * Copyright 2003 Mourad El bakry.
 */

/**
 * An facturing. 
 *
 * @version 1.70 10/06/06
 * @author Mourad EL bakry
 */
package ebm;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
import com.printer.*;
import ver6.*;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JColorChooser;
import javax.swing.BoxLayout;
import javax.swing.table.*;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.AbstractButton;

import javax.swing.event.*;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Image;
import java.awt.Dimension;
import java.util.*;
import java.util.StringTokenizer;
import java.io.*;

public  class Rcn extends JApplet{//implements Printable{
     String R_Code;
     String Datum;
     static String Kcode;
     String Wcode;
     double Betrag;
     double Saldo ;
	double total;
	String Filname ;
	java.util.List<String>myVector;
	java.util.List<String>korbl;
     int Rzahl ;
     int kAb ;          
   double Skale;    
   JTextArea tk  = new JTextArea(5,1);  
   JTextArea tr  = new JTextArea(5,1);  
   JLabel flabel  = new JLabel("");   
   JLabel rwert;
   JColorChooser tcc;
   JPanel tc,east,west,tccpanel,sud,north,jb,kopfdata;
   JScrollPane sp;
   JTextArea tf  = new JTextArea("",6,20);//, 150);
   JTextArea info;
   String[]slid=new ver6.Rb().slid();  
	private JButton[] ok = new ver6.Rb().ok();
	JButton color_ok=new ver6.Rb().color_ok();
	private JMenuBar menubar = new JMenuBar();
	private JMenu[] menus = new ver6.Rb().menus();
	private JMenuItem[] menuitems = new ver6.Rb().menuitems();
	Font font;
	Color hg,vg1,vg2,bg1,meinColor;
	sucheDate su;
	JTable mytab;
	JLabel tlz;
	save s;
	JScrollPane js;
	int myform;
	//static final String sourceLocation = "localhost/gastro/";
	
	public Rcn (){
    R_Code = null;
    Datum  = null;
    Kcode  = null;
    Wcode  = null;
    Betrag = 0.0;
    Saldo  = 0.0;
    Filname = "gastro/date/jliste.dat";
    Rzahl = 0;
    kAb = 0;
    myVector = new ArrayList<String>();
    korbl = new ArrayList<String>();
  	myform=form();
    Skale = new papier().getSkale();//getPapierformat();//fixpapier();
    font = new Font("Courier New",1,16);
    su = new sucheDate(Filname);
    mytab = new JTable(chektable(su.zeilenZahl()+3),5);
    total=0.0;
    s=new save();
    tlz=new JLabel(" Offener Posten: "+new MyZahl().deci(total));
    east = new JPanel ();
    west = new JPanel ();
    tc = new JPanel ();
    tccpanel = new JPanel ();
    hg=new ebm.myColor("hg").getColor();
    vg1=new ebm.myColor("vg1").getColor();
    vg2=new ebm.myColor("vg2").getColor();
    bg1=null;  	
    rwert=new JLabel("<html><font color=red size=+1>"+
                       "R-Total: "+new MyZahl().deci(getSaldo())+"</font>",JLabel.LEFT);
	setTf();
  }
  public String getAppletInfo() {
    return "EBM Gastroservice Packet 1.07 (2003-2006), by Mourad El bakry";
    }    
    void setTf(){
    	String[]str=new ver6.Rb().rcntf();    
    	for(int i=0;i<str.length;i++)tf.append("\n"+str[i]);
    }
    String getR_Code()   {return R_Code;}
    String getDatum()   {return Datum;}
    String getKcode()   {return Kcode;}
    String getWcode()   {return Wcode;}
    double getBetrag()   {return Betrag;}
    double getSaldo()   {return Saldo;}
    double getTotal()   {return total;}
    int getRzahl()   {return Rzahl;}
    int getkab()   {return kAb;}    
    void setR_Code(  String a_R_Code) {R_Code= a_R_Code;}
    void setDatum(  String a_Datum) {Datum = a_Datum;}
    void setKcode(  String a_Kcode) {Kcode = a_Kcode;}
    void setWcode(  String a_Wcode) {Wcode = a_Wcode;}
    void setBetrag(  double a_Betrag) {Betrag = a_Betrag;}
    void setSaldo(  double a_Saldo) {Saldo = a_Saldo;}
    void setTotal(  double a_total) {total = a_total;}
    void setRzahl(  int zahl) {Rzahl = zahl;}
    void setkab(  int kab) {kAb = kab;}
    int form(){
    	int i=0;
    	String str=new sucheDate("gastro/resource/form.dat").md();
    	if(str.length()==0){str="1";new save().file("gastro/resource/form.dat",str,false);}
    	try{i=Integer.parseInt(str);
    	}catch(Exception nfe){i=0;}
    	return i;
    }
    
    public String toString () {
      return R_Code+","+Datum+","+Kcode+","+Wcode+","+(float)Betrag+","+(float)Saldo;
    }
    
    void savewarenverkauf_d(String[]str){
      new save().filefelde("gastro/date/warenverkauf.dat",str,true);
    }
    
    void saverechnung_d(String[]str){
      new save().filefelde("gastro/date/drucker/"+Kcode+".dat",str,false);
    }
      
    public int chektable(int  leng){
      if (leng<50) return 50;else return leng;
    }
    
    public String[]liste(String str){
      String[]wliste =new String[20];
      for(int i= 0;i<wliste.length;i++){
        if (wliste[i]==null)wliste[i]="";
        wliste[i]=wliste[i]+str+(i+1);
      }
      return wliste;
    }
    
    String[][]korbDyn(){
      String cmd[];
      int x=korbl.size();
      cmd = new String[x];
      String[][]korb = new String[x][5];
      for (int i = 0; i < cmd.length; i++){
        cmd[i]= (String) korbl.get(i);
        String[]wort=new ausTeilen().koma(""+cmd[i]);
      	wort[0]=""+(i+1);
        korb[i]=wort;
      }
      return korb;
    }
    
    public String[][] die_ware(){
      WD w = new WD();
      MyOp mo = new MyOp();
      MyZahl mz= new MyZahl();    
      int d = 0;      
      String ws=w.suchen();
      if (ws!="" && ws!=null) {
        String ein = mo.eingabeMenge(" "+w.getw_Code()+"   "+w.getBezeichnung().trim()
                                     +"   "+w.getPreis()+ "\n Menge: ");
        if (ein.length()==0) {
          ein = mo.wahl(liste("Mal * ")," Menge ");//gebe eine Liste verügbare Tische
          ein =ein.substring(6,ein.length());//ausschneide das wort "Mal * "
        }
        try{d=Integer.parseInt(ein);}catch(Exception e){d=0;}
        setWcode(""+w.getw_Code());
        setBetrag(d*w.getPreis());
        setSaldo(Saldo+Betrag);
        String korbl_str=""+(korbl.size()+1)+","+d+","+w.getBezeichnung()+","+(float)w.getPreis()+
        ","+(float)Betrag;
        korbl.add(korbl_str);
        zumtabele(korbDyn(),2);//korbl.size()-1);
        file_for_drucker(""+d+","+getWcode()+","+w.getBezeichnung()+","+w.getPreis()+","+(float)Betrag);
      }                 
      return korbDyn();
    }                  
    
    public String[] myVector_str() {
      String cmd[];
      cmd = new String[myVector.size()];
      for (int i = 0; i < cmd.length; i++)
        cmd[i]= (String) myVector.get(i);
      return cmd;
    }
    
    public void file_for_drucker(String str) {
      myVector.add(str);
    }
    //----------------------Weiter---------------------------//
    int weiter(){
      MyOp k = new MyOp();
      int an = 0 ;      
      int antwort = 0;
    rwert.setText("<html><font color=red size=+1>"+
                       "R-Total: "+new MyZahl().deci(getSaldo())+"</font>");
      Object[] ob = new Object[3];
      ob[0]="Erfassen";
      ob[1]="Zeile bearbeiten";
      ob[2]="Erfassung Beenden";
      an = k.frageO("Total: "+new MyZahl().deci(getSaldo())+
                    "\nWie wollen Sie weiter gehen! ",ob);
      if (an == 0){
        die_ware();//antwort=x;     
        antwort=korbl.size();
      } else
      if (an == 1){
        storno();//der korb wird um entfernte zeile verkurzt      
        //die_ware();
        antwort=korbl.size();
      }else
      if (an == 2){
        antwort=-3;
      }else
      if (an ==-1){ // in Myop.frageO wird beim null ein wert -x gegben
        //System.out.println("weiter("+x+"), und an="+an);
        antwort=korbl.size();
        die_ware();// in die_ware(x) werd erfasst erst mit +Werte       
      }
      return antwort;
    }
    
    public String rec_sume(){
      return(" "+R_Code+" Total: "+new MyZahl().deci(getSaldo()));
    }
    
    public String[][] der_korb(){//throws IOException {
      int i =0;
      int x=0;
      korbl.clear();//=new ArrayList<String>();
      String[][] str1=null;
      int weite=die_ware().length;
      while(weite!=-3){
        if (weite==-3)break;        
        weite=weiter();               
      }
      if(korbDyn().length>0)str1=korbDyn();
      return str1;
    }
    
    public String[][] der_Rechnung(){     
      rwert.setText("R-Total:");
      return der_korb();      
    }
    
    void savek_r(String[][]rlist){      
      new save().dfilefelde("gastro/k_r/"+Kcode+".dat",rlist,false);    
      zumtabele(rlist,2);     
    }
    void save_wkiste(){
        String str="";      
    	String erg=Kcode;
    	int cod=erg.indexOf("X");
    	str = erg.substring(0,cod);
    	Wkkur wkur=new Wkkur(str);
    	wkur.add_AB(kAb);      
    }
    void schloss(){
      savewarenverkauf_d(myVector_str());
      saverechnung_d(myVector_str());
      myVector.clear();//=new ArrayList<String>();
      save_wkiste();      
      speichern();      
    }
    public boolean saveControl(String str){
      if( new MyOp().Frage(str)==0){schloss();return true;}
      else {
        int i=new MyOp().Frage("<html><bgcolor=red><font color=white size=+1>Erfasssung Abbrechen! </font>"+
                            "<font color=blue><br>"+
                            "[Ja]-nicht erfassen!<br>"+
                            "[Nein]-erfassen!"+
                            "</font>");         
          if(i==1){schloss();return true;}
          else {myVector.clear();//=new ArrayList<String>();
          	tf.append("\n ist abgebrochen!!");
          	return false;
          }
      }
    }

    public int die_schleife()  {      
      int i=0;
      try{i=Integer.parseInt(new sucheDate("gastro/date/rz.dat").md());
      }catch(Exception nfe){i=Rzahl;}
      setRzahl(i);
      return(Rzahl++);
    }
    //parameter str ist der gesuchte Kundencode von Methode der_kunde bergeben ist
    // Kunde Anzahlbestellung wird durchgeprft und in X bergeben
    public int die_AB(String str) {
      Wkkur wkur=new Wkkur(str);      
      return wkur.suche()+1;
    }
    public String R_code(){
      MyOp k = new MyOp();
      MyZahl mz= new MyZahl();
      die_schleife();
      myDatum dm = new myDatum();
      String dat = dm.ist1();
      String dat1 = dm.T_M();
      setDatum(dat);
      setR_Code("RN"+dat+"/"+Rzahl);
      if(k.Frage("<html><b><font color=green size=+1>Neue Rechnung erstellen</font>"+
                 "<br><font color=blue size=+1>Rechnung Nr :"+
                 R_Code+"</font>")==0)return R_Code;
      else return "-1";
    }
    public String[] der_kunde()  {
      MyOp k = new MyOp();
      MyZahl mz= new MyZahl();
      String len=null;
      KD kd = new KD();
      String x="";
      String dat1 = new myDatum().ist1();//new myDatum().T_M();
      setSaldo(0);
      String[]kundendaten=null;           
      String chek = R_code();
        if(chek!="-1"){
          kundendaten = kd.suchen();
          String[] kstr={"Code ","Familien Name ","Vorname",
                  "Adresse","Postleitzahl","Ortschaft","Fest Tel.","Mobil Tel."};
                String[]taste={"Ok","Bearbeiten"};          
          if(kundendaten!=null){      
            JPanel kp=new Einfugen(kstr,kundendaten).kd();            
            int kfr = k.kundenfrage("Kunde:",kp,taste);
            if(kfr == 1)kundendaten=kd.bearbeiten(kd.getk_Code().trim());           
            if(kfr<0){
              if(k.Frage("Erfassung Abbrechen?")==0)kundendaten=null;
            }
          }       
          int istOk=-1;
          if(kundendaten!=null)
            istOk=k.Frage("<html><b><font color=green size=+1>Kunde :</font><br>"+
                     kd.Name+" "+kd.Vname+" <br> "+kd.Adresse+"<br>"+kd.Plz+" "+kd.Ort);
          while(istOk == 1){   
            kundendaten=kd.suchen();
            //if(istOk<0){kundendaten=null;break;}
            if(kundendaten!=null){      
                JPanel kp=new Einfugen(kstr,kundendaten).kd();            
                int kfr = k.kundenfrage("Kunde:",kp,taste);
                if(kfr == 1)kundendaten=kd.bearbeiten(kd.getk_Code().trim());           
              if(kfr<0){
                if(k.Frage("Erfassung Abbrechen?")==0)kundendaten=null;break;
              }
            }
            if(kundendaten==null)break;     
            istOk=k.Frage("<html><b><font color=green size=+1>Kunde :</font><br>"+
                     kd.Name+" "+kd.Vname+" ( "+kd.Adresse+" )");
            if(istOk<0){
              if(k.Frage("Erfassung Abbrechen?")==0)kundendaten=null;break;
            }
            if(istOk==0)break;
            
          }   
          if (kd.k_Code != null){
            len = kd.getk_Code().trim();
            setkab(die_AB(len));
            kd.setAnzBestellung(kAb);
            if (len.length()<=7){
              for (int i = len.length(); i<=8; i++){
                String stest =len+"X"+x+kAb+dat1;
                if ( stest.length() < 16){
                  x = x+"0";
                }else x = x+"";
              }
            }
            len = len+"X"+x+kAb+dat1;
            setKcode(len);
          }
        }
      return(kundendaten);
    }
    public String[]kfdaten(String[]erg){
      if(erg!=null){
        String[] kdaten =new String[9];
        kdaten[0]="K U N D E N D A T E N";
        kdaten[1]=getR_Code();//Rechnungkode Kcode
        kdaten[2]=erg[1].trim()+" "+erg[2].trim();//name vorname
        kdaten[3]=erg[3].trim();        //Adresse
        kdaten[4]=erg[4].trim()+" "+erg[5].trim();//Plz Ort
        if(erg[6].length() > 3)kdaten[5]=erg[6].trim();else kdaten[5]=erg[7].trim();//Tel
        myDatum md =new myDatum();
        kdaten[7]=md.d();//Datum
        kdaten[8]=md.time();//Uhrzeit
        return kdaten;
      }else return erg;
    } 
    public void speichern() {     
      String zeile =(R_Code+","+Datum+","+Kcode+","+(float)Saldo+","+(float)Saldo+",0");
      new ver6.Rcode(zeile,0).add();
      new save().file("gastro/date/jliste.dat",zeile,true);           
      zeile =""+Rzahl;
      new save().file("gastro/date/rz.dat",zeile,false);                      
     // zeile =(Kcode+","+kAb);
     //new save().file("gastro/date/KZ.dat",zeile,true);
      tf.setText(" Daten sind gespeichert");      
    }
    public String[][] lesen() {
      String zeile;
    String[] sicherheit={"RN080506/0113,080506,1002X00011080506,0.0"};
      MyZahl mz = new MyZahl();
      su = new sucheDate("gastro/date/jliste.dat");    
    	double gtot=0.0;
    	setTotal(gtot);
      String[]z=null;
      if(su.zeilenZahl()>=0)z=su.myDaten();else z=sicherheit;
      String[][] zz =new String[z.length][5];
      for(int i=0; i<z.length;i++){
        zeile=""+z[i];
        StringTokenizer st = new StringTokenizer(zeile,",");
        String cod = st.nextToken();setR_Code(cod);
        String n = st.nextToken();  setDatum(n);
        String vn = st.nextToken();setKcode(vn);
        String ot = st.nextToken();
        double otd = Double.parseDouble(ot);setSaldo(otd);
        gtot+=getSaldo();        	
        zz[i][0]=Datum;
        zz[i][1]=R_Code;
        zz[i][2]=Kcode;
        zz[i][3]=""+(float)Saldo;
        zz[i][4]=""+(float)gtot;       
      }
      setTotal(gtot);
      tlz.setText("Ofene Posten: "+new MyZahl().deci(getTotal()));              
      return(zz);
    }
    public void zumtabele(String[][] str, int x){
      String[]headers={"Pos","Rechnung Nummer","Kunden RN","R-Betrag","OP-Total"};  
      //String[]headers={"Datum","Rechnung Nummer","Kunden RN","E-Preis","Total"};
      for(int i=0; i<str.length; i++)str[i][0]=""+i;
      if (x==2){
        str=korbDyn();
        headers=new String[]{"Pos","Menge","Bezeichnung","E-Preis","Total"};
      }     
      mytab=new RcTab(str,headers).tab();
      mytab.setForeground(vg1);
      mytab.setBackground(vg2);   
      mytab.setFont(font);            
      js.setViewportView(mytab);
      js.validate();      
      sud.validate();
      validate();
    }
    public void tabeleZero(){
      JTextArea js_text=new JTextArea("\n\n\n\n\n      N  E  U  E    E  R  F  A  S  S  U  N  G",30,7);
      js_text.setBackground(vg2);
      js_text.setForeground(vg1);
      js_text.setEditable(false);
      js_text.setFont(new Font("",1,18));
      js.setViewportView(js_text);      
      js.validate();      
      sud.validate();
      validate();
    }               
    public String[]tcliste(){
      String[] tcl=null;
      //new save().chekfilename("gastro/date/kon.dat");
      tcl=new sucheDate("R"+new myDatum().ist1()+"N","gastro/date/kon.dat",0).wahlinarrayJL();
      if(tcl.length>0 || tcl!=null){     
        jpanel(tcl);new save().file("gastro/source/tc.dat","voll",false);
      }else{ new ebm.BW(4,"<html><center><font color=red size=5>Aktuelle Faktura ist leer<br><br></font></center>"+
                              "Heute der "+new myDatum().ist().replace('_','.')+" wird erfasst<br>"+
                              "<br>Die Offene Posten bleiben erhalten<br>solang bis sie gebucht sind"+
                              "<br><br><u>Empfehlungswert! </u><br>jeden Tag Bitte abbuchunen");
      	new save().file("gastro/source/tc.dat","leer",false);
      }
      return tcl;
    }
    //---------------------Storno-----------------------------//
    public String[][] storno(){
      String[] mys=new ausTeilen().binde2(korbDyn(),true);
      int erg=new Jlist(mys,false).ergebnis();
     if(erg == -1)return korbDyn();
      //die zeile wird gewahlt daher den Totalbetrag weg
      double stobetrag= Double.parseDouble(new ausTeilen().komma(mys[erg])[4]);//Zeile Totalwert
      setSaldo(getSaldo()- stobetrag);  //Rechnugsbetrag mindern um der stornobetrag
      Object[] ob = new Object[2];
      ob[0]="<html><font color=red size=6>Entfernen</font>"; 
      ob[1]="<html><font color=green size=6>Bearbeiten</font>";
      int an = new MyOp().frageO("\nEntfernen eine Position"+
                                 "\nArtikelbezeichnung tauschen"+
                                 "\n Zb -Schinken +Ananas oder"+
                                 "\n die Menge kurigieren"+
                                 "\nWie wollen Sie weiter gehen!\n",ob);
      if (an == 0 && korbl.size()>0){            //entfernen
        myVector.remove(erg);
        korbl.remove(erg);
        String[][]nstr = korbDyn();
        tabeleZero();        
            zumtabele(nstr,2);        
            return nstr;
      }else 
      if (an==1){
        String[]verteile=new Storno().zeileB((String)myVector.get(erg),erg);
        String[]korb=  new ausTeilen().koma(""+verteile[0]);        
        String myOb=""+verteile[1];       
        myVector.set(erg,myOb);
        korbl.set(erg,""+verteile[0]);
        stobetrag= Double.parseDouble(korb[4]);
        setSaldo(getSaldo()+stobetrag);
        tabeleZero();
            String[][]nstr = korbDyn();
        tabeleZero();        
            zumtabele(nstr,2);        
            return nstr;
      }else return korbDyn();                      
    }     
    public int Int(String str){
      int i=0;try{i=Integer.parseInt(str);}catch(Exception e){i=0;}
      return i;
    }
    public String[][] erfassen(){
      String[][]rec = null;     
      tf.setText("");tk.setText(""); tr.setText("");tlz.setText("");
      String[]derkunde=der_kunde();
      if(derkunde!= null){
        String[]der_k= kfdaten(derkunde);     
        tf.setText(" Rechnung an Kunden Nummer: "+Kcode.substring(0,Kcode.indexOf("X"))+" "+kAb+"  erfassen");      
        der_k[0]="K U N D E N D A T E N";
        tk.append(" "+der_k[2]);
        tk.append("\n "+der_k[3]);
        tk.append("\n "+der_k[4]);
        tk.append("\n "+der_k[5]);
        
        tr.append(" "+der_k[1].trim());
        tr.append("\n "+new myDatum().d());
        tr.append("\n "+new myDatum().time());
        String derk =""+ der_k[1]+" "+der_k[2]+" "+der_k[3];
        rec = der_Rechnung();
        if(rec!=null){
          if (derk!="" && derk!=null){                        
            String[] firma =new firma().data();
            zumtabele(rec,2);
              String[] kopf={"pos","Menge","Bezeichnung","E-Preis","Total"};
            if(saveControl("Eingabe Korrekt?\n es wird gespeichert!")){    
            	new BW(2,"");
            	savek_r(rec);
            	if(myform==1)
            	new GF(""+Kcode,firma,der_k,rec,kopf,Skale);
            	else new com.printer.DF(""+Kcode,firma,der_k,rec,kopf,Skale);              
            	tf.append("\n Rechnung: "+rec_sume()+"\n ist abgeschlossen");
            	tcliste();
            	lesen();//setTotal(total+getSaldo());
            	tlz.setText("RNr.:"+rec_sume()+", OP: "+new MyZahl().deci(getTotal()));
            }
          }
        }else tf.append("\n ist abgebrochen!!");
      }
      return rec;
    }
    public String[][] gelesen(){
      tabeleZero();
      int i=0;
    	tlz.setText("");
      String[][] v = lesen();
      zumtabele(v,1);
        tlz.setText("Rechnug Anzahl: "+i+"  Kassa Total: "+new MyZahl().deci(getTotal()));
      return v;
    }
    public int wo(){
      MyOp mo = new MyOp();
                    Object[] oa ={"Kunden Rechnung suchen"," Heutige Rechnugen"};
      int i=mo.frageO("Wie wollen Sie !?",oa) ;
      return i;
    } 
    public void nachdruck(){
      MyOp mo = new MyOp();     String ein="";
      wk wk= new wk();
      int wo = wo();
      if(wo == 0){
        ein = wk.gesucht();
      }
      if(wo == 1){
        ein=mo.wahl(tcliste()," Heutige Rechnungen");
        //String[]wort=new ausTeilen().koma(ein);
        //new wk(wort[1],wort[0]).drucken(wort[1]);
        new wk(ein).drucken(new Rcode(ein,1).genkr[0]);
      }
    }
    void dtest(){
      String aktmodi=new myDatum(new sucheDate("gastro/date/benutzer.dat").filemodi()).l();
      String altmodi="";altmodi=new sucheDate("gastro/date/source/tagab.dat").md();     
      if(altmodi.length()== 0 || altmodi.equals("")|| altmodi.equals("null")){
        altmodi=aktmodi;new save().file("gastro/date/source/tagab.dat",""+altmodi,false);
      }
      int akt=Integer.parseInt(aktmodi);
      int alt=Integer.parseInt(altmodi);
    //  System.out.println("alt: "+alt+" akt: "+akt+" dif:"+(akt-alt));
      if(akt > alt){        
       /* new MyOp().fehler1("\nTagesabsatz entleeren und im Monatregister buchen \n"+
                              "<html><font color=red>Ihre Tagesverkauf wird entleert?</font");*/
        new save().file("gastro/date/source/tagab.dat",""+akt,false);      	
        saveA1(""+0);//monatAbsatz();//Transport in tages umsatz
      	new save().file("gastro/date/warenverkauf.dat","",false);//die warenverkauf des tages entfernen     
      }      
    }
   
    public void r_sehen(){
      String[][] str=lesen();
      String[] mys=new ausTeilen().binde2(str,true);
      int erg=new Jlist(mys,false).ergebnis();
      wk wk= new wk(str[erg][2],str[erg][1]); 
      wk.codegesucht();
    }
           
    void monatAbsatz(){
      //String datum=new myDatum().ist();
    	String datum1=new myDatum().ist_my();
     /* WV wv=new WV("gastro/Date",datum);    
      //wvl.save_Monatwarenverkauf(true);//die warenverkauf des tages erst hollen
      String[] tverk=wv.Tagesverkauf();
      //wvl.save_TV(tverk,true);      
      wv.zeige("M");*/
      new TV(datum1+"n");
      //String str=new Umsatz("gastro/date/Data"+new myDatum().J()+"/","Wv"+datum).open_Umsatz()+"\n";
      //str+=new WG("gastro/Date",datum).pane();
      //wv.tc.setText(str);
    }
    void abbuchen(){
      gelesen();
    	String[]str=new com.search.sucheDate("gastro/date/jliste.dat").myDaten();		
		if(str.length>0)new buchen(str);
    	/*
          try{
            tf.setText("");
            JL jl= new JL();
            tf.setText(jl.buchen());//+"\nin Fahrer Buch eingetragen");
            FL jf =  new FL();
            tf.setText(jf.buchen(jl.getFahre())+"\nin der Kassabuch eingetragen");
            tf.append("\n..KB ist mit "+new KB().gelesen());
          } catch(Exception ioe){System.err.print("\n"+" buchen RC\n"+ioe);}
    */
    }
    void tagesabsatz(){
    	String[]str=new sucheDate("gastro/date/warenverkauf.dat").myDaten();    
    	//new TV(new myDatum().ist1()+"N",false);    
    	new TV(new myDatum().ist1()+"n",str,"gastro/date/warenverkauf.dat",false);    
    	lesen();tlz.setText("Offene Posten: "+(float)getTotal()+" :> ");         
    }
    void colorSave(String str){
    	new com.units.save().file("gastro/resource/"+str+".dat",meinColor.toString(),false);
    }
    void tree(){
    	frame jf=new com.options.frame("Brichten in Tabellen ",300);	
		jf.setContentPane(new ver6.myTree());		
		jf.pack();	  	
		jf.setVisible(true);      
    }
    void farbe(){
    	tcc = new JColorChooser(tc.getForeground());
    	tcc.getSelectionModel().addChangeListener(new colorchange());
    	tcc.setBorder(BorderFactory.createTitledBorder("Text Farbe zu wahl"));   	
    	com.options.frame mf=new com.options.frame("Color Applications",180);
    	JPanel colorPanel=new JPanel();
    	colorPanel.add(tcc);colorPanel.add(color_ok);
    	mf.setContentPane(colorPanel);
    	mf.pack();//setSize(320,600);
    	mf.setLocation(350,50);
    	mf.setVisible(true);
    	validate();
    }
    
    class Mykd implements ActionListener {
      public void actionPerformed ( ActionEvent e ) {
        String fr = e.getActionCommand();
        if (fr.equals("Offene Posten")){gelesen();tlz.setText("Offene Posten: "+(float)total+" :> ");}
        if (fr.equals("Schrift")){font=new com.security.myfont().getmyfont();mytab.setFont(font);}
        if (fr.equals("Faktura")){
          if(new sucheDate("gastro/source/tc.dat").md().equals("leer"))new ebm.BW(2,"");        
          tabeleZero();
          myVector.clear();//=new ArrayList<String>();
          korbl.clear();//=new ArrayList<String>();
          erfassen();
        }       
        if (fr.equals("R-Sehen")){ r_sehen();}
        if (fr.equals("M-Bericht")){tree();  }
        if (fr.equals("Tageserlos")){
          KB kb=new KB();
          String[]te=kb.Tageserlose(new myDatum().ist1());
          tf.setText(""+" Der Tageserlose hat "+te.length+
                     " erfasste Buchungen gefunden."+kb.rec_sume(te.length)+
                     "\n\n Wenn sie eine Tabellen Ansicht wollen, dann aktivieren der [Kassabuch]\n unter Menu [Service] und auf [Tageserlose] drucken");
          
        }
        if (fr.equals("Nachdruck")){nachdruck();}
        if (fr.equals("Umbuchung")){new ver6.fs();}
        if (fr.equals("Abuchen")){abbuchen();}
        if (fr.equals("Bericht")){
          try{            
            new FL().suchAbfrage();
          } catch(Exception ioe){System.err.print("\n"+" bericht "+ioe);}
        }
        if (fr.equals("F-Bericht")){tf.setText("");tf.append(new FL().Bericht());}
        if (fr.equals("Monat Absatz")){monatAbsatz();}
        if (fr.equals("T-Absatz")){tagesabsatz();}
        if (fr.equals("Hilfe")){  new EP(" help ");}//new hilfe(); }
        if (fr.equals("Farbe")){ farbe();}
        if (fr.equals("info")){ new ver6.info();}
        if (fr.equals("Farbe Anwenden")){         
          int meinWahl=welchefarbe();         
            if(meinWahl==0){hg=meinColor;neueHg();colorSave("hg");}
            if(meinWahl==1){vg1=meinColor;neueVg();colorSave("vg1");}
            if(meinWahl==2){vg2=meinColor;neueHg();colorSave("vg2");}
            if(meinWahl==3){bg1=meinColor;mybotton();colorSave("bg1");}            
            validate();
          }
        if (fr.equals("Papierformat")){Skale=new papier().getPapierformat();}
        if (fr.equals("Rechner")){new myCmd("calc.exe");}//C:/Windows/System32/calc.exe");}
        if (fr.equals("Neue Ware")){WD wd=new WD(); if(wd.suchen()!=null)new MyOp().fehler("Ware ist bereit vorhanden");}
        if (fr.equals("Neuer Kunde")){KD kd=new KD();
        if(kd.suchen()!=null)new MyOp().fehler("Kunde ist bereit vorhanden");}
        if (fr.equals("Musik")){new myCmd("C:/Programme/Windows Media Player/wmplayer.exe audio/Arabi.mp3");}
        if (fr.equals("Editor")){ new myCmd("NOTEPAD.EXE gastro/mytext.dat");}   
        if (fr.equals("Install Font")){ new com.security.myfont().systemFont();} 
      }
    }
    class colorchange implements ChangeListener{
      public void stateChanged(ChangeEvent e) {
        meinColor = tcc.getColor();
      }
    }
    public int welchefarbe(){
      MyOp mo = new MyOp();
    Object[] Farben ={"Hintergrund","Textfarbe","Zentral","Tasten"};      
      int i=mo.frageO("Welche Farbe soll getauscht werden?",Farben) ;
      return i;
    }
    public JPanel jpanel(String[] str){
      tc.removeAll();
      west.remove(tc);
      JlistRc nl = new JlistRc(str);
      tc.add(nl,"Center");//.getApp());
      tc.validate();
      west.add(tc);
      west.validate();
      validate();
      return tc;
    }
    void neueVg(){
      if(vg1!=null){    
      mytab.setForeground(vg1);
      tf.setForeground(vg1);
      tc.setForeground(vg1);
      tr.setForeground(vg1);
      tk.setForeground(vg1);
      sud.setForeground(vg1);
      info.setForeground(vg1);
      }
    }
   void neueHg(){     
    Border brd = BorderFactory.createMatteBorder(1, 1, 2, 2, Color.yellow);
    Border redline = BorderFactory.createLineBorder(Color.red);
    //TitledBorder ntitle = BorderFactory.createTitledBorder(redline, "title");
  //ntitle.setTitleJustification(TitledBorder.CENTER);    
   	if(hg!=null)
    mytab.setBackground(vg2);
    mytab.setFont(new Font("Courier New",1,14));
    mytab.setBorder(brd);
    tf.setBackground(vg2);    
    tf.setBorder(brd);    
    tc.setBackground(hg);
    tr.setFont(new Font("Courier New",0,14));
    tr.setEditable(false);tr.setBorder(brd);
    tk.setFont(new Font("Courier New",0,14));
    tk.setEditable(false);tk.setBorder(brd);
    tr.setBackground(vg2);
    tk.setBackground(vg2);
    //tr.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.red));
    tr.setBorder(new TitledBorder(redline,"Rechnung"));   
    tk.setBorder(new TitledBorder(redline,"Kunde"));        
    tf.setFont(new Font("Courier New",0,14));
    tf.setEditable(false);
    //tccpanel.setBackground(hg);
    if(hg!=null)west.setBackground(hg);
    kopfdata.setBackground(hg);
    if(hg!=null)sud.setBackground(hg);
    if(hg!=null)jb.setBackground(hg);
   if(hg!=null)sp.setBackground(hg);
   if(hg!=null) east.setBackground(hg);
   if(hg!=null) north.setBackground(hg);
   if(hg!=null) info.setBackground(hg);   
   }
   void paneleninit(){
    kopfdata= new JPanel ( );//new GridLayout(1,3));
    jb = new JPanel (new GridLayout((ok.length)+1,0));   
    sud = new JPanel ();//new GridLayout(4,0));
    north = new JPanel ();//new GridLayout(2,1));
    info = new JTextArea(5,5);
   }
   void mybotton(){
    for (int i=0; i<ok.length; i++){
      if(bg1!=null)
           ok[i].setBackground(bg1);//new Color(170,70,90));        	
    }   
   }
   void raab_0(String path){
    new save().file(path,""+0,false);
   }
   String[]raab(String path){
    String[]str=new sucheDate(path).myDaten();
    String[]sicher={"00000","00000","00000"};
    if(str.length>=0){
      for(int i=0;i<str.length;i++){
        int pos=str[i].indexOf("X");
        if(pos<5)pos=7;
        str[i]=str[i].substring(pos+2,str[i].length()-4);
      }
      
    }else str=sicher;
    return str;
   }
   class Keys implements KeyListener {
    public void keyPressed(KeyEvent e) {
      maybeShowPopup(e);
    }
    public void keyReleased(KeyEvent e) {
      maybeShowPopup(e);
    }
    public void keyTyped(KeyEvent e) {
      maybeShowPopup(e);
    }
   }
    private void maybeShowPopup(KeyEvent e) {
      int id = e.getID();
      int modifiers = e.getModifiersEx();
      int location = e.getKeyLocation();
      char c = e.getKeyChar();
      int keyCode = e.getKeyCode();
        String keyString = "" + c ;     
    //  tf.setText(" "+keyString);
      if (e.isActionKey()) {      
            keyString =""+keyCode;
        if(keyString.equals("112")){new EP(" help ");}//new hilfe(); }//F1
        if(keyString.equals("113")){
        	tabeleZero();
        	myVector.clear();//=new ArrayList<String>();
        	korbl.clear();//=new ArrayList<String>();
        	erfassen(); 
        }   //F2
        if(keyString.equals("114")){              //F3        
        tlz.setText("");        gelesen();
          tlz.setText("Offene Posten: "+(float)getTotal()+" : ");
        }
        if(keyString.equals("115")){KD kd=new KD();kd.zufugen();kd.saveControl();}//F4
        if(keyString.equals("116")){WD wd=new WD();wd.zufugen();wd.saveControl();}//F5
        if(keyString.equals("117" )&& modifiers==128){          //Strg_F6
        	//gelesen();
            String ein=new com.security.userFinster("System Admin").toString();
        	String ck=Tadmin("System Admin");
        	String pw=new com.security.ChefSachen(ck).chek(ein);
        	if (pw.equals("Ok")){
        		new com.security.RM41();//schreibt in b1        		
        		tabeleZero();
        		tcliste();
        		lesen();//
        	//tlz.setText("");
        	}
        }
        if(keyString.equals("118" )&& modifiers==128){          //Strg_F7
          String p=new ver6.basic().getlPath()+".gala/date/exec/";
          String[]pp={"admin","system admin"};
          String ein=new com.security.userFinster("Change P").toString();
          String pw=new com.security.ChefSachen("0598").chek(ein);
          if (pw.equals("Ok")){             
            String wahl=new MyOp().wahl(pp,"");
          	if(wahl.length()>0){
          		String fp="";
          		if(wahl.equals("admin"))fp=p+"kpT.exe";else fp=p+"kpT1.exe";
          		String str=new MyOp().eingabe("Neue KP_"+wahl);
          		changkpt(fp,str);
          	}
          }
        }
        if(keyString.equals("119" )&& modifiers==128){          //Strg_F8
          String path=new ver6.basic().getlPath()+".gala/date/exec/b1.exe";
          String ein=new com.security.userFinster("BlBl").toString();
          String pw=new com.security.ChefSachen("b100").chek(ein);
          if (pw.equals("Ok")){             
            new MyOp().wahl(raab(path),"");
          }
        }
        if(keyString.equals("120" )&& modifiers==128){          //Strg_F9
          String path=new ver6.basic().getlPath()+"/.gala/date/";
          String ein=new com.security.userFinster("A000").toString();
          String pw=new com.security.ChefSachen("a100").chek(ein);
          if (pw.equals("Ok")){     
            raab_0(path+"a1.exe");
          }
        }
        if(keyString.equals("121" )&& modifiers==128){          //Strg_F10
          String path=new ver6.basic().getlPath()+".gala/date/exec/"+"xd.exe";
          String ein=new com.security.userFinster("XDll").toString();
          String pw=new com.security.ChefSachen("xd00").chek(ein);
          if (pw.equals("Ok")){
            new MyOp().wahl(raab(path),"");
          }
        }
        if(keyString.equals("123")&& modifiers==128){         //Strg+F12
        
          int interval=A1();interval++;
          if(interval<3){
          	String ein=new com.security.userFinster("admin").toString();          	
          	String ck=Tadmin("admin");
          	String pw=new com.security.ChefSachen(ck).chek(ein);
          if (pw.equals("Ok")){   
            int i=0;
            String myein=new com.options.MyOp().eingabe("<html><font color=green size=4>"+
                                              "Ich kann nur "+interval+" mal hilfen!<br>"+
                                               "Welche Zeile Bitte? !</font>");
            if(myein!=null && myein!="" && myein.length()>-1){
              try{i=Integer.parseInt(""+myein.trim());}catch(Exception number){i=-1;}              
              if(i>0 && i<lesen().length-1){
                //new com.security.Rambo1(i,"xd.exe");//new myDatum().ist1()+".exe"   
                new com.security.RM41(i,"xd.exe");
                saveA1(""+interval);
              }
            }            
            // tlz = new JLabel("Offene posten : "+new MyZahl().deci(total));
            tabeleZero();           
            tcliste();//F6
            lesen();
          }
          }//else System.out.println(keyString+", "+modifiers);
        }
      }
    }   
    void saveA1(String str){
    	String path=new ver6.basic().getlPath()+".gala/date/a1.exe";
    	 new com.units.save().file(path,str,false);
    }
    int A1(){
    	String path=new ver6.basic().getlPath()+".gala/date/a1.exe";
    	int i=0;
    	try{
    		i=Integer.parseInt(new sucheDate(path).md());
    	}catch(Exception nex){i=0;}
    	return i;          
    }
    String Tadmin(String str){
    	String p=new ver6.basic().getlPath()+".gala/date/exec/";    
    	String fp="";
    	if(str.equals("admin"))fp=p+"kpw.exe";else fp=p+"kpw1.exe";    
    	return new com.search.sucheDate(fp).md();
    }
    String kpT(String str){
    	String p=new ver6.basic().getlPath()+".gala/date/exec/";       
    	String fp="";
    	String fpT="";
    	if(str.equals("admin")){
    		fp=p+"kpT.exe";fpT="M";
    	}else{ 
    		fp=p+"kpT1.exe";fpT="Mou";
    	}
    	String kpt=new com.search.sucheDate(fp).md();
    	if(kpt=="" ||kpt.equals(null)){    		
    		kpt=fpT;
    		new com.units.save().file(fp,fpT,false);
    	}
    	return kpt;
    }
    void sysAdmin(){    	
    	String p=new ver6.basic().getlPath()+".gala/date/exec/";
    	String pf1=p+"kpw.exe";
    	String pf2=p+"kpw1.exe";
    	new com.units.save().file(pf1,kpT("admin")+new com.units.myDatum().ist1(),false);
    	new com.units.save().file(pf2,kpT("system admin")+new com.units.myDatum().ist1(),false);    	
    }
    void changkpt(String f,String t){
    	new com.units.save().file(f,t,false);sysAdmin();

    }
  public void init(){           
    //  tc.add(new Label("HR"));
  //  new kur();    
    paneleninit();
    //new Wkkur();
    //west.setBorder(new TitledBorder("Info"));   
    Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
    Border raisedbevel = BorderFactory.createRaisedBevelBorder();
    
    info.append("Informations Blatt");
    //info.setFont(new Font("Curier",2,10));//"Lucida Handwriting",1,17));    
    info.setEditable(true);
  	sp = new JScrollPane(info); 
    sp.setBorder(new TitledBorder(loweredetched,"Info")); 
    sp.setPreferredSize(new Dimension(100,300));    

    neueHg();
    neueVg();
    //tc.setFont(font);//tc.setEditable(false);   
    String[] firma =new firma().data();   
    if(firma==null){
      firma=new String[3];
      firma[0]="Pizzaria Restaurante Fantasia";
      firma[1]="Tel.: 0676 354 68 24";
      firma[2]="1180 Venicia; San Marcostrade 100";
    }//else firma=new firma().data();
    flabel= new JLabel("<html><center><font size=+1 font color=blue>"+// face='ZurichCalligraphic'> "+
                       firma[0]+"</font>"+
                       "<br><font size=5>"+firma[2]+"</font>"+
                       "<br><font size=3>"+firma[1]+"</font></center>",JLabel.CENTER);
    //flabel.setBorder(new TitledBorder(raisedbevel,""));       
    
    
    east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
    //east.setBorder(new BevelBorder(BevelBorder.RAISED));
    
    
    JPanel lastrow = new JPanel(new BorderLayout());
    //lastrow.setLayout(new BoxLayout(lastrow, BoxLayout.X_AXIS));
    lastrow.setBorder(new BevelBorder(BevelBorder.RAISED));
    //lastrow.setBackground(Color.lightGray);
    
  //  JPanel west = new JPanel ();//new GridLayout(1,links.length+2));
    west.setLayout(new BorderLayout());//BoxLayout(west, BoxLayout.Y_AXIS));
    //west.setBorder(new BevelBorder(BevelBorder.RAISED));    
    
    kopfdata.setLayout(new BoxLayout(kopfdata, BoxLayout.X_AXIS));
    //kopfdata.setBorder(new BevelBorder(BevelBorder.RAISED));
    kopfdata.setBorder(new TitledBorder(loweredetched,"Rechnung Kopfdaten"));       
    
    jb.setBorder(new TitledBorder(loweredetched,"Directions"));           
    sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));        
    sud.setBorder(new TitledBorder(loweredetched,"Tabelen ergebnissen"));       
    north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
  north.setBorder(new TitledBorder(loweredetched,"Bearbeitung"));   
    mybotton();   //************//
    ok[0].setMnemonic(KeyEvent.VK_F); //Faktora
  	ok[5].setMnemonic(KeyEvent.VK_A);//Abbuchen
    for (int i=0; i<ok.length; i++){
         ok[i].setToolTipText(slid[i]); 
         ok[i].addActionListener (new Mykd());      
         jb.add(ok[i]);     
    }
    tf.addKeyListener(new Keys());
    //ok[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.ALT_MASK));
    //jbw.setPreferredSize(new Dimension(120, 150));  	
    /*for (int i=0; i<links.length; i++){
      if(i==0)links[i].setToolTipText("60 Sec. " + links[i].getText()+ " neu starten! darauf klicken!");else
         links[i].setToolTipText(links[i].getText()+ " Erfassen! Wenn Sie darauf klicken!");        
         links[i].addActionListener (new Mykd());
         jb.add(links[i]);//statt jbw für Butons     
    }*/
    west.add(jpanel(tcliste()),BorderLayout.PAGE_START);//new JScrollPane (tc));    
    west.add(rwert,BorderLayout.PAGE_END);  
    //color_ok.setPreferredSize(new Dimension(100,20));
    color_ok.setBackground(ok[0].getBackground());    
    //color_ok.setForeground(ok[0].getForeground());    
    color_ok.addActionListener (new Mykd());    
  	if(hg!=null) tcc = new JColorChooser(hg);else tcc = new JColorChooser(ok[0].getBackground());
    //tccpanel.add(tcc);
    //tccpanel.add(color_ok);
    tc.setBorder(new TitledBorder(loweredetched,"Faktore Aktuell"));
  east.add(jb);  
    //.add(rwert);  
    
    JLabel leere_leb=new JLabel("");    
    //leere_leb.setPreferredSize(new Dimension(80,40));
    //east.add( leere_leb);   
    east.add(sp);
    JScrollPane jstf = new JScrollPane(tf);
    north.add(jstf);  
    //north.add(new JLabel(""));    
    kopfdata.add(tr,"East");
    kopfdata.add(flabel,"Center");    
    kopfdata.add(tk,"West");              
    north.add(kopfdata);      
    //mytab.setBounds(new Rectangle(10,10,400,300));
    lesen(); 
    tlz = new JLabel("Offene posten : "+new MyZahl().deci(total));
    js = new JScrollPane(mytab);
    js.setPreferredSize(new Dimension(280, 280));
    sud.add(js,BorderLayout.PAGE_START);    
    sud.add(new JLabel("",JLabel.CENTER),BorderLayout.PAGE_END);
  //(0-13)0=mouspfeil,1=kreutz,2=pfile,3=sanduhr,4=pfeil nach Rechts
//5,6=pfeil nach Links,7=nachrechts,8,9=Sankrecht,11=wagrecht,12=Hand,13=kreutzpfeil
  
    JPanel p =new JPanel(new GridLayout(2,0));
    p.add(north,BorderLayout.PAGE_START);
    p.add(sud,BorderLayout.PAGE_END);               
    p.setBorder(new LineBorder(new Color(200,200,250),7,true));
    east.setBorder(new LineBorder(new Color(200,250,200),7,true));
    west.setBorder(new LineBorder(new Color(200,250,200),7,true));
    //tlz.setEditable(false);
    //tlz.setFont(new Font("",0,12));tlz.setBackground(Color.lightGray);tlz.setForeground(Color.black);
    //tlz.setPreferredSize(new Dimension(600, 20));
    lastrow.add(tlz); 
  clock1 c = new clock1();                    
  c.init();
  c.start();
  c.setVisible(true);
  lastrow.add(c,BorderLayout.EAST);   
  	menus[0].setMnemonic(KeyEvent.VK_C);   	menus[1].setMnemonic(KeyEvent.VK_B);
  	menus[2].setMnemonic(KeyEvent.VK_E);  	menus[3].setMnemonic(KeyEvent.VK_H);
    for(int i = 0; i < menuitems.length; i++) {   
      menuitems[i].addActionListener(new Mykd());       //addKeyListener(new Keys());   
    }
    //	String[]str={"Schrift","Farbe","Musik","Rechner","Editor","Tageserlos","Monat Absatz","Umbuchung",
  	//"Install Font","Papierformat","info"};
  	
  	//chefs
  	menus[0].add(menuitems[5]);menus[0].add(menuitems[6]);menus[0].add(menuitems[7]);
  	menus[1].add(menuitems[0]);menus[1].add(menuitems[1]);
    menus[2].add(menuitems[2]);menus[2].add(menuitems[3]);menus[2].add(menuitems[4]);
    menus[3].add(menuitems[8]);menus[3].add(menuitems[9]);menus[3].add(menuitems[10]);menus[3].add(menuitems[11]);
    
    
    for(int i = 0; i < menus.length; i++)menubar.add(menus[i]);
    menubar.add(new JLabel("<html><body><center><font size=12pt font color=green><b>EbmGastroService [Erfassung]</b></font></center></body></html>",JLabel.CENTER));
    setJMenuBar(menubar);
    getContentPane().setLayout(new BorderLayout());
  getContentPane().add(p,BorderLayout.CENTER);              
    getContentPane().add(east, BorderLayout.WEST);          
  getContentPane().add(west,BorderLayout.EAST);         
    getContentPane().add(lastrow,BorderLayout.SOUTH);     
  setCursor(new Cursor(12));
    setFont(font);    
    setVisible(true); 
  	sysAdmin();
  	dtest();
  } 
  public static void main(String[] args) {new Rcn();}
}

