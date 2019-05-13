package ebm;
import java.util.Vector;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import javax.swing.border.*;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
import com.tabel.*;
import com.security.*;
public class FL extends JApplet{
        String F_Code;
        String Datum;
        String Kcode;
        double Betrag;
        double Saldo ;
        String Fahre;
		int Xy;
        JTextArea tb = new JTextArea(8,20);            
    	MyOp mo = new MyOp(); 
		Container cp = getContentPane();
		JPanel top = new JPanel ();
		tabelle tabe;
	private JMenu menus =  new JMenu("Fahrer"); 
    private JMenuItem[] items = { 
    	new JMenuItem("Lesen",KeyEvent.VK_L), 
    	new JMenuItem("Suchen",KeyEvent.VK_S),
    	new JMenuItem("Bericht",KeyEvent.VK_S),
    	new JMenuItem("updat",KeyEvent.VK_S),
        new JMenuItem("Buchen",KeyEvent.VK_B)};
	
  public FL (String a_F_Code, String a_Datum, String a_Kcode, double a_Betrag, double a_Saldo, String a_Fahre,int xy)
  {
        F_Code = a_F_Code ="RN00_00/000";
        Datum = a_Datum = "00_00_0000";
        Kcode = a_Kcode = "0000X000000_00";
        Betrag = a_Betrag = 0.0;
        Saldo = a_Saldo = 0.0;
        Fahre =a_Fahre="0";  	    
        Xy= xy = 0;
  }

                public FL (){};
               public String getF_Code()   {return F_Code;}
               public String getDatum()   {return Datum;}
               public String getKcode()   {return Kcode;}
               public double getBetrag()   {return Betrag;}
               public double getSaldo()   {return Saldo;}
               public String getFahre()   {return Fahre;}
               public int getXy()   {return Xy;}

                public void setF_Code(  String a_F_Code) {F_Code= a_F_Code;}
                public void setDatum(  String a_Datum) {Datum = a_Datum;}
               public void setKcode(  String a_Kcode) {Kcode = a_Kcode;}
               public void setBetrag(  double a_Betrag) {Betrag = a_Betrag;}
               public void setSaldo(  double a_Saldo) {Saldo = a_Saldo;}
               public void setFahre(  String a_Fahre) {Fahre = a_Fahre;}
               public void setXy(  int xy) {Xy = xy;}


                public String[] zeigeFahre(String str)throws IOException{
                        String filename ="gastro/fahre/"+str+".dat";
                        String zeile;
                	    sucheDate su = new sucheDate(filename);                	
                        String[] fx = su.myDaten();//new  String[su.filebig()];
                        int i = 0;                    
                	setSaldo(0);                	
                	for (i=0;i<fx.length;i++){
                		//System.out.println(fx[i]);
                		if(fx[i]!=null){
                			fx[i]=tokenZeile(fx[i],0);
                			setXy(i); 
                		}
                	}                	
                		
                	setFahre(str);
                    return (fx);                
                }
                 public String elementieren(String str){
                	ausTeilen a=new ausTeilen();
                	String[] wort = a.komma(str);
                 	str="";
                	for(int m=0; m<3;m++){
                 		if (wort[m]!= null) 
                 		str=str+wort[m];                	
                 	}                    
                 //	System.out.println("eleminiert:"+str);
                	return str;
                }

