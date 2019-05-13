// created on 01.02.2007 at 13:34
package ebm;
import java.io.*;
import java.util.*;
public class Up{
	String[]monat={"01","02","03","04","05","06","07","08","09","10","11","12"};
	String[]jahr;			
	String[][]suchZiel;
	String dif;
	public String myZeile;	
	public Up(){}
	/*dif= mit dem es im file zeilen bestimmt zu finden ist
	 * Zb. N in kon, / in Fliste und kbuch
	 *jahr=bestimmt die lenge die monaten files 
	 * suchziel = filesnamen und inthelt directory jahr Zb d2006/ deklar+0106+dif=FL+0106+F 
	 * oder KB+0106+K oder KO+0106+N
	 */
	public Up(String suchDif,String vfile,String path,String deklar){
		//init();
		jahrDirs();//jahr
		dif=suchDif;		
		suchZiels();//suchZiel		
		zeig(los(vfile.toLowerCase(),path.toLowerCase(),deklar.toLowerCase()));		
		zeig("....Ende gesammt Up "+deklar+"..."+new com.units.myDatum().ist());
	}
	public Up(String suchDif,String vfile,String path,String deklar,String aktMonat){
		//init();
		jahrDirs();//jahr
		dif=suchDif;		
		suchZiels();				
		String afile=aktuell(Files(path.toLowerCase(),deklar.toLowerCase()),aktMonat);//System.out.println(afile);		
		zeig(los(vfile.toLowerCase(),path.toLowerCase(),deklar.toLowerCase()));
		zeig(afile+" & "+save_akt(lesen(afile),vfile));
		zeig(".......Ende Up .............."+deklar+""+aktMonat);
	}
	void jahrDirs(){
		String J=new com.units.myDatum().J();
		new ebm.li("gastro/d2006/");new ebm.li("gastro/d2007/");new ebm.li("gastro/"+J.replace('_','d')+"/");
		jahr=new ebm.li("gastro/d2?").ordner();		
	}
	void del_0file(String mydir){	
		String[]fl = new com.search.sucheDate().dir(mydir);
		for(int i=0;i<fl.length;i++){
			File f =new File(mydir+fl[i]);
			if(f.exists()){
				if(f.length()==0){
					try{
						f.delete();System.out.print("-0,");
					}catch(SecurityException  ioe){System.err.println(ioe);}
				}
			}
		}
	}
	String[][]suchZiels(){//anzahl Jahrs*Monaten  	                
		String[]myjahr=new String[jahr.length];
		for(int i=0;i<jahr.length;i++){
			if(jahr.length>0 && jahr[i]!=null && jahr[i].length()>4)
				myjahr[i]=jahr[i].substring(3,5);//D2006=06
			else myjahr[i]=jahr[i];//.replace('D','_');
		}		
		suchZiel=new String[jahr.length][monat.length];
		for(int zielj=0;zielj<jahr.length;zielj++){
			for(int zielm=0;zielm<monat.length;zielm++){
				suchZiel[zielj][zielm]=""+monat[zielm]+myjahr[zielj];//1206
				if(dif.length()>0)suchZiel[zielj][zielm]+=dif;//1206+ / oder N oder 
			}
		} //[0][0]=0106/ 0 1=0206
		return suchZiel;
	}
	public void zeige(String[][]v){
		if(v.length>0 && v!=null){
			for(int i=0;i<v.length;i++){
				for(int x=0;x<v[i].length;x++){
					//System.out.print(" "+v[i][x]);
					myZeile+=""+v[i][x];
					//jta.append(""+v[i][x]);
				}
				//jta.append("\n");
				myZeile+="\n";
				System.out.println();
			}
		}
	}
	public void zeig(String[]v){
		myZeile+="..";
		if(v.length>0 && v!=null){
			String protofn="gastro/date/protocol/p"+new com.units.myDatum().ist_my()+".dat";
			for(int i=0;i<v.length;i++){
				//jta.append("\n"+v[i]);
				myZeile+="\n*"+v[i];
				new com.units.save().file(protofn,v[i],true);
			}
		}
	}
	public void zeig(String v){
		String protofn="gastro/date/protocol/p"+new com.units.myDatum().ist_my()+".dat";
		//jta.append("\n"+v);
		myZeile+="\n"+v;
		new com.units.save().file(protofn,v,true);					
	}
	String save_akt(String[]inhfile,String myfile){
		if(inhfile.length>-1){
			new com.units.save().dontsort(myfile,inhfile,false);
			//jta.append(myfile+" ok ");
			myZeile+="\n"+ myfile+" ok ";
			return myfile+" ok ";
		}else {//jta.append(myfile+" ok ");
			myZeile+="\n"+ myfile+" No ";
			return myfile+" No ";
		}
	}
	
