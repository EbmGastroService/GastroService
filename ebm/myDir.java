// created on 06.01.2007 at 01:15
package ebm;
public class myDir{
	String[]ordner;
	String[]jahr;
	public myDir(){
		new com.units.save().file("gastro/"+new com.units.myDatum().J().replace('_','D')+"/0.dat","",true);
		ordner=new com.search.sucheDate().dir("gastro/");		
			jahrs();
			zeige();		 
	}
	String[]jahrs(){
		int x=0;
		String[]j=new String[ordner.length];
		for(int i=0;i<ordner.length;i++){
			if(ordner[i].indexOf("D2")>-1){
				j[x]=ordner[i];//.substring(5,ordner[i].length());
				x++;};
		}
		jahr=new String[x];
		for(int i=0;i<jahr.length;i++)jahr[i]=j[i];
		return jahr;
	}
	void zeige(){
		for(int i=0;i<jahr.length;i++)
			System.out.println(jahr[i]);
	}
	public static void main(String[]args){
		new myDir();
	}
}
