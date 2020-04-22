import operaciones.RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        //Para imprimir numeros primos
        //Es_primo.imprimirPrimo();

        //Algoritmo de euclides
        //System.out.println(Euclides.mcd(67942,4209));

        //Encriptar
        //System.out.println(Criptografia_Ejemplo_1.encriptar("NO SOY UN BANDIDO"));

        //Desencriptar
        //System.out.println(Criptografia_Ejemplo_1.desencriptar("FDWUIEAGEIVDI"));

        //RSA
        String mensaje = "1420 0614 1301 1694";
        RSA rsa = new RSA("desencriptar", mensaje, "ingles", 47, 61, 2867, 11, -1, -1);


    }


}


