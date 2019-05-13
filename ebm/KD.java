package ebm;
import javax.swing.JApplet;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.io.*;
import java.util.Vector;
import javax.swing.border.*;
import java.util.StringTokenizer;
import com.search.sucheDate;
import com.units.MyZahl;
import com.options.MyOp;
import com.options.ausTeilen;
import com.units.save;
import com.tabel.tabelle;
import com.tabel.myTabel;
public class KD extends JApplet {
        String k_Code;
        String Name;
        String Vname;
        String Adresse;
        String Plz ;
        String Ort ;
        String Tel; 
        String Mobil;
		String[]opString;
		String[]opkey;
        int AnzBestellung;
        JTextArea ta  = new JTextArea(7,10);	
		//Container cp = getContentPane();
		JPanel top;
		JPanel sud ;
		JPanel mp ;
		myTabel tabe;//tabelle tabe;
	private JMenu menus = new JMenu(new ver6.Rb().kd_m());
    private JMenuItem[] items = new ver6.Rb().kd_mi();
        
  public KD(String a_k_Code, String a_Name, String a_Vname, String a_Adresse, String a_Plz,
        String a_Ort, String a_Tel, String a_Mobil, int a_AB)
  {
        k_Code = a_k_Code = "1";
        Name = a_Name = "Muster ";
        Vname = a_Vname = "Vorname";
        Adresse = a_Adresse = "Adresse";
        Plz = a_Plz ="Platz";
        Ort = a_Ort ="Ort";
        Tel = a_Tel = "T 45263542";
        Mobil= a_Mobil ="M 212125242";
        AnzBestellung = a_AB = 1;
  	top = new JPanel (new BorderLayout());
  	sud = new JPanel (new BorderLayout());
  	mp = new JPanel (new BorderLayout());
  	opString = new ver6.Rb().kd();
  	opkey = new ver6.Rb().opkd();
  	getContentPane().setLayout( new BorderLayout());

  }                  
  public KD(String code){
  	k_Code = code;
  	top = new JPanel (new BorderLayout());
  	sud = new JPanel (new BorderLayout());
  	mp = new JPanel (new BorderLayout());
  	opString = new ver6.Rb().kd();
  	opkey = new ver6.Rb().opkd();
  	getContentPane().setLayout( new BorderLayout());
  };
                //public KD(String vn){Vname = vn;};
  public KD(){
  	top = new JPanel (new GridLayout());
  	sud = new JPanel (new BorderLayout());
  	mp = new JPanel (new BorderLayout());
  	opString = new ver6.Rb().kd();
  	opkey = new ver6.Rb().opkd();
  	getContentPane().setLayout(new BorderLayout());
  };

                public String getk_Code()   {return k_Code;}
                public int getAnzBestellung()   {return AnzBestellung++;}
                public String getNam()   {return Name;}
                public String getVname()   {return Vname;}
                public String getAdresse()   {return Adresse;}
                public String getOrt()   {return Ort;}
                public String getPlz()   {return Plz;}
                public String getTel()   {return Tel;}
                public String getMobil()   {return Mobil;}

                void setk_Code(  String a_k_Code) {k_Code= a_k_Code;}
                void setAnzBestellung(int a_AB)   {AnzBestellung= a_AB;}
                void setNam(  String a_Name) {Name = a_Name;}
                void setVname(  String a_Vname) {Vname = a_Vname;}
                void setAdresse(  String a_Adresse) {Adresse = a_Adresse;}
                void setOrt(  String a_Ort) {Ort = a_Ort;}
                void setPlz(  String a_Plz) {Plz = a_Plz;}
                void setTel(  String a_Tel) {Tel = a_Tel;}
                void setMobil(  String a_Mobil) {Mobil = a_Mobil;}

