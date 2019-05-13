// created on 01.11.2007 at 14:17
package egslver;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JEditorPane;
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
import com.printer.JComponentVista;
import java.awt.print.*;
//MitarbeiterPanel
public class LVM{	
	JButton[]MButtons;
	String[]K;
	String[]M;			
	String klientCode;
	String MaCode;
	JEditorPane editor;
	JEditorPane TP;
	JScrollPane js,jt;
	JComponentVista vista;
	JFrame f;
	float Skale;
	public LVM(){				
		klientCode="";//klient();
		MaCode="";//Ma();
		//K=stammDaten("k");		
		//M=stammDaten("m");			
		MButtons=button(new lvstruck().mabedd());//activiere die Keys				
		js=new JScrollPane();		
		jt=new JScrollPane();		
		refrish_TP();
		refrish_0();
		f=new JFrame("Lohnverrechnung "+new com.units.myDatum().J()+" Klient: "+klientCode);
		show();
	}
	public LVM(String klientCode){				
		this.klientCode=klientCode;
		//klient();
		Skale =(float)0.97;
		///test();//MaCode="";//Ma();
		MButtons=button(new lvstruck().mabedd());//activiere die Keys				
		js=new JScrollPane();		
		jt=new JScrollPane();		
		refrish_TP();
		refrish_0();
		f=new JFrame("Lohnverrechnung "+new com.units.myDatum().J()+" Klient: "+klientCode);
		show();
	}
	void refrish_0(){
		String Form=openTemp("egslv/temp/mform.html");
		editor = new JEditorPane("text/html",Form);						
		js.setViewportView(editor);				
	}
	void refrish(){
		String Form=openTemp("egslv/temp/mform.tmp");
		editor = new JEditorPane("text/html",Form);						
		js.setViewportView(editor);				
	}
	void refrish_TP(){
		String Form=openTemp("egslv/temp/Tpform.tmp");
		TP= new JEditorPane("text/html",Form);						
		jt.setViewportView(TP);				
	}
	JButton[]button(String[]bf){
		JButton[]s=new JButton[bf.length];
		for(int i=0;i<bf.length;i++){
			s[i]=new JButton("",new Bimage().img(bf[i]));
			s[i].setActionCommand(bf[i]);	
			s[i].setVerticalTextPosition(AbstractButton.CENTER);//BOTTOM);
    		s[i].setHorizontalTextPosition(AbstractButton.CENTER);	
			s[i].addActionListener(new LvAction());
		}
		return s;
	}
	JPanel Panel_B(){
		JPanel pan =Panel();					
		JButton[]s=MButtons;//=new JButton[20];		
		pan.setLayout(new GridLayout(s.length,0));		
		for(int i=0;i<s.length;i++){
			pan.add(s[i]);
		}	
		pan.setBorder(new javax.swing.border.TitledBorder(null,"Mitarbeiter"));
		//pan.setBorder(new LineBorder(Color.blue,1,false));
		JPanel Bpan=Panel();	
		Bpan.add(pan,BorderLayout.PAGE_START);
		JPanel Tp=Panel();			
		refrish_TP();//TP=new JEditorPane("text/html","<html><body Bgcolor=#ffffcc><center>Mitarbeiter</body></html>");
		Tp.add(jt);
		Bpan.add(Tp);
		return Bpan;
	}
	JPanel Panel(){
		JPanel pan = new JPanel();	
		pan.setLayout(new BorderLayout());
		pan.setBorder(new javax.swing.border.TitledBorder(null,""));//.setBorder(new LineBorder(null,2,true));
		return pan;
	}
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	String[]MD(String Anteil,String datum){
 		String file="egslv/k"+K[0]+"/lz"+Anteil+"_"+M[0]+"_"+datum.substring(2,datum.length())+".lz";
 		String[]str=open(file);
 		if(str.length<=0){
 			new AR(K[0],M[0],datum.substring(2,datum.length()));
 			str=open(file);
 		}
 		return str;
 	}
 	void neu(){
 		new SD(klientCode); 		
 	}
 	String klient(){
 		String[]str=open("egslv/klient.dat"); 		
 		String file="egslv/k"+klientCode+"_ma.dat";
 		String[]mastr=open(file);
 		String test="";
 		if(str!=null && str.length>0){
 			for(int i=0;i<str.length;i++){
 				if(klientCode.equals(str[i]))test="ok"; 					
 			}
 			if(test!="ok"){
 				neu(); 				
 				return klientCode;
 			} 
 	    }else 	 	    	
 	    	str=open("egslv/klient.dat"); 	    	
 	    	return new com.options.MyOp().wahl(str,"Welche Klient?");
 	    
 	}
 	String Ma(){
 		String file="egslv/k"+klientCode+"_ma.dat";
 		String[]str=open(file);
 		if(str!=null && str.length>0){
 			return new com.options.MyOp().wahl(str,"Welche Mitarbeiter?");
 	    }else{
 	    	neu();
 	    	str=open(file);
 	    	MaCode=str[0];
 	    	addMa();
 	    	return str[0];
 	    }
 	    
 	}
 	void addMa(){
 		for(int m=1;m<13;m++){
 			String dat="";
 	    	String datum=new com.units.myDatum().ist_my(); 				
 			String j=datum.substring(2,datum.length());
 			if(m<10)dat="0"+m+j;else dat=m+j;
 			new LZ(klientCode,MaCode,dat);
 		}
 	}
	String[]stammDaten(String str){
	    SD sd=new SD(klientCode,MaCode);
 		if(str.equals("m"))return sd.MaSd;else
 		if(str.equals("k"))return sd.KlientSd;else return null;
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
 	String[]liste_j(){
 		String[]Datum=new String[12];
 		String j=new com.units.myDatum().jahr();
 		int ij=Int(j)-1;
 		if(ij<10)j="0"+ij;else j=""+ij;
 		for(int m=0;m<Datum.length;m++){ 			
 			int x=m+1;
 			String dat=j;
 			if(x<10)dat="0"+x+dat;else dat=x+dat;
 			Datum[m]=dat;
 		}
 		return Datum;
 	}
 	
 	
 	String[]liste1(){
 		String[]Datum=new String[12];
 		for(int m=0;m<Datum.length;m++){
 			String dat="";
 			String datum=new com.units.myDatum().ist(); 			
 			int x=m+1;
 			if(x<10)dat="01."+"0"+x+"."+datum.substring(6,datum.length());
 			else dat=dat="01."+x+"."+datum.substring(6,datum.length());
 			Datum[m]=dat;
 		}
 		return Datum;
 	}
 	String DA(String da,String datum){ 		
 		String str="";
 		String[]md=MD(da,datum);
 		for(int i=0;i<md.length;i++){
 			if(md[i].indexOf(datum)>-1)str=md[i];
 		}
 		return str;
 	}
 	public void setK(){
 		//if(klientCode.length()==0 || klientCode=="" ||klientCode==null ||
 		if(klientCode!=" Leider nicht Vorhanden" && klientCode.length()!=0 && klientCode!="" && klientCode!=null){
 			MaCode=Ma();	
 			K=stammDaten("k"); 			
 		}else klientCode=klient();		
 	}
 	public void setM(){ 		 		
 		if( MaCode!=" Leider nicht Vorhanden" && MaCode.length()!=0 && MaCode!="" && MaCode!=null)M=stammDaten("m");
 		else{
 			MaCode=Ma();
 			M=stammDaten("m");  			 			
 		}
 	}
 	void tpText(){ 		 		
 		String str="<html><body bgcolor=#ffffcc><h2>Klient:"+klientCode+"</h2><h3> Mitarbeiter</h3>";
 		String[]m=open("egslv/k"+klientCode+"_ma.dat"); 		
 		for(int i=0;i<m.length;i++){
 			str+="<br>"+m[i]; 		
 			String[]msd=open("egslv/k"+klientCode+"/sd/m"+m[i]+"_sd.dat"); 		
 			for(int n=2;n<4;n++)str+=" "+msd[n];
 		}
 		str+="</body></html>";
 		new com.units.save().file("egslv/temp/Tpform.tmp",str,false); 	
 	}
 	void test(){
 		setK();
 		setM();
 		f.setTitle("Lohnverrechnung: "+new com.units.myDatum().J()+" Klient: "+klientCode+" Mitarbeiter: "+MaCode);
 		if(Skale<0.97)Skale=(float)0.97; 	
 	}
 	String datum(){
 		test();
 		return new com.options.MyOp().wahl(liste(),"Welche Monat?");
 	}
 	String Jahr(){
 		test();
 		return new com.options.MyOp().wahl(liste_j(),"Vor Jahr!");
 	}
 	String adatum(){
 		return new com.units.myDatum().ist_my();
 	}
 	
 	void neuBer(){
 		String datum=datum();
 		for(int m=1;m<13;m++){
 			String dat="";
 			String j=datum.substring(2,datum.length());
 			if(m<10)dat="0"+m+j;else dat=m+j;
 			new LZ(klientCode,MaCode,dat);
 		}
 	}
 	void clear(String file){
 		new com.units.save().file(file,"",false); 		
 	}
 	void ChangeDate(int end){
 		end=end-1;
 		String datum=adatum();
 		String[]files={
 			"egslv/k"+klientCode+"/dga_"+MaCode+"_"+datum.substring(2,datum.length())+".lz",
 			"egslv/k"+klientCode+"/dna_"+MaCode+"_"+datum.substring(2,datum.length())+".lz",
 			"egslv/k"+klientCode+"/lzdna_"+MaCode+"_"+datum.substring(2,datum.length())+".lz",
 			"egslv/k"+klientCode+"/lzdga_"+MaCode+"_"+datum.substring(2,datum.length())+".lz",
 			"egslv/k"+klientCode+"/ls/dgg_"+MaCode+"_"+datum.substring(2,datum.length())+".dat"
 		};
 		String[]str=open(files[4]);
 		for(int fl=0;fl<files.length;fl++){
 			str=open(files[fl]);
 			clear(files[fl]);
 			for(int x=0;x<end;x++){
 				new com.units.save().file(files[fl],str[x],true);
 			}
 		}
 		for(int x=end;x<str.length;x++){
 			String mdatum=new com.options.ausTeilen().koma(str[x])[0];
 			new LZ(klientCode,MaCode,mdatum);
 		} 		
 	}
 	int Int(String str){
 		int l=0;
 		try{l=Integer.parseInt(str);}catch(Exception ex){l=0;}
 		return l;
 	}
	class LvAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String t = e.getActionCommand();   	   	 			
			if(t.equals("MA erfassen")){
				new SD(klientCode);
				neuBer();
			}
			if(t.equals("Neue Klient erfassen")){
				new SD();				
			}
			
			if(t.equals("Suchen")){
				test();
				new BP(klientCode,MaCode);				
			}
			if(t.equals("Bearbeiten")){
				test();
				String datum =new com.options.MyOp().wahl(liste1(),"Ab Wann soll der Lohn geandert werden?");					
				new BP(klientCode,MaCode,datum);
				String am=datum.substring(3,5); //monat
				//addMa();
				ChangeDate(Int(am));
			}			
			if(t.equals("Brutto Lohne")){		
				String datum=datum();
				new Form(DA("dga",datum),K,M,"dga");				
				//new LSum(klientCode,MaCode,datum);	
				refrish();
			}
			if(t.equals("Lohnverrechnung")){				
				String datum=datum();				
				new Form(DA("dna",datum),K,M,"dna","-");
				refrish();	tpText();refrish_TP();
				//	new Form(DA("dga",datum),K,M,"dga");				
				//new LSum(klientCode,MaCode,datum);
			}
			if(t.equals("Vor Jahr!")){				
				String datum=Jahr();				
				new Form(DA("dna",datum),K,M,"dna","-");
				refrish();
				//	new Form(DA("dga",datum),K,M,"dga");				
				//new LSum(klientCode,MaCode,datum);
			}
			if(t.equals("Drucke Formular")){
				drucken();
			}
			if(t.equals("Lohnkosten")){				
				String datum=datum();
				new LK(klientCode,MaCode,datum,1);				
				refrish();
			/*	int width=f.getWidth();
				if(width<1000)width+=200;
				f.setSize(width,700);				
				f.validate();	*/		
				Skale =(float)0.79;
				new LK(klientCode,MaCode,datum,2);	
				String Form=openTemp("egslv/temp/mform.tmp");
				new showMe(Form,datum,"Lohnkosten "+klientCode,Skale);				
				
				String[]form=open("egslv/k"+klientCode+"/ls/Erlagschein_"+datum+".dat");
					if (form.length<=0){new ES(klientCode,datum);form=open("egslv/k"+klientCode+"/ls/Erlagschein_"+datum+".dat");}
				Form="";
				Form+="<html><body><center><h1>Erlagschein Abgaben und Steuer</h1><h2>Klient : "+klientCode+" das Monat : "+datum+"</h2></center>";
				for(int i=0;i<form.length;i++)Form+="<h3>"+form[i]+"</h3>";
				Form+="</body></html>";
				
				new showMe(Form,datum,"Lohnkosten_000 "+klientCode,Skale);				
				
				new LK(klientCode,MaCode,datum,3);	
				Form=openTemp("egslv/temp/mform.tmp");
				refrish();
				//new showMe(Form,datum,"Lohnkosten "+klientCode,Skale);		
			}
			if(t.equals("Klient Wechseln")){
				klientCode=klient();
				f.setTitle("Lohnverrechnung "+new com.units.myDatum().J()+" Klient:"+klientCode);
			}
			if(t.equals("Klient Aufrollen")){
				new AR();
			}
			
		}
	}	

	void drucken(){			
		editor.setBounds(0,0,640,480);		
		com.printer.JComponentVista vista = new com.printer.JComponentVista(editor , new java.awt.print.PageFormat()); 
 		//vista.scaleToFit(true,new com.printer.papier().getSkale()); 
		vista.scaleToFit(true,Skale); 	
 		java.awt.print.PrinterJob pj = java.awt.print.PrinterJob.getPrinterJob();
		pj.setJobName("L_");
		pj.setPageable(vista);
		try {
			pj.print();
		} catch (java.awt.print.PrinterException e) {System.out.println(e);}	
	}
	String openTemp(String file){
 		return new com.search.sucheDate(file).md();
 	}
	public void show(){ 			
		JPanel pan = Panel();					
		pan.add(Panel_B(),BorderLayout.LINE_START);
		pan.add(js,BorderLayout.CENTER);				
		f.setContentPane(pan); 
		f.setLocation(100,50);
		f.pack();		
		f.setVisible(true);	
	
		f.setDefaultCloseOperation(2);				
	}	
	public static void main(String[]args){
		if(args.length>0)new LVM(args[0]);else new LVM("0001"); 	 	
	}
}
