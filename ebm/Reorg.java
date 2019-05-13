

package ebm;

import java.util.*;
import java.io.*;

public class org{
String[]mFiles;
int Bcode;

public org(String file){
	mFiles=lesen(file);
        for(int i=0;i<mFiles.length;i++){
	   String daten=new com.options.ausTeilen().koma(mFiles[i])[1];
	   if(daten.indexOf(".dat")>-1)daten=daten.substring(0,daten.indexOf(".dat"));
	   mFiles[i]=""+neuZeile(""+daten,""+Bcode++);
	}
        save();

}
public void save(){
	new com.units.save().dontsort("N"+file,mFiles,false);
}
String[]lesen(String file){
	return new com.search.sucheDate(file).myDaten();
}
String neuKc(String str){
	int posr=str.toLowerCase().indexOf("x");
	String kc=str.substring(0,posr);				
	return kc;	
}
String neuKz(String str){
	int posr=str.toLowerCase().indexOf("x");
	int posn=str.length();		
	String kz = str.substring(posr+1,posn-6);
	return kz;
}
String neuD(String str){
	int posr=str.toLowerCase().indexOf("x");
	int posn=str.length();		
	String datum = str.substring(posn-6,posn);
	return datum;
}

String neuZeile(String mStr,String code){
        String str="R"+neuD(mStr)+"N"+code+"K"+neuKc(mStr)+"X"+neuKz(mStr)+"E";
return str;
}
}
