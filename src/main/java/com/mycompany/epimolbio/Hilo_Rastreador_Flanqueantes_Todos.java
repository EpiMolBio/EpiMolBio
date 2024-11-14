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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Hilo_Rastreador_Flanqueantes_Todos implements Runnable {
    
    public static Thread t_rastreador_orf_todos;
    
    public String guardado;
    public String carga;
    public int tipoSecuencia;
    public int rangoInf;
    public int rangoSup;
    public String delante;
    public String detras;
    public int tamanoSecuencia;
    
    //Gestiona el hilo del Rastreador por secuencias Flanqueantes, cuando se hace la búsqueda de todas las regiones.
    //Manages the Flanking Sequences Tracker thread when searching all regions.
    
    public Hilo_Rastreador_Flanqueantes_Todos(String carga, String guardado, int tipoSecuencia, int rangoInf, int rangoSup, String delante, String detras, int tamanoSecuencia){
    
        this.guardado = guardado;
        this.carga = carga;
        this.tipoSecuencia = tipoSecuencia;
        this.rangoInf = rangoInf;
        this.rangoSup = rangoSup;
        this.tamanoSecuencia = tamanoSecuencia;
        
        t_rastreador_orf_todos = new Thread(this, "Hilo_Rastreador_Flanqueantes_Todos");
        t_rastreador_orf_todos.start();
        
    }
    
    @Override
    public void run(){
        
        try {
            
            btn_presionado = true;
            
            Botones_Calcular.llamadaCalcular();
            
            String proteinas[] = {"nsp1 (Leader)", "nsp2", "nsp3 (Incluye Papain Like Proteasa)", "Papain Like Proteasa",
                "nsp4", "nsp5 (3-C-Like Proteasa)", "nsp6", "nsp7", "nsp8", "nsp9", "nsp10", "nsp11", "nsp12 (RdRp)", "nsp13 (Helicasa)", "nsp14 (ExoN)", "nsp15 (EndoARNasa)",
                "nsp16 (2'-O-Metiltransferasa)", "S (Spike)", "ORF3a", "E (Envuelta)", "M (Membrana)", "ORF6", "ORF7a", "ORF7b", "ORF8", "N (Nucleocapside)", "ORF10"};
            
            for(int i = 1; i < 28; i++){
            
                Rastreador_Flanqueantes.cargarRastreadorFlanqueantes(this.carga, this.guardado + "/" + proteinas[i-1] + "_", this.tipoSecuencia, i, this.rangoInf, this.rangoSup, this.delante, this.detras, this.tamanoSecuencia);
            
            }
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            btn_presionado = false;
            
            Calculos_Finalizados.setLocationRelativeTo(null);
            Calculos_Finalizados.setVisible(true);
            
        } catch (Exception ex) {
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Hilo_Rastreador_Flanqueantes_Todos.class.getName()).log(Level.SEVERE, null, ex);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
        }
    }
}
