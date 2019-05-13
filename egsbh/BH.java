// created on 01.11.2007 at 14:17
package egsbh;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.AbstractButton;
//KlientPanel
public class BH{	
	JButton[]KButtons,MButtons;
	String[]BF;
	JEditorPane editor;
	JScrollPane js;
	public BH(){		
		BF=new String[]{"Monat","Einkauf","Verkauf","Personalkosten","Bezahle Rechnung","Rechnung bekommen","Rechnung geben","Vorschau"};
		KButtons=button(BF);//activiere die Keys
		js=new JScrollPane();
		refrish_0();
		show();
	}
	JButton[]button(String[]bf){		
		JButton[]s=new JButton[bf.length];
		for(int i=0;i<bf.length;i++){
			s[i]=new JButton("",new egslver.Bimage().img(bf[i]));
			s[i].setActionCommand(bf[i]);	
			s[i].setVerticalTextPosition(AbstractButton.CENTER);//BOTTOM);
    		s[i].setHorizontalTextPosition(AbstractButton.CENTER);	
			s[i].addActionListener(new BHAction());
		}
		return s;
	}
	JPanel Panel_B(){
		JPanel pan =Panel();					
		JButton[]s=KButtons;//=new JButton[20];		
		pan.setLayout(new GridLayout(s.length,0));		
		for(int i=0;i<s.length;i++){
			pan.add(s[i]);
		}		
		pan.setBorder(new javax.swing.border.TitledBorder(null,"Buche"));//setBorder(new LineBorder(Color.blue,1,false));
		JPanel Bpan=Panel();	
		Bpan.add(pan,BorderLayout.PAGE_START);
		return Bpan;
	}
	JPanel Panel(){
		JPanel pan = new JPanel();	
		pan.setLayout(new BorderLayout());
		pan.setBorder(new javax.swing.border.TitledBorder(null,""));//setBorder(new LineBorder(null,2,true));
		return pan;
	}	
	String openTemp(String file){
 		return new com.search.sucheDate(file).md();
 	}
 	void refrish_0(){
		String Form=new formhtml().gen();
		editor = new JEditorPane("text/html",Form);						
		js.setViewportView(editor);		
	}
 	void refrish(){
		String Form=openTemp("egsbh/temp/bhform.tmp"); 		
		editor = new JEditorPane("text/html",Form);						
 		//js.setPreferredSize(new Dimension(640,535));		
		js.setViewportView(editor);		
	}
	String[]kaufen(){
		String[]str={
			"<html>Wann? Datum oder Tag eingeben<br> TTMMYY od. TTMM od.TT <br> 010807 od.0108 od. 01 ",//0
			"Was haben Sie gekauft? \nWaren Einsatz 10%\nWaren Einsatz 20%"+
			"\nVerbrauchsmaterial 10%\nVerbrauchsmaterial 20%"+
			"\nHygiene und Reinigung 20%\nLKW Kraftstoff 20%",//1
			"Zahlen Sie vom Kasse oder Bank? \nKasse\nBank",//2
			"Es das Brutto oder Netto? \nNetto\nBrutto",//3			
			"Wie Hoch ist den Betrag? ",		//4
			"Von wo haben Sie gekauft? "//5
			//"Wie ist es versteuert? \n10\n20",//4			
		};		
		String[]ant=new OP(str).Gen();	
		String steuer="";
		ant[0]=Tdatum(ant[0]);
		if(ant[1].indexOf("10")>-1)steuer="10";else steuer="20";
		//Datum,gegenkonto,Betrag,BruttoNetto,KassaBank,steuer
		new Einkauf(ant[0],ant[1],ant[4],ant[3],ant[2],steuer,ant[5]);
		new Form();
		return ant;
	}

