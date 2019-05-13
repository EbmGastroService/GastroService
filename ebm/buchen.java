// created on 27.01.2007 at 00:04
package ebm;
public class buchen{
	String[]data;
	String[][]tdata;
    public buchen(String[]edata){
		data=edata;
		if(data.length>0){
			String[]str={"Buchen","Rechnung","Datum","kunde","R-Betrag","Total"};
			tdata=new select(dAstr(mitBoolean(data)),str).mdata;
			if(tdata!=null)
			save(einArrayOhneBoolean(tdata));
		}
	}
	void save_OP(String[]str){
		new com.units.save().dontsort("gastro/date/jliste.dat",str,false);
	}
	void save(String[]str){
		String benut=BenutzerName();
		String[]mystr;
		if(new com.security.chef(benut).test()=="positive"){
			mystr=PlusMinus(str,benut,"-");
			new com.units.save().dontsort("gastro/date/kbuch.dat",mystr,true);
			mystr=PlusMinus(str,benut,"+");
			new com.units.save().dontsort("gastro/date/fliste.dat",mystr,true);
			save_OP(oPosten(tdata));
		}		
	}
	String BenutzerName(){
		com.options.MyOp mo = new com.options.MyOp();
		String[] myliste = new com.search.sucheDate("gastro/date/benutzer.dat").myDaten();
		String eintext="<html><font color=red>Fahrer Code</font><font color=blue>";
		for(int i=0; i<myliste.length; i++){
			if(i % 2==0)eintext+="<br>";
			eintext+=""+i+" = "+myliste[i]+"&nbsp;&nbsp;&nbsp;&nbsp;";
			
		}
		eintext+="<br></font></html>";
		String dernamecode=mo.eingabe(eintext);
		String dername="";
		int i=-1;
		if(dernamecode.length()>0){
			try {
				i=Integer.parseInt(dernamecode);
			} catch (NumberFormatException e) {System.out.println("\nZahlfehler JL " + e);i=-1;}
		}
		if(i>=0 && i<myliste.length)dername=myliste[i];
		else dername=mo.wahl(myliste,"Fahrer suchen");
		return dername;//new Jlist(
	}
	void z(Object[]str){		
		System.out.println("Buchen Ausgang Ohne Boolean:> ");
		for(int i=0;i<str.length;i++){
			if(str[i]!=null){
				System.out.println(str[i]);
			}
		}
	}
	String datum(String str){
		if(str.length()==6)
			return str.substring(0,2)+"_"+str.substring(2,4)+"_20"+str.substring(4,6);
		else 
		if(str.length()==10)
			return str.substring(0,2)+str.substring(3,5)+str.substring(8,10);		
		else return str;
	}	
	String[]PlusMinus(String[]str,String driver,String pm){
		if(str!=null && str.length>0){			
			String[]nstr=new String[str.length];
			for(int i=0;i<nstr.length;i++){		
				if(str[i]!=null)			
				nstr[i]=str[i]+",F_"+driver+","+pm;
			}		
			return nstr;
		}else return new String[0];		
	}
	void z(String[][]str){
		if(str!=null && str.length>0){
		System.out.println("Buchen Ausgang length:> "+str[0].length);
		for(int i=0;i<str.length;i++){			
			for(int x=0;x<str[i].length;x++){
				System.out.print(str[i][x]+",");
			}			
			System.out.println("");
		}			
		}
	}
	Object[]mitBoolean(Object[]str){
		Object[]nstr=new Object[str.length];		
		for(int i=0;i<str.length;i++){
			nstr[i]=new Boolean(true)+","+str[i];
		}
		return nstr;
	}
	
	Object[][]dAstr(Object[]str){
		if(str!=null && str.length>0){
		String[]w=new com.options.ausTeilen().koma(""+str[0]);
		Object[][]nstr=new Object[str.length][w.length-1];
		for(int i=0;i<nstr.length;i++){					
			w=new com.options.ausTeilen().koma(""+str[i]);				
			//Object o=new Boolean(w[0]);
			nstr[i][0]=new Boolean(w[0]);
			w[2]=datum(w[2]);
			for(int x=1;x<nstr[0].length;x++)nstr[i][x]=(Object)w[x];							
			}
			System.out.println("Eingang length: "+nstr[0].length);
		return nstr;
		}else return new Object[0][0];
	}
	String[]oPosten(String[][]str){
		if(str!=null && str.length>0){
		String[]nstr=new String[str.length];		
		for(int i=0;i<str.length;i++){
			if(str[i][0]!="true"){
				nstr[i]=str[i][1];
				nstr[i]+=","+datum(str[i][2]);
				for(int x=3;x<str[i].length;x++){
					nstr[i]+=","+str[i][x];
				}
					nstr[i]+=",0";
			}
		}
		return nstr;
		}else return new String[0];
	}
	String[]einArrayOhneBoolean(String[][]str){
		if(str!=null && str.length>0){
		String[]nstr=new String[str.length];		
		for(int i=0;i<str.length;i++){
			if(str[i][0]!="false"){
				nstr[i]=str[i][1];
				for(int x=2;x<str[i].length;x++){
					nstr[i]+=","+str[i][x];
				}
				//nstr[i]+=","+str[i][str[i].length-2];
			}
		}
		return nstr;
		}else return new String[0];
	}
	/*public static void main(String[]args){
		String[]str=new com.search.sucheDate("gastro/date/jll.dat").myDaten();		
		new buchen(str);
	}*/
}
