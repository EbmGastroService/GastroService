// created on 09.02.2004 at 12:54
// by Mourad El bakry
//eine Panel zum Eifugen von daten
package ebm;
import com.options.MyOp;
import com.units.WG;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.BoxLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.BorderLayout;
//import java.awt.*;
import java.awt.event.*;
import java.awt.Dimension;

public class Einfugen extends JPanel{
	String[] Data;
	JTextField[] Field;// 
	JTextField hw=new JTextField("");
	String[] Tasta={"Save","Abbrechen"};
	JPanel jp ;
	Object op;//OptionIntialValue
	String[] p;
	String[]spaltenString=new WG().getsptext();
	//JOptionPane option;
	/*{
		"Vorspeisen","Pasta","Pizza","Fleisch","Fisch","Eis","Kaffee","Alko-frei","Alko","Spirituse"
	};*/
	 final JComboBox jcb = new JComboBox(spaltenString);		
	int SpWahl=0;
	public void setFelder(String[]str){p=str;}
	public String[] getFelder(){return p;}
	public String[] getData(){return Data;}
	public Einfugen(String[]data){
		Data=data;				
		Field= new JTextField[Data.length];		
		p=new String[Data.length];		       
		p=pleer();			
		//option=new JOptionPane();
		//System.out.println(Data.length);
		if(Data.length>3&&Data.length<6){op=Field[0];jp=inkJbox();}else{op=Field[1];jp=kd();}
	}
	String[] pleer(){for(int i=0;i<p.length;i++)p[i]="";return p;}
	
