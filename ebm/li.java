// created on 07.02.2007 at 20:17
package ebm;
import java.util.*;
import java.io.*;

public class li{
	String path;
	String[]mDirs,mFiles;
	public li(String Path){	
		mDirs=new String[0];
		mFiles=new String[0];			
		path=new com.units.CF(Path).path;
		//System.out.println("Li:"+path);
		start();		
	}
	public li(String Path,String target){	
		mDirs=new String[0];
		mFiles=new String[0];			
		path=new com.units.CF(Path).path;
		//System.out.println("Li:"+path);
		start();		
		String[]nFiles=suchStr(target,mFiles);	
		new com.units.save().dontsort("gastro/k_r_ft"+target,nFiles,false);

	}
	void start(){			
		String p=pTest(path);
		if(ChekDir(p)>-1){
			String[]l=dir(p);
			String s=sTest(path);
			if(s.equals(p)){
				mFiles=f(l);zeige(mFiles);
				new com.units.save().dontsort("gastro/k_r_f.dat",mFiles,false);
				mDirs=d(l);//zeige(d(l));

			}else{		
				mFiles=suchStr(s,f(l));	zeige(mFiles);		
				mDirs=suchStr(s,d(l));			
			}
		}else saveF(p);
		//System.out.println(saveF(p));
	}


	String sTest(String po){
		String str="";
		String p=po.toLowerCase();
		int starPos=p.indexOf("?");
		int pa=0;int pe=p.length();
		if(starPos>-1){			
			for(int i=0;i<p.length();i++){
				if(p.charAt(i)=='/' || p.charAt(i)=='\\')pa=i;
			}
			str=p.substring(pa+1,starPos);
		}else str=po;
		//System.out.println("*sTest :"+pa+","+pe+">"+str);
		return str;
	}
	String pTest(String po){
		String str="";
		String p=po.toLowerCase();
		int starPos=p.indexOf("?");
		int pe=0;
		if(starPos>-1){			
			for(int i=0;i<p.length();i++){
				if(p.charAt(i)=='/' || p.charAt(i)=='\\')pe=i;
			}
			str=p.substring(0,pe+1);
			//System.out.println("*pTest :"+str);
		}else str=po;
		//System.out.println("*pTest :"+str);
		return str;
	}
	public String[]ordner(){return mDirs;}
	public String[]files(){return mFiles;}
	String[]suchStr(String s,String[]l){
		int x=0;
		s=s.toLowerCase();
		String[]mstr=new String[l.length];
		for(int i=0;i<l.length;i++){
			if(l[i].toLowerCase().indexOf(s)>-1){mstr[i]=l[i];x++;}else mstr[i]="-";
		}
		String[]str=new String[x];x=0;
		for(int i=0;i<mstr.length;i++){
			if(mstr[i]!="-"){str[x]=mstr[i];x++;}
		}
		return str;
	}
	int ChekDir(String p){
		p=p.toLowerCase();
		File f=new File(p);
		if(f.isDirectory())return 1; else return -1;
	}
	int ChekFile(String p){
		p=p.toLowerCase();
		File f=new File(p);
		if(f.isFile())return 1; else return -1;
	}
	String strFile(String p){
		p=p.toLowerCase();
		File f=new File(p);		
		return f.getAbsolutePath();
	}
	String saveF(String P){
		String strf=strFile(P);
		int d=ChekDir(P);
		int f=ChekFile(P);
		int ext=strf.indexOf(".");
		String str="";
		if(ext>-1 && f<1)try{str+=creatfile(strf);}catch(Exception e){
			System.out.print(cf(strf));}else
		if(ext<0 && d<1)str+=makdir(strf);else str=strf+" ist vorhanden";
		return str;
	}
	String makdir(String p){		
		p=p.toLowerCase();
		return "save directory:"+new File(p).mkdirs();
	}
	String creatfile(String p)throws Exception{
		p=p.toLowerCase();
		File f=new File(p);
		return "save file:"+f.getName()+","+f.createNewFile();
	}
	String cf(String p){
		p=p.toLowerCase();
		File f =new File(p);				
		File dir = new File (f.getParent());
		String str="";
		if(!dir.exists()){
			try{							
				str+="save dir:";
				System.out.println("\nNew Dir: ");//+//
				                   dir.getAbsolutePath();//+" , "+
				                   dir.mkdirs();//);
				//+f.createNewFile());
			}catch(SecurityException  ioe){System.err.println("dir gesucht ");}
		}
		if(!f.exists()){
			try{
				str+="save File:";
				System.out.println("\nNew File: ");//+//
				                   f.getName();//+" ,"+
				                   f.createNewFile();//);
			}catch(IOException ioe){System.err.println("gesucht ");}
		}
		return str;
	}
	public String[]zeige(String[]str){		
		int i;
		if(str.length>-1){
			for(i= 0;i<str.length;i++)
				System.out.println(i+"-"+str[i]);
			System.out.println("__________________ "+(i)+" ________________________");
		}
		return str;
	}
	String[]dir(String p){		
		p=p.toLowerCase();
		File f =new File(p);				
		File d =new File(p);					
		//System.out.println(f.getAbsolutePath());		
		String[]vl=new String[0];				
		if(f.isDirectory()){
			try{
				vl=f.list();
				for(int i= 0;i<vl.length;i++)
					vl[i]=f.getAbsolutePath()+"\\"+vl[i];			
			}catch(SecurityException  ioe){System.err.println("dir Fehler ");}
		}else {
			vl=new String[0];
			try{
				vl=d.list();
				for(int i= 0;i<vl.length;i++)				
					vl[i]=d.getAbsolutePath()+"\\"+vl[i];
			}catch(SecurityException  ioe){System.err.println("File Fehler ");}
		}		
		return vl;
	}
	String[]f(String[]v){		
		File f=null;	
		List<String> m=new ArrayList<String>();
		for(int i= 0;i<v.length;i++){
			f=new File(v[i]);
			String time=new com.units.myDatum(f.lastModified()).ll();
			if(f.isFile()){				
				m.add(f.getName());//+", "+time+" , "+f.length());
			}
		}
		String[]str=new String[m.size()];
		for(int i= 0;i<m.size();i++)
			str[i]=""+m.get(i);
		Arrays.sort(str);
		return str;
	}
	String[]d(String[]v){		
		File f=null;	
		List<String> m=new ArrayList<String>();
		for(int i= 0;i<v.length;i++){
			f=new File(v[i]);
			//String time=new com.units.myDatum(f.lastModified()).ll();
			if(!f.isFile()){				
				m.add(f.getName());//+", "+time+" , "+f.length());
			}
		}
		String[]str=new String[m.size()];
		for(int i= 0;i<m.size();i++)
			str[i]=""+m.get(i);
		Arrays.sort(str);
		return str;
	}
	public static void main(String[]args){
		if(args.length>0){
			System.out.println("Haupt Menue");
			String ein=args[0].replace('*','?');
			ein=ein.replace('+','?');
			li l=new li(ein,args[1]);			
			//l.start();
		} //else new li(".");
	}
}
	
