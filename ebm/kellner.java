// Kellener created on 29.05.2003 at 02:32
//Autor Mourad El bakry
//Tel.:0676 354 68 24
//A-Wien 1180; Lacknergasse 100/4
//Mailto:elbakry@aon.at
// es dient für Gastronomie Betrieben, als Bedienungsfreudige System
//ich habe Anzahl die Tische ,Kellnern und Tischinhalt auf 20 sind aber unbeschränkt 
//da laut meine Erfahrung genugend ist, aber ich könnte auf 100 ohne probleme
package ebm;
import javax.swing.plaf.*;
import com.options.*;
import com.units.*;
import com.display.*;
import com.search.*;
import com.printer.*;
import com.security.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;
import java.awt.print.*;
import java.io.*;
import java.util.*;
import java.text.*;
public class kellner extends JApplet{
	int Tisch;
	int KellnerNummer;
	double TischUmsatz;
	double KellnerUmsatz;
	double Total;	
	WD w=new WD();//Warendatenbank
	myDatum md =new myDatum();// alle Datumformen 
	save s =new save();// Speichert zeile, oder Array, sortiert oder unsortiert
	//20 Kellner für je  300 Tische und 50 Zeilen pro Tisch
	String[][][] rec =new String [20][300][50];
	String[] Ware;//=w.lesen();
	int[] Verkaufsanzahl;//= new int[Ware.length]; 
	// Tischrecord 20 Kellner für je 300 TischUmsatz
	double[][] Trec =new double[20][300];
	// Kellnerrecord 20 KellnerUmsatz
	double[] Krec = new double[20];
	int Versuch;
	double Skale;
	String Datum ;
	MyOp mo = new MyOp();//Tastatur Bedienung oder Musklick! Erleichterung
	JPanel jl,east;
	JButton[] JB = {
		new JButton("Kellner"),
     	new JButton("Tisch"),//nach Kellner und Tisch fragen und eine Liste erstellen
     	new JButton("V-Tisch"),//Volle Tische zeigen
     	new JButton("R-Drucken"),//Rechnung drucken und Tisch löschen
     	new JButton("T-Listen")  //zeige alle belegte Tische ihren Inhalt   	     
     };
	 JTextArea ta  = new JTextArea(25,10); 	
 	 JLabel flabel  = new JLabel(""); 	
 	
