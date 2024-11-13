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
import static com.mycompany.epimolbio.Interfaz.idioma;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Roberto Reinosa Fernández
 */

public class Generador_Archivos_Dot_Plot {
    
    //Crea un gráfico de puntos en html sirviendose de la clase Dot_Plot.
    //Creates a dot plot graph in HTML using the Dot_Plot class.
    
    public static void cargarGeneradorArchivosDotPlot(String salida, String secuenciaA, String secuenciaB){
        
        try{
                    
            String ficheroSalida1 = salida;
            
            String secuenciaTraducida;
            
            if(idioma == 1){
                
                secuenciaTraducida = "Secuencia";
                
            }else{
                
                secuenciaTraducida = "Sequence";
                
            }

            try (FileWriter ficheroSalida2 = new FileWriter(ficheroSalida1)) {
                
                ficheroSalida2.write("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<head>\n" +
                        "  <script src=\"https://cdn.jsdelivr.net/npm/chart.js\"></script>\n" +
                        "  <style>\n" +
                        "    body, html {\n" +
                        "      height: 100%;\n" +
                        "      margin: 0;\n" +
                        "      padding: 0;\n" +
                        "    }\n" +
                        "\n" +
                        "    #chartContainer {\n" +
                        "      width: 100%;\n" +
                        "      height: 100%;\n"+
                        "    }\n" +
                        "\n" +
                        "    canvas {\n" +
                        "      max-width: 100%;\n" +
                        "      max-height: 100%;\n" +
                        "    }\n" +
                        "  </style>\n" +
                        "</head>\n" +
                        "<body>\n" +
                        "  <div id=\"chartContainer\">\n" +
                        "    <canvas id=\"scatterChart\"></canvas>\n" +
                        "  </div>\n" +
                        "\n" +
                        "  <script>\n" +
                        "    var scatterChart;\n" +
                        "\n" +
                        "    function createChart() {\n" +
                        "      var ctx = document.getElementById('scatterChart').getContext('2d');\n" +
                        "\n" +
                        "      var data = {\n" +
                        "        datasets: [{\n" +
                        "          label: 'Dot Plot',\n" +
                        "          data: [");
                
                int matrizDot[][];
                
                matrizDot = Dot_Plot.cargarDotPlot(secuenciaA, secuenciaB);
                
                for(int i = 0; i < secuenciaB.length(); i++) {
                    
                    for(int a = 0; a < secuenciaA.length(); a++) {
                        
                        if(matrizDot[a][i] == 1) {
                            
                            ficheroSalida2.write("{x:" + (a+1) + ", y: " + (i+1) + "},");
                            
                        }
                        
                    }

                }
                
                double pixel;
                
                if(secuenciaA.length() < 100){
                    
                    pixel = 2;
                    
                }else if(secuenciaA.length() < 1000){
                    
                    pixel = 1;
                    
                }else{
                    
                    pixel = 0.1;
                    
                }
                
                ficheroSalida2.write( "],\n" +
                        "          backgroundColor: 'rgba(46, 117, 182, 1)',\n" +
                        "pointRadius: "+ pixel +
                        "        }]\n" +
                        "      };\n" +
                        "\n" +
                        "      var options = {\n" +
                        "        responsive: true,\n" +
                        "        maintainAspectRatio: false,\n" +
                        "        scales: {\n" +
                        "          x: {\n" + "title: {\n" +
                        "              display: true,\n" +
                        "              text: '" + secuenciaTraducida + " 1'\n" +
                        "            },"+
                        "            type: 'linear',\n" +
                        "            position: 'bottom'\n" +
                        "          },\n" +
                        "          y: {\n" + "title: {\n" +
                        "              display: true,\n" +
                        "              text: '" + secuenciaTraducida + " 2'\n" +
                        "            },"+
                        "            type: 'linear',\n" +
                        "            position: 'left'\n" +
                        "          }\n" +
                        "        }\n" +
                        "      };\n" +
                        "\n" +
                        "      scatterChart = new Chart(ctx, {\n" +
                        "        type: 'scatter',\n" +
                        "        data: data,\n" +
                        "        options: options\n" +
                        "      });\n" +
                        "    }\n" +
                        "\n" +
                        "    function resizeChart() {\n" +
                        "      var chartContainer = document.getElementById('chartContainer');\n" +
                        "      var scatterChartCanvas = document.getElementById('scatterChart');\n" +
                        "\n" +
                        "      scatterChartCanvas.width = chartContainer.offsetWidth;\n" +
                        "      scatterChartCanvas.height = chartContainer.offsetHeight;\n" +
                        "\n" +
                        "      if (scatterChart) {\n" +
                        "        scatterChart.destroy(); // Destruye la instancia actual de la gráfica\n" +
                        "        createChart(); // Vuelve a crear la gráfica\n" +
                        "      }\n" +
                        "    }\n" +
                        "\n" +
                        "    window.addEventListener('DOMContentLoaded', function() {\n" +
                        "      createChart();\n" +
                        "      resizeChart(); // Ajustar el tamaño inicial de la gráfica\n" +
                        "    });\n" +
                        "\n" +
                        "    window.addEventListener('resize', resizeChart);\n" +
                        "  </script>\n" +
                        "</body>\n" +
                        "</html>");
            }
            
        }catch(IOException e){
            
            Error.setLocationRelativeTo(null);
            Error.setVisible(true);
            
            Logger.getLogger(Generador_Archivos_Dot_Plot.class.getName()).log(Level.SEVERE, null, e);
            
            btn_presionado = false;
            
            Botones_Calcular_Finalizada.llamadaCalcularFinalizada();
            
            Terminar_Hilos.cargarTerminarHilos();
            
        }
    }
}
