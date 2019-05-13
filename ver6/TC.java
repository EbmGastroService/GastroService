// created on 19.04.2007 at 13:10
package ver6;
public class TC{	
	public static String L(String filename){
		String str=filename;
		String ein="";
        	if(str.length()>0){        
		 		for(int i=0;i<str.length();i++){
        			char c=str.charAt(i);
        			Character ch=new Character(c);			        
        			ein+=ch.toLowerCase(c);	        		
        		}               		
        		str=ein;
        	}
		 return str;
    }
    public static String U(String filename){
		String str=filename;
		String ein="";
        	if(str.length()>0){        
		 		for(int i=0;i<str.length();i++){
        			char c=str.charAt(i);
        			Character ch=new Character(c);			        
        			ein+=ch.toUpperCase(c);	        		
        		}               		
        		str=ein;
        	}
		 return str;
    }    
}
