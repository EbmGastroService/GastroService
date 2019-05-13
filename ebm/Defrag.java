// created on 19.02.2010 at 21:15
package ebm;
public class Defrag{
	public static javax.swing.JEditorPane jta1;
	public static String zeile;
	public Defrag(){
		zeile="<html><body bgcolor=#fffffc><center><font color=blue size=3>"+
		"<h1>Ebm Gastro Service 2010</h1><h3> End Protocoll von Heute:</h3>"+
		"<br><font color=red><b>Bitte Warten Sie!!<br>Das System endet von selbst!!<br></b>"+
		"<br><font color=blue> Vielen Dank und auf wieder sehen<br><br><b> El Bakry M.</b></center><font color=#000000>";;
		jta1=new javax.swing.JEditorPane("text/html",zeile);
		jta1.setEditable(false);
		
	}		 
  public void update(){  	
  	zeile+="<br><u>Heute wird updatet 0000 bitte warten</u>";
  	String adatum=new com.units.myDatum().ist_my();
  	String[]fahre=new com.search.sucheDate("gastro/date/benutzer.dat").myDaten(); 
  	for(int i=0;i<fahre.length;i++)
  	new ebm.FL().update(fahre[i]);  	
  	new ebm.SK(1);new ebm.SK(2);  	
  	zeile+="<br>fl: "+new ebm.update("/","gastro/date/fliste.dat","gastro","fl",adatum).myZeile;
  	zeile+="<br>kb: "+new ebm.update("/","gastro/date/kbuch.dat","gastro","kb",adatum).myZeile;
  	zeile+="<br>ko: "+new ebm.update("N","gastro/date/kon.dat","gastro","ko",adatum).myZeile;  	
  	jta1.setText(jta1.getText()+zeile);  	
  }
  public void update(int y){  	
  	zeile+="<br><u>Heute wird updatet 1111 bitte warten</u>";
  	String[]fahre=new com.search.sucheDate("gastro/date/benutzer.dat").myDaten(); 
  	for(int i=0;i<fahre.length;i++)
  	new ebm.FL().update(fahre[i]);  	
  	new ebm.SK(1);new ebm.SK(2);  	
  	zeile+="<br>fl: "+new ebm.update("/","gastro/date/fliste.dat","gastro","fl").myZeile;
  	zeile+="<br>kb: "+new ebm.update("/","gastro/date/kbuch.dat","gastro","kb").myZeile;
  	zeile+="<br>ko: "+new ebm.update("N","gastro/date/kon.dat","gastro","ko").myZeile;
  	jta1.setText(jta1.getText()+zeile);  	
  }
  public void zeigeUpdate(){   	  	
  	jta1.setText(jta1.getText()+zeile);
  	com.options.frame f=new com.options.frame("Protocoll will be closed in "+(180/60)+" min",180);
  	f.setLayout(new java.awt.BorderLayout());	
  	f.setCursor( java.awt.Cursor.getPredefinedCursor( java.awt.Cursor.WAIT_CURSOR));
	java.awt.Dimension s = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
    javax.swing.JScrollPane js1=new javax.swing.JScrollPane();
  	js1.setViewportView(jta1);  	  	
  	javax.swing.JPanel jph=new javax.swing.JPanel();	
  	jph.setLayout(new javax.swing.BoxLayout(jph,javax.swing.BoxLayout.X_AXIS));	
  	javax.swing.JLabel jpl=new javax.swing.JLabel("	",getImg(1),2);	
  	jph.add(jpl);
	jph.setBorder(new javax.swing.border.TitledBorder(null,"Protocoll erstellen"));
	jph.add(js1);
   	f.setContentPane(jph);   
  	f.setLocation(s.width,s.height);
  	f.setVisible(true);
  	javax.swing.JWindow w = new javax.swing.JWindow(f);
  	w.setContentPane(jph);
  	//w.pack();
  	w.setSize(400,300);
  	w.setLocation((s.width/2)-200,(s.height/5)-100);
  	w.setVisible(true);  	
  }   
  public String vorMonat(){  	
  	String sd=new com.units.myDatum().M();  	
  	String svm=sd.substring(0,2);
  	String svj=sd.substring(3,sd.length());
  	int vm=intcode(svm)-1;
  	int vj=intcode(svj);
  	String strvm="";  	
  	if(vm==0){
  		vm=12;vj=vj-1;
  		new ebm.Trans();
  	}
  	if(vm<10)strvm="0"+vm;else strvm=""+vm;
  	return ""+strvm+"_"+vj;
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
  String TestFragment(String str){
  	String test=new com.search.sucheDate("gastro/date/fragment.dat").md();
  	if(test.length()>0 && str.equals(test))return "bereit";else return "ok";
  }
  public void entMon(){  	
  	String ein=new com.security.userFinster("Gastro Admin").toString();
  	String mou="gastro598";
  	String pw=new com.security.ChefSachen(mou).chek(ein);
  	if (pw.equals("Ok")){
  		String d=vorMonat();
  		if(TestFragment(d)=="ok"){
  			if(new com.options.MyOp().frage("<html>Fragmentiert<br><b>TT_MM_YYYY</b><br>"+d,"Ok","Nein")==0){
  				String stark=new com.options.MyOp().wahl(new String[]{"0","1","2","3","4","5","6"},"<html>Fragmentierturg St&auml;rke<br>1=50% 2=70% 3=80%<br>");
  				int Istark=intcode(stark);
  				if(Istark>0){
  					new com.security.fragment(d,stark);
  					update(1);update();
  					new com.security.fragment(1);
  					String m=d.substring(0,2);
  					String j=d.substring(3,d.length());  	
  					new com.units.Packen(m,j);
  				}else new com.options.MyOp().fehler("Eingabe nicht Ok\nFragmentierung Abgebrochen!");
  			}
  		}else new com.options.MyOp().fehler("Das Monat "+d.replace('_','/')+"\nIst bereit fragmentiert!");
  	}
 }
 	protected static javax.swing.ImageIcon getImg(int m) {
		String str="";
		if(m==1)str="hor2.gif";else str="ebmeingang.gif";
        java.net.URL imgURL =Defrag.class.getResource("/image/"+str);
        if (imgURL != null) {
            return new javax.swing.ImageIcon(imgURL);//.getImage();
        } else {
            return null;
        }
    }
 
}
