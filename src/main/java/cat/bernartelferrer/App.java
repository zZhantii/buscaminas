package cat.bernartelferrer;

import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;

/**
 * Programa para el juego del buscaMinas
 * @author Santiago lozada
 */
public final class App {


    /**
     * Scanner para la entrada en teclado del usuario
     */
    static Scanner entradaUser = new Scanner(System.in);


    /**
     * Array para guardar la tabla que visualiza el jugador
     */
    static String[][] tabla;

    /**
     * Array para guardar las minas contenidas en la tabla
     */
    static String[][] tablaMinas;

    /**
     * Array para guardar las partidas del jugador
     */
    static String[][] Partida;

    /**
     * Parametro para gestionar donde guardar los ficheros el usuario
     */
    static File fichero;

    /**
     * Variables para calcular la dificultad, minas
     */
    static int dificultad, minas;

    /**
     * Variable para guardar el nombre del archivo al guardar
     */
    static String nombreArchivo;

    /**
     * Variable para controla la salida de los bucles
     */
    static boolean salida = true;

    /**
     * Metodo para pedir una opción del menu
     * @return escoge la opcion por el usuario
     */
    public static int escogerOpcion() {

        System.out.print("Opcion escogida: ");
        
        if(entradaUser.hasNextInt()){
            return entradaUser.nextInt();
        }else{
            return - 1;
        }
    }

    /**
     * Metodo para mostrar los menus de opciones
     * @param menu Parametro que indica el menu que muestra
     */
    private static void mostrarMenu(String menu) {
        switch (menu) {
            case "main":
            
            System.out.println(" ");
            System.out.println("\t\t**** MENÚ PRINCIPAL ****");
            System.out.println(" ");
            System.out.println("\t1) Nueva partida " + "    2) Cargat partida");
            System.out.println("\t3) Salir");
            System.out.println(" ");

            break;

            case "menuSecundario":

            System.out.println(" ");
            System.out.println("\t\t   **** MENÚ ****");
            System.out.println(" ");
            System.out.println("\t1) Guardar Partida" + "  2) Finalizar partida");
            System.out.println("\t3) Trampa" + "           4) Normal");
            System.out.println("\t5) jugada");
            System.out.println(" ");

            break;

            default:
            break;
        }
    }

    /**
     * Punto de entrada principal al codigo
     * @param args lineas del codigo principal
     */
    public static void main(String[] args) {

        mostrarMenu("main");
        int opcio = escogerOpcion();
        entradaUser.nextLine();

        switch(opcio){
            case 1:
                iniciarJuego();
            break;
            
            case 2:
                registroPartida(tabla);
            break;
            
            case 3:
                salida = false;
            break;
            
            default:
                System.out.println("ERROR: opció incorrecta...");
            break;
        }
    }

    /**
     * Metodo para iniciar el juego y escoger la dificultad y la cantidad de minas
     */
    public static void iniciarJuego() {

        System.out.println(" ");
        System.out.println("\tEscoja la dificultad deseada");
        System.out.println(" ");
        System.out.println("\t1) Fácil" + "  2) Medio" + "  3) Difcil");
        System.out.println(" ");
        System.out.print("Opcion escogida: ");
        dificultad = entradaUser.nextInt();
        entradaUser.nextLine();

    
        switch (dificultad) {
            case 1:
                dificultad = 4;
            break;

            case 2:
                dificultad = 8;
            break;

            case 3:
                dificultad = 12;
            break;

            default:
            break;
        }

        System.out.println(" ");
        System.out.println("      Escoja la cantidad de minas deseada");
        System.out.println(" ");
        System.out.println("\t1) Pocas" + "  2) Medias" + "  3) Muchas");
        System.out.println(" ");
        System.out.print("Opcion escogida: ");
        minas = entradaUser.nextInt();
        entradaUser.nextLine();

        switch (minas) {
            case 1:
                minas = (dificultad*dificultad) * 20/100;
                creacionTabla();
                break;
            case 2:
                minas = (dificultad*dificultad) * 30/100;
                creacionTabla();
                break;
            case 3:
                minas = (dificultad*dificultad) * 40/100;
                creacionTabla();
                break;
            default:
                break;
        }
        mostrarTabla(tabla);
        menuSecundario();
    }

    /**
     * Metodo para mostrar la tabla al jugador
     * @param tabla  Array que contiene la tabla del juego
     */
    public static void mostrarTabla(String[][] tabla) {

        for(int i=0;i<tabla.length;i++) {  
            for(int j=0;j<tabla[i].length;j++){
                System.out.print("\t" + tabla[i][j] + "\t");
            }
            System.out.println(" ");
        }
    }

    /**
     * Metodo para crear las tabla necesarias para el juego
     */
    public static void creacionTabla() {

        tabla = new String[dificultad][dificultad];
        tablaMinas = new String[dificultad][dificultad];

        for(int i = 0; i < tabla.length; i++) {  
            for(int j = 0; j < tabla[i].length; j++){
                tabla[i][j] = "-";
            }
        }

        for(int i = 0; i < tablaMinas.length; i++) {  
            for(int j = 0; j < tablaMinas[i].length; j++){
                tablaMinas[i][j] = "*";
            }
        }

        int minasX, minasY;

        boolean repetido = true;

        for (int i = 0; i < minas; i++) { 
            do {

                minasX = (int)(Math.random()*dificultad);
                minasY = (int)(Math.random()*dificultad);

                if (tablaMinas [minasX][minasY] != "X"){
                    repetido = false;
                }

            } while (repetido);  

            tablaMinas [minasX][minasY] = "X";
        } 
    }

