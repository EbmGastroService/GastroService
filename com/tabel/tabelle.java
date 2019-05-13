// created on 26.05.2003 at 22:20
//<@created by Mourad El bakry 2003, Vianna
//diese Class nimmt Array String  und gibt eine Tabelle zuruck.
package com.tabel;
import com.options.MyOp;
import com.options.ausTeilen;
import com.units.save;
//import com.display.*;
import com.search.sucheDate;
import javax.swing.JTable;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
//import java.awt.event.*;
public class tabelle extends JPanel{
	JTable mytab;			
	String[] inhalt;	
	public tabelle (String[] inha){	
		String[] sicherheit={"ihre;gesuchte daten;hat","0;ergebnisse;gegben"};
		if(inha.length>0)inhalt = inha;else inhalt=sicherheit;
		if ( stchek() > -1) {for(int i=0;i<inhalt.length;i++)inhalt[i]=inhalt[i].replace(',',';');}
		mytab=new JTable(inhalt.length, new ausTeilen().komma(""+inhalt[0],";").length-1);			
		mytab=getTab();
		mytab.setFont(new Font("Courier New",1,14));
		mytab.setForeground(Color.blue);
		mytab.setBackground(new Color(255,255,204));
		setLayout(new BorderLayout());
		JScrollPane jp=new JScrollPane(mytab);
		add(new JLabel("<html><b><font size=3><center>EbmGasto"+"</center>",JLabel.CENTER),BorderLayout.PAGE_START);
		add(jp,BorderLayout.CENTER);
		setVisible(true);
	}
	int stchek(){
		int pos=0;
		String str=inhalt[0].substring(0,7);
		return pos = str.indexOf(",");
	}
	JTable getTab(){		
		for(int i=0;i<inhalt.length;i++){
			String[] wort = new ausTeilen().komma(""+inhalt[i],";");
			for(int x=0; x < wort.length-1;x++){
				mytab.setValueAt(wort[x],i,x);
			}
		}				
		return mytab;				
	}
	public void start(){}
	public void init(){}
	
/*	public static void main(String[] args) {
		sucheDate su =new sucheDate("gastro/date/kliste.dat");		
	 	String[] str = su.myDaten();	 		
		Console.run(new tabelle(str),650,650);
	}*/
}
