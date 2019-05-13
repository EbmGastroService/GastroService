// created on 15.11.2007 at 03:10

//zeigt eine taelle mit der Lohnkosten
package egslver;
public class LK{
	String[][]DGD;
	String K,M,datum;
	String Tab;
	float[]Gesamt;
	public LK(String K,String M,String datum){
		this.K=K;
		this.M=M;
		this.datum=datum;
		DGD=open_D("egslv/k"+K+"/ls/dgg_"+M+"_"+datum.substring(2,datum.length())+".dat");
		Tab="";
		new com.units.save().file("egslv/temp/mform.tmp","",false);				
		genTab();
	}
	public LK(String K,String M,String datum,int SollSein){
		this.K=K;
		this.M=M;
		this.datum=datum;
		DGD=open_D("egslv/k"+K+"/ls/dgg_"+M+"_"+datum.substring(2,datum.length())+".dat");
		Tab="";
		if(SollSein==1)Bericht_A();
		if(SollSein==2)showTab();
		if(SollSein==3)genBer();
	}
	void showTab(){		
		tabel(DGD,M);
		End();		
	}
	//0607,1500.0,270.0,187.70833,328.5,228.5625,22.95,67.5,7.5,193.62946,144.271,45.0
	void add(String[][]dgd,String m){				
		tabel(dgd,m);		
	}
	void add(String m){				
		Bericht(m);		
	}
	void End(){				
		Tab=HtmlKopf()+Tab+HtmlEnd();
		new com.units.save().file("egslv/temp/mform.tmp",Tab,false);		
	}
	String HtmlKopf(){
		return "<html><body bgcolor=#ffffff font size=-2><center><h1>Klient "+K+" Lohnkosten</h1>";
	}
	String HtmlEnd(){
		return "</center></body></html>";
	}
	String TKopf(){
		return "<table cellpadding=0 cellspacing=0 border=1 width=640>";		
	}
	String TEnd(){
		return "</table>";		
	}
	String tabel(String[][]dgd,String ma){	
		String[]head={"Datum","Brutto","SZ","DNA","DNSZ","DGA","DGSZ","MAV","DB","DZ","LStr","SZLstr","KStr"};
		//Tab+=TKopf();
		Tab+="<font size=-2>";
		Tab+="<table cellpadding=0 cellspacing=0 border=0 width=800>";		
		Tab+="<tr><tr><td colspan=11 width=800>"+ma+"</td></tr>";
		Tab+="<th align=center width=60>"+head[0]+"</th>";
		for(int i=1;i<head.length;i++)	Tab+="<th align=right width=74>"+head[i]+"</th>";
		Tab+="</tr>";
		int bis=(int)fd(datum.substring(0,2));	
		for(int i=0;i<bis;i++){
			Tab+="<tr><th align=center width=60>"+dgd[i][0]+"</th>";
			for(int x=1;x<dgd[i].length;x++){
				Tab+="<td align=right width=74>"+f(""+dgd[i][x])+"</td>";
			}
			Tab+="</tr>";						
		}		
 		float[]sumen=Sumen(ma); 		
		Tab+="<tr><th align=center >Gesamt</th>";
		for(int x=1;x<sumen.length;x++){
			Tab+="<th align=right>"+f(""+sumen[x])+"</th>";
		}
		Tab+=TEnd()+"</font>";		
		return Tab;
	}
	
