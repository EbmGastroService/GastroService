// created on 01.11.2007 at 19:50
//Druckerformular
package egslver;
public class Form{	
	
	String formular(){
		float[]smn=Sumen();
		//float[]DNsmn=SumenDN();
		float brutto=fd(SVBGL);
		float ecard=0;
		String line="<br><br><br>";
		if(datum.indexOf("12")>-1){
			if(datum.substring(0,2).equals("12")){
				ecard=10;
				line="<br><br>";
			}
		}else ecard=0;
		if(fd(""+SZ_BGL)>0)brutto+=fd(""+SZ_BGL);
		//float[]dgsum=new Sum(K[0],M[0],datum).Sumen();
		float netLohn=smn[1]-smn[3];
		float netSZ=smn[2]-smn[4];
		float nettoEink=(netLohn+netSZ)-(smn[10]+smn[11]);
		float mav=smn[7];
		float netto=brutto-ecard-(fd(""+Sum));			
		float DB=0;
		float DZ=0;
		float Komm=0;
		float dnOdg=-1;
		String zt="Gesamt Netto Bez&uuml;ge";							
		String DG="";
		String HD="";		
		String abgabenkopf="<table cellpadding=0 cellspacing=0 border=0 width=640>"+
		"<tr bgcolor=#eeeeee>"+"<th align=left width=180>Bezeichnung</th>"+
		"<th align=right width=115>Jahres<br>Summen</th>"+"<th align=right width=115>Soz./Lstr.<br>BMGL</th>"+
		"<th align=right width=115>Beitrags<br>Summen</th>"+
		"<th align=right width=115>Abzug<br>Beitrag</th>"+"</tr></table>";		
	/*					0		1		  2		 3			4
	String dateLineV=datum+","+brutto+","+sz+","+dnkk+","+szdnkk+","+
 	*	5	   6	    7		8	   9    	10	    11		 12
 	*dgkk+","+szgkk+","+mav+","+DB+","+DZ+","+lstr+","+lstrsz+","+Komm; 
 	*	0	1		2 	3	  4	   5	  6	  7	   8	  9	  10	11	12	
 	* 0111,1200.0,0.0,218.40,0.0,267.00,0.0,18.36,54.00,5.04,20.47,0.0,36.0	
 	*/
		if(Anteil.indexOf("dga")>-1){			
			dnOdg=0;
			netLohn=smn[1];
			netSZ=smn[2];
			nettoEink=netLohn+smn[2];
			float lstr=smn[10]+smn[11];					
			mav=smn[7];
			DB=smn[8];
			DZ=smn[9];
			Komm=smn[12];
			float FA=DB+DZ+lstr;
			float gkk_dn=(ecard+smn[3]+smn[4]);	
			float gkk_dg=(smn[5]+smn[6]+mav);
			float gabgaben=gkk_dn+gkk_dg+FA+Komm;
			netto=nettoEink+gabgaben-gkk_dn;
			
			zt="Bez&uuml;ge inkl. Nebenkosten";
			DG="<tr><td border=0 colspan=5 width=640></td></tr>";
			DG+="<tr bgcolor=#eeeeee>"+"<th align=left width=180>Dienstgeber Abgaben</th>"+
			"<th align=right width=115>DB</th>"+
			"<th align=right width=115>DZ</th>"+
			"<th align=right width=115>LStr</th>"+
			"<th align=right width=115>Gesamt</th>"+
			"</tr>";
			DG+="<tr>"+"<td align=left width=180>Finanzamt</td>"+
			"<td align=right width=115>"+f(""+DB)+"</td>"+
			"<td align=right width=115>"+f(""+DZ)+"</td>"+
			"<td align=right width=115>"+f(""+lstr)+"</td>"+
			"<th align=right width=115>"+f(""+FA)+"</th>"+
			"</tr>";
			
			DG+="<tr>"+"<th colspan=5 align=left></th>"+"</tr>";
			DG+="<tr>"+"<td align=left width=180>Gemeinde</td>"+			
			"<th colspan=4 align=right>"+f(""+Komm)+"</th>"+
			"</tr>";
			//DG+="<tr>"+"<th colspan=5 align=left></th>"+"</tr>";
			DG+="<tr bgcolor=#eeeeee>"+"<th align=left width=180></th>"+
			"<th align=right width=115>DN</th>"+
			"<th align=right width=115>DG</th>"+
			"<th align=right width=115>MAV</th>"+
			"<th align=right width=115></th>"+
			"</tr>";
			
			DG+="<tr>"+"<td align=left width=180>GKK</td>"+
			"<td align=right width=115>"+f(""+gkk_dn)+"</td>"+
			"<td align=right width=115>"+f(""+(gkk_dg-mav))+"</td>"+
			"<td align=right width=115>"+f(""+mav)+"</td>"+
			"<th align=right width=115>"+f(""+(gkk_dn+gkk_dg))+"</th>"+
			"</tr>";			
			DG+="<tr bgcolor=#eeeeee>"+"<th align=left width=180>Gesamt Abgaben</th>"+
			"<td align=right width=115>"+f(""+(gkk_dn+lstr))+"</td>"+
			"<td align=right width=115>"+f(""+(gabgaben-(gkk_dn+lstr)))+"</td>"+
			"<td align=right width=115>"+f(""+0)+"</td>"+
			"<th border=1 align=right width=115>"+f(""+gabgaben)+"</th>"+
			"</tr>";
			
			//smn[9]=DNsmn[9];
			abgabenkopf="<table cellpadding=0 cellspacing=0 border=0 width=640>"+
			"<tr bgcolor=#eeeeee>"+"<th align=left width=180>Bezeichnung</th>"+
			"<th align=right width=115>Jahres<br>Summen</th>"+
			"<th align=right width=115>SV/Lstr.<br>BMGL</th>"+
			"<th align=right width=115>DNA<br>Summen</th>"+
			"<th align=right width=115>Gesamt<br>Beitarg</th>"+"</tr></table>";
		}
		
		String str1="<html><body bgcolor=#ffffff><font face='Times New Roman'><center>";
		String str="<table cellpadding=0 cellspacing=0 border=0 width=640>"+
		"<tr>"+
		"<td collspan=5 >"+		
		"<b>"+Anteil.toUpperCase().substring(0,2)+"&nbsp;Abrechnung:&nbsp;"+datum+"</b>"+"&nbsp;Klient:&nbsp;"+K[0]+
		"<br>Firma:<b>&nbsp;"+K[1]+"</b>"+"<font size=-1>&nbsp;"+K[2]+" &nbsp;"+K[3]+"&nbsp;Tel.:&nbsp;"+K[5]+"</font>"+				
		"</td></tr>"+						
		"<tr><td collspan=5 >"+		
		"Mitarbeiter:&nbsp;"+M[0]+"&nbsp;&nbsp;"+M[1]+"&nbsp;"+M[2]+"&nbsp;"+M[3]+"&nbsp;Vers.:&nbsp;"+M[15]+"&nbsp;"+
		"&nbsp;"+M[10]+"&nbsp;Seit:&nbsp;"+M[11]+"&nbsp; als "+M[9]+""+		
		"</td></tr>"+				
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
		}
		
