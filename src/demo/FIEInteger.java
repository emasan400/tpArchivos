package demo;

import java.io.RandomAccessFile;

public class FIEInteger
{
	private RandomAccessFile raf;
	
	public FIEInteger(RandomAccessFile raf)
	{
		this.raf = raf;
	}
	
	public int read() throws Exception
	{
		return raf.readUnsignedShort();
	}

	public void write(int i) throws Exception
	{
		raf.writeShort(i);
	}

}
