// created on 01.11.2007 at 14:17
package egslver;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
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
import java.awt.Dimension;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics2D.*;
//import java.awt.RoundRectangle2D.Double;
import java.awt.GradientPaint;
//import java.awt.FontMetrics;
//import java.awt.geom.*;
import java.awt.Font;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.AbstractButton;
//KlientPanel
public class LV1{	
	JButton[]KButtons,MButtons;
	public LV1(){		
		KButtons=button(new lvstruck().klbedd());//activiere die Keys				
		show();
	}
	JButton[]button(String[]bf){		
		JButton[]s=new JButton[bf.length];
		for(int i=0;i<bf.length;i++){
			s[i]=new JButton("",new ImageIcon(image(bf[i],140,20)));
			s[i].setActionCommand(bf[i]);	
			s[i].setVerticalTextPosition(AbstractButton.CENTER);//BOTTOM);
    		s[i].setHorizontalTextPosition(AbstractButton.CENTER);	
			s[i].addActionListener(new LvAction());
		}
		return s;
	}
	JPanel Panel_B(){
		JPanel pan =Panel();					
		JButton[]s=KButtons;//=new JButton[20];
		if(x>1)s=MButtons;		
		pan.setLayout(new GridLayout(s.length,0));		
		for(int i=0;i<s.length;i++){
			pan.add(s[i]);
		}		
		pan.setBorder(new LineBorder(Color.blue,1,false));
		JPanel Bpan=Panel();	
		Bpan.add(pan,BorderLayout.PAGE_START);
		return Bpan;
	}
	JPanel Panel(){
		JPanel pan = new JPanel();	
		pan.setLayout(new BorderLayout());
		pan.setBorder(new LineBorder(null,2,true));
		return pan;
	}
	public void show(){ 	
		JPanel pan = Panel();			
		pan.setPreferredSize(new Dimension(480,640));
		pan.add(Panel_B(1),BorderLayout.LINE_START);
		pan.add(Panel(),BorderLayout.CENTER);
		pan.add(Panel_B(2),BorderLayout.LINE_END);
		JFrame f=new JFrame("Ebm GastroService 2006");
		f.setContentPane(pan); 
		f.pack();		
		f.setVisible(true);	
		f.setDefaultCloseOperation(2);				
	}	
	class LvAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String t = e.getActionCommand();   	   	 
			if(t.equals("Beenden")||t.equals("exit")){
				
			}
		}
	}
	protected Image image(String str,int x,int y) {
    	BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
    	Graphics g = bi.getGraphics();
        Graphics2D g2d = (Graphics2D) g.create();
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        BasicStroke stroke = new BasicStroke(2.0f);
    	BasicStroke wideStroke = new BasicStroke(18.0f);
        GradientPaint gp1 = new GradientPaint(x/2,5,Color.white,x-(x/5), y,Color.gray);    	
        g2d.setPaint(gp1);
        g2d.fill(new RoundRectangle2D.Double(0,0, x,y, 5, 1));    	    	    	
    	g2d.setFont(new java.awt.Font("Times New Roman",1,14));  
		g2d.setPaint(Color.black);
    	g2d.setStroke(wideStroke);
    	g2d.drawString(str,5, 15);	        
        g2d.dispose();
    	return bi;
    }
	public static void main(String[]args){
		new LV1(); 	
	}
}
