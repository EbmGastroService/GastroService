// created on 14.02.2007 at 20:01
package ebm;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.BorderLayout;
public class logo extends JPanel{
	public logo(){		
		setLayout(new BorderLayout());
		add(new JLabel("<html><font color=white size=+2><b>EGS 2010</b>",JLabel.CENTER),BorderLayout.CENTER);
		setOpaque(true); 
		setBackground(new java.awt.Color(100,154,200));				
	}
}
