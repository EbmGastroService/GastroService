// created on 10.07.2006 at 15:11
//Author Mourad El bakry
//Vianna
package com.security;
import java.util.*;
import java.io.*;
public class RM3 {
	List<String> myjl;
	List<String> mywv;
	List<String> mywk;
	List<String> myk;
	int Wahl;
	String myFile;
	public RM3(){
		myFile="b1.exe";
		myjl = add_date("gastro/Date/Jliste.dat");			
		mywk = add_date("gastro/Date/kon.dat");			
		mywv = new ArrayList<String>();
		Wahl=liste();
		test();		
	};
	public RM3(int wahl,String file){
		Wahl=wahl;
		myFile=file;
		myjl = add_date("gastro/Date/Jliste.dat");			
		mywk = add_date("gastro/Date/kon.dat");			
		mywv = new ArrayList<String>();
		test();		
	};
	public RM3(String rein){
		del_0file("gastro/Date/Drucker/");
		del_0file("gastro/k_r/");		
	};
	void test(){
		if(remove_zeile(Wahl).charAt(0)=='E'){			
			wv_add_rec_oposten();
			save_all();
			save_endz();
			del_0file("gastro/Date/Drucker/");
			del_0file("gastro/k_r/");
		}
	}

	List<String> add_date(String file){		
		String[]wl=new com.search.sucheDate(file).myDaten();
		List<String> myErg=new ArrayList<String>();			
		for(int i=0;i<wl.length;i++){			
			myErg.add(""+wl[i]);
		}
		return myErg;
	}
	int liste(){
		String[]wl=new String [myjl.size()];		
		for(int i=0;i<wl.length;i++){			
			wl[i]=myjl.get(i);
		}
		return new com.display.Jlist(wl,false).ergebnis();
	}
	String remove_zeile(int erg){
		String str="Erfolgreich!";			
		if(erg>0 && erg<myjl.size()){
			String rec=myjl.get(erg);			
			String path=System.getProperty("user.home", "not specified")+"/.gala/date/exec/"+myFile;
			new com.units.save().file(path,rec,true);
		//	System.out.println(rec);
			remove_wk(rec);
			remove_jl(rec);		
		}else str="Nicht "+str; 
			System.out.println(str);
		return str;
	}
	void save_all(){
		save_v("gastro/Date/Jliste.dat",inhalt(myjl));
		save_v("gastro/Date/kon.dat",inhalt(mywk));			
		save_v("gastro/Date/warenverkauf.dat",inhalt(mywv));
		save_v("gastro/Date/Data"+new com.units.myDatum().J()+
		       "/TV"+new com.units.myDatum().ist_my()+"N/"+
		       new com.units.myDatum().ist1()+"N.dat",inhalt(mywv));
	}
	void save_endz(){
		new com.units.save().file("gastro/Date/RZ.dat",""+endz(),false);
	}

	String[]inhalt(List<String> v){
		String[]str = new String[v.size()];
		for(int i=0;i<str.length;i++){			
			str[i]=v.get(i);
		}
		return str;
	}
	void save_v(String file,String[]v){
		new com.units.save().dontsort(file,v,false);
	}
	void remove_rec(String rec){
		String[] daten=new com.options.ausTeilen().koma(rec);		
		del("gastro/Date/Drucker/"+daten[2].trim()+".dat");
		del("gastro/k_r/"+daten[2].trim()+".dat");
	}
	void remove_jl(String rc){
		String r=new com.options.ausTeilen().koma(rc)[2];
		int x=-1;
		for(int i=0;i<myjl.size();i++){			
			String rec=myjl.get(i);
			String rd=new com.options.ausTeilen().koma(rec)[2];			
			if(rd.equals(r)){
				myjl.remove(i);
				x=i;				
			}							
		}			
		change_Jliste(x);
	}
	void remove_wk(String rc){
		//String r=new com.options.ausTeilen().koma(rc)[2];
		String r=new ver6.Rcode(rc,0).dgen;
	//	System.out.println("dgen: "+r+" vorher:"+ rc);
		int x=-1;
		for(int i=0;i<mywk.size();i++){			
			String rec=mywk.get(i);
			//String rd=new ver6.Rcode(rec,1).genkr[0];//new com.options.ausTeilen().koma(rec)[1];			
			if(rec.equals(r)){
				mywk.remove(i);
				x=i;
	//			remove_k(""+rd);
			}							
		}				
		//if(x>=mywk.size())x=x-1;
		change_Wkorb(x);
	}
	
