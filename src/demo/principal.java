package demo;/*
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
 * Ultima fecha modif: 03/09
 */

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.time.LocalDate;

public class principal
{
	public static void main(String[] args) throws IOException
	{
		//Creo objeto para leer ingreso por linea de comando
		Scanner scanner = new Scanner(System.in);
		
		//Int de ingreso de datos
		Integer manejoDeMenu;
		
		//Registro de nombre en objeto Archivo
		System.out.println("Ingrese nombre de archivo (CONSOLA): ");
		
		Archivo archivoACrear = new Archivo();
		
		String nombreArchivo = scanner.nextLine();
		
		
		archivoACrear.setNombreArchivo(nombreArchivo);
		
		String directorioActual = System.getProperty("user.dir");
		
		//Registro de nro Serie en objeto Archivo con numero aleatorio
		Random random = new Random();
		
		Integer nroSerie = random.nextInt(10000)+1;
		
		archivoACrear.setNumeroSerie(nroSerie);
		
		
		//Registro de fecha modificacion en objeto Archivo
		LocalDate fechaModif = LocalDate.now();
		
		archivoACrear.setFechaModif(fechaModif);
		
		
		//Realizar proceso hasta que usuario ingrese 0
		do{
			
			//Opciones de menu
			System.out.println("Seleccione opcion: ");
			System.out.println("1 - Definir registros ");
			System.out.println("2 - Mostrar ");
			System.out.println("0 - Salir ");
			
			//Ingreso de opcion
			manejoDeMenu = scanner.nextInt();
			//Consumir /n para evitar ERROR de buffer
			scanner.nextLine(); 
			
			//Opcion 1
			if(manejoDeMenu == 1)
			{
				//Valor de corte de control para atributos
				int corteDeControl = 1;
				
				//Modificador de codigo Atributo
				int codAtributo = 0;
				
				//Nombre de atributo
				String nomAtributo = new String();
				
				//Definicion de atributos de registros
				while(corteDeControl != 0) {
					//Nombre Atributo
					System.out.println("Nombre de atributo "+ (codAtributo+1) +": ");
					nomAtributo = scanner.nextLine();
					
					//Setteo en campo info obtenida
					archivoACrear.setCampo(codAtributo,nomAtributo,"");
					
					//Setteo cantidad de Atributos
					int campConfAux = archivoACrear.getCamposConfigurados();
					archivoACrear.setCamposConfigurados(campConfAux + 1);
					
					//Modifico valores auxiliares
					codAtributo = codAtributo + 1;
					
					//Seguir iterando?
					System.out.println("Seguir creando atributos? Salir ingresando 0");
					corteDeControl = scanner.nextInt();
					//Consumir /n para evitar ERROR de buffer
					scanner.nextLine(); 
				}
							
			}
				
			
			//Opcion 2
			if(manejoDeMenu == 2)
			{
				//Creacion del estilo de archivo
				System.out.println("----[CONTENIDO DEL ARCHIVO (CONSOLA)]--------------------" + "\n");
				System.out.println("Nro. de serie: " + archivoACrear.getNumeroSerie() + "\n");
				
				//Va "\\" por ERROR de caracter escape
				System.out.println("Full filename: " + directorioActual + "\\" + archivoACrear.getNombreArchivo() + "\n");
				System.out.println("Fecha de ultimo acceso: " + archivoACrear.getFechaModif() + "\n");
				System.out.println("Cantidad de campos configurados: " + archivoACrear.getCamposConfigurados() + "\n");
				
				// Itero Atributos generados
				for(int i = 0; i < archivoACrear.getCamposConfigurados(); i++)
				{
					String descripcionAMostrar = archivoACrear.getCampo(i).getdCampo();
					
					System.out.println("Campo [codigo: " + (i+1) + ", descripcion: " + descripcionAMostrar + "]\n");
				}
				
				//Ingreso cant de contactos a crear
				System.out.println("Ingrese cantidad de personas a crear: ");
				Integer cantContactos = scanner.nextInt();
				scanner.nextLine();
				
				//Setteo dato de cant de contactos en objeto archivo
				archivoACrear.setCantRegistros(cantContactos);
				
				//Dato Cant Registros generados
				System.out.println("Cantidad de Registros (contactos): " + archivoACrear.getCantRegistros() + "\n");
				
				//Separador header del body
				System.out.println("-----------------------------------------------\n");
				
				for(int i = 0; i < archivoACrear.getCantRegistros(); i++)
				{
							
					for(int j = 0; j < archivoACrear.getCamposConfigurados(); j++)
					{
						
						//Almaceno contenido de campo
						String contenidoCampo = new String();
						
						//Consigo nombre del campo
						String nomCampo = archivoACrear.getCampo(j).getdCampo();
						System.out.println("Ingrese " + nomCampo + " para persona "+ (i+1) +" (\"-\" para no agregar info): ");
						contenidoCampo = scanner.nextLine();
						
						//Meto info en el map infoCampo solo si tiene contenido
						if(!contenidoCampo.equals("-"))
						{
							archivoACrear.getCampo(j).setiCampo(contenidoCampo);
						}
						
					}
					
					//Escribo nueva persona en archivo
					//for (Entry<Integer, String> entry : archivoACrear.getInfoCampo().entrySet()) {

						//Integer nroPersona = entry.getKey();
						//String datoPersona = entry.getValue();

						//String nomCampo = archivoACrear.getDescripcionCampo(nroPersona);

						//System.out.println(nomCampo + " : " + datoPersona + "\n");
			        //}

                    for(Campo c : archivoACrear.getListCampo()){
                        int nroCampo = c.getnCampo();
                        String descCampo = c.getdCampo();
                        String infoCampo = c.getiCampo();

                        System.out.println(nroCampo + " : " + descCampo + " : " + infoCampo + "\n");
                    }
					
					//Separador de personas
					System.out.println("-----------------------------------------------\n");
					
				}
				
				//Confirmacion de generacion del archivo (va "\\" por ERROR de caracter escape)
				System.out.println();
				System.out.println("-----------------------------------------------");
				System.out.println("Archivo generado: " + directorioActual + "\\" + archivoACrear.getNombreArchivo());
				System.out.println("-----------------------------------------------");
				System.out.println();
			}
			
		}while(manejoDeMenu != 0);
		
		//Libero Memoria
		scanner.close();
		
	}
}
