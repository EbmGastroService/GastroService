// created on 24.10.2006 at 00:28
package ebm;
import java.io.*;
import java.util.List;
import java.util.ArrayList;
public class TV{
	List<String> absatz;
	List<String> zusamen;
	String ziel;
	String von;String bis;
	String[]myverkauf;
	float ums=0;	
	int art=0;
	int bestellung;
	javax.swing.JEditorPane ta;
	javax.swing.JButton druck=new javax.swing.JButton("Drucken");
	public String endzeile;
	public TV(){	//aktuelle monatabsatz		
		ziel=new com.units.myDatum().ist_my();
		absatz=new ArrayList<String> ();
		zusamen=new ArrayList<String> ();
		druck.addActionListener(new druckAction());
		absatz=openmonat_umsatz();		
		new ebm.BW(absatz.size(),"");
		zusamen=zusamenfassen();
		zumtabele(zusamen,label_string(ums,art,zusamen.size()-1));
		saveIt(V_S(zusamen),myfile());
		wg(myfile());		
	}	
	public TV(String ziel){//absatz nachwahl die Tage oder monaten oder kunden Ortschaften
		this.ziel=ziel;	
		absatz=new ArrayList<String> ();
		zusamen=new ArrayList<String> ();
		druck.addActionListener(new druckAction());
		absatz=openmonat_umsatz();
		zusamen=zusamenfassen();
		zumtabele(zusamen,label_string(ums,art,zusamen.size()-1));
		saveIt(V_S(zusamen),myfile());
		wg(myfile());		
	}
	public TV(String ziel,boolean wie){//absatz nachwahl die Tage oder monaten oder kunden Ortschaften
		this.ziel=ziel;	
		absatz=new ArrayList<String> ();
		zusamen=new ArrayList<String> ();
		druck.addActionListener(new druckAction());
		absatz=fileUmsatz(openFile(myfile()));
		zusamen=zusamenfassen();
		zumtabele(zusamen,label_string(ums,art,zusamen.size()-1));
		if(!wie)saveIt(V_S(zusamen),myfile());
		wg(myfile());		
	}
	public TV(String ziel,String[]str,String file, boolean wie){//absatz nachwahl die Tage oder monaten oder kunden Ortschaften
		this.ziel=ziel;	
		absatz=new ArrayList<String> ();
		zusamen=new ArrayList<String> ();
		druck.addActionListener(new druckAction());
		absatz=fileUmsatz(str);
		zusamen=zusamenfassen();
		zumtabele(zusamen,label_string(ums,art,zusamen.size()-1));
		//if(!wie)
			saveIt(V_S(zusamen),myfile());
		wg(file);		
	}
	public TV(String ziel,int code){// class SK braucht nur die end Ergebnissen dieswegen ein code als differinzierung
		this.ziel=ziel;	
		absatz=new ArrayList<String> ();
		zusamen=new ArrayList<String> ();
		druck.addActionListener(new druckAction());
		absatz=openmonat_umsatz();	
		zusamen=zusamenfassen();
		new com.units.WG(V_S(zusamen),ziel,code);
		saveIt(V_S(zusamen),myfile());
		endzeile=zusamen.get(zusamen.size()-1);		
	}	
	String suchen(){
		String[]was={"Kunde","Datum"};
		int gesucht=new com.options.MyOp().frage1("<html>Was suchen Sie?<br>Oder Welche Ums&auml;tze soll gezeigt werden",was);
		String wahl="..";
		if(gesucht==0)wahl="K"+ziel;else wahl=ziel+"N";
		return wahl;
	}
	String[]die_ware(){
		String[]wl=new com.search.sucheDate("gastro/date/wliste.dat").myDaten();		
		return wl;
	}
	List<String> zusamenfassen(){
		List<String> erg=new ArrayList<String> ();
		String[]wl=die_ware();
		String absatzCode,wareCode;
		int[]wv=new int[wl.length];
		float[]Wert=new float[wl.length];
		ums=0;
		art=0;		
		for(int i=0;i<wv.length;i++){wv[i]=0;Wert[i]=0;}
		for(int i=0;i<absatz.size();i++){
			absatzCode=new com.options.ausTeilen().koma(absatz.get(i))[1].trim();//code=1
			for(int x=0;x<wl.length;x++){
				wareCode=new com.options.ausTeilen().koma(""+wl[x])[0].trim();//code=0
				if(wareCode.equals(absatzCode)){					
					String absatzMenge=new com.options.ausTeilen().koma(absatz.get(i))[0].trim();//code=1					
					String absatzWert=new com.options.ausTeilen().koma(absatz.get(i))[4].trim();//code=1					
					int menge=intcode(absatzMenge);		
					float preis=fl(absatzWert);				
					wv[x]+=menge;
					Wert[x]+=preis;
				}
			}
		}
		
		for(int i=0;i<wv.length;i++){
			if(wv[i]!=0){
				String[]ware=new com.options.ausTeilen().koma(""+wl[i]);//Preis=3				
			//	float preis=fl(ware[2]);				
				art+=wv[i];
				//float total=preis*wv[i];
				ums+=Wert[i];//total;
				String zeile=wv[i]+","+ware[0]+","+ware[1]+","+ware[2]+","+Wert[i];//total;
				erg.add(zeile);
		//		System.out.println(":: "+erg.elementAt(erg.size()-1));
			}
		}
		erg.add(art+",Artikel,Umsatz,Total ,"+ums);
		//System.out.println("\n::Umsatz: "+ums);	
		return erg;	
	}
	int intcode(String str){
		int code=0;
		try{
			code=Integer.parseInt(str);
		}catch(Exception ex){code=0;};
		return code;
	}
	float fl(String str){
		float bet=0;
		try{
			bet=Float.parseFloat(str);
		}catch(Exception ex){bet=0;};
		return bet;
	}
	String label_string(float sume,int art_anzahl,int bewegung){
		String label_str="<html><font color=blue>Auswertung: ";
		if(bestellung>0)label_str+=bestellung+" Bestellung<br>";else label_str+=ziel+"<br>";
		if(von!=null && bis!=null)label_str+="<br>Von "+von+" bis "+bis;
		else label_str+="";
		label_str+=
		"<br>Anzahl Zeilen: "+bewegung+" Zeilen"+
		"<br>Anzahl Artikeln: "+art_anzahl+
		"<br>Gesammt Umsatz: "+f(""+sume)+
		"<br>im Durchschnitt: "+f(""+sume/art_anzahl)+" pro Artikel";
		//"<br>im Durchschnitt: "+f(""+sume/bewegung)+" pro Zeile"+
		if(bestellung>0){label_str+=
		"<br>im Durchschnitt: "+f(""+sume/bestellung)+" pro Bestellung"+
		"<br>im Durchschnitt: "+g((double)art_anzahl/bestellung)+" Artikel pro Bestellung";
		}label_str+="<br><br></font>";//</center>";
		return label_str;
	}
	List<String> openmonat_umsatz(){		
		String[]daten=new String[0];		
		String suche=ziel.trim();
		String[]wl = new com.search.sucheDate(suche,"gastro/date/kon.dat",0).wahlinarrayJL();
		bestellung=wl.length;
		if(bestellung>0){		
		if(wl[0].charAt(0)==('R'))von=wl[0].substring(wl[0].indexOf("R")+1,wl[0].indexOf("N"));
		if(wl[wl.length-1].charAt(0)==('R'))bis=wl[wl.length-1].substring(wl[wl.length-1].indexOf("R")+1,wl[wl.length-1].indexOf("N"));
		}
		for(int i=0;i<wl.length;i++){					
			String rec="gastro/date/drucker/"+new ver6.Rcode(wl[i].trim(),1).genkr[0]+".dat";
			//System.out.println(i+":0: "+rec);
			daten=wv_add_rec(rec);							
			for(int x=0;x<daten.length;x++)				
				absatz.add(""+daten[x]);
			//System.out.println(absatz.elementAt(i).toString());
		}
		
		//zumtabele(absatz);
		return absatz;
	}
	List<String> fileUmsatz(String[]daten){
		for(int x=0;x<daten.length;x++)				
				absatz.add(""+daten[x]);	
		return absatz;
	}
		
