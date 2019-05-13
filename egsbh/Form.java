// created on 29.11.2007 at 00:09
/*
 * von Mourad El Bakry 
 * Teil Buchhaltungsprogramm
 * Zeige Buchungen
 * Formular
 */
  package egsbh;
  public class Form  {
  	String[][]DGD;
	String datum;
	String Tab;
  	public Form(){
  		DGD=null;
  		DGD=open_D("egsbh/jb.dat");
  		Tab="";
  		tabel();
		String mTab=HtmlKopf()+Tab+HtmlEnd();
		new com.units.save().file("egsbh/temp/bhform.tmp",mTab,false);			  		
  	}
  	public Form(String jbfile){
  		DGD=null;
  		DGD=open_D(jbfile);
  		Tab="";
  		tabel();
		String mTab=HtmlKopf()+Tab+HtmlEnd();
		new com.units.save().file("egsbh/temp/bhform.tmp",mTab,false);			  		
  	}
  	public Form(String[][]date){  		
  		DGD=date;
  		Tab="";
  		tabel();
		String mTab=HtmlKopf()+Tab+HtmlEnd();
		new com.units.save().file("egsbh/temp/bhform.tmp",mTab,false);			  		
  	}
  	
  	String HtmlKopf(){
		return "<html><head>"+"<meta http-equiv='content-language' content='de'>"+		
		"<title>EGS Buchhaltung</title></head>"+
		"<body bgcolor='#ccceee' width='640' font face='Times New Roman' font color='#888999'>"+
		"<center><div align='left' style='padding:10px;color:#888990;width:615;background:#fffffe'>";
	}
	

	String HtmlEnd(){
		return "</div></center></body></html>";
	}
	String[][]open_D(String file){
 		return new com.search.sucheDate(file).teiledaten();
 	}
 	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	String TKopf(){
		return "<table cellpadding=0 cellspacing=0 border=0 width=534>"+
		"<tr><td colspan=8>Journal Vorschau</td></tr>";
	}
	String TEnd(){
		return "</table>";		
	}
	String tabel(){	
		float vstr=0;		
		float ustr=0;
		float kassa=0;
		float gkonto=0;
		String[][]dgd=DGD;//new java.util.Arrays.Sort(DGD);
		if(dgd.length>0){if(dgd[0][0].length()>6)dgd[0][0]=dgd[0][0].substring(3,dgd[0][0].length());}
		String[]head={"Datum","BgNr","ZKonto","GKonto","Einnahme","Ausgaben","VStr","USt","Text"};
		Tab="";
		//Tab+=TKopf();
		Tab+="<table cellpadding=0 cellspacing=0 border=0 width=615>";
		Tab+="<tr><td colspan="+(head.length)+">Journal Vorschau</td></tr>";
		Tab+="<tr><th align=center width=60>"+head[0]+"</th>";
		for(int i=1;i<4;i++)Tab+="<th align=center width=50>"+head[i]+"</th>";
		for(int i=4;i<head.length;i++)Tab+="<th align=right width=81>"+head[i]+"</th>";		
		Tab+="</tr>";			
		for(int i=0;i<dgd.length;i++){
			Tab+="<tr><th align=center width=60>"+dgd[i][0]+"</th>";
			for(int x=1;x<4;x++){
				Tab+="<td align=center width=50>"+dgd[i][x]+"</td>";				
			}			
			/*for(int x=4;x<dgd[i].length;x++){
				Tab+="<td align=right width=81>"+f(""+dgd[i][x])+"</td>";				
			}*/
			float tk=0;
			if(fd(""+dgd[i][4])<0 ){						
				tk=fd(dgd[i][4]);
				gkonto+=tk;
				dgd[i][4]="0";
				Tab+="<td align=right width=81>--</td>";//"+f(""+fd(dgd[i][4]))+"
				
			}else {
				kassa+=fd(dgd[i][4]);
				Tab+="<td align=right width=81>"+f(""+fd(dgd[i][4]))+"</td>";
			}
			float v=0;		float u=0;		
			if(fd(""+dgd[i][5])<=0){				
				v=fd(dgd[i][5])*-1;
				Tab+="<td align=right width=81>"+f(""+tk)+"</td>";
			}else {
				u=fd(dgd[i][5])*-1;
				Tab+="<td align=right width=81>--</td>";
			}
			vstr+=v;
			ustr+=u;
			String mk="";if(v==0)mk="--";else mk=""+f(""+v);
			Tab+="<td align=right width=81>"+mk+"</td>";				
			mk="";if(u==0)mk="--";else mk=""+f(""+u);
			Tab+="<td align=right width=81>"+mk+"</td>";				
			Tab+="<td align=right width=81>"+dgd[i][6]+"</td>";				
			Tab+="</tr>";				
			
		} 		
		Tab+="<tr><td colspan="+(head.length)+"><hr></td></tr>";
		Tab+="<tr><td align=center width=60>Gesamt</td>";
		for(int i=1;i<4;i++)Tab+="<td align=right width=50></td>";
		Tab+="<td align=right width=81>"+f(""+kassa)+"</td>";
		Tab+="<td align=right width=81>"+f(""+gkonto)+"</td>";
		Tab+="<td align=right width=81>"+f(""+vstr)+"</td>";
		Tab+="<td align=right width=81>"+f(""+ustr)+"</td>";
		
		Tab+="</tr>";			
		Tab+="<tr><td align=center width=60>Erfolg</td>";
		for(int i=1;i<3;i++)Tab+="<td align=right width=50></td>";
		Tab+="<td align=right width=50>V/G</td>";
		Tab+="<td align=right width=81>"+f(""+(kassa+gkonto))+"</td>";
		Tab+="<td align=right width=81>Str L/G</td>";
		Tab+="<td align=right width=81>"+f(""+(ustr+vstr))+"</td>";
		Tab+="<td align=right width=81>Ergibt</td>";
		Tab+="<td align=right width=81>"+f(""+((kassa+gkonto)+(ustr+vstr)))+"</td>";
		
		Tab+="</tr>";			
		
		
		Tab+=TEnd();		
		return Tab;
	}
	float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}	
	String f(String str){return new com.units.MyZahl().deci(fd(str));}	
  }