                public String toString () {
                  return getk_Code()+", "+Name+", "+Vname+", "+Adresse+", "+Plz+", "+Ort+", "+Tel+", "+Mobil+","+AnzBestellung;
                }
                 public String kommazupoint(String antwort){
            	if(antwort.length()>0 && antwort!=null){
                		if(antwort.indexOf(',') > -1){
                   			StringBuffer z= new StringBuffer();
                   			z.append(antwort);
                   			int pos =antwort.indexOf(',');
                   			z.replace(pos,pos+1,".");
                   			antwort=z.toString();
                	   	}      
            	}
            	return antwort;
            }
            String[]opStr(int ab,int to){      	            
            	String[]str=new String[to];
            	for(int i=ab;i<to;i++)str[i]=opString[i];
            	return str;
            }
            String[]opKeys(int ab,int to){      	            
            	String[]str=new String[to];
            	for(int i=ab;i<to;i++)str[i]=opkey[i];
            	return str;
            }
				public String[] zufugen(){                        
						String[] str=opStr(0,8);
						//{"Code ","Familien Name ","Vorname","Adresse","Postleitzahl","Ortschaft","Fest Tel.","Mobil Tel."};
		 				Einfugen ein = new Einfugen(str); //ein.Panel();
		 				String[] st=ein.werte();//getFelder();//Panel();					
					for(int i=0;i<st.length;i++) System.out.println(i+">:"+st[i]);
					if (st!=null){   	
                        setk_Code(kommazupoint(""+st[0]));                        
                        setNam(kommazupoint(""+st[1]));                        
                        setVname(kommazupoint(""+st[2]));                        
                        setAdresse(kommazupoint(""+st[3]));                        
                        setPlz(kommazupoint(""+st[4]));                        
                        setOrt(kommazupoint(""+st[5]));                        
                        setTel(kommazupoint(""+st[6]));                       
                        setMobil(kommazupoint(""+st[7]));					
					}else {if(new MyOp().Frage(opString[8]//"Ich kann ohne Daten nicht Anfangen\nwollen Sie Weiter?"
					                           )==0)zufugen();}
                	return st;
                  }
                 public String[]bearbeiten(String gesucht){                        
                 	int pos=0;
					String[] str=opStr(0,8);
					//{"Code ","Familien Name ","Vorname","Adresse","Postleitzahl","Ortschaft","Fest Tel.","Mobil Tel."};
                 	String[] st=new String[str.length];
                 	if(gesucht!=null){pos=new sucheDate(gesucht,"gastro/date/kliste.dat",0).ListePos();}
                 	else pos=new sucheDate(new MyOp().eingabe(opString[22]),"gastro/date/kliste.dat",0).ListePos();
                  if(pos>=0){
                  	String[]v=lesen();
                  	String[]wort=new ausTeilen().koma(""+v[pos]);
		 			Einfugen ein = new Einfugen(str,wort);//ein.inhalt(); 
		 			st=ein.inhalt();
                  	
					if (st!=null){    
						for(int i=0;i<st.length;i++)st[i]=kommazupoint(""+st[i]);
                        setk_Code(""+st[0]);                        
                        setNam(""+st[1]);                        
                        setVname(""+st[2]);                        
                        setAdresse(""+st[3]);                        
                        setPlz(""+st[4]);                        
                        setOrt(""+st[5]);                        
                        setTel(""+st[6]);                       
                        setMobil(""+st[7]);					
						v[pos]="";
						v[pos]=k_Code+","+Name+","+Vname+","+Adresse+","+Plz+","+Ort+","+Tel+","+Mobil;
						if(new MyOp().Frage(v[pos]+"<htm><br><b>Updaten!!</b>")==0)new save().filefelde("gastro/date/kliste.dat",v,false);
					}
                  }else	{new MyOp().fehler(opString[10]);//"Datensatz nicht gefunden");
                  	st=null;}
                 	return st;
                 }
                public String zeigen(){                        
                	return("<font color=blue><b>Code: "+k_Code+"</b><br>"+Name+" "+Vname+"<br>"+Adresse+", "+Plz+" "+Ort+"<br>"+Tel+"<br>"+Mobil);//+AnzBestellung);
                }
               

