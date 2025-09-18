package demo;

import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;

public class FIERegType
{
	private RandomAccessFile raf;
	
	public FIERegType(RandomAccessFile raf)
	{
		this.raf = raf;
	}
	
	public int escribirRegType() throws Exception
	{
		int camposDefinidos = 0;
		
		
		String ingreso = "";
		
		do{
			
		}while(ingreso != "-");
		
		return camposDefinidos;
	}

	public void leerRegType() throws Exception
	{
		raf.writeShort();
	}
}
