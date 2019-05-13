// created on 10.11.2007 at 00:45
//showMe
package egslver;
import com.printer.JComponentVista;
import java.awt.print.*;
import java.awt.event.*;
public class showMe{
	String Form;
	JComponentVista vista;
	javax.swing.JPanel blat;
	float Skale;
	javax.swing.JButton druck=new javax.swing.JButton("Drucken");
	public showMe(String Form,String datum,String Anteil){
		this.Form=Form;
		show(Anteil,datum);
		Skale=1;
	}
	public showMe(String Form,String datum,String Anteil,float skale){
		this.Form=Form;
		show(Anteil,datum);
		Skale=skale;
	}
	void show(String Anteil,String datum){	
		javax.swing.JEditorPane editor = new javax.swing.JEditorPane("text/html",Form);		
		druck.addActionListener(new druckAction());
		javax.swing.JScrollPane js=new javax.swing.JScrollPane();	
		js.setViewportView(editor);
		blat=new javax.swing.JPanel();
		blat.setLayout(new java.awt.BorderLayout());		
		blat.add(js,java.awt.BorderLayout.PAGE_START);	
      	javax.swing.JFrame jf=new com.options.frame(Anteil+" Lohnabgaben "+datum,180);	
		jf.getContentPane().add(druck,java.awt.BorderLayout.PAGE_START);
		jf.getContentPane().add(blat,java.awt.BorderLayout.PAGE_END);		
		jf.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);		
		jf.pack();	
		int fpos=0;
		if(Anteil.indexOf("dga")>-1)fpos=600;else fpos=-50;
		jf.setLocation(fpos,10);
		jf.setVisible(true);      
		
	}	
	class druckAction implements ActionListener {
        public void actionPerformed (ActionEvent e ) {        	
        	String fr = e.getActionCommand();     
        	//System.out.println(fr+" Actioncommand und Button:"+druck.getText());
        	if(fr.equals(druck.getText())){
        		drucken();
        	}
        }
	}
	void drucken(){		
		javax.swing.JEditorPane editor = new javax.swing.JEditorPane("text/html",Form);	
		editor.setBounds(0, 0, 640,800);
		
		com.printer.JComponentVista vista = new com.printer.JComponentVista(editor , new java.awt.print.PageFormat()); 
 		//vista.scaleToFit(true,new com.printer.papier().getSkale()); 
		vista.scaleToFit(true,Skale); 	
 		java.awt.print.PrinterJob pj = java.awt.print.PrinterJob.getPrinterJob();
		pj.setJobName("Auftarg_");
		pj.setPageable(vista);
		try {
			pj.print();
		} catch (java.awt.print.PrinterException e) {System.out.println(e);}	
	}
}
