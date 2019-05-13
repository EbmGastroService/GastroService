// created on 18.01.2007 at 18:41
package ebm;
public class Tm{
	String T_file;
	int zeit;
	int inhalt;
	long neu,old;
	public int Ergebnis;
	public String zeile;
	public Tm(String[]str,String file){
		T_file=file.toLowerCase();
		save_Neu(str);
		neu=open_Neu();
		old=open_Old();
		T_test();
		//Ergebnis=(int)(neu-old);
		if(T_file.length()>11)zeile=T_file.substring((T_file.length()-11),T_file.length());else zeile=T_file;
		if(Ergebnis<0)zeile+=",updatet ok!";else zeile+=",no update!";
		print(zeile);
	}	
	public Tm(int inhalt,String file){
		T_file=file.toLowerCase();
		
		zeit=mycalende();
		this.inhalt=inhalt;
		Ergebnis=TestFile();
		//System.out.println(",E:"+Ergebnis+"]");
		zeile=file+","+Ergebnis;
		print(zeile);		
		//new com.units.save().file("gastro/date/protocol/p"+new com.units.myDatum().ist_my()+".dat",file+","+Ergebnis,true);
	}
	void T_test(){
		long erg=neu-old;
		if(old>0 && neu>0 && erg!=0)Ergebnis=-1;
		else if(old==0 && neu>0 && erg>0)Ergebnis=-1;
		else Ergebnis=0;
	}
	void save_Neu(String[]str){
		new com.units.save().dontsort("gastro/temp/tvtest.tmp",str,false);
	}
	long open_Neu(){
		return new com.search.sucheDate("gastro/temp/tvtest.tmp").filelength();			
	}
	long open_Old(){
		return new com.search.sucheDate(T_file).filelength();			
	}
	public String  print(String str){
		System.out.println(str);
		//System.out.println(T_file+"\nLength old:"+old+" und temp: "+neu+" Diff:"+(neu-old)+" oder Erg:"+Ergebnis+" ");
		//new com.units.save().file("gastro/date/protocol/p"+new com.units.myDatum().ist_my()+".dat",T_file+","+Ergebnis,true);
		return str;
	}
	int TestFile_L(){
		String[]str=new com.search.sucheDate(T_file).myDaten();			
		//System.out.print(""+str.length);
		return str.length;
	}
	int TestFile_Z(){		
		return zeit;
	}
	int TestFile(){
		//System.out.print(" [N:"+inhalt+",A:"+TestFile_L()+",Z:"+TestFile_Z());
		if(inhalt>TestFile_L()&& TestFile_Z()<32)return 1;else return -1;
	}
	int intmodi(String str){		
		int modi=0;
		try{
			modi=Integer.parseInt(str);
		}catch(Exception ex){System.err.print(ex);modi=0;}
		return modi;
	}	
	
	int mycalende(){//addiere datum in Tage		
		long now_file =  new com.search.sucheDate("gastro/source/liz/ja.0").filemodi();
		long last_file = new com.search.sucheDate(T_file).filemodi();		
		String checker = new com.units.myDatum(last_file).l();
		String str= new com.units.myDatum(now_file).l();
		String fix= new com.units.myDatum(now_file).l();//galambo/d.exe fix
		//System.out.println(checker+","+str);//
		
		if(str.length()==0)str=checker;
		//day 061107 061206
		int at=intmodi(checker.substring(4,6));//aktuelle tag
		int btf=intmodi(fix.substring(4,6));//beginn Tag
		int et=intmodi(str.substring(4,6));	//End Tag	
		//monat
		int am=intmodi(checker.substring(2,4));//aktuelle monat
		int bmf=intmodi(fix.substring(2,4));//beginn monat
		int em=intmodi(str.substring(2,4));//end Monat
		//jahr
		int aj=intmodi(checker.substring(0,2));//aktuellesjahr
		int bjf=intmodi(fix.substring(0,2));//beginn jahr
		int ej=intmodi(str.substring(0,2));//end jahr		
		
		//int sum=0;
		//monat zu tage
		int tage=0;
		int ntage=0;
		if(ej==bjf){
			for(int i=bmf;i<am;i++)tage+=days(i);
		}
		if(ej>bjf){
			if(am >= bmf){
				for(int i=bmf;i<am;i++)tage+=days(i);//ab begin monat bis ende des verganegenen jahres
			}else if(am<bmf){
				for(int i=bmf;i<13;i++)tage+=days(i);//ab begin monat bis ende des verganegenen jahres
				for(int i=1;i<am;i++)tage+=days(i);//ab anfang des aktuellenjahres bis aktuellen Monat
			}			
		}		
		
		tage=tage-btf+at;
		return tage;
	}
	int days(int monat ){
		int[]day={0,31,28,31,30,31,30,31,31,30,31,30,31};
		return day[monat];
	}
	//public static void main(String[] args) {new Tm(0,"gastro/date/"+args[0]+".dat");}
}
