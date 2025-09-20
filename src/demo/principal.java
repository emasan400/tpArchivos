package demo;
/*
 * Grupo 1: Tapia, Nemi, Meneclier, Sanchez
 * 
 * TP Archivos:
 * 
 * Dado un archivo cuya estructura se describe más abajo, se
 * pide desarrollar un programa para mostrar su contenido por pantalla. Además, se debe actualizar la fecha de último
 * acceso con la fecha del sistema (ver estructura).
 * Luego, habrá que desarrollar otro programa que interactúe con el usuario para generar un nuevo archivo, con la misma
 * estructura que el anterior, y los datos que ingrese el usuario
 * 
 * Ultima fecha modif: 19/09
 */

import java.io.RandomAccessFile;
import java.util.*;

import static demo.FIERegType.escribirRegType;

public class principal
{
    private static void escribirArchivo(RandomAccessFile raf, String nombreArchivo, Scanner scanner) throws Exception{

        String directorioActual = System.getProperty("user.dir");

        Random random = new Random();
        int nroSerie = random.nextInt(10000)+1;

        FIEInteger i = new FIEInteger(raf);
        FIEString s = new FIEString(raf);
        FIEDate d = new FIEDate(raf);

        //Voy al inicio del archivo
        raf.seek(0);

        //Guardo nroSerieString
        i.write(nroSerie);

        //Guardo nombreArchivo
        s.write(directorioActual+"\\"+nombreArchivo);

        //Escribir date
        d.write();

        //Completar con RegType
        int campos = 1;

        //Definir cantidad de campos:
        int cantCampos = 0;
        System.out.println("Ingrese cantidad de campos configurables: ");
        cantCampos = scanner.nextInt();
        scanner.nextLine();

        //Escribo cantCampos
        i.write(cantCampos);

        //Guardo datos en un map
        Map<Integer,String> camposConfigurados = new LinkedHashMap<>();

        //Escribo registros en archivo con iteracion < cantCampos
        for(int val = 0; val < cantCampos; val++)
        {
            System.out.println("Nombre de campo " + (val+1) + ": ");
            String nomAtributo = scanner.nextLine();

            //Escribo en archivo campos configurados
            escribirRegType((val+1), nomAtributo);

            //Guardo dato para escribir registros
            camposConfigurados.put((val+1), nomAtributo);

        }

        //Definir cantidad de registros:
        int cantRegistros = 0;
        System.out.println("Ingrese cantidad de contactos a guardar: ");
        cantRegistros = scanner.nextInt();
        scanner.nextLine();

        //Escribo cantRegistros
        i.write(cantRegistros);

        // Escribo registros
        for(int val = 0; val < cantRegistros; val++)
        {
            for(Map.Entry<Integer,String> entry : camposConfigurados.entrySet())
            {
                //Entro y leo camposConfigurados
                Integer key = entry.getKey();
                String value = entry.getValue();

                //Ingreso valor a escribir
                System.out.println("Ingrese " + value + " de registro " + (val+1) + " (Saltar campo con \"-\"): ");
                String contenidoCampo = scanner.nextLine();

                //Escribo con escape
                if(!Objects.equals(contenidoCampo, "-"))
                {
                    i.write(key);
                    s.write(contenidoCampo);
                }
            }
        }
    }

