package demo;

import java.io.RandomAccessFile;
import java.util.LinkedHashMap;
import java.util.Map;
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

        //Guardo datos en un map
        Map<Integer,String> camposConfigurados = new LinkedHashMap<>();

        //Escribo registros en archivo con iteracion < cantCampos
        for(int val = 0; val < cantCampos; val++)
        {
            System.out.println("Nombre de atributo " + (campos) + ": ");
            String nomAtributo = scanner.nextLine();

            //Escribo en archivo campos configurados
            escribirRegType(campos, nomAtributo);

            //Guardo dato para escribir registros
            camposConfigurados.put(campos, nomAtributo);

        }

        //Definir cantidad de registros:
        int cantRegistros = 0;
        System.out.println("Ingrese cantidad de contactos a guardar: ");
        cantRegistros = scanner.nextInt();
        scanner.nextLine();

        // Escribo registros
        for(int val = 0; val < cantRegistros; val++)
        {
            for(Map.Entry<Integer,String> entry : camposConfigurados.entrySet())
            {
                //Entro y leo camposConfigurados
                Integer key = entry.getKey();
                String value = entry.getValue();

                //Ingreso valor a escribir
                System.out.println("Ingrese " + value + " (Saltar campo con \"_\"): ");
                String contenidoCampo = scanner.nextLine();

                //Escribo con escape
                if(contenidoCampo != "-")
                {
                    FIEInteger.write(key);
                    FIEString.write(contenidoCampo);
                }
            }
        }
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