                //sie Ã¼bernimmt die werte vom Fahrer Register(Fahrer/XX.dat)
                //und vegleicht diesen mit der Werte von die gemeinsammen Fahrer Register(gastro/date/FListe.dat)
                //trifft den Vergleich zu, dann wird eine Ã„nderung von (- zu +)
                //+ wirkt dass die Buchung durch gefÃ¼hrt worden
                //(Fahrer.dat) wird entleert, FListe wird neu gefasst
                //Kbuch.dat Ã¼bernimmt die neue liste in eine Zeile mit dem +zeichen
                public String[] vergleich(String[] fxx){ //@Argument fxx ist die Werte Fahrer/xx.dat
                	String[]fll = lesen();//die werte vom Fliste.dat                	
                 try{                 	   
                       String kor = null;//Fahrerliste.dat
                       String kor1 = null;//F_*.dat
                       tb.append("\nzum umbuchen\n__________");
                     for (int i = 0; i< fll.length;i++){
                        kor = null;
                        if (fll[i] != null){
                        	//um die Vergleicheszweck R-Code,K-Code und Datum
                        	//ab 44 kommen die BetrÃ¤ge und die sind Verschieden
                        	
                          //kor = fll[i].substring(0,44);
                        	kor=elementieren(fll[i]);
                        	//double altsaldo = getSaldo();
                           for (int x = 0; x< fxx.length;x++){
                              kor1=null;
                             if (fxx[x] != null) kor1 = elementieren(fxx[x]);//.substring(0,44);
                                if(kor.equals(kor1)){
                                    fll[i]= fll[i].replace('-','+');
                                	//	System.out.println("*"+fll[i]);
                                	
                                	fll[i]=tokenZeile(fll[i],1);
                                	//setSaldo(altsaldo);
                                    tb.append("\n+ " + fll[i]);
                                
                                	//fll[i]=tokenZeile(fll[i]);
                                }
                            }
                        }
                     }
                   } catch(Exception e){System.err.print("++");}
                   tb.append("\n Umbuchung durchgefuehrt");
                	intabele(fll);
                   return(fll);
                }
                public String speichern(String kb, String[] fll) throws IOException{
                int x=0;
                try{String filen=new ver6.basic().getPath().replace('\\','/')+"gastro/date/kbuch.dat";		
                        FileWriter dateiStream = new FileWriter (filen, true);
                        PrintWriter kbc = new PrintWriter(dateiStream);
                             if (kb != null){
                                kbc.println(kb);
                              }
                        kbc.close();
                	//Fahrer Journal Korregieren
                	 filen=new ver6.basic().getPath().replace('\\','/')+"gastro/date/fliste.dat";		
                        dateiStream = new FileWriter (filen);
                        PrintWriter faus = new PrintWriter(dateiStream);
                	setSaldo(0);
                        for (int i = 0; i< fll.length; i++){
                             if (fll[i] != null){
                                  faus.println(tokenZeile(fll[i],0));                                  
                             }
                         }
                        faus.close();
                		//fahre Akte entleeren.
                		filen=new ver6.basic().getPath().replace('\\','/')+"gastro/fahre/"+Fahre+".dat";		
                        dateiStream = new FileWriter (filen);
                        PrintWriter fr = new PrintWriter(dateiStream);
                        fr.close();                          
                   } catch(Exception e){System.err.print("\nfehle beim Speichern"+e);}                         
                     return("\n zum Kasabuch "+kb+"\n Umsatz die FahrerListe ist "+(float)Saldo);
                }

               public String tokenZeile(String str, int jn){
                            String gebe="";//RN290107/2239,29_01_2007,2353007X01290107,25.8,25.8,F_Jovani,+
                            ausTeilen z = new ausTeilen();
                            String[] wort =  z.koma(str);
               
                                       //wort[1]=z.randstS(" "+wort[1],13);
                                       setF_Code(wort[0]);//1

                                       myDatum dm = new myDatum();
                                       String n = dm.ist();
               						if(jn==1) wort[1]=n;
                                       setDatum(wort[1]); //2 gebucht am
                                       //wort[3]=z.randstS(" "+wort[3],20);
                                       setKcode(wort[2]);    //3

                                       float d=Float.parseFloat(wort[3]);
                                       wort[3]=""+d;
                                       //wort[4]=z.randstZ(" "+wort[4],7);
                                       setBetrag(d);//4

                                       float d1=0;
               						 if(jn==1)d1=d;else d1=d+(float)getSaldo();
               							//Double.parseDouble(wort[5]);
                                       setSaldo(d1);//+Saldo);//5
                                       wort[4]=""+(float)getSaldo();
                                       //wort[5]=z.randstZ(" "+wort[5],10);


                                       //wort[6]=z.randstS(" "+wort[6],6);
                                       //setFahre(wort[6]);

                                       //wort[7]=z.randstZ(wort[7],5);
							gebe=wort[0];			
                             for ( int i =1; i<wort.length; i++){
                                  if (wort[i] != null){
                                      gebe+=","+wort[i];//",\""+wort[i]+"\"";
                                  }
                             }
                             //  System.out.println("\n returwet :"+gebe);
                           //tb.append("\nT "+gebe);
               	
                            return(gebe);
                }

   public String f(String in){
   	double d=0.0;   
   	try{
   		d=Double.parseDouble(in);
   	}catch(Exception e){d=0.0;};
   	return new com.units.MyZahl().deci(d);
   } 	