	String Bericht(String ma){	
		String[]md=open("egslv/k"+K+"/sd/m"+ma+"_sd.dat");
		float[]sumen=Sumen(ma); 		
		Tab+="<font size=10>";
		Tab+="<table cellpadding=0 cellspacing=0 border=0 width=600>";
		Tab+="<tr><td><h2>Mitarbeiter: "+ma+" Lohnabrechnug "+datum+"</h2></td></tr>";
		Tab+="<tr><th><h4>"+md[1]+": "+md[2]+" "+md[5]+" "+md[6]+", "+md[4]+", "+md[8]+" als "+md[9]+", "+md[10]+" seit "+md[11]+"</h4></th><th></th>.</tr>";
		Tab+="<tr><td><h3><U>Abrechnung Brutto von"+" 01 bis "+datum+"</U></h3></td></tr>";	
		Tab+="<tr><th  align=left>Brutto Lohne:</th><th align=right>"+f(""+sumen[1])+"</th></tr>";		
		if(sumen[4]>0)Tab+="<tr><td>Brutto SZ:</td><td align=right> "+f(""+(sumen[2]))+"</td></tr>";
		Tab+="<tr><td>Brutto Gesamt:</td><td align=right> "+f(""+(sumen[1]+sumen[2]))+"</td></tr>";
		Tab+="<tr><td><h3><U>Abrechnung GKK</U></h3></td></tr>";	
		Tab+="<tr><td>DNA GKK:</td><td align=right>"+f(""+(sumen[3]))+"</td></tr>";
		if(sumen[4]>0)Tab+="<tr><td>DNA SZ GKK:</td><td align=right> "+f(""+(sumen[4]))+"</td></tr>";
		Tab+="<tr><th align=left>Gesamt DNA GKK: </th><th align=right>"+f(""+(sumen[3]+sumen[4]))+"</th></tr>";
		Tab+="<tr><th align=left><U>Gesamt Netto Lohn/Gehalt: </U></th><th align=right>"+f(""+((sumen[1]+sumen[2])-(sumen[3]+sumen[4])))+"</th></tr>";
		Tab+="<tr><td>DGA GKK: </td><td align=right>"+f(""+(sumen[5]))+"</td></tr>";
		if(sumen[4]>0)Tab+="<tr><td>DGA SZ GKK: </td><td align=right>"+f(""+(sumen[6]))+"</td></tr>";
		Tab+="<tr><td>DG MAV: </td><td align=right>"+f(""+(sumen[7]))+"</td></tr>";
		Tab+="<tr><th align=left>Gesamt DGA GKK: </th><th align=right>"+f(""+(sumen[5]+sumen[6]+sumen[7]))+"</th></tr>";
		Tab+="<tr><th align=left>Gesamt Beitrag an der GKK:</th><th align=right> "+f(""+(sumen[3]+sumen[4]+sumen[5]+sumen[6]+sumen[7]))+"</th></tr>";
		Tab+="<tr><td><h3><U>Finanzamt</U></h3></td></tr>";
		Tab+="<tr><td>DB:</td><td align=right>"+f(""+(sumen[8]))+"</td></tr>";
		Tab+="<tr><td>DZ:</td><td align=right>"+f(""+(sumen[9]))+"</td></tr>";
		Tab+="<tr><td>Lstr:</td><td align=right>"+f(""+(sumen[10]))+"</td></tr>";
		if(sumen[4]>0)Tab+="<tr><td>SZ Lstr: </td><td align=right>"+f(""+(sumen[11]))+"</td></tr>";
		Tab+="<tr><th align=left>Gesamt Beitrag an der FA:</th><th align=right> "
		+f(""+(sumen[8]+sumen[9]+sumen[10]+sumen[11]))+"</th></tr>";
		Tab+="<tr><td><h3><U>Gemeind Stadtkasse</U></h3></td></tr>";
		Tab+="<tr><td>Kommunal Steuer:</td><td align=right>"+f(""+(sumen[12]))+"</td></tr>";		
		Tab+="<tr><th align=left><U>Summe Abgaben:</U></th><th align=right>"+f(""+(sumen[3]+sumen[4]+sumen[5]+sumen[6]+sumen[7]+(sumen[8]+sumen[9]+sumen[10]+sumen[11])+sumen[12]))+"</th></tr>";		
		Tab+="<tr><td>____________________________________________________________</td></tr>";		
		Tab+=TEnd();
		Tab+="</font>";
		return Tab;
	}
	String Bericht_A(){			
 		String[]ma=open("egslv/k"+K+"_ma.dat");
		float[]sumen=new Sum(K,ma[0],datum).Sumen(0);
		Gesamt=sumen;				
 		for(int i=1;i<ma.length;i++){ 			 			
 			sumen=new Sum(K,ma[i],datum).Sumen(0);
 			for(int x=0;x<sumen.length;x++)	Gesamt[x]+=sumen[x]; 			
 		}		
		Tab+="<table cellpadding=0 cellspacing=0 border=0 width=600 >";
		Tab+="<tr><td><h2>Gesamt Personalabrechnug das Monat "+datum+"</h2></td></tr>";
		Tab+="<tr><td><h3><U>Abrechnung Brutto</U></h3></td></tr>";
		Tab+="<tr><th  align=left>Brutto Lohne:</th><th align=right>"+f(""+Gesamt[1])+"</th></tr>";
		if(Gesamt[4]>0)Tab+="<tr><td>Brutto SZ:</td><td align=right> "+f(""+(Gesamt[2]))+"</td></tr>";
		Tab+="<tr><td>Brutto Gesamt:</td><td align=right> "+f(""+(Gesamt[1]+Gesamt[2]))+"</td></tr>";
		Tab+="<tr><td><h3><U>Abrechnung GKK</U></h3></td></tr>";
		Tab+="<tr><td>DNA GKK:</td><td align=right>"+f(""+(Gesamt[3]))+"</td></tr>";		
		if(Gesamt[4]>0)Tab+="<tr><td>DNA SZ GKK:</td><td align=right> "+f(""+(Gesamt[4]))+"</td></tr>";
		Tab+="<tr><th align=left>Gesamt DNA GKK: </th><th align=right>"+f(""+(Gesamt[3]+Gesamt[4]))+"</th></tr>";
		Tab+="<tr><td>DGA GKK: </td><td align=right>"+f(""+(Gesamt[5]))+"</td></tr>";
		if(Gesamt[6]>0)Tab+="<tr><td>DGA SZ GKK: </td><td align=right>"+f(""+(Gesamt[6]))+"</td></tr>";
		Tab+="<tr><td>DG MAV: </td><td align=right>"+f(""+(Gesamt[7]))+"</td></tr>";
		Tab+="<tr><th align=left>Gesamt DGA GKK: </th><th align=right>"+f(""+(Gesamt[5]+Gesamt[6]+Gesamt[7]))+"</th></tr>";
		Tab+="<tr><th align=left>Gesamt Beitrag an der GKK:</th><th align=right> "+f(""+(Gesamt[3]+Gesamt[4]+Gesamt[5]+Gesamt[6]+Gesamt[7]))+"</th></tr>";
		Tab+="<tr><td><h3><U>Finanzamt</U></h3></td></tr>";
		Tab+="<tr><td>DB:</td><td align=right>"+f(""+(Gesamt[8]))+"</td></tr>";
		Tab+="<tr><td>DZ:</td><td align=right>"+f(""+(Gesamt[9]))+"</td></tr>";
		Tab+="<tr><td>Lstr:</td><td align=right>"+f(""+(Gesamt[10]))+"</td></tr>";
		if(Gesamt[4]>0)Tab+="<tr><td>SZ Lstr: </td><td align=right>"+f(""+(Gesamt[11]))+"</td></tr>";
		Tab+="<tr><th align=left>Gesamt Beitrag an der FA:</th><th align=right> "
		+f(""+(Gesamt[8]+Gesamt[9]+Gesamt[10]+Gesamt[11]))+"</th></tr>";
		Tab+="<tr><td><h3><U>Gemeind Stadtkasse</U></h3></td></tr>";
		Tab+="<tr><td>Kommunal Steuer:</td><td align=right>"+f(""+(Gesamt[12]))+"</td></tr>";		
		Tab+=TEnd();		
		End();
		return Tab;
	}
	//	0		1			2		 3		4
	//datum+","+brutto+","+sz+","+dnkk+","+szdnkk+","+
 	//	  5		   6	      7		 8	    9    	10	  11		12
 	//	dgkk+","+gszkk+","+mav+","+DB+","+DZ+","+lstr+","+lstrsz+","+Komm;
	String Bericht_B(){	
		Gesamt=new float[6];
 		String[]ma=open("egslv/k"+K+"_ma.dat");		
 		for(int i=0;i<ma.length;i++){ 			 			
 			float[]sumen=Sumen(ma[i]);
 			Gesamt[0]+=sumen[1];
 			Gesamt[1]+=sumen[2];
 			Gesamt[2]+=sumen[3]+sumen[4];
 			Gesamt[3]+=sumen[5]+sumen[6]+sumen[7];
 			Gesamt[4]+=sumen[8]+sumen[9]+sumen[10]+sumen[11];
 			Gesamt[5]+=sumen[12];
 		}
		Tab+="<font size=10>";
		Tab+="<table cellpadding=0 cellspacing=0 border=0 width=600>";
		Tab+="<tr><td><h2> Lohn Abrechnug "+datum+"</h2></td></tr>";
		Tab+="<tr><td><h3><U>Abrechnung GKK</U></h3></td></tr>";	
		Tab+="<tr><th  align=left>Brutto Lohne:</th><th align=right>"+f(""+Gesamt[0])+"</th></tr>";				
		Tab+="<tr><th  align=left>Brutto SZ:</th><th align=right>"+f(""+Gesamt[1])+"</th></tr>";				
		Tab+="<tr><th align=left>Gesamt DNA GKK: </th><th align=right>"+f(""+Gesamt[2])+"</th></tr>";						
		Tab+="<tr><th align=left>Gesamt DGA GKK: </th><th align=right>"+f(""+Gesamt[3])+"</th></tr>";
		Tab+="<tr><th align=left>Gesamt Beitrag an der GKK:</th><th align=right> "+f(""+(Gesamt[2]+Gesamt[3]))+"</th></tr>";
		Tab+="<tr><td><h3><U>Finanzamt</U></h3></td></tr>";
		Tab+="<tr><th align=left>FA DB DZ:</th><th align=right> "
		+f(""+(Gesamt[4]))+"</th></tr>";
		Tab+="<tr><td><h3><U>Gemeind Stadtkasse</U></h3></td></tr>";
		Tab+="<tr><td>Kommunal Steuer:</td><td align=right>"+f(""+(Gesamt[5]))+"</td></tr>";		
		Tab+=TEnd();
		Tab+="</font>";
		return Tab;
	}
	
