// created on 28.10.2004 at 01:31

package com.units;
import com.search.*;
import com.options.ausTeilen;
import com.options.MyOp;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
public class WG{		
	double[]U;//umsatz
	int[]A;//Anzahl	
	double[] steuer;//steuer;
	String dir,datum;
	public static String Ausgabe;

	public WG(String dir,String datum){//nach dir bewerten
		this.dir=dir;
		this.datum=datum.toLowerCase();
		int size=getsptext().length;		
		U=new double[size];
		A=new int[size];		
		steuer=new double[size];		
		leeren();
		U=bewerten();				
		save();
		if(dir.equals("gastro/kdate") ||dir.equals("gastro/Kdate"))Ausgabe=pane();else
		Ausgabe=htmlPane();//zeige(Ausgabe=pane());
	}
	public WG(String[]data,String datum){//ob tag oder monat die daten werden steuerlich berechnet sehe TV.java Wg
		dir="gastro/date";
		this.datum=datum.toLowerCase();
		int size=getsptext().length;
		U=new double[size];
		A=new int[size];		
		steuer=new double[size];		
		leeren();
		U=bewerten(data);				
		Ausgabe=htmlPane();//zeige(Ausgabe=pane());		
		BH_liste();
	}
	public WG(String[]data,String datum,int ok){//ob tag oder monat die daten werden steuerlich berechnet sehe TV.java Wg
		dir="gastro/date";
		this.datum=datum.toLowerCase();
		int size=getsptext().length;
		U=new double[size];
		A=new int[size];		
		steuer=new double[size];		
		leeren();
		U=bewerten(data);				
	//	Ausgabe=htmlPane();//zeige(Ausgabe=pane());		
		//if(str.length>0 && new ebm.Tm(str,file).Ergebnis!=0)
		if(data.length>0 && new ebm.Tm(data,nFile()).Ergebnis!=0)	
		BH_liste();
	}
	public WG(String[]data, String dir,String datum){//es dient kellner program daher wird gelesen und geschrieben im dir
		this.dir=dir;
		this.datum=datum.toLowerCase();
		int size=getsptext().length;
		U=new double[size];
		A=new int[size];		
		steuer=new double[size];
		leeren();
		U=bewerten(data);
		Ausgabe=R();
	//	zeige(R());		
	}
	public WG(String[]data){//Rechnung ausdruck steuer zeile sehe GF oder DF.java
		dir="gastro/date";
		datum=new myDatum().d();
		int size=getsptext().length;
		U=new double[size];
		A=new int[size];		
		steuer=new double[size];
		leeren();
		U=bewerten(data);
		Ausgabe=pane();
	//	zeige(R());		
	}
	public WG(){dir="gastro/date";};
	void leeren(){for(int i=0;i<U.length;i++){U[i]=0.0;steuer[i]=0.0;A[i]=0;}}
	public void zeige(String str){new MyOp().fehler1(str);}
	public String TD(String str){if(str.length() > 7) return str.substring(3,str.length());else return str;}
	public String[]getsptext(){
		String[]sp = new sucheDate("gastro/source/wg.cfg").myDaten();//Date/sp-text.dat").myDaten();
		if(sp.length<1){
			String[] sp1={"Vorspeisen","Pasta","Pizza","Fleisch",
			"Fisch","Desert","Kaffee","Alko-frei","Alko","Spiri/Service"};
			sp=sp1;
			new save().dontsort("gastro/source/wg.cfg",sp,false);
		}
		return sp;
	}
	public String[]getStsatz(){
		String[]sp = new sucheDate("gastro/source/stsatz.cfg").myDaten();//Date/sp-text.dat").myDaten();
		if(sp.length<1){
			String[] sp1={"10","20","0"};
			sp=sp1;
			new save().dontsort("gastro/source/stsatz.cfg",sp,false);
		}
		return sp;
	}
	double[]divi(){
		String[]str=getStsatz();
		double[]div=new double[str.length];
		for(int i=0;i<str.length;i++){
			if(str[i].indexOf("%")!=-1)str[i]=str[i].substring(0,str[i].length()-1);
			try{
			double divis=Double.parseDouble(""+str[i])/100;
			div[i]=((1.0+divis)*100	)/(divis*100);
			}catch(Exception e){System.err.print("ZF!");div[i]=0.0;}
		}
		return div;
	}
	public String f(String str){return new Methoden().f(str);}
	public void save(){
		//save s=new save();	
		String[]str=new String[U.length];
		for(int i=0;i<U.length;i++)str[i]=""+i+","+A[i]+","+(float)U[i];
		  new save().dontsort(dir+"/w-spalten.dat",str,false);
	}
	public String htmlPane(){
		int art=0;
		double[]div=divi();
		String[]Tsatz=getStsatz();
		ausTeilen aus=new ausTeilen();
		String[]sp=getsptext();		
		String text="";
		double sum=0.0;double stsum=0.0;
		double speisen=0.0;double getrank=0.0;
		double u10=0.0; double u20=0.0;
		int Au10=0;int Au20=0;
		String str="<html><center><font size=+1> Spalten Verteilung der Periode "+datum+"</center><br><br>";
		str+="<table cellpadding=0 cellspacing=0 width=400 ><tr color=black><td> Bezeichnung</td><td align=right>Artikel</td><td align=right>Brutto</td><td align=right>% Satz</td><td align=right>MWstr</td></tr>";
		str+="<tr><td colspan=5><hr></td></tr>";
		int i=0;
		for(i=0;i<U.length;i++){
			if(i<6){
				speisen+=U[i];		
				steuer[i]+=(U[i]/div[0]);//10%
				u10+=steuer[i];
				Au10+=A[i];
				text=aus.randstZ(" "+Tsatz[0],8);
			}else{ 
				getrank+=U[i];				
				steuer[i]+=(U[i]/div[1]);//20%
				u20+=steuer[i];
				Au20+=A[i];				
				text=aus.randstZ(" "+Tsatz[1],8);
				}
			art+=A[i];
			sum+=U[i];
			stsum+=steuer[i];
			str+="<tr><td>"+sp[i]+"</td><td align=right>"+A[i]+"</td><td align=right>"+f(""+U[i])+"</td><td align=right>"+text+"</td><td align=right>"+f(""+steuer[i])+"</td></tr>";
			
		}
		str+="<tr><td colspan=5><hr></td></tr>";
		str+="<tr><td> Summe:</td><td align=right>"+art+"</td><td align=right>"+f(""+sum)+"</td><td></td><td align=right>"+f(""+stsum)+"</td></tr>";
		str+="<tr><td colspan=5></td></tr>";		
		str+="<tr><td colspan=5><b> Brutto Umsatz und Steuerverteilung</b></td></tr>";
		str+="<tr><td>"+Tsatz[0]+" Brutto:</td><td align=right>"+" "+Au10+"</td><td align=right>"+f(""+speisen)+"</td><td></td><td align=right>"+f(""+u10)+"</td></tr>";
		str+="<tr><td>"+Tsatz[1]+" Brutto:</td><td align=right>"+" "+Au20+"</td><td align=right>"+f(""+getrank)+"</td><td></td><td align=right>"+f(""+u20)+"</td></tr>";
		str+="<tr><td colspan=5></td></tr>";
		
		str+="<tr><td colspan=5><b> Steuerlast Verteilung und gesamt Ergebnis</b></td></tr>";
		str+="<tr><td>"+Tsatz[0]+" Netto:</td>"+"<td colspan=2 align=right>"+f(""+(speisen-u10))+"</td><td></td><td></td></tr>";		
		str+="<tr><td>"+Tsatz[1]+" Netto:</td>"+"<td colspan=2 align=right>"+f(""+(getrank-u20))+"</td><td></td><td></td></tr>";				
		
		str+="<tr><td>"+" Gesamt Netto:"+"</td><td colspan=2 align=right>"+f(""+(sum-stsum))+"<td></td><td></td></tr>";				
		str+="<tr><td>"+" Umsatzsteuer:"+"</td><td colspan=4 align=right>"+f(""+(u10+u20))+"</tr></table>";				
		
		//str+="			==============\n";
		//str+=aus.randstS(" Umsatzs Brutto:",24)+""+aus.randstZ(""+f(""+sum),16);	
		return str;
		
		
		
	}
	public String pane(){
		int art=0;
		double[]div=divi();
		String[]Tsatz=getStsatz();
		ausTeilen aus=new ausTeilen();
		String[]sp=getsptext();		
		String text="";
		double sum=0.0;double stsum=0.0;
		double speisen=0.0;double getrank=0.0;
		double u10=0.0; double u20=0.0;
		int Au10=0;int Au20=0;
		String str=" Spalten Verteilung :\n"+//TD(datum).replace('_','.')+"\n"+		
		     " _____________________________________________________________\n";
		str+=aus.randstS(" Bezeichnung",12)+" "+aus.randstZ("Artikel",10)+" "+aus.randstZ("Brutto",14)+
		""+aus.randstZ("% Satz",8)+""+aus.randstZ("MWstr",15)+"\n";
		str+=" _____________________________________________________________\n";
		int i=0;
		for(i=0;i<U.length;i++){
			if(i<6){
				speisen+=U[i];		
				steuer[i]+=(U[i]/div[0]);//10%
				u10+=steuer[i];
				Au10+=A[i];
				text=aus.randstZ(" "+Tsatz[0],8);
			}else{ 
				getrank+=U[i];				
				steuer[i]+=(U[i]/div[1]);//20%
				u20+=steuer[i];
				Au20+=A[i];				
				text=aus.randstZ(" "+Tsatz[1],8);
				}
			art+=A[i];
			sum+=U[i];
			stsum+=steuer[i];
			str+=aus.randstS(" "+sp[i],12)+" "+aus.randstZ(""+A[i],8)+" "+aus.randstZ(""+f(""+U[i]),16)+
			text+aus.randstZ(""+f(""+steuer[i]),16)+"\n";
		}
		str+=" _____________________________________________________________\n";
		str+=aus.randstS(" Summe:",12)+" "+aus.randstZ(""+art,8)+" "+aus.randstZ(""+f(""+sum),16)+
		aus.randstS(" ",8)+""+aus.randstZ(""+f(""+stsum),16)+"\n";
		//str+=" ________________________________________________________________________\n";
		str+="\n\n Brutto Umsatz und Steuer Verteilung:\n\n";
		str+=aus.randstS(" "+Tsatz[0]+" Brutto:",12)+" "+aus.randstZ(""+Au10,8)+" "+aus.randstZ(""+f(""+speisen),16)+
		aus.randstS(" ",8)+""+aus.randstZ(""+f(""+u10),16)+"\n";
		str+=aus.randstS(" "+Tsatz[1]+" Brutto:",12)+" "+aus.randstZ(""+Au20,8)+" "+aus.randstZ(""+f(""+getrank),16)+
		aus.randstS(" ",8)+""+aus.randstZ(""+f(""+u20),16)+"\n";;
		//str+=" ________________________________________________________________________\n";
		str+="\n\n Steuerlast Verteilung und gesamt Ergebnis:\n\n";
		str+=aus.randstS(" "+Tsatz[0]+" Netto:",20)+""+aus.randstZ(""+f(""+(speisen-u10)),16)+"\n";		
		str+=aus.randstS(" "+Tsatz[1]+" Netto:",20)+""+aus.randstZ(""+f(""+(getrank-u20)),16)+"\n";
		str+="			 ==============\n";
		str+=aus.randstS(" Gesamt:",20)+""+aus.randstZ(""+f(""+(sum-stsum)),16)+"\n";				
		str+=aus.randstS("\n UST:",20)+""+aus.randstZ(""+f(""+(u10+u20)),16)+"\n";	
		//str+="			==============\n";
		//str+=aus.randstS(" Umsatzs Brutto:",24)+""+aus.randstZ(""+f(""+sum),16);	
		return str;
	}
	public String R(){
		String str="";
		double[]div=divi();
		String[]Tsatz=getStsatz();		
		ausTeilen aus=new ausTeilen();
		double speisen=0.0;double getrank=0.0;
		double u10=0.0; double u20=0.0;
		for(int i=0;i<U.length;i++){
			if(i<6){
				speisen+=U[i];
				steuer[i]+=(U[i]/div[0]);
				u10+=steuer[i];				
			}else 
			if(i>=6){
				getrank+=U[i];			
				steuer[i]+=(U[i]/div[1]);
				u20+=steuer[i];				
			}
		}
		str+=aus.randstS(" "+Tsatz[0]+" Netto: ",16)+" "+aus.randstZ(f(""+(speisen-u10)),16)+
		" "+aus.randstZ(" "+Tsatz[0]+"",8)+" "+aus.randstZ(""+f(""+u10),8)+" "+aus.randstZ(f(""+speisen),8)+"\n";
		str+=aus.randstS(" "+Tsatz[1]+" Netto: ",16)+" "+aus.randstZ(f(""+(getrank-u20)),16)+
		" "+aus.randstZ(" "+Tsatz[1]+"",8)+" "+aus.randstZ(""+f(""+u20),8)+" "+aus.randstZ(f(""+getrank),8)+"\n";
		return str;
	}	
	public String[] RD(){
		String[] str=new String[2];
		double[]div=divi();
		String[]Tsatz=getStsatz();				
		double speisen=0;double getrank=0;
		double u10=0; double u20=0;
		for(int i=0;i<U.length;i++){
			if(i<6){
				speisen+=U[i];
				steuer[i]+=(U[i]/div[0]);
				u10+=steuer[i];				
			}else {
				getrank+=U[i];			
				steuer[i]+=(U[i]/div[1]);
				u20+=steuer[i];				
			}
		}
		str[0]=" "+Tsatz[0]+" Netto:,"+f(""+(speisen-u10))+",Ust "+Tsatz[0]+","+f(""+u10)+",Brutto:,"+f(""+speisen);
		str[1]=" "+Tsatz[1]+" Netto:,"+f(""+(getrank-u20))+",Ust "+Tsatz[1]+","+f(""+u20)+",Brutto:,"+f(""+getrank);		
		return str;
	}	
	public String[]wgSp(){
		new MyOp().fehler1("<Html><font size=2>Bitte nehmen Sie sich 5 Minuten Zeit!<br>"+
		                   "Nehmen Sie Ihre Preisliste in der Hand<br>"+
		                   "und beantworten Sie Folgenen Fragen<br>"+
		                   "ZB:Die Vorspeisen ist von ... bis ...?<br>"+
		                   "Antwort: von 260 bis 299<br>und Pizza von 100 bis 200"+
		                   "<br>und soweiter! Danke"
		                   );
		String[]sp = getsptext();
		String[]wgsp = new String[sp.length];
		JPanel jp=new JPanel();
		JPanel[] jpn = new JPanel[sp.length];
		JTextField[][]jft = new JTextField[sp.length][2];
		jp.setLayout(new BoxLayout(jp,BoxLayout.Y_AXIS));		
		for(int i=0;i<sp.length;i++){
			jpn[i]=new JPanel();
			jpn[i].setLayout(new BoxLayout(jpn[i],BoxLayout.X_AXIS));		
			JLabel jl=new JLabel(sp[i]+" von ");
			jl.setPreferredSize(new Dimension(120,10));
			JLabel jl1=new JLabel(" bis ");
			jft[i][0]=new JTextField("0");
			jft[i][1]=new JTextField("0");
			jpn[i].add(jl);
			jpn[i].add(jft[i][0]);
			jpn[i].add(jl1);
			jpn[i].add(jft[i][1]);
			jp.add(jpn[i]);
		}
		Object Tasta[]={"save","Abbrechen"};
	new JOptionPane().showOptionDialog(null,jp, "Daten Einlegen",
	   JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
	   null, Tasta, Tasta[0]);				
		for(int i=0;i<sp.length;i++){
			wgsp[i]=jft[i][0].getText()+","+jft[i][1].getText()+","+i;
		}
		new com.units.save().dontsort(dir+"/wsp.bew",wgsp,false);
		return wgsp;
	}
	public int spalte(int wc){
		//"0 Vorspeisen","1 Pasta","2 Pizza","3 Fleisch",
			//"4 Fisch","5 Desert","6 Kaffee","7 Alko-frei","8 Alko","9 Spiritus"};
		int wg=0;		
		String[]wgSpBew=new sucheDate(dir+"/wsp.bew").myDaten();
		if(wgSpBew.length<=0)wgSpBew=wgSp();
		for(int i=0;i<wgSpBew.length;i++){
			String[] wort=new ausTeilen().komma(wgSpBew[i]);					
			int a=Integer.parseInt(wort[0]);//von
			int b=Integer.parseInt(wort[1]);//bis				
			int c=Integer.parseInt(wort[2]);//dann Spalte ist c
			if(wc>=a && wc<=b){wg=c;}

		}
		return wg;
	}
	public String[] setSpalten(){
		String[]ware=new ebm.WD().lesen();
		if(ware.length>0){
		for(int i=0;i<ware.length;i++){
			String cod=new ausTeilen().koma(""+ware[i])[0];
			int invest=intmodi(cod);
			int sp=spalte(invest);
			ware[i]=cod+","+sp;			
		}
		new save().filefelde(dir+"/wl-spalten.dat",ware,false);		
		}
		return ware;
	}
	public String[] getSpalten(){
		String[]ware=new sucheDate(dir+"/wl-spalten.dat").myDaten();		
		if(ware.length>=0 && ware!=null)return ware;
		else return setSpalten();
	}
	
