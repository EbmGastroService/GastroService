// created on 01.02.2007 at 22:43
package ebm;
public class BR{
	
	public BR(String file,String[]headers){
		String[]data=lese(file);
		String[][]clomdata=inclomen(data);
		//zeige(clomdata);
		int b_pos=geldpos(headers,"Betrag");
		int t_pos=geldpos(headers,"Total");
		String[][]neudata=clomenbrechnen(clomdata,b_pos,t_pos);		
		//zeige(neudata);
		save_akt(datazeilen(neudata),file);
	}
	public BR(String file,String[]headers,int wahl){
		String[]data=lese(file);
		if(data.length>0){
			String mfile=wfile(file);
			openumsatz(mfile,data);
			String[]fdata=lese(mfile);
			if(fdata.length>0)
				kon(mfile,fdata,headers);
		}
	}
	void kon(String mfile,String[]fdata,String[]headers){		
		String[][]clomdata=inclomen(fdata);
		//zeige(clomdata);
		int b_pos=geldpos(headers,"Betrag");
		int t_pos=geldpos(headers,"Total");
		int m_pos=geldpos(headers,"Menge");
		int c_pos=geldpos(headers,"Code");
		String[]waren=lese("gastro/date/wliste.dat");
		String[][]neudata=clomenbrechnen(waren,clomdata,b_pos,t_pos,m_pos,c_pos);		
		//new com.units.save().filefelde(mfile,datazeilen(neudata),false);//Sortiert
		//zeige(neudata);
		save_akt(datazeilen(neudata),mfile);		
		savkon(datazeilen(neudata),mfile);		
		//save_akt(datazeilen(neudata),mfile);		
	}
	String[]ohneMinus(String[]data){
		int m=0;
		String[]str=new String[m];
		if(data.length>0){									
			for(int x=0;x<data.length;x++){
				if(data[x].charAt(0)=='0'){
					data[x]="-";
				}else m++;
			}
			str=new String[m];
			m=0;
			for(int x=0;x<data.length;x++){
				if(data[x]!="-"){
					str[m]=data[x];m++;
				}
			}
		}
		return str;
	}
	void savkon(String[]data,String mfile){
		mfile=mfile.toLowerCase();
		mfile=mfile.replace('w','v');
		String[]str=ohneMinus(data);
		if(str.length>0){												
			new com.units.save().dontsort(mfile,str,false);		
		}
	}
	String[]lese(String file){
		return new com.search.sucheDate(file).myDaten();
	}
	int geldpos(String[]str,String suchtext){
		int x=0;
		for(int i=0;i<str.length;i++){
			if(str[i].indexOf(suchtext)>-1)	x=i;//else x=-1;		
		}
		return x;
	}
	String[][]inclomen(String[]str){
		String[][]nstr=new String[0][0];
		if(str.length>0 &&str[0]!=null){
			String[]w=new com.options.ausTeilen().koma(str[0]);
			nstr=new String[str.length][w.length];
			for(int i=0;i<nstr.length;i++){
				w=new com.options.ausTeilen().koma(str[i]);				
				for(int x=0;x<nstr[i].length;x++){
					nstr[i][x]=w[x];
				}
			}
		}
		return nstr;
	}
	String[][]clomenbrechnen(String[][]str,int b,int t){
		String[][]nstr=str;
		float total=0;float betrag=0;
		if(str.length>0 &&str[0].length>0){						
			for(int i=0;i<nstr.length;i++){										
				betrag=f(""+nstr[i][b]);
				total+=betrag;				
				nstr[i][t]=""+(float)total;
			}
		}
		return nstr;
	}
	
	String[][]clomenbrechnen(String[]ware,String[][]str,int b,int t,int m,int c){		
		String[][]w=inclomen(ware);	
		String[][]nstr=new String[w.length][str[0].length];
		int[]absatz=new int[w.length];
		float total=0;float betrag=0;int menge=0;
		if(str.length>0 &&str[0].length>0){						
			for(int x=0;x<w.length;x++){				
				for(int i=0;i<str.length;i++){			
					if(str[i][c].equals(w[x][0])){
						if(str[i][m].indexOf("-")>-1)
							absatz[x]-=Int(""+str[i][m]);else absatz[x]+=Int(""+str[i][m]);						
					}
				}
			}
			for(int i=0;i<absatz.length;i++){				
				betrag=f(""+w[i][2]);
				total=absatz[i]*betrag;
				nstr[i][m]=""+absatz[i];
				nstr[i][c]=w[i][0];
				nstr[i][c+1]=w[i][1];
				nstr[i][b]=""+betrag;
				nstr[i][t]=""+(float)total;					
			}			
		}		
		return nstr;
	}
	
	String[]datazeilen(String[][]str){
		String[]nstr=new String[str.length];		
		if(str.length>0 &&str[0].length>0){						
			for(int i=0;i<nstr.length;i++){			
				nstr[i]=str[i][0];
				for(int x=1;x<str[i].length;x++){
					nstr[i]+=","+str[i][x];
				}			
			}
		}
		return nstr;
	}
	String save_akt(String[]filedata,String file){
		if(filedata.length>0){						
			new com.units.save().dontsort(file,filedata,false);
		return "file: "+file+" updatet";
		}else return file+" nicht eingelegt";
	}
	float f(String str){
		float n=0;
		try{
			n=Float.parseFloat(str);
		}catch(Exception ne){n=0;}
		return n;
	}
	int Int(String str){
		int n=0;
		try{
			n=Integer.parseInt(str);
		}catch(Exception ne){n=0;}
		return n;
	}
	void zeige(String[][]v){
		for(int i=0;i<v.length;i++){//v.length;i++){
			System.out.print(v[i][0]);
			for(int x=1;x<v[i].length;x++){
			System.out.print(","+v[i][x]);
			}			
			System.out.println();
		}
	}
	String wfile(String file){
		String wvb=file.substring(0,file.length()-11);
		String wfn=wvb+"wv"+file.substring(15,19)+"w.dat";
		return wfn;		
	}
	void openumsatz(String wf,String[]wl){		
		java.util.Vector<String> daten = new java.util.Vector<String>();	
		System.out.println(wf);
		new com.units.save().file(wf,"",false);						
		for(int i=0;i<wl.length;i++){					
			String rec="gastro/date/drucker/"+new ver6.Rcode(wl[i].trim(),1).genkr[0]+".dat";			
			String[]str=lese(rec);
			//System.out.println(str.length);
			for(int x=0;x<str.length;x++){
				daten.addElement(""+str[x]);		
				//System.out.println(str[x]);
			}
			
		}
		//System.out.print("BR-Openumsatz: "+daten.size());
		String[]nstr=new String[daten.size()];
		for(int x=0;x<daten.size();x++){
			nstr[x]=daten.elementAt(x).toString();
			//System.out.println(nstr[x]);
		}
		saveW(wf,nstr);
	}
	void saveW(String wf,String[]str){
		if(str.length>0 && str!=null)
		new com.units.save().dontsort(wf,str,true);						
	}
	
	/*public static void main(String[] args) {
		//String file="gastro/D2006/KB1106K.dat";
		//String file="gastro/D2006/FL1006F.dat";
		String file="gastro/D2006/KO1006N.dat";
		//Kbuch und Fliste: (RN011206/1779,01_12_2006,2514041X27011206,16.1,16.1,F_Jovani,-)
		String[]headers={
			//"Menge","coDatum","KN","Betrag","Total","Driver","BuchBez"
			"Menge","Code","Bezeichnung","Betrag","Total"
		};
		new BR(file,headers,0);
	}*/
}
