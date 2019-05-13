package ebm;
import javax.swing.JApplet;
import javax.swing.JTextField;
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
import com.tabel.myTabel;

public class WD extends JApplet{
        String w_Code;
        String Bezeichnung;        
        double Preis;
		String Spalte; 	    
        JTextField ta  = new JTextField("");
        private JMenu menus =  new JMenu("Ware"); 
    private JMenuItem[] items = { new JMenuItem("Lesen",KeyEvent.VK_L), 
    new JMenuItem("Suchen",KeyEvent.VK_S),new JMenuItem("Erfassen",KeyEvent.VK_E),
    new JMenuItem("Entfernen",KeyEvent.VK_T),new JMenuItem("Bearbeiten",KeyEvent.VK_B),
    new JMenuItem("Drucken",KeyEvent.VK_D),new JMenuItem("Importieren",KeyEvent.VK_I)};
		//Container cp = getContentPane();
		JPanel top = new JPanel (new BorderLayout());
		JPanel sud = new JPanel (new BorderLayout());
		JPanel mp = new JPanel (new BorderLayout());
		myTabel tabe;
	//JTabel tabe;
  public WD(String a_w_Code, String a_Bezeichnung, double a_Preis,String a_Spalte)  {
        w_Code = a_w_Code;
        Bezeichnung = a_Bezeichnung ;
        Preis = a_Preis ; 
  		Spalte=a_Spalte;
  }
  public WD(){
       /* w_Code ="000";
        Bezeichnung ="Art.000";
        Preis =0.0;
  		Spalte="0";  	*/
  };
	public String getAppletInfo() {return "Warenliste EBM Gastro 2007";}
    public String[][] getParameterInfo() {       
       String[][] info = { 	       
            {"dir","gastro/date","Directory."},                         
             {"file", "gastro/date/wliste.dat","Filename."},             
        };
        return info;
    }
            public String getw_Code(){return w_Code;}
            public String getBezeichnung(){return Bezeichnung;}                
            public String getSpalte(){return Spalte;}                
            public double getPreis(){return Preis;}

