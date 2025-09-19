package demo;

import java.io.RandomAccessFile;
import java.util.Random;
import java.util.Scanner;

import static demo.FIERegType.*;

public class principalMain
{
	
	private static void escribirArchivo(RandomAccessFile raf, String nombreArchivo) throws Exception{

        Scanner scanner = new Scanner(System.in);

		String directorioActual = System.getProperty("user.dir");
		
		Random random = new Random();
		int nroSerie = random.nextInt(10000)+1;
		String nroSerieString = String.valueOf(nroSerie);
		
		//Voy al inicio del archivo
		raf.seek(0);

        //Guardo nroSerieString
		FIEString.write(nroSerieString);

        //Guardo nombreArchivo
		FIEString.write(nombreArchivo);

        //Escribir date
		FIEDate.write();
		
		//Completar con RegType
        int campos = 1;

        //Definir cantidad de campos:
        int cantCampos = 0;
        System.out.println("Ingrese cantidad de campos configurables: ");
        cantCampos = scanner.nextInt();
        scanner.nextLine();

        //Escribo cantCampos
        FIEInteger.write(cantCampos);

        //Escribo registros en archivo con iteracion < cantCampos
        for(int val = 0; val < cantCampos; val++)
        {
            System.out.println("Nombre de atributo " + (campos) + ": ");
            String nomAtributo = scanner.nextLine();

            escribirRegType(campos, nomAtributo);

        }

        //Definir cantidad de registros:
        int cantRegistros = 0;
        System.out.println("Ingrese cantidad de contactos a guardar: ");
        cantRegistros = scanner.nextInt();
        scanner.nextLine();
		*/
		
	}
	
	static void main(String[] args) throws Exception
	{	
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Ingrese nombre del archivo: ");
		
		String nombreArchivo = scanner.nextLine();
		
		nombreArchivo = nombreArchivo + ".dat";
		
		RandomAccessFile raf = new RandomAccessFile(nombreArchivo,"rw");
		
		escribirArchivo(raf, nombreArchivo);
		
	}

}
