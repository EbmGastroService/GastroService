// created on 23.07.2006 at 02:00
package ver6;
public class Rcode{	
	public String[]gen,genkr;
	public String dgen;
	public Rcode(){
		gen=lesen();//RN120706/17,0000X00014120706,15.4,14
		for(int i=0;i<gen.length;i++){
			gen[i]=""+neu(""+gen[i]);
		//	System.out.println(gen[i]);
		}
//		save();
	}
	public Rcode(String zeile,int i){
		//RN120706/17,120706,0000X00014120706,15.4,14
		genkr=new String[2];
		if(i==0){
		dgen = neu(zeile);
		//System.out.println(dgen);		
		//add();
		}else {
			genkr[0] = Kname(zeile);
			genkr[1] = Rname(zeile);
			dgen = genkr[0]+","+genkr[1];
			//System.out.println(dgen);		
		}
	}
	////gibt (2514001) X00014120706
	String neuKc(String str){//1
		int posr=str.indexOf("X");		
		String kc=str.substring(0,posr);				
		return kc;	
	}
	//RN120706/ (xxx)
	String neuRc(String str){//0	
		int posn=str.indexOf("/");		
		String code=str.substring(posn+1,str.length());
		return code;
	}
	//gibt xx (120706) /xxx
	String neuD(String str){
		int posr=str.indexOf("RN");
		int posn=str.indexOf("/");
		String datum=str.substring(posr+2,posn);
		return datum;
	}
	//gibt 0000X (00014) 120706
	String neuKz(String str){//1,neuD();
		int posr=str.indexOf("X");
		int posn=str.length();		
		String kz = str.substring(posr+1,posn-6);
		return kz;
	}
	//gibt R220207N2500K2514108X123E
	String neu(String erg){
		String str="";int x=1;
		String[] daten=new com.options.ausTeilen().koma(erg);
		if(daten.length>4)x=2;
    	//int kcod=0;		
		str="R"+neuD(daten[0])+"N"+neuRc(daten[0])+"K"+neuKc(daten[x])+"X"+neuKz(daten[x])+"E";
		//System.out.println(str);
		return str;
	}
	//gibt 0000X00014120706
	public String Kname(String zeile){                		
		StringBuffer path=new StringBuffer(zeile);
		String erg= path.substring(path.indexOf("K")+1,path.indexOf("E"));
		String datum=path.substring(path.indexOf("R")+1,path.indexOf("N"));		
		erg=erg+datum;
		return erg;
	}
	//gibt RN120706/17
	public String Rname(String zeile){                				
		StringBuffer path=new StringBuffer(zeile);		
		String datum=path.substring(path.indexOf("R")+1,path.indexOf("N"));
		String wcod=path.substring(path.indexOf("N")+1,path.indexOf("K"));
		wcod="RN"+datum+"/"+wcod;		
		return wcod;
	}
	public void save(){
		new com.units.save().dontsort("gastro/Date/kon.dat",gen,false);
	}
	public void add(){
		new com.units.save().file("gastro/Date/kon.dat",dgen,true);
	}
	String[]lesen(){
		return new com.search.sucheDate("gastro/Date/Wkorb.dat").myDaten();
	}
	/*public static void main(String[] args) {
		String old=new Rcode("RN300806/532,2340001X01300806,4.8,1",0).dgen;
		String neu=new Rcode(new Rcode("RN300806/532,2340001X01300806,4.8,1",0).dgen,1).dgen;
        //	System.out.println(old+"\n"+neu);
		new Rcode().save();
			//System.out.println(new Rcode("R120706N2K0000X00002E",1).dgen);
	
	}*/
}
