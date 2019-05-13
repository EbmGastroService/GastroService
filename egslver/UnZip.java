// created on 29.10.2007 at 19:55
package egslver;
import java.util.zip.*; 
import java.io.*; 
import java.util.*; 
 
public class UnZip 
{ 
  public static final int EOF = -1; 
 
  public static void main( String[] args ) 
  { 
    if ( args.length != 1 ) 
      System.out.println( "Usage:java UnZip zipfile" ); 
    else 
    { 
      try 
      { 
        ZipFile zf = new ZipFile( args[0] ); 
 
        for ( Enumeration<? extends ZipEntry> e = zf.entries();              e.hasMoreElements(); ) 
        { 
          ZipEntry target = e.nextElement(); 
          System.out.print( target.getName() + " ." ); 
          saveEntry( zf, target ); 
          System.out.println( ". unpacked" ); 
        } 
      } 
      catch( FileNotFoundException e ) { 
        System.out.println( "zipfile not found" ); 
      } 
      catch( ZipException e ) { 
        System.out.println( "zip error..." ); 
      } 
      catch( IOException e ) { 
        System.out.println( "IO error..." ); 
      } 
    } 
  } 
 
 
  public static void saveEntry( ZipFile zf, ZipEntry target ) 
                                throws ZipException,IOException 
  { 
    File file = new File( target.getName() ); 
 
    if ( target.isDirectory() ) 
      file.mkdirs(); 
    else 
    { 
      InputStream is = zf.getInputStream( target ); 
      BufferedInputStream bis = new BufferedInputStream( is ); 
 
      new File( file.getParent() ).mkdirs(); 
 
      FileOutputStream fos = new FileOutputStream( file ); 
      BufferedOutputStream bos = new BufferedOutputStream(fos); 
 
 
      final int EOF = -1; 
 
      for ( int c; ( c = bis.read() ) != EOF; )  // oder schneller 
        bos.write( (byte)c ); 
      bos.close(); 
      fos.close(); 
    } 
  } 
}
