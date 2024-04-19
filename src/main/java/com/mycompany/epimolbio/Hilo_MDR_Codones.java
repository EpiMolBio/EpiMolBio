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

import static com.mycompany.epimolbio.Interfaz.Calculos_Finalizados;
import static com.mycompany.epimolbio.Interfaz.Error;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import static com.mycompany.epimolbio.Interfaz.jCheckBox4;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Hilo_MDR_Codones implements Runnable{
    
    public static Thread t_mdr_codones;
    
    public String archivoCarga;
    public String archivoGuardado;
    public int tipoMDRCodones;
    public String codonesAnalizar;
    
    //Gestiona el hilo de MDR Codones.
    
    public Hilo_MDR_Codones(String archivoCarga, String archivoGuardado, int tipoMDRCodones, String codonesAnalizar){
        
        this.archivoCarga = archivoCarga;
        this.archivoGuardado = archivoGuardado;
        this.tipoMDRCodones = tipoMDRCodones;
        this.codonesAnalizar = codonesAnalizar;
        
        t_mdr_codones = new Thread(this, "Hilo_MDR_Codones");
        t_mdr_codones.start();
        
    }
    
    @Override
    public void run(){
   
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            if(!jCheckBox4.isSelected()){
            
                MDR_Codones.cargarMDRCodones(this.archivoCarga, this.archivoGuardado, this.tipoMDRCodones, this.codonesAnalizar);
            
            }else{
                
                String codonesAnalizarSeparado[] = this.codonesAnalizar.split(",");
                
                for (String codonesAnalizarSeparado1 : codonesAnalizarSeparado) {
                    
                    MDR_Codones.cargarMDRCodones(this.archivoCarga, this.archivoGuardado + "/" + codonesAnalizarSeparado1 + ".html", this.tipoMDRCodones, codonesAnalizarSeparado1);
                    
                }
                
            }
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_MDR_Codones.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
