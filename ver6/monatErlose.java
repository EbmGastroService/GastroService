// created on 16.10.2006 at 23:28
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JFrame;
import java.awt.GridLayout;
import java.awt.*;
import java.applet.*;
import java.awt.event.*;
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
	JPanel jp;
	JFrame jf;
	JTable tabe;
	public monatErlose(){
	int[]ml={0,31,29,31,30,31,30,31,31,30,31,30,31};
	wahl=0;
	jp=new JPanel();
	jp.setLayout(new GridLayout(3,3));
	for(int i=0;i<bu.length;i++){		
		bu[i].addActionListener(new MAction());
		jp.add(bu[i]);		
	}	
	System.out.println(wahl);
	jf=new JFrame("MonatsWahl");
	jf.getContentPane().add(jp);
	//jf.getContentPane().add(tabe);
	jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);  
	jf.pack();
	jf.setVisible(true);
	}
	void zeige(int i){
		String it="";
		if(i<10)it="0"+i;
		Vector v=new Vector();
		Vector dn=new Vector();
		dn.addElement("Datum");dn.addElement("Anzahl-Bestllung");dn.addElement("T-Erlose");
		dn.addElement("MonatErlose");
		
		String[]te=new com.search.sucheDate("gastro/D2006/M"+it+".dat").myDaten();
		//new KB().AlleTage(""+i);//22070
		for(int x=0;x<te.length;x++)v.addElement(te[x]);
		tabe =new JTable(v,dn);
		JFrame tf= new JFrame("MonatsUmsatz");
		
		tf.getContentPane().add(tabe);
		tf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		tf.pack();
		tf.setVisible(true);
	}
	class MAction implements ActionListener {
        public void actionPerformed ( ActionEvent e ) {        	
        	String fr = e.getActionCommand();        	
        	for(int i=0;i<bu.length;i++){
        		if(fr.equals(bu[i].getText()))wahl=i;        	
        	}
        	System.out.println("Ihre Wahl="+wahl);
        	zeige(wahl);
        }
	}
	
	 public static void main(String[] args) {
	 	new monatErlose();
	 }
}
