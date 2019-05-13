/* 
 * <@author "Mourad Elbakry">
 * @see java.lang.String   
*/
package ebm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.applet.AudioClip;
import com.tabel.*;
import java.io.*;
import java.util.*;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
 public class JL extends JApplet{
 	//serialVersionUID suid="11";
        String J_Code;
        String Datum;
        String Kcode;
        String Betrag;
        String Saldo ;
        String Fahre;
 		//sucheDate su = new sucheDate("gastro/date/Jliste.dat");        
        String Jl[]= new sucheDate("gastro/date/jliste.dat").myDaten();   
 		JTextArea ta  = new JTextArea(10,20);
	    JTextArea tb = new JTextArea(10,20);        
		Container cp = getContentPane();
		JPanel top = new JPanel (new BorderLayout());
		tabelle tabe;
	private JMenu menus =  new JMenu("Journal"); 
    private JMenuItem[] items = { new JMenuItem("Lesen",KeyEvent.VK_L), 
    new JMenuItem("Suchen",KeyEvent.VK_S),new JMenuItem("Buchen",KeyEvent.VK_B)};

  public JL (String a_J_Code, String a_Datum, 
             String a_Kcode, String a_Betrag, 
             String a_Saldo, String a_Fahre){
             	
        J_Code = a_J_Code ="";
        Datum = a_Datum = "";
        Kcode = a_Kcode = "";
        Betrag = a_Betrag = "";
        Saldo = a_Saldo ="";  		       	
  		//Jl=vor_lesen();//su.myDaten();         	
  }
  public JL (){
  	    J_Code = "RN250406/00";
        Datum  = "250406";
        Kcode  = "1002X00001250406";
        Betrag ="0.0";
        Saldo  = "0.0";  		  		
  		//Jl=vor_lesen();//su.myDaten();   
  };
                String getJ_Code()   {return J_Code;}
                String getDatum()   {return Datum;}
                String getKcode()   {return Kcode;}
                String getBetrag()   {return Betrag;}
                String getFahre()   {return Fahre;}
                void setJ_Code(  String a_J_Code) {J_Code= a_J_Code;}
                void setDatum(  String a_Datum) {Datum = a_Datum;}
                void setKcode(  String a_Kcode) {Kcode = a_Kcode;}
                void setBetrag(  String a_Betrag) {Betrag = a_Betrag;}
                void setSaldo(  String a_Saldo) {Saldo = a_Saldo;}
                void setFahre(  String a_Fahre) {Fahre = a_Fahre;}
                public String toString () {
                  return( J_Code+","+Datum+","+Kcode+","+Betrag+","+Saldo+","+Fahre);
                }
                public String[] lesen(){return(Jl);}
        //        public String[] vor_lesen(){su=new sucheDate("gastro/date/Jliste.dat");return su.myDaten();}

                public String tokenZeile(String str){
                	str=str.replace(';',',');
                            StringTokenizer st = new StringTokenizer(str,",");
                			//String cod = st.nextToken();
                            String cod = st.nextToken();setJ_Code(cod);
                            String n = st.nextToken(); setDatum(n);
                            String vn = st.nextToken();setKcode(vn);
                            String ad = st.nextToken();setBetrag(ad);
                            String ot = st.nextToken();
                            if (ot==null) ot="0";setSaldo(ot);
                            String fr = st.nextToken(); setFahre(fr);
                            //ta.append("\n"+J_Code+"\t "+Datum+"\t "+Kcode+"\t "+Betrag+"\t\t "+Saldo+" "+Fahre);
                            return(J_Code+","+Datum+","+Kcode+","+Betrag+","+Saldo+","+Fahre);
                }
                public String[] suchen(){                      
                    //String mytoken="";
                    //int x=0;
                    MyOp mo= new MyOp();
                    //try{
                    String str = mo.eingabe("Code oder  Bezeichnung  eingeben  Bitte");
                    sucheDate su = new sucheDate(str,"gastro/date/jliste.dat",0);
                    String[] myzeile= su.wahlinarrayJL();
                	if(myzeile.length>0){	//!=" Leider nic//ht Vorhanden"){
                		for(int i=0;i<myzeile.length;i++){
                			myzeile[i]= myzeile[i].replace(',',';');                            
                		}
                	}else { 
                		if(str.length()>8) str=str.substring(0,7)+"..";
                		mo.fehler(" Der Datensatz ["+str+"] unter (Suche) ist nicht vorhanden\n");
                	}
                    //} catch(NullPointException e){System.err.println("\n inJl suchen"+e);}                  
                    //System.out.println("Jl-suchen"+myzeile);
                      return(myzeile);
                }

                public void speichern(String str){
                	String zeile=""+J_Code+","+Datum+","+Kcode+","+Betrag+",0,F_"+Fahre+",-";
                	new com.units.save().file("gastro/date/fliste.dat",zeile,true);
                	zeile=""+J_Code+","+Datum+","+Kcode+","+Betrag+",0,F_"+Fahre+",-";
                	new com.units.save().file("gastro/fahre/"+str+".dat",zeile,true);
                	java.util.List<String> vector=new ArrayList<String>();
                	for(int i=0; i <Jl.length;i++){
                		if (Jl[i]!= null) {
                			vector.add(""+Jl[i]);
                		}
                	}
                	String[] nvec=new String[vector.size()];
                	for(int i=0; i <nvec.length;i++){
                		nvec[i]=vector.get(i);
                	}
                	new com.units.save().dontsort("gastro/date/jliste.dat",nvec,false);
                	/*try{
                        FileWriter dateiStream = new FileWriter ("gastro/date/Fliste.dat", true);
                        PrintWriter fl = new PrintWriter(dateiStream);
                        fl.println(J_Code+","+Datum+","+Kcode+","+Betrag+",0,F_"+Fahre+",-");
                        fl.close();
                		
                        dateiStream = new FileWriter ("gastro/fahre/"+str+".dat", true);
                        PrintWriter ausgabef = new PrintWriter(dateiStream);
                        ausgabef.println(J_Code+","+Datum+","+Kcode+","+Betrag+",0,F_"+Fahre+",-");        
                        ausgabef.close();

                        dateiStream = new FileWriter ("gastro/date/Jliste.dat");
                        PrintWriter j = new PrintWriter(dateiStream);
                        for(int i=0; i <Jl.length;i++){
                                if (Jl[i]!= null) {
                                        j.println(Jl[i]);
                                 }
                        }
                        j.close();
                	} catch(Exception e){System.err.print("\n Jl speichern"+e);}*/


                }


                public String[] vorbuchen(){//throws IOException{
                	java.util.List<String> v = new ArrayList<String>();                	
                	for(int i=0;i<Jl.length; i++){
                		if(Jl[i]!=null)v.add(Jl[i]);
                	}
                	String cmd[]= new String[v.size()];
                	for (int i = 0; i < cmd.length; i++)
                		cmd[i] = (String) v.get(i);
                	return(cmd);
                }

                public String[] nachbuchen(String str,int zn){
                        MyOp mo = new MyOp(); 
                  // try{
                   		String mstr=""+Jl[zn];	
                		String[]tasta={"Ja","Nein"};
                        int x = mo.frage1("<Html><font size=4 face='Courier New' color=blue>"+mstr+"</font>"+
                          "<br><font size=4>wird zum : <font color=red><b>"+str+"</b></font>"+
                          " vergebucht JA oder Nein </font>",tasta);
                        if (x == 0) {
                                 setFahre(str);
                                 Jl[zn]=null;
                                 speichern(str);
                         }   
                  // } catch(NullPointerException e){System.err.print(e+" ** nachbuchung ");}
                   return(Jl);
                }
                 public String FahreName(){
                 	MyOp mo = new MyOp();                    
                	String[] myliste = new sucheDate("gastro/date/benutzer.dat").myDaten();
                 	String eintext="<html><font color=red>Fahrer Code</font><font color=blue>";
                 	for(int i=0; i<myliste.length; i++){
                 		if(i % 2==0)eintext+="<br>";
                 		eintext+=""+i+" = "+myliste[i]+"&nbsp;&nbsp;&nbsp;&nbsp;";
                 	
                 	}
                 		eintext+="<br></font></html>";
                 	String dernamecode=mo.eingabe(eintext);
                 	String dername="";
                 	int i=-1;
                 	if(dernamecode.length()>0){
						try {    
       					 i=Integer.parseInt(dernamecode);
                 		} catch (NumberFormatException e) {System.out.println("\nZahlfehler JL " + e);i=-1;}                 	
                 	}
                 	if(i>=0 && i<myliste.length)dername=myliste[i];
                 	else dername=mo.wahl(myliste,"Fahrer suchen");
                 	//sucheDate().dir("Fahre/.");
                //	for(int i=0;i<myliste.length;i++)myliste[i]=myliste[i].substring(0,myliste[i].length()-4);
                 return dername;//new Jlist(myliste,false).element();
                }
				/*String[] FahreName(){
                	String[] myliste = new sucheDate().dir("Fahre/.");
                	for(int i=0;i<myliste.length;i++)myliste[i]=myliste[i].substring(0,myliste[i].length()-4);
                	return myliste;
                }*/
                public String zeilezumFahre(int zn){
                        MyOp mo = new MyOp();                    
                        String fstr = FahreName();
                        //mo.fehler("Zeile: "+zn+" "+Jl[zn]+" \n zum Fahrer?  "+fstr);                        
                	    tb.append("\n> "+zn+" "+elementieren(Jl[zn])+"  gebucht");
                   return(fstr);
                }

                public String buchen(){
                        MyOp mo = new MyOp();
                        int zn =0;
                  try{
                            if (Jl!= null) Jl = vorbuchen();
                  		zn =new Jlist(Jl,false).ergebnis();// Integer.parseInt(mo.eingabe(" Zeile Nummer eingeben: "));
                            String arylang = Jl[zn];
                  //	System.out.println("in JL: "+Jl[zn]);
                            if (arylang.length()>0){
                            	
                                        tokenZeile(arylang);
                             } 
                          Jl = nachbuchen(zeilezumFahre(zn),zn);
                          ta.setText("\n");

                          for(int i=0; i < Jl.length;i++){
                                 if (Jl[i] != null) {
                                     ta.append("\n> "+i);
                                 	 elementieren(Jl[i]);
                                  }
                           }                        
                   }catch(NullPointerException e){System.err.print(e+" ** buchen");}
                   return(zn+"(-) "+toString()+" ist gebucht");
                }
                public String elementieren(String str){
                	ausTeilen a=new ausTeilen();
                	str=str.replace(';',',');//*********************
                	String[] wort = a.koma(str);
                 	for(int m=0; m<wort.length;m++){
                 		if (wort[m]!= null) 
                 		ta.append("   "+wort[m]);
                 	}                    
                	return str;
                }

  class Journal implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String target = e.getActionCommand();
      MyOp mo = new MyOp();       
      String ein = null;      
      int i = 0;
    	
      int x=0;
    	
    	if (target.equals("Lesen")){
    		String[] v =lesen();
    		for(i=0;i<v.length;i++)v[i]=v[i].replace(',',';');
            tabe =new tabelle(v);
      		tabe.init();
      		tabe.start();
      		cp.remove(top);
      		top.removeAll();
      		top.add(tabe);//,BorderLayout.CENTER);
      		cp.add(top); //sud.validate();
      		validate(); 
         }
    
      if (target.equals("Buchen")){
         try{
         	tb.setText("");
            tb.append("\n"+buchen());
         } catch(Exception ioe){System.err.print("\n"+ioe+" buchen ");}
      }
      if (target.equals("Suchen")){      	
      	tabe =new tabelle(suchen());
      	tabe.init();
      	tabe.start();
      	cp.remove(top);
      	top.removeAll();
      	top.add(tabe);//,BorderLayout.CENTER);
      	cp.add(top); //sud.validate();
      	validate();
      }
    }
  }


      public void init(){  	
       	menus.setMnemonic(KeyEvent.VK_J);
       	 for(int i = 0; i < items.length; i++) {
       	 	items[i].addActionListener(new Journal());
       	 	menus.add(items[i]);
       	 }
       	 JMenuBar mb = new JMenuBar();
      	mb.add(menus);
      	setJMenuBar(mb);
       	cp.setLayout(new GridLayout(3,0));
       	JPanel sud = new JPanel ();
       	top.setLayout( new GridLayout(1,0));
       	sud.setLayout( new GridLayout(2,0));
		ta.setBackground(Color.cyan);
      	ta.setFont(new Font("Courier New",0,12));
      	
       	sud.setBackground(Color.red);
       	sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));
  		sud.setBorder(new BevelBorder(BevelBorder.RAISED));
       	sud.add(new JScrollPane (ta));
       	JLabel jl = new JLabel("<html><Font Color=blue> <font face=Swis721 BdOul BT> <b><font size=-2>"+
  		                    "<center>"+"Journal"+"</center></b></font></html>",JLabel.CENTER);
       	sud.add(jl);              	
       	String[] v = {"0;0;0;0","0;0;0;0"};
       	tabe=new tabelle(v);
       	tabe.init();
      	//tab.setFont(new Font("Courier New",0,12));
        tabe.start();                	             	 
       	top.add(tabe);              
        cp.add(sud);//panel);       	
       	cp.add(top);
       	cp.setVisible(true);  	
  	
  }
  public static void main(String[] args) {
    schow.run(new JL(), 650, 500);
  }

 }


