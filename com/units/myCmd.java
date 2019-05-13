// created on 14.07.2003 at 16:54
package com.units;
import java.io.*;
public class myCmd {
	private String command;
	private Runtime rt;
	
	public myCmd(String comand){
		command=comand;
		rt = rt.getRuntime();
		
		try {
			rt.exec(command);			
		}catch(IOException e){System.out.println(e);}
	}
	public static void main(String[] args) {
	  new myCmd("C:/Programme/Real/RealPlayer/realplay.exe / c:/ebmgastro/Gastro/gastro/audio/Eldonia.mp3");
	  new myCmd("C:/Programme/Internet Explorer/IEXPLORE.EXE c:/ebmgastro/Gastro/gastro/audio/Eldonia.mp3");
	  new myCmd("C:/Programme/Windows Media Player/wmplayer.exe c:/ebmgastro/Gastro/gastro/audio/Arabi.mp3");
	}
}
