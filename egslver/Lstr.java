// created on 15.11.2007 at 14:03
//Berechnung der Lohnsteuer nach gesetzliche Bestimmung
package egslver;
public class Lstr{
	String Lohn;
	String Gruppe;
	String Datum;
	String datum;
	String[]MaSatz;
	// Ma 13,Ma16,Ma11 Lohn,Gruppe,Dienst Antritt
	public Lstr(String Lohn,String Gruppe,String BeginDatum){
		this.Lohn=Lohn;
		this.Gruppe=Gruppe;
		Datum=BeginDatum;
		datum=new com.units.myDatum().ist_my();
		MaSatz=new lvstruck().masatzd();
	}

	float DN_SZSatz(){ 	
 		int i=1;
 		if(Gruppe.charAt(0)==('A'))i=1;else if(Gruppe.charAt(0)==('D'))i=3; else if(Gruppe.charAt(0)==('N')||Gruppe.charAt(0)==('M'))i=5; 	
 		float gkk_satz=Wert(MaSatz[i])/100; 		
		
		if(i==5)gkk_satz=0;
		if(gkk_satz>0)gkk_satz=(float)N25(Wert(Lohn),(double)gkk_satz);
 		return gkk_satz;
 	}
 	float DN_gkkSatz(){ 		
 		int i=1;
 		if(Gruppe.charAt(0)==('A'))i=1;else if(Gruppe.charAt(0)==('D'))i=3; else if(Gruppe.charAt(0)==('N')||Gruppe.charAt(0)==('M'))i=5; 		
 		float gkk_satz=Wert(MaSatz[i])/100;
 		float kum_satz=Wert(MaSatz[7])/100;
 		float wbf_satz=Wert(MaSatz[9])/100; 		
 		float iesg_satz=Wert(MaSatz[11])/100;
 		float satz=gkk_satz+kum_satz+wbf_satz+iesg_satz;
 		if(i==5)satz=0;
 		if(satz>0)satz=(float)N25(Wert(Lohn),(double)satz);
 		return satz;
 	} 	
 	double N25(float brutto,double satz){
 		double nSatz=0;
 		if(satz>0.014 && brutto<1100)nSatz=satz-0.03;else
 		if(brutto>1100 && brutto<1280)nSatz=satz-0.02;else
 		if(brutto>1280 && brutto<1380)nSatz=satz-0.01;else	
 		if(brutto>1380)nSatz=satz; 		
 		System.out.println("LOHN:"+Lohn+"...GKK_DNA_Satz:"+satz+"Neue Satz: "+nSatz+"\n________");
 		return nSatz;
 	}
 		
 	float JNE(){//Jahres Netto Einkommen
 		float lohn=Wert(Lohn); 		
 		float dnkk=lohn*DN_gkkSatz();
 		float ne=((lohn-dnkk)*12)-LstrAbzugsBetrag();
 		//System.out.print("Lohn: "+(lohn-dnkk)+"SonderZahlung:"+(sz-szdnkk)+" Netto Einkommen"+ne);
		return ne;
 	}
 	float LstrAbzugsBetrag(){
 		float Werbungskosten=16*12;
 		float Sonderausgabenpauschale=5*12;
 		return Werbungskosten+Sonderausgabenpauschale;
 	}
 	float ANabsetzbetrag(){
 		float Arbeitnehmerabsetzbetrag=54;
 		float Verkehrsabsetzbetrag=291;
 		return Arbeitnehmerabsetzbetrag+Verkehrsabsetzbetrag;
 	}
	float LStr_satz(){
		float NE=JNE();
 		float GS=0;//GrenzSatz	
		if(NE<=10000)GS=0;else
		if(NE>10000 && NE<=25000)GS=((NE-10000)*5750)/15000;else
		if(NE>25000 && NE<=51000)GS=5750+((NE-25000)*11335)/26000;else
		if(NE>51000)GS=17085+((NE-51000)*50/100);
		GS=GS-ANabsetzbetrag();
		float Satz=0;
		if(GS>0)Satz=GS/NE;
		//System.out.println("LohnsteuerSatz "+Satz*100);
		return Satz;
	}
	float Wert(String str){
 		float lohn=0;
 		try{lohn=Float.parseFloat(str);}catch(Exception ex){lohn=0;}
 		return lohn;
 	}
 	/** Gesetzliche Formelen
	*10.000 und darunter		0						0%	0%
	*10.000 bis 25.000	[(Einkommen - 10.000) x 5.750] / 15.000			-	38,333% **)
	*25.000	5.750									23%	38,333% **)
	*25.000 bis 51.000	5.750 + [(Einkommen - 25.000) x 11.335] / 26.000	-	43,594%
	*51.000	17.085									33,5%	43,594%
	*über 51.000	17.085 + [(Einkommen - 51.000) x 0,5	-				50%
	**/		
}