	void getSpaltenWert(String wc,int zahl,double betrag){			  
		String[]ware=getSpalten();
		if(ware.length>0){
		 for(int i=0;i<ware.length;i++){		
		 	String cod=new ausTeilen().koma(""+ware[i])[0];
		 	int sp=intmodi(new ausTeilen().koma(""+ware[i])[1].trim());		 	
			if(wc.equals(cod)){
				U[sp] = U[sp]+betrag;
				A[sp]=A[sp]+zahl;				
			}
		 }
		}	  
	}
	String BH_liste(){
		String bh=""+datum.toLowerCase();
		for(int i=0;i<U.length;i++)bh+=","+(float)U[i];		
		java.util.List<String>str=new java.util.ArrayList<String>();
		String[]bstr=bFileL();
		;//das problem er schreibt die daten 2mal
		for(int i=0;i<bstr.length;i++)
			if(bstr[i].toLowerCase().indexOf(datum)>-1)bstr[i]="-";		
		for(int i=0;i<bstr.length;i++)
			if(bstr[i]!="-")str.add(bstr[i]);
		str.add(bh);
		String[]nstr=new String[str.size()];
		for(int i=0;i<str.size();i++)
			nstr[i]=str.get(i);
		save_BHL(nstr);
		return bh;
	}
	
