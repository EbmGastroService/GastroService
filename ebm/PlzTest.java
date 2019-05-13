// created on 22.09.2009 at 20:05
// by Mourad El bakry
//Postleitzahl ComboBox
package ebm;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JOptionPane;
import javax.swing.border.*;
import com.options.ausTeilen;
import javax.swing.ComboBoxModel;
public class PlzTest{
	String[]PlzString;
	String Wahl;
	JOptionPane optionPane=new JOptionPane();
	public PlzTest(){
		wahl(Plz(),"Ihre Eingabe Bitte!");	
		
		//setLayout(new BorderLayout());
		//add(new PlzBox(PlzString));
		
		update();
	}
	String[]Plz(){		
		String[]estr=open();
		if(estr.length<1){
			String[]str={"2514,Traiskirchen","2513,Möllersdorf","2521,Trumau","2512,Oyenhausen","2352,Guntramersdorf"};
			save(str,false);return str;
		}else return estr;
	}	
	String[]open(){
 		return new com.search.sucheDate("gastro/date/plz.dat").myDaten();
 	}
 	void save(String[]date,boolean wie){
 		new com.units.save().dontsort("gastro/date/plz.dat",date,wie);
 	}
 	void save(String date,boolean wie){
 		new com.units.save().file("gastro/date/plz.dat",date,wie);
 	}
 	void update(){ 		 		
 		Wahl=new com.search.sucheDate("gastro/date/plzeingabe.dat").md();
 		int i=I(new com.search.sucheDate("gastro/date/plzwahl.dat").md()); 		
 	
 		System.out.println("<Zeile>:"+i+"||"+Wahl+"....>>>");
 		if(i<0){ 			
 			save(Wahl,true); 		 		
 		}
 	}
 	int I(String str){
		int n=0;
		try{
			n=Integer.parseInt(str);
		}catch(Exception ne){n=0;}
		return n;
	}
	 public String wahl(Object[] selec, String txt){
      Object[] selections = new Object[selec.length];
      selections = selec;
      txt=txt+" aus "+selec.length+" Ihr Wahl Bitte";
      Object val = optionPane.showInputDialog(null, txt, "Wahl eingabe Dialog",
          		   JOptionPane.INFORMATION_MESSAGE,
          		   null, selections, selections[0]);
      //if(val == null) val=" Leider nicht Vorhanden";
   return(val.toString());
  }

	 public static void main(String[] args) {new PlzTest();};
/*	void option(){
	JOptionPane pane = new JOptionPane ("Argumente"); 
      pane.set. Xxxx (...); / / Configure
     JDialog dialog = pane.createDialog (parentComponent Titel); 
      dialog.show (); 
      Object selectedValue = pane.getValue (); 
      if (selectedValue == null) 
        Rückkehr CLOSED_OPTION;
     / / Wenn es nicht eine Reihe von Optionsfelder:
     if (options == null) ( 
        if (selectedValue instanceof Integer) 
           return ((Integer) selectedValue). intValue (); 
        Rückkehr CLOSED_OPTION; 
      )
     / / Wenn es eine Reihe von Optionsfelder:
     for (int counter = 0, maxCounter = options.length; 
         counter <maxCounter; counter + +) ( 
         if (options [counter]. equals (selectedValue)) 
         return counter; 
      ) 
      Rückkehr CLOSED_OPTION;
	}*/


}
