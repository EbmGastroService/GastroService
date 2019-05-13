// created on 20.07.2006 at 22:18
//Author Mourad Elbakry
//Vianna
package ebm;
import javax.swing.*;
import javax.swing.text.html.*;
import com.printer.JComponentVista;
import java.awt.print.*;
import java.awt.*;
import com.options.ausTeilen;
public class GF{
	JComponentVista vista;	
	String[]Firma;
	String[]Kunde;
	String[][]Rechnung;	
	String[]Kopf;	
	double Skale;
	String titel;
	JEditorPane editor;
	
	public GF(String titel,String[]firma,String[]kunde,String[][]rechnung,String[]kopf,double skale){	
		this.titel=titel;
		Firma=firma;
		Kunde=kunde;
		Rechnung=rechnung;
		Kopf=kopf;
		Skale= skale;
		int x=Rechnung.length;
		String formula="";
		int page=1;
		if(x>=18){
			formula=formular(0,18);
			seitendruck(formula,1);
			formula=formular(18,x);			
			seitendruck(formula,2);
		}else {
			formula=formular(0,18);
			seitendruck(formula,1);
		}	
	}
	public GF(){ 	 
		initDate();
 		int x=Rechnung.length;
		String formula="";
		int page=1;
		if(x>=18){
			formula=formular(0,18);
			seitenshow(formula,1);
			formula=formular(18,x);			
			seitenshow(formula,2);
		}else {
			formula=formular(0,18);
			seitenshow(formula,1);
		}	
 	//close();
 }
 	public void seitenshow(String formula , int page){
 		new com.options.EP(page,formula);	
	}
	public void seitendruck(String formula , int page){		
		editor = new JEditorPane("text/html",formula);	
		editor.setPreferredSize(new Dimension(400,640));
		JFrame f=new JFrame("Ebm GastroService 2006");
		f.setContentPane(new JScrollPane(editor)); 
		f.pack();
		f.setBounds(0, 0, 400,640);
		//f.setVisible(true);
		f.setDefaultCloseOperation(2);		
		vista = new JComponentVista(editor , new PageFormat()); 
 		vista.scaleToFit(true,Skale); 	
 		pb(page);
		f.dispose();
	}
	public void  pb(int p){
		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setJobName("RN_"+Kunde[1].trim()+"Seite_"+p);
		pj.setPageable(vista);
		try {
			pj.print();			
			} catch (PrinterException e) {System.out.println(e);}			
	}
	String formular(int an,int end){
		String Ezeit=new com.units.myDatum().time();
		Ezeit=Ezeit.substring(0,5);
		String str="<html><body bgcolor=#ffffff>"+
		"<center><font size=+2 face='Ms Serif'><b>"+Firma[0]+"</b></font>"+//ZurichCalligraphic face='Verdane'
		"<br><font size=4>"+Firma[2]+"</font>"+
		"<br><font size=3>"+Firma[1]+"</font>"+
		"<br><font size=3>UID: "+Firma[3]+"</font>"+
		"<br><br>"+
		"<table cellpadding=0 cellspacing=0 border=0 width=400>"+
		"<tr>"+
		"<td width=290>"+
		"<font size=4><b>KUNDEN Nr.: "+kundennummer()+" </b>"+
		"<br>Tel: "+Kunde[5]+"</font>"+
		"<br><font size=4 >"+Kunde[2]+
		"<br>"+Kunde[3]+
		"<br>"+Kunde[4]+"</font>"+
		"</td>"+
		/*"<td colspan=2 width=140 align=center><font size=2><b>Kundennummer</b><br>"+kundennummer()+
		"<br>Tel.:<br>"+Kunde[5]+"<br></font></td>"+*/
		"<td width=110>"+
		"<font size=4><b>RECHNUNGDATEN</b></font><br><font size=4>"+ Kunde[1]+
		"<br>"+new com.units.myDatum().d()+	"<br>Erfast um: <b>"+Ezeit+"</b></font>"+
		"<br><font size=4><b>K-Bestel NR: "+AzB()+"</b></font><br>"+
		"</td>"+
		"</tr>"+				
		"</table><br>";//"<hr width=400>"+
		if(Kopf[1].equals("Menge"))Kopf[1]="Stk";
		String tabelkopf="<table cellpadding=0 cellspacing=0 border=0 width=400>"+
		"<tr bgcolor=#eeeeee>"+
		"<td width=30><font size=4><b>"+Kopf[1]+"</b></font></td>"+
		"<td align=left width=240><font size=4><b>"+Kopf[2]+"</b></font></td>"+
		"<td width=50><font size=4><b>"+Kopf[3]+"</b></font></td>"+
		"<td align=right width=80><font size=4><b>"+Kopf[4]+"</b></font></td>"+		
		//"<tr><td colspan=4><font size=1><hr></font></td></tr>"+
		"</tr></table>";		
		String tabelbody="<table cellpadding=0 cellspacing=0 border=0 width=400>";
		int i=0;
		int myend=Rechnung.length;
		if(myend>end){myend=end;}
		int m=0;int wrp=0;
		for(i=an;i<myend;i++){			m++;wrp++;
			tabelbody+="<tr><td valign=top width=30><font size=4><b>"+Rechnung[i][1]+"</b></font></td>";
			tabelbody+="<td align=left width=240><font size=4><b>"+Rechnung[i][2]+"</b></font></td>";
			tabelbody+="<td align=right width=50><font size=3>"+f(""+Rechnung[i][3])+"</font></td>";
			double tot=Integer.parseInt(""+Rechnung[i][1])*Double.parseDouble(""+Rechnung[i][3]);
			tabelbody+="<td align=right width=80><font size=4><b>"+f(""+tot)+"</b></font></td>";
			tabelbody+="</tr>";		
			int Rlen=Rechnung[i][2].length();
			if(Rlen>25)wrp++;if(Rlen>49)wrp++;if(Rlen>74)wrp++;
		}
		//wenn lenge=8 dann add linie bis end=13		
		//if((end-an)>0){
		for(;wrp<18;wrp++){	
			tabelbody+="<tr><td colspan=4 width=400></td></tr>";
		}
		String endzeile="";
		if(an==0 && Rechnung.length >18){
			endzeile="<tr><td colspan=4 width=400><hr></td></tr><tr>"+
			"<td colspan=3><b>Ihre Zwischensumme ist</b></td>"+
			"<td align=right><b>"+f(sume(an,m))+"</b></td>"+"</tr>"			
			+"</table><font size=3><br>es folgt noch eine Seite:</font></center></body></html>";
		}else {
		tabelbody+="<tr><td colspan=4 width=400><hr></td></tr><tr>";
		tabelbody+="<td colspan=3><b>Ihre Rechnung Summe ist</b></td>";
		tabelbody+="<td align=right><b>"+f(sume())+"</b></td>"+"</tr>";
		tabelbody+="</table>";
		//new EP(str);
		String op=new com.search.sucheDate("gastro/source/op.cfg").md();	
		String wy=new com.search.sucheDate("gastro/source/wy.cfg").md();		
		String[]wt=new com.search.sucheDate("gastro/source/wt.cfg").myDaten();			
		endzeile="<font size=3>"+
		new com.units.WG(Record(),"gastro/date/drucker",new com.units.myDatum().ist()).Ausgabe+"</font>"+
		"<font size=2><br>Wir haben f&uuml;r Sie "+op+" ge&ouml;fnet"+
		"</font><br><font size=3><b>"+wt[0]+"</b> "+wt[1]+
		"<br><b>"+wy+"</b></font></center></body></html>";
		}
		str=str+tabelkopf+tabelbody+endzeile;
		
		return str;
	}
	
