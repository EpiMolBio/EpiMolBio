/**

    Authors: Roberto Reinosa Fernández, África Holguín Fernández, Paloma Troyano Hernáez

    This software is licensed under Creative Commons Attribution-NonCommercial-NoDerivatives 4.0

    This license enables reusers to copy and distribute the material in any medium or format in unadapted form only, 
    for noncommercial purposes only, and only so long as attribution is given to the creator. 
     
    CC BY-NC-ND includes the following elements:
    
        BY: credit must be given to the creator.
        NC: Only noncommercial uses of the work are permitted.
        ND: No derivatives or adaptations of the work are permitted.

*/

package com.mycompany.epimolbio;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import static com.mycompany.epimolbio.Interfaz.Error;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Abrir_Informacion {

    //Abre los pdf de los botones de información.
    //Open the PDFs from the information buttons.
    
    public static void abrirPdf(String nombrePDF, String nombreTemporal) {
        
        InputStream inputStream = Abrir_Informacion.class.getClassLoader().getResourceAsStream(nombrePDF);

        if (inputStream != null) {
            
            try {
                
                File tempFile = new File(System.getProperty("java.io.tmpdir"), nombreTemporal);

                try (OutputStream outputStream = new FileOutputStream(tempFile)) {
                    
                    byte[] buffer = new byte[1024];
                    int bytesRead;

                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        
                        outputStream.write(buffer, 0, bytesRead);
                        
                    }
                }

                Desktop desktop = Desktop.getDesktop();
                desktop.open(tempFile);

            } catch (IOException e) {
                
                Error.setLocationRelativeTo(null);
                Error.setVisible(true);
                
            }
        } 
    }
}
