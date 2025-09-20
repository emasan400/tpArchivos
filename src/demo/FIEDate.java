package demo;

import java.io.RandomAccessFile;
import java.util.Calendar;

public class FIEDate {
    private static RandomAccessFile raf;

    public FIEDate(RandomAccessFile raf) {
        this.raf = raf;
    }

    public static String read() throws Exception {
        String datos = "";

        int fechaALeer = raf.readUnsignedShort();

        // Día = bits 0–4
        int d = fechaALeer & 0b11111;

        // Mes = bits 5–8
        int m = (fechaALeer >> 5) & 0b1111;

        // Año codificado = bits 9–15
        int a = (fechaALeer >> 9) & 0b1111111;

        // Traducir año
        int year;
        if (a < 100) {
            year = 2000 + a;
        } else {
            year = 1999 - a + 100;
        }

        // Formatear como
        datos = d + "-" + m + "-" + year;

        return datos;
    }

    public void write() throws Exception {
        Calendar cal = Calendar.getInstance();

        int dia = cal.get(Calendar.DAY_OF_MONTH);
        int mes = cal.get(Calendar.MONTH) + 1; // enero=0
        int year = cal.get(Calendar.YEAR);

        // Codificar año según consigna
        int a = (year >= 2000) ? (year - 2000) : (1999 - year + 100);

        // Empaquetar en 16 bits
        int fechaFinal = (a << 9) | (mes << 5) | dia;

        raf.writeShort(fechaFinal); // guarda 2 bytes
    }
}
