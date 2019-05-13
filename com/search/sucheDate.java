//sucheDate
package com.search;
import com.units.*;
import com.options.*;
//import com.display.Console;
//import javax.swing.*;
//import java.awt.*;
//import java.awt.event.*;
import java.util.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;
import java.net.*;
import java.security.*;
import java.security.AccessController;
import java.net.URLStreamHandler;

public class sucheDate{
        static String suchtext ;
        static String filename;
        static int position;
        FilePermission perm ;
   public sucheDate(String gesucht,String fln, int plz){
        suchtext=testchar(gesucht) ;
        filename=new com.units.CF(fln).path;//fln;
        position=plz;       
		myfn();
   		chekfilename();
	//System.out.println("in SucheDate:>> "+filename);

        }       
   public sucheDate(String fln){
    	suchtext="" ;
    	filename=new com.units.CF(fln).path;//fln;
    	position=0;
    	//myfn();
   	//filename=new com.units.CF(filename).path;
    	chekfilename();
	//System.out.println("in SucheDate:>> "+filename);
    }
    public sucheDate(){ }
    void myfn(){
    		filename=new ver6.TC().L(filename);
    	if(filename.indexOf(":\\")>-1 || filename.indexOf(":/")>-1){
    		filename=filename.replace('\\','/');
    	}else filename=new ver6.basic().getPath().replace('\\','/')+filename;
    }
    public static String testchar(String str){
        	if(str.length()>1){        
		 		char c=str.charAt(0);
        		
		 		Character ch=new Character(c);
		 		String ein="";        		
        		if(ch.isLetter(c)){
        			//System.out.println(suchtext+", test:"+str+", char:"+c);
        			if(ch.isLowerCase(c)){
        				c=ch.toUpperCase(c);	ein=c+str.substring(1);str="";str=ein;
        			}	
		 		}
        			
        	}
		 return str;
    }

        // sucht in den Arrayfelder nach den gesuchtentext und übergibt
        // die ergebnisse falls mehr als eins in wahlliste zurück
        //die sucht ganz Zeile oder die Ersten 10 Buchstaben
        public static String wahlListe(){//throws IOException{
            String[] wl = new String[filebig()];
            wl = myDaten();
        	wl=new save().sortliste(myDaten());
        	String erg[] = new String[wl.length];        	
        	int lauf = 0;
        	int x=0;int i=0;
        	MyOp mo=new MyOp();
        	String ergebnis="";String  zeile  = null;
        	//erg[0]=" Leider nicht Vorhanden";
        	try{
                          while (true){
                          	lauf++;
                          	if (position == 0){zeile = ""+wl[lauf].trim();
                          	}else{ zeile = new ausTeilen().komma(wl[lauf])[0];}//.substring(0,suchtext.length());
                          	if (zeile== null) break;
                            int pos = zeile.indexOf(suchtext);
                            if ( pos > -1) {                           
                            //	System.out.println(suchtext+","+zeile.trim()+",");
                            		if(position == 0) {erg[x]=wl[lauf];x++;}else
                            		if(position > 0 && zeile.trim().equals(suchtext.trim())){
                            			erg[x]=wl[lauf];x++;                          		                            			
                            		}
                            }
                          }                       
               } catch(Exception e){System.err.print("");}               
               if (erg[0]== null) {ergebnis = " Leider nicht Vorhanden";}else               
               if (erg[0]!= null && x==1) {ergebnis =erg[0];}else
               if (x>1) {ergebnis = mo.wahl(erg," Die Ergebnise sind < "+x+" > Daten ");}
               //System.out.println("\n"+x+":"+erg[0]+"\n"+ergebnis);
               return(ergebnis);
        }
        //übergibt der gesuchte position an
        public static int ListePos(){
            String[]wl = myDaten();
		int lauf=0;int i=-1;int x=0;
		String[]erg = new String[wl.length];
        	String  zeile  = null;
        	try{
        		while (true){
        			lauf++;
        			if (wl[lauf]== null) break;
        			//System.out.println(wl[lauf]);
        			int pos = wl[lauf].indexOf(suchtext);
        			if ( pos > -1) {
        				String[]wort=new ausTeilen().komma(""+wl[lauf]);
        				String str0=""+wort[0];
        				String str1=""+wort[wort.length-2];
        				String str2=""+wort[wort.length-3];
        				if((str0.trim().equals(suchtext.trim()))||(str1.trim().equals(suchtext.trim()))|| (str2.trim().equals(suchtext.trim())))
        				{
        					i=lauf;
        					erg[x++]=i+","+wl[i];
        					// System.out.println("\nPos: "+i+" ?<"+suchtext+">\n"+wl[i]);
        				}
        			}
        		}        		
               } catch(Exception e){//System.err.print(" wl volum: "+wl.length+" Position:"+i);
               }
               if (x>1)i=pos(new MyOp().wahl(erg," Die Ergebnise sind < "+x+" > Daten "));
               return i;
        }
        public static int pos(String str){
        	String[] wort=new ausTeilen().koma(str);
        	return new Methoden().Int(""+wort[0]);
        }
public static String codgen(String code){//Kunden code Automatisch nach ortschaft Vorkommend
    String[]wl = myDaten();
	int lauf=0;int x=0;
	code=testchar(code) ;
	try{
		while (true){
			lauf++;
			if (wl[lauf]== null) break;
			int pos = wl[lauf].indexOf(code);
			if ( pos > -1) {
				String[] wort=new ausTeilen().koma(""+wl[lauf]);
				String str0=""+wort[4];//Postleitzahl 1180 2500 2514
				if(str0.trim().equals(code.trim())) {x++;}
			}
		}
	} catch(Exception e){System.err.print(" Codegen: "+wl.length+" X:"+x);}
	String  str=code;//0 2514 length=0 bis 3	
	String str1=""+(x+1);//0 1 11 100 999 length=0 bis 2
	int m=str1.length();
	int def=3-m;//1 oder 12 oder 120
	if(def>0){
		for(int l=code.length();l<(code.length()+def);l++)str=str+"0";
	}
	str+=str1;
	return str;
}


