// created on 03.01.2008 at 23:59
package ebm;
import java.io.File;
import java.security.AccessController;
import java.security.*;
public class Trans{
	public Trans(){
		F();
	}
	public void F(){
		String jahr=new com.units.myDatum().J();
		String sj=jahr.substring(1,jahr.length());
		int vj=intcode(sj)-1;
		String akt="gastro/date/data"+jahr;
		String old="gastro/date/data_"+vj;
		String[]ordner=new ebm.li(old).ordner();//data_2007/tv0807n
		for(int i=0;i<ordner.length;i++){
			String file_old=old+"/"+ordner[i];//data_2007/tv0807n
			String file_akt=akt+"/"+ordner[i];//data_2008/tv0807n
			String[]fIndex=new ebm.li(file_old).files();
			for(int x=0;x<fIndex.length;x++){
				String[]in=new com.search.sucheDate(file_old+"/"+fIndex[x]).myDaten();
				new com.units.save().dontsort(file_akt+"/"+fIndex[x],in,false);
				System.out.println("Trans: "+file_old+"/"+fIndex[x]+" nach "+file_akt+"/"+fIndex[x]+" ok");
				del(file_old+"/"+fIndex[x]);
				System.out.println(" del: "+file_old+"/"+fIndex[x]);
			}
			System.out.println(" del: "+file_old);
			del(file_old);
		}
		System.out.println(" del: "+old);
		del(old);
  	} 
  	int intcode(String str){
  		int code=0;
  		try{
  			code=Integer.parseInt(str);
  		}catch(Exception ex){code=0;};
  		return code;
  	}
  	public static String chekfilename(String filename){	
		String filen=new com.units.CF(filename).path;
		new ebm.li(filen);
		return filen;
	}
  	void del(String filename){			
  		filename=chekfilename(filename); 
		File f =new File(filename);	
  		File dir = new File (f.getParent());
		File file = new File(f.getName());
  		if(f.exists()){							
			try{
				System.out.print(" delete F: "+f.getName()+" "+f.delete());
				}catch(SecurityException  ioe){System.err.println(ioe);}		
		}
		/*else
		if(file.exists()){							
			try{
				System.out.println("\ndelete File: "+file.getName()+" "+file.delete());
				}catch(SecurityException  ioe){System.err.println(ioe);}		
		}else
		if(dir.exists()){							
			try{
				System.out.println("\ndelete Dir: "+dir.getName()+" "+dir.delete());
				}catch(SecurityException  ioe){System.err.println(ioe);}		
		}*/
	}
	
  	public static void main(String[]args){
  		new Trans().F();
  		
  	}
}
