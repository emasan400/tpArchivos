package demo;

import java.io.RandomAccessFile;

public class MainFIETypes
{

	public static void main(String[] args) throws Exception
	{
		RandomAccessFile raf = new RandomAccessFile("MIARCHIVO.dat","rw");

		FIEInteger i = new FIEInteger(raf);
		FIEString s = new FIEString(raf);

		i.write(123);
		i.write(456);
		i.write(789);
		
		s.write("Testeo");
		
		//Voy al inicio del archivo
		raf.seek(0);
		System.out.println(i.read());
		
		//Me muevo 
		raf.seek(raf.getFilePointer());
		System.out.println(i.read());
		
		//Me muevo de nuevo 
		raf.seek(raf.getFilePointer());
		System.out.println(i.read());
		
		raf.seek(raf.getFilePointer());
		System.out.println(s.read());
		
		s.write("Testeo2");
		System.out.println(s.read());
		
		i.write(456);
		raf.seek(raf.getFilePointer()-2);
		System.out.println(i.read());
		
		s.write("Testeo2");
		//1 es byte length y 7 letras
		raf.seek(raf.getFilePointer()-8);
		//Para leer tengo que poner el puntero al final de lo que acabo de escribir!
		//Hacer funcion para eso
		System.out.println(s.read());
		
		
		raf.close();
	}
}
