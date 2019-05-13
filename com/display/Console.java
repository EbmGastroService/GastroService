//Display Framework
package com.display;
import java.awt.*;

import javax.swing.*;
import java.awt.event.*;

public class Console {
	
	// Titelstring vom Classname
	public static String title(Object o) {
		String t= o.getClass().toString();
		//remove das wort class
		if (t.indexOf("class") != -1) t=t.substring(6);
	return t;
	}
	public static void stop(JFrame frame) {
		frame.dispose();
	}
	public static void setupClosing(JFrame frame) {
                //Jdk 1.2 lsung ananyme innera class
               frame.setDefaultCloseOperation(2);//0=do_nathing,1 exit_on_close,2 dispose_on_close
		/*
      frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
                        //Runtime.getRuntime().exit(0);
                                //System.exit(3);
				
			}
		});
                //dispose();
                //in JDK 1.3 wre  frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
                //frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		*/
	}

	public static void run(JFrame frame ,int width,int height) {
		 //stop(frame);
		setupClosing(frame);
		frame.setSize(width,height);
		 Center(frame);
		frame.setVisible(true);
	}
	// Center.
	public static void Center(JFrame f){
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    Dimension frameSize = f.getSize();
    int x = (screenSize.width - frameSize.width) / 2;
    int y = (screenSize.height - frameSize.height) / 2;
    f.setLocation(x, y);
	}

	
        public static void run(JApplet applet , int width , int height) {
		JFrame frame=new JFrame(title(applet));
		setupClosing(frame);    
		frame.getContentPane().add(applet);	
        frame.setSize(width, height);		
		applet.init();
		applet.start();
        Center(frame);
		frame.setVisible(true);
	}
	
	public static void run(JPanel panel, int width, int height) {
        JFrame frame=new JFrame(title(panel));
		setupClosing(frame);
		frame.getContentPane().add(panel);	
		frame.setSize(width , height);
		Center(frame);
		frame.setVisible(true);
	}
}
