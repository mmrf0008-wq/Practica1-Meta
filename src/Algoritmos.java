import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public class Algoritmos {
    public String nombre;

    private class Candidato implements Comparable<Candidato> {
        double sumaDistancia;
        int ciudad;
        
        /**
         * Constructor de la clase Candidato
         * @param sumaDistancia Sumatoria de distancias
         * @param ciudad Ciudad
         */

        public Candidato(double sumaDistancia, int ciudad) {
            this.sumaDistancia = sumaDistancia;
            this.ciudad = ciudad;
        }
        
        /**        
         * Compara dos candidatos según su sumatoria de distancias
         * @param o Candidato a comparar
         * @return 1 si this es mayor que o
         * @return -1 si this es menor que o
         * @return 0 si son iguales
         */
        @Override
        public int compareTo(Candidato o) {
            return Double.compare(this.sumaDistancia, o.sumaDistancia);
        }
    }

    /**
     * Calcula la distancia euclidea entre dos ciudades dadas sus coordenadas
     * @param x1 x2 ciudad 1
     * @param y1 y2 ciudad 2
     * @return distancia euclidea
     */

    private double distancia_euclidea(int x1, int x2, int y1, int y2){
        return sqrt(pow(x1-y1, 2)+ pow(x2-y2,2));
    }

    /**
     * Calcula la matriz de distancias euclideas entre todas las ciudades
     * @param matrizEuclidea Matriz de distancias euclideas a llenar
     * @param matriz Matriz de coordenadas de las ciudades
     */

    private void calculoMatrizEuclidea(double matrizEuclidea[][], int matriz[][]){
        int c=0;

        for(int i =0; i < matriz.length  ; i++){
            for(int j=0; j< matriz.length ; j++){

                if(i==j){
                    matrizEuclidea[i][j]=0;
                    c++;
                }
                else{

                    c=1;
                   /* System.out.printf("[DEBUG] i=%d, j=%d | [i][j]=%d, [i][j+1]=%d, [i+1][j]=%d, [i+1][j+1]=%d%n",
                            f, c,
                            matriz[i][c],
                            matriz[i][c+1],
                            matriz[j][c],
                            matriz[j][c+1]);*/
                    matrizEuclidea[i][j] = distancia_euclidea(matriz[i][c],matriz[i][c+1],matriz[j][c], matriz[j][c+1]);
                    // System.out.println("matrizEuclidea["+i+ "]["+j+"]="+ matrizEuclidea[i][j]);
                    c=0;

                }
            }

        }
    }

    /**
     * Imprime la matriz de distancias euclideas
     * @param matriz Matriz de distancias euclideas a imprimir
     */

    private void printMatriz(double matriz[][]){
        for(int l = 0; l < matriz.length; l++){
            for(int m = 0; m < matriz.length; m++){
                System.out.print(" " + matriz[l][m]);
            }
            System.out.println("");
        }
    }


    public ArrayList<Integer> greedy(int matriz[][]){

        MedidorTiempos.empezarContador();
        int n = matriz.length;
        double matrizEuclidea[][] = new double[n][n];
        calculoMatrizEuclidea(matrizEuclidea,matriz);
        
        ArrayList<Candidato> vectorSolucion = new ArrayList<>();

        double sumatorio = 0;
        //suma de las distancias de las ciudades con respecto a la primer columna
        for(int i = 0; i < n; i++){
            sumatorio = 0;
            for(int j = 0; j < n; j++){
                sumatorio += matrizEuclidea[i][j];
            }
            // Guardamos el candidato con su sumatoria de distancias y su ciudad correspondiente en el vector de soluciones
            vectorSolucion.add(new Candidato(sumatorio, i));
        }

        // Ordenamos menor mayor
        Collections.sort(vectorSolucion);

        // Creamos un nuevo vector para almacenar solo las ciudades ordenadas según la sumatoria de distancias
        ArrayList<Integer> ciudadesOrdenadas = new ArrayList<>();
        boolean[] visitado = new boolean[n];

        // Ciudad con menor sumatoria de distancias
        int actual = vectorSolucion.get(0).ciudad; 
        ciudadesOrdenadas.add(actual);
        visitado[actual] = true;
        
        for (int i = 1; i < n; ++i) {
            double minDistancia = Double.MAX_VALUE;
            int siguienteCiudad = -1;

            // Ciudad más cercana a la ciudad actual que no haya sido visitada
            for (int j = 0; j < n; ++j) {
                if (!visitado[j] && matrizEuclidea[actual][j] < minDistancia) {
                    minDistancia = matrizEuclidea[actual][j];
                    siguienteCiudad = j;
                }
            }

            // La añadimos a la lista de ciudades ordenadas y marcamos como visitada
            if (siguienteCiudad != -1) {
                ciudadesOrdenadas.add(siguienteCiudad);
                visitado[siguienteCiudad] = true;
                actual = siguienteCiudad;
            }
        }
        
        MedidorTiempos.finalizarYMostrar("Greedy");
        
        // Devolvemos la ruta de las ciudades ordenadas
        return ciudadesOrdenadas;
    }


    public ArrayList<Integer> greedyAleatorio(int matriz[][], long semilla){
        MedidorTiempos.empezarContador();
        int n = matriz.length;
        double matrizEuclidea[][] = new double[n][n];
        calculoMatrizEuclidea(matrizEuclidea,matriz);

        // Creamos un vector solucion de la misma forma que antes
        ArrayList<Candidato> vSolucion = new ArrayList<>(); //vector solucion del greedy, partimos de el para sacar el vsolAlea
       
        double sumatorio = 0;
        //suma de las distancias de las ciudades con respecto a la primer columna
        for(int i = 0; i < n; i++){
            sumatorio = 0;
            for(int j = 0; j < n; j++){
                sumatorio += matrizEuclidea[i][j];
            }
            // Guardamos el candidato con su sumatoria de distancias y su ciudad correspondiente en el vector de soluciones
            vSolucion.add(new Candidato(sumatorio, i));
        }

        // Ordenamos menor mayor
        Collections.sort(vSolucion);

        ArrayList<Integer> vsolAlea = new ArrayList<>(); //vector solucion de greedyAleatorio (vsolAlea = vector solucion Aleatorio)
        //se elige aleatoriamente un numero del 0-5
        int k = 5;

        java.util.Random rand = new java.util.Random(semilla);

        //en bucle
        while(!vSolucion.isEmpty()){
            // Para cuando quedan menos soluciones que k
            if(vSolucion.size() < k) {
                k = vSolucion.size();
            }

            int pos = rand.nextInt(k);

            //se elimina la solucion del vector vsolucion
            Candidato seleccionado = vSolucion.remove(pos);

            //el valor seleccionado del vector será nuestra solucion para agregar al vector
            vsolAlea.add(seleccionado.ciudad);
        }
        
        MedidorTiempos.finalizarYMostrar("Greedy Aleatorio");

        //se devuelve la solucion
        return vsolAlea;
    }
}
