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

import static com.mycompany.epimolbio.Interfaz.btn_calcular_alineamientos_multiples;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_buscar_reemplazar;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_busqueda_secuencias;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_codones_polimorfismos;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_consensos;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_conservacion;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_conservacion_codones;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_conservacion_pol;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_contar_secuencias;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_dotplot;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_filtrado_secuencias_parciales;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_filtro_especifico;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_flanqueantes_rastreador;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_frecuencia_mutacion;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_fusionar_archivos;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_fusionar_secuencias;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_otras_mutaciones_pol;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_polimorfismos;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_programar_funciones;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_rastreador_sars_cov_2;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_secuencias_unicas;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_similitud;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_similitud_parcial;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_similitud_rastreador;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_simples;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_traduccion;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_transmitidas;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_wukabat;
import static com.mycompany.epimolbio.Interfaz.color2;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_eliminar_inserciones;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_busqueda_secuencias_conservadas;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_filtrado_encabezado;
import static com.mycompany.epimolbio.Interfaz.btn_calcular_mdr_codones;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Botones_Calcular {
    
    /*Gestiona el color del botón de calcular pulsado. Cuando uno se pulsa, 
    todos se ponen en el color correspondiente a botón pulsado*/
    
    public static void llamadaCalcular(){
        
        btn_calcular_simples.setBackground(color2);
        btn_calcular_transmitidas.setBackground(color2);
        btn_calcular_mdr_codones.setBackground(color2);
        btn_calcular_otras_mutaciones_pol.setBackground(color2);
        btn_calcular_conservacion_pol.setBackground(color2);
        btn_calcular_rastreador_sars_cov_2.setBackground(color2);
        btn_calcular_similitud.setBackground(color2);
        btn_calcular_similitud_parcial.setBackground(color2);
        btn_calcular_busqueda_secuencias_conservadas.setBackground(color2);
        btn_calcular_similitud_rastreador.setBackground(color2);
        btn_calcular_flanqueantes_rastreador.setBackground(color2);
        btn_calcular_alineamientos_multiples.setBackground(color2);
        btn_calcular_dotplot.setBackground(color2);
        btn_calcular_consensos.setBackground(color2);
        btn_calcular_wukabat.setBackground(color2);
        btn_calcular_frecuencia_mutacion.setBackground(color2);
        btn_calcular_filtrado_encabezado.setBackground(color2);
        btn_calcular_traduccion.setBackground(color2);
        btn_calcular_filtro_especifico.setBackground(color2);
        btn_calcular_buscar_reemplazar.setBackground(color2);
        btn_calcular_busqueda_secuencias.setBackground(color2);
        btn_calcular_filtrado_secuencias_parciales.setBackground(color2);
        btn_calcular_programar_funciones.setBackground(color2);
        btn_calcular_fusionar_archivos.setBackground(color2);
        btn_calcular_eliminar_inserciones.setBackground(color2);
        btn_calcular_contar_secuencias.setBackground(color2);
        btn_calcular_secuencias_unicas.setBackground(color2);
        btn_calcular_fusionar_secuencias.setBackground(color2);
        btn_calcular_conservacion.setBackground(color2);
        btn_calcular_conservacion_codones.setBackground(color2);
        btn_calcular_codones_polimorfismos.setBackground(color2);
        btn_calcular_polimorfismos.setBackground(color2);
        
    }
    
}
