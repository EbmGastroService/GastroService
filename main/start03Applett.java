
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

import java.awt.*;
import java.beans.*;
import javax.swing.border.*;
import javax.swing.KeyStroke;
import javax.swing.JDesktopPane;

import java.util.*;

import com.options.*;
import com.units.*;
import com.display.*;
import com.tabel.*;
import com.security.*;
import ebm.*;

public class start03Applett extends JApplet {
	Locale locale;
	JMenuBar mb = new JMenuBar();		
	JPanel jp=new JPanel(new BorderLayout());
	JPopupMenu popup = new JPopupMenu("Pfehlestasten");
	JToolBar jtb =new JToolBar("TOOLS");
	String[]buttons={"Rechnung erfassen","Waren","Kunden","Kassa Buch","Neue Passwort","Beenden"};
  	JTabbedPane[] tabs = {new JTabbedPane(2),new JTabbedPane(2),new JTabbedPane(2)};
	String[] program ={"Bearbeiten","Musik","Editor","Taschenrechner","Neue Kunde","Neue Ware","Hilfe",
	"Drucker Papier","Rechnung erfassen","Kellner","Fahrer","Waren","Kassa Buch","Journal","Neue Passwort","Beenden",
	"Tabelle","Kunden","Waren Korb","Spalten"};
	JDesktopPane desktop;
	JMenu[] menus = {new JMenu("Gastro"),new JMenu("Service") ,new JMenu("Chef") };
    JMenu syst = new JMenu("System");
    JMenuItem[] items = {
    new JMenuItem("Rechnung erfassen",KeyEvent.VK_R), new JMenuItem("Bearbeiten",KeyEvent.VK_B),
    new JMenuItem("Kassa Buch",KeyEvent.VK_C),new JMenuItem("Kellner",KeyEvent.VK_K), 
    new JMenuItem("Waren Korb",KeyEvent.VK_W) , new JMenuItem("Tabelle",KeyEvent.VK_T),
    new JMenuItem("Fahrer",KeyEvent.VK_F),new JMenuItem("Journal",KeyEvent.VK_J),
    new JMenuItem("Kunden",KeyEvent.VK_K),new JMenuItem("Waren",KeyEvent.VK_A),   
    new JMenuItem("Spalten",KeyEvent.VK_S)   
  };
JMenuItem[] sys = {
	new JMenuItem("Beenden",KeyEvent.VK_B),//new ImageIcon("image/g01.gif")),
	new JMenuItem("Neue Passwort",KeyEvent.VK_P),
	//new JMenuItem("Install Font",KeyEvent.VK_F),
	new JMenuItem("Wkorb Kurriktor"),
	new JMenuItem("Einstellung"),
	new JMenuItem("Dekorieren",KeyEvent.VK_D)
};
public start03Applett(){
	an();
	locale = new Locale("de","DE") ;
	Locale.setDefault(locale);//Locale.GERMANY);
	//init();
	//showStatus("MyApplet: Loading classes ");
	}
 void update(){
  	String adatum=new com.units.myDatum().ist_my();
  	String[]fahre=new com.search.sucheDate("gastro/date/benutzer.dat").myDaten(); 
  	for(int i=0;i<fahre.length;i++)
  	new ebm.FL().update(fahre[i]);  	
  	new ebm.SK(1);new ebm.SK(2);  	
  	new ebm.update("/","gastro/date/fliste.dat","gastro","FL",adatum);
  	new ebm.update("/","gastro/date/kbuch.dat","gastro","KB",adatum);
  	new ebm.update("N","gastro/date/kon.dat","gastro","KO",adatum);  	
  	//new ebm.BW(60,"Update war Erfolgreich");    
  }
  void update(int y){  	
  	String[]fahre=new com.search.sucheDate("gastro/date/benutzer.dat").myDaten(); 
  	for(int i=0;i<fahre.length;i++)
  	new ebm.FL().update(fahre[i]);  	
  	new ebm.SK(1);new ebm.SK(2);
  	new ebm.update("/","gastro/date/fliste.dat","gastro","FL");
  	new ebm.update("/","gastro/date/kbuch.dat","gastro","KB");
  	new ebm.update("N","gastro/date/kon.dat","gastro","KO");
  }
  	//new ebm.BW(60
class zuap implements ActionListener {
    public void actionPerformed(ActionEvent e) {
   //JMenuItem target = (JMenuItem)e.getSource();          	
      String t = e.getActionCommand();   	   	 
    if(t.equals("Beenden")||t.equals("exit")){                	
    	if(t.equals("exit")){
    		if(new MyOp().frage("exit the programe now!","yes","no")==0){           
    				update(1);update();
    			Runtime.getRuntime().exit(0); 
    		}
    	}else
    		if(new MyOp().frage("Program wird Beendet!","OK","NEIN")==0){           
    				update(1);update();
    			Runtime.getRuntime().exit(0); 
    		}      
    }
    if(t.equals("Neue Passwort")||t.equals("new password")){new com.security.chef();}      
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
    	String ein=new com.security.userFinster("System Admin").toString();
    	String mou="Mou"+new com.units.myDatum().ist1();
        String pw=new com.security.ChefSachen(mou).chek(ein);
    	if (pw.equals("Ok")){update(1);    		    	}
    }else
    if(t.equals("Auswertung")){
    		/*if(new com.options.MyOp().frage1("<html><font color=blue>Umsatz Bewertung!<br>"+
    		                                 "<u>Detail:</u> Sie haben die Wahl einzige element zu bewerten<br>"+
    		                                 "also ein Tag oder ein Monat oder ein Kundencode u..<br>"+
    		                                 "<u>Gesamt:</u> Wenn Sie Monatsumsatz dann gilt f&uuml;r <br>"+
    		                                 "Alle Monaten im Jahr oder Alle Kunden oder Alle Tage u..<br>",
    		                                 new String[]{"Detail","Gesamt"})==0){
    			new ebm.SK();
    		}else new ebm.SK(-1);*/
    }else			
    if(t.equals("Waren Korb")||t.equals("goods basket")){createFrame(new ebm.wk());}else
    if(t.equals("Kellner Programm")||t.equals("Weater application")){/*createFrame(new ebm.kellner())*/;}else	
    if(t.equals("Fahrer")||t.equals("driver")){//createFrame(new ebm.FL());}else//} 
      new MyOp().fehler1("<Html><font color=red face='Courier New'>"+
         "<center>Dieser Mudull ist nicht aktive</center></font>");}else
    if(t.equals("Kunden")||t.equals("customers application")){createFrame(new ebm.KD());}else
    if(t.equals("Spalten")||t.equals("calculation view")){          
    	String dat=new myDatum().ist_my();
      WG wg = new WG("gastro/Date",dat);    	
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
  if(t.equals("EbmGastroService online")){new EP("http://www.ilone.at/start.htm",1);  }
    if(t.equals("Formular Vorschau")||t.equals("formular view")){new com.printer.DF();}
  
  
  if(t.equals("Musik")){        
            new myCmd("wmplayer.exe" );}
  if (t.equals("Editor")){  
    new myCmd("Notepad.exe gastro/mytext.dat");   
  }
  if (t.equals("Dekorieren")||t.equals("design")){dekor();}
  if (t.equals("Einstellung")||t.equals("config")){new ver6.config();}  
   }
  };
	void myfont(){
		String myfont=new com.search.sucheDate("gastro/date/source/myfont.dat").md();		
		if(myfont.equals("instaliert")) System.out.println("Font ist "+myfont);
		else {new com.security.myfont().systemFont();
			System.out.println("jetzt instalieren");}
	}
public void createFrame(JApplet c) {        
	Display f=new Display(c,desktop.getWidth()-100,desktop.getHeight()-100);
	f.setVisible(true);
	   	desktop.add(f);	validate();
        try {
            f.setSelected(true);
        	f.setFocusable(true);
        } catch (java.beans.PropertyVetoException e) {}        
    }
    public void createFrame(JFrame c) {        
	Display f=new Display(c,desktop.getWidth()-100,desktop.getHeight()-100);
	f.setVisible(true);
	   	desktop.add(f);	validate();
        try {
            f.setSelected(true);
        	f.setFocusable(true);
        } catch (java.beans.PropertyVetoException e) {}        
    }
public void createGUI() {
	//myfont();
	jtb.setLayout(new GridLayout(buttons.length+1,0));		
    for(int i = 0; i < program.length; i++) {
	 JMenuItem progb=new JMenuItem(program[i]);
	 progb.addActionListener(new zuap());		
	 popup.add(progb);  
     //jtb.add(progb);		
	}
	//jtb.setLayout(new GridLayout(2,0));		
	for(int i = 0; i < buttons.length; i++) {
	 JButton button=new JButton(buttons[i]);	
	 button.setActionCommand(buttons[i]);
     button.setToolTipText(buttons[i]+" Wenn Sie darauf drucken");	
	 button.addActionListener(new zuap());			 
    jtb.add(button);	
	}	
	
	/*/tabs.setBackground(Color.orange);
	JPanel p3 = new JPanel();
	JPanel p4 = new JPanel();
	p4.setLayout(new BoxLayout(p4, BoxLayout.X_AXIS));
  	p4.setBorder(new BevelBorder(BevelBorder.RAISED));
	p3.setLayout(new  BoxLayout(p3, BoxLayout.Y_AXIS));
  	p3.setBorder(new BevelBorder(BevelBorder.RAISED));

	for(int i = 0; i < program.length; i++){
	  JMenuItem mi=new JMenuItem(program[i]);	
	   mi.addActionListener(new zuap());	
		tabs[i %3].addTab(program[i],mi);
	}
	for(int i = 0; i < tabs.length; i++)p3.add(tabs[i]);
	p4.add(p3);	*/
	for(int i = 0; i < items.length; i++) {
      items[i].addActionListener(new zuap());		
      menus[i % 3].add(items[i]);		
    }
    items[0].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
	items[3].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_K, ActionEvent.ALT_MASK));
	items[10].setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.ALT_MASK));

    for(int i = 0; i < sys.length; i++) {	  
		sys[i].addActionListener(new zuap());
		syst.add(sys[i]);
	}
	syst.setMnemonic(KeyEvent.VK_S);		
 	/*JLabel[] ok = {new JLabel("",new ImageIcon("gastro/image/Gaslog01.gif"),0),
    		new JLabel("<html><center><font color=green><strong>EBM GASTRO SERVICE</strong></font></center></html>",JLabel.CENTER),
    		new JLabel("",new ImageIcon("gastro/image/ico1.gif"),0)
    	};
	ok[0].setPreferredSize(new Dimension(20, 25));
	ok[1].setPreferredSize(new Dimension(220, 25));
	ok[2].setPreferredSize(new Dimension(20, 25));*/
	
    menus[0].setMnemonic(KeyEvent.VK_G);	
	menus[1].setMnemonic(KeyEvent.VK_V);
  	menus[2].setMnemonic(KeyEvent.VK_C);
	//java.net.URL imgURL = start03Applett.class.getResource("/gastro/image/ico01.gif");
    //mb.add(new JLabel("",new ImageIcon(imgURL),0));	    
    for(int i = 0; i < menus.length; i++)mb.add(menus[i]);
  	mb.add(syst);		
	//mb.setBackground(Color.orange);  	
	//jtb.setBackground(Color.orange);  	
  
	int inset = 20;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    setBounds(inset, inset,screenSize.width  - inset*2, screenSize.height - inset*2);
	desktop = new JDesktopPane();
	desktop.setBackground(new Color(104,164,100));
	
	//mb.add(ok[0],"East");	
	desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE); 	
	desktop.add(jtb);
	//add(desktop);
	desktop.setPreferredSize(new Dimension(950,700));
	setContentPane(desktop);
   	setJMenuBar(mb);	
	addMouseListener(new PL());		
	//setSize(screenSize.width,screenSize.height);