 	 JTextField tf  = new JTextField("	Hier soll die rechnung Kopf Erscheinen");//, 150);
	 JTable mytab = new JTable(25,5);
 	 Container cp;
 	 JTextField tlz  = new JTextField("Rechnug summe : "+(double)0+" :> ",50);
	 JButton[] links = { 		
	 	new JButton("T-Absatz"),//Gesammt Warenverkauf am Tag Zeigen und bei Datum Wechsel Drucken
	 	new JButton("M-Absatz"),//Nimmt die Tagsverkauf und stellt eine Liste fürs Monatverkaufsware    	
 		new JButton("J-Bricht"),// Dienstprogramm für die Zustellservice
 		new JButton("K-Umsatz"),
 		new JButton("T-Umsatz"),//per Knopfdruck starten alle Möglichen Diensten
     	new JButton("Hilfe") // Hinweis über mich
	 };
 	 Font font= new Font("Courier New",1,14); 	//Schrift
 	 //Konstractor
	public kellner(int tisch,int kellnernummer,double tischumsatz,double kellnerumsatz){
		Tisch=tisch;
		KellnerNummer=kellnernummer;
		TischUmsatz=tischumsatz;
		KellnerUmsatz=kellnerumsatz;
		Versuch=0;	
		Total=0;
		Datum="29_05_2003";
		chekDatum();
	}
	public kellner(){
		Tisch=0;
		KellnerNummer=0;
		TischUmsatz=0;
		KellnerUmsatz=0;
		Versuch=0;	
		Total=0;		
		//Datum="29_05_2003";
		east = new JPanel ();
		jl = new JPanel (new BorderLayout());
		jl.add(new JPanel());
		Skale = new papier().getSkale();//getPapierformat();//fixpapier();
		chekDatum();
	}
	public String[] getWare(){String[]wl=w.lesen();Ware= new String[wl.length]; return Ware=wl;}
	public int[] getVerkauf(){Verkaufsanzahl = new int[Ware.length];return Verkaufsanzahl;}	
	public void chekWare(){getWare();getVerkauf();}  
	// ob die eingabe i>die vorgegebne Array von 20 zur Zeit
	public boolean T_cheklength(int i){		
		if(i>0 && i<rec[0].length)return true;
		else return false;		
	}
	public boolean cheklength(int i){		
		if(i>0 && i<rec.length)return true;
		else return false;		
	}
	public boolean chekkellnertisch(){				
		if(rec[KellnerNummer][Tisch][0]== null)return true;
		else return false;
	}	//stellt eine Mousklickliste zum Wählen ob Tisch oder Kellner wäre
	public String[]liste(String str){
		String[]wliste =new String[Trec.length];
		if (str.equals("Tisch "))wliste=new String[Trec[0].length];
		for(int i= 0;i<wliste.length;i++){
			if (wliste[i]==null)wliste[i]="";
			wliste[i]=wliste[i]+str+(i+1);
		}
		return wliste;
	}
	public int versuch(){return Versuch++;}// falls eine Möglichkeit gebe zum Entscheiden
	//diese Methode übergibt den TischNummer züruck nach überprüfung 
	//die Rahmenbedienung: muss Zahlencode sein, nicht grosser als 20 sein und nicht negative
	//dieser Bedienung werden über die Methode checkzahl() controliert.
	//die Methode wahl() ist eine dynamic zum übernahme den Tisch oder Kellner Nummer
	// über die liste() der OptionPanel vorgedacht.
	public int opentisch(){				
		Versuch=1;
		String ein = new MyOp().eingabe(versuch() +" Kellner("+KellnerNummer+")\nTisch Nummereingeben");
		new Methoden().chekzahl(ein);
		while(nichtnull(ein)&& Versuch>2){				
			ein = mo.eingabe(versuch() +" Kellner("+KellnerNummer+")\nTisch Nummereingeben");					
			new Methoden().chekzahl(ein);
		}
		if (ein.length()==0) {
			ein = mo.wahl(liste("Tisch ")," Tishe ");//gebe eine Liste verügbare Tische
			ein =ein.substring(6,ein.length());//ausschneide das wort "Tisch "
		}
		return Tisch=new Methoden().Int(ein);
	}
	//diese Methode übergibt den KellnerNummer züruck nach überprüfung 
	//die Rahmenbedienung: muss Zahlencode sein, nicht grosser als 20 sein und nicht negative
	//dieser Bedienung werden über die Methode checkzahl() controliert.
	public int forkellner(){				
		Versuch=1;
		String ein = mo.eingabe(versuch() +","+" Kellner Nummer eingeben");		
		new Methoden().chekzahl(ein);
		while(nichtnull(ein)&& Versuch>2){						
			ein = mo.eingabe(versuch() +","+" Kellner Nummer eingeben");		
			new Methoden().chekzahl(ein);		
		}
		if (ein.length()==0) {
			ein = mo.wahl(liste("Kellner ")," Kellnern ");
			ein =ein.substring(8,ein.length());
		}
		return KellnerNummer=new Methoden().Int(ein);
	}
	//Verkaufsanzahl intialisierung zum 0
	public void vkzero(){for (int i=0; i<Verkaufsanzahl.length;i++){Verkaufsanzahl[i]=0;}}
	//fügt eine Zeile zum Warenverkaufsregister
	public void save_warenverkauf(String str,boolean b){// str= warencode,menge b= false=file neue schreiben
		String file="gastro/Kdate/warenverkauf.dat";
		s.file(file,str,b);
	}		
	//die Ware suchen und die Menge bestimmen dann gleich registriern in warenvekauf.dat
	//es besteht die Möglichkeit die WarendB zur blätern über leere Eingabe oder
	//die suche bestimmen ob es Code, Bezeichnung (eine Buchstabe) oder Preis
	//sonst wenn der Warencode stimmt geht zum Mengen abfrage.
	public String addArtikel(){		
		mo=new MyOp();	
		String ergebnis="";
		String erg = w.suchen();              			
		String bez=w.getBezeichnung().trim();
		if(bez==null)bez="_";
		if(erg=="")chekWare();
        String ein = mo.eingabeMenge(" "+w.getw_Code()+"   "+w.getBezeichnung().trim()
                                  +"   "+w.getPreis()+ "\n Menge: ");
		if (ein.length()==0) {
			ein = mo.wahl(liste("Mal * ")," Menge ");//gebe eine Liste verügbare Tische
			ein =ein.substring(6,ein.length());//ausschneide das wort "Tisch "
		}
        int d =new Methoden().Int(ein);    		
		double betrag = d*w.getPreis();		
		ergebnis=(d+","+w.getw_Code()+","+bez+","+w.getPreis()+","+(float)betrag);	
		save_warenverkauf((""+d+","+w.getw_Code()),true);		
		return ergebnis;
	}
	//wenn ein Tisch Bearbeitet ist, wird ihr Umsatz um den neuen Zeilenwert(warepreis)addiert.
	public double TU(String str){
		ausTeilen a=new ausTeilen();
		String[]wort=a.zeile(str);		//der wert ist am ende jeder zeile(Arraylength)
		TischUmsatz = TischUmsatz+Double.parseDouble(""+wort[wort.length-1]);
		return TischUmsatz;
	}
	//dient um Mengen zur verändern in der KlenerTische.
	//durch den Argoment str (zeile vom Tisch) kann die Menge und imfolge auch die Wert
	//veandert.Über eine TextPane() kann bedient werden.
	public String bearbeiten(String str){
		ausTeilen a=new ausTeilen();
		String[]wort = a.komma(str);
		String[]nwort ={wort[0],wort[1],wort[2],wort[3]};		
		nwort=new textPane(nwort).worte();
		int menge=new Methoden().Int(nwort[0])-new Methoden().Int(wort[0]);
	//	System.out.println("org Menge: "+wort[0]+" new Menge: "+nwort[0]+" Dif. :"+menge);
		save_warenverkauf(menge+","+wort[1],true);
		Trec[KellnerNummer][Tisch]=Trec[KellnerNummer][Tisch]+(menge*Double.parseDouble(""+nwort[3]));
		double betrag=new Methoden().Int(nwort[0])*Double.parseDouble(""+nwort[3]);
		str=(nwort[0]+","+nwort[1]+","+nwort[2]+","+nwort[3]+","+(float)betrag);
	//	System.out.println("bearbeiten kellner.storno: \n"+str);	
		return str;
	}
	//da kann bestimmt werden, ob die zeile entfernt oder die Menge oder Bezeichnung geändert.
	//Jlist(Tischinhalt)=eine Optionpane übergibt die Auswahlposition zurück
	public String[]storno(String[] str){                   	       	
    	int erg=new Jlist(str,false).ergebnis();
		Object[] ob = {"Bearbeiten","Entfernen","Abbrechen"};
		mo=new MyOp();
        int an = mo.frageO(elementieren(str[erg])+"\n bereit zum bearbeiten!",ob);        
		if(an==0){
			str[erg] = bearbeiten(str[erg]);//Ändren
			rec[KellnerNummer][Tisch][erg]=str[erg];			
		}
		if(an==1){
			save_warenverkauf("-"+str[erg],true);//in der warenliste "-Menge"
        	str[erg]=null;                				
		}
        return str;                	
    }
	public int weiter(int x){        
        int an = 0 ;
        x++;
        int antwort = x;
		Object[] ob = {"Weiter","Editor","Beenden"};
        an = new MyOp().frageO("Wie soll es weiter gehen! ",ob);        
        if (an == 0){antwort=x;} 
        else if (an == 1){        	
        	String[]korb=storno(rec[KellnerNummer][Tisch]);
           // ta.setText("");        	
            for (int i=0;i<korb.length;i++){
            	if(nichtnull(korb[i])) {
            		tap(elementieren(korb[i]));             		            	
            	}
            }
           antwort=x;
        }//nur eine Zeile zurück
        //if (an == 1){        	
        else antwort=-3;        
		return antwort;
	}

