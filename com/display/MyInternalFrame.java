package com.display;
import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import javax.swing.JApplet;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;

/* Used by InternalFrameDemo.java. */
public class MyInternalFrame  {
    static int openFrameCount = 0;
    static final int xOffset = 30, yOffset = 30;

    /*public MyInternalFrame() {
        super("Document #" + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
        //...Create the GUI and put it in the window...
        //...Then set the window size or call pack...
        //Set the window's location.
        setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
    }*/
	public String title(Object o) {
		String t= o.getClass().toString();
		//remove das wort class
		if (t.indexOf("class") != -1) t=t.substring(6);
	return t;
	}

	public JInternalFrame run(JFrame frame ,int width,int height) {	
		JInternalFrame f=new JInternalFrame(title(frame) + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
		f.add(frame);	
      	frame.setVisible(true);
      	f.setVisible(true);
	f.setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
	return f;

	}
     public JInternalFrame run(JApplet applet , int width , int height) {
		JInternalFrame f=new JInternalFrame(title(applet) + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
		f.add(applet);	
	      f.setSize(width, height);		
		applet.init();
		applet.start();      
		applet.setVisible(true);
		f.setVisible(true);

		f.setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		return f;

	}
	
	public JInternalFrame run(JPanel panel, int width, int height) {
     		JInternalFrame f=new JInternalFrame(title(panel) + (++openFrameCount), 
              true, //resizable
              true, //closable
              true, //maximizable
              true);//iconifiable
		f.add(panel);	
	      f.setSize(width, height);				
		panel.setVisible(true);
		f.setVisible(true);
		f.setLocation(xOffset*openFrameCount, yOffset*openFrameCount);
		return f;
	}

}