    /**
     * Metodo para mostrar el menu dentro del juego
     */
    static void menuSecundario() {
        
        do {

            mostrarMenu("menuSecundario");
            int opcio = escogerOpcion();
            entradaUser.nextLine();
            
            switch (opcio) {
                case 1:
                    guardarPartida(tabla);
                break;

                case 2:
                    salida = false;
                break;

                case 3:
                    mostrarTabla(tablaMinas);
                break;

                case 4:
                    mostrarTabla(tabla);
                break;

                case 5:
                    jugada();
                break;
            
                default:
                    System.out.println("Te has equivocado de opcion...");
                break;
            }

        } while (salida);
        
        main(null);
    }

    /**
     * Metodo que permite realizar una jugada y substituirla por la otra tabla
     */
    public static void jugada() {

        do {

            System.out.print("Fila: ");
            int x = entradaUser.nextInt();
            System.out.print("Columna: ");
            int y = entradaUser.nextInt();

            if (tablaMinas[x - 1][y - 1] == "X") {
                tabla[x - 1][y - 1] = "X";
                System.out.println("**** Fin de la partida ****");
                guardarPartidaperdida(tabla);
                salida = false;
            } else {
                tabla[x - 1][y - 1] = "*";
                mostrarTabla(tabla);
                menuSecundario();
            }

            if (tabla[x][y] != "-") {
                for (int i = 0; i < tabla.length; i++) {
                    for (int j = 0; j< tabla[i].length; j++) {
                    }
                }
                System.out.println("Partida Ganada");
                guardarPartida(tabla);
            }

            mostrarTabla(tabla);

        } while (salida);
    }

    /**
     * Metodo para guardar las partidas al usuario
     * @param tabla  Tabla al ser guardada
     */
    public static void guardarPartida(String tabla[][]) {

        System.out.print("Nombre: ");
        nombreArchivo = entradaUser.nextLine();

        fichero = new File("C:"+ File.separator +"Users"+ File.separator +"santi"+ File.separator +"Documents"+ File.separator +"ASIX1B"+ File.separator +"PB"+ File.separator +"buscaminas"+ File.separator +"partidas"+ File.separator + nombreArchivo + ".bmi"); 

        try {

            fichero.createNewFile();

            FileWriter escritura = new FileWriter(fichero);

            escritura.write(dificultad + ";" + "\n");

            for (int i = 0; i < tabla.length; i++) {
                for (int j = 0; j< tabla[i].length; j++) {
                    escritura.write(tabla[i][j] + ";");
                }
                escritura.write("\n");
            }

            for (int i = 0; i < tablaMinas.length; i++) {
                for (int j = 0; j< tablaMinas[i].length; j++) {
                    escritura.write(tablaMinas[i][j] + ";");
                }
                escritura.write("\n");
            }

            escritura.close();

            System.out.println("\t\t***Se ha guardado con exito***");

            mostrarTabla(tabla);
            
        } catch (Exception e) {
            System.out.println("\t*** ERROR... No se ha guardado la partida correctamente. *** ");   
        }
    }

    /**
     * Metodo para guardar las partidas perdidas del usuario
     * @param tabla  tabla de la partida perdida
     */
    public static void guardarPartidaperdida(String tabla[][]) {

        fichero = new File("C:"+ File.separator +"Users"+ File.separator +"santi"+ File.separator +"Documents"+ File.separator +"ASIX1B"+ File.separator +"PB"+ File.separator +"buscaminas"+ File.separator +"partidas"+ File.separator + "partidaPerdida.bmi"); 

        try {

            fichero.createNewFile();

            FileWriter escritura = new FileWriter(fichero);

            escritura.write(dificultad);

            for (int i = 0; i < tabla.length; i++) {
                for (int j = 0; j< tabla[i].length; j++) {
                    escritura.write(tabla[i][j] + ";");
                }
                escritura.write("\n");
            }

            for (int i = 0; i < tablaMinas.length; i++) {
                for (int j = 0; j< tablaMinas[i].length; j++) {
                    escritura.write(tablaMinas[i][j] + ";");
                }
                escritura.write("\n");
            }

            escritura.close();
            
        } catch (Exception e) {
            System.out.println("\t*** ERROR... No se ha guardado la partida correctamente. *** ");    
        }
    }

    /**
     * Metodo para cargar las partidas guardadas por el usuario
     * @param tabla  tabla donde se almacenaran las partidas cargadas
     * @param tablaMinas  tabla con las minas que se utilizaron en cada una de las partidas
     */
    public static void cargarPartida(int dificultad, String tabla[][], String tablaMinas[][]) {
        fichero = new File("C:"+ File.separator +"Users"+ File.separator +"santi"+ File.separator +"Documents"+ File.separator +"ASIX1B"+ File.separator +"PB"+ File.separator +"buscaminas"+ File.separator +"partidas"+ File.separator + nombreArchivo + ".bmi");

        try {

            Scanner lectura = new Scanner(fichero);
            
            while(lectura.hasNextLine()){
                String fila = lectura.nextLine();
                
                String[] cargar = fila.split(";"); 
                int[] juego = new int[dificultad];
                
                dificultad = juego[0];

                tabla[0][0] = cargar[0];

                tablaMinas[0][0] = cargar[0];    
            }

            lectura.close();
        } catch (Exception e) {
            System.out.println("\t*** ERROR... No se ha cargado las partidas correctamente. *** "); 
        }

    }
}
