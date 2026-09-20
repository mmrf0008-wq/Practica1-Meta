public class Main {
    public static void main(String[] args) {
       
        /*String ruta = "C:\\Users\\Maitena\\Desktop\\Apuntes\\3º\\meta\\Practica1-Meta\\src\\config.txt";*/
        String rutaRelativa = "src/config.txt";
        System.out.println("Ruta actual de ejecución: " + new java.io.File(".").getAbsolutePath());
        Configuracion config = new Configuracion(rutaRelativa);

        ArchivoDatos archivosDatos = new ArchivoDatos("src/" + config.getArchivo(0));
        Algoritmos algoritmos= new Algoritmos();


        //comando terminal sacar logs  javac *.java && java Main >> log.txt

        switch(config.getAlgoritmos(0)){
            case "greedy":
                algoritmos.greedy(archivosDatos.getMatriz1());
                break;
            case "greedyAleatorio":
                algoritmos.greedyAleatorio(archivosDatos.getMatriz1(), config.getSemilla());
        }
    }
}