	String bFile(){
		String myd=datum.toLowerCase();
		if(myd.indexOf("n")>-1)myd=myd.substring(0,myd.length()-1);
		String j="";
		String m="";
		if(myd.length()>4){
			j=myd.substring(4,myd.length());
			m=myd.substring(2,myd.length());
		}else {
			j=myd.substring(2,myd.length());
			m=j;
		}		
		return ""+dir+"/data"+new myDatum().J()+"/tv"+j+"n/"+"bh"+m+"n.dat";		
	}
	String[]bFileL(){
		return new com.search.sucheDate(bFile().toLowerCase()).myDaten();
	}
	void save_BHL(String[]str){		
		new save().dontsort(bFile().toLowerCase(),str,false);		
		//new save().file(bFile(),str,true);		
	}
	
	String nFile(){
		String myd=datum.toLowerCase();
		if(myd.indexOf("N")>-1||myd.indexOf("n")>-1)myd=myd.substring(0,myd.length()-1);	
		String m="";
		if(myd.length()>4){			
			m=myd.substring(2,myd.length());
		}else m=myd;		
		return dir+"/data"+new myDatum().J()+
		"/tv"+m+"n/"+datum+".dat";
	}
	
	String[]dFile(){
		return new sucheDate(nFile()).myDaten();		
	}
	public double[] bewerten(){				//***bewerte das gesamte Monatabsatz***//
		String[]liste=dFile();//new sucheDate(nFile()).myDaten();		
		for(int i=0;i<liste.length;i++){
			String[] wort=new ausTeilen().koma(""+liste[i]);					
			int a=intmodi(""+wort[0].trim());//menge
			String wc=""+wort[1];//Integer.parseInt(wort[1]);//WareCode				
			double wert=Double.parseDouble(""+wort[4]);//Wert	
			getSpaltenWert(wc,a,wert);
		}
		return U;
	}
	public double[] bewerten(String[] data ){						
		String[]liste=data;
		for(int i=0;i<liste.length;i++){
			String[] wort=new ausTeilen().koma(""+liste[i]);					
			int a=intmodi(""+wort[0].trim());//menge
			String wc=""+wort[1];//Integer.parseInt(wort[1]);//WareCode				
			//int wc=Integer.parseInt(wort[1]);//WareCode				
			double wert=Double.parseDouble(""+wort[4]);//Wert	
			getSpaltenWert(wc,a,wert);
		}
		return U;
	}
	int intmodi(String str){		
		int modi=0;
		try{
			modi=Integer.parseInt(str);
		}catch(Exception ex){System.err.print(ex);modi=0;}
		return modi;
	}
	float fl(String str){
		float bet=0;
		try{
			bet=Float.parseFloat(str);
		}catch(Exception ex){bet=0;};
		return bet;
	}

}
