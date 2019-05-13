// created on 12.09.2007 at 16:01
//Rcn Buttons in verschiedene Sprachen
package ver6;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import javax.swing.Icon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
//import javax.swing.event.*;
import java.awt.event.*;
import java.awt.BasicStroke;
import java.awt.Graphics2D;
import javax.swing.AbstractButton;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
public class Rb{
	public String imo;
	public Rb(){
		imo=basicSprache();		
	}
	public Rb(int X){
		
	}
	
	public String basicSprache(){
		String str=new com.search.sucheDate("gastro/resource/sp.dat").md();
		if(str=="" ||str==null ||str.length()<=0){
			str=sprache();
			new com.units.save().file("gastro/resource/sp.dat",str,false);
		}
		return str;
	}
	public String sprache(){
		ver6.basic b=new ver6.basic();
		String sprache=b.getSprache();
		String o="";
		if(sprache.equals("ar"))o="a";
		if(sprache.equals("en"))o="e";
		if(sprache.equals("de"))o="d";
		return o;//"e/";
	}	
	public String[]kd(){
		if(imo.equals("d"))return new opString().kd_d();
		else return new opString().kd_e();		
	}
	public String[]rc(){
		if(imo.equals("d"))return new opString().rc_d();
		else return new opString().rc_e();		
	}
	public String[]opkd(){
		if(imo.equals("d"))return new opString().opkeys_d();
		else return new opString().opkeys_e();		
	}
	public String kd_m(){
		if(imo.equals("d"))return "Datei";
		else return "File";
	}
	public String[]kd_mi_e(){
		String[]strEx=new com.search.sucheDate("gastro/resource/kd_keys_e.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"Read","Search","Add new entry","Edite","Print","quite"};		
			new com.units.save().dontsort("gastro/resource/kd_keys_e.dat",strEx,false);
		}
		return strEx;
	}
	public String[]kd_mi_d(){
		String[]strEx=new com.search.sucheDate("gastro/resource/kd_keys_d.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"Lesen","Suchen","Erfassen","Bearbeiten","Drucken","Beenden"};		
			new com.units.save().dontsort("gastro/resource/kd_keys_d.dat",strEx,false);
		}
		return strEx;
	}
	public JMenuItem[]kd_mi(){
  	String[]astr=kd_mi_d();
  	String[]str=new String[astr.length];		
  	JMenuItem[] m = new JMenuItem[str.length];  	
  	for(int i=0;i<m.length;i++){
  		if(imo.equals("d"))str=astr;else str=kd_mi_e();
  		m[i]=new JMenuItem(str[i]);
  		m[i].setActionCommand(astr[i]);					
  	}  	
  	return m;
  }
	String[]actionString_d(){
		String[]astr={"Faktura","Offene Posten","R-Sehen","Nachdruck","T-Absatz",
		"Abuchen","Bericht","F-Bericht","M-Bericht","Neuer Kunde","Neue Ware","Storno"};		
		return astr;		
	}
	String[]actionString_e(){
		String[]strEx=new com.search.sucheDate("gastro/resource/rc_keys_e.dat").myDaten();    
		if(strEx.length<=0){
			strEx=new String[]{"New bill","Todays sale","View of bills","Print out bills","View of sales",
			"Transfer","First control","Second control","Print all","Add customer","Add good","Del Bill"};		
			new com.units.save().dontsort("gastro/resource/rc_keys_e.dat",strEx,false);
		}		
		return strEx;		
	}
	public JButton[] ok(){ 			
		String[]astr=actionString_d();
		String[]str=new String[astr.length];		
		ImageIcon[] icons={ico(imo+"01.gif"),ico(imo+"02.gif"),ico(imo+"03.gif"),ico(imo+"04.gif"),
		ico(imo+"5.gif"),ico(imo+"6.gif"),ico(imo+"7.gif"),ico(imo+"8.gif"),ico(imo+"10.gif"),
		ico(imo+"11.gif"),ico(imo+"12.gif"),ico(imo+"14.gif")};
		JButton[] ok=new JButton[astr.length];
		for(int i=0;i<ok.length;i++){
			if(icons[i]!=null) ok[i]=new JButton("",icons[i]);
				else {
					if(imo.equals("d"))str=astr;else str=actionString_e();
					//color=ok[i].getBackground();
					icons[i]=new ImageIcon(image(str[i],140,18));
					ok[i]=new JButton("",icons[i]);
					//links[i].setFont(new Font("",0,8));
				}
			ok[i].setVerticalTextPosition(AbstractButton.CENTER);//BOTTOM);
    		ok[i].setHorizontalTextPosition(AbstractButton.CENTER);	
			ok[i].setActionCommand(astr[i]);					
		}
		return ok;
	}
	public JButton color_ok(){
		if(imo.equals("d"))return new JButton("Farbe Anwenden");
		else { 
			JButton ok=new JButton("Activate it");
			ok.setActionCommand("Farbe Anwenden");					
			return ok;
		}
	}
  public String[] slid(){  	
  	if(imo.equals("d"))return new actionString().slide_d();
  	else return new actionString().slide_e();
  }
  public JMenu[] menus(){  	
  	if(imo.equals("d"))return new JMenu[]{new JMenu("Chefs" ),new JMenu("Bearbeiten"),new JMenu("Extras") ,new JMenu("Hilfe") };
  	else return new JMenu[]{new JMenu("Boos" ),new JMenu("Edit"),new JMenu("Extras") ,new JMenu("Help") };  	
  }
  public JMenu[] menus_z(){  	
  	if(imo.equals("d"))return new JMenu[]{new JMenu("Gastro" ),new JMenu("Service"),new JMenu("Chefs") };
  	else return new JMenu[]{new JMenu("Gastro" ),new JMenu("Service"),new JMenu("Boos") };  	
  }
  public JMenuItem[] menuitems(){
  	String[]strd={"Schrift","Farbe","Musik","Rechner","Editor","Tageserlos","Monat Absatz","Umbuchung",
  	"Hilfe","Install Font","Papierformat","info"};
  	String[]stre={"font","Color","music","Calculater","Editor","Days turnover","Month seals","Transfar",
  	"Help","Install font","Paper format","Info"};
  	JMenuItem[] m = new JMenuItem[strd.length];
  	for(int i=0;i<m.length;i++){
  		if(imo.equals("d"))stre=strd;
  		m[i]=new JMenuItem(stre[i]);
  		m[i].setActionCommand(strd[i]);					
  	}  
  	return m;
  }
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
  
