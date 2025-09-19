package demo;

import java.io.RandomAccessFile;
import java.nio.charset.StandardCharsets;

public class FIEString
{
	private static RandomAccessFile raf;
	
	public FIEString(RandomAccessFile raf)
	{
		FIEString.raf = raf;
	}
	
	public String read() throws Exception {
		
        int lengthByte = raf.readUnsignedByte();
        
        int length = 0;
        if(lengthByte < 255)
        	{
        	
        		length = lengthByte;
        		
        	}else{
        		
        		raf.readUnsignedShort();
        		
        	}
        
        byte[] buffer = new byte[length];
        
        raf.readFully(buffer);
        
        return new String(buffer, StandardCharsets.UTF_8);
    }
	
    public static void write(String s) throws Exception {
    	
        byte[] stringEnBytes = s.getBytes(StandardCharsets.UTF_8);
        
        int length = stringEnBytes.length;
        if (length < 255){
        	
        	raf.writeByte(length); 
        	
        }else {
        	raf.writeByte(255);
        	raf.writeShort(length);
        	}
        
        raf.write(stringEnBytes);
    }
	
}
