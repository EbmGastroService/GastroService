// created on 28.01.2008 at 18:17
//Mourad El Bakry
package egsbh;
public class kTp{	
	public kTp(){		
	}
	public String kpStr(String antwort){
		if(antwort.length()>0 && antwort!=null){
			if(antwort.indexOf(',') > -1){
				StringBuffer z= new StringBuffer();
				z.append(antwort);
				int pos =antwort.indexOf(',');
				z.replace(pos,pos+1,".");
				antwort=z.toString();
			}
		}
		return antwort;
	}
	public static void main(String[]args){
		System.out.println(new kTp().kpStr("55,25"));
		
	}
}
