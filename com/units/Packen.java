// created on 17.12.2010 at 15:55
//remove und katagorieren die dateien

package com.units;
import com.search.*;
import java.io.*;
import java.util.*;
import java.io.File;
import java.security.AccessController;
import java.security.*;

public class Packen{
	String jahr,monat;
	boolean wie;
	public Packen(String Monat,String Jahr){
		jahr=Jahr;
		monat=Monat;
		String s=leseP();
		wie=false;
		if(s.equals("***"))wie=true;
		start();
	}
	void vorbreite(){
		String j="";
		if(jahr.length()>0)j=jahr.substring(2,4);	
		String filename="gastro/d"+jahr+"/fl"+monat+j+"f.dat";				
		String zielordner="gastro/d"+jahr+"/M"+monat+"/";				
		String[]rd=Rdate(filename);
		save(zielordner+"M"+monat+".dat",rd);	
	}
	void start(){
		String j="";				
		if(jahr.length()>0)j=jahr.substring(2,4);			
		String lesordner="gastro/d"+jahr+"/M"+monat+"/";								
		String remordner="gastro/K_r/";
		String zielordner=lesordner+"kr/";
		String dremordner="gastro/date/drucker/";
		String dzielordner="gastro/d"+jahr+"/M"+monat+"/";						
		String file=lesordner+"M"+monat+".dat";
		vorbreite();
		String[]rd=test(file);
		remove(dremordner,dzielordner,rd,wie);
		remove(remordner,zielordner,rd,wie);				
	}
	String[]test(String file){
		String[]d=lese(file);
		return d;
	}
	String[]Rdate(String filename){
		String[]rd=lese(filename);
		String[]daten=new String[rd.length];
			for(int i=0;i<rd.length;i++){
				daten[i]=new com.options.ausTeilen().koma(rd[i])[2]+".dat";
			}				
		return daten;
	}	
	String leseP(){return new com.search.sucheDate("gastro/date/packen.dat").md();}
	String[]lese(String file){return new com.search.sucheDate(file).myDaten();}
	void save(String file,String[]date){
		new com.units.save().dontsort(file,date,false);
	}
	void remove(String remordner,String zielordner,String[]rd,boolean wie){				
		for(int i=0;i<rd.length;i++){
			System.out.println(remordner+rd[i]+", an.. "+zielordner+""+rd[i]+".."+wie);
			new com.units.save().Removeto(remordner+rd[i],zielordner+""+rd[i],wie);
		}		
	}
	public static void main(String[]args){
		if(args.length>0){//java ..Packen 02 2010
			new Packen(args[0],args[1]);
		}else System.out.println("nicht vorhanden!!");
	}
}
