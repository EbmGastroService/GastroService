// created on 22.10.2007 at 16:33

/*
 * Bedienung Pane 
 * Autor Mourad Elbakry
 */
 package egslver;
 import javax.swing.JPanel;
 import javax.swing.JLabel;
 import javax.swing.JTextField;
 import javax.swing.JFrame;
 import java.awt.BorderLayout;
 import java.awt.Color;
 import javax.swing.border.*;
import javax.swing.BorderFactory;
 import javax.swing.JOptionPane;
 
 public class BP{
 	JPanel consola;
 	JPanel[]Zeile;
 	JLabel label;
 	JTextField[]Input;
 	int L;
 	String[]DateIn,p;
 	String test;
 	String MaCode;
 	String KlientCode;
 	public BP(String[]DateIn){
 		L=DateIn.length;
 		this.DateIn=DateIn;  		
 		p=new String[L];
 		init(); 		
 		//inhalt();
 		zeige();
 	}
 	public BP(String[]DateIn,String derCode){
 		L=DateIn.length;
 		this.DateIn=DateIn;  		
 		p=new String[L];
 		p[0]=derCode;
 		init(); 		
 		//inhalt();
 		zeige();
 	}
 	public BP(String KlientCode,String MaCode){
 		if(KlientCode.toLowerCase().indexOf("k")>-1)KlientCode=KlientCode.substring(1,KlientCode.length());
 		if(MaCode.toLowerCase().indexOf("m")>-1)MaCode=MaCode.substring(1,MaCode.length());
 		Testk(KlientCode);
 		String[]MaDate=open("egslv/k"+KlientCode+"/sd/m"+MaCode+"_sd.dat"); 	 		 		 	
 		if(MaDate!=null && MaDate.length>0){
 			L=MaDate.length; 		
 			DateIn=Ein("m"); 			
 			p=MaDate;
 			show(); 			
 		}else {
 			DateIn=Ein("m"); 			
 			L=DateIn.length;
 			p=new String[L];
 			show();
 		}
 	}
 	public BP(String KlientCode,String MaCode,String datum){
 		this.KlientCode=KlientCode;
 		this.MaCode=MaCode;
 		Testk(KlientCode);
 		String[]MaDate=open("egslv/k"+KlientCode+"/sd/m"+MaCode+"_sd.dat"); 	 		 		 	
 		if(MaDate!=null && MaDate.length>0){
 			L=MaDate.length; 		
 			DateIn=Ein("m"); 			
 			p=MaDate;
 			String od=p[11];
 			p[11]=datum;
 			if(KlientCode!=null && KlientCode.length()>0)saveMaCD(od,datum);
 		}else {
 			DateIn=Ein("m"); 			
 			L=DateIn.length;
 			p=new String[L];
 			show();
 		}
 	}
 	public BP(String KlientCode){ 		
 		Testk(KlientCode);
 		show();
 	}
 	void Testk(String KlientCode){
 		String[]MaDate=open("egslv/k"+KlientCode+"_sd.dat"); 	 		 		 	
 		if(MaDate!=null && MaDate.length>0){
 			L=MaDate.length; 		
 			DateIn=Ein("k"); 			
 			p=MaDate;
 		//	show();
 		}else {
 			DateIn=Ein("k"); 			
 			L=DateIn.length;
 			p=new String[L];
 			p[0]=KlientCode;
 			show();
 		}
 	}
 	void show(){
 		init(); 		
 		inhalt(); 		
 	}
 	String[]Ein(String wer){ 			
 		String[]ein=null;
 		if(wer.equals("m"))ein= open("egslv/resource/masdd.dat");else 
 		if(wer.equals("k"))ein= open("egslv/resource/klsdd.dat");		 		 		
 		return ein;
 	}
 	void zeige(){
 		if(p!=null){for (int i=0;i<p.length;i++)System.out.println(p[i]);}
 	}
 	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	//JPanel
 	void init(){
 		consola=new JPanel();
 		consola.setLayout(new java.awt.GridLayout(2+L/2,2));
 		consola.setOpaque(true);		
 		label=new JLabel("",JLabel.CENTER);
 		Zeile=new JPanel[L]; 		
 		Input=new JTextField[L]; 		
 		Input[0]=new JTextField(p[0]);
 		for (int i=0;i<L;i++){
 		//	p[i]="";
 			Zeile[i]=new JPanel();
 			Zeile[i].setOpaque(true);
 			//Zeile[i].setBackground(new ebm.myColor("hg").getColor()); 			
 			Zeile[i].setBorder(new javax.swing.border.TitledBorder(null,(i+1)+" "+DateIn[i])); 			
 			//Zeile[i].setBorder(new javax.swing.border.TitledBorder(null,(i+1)+" "+DateIn[i])); 			
 			if(i>0)Input[i]=new JTextField(p[i]);
 			Input[i].selectAll();;
 			Input[i].setPreferredSize(new java.awt.Dimension(280,20)); 	
 			Zeile[i].add(Input[i]);//,BorderLayout.LINE_END);
 			consola.add(Zeile[i]);
 		}
 		consola.add(label);
 		//return console;
 	} 	
 	public int panel(){//String str,JPanel p){ 		
		Object[] options ={"OK","Abbrechen"};
   	    int erg=JOptionPane.showOptionDialog(null, consola, "Abfrage "+DateIn[0],JOptionPane.DEFAULT_OPTION,
          JOptionPane.PLAIN_MESSAGE, null, options,Input[0]);  
 			if(erg==0){
			for(int i=0;i<L;i++){
				p[i]=""+Input[i].getText();
			}			
		}else erg=-1;		
		return erg;
	}
	void setBorder(int i,Color color,String fehler){
		Border redline = BorderFactory.createLineBorder(color);
		Zeile[i].setBorder(new javax.swing.border.TitledBorder(redline,(i+1)+" "+DateIn[i]+" "+fehler));		
	};
 	void DV(){//Dienstverhältnise und Gruppe
 		if(DateIn[0].indexOf("Mitar")>-1){
 			if(p[10].toLowerCase().indexOf("arb")>-1)p[16]="A1";
 			if(p[10].toLowerCase().indexOf("arb")>-1 && p[8].toLowerCase().indexOf("ger")>-1)p[16]="N14";
 			if(p[10].toLowerCase().indexOf("ang")>-1)p[16]="D1";
 			if(p[10].toLowerCase().indexOf("ang")>-1 && p[8].toLowerCase().indexOf("ger")>-1)p[16]="M14";
 			if(p[11].length()<10){p[11]=new com.units.myDatum().ist();setBorder(1,Color.red,"TT.MM.YYYY");}
 			if(p[13].toLowerCase().indexOf(",")>-1)p[13]=p[13].replace(',','.');
 			Input[11].setText(p[11]);
 			Input[13].setText(p[13]);
 			Input[16].setText(p[16]);
 			
 		}
 	}
	String fehler(){
		String fehler="";
		int x=0;
		for(int i=0;i<L;i++){
			p[i]=Input[i].getText();
			DV();
			if(p[i].length()==0){	
				setBorder(i,Color.red,"");
				fehler+=""+(i+1)+" ,";
			}else setBorder(i,Color.blue,"");
		}
		return fehler;
	}
	public String[]inhalt(){		
		int X=3;
		if(panel()==0){		
			for(int i=0;i<X;i++){
				String fehler=fehler();
				if(fehler.length()>1){		
					label.setText("<html><font color=red><b>Ihre Eingabe ist Fehlerhaft</b><br>"+
					                               fehler+" fehlen!"+"<br> noch "+(X-i)+" Versuchungen!!");
					
							
					if(panel()<0){p=null;i++;}
				}			
			}			
		}else p=null;
		return p;
	}
	void saveMaCD(String od,String datum){
		if(MaCode!=null || MaCode!=" Leider nicht Vorhanden" || MaCode!=""){
			show();		
			save("egslv/k"+KlientCode+"_mc.dat",MaCode+","+od+","+datum);
			String file="egslv/k"+KlientCode+"/sd/m"+MaCode+"_sd.dat";			
			save(file,p,false);
		}
 	}
 	void save(String file,String[]date,boolean wie){
 		new com.units.save().dontsort(file,date,wie);
 	}
 	void save(String file,String date){
 		new com.units.save().file(file,date,true);
 	}
	public static void main(String[]args){	
		if(args.length<2)new BP(args[0]);
		if(args.length>=2)new BP(args[0],args[1]);
 		
	}
 }
