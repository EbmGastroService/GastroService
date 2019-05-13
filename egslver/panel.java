
/*
 * panel.java
 *
 * created on 13.10.2008 at 18:31
 */

/**
 *
 * author  Administrator
 */
import javax.swing.JLabel;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class panel extends java.awt.Panel {    
    public panel() {
        initComponents();
    }
    private void initComponents() {
        setLayout(new java.awt.BorderLayout());
    	add(new javax.swing.JLabel(ImageIcon(1)));
    }
    protected ImageIcon getImg(int m) {
		String str="";
		if(m==1)str="hor2.gif";else str="ebmeingang.gif";
        java.net.URL imgURL =panel.class.getResource("/image/"+str);
        if (imgURL != null) {
            return new ImageIcon(imgURL);//.getImage();
        } else {
            return null;
        }
    }
    void Schow(){
		javax.swing.JFrame jf=new javax.swing.JFrame("GEWINNSPIEL");
		jf.setContentPane(new panel());
		jf.pack();
		java.awt.Dimension s = java.awt.Toolkit.getDefaultToolkit().getScreenSize();		
		jf.setLocation(s.width/2-(jf.getWidth()/2),s.height/3-(jf.getHeight()/2));
		jf.setVisible(true);
	}
}
