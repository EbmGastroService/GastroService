// created on 01.11.2007 at 16:40
//Butons Images
package egslver;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics2D.*;
import java.awt.GradientPaint;
import java.awt.Font;
import java.awt.geom.*;
import java.awt.RenderingHints;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;

public class Bimage{
	public Bimage(){}
	public ImageIcon img(String str,int x,int y){
		if(x<16)x=16;if(y<16)y=16;
		return new ImageIcon(image(str,x,y));		
	}
	public ImageIcon img(String str){
		int x=140;int y=20;
		return new ImageIcon(image(str,x,y));		
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
}
