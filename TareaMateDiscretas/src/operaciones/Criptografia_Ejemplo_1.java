package operaciones;

public class Criptografia_Ejemplo_1 {

    static String alfabeto =            " ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static String alfabetoEncriptado =  "EIJFUAXVHWP GSRKOBTQYDMLZNC";

    public static String encriptar(String palabra){
        String resultado = "";

        for (int i = 0; i < palabra.length(); i++) {
            for (int j = 0; j < alfabetoEncriptado.length(); j++) {
                if( palabra.charAt(i) == alfabeto.charAt(j)  ){
                    resultado += alfabetoEncriptado.charAt(j);
                }
            }
        }

        return resultado;
    }

    public static String desencriptar(String palabra_encriptada){
        String resultado = "";

        for (int i = 0; i < palabra_encriptada.length(); i++) {
            for (int j = 0; j < alfabeto.length(); j++) {
                if( palabra_encriptada.charAt(i) == alfabetoEncriptado.charAt(j)  ){
                    resultado += alfabeto.charAt(j);
                }
            }
        }
        return resultado;
    }

}