                public String[] lesen(){ 
                	sucheDate su = new sucheDate("gastro/date/fliste.dat");                	
                    String[] fl=su.myDaten();
                	setSaldo(0);                	
                	for (int i=0;i<fl.length;i++){
                		//System.out.println(fl[i]);
                		if(fl[i]!=null)
                			fl[i]=tokenZeile(fl[i],0);
                	}     
                	 intabele(fl);
                    return (fl);
                }
                public String[]offenerechnung(String str){
                	String[]umsatz=null;
                	String[]numsatz=null;
                	setSaldo(0);
                	try{
                		umsatz=suchen(str);
                		setSaldo(0);
                		numsatz=new String[umsatz.length];
                		int x=0;
                		for(int i=0;i<umsatz.length; i++){
                			if(umsatz[i].indexOf('-')>-1){
                				if(umsatz[i]!=null){
                					numsatz[x]=tokenZeile(""+umsatz[i],0);
                					x++;
                				}
                			}                				
                		}
                	}catch(Exception e){}
                	intabele(numsatz);
                	return numsatz;
                }
                String[]intabele(String[]str){
                	String[] nstr=new String[str.length];
                	 for(int i=0;i<str.length; i++){                	 	
                	 	String[]w=new com.options.ausTeilen().koma(""+str[i]);
                	 	/*str[i]=""+w[0];
                	 	for(int x=1;x<w.length; x++){
                	 		str[i]+=";"+w[x];
                	 	}*/
                	 	w[3]=f(""+w[3]);w[4]=f(""+w[4]);
                	 	nstr[i]=""+w[0];
                	 	for(int x=1;x<w.length; x++){
                	 		nstr[i]+=";"+w[x];
                	 	}
                	 }
                	 tabe =new tabelle(nstr);
                	 tabe.init();
            	   	 tabe.start(); 
                     cp.remove(top);
                	 top.removeAll();
             	     top.add(tabe);//,BorderLayout.CENTER);
                	 cp.add(top); //sud.validate();
           			  validate();
                	return str;
                }                
                private void g1(String[] str){                	
                	MyOp mo = new MyOp(); 
                	Object[] ob ={"Drucken","Betarchten","Abbrechen"};
                	int an = mo.frageO("Liste soll...?",ob);
                	String file="gastro/temp/form01.pre";
                	new save().dontsort(file,str,false);                	                		
                	if(an==0){new Report(file);}else
                	if(an==1){new myTabel(file,true);}
                	//mo.fehler("Aktion ist Abgebrochen!");	
                }

                public String[] suchen(String str){
                 	String[]fl = new sucheDate("gastro/date/fliste.dat").myDaten();                	
                    setSaldo(0);                    
                    tb.append("\nSie suchen nach >"+str);
                	for(int i=0;i<fl.length;i++){
                		if(fl[i].indexOf(str)>-1)fl[i] = tokenZeile(fl[i],0);else fl[i]=null;
                	}               
                    return(rein(fl));
                }
                 public String[] sucheDatum(String str,String date){                 	
                 	//if(date=="")date=getDatum().substring(3);
                    sucheDate su = new sucheDate("gastro/fahre/"+str+"/"+date+".dat");                	
                    String[] fl = su.myDaten();                    
                    setSaldo(0);
                    MyOp mo = new MyOp(); 						                	
                    tb.append("\nSie suchen nach >"+str+"..."+date);
                	for(int i=0;i<fl.length;i++){
                		if(fl[i].indexOf(date)>-1)fl[i] = tokenZeile(fl[i],0);else fl[i]=null;
                	}               
                    return(rein(fl));
                }
                String FahreName(){
                	String[] myliste = new sucheDate("gastro/date/benutzer.dat").myDaten();
                	String str=new Jlist(myliste,false).element();
                	if(str.equals("")){
                		new MyOp().fehler(" Sie sollten Ihre Wahl clicken Bitte! ");
                		str=FahreName();
                	}
                return str;
                }
               