                public void speichern(){
                        save s = new save();	
                		String str=(k_Code+","+Name+","+Vname+","+
                		Adresse+","+Plz+","+Ort+","+Tel+","+Mobil);    
                		System.out.println(s.file("gastro/date/kliste.dat",str,true));
                }

               public String[] tokenZeile(String str){
                            String gebe=""; 
                            ausTeilen z = new ausTeilen();
                            String[] wort = z.koma(str);
                            setk_Code(""+wort[0]);      //1
                            setNam(""+wort[1]);        //2
                            setVname(""+wort[2]);       //3
                            setAdresse(""+wort[3]);     //4
                            setPlz(""+wort[4]);         //5                                    
                            setOrt(""+wort[5]);         //6
                            setTel(""+wort[6]);         //7
                            setMobil(wort[7]);          //8                         
                           	setAnzBestellung(AnzBestellung+1);   	//
                             for ( int i = 1; i<wort.length; i++){
                                 // if (wort[i] != null){
                                      gebe=gebe+wort[i];
                                 // }
                               }                                
                            return(wort);
                }

                public void saveControl(){
                        MyOp k = new MyOp();
                        int an = 0 ;     
                	if(k_Code!=null || k_Code!=""){
                        an = k.Frage("<html>"+zeigen()+"</font>"+opString[11]);//"\n Soll gespeichert werden JA/NEIN?");
                        if(an==0){
                           try{ speichern();
                           } catch(Exception e){System.err.print("\n");}
                         }//else k.fehler(" Daten sind nicht gespeichrt!"); 
                	}

                }               
                public String[] BControl(){
                        MyOp k = new MyOp();
                        int an = 0 ;
                		String[] str=null;
                        //Object[]ob = opKeys(2,opkey.length);//new Object[2];
                        //ob[0]="Kunde erfassen";//2                        ob[1]="Weiter suchen";//3
                	   	String t =opString[12];//"Die suche ist Negativ! \nWie wollen Sie weiter gehen? ";                	
                        an = k.frage(t,opkey[2],opkey[3]);
                		if(an==0){
                            str=zufugen();
                			saveControl();                        
                        }else if(an==1){str=suchen();}//} catch(Exception e){System.err.print("\n"+e);}}
                        return str;
                }
         
         public String[] lesen(){ 
                     /*String[] v = null;                   
                       sucheDate su = new sucheDate("0","gastro/date/kliste.dat",0);
                       v= new String[su.myDaten().length];
                       v=su.myDaten();                   	*/
                      return new sucheDate("gastro/date/kliste.dat").myDaten();//(v);
                }          
                public String[] suchen(){
                        String myzeile="";
                        String[] mytoken= null;                		
                        String fragtext="";
                		int ftext=0;
                        int x=0;
                        MyOp k= new MyOp();                		
                		Object[] ob =opKeys(0,2);//{"Bezeichnung","Code Nummer"};
                        String str=null;
                        try{
                               x = k.frageO(opString[14],ob);//"Kunden suchen\nWie wollen sie?",ob);                        	   
                        if(x==0 || x==1){
                        	   fragtext=ob[x].toString();	  		                      	
                               str = k.eingabe(fragtext+" hier");
                        	if(str!=null){       	                        	
                               sucheDate su = new sucheDate(str,"gastro/date/kliste.dat",x);                        	
                        	 if(su.zeilenZahl()>0){
                               myzeile=su.wahlListe();                        		
                               if(myzeile!=" Leider nicht Vorhanden"){                               	
                               mytoken=tokenZeile(myzeile);}
                               else {
                                	//if(str.length()>8) str=str.substring(0,7)+"..";
                                	String t =opString[16]+//"Der Datensatz [ "+
                                	str+
                                	opString[17]+
                                	//" ] unter (Suche "+
                                	 fragtext+
                                	 opString[18];//+//          ")\nist "+
                                	 //myzeile;
        							k.fehler(t);                                    
                                    mytoken=BControl();
                               }
                        	 }else {
                        	 	k.fehler(opString[9]);//"die Liste ist leer!");
                        	 	new save().file("gastro/date/kliste.dat","000,Vn,Nn,Adr,Plz,Ort,TelFn,Telmobile",true);
                        	 	mytoken=BControl();                 	 
                        	 }
                        	}
                        }//}//else mytoken[0]="-1";
                        } catch(Exception e){System.err.print(",");}//\n suchen KD "+mytoken.length);}
                       return(mytoken);
                }
                public void panel(){
                 String[] str=opStr(0,8);
                 //{"Code ","Familien Name ","Vorname","Adresse","Postleitzahl","Ortschaft","Fest Tel.","Mobil Tel."};
                 String[] v=suchen();
                 if(v!=null){
                	JPanel kd=new Einfugen(str,v).kd();
                 	kd.setBackground(new ebm.myColor("hg").getColor());
                	mp.removeAll();
                	sud.remove(mp);                	
                 	if(kd!=null){mp.add(kd);mp.validate();}
                	sud.add(mp);sud.validate();                
                	validate();
                 }
                }
               
