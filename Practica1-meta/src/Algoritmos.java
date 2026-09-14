import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Algoritmos {
    public String nombre;


    /**
     *
     * @param x1 x2 ciudad 1
     * @param y1 y2 ciudad 2
     * @return distancia euclidea
     */
    private double distancia_euclidea(int x1, int x2, int y1, int y2){
        return sqrt(pow(x1-y1, 2)+ pow(x2-y2,2));
    }
    public void greedy(int matriz[][]){
        //calcular distancia euclidea entre ciudades, saco la matriz de distnacias euclideas
        double matrizEuclidea[][]= new double[matriz.length][matriz.length];

        for(int i =0; i < matriz.length-1; i++){ //filas
            for(int j=0; j < matriz.length; j++){ //col
                if(i==j){
                    matrizEuclidea[i][j]=0;
                }
                else{
                    matrizEuclidea[i][j] = distancia_euclidea(matriz[i][j+1],matriz[i][j+2],matriz[i+1][j+1], matriz[i+1][j+2]);
                }
            }
        }

        for(int i =0; i < matriz.length; i++){ //filas
            for(int j=0; j < matriz.length; j++) { //col
                System.out.println(matriz[i][j]+ " ");
            }
            System.out.println("\n");
        }


        //suma de las distancias de las ciudades con respecto a la primer columna


        //ordenamos menor mayor

        //devolvemos solución

    }
}