                public String buchen(String mein){
                	MyOp mo = new MyOp();                       	                                	
         			tb.setText("");
            		int x=0;
                	int i=0;
		            String[] v = null;         	
        		 	setSaldo(0);
                	String ein =null;
                	String fu="";
                	String ruckgabe="";                	
         		try{
                	if (mein.equals("")){
            	    	mo.fehler(" Achtung es wird vom konto gebucht! ");
            	    	ein=FahreName();// mo.eingabe("Fahre Name/Code ZB (1)oder (2): ");                		
                	}else ein=mein.trim();
            		String[] v1 =null;         			
         			ruckgabe="Fahrer ("+ein+") hat 0 Bestellung offen";
            		v = zeigeFahre(ein);// wenn die Register vorhanden ist
            		fu=""+Saldo;
            		v1 = offenerechnung("F_"+ein);//nur wenn nicht gebucht ist
            		
            	 if(v[0]!=null){            	
            		String[] v2 = null;
            		for (i=0; i<v.length;i++){
                 		if (v[i]!= null) {x++;tb.append("\n ein "+x+"\t"+v[i]);}
            		}
            		//mo.fehler(x+" Buchungen, Euro "+(float)Saldo);
            		tb.append("\n******** Konto vergleich Korregiert:> Fahre: "+ein);
            	
                	/* gebe offeneposten oder nicht
                	 * wenn ja dann Ã¤ndrer buchbezeichnung und speichere 
                	 * in Kasabuchliste den Fahreumsatz und lÃ¶sche Fahre/*.dat
                	 * in Fahreliste das ganze umbezeichnet
                	 */
                	 x=0;
         			
            			v2= vergleich(v);
            			for (i=0; i<v1.length;i++){            
                 			if (v1[i]!= null) {x++;tb.append("\n vergl>"+x+"\t"+v1[i]);}
            			}   
            			//setSaldo(0);
            	 	if(new chef(getFahre()).test()=="positive"){
            			tb.append("\ngespeichert "+(getXy()+1)+" >"+
            			speichern(tokenZeile(v[getXy()],1),v2));
            			//mo.fehler((getXy()+1)+" Zeile"+" Transfieriert\n"+v[getXy()]);      
            			ruckgabe=(v[getXy()]+"\nTransfieriert\nFahrer ("+getFahre()+") ist mit "+(getXy()+1)+""+
                	        " Bestellung in Wert ("+fu+") verbucht");
            	 	}else mo.fehler("Sie sind nicht berichtigt!");
            	}else mo.fehler(" keine offeneposten Fahrer( "+ein+" )");
         		}catch(Exception e){}
            	return (ruckgabe);
                }
                String[] rein(String[] str){                	
                	int x=0;
                	for(int i=0;i<str.length;i++) if(str[i]!=null)x++;
                	String[]voll =new String[x];x=0;
                	for(int i=0;i<str.length;i++) {
                		if(str[i]!=null){voll[x]=str[i];x++;}
                	}
                	return voll;
                }
                public String[]saveErgebnis(String ein){
                	String[] v = suchen(ein);                		
                	String[]vr=rein(v);
                	new save().dontsort("gastro/fahre/"+ein+"/"+getDatum().substring(3)+".dat",vr,false);
                	return vr;
                }
                public String gesucht(){
                	mo = new MyOp();                                	
         	 		int x=0;
             		//String ein = FahreName();//mo.eingabe("Fahre Name/Code ZB (F_1): ");
                	//setFahre(ein);
             		String[] v =suchen(getFahre()) ;                		
                	new save().filefelde("gastro/fahre/"+getFahre()+
                	                     "/"+getDatum().substring(3)+".dat",rein(v),false);
             		for (int i=0; i<v.length;i++){
                 		if (v[i]!= null) {x++;tb.append("\n S>"+x+"\t"+v[i]);}
             		}
             	//	mo.fehler(x+" Bestellung um Euro "+(float)getSaldo());                	                
                if (x>0)g1(intabele(v));
                return ("Fahrer "+getFahre()+"\nhat bis Heute "+x+" Bestellung um Euro "+f(""+(float)Saldo));
                		
                }
                public void update(String farer){//,String mo,String jahr){
                	String[] v =suchen(farer) ;                	
                	if(v.length>-1){
                		
                	String[]str={"01","02","03","04","05","06","07","08","09","10","11","12"};
                	String[]dir=new ebm.li("gastro/d2?").ordner();                	                	
                	if(dir.length>-1){
                		for(int i=0;i<dir.length;i++){
                			if(dir[i]!=null)dir[i]=dir[i].replace('D','_');                		
                		}
                	}//else dir[0]=new myDatum().J().replace('_','D');                		
                	String[][]suchZiel=new String[dir.length][str.length];
                	for(int zielj=0;zielj<dir.length;zielj++){
                		for(int zielm=0;zielm<str.length;zielm++){
                			suchZiel[zielj][zielm]=""+str[zielm]+dir[zielj];
                		}
                	}                   	
                	
                	String[]moErg=new String[v.length];
                	String fnm="";
                	int x=0;
                	for(int zielj=0;zielj<dir.length;zielj++){
                		String fnj="gastro/fahre/"+farer+"/";                		
                		for(int zielm=0;zielm<str.length;zielm++){                  			                			                	                		
                			fnm=fnj+suchZiel[zielj][zielm]+".dat"; 
                			
                			for(int i=0;i<v.length;i++){
                				if(v[i].indexOf(""+suchZiel[zielj][zielm])>-1){                	
                					moErg[i]=v[i];x++;
                				}else moErg[i]="-";
                			}
                			//-------------***--------------//
                			String[]moErgF=new String[x];
                			x=0;
                			for(int m=0;m<moErg.length;m++){
                				if(moErg[m]!="-"){moErgF[x]=moErg[m];x++;}
                			}
                			//if(moErgF.length>-1 && new ebm.Tm(moErgF.length,fnm).Ergebnis>0)
                			if(moErgF.length>0 && moErgF[0]!=null)new save().dontsort(fnm,moErgF,false);
                			//System.out.println(fnm);                			                			
                		}                		
                	}                  	
                	del_0file("gastro/fahre/"+farer+"/");
                	}
                }
               public String gesuchtMonat(){
                mo = new MyOp();                                	
         	 	int x=0;     
               	saveDir();
               	String[] dir =new sucheDate("gastro/fahre/"+getFahre()+".dir").myDaten();
               	if(dir.length>-1 && dir!=null){;}else dir=saveDir();
               		//dir =new sucheDate("gastro/fahre/"+getFahre()+".dir").myDaten();               	
               	for(int i=0;i<dir.length;i++)dir[i]=dir[i].substring(0,7);//dir[i].length()-4);
               	String date="";
               	if (dir.length>-1)date=new Jlist(dir,false).element();
               	if(date==null || date=="")date=getDatum().substring(3);
             		String[] v =sucheDatum(getFahre(),date);//new myDatum().M()) ;                		
             		for (int i=0; i<v.length;i++){
                 		if (v[i]!= null) {x++;tb.append("\n S>"+x+"\t"+v[i]);}
             		}
             		mo.fehler("im Monat "+date.replace('_','.')+" sind "+x+" Bestellung um Euro "+f(""+(float)getSaldo()));                	                
                if (x>0)g1(intabele(v));
                return ("hat im Monat "+date.replace('_','.')+", "+x+" Bestellung um Euro "+f(""+(float)Saldo));
                		
                }
                
