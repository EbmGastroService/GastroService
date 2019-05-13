// created on 03.11.2004 at 21:41
package com.units;
import com.options.MyOp;
import com.search.*;
public class Umsatz{
	String path,file;
	public Umsatz(String path,String file){
		this.path=new com.units.CF(path).path;
		this.file=file;
		open_Umsatz();
	}
	
	 public String open_kumsatz(String[][] str){    	    	
    	double[] ns=new double[20];
	 	String ausgabe="";
	 	if(str.length>0 && str!=null){
    	for(int i=0;i<ns.length;i++)ns[i]=0;       		
    	for(int i=0;i<str.length;i++){       		
    		int cod=Integer.parseInt(str[i][0].trim());
    		ns[cod]=Double.parseDouble(""+str[i][1].trim());    		
    	}   	    
    	double kum=0;
	 	ausgabe="Kellner: ";
    	for(int i=0;i<ns.length;i++){
    		if(ns[i]>0){
    		  	ausgabe=ausgabe+ "\n["+i+"]	Umsatz:		 "+new Methoden().f(""+ns[i]);
    			kum=kum+ns[i];
    		}
    	}    	
    	ausgabe=ausgabe+ "\n___________________________________________"+
    	"\n Total:			 "+new Methoden().f(""+kum);
	 	}else ausgabe ="keine Daten gefunden!";
    	return ausgabe;
    }
    	
    public String open_Umsatz(){    	
    	String filename = path+file+".dat";
    	//System.out.println(filename);
    	String text="";
    	sucheDate su =new sucheDate(filename);                	    	
    	if(su.filelength()>0){
		String[][] ums = su.teiledaten();    	
    	double kum=0;
    //	String text="";
    	//if(ums.length>0){
    	if(file.charAt(0)=='M'||file.charAt(0)=='m'){
    		kum=0;
    	for(int i=0;i<ums.length;i++)kum=kum+Double.parseDouble(""+ums[i][1].trim());    	
    		text="Monat "+file.substring(1,file.length()).replace('_','.')+
    		"\n Total: "+new Methoden().f(""+kum);
    	}else 
    	if(file.charAt(0)=='k'||file.charAt(0)=='K'){
    		kum=0;
    		for(int i=0;i<ums.length;i++)kum=kum+Double.parseDouble(""+ums[i][1].trim());    	
    		text=" Kellner ("+file.substring(1,file.length()).replace('_','.')+
    		                ")\n Umsatz: "+new Methoden().f(""+kum);
    	}else if(file.charAt(0)=='U' || file.charAt(0)=='u'){
    		kum=0;
    		text="Umsatz "+file.substring(1,file.length()).replace('_','.')+"\n"+open_kumsatz(ums);
    		//text=" Umsatz "+file.substring(1,file.length())+ "\n Total: ";
    	}else if(file.charAt(0)=='W' || file.charAt(0)=='w' || file.charAt(0)=='T'){
    		int zahl=0;
    		kum=0;
    			for(int i=0;i<ums.length;i++){kum=kum+Double.parseDouble(""+ums[i][4].trim());
    				zahl=zahl+Integer.parseInt(""+ums[i][0].trim());    				
    			}
    			String f_text="";
    			if(file.charAt(0)=='W' || file.charAt(0)=='T')
    				f_text=file.substring(2,file.length()).replace('_','.');    		
    		else f_text=file;
    			text="Absatz "+f_text+" ("+ zahl+
    			") Artikeln, Total: "+new Methoden().f(""+kum);
    	}
    	}//else text="\n Umsatz "+file.substring(1,file.length()).replace('_','.')+" ist geleert!\n";
    //		else new com.options.MyOp().fehler("Kartei ist leer!");
    	else text="keine Daten gefunden!";
		return text;
    }
    
}