	//gebe position um den Array zu nullieren-kellner tclistechange
	//Suchmachiene die löscht wenn es gefunden ist
	public static String[] wahl(){
            String[] wl = myDaten();        			
        	int lauf = 0;        	        	
        	String zeile  = null;
        	try{
                          while (true){                          	
                          	zeile = ""+wl[lauf];
                          	if (zeile == null) break;
                          	int pos = zeile.indexOf(suchtext);
							if ( pos > -1) {wl[lauf]=null;}
                            lauf++;
                          }                       
               } catch(Exception e){System.err.print("");}
               return wl;
	}
	public String Wcode(String code){		
		ausTeilen a= new ausTeilen();
		String[] wl=myDaten();
		String ergeb="";
	//	int derCode=Integer.parseInt(code);				
		for(int i=0;i<wl.length;i++){
			String[]wort = a.koma(""+wl[i]);
			String testCode=""+wort[0];//Integer.parseInt(wort[0]);				
			if(code.equals(testCode))ergeb=""+wl[i];
		}
		return ergeb;
		
	}
	//Suchmaschiene die nur eine zeile übergibt wenn vorhanden oder Leider nicht vorhanden
	//die sucht nur die ersten 5 Buchstaben oder die ganz Zeile
	//Ergebnis ist arrayliste zum wahl(Mouseklick)
	public static String wahlware(){
            String[] wl = new String[filebig()];
            wl=new save().sortliste(myDaten());
        	String erg[] = new String[wl.length];        	
        	int lauf = 0;
        	int x=0;
        	MyOp mo=new MyOp();
        	String ergebnis, zeile  = null;
        	try{
                          while (true){
                          	lauf++;
                          	if (position == 0){
                                zeile = ""+wl[lauf].trim();                          		
                          	}else zeile = wl[lauf].substring(0,5);//wl[lauf].length());
                          	if (zeile== null) break;
                            int pos = zeile.indexOf(suchtext);
                              if ( pos > -1) {
                               erg[x++]=wl[lauf];
                               }                           
                          }                       
               } catch(Exception e){//System.err.print(" ww = "+erg.length);
               }
               if (erg[0]== null) {
               	   return(" Leider nicht Vorhanden");
               }else 
               	if (x>1) {
                   return(ergebnis = mo.wahl(erg," Die Ergebnise sind < "+x+" > Daten ")); 
               }else return(""+erg[0]);
	}
	public static String[] wahlinarrayRC(){
		ausTeilen a= new ausTeilen();
		String[] wl=myDaten();
		List<String> myErg=new ArrayList<String>();			
		for(int i=0;i<wl.length;i++){
			String[]wort = a.koma(""+wl[i]);			
			String testCode=""+wort[0];//Integer.parseInt(wort[0]);							
            if ( testCode.indexOf(suchtext)> -1) {
				myErg.add(""+wort[0]+","+wort[1]+","+wort[2]);
            }
		}
		String[]cmd = new String[myErg.size()];
		for (int i = 0; i < cmd.length; i++)
			cmd[i] = (String) myErg.get(i);
		return cmd;
	}
	public static String[] wahlinarrayJL(){
		ausTeilen a= new ausTeilen();
		String[] wl=myDaten();
		List<String> myErg=new ArrayList<String>();
		for(int i=0;i<wl.length;i++){
			String testCode=""+wl[i];//Integer.parseInt(wort[0]);							
            if ( testCode.indexOf(suchtext)> -1) {
				myErg.add(""+wl[i]);
            }
		}
		String[]cmd = new String[myErg.size()];
		for (int i = 0; i < cmd.length; i++)
			cmd[i] = myErg.get(i);
		return cmd;
	}

