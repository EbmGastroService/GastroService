package ver6; 
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.UIManager;

import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreeSelectionModel;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;

import java.net.URL;
import java.io.IOException;
import java.awt.Dimension;
import java.awt.GridLayout;

public class myTree extends JPanel implements TreeSelectionListener {    
    private JTree tree;    
    private static boolean DEBUG = false;
    //Optionally play with line styles.  Possible values are
    //"Angled" (the default), "Horizontal", and "None".
    private static boolean playWithLineStyle = false;
    private static String lineStyle = "Horizontal";
    
    //Optionally set the look and feel.
    private static boolean useSystemLookAndFeel = false;
	
    public myTree() {
        super(new GridLayout(1,0));
    	
        //Create the nodes.
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Brichten");
        createNodes(top);

        //Create a tree that allows one selection at a time.
        tree = new JTree(top);
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        //Listen for when the selection changes.
        tree.addTreeSelectionListener(this);

        if (playWithLineStyle) {
            System.out.println("line style = " + lineStyle);
            tree.putClientProperty("JTree.lineStyle", lineStyle);
        }

        //Create the scroll pane and add the tree to it. 
        JScrollPane treeView = new JScrollPane(tree);       
        Dimension minimumSize = new Dimension(100, 50);        
        treeView.setMinimumSize(minimumSize);
        //splitPane.setDividerLocation(100); //XXX: ignored in some releases
                                           //of Swing. bug 4101306
        //workaround for bug 4101306:
        treeView.setPreferredSize(new Dimension(200, 500)); 
        add(treeView);
    }
    String[]ordner(String str){
    	return new ebm.li(str).ordner();
    }
    String[]files(String str){
    	return new ebm.li(str).files();
    }
    

    /** Required by TreeSelectionListener interface. */
    public void valueChanged(TreeSelectionEvent e) {
        DefaultMutableTreeNode node = (DefaultMutableTreeNode)
                           tree.getLastSelectedPathComponent();

        if (node == null) return;

        Object nodeInfo = node.getUserObject();
        if (node.isLeaf()) {
        	display(""+nodeInfo);
            if (DEBUG) {
                //System.out.print(book.bookURL + ":  \n    ");
            }
        } else {
           // displayURL(helpURL); 
        }
        if (DEBUG) {
            System.out.println(nodeInfo.toString());
        }
    }

    private class BookInfo {
        public String bookName;
    	public BookInfo(String book) {
            bookName = book;                   
        }

