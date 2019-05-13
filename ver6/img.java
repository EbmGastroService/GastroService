// created on 26.09.2007 at 23:34
package ver6;
import javax.swing.JLabel;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Graphics2D.*;
//import java.awt.RoundRectangle2D.Double;
import java.awt.GradientPaint;
import java.awt.FontMetrics;
import java.awt.geom.*;
import java.awt.Font;
import java.awt.RenderingHints;
//import javax.swing.*;


import javax.swing.AbstractButton;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import java.util.*;
public class img{
	FontMetrics fontMetrics;
	Font font;
	public img(){	}
	
	public JLabel label(String[]str,String logo,int w,int h){
		if(str!=null){}else str=logoStr();
		if(ico(logo)!=null)return new JLabel(ico(logo),JLabel.CENTER);
		else return new JLabel("",new ImageIcon(image(str,w,h)),2);//300,200
	}
	public JLabel label(String[]str,String logo){
		java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		int w=screenSize.width/5;
		int h=screenSize.height/5;
		if(str!=null){}else str=logoStr();
		if(ico(logo)!=null)return new JLabel(ico(logo),JLabel.CENTER);
		else return new JLabel("",new ImageIcon(image(str,w,h)),2);//300,200
	}
	String[]logoStr(){
		String[] data=new ebm.firma().data();
		return new String[]{"Ebm Gastro Service "," 2000 - 2007","Hertzlich Willkommen",data[0],data[3]};
	}
/*	
 * protected static Image image(String[] str,int w,int h) {
    	//Create a 16x16 pixel image.
    	BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    	//Draw into it.
    	Graphics g = bi.getGraphics();
    	g.setColor(new ebm.myColor("hg").getColor());    	
    	g.fillRect(0, 0, w, h);
    	boolean bo=true;		
		if(str[0].indexOf("Ebm")>-1)bo=false;
		g.fill3DRect(1, 1, w-1, h-1, bo) ;
		g.setColor(java.awt.Color.red);
    	g.fillOval(2, 2, w-2, h-2);		

    	g.setColor(java.awt.Color.white);
    	g.fillOval(5, 5, w-15, h-15);
    	g.setColor(java.awt.Color.green);
    	g.drawOval(6, 6, w-16, h-16);    	
		g.setColor(new ebm.myColor("hg").getColor());    	
    	g.fillOval(w/6, h/6, w-(w/3), h-(h/3));   	    	
		//g.setColor(java.awt.Color.blue);
	//	g.fillRoundRect(w/10, h/10, w-(w/10), h-(h/10), w/10, h/10) ;
		g.setColor(java.awt.Color.yellow);
      //g.fillArc(3, 3, w-3, h-3,h/5, h/10) ;
    	g.fillArc(w/20, w/20, w-(w/10), h-(h/10),w/10,135) ; 
		g.setColor(java.awt.Color.green);
		g.drawArc(w/20, w/20, w-(w/10), h-(h/10),w/10,135) ; 
    	if(str!=null){
    		
    		//g.setFont(new java.awt.Font("",1,15));
    		String[]tit=new String[0];    		
    		int fnt=3+(w/20);
    		
    		g.setColor(new ebm.myColor("vg1").getColor());
    		int strw=(w-(w/4+(str[0].length()*fnt))/2);
    	
    		if(w>300)fnt=w/20;//str[0].length()*2;
    		System.out.println(""+fnt);
    		if(strw<0 || strw>w/3){    			
    		//	System.out.println(""+strw);
    			strw=w/5;
    		}
    		if(str[0].length()>25){			
    			tit=tok(str[0]);
    			String titrest="";
    			g.setFont(new java.awt.Font("Times New Roman",1,fnt));
    			g.drawString(tit[0]+" "+tit[1],strw,fnt*3);
    			strw+=(tit[0].length()+tit[1].length())+(fnt*3);
    		
    			for(int i=2;i<tit.length;i++)
    				titrest+=" "+tit[i];
    			g.drawString(titrest,strw,fnt*4);
    		}else {
    			g.setFont(new java.awt.Font("Times New Roman",1,fnt));
    			g.drawString(str[0],strw,fnt*3);
    		}
    		//System.out.println(""+strw);
    		g.setFont(new java.awt.Font("Times New Roman",0,fnt-(fnt/3)));
    		g.drawString(str[1],w-(w/5+(str[1].length()*(fnt-(fnt/3))))/2,fnt*5);
    		if(str.length >4){}else g.setFont(new java.awt.Font("Times New Roman",1,fnt-(fnt/4)));
    		for(int i=2;i<str.length;i++){
    			int x=strw;//str[i].length()*i;
    			if(str[i].length()<25)x=w/2;
    			g.drawString(str[i],x, (fnt*(i+4)));
    		//g.drawString(str[3],w-(w/2+(str[3].length()*(fnt+6)))/2, fnt*9);
    		}
    		//g.drawString(str[2],w-(w/2+(str[2].length()*fnt))/2, (fnt*7));
    		//g.drawString(str[3],w-(w/2+(str[3].length()*(fnt+6)))/2, fnt*9);
    	}
    	//Clean up.
    	g.dispose();
    	return bi;
    }*/
    protected ImageIcon ico(String u) {
  		String file="/image/"+u;
		java.net.URL imgURL = Rb.class.getResource(file);
  	//	System.out.println(imgURL);
        if (imgURL != null) {
            return new ImageIcon(imgURL);//.getImage();
        } else {
            return null;
        }
    }
    protected Image image(String str,int x,int y) {
    	BufferedImage bi = new BufferedImage(x, y, BufferedImage.TYPE_INT_RGB);
    	Graphics g = bi.getGraphics();
        Graphics2D g2d = (Graphics2D) g.create();
    	g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BasicStroke stroke = new BasicStroke(2.0f);
    	BasicStroke wideStroke = new BasicStroke(18.0f);
        //g2d.setColor(Color.WHITE);
        //g2d.fillRect(x +1 ,y + 1,x -2 ,y -2);
        GradientPaint gp1 = new GradientPaint(x,y,Color.red,0, 0,new ebm.myColor("hg").getColor());
    	GradientPaint gp2 = new GradientPaint(0,0,Color.black,x/2, y/2,new ebm.myColor("vg2").getColor());
        g2d.setPaint(gp1);
        g2d.fill(new RoundRectangle2D.Double(1,1, x-1,y-1, 10, 10));
    	g2d.setPaint(gp2);
    	g2d.draw(new Line2D.Double(1, 1, x-1, y-1));
    //	g2d.fill(new Ellipse2D.Double(10, 10, x/2, y/2));
    
    	g2d.setStroke(wideStroke);
    	g2d.fill(new Ellipse2D.Double(x/10, y/10, x/5, y/5));
    	GradientPaint redtowhite2 = new GradientPaint(0,0,Color.yellow,x, y,Color.black);
    	g2d.setPaint(redtowhite2);
    	int fnt=3+(x/20);
    	g2d.setFont(new java.awt.Font("Times New Roman",1,fnt));    	//g2d.setFont(font);		 	//g2d.setStroke(wideStroke);
    	g2d.drawString(str, x/5, y/2);	
    	fontMetrics = genFont(g2d,str+" "+str+" "+str,x);
    	g2d.setPaint(gp2);
    	g2d.setStroke(stroke);
    	g2d.drawString(str+" "+str+" "+str+str+" "+str+" "+str,10, y-30);	
        
        //g2d.drawLine(x +10, y + 10, x + width -10, y + height -10);
        //g2d.drawLine(x +10, y + height -10, x + width -10, y + 10);        
        g2d.dispose();
    	return bi;
    }
    protected Image image(String[]str,int w,int h) {
    	BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    	Graphics g1 = bi.getGraphics();
        Graphics2D g = (Graphics2D) g1.create();
    	g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        BasicStroke stroke = new BasicStroke(2.0f);
    	BasicStroke wideStroke = new BasicStroke(18.0f);
        GradientPaint gp1 = new GradientPaint(0,0,Color.gray,w, h,Color.red);
    	GradientPaint gp2 = new GradientPaint(0,0,new ebm.myColor("vg2").getColor(),w/2, h/2,new ebm.myColor("hg").getColor());
    	GradientPaint gp3 = new GradientPaint(0,0,new ebm.myColor("vg1").getColor(),w/2, h/2,Color.yellow);
        g.setColor(new ebm.myColor("hg").getColor());
    	//g.fill(new RoundRectangle2D.Double(0,0, w,h, 10, 10));
    	g.fillRect(0, 0, w, h);
    /*	boolean bo=true;		
    	if(str[0].indexOf("Ebm")>-1){
    		bo=false;
    		g.fill3DRect(1, 1, w-1, h-1, bo) ;
    	}
		//g.setColor(java.awt.Color.red);*/
    	g.setPaint(gp1);
    	g.fillOval(2, 2, w-2, h-2);		

    	g.setColor(java.awt.Color.white);
    	g.fillOval(5, 5, w-15, h-15);
    	g.setColor(new ebm.myColor("vg1").getColor());
    	//g.setColor(java.awt.Color.green);
    	g.drawOval(5, 5, w-15, h-15);    	
		//g.setColor(new ebm.myColor("hg").getColor());   
    	g.setPaint(gp2);
    	g.fillOval(w/6, h/6, w-(w/3), h-(h/3));   	    	
		g.setPaint(gp3);
    	g.fillArc(w/20, w/20, w-(w/10), h-(h/10),w/10,135) ; 
		g.setColor(new ebm.myColor("vg1").getColor());
		g.drawArc(w/20, w/20, w-(w/10), h-(h/10),w/10,135) ; 
    	if(str!=null){    		
    		String[]tit=new String[0];    		
    		int fnt=3+(w/20);    		
    		g.setColor(new ebm.myColor("vg1").getColor());
    		int strw=(w-(w/4+(str[0].length()*fnt))/2);
    	
    		if(w>300)fnt=w/20;//str[0].length()*2;
    		System.out.println("vorher:"+fnt+","+strw);
    		if(strw<0 || strw>w/4 || strw<w/4){    			
    		//	System.out.println(""+strw);
    			strw=w/4;
    		}
    		System.out.println("nachher:"+fnt+","+strw);
    		if(str[0].length()>25){			
    			tit=tok(str[0]);
    			String titrest="";
    			g.setFont(new java.awt.Font("Times New Roman",1,fnt));
    			g.setStroke(wideStroke);
    			g.drawString(tit[0]+" "+tit[1],strw,fnt*3);
    			strw+=(tit[0].length()+tit[1].length())+(fnt*3);
    		
    			for(int i=2;i<tit.length;i++)
    				titrest+=" "+tit[i];
    			g.drawString(titrest,strw,fnt*4);
    		}else {
    			g.setFont(new java.awt.Font("Times New Roman",1,fnt));
    			g.drawString(str[0],strw,fnt*3);
    		}
    		//System.out.println(""+strw);
    		g.setFont(new java.awt.Font("Times New Roman",0,fnt-(fnt/3)));
    		g.drawString(str[1],w-(w/5+(str[1].length()*(fnt-(fnt/3))))/2,fnt*5);
    		if(str.length >4){}else g.setFont(new java.awt.Font("Times New Roman",1,fnt-(fnt/4)));
    		for(int i=2;i<str.length;i++){
    			int x=strw;//str[i].length()*i;
    			if(str[i].length()<25)x=w/2;
    			g.drawString(str[i],x, (fnt*(i+4)));
    		}
    	}    	
    	g.dispose();
    	return bi;
    }
    FontMetrics genFont(Graphics2D g2,String myString,int xSpace) {
        boolean fontFits = false;
        Font font = g2.getFont();
        FontMetrics fontMetrics = g2.getFontMetrics();
        int size = font.getSize();
        String name = font.getName();
        int style = font.getStyle();
        while ( !fontFits ) {
            if ( (fontMetrics.getHeight() <= 25)//15
                 && (fontMetrics.stringWidth(myString) <= xSpace) ) {
                fontFits = true;
            }
            else {
                if ( size <= 12 ) {//6
                    fontFits = true;
                }
                else {
                    g2.setFont(font = new Font(name,style,--size));
                    fontMetrics = g2.getFontMetrics();
                }
            }
        }

        return fontMetrics;
    }

    public static String[] tok(String input) {
    	List<String> v = new ArrayList<String>();
    	StringTokenizer t = new StringTokenizer(input," ");
    	String cmd[];
    	while (t.hasMoreTokens())
    		v.add(t.nextToken());
    	cmd = new String[v.size()];
    	for (int i = 0; i < cmd.length; i++)
    		cmd[i] = (String) v.get(i);
    	
	return cmd;
    }

}