	//wieviel Artikelzeilen ist auf den Tisch registriert
	//sind die wenige als 20 Zeilen, dann weitererfassen, 
	//sonst fordere entleerung den Tisch und Rechnungsausdruck
	public int tischgecheckt(){
		int x=0;
		TischUmsatz=0;		
		if(!chekkellnertisch()){						
			String[]str = rec[KellnerNummer][Tisch];
			//parameter 1 sorgt dafür dass der preis spalte doubleet.
			str=new WVL("gastro/Kdate",Datum).Artiklnzusammenfassen(str,1);//sorget dafür dass die gleichen Zeilen zusammengefasst 			
			rec[KellnerNummer][Tisch]=str;
				for(int m = 0;m<rec[KellnerNummer][Tisch].length;m++){
					if(nichtnull(rec[KellnerNummer][Tisch][m])){
						/*tap("\nK "+fi(""+KellnerNummer)+
						    " T "+fi(""+Tisch)+" Record :"+m+" Art: "+
					      elementieren(rec[KellnerNummer][Tisch][m]));*/
						TU(""+rec[KellnerNummer][Tisch][m]);
						x++;
						if(!cheklength(x)){
							mo.fehler("Blat ist voll!,\nBitte Drucken Sie diese Rechnung aus!");			
							x=-1;
						}
					}
				}							
		}		
		if(x>-1)KUaddieren(KellnerNummer,-TischUmsatz);
		return x;
	}
	public int tischnehmen(int vkelner , int tisch){		
		//Tisch inhalt umbuchen zum neuen Kellner
		//alte Kellner Tisch leeren und vermindere den Umsatz um den Inhaltwert
		for(int m = 0;m<rec[vkelner][tisch].length;m++){
			// übergebe die ware den vorkellner an dn neuen Kellner
			rec[KellnerNummer][tisch][m]=rec[vkelner][tisch][m];					
		}	
		//neue Kellner bekommt den Tischwert
		Krec[KellnerNummer]= Krec[KellnerNummer]+Trec[vkelner][tisch];
		//alten Kellner verliert den Tischwert
		Krec[vkelner]= Krec[vkelner]-Trec[vkelner][tisch];
		//alten Kellner sein Tisch auf zero
		Trec[vkelner][tisch]=0;
		// alten Kellner sein Tisch inhahlt auf null
		tischleeren(vkelner,tisch);
		//im U(Datum).dat der Veränderung registriern
		save_Kellnerumsatz();//""+vkelner+" , "+Krec[vkelner],true);	
		return tisch;
	}
	public int solltischgenomen(int vkeln,int vtisch){
		//3 versuch um den Tisch zu Ändern, sonst übergebe an neuen Kellner 
		int ntisch = opentisch();
		while(ntisch == vtisch && Versuch<3){
			ntisch = opentisch();			
			versuch();
		}
		if(ntisch == vtisch && Versuch>2){				
				mo.fehler(Versuch+" Versuch, Kellner "+KellnerNummer
			      		+" der Tisch ("+vtisch+") wird an Sie umgebucht"+
			      		"Sie werden um den Passwort angefordert!");
				String str=new userFinster("Kellner "+KellnerNummer).toString();
			if (str.equals("701")){
				ntisch=tischnehmen(vkeln,vtisch);				
			}else{ mo.fehler("Sie haben keine Genehmigung!");
				ntisch=solltischgenomen(vkeln,vtisch);
			}
		}
		return ntisch;
	}
	// es wird geprüft ob den Tisch an jemandanders übergeben wurden
	//sollte den Tisch doch belegt ist, falls eine Dienstwechsel gebe!
	//oder übergabe erlaubnis vorhanden ist, dann muss den Tisch mit dem Inhalt an den Übernehme
	//Transportiert, und den Übergebe mit diesen Inhalt entlastet
	public int isttischvoll(){				
		int tisch=Tisch;//geprüfte Tisch		
		int vtisch=0;//belegte tisch		
		for(int m = 0;m<Trec.length;m++){ // m = KellnerNummer,Trec=Kellner TischUmsatz
			if(Trec[m][Tisch]>0){		// ist Tischumsatz >0 	
			if(m!=KellnerNummer){ // ist Tischumsatz für anderen Kellener
					vtisch=Tisch;		// Diese Tisch ist voll
			      	mo.fehler("Kellner "+KellnerNummer
			          +" diese tisch ist belegt an Kellner "+m);
					Versuch=1;					
					//sorgt dafür dass entwieder Tisch übernommen oder neue Freie Tisch übernommen
				//	while(tisch==0){
						tisch=solltischgenomen(m,vtisch);
				//		if(tisch>0)break;
				//	}
				}
			}
		}		
		return tisch;
	}
	//nachdem es eine Zeile zugefügt wurde 
	//Tisch Umsatz neue berechnen und registrieren in(TxxKxx.dat)
	public double TUaddieren(int keln,int tisch){
	//	ta.setText("");
		TischUmsatz=0;
		Trec[keln][tisch]=0;
		save_Tischinhalt(keln,tisch,"",false);
		for(int x = 0;x<rec[keln][tisch].length;x++){			
			if(nichtnull(rec[keln][tisch][x])){
				//("\n"+Elementiere(rec[keln][tisch][x]));				
				//addiere zum TischUmsatz diese Zeile
				TU(""+rec[keln][tisch][x]);				//TischUmsatz addiert diese Artikel x
				save_Tischinhalt(keln,tisch,""+rec[keln][tisch][x].trim(),true);
				tf.setText(" 	Kellner ("+fi(""+keln)+") Tisch ("+fi(""+tisch)+") Euro "+f(""+TischUmsatz));
			}
		}
		// übergebe die neue Berechnung diese Tisch
		Trec[keln][tisch] =TischUmsatz;				
		return TischUmsatz;
	}	
	public double KUaddieren(int keln,double betrag){
		Krec[keln]=Krec[keln]+betrag;
		return Krec[keln];
	}
	public int erfasseKellner(){
		Versuch=1;
		int keln=forkellner();
		//sicherung, nicht über 20 eingeben
		while(!cheklength(keln)) keln=forkellner();
		tf.setText("		Kellner :"+keln);
		return keln;
	}
	public String[][][] erfassen(int keln){						
	  if (keln>0){
		int x=0;		
	  	Versuch=1;
		int tisch=opentisch();		
		//sicherung, nicht über 20 eingeben
		while(!T_cheklength(tisch)) tisch=opentisch();		
		tisch=isttischvoll();//ob Tisch belegt ist
		//while(tisch==0)erfassen(keln);
		tf.setText("Kellner ("+keln+") 		Tisch: ("+tisch+")");
		//belegte Tischinhalt zeigen und addieren und gibt x zeile falls über 20 dann -1
		
		x = tischgecheckt();					
	  	x=weiter(x-1);//bearbeiten und storno inthalten!,sowie beenden
	  	int schalter=x;	  		  	
		while(x>=0 ){//{&& x< rec[keln][tisch].length){
				if(x<0)break;							
				rec[keln][tisch][x]=addArtikel();				
				TUaddieren(keln,tisch);		//Tischumsataz neue Addieren
				// zeige in die Tabelle diese zeile
				if(nichtnull(rec[keln][tisch][x]))inzeile(rec[keln][tisch]);			
				x = weiter(x);
			if(x>0 && !cheklength(x)){			//als Sicherheit falls die Zeile über 20 sind
				mo.fehler("Kellner ("+keln+") Blatt ist voll, \n Drucken Sie dieser Tisch aus");
				break;
			}
		}//falls blatt ist voll, die interne Berechnungen nicht bearbeiten -1=schalter off
		if(schalter>=0){speichern(keln,tisch);}
	  }else mo.fehler("Bitte gehen Sie zum Kellner eingabe");
	  return rec;
	}
	
