package egslver;
import java.io.*; 
import java.util.zip.*; 
 
public class gunzip 
{ 
  private static final int BLOCKSIZE = 8192; 
 
  public static void main( String[] args ) 
  { 
    if ( args.length != 1 ) { 
      System.out.println( "Usage: gunzip source" ); 
      return; 
    } 
 
    String zipname, source; 
 
    if ( args[0].toLowerCase().endsWith(".gz") ) { 
      zipname = args[0]; 
      source = zipname.substring( 0, zipname.length() - 3 ); 
    } 
    else { 
      zipname = args[0] + ".gz"; 
      source = args[0]; 
    } 
 
    InputStream  gzis = null; 
    OutputStream fos  = null; 
 
    try 
    { 
      gzis = new GZIPInputStream( new FileInputStream(zipname) ); 
      fos  = new FileOutputStream( source ); 
 
      byte[] buffer = new byte[ BLOCKSIZE ]; 
      for ( int length; (length = gzis.read(buffer, 0, BLOCKSIZE)) != -1; ) 
        fos.write( buffer, 0, length ); 
    } 
    catch ( IOException e ) { 
      System.out.println( "Error: Couldn't decompress " + args[0] ); 
    } 
    finally { 
      if ( fos != null ) 
        try { fos.close(); } catch ( IOException e ) { } 
      if ( gzis != null ) 
        try { gzis.close(); } catch ( IOException e ) { } 
    } 
  } 
}// created on 29.10.2007 at 19:24
