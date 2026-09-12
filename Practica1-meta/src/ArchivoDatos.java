import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class ArchivoDatos {

    private int matriz1[][];
    private String nombre; //nombre de ficheros de la matriz
    private int matriz2[][];

    public ArchivoDatos(String ruta) {
        String linea;
        FileReader file = null;

        try {
            file = new FileReader(ruta);
            BufferedReader buffer = new BufferedReader(file);

            int numero = Integer.parseInt(buffer.readLine()); //leemos el numero de filas y columnas especificado en el documento, es la primer linea

            //inicializamos las matrices al numero de filas y columnas indicado
            matriz1 = new int[numero][numero];
            matriz2 = new int [numero][numero];
            linea = buffer.readLine(); //es necesaria la lectura de linea vacia

            //rellenamos las matrices con el contenido del doc
            for(int i =0; i < numero; i++){
                linea = buffer.readLine();
                String split[] = linea.split(" ");
                int errores =0;
                for( int j =0; j < split.length; j++){
                    //como el archivo no tiene la misma cantidad de espacios en el doc para separar hacemos esto
                    try{
                        matriz1[i][j-errores] = Integer.parseInt(split[j]);
                    }catch (NumberFormatException e ){
                        errores++;
                    }
                }
            }
            linea = buffer.readLine(); //es necesaria la lectura de linea vacia

            //lo mismo con matriz 2

            for(int i =0; i < numero; i++){
                linea = buffer.readLine();
                String split[] = linea.split(" ");
                int errores =0;
                for( int j =0; j < split.length; j++){
                    //como el archivo no tiene la misma cantidad de espacios en el doc para separar hacemos esto
                    try{
                        matriz2[i][j-errores] = Integer.parseInt(split[j]);
                    }catch (NumberFormatException e ){
                        errores++;
                    }
                }
            }



        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
