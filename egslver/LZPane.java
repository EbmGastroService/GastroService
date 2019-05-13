// created on 31.10.2007 at 00:12
package egslver;
import com.printer.JComponentVista;
import java.awt.print.*;
public class LZPane{
	String[]K;
	String[]M;
	String[]LD;
	String datum;
	float SVBGL,SVB,StBGL,L_LB;
	float SZ_BGL,SZ_SVB,SZ_StBGL,SZ_LB,Sum;
	JComponentVista vista;
	public LZPane(String[]K,String[]M,String datum){
		this.K=K;
		this.M=M;
		this.datum=datum;		
		new Form(DNA(),K,M,"dna","-");	
		new Form(DGA(),K,M,"dga","-");	
	}
	String DNA(){
		String da="dna";
		LD=MonatDate();
		SVBGL=aliLo();//fd(LD[2]);//Lohnbrutto
		SVB=Asum();//KKBeitrag vonm Lohn
		
		StBGL=LStrBGL();//Lohnsteuer bemessung grundlage
		L_LB=MonLStr();//Lohnsteuer vom Lohn
		
		SZ_BGL=aliSZ();//Sonderzahlung Bem.Grundlage
		SZ_SVB=SZBei(da);//SonderZahlungsbeitrag
		
		SZ_StBGL=SZBGL();//SonderZhalung LohnSteuer bemessungs Grundlage
		SZ_LB=SZLStr();//SonderZahlung Steuer Betrag
		
		Sum=sum();//Sume Abzug betrags
		String DNA=datum+","+SVBGL+","+SVB+","+StBGL+","+L_LB+","+SZ_BGL+","+SZ_SVB+","+SZ_StBGL+","+SZ_LB+","+Sum;			
		save(DNA,da);		
		return DNA;
	}
	String DGA(){
		String da="dga";
		LD=MonatDate();
		SVBGL=aliLo();//Lohnbrutto
		SVB=Bsum();//KKBeitrag vonm Lohn
		
		StBGL=0;//LStrBGL();//Lohnsteuer bemessung grundlage
		L_LB=0;//MonLStr();//Lohnsteuer vom Lohn
		
		SZ_BGL=aliSZ();//Sonderzahlung Bem.Grundlage
		SZ_SVB=SZBei(da);//SonderZahlungsbeitrag
		
		SZ_StBGL=0;//SZBGL();//SonderZhalung LohnSteuer bemessungs Grundlage
		SZ_LB=0;//SZLStr();//SonderZahlung Steuer Betrag
		
		Sum=sum();//Sume Abzug betrags		
		String DGA=datum+","+SVBGL+","+SVB+","+StBGL+","+L_LB+","+SZ_BGL+","+SZ_SVB+","+SZ_StBGL+","+SZ_LB+","+Sum;			
		save(DGA,da);		
		return DGA;
	
	}	
	//Sonderzahlung Bemesungsgrundlage 	
 	float SZBGL(){ return aliSZ()-aliSZBei();}
 	 //Sonderzahlungsbeitrag
 	float SZBei(String da){
 		String mon=datum.substring(0,2);
 		float beitrag=0;
 		if(mon.equals("05")||mon.equals("11")){
 			if(da.equals("dna"))beitrag=aliSZBei();
 			if(da.equals("dga"))beitrag=aliSZBei_DG()+aliSZBei();
 		}
 		return beitrag;
 	}
 	float aliSZBei(){return fd(LD[4]); }
 	float aliSZBei_DG(){return fd(LD[6]);}
 	float LStrBGL(){return fd(LD[1])-fd(LD[3]);	}
 	float MonLStr(){return fd(LD[10]);}
 	float SZLStr(){return fd(LD[11]);}
 	float sum(){return SVB+L_LB+SZ_LB+SZ_SVB; 	}
 	float aliSZ(){return fd(LD[2]);}//new SR(fd(M[13]),M[11],datum).aliSZ();	}
 	float aliLo(){return fd(LD[1]);}//new SR(fd(M[13]),M[11],datum).aliLo();	} 	
 	float Bsum(){return fd(LD[3])+fd(LD[5]);}
 	float Asum(){return fd(LD[3]);}
	//float LStr(){return fd(LD[10]);}//new Lstr(M[13],M[16],M[11]).LStr_satz();}
	String f(String str){return new com.units.MyZahl().deci(Float.parseFloat(str));}
	float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}
	String[]lohnDate(){
		String Mafile="egslv/k"+K[0]+"/ls/dgg"+"_"+M[0]+"_"+datum.substring(2,datum.length())+".dat";		
		return open(Mafile);
	}	
	String[]MonatDate(){
		String[]ld=lohnDate();
		String[]DN=new com.options.ausTeilen().koma(ld[0]);
		for(int i=0;i<DN.length;i++)DN[i]="";
		for(int i=0;i<ld.length;i++){
			if(ld[i].indexOf(datum)>-1){
				DN=new com.options.ausTeilen().koma(ld[i]);
			}
		}
		return DN;
	}
	void save(String dateLine,String da){
		String file="egslv/k"+K[0]+"/lz"+da+"_"+M[0]+"_"+datum.substring(2,datum.length())+".lz";					
 		new com.units.save().file(file,dateLine,true);
 	} 	
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}	
	/*					0		1		  2		 3			4
	String dateLineV=datum+","+brutto+","+sz+","+dnkk+","+szdnkk+","+
 	*	 5		   6	    7		8	   9    	10		 11		 12
 	*	dgkk+","+szgkk+","+mav+","+DB+","+DZ+","+lstr+","+lstrsz+","+Komm; 	*/
 	
}
