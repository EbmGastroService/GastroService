// created on 16.10.2006 at 23:28
package ebm;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.*;
import javax.swing.JEditorPane;
import ebm.KB;
import java.util.*;
public class monatErlose {
	int[]ml;int wahl;
	JButton[] bu={
		new JButton("Januar"),new JButton("Febuar"),
		new JButton("Martz"),new JButton("April"),
		new JButton("Mai"),new JButton("Juni"),
		new JButton("Juli"),new JButton("August"),
		new JButton("September"),new JButton("Oktober"),
		new JButton("November"),new JButton("Dezember")		
	};	
	JButton help=new JButton("Hilfe");
	JButton upd=new JButton("Update");
	JButton bhl=new JButton("BH Liste");
	JButton druck=new JButton("Drucken");
	JPanel jp;
	JFrame jf;
	JTable tabe;		
	JEditorPane ta;
	String[]jahrT=new ebm.li("gastro/d2?").ordner();		//new myDir().jahr;	
	JTextField jtf=new JTextField(jahrT[0]);
	int jahr;
	String Ziel;
	JComboBox jcb = new JComboBox(jahrT);		
	JPanel blat=new JPanel();
	public monatErlose(String ZielJahr,int ZielMonat){				
		Ziel=ZielJahr;
		druck.addActionListener(new MAction());
		zeige(ZielMonat);
	}
	public monatErlose(){		
		Ziel=jahrT[jahr];
		init();
	}
	void init(){
		jcb.addActionListener(new CB());		
		jtf.setEditable(false);
		jtf.setForeground(java.awt.Color.red);
		int[]ml={0,31,29,31,30,31,30,31,31,30,31,30,31};
		wahl=0;
		help.addActionListener(new MAction());
		upd.addActionListener(new MAction());
		bhl.addActionListener(new MAction());
		druck.addActionListener(new MAction());
		jp=new JPanel();
		JPanel jjp=new JPanel();
		JPanel jptf=new JPanel();
		jptf.setLayout(new BorderLayout());				
		jptf.add(new JLabel("Ziel Jahr"));
		jptf.add(jtf);
		jjp.setLayout(new javax.swing.BoxLayout(jjp, javax.swing.BoxLayout.X_AXIS));
		jjp.setPreferredSize(new java.awt.Dimension(150,200));
		jjp.add(jcb);	
		jjp.add(jptf);		
		jp.setLayout(new GridLayout(bu.length+6,0));
		jp.add(new JLabel("<html><font color=blue>Ihre Wahl <br>",JLabel.CENTER));
		jp.add(help);
		jp.add(jjp);
		jp.add(upd);
		jp.add(bhl);
		jp.add(new JLabel(""));
		
		for(int i=0;i<bu.length;i++){
			bu[i].addActionListener(new MAction());
			jp.add(bu[i]);
		}
		
		//System.out.println(wahl);
		jf=new com.options.frame("MonatsWahl|geschlossen in 5 Minuten",300);		
		jf.getContentPane().add(jp);
		
		//jf.getContentPane().add(tabe);
		jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		jf.setSize(150,600);
		//jf.pack();
		jf.setVisible(true);
	}
	String[]openFile(String nfile){
		String[]te=new com.search.sucheDate(nfile).myDaten();		
		return te;
	}
	String bhFile(int i){
		String it="";
		if(i<10)it="0"+i;
		else if(i>12){
			i=12;
			it=""+i;
		}else it=""+i;
		String zj=Ziel.substring(3,Ziel.length());//D2007
		String zm="bh"+it+zj+"n.dat";
		//System.out.println("gastro/date/data_2007/tv"+zj+"n/"+zm);
		return "gastro/date/data"+new com.units.myDatum().J()+"/tv"+zj+"n/"+zm;
	}
	void zeigeBhl(int i){		
		new ebm.Bhl(bhFile(i),i);
	}
	void zeige(int i){		
		String it="";
		if(i<10)it="0"+i;
		else if(i>12){
			i=12;
			it=""+i;
		}else it=""+i;
		String[]te=openFile("gastro/"+Ziel+"/M"+it+Ziel.substring(3,5)+".dat");
		String[]erg=openFile("gastro/"+Ziel+"/E"+it+Ziel.substring(3,5)+".dat");		
		mhtml(erg,te,i);
		
	}
	void mhtml(String[]erg,String[]te,int mi){
		if(te.length>0){
			String[][]str=inclomen(te);
			String hm="<html><b>"+erg[0]+"</b>";
			for(int x=1;x<erg.length;x++)hm+="<br>"+erg[x];			
			hm+="<br><br><table cellpadding=0 cellspacing=0 width=400 ><tr>";			
			hm+="<td align=right>Datum</td>";
			hm+="<td align=right>von - bis";
			hm+="</td><td align=right>A-Rec";
			hm+="</td>";
			hm+="<td align=right>T-Rec</td><td align=right>T-Wert</td><td align=right>Gesamt</td></tr>";
			hm+="<tr><td colspan=6><hr></td></tr>";
			for(int i=0; i<str.length;i++){
				hm+="<tr>";
				for(int x=0; x<str[i].length-2;x++){
					hm+="<td align=right>"+str[i][x]+"</td>";
				}
				for(int x=str[i].length-2; x<str[i].length;x++){
					hm+="<td align=right>"+f(str[i][x])+"</td>";
				}
				hm+="</tr>";
			}
			hm+="</table>";
			ta=new javax.swing.JEditorPane("text/html",hm);
			javax.swing.JScrollPane js=new javax.swing.JScrollPane();
			js.setViewportView(ta);
			blat=new javax.swing.JPanel();
			blat.setOpaque(true);
			blat.setBackground(java.awt.Color.white);
			blat.setLayout(new java.awt.BorderLayout());
			blat.add(js,java.awt.BorderLayout.PAGE_START);
			javax.swing.JFrame jf=new com.options.frame("Umsatzverteilung des Monats"+Ziel,180);
			jf.getContentPane().add(blat,java.awt.BorderLayout.PAGE_START);
			jf.getContentPane().add(druck,java.awt.BorderLayout.PAGE_END);
			jf.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
			jf.pack();
			//	jf.setBounds(0, 0, 460,840);
			//jf.setSize(480,500);
			jf.setLocation(50+(mi*20),10+(mi*10));
			jf.setVisible(true);
		}		
	}
	public String f(String in){
   	float d=0;   
   	try{
   		d=Float.parseFloat(in);
   	}catch(Exception e){d=0;};
   	return new com.units.MyZahl().deci(d);
   } 	
	void drucken(){
		com.printer.JComponentVista vista = new com.printer.JComponentVista(ta , new java.awt.print.PageFormat()); 
 		vista.scaleToFit(true,new com.printer.papier().getSkale()); 
		//vista.scaleToFitX(); 
		//vista.scaleToFit(true,Skale); 
 		java.awt.print.PrinterJob pj = java.awt.print.PrinterJob.getPrinterJob();
		pj.setJobName(Ziel);
		pj.setPageable(vista);
		try {
			pj.print();
		} catch (java.awt.print.PrinterException e) {System.out.println(e);}	
	}
	String[][]inclomen(String[]str){
		String[][]nstr=new String[0][0];
		if(str.length>0 &&str[0]!=null){
			String[]w=new com.options.ausTeilen().koma(str[0]);
			nstr=new String[str.length][w.length];
			for(int i=0;i<nstr.length;i++){
				w=new com.options.ausTeilen().koma(str[i]);				
				for(int x=0;x<nstr[i].length;x++){
					nstr[i][x]=w[x];
				}
			}
		}
		return nstr;
	}
	JTable getTab_t(String[]inhalt){
		JTable mytab=new JTable(inhalt.length, 5);		
		String[]str={"Datum","von - bis R","T-R","G-R","T-Betrag","M-Total"};	
        mytab =new RcTab(inclomen(inhalt),str).tab();				
		return mytab;				
	}
	
