package demo;

import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

public class principalMain
{
	
	private static void escribirArchivo(RandomAccessFile raf, FIEString s, FIEInteger i, FIEDate d, String nombreArchivo) throws Exception{
		
		String directorioActual = System.getProperty("user.dir");
		
		Random random = new Random();
		Integer nroSerie = random.nextInt(10000)+1;
		String nroSerieString = nroSerie.toString();
		
		//Voy al inicio del archivo
		raf.seek(0);
		s.write("----[CONTENIDO DEL ARCHIVO]--------------------");
		s.write("Nro. de serie: " + nroSerieString);
		s.write("Full filename: " + nombreArchivo);
		s.write("Fecha de ultimo acceso: ");
		
		d.write();
		
		//Completar con RegType
		int campos = 00;
		s.write("Cantidad de campos configurados: ");
		
		
	}
	
	public static void main(String[] args) throws Exception
	{	
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Ingrese nombre del archivo: ");
		
		String nombreArchivo = scanner.nextLine();
		
		nombreArchivo = nombreArchivo + ".dat";
		
		RandomAccessFile raf = new RandomAccessFile(nombreArchivo,"rw");
		
		FIEInteger i = new FIEInteger(raf);
		FIEString s = new FIEString(raf);
		FIEDate d = new FIEDate(raf);
		
		
		escribirArchivo(raf,s,i,d, nombreArchivo);
		
	}

}
