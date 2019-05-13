// teilt zeilen in Array ob es mit Komma oder Leer zeichen Escap
// übergibt möglichst genauer Arraylänge
// mit 0 Zählung oder mit 1 starten
package com.options;
import java.io.*;
import java.util.*;


public class ausTeilen{
				//prüft ob komma oder leerezeichen Vorhanden und stellt die suche ein
				//mit 1 startet,0=null,ende !=null
               public String[] zele(String str){
                              int i=0;
               //	String[] mystr=null;
               //	if (str.length()> 10){
                              String[] gebe = new String[30];
                              StringTokenizer st;
               				  String test = str.substring(0,10)	;
                              int pos = test.indexOf(',');
                              if (pos>-1){
                                st = new StringTokenizer(str,",");
                                }
                                else st = new StringTokenizer(str);

                            while(st.hasMoreTokens()){                              
                               gebe[i] = st.nextToken();
                            	 i++;
                             }
                             String[] gebekurtz = new String[i];
               					for(int l=0; l<i; l++)gebekurtz[l] = gebe[l].trim();
                             //Table mytable = new Table(gebe);
                            return(gebekurtz);
              	// 	}else return mystr;
                }
                //setzt ein Array zum komma Zeile zusammen
                public String binde(String[] str, boolean  koma){
                	String gebunden="";
                	String trene=" ";
                	if(koma==true)trene=",";
                	for(int i=0; i<str.length; i++)
                	 gebunden= gebunden+trene+str[i];
                	return gebunden;
                }
                //setzt ein Array zum komma Zeile zusammen
                //über gibt ein Array zurück
                public String[] binde2(String[][] str, boolean  koma){
                	String[] gebunden = new String[str.length];
                	String trene=" ";
                	if(koma==true)trene=",";
                	for(int i=0; i<str.length; i++){
                		String mys="";
                		mys=str[i][0];
                		for(int x=1; x<str[0].length; x++){
                			mys=mys+trene+str[i][x];
                		}
                		gebunden[i]=mys;
                	}
                	
                	return gebunden;
                }
                //prüft ob komma oder leerezeichen Vorhanden und stellt die suche ein
				//mit 1 startet,0=null,ende = null
                public static String[] zeile(String str){
                              int i=0;
               	//if (str!=null && str.length()>10){
                              String[] gebe = new String[30];
                              StringTokenizer st;
               	//			  String test = str.substring(0,10)	;
                //              int pos = test.indexOf(',');
                //              if (pos>-1){
                                st = new StringTokenizer(str,",");
                //                }
                //                else st = new StringTokenizer(str);

                            while(st.hasMoreTokens()){                              
                            	i++;
                               gebe[i] = st.nextToken();
                            	 
                             }
                             String[] gebekurtz = new String[i+1];
               					for(int l=0; l<i+1; l++)gebekurtz[l] = gebe[l];
                             //Table mytable = new Table(gebe);
                            return(gebekurtz);
              // 	}else return new String[0];
                }
   public String[] koma(String input) {
	List<String> v = new ArrayList<String>(10);
	StringTokenizer t = new StringTokenizer(input,",");
	String cmd[];
	while (t.hasMoreTokens())
	    v.add(t.nextToken());
	cmd = new String[v.size()];
	for (int i = 0; i < cmd.length; i++)
	    cmd[i] = (String) v.get(i);

	return cmd;
    }

                //prüft ob komma Vorhanden und stellt die suche ein
				//mit 0 startet,ende = null
                public static String[] komma(String str){
                   int i=0;               	
                   String[] gebe = new String[30];
                   StringTokenizer st;               				  
                   st = new StringTokenizer(str,","); 
               		while(st.hasMoreTokens()){                        
               			i++;
                        gebe[i] = st.nextToken();               			
               		}
                    String[] gebekurtz = new String[i+1];
                	int x=0;
                		for(int l=0; l<i+1; l++){x++;gebekurtz[l] = gebe[x];}
                   return(gebekurtz);
                }
               
                //prüft ob (,) oder ergend welche Trene (;) Vorhanden und stellt die suche ein
				//mit 0 startet,ende = null
                public static String[] komma(String str,String trene){
                   int i=0;               	
                   String[] gebe = new String[30];
                   StringTokenizer st;               				  
                   st = new StringTokenizer(str,trene); 
               		while(st.hasMoreTokens()){                        
               			i++;//Beginnen mit 1
                        gebe[i] = st.nextToken();               			
               		}
                    String[] gebekurtz = new String[i+1];
                	int x=0;
                		for(int l=0; l<i+1; l++){x++;gebekurtz[l] = gebe[x];}
                   return(gebekurtz);
                }
                
                //stellt ein Stringlänge mit xxxxxxx+String für Zahlenpositionierung
                public static String randstZ(String s,int x){                	
                	int len=s.length();	
                	int y=0;	
                	char[] nstr=new char[x];
                	String mystr="";
                	if(x>len)y = x-len;else y=nstr.length;
                	int i=0;
                		for (i = 0 ;i<y; i++){
                        	//mystr=mystr+" ";//str.append(" ");
                			nstr[i]=' ';
                		}          
                		int m=0;
                		for ( i=y;i<nstr.length; i++){
                        	//mystr=mystr+" ";//str.append(" ");
                			nstr[i]=s.charAt(m);m++;
                		}                	
                		s=new String(nstr);
                    return s;//=mystr+s;
                	
                }
                //stellt ein Stringlänge mit String+xxxxxxxxxxxx für Bezeichnungpositionierung
                public static String randstS(String s,int x){                                        	
                	StringBuffer str = new StringBuffer();
                	str = str.append(s);//.trim());
                	if(s.length()>x){
                		str = str.delete(x,str.length());
                	}
                	else{
                    	for (int i =s.length() ; i<x; i++){
                      		str = str.append(' ');
                    	}   
                	}
                    String ns = str.toString();
                	//System.out.println(ns);
                    return ns;
                }


}
