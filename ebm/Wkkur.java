// created on 28.07.2006 at 00:19
//Author Mourad El bakry
package ebm;
import java.util.*;
public class Wkkur{
	Vector<String>alt;
	Vector<String>neu;
	String Code;
	int AB;
	public Wkkur(){		
		alt=lesen("gastro/Date/Wkiste.dat");
		for(int n=0;n<alt.size();n++)
				System.out.println(n+", "+alt.elementAt(n).toString());
		neu=new Vector<String>();
		check();
		save();
	}
	public Wkkur(String code){		
		neu=lesen("gastro/Date/Wkiste1.dat");
		Code=code;		
		//System.out.println(add_AB());
		//save();
	}
	Vector<String> lesen(String fn){
		String[]wl= new com.search.sucheDate(fn).myDaten();
		Arrays.sort(wl);
		Vector<String> myErg=new Vector<String>();			
		for(int i=0;i<wl.length;i++){			
			myErg.addElement(""+wl[i]);
		}
		return myErg;
	}
	void save(){
		String[]str=new String[neu.size()];
		for(int i=0;i<str.length;i++)
			str[i]=neu.elementAt(i).toString();			
		new com.units.save().filefelde("gastro/Date/Wkiste1.dat",str,false);
	}
	Vector<String> check(){							
		neu=new Vector<String>();		
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
	
	public int suche(){		
		String abstrn="";
		for(int n=0;n<neu.size();n++){
			String mstr=neu.elementAt(n).toString();
			String abstr=new com.options.ausTeilen().koma(mstr)[0];		
			String abstr1=new com.options.ausTeilen().koma(mstr)[1];		
			if(abstr.equals(Code)){abstrn=abstr1;break;}
		}		
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
		//System.out.println(nstr);
		save();
		return nstr;
	}
	 public static void main(String[] args) {
	 //	String str=args[0];
	 	System.out.print(new Wkkur());
	 	/*Wkkur kur=new Wkkur(str);
	 	int i=kur.suche();
	 	System.out.print(i);
	 	System.out.println(", "+kur.add_AB(i+1));*/
	 	
	 }
}
