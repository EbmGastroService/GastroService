// created on 18.01.2007 at 23:24
package ebm;
import java.awt.event.*;
import javax.swing.JComponent ;
public class Bhl{
	String file;
	String datum;
	int fpos;
	int wahl;
	javax.swing.JPanel blat;
	javax.swing.JButton druck=new javax.swing.JButton("Drucken");
	public Bhl(String file,String datum,int fpos){
		this.file=file;
		this.datum=datum;
		this.fpos=fpos;		
		wahl=0;
		init();
	}
	public Bhl(String file,int wahl){
		this.file=file;
		this.wahl=wahl;
		datum="Buchhaltungsliste";		
		fpos=5;
		init();
	}
	String[]lesen(){
		return new com.search.sucheDate(file).myDaten();	
	}
	String[][]dString(String[]tot){
		java.util.Arrays.sort(tot);
		String[]wort=new com.options.ausTeilen().koma(tot[0]);
		String[][]str=new String[tot.length][wort.length];//{"0","0","0","0","0"};
		for(int i=0; i<str.length;i++){
			String myvec=tot[i];
			wort=new com.options.ausTeilen().koma(myvec);
			for(int x=0; x<wort.length;x++)
			str[i][x]=""+wort[x];
		}
		return str;		
	}
	String f(String str){
		return new com.units.Methoden().f(str);
	}	
	String[][]vorbreiten(){
		String[][]str=dString(lesen());		
		int l=str.length+1;
		String[][]nstr=new String[l][5];		
		float tu20=0;float tu10=0;float tz20=0;float tsum=0;
		for(int i=0; i<str.length;i++){
			float u20=0;float u10=0;float z20=0;float sum=0;
			for(int x=1; x<str[0].length;x++){
				if(x<=6)u10+=fl(str[i][x]);		
				else if(x>6 && x<str[0].length-1)u20+=fl(str[i][x]);		
				else z20=fl(str[i][x]);				
			}
			sum=u10+u20+z20;
			tu10+=u10; tu20+=u20; tz20+=z20;
			tsum+=sum;
			
			nstr[i][0]=str[i][0];nstr[i][1]=f(""+u10);
			nstr[i][2]=f(""+u20);nstr[i][3]=f(""+z20);nstr[i][4]=f(""+sum);
		}
		l-=1;
		nstr[l][0]="Summe";
		nstr[l][1]=f(""+tu10);
		nstr[l][2]=f(""+tu20);nstr[l][3]=f(""+tz20);nstr[l][4]=f(""+tsum);
		saveCsv(nstr);
		return nstr;
		
	}
	void saveCsv(String[][]mstr){
		String d=mstr[0][0].substring(2,mstr[0][0].length()-1);
		for(int i=0; i<mstr.length;i++){					
			for(int x=0; x<mstr[i].length;x++){
				mstr[i][x]="\""+mstr[i][x]+"\"";
			}			
		}
		new com.units.save().dfilefelde("gastro/date/cvs/bhl"+d+".csv",mstr,false);
	}
	
