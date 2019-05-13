// created on 13.12.2006 at 20:52
package ver6;
public class basic{
	String path,lpath;
	String sprache;
	String land;
	String sep;
	public basic(){
		sep=System.getProperty("file.separator");
		path=System.getProperty("user.home")+sep+"gastro"+sep;
		lpath=System.getProperty("user.home")+sep+".galambo"+sep+"date"+sep+"source"+sep;
		sprache=System.getProperty("user.language");
		land=System.getProperty("user.country");
	}
	/*extintion starter basic
	 * SolarisTM Operating System: /usr/jdk/packages/lib/ext 
	 * Linux: /usr/java/packages/lib/ext 
	 * Microsoft Windows: %SystemRoot%\Sun\Java\lib\ext 
	 * */
	public String getPath(){return path;}
	public String getlPath(){return lpath;}
	public String getLand(){return land;}
	public String getSprache(){return sprache;}
	public String getSep(){return sep;}
	public static void main(String[] args) {
		basic b=new basic();
		System.out.println(b.getSprache());
		System.out.println(b.getLand());
		System.out.println(b.getPath());
		System.out.println(b.getSep());
	}
}
