// created on 25.08.2009 at 22:12
//EGS09
import java.io.*;
import javax.swing.JFrame;
import javax.swing.UIManager ;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class EGS09{
public EGS09(){}

private static void createAndShowGUI() {            
        JFrame frame =new zustellung(); 		
        frame.setIconImage(getFDImage()); 
    	try {       
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	} catch (Exception e) { }
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);        
        frame.pack();    	
        frame.setVisible(true);       	
    }
protected static Image createFDImage() {
 	//Create a 16x16 pixel image.
 	BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
 	Graphics g = bi.getGraphics();
 	g.setColor(Color.green);
 	g.fillRect(0, 0, 15, 15);
 	g.setColor(Color.red);
 	g.fillRect(4, 4, 10,10);
	g.setColor(Color.yellow);
	g.fillOval(5, 3, 7, 7); 	
 	g.dispose();
 	return bi;
 }
protected static Image getFDImage() {
        java.net.URL imgURL = EGS09.class.getResource("/image/ico1.gif");
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return createFDImage();
        }
    }

public static void main(String[] args) {
     try {
        String vers = System.getProperty("java.version");
        if (vers.compareTo("1.6") < 0) {
            System.out.println("!!!WARNING: Swing must be run with a " +
                               "1.6 or higher version VM!!!");
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });       
     } catch (Throwable t) {
            System.out.println("uncaught exception: " + t);
            t.printStackTrace();
        }
  }      
}