	String mhtml(){
		String[][]str=vorbreiten();	
		String[]sp0 = new com.search.sucheDate("gastro/source/stsatz.cfg").myDaten();
		String hm="<html><body width=450><center><center>Steuerverteilung f&uuml;r die Periode "+
		str[0][0].substring(2,str[0][0].length()-1)+"<br>";
		hm+="<table cellpadding=0 cellspacing=0 width=400><tr>";
		hm+="<td align=right>Datum</td>";
		hm+="<td align=right>U";
		hm+=sp0[0]+"</td><td align=right>U";
		hm+=sp0[1]+"</td>";
		hm+="<td align=right>Servicegeb.</td><td align=right>Total</td></tr>";
		hm+="<tr><td colspan=5><hr></td></tr>";
		for(int i=0; i<str.length-1;i++){		
			hm+="<tr>";
			for(int x=0; x<str[i].length;x++){
				hm+="<td align=right>"+str[i][x]+"</td>";
			}
			hm+="</tr>";
		}
		hm+="<tr><td colspan=5><hr></td></tr>";		
		for(int i=str.length-1; i<str.length;i++){		
			hm+="<tr>";
			for(int x=0; x<str[i].length;x++){
				hm+="<td align=right>"+str[i][x]+"</td>";
			}
			hm+="</tr>";
		}
		hm+="</table></center></body></html>";
		return hm;
	}
	String mtext(){
		com.options.ausTeilen aus=new com.options.ausTeilen();
		String[][]str=vorbreiten();
		String hm="Steuer Verteilung des Monat:"+str[0][0].substring(2,str[0][0].length()-1)+"\n";		
		hm+="________________________________________________________\n";
		hm+=aus.randstS("Datum ",12)+aus.randstS("G %10  ",12)+""+aus.randstS("G %20  ",12)+""+aus.randstS("ZG %20  ",12)+aus.randstS("  Total",12)+"\n";
		hm+="________________________________________________________\n";
		
		for(int i=0; i<str.length-1;i++){		
			//hm+="\n";
			for(int x=0; x<str[i].length;x++){
				hm+=aus.randstS(""+str[i][x],12);
			}
			hm+="\n";
		}
		hm+="________________________________________________________\n";
		for(int i=str.length-1; i<str.length;i++){		
			//hm+="\n";
			for(int x=0; x<str[i].length;x++){
				hm+=aus.randstS(""+str[i][x],12);
			}
			hm+="\n";
		}
		//hm+="</table>";
		return hm;
	}
	void init(){
		druck.addActionListener(new druckAction());
		javax.swing.JScrollPane js=new javax.swing.JScrollPane();	
		js.setViewportView(editor());
		blat=new javax.swing.JPanel();
		blat.setLayout(new java.awt.BorderLayout());
		javax.swing.JPanel dblat=new javax.swing.JPanel();
		dblat.setLayout(new java.awt.BorderLayout());
		dblat.add(druck,java.awt.BorderLayout.CENTER);	
		blat.add(js,java.awt.BorderLayout.PAGE_START);	
      	javax.swing.JFrame jf=new com.options.frame(" Umsatzsteuer Verteilung ",180);	
		jf.getContentPane().add(blat,java.awt.BorderLayout.PAGE_END);
		jf.getContentPane().add(dblat,java.awt.BorderLayout.PAGE_START);		
		jf.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);		
		jf.pack();
	//	jf.setBounds(0, 0, 460,840);	
		jf.setSize(480,740);
		jf.setLocation(600+(fpos*10),10);
		jf.setVisible(true);      
		
	}	
	JComponent editor(){
		javax.swing.JComponent ta;
		if(wahl>0){
		 ta=new javax.swing.JEditorPane("text/html",mhtml());
		//	ta.setFont(new java.awt.Font("",0,8));
		}else {
			ta=new javax.swing.JTextArea(mtext());
			ta.setFont(new java.awt.Font("Courier New",0,12));							
		}
		//ta.setPreferredSize(new java.awt.Dimension(480, 640));
		ta.setBounds(0, 0, 480,640);	
		//ta.setVisible(true); 
		return ta;
	}
	float fl(String str){
		float total=0;
		try{
			total=Float.parseFloat(str);
		}catch(Exception ex){total=0;}
		return total;
	}
	void drucken(){
		com.printer.JComponentVista vista = new com.printer.JComponentVista(editor() , new java.awt.print.PageFormat()); 
 		vista.scaleToFit(true,new com.printer.papier().getSkale()); 
		//vista.scaleToFitX(); 
		//vista.scaleToFit(true,Skale); 
 		java.awt.print.PrinterJob pj = java.awt.print.PrinterJob.getPrinterJob();
		pj.setJobName("Auftarg_");
		pj.setPageable(vista);
		try {
			pj.print();
		} catch (java.awt.print.PrinterException e) {System.out.println(e);}	
	}
	class druckAction implements ActionListener {
        public void actionPerformed (ActionEvent e ) {        	
        	String fr = e.getActionCommand();     
        	System.out.println(fr+" Actioncommand und Button:"+druck.getText());
        	if(fr.equals(druck.getText())){
        		drucken();
        	}
        }
	}

	public static void main(String[] args) {new Bhl("gastro/date/data_2008/tv08n/bh0108n.dat",1);}
}
