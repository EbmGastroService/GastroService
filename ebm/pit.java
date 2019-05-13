// created on 16.01.2007 at 00:23
package ebm;
import com.printer.JComponentVista;
import java.awt.print.*;

public class pit{
	JComponentVista vista;	
	public pit(javax.swing.JComponent comp , String T){
	//	comp.setBounds(0, 0, 540,880);	
		vista = new JComponentVista(comp , new PageFormat()); 
 		vista.scaleToFit(true,new com.printer.papier().getSkale()); 
		//System.out.println(""+comp+" T:"+T);
 		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setJobName("Auftarg_"+T);
		pj.setPageable(vista);
		try {
			pj.print();
		} catch (PrinterException e) {System.out.println(e);}
	}
	public pit(javax.swing.JComponent comp){
	//	comp.setBounds(0, 0, 540,880);	
		vista = new JComponentVista(comp , new PageFormat()); 
 		vista.scaleToFitX(); 	
		System.out.println("im pit:"+comp);
 		PrinterJob pj = PrinterJob.getPrinterJob();
		pj.setJobName("Seite_");
		pj.setPageable(vista);
		try {
			pj.print();
		} catch (PrinterException e) {System.out.println(e);}
	}
	
	
}
