// created on 23.08.2010 at 12:09
//lizCalender.class
//Authur El Bakry Mourad
package ebm;
import java.io.*;
import java.text.*;
import java.util.*;

public class lizCalender{
	Calendar cal;
	Date date;
	SimpleDateFormat df,zf;
	public lizCalender(){		
		install();
		long datum=l(getDatum());
		//long vdatum=l(redDatum());
		long vdatum = new com.search.sucheDate(path+"d.exe").filemodi();
		System.out.println("Heute in Zahlen ist: "+datum+
		                   " in Format ist :"+df.format(datum)+","+zf.format(datum));
		System.out.println("Letzte Datum ist: "+vdatum+
		                  " in Format ist :"+df.format(vdatum)+","+zf.format(vdatum));
		long diff=Diff(vdatum,datum);
		save_liz(path+"a123.dat",""+diff);
		System.out.println("Diff ist: "+diff+" Tage");
	}
	public lizCalender(String feld,String wert){		
		install();
		long Vdatum=l(getDatum());save_liz(path+"j123.dat",""+Vdatum);
		add(i(feld),i(wert));
		long datum=l(getDatum());save_liz(path+"j223.dat",""+datum);		
		System.out.println("nach Add Heute  ist: "+datum+
		                   " in Format ist :"+df.format(datum)+","+zf.format(datum));		
	}
	void install(){
		cal = Calendar.getInstance(Locale.GERMANY);
	    df = new SimpleDateFormat ("dd.MM.yyyy",Locale.GERMANY);
	    zf = new SimpleDateFormat ("HH:mm:ss",Locale.GERMANY);		
		date = new Date();
	}
	long Diff(long v,long n){
		return (v-n)/86242093;
	}
	String getDatum(){
		return ""+cal.getTimeInMillis();
	}
	String redDatum(){
		return ""+new com.search.sucheDate(path+"j223.dat").md();
	}
	void set(int feld,int wert){
		if(feld==1)cal.set(cal.DAY_OF_MONTH,wert);
		if(feld==2)cal.set(cal.MONTH,wert);
		if(feld==3)cal.set(cal.YEAR,wert);	
	}
	void add(int feld,int wert){
		if(feld==1)cal.add(cal.DAY_OF_MONTH,wert);
		if(feld==2)cal.add(cal.MONTH,wert);
		if(feld==3)cal.add(cal.YEAR,wert);	
	}
	long l(String str){
		long modi=0;
		try{
			modi=Long.parseLong(str);
		}catch(Exception ex){System.err.print(ex);modi=0;}
		return modi;
	}
	int i(String str){
		int modi=0;
		try{
			modi=Integer.parseInt(str);
		}catch(Exception ex){System.err.print(ex);modi=0;}
		return modi;
	}
	void save_liz(String file,String str){				
		new com.units.save().file(file,str,false);
	}	
	public static void main(String[] args) {
		new lizCalender();
		new lizCalender(args[0],args[1]);
	}
	String path=new ver6.basic().getlPath();
	
}
