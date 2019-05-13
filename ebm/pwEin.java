// created on 19.09.2007 at 16:50
// Mourad Elbakry
package ebm;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
public class pwEin{
	char[]c;
	public static boolean AA=true;
	JFormattedTextField[] f;
	JPanel jp ;
	String[] Tasta={"Save","Abbrechen"};
	com.options.frame ff;
	public String p="..";
	String LizEin;
	public pwEin(){	
		LizEin="Password Eingeben";
		ff=new com.options.frame("Password eingabe | closed in "+(3)+" min.",180);
		genPanel();
	//	Panel();
	//	zeigeMe();				
	}
	public pwEin(String strein){		
		LizEin=strein;
		ff=new com.options.frame("Password eingabe | closed in "+(3)+" min.",180);
		genPanel();
	}
	protected static ImageIcon getFDImage(int m) {
		String str="";
		if(m==1)str="hor2.gif";
		if(m==2)str="vertical.gif";
		if(m==3)str="ebmeingang.gif";
        java.net.URL imgURL = pwEin.class.getResource("/image/"+str);
        if (imgURL != null) {
            return new ImageIcon(imgURL);//.getImage();
        } else {
            return null;
        }
    }
	void genPanel(){
		jp=new JPanel();
		jp.setLayout(new BoxLayout(jp,BoxLayout.X_AXIS));	
		//mjp.setOpaque(true);new java.awt.GridLayout());	
		jp.setPreferredSize(new java.awt.Dimension(280,20));
		f=new JFormattedTextField[5];
		for(int i=0;i<f.length-1;i++){
			f[i]=new JFormattedTextField(createFormatter("AAAA"));
			//f[i].setColumns(4);
			//f[i].setPreferredSize(new java.awt.Dimension(30,20));
			jp.add(f[i]);
			jp.add(new JLabel(" "));
		}
		int i=f.length-1;
		f[i]=new JFormattedTextField(createFormatter("AAAA"));
		//f[i].setPreferredSize(new java.awt.Dimension(30,20));
		//f[i].setColumns(4);
		jp.add(f[i]);		
	}
	protected MaskFormatter createFormatter(String s) {
    MaskFormatter formatter = null;
    try {
        formatter = new MaskFormatter(s);
    } catch (java.text.ParseException exc) {
        System.err.println("formatter is bad: " + exc.getMessage());
        System.exit(-1);
    }
    return formatter;
}

	public String Panel(){		
				String pp="";
		int erg=JOptionPane.showOptionDialog(null,die(), "Password Eingabe",
	   JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
	   null, Tasta, Tasta[0]);			
		if(erg==0){	
			for(int i=0;i<f.length-1;i++){
				pp+=f[i].getText()+"-";
			}			
			pp+=f[f.length-1].getText();
		}
		setP(pp);
		System.out.println("Ihre eingabe: "+pp);
		return pp;
	}
	 class okAction implements java.awt.event.ActionListener {
        public void actionPerformed (java.awt.event.ActionEvent e ) {        	
        	String fr = e.getActionCommand();        	
        	if(fr.equals("Ok")){
        		String pp="";
        		for(int i=0;i<f.length-1;i++){
				pp+=f[i].getText()+"-";
        		}
        		pp+=f[f.length-1].getText();
        		setP(pp);
        		System.out.println("Ihre eingabe: "+p);
        		if(pp.length()>=24)ff.dispose();
        	}else if(fr.equals("Abbrechen"))ff.dispose();
        }
	}
	JPanel die(){
		String[]str={"Ebm Gastro Service 2010","",
		"Bitte geben Sie Ihre Passworte ein",
		"Was Sie als Key bekommen haben.",
		"jeder Code bitte genau eingeben","","Besuchen Sie uns online www.ilone.at",
		"Hertzlichen Dank      ",
		"   viel Erfolg!!"};
		JLabel jl=new ver6.img().label(str,"logo.gif",450,300);//new JLabel(getFDImage(3),0);		
		//jl.setPreferredSize(new java.awt.Dimension(600,400));
		JPanel mjp=new JPanel();
		mjp.setLayout(new BoxLayout(mjp,BoxLayout.Y_AXIS));//java.awt.FlowLayout());//BoxLayout(mjp,BoxLayout.Y_AXIS));
		mjp.setOpaque(true);
		mjp.setBackground(new ebm.myColor("hg").getColor());		
		mjp.add(jl);
		mjp.add(new javax.swing.JSeparator(javax.swing.JSeparator.VERTICAL),java.awt.BorderLayout.LINE_START);
		//JLabel jl0=new JLabel("<html><hr width=350></hr><br>"+getP());		
		//jl0.setOpaque(true);
		//jl0.setBackground(new ebm.myColor("hg").getColor());						
		JPanel jph=new JPanel();
		jph.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));
		//jph.setPreferredSize(new java.awt.Dimension(100,60));
		jph.setBorder(new javax.swing.border.TitledBorder(null,LizEin));
		jph.setOpaque(true);
		jph.setBackground(new ebm.myColor("hg").getColor());
		jph.add(jp);		
		
		mjp.add(jph);		
		return mjp;
	}
	JPanel dieB(){
		JPanel mjp=die();
		JPanel jph=new JPanel();
		jph.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.TRAILING));
		jph.setOpaque(true);
		jph.setBackground(new ebm.myColor("hg").getColor());
		JButton button=new JButton("Ok");
		JButton button1=new JButton("Abbrechen");
		button.addActionListener(new okAction());
		button1.addActionListener(new okAction());
		jph.add(button);
		jph.add(button1);
		mjp.add(jph);
		return mjp;
		
	}
	public String getP(){return p;}
	void setP(String str){p=str;}
	void zeigeMe(){	
		java.awt.Dimension s = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		JPanel dp=dieB();
		dp.setPreferredSize(new java.awt.Dimension(580,400));
        ff.setContentPane(dp); 
		ff.setLocation(s.width,s.height);		
		ff.setVisible(true);	
		javax.swing.JWindow w = new javax.swing.JWindow(ff);
		w.setContentPane(dp); 		
		w.pack();			
		w.setLocation((s.width/2)-200,(s.height/5)-100);
		w.setVisible(true);	
	}
	public static void main(String[] args) {
		pwEin pw=new pwEin();
		System.out.println("Constactor.. Eingabe: "+pw.Panel());
			pw.zeigeMe();				
				System.out.println("Constactor.. Eingabe: "+pw.p);
	}
	
}
