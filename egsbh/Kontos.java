// created on 26.11.2007 at 00:19
//Author Mourad Elbakry
//Kontoklassen erstellen
package egsbh;
public class Kontos{
	String[]Aktive={
		"0,Anlage Vermögen",
		"0300,unbebaute Grundstücke",
		"0200,Bebauten",
		"0400,Machiene",
		"0610,Austattung",
		"1,Vorräte",
		"1100,Rohmstoffvorrat",
		"1200,Vorat Fertige Produckten",
		"1600,Handelswarenvorrat",
		"2,Sonstiges Umlaufvermögen und RAP",
		"2700,Kassa",
		"2800,Bank",
		"2650,Wertpapiere des Umlaufvermögen",
		"2900,Aktive RAP"
	};
	String[]Passive={
		"9,Eigene Kapital",
		"9390,Bilanzgewinn od.Bilanzverlust","9400,Privat","9350,Freie Rücklage","9800,EBK","9850,SBK","9890,G+V-Konto",	
		"3,Verbindlichkeiten",		
		"3900,Passive RAP"
	};
	String[]Zahlungskonto={"2700,Kassa","2800,Bank"};
	String[]Forderung={"2000,Forderung aus Lieferung und Leistung Inland",
	"2500,Vorsteuer"};
	String[]Steuer={"s0,0","s1,10","s2,20","s3,25","s4,34"};
	String[]Ertargskonto={
		"4000,Umsatz Inland 20%",
		"4010,Umsatz Inland 10%",
		"4600,Umsatz aus den Verkauf von Anlagen 20%",
		"4610,Versicherungentschädigung 20%",
		"4630,Umsatz aus den Verkauf von Anlagen 20%",
		"4820,Eigenverbrauch 10%",
		"4825,Eigenverbrauch 20%",
		"4830,Mieterträge 20%"
		
	};
	String[]SachAufwand={
		"5010,Waren Einsatz 10%",
		"5020,Waren Einsatz 20%",
		"5060,Verbrauchsmaterial 10%",
		"5070,Verbrauchsmaterial 20%",
		"7780,Grundumlage Wirschaftskammer",
		"7100,Werbung 20%",
		"7400,Miete Pachtund Leasing 20%",
		"7200,Energie 20%",
		"7400,LKW Kraftstoff",
		"7500,Hygiene und Reinigung 20%",
		"7800,Provision an Dritte 20%",
		"7000,Abschreibung"};
	
	String[]PersonalAufwand={
		"6000,Löhne",
		"6200,Gehälte",
		"6410,Mitarbeite Vorsorge Kassa",
		"6500,Gesetzlicher Sozialaufwand Arbeiter",
		"6550,Gesetzlicher Sozialaufwand Angestellte",		
		"6600,Kommunalsteuer",
		"6610,Dienstgebebeitrag zur Familienbeihilfe DB",
		"6615,Dienstgeber Zuschlag DZ",
		"6700,Freiwilliger Sozialaufwand",
		"6888,Gewerblich Sozial Aufwand"		
	};
	String[]ZinsenKonto={"8100,Zinsen Ertäge","8200,Zinsen Aufwand","8300,Spesen des Geldes Verkehr"};
	String[]Verbindlichkeit={		
	"3300,Verbindlichkeit aus Lieferungen und Leistungen Inland",
	"3310,Verbindlichkeit aus Lieferungen und Leistungen Ausland",
	"3500,Umsatzsteuer",
	"3520,Finanzamt-Zahllast",
	"3200,Verrechnungskonto Kreditkarten",
	"3120,Sonstige Rückstellungen",
	"3540,Verrechnungskonto Finanzamt Lstr DB DZ",
	"3600,Verrechnungskonto Sozialversicherung",
	"3610,Verrechnungskonto Stadt Gemeinde",
	"3630,Verrechnungskonto Löhne und Gehälte",
	"3888,Verrechnungskonto Gewerblich Sozialaufwand",
	};
	String[]Files={"ave","pve","zak","fd","ek","sa","pa","zink","verbk","strs"	};	
	
	public Kontos(){	
		new com.units.save().file("egsbh/kontos.dat","",false);
		for(int i=0;i<Files.length;i++)Files[i]="egsbh/resource/"+Files[i]+".dat";
		save(Files[0],Aktive);
		save(Files[1],Passive);
		save(Files[2],Zahlungskonto);
		save(Files[3],Forderung);
		save(Files[4],Ertargskonto);
		save(Files[5],SachAufwand);
		save(Files[6],PersonalAufwand);
		save(Files[7],ZinsenKonto);
		save(Files[8],Verbindlichkeit);
		save(Files[9],Steuer);
	}	
	void save(String fn,String[]str){
		com.units.save save=new com.units.save();		
		for(int i=0;i<str.length;i++){						
			System.out.println(str[i]);			
		}
		save.dontsort(fn,str,false);
		save.dontsort("egsbh/kontos.dat",str,true);
	}
	public static void main(String[]args){
		new Kontos();
	}
		
}
