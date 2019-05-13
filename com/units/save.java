// created on 29.04.2003 at 12:59
package com.units;
import com.search.*;
import java.io.*;
import java.util.*;
import java.io.File;
import java.security.AccessController;
import java.security.*;

public class save {
	
	public static void del(String filename){	
		filename=chekfilename(filename);  
		File f =new File(filename);				
		File dir = new File (f.getParent());
		File file = new File(f.getName());
		String name=f.getName();
		name=name.substring(0,name.length()-4);
		if(f.exists()){			
			System.out.println("\nFile in tempel: "+name+"\n");
			try{
				try{
					System.out.println(
					 "\ndelete File: "+f.getName()+" rename:"+
				 	f.renameTo(new File("gastro/kdate/temp/"+name+".tmp"))+","+f.delete()
				 	);
				}catch(SecurityException  ioe){System.err.println(ioe);}
			}catch(SecurityException se){System.err.println(se);}
		}		
	}
	public static String ren(String newfile,String oldfile,long modi){	
		newfile=chekfilename(newfile);  
		oldfile=chekfilename(oldfile);  
		File n =new File(newfile);				
		File o=new File(oldfile);
		if(o.exists()){	
			try{
			try{
				try{						
					copy(o,n);
				 	o.renameTo(n);
				 	n.setLastModified(modi);					
					o.delete();
				}catch(SecurityException  ioe){System.err.println(ioe);}
			}catch(SecurityException se){System.err.println(se);}			
			}catch(IOException ioe){System.err.println(ioe+"...copyto");}
			
		}		
		return "\no-data:["+o+"]\n"+"N-data:["+n+"] Datum:["+new myDatum(modi).ll()+"]";
	}
	public static void copy(File src, File dst) throws IOException {		
    	InputStream in = new FileInputStream(src);
    	OutputStream out = new FileOutputStream(dst);	

    	// Transfer bytes from in to out
    	byte[] buf = new byte[1024];
    	int len;
    	while ((len = in.read(buf)) > 0) {
	        out.write(buf, 0, len);
	    }
	    in.close();
    	out.close();
	}

	public static void Removeto(String filename,String Zielordner,boolean wie){	
		filename=chekfilename(filename);
		Zielordner=chekfilename(Zielordner);
		File f =new File(filename);
		File to=new File(Zielordner);
		if(f.exists()){					
			try{
			  try{			  				  	  					
			  	copy(f,to);
			  	to.setLastModified(f.lastModified());
			  	if(!wie)f.delete();
			}catch(SecurityException se){System.err.println(se);}				
			}catch(IOException ioe){System.err.println(ioe+"...Remove it To");}
		}		
	}
	public static String sameModi(String filename,long b){	
		filename=chekfilename(filename);		
		File f =new File(filename);		
		long a=0;		
		if(f.exists()){											
			a=f.lastModified();
			f.setLastModified(b);					
		}
		return f+"\nA-data:["+a+"]\n"+"N-data:["+b+"] Datum:["+new myDatum(a).ll()+"]";
	}		
	
	public static String chekfilename(String filename){	
		String filen=new com.units.CF(filename).path;
		new ebm.li(filen);
		return filen;
	}
	public static String file(String filename,String zeile, boolean wie){
		 filename=chekfilename(filename);  		
		try{
			FileWriter dateiStream = new FileWriter (filename,wie);
			FilePermission perm = new FilePermission(filename , "write");
			PrintWriter ausgabe = new PrintWriter(dateiStream);
			if (zeile != null && zeile != "" && zeile.length()>0) {
				ausgabe.println(zeile);
			}
			ausgabe.close();
		}catch(IOException e){;}
		return zeile+"\n ist updatet";
	}
		public static String filefelde(String filename,String[] zeile, boolean wie){
			filename=chekfilename(filename);  
		try{		
			FileWriter dateiStream = new FileWriter (filename,wie);
			FilePermission perm = new FilePermission(filename , "write");
         	PrintWriter ausgabe = new PrintWriter(dateiStream);
			String[] sl = null;					
			sl = sortliste(zeile);					
			for(int i=0; i < sl.length ; i++){
				if(sl[i]!= null){				
         			ausgabe.println(sl[i]);				
				}
			}
         	ausgabe.close();               
		}catch(IOException e){}
		return "Die Daten sind updatet";//+zeile[2];
	}
		
		public static String dfilefelde(String filename,String[][] zeile, boolean wie){
			filename=chekfilename(filename);  
			String str="";
		try{		
			FileWriter dateiStream = new FileWriter (filename,wie);
			FilePermission perm = new FilePermission(filename , "write");
	        	PrintWriter ausgabe = new PrintWriter(dateiStream);	
	            for (int i = 0;i< zeile.length; i++){                         
                        if(zeile[i][0]!=null && zeile[i][0]!=""){
                         	str =""+zeile[i][0];
                        	if((str!=null)&&(str!="")){                        	  
                        	  ausgabe.print(zeile[i][0]);
                         	  for ( int m = 1;m< zeile[0].length; m++){
                         		str =""+zeile[i][m];
                         	 	if((str!=null)&&(str!="")){
                                         ausgabe.print(","+zeile[i][m]);
                         	 	}
                         	  }                            
                        	}         
                        	ausgabe.println();
                        }
                  }

         	ausgabe.close();               
		}catch(IOException e){}
		return "Die Zeilendaten sind updatet";
	}

	public static String dontsort(String filename,String[] zeile, boolean wie){		
		filename=chekfilename(filename);  
		try{
			FileWriter dateiStream = new FileWriter (filename,wie);
			FilePermission perm = new FilePermission(filename , "write");
         	PrintWriter ausgabe = new PrintWriter(dateiStream);
			String[] sl = null;					
			sl = zeile;					
			for(int i=0; i < sl.length; i++){
				if(sl.length>0 && sl[i]!= null){				
         			ausgabe.println(sl[i]);					
				}
			}
         	ausgabe.close();               
		}catch(IOException e){}
		return "Die Daten sind updatet";
	}	
	public static String[] sortliste(String[] str){						
		Arrays.sort(str);		
		return str;
	}
}