		for(int i=0;i<2;i++){	
			tabelbody+="<tr><td border=0 colspan=5 width=640></td></tr>";
		}
		tabelbody+="<tr><td colspan=5 width=640>"+"<hr></td></tr><tr>";
		tabelbody+="<td colspan=4><b>Gesamt Brutto Bez&uuml;ge</b></td>";
		tabelbody+="<td align=right><b>"+f(""+brutto)+"</b></td>"+"</tr>";
		tabelbody+="</table><br>";		
	/*					0		1		  2		 3			4
	String dateLineV=datum+","+brutto+","+sz+","+dnkk+","+szdnkk+","+
 	*	 5		   6	    7		8	   9    	10		 11		 12
 	*	dgkk+","+szgkk+","+mav+","+DB+","+DZ+","+lstr+","+lstrsz+","+Komm; 	
 	**/
	
		abgabenkopf+="<table cellpadding=0 cellspacing=0 border=0 width=640>";
		abgabenkopf+="<tr>"+"<td align=left width=180>SV Beitag</td>"+
		"<td align=right width=115>"+f(""+netLohn)+//Lohnsummen
		"</td>"+"<td align=right width=115>"+f(""+SVBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[3])+"</td>"+//Beitragssummen
		"<th align=right width=115>"+f("0"+SVB)+"</th>"+"</tr>";//Versicherungsbeitrag
		
