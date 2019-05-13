// created on 01.11.2007 at 19:50
//Druckerformular
package egslver;
import com.printer.JComponentVista;
import java.awt.print.*;
import java.awt.event.*;
public class LSForm{	
	
	String formular(){
		float[]smn=Sumen();
		float brutto=fd(SVBGL);
		float netLohn=smn[1]-smn[2];
		float nettoEink=netLohn+smn[7];
		float mav=0;
		float netto=0;
		String zt="Gesamt Netto Bez&uuml;ge";		
		if(Anteil.indexOf("dga")>-1){
			mav=(nettoEink)*153/10000;
			netto=nettoEink+fd(""+smn[9]);
			netLohn=smn[1];smn[7]=smn[5];
			zt="Bez&uuml;ge inkl. Nebenkosten";
		}else{
			mav=((nettoEink+smn[9]-(smn[8]+smn[4])))*153/10000;
			netto=brutto-fd(""+Sum);
		}
		String str="<html><body bgcolor=#ffffff><font face='Times New Roman'>"+
		"<center>"+
		"<table cellpadding=0 cellspacing=0 border=0 width=640>"+
		"<tr>"+
		"<td collspan=5 >"+		
		"<b>"+Anteil.toUpperCase().substring(0,2)+"&nbsp;Abrechnung:&nbsp;"+datum+"</b>"+"&nbsp;Klient:&nbsp;"+K[0]+
		"<br>Firma:<b>&nbsp;"+K[1]+"</b>"+"<font size=-1>&nbsp;"+K[2]+" &nbsp;"+K[3]+"&nbsp;Tel.:&nbsp;"+K[5]+"</font>"+				
		"</td>"+
		"</tr>"+						
		"<tr>"+
		"<td collspan=5 >"+		
		"Mitarbeiter:&nbsp;"+M[0]+"&nbsp;&nbsp;"+M[1]+"&nbsp;"+M[2]+"&nbsp;"+M[3]+"&nbsp;Vers.:&nbsp;"+M[15]+"&nbsp;"+
		"&nbsp;"+M[10]+"&nbsp;Seit:&nbsp;"+M[11]+"&nbsp; als "+M[9]+""+		
		"</td>"+
		"</tr>"+				
		"</table>";
		String tabelkopf="<table cellpadding=0 cellspacing=0 border= width=640>"+
		"<tr bgcolor=#eeeeee>"+
		"<th align=left width=60>Code</th>"+
		"<th align=left width=300>Bezeichnung</th>"+
		"<th align=right width=80>Std</th>"+
		"<th align=right width=100>Std Satz</th>"+
		"<th align=right width=100>Lohn<br>Gehalt</th>"+				
		"</tr></table>";		
		String tabelbody="<table cellpadding=0 cellspacing=0 border=0 width=640>";
		tabelbody+="<tr><td valign=top width=60>"+"1001"+"</td>";
		tabelbody+="<td align=left width=300>"+"Lohn/Gehalt"+"</td>";
		tabelbody+="<td align=right width=80>"+"0"+"</td>";		
		tabelbody+="<td align=right width=100>"+f("0")+"</td>";		
		tabelbody+="<td align=right width=100><b>"+f(""+SVBGL)+"</b></td>";
		tabelbody+="</tr>";		
		if(fd(""+SZ_BGL)>0){
			tabelbody+="<tr><td valign=top width=60>"+"1002"+"</td>";
			tabelbody+="<td align=left width=300>"+"Sonder Zahlung WR/UR"+"</td>";
			tabelbody+="<td align=right width=80>"+"0"+"</td>";
			tabelbody+="<td align=right width=100>"+f("0")+"</td>";
			tabelbody+="<td align=right width=100><b>"+f(""+SZ_BGL)+"</b></td>";
			tabelbody+="</tr>";				
			brutto+=fd(""+SZ_BGL);
		}
		
		for(int i=0;i<2;i++){	
			tabelbody+="<tr><td border=0 colspan=5 width=640></td></tr>";
		}
		tabelbody+="<tr><td colspan=5 width=640>"+"<hr></td></tr><tr>";
		tabelbody+="<td colspan=4><b>Gesamt Brutto Bez&uuml;ge</b></td>";
		tabelbody+="<td align=right><b>"+f(""+brutto)+"</b></td>"+"</tr>";
		tabelbody+="</table><br>";
		
		String abgabenkopf="<table cellpadding=0 cellspacing=0 border=0 width=640>"+
		"<tr bgcolor=#eeeeee>"+"<th align=left width=180>Bezeichnung</th>"+
		"<th align=right width=115>Jahres<br>Summen</th>"+"<th align=right width=115>Soz./Lstr.<br>BMGL</th>"+
		"<th align=right width=115>Beitrags<br>Summen</th>"+
		"<th align=right width=115>Abzug<br>Beitrag</th>"+"</tr></table>";		
		
		abgabenkopf+="<table cellpadding=0 cellspacing=0 border=0 width=640>";
		abgabenkopf+="<tr>"+"<td align=left width=180>Soz. Beitag</td>"+
		"<td align=right width=115>"+f(""+netLohn)+//Lohnsummen
		"</td>"+"<td align=right width=115>"+f(""+SVBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[2])+"</td>"+//Beitragssummen
		"<th align=right width=115>"+f("0"+SVB)+"</th>"+"</tr>";//Versicherungsbeitrag
		
		abgabenkopf+="<tr>"+"<td align=left width=180>SZ Soz. Beitag</td>"+
		"<td align=right width=115>"+f(""+smn[7])+//SonderZahlungsummen
		"</td>"+"<td align=right width=115>"+f(""+SZ_BGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[6])+
		"</td>"+"<th align=right width=115>"+f(""+SZ_SVB)+"</th>"+"</tr>";
		
		abgabenkopf+="<tr>"+"<td align=left width=180>SZ Lstr. Beitag</td>"+
		"<td align=right width=115>"+f(""+0)+
		"</td>"+"<td align=right width=115>"+f(""+SZ_StBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[8])+"</td>"+"<th align=right width=115>"+f(""+SZ_LB)+"</th>"+"</tr>";	
		
		abgabenkopf+="<tr>"+"<td align=left width=180>Lohnsteuer</td>"+
		"<td align=right width=115>"+f(""+0)+"</td>"+"<td align=right width=115>"+f(""+StBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[4])+"</td>"+"<th align=right width=115>"+f(""+L_LB)+"</th>"+"</tr>";			
		
		abgabenkopf+="<tr><td colspan=5 width=640>"+"<hr></td></tr><tr>";
		abgabenkopf+="<tr>"+"<td align=left width=180>Summe Abz&uuml;ge</td>"+
		"<td align=right width=115>"+f(""+nettoEink)+"</td>"+"<td align=right width=115>MAV&nbsp;"+f(""+mav)+"</td>"+
		"<td align=right width=115>"+f(""+smn[9])+"</td>"+"<th align=right width=115>"+f(""+Sum)+"</th>"+"</tr>";	
		
		abgabenkopf+="<tr><td colspan=5 width=640>"+"<hr></td></tr><tr>";
		abgabenkopf+="<th align=left colspan=4>"+zt+"</th>";
		abgabenkopf+="<th align=right>"+f(""+netto)+"</th>"+"</tr>";	
		
		abgabenkopf+="</table>";			
		str=str+tabelkopf+tabelbody+abgabenkopf;
		str+="<br><br><br><br><br>----------------------------------------------<br><br><br><br>"+str+"</center></body></html>";
		return str;
	}
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	String[]MD(){
 		String file="egslv/k"+K[0]+"/lz"+Anteil+"_"+M[0]+"_"+datum.substring(2,datum.length())+".lz";
 		return open(file);
 	}
 	float[]Sumen(){ 		
 		String[]md=MD();
 		java.util.Arrays.sort(md);
 		String[]zin=Date(md[0]);
 		int bis=(int)fd(datum.substring(0,2));
 		float[]summen=new float[zin.length];
 		for(int i=0;i<summen.length;i++)summen[i]=0;
 		for(int i=0;i<bis;i++){
 			zin=Date(md[i]);
 			for(int x=1;x<zin.length;x++)summen[x]+=fd(zin[x]);
 		}
 		return summen;
 	}
 	String[]Date(String ld){				
		String[]LD =new com.options.ausTeilen().koma(ld);
		return LD;
	}
	float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}	
	String f(String str){return new com.units.MyZahl().deci(Float.parseFloat(str));}
	
	void show(){
		String form=formular();
		javax.swing.JEditorPane editor = new javax.swing.JEditorPane("text/html",form);		
		druck.addActionListener(new druckAction());
		javax.swing.JScrollPane js=new javax.swing.JScrollPane();	
		js.setViewportView(editor);
		blat=new javax.swing.JPanel();
		blat.setLayout(new java.awt.BorderLayout());		
		blat.add(js,java.awt.BorderLayout.PAGE_START);	
      	javax.swing.JFrame jf=new com.options.frame(Anteil+" Lohnabgaben "+datum,180);	
		jf.getContentPane().add(druck,java.awt.BorderLayout.PAGE_START);
		jf.getContentPane().add(blat,java.awt.BorderLayout.PAGE_END);		
		jf.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);		
		jf.pack();	
		int fpos=0;
		if(Anteil.indexOf("dga")>-1)fpos=600;else fpos=-50;
		jf.setLocation(fpos,10);
		jf.setVisible(true);      
		
	}	
	class druckAction implements ActionListener {
        public void actionPerformed (ActionEvent e ) {        	
        	String fr = e.getActionCommand();     
        	//System.out.println(fr+" Actioncommand und Button:"+druck.getText());
        	if(fr.equals(druck.getText())){
        		drucken();
        	}
        }
	}
	void drucken(){
		String form=formular();
		javax.swing.JEditorPane editor = new javax.swing.JEditorPane("text/html",form);	
		editor.setBounds(0, 0, 640,800);
		
		com.printer.JComponentVista vista = new com.printer.JComponentVista(editor , new java.awt.print.PageFormat()); 
 		//vista.scaleToFit(true,new com.printer.papier().getSkale()); 
		vista.scaleToFit(true,1.0); 	
 		java.awt.print.PrinterJob pj = java.awt.print.PrinterJob.getPrinterJob();
		pj.setJobName("Auftarg_");
		pj.setPageable(vista);
		try {
			pj.print();
		} catch (java.awt.print.PrinterException e) {System.out.println(e);}	
	}

	String[]K;
	String[]M;
	String[]D;
	String datum,Anteil;
	String SVBGL,SVB,StBGL,L_LB;
	String SZ_BGL,SZ_SVB,SZ_StBGL,SZ_LB,Sum;
	JComponentVista vista;
	javax.swing.JPanel blat;
	javax.swing.JButton druck=new javax.swing.JButton("Drucken");
	public LSForm(String Date,String[]K,String[]M,String Anteil){
		this.K=K;
		this.M=M;		
		this.Anteil=Anteil;
		D=Date(Date);		
	//	for(int i=0;i<D.length;i++)System.out.println(D[i]);
		datum=D[0];
		SVBGL=D[1];
		SVB=D[2];
		StBGL=D[3];
		L_LB=D[4];
		SZ_BGL=D[5];
		SZ_SVB=D[6];
		SZ_StBGL=D[7];
		SZ_LB=D[8];		
		Sum=D[9];	
	//	formular();
		show();
	}	
	public LSForm(String Date,String[]K,String[]M,String Anteil,String awt){
		this.K=K;
		this.M=M;		
		this.Anteil=Anteil;
		D=Date(Date);		
	//	for(int i=0;i<D.length;i++)System.out.println(D[i]);
		datum=D[0];
		SVBGL=D[1];
		SVB=D[2];
		StBGL=D[3];
		L_LB=D[4];
		SZ_BGL=D[5];
		SZ_SVB=D[6];
		SZ_StBGL=D[7];
		SZ_LB=D[8];		
		Sum=D[9];	
		//formular();
		//show();
	}	
}
