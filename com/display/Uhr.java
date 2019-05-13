// created on 14.07.2003 at 22:39
/*
 * @(#)Clock.java	1.9 01/12/03
 *
 * Copyright 2002 Sun Microsystems, Inc. All rights reserved.
 * SUN PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.display;
import java.util.*;
import java.awt.*;
import java.applet.*;
import java.text.*;
import javax.swing.*;
/**
 * Time!
 *
 * @author Rachel Gollub
 * @modified Daniel Peek replaced circle drawing calculation, few more changes
 */
public class Uhr extends JApplet implements Runnable {
    private volatile Thread timer;       // The thread that displays clock
    private int lastxs, lastys, lastxm,
                lastym, lastxh, lastyh;  // Dimensions used to draw hands 
    private SimpleDateFormat formatter;  // Formats the date displayed
    private String lastdate;             // String to hold date displayed
    private Font clockFaceFont;          // Font for number display on clock
    private Date currentDate;            // Used to get date to display
    private Color handColor;             // Color of main hands and dial
    private Color numberColor;           // Color of second hand and numbers
    private int X,Y;

    public void init() {
        int x,y;
        lastxs = lastys = lastxm = lastym = lastxh = lastyh = 0;
        formatter = new SimpleDateFormat ("EEE dd/MMM/yyyy hh:mm:ss", 
                                          Locale.getDefault());
        currentDate = new Date();
        lastdate = formatter.format(currentDate);
        clockFaceFont = new Font("Serif", Font.PLAIN, 16);
        handColor = Color.blue;
        numberColor = Color.red;//darkGray;
    	try {        	
            X=Integer.parseInt(getParameter("X"));        	
			Y=Integer.parseInt(getParameter("Y"));        	
        } catch (NullPointerException e) { }
        catch (NumberFormatException e) {
        }
        if(X==0)X=45;
    	if(Y==0)Y=40;
        clockFaceFont = new Font("Serif", Font.PLAIN, (X/3));

        try {
            setBackground(new Color(Integer.parseInt(getParameter("bgcolor"),
                                                     16)));
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }
        try {
            handColor = new Color(Integer.parseInt(getParameter("fgcolor1"),
                                                   16));
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }
        try {
            numberColor = new Color(Integer.parseInt(getParameter("fgcolor2"),
                                                     16));
        } catch (NullPointerException e) {
        } catch (NumberFormatException e) {
        }
        resize(300,300);              // Set clock window size
    }

    // Paint is the main part of the program
    public void paint(Graphics g) {
        int xh, yh, xm, ym, xs, ys;
        int s = 0, m = 10, h = 10;
        int xcenter = 10+(Y*2), ycenter = X+25;//80,55
        String today;

        currentDate = new Date();
        
        formatter.applyPattern("s");
        try {
            s = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            s = 0;
        }
        formatter.applyPattern("m");
        try {
            m = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            m = 10;
        }    
        formatter.applyPattern("h");
        try {
            h = Integer.parseInt(formatter.format(currentDate));
        } catch (NumberFormatException n) {
            h = 10;
        }
    
        // Set position of the ends of the hands
        xs = (int) (Math.cos(s * Math.PI / 30 - Math.PI / 2) * X + xcenter);
        ys = (int) (Math.sin(s * Math.PI / 30 - Math.PI / 2) * X + ycenter);
        xm = (int) (Math.cos(m * Math.PI / 30 - Math.PI / 2) * Y + xcenter);
        ym = (int) (Math.sin(m * Math.PI / 30 - Math.PI / 2) * Y + ycenter);
        xh = (int) (Math.cos((h*30 + m / 2) * Math.PI / 180 - Math.PI / 2) * 30
                   + xcenter);
        yh = (int) (Math.sin((h*30 + m / 2) * Math.PI / 180 - Math.PI / 2) * 30
                   + ycenter);
    
        // Draw the circle and numbers
        g.setFont(clockFaceFont);
        g.setColor(handColor);
        g.drawArc(xcenter-(X+5), ycenter-(X+5), 2*(X+5), 2*(X+5), 0, 360);
        g.setColor(numberColor);    	
        g.drawString("9", xcenter-X, ycenter+3); 
        g.drawString("3", xcenter+Y, ycenter+3);
        g.drawString("12", xcenter-(X-Y), ycenter-(Y-3));
        g.drawString("6", xcenter-3, ycenter+X);
    	
    	g.setColor(handColor);
    	g.drawString("Uhr Mourad El Bakry",20,160);
	   
        // Get the date to print at the bottom
        formatter.applyPattern("EEE dd/MMM/yyyy HH:mm:ss");
        today = formatter.format(currentDate);

        // Erase if necessary
        g.setColor(getBackground());
        if (xs != lastxs || ys != lastys) {
            g.drawLine(xcenter, ycenter, lastxs, lastys);
            g.drawString(lastdate, 10+(X-Y), X*3);
        }
        if (xm != lastxm || ym != lastym) {
            g.drawLine(xcenter, ycenter-1, lastxm, lastym);
            g.drawLine(xcenter-1, ycenter, lastxm, lastym); 
        }
        if (xh != lastxh || yh != lastyh) {
            g.drawLine(xcenter, ycenter-1, lastxh, lastyh);
            g.drawLine(xcenter-1, ycenter, lastxh, lastyh); 
        }

        // Draw date and hands
        g.setColor(numberColor);
        g.drawString(today, 10+(X-Y), X*3);    
        g.drawLine(xcenter, ycenter, xs, ys);
        g.setColor(handColor);
        g.drawLine(xcenter, ycenter-1, xm, ym);
        g.drawLine(xcenter-1, ycenter, xm, ym);
        g.drawLine(xcenter, ycenter-1, xh, yh);
        g.drawLine(xcenter-1, ycenter, xh, yh);
        lastxs = xs; lastys = ys;
        lastxm = xm; lastym = ym;
        lastxh = xh; lastyh = yh;
        lastdate = today;
        currentDate = null;
    }

    public void start() {
        timer = new Thread(this);
        timer.start();
    }

    public void stop() {
        timer = null;
    }

    public void run() {
        Thread me = Thread.currentThread();
        while (timer == me) {
            try {
                Thread.currentThread().sleep(100);
            } catch (InterruptedException e) {
            }
            repaint();
        }
    }

    public void update(Graphics g) {
        paint(g);
    }

    public String getAppletInfo() {
        return "Title: A Clock \n"
            + "Author: Rachel Gollub, 1995 \n"
            + "An analog clock.";
    }
  
    public String[][] getParameterInfo() {
        String[][] info = {
            {"bgcolor", "hexadecimal RGB number", 
             "The background color. Default is the color of your browser."},
            {"fgcolor1", "hexadecimal RGB number", 
             "The color of the hands and dial. Default is blue."},
            {"fgcolor2", "hexadecimal RGB number", 
             "The color of the second hand and numbers. Default is dark gray."},
             {"X", "45", 
             "The color of the second hand and numbers. Default is dark gray."},
             {"Y", "40", 
             "The color of the second hand and numbers. Default is dark gray."}
        };
        return info;
    }
    public static void main(String[] args) {
		//new Clock();
		Console.run(new Uhr(),200,200);
		
	}
}
