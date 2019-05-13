// created on 02.11.2006 at 03:05
package ebm;
import java.util.List ;
import java.util.ArrayList ;
public class SK{
	String sucheErg;	
	List<String> voll;
	String wahl;
	int pos=200;	
	public SK(){
		sucheErg=suchen();
		new TV(sucheErg);
	}
	public SK(String sucheErg){
		this.sucheErg=sucheErg;
		new TV(sucheErg);
	}
	public SK(int code){				
		voll=new ArrayList<String>();		
		suchenVoll(code);
		if(code<0)weiter();
	}
	public SK(String[]mykon){				
		voll=new ArrayList<String>();		
		myVoll(2,mykon);		
		myVoll(1,mykon);		
	}
	String[] kunde(){
		String[]wl =  new com.search.sucheDate("gastro/date/wkiste1.dat").myDaten();
		//for(int i=0;i<wl.length;i++)
			//wl[i]=""+new com.options.ausTeilen().koma(""+wl[i])[0];
		//warten((int)wl.length/10);
		return wl;
	}
	void myVoll(int gesucht,String[]kon){
		String[]date=new String[0];
		
		if(gesucht==1){date=add(datum(kon),1);wahl="Tag";}//220107
		if(gesucht==2){date=add(monaten(kon),2);wahl="Monat";}//0107		
		mywahlVoll(date,wahl);		
	}
	String[] abkurtzen(String[]wl){
		List<String> ort=new ArrayList<String>();
		//ort.addElement(wl[0]);
		for(int i=0;i<wl.length;i++){
			for(int x=i+1;x<wl.length;x++){
				if(wl[i].equals(wl[x]))wl[x]="-";						
			}	
		}
		for(int i=0;i<wl.length;i++){
			if(wl[i]!="-")ort.add(""+wl[i]);
		}
		wl=new String[ort.size()];
		for(int i=0;i<wl.length;i++)wl[i]=ort.get(i);
		//warten((int)wl.length/10);
		return wl;
	}
	String[] plz(){
		String[]wl =  kunde();
		for(int i=0;i<wl.length;i++)
			wl[i]=wl[i].substring(0,4);				
		return abkurtzen(wl);
	}
	String[] datum(String[]wl){		
		for(int i=0;i<wl.length;i++){								
			wl[i]=Rname(""+wl[i]);
		}				
		return abkurtzen(wl);		
	}
	String[] datum(){
		String[]wl = new com.search.sucheDate("gastro/date/kon.dat").myDaten();
		for(int i=0;i<wl.length;i++){					
			//String rn=new ver6.Rcode(wl[i].trim(),1).genkr[1];
			wl[i]=Rname(""+wl[i]);
		}				
		return abkurtzen(wl);		
	}
	String[]monaten(String[]str){
		String[]wl=datum(str);
		for(int i=0;i<wl.length;i++)
			if(wl[i].length()>5)wl[i]=wl[i].substring(2,6)+"N";else wl[i]+="N";				
		return abkurtzen(wl);
	}
	String[]monaten(){
		String[]wl=datum();
		for(int i=0;i<wl.length;i++)
			if(wl[i].length()>5)wl[i]=wl[i].substring(2,6)+"N";else wl[i]+="N";				
		return abkurtzen(wl);
	}
	String Rname(String zeile){                		
		//StringBuffer path=new StringBuffer(zeile);		
		//System.out.println("Problem zeile: "+zeile);
		String datum="";
		if(zeile.indexOf("R")>-1 && zeile.indexOf("N")>-1)
			datum=zeile.substring(zeile.indexOf("R")+1,zeile.indexOf("N"));else datum=zeile;
		return datum;
	}		
	String mywahl(String[]liste,String text){
		if(liste.length>0){
			//for(int i=0;i<liste.length;i++)liste[i]=liste[i]+"N";			
			return new com.options.MyOp().wahl(liste,text);
		}
		else return "0";
	}
	void warten(int i){		
		new ebm.BW(i,"");//"Sie werden ersucht um ein bischen getuld! gesammte Daten werden gesammelt");
	}
	void mywahlVoll(String[]wl,String txt){
		int art=0;
		float summe=0;		
		//warten((int)wl.length/10);
		if(wl.length>0){
		for(int i=0;i<wl.length;i++){	
			System.out.print(wl[i]+"|");
			//if(txt.equals("Monat"))wl[i]=wl[i]+"N";
			//if(wl[i].length()!=null)
			String last=new TV(wl[i],0).endzeile;
			String[]wort=new com.options.ausTeilen().koma(""+last);			
			//art+",Artikel,in Wert von ,"+ums
			last=wort[0]+","+wort[1]+","+wl[i]+","+wort[3];			
			float total=0;				
				try{
					total=Float.parseFloat(wort[3]);
				}catch(Exception ex){total=0;}
			summe+=total;
			int menge=0;
			try{
				menge=Integer.parseInt(wort[0]);
			}catch(Exception ex){menge=0;}
			art+=menge;			
			if(menge>0)voll.add(last);
		}
		}
		//voll.add(art+",Artikel,in Wert von ,"+summe);
		
		//zumtabele(voll,label_string(summe,art,voll.size(),txt));	//	return voll;	
	}
	String label_string(float sume,int art_anzahl,int bewegung,String txt){
		String label_str="<html><font color=blue>Auswertung:<br>"+
		"<br>Bewegung: "+bewegung+" "+txt+
		"<br>Anzahl artikeln: "+art_anzahl+
		"<br>Gesammt Umsatz: "+f(""+sume)+
		"<br>im Durchschnitt: "+f(""+sume/art_anzahl)+" pro Artikel"+
		"<br>im Durchschnitt: "+f(""+sume/bewegung)+" pro "+txt+
		"<br><br></font>";//</center>";
		return label_str;
	}
	String suchen(){
		String[]was={"<html><font color=red>Kundeumsatz","<html><font color=red>Tagesumsatz","<html><font color=red>Monatsumsatz","<html><font color=red>PLZ-umsatz"};
		int gesucht=new com.options.MyOp().frage1("<html><font color=blue>Absatz Bewertung<br>Welche Ums&auml;tze soll gezeigt werden?"+
		                                          "<br>Sie haben die Wahl ob Tag nach Tag oder Einzelne Monaten bewertet"+
		                                          "<br>Sie k&ouml;nnen jeder kunde oder Ortschaft in eine informelen Blatt betrachten"+
		                                          "<br> odre ausdrucken!",was);
		//String wahl="..";
		if(gesucht==0){
			wahl=mywahl(kunde(),"");
				wahl="K"+new com.options.ausTeilen().koma(""+wahl)[0];
		}
		else if(gesucht==1)wahl=mywahl(datum(),"")+"N";
		else if(gesucht==2)wahl=mywahl(monaten(),"");
		else if(gesucht==3)wahl="K"+mywahl(plz(),"");
		return wahl;
	}
	String[]add(String[]wl,int pos){
		for(int i=0;i<wl.length;i++){
			if(pos==0)wl[i]="K"+new com.options.ausTeilen().koma(""+wl[i])[0];			
			if(pos==1)wl[i]=wl[i]+"N";
			if(pos==2)wl[i]=wl[i]+"";
			if(pos==3)wl[i]="K"+wl[i];
		}
		return wl;
	}
	void weiter(){				
		while(new com.options.MyOp().frage1("<html><font color=blue>Kunden oder Ortschaft Bewertung",
		                                    new String[]{"Ja","Nein"})==0){
			pos+=100;voll=new ArrayList<String>();suchenVoll(-1);
		}
	}	
	void suchenVoll(int cod){//detailiert
		String[]was={"<html><font color=blue>Kundeumsatz","<html><font color=blue>Tagesumsatz","<html><font color=blue>Monatsumsatz","<html><font color=blue>PLZ-umsatz"};
		String[]date=new String[0];
		int gesucht=0;
		if(cod>-1 && cod<4)gesucht=cod;
		else gesucht=new com.options.MyOp().frage1("<html>Was suchen Sie? bzw. Welche Ums&auml;tze soll gezeigt und bewertet werden?"+
		                                           "<br>Wenn Sie Tagesumsatz drucken werden alle erfassten Ums&auml;tze aktualisiert"+
		                                           "<br>daher Bitte ich um etwas getuld!<br>",was);
		//String wahl="";
		if(gesucht==0){date=add(kunde(),0);wahl="Kunde";}
		if(gesucht==1){date=add(datum(),1);wahl="Tag";}//220107
		if(gesucht==2){date=add(monaten(),2);wahl="Monat";}//0107
		if(gesucht==3){date=add(plz(),3);wahl="Ortschaft";}
	//	warten();
		mywahlVoll(date,wahl);		
	}	
	void zumtabele(List<String> tot,String label){				
	  if(!tot.isEmpty()&& tot.size()>1){	  		  	
		String[]wort=new com.options.ausTeilen().koma(tot.get(0).toString());
		String[][]str=new String[tot.size()][wort.length];//{"0","0","0","0","0"};
		for(int i=0; i<str.length;i++){
			String myvec=tot.get(i);
			wort=new com.options.ausTeilen().koma(myvec);
			for(int x=0; x<wort.length;x++)
			str[i][x]=""+wort[x];
		}
		//for(int i=0; i<str.length;i++){for(int x=0; x<str[i].length;x++)System.out.println(str[i][x]);}
		String titel="Zusammenfassung ";//+ziel	;
		if(pos>500)pos=100;
		String[]headers={"Menge","Anzahl",wahl+" Bezeichnung","Total"};				
		javax.swing.JScrollPane js=new javax.swing.JScrollPane();	
		js.setViewportView(new ebm.RcTab(str,headers).tab());
      	javax.swing.JFrame jf=new com.options.frame(titel+" Tabelle "+wahl,180);	
		jf.getContentPane().add(js,java.awt.BorderLayout.PAGE_START);
	  	jf.getContentPane().add(new javax.swing.JLabel(label,javax.swing.JLabel.CENTER));
		jf.pack();
	  	jf.setSize(520,600);
		jf.setLocation(pos,100);
		jf.setVisible(true);      
	  }
    }
    public String f(String in){
   	return new com.units.MyZahl().deci(Double.parseDouble(in));
   }
	public static void main(String[]args){
		if(new com.options.MyOp().frage1("<html><font color=blue>Bewertung!",new String[]{"Datail","Gesamt"})==0){
			new SK();}else new SK(0);
	}
}