	public void speichern(int keln,int tisch){
			//TischUmsatz wird in tischgecheckt auf 0 oder Tischwert durch TU()
			//Addiere zum Kellner Tisch diese Umsatz		
			//Krec[keln]=Krec[keln]+Trec[keln][tisch];
			KUaddieren(keln,Trec[keln][tisch]);//TUaddieren(keln,tisch));
			//register KuT für Journal,Kumsatz für jeder Kellner ein file
			//Kellnerumsatz in U(Datum).dat Tagesumsatz alle Kellnern
			save_KuT(""+keln+" , "+tisch+" , "+Trec[keln][tisch]+" , "+Krec[keln],true);
 			//save_Kumsatz(Datum+","+Krec[keln],true);
			save_Kellnerumsatz();
			tcliste();//Tischrolle tcliste wird erfrischt
			tlz.setText("		Kellner ("+keln+ ") Tisch ("+Tisch+")");	
	}
	//registriert die Tagsumsätze in Monatsfile
	//nur bei Datum wechsel wird aktiviert
	public double Tagesumsatz(){
		return Total=Total+open_Tagesumsatz();		
	}
	String TD(String str){
		if(Datum.length()>7)return str.substring(3,str.length());
		else return str;
	}
	public double open_Tagesumsatz(){
		String monat ="M"+TD(Datum)+".dat";
		String file ="gastro/Kdate/Data"+md.J()+"/"+monat;		
		sucheDate su =new sucheDate(file);                	
		String ums = su.md().trim();
		System.out.println(file+" sum  "+ums);
		return new Methoden().flo(""+ums);
	}
	public void save_Tagesumsatz(String str,boolean b){
		String monat ="M"+TD(Datum)+".dat";
		String file ="gastro/Kdate/Data"+md.J()+"/"+monat;		
		s.file(file,str,b);		
	}
	// über gebe jederzeit die Umsatz
	public double Umsatz(){
		double ums=0;
		for(int i = 0;i<Krec.length;i++)
			ums =ums+Krec[i];						
		return ums;
	}
	public void tap(String str){
		ta.append(str);
	}
	public String elementieren(String str){
		ausTeilen a= new ausTeilen();
		String nzeile="";
		String[] wort=a.komma(str);
		wort[2]=a.randstS(""+wort[2].trim(),30);
		for(int i = 0;i<wort.length;i++){			
			if(nichtnull(wort[i])) nzeile = nzeile+"  "+wort[i];
		}
		return nzeile;			
	}
	public String Elementiere(String str){
		ausTeilen a= new ausTeilen();
		String nzeile="";
		String[] wort=a.komma(str);
		wort[2]=a.randstS(""+wort[2].trim(),30);
		if(wort.length>2)wort[3]=f(""+wort[3]);
		if(wort.length>3)wort[4]=f(""+wort[4]);
		for(int i = 0;i<wort.length;i++){			
			if(nichtnull(wort[i])) nzeile = nzeile+"  "+wort[i];
		}
		return nzeile;			
	}
	public void zeigealles(){
		//ta.setText("");
		int i,x,m;
		i=x=m=0;			
		for(i = 0;i<rec.length;i++){			
			for(x = 0;x<rec[0].length;x++){			 				 	
				for(m = 0;m<rec[0][0].length;m++){
					if(nichtnull(rec[i][x][m])){
					 // tap("\n 	Record :"+m+" Art: "+Elementiere(rec[i][x][m]));					  
					 zumtabele( inzeile(rec[i][x]),m);	
					}			
				}			 
				if(nichtnull(rec[i][x][0])){
			 		/*mo.fehler("Kellner ("+fi(""+i)+") Tisch ( "+fi(""+x)+" )"+
			 		     "\n___________\n T-U> Euro( "+f(""+Trec[i][x])+" )\n K-U> Euro( "+f(""+Krec[i])+" )");*/
			 		tabeleZero();
			 		tap("\nKellner ("+fi(""+i)+") Tisch ( "+fi(""+x)+" )");}
			 	if(Trec[i][x]>0 ){tap(" T-U> Euro( "+f(""+Trec[i][x])+" ) K-U> Euro( "+f(""+Krec[i])+" )");}
			}		
		}		
		tap("\n	 Kein Tisch mehr gefunden!");
		tap("\n_________________________________________________________");
		for(i = 0;i<rec.length;i++){
					if(Krec[i]>0 ){
						tap("\nKellner ("+fi(""+i)+") Umsatz  Euro ( "+f(""+Krec[i])+" )");
					}			
		}
		
		tap("\n===============\nTotal: "+f(""+Umsatz())+"\n");		
	}	
	public void tabeleZero(){                	
           	for(int i=0; i<mytab.getRowCount(); i++){                		
            		for(int x=0; x<mytab.getColumnCount(); x++){                		
             			mytab.setValueAt("",i,x); 
              		}               		
           	}                    
	}
	//übergebe ein Array neuer Länge zurück
	public int datalength(String[] inhalt){
		int x=0;
		for(int i=0;i<inhalt.length;i++){			
			if (nichtnull(inhalt[i]))x++;
		}
		return x;
	}
	public int datalength(int[] inhalt){
		int x=0;
		for(int i=0;i<inhalt.length;i++){			
			if (inhalt[i]>0)x++;
		}
		return x;
	}
	//Tabelle wird mit zeilenwerte eingefült
	public String[][] inzeile(String[] inhalt){		       	
		ausTeilen a = new ausTeilen();			
		String[][] recdata = new String [datalength(inhalt)][5];//a.komma(inhalt[0]).length];
		int p=0;
		for(int i=0;i<inhalt.length;i++){				
			if (nichtnull(inhalt[i])){
				String[] wort = a.komma(""+inhalt[i]);					
				recdata[p][0]=""+(i+1);//Pos
				recdata[p][1]=wort[0].trim();//Menge
				recdata[p][2]=wort[2].trim();//Bezeichnung statt code!
				recdata[p][3]=wort[3].trim();//E-Pries
				wort[3]=f(wort[3]);
				recdata[p][4]=wort[4].trim();//Total
				wort[4]=f(wort[4]);
				for(int x=0; x < wort.length;x++){
				if (nichtnull(wort[x])){
					mytab.setValueAt(wort[x].trim(),p,x);				
					}
				}				
				p++;
			}
		}
		return recdata;
	}
	 public void zumtabele(String[][]str, int x){                		 	
         for(int i=0; i<str[0].length-2; i++){                		
           	if (nichtnull(str[x][i])){
           		mytab.setValueAt(""+x,x,0);//pos            	
          		mytab.setValueAt(str[x][i].trim(),x,i);
           	}
         }
         if (nichtnull(str[x][3])&& nichtnull(str[x][4])){
         mytab.setValueAt(f(str[x][3].trim()),x,3);
	     mytab.setValueAt(f(str[x][4].trim()),x,4);
         }
	 }	
	 //zahlenformat Decimal 0.00
    public String f(String str){
    	return new MyZahl().deci(Double.parseDouble(str));
    }
    public String fi(String str){
    	return new MyZahl().g(str,2);
    }
    public boolean nichtnull (String str){
    	if(str != null && str!="" )return true;
    	else return false;
    }   
    //holle die Bewegung (Journal)
	public String[] tcliste(){
        String[] tcl=null;
	
        sucheDate su =new sucheDate("gastro/Kdate/kjliste.kel");  
        tcl = su.myDaten();
	    String[] listetcl=tcl;
		if(tcl.length>0){	
         for(int i= 0; i<tcl.length; i++){        	        	
         	String[] wort = new ausTeilen().komma(tcl[i]);                 	
         	int keln          = new Methoden().Int(wort[0].trim());
        	int tisch         = new Methoden().Int(wort[1].trim());        	
        	Trec[keln][tisch] = new Methoden().flo(wort[2].trim());
        	Krec[keln]        = new Methoden().flo(wort[3].trim());        	
        	open_kellnertisch(keln,tisch);         	
        	String ztcl="K"+keln+"T"+tisch+": TU("+f(""+Trec[keln][tisch])+") KU("+f(""+Krec[keln])+")";        	
         	listetcl[i]=ztcl;         
         }         
         jpanel(listetcl);
		}		
		open_kellnerumsatz();
     return listetcl;
    }
    // Verkurtze die Bewegungenliste (Journal)
    public String[] tclistechange(String str){    	    	    	
        sucheDate su =new sucheDate(str,"gastro/Kdate/kjliste.kel",0);                	   	  	
    	String[] tcl=su.wahl();
    	String[] ntcl = new String[datalength(tcl)];
    	int x=0;    	    
    	for(int i= 0; i<tcl.length; i++){
    		if(nichtnull(tcl[i])){
    			ntcl[x]=tcl[i];    			
    			x++;
    		}
    	}
    	save_Tcliste(ntcl,false);     
    	return ntcl;
    }
    