	/*public String[]Record(){
		return new com.search.sucheDate("gastro/date/drucker/"+titel+".dat").myDaten();
	}*/
	public String[]Record(){
		if(titel.indexOf("kellner-")>-1){String tit1=titel.substring(8,titel.length());
			return new com.search.sucheDate("gastro/kdate/Tische/"+tit1+".dat").myDaten();
		}else return new com.search.sucheDate("gastro/date/drucker/"+titel+".dat").myDaten();
	}
	
	public String f(String str){return new com.units.MyZahl().deci(Double.parseDouble(str));}
	 String kundennummer(){
	 	int cod=0;
	 	String str="";
	 	String erg=titel;
	 	if(erg.length()>0){
	 		for(int i=0; i<erg.length();i++){
	 			if(erg.charAt(i)=='X') cod=i;
	 		}
	 		str = erg.substring(0,cod);
	 	}else str=erg; 	
	 	return str;
	 }
	 String AzB(){//Anzahbestellung
	 	int cod=0;
	 	String str="";
	 	String erg=titel;
	 	if(erg.length()>0){
	 		for(int i=0; i<erg.length();i++){
	 			if(erg.charAt(i)=='X') cod=i;
	 		}
	 		str = erg.substring(cod+1,erg.length()-6);
	 		int in=Integer.parseInt(str);
	 		str=""+in;
	 	}else str=erg; 	
	 	return str;
	 }
	 public String sume(int von,int bis){
		Object[][]Rec = Rechnung; 			
		String str=""; 			
		double sum = 0.000;
 		int x=0;
 		for(int i=von;i<bis;i++){ 			
 			str=""+0.00; 	
 			int l=Rec[i].length; 		 
 			if ((Rec[i][l-2]!= null)&&(Rec[i][l-1]!= null)&&(Rec[i][l-2]!="")&&(Rec[i][l-1]!="")){	 					
 				str=Rec[i][l-1].toString(); 						 					
 			}	 			
 		try{
 			sum = sum +Double.parseDouble(str); 	 	
 			//}catch(IOException ioe){
 		}catch(NumberFormatException ioe){System.err.println("ZahlenFormat"+ioe);} 		
 		}
 		return(""+sum);
	}
	 
