package com.units;
 import java.util.*;
import java.text.*;

public class MyZahl {
    public synchronized String deci(double amount) {
		NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.GERMANY);   
		return nf.format(amount);
	}
	public synchronized String gint(double amount) {
		NumberFormat nf = NumberFormat.getNumberInstance(Locale.GERMANY);   
		return nf.format(amount);
	}

   public static String g (String argos,int l) {
		String myl="";    	
    	for (int i=0;i<l;i++){myl=myl+"0";}
  	  	argos= myl+argos;
   		NumberFormat nf = NumberFormat.getNumberInstance(new Locale("de", "DE"));
		DecimalFormat intf = (DecimalFormat)nf;
		intf.applyPattern("###,###.###");
		int invest=0;
 		try {    
       	invest = Integer.parseInt(argos);
    	} catch (Exception e) {System.out.println("Zahlfehler * " + e);
      	invest = 000;
    	}
    return(intf.format(invest));
    }
}