    public void save_Tcliste(String[] str, boolean b){    	
        String file = "gastro/Kdate/kjliste.kel";
    	new save().dontsort(file,str,b);
    }
    public String[] open_kellnertisch(int keln, int tisch){    	
    	String file = "gastro/kdate/tische/k"+fi(""+keln)+"t"+fi(""+tisch)+".dat";    	
        sucheDate su =new sucheDate("",file,0);                	
        String[]kt = su.myDaten();                	
    	for(int i= 0; i<kt.length; i++){
    		rec[keln][tisch][i]=kt[i];    		
    	}
    	return kt;        	
    }
    public String[] open_kellnerumsatz(){
    //	System.out.println("KU: "+Datum);
    	String file = "gastro/Kdate/Tagsumsatz/U"+Datum+".dat";
    	sucheDate su =new sucheDate(file);
    	String[] kt = su.myDaten();
    	if(kt.length>0){
    		for(int i= 0; i<kt.length; i++){        	        	
         	 	String[] wort = new ausTeilen().komma(kt[i]);        	
    			KellnerNummer			 = new Methoden().Int(wort[0].trim());
        		Krec[KellnerNummer]      = new Methoden().flo(wort[1].trim());        	
    		}        
    	}
    	return kt;
    }
    
    //nur beim Datum wechsel übertrage den U(datum)blat zum neuen Datum
    public void save_blatKellnerumsatz(String[]str){     	
    	String file = "gastro/Kdate/Tagsumsatz/U"+Datum+".dat";    	
    	new save().dontsort(file,str,false);   	    	
    }
    //U(datum) als Registerfile für alle Kellnerumsätze
    public void save_Kellnerumsatz(){     	
    	String file = "gastro/Kdate/Tagsumsatz/U"+Datum+".dat";    	
    //	s.del(file);   	
    	for(int kel=0;kel<Krec.length;kel++){
    		if(Krec[kel]>0){
    			String str=kel+" , "+Krec[kel];
    			new save().file(file,str,true);   	
    		}
    	}    	
    }
    public void save_Tischinhalt(int keln,int tisch,String str , boolean b){    	
        String file = "gastro/Kdate/Tische/K"+fi(""+keln)+"T"+fi(""+tisch)+".dat";
    	new save().file(file,str,b);
    }
    //A4=100% A5=70% für EPSON Drucker
    public double druckerPapier(){
    	return new papier().getPapierformat();
    }
    //druckt nur Rechnungen nach eingabe den Kellner und den Tisch
    public void recdrucken(){
    	int keln=forkellner();
		while(!cheklength(keln)) keln=forkellner();
		tf.setText("		Kellner :"+keln);
		Versuch=1;
		int tisch=opentisch();		
		while(!T_cheklength(tisch)) tisch=opentisch();
		tisch=isttischvoll();
    	tf.setText(tf.getText()+" 		Tisch: "+tisch);
    	String[][] recdata = inzeile(rec[keln][tisch]);
    	for(int i= 0;i<recdata.length;i++){
    		tap("\n "+fi(""+recdata[i][0])+"  "+fi(""+recdata[i][1]));
    		tap("  "+new ausTeilen().randstS(""+recdata[i][2].trim(),30)+"  "+
    		    f(""+recdata[i][4]));    		
    	}
    	String[] firma =new firma().data();
    	String[] der_k={
    	"B E D I E N U N G",
    	"Tisch "+tisch, 
    	"Kellner "+keln,
    	"Rechnung inkl.",
    	"Steuer und Abgaben", 
    	"",
    	"",
    	""+new myDatum().d(),
    	""+new myDatum().time()
    	};
    	
    	String[] kopf={"Pos","Menge","Bezeichnung","E-Preis","Total"};
        mydruck form = new mydruck("R-Tisch "+fi(""+tisch)+"K"+fi(""+keln));    	
    	if(recdata.length>0){
    	//	new ebm.GF("kellner-k"+fi(""+keln)+"t"+fi(""+tisch),firma,der_k,recdata,kopf,Skale);              
    		form.druck(firma,der_k,recdata,kopf,druckerPapier());
    		tischleeren(keln,tisch);
    	}else mo.fehler("Diese Tisch ist LEEEEEEEEEER!\n kann nicht gedruckt werden!");            
    	tcliste();
    }
    public String[][] d_date(String[][] str){
    	for(int i=0;i<str[0].length;i++){
    		str[i][3]=f(str[i][3]);
    		str[i][4]=f(str[i][4]);
    	}
    	return str;
    }
    
