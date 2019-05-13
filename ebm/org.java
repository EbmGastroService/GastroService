package ebm;
import java.util.*;
import java.io.*;

public class org{
String[]mFiles;
int Bcode;

public org(String file,int Bcode){
	this.Bcode=Bcode;
	mFiles=lesen(file);		
	for(int i=0;i<mFiles.length;i++){//=+2){
        	String[]daten=new com.options.ausTeilen().koma(mFiles[i]);        
        	String date=daten[1];
        	String datum=daten[0].replace('.','_');
        	if(date.indexOf(".dat")>-1)date=date.substring(0,date.indexOf(".dat"));
        	Bcode++;        	
        	mFiles[i]=""+neuZeile(""+date,""+Bcode);        	
        	new NBR(mFiles[i],""+daten[1].toUpperCase(),datum);
        }
        save(file);
}
public org(String file,int Bcode,int def){
	this.Bcode=Bcode;
	mFiles=lesen(file);		
	for(int i=0;i<mFiles.length;i=i+def){//=+2){
        	String[]daten=new com.options.ausTeilen().koma(mFiles[i]);        
        	String date=daten[1];
        	String datum=daten[0].replace('.','_');
        	if(date.indexOf(".dat")>-1)date=date.substring(0,date.indexOf(".dat"));
        	Bcode++;        	
        	mFiles[i]=""+neuZeile(""+date,""+Bcode);        	
        	new NBR(mFiles[i],""+daten[1].toUpperCase(),datum);
        }
        save(file);
}
String[]rein(String[]v){
List<String> m=new ArrayList<String>();
for(int i= 0;i<v.length;i++){
	if(v[i].indexOf(".dat")>-1){}else m.add(v[i]);
}
	String[]str=new String[m.size()];
		for(int i= 0;i<m.size();i++)
			str[i]=""+m.get(i);
return str;	

}

void save(String file){
	new com.units.save().dontsort(file,rein(mFiles),false);
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
	int posn=str.length();		
	String datum = str.substring(posn-6,posn);
	return datum;
}
String neuZeile(String mStr,String code){
        String str="R"+neuD(mStr)+"N"+code+"K"+neuKc(mStr)+"X"+neuKz(mStr)+"E";
return str;
}
}
