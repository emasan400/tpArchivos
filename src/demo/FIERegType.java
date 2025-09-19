package demo;

import java.io.RandomAccessFile;

public class FIERegType
{
    private static RandomAccessFile raf;

    public FIERegType(RandomAccessFile raf)
	{
        RandomAccessFile raf1 = this.raf;
    }

    public static void escribirRegType(int dato, String campo) throws Exception {

        FIEInteger.write(dato);
        FIEString.write(campo);
        
    }
}
