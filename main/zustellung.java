
import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JPopupMenu;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import javax.swing.UIManager ;
import javax.swing.KeyStroke;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Dimension;
import java.util.*;
//import java.util.StringTokenizer;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.Image;
import java.awt.image.BufferedImage;

//import java.util.Locale;
import java.io.*;
import java.lang.*;

import com.options.*;
import com.units.*;
import com.display.*;
import com.tabel.*;
import com.security.*;
import ebm.*;

public class zustellung extends JFrame{
  // Anfang Variablen
  Locale locale;
  private JMenuBar mb = new JMenuBar();   
  JPanel jp=new JPanel();
  private JPopupMenu popup = new JPopupMenu("Pfehlestasten");   
  String[]program;	
  JDesktopPane desktop=new JDesktopPane();
//	JInternalFrame f=new JInternalFrame();
  private JMenu[]menus = new ver6.Rb().menus_z();  
  private JMenuItem[]items =new ver6.Rb().items();
  private JMenu syst = new JMenu("System");	
  private JMenuItem[] sys = new ver6.Rb().sys();
  public Defrag Def;  
  // Ende Variablen
  
  public zustellung(){  
  	Def=new ebm.Defrag();
  	//defzeile=Def.jta1.getText();
  	new ver6.info(1);
	String str=an();  	
  	if(new ver6.basic().getSprache().equals("de"))
  		program=new ver6.actionString().program_D();
  	else program=new ver6.actionString().program_E();    
  	setTitle("Ebm Gastro Service i13 1.10-10-2013 +++ Rechnung erfassen, LOHNVERRECHNUNG, Buchhaltung,.. +++ "+str+" Tage");  	  	  	    	
    	init();  	  	      	
  }
  
  void pm(){  
  	new ebm.PM();
}
 int Intcode(String str){
  	int code=0;
  	try{
  		code=Integer.parseInt(str);
  	}catch(Exception ex){};
  	return code;
  }
  int intcode(String str){
  	int code=0;
  	try{
  		code=Integer.parseInt(str);
  	}catch(Exception ex){code=0;};
  	return code;
  }
  String Test_up(){
  	return new com.search.sucheDate("gastro/resource/up.dat").md();
  }
  
