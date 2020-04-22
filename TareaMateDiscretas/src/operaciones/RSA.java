package operaciones;

import java.math.BigInteger;

public class RSA {

    static Euclides euclides = new Euclides();
    static Es_primo primo = new Es_primo();

    public static String[][] alfabetoEspanol = {
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "Ñ", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"},
            {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26"}
    };

    public static String[][] alfabetoIngles = {
            {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"},
            {"00", "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25"}
    };

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
    public static String volverStringRSA(String frase, String[][] alfabeto) {
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

        if (!(frase.length() % 2 == 0)) {//Si es impar la cadena le agregamos X que equivale a 24
            resultado += "24";
        }
        return resultado;
    }

    /**
     * Funcion para desencriptarRSA
     * @param fraseEncriptada
     * @param alfabeto
     * @return
     */
    public static String descifrarStringRSAaLetras(String fraseEncriptada, String[][] alfabeto) {
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

}