        public String toString() {
            return bookName;
        }
    }
    int mon(String str){
    	String mo="";
    	str=new ver6.TC().L(str);
    	if(str.indexOf("bh")>-1 && str.length()>4)mo=str.substring(2,str.indexOf("n"));
    	if(str.indexOf("bh")>-1 && str.length()>6)mo=str.substring(str.indexOf("bh")+2,str.indexOf("n")-2);    	
    	return Integer.parseInt(mo);
    }
    String j(String str){
    	String mj="";
    	str=new ver6.TC().L(str);
    	if(str.indexOf("bh")>-1 && str.length()>4)mj=str.substring(str.indexOf("bh")+2,str.indexOf("n"));
    	if(str.indexOf("bh")>-1 && str.length()>6)mj=str.substring(4,str.indexOf("n"));
    	
    	return "d20"+mj;
    }
    String bhFile(String str){		
    	str=new ver6.TC().L(str);
		String zm="bh"+str+"n.dat";
    	if(str.indexOf("bh")>-1)zm=str+".dat";
    	String mj="";
    	if(str.indexOf("bh")>-1 && str.length()>4)mj=str.substring(str.indexOf("bh")+2,str.indexOf("n"));
    	if(str.indexOf("bh")>-1 && str.length()>6)mj=str.substring(4,str.indexOf("n"));
    	//System.out.println("gastro/date/data_2007/tv"+mj+"n/"+zm);
		return "gastro/date/data"+new com.units.myDatum().J()+"/tv"+mj+"n/"+zm;
	}
	void zeigeBhl(int i, String str){		
		//System.out.println(str);
		str=new ver6.TC().L(str);
		new ebm.Bhl(bhFile(str),1);
	}
	void zeigeMe(int i,String jahr){		
		//System.out.println(str);
		new ebm.monatErlose(jahr,i);
	}    
    void display(String str){    	
    	str=new ver6.TC().L(str);
    	if(str.indexOf("bh")>-1){
    		int mo=mon(str);String j=j(str);
    		//System.out.println(mo+","+j);
    		zeigeBhl(mo,str);
    		zeigeMe(mo,j);
    	}else 
    	if(str.indexOf("kb")>-1){
    			String j="20"+str.substring(4,6);
    			zeigTab(str,j);
    	}else
    	if(str.indexOf("fl")>-1){
    			String j="20"+str.substring(4,6);
    			zeigTab(str,j);
    	}else		
    	if(str.indexOf("bh")<0 && str.indexOf("e")<0 && 
    	   str.indexOf("ko")<0 && str.indexOf("m")<0 && 
    	   str.indexOf("wv")<0 && str.indexOf("vv")<0){
    		new ebm.TV(str,false);
    	}else System.out.println("kann nicht..");
    }
    String[]suchit(String[]str,String such){
    	java.util.List<String>mv=new java.util.ArrayList<String>();
    	for(int i=0;i<str.length;i++){    		
    		//System.out.println(str[i]);
    		String sz=str[i];
    		if(sz.indexOf(such)>-1)mv.add(sz);
    	}
    	String[]nstr=new String[mv.size()];
    	for(int i=0;i<mv.size();i++){
    		nstr[i]=mv.get(i);
    		//System.out.println(nstr[i]);
    	}
    	return nstr;
    }
    String[]verFiles(String dek,String j){
    	String[]str=files("gastro/d"+j+"/"+dek+"?");
    	//String[]kb=suchit(str,dek);
    	return str;//kb;
    }
    private void createNodes(DefaultMutableTreeNode top) {
        DefaultMutableTreeNode category = null;
        DefaultMutableTreeNode book = null;
    	DefaultMutableTreeNode kind =null;
    	DefaultMutableTreeNode nkind =null;
    	String mstr="";
    	String[]j=ordner("gastro/d2?");
    	java.util.Arrays.sort(j);
    	for(int i=0;i<j.length;i++){    		
    		String tvj=j[i].substring(3,j[i].length());
    		category = new DefaultMutableTreeNode("Daten "+j[i].substring(1,j[i].length()));
    		kind =new DefaultMutableTreeNode("KB "+j[i].substring(1,j[i].length()));
    		category.add(kind);
    		top.add(category);
    		//top.add(category);
    		
    		String[]str=verFiles("kb",j[i].substring(1,j[i].length()));
    		java.util.Arrays.sort(str);
    		for(int x=0;x<str.length;x++){
    			mstr=str[x].substring(0,str[x].indexOf(".dat"));
    			if(i==(j.length-1) && x==(str.length-1))update("gastro/"+j[i]+"/"+mstr+".dat");
    			book = new DefaultMutableTreeNode(new BookInfo(mstr));
    			//category.add(book);
    			kind.add(book);
    			category.add(kind);
    		}
    		kind =new DefaultMutableTreeNode("FL "+j[i].substring(1,j[i].length()));
    		str=verFiles("fl",j[i].substring(1,j[i].length()));
    		for(int x=0;x<str.length;x++){
    			mstr=str[x].substring(0,str[x].indexOf(".dat"));
    			book = new DefaultMutableTreeNode(new BookInfo(mstr));
    			//category.add(book);
    			kind.add(book);
    			category.add(kind);
    		}
    	
    	kind =new DefaultMutableTreeNode("Absatz ");
    		//category.add(kind);
    	String[]ostr=Absatz(tvj+"n");//ordner("gastro/date/Data_2007/TV?");
    	for(int a=0;a<ostr.length;a++){
    		String[]astr= files("gastro/date/data"+new com.units.myDatum().J()+"/"+ostr[a]);
    		String bez="Absatz ";
    		String sp=""+ostr[a].toLowerCase();
    		//if(astr[n].length()>5)bez="Absatz ";else bez="BH ";    		
    		bez+=sp.substring(sp.indexOf("tv")+2 , sp.indexOf("n"));
    		nkind =new DefaultMutableTreeNode(bez);    	    		
    		for(int n=0;n<astr.length;n++){
    			String myzeile=astr[n].toLowerCase();
    			if(myzeile.indexOf("tv")>-1)    				
    				mstr=myzeile.substring(2,myzeile.indexOf("n")+1);
    			else mstr=myzeile.substring(0,myzeile.indexOf("n")+1);
    			book = new DefaultMutableTreeNode(new BookInfo(mstr));
    			nkind.add(book);
    			kind.add(nkind);
    			category.add(kind);
    		}
    	}
    	
    	//category = new DefaultMutableTreeNode(bez);
    	//top.add(category);
    		//if(ostr[i].length()<5){
    	kind =new DefaultMutableTreeNode("BH ");
    	String[]bstr=BH(tvj+"n");//files("gastro/date/Data_2007/"+ostr[i]);//BH();
    	for(int b=0;b<bstr.length;b++){
    		String[]nstr=files("gastro/date/data"+new com.units.myDatum().J()+"/"+bstr[b]);
    		String bez="BH ";
    		String sp=""+bstr[b].toLowerCase();
    		//int bhpos=sp.indexOf("BH");
    		
    		bez+=sp.substring(sp.indexOf("bh")+2 , sp.indexOf("n"));
    		//nkind =new DefaultMutableTreeNode(bstr[i]);    	    		    
    		for(int n=0;n<nstr.length;n++){
    			System.out.println(nstr[b]);
    			mstr=nstr[n].substring(0,nstr[n].indexOf(".dat"));
    			//else mstr=nstr[n].substring(0,nstr[n].indexOf("N")+1);   			
    			book = new DefaultMutableTreeNode(new BookInfo(mstr));
    			kind.add(book);
    			//kind.add(nkind);
    			category.add(kind);
    			//category.add(book);
    		}
    	}    	
    	}
    }
    String[]Absatz(String jahr){
    	String[]astr=ordner("gastro/date/data"+new com.units.myDatum().J()+"/tv?");
    	java.util.List<String>mv=new java.util.ArrayList<String>();
    	for(int i=0;i<astr.length;i++){    		
    		if(astr[i].length()>5 && astr[i].toLowerCase().indexOf(jahr)>-1){
    			mv.add(astr[i].toLowerCase());
    		}    		
    	}
    	String[]nstr=new String[mv.size()];
    	for(int i=0;i<mv.size();i++){
    		nstr[i]=mv.get(i).toString();
    		System.out.println(nstr[i]);
    	}
    	return nstr;
    }
    String[]BH(String jahr){
    	String[]astr=ordner("gastro/date/data"+new com.units.myDatum().J()+"/tv?");
    	java.util.List<String>mv=new java.util.ArrayList<String>();
    	for(int i=0;i<astr.length;i++){    	
    		if(astr[i].length()<6 && astr[i].toLowerCase().indexOf(jahr)>-1){
    			mv.add(astr[i].toLowerCase());
    		}    		
    	}
    	String[]nstr=new String[mv.size()];
    	for(int i=0;i<mv.size();i++){
    		nstr[i]=mv.get(i).toString();
    		System.out.println(nstr[i]);
    	}
    	return nstr;
    }
    String[][]inclomen(String[]str){
		String[][]nstr=new String[0][0];
		if(str.length>0 &&str[0]!=null){
			String[]w=new com.options.ausTeilen().koma(str[0]);
			nstr=new String[str.length][w.length];
			for(int i=0;i<nstr.length;i++){
				w=new com.options.ausTeilen().koma(str[i]);				
				for(int x=0;x<nstr[i].length;x++){
					nstr[i][x]=w[x];
				}
			}
		}
		return nstr;
	}
	void update(String mfile){
		System.out.println(mfile);
		new ebm.KB().KB_file_T(mfile);
	}
	void zeigTab(String file,String j){
		String[]str=new com.search.sucheDate("gastro/d"+j+"/"+file+".dat").myDaten();
		String tit="";
		if(file.charAt(0)=='K' || file.charAt(0)=='k')tit="laut Kassabuch ";else tit="laut Fahrerbuch ";
		if(str.length>0){
			String[]headers={"RNummer","Datum","KundenCode","R-Betrag","Total","MA","-/+"};
			javax.swing.JScrollPane js=new javax.swing.JScrollPane();
			js.setViewportView(new ebm.RcTab(inclomen(str),headers).tab());			
			javax.swing.JFrame jf=new com.options.frame(str.length+" Zeilen "+tit+"in Tabellen Ansicht ",180);
			jf.getContentPane().add(js,java.awt.BorderLayout.PAGE_START);
			jf.pack();
			jf.setSize(600,600);
			jf.setLocation(400,100);
			jf.setVisible(true);
		}
	}
        
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        if (useSystemLookAndFeel) {
            try {
                UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println("Couldn't use system look and feel.");
            }
        }

        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        JFrame frame = new JFrame("Berichten");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        myTree newContentPane = new myTree();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
