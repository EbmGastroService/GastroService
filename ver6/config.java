// created on 25.09.2006 at 01:16
//Author Mourad Elbakry
package ver6;
import javax.swing.*;
import javax.swing.JOptionPane;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import com.units.save;
import javax.swing.border.*;
//import java.awt.ActionListener;
public class config{
	String Test="false";
	JTextField[]firma={new JTextField("Ristorante Pizzeria a Mori Mio"),
	new JTextField("xxxx YYYY, xxxxxx xx xx"),
	new JTextField("+xx xxxx xxxxxx"),	
	new JTextField("ATUxxxxxxxx")};
	JTextField[]warengruppen={
		new JTextField("Vorspeisen"),new JTextField("Pasta"),new JTextField("Pizza"),
		new JTextField("Fleisch"),new JTextField("Fisch"),new JTextField("Desert"),
		new JTextField("Kaffee"),new JTextField("Alko-Frei"),
		new JTextField("Alko"),new JTextField("Spirti/Service")};
	JTextField[]werbetext={new JTextField("Angebot des Tages"),
	new JTextField("Jeder Pizza beim Abhollung nur 5,10")};
	JTextField[]steuersatz={new JTextField("10%"),new JTextField("20%"),new JTextField("0%")};
	JTextField opened=new JTextField(" Mo-Fr 11-14Uhr & von 17-23Uhr, Sa & So & Feirtagen 11-23Uhr");
	JTextField wishyou=new JTextField("Vielen Dank und guten Appetit");
	JTextField[]papiert={new JTextField("A5 148 x 210 mm"),new JTextField("A4 297 x 210 mm")};
	JTextField[]papierf={new JTextField("0.7"),new JTextField("1.0")};	
	public String zoom="0.40";
	public config(){
		init();
		safty();
	}
	void safty(){
		new ver6.Rb().slid();  
		new ver6.Rb().ok();
		new ver6.Rb().color_ok();
		new ver6.Rb().menus();
		new ver6.Rb().menuitems();
		new ver6.Rb().rc();
		new ver6.Rb().kd_m();
		new ver6.Rb().kd_mi();
		new ver6.Rb().kd();
  		new ver6.Rb().opkd();
	}
	JLabel label(String str){
		JLabel jl=new JLabel(str);
		jl.setPreferredSize(new Dimension(120,10));
		return jl;
	}	
	JPanel jpanel(JLabel jl,JTextField tf){		
		JPanel mypanel=new JPanel();
		mypanel.setLayout(new BoxLayout(mypanel,BoxLayout.X_AXIS));				
		mypanel.add(jl);mypanel.add(tf);
		return mypanel;
	}
	JPanel hr(String str){
		Border redline = BorderFactory.createLineBorder(Color.blue);
		JPanel mypanel=new JPanel();
		mypanel.setLayout(new BorderLayout());
		JLabel jl=new JLabel(str+"<br>. ",JLabel.CENTER);
		jl.setOpaque(true);
		jl.setBackground(new Color(174,165,174));
		//jl.setPreferredSize(new Dimension(650,5));
		mypanel.setBorder(redline);
		mypanel.add(jl);
		return mypanel;
	}
	public void init(){
		//setTitle("EbmGastroService2006|Einstellung System");
		//new com.printer.papier().fixpapier();
		JPanel mypanel=new JPanel();		
		mypanel.setLayout(new BoxLayout(mypanel,BoxLayout.Y_AXIS));		
		JLabel jl=new JLabel("<html><br><font size=2> um ihre Erfassung ordnunggem&auml;ss<br> durch f&uuml;hren"+
		                       " zu k&ouml;nnen wird darum gebetten folgeneden daten zuver&auml;dnern"+
		                       " Es ist voreingestellt f&uuml;r Gastronomie, aber Sie k&ouml;nnen es f&uuml;r"+
		                       " ZB. eine Reiseb&uuml;ro um&auml;ndern, die Mehrwert Steuer sind f&uuml;r &Ouml;sterreich"+
		                       " Die Warengruppen sollen ebenfalls auf ihre umgestellt<hr>");
		jl.setPreferredSize(new Dimension(650,80));
		//mypanel.add(jl);
		mypanel.add(hr("<html><font color=blue>Ihre Firmendaten<font size=2> scheint im Rechnungsausdruck"));
		open(firma,"gastro/source/fr.cfg");
		mypanel.add(jpanel(label(" Bezeichnung"),firma[0]));			
		mypanel.add(jpanel(label(" Adresse"),firma[1]));				
		mypanel.add(jpanel(label(" Telefon"),firma[2]));			
		mypanel.add(jpanel(label(" UID"),firma[3]));			
		//mypanel.add(hr());
		mypanel.add(hr("<html><font color=blue>Ihre Werbungstext<font size=2> scheint im Rechnungsausdruck"));
		open(werbetext,"gastro/source/wt.cfg");
		for(int i=0;i<werbetext.length;i++){
			mypanel.add(jpanel(label(" "+(i+1)+"te Werbungzeile"),werbetext[i]));			
		}
	
		mypanel.add(hr("<html><font color=blue>Ihre &Ouml;ffnungzeiten<font size=2> scheint im Rechnungsausdruck"));
		open(opened,"gastro/source/op.cfg");
		mypanel.add(jpanel(label(" von bis"),opened));			
	
		mypanel.add(hr("<html><font color=blue>Ihre dank und W&uuml;nschzeile<font size=2> scheint im Rechnungsausdruck"));
		open(wishyou,"gastro/source/wy.cfg");
		mypanel.add(jpanel(label(" ihre Dank "),wishyou));			
		
		mypanel.add(hr("<html><font color=blue>Mehrwert Steuersatz<font size=2> interne Berechnung"));
		JPanel panel=new JPanel();
		panel.setLayout(new GridLayout(1,2));						
		open(steuersatz,"gastro/source/stsatz.cfg");
		for(int i=0;i<steuersatz.length;i++){
			panel.add(jpanel(label(" "+(i+1)+" te Steuersatz"),steuersatz[i]));			
		}
		mypanel.add(panel);
		mypanel.add(hr("<html><font color=blue>Druckerpapier<font size=2> interne Berechnung"));
		panel=new JPanel();
		panel.setLayout(new GridLayout(3,2));
		open();openzoom();
		//for(int i=0;i<papier.length;i++){
		panel.add(jpanel(label(" "+" A5 "),papiert[0]));panel.add(papierf[0]);
		panel.add(jpanel(label(" "+" A4 "),papiert[1]));panel.add(papierf[1]);
		panel.add(new JLabel(" "+" Zoom bis Bonnformat 20%"));panel.add(new ebm.zoom(zoom));			
		//}
		mypanel.add(panel);
		mypanel.add(hr("<html><font color=blue>Warengruppen<font size=2> interne Berechnung"));
		panel=new JPanel();
		panel.setLayout(new GridLayout(4,4));						
		open(warengruppen,"gastro/source/wg.cfg");
		for(int i=0;i<warengruppen.length;i++){			
			panel.add(jpanel(label(" Gruppe ("+(i+1)+")"),warengruppen[i]));			
			//panel.add(textfeld("?%"));
		}
		mypanel.add(panel);
		panel=new JPanel(new BorderLayout());
		panel.add(jl,"Center");		
		JPanel console=new JPanel();
		console.setLayout(new BorderLayout());		
		console.add(panel,BorderLayout.PAGE_START);		
		console.add(mypanel,BorderLayout.PAGE_END);
		console.setVisible(true);
		Object[]options={"Speichern","Abbrechen"};
		int erg=JOptionPane.showOptionDialog(null,console, "EbmGastroService2006|Einstellung System",
	   JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,null,options, options[0]);		
		if(erg == 0){
			save();
		}else{
			new com.options.MyOp().fehler1("<html><font size=4 color=red>"+
    		 "Aktuelle Einstellung wird gespeichert!</font><br>"+
    		 "Sie k&ouml;nnen Einstellung jederzeit im [Menue]+(System) abrufen!");
			save();
		}
	}	
	String[]data(JTextField[]tf){
		String[]str = new String[tf.length];
		for(int i=0;i<tf.length;i++){
			str[i]=tf[i].getText();
			//System.out.println(str[i]);
		}
		return str;
	}
	String data(JTextField tf){
		String str=tf.getText();
		//System.out.println(str);
		return str;
	}
	String[]data(){
		String[]str = new String[papierf.length];
		for(int i=0;i<str.length;i++){
			str[i]=papiert[i].getText()+","+papierf[i].getText();
			//System.out.println(str[i]);
		}
		return str;
	}
	void open(){		
		String[]daten=new com.search.sucheDate("gastro/source/pf1.cfg").myDaten();
		if(daten!=null){
			for(int i=0;i<daten.length;i++){
				String[]wort=new com.options.ausTeilen().koma(daten[i]);
				papiert[i].setText(wort[0]);
				papierf[i].setText(wort[1]);				
			}
		}else new save().dontsort("gastro/source/pf1.cfg",data(),false);				
	}
	void openzoom(){
		String data=new com.search.sucheDate("gastro/source/zoom.cfg").md();
		if(data.length()>0)zoom=data;
		else{
			new save().file("gastro/source/zoom.cfg",zoom,false);
		} 
	}
	void open(JTextField[]jt,String file){		
		String[]daten=new com.search.sucheDate(file).myDaten();
		if(daten!=null){
			for(int i=0;i<daten.length;i++)jt[i].setText(daten[i]);
		}		
	}
	void open(JTextField jt,String file){		
		String daten=new com.search.sucheDate(file).md();
		if(daten!=null && daten!=""){
			jt.setText(daten);
		}		
	}
	void save(){
		String[]str=data(firma);
		new save().dontsort("gastro/source/fr.cfg",str,false);
		str=data(werbetext);
		new save().dontsort("gastro/source/wt.cfg",str,false);
		str=data(steuersatz);
		new save().dontsort("gastro/source/stsatz.cfg",str,false);
		str=data();
		new save().dontsort("gastro/source/pf1.cfg",str,false);		
		//new save().dontsort("gastro/source/pf1.cfg",str,false);		
		str=data(warengruppen);
		new save().dontsort("gastro/source/wg.cfg",str,false);
		String str1=data(wishyou);
		new save().file("gastro/source/wy.cfg",str1,false);
		str1=data(opened);
		new save().file("gastro/source/op.cfg",str1,false);
		new save().file("gastro/date/source/config.dat","alles ist bereit",false);		
	}

	public static void main(String[]args){
		new config().init();
	}
}
