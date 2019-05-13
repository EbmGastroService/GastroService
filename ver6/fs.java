// created on 25.12.2006 at 22:38

//Fehlerbuchungs suchmachiene es wird im Fliste nach fehler gesucht und angezeigt
package ver6;
import javax.swing.JLabel;
public class fs{
	String[]FS;
	String zeig;
	float sum=0;
	public fs(){
		FS=suchen();
		zeig="<Html><font color=blue>";
		if(FS.length>0)umbuchen();
		info();
	}
	public String[]suchen(){
		String[]wl=new com.search.sucheDate(",-","gastro/date/fliste.dat",20). wahlinarrayJL();		
		return wl;		
	}
	void zeige(String str){		
		//for(int i=0;i<FS.length;i++)				
		//System.out.println(str);		
		//System.out.println(" Umbuchen durchgezogen!!");		
		new com.units.save().file("gastro/date/umbuchung.dat",str,true);		
		zeig+="<b>"+str+"</b><br><br>in umbuchungsdatei gespeichert"+
		"<br>in alle anderen Datein sind keine &auml;nderung durch gef&uuml;hrt!";
		
	}
	void info(){
		zeig+="<br><br> Umbuchungen durchgezogen!!";		
		com.options.frame f=new com.options.frame("Info|geschlossen in 60 Secunden",60);//Secunden
		javax.swing.JPanel jp=new javax.swing.JPanel( new java.awt.BorderLayout());
		jp.add(new JLabel(zeig,JLabel.CENTER));
    	f.setContentPane(jp); 
		f.setSize(650,300);
		f.setLocation(300,200);
		//f.pack();
		f.setVisible(true);		
	}
	void add_kb(String str){
		new com.units.save().file("gastro/date/kbuch.dat",str,true);
	}
	void save_fl(String[] str){
		new com.units.save().dontsort("gastro/date/fliste.dat",str,false);
	}
	float sume(String str){
		float total=0;				
		try{
			total=Float.parseFloat(str);
		}catch(Exception ex){total=0;}
		sum+=total;
		return sum;
	}
	String change(String str){		 
        String[]w =  new com.options.ausTeilen().zeile(str);
		w[1]+="G";
		//w[2]=new com.units.myDatum().ist();
		w[6]="F_Chef";
		w[7]="+";
		if(w[2].trim().length()<7){
			String nd=w[2].substring(0,2)+"_"+w[2].substring(2,4)+"_20"+w[2].substring(4,6);
			w[2]=nd;
		}
		String fl=","+w[1]+","+w[2]+","+w[3]+","+w[4]+","+w[5]+","+w[6]+",+";
		//add_kb(","+w[1]+","+w[2]+","+w[3]+","+w[4]+","+w[4]+","+w[6]+",-");		
		sume(w[4]);zeige(w[1]+" Fehler am: "+w[2]+" Fehlbetrag Euro "+w[4]+" Gesamt Euro "+sum);
		return fl;
	}
	/*Kassbuch 
	 * ,RN240806/0, 24_08_2006, 0000X00509240806, 33.2, 33.2, F_Jovani ,-
	 * Fliste   korrekt
	 * ,RN240806/0, 24_08_2006, 0000X00509240806, 33.2, 33.2, F_Jovani ,+
	 * Fliste Falsch 
	 * ,RN271006/1124,27_10_06,2352014X14271006,9.2,16901.7,F_Chef,-
	 * ,RN061206/1875,061206,0000X01595061206,20.1,28148.7,F_ Leider nicht Vorhanden,-
	 */
	public void umbuchen(){		
		String[]fl=new com.search.sucheDate("gastro/date/fliste.dat"). myDaten();		
		for(int i=0;i<FS.length;i++){
			for(int a=0;a<fl.length;a++){
				if(FS[i].equals(fl[a])){
					fl[a]=change(fl[a]);
				}				
			}
		}
		//save_fl(fl);
	}
	//public static void main(String[]args){	new fs();	}
}
