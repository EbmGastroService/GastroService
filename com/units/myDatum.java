package com.units;
import java.io.*;
import java.util.*;
import java.text.*;

public class myDatum{
	private SimpleDateFormat df,dt;  // Formats the date displayed
	private Date heute;	
	public myDatum(){
		df = new SimpleDateFormat ("dd.MM.yyyy",Locale.getDefault());
		dt = new SimpleDateFormat ("HH:mm:ss",Locale.GERMANY);
		heute = new Date();		 
	}
	public myDatum(long data){
		df = new SimpleDateFormat ("dd.MM.yyyy",Locale.getDefault());
		dt = new SimpleDateFormat ("HH:mm:ss",Locale.GERMANY);
		heute = new Date(data);		 
	}
                    public String ist(){//25_05_2003                        
                        //DateFormat df = DateFormat.getDateInstance();                         
                        String dat = df.format(heute);
                        dat = dat.replace('.','_');                    	
                     return(dat);
                    }
                    public String l(){
                    	df = new SimpleDateFormat ("yyMMdd",Locale.getDefault());
                    	String dat=df.format(heute);
                    	return dat;                    	
                    }
                    public String ll(){
                    	//df = new SimpleDateFormat ("dd:MM:yy",Locale.getDefault());
                    	String dat=df.format(heute)+" "+dt.format(heute);                               
                    	return dat;                    	
                    }
                    public String ist1(){
                    	df = new SimpleDateFormat ("ddMMyy",Locale.getDefault());
                    	String dat=df.format(heute);
                    	return dat;                    	
                    }
                     public String ist_my(){
                    	df = new SimpleDateFormat ("MMyy",Locale.getDefault());
                    	String dat=df.format(heute);
                    	return dat;                    	
                    }
                     public String jahr(){
                    	df = new SimpleDateFormat ("yy",Locale.getDefault());
                    	String dat=df.format(heute);
                    	return dat;                    	
                    }
                    public String ist01(){//2004/05/25                        
                        df = new SimpleDateFormat ("yyyy/MM/dd",Locale.getDefault());
                        String dat = df.format(heute);                        
                     return(dat);
                    }
                    
                    public String d(){//25.05.2003
                        String dat = df.format(heute);                                             	
                     return(dat);
                    }
                    public String time(){//19:30:23                                                   
                        String dat = dt.format(heute);                               
                     return(dat);
                    }
                    public String t(){//19:30:23
                    	String neu=time().replace(':','_');
                        String dat ="T"+ neu.substring(3,5)+T_M();                               
                     return(dat);
                    }
                    public String T_M(){//06_06
                        String dat = df.format(heute);
                        dat = dat.replace('.','_');                    	
                    	dat = dat.substring(0,5);
                     return(dat);
                    }
                    public String M(){//06_2003
                        String dat = df.format(heute);
                        dat = dat.replace('.','_');                    	
                    	dat = dat.substring(3,dat.length());
                     return(dat);
                    }
                     public String Mn(){//06/2003
                        df = new SimpleDateFormat ("dd.MMM.yy",Locale.GERMANY);
                        String dat = df.format(heute);                              	
                    	dat = dat.substring(3,dat.length());
                     return(dat);
                    }
                    public String J(){//_2003
                    	df = new SimpleDateFormat ("dd.MM.yyyy",Locale.getDefault());
                    	heute = new Date();		 
                        String dat = df.format(heute);
                        dat = dat.replace('.','_');                    	
                    	dat = dat.substring(5,dat.length());
                     return(dat);
                    }
}