                public String gesuchtHeute(){
                	mo = new MyOp();                                	
         	 		int x=0;//06_2006.dat
                    sucheDate su = new sucheDate("gastro/fahre/"+getFahre()+"/"+new myDatum().M()+".dat");                	
                    String[] fl = su.myDaten();                    
                    setSaldo(0);                    
                	String date=new myDatum().ist();
                    tb.append("\nSie suchen nach >"+getFahre()+"..."+date);
                	for(int i=0;i<fl.length;i++){
                		if(fl[i].indexOf(date)>-1){                			
                			fl[i] = tokenZeile(fl[i],0);
                			tb.append("\n S>"+x+"\t"+fl[i]);
                			x++;
                		}else fl[i]=null;
                	}           		
             		mo.fehler("Heute "+getDatum()+" sind "+x+" Bestellung um Euro "+f(""+(float)getSaldo()));                	                
                if (x>0)g1(intabele(rein(fl)));
                return (" hat Heute "+date.replace('_','.')+", "+x+" Bestellung um Euro "+f(""+(float)getSaldo()));
                		
                }
                 public String Heute(){                	                     	
         	 		int x=0;             	                	                	
                    String[] fl = new sucheDate("gastro/fahre/"+getFahre()+"/"+new myDatum().M()+".dat").myDaten();                    
                    setSaldo(0);                    
                	String date=new myDatum().ist();                    
                	for(int i=0;i<fl.length;i++){
                		if(fl[i].indexOf(date)>-1){                			
                			fl[i] = tokenZeile(fl[i],0);                			
                			x++;
                		}
                	}           		             		
                	return ("Am "+date.replace('_','.')+", "+x+" Bestellung und Umsatz: "+f(""+getSaldo()));           	
                 }
                 void del_0file(String mydir){	
                 	String[]fl = new com.search.sucheDate().dir(mydir);
                 	for(int i=0;i<fl.length;i++){
                 		File f =new File(mydir+fl[i]);
                 		if(f.exists()){
                 			if(f.length()==0){
                 				try{
                 					f.delete();System.out.println(" dir ist ok ");
                 				}catch(SecurityException  ioe){System.err.println(ioe);}
                 			}
                 		}
                 	}
                 }
               public String[]saveDir(){               		   
                	String dirfile="gastro/fahre/"+getFahre()+".dir";                               		
               		del_0file("gastro/fahre/"+getFahre());
                	String[]fl = new ebm.li("gastro/fahre/"+getFahre()).files();               		
               		new save().filefelde(dirfile,fl,false);               		
                	return fl;
                }
               public String Bericht(){
                	String mytext="";
                	String driver = FahreName();setFahre(driver);saveDir();                	
                	int i=0;int vorbest=0;int gesammt=0;float gesumsatz=0;
                	String[] dir =new sucheDate("gastro/fahre/"+getFahre()+".dir").myDaten();
               		if(dir.length==0)dir=saveDir();                		
               	System.out.println(getFahre()+"Nach :"+dir.length);
                	if(dir.length>0){
                		mytext="\n"+driver+" Detail";           
                		for(int d=0;d<dir.length;d++){
                			setSaldo(0);vorbest=0;
                			String myerstzeile=dir[d].substring(0,7);                			
                			String[]fl = new sucheDate("gastro/fahre/"+driver+"/"+myerstzeile+".dat").myDaten();//der file inhalt
                			if(fl.length>0){
                				mytext=mytext+"\nIm "+myerstzeile.replace('_','.')+", ";
                				for(i=0;i<fl.length;i++){
                					if(fl[i].indexOf(dir[d].substring(0,7))>-1){
                						tokenZeile(fl[i],0);
                						vorbest++;
                					}
                				}
                				mytext=mytext+" "+vorbest+" Buchungen erfasst und umsatz: "+f(""+getSaldo());
                				gesammt+=vorbest;
                				gesumsatz+=(float)getSaldo();
                			}
                		}
                		mytext+="\n______________________"+
                		"\nGesammt Buchungen "+gesammt+" und Umsatz: "+f(""+gesumsatz);
                		mytext+="\n"+Heute();
                	}else {
                		mytext="Es sind keine Angaben erfasst versuchen sie mit Update!!";
                	}
               
               	return mytext;
               }
                public void suchAbfrage(){
                	String driver = FahreName();setFahre(driver);
         			String[] ob={"Heute","nach Monat","im Jahr"};         
         			int an= new MyOp().frage1("Suchen Sie im Kartei von ..",ob);
         			if(an==0){saveErgebnis(driver);gesuchtHeute();
         			}else if(an==1){gesuchtMonat();
         			}else if(an==2){gesucht();}
                }

