// created on 07.11.2006 at 00:43
package ebm;
import java.awt.Color;
import javax.swing.JFrame;

public class BW extends javax.swing.JFrame{		
	String txt;	
	public BW(int i,String txt){		
		if(i<2)i=2;		
		i=i*500;
		if(txt.length()<=0)this.txt="Daten werden gesammelt!  Bitte Warten!   ";else this.txt="Bitte warten!!";
		setTitle("Daten Vorbereitung Bitte warten!");
		javax.swing.Timer timer=new javax.swing.Timer(i, new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dispose();
            }
        });
    	timer.start();		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	  	
		setSize(350,80);
		setLocation(600,100);
		setVisible(true);   		
	}	
		public void paint(java.awt.Graphics g) {	
			g.setColor(Color.red);
			g.fillRect(0, 0, 350, 80);
			g.setColor(java.awt.Color.white);//,	java.awt.Color.blue);
			g.setFont(new java.awt.Font("",1,15));
			g.drawString(txt,20, 45);		
	}
	//public static void main(String[] args) {new BW(3,"");}
}