	String[]lohnDate(String Ma){		
		String Mafile="egslv/k"+K+"/ls/dgg"+"_"+Ma+"_"+datum.substring(2,datum.length())+".dat";		
		return open(Mafile);
	}
	//BH Schnitt Stelle 
	String[]BL(String m){		
		java.util.List<String>list=new java.util.ArrayList<String>();		
		String[]md=lohnDate(m);		
		int bis=(int)fd(datum.substring(0,2));
		for(int i=0;i<bis;i++){
			list.add(md[i]);
		}	
 		String[]bl=new String[list.size()];
		for(int i=0;i<bl.length;i++){
			bl[i]=list.get(i);
		}
		save(bl);
		return bl;
 	} 	
 	String[]Date(String ld){				 		
 		String[]LD =new com.options.ausTeilen().koma(ld);
		return LD;
	}
 	float[]Sumen(String m){
 		return new Sum(K,m,datum).Sumen();
 	}
 	void genTab(){
 		String[]kma=open("egslv/k"+K+"_ma.dat");
 		for(int i=0;i<kma.length;i++){
 			BL(kma[i]);
 			String[][]str=open_D("egslv/temp/lb.tmp");
 			tabel(str,kma[i]); 			
 		}
 		End();
 	}
 	void genBer(){ 	 		
 		String[]kma=open("egslv/k"+K+"_ma.dat");
 		for(int i=0;i<kma.length;i++){ 			 			
 			Bericht(kma[i]); 			
 		} 					
 		End();
 	}
 	String[]BLSumen(){
 		String[][]str=open_D("egslv/temp/lb.tmp");	
 		String[]stSum=new String[str[0].length];
 		float[]sum=new float[str[0].length];
 		for(int i=0;i<str.length;i++){			
 			stSum[0]="Gesamt";sum[0]=0;
			for(int x=1;x<str[i].length;x++){
				sum[x]+=fd(str[i][x]);
				stSum[x]=f(""+sum[x]);
			}	
		}
 		return stSum;
 	} 	
	String[][]open_D(String file){
 		return new com.search.sucheDate(file).teiledaten();
 	}
 	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	void save(String[]dateLine){
		String file="egslv/temp/lb.tmp";					
		new com.units.save().dontsort(file,dateLine,false);
 	}
	
	float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}	
	String f(String str){return new com.units.MyZahl().deci(Float.parseFloat(str));}	

}