	String[]verkaufen(){
		String[]str={
			"<html>Wann? Datum oder Tag eingeben<br> TTMMYY od. TTMM od.TT <br> 010807 od.0108 od. 01 ",//0
			"Was haben Sie Verkauft? \nUmsatz Inland 10%\nUmsatz Inland 20%"+
			"\nUmsatz aus den Verkauf von Anlagen 10%\nUmsatz aus den Verkauf von Anlagen 20%",//1			
			"Es das Brutto oder Netto? \nNetto\nBrutto",//2			
			"Wie Hoch ist den Umsatzbetrag? "//3
			//"Wie ist es versteuert? \n10\n20",//4			
		};		
		String[]ant=new OP(str).Gen();	
		String steuer="";
		if(ant[1].indexOf("10")>-1)steuer="10";else steuer="20";
		ant[0]=Tdatum(ant[0]);
		//Datum,gegenkonto,Betrag,BruttoNetto,KassaBank,steuer
		new Verkauf(ant[0],ant[1],ant[3],ant[2],"kassa",steuer,"Umsatz "+steuer+"%");
		new Form();
		return ant;
	}
	String[]personal(){
		String[]str={
			"<html>Wann? Datum oder Tag eingeben<br> TTMMYY od. TTMM od.TT <br> 010807 od.0108 od. 01 ",//0
			"Was haben Sie bezahlt "+
			"\nLöhne"+
			"\nGehälte"+
			"\nMitarbeite Vorsorge Kassa"+
			"\nGesetzlicher Sozialaufwand Arbeiter"+
			"\nGesetzlicher Sozialaufwand Angestellte"+
			"\nKommunalsteuer"+
			"\nDienstgebebeitrag zur Familienbeihilfe DB"+
			"\nDienstgeber Zuschlag DZ"+
			"\nFreiwilliger Sozialaufwand"+
			"\nGewerblich Sozial Aufwand",	//1
			"Wie Hoch ist den Betrag? ",//2
			"Zahlen Sie vom Kasse Bank oder ofen bleiben? \nofen\nKasse\nBank"//3
			//"Wie ist es versteuert? \n10\n20",//4			
		};		
		String[]ant=new OP(str).Gen();	
		String steuer="0";		
		ant[0]=Tdatum(ant[0]);
		//Datum,gegenkonto,Betrag,KassaBank
		new PR(ant[0],ant[1],ant[2],ant[3]);
		new Form();
		return ant;
	}
	
	String Tdatum(String str){
		int len=str.length();
		String nstr=str;
		String monat=openTemp("egsbh/datum.dat");
		if(len<2)nstr="0"+str+monat;else 
		if(len==2)nstr=str+monat;else 
		if(len==4)nstr=str+monat.substring(2,monat.length());else 
		if(len>4 && len<6 && str.indexOf(monat)>-1)nstr="0"+str;		
		return nstr;
	}
	String monat(){ 	
 		String str=new com.options.MyOp().wahl(liste(),"Welche Monat?");
		new com.units.save().file("egsbh/datum.dat",str,false);
		return str;
 	}
 	String[]liste(){
 		String[]Datum=new String[12];
 		for(int m=0;m<Datum.length;m++){
 			String dat=new com.units.myDatum().jahr();
 			int x=m+1;
 			if(x<10)dat="0"+x+dat;else dat=x+dat;
 			Datum[m]=dat;
 		}
 		return Datum;
 	}
 	
	class BHAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String t = e.getActionCommand();   		
			if(t.equals("Einkauf")){
				kaufen();refrish();
			}
			if(t.equals("Monat")){
				monat();
			}
			if(t.equals("Verkauf")){
					verkaufen();refrish();
			}
			if(t.equals("Personalkosten")){
				personal();refrish();
			}
			if(t.equals("Zahle Rechnung")){
				
			}
			if(t.equals("Rechnung bekommen")){
				
			}
			if(t.equals("Rechnung geben")){
				
			}
			if(t.equals("Vorschau")){
				new Form();refrish();
			}
		}
	}
	public void show(){ 			
		JPanel pan = Panel();			
		//pan.setPreferredSize(new Dimension(840,640));		
		pan.add(Panel_B(),BorderLayout.LINE_START);
		pan.add(js,BorderLayout.CENTER);		
		JFrame f=new JFrame("Buchhaltung -- Ebm GastroService 2006-2008");
		f.setContentPane(pan); 
		f.pack();		
		f.setVisible(true);	
		f.setDefaultCloseOperation(2);				
	}	
	public static void main(String[]args){
		new BH();
	}
}
