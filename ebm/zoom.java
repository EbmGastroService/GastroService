// created on 21.10.2006 at 01:52
//checkbox > Printer papierformat
package ebm;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JPanel;
//import javax.swing.GridLayout;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
public class zoom extends JPanel{
	String[]wert;
	JComboBox jcb;
	JTextField jtf;
	//JPanel panel;
	float[]dwert;
	public zoom(String text){
		wert=setWert();
		jcb = new JComboBox(wert);		
		jcb.addActionListener(new docAction());
		jtf=new JTextField(text);
		//panel=new JPanel();
		//panel.
		setLayout(new GridLayout(0,2));
		//panel.
		add(jtf);
		//panel.
		add(jcb);
	}
	String[]setWert(){
		String[]awert=new String[100];
		dwert=new float[awert.length];
		for(int i=0;i<awert.length;i++){
			dwert[i]=(float)(0.10+(float)i/100);
			float fl=dwert[i]*100;
			awert[i]=""+(int)fl+"%";
		}
		return awert;
	}
	class docAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int wahl=jcb.getSelectedIndex();
			String str=""+dwert[wahl];
			jtf.setText(str);
			save(str);
		}
	}
	void save(String str){
		new com.units.save().file("gastro/source/zoom.cfg",str,false);		
	}
	public static void main(String[] args) {
		JFrame jf =new com.options.frame("Wahl");
		jf.setContentPane(new zoom("0.7"));
		jf.pack();
		jf.setVisible(true);
	}
	
}
