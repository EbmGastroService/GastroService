// created on 07.11.2006 at 02:50
package ebm;
public class gen{
	String mycod=		
	"01111111111111111111"+
	"11001110010110100110"+
	"10110001006305980000"+
	"1111AQ01Px63C598L999"+
	"11011110010110100110"+
	"10120001006305980000"+
	"1113AQ01Px63C598L999"+	
	"3011X001Z063A598M010"+
	"3011M001HH63L5981234"+
	"30LLT41GD941R598V958"+
	"3011H526J845C5989G10"+	
	"9011T631N745K508F090"+
	"9011B741n645G098Q010"+
	"9011H851z555G008A719"+
	"9011Z061z383GS98D754"+
	"9011D971z129GC98K145";
	public gen(){		
	//	zeige(teile());
	}
	public String zeile(){
		return "0102010024102056401041201020100131020164010412010201024102056401041000"+
		"000102010241020564010012©"+
		"010201067090670801080607000001020100241020564010412ABCDEFGHIJKLMNOPQRSTUVWYZÄÜÖ"+
		"abcdefghijklmnopqrstuvwyzäüöß010201024102056401004110102020107090815101101001©";
	}
	public String[]teile(){
		int x=mycod.length()/20;
		int l=0;
		int m=1;
		int n=1;
		String[]mein=new String[x];
		int i=0;
		mein[l]="";
		for(i=0;i<mycod.length();i++){						
			if(i==(m*20)){l++;m++;n=0;mein[l]="";}			
			if(n==4 || n==8 || n==12 || n==16)mein[l]+="-";				
			mein[l]+=mycod.charAt(i);			
			n++;						
		}
		return mein;
	}

	/*String[]teile(){
		String[]mein=new String[mycod.length];
		for(int i=0;i<mycod.length;i++){
			mein[i]="";
			String lein=mycod[i];
			for(int x=0;x<lein.length();x++){				
				if(x==4 || x==8 ||x==12 || x==16)mein[i]+="-";
				mein[i]+=lein.charAt(x);
			}
		}
		return mein;
	}*/
	void zeige(String[]str){
		for(int i=0;i<str.length;i++)System.out.println(str[i]);
	}
	public static void main(String[] args) {new gen();}
}