    // volle Tische zeigen
    public void VTisch(){
    	//ta.setText("");
		int i,x,m;
		i=x=m=0;	    	
		for(i = 0;i<rec.length;i++){
			for(x = 0;x<rec[0].length;x++){								
				if(nichtnull(rec[i][x][0])){					
					tap("\n\tKellner ("+fi(""+i)+") Tisch ("+fi(""+x)+")");					
				}
				if(Trec[i][x]>0){
					tap(" T-U> Euro( "+f(""+Trec[i][x])+" ) K-U> Euro( "+f(""+Krec[i])+" )");	
				//	tap("\n_______________________________________________________________");
				}
			}
			
		}
		tap("\n===============\nTotal: "+f(""+Umsatz())+"===========================\n");		
    }
    //Journalregister
    public void save_KuT(String str , boolean b){    	
        String file = "gastro/Kdate/kjliste.kel";    	
    	new save().file(file,str,b);
    }
    //Kellnern register
    public void save_Kumsatz(String str,int keln,boolean b){    	
    	String date="k"+fi(""+keln)+".dat";
    	String file = "gastro/Kdate/umsatz/"+date;
    	new sucheDate(file); 
    	new save().file(file,str,b); 	
    }
   
    // sollte den Tagsumsatz weiter registriert, trotz Datum Änderung möglich sein?
    public void umbuchen(String[] ku){save_blatKellnerumsatz(ku);  }
    //druckt Kellner Tisch oder Registern
    public void drucken(int keln,int tisch,String file){    	
    	tf.setText(tf.getText()+" 		Tisch: "+tisch);    	
    	sucheDate su =new sucheDate(file);  
    	if(su.zeilenZahl()>0){
    	String[][] recdata = inzeile(su.myDaten());//su.teiledaten();    	
    	String[] firma =new firma().data();
    	String[] der_k=
    	{"B E D I E N U N G", "Tisch "+tisch, "Kellner "+keln, "die Rechnung", 
    	"ist ohne MWstr", "",
    	"",
    	""+new myDatum().d(),
    	""+new myDatum().time()};
    	
    	String[] kopf={"Pos","Menge","Bezeichnung","E-Preis","Total"};
        //mydruck form = new mydruck("R-Tisch "+tisch+" Kellner"+keln);
    	//if(recdata.length>0){
    		new ebm.GF("kellner-k"+keln+"t"+tisch,firma,der_k,recdata,kopf,Skale);              
    		//form.druck(firma,der_k,recdata,kopf,druckerPapier());
    		tischleeren(keln,tisch);
    	}else mo.fehler("Diese Tisch ist LEEEEEEEEEER!\n kann nicht gedruckt werden!");        
    	tcliste();
    }
    //bei tisch übernahme oder Rechnungsdruck soll den Tisch Entleert sein
    public void tischleeren(int keln,int tisch){
    	tclistechange(keln+" , "+tisch);
    	for(int i= 0;i<rec[keln][tisch].length;i++){
    		rec[keln][tisch][i]=null;
    	}    	
    	Trec[keln][tisch]=0;
    	s.del( "gastro/Kdate/Tische/K"+fi(""+keln)+"T"+fi(""+tisch)+".dat");    
    }
    //beim Tagesabrechnug sollen alle Tische und Kumsätze  Leer sein
    public void allesleeren(){
    	for(int i = 0;i<rec.length;i++){//kelner =0..20
    		//rec[i]=null;//das grosse Rigisterbuch(Kellner,Tisch und Ware)    		
    		if(Krec[i]>0){
    			save_Kumsatz(Datum+", "+Krec[i],i,true);//registeriere sein Tagesumsatz
    			Krec[i]=0;//Kellnerumsatz löschen
    		}
    		
			for(int x = 0;x<rec[0].length;x++){//300 Tische
				if (Trec[i][x]>0){
					tischleeren(i,x);//der Tisch x auch wird null, und Tischrigister(TxxKxx.dat)null
				//	KellnerNummer=i;//entleere alle Tische
					save_Tischinhalt(i,x,"",false);
				}
			}
    	}    	
    	new WVL("gastro/Kdate",Datum).save_Monatwarenverkauf(true);
    	save_warenverkauf("",false);
    //	save_blatKellnerumsatz(new String[0]);
    }
    public void mussdrucken(){
    	tcliste();
    	String file="";
    	for (int kel=0;kel<Trec.length;kel++){
    		for(int tisch=0;tisch<Trec[0].length;tisch++){
    			if(Trec[kel][tisch]>0){
    				mo.fehler("Kellner: "+kel+" Tisch : "+tisch+" Drucken!");
    				file = "gastro/Kdate/Tische/K"+fi(""+kel)+"T"+fi(""+tisch)+".dat";
    				drucken(kel,tisch,file); 
    			//	s.del(file);
    			}
    		}    		
    	}
    	vkzero();new WVL("gastro/Kdate",Datum).Tv();
        new WVL("gastro/Kdate",Datum).Tagesverkauf();
    	file ="gastro/Kdate/warenverkauf.dat" ;
    	mo.fehler("Heutige Verkaufsliste Drucken!");
    	drucken(0,0,file);
    }
    
