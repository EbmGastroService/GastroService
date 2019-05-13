// created on 05.11.2004 at 23:45
package com.units;
import com.options.MyOp;
public class Eingang{	
	public String AlteDatum,NewDatum;
	public static String MeinDatum;
	public static int ant;
	String dir;
	public Eingang(String dir){
		ant=1;
		this.dir=dir;		
		NewDatum=newDatum();
		AlteDatum=alteDatum();
		MeinDatum=meinDatum();	
		AnDatum();
		System.out.println(MeinDatum);
	}
	public void saveDatum(){new save().file(dir+"/datum.dat",MeinDatum,false);}    
    public String alteDatum(){
    	String str=new com.search.sucheDate(dir+"/datum.dat").md();
    	if(str.length()==0 || str==null){str=meinDatum();saveDatum();}
    	return str;    
    }
    public String newDatum(){return new myDatum().ist();}
    public int TestDatum(){if(NewDatum.equals(AlteDatum)) return 1;else return -1;}
    public String meinDatum(){if(TestDatum()>-1)return AlteDatum;	else return NewDatum;}
    public String AnDatum(){
    	if(TestDatum()<1){
    		int ein=new MyOp().frage("Aktuelle Datum: "+MeinDatum.replace('_','.')+
    	                             "\nIhre Buchungsdatum: "+AlteDatum.replace('_','.')+
    	                             "\n\nSoll aktuellisiert ?","Ok","Nein");
    		if(ein==0){zeige();}
    		else if(ein==1){ant=-1;MeinDatum=AlteDatum;}
    	}else {ant=-1;new MyOp().fehler("Aktuelle Datum: "+MeinDatum.replace('_','.'));}
    	saveDatum();
    	return MeinDatum;
    }
    
    public void zeige(){    	
    	if(dir.charAt(7)=='K'){//gastro/kdate
    		int ein=new MyOp().Frage("Achtung!\nBuchungsdatum ist erneut\nsoll Tageserloese umgebucht werden?"+
    		          "\nes werden alle offene Tische Ausgedruckt und entfernt"+
    			          "\nTagesabrechnung ausgeworfen, und entfernt!"+
    			          "\nSie haben noch eine Chance  [Nein] zudrucken! oder Drucken Sie [ Strg + C ] "+
    			          "\nstarten Sie das System erneut an und beantworten Sie mit [Nein]");
 			if(ein==0){MeinDatum=NewDatum;ant=1;}else{MeinDatum=AlteDatum;ant=-1;}    			         
    	}else new MyOp().fehler("Es wird mit dieser Datum \n"+ AlteDatum.replace('_','.')+" weiter gearbeitet");
    }
}