//desktop.setTitle("EBM-GASTRO-SERVICE 1.04");
	setVisible(true);
//	desktop.setDefaultCloseOperation(0);  	
  }
  public void init() {
        //Execute a job on the event-dispatching thread:
        //creating this applet's GUI.
        try {
            SwingUtilities.invokeAndWait(new Runnable() {
                public void run() {
                    createGUI();
                }
            });
        } catch (Exception e) {
            System.err.println("createGUI didn't successfully complete");
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
			//UIManager.getSystemLookAndFeelClassName()) ;
    	} catch (Exception e) { }
    }
    String mycon(){
		String myc=new com.search.sucheDate("gastro/date/source/config.dat").md();		
		if(myc.equals("alles ist bereit")) System.out.println("bereit "+myc);
		else myc="nochnichtbereit";
		return myc;
	}
 void an(){
  	new ver6.info(1);
  	com.security.lizenc l=new com.security.lizenc();
  	String str=l.test();
    if(str.equals("neue")&& mycon().equals("nochnichtbereit")){new ver6.config();}
    new save().file("gastro/Date/benutzer.dat","Chef",false);
    if(!new com.security.system().aus()) {         
        Runtime.getRuntime().exit(0);     
    }
  }   
  public static void main(String[] args) {
  	 try {
        String vers = System.getProperty("java.version");
        if (vers.compareTo("1.1.2") < 0) {
            System.out.println("!!!WARNING: Swing must be run with a " +
                               "1.1.2 or higher version VM!!!");
        }
        //javax.swing.SwingUtilities.invokeLater(new Runnable() {
        //    public void run() {
                JApplet c = new start03Applett();//createAndShowGUI();
            	c.init();c.start();c.setVisible(true);
        //    }
        //});

        //new com.security.Rambo("reingen");
  	 } catch (Throwable t) {
            System.out.println("uncaught exception: " + t);
            t.printStackTrace();
        }
  }      
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

} ///:~
