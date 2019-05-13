/**
 * erstellt am 19/10/2006
 *Author Mourad El bakry
 */
package ebm;
import java.awt.Color;
public class myColor {
	String Filename;
public myColor(String file){	
	Filename=file;	
}
public String getData(){
  String data=new com.search.sucheDate("gastro/resource/"+Filename+".dat").md();  
  if(Filename.equals("hg")&& data.length()<=0)data="102,204,255";//r=102,g=204,b=255]
  if(Filename.equals("vg1")&& data.length()<=0)data="102,0,102";
  if(Filename.equals("vg2")&& data.length()<=0)data="204,255,254";
  //if(data.length()>0)  return data;else return null;
	return data;
}
public String colorData(){	
 	String str=getData();
	String[]wort=new String[3];
	if(str!=null && str.length()>20){
   	   str=str.substring(15,str.length()-1);//cut java.awt.color[, und]	
	   wort=new com.options.ausTeilen().koma(str);
	  for(int i=0;i<wort.length;i++){
	  	wort[i]=wort[i].substring(2,wort[i].length());
	  	//System.out.println(wort[i]);
	  }
	  	str=wort[0]+","+wort[1]+","+wort[2];	
	}else
		if(str!=null && str!=""){
			wort=new com.options.ausTeilen().koma(str);	
			str=wort[0]+","+wort[1]+","+wort[2];	
		}//else str=null;
	//System.out.println(str);
	return str;
}
int[]intValue(String str){
     int[]myValue=new int[3];
     if(str.length()>0){
       String[]wort=new com.options.ausTeilen().koma(str);
       for(int i=0;i<myValue.length;i++){
        try{
           myValue[i]=Integer.parseInt(wort[i]);
        }catch(Exception ex){
         myValue[i]=0;
        }
       }
     }
     return myValue;
}
public Color getColor(){
  String data=colorData();
	Color color;//=null;
	if(data!=null){
		int[]value=intValue(data);
		color=new Color(value[0],value[1],value[2]);
		return color;
	} else return null; // System.out.println(color);	  
}    
//public static void main(String[]args){  new myColor();}

}