	String aktuell(String[]files,String file){//file=KO0207N von v[]=KO0106N..KO0207N
		String dataname="";
		for(int i=0;i<files.length;i++){
			String vfile=files[i];
			if(vfile.indexOf(file)>-1){
				//System.out.println(vfile+","+file);
				dataname=vfile;
			}
		}
		//jta.append(dataname);
		myZeile+="\nA:"+dataname;
		return dataname;
	}
	String[]lesen(String file){
		return new com.search.sucheDate(file).myDaten();
	}
	void berechneFile(String myfile){				
		if(myfile.indexOf("FL")>-1 || 
		   myfile.indexOf("fl")>-1 || 
		   myfile.indexOf("kb")>-1 || 
		   myfile.indexOf("KB")>-1){
			String[]headers={"RN","Datum","KN","Betrag","Total","Driver","BuchBez"};
			new BR(myfile,headers);
		}
		if(myfile.indexOf("KO")>-1 || myfile.indexOf("ko")>-1){			
			String[]headers={"Menge","Code","Bezeichnung","Betrag","Total"};
			new BR(myfile,headers,0);
		}
	}
	String[]Files(String path,String deklar){
		String[]dir=new String[jahr.length*monat.length];
		int x=0;		
		for(int zielj=0;zielj<jahr.length;zielj++){
			String fnj=path+"/"+jahr[zielj];			
			//System.out.println("Up: "+fnj);
			for(int zielm=0;zielm<monat.length;zielm++){					
				String fnm="";
				if(suchZiel[zielj][zielm].indexOf("/")>-1)
				fnm=fnj+"/"+deklar+suchZiel[zielj][zielm].replace('/',deklar.charAt(0))+".dat";
				else fnm=fnj+"/"+deklar+suchZiel[zielj][zielm]+".dat";					
				dir[x++]=fnm;
			}
		}
		return dir;
	}
	String TestFileName(String[]files,String suche,String deklar){		
		String myfile="";
		suche=suche.replace('/',deklar.charAt(0))+".dat";
		for(int x=0;x<files.length;x++){			
			if(files[x].indexOf(deklar+suche)>-1){
				myfile=files[x];
			}
			//System.out.println(files[x]+","+myfile+",suche:"+deklar+suche);
		}		
		return myfile;
	}
	String[]mfData(String vfile,String suche){		
		String[]inhfile=lesen(vfile);
		String[]inh=new String[inhfile.length];
		int x=0;
		for(int i=0;i<inhfile.length;i++){
			if(inhfile[i].indexOf(suche)>-1){
				inh[i]=inhfile[i];x++;
			}else inh[i]="-";
		}	
		String[]in=new String[x];
		x=0;
		for(int i=0;i<inh.length;i++){
			if(inh[i]!="-"){
				in[x]=inh[i];x++;
			}
		}
		return in;
	}
	String SZ(int j,int m){
		return suchZiel[j][m];
	}
	String[]los(String vfile,String path,String deklar){
		int x=0;		
		String[]inhfile=new String[0];
		for(int zielj=0;zielj<jahr.length;zielj++){
			for(int zielm=0;zielm<monat.length;zielm++){					
				String suche=SZ(zielj,zielm);
				String myfile=TestFileName(Files(path,deklar),suche,deklar);
				inhfile=mfData(vfile,suche);
				//jta.append(
				if(inhfile.length>0)myZeile+="\n"+vfile+" & "+suche+saveInM(myfile,inhfile);//);
			}
		}
		return inhfile;
	}
	String saveInM(String myfile,String[]inhfile){
		//ebm.Tm myTm = new ebm.Tm(inhfile.length,myfile);
			String mstr="";
		if(inhfile.length>0){
			ebm.Tm myTm = new ebm.Tm(inhfile,myfile);
			if(myTm.Ergebnis!=0){
				new com.units.save().dontsort(myfile,inhfile,false);
				mstr=myTm.zeile;
			}
		}
		/*String mzeile=" ";
		if(inhfile.length>0 && myTm.Ergebnis>0){
			new com.units.save().dontsort(myfile,inhfile,false);
			berechneFile(myfile);
			mzeile="\n"+myTm.zeile+"..*"+" Upt ";			
		}
		mzeile="\n"+myTm.zeile;*/
		if(mstr.equals(null))mstr="";
		return mstr;
	}	
	
public static void main(String[]args){
		/*new Up("/");
		String adatum=new com.units.myDatum().ist_my();
		new Up("/","gastro/date/fliste.dat","gastro","FL",adatum);
		new Up("/","gastro/date/kbuch.dat","gastro","KB",adatum);*/

		new Up("N","gastro/d2009/ko0309n.dat","gastro","KO");//adatum);//);
		//new Up();	
	}
}
