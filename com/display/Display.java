package com.display;
import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import javax.swing.JComponent;
import javax.swing.JApplet;
import javax.swing.JPanel;

public class Display extends JInternalFrame {
    static int openFrameCount = 0;
    static final int xOffset = 40, yOffset = 65;	

	public String title(Object o) {
		String t= o.getClass().toString();
		String[]mudul={"Erfassung","Ware","Kunden","Warenkorb","Kasse Buch"};
		//remove das wort class
		if (t.indexOf("class")!= -1) t=t.substring(6);		
		if (t.indexOf("ebm")!= -1) t=t.substring(4);			
		
		if(t.indexOf("Rcn")!= -1)t=mudul[0];
		if(t.indexOf("WD")!= -1)t=mudul[1];
		if(t.indexOf("KD")!= -1)t=mudul[2];
		if(t.indexOf("wk")!= -1)t=mudul[3];
		if(t.indexOf("KB")!= -1)t=mudul[4];

	return t+" | EbmGastroService ";
	}		
	public Display(JFrame frame ,int width,int height) {			
		super("#"+"("+ (++openFrameCount)+")", 
              true, //resizable
              true, //closable
              false, //maximizable
              false);//iconifiable
        setTitle(title(frame)); 		
		java.awt.Container con=frame.getContentPane();		
		setContentPane(con);	
		setSize(width, height);	
      	setVisible(true);java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		//int w=screenSize.width;
		int h=screenSize.height;
     	if(h<800)setLocation(xOffset, 5);  else setLocation(xOffset, yOffset);  
     	
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}
     public Display(JApplet applet , int width , int height) {
		super("#"+"("+ (++openFrameCount)+")", 
              true, //resizable
              true, //closable
              false, //maximizable
              false);//iconifiable
		//applet.setIconImage(getFDImage());
     	setTitle(title(applet)); 
		applet.init();
		applet.start();      
     	java.awt.Container con=applet.getContentPane();
		//applet.setVisible(true);     	
     //	applet.setFocusable(true);     	
       	setContentPane(con);     	
     	setSize(width, height);				
      	setVisible(true);
     	java.awt.Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize(); 
		//int w=screenSize.width;
		int h=screenSize.height;
     	if(h<800)setLocation(xOffset, 5);  else setLocation(xOffset, yOffset);  
     	setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}
	public Display(JComponent panel, int width, int height) {
     	super("#"+(++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
        setTitle(title(panel));     
        panel.setVisible(true);      
		setContentPane(panel);		
     	setSize(width, height);				
      	setVisible(true);
		setLocation(xOffset, yOffset);		
		setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);
	}
	
	public Display(JPanel panel, int width, int height) {
     	super("#"+(++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
    setTitle(title(panel)); 
    panel.setVisible(true);      
	setContentPane(panel);	
	setSize(width, height);
	setVisible(true);
	setLocation(xOffset, yOffset);	
	setDefaultCloseOperation(JInternalFrame.DISPOSE_ON_CLOSE);	
	}
	/*class IFL implements InternalFrameListener {
    public void internalFrameClosing(InternalFrameEvent e) {
        //displayMessage("Internal frame closing", e);
    }

    public void internalFrameClosed(InternalFrameEvent e) {
        //displayMessage("Internal frame closed", e);
    }

    public void internalFrameOpened(InternalFrameEvent e) {
        //displayMessage("Internal frame opened", e);
    }

    public void internalFrameIconified(InternalFrameEvent e) {
        //displayMessage("Internal frame iconified", e);
    }

    public void internalFrameDeiconified(InternalFrameEvent e) {
        //displayMessage("Internal frame deiconified", e);
    }

    public void internalFrameActivated(InternalFrameEvent e) {
        //displayMessage("Internal frame activated", e);    
    }

    public void internalFrameDeactivated(InternalFrameEvent e) {
        //displayMessage("Internal frame deactivated", e);    	
    }
	}*/
	
}
