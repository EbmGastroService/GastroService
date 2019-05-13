// created on 30.09.2009 at 16:04
package ebm;
public class Benutzer{

	public Benutzer(){	}
	public String BenutzerName(){
		com.options.MyOp mo = new com.options.MyOp();
		String[] myliste = new com.search.sucheDate("gastro/date/benutzer.dat").myDaten();
		String eintext="<html><font color=red>Fahrer Code</font><font color=blue>";
		for(int i=0; i<myliste.length; i++){
			if(i % 2==0)eintext+="<br>";
			eintext+=""+i+" = "+myliste[i]+"&nbsp;&nbsp;&nbsp;&nbsp;";
			
		}
		eintext+="<br></font></html>";
		String dernamecode=mo.eingabe(eintext);
		String dername="";
		int i=-1;
		if(dernamecode.length()>0){
			try {
				i=Integer.parseInt(dernamecode);
			} catch (NumberFormatException e) {System.out.println("\nZahlfehler JL " + e);i=-1;}
		}
		if(i>=0 && i<myliste.length)dername=myliste[i];
		else dername=mo.wahl(myliste,"Fahrer suchen");
		return dername;//new Jlist(
	}
}