    class Mykd implements ActionListener {
     public void actionPerformed ( ActionEvent e ) {
        MyOp k= new MyOp();
        String fr = e.getActionCommand();
        if (fr.equals("Lesen")){
            String[] v =lesen();
    		for(int i=0;i<v.length;i++)v[i]=v[i].replace(',',';');
        	tabe =new myTabel("gastro/date/kliste.dat",false);
        	//tabelle(v);
        	//tabe.init();
      		//tabe.start();
      		//getContentPane().
      		//top.remove(tabe);top.validate();  
      		top.removeAll();
      		top.add(tabe.JS());//,BorderLayout.CENTER);
        	top.add(new JLabel("<html>status: "+v.length+" Daten gefunden"),BorderLayout.PAGE_END);
        	top.validate();          
      		//getContentPane().add(top); //sud.validate();
      		validate();          
        }       
        if (fr.equals("Erfassen")){
                try{
                        ta.setText("");
                         if(zufugen()!=null)saveControl();
                   } catch(Exception ioe){System.err.print("\n");}
         }
         if (fr.equals("Bearbeiten")){
                try{
                        ta.setText("");
                         bear();//bearbeiten(null);                                    		
                   } catch(Exception ioe){System.err.print("\n");}
                   //while(new MyOp().Frage("\nWeiter Bearbeiten?")==0)bearbeiten(null);
         }
        if (fr.equals("Suchen")){      
        	ta.setText("");       
        	panel();
        	ta.append("\n Code	  : "+getk_Code()+
        	          "\n Name 	  : "+Name+" "+Vname+
        	          "\n Adresse : "+Adresse+
        	          "\n Plz/Ort : "+Plz+" "+Ort+
        	          "\n Tel.Fest: "+Tel+
        	          "\n Mobile  : "+Mobil//+
        	          //"\nBestellung   : ("+AnzBestellung+") mal"
        	          );
        }
        if (fr.equals("Drucken")){new com.tabel.Report("gastro/date/kliste.dat"); }
        if (fr.equals("Beenden")){getContentPane().setVisible(false); 
        }
     }
   }
   void bear(){
   	if(bearbeiten(null)==null){
   		if(new MyOp().Frage(opString[19]//"<html><font color=red><br>in Erweiterte Textsuche suchen?"
   		                    )==0){suchen();bearbeiten(getk_Code());}
   	}else if(new MyOp().Frage(opString[20]
   	                          //"<html><font color=red><br>Weiter Bearbeiten?"
   	                          )==0)bear();
   }

