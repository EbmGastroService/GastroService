// created on 01.11.2007 at 17:05
//LV Start
package egslver;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JEditorPane;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.UIManager ;
import javax.swing.KeyStroke;
import javax.swing.BoxLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.AbstractButton;
public class LVStart{
	String klient;
	JEditorPane editor;
	JScrollPane js;
	public LVStart(){
		show();
	}
	public LVStart(String klient){
		this.klient=klient;
		if(klient.length()==0||klient=="")
			klient=new com.options.MyOp().wahl(open("egslv/klient.dat"),"Welche Klient?");
		js=new JScrollPane();
		refrish();
		show();
	}
	void refrish(){
		String Form=openTemp("egslv/temp/mform.tmp");
		editor = new JEditorPane("text/html",Form);						
		js.setViewportView(editor);		
	}
	JPanel Panel(){
		JPanel pan = new JPanel();	
		pan.setLayout(new BorderLayout());
		pan.setBorder(new javax.swing.border.TitledBorder(null,""));//setBorder(new LineBorder(null,2,true));
		return pan;
	}
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	String openTemp(String file){
 		return new com.search.sucheDate(file).md();
 	}
	public void show(){ 			
		JPanel pan = Panel();			
		//pan.setPreferredSize(new Dimension(840,640));
		pan.add(new LVM(klient).Panel_B(),BorderLayout.LINE_START);
		pan.add(js,BorderLayout.CENTER);		
		JFrame f=new JFrame(klient+"Ebm GastroService 2006");
		f.setContentPane(pan); 
		f.pack();		
		f.setVisible(true);	
		f.setDefaultCloseOperation(2);				
	}	
	public static void main(String[]args){
		new LVStart("251401"); 	
	}
}
