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

import static com.mycompany.epimolbio.Interfaz.Error;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import static com.mycompany.epimolbio.Interfaz.jCheckBox27;
import static com.mycompany.epimolbio.Interfaz.jCheckBox28;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Generador_Archivos_Traductor_Gaps {
    
    /*Genera los archivos de la funcionalidad Traducción. Se generan archivos .fasta con las secuencias de entrada traducidas y/o con los gaps eliminados.
    Hace uso de la clase Traductor_Gaps.*/
    
    /*Generates files for the Translation functionality. .fasta files are created with the translated input sequences and/or with gaps removed.
    Utilizes the Traductor_Gaps class.*/
    
    public static void cargarGeneradorArchivosTraductorGaps(String carpetaEntrada, String archivoEntrada, int selectorFiltro[], int traducirNoFiltro, int gapsNoFiltro, int marco, String directorioSalida, 
            int seleccionFiltro, int traducirFiltro, String separador){
        
        try{
        
            String directorio = carpetaEntrada;

            final File carpeta = new File(directorio);

            String ficheros[] = new String[5000];

            for(int i = 0; i < ficheros.length; i++) {

                ficheros[i] = "";

            }

            int a = 0;

            String Fichero;

            for (File ficheroEntrada : carpeta.listFiles()) {

                ficheros[a] = (ficheroEntrada.getName());
                a++;

            }

            for (String fichero : ficheros) {
                
                if (fichero.equals("")) {
                    
                    break;
                    
                }
                
                Fichero = carpetaEntrada + fichero;
                archivoEntrada = Fichero;
                String refiltrado = "";
                
                for (int a1 = 0; a1 < fichero.length(); a1++) {
                    
                    if (fichero.toCharArray()[a1] == '.') {
                        
                        break;
                        
                    }
                    
                    refiltrado += fichero.toCharArray()[a1];
                    
                }
                
                if (jCheckBox28.isSelected() || jCheckBox27.isSelected()) {
                    
                    Traductor_Gaps.cargarTraductorGaps(archivoEntrada, traducirNoFiltro, gapsNoFiltro, marco, directorioSalida, fichero);
                    continue;
                    
                }
                
                Filtrado_Encabezado_Multiple.cargarFiltroEncabezadoMultiple(selectorFiltro, archivoEntrada, directorioSalida, seleccionFiltro, refiltrado, traducirFiltro, separador);
            
            }
        
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Generador_Archivos_Traductor_Gaps.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
    
}