 public void init(){ 
 	JPanel keys=new JPanel(new GridLayout(items.length,0));
       	menus.setMnemonic(KeyEvent.VK_I);
       	 for(int i = 0; i < items.length; i++) {
       	 	javax.swing.JButton bot=new javax.swing.JButton(items[i].getText());
       	 	bot.setActionCommand(items[i].getActionCommand());	
       	 	bot.addActionListener(new Mykd());
       	 	items[i].addActionListener(new Mykd());
       	 	menus.add(items[i]);
       	 	keys.add(bot);
       	 }
       	 JMenuBar mb = new JMenuBar();
      	mb.add(menus);
 		mb.add(new JLabel(opString[21],JLabel.CENTER)); 	
      	setJMenuBar(mb);	 	  	
 	Dimension s = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
 		keys.setBorder(new LineBorder(new Color(160,180,180),7,true));//BevelBorder.RAISED));
 	//	getContentPane().setLayout( new BoxLayout(cp, BoxLayout.Y_AXIS));
       	sud = new JPanel ();
       	top.setLayout( new BoxLayout(top, BoxLayout.Y_AXIS));//setLayout( new GridLayout(0,2));
       	sud.setLayout( new BorderLayout());
  		sud.setBorder(new LineBorder(new Color(160,180,180),7,true));//BevelBorder.RAISED));
 		
 		mp.setBorder(new LineBorder(Color.black,2,true)); 	
 		top.setBorder(new LineBorder(new Color(150,150,180),7,true));//BevelBorder.RAISED));
 		String[] str={"Code ","Familien Name ","Vorname",
        		 "Adresse","Postleitzahl","Ortschaft","Fest Tel.","Mobil Tel."};
 		String[]data={"Automatik","Max","Musterman","Muster Adresse","0000","Musterort","000000000","0000000",""};
 		JPanel kd=new Einfugen(str,data).kd(); 		
 		JScrollPane js=new JScrollPane (ta);
 		//mp.setPreferredSize(new Dimension(600,150));
 		js.setPreferredSize(new Dimension(s.width/4,s.height/2));
 		js.setBorder(new LineBorder(Color.black,3,false)); 
 		mp.add(kd); 	
 		ta.setForeground(Color.blue); 
 		//sud.add(new JLabel("A"+ta.getText()),BorderLayout.NORTH);
       	sud.add(js,BorderLayout.LINE_START); 		    	
 		sud.add(mp,BorderLayout.LINE_END);
 		//sud.add(new JLabel("B"+ta.getText()),BorderLayout.PAGE_END);
 		//sud.setPreferredSize(new Dimension(595,200));
       	//top.setPreferredSize(new Dimension(595,250)); 		
 		
 		String[] v = lesen();
		//if(v.length<=0)v=new String[]{"0,0,0,0","0,0,0,0"};
        tabe=new com.tabel.myTabel("gastro/date/kliste.dat",false);//new tabelle(v);//new JTabel(v)//tabelle(v); 	
 		/*tabe.setPreferredSize(new Dimension(s.width-180,s.height-500));
       	tabe.init();
        tabe.start();                	            	*/
       	top.add(tabe.JS());//,BorderLayout.PAGE_START);
       	top.add(new JLabel("<html>status: "+v.length+" Daten gefunden"),BorderLayout.PAGE_END);
 		top.setBackground(new ebm.myColor("hg").getColor());
 		top.setPreferredSize(new Dimension(s.width/2,s.height/2));
 		sud.setPreferredSize(new Dimension((s.width/2)+50,s.height/2));
 		//mp.setBackground(new ebm.myColor("hg").getColor());
 		getContentPane().setBackground(new ebm.myColor("hg").getColor()); 		
 		getContentPane().add(mb,BorderLayout.PAGE_START);//pane
 		getContentPane().add(new ver6.img().label(null,"logo.gif"));//panenew firma().data()
 		getContentPane().add(keys,BorderLayout.LINE_START); 		    	
        getContentPane().add(sud,BorderLayout.LINE_END);//panel);       	
       	getContentPane().add(top,BorderLayout.PAGE_END); 		    	
 		
       	getContentPane().setVisible(true);  	
 }

 public static void main(String[] args) { new com.display.Console().run(new KD(), 650, 500);  }


 }
