public class Main {
    public static void main(String[] args) {
        String ruta = "C:\\Users\\Maitena\\Desktop\\Apuntes\\3º\\meta\\Practica1-Meta\\Practica1-meta\\src\\config.txt";
        Configuracion config = new Configuracion(ruta);

        ArchivoDatos archivosDatos = new ArchivoDatos("C:\\Users\\Maitena\\Desktop\\Apuntes\\3º\\meta\\Practica1-Meta\\Practica1-meta\\src\\"+ config.getArchivo(0));
        Algoritmos algoritmos= new Algoritmos();


        switch(config.getAlgoritmos(0)){
            case "greedy":
                algoritmos.greedy(archivosDatos.getMatriz1());
                break;
        }
    }
}