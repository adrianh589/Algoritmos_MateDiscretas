package operaciones;

import java.math.BigInteger;

public class RSA {

    static Euclides euclides = new Euclides();
    static Es_primo primo = new Es_primo();

    private static String[][] alfabetoEspanol = {
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"},
            {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"}
    };

    private static String[][] alfabetoIngles = {
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"},
            {"00","01","02","03","04","05","06","07","08","09","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25"}
    };

    /**
     * Funcion para calcular en RSA
     * @param accion "encriptar": Encripta el mensaje en RSA "Desencriptar": Desencripta el mensaje "ambas":Enripta y desencripta el mensaje
     * @param frase Frase a hacerle la acción
     * @param idioma Eleccion del idioma del lenguaje "español" o "ingles"
     * @param p Eleccion de la p, debe ir si o si
     * @param q Eleccion de la p, debe ir si o si
     * @param z -1 para elegirlo automaticamente, en otro caso colocar el valor
     * @param n -1 para elegirlo automaticamente, en otro caso colocar el valor
     * @param FI -1 para elegirlo automaticamente, en otro caso colocar el valor
     * @param s -1 para elegirlo automaticamente, en otro caso colocar el valor
     */
    public RSA(String accion, String frase, String idioma, int p, int q, int z, int n, int FI, int s){

        String[][] alfabeto = eleccionDeAlfabeto(idioma);
        accion = accion.toLowerCase();
        frase = anadirX(frase.toUpperCase());

        System.out.println("La frase es: \n"+frase);

        if(idioma.equals("español")){
            System.out.println("Idioma elegido: Español");
        }else if(idioma.equals("ingles")){
            System.out.println("Idioma elegido: Ingles");
        }else {
            System.err.println("ERROR CON EL IDIOMA");
        }

        System.out.println("p: "+p);
        System.out.println("q: "+q);

        if(z == -1){
            z = calcularZ(p,q);
        }
        System.out.println("z: "+z);

        if(n == -1){
            n = calcularN( 2,FI );
        }
        System.out.println("n: "+n);

        if(FI == -1){
            FI = calcularFI(p, q);
        }
        System.out.println("FI: "+FI);

        if(s == -1){
            s = calcularS(n, FI);
        }
        System.out.println("S: "+s);

        switch (accion){
            case "encriptar":

                System.out.println("\nRepresentacion en RSA:");

                //Esto es para poner el numero que corresponde a cada letra
                String representacionEnRSA = representarEnRSA(frase,
                        alfabeto);

                System.out.println(representacionEnRSA+"\n");
                System.out.println("--------------------------------------");


                //Aqui se encripta el mensaje tomando el representarEnRSA()
                System.out.println("encriptacion con RSA");
                String encriptacionRSA = encriptarRSA(representacionEnRSA, n, z);
                System.out.println(encriptacionRSA+"\n");
                System.out.println("--------------------------------------");

                break;
            case "desencriptar":

                System.out.println("desencriptacion con RSA");
                String desencriptarRSA = desencriptarRSA(s, z, frase);
                System.out.println(desencriptarRSA+"\n");//Aqui se desencripta el RSA, volviendolo a las letras que corresponde
                System.out.println("--------------------------------------");

                //Aqui se vuelve el encriptamiento en Strings (No en RSA) y se devuelven las letras originales
                System.out.println("Representacion RSA en letras");
                System.out.println(representarRSAenLetras(desencriptarRSA, alfabeto));

                break;
            case "ambas":
                System.out.println("\nRepresentacion en RSA:");

                //Esto es para poner el numero que corresponde a cada letra
                String representarEnRSA = representarEnRSA(frase,
                      alfabeto);

                System.out.println(representarEnRSA+"\n");
                System.out.println("--------------------------------------");


                //Aqui se encripta el mensaje tomando el representarEnRSA()
                System.out.println("encriptacion con RSA");
                String encriptarRSA = encriptarRSA(representarEnRSA, n, z);
                System.out.println(encriptarRSA+"\n");
                System.out.println("--------------------------------------");

                System.out.println("desencriptacion con RSA");
                String desencriptacionRSA = desencriptarRSA(s, z, encriptarRSA);
                System.out.println(desencriptacionRSA+"\n");//Aqui se desencripta el RSA, volviendolo a las letras que corresponde
                System.out.println("--------------------------------------");

                //Aqui se vuelve el encriptamiento en Strings (No en RSA) y se devuelven las letras originales
                System.out.println("Representacion RSA en letras");
                System.out.println(representarRSAenLetras(desencriptacionRSA, alfabeto));
                break;
            default:
                System.out.println("Accion no válida");

        }

    }

    /**
     * Funcion para seleccionar el idioma de RSA
     * @param idioma español o ingles
     * @return devuelve el idioma seleccionado
     */
    private static String[][] eleccionDeAlfabeto(String idioma){
        String[][] alfabeto = null;
        idioma = idioma.toLowerCase();
        //System.out.println("Selecciona tu idioma: \n1. Español\n2. Ingles");
        if(idioma.equals("español")){
            alfabeto = alfabetoEspanol;
        }else if(idioma.equals("ingles")){
            alfabeto = alfabetoIngles;
        }else {
            System.err.println("Idioma Inválido");
        }
        return alfabeto;
    }



    public static int calcularZ(int p, int q) {
        return p * q;
    }

    public static int calcularFI(int p, int q) {
        return (p - 1) * (q - 1);
    }

    public static int calcularN(int n, int FI) {
        while (true) {
            boolean bandera = (euclides.mcd(n, FI) == 1) ? true : false;
            if (bandera) {
                return n;
            }
            n++;
        }
    }

    /**
     * Funcion para calcular s
     * @param n
     * @param FI
     * @return
     */
    public static int calcularS(int n, int FI) {
        int S = 0;//S a encontrar
        int s = 0;//s Iterativa
        while (true) {
            S = (s * n) % FI;
            if (S == 1) {
                return s;
            }
            s++;
        }
    }

    /**
     * Funcion para volver a String (NO ENCRIPTA)
     *
     * @param frase
     * @return
     */
    public static String representarEnRSA(String frase, String[][] alfabeto) {
        String resultado = "";
        frase = frase.replace(" ", "");//Limpiamos los espacios
        int separador = 0;

        for (int i = 0; i < frase.length(); i++) {//Desde la palabra
            for (int j = 0; j < alfabeto[0].length; j++) {//Desde el alfabeto
                if (frase.valueOf(frase.charAt(i)).equals(alfabeto[0][j])) {
                    resultado += alfabeto[1][j];
                    separador++;
                    break;//Si la encuentra sigue a la otra letra
                }
            }
            if (separador % 2 == 0) {
                resultado += " ";
            }
        }
        return resultado;
    }

    /**
     * Funcion para desencriptarRSA
     * @param fraseEncriptada
     * @param alfabeto
     * @return
     */
    public static String representarRSAenLetras(String fraseEncriptada, String[][] alfabeto) {
        String fraseCopiada = fraseEncriptada;
        fraseCopiada = fraseCopiada.replace(" ", "");
        String resultado = "";
        int separador = 0;

        for (int i = 0; i < fraseCopiada.length(); i+=2) {//Desde la palabra
            String aux = fraseCopiada.substring(i,i+2);//Verificamos de a 2 letras
            for (int j = 0; j < alfabeto[0].length; j++) {//Desde el alfabeto

                if (fraseCopiada.valueOf(aux).equals(alfabeto[1][j])) {
                    resultado += alfabeto[0][j];
                    separador++;
                    break;//Si la encuentra sigue a la otra letra
                }
            }
            if (separador % 2 == 0) {
                resultado += " ";
            }
        }

        return resultado;
    }

    /**
     * Funcion que encripta a RSA teniendo en cuenta el String convertido a numeros inicialmente
     *
     * @param volverStringRSA
     * @param n
     * @param z
     * @return
     */
    public static String encriptarRSA(String volverStringRSA, int n, int z) {
        String[] bloques = volverStringRSA.split(" ");
        String resultado = "";
        BigInteger zB = new BigInteger(String.valueOf(z));
        BigInteger aux;

        for (int i = 0; i < bloques.length; i++) {//Para cada bloque
            BigInteger numBloque = new BigInteger(bloques[i]);
            aux = numBloque.pow(n).mod(zB);
            resultado += agregarCeros(String.valueOf(aux)) + " ";
        }

        return resultado;
    }

    /**
     * Metodo para desencriptar RSA (NO STRING)
     *
     * @return
     */
    public static String desencriptarRSA(int s, long z, String fraseEncriptadaConRSA) {

        String[] bloquesEncriptados = fraseEncriptadaConRSA.split(" ");
        String resultado = "";
        BigInteger zB = new BigInteger(String.valueOf(z));
        BigInteger aux;

        for (int i = 0; i < bloquesEncriptados.length; i++) {//Para cada bloque
            BigInteger numBloque = new BigInteger(bloquesEncriptados[i]);
            aux = numBloque.pow(s).mod(zB);
            resultado += agregarCeros(String.valueOf(aux)) + " ";
        }

        return resultado;
    }

    /**
     * Funcion para agregarle los ceros que faltan al bloque
     *
     * @param bloque Recibe un String de 4 caracteres
     * @return
     */
    public static String agregarCeros(String bloque) {
        String resultado = bloque;
        if (bloque.length() < 4) {
            int cantidadCaracteres = 4 - bloque.length();
            for (int i = 0; i < cantidadCaracteres; i++) {
                resultado = "0" + resultado;
            }
        }
        return resultado;
    }

    /**
     * Anadir la X si la cadena es impar
     * @param frase
     * @return
     */
    public static String anadirX(String frase){
        String aux = frase;
        aux = aux.replace(" ", "");//Limpiamos los espacios
        if (!(aux.length() % 2 == 0)) {//Si es impar la cadena, le agregamos X
            frase += "X";
        }
        return frase;
    }

}
