package com.units;
 import java.text.*;
public class MyZahl {
  public static String deci (String argos) {
  //	getCurrencyInstance
   // DecimalFormat df = new DecimalFormat("######,#00000.0;-######,#00000.0");
   DecimalFormat df = new DecimalFormat("000.00");
	double invest;
       try {    
            invest = Double.valueOf(argos).doubleValue();
           } catch (Exception e) {
             // ArrayIndexOutOfBoundsException or NumberFormatException
             System.out.println("Zahlfehler * " + e);
             invest = 00000;
            }            
    return(df.format(invest));
    }
    public static String d(String argos,int l) {
   		String myl="";    	
    	for (int i=0;i<l;i++){myl=myl+"0";}
   		DecimalFormat df = new DecimalFormat(myl+".00");
		double invest;
       try {    
            invest = Double.valueOf(argos).doubleValue();
           } catch (Exception e) {
             // ArrayIndexOutOfBoundsException or NumberFormatException
             System.out.println("Zahlfehler * " + e);
             invest = 00000;
            }            
    return(df.format(invest));

    }
public static String d (String argos) {
    DecimalFormat df = new DecimalFormat("######,#00000.00;-######,#00000.00");
    //DecimalFormat df = new DecimalFormat("0000.0");
	double invest;
       try {    
            invest = Double.valueOf(argos).doubleValue();
           } catch (Exception e) {
             // ArrayIndexOutOfBoundsException or NumberFormatException
             System.out.println("Zahlfehler * " + e);
             invest = 00000;
            }            
            String mz="\t"+df.format(invest);
            
    return(mz);

    }
  public static String ganz (String argos) {
    DecimalFormat intf = new DecimalFormat("0000");
	int invest=0;
 try {    
       invest = Integer.parseInt(argos);
    } catch (Exception e) {
      // ArrayIndexOutOfBoundsException or NumberFormatException
      System.out.println("Zahlfehler * " + e);
      invest = 0000;
    }
    return(intf.format(invest));
    }

public static String g (String argos,int l) {
		String myl="";    	
    	for (int i=0;i<l;i++){myl=myl+"0";}
    DecimalFormat intf = new DecimalFormat(myl);
	int invest=0;
 try {    
       invest = Integer.parseInt(argos);
    } catch (Exception e) {
      // ArrayIndexOutOfBoundsException or NumberFormatException
      System.out.println("Zahlfehler * " + e);
      invest = 000;
    }
    return(intf.format(invest));
    }
}