  	void endEs(){
  	String[]jliste=new com.search.sucheDate("gastro/date/jliste.dat").myDaten(); 
  		if(jliste!=null && jliste.length>0){
  			new MyOp().fehler("Sie sollten Erst abbuchen Bitte!");  			
  			Def.zeile+="<br><font color=red>Es sind noch Offene Rechnungen zum Abbuchen!"+
  			            "<br>nes wird dringend Empfohlen Sie abzubuchen<br></font>";
  			Def.jta1.setText(Def.zeile);
  			getContentPane().add(desktop);
  			validate();
  			setCursor(null);
  		}else {	
  			Def.zeile+="<br>Bitte Warten! <br>es kann eine Minute oder mehr dauern<br>";  		
  			Def.update(1);Def.update();pm(); 
  			Def.zeile+="<br> Vielen Dank und auf wieder sehen<br><b> El Bakry M.";
  			Def.jta1.setText(Def.zeile);
  			//new MyOp().fehler("Program wird Beendet!");
  			//Runtime.getRuntime().exit(0);
  		}  		
  }
  void beenden(String t){  	
  	Def.zeigeUpdate();
  	String str="Program wird Beendet!";
  	if(t.equals("exit")) str="exit the programe now!";
  		if(new MyOp().frage(str,"ok","no")==0){  			  			 			  			  			 			
  			getContentPane().remove(desktop);
  			validate();
  			setCursor( java.awt.Cursor.getPredefinedCursor( java.awt.Cursor.WAIT_CURSOR));
  			endEs(); 
  		}
  }
  // Anfang Ereignisprozeduren
 class zuL implements ActionListener {
   public void actionPerformed(ActionEvent e) {
   //JMenuItem target = (JMenuItem)e.getSource();          	
      String t = e.getActionCommand();   	   	 
    if(t.equals("Beenden")||t.equals("exit")){                	    	
    		beenden(t);    
    }
    if(t.equals("Neue Passwort")||t.equals("new password")){
    		if(new com.options.MyOp().frage1("<html><font color=blue>User anlegen oder vVorhandenen User Password Abfragen<br>",
    		                                 new String[]{"User password abfragen","Neue User anlegen"})==0){
				String BenutzerName=new Benutzer().BenutzerName();    		                                 	
    		                                 	
    	      new com.security.chef(BenutzerName);
    		}else new com.security.chef();}
    if(t.equals("Lohnverrechnung")||t.equals("Wages")){
    		String ein=new com.security.userFinster("System Admin").toString();
    		String mou="Mou"+new com.units.myDatum().ist1();
    		String pw=new com.security.ChefSachen(mou).chek(ein);
    		if (pw.equals("Ok")){
    			new egslver.LVM("251401");
    		}
    }
    if(t.equals("Buchhaltung")||t.equals("Wages")){new egsbh.BH();}
  	if (t != "Taschenrechner" && t != "Editor" && t != "Musik" && t != "Beenden" && t != "Spalten"){    }
  	if(t.equals("Rechnung erfassen")||t.equals("new bill")){//createFrame(new myRc());
    	createFrame(new ebm.RcnF());
  	}else
    if(t.equals("Change Firmen Bezeichnung")||t.equals("change organisation data")){
      new ebm.firma().neueDate();
    }
    if(t.equals("Info")||t.equals("about us")){  new ver6.info(); }
    if(t.equals("Kassa Buch")||t.equals("cash book")){createFrame(new ebm.KB());}else
    if(t.equals("Update")){    	
    	if(Test_up().length()>0){
    		System.out.println("***** have done ************");
    	}else{    		
    		String ein=new com.security.userFinster("System Admin").toString();
    		String mou="Mou"+new com.units.myDatum().ist1();
    		String pw=new com.security.ChefSachen(mou).chek(ein);
    		if (pw.equals("Ok")){
    			Def.zeigeUpdate();
    			new com.security.RMS();
    			Def.update(1);
    		}
    	}
    }else
    if(t.equals("Auswertung")){/*
    		if(new com.options.MyOp().frage1("<html><font color=blue>Umsatz Bewertung!<br>"+
    		                                 "<u>Detail:</u> Sie haben die Wahl einzige element zu bewerten<br>"+
    		                                 "also ein Tag oder ein Monat oder ein Kundencode u..<br>"+
    		                                 "<u>Gesamt:</u> Wenn Sie Monatsumsatz dann gilt f&uuml;r <br>"+
    		                                 "Alle Monaten im Jahr oder Alle Kunden oder Alle Tage u..<br>",
    		                                 new String[]{"Detail","Gesamt"})==0){
    			new ebm.SK();
    		}else new ebm.SK(-1);*/
    }else			
    if(t.equals("Waren Korb")||t.equals("goods basket")){createFrame(new ebm.wk());}else
    //if(t.equals("Defragmentieren")){Def.entMon();}else
    if(t.equals("Kellner Programm")||t.equals("Weater application")){createFrame(new ebm.kellner());}else	
    if(t.equals("Fahrer")||t.equals("driver")){//createFrame(new ebm.FL());}else//} 
      new MyOp().fehler1("<Html><font color=red face='Courier New'>"+
         "<center>Dieser Mudull ist nicht aktive</center></font>");}else
    if(t.equals("Kunden")||t.equals("customers application")){createFrame(new ebm.KD());}else
    if(t.equals("Spalten")||t.equals("calculation view")){          
    	String dat=new com.units.myDatum().ist_my();
      com.units.WG wg=new com.units.WG("gastro/Date",dat);    	
      wg.zeige(wg.Ausgabe);    	
      }
    if(t.equals("Waren")||t.equals("goods application")){createFrame(new ebm.WD());}else
    if(t.equals("Journal")){new MyOp().fehler1("<Html><font color=red face='Courier New'>"+
         "<center>Dieser Mudull ist nicht aktive</center></font>");}//createFrame(new JL());}else
    if(t.equals("Tabelle")||t.equals("tables expression")){new RepTest("T a b e l l e n");}  
    if(t.equals("Hilfe")||t.equals("help")){new EP(" help ");}
    if(t.equals("Drucker Papier")||t.equals("choose paper")){ double Skale = new com.printer.papier().getPapierformat();}
  if(t.equals("Taschenrechner")||t.equals("calculater")){new myCmd("calc.exe");}
  if(t.equals("Neue Kunde")||t.equals("add customer")){ebm.KD kd=new ebm.KD();kd.zufugen();kd.saveControl();}
  if(t.equals("Neue Ware")||t.equals("add good")){ebm.WD wd=new ebm.WD();wd.zufugen();wd.saveControl();}
  if(t.equals("EbmGastroService online")){ 
  	/*String[] hd = new com.search.sucheDate("gastro/tvradio.html").myDaten();
  	String html="";
  	for( int i= 0;i<hd.length;i++)html+=hd[i];*/
  	try { 
  		//java.awt.Desktop.getDesktop().browse( new URI("http://www.ilone.at/") );
  		//java.awt.Desktop.getDesktop().open( new File("http://www.ilone.at/start.htm") );
  		String url = "http://www.ilone.at/"; 
  		new ProcessBuilder( "rundll32", "url.dll,FileProtocolHandler", url ).start();
  		}catch ( Exception /* IOException, URISyntaxException */ eU ) {  eU.printStackTrace(); }
  		//new EP("http://www.ilone.at/start.htm",1);
  }
    if(t.equals("Formular Vorschau")||t.equals("formular view")){new ebm.GF();}
  
  
  if(t.equals("Musik")){        
            new myCmd("wmplayer.exe" );}
  if (t.equals("Editor")){  
    new myCmd("Notepad.exe gastro/mytext.dat");   
  }
  if (t.equals("Dekorieren")||t.equals("design")){dekor();}
  if (t.equals("Einstellung")||t.equals("config")){new ver6.config();}
  /*if (t.equals("Wkorb Kurriktor")|| t.equals("Install Font")){
      String ein=new com.security.userFinster("System Admin").toString();
        String pw=new com.security.ChefSachen("Mou110163").chek(ein);
        if (pw.equals("Ok")){
          //if (t.equals("Install Font")){new myfont().systemFont();}
          if (t.equals("Wkorb Kurriktor")){new ver6.Rcode().save();}          
        }
  }*/         
  //createLogo(new ebm.logo());
   }
  };
  void myfont(){
    String myfont=new com.search.sucheDate("gastro/date/source/myfont.dat").md();   
    if(myfont.equals("instaliert")) System.out.println("bereit "+myfont);
    else {new com.security.myfont().systemFont();
      System.out.println("...instalieren");}
  }
  String mycon(){
    String myc=new com.search.sucheDate("gastro/date/source/config.dat").md();    
    if(myc.equals("bereit eingestellt")) System.out.println(""+myc);
    else myc="nochnichtbereit";
    return myc;
  }
protected void createFrame(JApplet c) {
	int h=desktop.getHeight();
	int w=desktop.getWidth();
    if(h>800)h=h-100;else h=h-30;     	
	
	Display f=new Display(c,w-100,h);
	f.setVisible(true); 
    desktop.add(f);
    try {
         f.setSelected(true);
     } catch (java.beans.PropertyVetoException e) {}
     if(f.isSelected())f.moveToFront();c.validate();f.validate();desktop.validate();	
}
protected void createFrame(JFrame c) {		
	int h=desktop.getHeight();
	int w=desktop.getWidth();
    if(h>800)h=h-100;else h=h-30;     	
	Display f=new Display(c,w-100,h);
	f.setVisible(true); 
	f.addMouseListener(new PL());   ;
    desktop.add(f);// desktop.validate();
        try {
            f.setSelected(true);          
        } catch (java.beans.PropertyVetoException e) {}        
         if(f.isSelected())f.moveToFront();c.validate();f.validate();desktop.validate();
}
protected void createFrame() {	
	int h=desktop.getHeight();
	int w=desktop.getWidth();
    if(h>800)h=h-100;else h=h-20;     	
	
	Display f=new Display(this,w-100,h);
	f.setVisible(true); //necessary as of 1.3
	desktop.add(f);
	try {            
		f.setSelected(true);
	} catch (java.beans.PropertyVetoException e) {}
}
public void init() {
	//  myfont();		
	Border loweredetched = BorderFactory.createEtchedBorder(EtchedBorder.LOWERED);
	Border raisedbevel = BorderFactory.createRaisedBevelBorder();
	JPanel jpp=new JPanel();
	jpp.setLayout(new BorderLayout());
	jp.setLayout(new GridLayout());
	//jp.setLayout(new javax.swing.BoxLayout(jp, javax.swing.BoxLayout.X_AXIS));//new GridLayout(0,3));//
	//jp.setBorder(new LineBorder(new Color(0,100,150),1,true));
	jp.setBorder(new TitledBorder(raisedbevel,""));       
	//jpp.setBorder(new TitledBorder(loweredetched,"Funktionstasten"));
    for(int i = 0; i < program.length; i++) {
    	JMenuItem progb=new JMenuItem(program[i]);
    	progb.addActionListener(new zuL());
    	popup.add(progb);    	
    }
    //javax.swing.ButtonGroup group = new javax.swing.ButtonGroup();
	//JPanel rP = new JPanel(new GridLayout(1, 0));	
  for(int i = 0; i < items.length; i++) {   
    items[i].addActionListener(new zuL());    
    menus[i % 3].add(items[i]); 
  	String str=items[i].getText();
  	javax.swing.JButton jpb=leiste(""+items[i].getText());
  	//javax.swing.JRadioButton jprb=leiste_rb(""+items[i].getText());  	
  	if(jpb!=null){
  		//	group.add(jprb);
  		//	rP.add(jprb);
  			jp.add(jpb);
  		}
  	}
  	//jp.add(rP);
    //jp.add(group);
	jp.setBackground(Color.blue);//new Color(55,205,0));
	JLabel logl=new JLabel("<html><font size=+1>&copy; E G S (2003 - 2013)</html>",null,0);
	logl.setForeground(Color.white);
	jp.add(logl,BorderLayout.PAGE_END);
	jpp.add(jp,BorderLayout.PAGE_END);
	jpp.setBackground(Color.blue);//new Color(100,154,200));
	//jp.setBackground(new Color(55,205,0));
	
    items[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
	items[2].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, ActionEvent.ALT_MASK));
	items[8].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.ALT_MASK));
	
  for(int i = 0; i < sys.length; i++) {   
    sys[i].addActionListener(new zuL());
    syst.add(sys[i]);
  }
  sys[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_END, ActionEvent.ALT_MASK));
  syst.setMnemonic(KeyEvent.VK_S);  
	
  menus[0].setMnemonic(KeyEvent.VK_G);  
  menus[1].setMnemonic(KeyEvent.VK_V);
  menus[2].setMnemonic(KeyEvent.VK_C);
  
  for(int i = 0; i < menus.length; i++){
    mb.add(menus[i]);
  }
    mb.add(syst);   
	mb.addKeyListener(new myKeyslist());

    int inset = 20;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();    
	jpp.add(mb);
 	desktop = new JDesktopPane();	
	desktop.setOpaque(true);
	desktop.setBackground(new Color(100,154,200));
	desktop.putClientProperty("JDesktopPane.dragMode","outline");
  
	//desktop.setSize(700,700);
  	desktop.addMouseListener(new PL());  
	//jpp
	getContentPane().addKeyListener(new myKeyslist());
   //mb.add(jpp);   	
	//desktop.setPreferredSize(new Dimension((screenSize.width),(screenSize.height)));
	getContentPane().setLayout(new BorderLayout());  
	getContentPane().add(jpp,BorderLayout.PAGE_START);
	getContentPane().add(new JLabel(""));
	getContentPane().add(desktop);
	setJMenuBar(mb);  
	setPreferredSize(new Dimension(screenSize.width,screenSize.height));
	//setContentPane(desktop);createFrame(this);  //setContentPane(desktop);     		
 }
 javax.swing.JButton leiste(String str){
 	javax.swing.JButton jpb;
 	if(str.equals("Rechnung erfassen") || str.equals("Waren") || str.equals("Kunden") || 
  	   str.equals("Waren Korb")||str.equals("Kassa Buch")||str.equals("cash book")||
  	   str.equals("goods application")||str.equals("customers application")||
  	   str.equals("goods basket")||str.equals("new bill")){
  	   	//if(str.equals("new bill")||str.equals("Rechnung erfassen"))
  	   	if(str.length()>9||str.indexOf("application")>-1)	
  	   		jpb=new javax.swing.JButton("",new ImageIcon(image(str.substring(0,9),130,25)));  	   
  	   	else jpb=new javax.swing.JButton("",new ImageIcon(image(str,130,25)));  	   	
  	   	jpb.setActionCommand(str);		
  	//	jpb.setFont(new Font("",1,12));
  		jpb.addActionListener(new zuL());
  		//jpb.setBackground(Color.blue);//new Color(0,153,0));
  	   	return jpb;
  	   }else return null;
 }
 javax.swing.JRadioButton leiste_rb(String str){
 	javax.swing.JRadioButton jpb;
 	if(str.equals("Rechnung erfassen") || str.equals("Waren") || str.equals("Kunden") || 
  	   str.equals("Waren Korb")||str.equals("Kassa Buch")||str.equals("cash book")||
  	   str.equals("goods application")||str.equals("customers application")||
  	   str.equals("goods basket")||str.equals("new bill")){
  	   	if(str.length()>9||str.indexOf("application")>-1){
  	   		jpb=new javax.swing.JRadioButton(str.substring(0,9));//,new ImageIcon(image(str.substring(0,9),130,25)));  	     	   		

  	   	}else jpb=new javax.swing.JRadioButton(str);//,new ImageIcon(image(str,130,25)));
  	   	if(str.equals("Rechnung erfassen")||str.equals("new bill"))jpb.setSelected(true);
  	   	//jpb.setForeground(Color.white);
  	   	jpb.setActionCommand(str);		
  	//	jpb.setFont(new Font("",1,12));
  		jpb.addActionListener(new zuL());
  		//jpb.setBackground(Color.blue);//new Color(0,153,0));
  	   	return jpb;
  	   }else return null;
 }
  protected static Image image(String str,int w,int h) {
    	//Create a 16x16 pixel image.
    	BufferedImage bi = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
    	//Draw into it.
    	Graphics g = bi.getGraphics();
    	g.setColor(java.awt.Color.white);    	
    	g.fillRect(0, 0, w, h);
    	g.setColor(java.awt.Color.blue);    	
    	g.fillOval(0, 0, 20, h);
    	g.setColor(java.awt.Color.green);
    	g.fillOval(20, 5, w, h);
    	g.setColor(java.awt.Color.yellow);
    	g.fillOval(5, 2, w-10, h-5);
    	g.setColor(java.awt.Color.blue);
    	g.setFont(new java.awt.Font("",1,15));
    	g.drawString(str,25, 15);
    	//Clean up.
    	g.dispose();
    	return bi;
    }
 protected static Image createFDImage() {
 	//Create a 16x16 pixel image.
 	BufferedImage bi = new BufferedImage(16, 16, BufferedImage.TYPE_INT_RGB);
 	//Draw into it.
 	Graphics g = bi.getGraphics();
 	g.setColor(Color.green);
 	g.fillRect(0, 0, 15, 15);
 	g.setColor(Color.red);
 	//g.drow("G");
 	g.fillRect(4, 4, 10,10);
    g.setColor(Color.yellow);
    g.fillOval(5, 3, 7, 7);
 	//Clean up.
 	g.dispose();
 	//Return it.
 	return bi;
 }
 protected static ImageIcon getImg(int m) {
		String str="";
		if(m==1)str="hor2.gif";else str="ebmeingang.gif";
        java.net.URL imgURL =zustellung.class.getResource("/image/"+str);
        if (imgURL != null) {
            return new ImageIcon(imgURL);//.getImage();
        } else {
            return null;
        }
    }
  //Returns an Image or null.
    protected static Image getFDImage() {
        java.net.URL imgURL = zustellung.class.getResource("/image/ico1.gif");
        if (imgURL != null) {
            return new ImageIcon(imgURL).getImage();
        } else {
            return createFDImage();
        }
    }
    void dekor(){
      String[]mylook={
      "com.sun.java.swing.plaf.windows.WindowsLookAndFeel",
    "com.sun.java.swing.plaf.motif.MotifLookAndFeel",
    "javax.swing.plaf.metal.MetalLookAndFeel" ,
    "com.sun.java.swing.plaf.gtk.GTKLookAndFeel",       
      };
      String[]wahl={"Windows","Motif","Metal","Java"};      
      int i=new MyOp().frage1("Wie soll Ihre Dekor ausschauen?",wahl);
      try {       
      UIManager.setLookAndFeel(mylook[i]);
      	validate();
      //UIManager.getSystemLookAndFeelClassName()) ;
      } catch (Exception e) { }
    }
    
    private static void createAndShowGUI() {            
        JFrame frame =new zustellung(); 		
        frame.setIconImage(getFDImage()); 
    	try {       
    		UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
    	} catch (Exception e) { }
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);        
        frame.pack();    	
        frame.setVisible(true);       	
    }
    //******************************************************************//
  String an(){
  	//new ver6.info(1);
  	com.security.lizenc l=new com.security.lizenc();
  	String str=l.test();  	
  	int abgelaufen=Intcode(str);
    if(str.equals("neue")&& mycon().equals("nochnichtbereit")){
    	new ver6.config();
    	str="Demo";
    	new save().file("gastro/date/benutzer.dat","Chef",false);
    }     	
  	//if(abgelaufen < 0)str=" Lizenc seit "+(abgelaufen*-1)+" Tage abgelaufen!!";else str=(abgelaufen*1)+" Tage" ;
    
    	str=new com.search.sucheDate("gastro/date/protocol/l.dat").md();
  	//if(!new com.security.system().aus()) {Runtime.getRuntime().exit(0);}
    return str;
  }   
  //************************************************************//
  public static void main(String[] args) {
     try {
        String vers = System.getProperty("java.version");
        if (vers.compareTo("1.6") < 0) {
            System.out.println("!!!WARNING: Swing must be run with a " +
                               "1.6 or higher version VM!!!");
        }
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });

        //new com.security.Rambo("reingen");
     } catch (Throwable t) {
            System.out.println("uncaught exception: " + t);
            t.printStackTrace();
        }
  }      
  // Ende Ereignisprozeduren
 class PL extends MouseAdapter {
    public void mousePressed(MouseEvent e) {
      maybeShowPopup(e);
    }
    public void mouseReleased(MouseEvent e) {
      maybeShowPopup(e);
    }
    private void maybeShowPopup(MouseEvent e) {
      if(e.isPopupTrigger()) {
        popup.show(e.getComponent(), e.getX(), e.getY());
    //popup.show(e.getComponent(),e.getX() + popup.getWidth()/3, e.getY() + popup.getHeight()/3);
      }
    }
 }
 class myKeyslist implements KeyListener {
    public void keyPressed(KeyEvent e) {
      maybeShowPopup(e);
    }
    public void keyReleased(KeyEvent e) {
      maybeShowPopup(e);
    }
    public void keyTyped(KeyEvent e) {
      maybeShowPopup(e);
    }   
    private void maybeShowPopup(KeyEvent e) {
      int id = e.getID();
      int modifiers = e.getModifiersEx();
      int location = e.getKeyLocation();
      char c = e.getKeyChar();
      int keyCode = e.getKeyCode();
        String keyString = "" + c ;    	 	
   		if (e.isActionKey()) {      
   			keyString =""+keyCode;
        	if(keyString.equals("112")){new EP(" help ");}
        	//if(keyString.equals("d")&& modifiers==128){          //Strg_D oder Strg_d  	entMon();        	}
   		}else System.out.println("ID:"+id+",Modifiers:"+modifiers+", location: "+location+", Char:"+c+", KeyCode:"+keyCode);
    }
 }
 } ///:~