	void del(String filename){	
		File f =new File(filename);				
		if(f.exists()){				
			try{
				f.delete();System.out.print(" ..,");
			}catch(SecurityException  ioe){System.err.println(ioe);}
		}
	}
	void del_0file(String mydir){	
		String[]fl = new com.search.sucheDate().dir(mydir);
		for(int i=0;i<fl.length;i++){			
		File f =new File(mydir+fl[i]);				
			if(f.exists()){
				if(f.length()==0){
					try{
						f.delete();System.out.println(" dir ist ok ");
					}catch(SecurityException  ioe){System.err.println(ioe);}
				}
			}
		}
	}
	int ez(String c){
		String rec="";
		String d="";//new com.options.ausTeilen().koma(rec)[0];		
		int x=Int(c.substring(1,c.length()));
		if(c.charAt(0)=='J'){
			if(x==myjl.size())x=x-1;
			rec = myjl.get(x);
			d=new com.options.ausTeilen().koma(rec)[0];		
			d=d.substring(d.indexOf("/")+1,d.length());
		}
		else {			
			if(x==mywk.size())x=x-1;
			rec = mywk.get(x);
			d=rec.substring(rec.indexOf("N")+1,rec.indexOf("K"));
			//new com.options.ausTeilen().koma(rec)[0];
		}					
	return Int(d);//.substring(pos(d)+1,d.length()));	
	}
	int endz(){
		String rec = myjl.get(myjl.size()-1);		
		String d = new com.options.ausTeilen().koma(rec)[0];		
	return Int(d.substring(pos(d)+1,d.length()));	
	}
	void change_Jliste(int x){
		int rz=ez("J"+x)-1;int i=0;int m=0;
		for(i=x;i<myjl.size();i++){			
			String rec=myjl.get(i);
			String[] daten=new com.options.ausTeilen().koma(rec);						 
			String neuZeile=change_Rz(""+daten[0],rz)+","+daten[1]+","+daten[2]+","+daten[3]+","+daten[4]+
			","+daten[5];
			rz++;
			myjl.set(i,""+neuZeile);
			x=i;
		}		
	}		
	//R300806N(535)K2340001X01E
	void change_Wkorb(int x){				
		int rz=ez("W"+x)-1;int m=0;
		for(int i=x;i<mywk.size();i++){			
			String rec=mywk.get(i);
			//String[] daten=new com.options.ausTeilen().koma(rec);									
			String neuZeile=change_Rwk(""+rec,rz);			
			rz++;
			mywk.set(i,""+neuZeile);m=i;
		}
	}
	String change_Rwk(String rec,int lr){		
		String r1=rec.substring(0,rec.indexOf("N"));
		String r2=rec.substring(rec.indexOf("K"),rec.length());
		String str=r1+"N"+lr+r2;	
	//	System.out.println(str);
		return str;
	}
	void wv_add_rec(String rec){
		String[]wl=new com.search.sucheDate(rec).myDaten();		
		for(int i=0;i<wl.length;i++){			
			mywv.add(""+wl[i]);
		}		
	}

	void wv_add_rec_oposten(){
		String[]wl=new String[myjl.size()];
		for(int i=0;i<wl.length;i++){			
			wl[i] = myjl.get(i);
		}
		for(int i=0;i<wl.length;i++){			
			String[] daten=new com.options.ausTeilen().koma(""+wl[i]);			
			wv_add_rec("gastro/Date/Drucker/"+daten[2].trim()+".dat");			
		}
	}
	public int Int(String str){	
    	int i=0;try{i=Integer.parseInt(str);}catch(Exception e){i=0;}
    	return i;
    }
    int pos(String rec){
		int cod=0;
		for(int i=0; i<rec.length()-1;i++){
			if(rec.charAt(i)=='/') cod=i;
		}
		return cod;
	}
	String change_Rz(String rec,int lr){
		int cod=pos(rec);
		String r1=rec.substring(0,cod);
		String str=r1+"/"+lr;	
		return str;
	}
	public static void main(String[] args) {
        new Rambo1();
		
	
	}
	
}
