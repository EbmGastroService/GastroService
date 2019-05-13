// created on 28.05.2003 at 18:02
package com.printer;
import com.options.MyOp;
import com.units.save;
import com.search.*;

public class papier{	
		double A5;// = 0.70;
		double A4;// = 1.0;
		double A0;// = 0.0;
	public papier(){ein();}
	
	String[]addwert(String file){
		String[]str=new sucheDate(file).myDaten();
		if(str!=null && str.length>0)return str; else{
			new ver6.config();
			str=new sucheDate(file).myDaten();
			return str;
		} 
	}
	void ein(){
		String[]str= addwert("gastro/source/pf1.cfg");//new sucheDate("gastro/source/pf1.cfg").myDaten();
		if(str.length<=0)str=new String[]{"A5 148 x 210 mm,0.7","A4 297 x 210 mm,1.0"};
		//str=addwert("gastro/source/pf1.cfg");
		String strA5=new com.options.ausTeilen().koma(str[0])[1];
		String strA4=new com.options.ausTeilen().koma(str[1])[1];		
		double dub=0.0;
		try{
			dub=Double.parseDouble(strA5);
		}catch(Exception ze){System.err.print("papierfehler");dub=0.0;}
		if(dub>0)A5=dub;else A5=0.7;
		dub=0.0;
		try{
			dub=Double.parseDouble(strA4);
		}catch(Exception ze){System.err.print("papierfehler");dub=0.0;}
		if(dub>0)A4=dub;else A4=1.0;
		String zoom= new sucheDate("gastro/source/zoom.cfg").md();
		if(zoom.length()<=0)zoom="0.40";
		dub=0.0;
		try{
			dub=Double.parseDouble(zoom);
		}catch(Exception ze){System.err.print("papierfehler");dub=0.0;}
		if(dub>0)A0=dub;else A0=0.5;
	}
	public double getPapierformat(){
		String[] ob ={"A5 "+((float)A5*100)+"%","A4 "+((float)A4*100)+"%","zoom "+((float)A0*100)+"%"};
		int ein = new MyOp().frage1("<html><center><font color=blue face='Courier New' size=+1>"+
		                            "Welche Papierformat verwenden Sie</font></center>",ob);
		if (ein==0){save(A5);return A5;}
		if (ein==1){save(A4);return A4;}
		if (ein==2){save(A0);return A0;}
		else {save(A4);return 1.0;}
	}
	public double fixpapier(){
		double d = getPapierformat();
		save(d);
		return d;
	}
	void save(double d){
		new save().file("gastro/Date/mypapier.dat",""+d,false);
	}
	public double getSkale(){
        //sucheDate	su = new sucheDate("gastro/Date/mypapier.dat");
		String str=new sucheDate("gastro/Date/mypapier.dat").md();
		double d=0;
		if(str=="")str=""+fixpapier();else d = Double.parseDouble(str);
		//if (d==0)d=fixpapier();
     	return d;
    }
}
