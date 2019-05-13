// created on 13.07.2003 at 22:42
package com.display;
import  com.units.myDatum;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
public class clock1 extends JPanel implements Runnable {
	private Thread timer;       // The thread that displays clock
	private String lastdate;             // String to hold date displayed
	private JLabel zeitf;
	private JLabel datum;   
	public clock1(){
    	lastdate=zeit();
    	zeitf=new JLabel(lastdate,JLabel.CENTER);
    	datum=new JLabel(""+new myDatum().d(),JLabel.CENTER);
    	setLayout(new GridLayout(0,2));
     	add(datum);    
     	add(zeitf);     	
     	setVisible(true);
    }
    String zeit(){
    	String today=new myDatum().time();
    	return lastdate = today;
    }    
    public void init(){}
    public void start() {
        timer = new Thread(this);
        timer.start();
    }
    public void destroy(){//stop() {
        timer = null;
    }
    public void run() {
        Thread me = Thread.currentThread();
        while (timer == me) {
            try {
                Thread.currentThread().sleep(1000);
            } catch (InterruptedException e) {
            }    
            zeitf.setText("	"+zeit());
        }
    }    
}