		abgabenkopf+="<tr>"+"<td align=left width=180>SZ SV Beitag</td>"+
		"<td align=right width=115>"+f(""+netSZ)+//SonderZahlungsummen
		"</td>"+"<td align=right width=115>"+f(""+SZ_BGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[4])+
		"</td>"+"<th align=right width=115>"+f(""+SZ_SVB)+"</th>"+"</tr>";
		
		abgabenkopf+="<tr>"+"<td align=left width=180>SZ Lstr. Beitag</td>"+
		"<td align=right width=115>"+f(""+smn[11]*dnOdg)+
		"</td>"+"<td align=right width=115>"+f(""+SZ_StBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[11])+"</td>"+"<th align=right width=115>"+f(""+SZ_LB)+"</th>"+"</tr>";	
		
		abgabenkopf+="<tr>"+"<td align=left width=180>Lohnsteuer</td>"+
		"<td align=right width=115>"+f(""+smn[10]*dnOdg)+"</td>"+"<td align=right width=115>"+f(""+StBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[10])+"</td>"+"<th align=right width=115>"+f(""+L_LB)+"</th>"+"</tr>";			
		float ges=smn[3]+smn[4]+smn[10]+smn[11];
		abgabenkopf+="<tr><td colspan=5 width=640>"+"<hr></td></tr><tr>";
		abgabenkopf+="<tr>"+"<td align=left width=180>Summen</td>"+
		"<td align=right width=115>"+f(""+nettoEink)+"</td>"+"<td align=right width=115>MAV&nbsp;"+f(""+mav)+"</td>"+
		"<td align=right width=115>"+f(""+ges)+"</td>"+"<th align=right width=115>"+f(""+Sum)+"</th>"+"</tr>";	
		if(Anteil.indexOf("dna")>-1 && datum.indexOf("12")>-1)
			abgabenkopf+="<tr><td align=left colspan=4>E-Card/Sonstige</td>"+"<th align=right>"+f(""+ecard)+"</th>"+"</tr>";
		if(Anteil.indexOf("dga")>-1)abgabenkopf+=DG;
		
		abgabenkopf+="<tr><td colspan=5 width=640>"+"<hr></td></tr><tr>";
		abgabenkopf+="<th align=left colspan=4>"+zt+"</th>";
		abgabenkopf+="<th align=right>"+f(""+netto)+"</th>"+"</tr>";	
		
		abgabenkopf+="</table>";			
		//str=str;
		if(Anteil.indexOf("dna")>-1)str+=tabelkopf+tabelbody+abgabenkopf;else str+=abgabenkopf;
		if(Anteil.indexOf("dna")>-1)			
		str+="<h5>alle Vepflichtung aus dem KV sind mit der Auszahlung erf&uuml;hlt</h5>"+
		"<h3>Betrag Dankend Erhalten</h3><br>.............<br>"+line+"<hr>"+str;
		str1+=str+"</center></body></html>";
		return str1;
	}
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	float[]Sumen(){
 		return new Sum(K[0],M[0],datum).Sumen();
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

	String[]K;
	String[]M;
	String[]D;
	String datum,Anteil;
	String SVBGL,SVB,StBGL,L_LB;
	String SZ_BGL,SZ_SVB,SZ_StBGL,SZ_LB,Sum;
	
	public Form(String Date,String[]K,String[]M,String Anteil){
		this.K=K;
		this.M=M;		
		this.Anteil=Anteil;
		D=Date(Date);			
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
		String mForm=formular();
		new com.units.save().file("egslv/temp/mform.tmp",mForm,false);
		//new showMe(mForm,datum,Anteil);
	}	
	public Form(String Date,String[]K,String[]M,String Anteil,String awt){
		this.K=K;
		this.M=M;		
		this.Anteil=Anteil;
		D=Date(Date);			
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
		String mForm=formular();
		new com.units.save().file("egslv/temp/mform.tmp",mForm,false);
	}	
}
