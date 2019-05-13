// created on 25.01.2007 at 23:29
package ebm;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
public class select{
	public String[][]mdata;		
	
	ebm.RcTab tab;
	JPanel mpan;	
	public select(){};
	public select(Object[][]dat,String[]head){
		mpan=new JPanel(new java.awt.BorderLayout());	
		tab=new ebm.RcTab(dat,head);		
		mpan=tab.panel();
		System.out.println("\nvom Select: ");		
		test_buchen();
	}	
	void test_buchen(){
		int opan=oPanel();
		if(opan == 0){
			mdata=tab.getdata();
			zdata(mdata);		
		}else {
			mdata=null;
			System.out.println("Abgebrochen");
		}
	}
	
	void zdata(String[][]str) {
		//float betrag=0;
		if(str!=null && str.length>0){
		for (int i=0; i < str.length; i++) {
			for (int j=0; j < str[0].length; j++) {
				System.out.print(" "+str[i][j]);								
			}
			System.out.println();
			
		}
		System.out.println("....... ");
		}
	}
	int oPanel(){
		String[]taste={"<html><font size=+1 color=blue>Buchen","<html><font size=+1 color=red>Abbrechen"};
		return JOptionPane.showOptionDialog(null,mpan, "Daten Einlegen",
		                                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
		                                    null, taste, taste[0]);
	}
	public static void main(String[] args) {new select();}
}