	public Einfugen(String[]data,String[]db){
		Data=data;		
		p=db;		
		//option=new JOptionPane();
		SpWahl=0;
		Field= new JTextField[Data.length];
		for(int i=0;i<Data.length;i++)p[i]=testchar(""+p[i]);
			//System.out.println(Data.length);
		//if(Data.length>3&&Data.length<6){jp=inkJbox();}else jp=kd();		
		op=Field[1];
		jp=kd();
		
	}
	public Einfugen(String[]Tasta,String[]data,String[]db){
		this.Tasta=Tasta;
		Data=data;		
		p=db;
		//option=new JOptionPane();
		Field= new JTextField[Data.length];
		for(int i=0;i<Data.length;i++)p[i]=testchar(""+p[i]);		
		op=Tasta[0];
		jp=kd();
		
	}
	public JPanel kd(){	
		JPanel jp1=new JPanel();
		jp1.setLayout(new BoxLayout(jp1,BoxLayout.Y_AXIS));		
		//jp1.setBackground(new ebm.myColor("hg").getColor());
		jp1.setPreferredSize(new Dimension(450,Data.length*30));		
	    	
		//if(p!=null)p=getFelder();
		JPanel[] jg =new JPanel[Data.length];	
		if(Data.length>6){		
			jg[0] =new JPanel();jg[0].setLayout(new BoxLayout(jg[0],BoxLayout.X_AXIS));
			Field[0]=new JTextField(p[0]);		
			Field[0].setPreferredSize(new Dimension(180,20));
			Field[0].setFont(new Font("Roman",1,14));
			Field[0].setForeground(Color.blue);
			Field[0].setEditable(false);
			JLabel jl=	new JLabel("<html><b>"+Data[0],JLabel.CENTER);
			jl.setOpaque(true);
			jl.setPreferredSize(new Dimension(100,20));			    
			jl.setBackground(new ebm.myColor("hg").getColor());
	    	jg[0].add(jl);
			jg[0].add(Field[0]);				
			jg[0].setBackground(new ebm.myColor("hg").getColor());
			//jp1.setPreferredSize(new Dimension(400,Data.length*30));		
			jp1.add(jg[0]);		
			
		for(int i=1;i<4;i++){
    		jg[i] =new JPanel();jg[i].setLayout(new BoxLayout(jg[i],BoxLayout.X_AXIS));
			Field[i]=new JTextField(p[i]);		
			Field[i].setPreferredSize(new Dimension(180,20));
			Field[i].setFont(new Font("Roman",1,14));
			Field[i].setForeground(Color.blue);
			jl=	new JLabel("<html><b>"+Data[i],JLabel.CENTER);
			jl.setOpaque(true);
			jl.setPreferredSize(new Dimension(100,20));		
			jl.setBackground(new ebm.myColor("hg").getColor());
			jg[i].setBackground(new ebm.myColor("hg").getColor());	
	    	jg[i].add(jl);			
			jg[i].add(Field[i]);
			jp1.add(jg[i]);		
		}
		jg[4] =new JPanel();
		jg[4].setLayout(new BoxLayout(jg[4],BoxLayout.X_AXIS));
		jg[4].setBackground(new ebm.myColor("hg").getColor());	
		Field[4]=new JTextField(p[4]);		
		Field[4].setPreferredSize(new Dimension(180,20));	
		Field[4].setFont(new Font("Roman",1,14));
		Field[4].setForeground(Color.blue);	
		Field[4].setBackground(Color.yellow);		
		jl=	new JLabel("<html><b>"+Data[4],JLabel.CENTER);
		jl.setOpaque(true);	
		jl.setPreferredSize(new Dimension(100,20));			    		
		jl.setBackground(new ebm.myColor("hg").getColor());	
	    jg[4].add(jl);
		//jg[4].setForeground(Color.red);
		jg[4].setBackground(new ebm.myColor("hg").getColor());	
		jg[4].add(Field[4]);						
		jp1.add(jg[4]);		
		for(int i=5;i<Data.length;i++){
    		jg[i] =new JPanel();jg[i].setLayout(new BoxLayout(jg[i],BoxLayout.X_AXIS));
			Field[i]=new JTextField(p[i]);		
			Field[i].setPreferredSize(new Dimension(180,20));
			Field[i].setFont(new Font("Roman",1,14));
			Field[i].setForeground(Color.blue);
			jl=	new JLabel("<html><b>"+Data[i],JLabel.CENTER);
			jl.setOpaque(true);
			jl.setPreferredSize(new Dimension(100,20));			    	
			jl.setBackground(new ebm.myColor("hg").getColor());
			jg[i].setBackground(new ebm.myColor("hg").getColor());
	    	jg[i].add(jl);
			jg[i].add(Field[i]);				
			jp1.add(jg[i]);				
		}
		}else{
			for(int i=0;i<Data.length;i++){
    		jg[i] =new JPanel();jg[i].setLayout(new BoxLayout(jg[i],BoxLayout.X_AXIS));
			Field[i]=new JTextField(p[i]);		
			Field[i].setPreferredSize(new Dimension(180,20));	
			Field[i].setFont(new Font("Roman",1,14));
			Field[i].setForeground(Color.blue);
			JLabel jl=	new JLabel("<html><b>"+Data[i],JLabel.CENTER);
			jl.setOpaque(true);
			jl.setPreferredSize(new Dimension(100,20));		
			jl.setBackground(new ebm.myColor("hg").getColor());
			jg[i].setBackground(new ebm.myColor("hg").getColor());	
	    	jg[i].add(jl);			
			jg[i].add(Field[i]);							
	    	jp1.add(jg[i]);		
			}	
		}		
		//op=""+Field[1];
		//1.setInitialValue(Field[0]);		
		jp1.setVisible(true);		
		return jp1;
	}
	public JPanel inkJbox(){
	  JPanel[] jg =new JPanel[Data.length];	
	  JPanel jp1=new JPanel();
	  jp1.setLayout(new BoxLayout(jp1,BoxLayout.Y_AXIS));		
	  //jp1.setBackground(new ebm.myColor("hg").getColor());
	  jp1.setPreferredSize(new Dimension(450,Data.length*30));
	    		
		int i=0;
	  	for(i=0;i<Data.length-1;i++){
    		jg[i] =new JPanel();
	  		jg[i].setLayout(new BoxLayout(jg[i],BoxLayout.X_AXIS));
			Field[i]=new JTextField(p[i]);		
	  		Field[i].setPreferredSize(new Dimension(180,20));
	  		Field[i].setFont(new Font("Roman",1,14));
			Field[i].setForeground(Color.blue);
	  		JLabel jl=	new JLabel("<html><b>"+Data[i],JLabel.CENTER);
	  		jl.setOpaque(true);
			jl.setPreferredSize(new Dimension(100,20));		
	  		jl.setBackground(new ebm.myColor("hg").getColor());
	  		jg[i].setBackground(new ebm.myColor("hg").getColor());
	    	jg[i].add(jl);
			jg[i].add(Field[i]);				
	  		//jp1.setBackground(new ebm.myColor("hg").getColor());	
	    	jp1.add(jg[i]);		
	  }
	  //i++;
	  jg[i] =new JPanel();
		jg[i].setLayout(new BoxLayout(jg[i],BoxLayout.X_AXIS));
		jcb.addActionListener(new CB());
		jg[i].add(jcb,BorderLayout.WEST);
		Field[i]=new JTextField(""+SpWahl);
		Field[i].setFont(new Font("Roman",1,14));
		Field[i].setForeground(Color.blue);
		Field[i].setEditable(false);
		jg[i].setBackground(new ebm.myColor("hg").getColor());
		jg[i].add(Field[i],BorderLayout.EAST);
		jp1.add(jg[i]);	
		jp1.setVisible(true);		
	  return jp1;
	}
	public int Panel(){				
		jp.setOpaque(true);
		jp.setBackground(new ebm.myColor("hg").getColor());
			int erg=new com.options.MyOp().panel(Tasta,"Daten Einlegen",jp);
		//int erg=JOptionPane.showOptionDialog(null,jp, "Daten Einlegen", JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,null, Tasta, op);			
		if(erg==0){
			for(int i=0;i<Data.length;i++)p[i]=""+Field[i].getText();			
		}else erg=-1;
		return erg;
	}
	public String testchar(String str){
		int i=1;
		if(str.length()>1){			
		 	char c=str.charAt(0);
			if(str.charAt(0)==' '){c=str.charAt(1);i=2;}
		 	Character ch=new Character(c);
		 	String ein="";
			if(ch.isLetter(c)){    
        		if(ch.isLowerCase(c)){
        			c=ch.toUpperCase(c);	ein=c+str.substring(i);str="";str=ein;
        		}
			}		 
		}
		return str;
	}
	public String[] werte(){		
		Panel();		
		if(p.length>6){			
				if(p[4].length()>0){
					p[4]=new com.search.sucheDate("gastro/date/Kliste.dat").codgen(""+p[4].trim());
					Field[0].setText(""+p[4]);						
				}else {										
					String str="<Html><font color=red size=3>"+
				       "<br>Postleitzahl fehlt!"+
				       " Es ist notwendig um<br> automatischen <u>Codegenerator</u> zu bilden"+
					   "<br>Vielen Dank</font>"+
					   "<hr><br><font color=blue size=2>Wollen Sie abbrechen!&nbsp; geben Sie Postleitzahl"+
					   "<br>ein&nbsp;und&nbsp; [Save]&nbsp;drucken&nbsp;dann&nbsp;"+
					   " [Nein]&nbsp; drucken</font>";
					int erg=new com.options.MyOp().Frage(str);
					if(erg==0)werte();else p=null;
				}  					
		}				
		if(p!=null){
		for(int i=0;i<Data.length;i++){
				p[i]=testchar(""+Field[i].getText()); 						
				p[i]=p[i].replace(',',' ');
				if(p[i].length()==0)p[i]="..";
		}
		}
		
		return p;
	}
	String istLeer(){		
		String message="";
		for(int i=0;i<Data.length;i++){
			p[i]=testchar(""+Field[i].getText());
			if(p[i].length()<=0){
				message+="<br>Zeile:"+i+" ist leer!";
				op=Field[i];			
			}
		}
		return message;
	}
	public String[]inhalt(){		
		String message="<Html><font color=red>Ihre Eingabe ist Fehlerhaft</font><br>";	
		String leer="";
		Panel();
		while(istLeer().length()>0){			
			leer=istLeer();
			new MyOp().fehler(message+"<font color=blue>"+leer+"<br><hr>Bitte korregieren!</font>");			
			if(Panel()<0){p=null;break;}
		}
		return p;
	}
	class CB implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			int wahl=jcb.getSelectedIndex();
			SpWahl=wahl;Field[Data.length-1].setText(""+SpWahl);
			validate();
       }
	}

public static void main(String[] args) {
	String[]r={"Code","Bez","Preis","Spalte"};
	String[]l={"Code","Name","VName","Adr","PLZ","Ort","FTel..","Mobil..."};
		String[]taste={"Ok","Bearbeiten"};
	//Einfugen eif=new Einfugen(taste,r,r);
	Einfugen eif=new Einfugen(l);
	//String[]str=eif.inhalt();
	String[]str=eif.werte();
	for(int i=0;i<str.length;i++)System.out.println(str[i]);
    //new com.display.Console.run(eif, 650, 500);   
  }

}
