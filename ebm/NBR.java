// created on 15.04.2009 at 21:09
package ebm;
import java.util.*;
import java.io.*;

public class NBR{
	String[]R;//RN220209/7591,22_02_2009,2513255X15220209,12.1,12.1,F_Chef,+
	float total;
	public NBR(String file,String zeile,String datum){
		total=lesef();
		R=lese(zeile);
		String bet=brechne(R);
		//R010209N6985K0000X08320E  0000x08320010209.dat
		String RN=file.substring(1,file.indexOf("K"));
		RN=RN.replace('N','/');
		total=total+f(bet);
		new com.units.save().file("gastro/tot.dat",""+total,false);
		String Nzeile="RN"+RN+","+datum+","+zeile.substring(0,zeile.length()-4)+","+bet+","+""+total+",F_Chef,+";
		save(Nzeile,RN.substring(2,6));
		
	}
void save(String str,String file){
	new com.units.save().file("gastro/kbn"+file+".dat",str,true);
}
	String brechne(String[]str){
		float tot=0;
		for(int i=0;i<str.length;i++){
			String w=new com.options.ausTeilen().koma(str[i])[4];
			tot=tot+f(w);
		}		
		return ""+tot;
	}
	float f(String str){
		float n=0;
		try{
			n=Float.parseFloat(str);
		}catch(Exception ne){n=0;}
		return n;
	}
	String[]lese(String file){
		return new com.search.sucheDate("gastro/date/drucker/"+file).myDaten();
	}
	float lesef(){
		String str=new com.search.sucheDate("gastro/tot.dat").md();
		return f(str);
	}

}
