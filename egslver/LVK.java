// created on 01.11.2007 at 14:17
package egslver;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.*;
import javax.swing.BorderFactory;
import javax.swing.event.*;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.AbstractButton;
//KlientPanel
public class LVK{	
	JButton[]KButtons,MButtons;
	String[]BF;
	public LVK(){		
		BF=new lvstruck().klbedd();
		KButtons=button(BF);//activiere die Keys					
	}
	JButton[]button(String[]bf){		
		JButton[]s=new JButton[bf.length];
		for(int i=0;i<bf.length;i++){
			s[i]=new JButton("",new Bimage().img(bf[i]));
			s[i].setActionCommand(bf[i]);	
			s[i].setVerticalTextPosition(AbstractButton.CENTER);//BOTTOM);
    		s[i].setHorizontalTextPosition(AbstractButton.CENTER);	
			s[i].addActionListener(new LvAction());
		}
		return s;
	}
	JPanel Panel_B(){
		JPanel pan =Panel();					
		JButton[]s=KButtons;//=new JButton[20];		
		pan.setLayout(new GridLayout(s.length,0));		
		for(int i=0;i<s.length;i++){
			pan.add(s[i]);
		}		
		pan.setBorder(new javax.swing.border.TitledBorder(null,"Klient"));//setBorder(new LineBorder(Color.blue,1,false));
		JPanel Bpan=Panel();	
		Bpan.add(pan,BorderLayout.PAGE_START);
		return Bpan;
	}
	JPanel Panel(){
		JPanel pan = new JPanel();	
		pan.setLayout(new BorderLayout());
		pan.setBorder(new javax.swing.border.TitledBorder(null,""));//setBorder(new LineBorder(null,2,true));
		return pan;
	}	
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
	class LvAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String t = e.getActionCommand();   	   	 
			if(t.equals("Klient Erfassen")){
				new SD();
			}
			if(t.equals("Suchen")){
				String str=new com.options.MyOp().wahl(open("egslv/klient.dat"),"Welche Klient?");
				new LVM(str);
			}
		}
	}	
	public static void main(String[]args){
		new LVK();
	}
}