    protected static Image image(String str,int w,int h) {
    	//Create a 16x16 pixel image.
    	BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    	//Draw into it.
    	Graphics g = bi.getGraphics();
    	g.setColor(java.awt.Color.white);    	
    	g.fillRect(0, 0, w, h);
    	g.setColor(java.awt.Color.red);    	
    	g.fillOval(0, 0, 20, h);
    	g.setColor(java.awt.Color.green);
    	g.fillOval(20, 5, w, h);
    	g.setColor(java.awt.Color.yellow);
    	g.fillOval(5, 2, w-10, h-5);
    	g.setColor(java.awt.Color.red);    	
    	g.fillOval(w-10, h-5, w, h);    	
    	g.setColor(java.awt.Color.black);
    	g.setFont(new java.awt.Font("",1,15));
    	g.drawString(str,25, 15);
    	//Clean up.
    	g.dispose();
    	return bi;
    }
   
    public String[]rcntf(){
    	if(imo.equals("d"))return new actionString().rcn_tf_d();  	
    	else return new actionString().rcn_tf_e();  	  	
    }
  public JMenuItem[]items(){ 
  	String[]astr=new actionString().items();  	
  	String[]str=new String[astr.length];		
  	JMenuItem[] m = new JMenuItem[str.length];  	
  	for(int i=0;i<m.length;i++){
  		if(imo.equals("d"))str=astr;else str=new actionString().items_E();
  		m[i]=new JMenuItem(str[i]);
  		m[i].setActionCommand(astr[i]);					
  	}
  	m[0].setMnemonic(KeyEvent.VK_R); m[1].setMnemonic(KeyEvent.VK_A);
  	m[2].setMnemonic(KeyEvent.VK_B);  m[3].setMnemonic(KeyEvent.VK_N);
  	m[4].setMnemonic(KeyEvent.VK_T);m[5].setMnemonic(KeyEvent.VK_F);
  	m[6].setMnemonic(KeyEvent.VK_J);m[7].setMnemonic(KeyEvent.VK_K);
  	m[8].setMnemonic(KeyEvent.VK_W);m[9].setMnemonic(KeyEvent.VK_U);
  	m[10].setMnemonic(KeyEvent.VK_P);m[11].setMnemonic(KeyEvent.VK_L);  
  	return m;
  }
  public JMenuItem[]sys(){
  	String[]astr=new actionString().syst();
  	String[]str=new String[astr.length];		
  	JMenuItem[] m = new JMenuItem[str.length];  	
  	for(int i=0;i<m.length;i++){
  		if(imo.equals("d"))str=astr;else str=new actionString().syst_E();
  		m[i]=new JMenuItem(str[i]);
  		m[i].setActionCommand(astr[i]);					
  	}
  	m[0].setMnemonic(KeyEvent.VK_B);m[1].setMnemonic(KeyEvent.VK_P);m[2].setMnemonic(KeyEvent.VK_E);
  	m[3].setMnemonic(KeyEvent.VK_I);m[4].setMnemonic(KeyEvent.VK_C);m[5].setMnemonic(KeyEvent.VK_D);
  	//m[m.length-1].setMnemonic(KeyEvent.VK_F);  	
  	return m;
  }
}
