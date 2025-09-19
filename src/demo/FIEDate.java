package demo;

import java.io.RandomAccessFile;
import java.util.Date;

public class FIEDate
{
	private RandomAccessFile raf;
	
	public FIEDate(RandomAccessFile raf)
	{
		this.raf = raf;
	}
	
	public Date read() throws Exception
	{
		//Date mide long bits
		long millis = raf.readLong();
		Date fechaLeida = new Date(millis);
		
		//Devuelve tipo long
		return fechaLeida;
	}
	
	public static void write() throws Exception
	{
		Date hoy = new Date();
        raf.writeLong(hoy.getTime());
	}
	
	/*
	public String read() throws Exception
	{
		int fechaALeer = raf.readUnsignedShort();

		//Quilombo! Enmascaramiento de bits como el profe pidio. El << y >> desplazan por bytes el puntero wtf
        int d = fechaALeer & 0b11111;
        /*
         * Ejemplo:
         * 1111101010110111
         * 0000000000011111
         * 
         * 0000000000010111
         * 
         */
        
        //Me muevo los 5 lugares reservados para dia y repito solo para los 4 bits del mes
	 /*
		int m = (fechaALeer >> 5) & 0b1111;
        
        int aa = (fechaALeer >> 9) & 0b1111111;
     */   
        /*
         * Año
         * 1111101000000000
         * 
         * Mes
         * 0000000010100000
         * 
         * Dia
         * 0000000000010111
         * 
         * ----------------
         * 
         * Resultado
         * 1111101010110111 = 64183
         * 
         */
        /*
        int aaTraducido = 0;
        if(aa < 100) {
        	
        	aaTraducido = 2000 + aa;
        	
        }else {
        	
        	aaTraducido = 1999 - aa + 100;
        	
        }
        
        return d + "/" + m + "/" + aaTraducido;
	}

	public void dateDia(Date d)
	{
		
	}
	
	public void write() throws Exception
	{
		Date cal = new Date();
		
        int dia = cal.;
      
        //Empieza en enero = 0;
        int mes = cal.get(Calendar.MONTH) + 1;
        
        int aa = cal.get(Calendar.YEAR);
        
        //Trabajo como pide consigna
        int datoAaTraducido = 0;
        
        if(aa >= 2000){
        	datoAaTraducido = aa-2000;
        }else {
        	datoAaTraducido = 1999 - aa + 100;
        }
        
        //Guardo dato en 2 bytes
        int datoAEscribir = (datoAaTraducido << 9) | (mes << 5) | dia;
        
        // 1111101010110111 = 64183
        
		raf.writeShort(datoAEscribir);
	}
	*/
}
