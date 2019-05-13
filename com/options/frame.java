
/*
 * frame.java
 *
 * created on 27.07.2006 at 14:54
 */
package com.options;
import java.awt.*;
import javax.swing.*;
public class frame extends javax.swing.JFrame {

    /** Creates new form frame  mit closezeit*/
    public frame(String str,int zeit) {
    	zeit=zeit*1000;
    	super.setTitle(str);    	
        initComponents();
    	javax.swing.Timer timer=new javax.swing.Timer(zeit, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
    	timer.start();
    }
    public frame(String str) {    	
    	super.setTitle(str);    	
        initComponents();    	
    }
 protected static Image getFDImage() {
        java.net.URL imgURL = frame.class.getResource("/image/ico1.gif");
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return null;
        }
    }
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    private void initComponents() {//GEN-BEGIN:initComponents
    	setIconImage(getFDImage());
        addWindowListener(new java.awt.event.WindowAdapter() {
                              public void windowClosing(java.awt.event.WindowEvent evt) {
                                  exitForm(evt);
                              }
                          }
                         );
        pack();

    }//GEN-END:initComponents

    /** Exit the Application */
    private void exitForm(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_exitForm
        //Runtime.getRuntime().halt(1);
    	setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE );
    }//GEN-LAST:event_exitForm

    /**
    * param args the command line arguments
    *
    public static void main(String args[]) {
        new frame().show();
    }*/


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

}