	public String url(){		
	 String ur="nicht gefunden";
		try{//URLStreamHandler handler= new handler(createURLStreamHandler("http") );			
			URL u = new URL("file://",// //c|/ebmgastro/Gastro/gastro",
			                "net.www.protocol.http.Handler",//"local",
1030//900,int port,		
,"protocol"//filename//String file
           );
			ur=u.toString();
			//URLStreamHandlerFactory fac=new URLStreamHandlerFactory(createURLStreamHandler("http");
			//u.setURLStreamHandlerFactory(fac);			
			/*
			setURL(u,"file://",//String protocol,
                      "hostname",//String host,
                      1080,//int port,
                      "598",//String authority,
                      "ok",//String userInfo,
                      f.getAbsolutePath(),//String path,
                      f.getAbsolutePath(),//String query,
                      f.getAbsolutePath());//String ref)
			//	int i=u.getDefaultPort();
			//System.out.println("\n us.getDefaultPort(): "+i);		
    		//throws MalformedURLException
    		*/
			System.out.println("\n Url: "+u.getFile()+"getUserInfo: "+u.getUserInfo());
		}catch(MalformedURLException mu){System.err.println(mu);}
		return ur;
	}
	public String[]dir(){
		return new ebm.li(filename).ordner();
	}
	public String[]dir(String file){
		//file=new ver6.basic().getPath()+file;	
		file=new ver6.TC().L(file);
		file=new com.units.CF(file).path;//fln;
		return new ebm.li(file).ordner();
	}
	
