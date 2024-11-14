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

import static com.mycompany.epimolbio.Interfaz.btn_aceptar;
import static com.mycompany.epimolbio.Interfaz.btn_aceptar_error;
import static com.mycompany.epimolbio.Interfaz.btn_atras_alineamientos_multiples;
import static com.mycompany.epimolbio.Interfaz.btn_atras_buscar_reemplazar;
import static com.mycompany.epimolbio.Interfaz.btn_atras_busqueda_secuencias;
import static com.mycompany.epimolbio.Interfaz.btn_atras_codones_polimorfismos;
import static com.mycompany.epimolbio.Interfaz.btn_atras_consensos;
import static com.mycompany.epimolbio.Interfaz.btn_atras_conservacion;
import static com.mycompany.epimolbio.Interfaz.btn_atras_conservacion_codones;
import static com.mycompany.epimolbio.Interfaz.btn_atras_contar_secuencias;
import static com.mycompany.epimolbio.Interfaz.btn_atras_dotplot;
import static com.mycompany.epimolbio.Interfaz.btn_atras_edicion;
import static com.mycompany.epimolbio.Interfaz.btn_atras_filtrado;
import static com.mycompany.epimolbio.Interfaz.btn_atras_filtrado_secuencias_parciales;
import static com.mycompany.epimolbio.Interfaz.btn_atras_filtro_especifico;
import static com.mycompany.epimolbio.Interfaz.btn_atras_flanqueantes_rastreador;
import static com.mycompany.epimolbio.Interfaz.btn_atras_frecuencia_mutacion;
import static com.mycompany.epimolbio.Interfaz.btn_atras_fusionar;
import static com.mycompany.epimolbio.Interfaz.btn_atras_fusionar_secuencias;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_individual_tripletes;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_individual_tripletes_conservacion;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_mutaciones_polimorfismos;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_simple_transmitida;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_utilidades_conservacion_pol;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_utilidades_otras_mutaciones_pol;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_utilidades_simple;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_utilidades_transmitidas;
import static com.mycompany.epimolbio.Interfaz.btn_atras_polimorfismos;
import static com.mycompany.epimolbio.Interfaz.btn_atras_programar_funciones;
import static com.mycompany.epimolbio.Interfaz.btn_atras_rastreador_proteinas_virus;
import static com.mycompany.epimolbio.Interfaz.btn_atras_rastreador_sars_cov_2;
import static com.mycompany.epimolbio.Interfaz.btn_atras_similitud;
import static com.mycompany.epimolbio.Interfaz.btn_atras_similitud_parcial;
import static com.mycompany.epimolbio.Interfaz.btn_atras_similitud_rastreador;
import static com.mycompany.epimolbio.Interfaz.btn_atras_traduccion;
import static com.mycompany.epimolbio.Interfaz.btn_atras_wukabat;
import static com.mycompany.epimolbio.Interfaz.color1;
import static com.mycompany.epimolbio.Interfaz.btn_atras_eliminar_inserciones;
import static com.mycompany.epimolbio.Interfaz.btn_atras_busqueda_secuencias_conservadas;
import static com.mycompany.epimolbio.Interfaz.btn_atras_filtrado_encabezado;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_utilidades_mdr_codones;
import static com.mycompany.epimolbio.Interfaz.btn_atras_panel_individual_codones_polimorfismos;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Reinicio_Color_Btn_Atras {
    
    //Gestiona el color de lo botones con el texto Atrás, para que recuperen el color azul correctamente.
    //Manages the color of the buttons with the text "Back," so they correctly return to the blue color.
    
    public static void reinicioColorBotones(){
        
        btn_atras_rastreador_sars_cov_2.setBackground(color1);
        btn_atras_panel_mutaciones_polimorfismos.setBackground(color1);
        btn_atras_rastreador_proteinas_virus.setBackground(color1);
        btn_atras_panel_individual_tripletes.setBackground(color1);
        btn_atras_panel_simple_transmitida.setBackground(color1);
        btn_atras_panel_utilidades_simple.setBackground(color1);
        btn_atras_panel_utilidades_transmitidas.setBackground(color1);
        btn_atras_panel_utilidades_mdr_codones.setBackground(color1);
        btn_atras_panel_utilidades_otras_mutaciones_pol.setBackground(color1);
        btn_atras_panel_utilidades_conservacion_pol.setBackground(color1);
        btn_atras_similitud.setBackground(color1);
        btn_atras_similitud_parcial.setBackground(color1);
        btn_atras_busqueda_secuencias_conservadas.setBackground(color1);
        btn_atras_similitud_rastreador.setBackground(color1);
        btn_atras_flanqueantes_rastreador.setBackground(color1);
        btn_atras_alineamientos_multiples.setBackground(color1);
        btn_atras_dotplot.setBackground(color1);
        btn_atras_consensos.setBackground(color1);
        btn_atras_wukabat.setBackground(color1);
        btn_atras_frecuencia_mutacion.setBackground(color1);
        btn_atras_filtrado_encabezado.setBackground(color1);
        btn_atras_traduccion.setBackground(color1);
        btn_atras_filtro_especifico.setBackground(color1);
        btn_atras_buscar_reemplazar.setBackground(color1);
        btn_atras_busqueda_secuencias.setBackground(color1);
        btn_atras_filtrado_secuencias_parciales.setBackground(color1);
        btn_atras_programar_funciones.setBackground(color1);
        btn_atras_eliminar_inserciones.setBackground(color1);
        btn_atras_contar_secuencias.setBackground(color1);
        btn_atras_fusionar_secuencias.setBackground(color1);
        btn_atras_edicion.setBackground(color1);
        btn_atras_filtrado.setBackground(color1);
        btn_atras_fusionar.setBackground(color1);
        btn_atras_panel_individual_tripletes_conservacion.setBackground(color1);
        btn_atras_conservacion.setBackground(color1);
        btn_atras_conservacion_codones.setBackground(color1);
        btn_atras_panel_individual_codones_polimorfismos.setBackground(color1);   
        btn_atras_codones_polimorfismos.setBackground(color1);
        btn_atras_polimorfismos.setBackground(color1);
        btn_aceptar.setBackground(color1);
        btn_aceptar_error.setBackground(color1);
        
    }
    
}
