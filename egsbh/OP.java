// created on 28.11.2007 at 21:30
/*
 * Erstellt von Mourad El Bakry 2007
 * Teil Buchhaltungs Programm Eikauf Parametern
 */
 package egsbh;
 public class OP{
 	String[]Fragen;
 	public OP(String[]Fragen){
 		this.Fragen=Fragen;
 	}
 	String[]Gen(){
 		String[]F=new String[Fragen.length];
 		for(int i=0;i<F.length;i++){
 			if(Fragen[i].indexOf("\n")>-1)F[i]=TestF(Fragen[i]);else
 			F[i]=new com.options.MyOp().eingabe(Fragen[i]); 			
 			if(F[i].length()==0||F[i].equals(" Leider nicht Vorhanden")||F[i]==null){
 				F[i]=EingTest(F[i],Fragen[i]);
 			}	
 			F[i]=new kTp().kpStr(F[i]);
 		}
 		return F;
 	}
 
 	String EingTest(String p,String frage){ 		 			
 		String w=new com.options.MyOp().eingabe("<html><font color=red>Falsche Eingabe!<br>Nochmal versuchen<br>"+frage);
 		return w=new kTp().kpStr(w);
 	}
 	
 	String TestF(String input) {
 		java.util.List<String> v = new 	java.util.ArrayList<String>(10);
 		java.util.StringTokenizer t = new java.util.StringTokenizer(input,"\n");
 		String cmd[];
 		while (t.hasMoreTokens())
 			v.add(t.nextToken());
 		cmd = new String[v.size()-1];
 		for (int i = 0; i < cmd.length; i++)
 			cmd[i] = (String) v.get(i+1);	
 		String w=wahl(cmd,v.get(0).toString());
 		w=new kTp().kpStr(w);
 		return w;
 	}
 	String wahl(String[]str,String frage){
 		String F=new com.options.MyOp().wahl(str,frage); 		
 		F=new kTp().kpStr(F);
 		return F;
 	}
 }