    private static void leerArchivo(RandomAccessFile raf, String nombreArchivo, Scanner scanner, Archivo archivoACrear) throws Exception {

        //Voy al inicio del archivo
        raf.seek(0);

        //Leo contenido y guardo en clase archivo!
        //Numero de serie
        archivoACrear.setNumeroSerie(FIEInteger.read());
        //Nombre archivo
        archivoACrear.setNombreArchivo(FIEString.read());
        //FechaModif
        archivoACrear.setFechaModif(FIEDate.read());
        //CamposConfigurados
        archivoACrear.setCamposConfigurados(FIEInteger.read());

        //Creacion del estilo de archivo
        System.out.println("----[CONTENIDO DEL ARCHIVO]--------------------");
        System.out.println("Nro. de serie: " + archivoACrear.getNumeroSerie());

        //Va "\\" por ERROR de caracter escape
        System.out.println("Full filename: " + archivoACrear.getNombreArchivo());
        System.out.println("Fecha de ultimo acceso: " + archivoACrear.getFechaModif());
        System.out.println("Cantidad de campos configurados: " + archivoACrear.getCamposConfigurados());

        // Itero Atributos generados
        for(int i = 0; i < archivoACrear.getCamposConfigurados(); i++)
        {
            int nroCampo = FIEInteger.read();
            String descripcionAMostrar = FIEString.read();

            //archivoACrear.getCampo(i).getdCampo();

            System.out.println("Campo [codigo: " + nroCampo + ", descripcion: " + descripcionAMostrar + "]");

            archivoACrear.setNomCamposConfigurados(descripcionAMostrar);
        }

        /*
        //Ingreso cant de contactos a crear
        System.out.println("Ingrese cantidad de personas a crear: ");
        Integer cantContactos = scanner.nextInt();
        scanner.nextLine();
        */

        //Leo cant de contactos
        int cantContactos = FIEInteger.read();

        //Setteo dato de cant de contactos en objeto archivo
        archivoACrear.setCantRegistros(cantContactos);

        //Dato Cant Registros generados
        System.out.println("Cantidad de Contactos: " + archivoACrear.getCantRegistros());

        System.out.println("-----------------------------------------------");

        // Iterar por cada contacto en el archivo
        for (int i = 0; i < cantContactos; i++) {
            // Variables para controlar la lectura del registro
            int ultimoCampoLeido = 0;
            boolean finRegistro = false;

            //Leo si: CorteControl true y que posActual del puntero es menor al final del archivo (caso ultimo contacto)
            while (!finRegistro && raf.getFilePointer() < raf.length()) {
                long posicionActual = raf.getFilePointer();

                // Verifico que haya dato a leer despues de mi pos actual (Final del archivo por si me paso (Sin ultimo campoConf))
                if (posicionActual + 4 > raf.length()) {
                    finRegistro = true;
                    break;
                }

                int nroCampo = FIEInteger.read();

                // Verificar si este campo pertenece al siguiente registro. Como leo secuencial siempre. Me avisa de cambio de reg.
                if (nroCampo <= ultimoCampoLeido) {
                    // Retroceder y terminar este registro
                    raf.seek(posicionActual);
                    finRegistro = true;
                } else {
                    // Leer el valor del campo
                    String valorCampo = FIEString.read();

                    // Mostrar el campo con su nombre
                    String nombreCampo = archivoACrear.getNomCamposConfigurados(nroCampo - 1);
                    System.out.println(nombreCampo + " : " + valorCampo);

                    ultimoCampoLeido = nroCampo;
                }
                if(finRegistro == true)
                    // Línea separadora solo entre registros
                    System.out.println("-----------------------------------------------");
            }
        }

        System.out.println("-----------------------------------------------");

        System.out.println();
        System.out.println("----[FIN CONTENIDO DEL ARCHIVO]-----------------");
        /*
        for(int i = 0; i < archivoACrear.getCantRegistros(); i++)
        {

            archivoACrear;
        }
        */
        /* Ya esta hecho en funcion escribirArchivo! Solo quiero leer el archivo

        for(int i = 0; i < archivoACrear.getCantRegistros(); i++) --> Usar!
        {
            for(int j = 0; j < archivoACrear.getCamposConfigurados(); j++)
            {
                //Almaceno contenido de campo
                String contenidoCampo = new String();

                //Consigo nombre del campo --> Usar
                String nomCampo = archivoACrear.getCampo(j).getdCampo();

                System.out.println("Ingrese " + nomCampo + " para persona "+ (i+1) +" (\"-\" para no agregar info): ");
                contenidoCampo = scanner.nextLine();


                //Meto info en el map infoCampo solo si tiene contenido
                if(!contenidoCampo.equals("-"))
                {
                    --> Usar
                    archivoACrear.getCampo(j).setiCampo(contenidoCampo);
                }

            }
         */

        /*
            //Recorro lista
            for(Campo c : archivoACrear.getListCampo()){
                int nroCampo = c.getnCampo();
                String descCampo = c.getdCampo();
                String infoCampo = c.getiCampo();

                System.out.println(nroCampo + " : " + descCampo + " : " + infoCampo + "\n");
            }
            //Separador de personas
            System.out.println("-----------------------------------------------\n");
            */

        //Confirmacion de generacion del archivo
        System.out.println();
        System.out.println("-----------------------------------------------");
        System.out.println("Archivo generado: " + archivoACrear.getNombreArchivo());
        System.out.println("-----------------------------------------------");
        System.out.println();
    }


	public static void main(String[] args) throws Exception
	{
		//Int de ingreso de datos
		Integer manejoDeMenu;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese nombre y extension del archivo: ");

        Archivo archivoACrear = new Archivo();

        String nombreArchivo = scanner.nextLine();

        nombreArchivo = nombreArchivo;

        RandomAccessFile raf = new RandomAccessFile(nombreArchivo,"rw");

		//Realizar proceso hasta que usuario ingrese 0
		do{
			
			//Opciones de menu
			System.out.println("Seleccione opcion: ");
			System.out.println("1 - Escribir archivo ");
			System.out.println("2 - Mostrar ");
			System.out.println("0 - Salir ");
			
			//Ingreso de opcion
			manejoDeMenu = scanner.nextInt();
			//Consumir /n para evitar ERROR de buffer
			scanner.nextLine(); 
			
			//Opcion 1
			if(manejoDeMenu == 1)
			{
                escribirArchivo(raf, nombreArchivo, scanner);
            }
				
			
			//Opcion 2
			if(manejoDeMenu == 2)
			{
                leerArchivo(raf, nombreArchivo, scanner, archivoACrear);
            }
			
		}while(manejoDeMenu != 0);
		
		//Libero Memoria
		scanner.close();

        //Cerrar archivo
        raf.close();
		
	}
}
