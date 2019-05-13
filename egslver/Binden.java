// created on 24.03.2009 at 18:13
// Authur Mourad El Bakry
package ver6;
import java.util.*;
public class Binden{
	String[]dbAlt1,dbAlt2;
	
	public Binden(){
		dbAlt1=open("gastro/date/kliste.dat");
		dbAlt2=open("gastro/date/Wkiste1.dat");		
		save("gastro/date/Wkiste2.dat",binde());
	}
	String[]binde(){				
		java.util.List<String>mv=new java.util.ArrayList<String>();
		for(int i=0;i<dbAlt1.length;i++){
			String s1=new com.options.ausTeilen().koma(dbAlt1[i])[0];
			String s2=data(s1);
			if(s2.length()>0)mv.add(s2+","+dbAlt1[i]);else mv.add("00,"+dbAlt1[i]);
		}
		String[]nstr=new String[mv.size()];
    	for(int i=0;i<mv.size();i++){
    		nstr[i]=mv.get(i).toString();    
    	}
    	return nstr;
	}
	String data(String str){//suche kundennummer in dbAlt1
	String gefunden="";
		for(int i=0;i<dbAlt2.length;i++){
			String[] test=new com.options.ausTeilen().koma(dbAlt2[i]);
			if(test[0].equals(str)){gefunden=test[1];}
		}
		return gefunden;
	}
	void save(String fn,String[]str){
		com.units.save save=new com.units.save();
		Arrays.sort(str);
		save.file(fn,"",false);
		for(int i=0;i<str.length;i++)save.file(fn,str[i],true);
	}
	String[] open(String file){
		String[]str=new com.search.sucheDate(file).myDaten();
		return str;
	}
		public static void main(String[] args) {
			new Binden();
		}
}
