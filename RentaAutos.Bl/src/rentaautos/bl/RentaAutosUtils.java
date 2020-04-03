/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rentaautos.bl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Isla
 */
public class RentaAutosUtils {
    
    public static byte[]ConvertirArchivosBytes(File archivo) throws FileNotFoundException, IOException
    {
        byte[] bytes=new byte[(int) archivo.length()];
        try(FileInputStream stream=new FileInputStream(archivo)){
          stream.read(bytes);  
        } 
        return bytes;
    }
    
}
