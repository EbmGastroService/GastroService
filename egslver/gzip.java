// created on 29.10.2007 at 19:12

package egslver; 
import java.io.*; 
import java.util.zip.*; 
 
class gzip 
{ 
  private static final int BLOCKSIZE = 8192; 
 
  public static void main( String[] args ){ 
    if ( args.length != 1 ) { 
      System.out.println( "Usage: gzip source" ); 
      return; 
    } 
 
    OutputStream gzos = null; 
    InputStream  fis  = null; 
 
    try 
    { 
      gzos = new GZIPOutputStream( new FileOutputStream( args[0] + ".gz" ) ); 
      fis  = new FileInputStream( args[0] ); 
 
      byte[] buffer = new byte[ BLOCKSIZE ]; 
 
      for ( int length; (length = fis.read(buffer, 0, BLOCKSIZE)) != -1; ) 
        gzos.write( buffer, 0, length ); 
    } 
    catch ( IOException e ) { 
      System.err.println( "Error: Couldn't compress " + args[0] ); 
    } 
    finally { 
      if ( fis != null ) 
        try { fis.close(); } catch ( IOException e ) { } 
      if ( gzos != null ) 
        try { gzos.close(); } catch ( IOException e ) { } 
    } 
  } 
}