	public void chekfilename(){
		String ein=filename.replace('*','?');
		ein.replace('+','?');ein.replace('-','?');		
		SecurityManager sm = System.getSecurityManager();
		if (sm != null) {
			sm.checkPermission(new com.security.myPerm(filename));
		}
		new ebm.li(ein);
	}
	public long filelength(){
		File f =new File(filename);		
		long i=0;		
		if(f.isFile())i = f.length();else i=0;
		return i;
	}
	public long filemodi(){
		File f =new File(filename);		
		long i=0;		
		if(f.isFile())i = f.lastModified();else i=0;
		return i;
	}
	public static int zeilenZahl(){
		int i=0;
		try{
			FileReader dateiName = new FileReader(filename);			
            BufferedReader eingabe = new BufferedReader(dateiName);        		
			LineNumberReader lr =new LineNumberReader(eingabe);		
			while (true){  
				String zeile=lr.readLine();
				i=lr.getLineNumber();
				//System.out.println(i+": "+zeile);
				if (zeile== null) break;
			}
            eingabe.close();
		} catch(Exception e){System.err.println("");}
		return i;
	}
	public static String[] zeileninhalt(){
		int i=0;
		String[] str=null;
		try{
			FileReader dateiName = new FileReader(filename);			
            BufferedReader eingabe = new BufferedReader(dateiName);        		
			LineNumberReader lr =new LineNumberReader(eingabe);		
			while (true){  
				String zeile=lr.readLine();			
				i=lr.getLineNumber();
				str[i]=zeile;
				
				//System.out.println(i+": "+zeile);
				if (zeile== null) break;
			}
            eingabe.close();
		} catch(Exception e){System.err.println("");}
		return str;
	}
	// ermittelt die vorhandenen Datensätze und übergibt den Array feldgröße
	//als vorbereitung für unbeschränkten Array
	//die zählung beginnt mit 0, und gibt länge > filelänge um 1
	public static int filebig(){//throws IOException{
                String zeile=null;
                int lauf = 0;	 					
		        try{
                		FileReader dateiName = new FileReader(filename);
                		BufferedReader eingabe = new BufferedReader(dateiName);        
                          while (true){                          	
                          	zeile = eingabe.readLine();                          	                          	
                          	if(zeile== null) break;else lauf++;                                                      	
                          }
                          eingabe.close();
                 } catch(Exception e){System.err.print(" fbig = "+lauf);}
          return(lauf+1);
	}
	// leset einen Datenfile alle zeilen durch und übergibt sie in Arrayfelder ein
	//ermöglicht eine unbeschränkte Arrayliste, da die Liste sogross wie den File
	public static String[] myDaten(){
		String[] md = new String[zeilenZahl()];
        MyOp mo=new MyOp();
		String zeile;//=null;
        int lauf = 0;
        int  x = 0;
		try{
			FileReader dateiName = new FileReader(filename);
            BufferedReader eingabe = new BufferedReader(dateiName);                      
            while (true){
            	zeile= eingabe.readLine(); 
                if (zeile== null) break;    
                if (lauf< md.length && zeile!=null) md[lauf++]= zeile;
            }
            eingabe.close();                       	
		} catch(Exception e){System.err.print(" myDate= "+md.length);}
     return(md);
	}
	//wandelt eine Arrayliste in Unter Arrayliste - für Tabellendruck notwendig
	public static String[][] teiledaten(){		
		ausTeilen a= new ausTeilen();
		String[] wl=myDaten();
		String[][] md =null;
	 if(wl.length>0){
		md = new String[wl.length][a.koma(""+wl[0]).length];        
		String zeile="";//=null;        
           for(int lauf=0;lauf<md.length;lauf++){            	
               zeile = ""+wl[lauf];            
            	if (zeile!=null){             		
            		String[]wort = a.koma(zeile);
            		//System.out.println("\n>:"+wort.length);
            		for (int x=0;x<wort.length;x++){
            			md[lauf][x]=wort[x];
            		}            		
            	}
            }            		
	 }else {System.out.println("Kartei ist Leer!");//new MyOp().fehler("Kartei ist LEEEEEER !");
	 	md=new String[0][0];}
     return(md);
	}
	public static String[][] teiledaten(String trene){		
		ausTeilen a= new ausTeilen();
		String[] wl=myDaten();
		String[][] md=null;
		if(wl.length>0){
		md = new String[wl.length][a.koma(""+wl[0]).length];        
		String zeile="";//=null;        
           for(int lauf=0;lauf<md.length;lauf++){            	
               zeile = ""+wl[lauf];            
            	if (zeile!=null){             		
            		String[] wort = a.koma(zeile);
            		//.println("\n>:"+wort.length);
            		for (int x=0;x<wort.length;x++){
            			md[lauf][x]=wort[x];
            		}            		
            	}
            }            		
		}else {new MyOp().fehler("Kartei ist LEEEEEER !");md=new String[0][0];}
     return(md);
	}
	public static String[][] teiledaten_sort(){		
		ausTeilen a= new ausTeilen();
		String[] wl=myDaten();
		Arrays.sort(wl);
		String[][] md=null;
		if(wl.length>0){
		md = new String[wl.length][a.koma(""+wl[0]).length];        
		String zeile;//=null;        
           for(int lauf=0;lauf<md.length;lauf++){            	
               zeile = ""+wl[lauf];            
            	if (zeile!=null){             		
            		String[] wort = a.koma(zeile);
            		//.println("\n>:"+wort.length);
            		for (int x=0;x<wort.length;x++){
            			md[lauf][x]=wort[x];
            		}            		
            	}
            }            		
		}else {new MyOp().fehler("Kartei ist LEEEEEER !");md=new String[0][0];}
     return(md);
	}
	//gibt nur eine zeile zurück ZB Datum
	public static String md(){		
		String zeile="";//=null;
		String erg="";
		try{
			FileReader dateiName = new FileReader(filename);
            BufferedReader eingabe = new BufferedReader(dateiName);                      
            while (true){            	
            	zeile= eingabe.readLine();             	
                if (zeile== null) break;else erg = zeile;
            }
            eingabe.close();                       	
		} catch(Exception e){System.err.print("");}		
     return(erg);
	}

 
 }