	 public String sume(){
		Object[][]Rec = Rechnung; 			
		String str=""; 			
		double sum = 0.000;
 		int x=0;
 		for(int i=0;i<Rec.length;i++){ 			
 			str=""+0.00; 	
 			int l=Rec[i].length; 		 
 			if ((Rec[i][l-2]!= null)&&(Rec[i][l-1]!= null)&&(Rec[i][l-2]!="")&&(Rec[i][l-1]!="")){	 					
 				str=Rec[i][l-1].toString(); 						 					
 			}	 			
 		try{
 			sum = sum +Double.parseDouble(str); 	 	
 			//}catch(IOException ioe){
 		}catch(NumberFormatException ioe){System.err.println("ZahlenFormat"+ioe);} 		
 		}
 		return(""+sum);
	}
	 void initDate(){	
	titel="1180186X02010107"; 	
 	Firma=new ebm.firma().data();
 	Kunde=new String[]{"K u n d e","R101006/01","Muster Vorname Nachname",
   	"Muster HauptStr 100/Stg1/7/54","0000 Gemeinde Bezirk","+436761234561"}; 
 	Rechnung=new String[][]
 	{
   		{"2","313","Tortellini della Casa","5.70","10.40"},
   		{"2","132","Pizza Pollo","6.90","12.80"},
   		{"2","112","Pizza Tonno","6.10","12.80"},   	   		
   		{"2","305","Spaghetti Carbonara","7.20","12.80"},   		
   		{"2","502","Forelle gegrilt mit Broccoli und Bratkartoffeln","6.90","12.80"},
   		{"5","306","Spaghetti Bolognise","5.20","12.80"},
   		{"2","302","Spaghetti alio oligo","5.00","12.80"},
 	    {"2","409","Gemischten Salat mit Thunfisch und Zwiebel","5.80","12.80"},
   		{"2","610","Winerschnitzel mit Pommes","7.20","12.80"},   		
   		{"2","701","Tiramaisu","3.50","7.00"},   		   		
   		{"2","610","Winerschnitzel mit Pommes","7.20","12.80"},   		
   		{"2","701","Tiramaisu","3.50","7.00"},   		
   		{"2","302","Spaghetti alio oligo","5.00","12.80"},
 	    {"2","408","Gemischten Salat mit geg. Putenstreifen","5.80","12.80"},
 	    {"2","403","Gemischten Salat mit Schinken und Ei","5.80","12.80"},
 	    {"2","405","Gemischten Salat mit Schafk.und Oliven","5.80","12.80"},
 	    {"2","409","Gemischten Salat mit Thunfisch und Zwiebel","5.80","12.80"},
 	    {"2","319","<u>Urlaub in Malorca</u> f&uuml;r 2 Persone VP inkl hin und Retur Flug mit Austria Airline!!! ","199.00","398.00"},
 	    {"2","318","<u>Urlaub in Italian</u> f&uuml;r 2 Persone VP inkl hin und Retur Flug mit Austria Airline!!! ","299.00","598.00"},
   		{"1","319","<u>Urlaub in Parisf&uuml;r 2 Persone VP inkl hin und Retur Flug mit Austria Airline!!! ","399.00","798.00"},   		
   		{"1","320","<u>Urlaub in Rodos</u> f&uuml;r 2 Persone VP inkl hin und Retur Flug mit Austria Airline!!! ","499.00","998.00"},   		   		
   		{"2","701","Tiramaisu","3.50","7.00"},   		   		
   		{"2","610","Winerschnitzel mit Pommes","7.20","12.80"},   		
   		{"2","701","Tiramaisu","3.50","7.00"},   		
   		{"2","302","Spaghetti alio oligo","5.00","12.80"},
   		{"1","90","Zustellgeb&uuml;hr","2.20","22.00"}   		
   	}; 	
 	String[][]rch1=new String[Rechnung.length][Rechnung[0].length];
 	for(int i=0;i<rch1.length;i++){
 		rch1[i][0]=""+i;
 		rch1[i][1]=""+Rechnung[i][0];
 		rch1[i][2]=""+Rechnung[i][2];
 		rch1[i][3]=""+Rechnung[i][3];
 		rch1[i][4]=""+Rechnung[i][4];
 	} 	
 	Rechnung=rch1;
 	new com.units.save().dfilefelde("gastro/k_r/"+titel+".dat",rch1,false);
 	new com.units.save().dfilefelde("gastro/date/drucker/"+titel+".dat",Rechnung,false);
 	Kopf=new String[]{"Pos","Stk","Bezeichnung","E-Preis","Total"};
 	
   }
	
}
