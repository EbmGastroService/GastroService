// created on 15.11.2007 at 18:38
//Lkosten summen
package egslver;
public class Sum{
	String[][]DGD;//,DND;
	String datum;
	public Sum(String K,String M,String datum){
		this.datum=datum;
		DGD=open_D("egslv/k"+K+"/ls/dgg_"+M+"_"+datum.substring(2,datum.length())+".dat");
		//DND=open_D("egslv/k"+K+"/lzdna_"+M+"_"+datum.substring(2,datum.length())+".lz");
	}
	float[]Sumen(){
 		if(DGD[0]!=null){ 		
 			float[]summen=new float[DGD[0].length];
 			for(int i=0;i<summen.length;i++)summen[i]=0;
 			int bis=(int)fd(datum.substring(0,2));
 			for(int i=0;i<bis;i++){
 				for(int x=1;x<summen.length;x++)summen[x]+=fd(DGD[i][x]);		
 				//summen[1]+=fd(DND[i][5]); 			
 			}
 			return summen;
 		}else return null;
	}
	float[]Sumen(int monat){
 		if(DGD[0]!=null){ 		
 			float[]summen=new float[DGD[0].length];
 			for(int i=0;i<summen.length;i++)summen[i]=0;
 			int bis=(int)fd(datum.substring(0,2))-1; 			
 				for(int x=1;x<summen.length;x++)summen[x]=fd(DGD[bis][x]); 			
 			//summen[1]+=fd(DND[bis][5]); 			
 			
 			return summen;
 		}else return null;
	}
 	String[][]open_D(String file){
 		return new com.search.sucheDate(file).teiledaten();
 	}
 	float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}		
}
