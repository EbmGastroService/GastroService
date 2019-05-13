// created on 18.10.2004 at 00:18
package com.units;
import com.options.ausTeilen;
import com.options.MyOp;
import com.display.textPane;
public class Storno{		
	 //dient um Mengen zur verändern .
	//durch den Argoment str (zeile vom Tisch) kann die Menge und imfolge auch die Wert
	//veandert.Über eine TextPane() kann bedient werden.
	public String zeile(String str){
		ausTeilen a=new ausTeilen();
		String[]wort = a.koma(str);
		String[]nwort ={wort[0],wort[1],wort[2],wort[3],wort[4]};		
		nwort=new textPane(nwort).worte();
		int menge=Integer.parseInt(nwort[1])-Integer.parseInt(wort[1]);				
		double betrag=Integer.parseInt(nwort[1])*Double.parseDouble(""+nwort[3]);
		String nstr=(nwort[0]+","+nwort[1]+","+nwort[2]+","+nwort[3]+","+(float)betrag);		
		new MyOp().fehler("Sie haben geaendert alt \n"+str+"\n in neue \n"+nstr);
		return nstr;
	}	
	public String[] zeileA(String str){
		ausTeilen a=new ausTeilen();
		String[]wort = a.koma(str);
		String[]nwort ={wort[0],wort[1],wort[2],wort[3],wort[4]};		
		nwort=new textPane(nwort).worte();
		int menge=Integer.parseInt(nwort[1])-Integer.parseInt(wort[1]);				
		double betrag=Integer.parseInt(nwort[1])*Double.parseDouble(""+nwort[3]);
		nwort[4]=""+(float)betrag;	
		return nwort;
	}
	public String[]zeileB(String str,int erg){
		ausTeilen a=new ausTeilen();
		String[]wort = a.koma(str);
		String[]nwort ={wort[0],wort[1],wort[2],wort[3],wort[4]};		
		nwort=new textPane(nwort).worte();
		int menge=Integer.parseInt(nwort[1])-Integer.parseInt(wort[1]);				
		double betrag=Integer.parseInt(nwort[0])*Double.parseDouble(""+nwort[3]);
		nwort[4]=""+(float)betrag;	
		String[]vektorwort=new String[2];
		//RC0406 str[erg] 1,5,cardinale,5.7,28.5
		//if(erg>1)erg=erg-1;
		vektorwort[0]=(erg+","+nwort[0]+","+nwort[2]+","+nwort[3]+","+(float)betrag);
		//RC0406 myVector 5,155,cardinale,5.7,28.5
		vektorwort[1]=(nwort[0]+","+nwort[1]+","+nwort[2]+","+nwort[3]+","+(float)betrag);		
		return vektorwort;
	}
}
