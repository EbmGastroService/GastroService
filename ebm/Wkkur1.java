// created on 28.07.2006 at 00:19
//Author Mourad El bakry
//package ebm;
import java.util.*;
public class Wkkur1{
	Vector alt,neu;
	String Code;
	public int AB;
	public Wkkur1(){		
		alt=lesen("Date/Wkiste.dat");
		for(int n=0;n<alt.size();n++)
				System.out.println(n+", "+alt.elementAt(n).toString());
		neu=new Vector();
		check();
		save();
	}
	public Wkkur1(String code){		
		neu=lesen("Date/Wkiste1.dat");
		Code=code;		
		//System.out.println(add_AB());
		//save();
	}
	Vector lesen(String fn){
		String[]wl= new com.search.sucheDate(fn).myDaten();
		Arrays.sort(wl);
		Vector myErg=new Vector();			
		for(int i=0;i<wl.length;i++){			
			myErg.addElement(""+wl[i]);
		}
		return myErg;
	}
	void save(){
		String[]str=new String[neu.size()];
		for(int i=0;i<str.length;i++)
			str[i]=neu.elementAt(i).toString();
			
		new com.units.save().filefelde("Date/Wkiste1.dat",str,false);
	}
	Vector check(){							
		neu=new Vector();		
		int m=1;
			for(int i=0;i<alt.size();i++){				
				String stri=alt.elementAt(i).toString();				
				int z=1;
				m=i+1;				
				for(int x=m;x<alt.size();x++){
					String strx=alt.elementAt(x).toString();
					if(stri.equals(strx)){
						z++;						
						alt.setElementAt("LEER",x);
					}
				}				
				alt.setElementAt(stri+","+z,i);
			}		
			for(int n=0;n<alt.size();n++){
				String stri=alt.elementAt(n).toString().substring(0,4);				
				if(stri.equals("LEER"))System.out.print("");
				else neu.addElement(alt.elementAt(n).toString());
			}
			for(int n=0;n<neu.size();n++)
				System.out.println(n+", "+neu.elementAt(n).toString());
		return neu;
	}
	
	int suche(){		
		String abstrn="";
		for(int n=0;n<neu.size();n++){
			String mstr=neu.elementAt(n).toString();
			t.println("Code:"+Code+"  abstr:"+abstr+"  mstr:"+mstr);
			if(abstr.trim().equals(Code.trim())){abstrn=abstr1;}
		}	
		//System.out.println(">>gesucht:"+Code+", "+abstrn);
		int ab=0;
		try{
			ab=Integer.parseInt(abstrn);
		}catch(Exception ne){ab=0;};
		return AB=ab;
	}
	String add_AB(int ab){		
		String nstr="";
		for(int n=0;n<neu.size();n++){
			String mstr=neu.elementAt(n).toString();
			String kc=new com.options.ausTeilen().koma(mstr)[0];
			if(kc.equals(Code)){
				neu.setElementAt(kc+","+ab,n);
				nstr=neu.elementAt(n).toString();
			} 
		}
		if(nstr==""){
			nstr=Code+","+ab;
			neu.addElement(nstr);
		}
		System.out.println(nstr);
		save();
		return nstr;
	}
	 public static void main(String[] args) {
	  new Wkkur1();	 	
	 }
}
