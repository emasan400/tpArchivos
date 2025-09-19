package demo;

import java.io.RandomAccessFile;

public class FIEInteger
{
	private static RandomAccessFile raf;
	
	public FIEInteger(RandomAccessFile raf)
	{
		this.raf = raf;
	}
	
	public int read() throws Exception
	{
		return raf.readUnsignedShort();
	}

	public static void write(int i) throws Exception
	{
		raf.writeShort(i);
	}

}