               public void setw_Code(  String a_w_Code) {w_Code= a_w_Code;}
               public void setBezeichnung(  String a_Bezeichnung) {Bezeichnung = a_Bezeichnung;}                
               public void setPreis(  double a_Preis) {Preis = a_Preis;}
               public void setSpalte(  String spalte) {Spalte= spalte;}
            public String bindestring(String antwort){
                		StringTokenizer st = new StringTokenizer(antwort);                            
                    	String ant="";
                            while(st.hasMoreTokens()){
                                 ant =ant+st.nextToken()+"_";
                             }
                             ant= ant.substring(0,ant.length()-1);
                	return ant;                    
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
 			public String pointzukomma(String antwort){
                		if(antwort.indexOf('.') > -1){
                   			StringBuffer z= new StringBuffer();
                   			z.append(antwort);
                   			int pos =antwort.indexOf('.');
                   			z.replace(pos,pos+1,",");
                   			antwort=z.toString();
                	   	}      
                	   	return antwort;
                }
                public int bearbeiten(String gesucht){                        
                 	int pos=0;
					String[] str={"Waren Code ","Artekel Bezeichnung ","Preis","Spalte"};            		
                 	if(gesucht!=null){pos=new sucheDate(gesucht,"gastro/date/wliste.dat",0).ListePos();}
                 	else pos=new sucheDate(new MyOp().eingabe("<html><center><font color=red face='Courier New' size=+1>"+
                 	                                          "genaue Eingabe</font></center>"+
                 	                                          "<br>Sie Suchen nach Code oder Bezeichnung"),
                 	                                          "gastro/date/wliste.dat",0).ListePos();
                  if(pos>=0){
                  	String[]v=lesen();                  	
                  	String[]ls=lese_spalte();
                  	String[]wort0=new ausTeilen().komma(""+ls[pos]);
                  	String[]wort=new ausTeilen().komma(""+v[pos]+","+wort0[1]);
		 			Einfugen ein = new Einfugen(str,wort); 
                  	ein.inhalt();
		 			String[] st=ein.getFelder();
					if (st!=null){                       
                        setw_Code(kommazupoint(""+st[0]));                        
                        setBezeichnung(kommazupoint(""+st[1]));                        
						double za = Double.parseDouble(kommazupoint(""+st[2]));
                   	    setPreis(za);                       
                        setSpalte(""+st[3]);  
						v[pos]="";
						v[pos]=w_Code+","+Bezeichnung+","+Preis;						
						ls[pos]="";ls[pos]=""+w_Code+","+st[3];						
						if(new MyOp().Frage(v[pos]+"\n"+ls[pos]+"\nUpdaten!!")==0){
							new save().filefelde("gastro/date/wliste.dat",v,false);								
							save_spalte(ls);
						}
					}
                  }else	new MyOp().fehler1("<html><center><font color=red face='Courier New' size=+1>"+
                                           "Datensatz nicht gefunden</font></center>"+
                                           "<br><u>L&ouml;sung:</u>"+
                                           "<br>geben Sie Bezeichnung oder Code genaue ein");
                return pos;
                }
               public String[] zufugen(){                        
					String[] str={"Code ","Art. Bezeichnung ","Preis","Spalte"};            		
		 			Einfugen ein =new Einfugen(str);
		 			String[] st=ein.inhalt();
					if (st!=null){                       
                        setw_Code(kommazupoint(""+st[0]));                        
                        setBezeichnung(kommazupoint(""+st[1]));                        
						double za = Double.parseDouble(kommazupoint(""+st[2]));
                   	    setPreis(za);                       
                        setSpalte(""+st[3]);                                                
					}else
						if(new MyOp().Frage("<html><font size=+1 font color=red>Ich kann ohne Daten nicht Anfangen\n"+
					   "<html><font size=-1 font color=blue>Alle Felde sollen beschrieben sein!\nwollen Sie Weiter?")==0)
					   zufugen();//else st=null;
                	return st;
					}
                
            public String zeigen(){
                        return(w_Code+","+Bezeichnung+","+""+Preis+","+Spalte);
                }
            public String myString () {
                  return (w_Code+","+Bezeichnung+","+Preis+","+Spalte);
                }
			public String saveControl(){                                
			 	String str=null;
                int an = new MyOp().Frage(zeigen()+"\n Eingabe Kurrekt? ");
                if(an!=1){
                	str=speichern(); 
                	getWarenGruppe();    	
                }else str=null;
			 	return str;

			 }
			 public String[]lese_spalte(){			 		
			 	//new sucheDate("gastro/date/drucker/wl-spalten.dat").myDaten();
			 	return new sucheDate("gastro/date/wl-spalten.dat").myDaten();
			 }
			 public void save_spalte(String[]v){			 	
			 	new save().filefelde("gastro/date/wl-spalten.dat",v,false);
			 	new save().filefelde("gastro/date/drucker/wl-spalten.dat",v,false);
			 }
			 String[]getWarenGruppe(){
			 	String[]ls=lese_spalte();	
			 	String[]v=lesen();
			 	if(v.length>0 && ls.length>0){
			 		for(int i=0;i<v.length;i++){
			 			String[] wort=new ausTeilen().koma(""+ls[i]);
			 			v[i]=v[i]+","+wort[1];
			 		}
			 	}
			 	new save().filefelde("gastro/date/wgliste.dat",v,false);
			 	return v;
			 }
			 public String speichern() {//throws IOException{              	
              	String file="gastro/date/wliste.dat";
			  	String str = (w_Code+","+Bezeichnung+","+Preis);	              	
              	new MyOp().fehler(new save().file(file,str,true));			 	
			 	sort();
              	new save().file("gastro/date/wl-spalten.dat",w_Code+","+Spalte,true);
			 	new save().file("gastro/date/drucker/wl-spalten.dat",w_Code+","+Spalte,true);
              	return str;
              }
              void sort(){
              	new save().filefelde("gastro/date/wliste.dat",lesen(),false);              	
              }      
			 public String[]lesen(){
			  	String[]v = new sucheDate("gastro/date/wliste.dat").myDaten();			  
			  	return v;
			  }
			  public String BControl(){
              		 String str="";
            	String[]tast={"<html><b><font color=red size=+1>Ware erfassen</font>",
            	"<html><b><font color=green size=+1>Weiter suchen</font>"};
                     int an = new MyOp().frage1("Die suche ist Negativ\nWie wollen Sie weiter gehen! ",tast);
                        if(an==0){                                             	
                        	if(zufugen()!=null){
                        		if(saveControl()!=null){
                        			   str=zeigen();
                        		} else str=suchen();
                        	}else str=suchen();
                         }else str=suchen();//if(tokenZeile(zeigen())!=null)ta.append(suchen());
            	return str;
			  }
			  public int chekstr(String str){
                	if(str.length()== 1)return 1;else return 0;
			  }
			  public String Weingabe(){
			  	String str = new MyOp().eingabe("<html><b><font color=green size=4>" +
                	                "Waren Code oder Bezeichnung eingeben <br>"+
                	                "   oder [Ok] zum Warenliste</font>");            
			  	return str;
                	
			  }
			  public String suchen(){
			  	String myzeile="";
    			String[] mytoken= null;
    			String mytok=null;
    			int x=0;
    			MyOp k= new MyOp();
    			String str=null;
    			String file="gastro/date/wgliste.dat";
    			   	str = Weingabe();            
                	if(str!=null && str!=""){                		
                		str=kommazupoint(str);
                		sucheDate su = new sucheDate(str,file,x);
                		if(su.zeilenZahl()>0){                			
                			myzeile=su.Wcode(""+str);
                			if(myzeile.length()==0)myzeile=su.wahlware();
                			if(myzeile!=" Leider nicht Vorhanden"){
                        		    mytoken=tokenZeile(myzeile);
                        			mytok =""+mytoken[0];
                        			for (int i=1; i<mytoken.length;i++)mytok=mytok+","+mytoken[i];
                			}else {               				
                			if(str.length()>8) str=str.substring(0,7)+"..";
                				k.fehler(" Der Datensatz ["+str+"]  ist"+myzeile+"\n");
                				mytok=BControl();
                			}
                		}else{
                			k.fehler("die Liste ist leer!");
                			if(lesen().length>0)getWarenGruppe();
                			else {
                				new save().file("gastro/date/wgliste.dat","0,Speisen,0.0,0",false);
                				new save().file("gastro/date/wliste.dat","0,Speisen,0.0",false);
                				save_spalte(new String[]{"0,0"});
                				BControl();
                			}
                		}
                	}else mytok="";
    			return(mytok);
    		}
            public String[] tokenZeile(String str){                
             ausTeilen z = new ausTeilen();
            	String[] wort=null;
             if(str.length()>0){
               	wort = z.koma(str);
             	if(wort[0].equals(null)||wort[1].equals(null))wort=null;
             	else{
             	   	setw_Code(""+wort[0]);      //1
             	   	setBezeichnung(""+wort[1]);        //2
             	   	setPreis(Double.parseDouble(""+wort[2]));       //3
             	   	if(wort.length>3)setSpalte(""+wort[3]);       //4
             	}
             }
             return(wort);
            }
		 public void panel(){
            String[] str={"Code ","Art.Bezeichnung","Preis","Waren Gruppe"};
		 	suchen();
		 	String[] v=new ausTeilen().koma(myString());
		 	//System.out.println("Ware: "+v.length);
		 	if(v!=null){
		 		JPanel kd=new Einfugen(str,v).kd();
		 		kd.setBackground(new ebm.myColor("hg").getColor());
		 		mp.removeAll();
		 		sud.remove(mp);
		 		if(kd!=null)mp.add(kd);
		 		sud.add(mp,BorderLayout.PAGE_START);
		 		sud.validate();
		 	}
		 }
		 void gelesen(){
		 	getWarenGruppe();
        	tabe=new com.tabel.myTabel("gastro/date/wgliste.dat",false);        
        	top.removeAll();
      		top.add(tabe.JS());
      		top.validate();          validate();          
		 }
    class Mywd implements ActionListener {
      public void actionPerformed ( ActionEvent e ) {
        MyOp k= new MyOp();
        String fr = e.getActionCommand();
        if (fr.equals("Lesen")){               
        	gelesen();
        }
        if (fr.equals("Erfassen")){
        	try{
        		ta.setText("");
        		zufugen();
        		saveControl();
        		gelesen();
        	} catch(Exception ioe){System.err.print("\nW-Erfasse: ");}
        }
        if (fr.equals("Suchen")){                       	
            ta.setText("");       
        	panel();        	
            ta.setText(zeigen());                	                          	
         }
         if (fr.equals("Entfernen")){                       	    
        	
         }
         if (fr.equals("Bearbeiten")){
         	try{
         		ta.setText("");
         		bear();         		
         		gelesen();
         	} catch(Exception ioe){System.err.print("\nW-Bearbeiter: ");}         	
         }
         if (fr.equals("Drucken")){new com.tabel.Report("gastro/date/wgliste.dat"); }
         if (fr.equals("Importieren")){
         	String[][]mstr=new com.search.sucheDate("gastro/date/wgliste.dat").teiledaten();          	
         	for(int i=0;i<mstr.length;i++){         	
         		mstr[i][2]=f(""+mstr[i][2]);
         		for(int x=0;x<mstr[i].length;x++){
         			mstr[i][x]="\""+mstr[i][x]+"\"";
         		
         		}         		  		         		
         	}
         	new com.units.save().dfilefelde("gastro/date/cvs/wgliste.csv",mstr,false);         	
         }
      }
    }
    public String f(String in){
   	return new com.units.MyZahl().deci(Double.parseDouble(in));
   }
    void bear(){
    	if(bearbeiten(null)<0){
    		if(new MyOp().Frage("<html><font color=red><br>in Erweiterte Textsuche suchen?")==0){suchen();bearbeiten(getw_Code());}
    	};        
    	if(new MyOp().Frage("<html><font color=red><br>Weiter Bearbeiten?")==0)bear();
    }
 public void init(){  	
 	JPanel keysP=new JPanel();
 	JPanel keys=new JPanel( new GridLayout(items.length,0));
 	keysP.setLayout(new BorderLayout());//new javax.swing.BoxLayout(keysP, javax.swing.BoxLayout.PAGE_AXIS));
       	menus.setMnemonic(KeyEvent.VK_W);
       	 for(int i = 0; i < items.length; i++) {
       	 	javax.swing.JButton bot=new javax.swing.JButton(items[i].getText());
       	 	bot.setActionCommand(items[i].getActionCommand());	
       	 	bot.addActionListener(new Mywd());
       	 	items[i].addActionListener(new Mywd());
       	 	menus.add(items[i]);
       	 	keys.add(bot);
       	 }
       	 keysP.add(keys,BorderLayout.PAGE_START);
       	 keysP.setBackground(new ebm.myColor("hg").getColor());
       	// keysP.add(new ver6.img().label(new firma().data(),"logo.gif",180,150),BorderLayout.PAGE_END);//pane
       	 JMenuBar mb = new JMenuBar();
      	mb.add(menus);
 		mb.add(new JLabel("<html><body><center><font size=12pt font color=green><b>EbmGastroService [ Waren Datenbank ]</b></font></center></body></html>",JLabel.CENTER));
      	setJMenuBar(mb);
       	getContentPane().setLayout( new BorderLayout());
       	sud = new JPanel ();
       	top.setLayout( new GridLayout(1,0,1,1)); 		
       	//sud.setLayout( new GridLayout(4,0));
		//ta.setBackground(new Color(102,102,0));  		      		
       	sud.setLayout( new BorderLayout());//new GridLayout(4,1,5,5));//BoxLayout(sud, BoxLayout.Y_AXIS));
  		//sud.setBorder(new BevelBorder(BevelBorder.RAISED));
 		//sud.setBorder(new EtchedBorder(3, Color.green , Color.black) );
		sud.setBorder(new LineBorder(new Color(50,50,180),10,true));//BevelBorder.RAISED));
 		//mp.setBorder(new MatteBorder(2,1,8,5,Color.gray));//,new Color(202,150,0)));
 		mp.setBorder(new LineBorder(Color.black,2,true));
 	//mp.setTitledBorder(new TitledBorder(mp.getBorder()," Ergebniss",2,4,new Font("Roman",1,14),Color.red));
 		top.setBorder(new LineBorder(Color.blue,10,true));//BevelBorder.RAISED));
 		//mp.setPreferredSize(new Dimension(400,450));
 		//mp.setBackground(new Color(112,112,0));  		      		
 		String[]str={"Waren Code","Bezeichnung","E-Preis","Spalte"};
 		String[]data={"155","Cardinale","5.50","2"};
 		JPanel kd=new Einfugen(str,data).kd();
 	//	mp.setPreferredSize(new Dimension(450, 150));
 		mp.add(kd,BorderLayout.PAGE_START);
 		mp.add(new JLabel(),BorderLayout.PAGE_END);
 		sud.setBackground(new ebm.myColor("hg").getColor());
       //	sud.add(ta,BorderLayout.PAGE_START); 	 		 		
 		
 		JPanel centerpanel=new JPanel(new BorderLayout());
 	    centerpanel.setBackground(new ebm.myColor("hg").getColor());
 		
 		JPanel logopanel=new JPanel(new BorderLayout());
 	    logopanel.setBackground(new ebm.myColor("hg").getColor());
 		//logopanel.add(null,BorderLayout.LINE_START);
 		logopanel.add(new ver6.img().label(null,"logo.gif",150,100),BorderLayout.LINE_END); 	
 		sud.add(mp,BorderLayout.PAGE_START);////new ver6.img().label(new firma().data(),
 		 sud.add(centerpanel,BorderLayout.CENTER);                                                                
 		sud.add(logopanel,BorderLayout.PAGE_END);////new ver6.img().label(new firma().data(),
       	JLabel jl = new JLabel("Waren Daten Bank",JLabel.CENTER);
 	    //jl.setBackground(new Color(102,102,0));  		      		
 		JPanel jp=new JPanel(new BorderLayout());
 		jp.setBorder(new LineBorder(Color.black,2,true));//BevelBorder.RAISED));
 		jp.add(jl,BorderLayout.CENTER);
 		String[] v = getWarenGruppe();	
 		tabe=new com.tabel.myTabel("gastro/date/wgliste.dat",false);
        top.add(tabe.JS());              
 		getContentPane().add(mb,BorderLayout.PAGE_START);//pane
 		getContentPane().add(keysP,BorderLayout.LINE_START); 
        getContentPane().add(sud,BorderLayout.EAST);//panel);       	
       	getContentPane().add(top,BorderLayout.CENTER);
 		getContentPane().add(jp,BorderLayout.PAGE_END);               	
       	getContentPane().setVisible(true);  	
 }

 //public static void main(String[] args) {    Console.run(new WD(), 650, 500);  }


}
