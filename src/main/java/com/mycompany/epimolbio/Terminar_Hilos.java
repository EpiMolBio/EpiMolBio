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

import static com.mycompany.epimolbio.Alineamientos_Multiples.p;
import static com.mycompany.epimolbio.Interfaz.btn_presionado;
import static com.mycompany.epimolbio.Alineamientos_Multiples_Reales.p_reales;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Terminar_Hilos {
    
    //Termina con los hilos en ejecución, lanzadados desde el programa.
    
    public static void cargarTerminarHilos(){
        
        //Termina el hilo de Similitud de Homología.
        
        try{
           
            if(Hilo_Similitud.t_similitud.isAlive()){
            
                Hilo_Similitud.t_similitud.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Conservación Codones.
        
        try{
           
            if(Hilo_Conservacion_Codones.t_conservacion_codones.isAlive()){
            
                Hilo_Conservacion_Codones.t_conservacion_codones.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Polimorfismos Codones.
        
        try{
           
            if(Hilo_Polimorfismos_Codones.t_polimorfismos_codones.isAlive()){
            
                Hilo_Polimorfismos_Codones.t_polimorfismos_codones.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo la tabla de Conservación Individual.
        
        try{
           
            if(Hilo_Conservacion_Individual_Tabla.t_conservacion_individual_tabla.isAlive()){
            
                Hilo_Conservacion_Individual_Tabla.t_conservacion_individual_tabla.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de la lista de Conservación Individual.
        
        try{
           
            if(Hilo_Conservacion_Individual_Lista.t_conservacion_individual_lista.isAlive()){
            
                Hilo_Conservacion_Individual_Lista.t_conservacion_individual_lista.stop();
            
            }
            
        }catch(Exception e){}
       
        //Termina el hilo de Conservación Pol.
        
        try{
           
            if(Hilo_Conservacion_Pol.t_conservacion_pol.isAlive()){
            
                Hilo_Conservacion_Pol.t_conservacion_pol.stop();
            
            }
            
        }catch(Exception e){}
        
        try{
           
            //Termina el hilo de MDR Codones.
            
            if(Hilo_MDR_Codones.t_mdr_codones.isAlive()){
                            
                Hilo_MDR_Codones.t_mdr_codones.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Similitud Parcial.
        
        try{
           
            if(Hilo_Similitud_Parcial.t_similitud_parcial.isAlive()){
            
                Hilo_Similitud_Parcial.t_similitud_parcial.stop();
                
            }
            
        }catch(Exception e){}
        
        //Termina el hilo del Rastreador por Similitud.
        
        try{
           
            if(Hilo_Rastreador_Similitud.t_rastreador_similitud.isAlive()){
            
                Hilo_Rastreador_Similitud.t_rastreador_similitud.stop();
            
            }
            
        }catch(Exception e){}
            
        //Termina el hilo de Frecuencia de Mutación.
        
        try{
           
            if(Hilo_Frecuencia_Mutacion.t_frecuencia_mutacion.isAlive()){
            
                Hilo_Frecuencia_Mutacion.t_frecuencia_mutacion.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Eliminar Inserciones.
        
        try{
           
            if(Hilo_Eliminar_Inserciones.t_eliminar_inserciones.isAlive()){
            
                Hilo_Eliminar_Inserciones.t_eliminar_inserciones.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Búsqueda de Secuencias Conservadas.
        
        try{
           
            if(Hilo_Busqueda_Secuencias_Conservadas.t_busqueda_secuencias_conservadas.isAlive()){
            
                Hilo_Busqueda_Secuencias_Conservadas.t_busqueda_secuencias_conservadas.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo del Dot Plot.
        
        try{
           
            if(Hilo_Dot_Plot.t_dot_plot.isAlive()){
            
                Hilo_Dot_Plot.t_dot_plot.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Traducción.
        
        try{
           
            if(Hilo_Traduccion.t_traduccion.isAlive()){
            
                Hilo_Traduccion.t_traduccion.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo del Rastreador por secuencias Flanqueantes.
        
        try{
           
            if(Hilo_Rastreador_Flanqueantes.t_rastreador_flanqueantes.isAlive()){
            
                Hilo_Rastreador_Flanqueantes.t_rastreador_flanqueantes.stop();
            
            }
            
            try{
                
                Rastreador_Flanqueantes.eliminarArchivosTemporalesRastreador();
                                
            }catch(Exception Ex){}
            
        }catch(Exception e){}
        
        //Termina el hilo de posiciones Mutadas.
        
        try{
           
            if(Hilo_Posiciones_Mutadas_Tabla_Mutaciones.t_posiciones_mutadas_tabla_mutaciones.isAlive()){
            
                Hilo_Posiciones_Mutadas_Tabla_Mutaciones.t_posiciones_mutadas_tabla_mutaciones.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de la opción de subcarpetas de Posiciones Mutadas.
        
        try{
           
            if(Hilo_Tabla_Mutaciones_Subcarpetas.t_tabla_mutaciones_subcarpetas.isAlive()){
            
                Hilo_Tabla_Mutaciones_Subcarpetas.t_tabla_mutaciones_subcarpetas.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Consensos.
        
        try{
           
            if(Hilo_Consensos.t_consensos.isAlive()){
            
                Hilo_Consensos.t_consensos.stop();
            
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Fusionar Archivos.
        
        try{
           
            if(Hilo_Fusionar_Archivos.t_fusionar_archivos.isAlive()){
            
                Hilo_Fusionar_Archivos.t_fusionar_archivos.stop();
            
            }
            
        }catch(Exception e){}
       
        //Termina el hilo de Filtrado de Secuencias Parciales.
        
        try{
            
            if(Hilo_Filtrado_Secuencias_Parciales.t_filtrado_secuencias_parciales.isAlive()){
               
                Hilo_Filtrado_Secuencias_Parciales.t_filtrado_secuencias_parciales.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Mutaciones Múltiples.
        
        try{
            
            if(Hilo_Mutaciones_Multiples.t_mutaciones_multiples.isAlive()){
               
                Hilo_Mutaciones_Multiples.t_mutaciones_multiples.stop();
                
            }
            
        }catch(Exception ex){}
            
        //Termina el hilo de la opción de subcarpetas de Mutaciones Múltiples.
        
        try{
            
            if(Hilo_Mutaciones_Multiples_sc.t_mutaciones_multiples_sc.isAlive()){
               
                Hilo_Mutaciones_Multiples_sc.t_mutaciones_multiples_sc.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Fusionar Secuencias, funcionalidad no disponible desde el interfaz.
        
        try{
            
            if(Hilo_Fusionar_Secuencias.t_fusionar_secuencias.isAlive()){
               
                Hilo_Fusionar_Secuencias.t_fusionar_secuencias.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Programar Funciones.
        
        try{
            
            if(Hilo_Programar_Funciones.t_programar_funciones.isAlive()){
               
                Hilo_Programar_Funciones.t_programar_funciones.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de la tabla de Contar Secuencias.
        
        try{
            
            if(Hilo_Contar_Secuencias_Tabla.t_contar_secuencias_tabla.isAlive()){
               
                Hilo_Contar_Secuencias_Tabla.t_contar_secuencias_tabla.stop();
                
            }
            
        }catch(Exception ex){}    
        
        //Termina el hilo de Alineamientos Múltiples.
        
        try{
            
            if(Hilo_Alineamientos_Multiples.t_alineamientos_multiples.isAlive()){
               
                Hilo_Alineamientos_Multiples.t_alineamientos_multiples.stop();
                
            }
            
            try{
                
                Alineamientos_Multiples.eliminarArchivosTemporales();
                
                p.destroy();
                
            }catch(Exception Ex){}
             
        }catch(Exception ex){}
        
        //Termina el hilo de Secuencias Únicas.
        
        try{
            
            if(Hilo_Secuencias_Unicas.t_secuencias_unicas.isAlive()){
            
                Hilo_Secuencias_Unicas.t_secuencias_unicas.stop();
                
            }
            
        }catch(Exception ex){}
              
        //Termina el hilo del Filtro Específico.
        
        try{
                
            if(Hilo_Filtro_Especifico.t_filtro_especifico.isAlive()){
               
                Hilo_Filtro_Especifico.t_filtro_especifico.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Búsqueda de Secuencias.
        
        try{
            
            if(Hilo_Busqueda_Secuencias.t_busqueda_secuencias.isAlive()){
               
                Hilo_Busqueda_Secuencias.t_busqueda_secuencias.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Mutaciones por Posición.
        
        try{
            
            if(Hilo_Mutaciones_Posicion.t_mutaciones_posicion.isAlive()){
               
                Hilo_Mutaciones_Posicion.t_mutaciones_posicion.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de la opción de subcarpetas de Mutaciones por Posición.
        
        try{
            
            if(Hilo_Mutaciones_Posicion_sc.t_mutaciones_posicion_sc.isAlive()){
                    
                Hilo_Mutaciones_Posicion_sc.t_mutaciones_posicion_sc.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Contar Secuencias Mutadas.
        
        try{
            
            if(Hilo_Contar_Secuencias_Mutadas.t_contar_secuencias_mutadas.isAlive()){
               
                Hilo_Contar_Secuencias_Mutadas.t_contar_secuencias_mutadas.stop();
                
            }
            
        }catch(Exception ex){}
               
        //Termina el hilo de Coeficiente de Wu-Kabat.
        
        try{
            
            if(Hilo_Wu_Kabat.t_wu_kabat.isAlive()){
               
                Hilo_Wu_Kabat.t_wu_kabat.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Tabla MDR.
        
        try{
            
            if(Hilo_Tabla_MDR.t_tabla_mdr.isAlive()){
               
                Hilo_Tabla_MDR.t_tabla_mdr.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Lista MDR.
        
        try{
            
            if(Hilo_Lista_MDR.t_lista_mdr.isAlive()){
                   
                Hilo_Lista_MDR.t_lista_mdr.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Tabla Resumen MDR.
        
        try{
            
            if(Hilo_Tabla_Resumen_MDR.t_tabla_resumen_mdr.isAlive()){
               
                Hilo_Tabla_Resumen_MDR.t_tabla_resumen_mdr.stop();
                
            }
            
        }catch(Exception ex){}
        
        //Termina el hilo de Búscar y Reemplazar.
        
        try{
            
            if(Hilo_Buscar_Reemplazar.t_buscar_reemplazar.isAlive()){
                    
                Hilo_Buscar_Reemplazar.t_buscar_reemplazar.stop();
                
            }
            
        }catch(Exception ex){}
       
        //Termina el hilo de Marcadores.
        
        try{
            
            if(Hilo_Marcadores.t_marcadores.isAlive()){
              
                Hilo_Marcadores.t_marcadores.stop();
                    
            }
                    
        }catch(Exception ex){}
        
        //Termina el hilo de Filtro por Encabezado.
        
        try{
            
            if(Hilo_Filtrado_Encabezado_Unico.t_filtrado_encabezado_unico.isAlive()){
                
                Hilo_Filtrado_Encabezado_Unico.t_filtrado_encabezado_unico.stop();
                
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Cortar Secuencias, funcionalidad no disponible desde el interfaz.
        
        try{
            
            if(Hilo_Cortar_Secuencias.t_cortar_secuencias.isAlive()){
                
                Hilo_Cortar_Secuencias.t_cortar_secuencias.stop();
                
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Eliminar Stops, funcionalidad no disponible desde el interfaz.
        
        try{
            
            if(Hilo_Eliminar_Stops.t_eliminar_stops.isAlive()){
                
                Hilo_Eliminar_Stops.t_eliminar_stops.stop();
                
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Rastreador Flanqueantes SARS-CoV-2 de todas las regiones.
        
        try{
            
            if(Hilo_Rastreador_Flanqueantes_Todos.t_rastreador_orf_todos.isAlive()){
                
                Hilo_Rastreador_Flanqueantes_Todos.t_rastreador_orf_todos.stop();
                
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de la tabla de Otras Mutaciones Pol.
        
        try{
            
            if(Hilo_Otras_Mutaciones_Pol_Tabla.t_otras_mutaciones_pol_tabla.isAlive()){
                
                Hilo_Otras_Mutaciones_Pol_Tabla.t_otras_mutaciones_pol_tabla.stop();
                
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de la tabla resumen de Otras Mutaciones Pol.
        
        try{
            
            if(Hilo_Otras_Mutaciones_Pol_Tabla_Resumen.t_otras_mutaciones_pol_tabla_resumen.isAlive()){
                
                Hilo_Otras_Mutaciones_Pol_Tabla_Resumen.t_otras_mutaciones_pol_tabla_resumen.stop();
                
            }
            
        }catch(Exception e){}
            
        //Termina el hilo de la lista de Otras Mutaciones Pol.
        
        try{
            
            if(Hilo_Otras_Mutaciones_Pol_Lista.t_otras_mutaciones_pol_lista.isAlive()){
                
                Hilo_Otras_Mutaciones_Pol_Lista.t_otras_mutaciones_pol_lista.stop();
                
            }
            
        }catch(Exception e){}
        
        //Termina el hilo de Alineamientos Multiples con la opción de conservar inserciones.
        
        try{
            
            if(Hilo_Alineamientos_Multiples_Reales.t_alineamientos_multiples_real.isAlive()){
                
                Hilo_Alineamientos_Multiples_Reales.t_alineamientos_multiples_real.stop();
                
                try{
                
                    Alineamientos_Multiples_Reales.eliminarArchivosTemporales();
                
                }catch(Exception ex){}
                
                System.gc();
     
            }
            
        }catch(Exception e){}
        
        try{
            
            p_reales.destroy();
            
        }catch(Exception e){}
        
        try{
            
            p.destroy();
            
        }catch(Exception e){}
        
        Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
        
        btn_presionado = false;
        
    }
    
}