  class service implements ActionListener {
    public void actionPerformed(ActionEvent e) {
      String target = e.getActionCommand();      
      //FL fL = new FL();
      String ein = null;      
      int i = 0;
      int x=0;      
    try{
    	
        tb.setText("");    	
         if (target.equals("Lesen")){              
             String[] v = lesen();          	 
         	intabele(v);
            tb.append("\n"+v.length+" Buchungen Euro "+f(""+(float)getSaldo()));
            mo.fehler(v.length+" Bestellunssg um Euro "+f(""+(float)getSaldo()));
         }
         if (target.equals("Suchen")){         	
         	suchAbfrage();
         }
         // B U C H E N
         if (target.equals("Buchen")){
         	tb.append("\n ist nicht aktive!!");//+buchen(""));
         }
         if (target.equals("updat")){
         	String farer=FahreName();
         	update(farer);
         }
         if (target.equals("Bericht")){
         	//Bericht();
         	tb.append("\n"+Bericht());
         }
     } catch(Exception ioe){mo.fehler(i+" Fehler beim "+target+" Bestellung um Euro "+f(""+(float)getSaldo())+"\n"+ioe);}

    }
  }


  public void init(){                 
       	menus.setMnemonic(KeyEvent.VK_F);
       	 for(int i = 0; i < items.length; i++) {
       	 	items[i].addActionListener(new service());
       	 	menus.add(items[i]);
       	 }
    JMenuBar mb = new JMenuBar();    
  	mb.add(menus);
    setJMenuBar(mb); 
  	tb.setFont(new Font("Courier New",1,13));
       	getContentPane().setLayout(new BorderLayout());
       	JPanel sud = new JPanel ();
       	top.setLayout( new GridLayout(1,0));
       	sud.setLayout( new GridLayout(2,0));
		top.setBorder(new LineBorder(new Color(150,150,150),10,true));//BevelBorder.RAISED));
       	//sud.setBackground(Color.orange);
       	//sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));
  		//d.setBorder(new BevelBorder(BevelBorder.RAISED));
  	 	sud.setBorder(new LineBorder(new Color(140,140,100),10,true));//BevelBorder.RAISED));
       	sud.add(new JScrollPane (tb));
       	JLabel jl = new JLabel("<html><Font Color=blue> <font face=Swis721 BdOul BT> <b><font size=-2>"+
  		                    "<center><i>"+"Fahrer Buch"+"</i></center></b></font></html>",JLabel.CENTER);
       	//sud.add(jl);        
  		
       	String[] v = {"0;0;0;0","0;0;0;0"};
       	tabe=new tabelle(v);  	
       	tabe.init();  		
        tabe.start();                	  	
       	top.add(tabe);               
       	//top.add(tabe);       
        jl.setBorder(new LineBorder(new Color(155,155,155),5,true));
  	sud.add(top);
  		/*cp.add(sud,BorderLayout.PAGE_START);//panel);       	
        cp.add(top,BorderLayout.CENTER);//panel);       	
       	cp.add(jl,BorderLayout.PAGE_END);
       	cp.setVisible(true);*/
       	Dimension s = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
       	top.setBackground(new ebm.myColor("hg").getColor());
  		sud.setBackground(new ebm.myColor("hg").getColor());
 		top.setPreferredSize(new Dimension(s.width-50,s.height/3));
 		sud.setPreferredSize(new Dimension(s.width-50,s.height/3));
  		getContentPane().setBackground(new ebm.myColor("hg").getColor()); 		
 		getContentPane().add(mb,BorderLayout.PAGE_START);//pane
 		getContentPane().add(new ver6.img().label(null,"logo.gif"));//panenew firma().data()
 		//getContentPane().add(sud,BorderLayout.LINE_START); 		    	
        //getContentPane().add(top,BorderLayout.LINE_END);//panel);       	
       	getContentPane().add(sud,BorderLayout.PAGE_END); 		    	
 		
       	getContentPane().setVisible(true);  
  	
  }
  /*public static void main(String[] args){
  	FL fl= new FL();
  	String[] str ={  		
  	"RN07_05/0108,07_05_2003,10200X000707_05,-10.2,0,F_Mourad,-",
  	"RN07_05/0108,07_05_2003,10200X000707_05,-8.4,0,F_Mourad,-",
  	"RN07_05/0108,07_05_2003,10200X000707_05,-18.4,0,F_Mourad,-"};
			  
   //System.out.println(fl.tokenZeile("RN07_05/0108,07_05_2003,10200X000707_05,8.4,0,X,-"));//,10.5,F_Mourad,-"));
   // System.out.println(fl.tokenZeile("RN07_05/0108,07_05_2003,10200X000707_05,18.4,0,X,-"));//,10.5,F_Mourad,-"));
  	//String[] verg= fl.vergleich(str);
  	//for (int i=0; i<str.length;i++)System.out.println(fl.tokenZeile(str[i]));
  	//System.out.println(fl.getSaldo());
  	//System.out.println(verg[i]);
  	
  	//System.out.println(fl.buchen(""));
  //System.out.println(fl.getSaldo());//toString());
  	try{
  	 fl.zeigeFahre("1");
  	 } catch(IOException ioe){System.err.print(ioe+" Bestellung um Euro ");}
  	fl.init();  	
  	fl.setVisible(true);
  	JFrame f = new JFrame("Rechnung");
  	f.setDefaultCloseOperation(2);	
	f.getContentPane().add(fl);	  	
	f.pack();
  	f.setBounds(100, 50, 800, 650);
  	f.setVisible(true); 
  	
    
    Console.run(new FL(), 650, 500);
  }*/

 }



