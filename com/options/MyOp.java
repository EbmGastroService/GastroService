package com.options;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class MyOp{
//JPanel jp=new JPanel();
JOptionPane optionPane;
	String title="";
	public MyOp(){
		optionPane=new JOptionPane();	
		optionPane.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent e) {
                String prop = e.getPropertyName();
                if (optionPane.isVisible()&&(e.getSource()== optionPane)&&(prop.equals(JOptionPane.VALUE_PROPERTY)||prop.equals(JOptionPane.INPUT_VALUE_PROPERTY))){
                    Object value = optionPane.getValue();
                	if (value == JOptionPane.UNINITIALIZED_VALUE) {
                        //ignore reset
                        return;
                    }
                    // Reset the JOptionPane's value.
                    // If you don't do this, then if the user
                    // presses the same button next time, no
                    // property change event will be fired.
                    optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);
                }
            }
		});	
		title="Entscheidung ";
	}
	public void fehler(String str){
		title+="Aufgepasst!";
  		String nstr="<Html><font size=+1 color=red face='Courier New'><b>"+str;
		optionPane.showMessageDialog(null,nstr,title,JOptionPane.PLAIN_MESSAGE );
	}
	public String fehler1(String str){
		optionPane.showMessageDialog(null,str, "Hinweis", JOptionPane.PLAIN_MESSAGE);
		return str;
	}
	public String wahl(Object[] selec, String txt){
		Object[] selections = new Object[selec.length];
		selections = selec;
		txt=txt+" aus "+selec.length+" Ihr Wahl Bitte";
		String val =(String)optionPane.showInputDialog(null, txt, "Wahl eingabe Dialog",JOptionPane.INFORMATION_MESSAGE,null,selections, selections[0]);
		if(val == null||val.length()<0) val=" Leider nicht Vorhanden";
		return(val);
	}
  public int Frage(String str){
  	Object[] options = { "Ja", "Nein" };
  	title="Achtung!";
  	int sel = optionPane.showOptionDialog(null, str, title,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, null, options, options[0]);
  	if(sel == JOptionPane.CLOSED_OPTION)sel=1;
  	return(sel);
  }
  public int chek(String str){  	
  	if (str==null||str=="")return -1;else return 1;
  }
  public String Controll(String str){  	  	
    if(chek(str)<0){
  	    if(Frage("<html><font color=red>Sie sollten Wert Eingeben<br>Nochmal versuchen!</font>")==0)
  	    	str="-";else str=null;
    }
    return str;
  }
  public String eingabe(String str){
    String val=""+optionPane.showInputDialog(str);  	
  	if(chek(val)<0){ 
  		if(Frage("<html><font color=red>"+
  		         "Sie sollten Wert Eingeben"+
  		         "<br>Nochmal versuchen!</font>")==0)
  		         val=eingabe(str);
  	}  	  	
  	return val;
  }
  public String eingabeMenge(String str){         
        String val=""+optionPane.showInputDialog(str+ " <font color=black size=+1>Wenn nur 1 Mal [ok] drucken");
  	//	optionPane.setFont(new Font("",1,20));
  		if(chek(val)<0){ 
  			if(Frage("Sie sollten Wert Eingeben\nNochmal versuchen!")==0)val=eingabeMenge(str);
  		}
  		int z=0;  	
      	try{z = Integer.parseInt(val);}catch(Exception e){z=0;}  			  			  		
  	 if(val=="")val=""+z;
    return(val);
  }
  public int frageO(String str, Object[] options){      	
  	//tf.setText(str);
  	//jp=mp();
  	//jp.add(tf);
  	String nstr="<Html><font size=+1 color=red face='Courier New'>"+str;
  	    int sel = optionPane.showOptionDialog(null, nstr, "Zur Entscheidung!", 
          JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, null, options, options[0]);  
  	if(sel == JOptionPane.CLOSED_OPTION)sel=-1;   
    return(sel);
  }
   public int frage1(String str, String[] option){    
  	Object[] options = new Object[option.length];
   	options = option;
   	int sel = optionPane.showOptionDialog(null, str, "Entscheidung Eingabe! ",JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
    return(sel);
  }
   public int frage(String str,Object oa,Object ob){
   	Object[] options = { oa, ob };
  	//tf.setText(str);
   	//jp=mp();
  	//jp.add(tf);	
   	String nstr="<Html><font size=+1 color=blue face='Courier New'>"+str;
   	int sel = optionPane.showOptionDialog(null, nstr, "Frage",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, null, options, options[0]);
   	return(sel);
  }
   public int kundenfrage(String str,JPanel p,String[]option){
    Object[] options = new Object[option.length];
   	options = option;
   	int sel = optionPane.showOptionDialog(null, p, "kunden Abfrage",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, null, options, options[0]);
   	if(sel == JOptionPane.CLOSED_OPTION)sel=-1;
   	return(sel);
  }
  public int panel(String str,JPanel p){
  	Object[] options ={"Ja","Nein","Abbrechen"};
  	return optionPane.showOptionDialog(null, p, "Abfrage",JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, null, options, options[0]);   		
  }  
  public int panel(Object[]options,String str,JPanel p){
  	//Object[] options ={"Ja","Nein","Abbrechen"};
  	return optionPane.showOptionDialog(null, p, str,JOptionPane.DEFAULT_OPTION,JOptionPane.WARNING_MESSAGE, null, options, options[0]);   		
  }  
}
