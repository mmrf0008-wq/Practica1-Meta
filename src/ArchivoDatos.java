import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
/** 
public class ArchivoDatos {

    private double[][] matriz1;
    private String nombre; //nombre de ficheros de la matriz

    public ArchivoDatos(String ruta) throws IOException {
        String linea;
        FileReader file = null;

        try (BufferedReader buffer = new BufferedReader(new FileReader(ruta))){
            
            int dimension = 0;
            //sacamos la dimensión
            boolean parar = false;

            while (((linea = buffer.readLine())!= null) && !parar){

                    String[] split = linea.split(":");
                    if (split[0].trim().equalsIgnoreCase("DIMENSION")) {
                        dimension = Integer.parseInt(split[1].trim());
                        parar = true;
                    }
            }

            parar = false;
            while ((linea = buffer.readLine()) != null  && !parar ) {
                if (linea.trim().toUpperCase().startsWith("NODE_COORD_SECTION")) {
                    parar = true;
                }
            }

            //inicializamos las matrices al numero de filas y columnas indicado
            matriz1 = new double[dimension][3];

            //rellenamos las matrices con el contenido del doc
            int i = 0;
            while( i < dimension && (linea = buffer.readLine()) != null){
                linea = linea.trim();
                if (linea.isEmpty() || linea.equalsIgnoreCase("EOF")) {
                    continue;
                }

                String[] split = linea.trim().split("\\s+");
                    
                if (split.length >= 3) {
                    matriz1[i][0] = (int) Double.parseDouble(split[0]);
                    matriz1[i][1] = (int) Double.parseDouble(split[1]);
                    matriz1[i][2] = (int) Double.parseDouble(split[2]);
                    i++;
                }
            }
            
        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage());
        }
    }

    public double[][] getMatriz1() {
        return matriz1;
    }

}
**/

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ArchivoDatos {

    private double[][] matriz1;

    public ArchivoDatos(String ruta) {
        String linea;

        try (BufferedReader buffer = new BufferedReader(new FileReader(ruta))) {

            int dimension = 0;
            boolean parar = false;

            // 1. Obtener la dimensión (sin break)
            while (!parar && (linea = buffer.readLine()) != null) {
                String[] split = linea.split(":");
                if (split[0].trim().equalsIgnoreCase("DIMENSION")) {
                    dimension = Integer.parseInt(split[1].trim());
                    parar = true;
                }
            }

            // 2. Buscar NODE_COORD_SECTION (sin break)
            parar = false;
            while (!parar && (linea = buffer.readLine()) != null) {
                if (linea.trim().toUpperCase().startsWith("NODE_COORD_SECTION")) {
                    parar = true;
                }
            }

            matriz1 = new double[dimension][3];

            // 3. Leer las N ciudades (AQUÍ NO DEBE HABER NINGÚN buffer.readLine() SUELTO ANTES)
            int i = 0;
            while (i < dimension && (linea = buffer.readLine()) != null) {
                linea = linea.trim();

                if (!linea.isEmpty() && !linea.equalsIgnoreCase("EOF")) {
                    String[] split = linea.split("\\s+");

                    if (split.length >= 3) {
                        matriz1[i][0] = Double.parseDouble(split[0]); // ID del nodo (1, 2, 3...)
                        matriz1[i][1] = Double.parseDouble(split[1]); // Coordenada X
                        matriz1[i][2] = Double.parseDouble(split[2]); // Coordenada Y
                        i++;
                    }
                }
            }

        } catch (IOException e) {
            throw new RuntimeException("Error al leer el archivo: " + e.getMessage(), e);
        }
    }

    public double[][] getMatriz1() {
        return matriz1;
    }
}