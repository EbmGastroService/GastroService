// created on 30.11.2006 at 21:26
package ver6;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.image.BufferedImage;

public class info{
	int I=60;
	public info(int i){
		I=i*60;
		zeigeMe();
	}	
	public info(){		
		zeige();
	}
	void zeige(){
		com.options.frame f=new com.options.frame("Info|geschlossen in "+(I/60)+" Minuten",I);
		//new com.options.EP(urheber());
		//JLabel jp=new JLabel(urheber(),getFDImage(1),2);
		JLabel jp=new JLabel(urheber(),getFDImage(1),2);
		jp.setOpaque(true);
		jp.setBackground(java.awt.Color.blue);
    	f.setContentPane(jp);//JLabel.CENTER)); 
		f.pack();//setSize(320,600);
		f.setLocation(200,50);
		f.setVisible(true);	
	}	
	protected static ImageIcon getFDImage(int m) {
		String str="";
		if(m==1)str="horizent.gif";else str="ebmeingang.gif";
        java.net.URL imgURL = info.class.getResource("/image/"+str);
        if (imgURL != null) {
            return new ImageIcon(imgURL);//.getImage();
        } else {
            return null;
        }
    }
	void zeigeMe(){
		com.options.frame f=new com.options.frame("Info|geschlossen in "+(I/60)+" Minuten",I);		
		java.awt.Dimension s = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
        JLabel jp=new JLabel("",getFDImage(2),2);		
    	f.setContentPane(jp); 
		f.setLocation(s.width,s.height);		
		f.setVisible(true);	
		javax.swing.JWindow w = new javax.swing.JWindow(f);
		w.setContentPane(jp); 		
		w.pack();			
		w.setLocation((s.width/2)-200,(s.height/5)-100);
		w.setVisible(true);	
	}
	public String urheber(){
		 String myinfo=new com.search.sucheDate("gastro/resource/info.dat").md();    
		if(myinfo.indexOf("Mourad El bakry")>-1 && myinfo.indexOf("Vielen Dank")>-1){
			System.out.println("Urheber schutz aktive!!");
			return myinfo;
		}else {
			System.out.println("Urheber schutz aktuellasiert....!!");
			new com.units.save().file("gastro/resource/info.dat",info(),false);
			return new com.search.sucheDate("gastro/resource/info.dat").md();    			
		}
	}
	String[]infoPlus(){
		return new com.search.sucheDate("gastro/resource/infoPlus.dat").myDaten();    
	}
	String[]testInfoPlus(){
		String[]str=infoPlus();
		String[]nstr = {"<u><b>Vielen Dank an Mitwirckenden</b></u>",
		"<br><b>F&uuml;r Verbesserungsvorschl&auml;ge",
		"Design Entwicklung","Korrektur...</b>","<br>Wokachek G & C & J",
		"Zinbauer W","Dettolino  & La Luna","Castello d'Oro & Da Muratti",
		"Frischauf B & D","Daif M",
		"Srivastava V & Khan I","Surmali H<br>..."};		
		if(str.length>0)return str;
		else{
			new com.units.save().dontsort("gastro/resource/infoPlus.dat",nstr,false);
			return nstr;
		}
	}
	
	public String info(){
		basic b=new basic();
		String land=b.getLand();
		if(land.equals("AT"))land="&Ouml;sterreich";
		if(land.equals("GR")||land.equals("DE"))land="Deutschland";
		//if(land.equals("AT")land="Austria";
		 String myinfo="<html><body bgcolor=white width=280 height=520>"+
		 "<br><center><font size=+1 color=red> EbmGastroService&copy;2007 </font>"+
		 "<br><font color=blue><br>Copyright und Eigentum<br>Mourad El bakry<br>Entwickelung<br>nach"+
		 "<br>Jahre lange Erfahrung<br>und  BWL Know How! <br>Bei Fragen oder Hilfe"+
		 "<br>   Telefon<br> +43 6 7 6 3 5 4 6 8 2 4<br>  elbakry@ilone.at</font>"+
		 "<br><br> Programm wird in "+land+" ausgef&uuml;hrt<br>"+
		 "Sprache/Languatsh ist Deutsch/Germany";
		String[]str=testInfoPlus();
		for(int i=0;i<str.length;i++){
			myinfo+="<br>"+str[i];
		}
		myinfo+="</center></body></html>";
		return myinfo;
	}
	public static void main(String[]args){
		new info();
	}
}
