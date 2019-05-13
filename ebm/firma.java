// created on 28.05.2003 at 00:59
package ebm;
import com.search.*;
import com.units.save;

public class firma {	
	String[] fd;
	public firma(){		
		String[]bez={"Firmen Bezeichnung ","Plz/Ort/Adresse ","Tel.: ","UID"};		
		fd = new sucheDate("gastro/source/fr.cfg").myDaten();		
		if(fd.length<=0){
			new ver6.config();
			fd = new sucheDate("gastro/source/fr.cfg").myDaten();		
		}
	}
	public String[]neueDate(){
		String[]bez={"Firmen Bezeichnung","Plz/Ort/Adr.","Tel.:","UID:"};		
		String[]myfd=new Einfugen(bez,data()).inhalt();
		if(myfd.length>0 || myfd!=null){
			new save().dontsort("gastro/source/fr.cfg",myfd,false);
		}else myfd=fd;
		return myfd;
	}
	public String[] data(){	return fd;	}
	
}