	String[] wv_add_rec(String rec){
		String[]wl=new com.search.sucheDate(rec).myDaten();		
		return wl;
	}
	
	void zeige(){
		for(int i=0;i<absatz.size();i++)				
		System.out.println(absatz.get(i));
		
	}
	String[]V_S(List<String> tot){
		String[]str=new String[tot.size()-1];
		for(int i=0; i<str.length;i++){
			str[i]=tot.get(i);
		}
		return str;
	}
	String myfile(){		
		String file="";		
	//	System.out.println(ziel);
		String path="gastro/date/data"+new com.units.myDatum().J();
		if(ziel.indexOf("K")>-1 || ziel.indexOf("k")>-1){
			path+="/kunden";			
		}else {
			if(ziel.length()<=5)
				path+="/tv"+ziel;
			else
				path+="/tv"+ziel.substring(2,ziel.length());
		}					
		file=path+"/"+ziel+".dat";		
	//	System.out.println(file);
		return file.toLowerCase();
	}	
	String saveIt(String[]str,String file){	
		String mstr="";
		if(str.length>0){
			ebm.Tm myTm = new ebm.Tm(str,file);
			if(myTm.Ergebnis!=0){
				long modi=lastmod(file);			
				new com.units.save().dontsort(file,str,false);
				new com.units.save().sameModi(file,modi);//29/6/2011 New changed
				mstr=myTm.zeile;
			}
		}return mstr;
	}
	String[]openFile(String file){
		return new com.search.sucheDate(file).myDaten();	
	}
	long lastmod(String file){
		file=new com.units.save().chekfilename(file);
    	File ofile=new File(file);
    	long modi=ofile.lastModified();		
		return modi;
	}
	void wg(String file){			
		String[]str=openFile(file);
		if(str.length>0){
		ta=new javax.swing.JEditorPane("text/html",
		                           new com.units.WG(str,ziel).Ausgabe);
		ta.setEditable(false);
		//ta.setFont(new java.awt.Font("Courier New",0,12));//Font.ITALIC, 12));	
		javax.swing.JScrollPane js=new javax.swing.JScrollPane();	
		js.setViewportView(ta);
      	javax.swing.JFrame jf=new com.options.frame(" Umsatzsteuer Verteilung "+ziel,180);	
		jf.getContentPane().add(js,java.awt.BorderLayout.PAGE_START);	
		jf.getContentPane().add(druck,java.awt.BorderLayout.PAGE_END);		
		jf.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);		
		jf.pack();
		//jf.setSize(480,500);
		jf.setLocation(600,100);
		jf.setVisible(true);   
		ta.setBounds(0, 0, 640,800);	
		}	
	}
	String[][]VectorString(List<String> tot){
		String[]wort=new com.options.ausTeilen().koma(tot.get(0));
		String[][]str=new String[tot.size()][wort.length];//{"0","0","0","0","0"};
		for(int i=0; i<str.length;i++){
			String myvec=tot.get(i);
			wort=new com.options.ausTeilen().koma(myvec);
			for(int x=0; x<wort.length;x++)
			str[i][x]=""+wort[x];
		}
		return str;
		
	}
	void zumtabele(List<String> tot,String label){
		//zusamen=zusamenfassen();
		if(!tot.isEmpty()&& tot.size()>1){			
		String[][]str=VectorString(tot);
		//for(int i=0; i<str.length;i++){for(int x=0; x<str[i].length;x++)System.out.println(str[i][x]);}
		String titel="Zusammenfassung "+ziel	;
		int pos=100;int hight=200+str.length*12;if(hight>700)hight=700;
		String[]headers={"Menge","code","Bezeichnung","E-Preis","Total"};
		/*if (str[0].length>4){
			headers=new String[]{"Menge","code","Bezeichnung","E-Preis","Total"};
			titel="detail "+ziel;
			pos+=500;
		}*/		
		javax.swing.JScrollPane js=new javax.swing.JScrollPane();	
		js.setViewportView(new ebm.RcTab(str,headers).tab());
      	javax.swing.JFrame jf=new com.options.frame(titel+" Tabelle ",180);	
		jf.getContentPane().add(js,java.awt.BorderLayout.PAGE_START);
	  	jf.getContentPane().add(new javax.swing.JLabel(label,javax.swing.JLabel.CENTER));
		jf.pack();
		//jf.setSize(500,hight);
		jf.setLocation(pos,100);
		jf.setVisible(true);   		
		//if(new com.options.MyOp().Frage("Bericht Ausdrucken?")==0)new pit(js,ziel);		
		}
    }
    public String f(String in){
   	return new com.units.MyZahl().deci(Double.parseDouble(in));
   }
   public String g(double in){
   	return new com.units.MyZahl().gint(in);
   }
   void drucken(){
		new pit(ta,""+ziel);		
	}
   class druckAction implements java.awt.event.ActionListener {
        public void actionPerformed (java.awt.event.ActionEvent e ) {        	
        	String fr = e.getActionCommand();        	
        	if(fr.equals(druck.getText())){
        		drucken();
        	}
        }
	}
	public static void main(String[]args){
		if(args[0].length()>0)new TV(args[0]);
		//if(args == null)
		else System.out.println(new TV());
	}

}
