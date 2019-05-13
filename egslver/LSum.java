// created on 02.11.2007 at 00:45
package egslver;
public class LSum{
	String K,datum;
	public LSum(String K){
		this.K=K;
		datum=new com.units.myDatum().ist_my();
		save(DateGes());
		//show();
	}
	public LSum(String K,String Mitar,String datum){
		this.K=K;
		this.datum=datum;
		if(datum=="" || datum.length()<=0)datum=new com.units.myDatum().ist_my();
		//save(DateGes());
		show(Mitar);
	}
	String dateLine(String[]ges){				
		if(ges!=null){
			String date=""+ges[0];
			for(int x=1;x<ges.length-1;x++)date+=","+ges[x];
			date+=","+ges[ges.length-1];
			return date;
		}else return null;
	}
	String[]open(String file){
 		return new com.search.sucheDate(file).myDaten();
 	}
 	String[]MD(String M,String da){ 		
 		String file="egslv/k"+K+"/lz"+da+"_"+M+"_"+datum.substring(2,datum.length())+".lz";				
 		return open(file);
 	}
 	String[]Date(String ld){				
		String[]LD =new com.options.ausTeilen().koma(ld);
		return LD;
	}
	float fd(String str){
		float ld=0;
		try{
			ld=Float.parseFloat(str);
		}catch(NumberFormatException nfe){ld=0;}
		return ld;		
	}
 	float[]Sumen(String mf,String da){
 		String[]md=MD(mf,da);
 		if(md!=null){
 		String[]zin=Date(md[0]);
 		float[]summen=new float[zin.length];
 		for(int i=0;i<summen.length;i++)summen[i]=0;
 		int bis=(int)fd(datum.substring(0,2));	
 		for(int i=0;i<bis;i++){
 			zin=Date(md[i]);
 			for(int x=1;x<zin.length;x++)summen[x]+=fd(zin[x]);
 		}
 		return summen;
 		}else return null;
 	}
 		
 		//dna 0607,750.0,135.0,0.0,0.0,750.0,127.5,622.5,0.0,262.5
 		//dga 0607,750.0,310.725,0.0,0.0,750.0,282.75,0.0,0.0,593.475
 	String[]Gesamt(String M){
 		float[]dgasume=Sumen(M,"dga");
 		float[]dnasume=Sumen(M,"dna");
 		float[]sume=dgasume;
 		String[]strSum=new String[sume.length]; 		 		
 		strSum[0]=M;
 		dnasume[1]=0;//Lohn
 		dnasume[2]=0;//Lohn
 		dnasume[5]=0;//SonderZahlungsbasis 	
 		//sume[9]=dnasume[9];
 		dnasume[9]=0;//Dienstnehmeranteil	 		
 		for(int x=1;x<sume.length;x++){
 			//sume[x]+=dnasume[x];
 			
 			strSum[x]=""+sume[x];
 		}	
 		return strSum;
 	}
 	String[]DateGes(){
 		String[]kma=open("egslv/k"+K+"_ma.dat");  
 		String[]strSum=new String[kma.length];
 		if(kma!=null){ 			
 			for(int i=0;i<kma.length;i++){
 				strSum[i]=dateLine(Gesamt(kma[i]));
 			}
 			return strSum;
 		}else return null;
 	}
	void save(String[]dateLine){
		String file="egslv/k"+K+"/gesm_"+datum.substring(2,datum.length())+".lz";					
		if(dateLine!=null)new com.units.save().dontsort(file,dateLine,false);
 	}
 	String[]stammDaten(String str,String M){
 		SD sd=new SD(K,M);
 		if(str.equals("m"))return sd.MaSd;else
 		if(str.equals("k"))return sd.KlientSd;else return null;
 	}
 	void show(){
 		String[]md=open("egslv/k"+K+"/gesm_"+datum.substring(2,datum.length())+".lz");					
 		String[]Klient=open("egslv/k"+K+"_sd.dat");
 			for(int i=0;i<md.length;i++){ 		
 				String[]fd=Date(md[i]); 
 				String m=fd[0];
 				String date=datum+","+fd[1];
 				for(int x=2;x<fd.length-1;x++)date+=","+fd[x];
 				date+=","+fd[fd.length-1];
 				System.out.println(m); 				
 				String[]Ma=open("egslv/k"+K+"/sd/m"+m+"_sd.dat");
 				new Form(date,Klient,Ma,"dga","-");
 				//new Form(date,Klient,Ma,"dna");
 			}
 	}
 	void show(String str){
 		String[]md=DateGes();				
 		String[]Klient=open("egslv/k"+K+"_sd.dat");
 			for(int i=0;i<md.length;i++){ 		
 				String[]fd=Date(md[i]); 
 				String m=fd[0];
 				if(m.equals(str)){
 					String date=datum+","+fd[1];
 					for(int x=2;x<fd.length-1;x++)date+=","+fd[x];
 					date+=","+fd[fd.length-1];
 					System.out.println(m);
 					String[]Ma=open("egslv/k"+K+"/sd/m"+m+"_sd.dat");
 					new Form(date,Klient,Ma,"dga","-");
 				}
 			}
 	} 
 	public static void main(String[]args){
 		if(args.length>0)new LSum(args[0]);else
 		new LSum("251401","1600","");
 	}
}