    public void chekDatum(){
    	//ist eröffnung datum nicht der eingelegte Udatum
    	// dann stelle eine Abrechnung befor eine neue Umsatzdatum eingelegt wird   
    	chekWare();//WarenListe lesen und die Zähler Vorbereiten    
    	Eingang eg=new Eingang("gastro/Kdate");      	    	//ist d. Buchungsdatum geändert!
    	Datum=eg.AlteDatum;
    	umbuchen(open_kellnerumsatz());    	//kellnerumsatz diese alteDatum neu anlegen
    //	System.out.println(Datum);
    	if(eg.ant>-1){    		    		//ist Änderung Bestätigt
    	//	System.out.println(Datum);
    				vorinit();
    				String tag = Datum.substring(0,2);    			
    				save_Tagesumsatz(""+tag+" , "+Umsatz(),true);//in Register(Monatumsatz)
    				mussdrucken();
    				allesleeren();    				
    				ta.setText("");    				
    				Datum=eg.NewDatum;
    				umbuchen(new String[0]);    	//kellnerumsatz diese new Datum auf null
    		}else Datum=eg.MeinDatum;    	
    //	System.out.println(Datum);
     //return eg.MeinDatum;
    }
    public String[] dirlist(String dir){    	
    	System.out.println(dir);
    	String[]file=new li(dir).files();
    	/*sucheDate su1 =new sucheDate(dir);
    	String[] file=su1.dir();*/
    	for(int i=0;i<file.length;i++){
    		if(file[i].length()>4)
    		file[i]=file[i].substring(0,file[i].length()-4);//.dat weg
    	}
    	return file;
    }
    
