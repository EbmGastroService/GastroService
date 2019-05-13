// created on 28.06.2007 at 22:30

/*Author Mourad El bakry
 * Vianna
 * umwandler ein Vector to String[][]
 */
  package ver6;
  import java.util.List;
  public class VTS{
  	String[][]V;
  	public VTS(List<String> tot){
  		V=VectorString(tot);
  	}
  	public VTS(String[]tot){
  		V=VectorString(tot);
  	}
  	public String[][]getVS(){return V;}
  	
  	String[][]VectorString(List<String> tot){
		String[]wort=new com.options.ausTeilen().koma(tot.get(0));
		String[][]str=new String[tot.size()][wort.length];//{"0","0","0","0","0"};
		for(int i=0; i<str.length;i++){
			String myvec=tot.get(i);
			wort=new com.options.ausTeilen().koma(myvec);
			for(int x=0; x<wort.length;x++)
			str[i][x]=""+wort[x];
		}
		return str;
		
	}
	String[][]StringtoString(String[]tot){
		for(int a=0;a<tot.length;a++){
		String[]wort=new com.options.ausTeilen().koma(tot.get(0));
		String[][]str=new String[tot.size()][wort.length];//{"0","0","0","0","0"};
		for(int i=0; i<str.length;i++){
			String myvec=tot.get(i);
			wort=new com.options.ausTeilen().koma(myvec);
			for(int x=0; x<wort.length;x++)
			str[i][x]=""+wort[x];
		}
		}
		return str;		
	}
  }
