// created on 01.11.2007 at 19:50
//Druckerformular
package egslver;
public class formtext{		
	String formular(){
		float[]smn=Sumen();
		float[]DNsmn=SumenDN();
		float brutto=fd(SVBGL);
		float ecard=0;
		String line="<br><br><br><br>";
		if(datum.indexOf("12")>-1){
			if(datum.substring(0,2).equals("12")){
				ecard=10;
				line="<br><br>";
			}
		}else ecard=0;
		if(fd(""+SZ_BGL)>0)brutto+=fd(""+SZ_BGL);
		float[]dgsum=new Sum(K[0],M[0],datum).Sumen();
		float netLohn=dgsum[1]-dgsum[3];
		float nettoEink=(netLohn+smn[7])-(smn[4]+smn[8]);;
		float mav=dgsum[7];
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
	//	0		1			2		 3		4
	//datum+","+brutto+","+sz+","+dnkk+","+szdnkk+","+
 	//	  5		   6	      7		 8	    9    	10	  11		12
 	//	dgkk+","+gszkk+","+mav+","+DB+","+DZ+","+lstr+","+lstrsz+","+Komm;		
		if(Anteil.indexOf("dga")>-1){
			
			dnOdg=0;
			smn[7]=smn[5];
			smn[2]=DNsmn[2];
			smn[4]=DNsmn[4];
			smn[6]=DNsmn[6];
			smn[8]=DNsmn[8];		
			netLohn=smn[1];
			nettoEink=netLohn+smn[7];
			float lstr=dgsum[9]+dgsum[10];					
			mav=dgsum[6];
			DB=dgsum[7];
			DZ=dgsum[8];
			Komm=dgsum[11];
			float FA=DB+DZ+lstr;
			float gkk_dn=ecard+DNsmn[9]-lstr;	
			float gkk_dg=smn[9]-(gkk_dn+mav);
			float gabgaben=smn[9]+FA+Komm;
			netto=nettoEink+gkk_dg+DB+DZ+Komm+mav;
			
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
			"<td align=right width=115>"+f(""+gkk_dg)+"</td>"+
			"<td align=right width=115>"+f(""+mav)+"</td>"+
			"<th align=right width=115>"+f(""+smn[9])+"</th>"+
			"</tr>";			
			DG+="<tr bgcolor=#eeeeee>"+"<th align=left width=180>Gesamt Abgaben</th>"+
			"<td align=right width=115>"+f(""+(gkk_dn+lstr))+"</td>"+
			"<td align=right width=115>"+f(""+(gabgaben-(gkk_dn+lstr)))+"</td>"+
			"<td align=right width=115>"+f(""+0)+"</td>"+
			"<th border=1 align=right width=115>"+f(""+gabgaben)+"</th>"+
			"</tr>";
			
			smn[9]=DNsmn[9];
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
	
		abgabenkopf+="<table cellpadding=0 cellspacing=0 border=0 width=640>";
		abgabenkopf+="<tr>"+"<td align=left width=180>SV Beitag</td>"+
		"<td align=right width=115>"+f(""+netLohn)+//Lohnsummen
		"</td>"+"<td align=right width=115>"+f(""+SVBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[2])+"</td>"+//Beitragssummen
		"<th align=right width=115>"+f("0"+SVB)+"</th>"+"</tr>";//Versicherungsbeitrag
		
		abgabenkopf+="<tr>"+"<td align=left width=180>SZ SV Beitag</td>"+
		"<td align=right width=115>"+f(""+smn[7])+//SonderZahlungsummen
		"</td>"+"<td align=right width=115>"+f(""+SZ_BGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[6])+
		"</td>"+"<th align=right width=115>"+f(""+SZ_SVB)+"</th>"+"</tr>";
		
		abgabenkopf+="<tr>"+"<td align=left width=180>SZ Lstr. Beitag</td>"+
		"<td align=right width=115>"+f(""+smn[8]*dnOdg)+
		"</td>"+"<td align=right width=115>"+f(""+SZ_StBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[8])+"</td>"+"<th align=right width=115>"+f(""+SZ_LB)+"</th>"+"</tr>";	
		
		abgabenkopf+="<tr>"+"<td align=left width=180>Lohnsteuer</td>"+
		"<td align=right width=115>"+f(""+smn[4]*dnOdg)+"</td>"+"<td align=right width=115>"+f(""+StBGL)+"</td>"+
		"<td align=right width=115>"+f(""+smn[4])+"</td>"+"<th align=right width=115>"+f(""+L_LB)+"</th>"+"</tr>";			
		
		abgabenkopf+="<tr><td colspan=5 width=640>"+"<hr></td></tr><tr>";
		abgabenkopf+="<tr>"+"<td align=left width=180>Summen</td>"+
		"<td align=right width=115>"+f(""+nettoEink)+"</td>"+"<td align=right width=115>MAV&nbsp;"+f(""+mav)+"</td>"+
		"<td align=right width=115>"+f(""+smn[9])+"</td>"+"<th align=right width=115>"+f(""+Sum)+"</th>"+"</tr>";	
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
		str+=line+"<br>--------------------------------------------------------------"+line+str;
		str1+=str+"</center></body></html>";
		return str1;
	}
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	String[]MD(){
 		String file="egslv/k"+K[0]+"/lz"+Anteil+"_"+M[0]+"_"+datum.substring(2,datum.length())+".lz";
 		return open(file);
 	} 	
 	
 	/*					0		1			2		 3
	String dateLineV=datum+","+brutto+","+dnkk+","+szdnkk+","+
 	*	 4		   5	    6		7	   8    	9		 10		 11
 	*	dgkk+","+szgkk+","+mav+","+DB+","+DZ+","+lstr+","+lstrsz+","+Komm;
 	*/
 	float[]Sumen(){
 		return new Sum(K[0],M[0],datum).Sumen();
 	}
 	/*
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
 	String[]MD(String dna){
 		String file="egslv/k"+K[0]+"/lz"+dna+"_"+M[0]+"_"+datum.substring(2,datum.length())+".lz";
 		return open(file);
 	} 
 	float[]SumenDN(){ 		
 		String[]md=MD("dna");
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
 	*/
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
	
	public formtext(String Date,String[]K,String[]M,String Anteil){
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
	public formtext(String Date,String[]K,String[]M,String Anteil,String awt){
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