	class Mykellner implements ActionListener {        
      public void actionPerformed ( ActionEvent e ) {
        String fr = e.getActionCommand();       
      	//	ta.setText("");
      	if (fr.equals("Kellner")){ 
      	//	ta.setText("");
      		tabeleZero();
      		tf.setText("	Erfasse neue Rechnung");      		
      		erfassen(erfasseKellner());
      	}
      	if (fr.equals("Tisch")){             	
      	//	ta.setText("");
      		tabeleZero();
      		erfassen(KellnerNummer);
      		
      	}
        if (fr.equals("V-Tisch")){             	
        	tabeleZero();
        	VTisch();
        	tlz.setText("Kassabuch Euro "+f(""+Umsatz())+" :> ");
         }
         
         if (fr.equals("T-Listen")){    
         	tabeleZero();       		         	
         	zeigealles();
        	tlz.setText("Kassabuch Euro "+f(""+Umsatz())+" :> ");
         }         
         if (fr.equals("R-Drucken")){        	
         	tabeleZero();
         	tf.setText("	Rechnung Tisch zum Ausdrucken");
        	//ta.setText("");            
         	recdrucken();         		
         }     
         if(fr.equals("T-Absatz")){         	         
         	ChefSachen cs=new ChefSachen("598");
         	String ein=new userFinster("Chef").toString();
         	if (cs.chek(ein).equals("Ok")){
         	    WVL wvl=new WVL("gastro/Kdate",Datum);
         		wvl.Tv();
         		String str=new Umsatz("gastro/Kdate/Tagsumsatz/","U"+Datum).open_Umsatz()+"\n";         		
         		wvl.tc.setText(str);
         		wvl.zeige("T");         		
         	}else {mo.fehler("Sie sind nicht Berichtigt"); }
         }
         if (fr.equals("J-Bricht")){
         	String dir="gastro/kdate/Data"+md.J();         	
    		String str=new Jlist(dirlist(dir),false).element();         	
         	mo.fehler(new Umsatz(dir+"/",str).open_Umsatz());
         	
         }
         if (fr.equals("M-Absatz")){
         	ChefSachen cs=new ChefSachen("598");
         	String ein=new userFinster("Chef").toString();
         	if (cs.chek(ein).equals("Ok")){
         		String str=new Umsatz("gastro/Kdate/Tagsumsatz/","U"+Datum).open_Umsatz()+"\n";
         		/*WVL wvl=new WVL("KDate",Datum);         		    		
         		str+=new WG("KDate",Datum).Ausgabe;
        		wvl.tc.setBackground(Color.white);
				wvl.tc.setForeground(Color.blue);		
        		wvl.tc.setFont(new Font("",0,12));
        		wvl.tc.setText(str);         		
         		wvl.zeige("M");     */
         	WVL wvl=new WVL("gastro/kdate",Datum);
        	wvl.save_Monatwarenverkauf(true);//die warenverkauf des tages erst hollen
        	wvl.save_warenverkauf("",false);//die warenverkauf des tages entfernen        	
        	//wvl.tc.setText(str);
        	wvl.zeige("M");
        	str+=new WG("gastro/kdate",Datum).Ausgabe;
        	//wvl.tc.setBackground(Color.white);
			//wvl.tc.setForeground(Color.blue);		
        	//wvl.tc.setFont(new Font("Courier New",0,12));
        	wvl.tc.setText(str);
        	//wvl.ta.append("\n"+str);
         	}
         	else mo.fehler("Sie sind nicht Berichtigt");      	
         }         	
         if (fr.equals("K-Umsatz")){
           String dir = "gastro/kdate/umsatz";         	
         	String[]dir1=dirlist(dir);
         	if(dir1.length!=0){
         		String str=new Jlist(dir1,false).element();
         		mo.fehler(new Umsatz(dir+"/",str).open_Umsatz());
         	}else mo.fehler("keine Umsatzdaten gefunden!");
         }
         if (fr.equals("T-Umsatz")){
           String dir = "gastro/KDate/Tagsumsatz";         	
         	String[]dir1=dirlist(dir);
         	if(dir1.length>0){
    		String str=new Jlist(dir1,false).element();         	
         	mo.fehler(new Umsatz(dir+"/",str).open_Umsatz());
         	}else mo.fehler("Tagesumsatz Daten nicht gefunden!");
         }
          if (fr.equals("Hilfe")){
          		
          	new hilfe();
          }
      }
   }
   public JPanel jpanel(String[] str){
   	jl.removeAll();
   	east.remove(jl);
   	if(str==null)str=new String[0];
   	JlistApp nl = new JlistApp(str);
   	jl.add(nl);//.getApp());
   
   	jl.validate();
   	east.add(jl);
   	east.validate();
   	return jl;
   }
   public void vorinit(){
   // jl.setBackground(new Color(50,120,70));  	
    east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
  	east.setBorder(new BevelBorder(BevelBorder.RAISED));
  	//east.setBackground(new Color(50,120,70));
   	JPanel jb = new JPanel (new BorderLayout());//GridLayout(1,0));  	
   	east.add(jb);
  	east.add( new JLabel("Akt.Fakture"));
  	//east.add(
  	         jpanel(tcliste());//);//; new JScrollPane (tc));
   }
	public void init(){           
//	hochfahren();
  	ta.setEditable(false);
  	Border brd = BorderFactory.createMatteBorder(10, 10, 10, 10, new Color(50,120,65));  
  	ta.setFont(font);  	
  	mytab.setFont(font);//mytab.setBorder(brd);
  	tf.setFont(font);tf.setEditable(false);  	
  	String[] firma = new firma().data();  
    flabel= new JLabel("<html><center><font size=+1><b>"+
  						firma[0]+"</b><br>"+firma[1]+"<br>"+firma[2]+
  						"</font></center></html>",JLabel.CENTER);
   
   

	//jl.setBackground(new Color(50,120,70));  	
  	east.setLayout(new BoxLayout(east, BoxLayout.Y_AXIS));
  	east.setBorder(new BevelBorder(BevelBorder.RAISED));
  	//east.setBackground(new Color(50,120,70));
  	
  	JPanel lastrow = new JPanel(new GridLayout(3,0));
  	lastrow.setLayout(new BoxLayout(lastrow, BoxLayout.X_AXIS));
  	lastrow.setBorder(new BevelBorder(BevelBorder.RAISED));
  	//lastrow.setBackground(Color.lightGray);
  	
  	JPanel west = new JPanel ();//new GridLayout(2,0));
  	west.setLayout(new BoxLayout(west, BoxLayout.Y_AXIS));
  	west.setBorder(new BevelBorder(BevelBorder.RAISED));
  	//west.setBackground(new Color(50,120,70));
	//west.setForeground(Color.orange);    	
  	
  	JPanel kopfdata= new JPanel ( );//new GridLayout(1,3));
  	kopfdata.setLayout(new BoxLayout(kopfdata, BoxLayout.X_AXIS));
  	kopfdata.setBorder(new BevelBorder(BevelBorder.RAISED));
  	kopfdata.setBorder(new TitledBorder(""));  	
  	//kopfdata.setBackground(new Color(50,120,70));
	//kopfdata.setForeground(Color.orange);  
  	
  	JPanel jb = new JPanel (new GridLayout(JB.length,0));  	
  	jb.setBorder(new TitledBorder("Directions L"));
	jb.setPreferredSize(new Dimension(120,JB.length*40));
  	//jb.setBackground(new Color(50,120,70));
  	
  	JPanel jbw = new JPanel ();
	jbw.setLayout(new BoxLayout(jbw, BoxLayout.Y_AXIS));//new GridLayout(links.length,0));
  	jbw.setBorder(new TitledBorder("Directions R"));  	
	jbw.setPreferredSize(new Dimension(120,links.length*20));	
  	//jbw.setBackground(new Color(50,120,70));
  	
  	JPanel sud = new JPanel ();//new GridLayout(4,0));
  	sud.setLayout(new BoxLayout(sud, BoxLayout.Y_AXIS));
  	sud.setBorder(new TitledBorder("Tabelen ergebnissen"));
  	//sud.setBackground(new Color(50,100,70));
  	
  	JPanel north = new JPanel ();//new GridLayout(2,1));
  	north.setLayout(new BoxLayout(north, BoxLayout.Y_AXIS));
  	north.setBorder(new TitledBorder("Bearbeitung"));
  	//north.setBackground(new Color(50,120,70));
  	
  	for (int i=0; i<JB.length; i++){  	       
  		   JB[i].addActionListener (new Mykellner());
  		   jb.add(JB[i]);  		
  	}
  	for (int i=0; i<links.length; i++){
  	     links[i].addActionListener (new Mykellner());
  		links[i].setPreferredSize(new Dimension(120,20));	
  		   jbw.add(links[i],"Center");  		
  	}  	
  	west.add(jbw,BorderLayout.PAGE_START);  	
		
	east.add(jb,BorderLayout.PAGE_START);
  	east.add( new JLabel("Aktuelle Rechnung"));
  	east.add(jpanel(tcliste()),BorderLayout.PAGE_END);
		//; new JScrollPane (tc));
  //	east.add( new JLabel("",new ImageIcon("image/ebmgstro01.gif"),2));  	
  	//JLabel titelL=new JLabel("<html>"+
    //                   "<center>K E L L N E R V E R W A L T U N G</center></html>",JLabel.CENTER);
  	
  	//north.add(titelL,JLabel.CENTER);	//kopfdata.add(tr,"East");
  		kopfdata.add(flabel,"center");  
		tf.setPreferredSize(new Dimension(180,20));	
  	north.add( new JScrollPane(ta),BorderLayout.PAGE_START); 	
		north.add(kopfdata,BorderLayout.CENTER);  
	north.add(new JPanel(new BorderLayout()));    	  		  		
  	north.add(new JPanel().add(tf),BorderLayout.PAGE_END);    	  		  	
  	
  	
   
  	tlz = new JTextField("Rechnug summe : "+f(""+Umsatz()));
  	JScrollPane js = new JScrollPane(mytab);
	js.setBorder(brd);	
  	//js.setPreferredSize(new Dimension(400, 300));
	//mytab.setBounds(new Rectangle(20,20,410,310)); 	
    sud.add(js,BorderLayout.PAGE_START);
	sud.add(north);	
  	sud.add(new JLabel("<html><font size=4 color=#ffffcc >Kassasystem &copy;El bakry M.</font>"),
  	        BorderLayout.PAGE_END);
  	JPanel p =new JPanel(new GridLayout(1,0));  	
  	p.add(sud);  	  	  	
  	tlz.setEditable(false);
  	tlz.setFont(font);//tlz.setBackground(Color.lightGray);tlz.setForeground(Color.red);
  	lastrow.add(tlz); 
	//lastrow.add(new JPanel().add(new JLabel("EBM < "+Datum.replace('_','.')+" >"))); 				
	
	clock1 c = new clock1();      		  		  	
	c.init();
	c.start();
	c.setVisible(true);
	lastrow.add(c,BorderLayout.EAST); 	
		
	getContentPane().setLayout(new BorderLayout());
	getContentPane().add(p,BorderLayout.CENTER);	  	  	  	
  	getContentPane().add(east, BorderLayout.WEST);  		   	
	getContentPane().add(west,BorderLayout.EAST);  		   	
  	getContentPane().add(lastrow,BorderLayout.SOUTH); 	
	setCursor(new Cursor(12));
  	setVisible(true);	
	
  } 
 // public void hochfahren(){erstegang();}
	public static void main(String[] args) {			
		schow.run(new kellner(),1050,700);
	}
}
