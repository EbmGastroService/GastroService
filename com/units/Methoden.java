// created on 20.06.2003 at 11:59 
//Autor Mourad El bakry
//Methoden Sammlung für Kellner Klassen
package com.units;
import com.options.*;
import com.units.*;
import com.display.*;
import java.applet.Applet;

import java.awt.event.*;
import java.lang.reflect.*;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Methoden {
	
	String[]tf;
	String[] ConnectOptionNames;
	String[] str = {"OK","Abbrechen"};
	JPanel connectionPanel;
	JTextField apwf,npwf,bf;	
	ausTeilen a= new ausTeilen();
	public Methoden(){
		ConnectOptionNames = str;
		tf = new String[3];
		connectionPanel = new JPanel(false);
		connectionPanel.setLayout(new BoxLayout(connectionPanel,BoxLayout.X_AXIS));		
	}
	public String elementieren(String str,int pos){
		ausTeilen a= new ausTeilen();
		String nzeile="";
		String[] wort=a.zele(str);
		wort[pos]=a.randstS(""+wort[pos].trim(),24);
		for(int i = 0;i<wort.length;i++){			
			if(nichtnull(wort[i])) nzeile = nzeile+"  "+wort[i];
		}
		return nzeile;			
	}
	//übergebe ein String Array neuer Länge zurück
	public int datalength(String[] inhalt){
		int x=0;
		for(int i=0;i<inhalt.length;i++){if (nichtnull(inhalt[i])){x++;}}
		return x;
	}
	//übergebe ein Integer Array neuer Länge zurück
	public int datalength(int[] inhalt){
		int x=0;
		for(int i=0;i<inhalt.length;i++){if (inhalt[i]>0){x++;}	}
		return x;
	}
	//String zur float wert
    public float flo(String str){
    	return Float.parseFloat(str);
    }
    //formatzahl 000.00
	public String f(String str){
    	return new MyZahl().deci(Double.parseDouble(str));
    }
    //format zahl integer 01 länge 2
    public String fi(String str){
    	return new MyZahl().g(str,2);
    }
    public boolean nichtnull (String str){
    	if(str != null && str != "")return true;
    	else return false;
    }
    public boolean zahl(String str){
    	boolean b= true;   	    	
    	int i=0;
    	while(i<str.length()){    	    		
    		if(str.charAt(i)>'9'){     			
    			System.out.println("Fehler at :"+(i)+" es ist false bei char");
    			new MyOp().fehler(str +"\nhat Fehler at :"+(i)+"\n ist falsche Zeichenen! ("+
    			                         str.charAt(i)+")\nSie mussen Zahlenwerte eingeben!");
    			b = false;
    			break;    			
    		}i++;
    	}    	   		
    	return b;
    }
    public String chekzahl(String str){
    	String mstr="";
    	if(zahl(str)){mstr=str;}
    	else mstr="0";//if(!zahl(str)){mstr="";}
    	return mstr;
    }
    public int Int(String str){    	    	    
    	int i=0;
    	if (nichtnull(str)&& chekzahl(str) != "0" ){
    		try{i=Integer.parseInt(str);}catch(Exception e){i=0;}
    	}
	return i;
	}
	public String ohnezero(String str){
		String nstr="";		
		int i=0;
		int m=str.length();
		int x=0;
		if(str.charAt(0)=='0')x=1;
		if(str.charAt(0)=='0'&& str.charAt(1)=='0')x=2;
	//	if(str.charAt(0)=='0'&& str.charAt(1)=='0' && str.charAt(2)=='0')x=3;
		
		
		for ( i=x; i<m;i++ ){			
			nstr=nstr+str.charAt(i);		
		}
		nstr=a.randstZ(nstr,6);
		return nstr;
	}
	//umvandelt eine kommazeile in spaltenzeile
	public String elementieren(String str){		
		String nzeile="";
		String[] wort=a.komma(str);
		wort[3]=a.randstS(""+wort[3].trim(),25);
		for(int i = 0;i<wort.length;i++){			
			if(nichtnull(wort[i])) nzeile = nzeile+"  "+wort[i];
		}
		return nzeile;			
	}
    /**
     * Creates the connectionPanel, which will contain all the fields for
     * the connection information.
     */
    public String[] okDialog() {
 	// Create the labels and text fields.	
	JLabel apwl = new JLabel("alte Password: ", JLabel.RIGHT);
	apwf = new JTextField("",20);

    JLabel  npwl = new JLabel("neue Password: ", JLabel.RIGHT);
	npwf= new JTextField("",20);

	JLabel bl = new JLabel("Bestaetige PW: ", JLabel.RIGHT);
	bf = new JTextField("",20);    	
	JPanel namePanel = new JPanel(false);
	namePanel.setLayout(new GridLayout(0, 1));
	namePanel.add(apwl);
	namePanel.add(npwl);
	namePanel.add(bl);
    	
	JPanel fieldPanel = new JPanel(false);
	fieldPanel.setLayout(new GridLayout(0, 1));
	fieldPanel.add(apwf);
	fieldPanel.add(npwf);
    fieldPanel.add(bf);

	connectionPanel.add(namePanel);
	connectionPanel.add(fieldPanel);            
	if(JOptionPane.showOptionDialog(null,connectionPanel, "neue Password erstellen",
		   JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                   null, ConnectOptionNames, ConnectOptionNames[0]) == 0) {	    	                   	
    tf[0]=apwf.getText();
    tf[1]=npwf.getText();
    tf[2]=bf.getText();
    }   
    return tf;
    }

 	public static void main(String[] args) {
 		Methoden m=new Methoden(); 		
 		System.out.println("-954100 "+m.zahl("-954100"));
 		System.out.println("52500 "+m.zahl("52500"));
 		System.out.println("f "+m.zahl("f"));
 		
 		System.out.println("-521999 "+m.chekzahl("-5251999"));
 		System.out.println("d525f1999 "+m.chekzahl("d525f1999"));
 		System.out.println("-5251999 "+m.nichtnull("-5251999"));
 		System.out.println("null "+m.nichtnull(""));
 		System.out.println("Int -5251999 "+m.Int("-5251999"));
 		
 		
 		//String str=new MyOp().eingabe("Code");
 		//System.out.println(str+"> "+m.Int(str)); 		
 		System.out.println("052,20> "+m.ohnezero("052,20")); 		
 		System.out.println("002,20> "+m.ohnezero("002,20")); 		
 		System.out.println("012,20> "+m.ohnezero("012,20")); 		
 		System.out.println("1 1 1 Mourad 12.20    25.50\n"+
 		                   m.elementieren("0,1,154,Mourad in schlafanzug mit gemüse,12.20,25.50",3));
 		
	 	//System.out.println(m.Int("5f"));
	 	//System.out.println(m.Int("500"));
 	}
 
}