	JTable getTab(String[]inhalt){
		JTable mytab=new JTable(inhalt.length, 5);		
		for(int i=0;i<inhalt.length;i++){
			String[] wort = new com.options.ausTeilen().komma(""+inhalt[i],";");
			for(int x=0; x<5;x++){
				mytab.setValueAt(wort[x],i,x);
			}
		}		
		return mytab;				
	}
	void helpMe(){
		com.options.frame f=new com.options.frame("Ebm GastroService 2006|geschlossen in 60 Secunden",60);//Secunden
		f.setContentPane(new JLabel("<html><font color=blue>F: Sie sehen keine Ergebnissen!<br>"+
		                            "L: gehen Sie zum Hauptprogram Menu - Kassabuch Menu <br>"+
		                            "   Monaterlose und das Monat Ihre Wahl!<br>"+
		                            "	dann wiederholen Sie ihre wahl hier<br><br>"+
		                            "F: Sie wollen Ergebnissen das vergangen Jahr anschauen!<br>"+
		                            "L:	dann Hier oben Jahr aussuchen und die Monatentasten<br>"+
		                            "	klicken!",JLabel.CENTER));
		f.setSize(400,200);
		f.setLocation(200,50);
		f.setVisible(true);	
	}
	void update(int i){
		String it="";		
		if(i<10)it="0"+i;
		else if(i>12){i=12;it=""+i;}else it=""+i;
		String mfile="gastro/"+Ziel+"/KB"+it+Ziel.substring(3,5)+"K.dat";
    	String[]str={"Datum","von bis R","T-R","G-R","T-Betrag","M-Total"};
		javax.swing.JScrollPane tab =new RcTab(inclomen(new KB().KB_file_T(mfile)),str).app();
		com.options.frame myf=new com.options.frame("Monaterlose"+Ziel,100);
		myf.setContentPane(tab);myf.setVisible(true);myf.pack();myf.setLocation(160,50);
	}
	class MAction implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {        	
        	String fr = e.getActionCommand();        	
        	if(fr.equals(help.getText())){
        		helpMe();
        	}
        	if(fr.equals(upd.getText())){
        		update(wahl+1);
        	}
        	if(fr.equals(druck.getText())){
        		drucken();
        	}
        	if(fr.equals(bhl.getText())){
        		zeigeBhl(wahl+1);
        	}
        	for(int i=0;i<bu.length;i++){
        		if(fr.equals(bu[i].getText())){
        			wahl=i;        	
        			zeige(wahl+1);
        			zeigeBhl(wahl+1);
        		}        			
        	}        	        	
        	//System.out.println("Ihre Wahl="+(wahl+1)+","+bu[wahl].getText());
        	
        }
	}
	class CB implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			jahr=jcb.getSelectedIndex();						
			Ziel=jahrT[jahr];
			jtf.setText(Ziel);
			jtf.validate();
       }
	}
	
	 public static void main(String[] args) {
	 	if(args.length>1){
	 		int mon=1;
	 		try{mon=Integer.parseInt(args[1]);}catch(Exception e){mon=8;}
	 		new monatErlose(args[0],mon);
	 	}else new monatErlose();
	 }
}
