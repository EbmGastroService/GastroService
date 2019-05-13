// created on 24.04.2007 at 00:30
package com.units;
public class CF{
	public String path;
	public CF(String fn){
		path=chekfilename(fn);
	}
	public static String chekfilename(String filename){
		String filen="";
		String pat=new ver6.basic().getPath().replace('\\','/');
		filename=new ver6.TC().L(filename);
		if(filename.indexOf(":\\")>-1 || filename.indexOf(":/")>-1){
			filen=filename.replace('\\','/');//filename;
		}else if(filename.charAt(0)==('/')||filename.charAt(0)==('\\')){//linux	==/usr oder /home		
			filen=pat+filename.substring(1,filename.length());
		}else if(filename.indexOf(pat)>-1){
		}else filen=pat+filename;
		//System.out.println("\nin save >>"+filename);
		//new ebm.li(filen);
		filen=filen.toLowerCase();
		//System.out.println("von save:"+filen);
		return filen;
	}
}
