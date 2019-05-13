// created on 23.07.2006 at 02:00
package ebm;
public class kur{	
	String[] mystr;
	public kur(){
		mystr=lesen();
		for(int i=0;i<mystr.length;i++){
			mystr[i]=""+neu(""+mystr[i]);
		}
		save();
	}
	String neu(String erg){
		String str="";
    	int cod=0;    	
    	for(int i=0; i<erg.length()-1;i++){
    		if(erg.charAt(i)=='X') cod=i;
    	}
    	int l=erg.length()-1;
    	if(cod>=l || cod==0)str=erg;else
    	str = erg.substring(0,cod);//+" ";
		return str;
	}
	void save(){
		new com.units.save().filefelde("gastro/Date/Wkiste.dat",mystr,false);
	}
	String[]lesen(){
		return new com.search.sucheDate("gastro/Date/Wkiste.dat").myDaten();
	}
}
