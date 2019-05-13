// created on 28.11.2007 at 01:36
/*
 * von Mourad El Bakry 
 * Teil Buchhaltungsprogramm
 * Buche und in folder speichern
 * Journal Buch
 */
 package egsbh;
 public class JB{
 	String Datum,BelegNr,Konto,GegKonto,NBetrag,Steuer,Wo;
 	public JB(String Datum,String BelegNr,String Konto,String GegKonto,String NBetrag,String Steuer,String Wo){
 		this.Datum=Datum;
 		this.Wo=Wo;
 		this.BelegNr=BelegNr;
 		this.Konto=Konto;
 		this.GegKonto=GegKonto;
 		this.NBetrag=NBetrag;
 		this.Steuer=Steuer;
 		save();
 	}
 	void save(){
 		String zeile=Datum+","+BelegNr+","+Konto+","+GegKonto+","+NBetrag+","+Steuer+","+Wo;
 		new com.units.save().file("egsbh/jb.dat",zeile,true);
 	}
 }
