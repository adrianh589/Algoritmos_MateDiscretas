import operaciones.RSA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        RSA rsa = new RSA();

        //Para imprimir numeros primos
        //Es_primo.imprimirPrimo();

        //Algoritmo de euclides
        //System.out.println(Euclides.mcd(67942,4209));

        //Encriptar
        //System.out.println(Criptografia_Ejemplo_1.encriptar("NO SOY UN BANDIDO"));

        //Desencriptar
        //System.out.println(Criptografia_Ejemplo_1.desencriptar("FDWUIEAGEIVDI"));

        //RSA
        //String[][] alfabeto = RSA.alfabetoEspanol;//Descomenta esta linea para usar el español
        String[][] alfabeto = RSA.alfabetoIngles;//Descomenta esta linea para usar el gringo :D

        int p = 59;//Selecciona tu p

        int q = 101;//Selecciona tu q

        //int z = rsa.calcularZ(p,q);
        int z = 2867;//Descomenta esta linea para ponerla manualmente
        System.out.println("z: "+z);

        int n = 11;//Selecciona tu n
        //int n = rsa.calcularN( 2,FI );//en caso de quererlo calcular automaticamente, descomenta esta linea

        int FI = rsa.calcularFI(p,q);
        System.out.println("FI: "+FI);

        System.out.println("n: "+n);

        int s = rsa.calcularS(n, FI);
        System.out.println("S: "+s);

//        System.out.println("Mensaje convertido a string RSA (No encriptado)");
//
//        String volverStringRSA = rsa.volverStringRSA("EL FINAL ESTA PROXIMO",
//                alfabeto);//Esto es para poner el numero que corresponde a cada letra
//
//        System.out.println(volverStringRSA+"\n");
//
//        System.out.println("Mensaje encriptado con RSA");
//        String encriptarRSA = rsa.encriptarRSA(volverStringRSA, n, z);
//        System.out.println(encriptarRSA+"\n");//Aqui se encripta el mensaje tomando el volverStringRSA()
//
        System.out.println("Mensaje desencriptado con RSA");
        String desencriptarRSA = rsa.desencriptarRSA(s, z, "1420 0614 1301");
        System.out.println(desencriptarRSA+"\n");//Aqui se desencripta el RSA, volviendolo a las letras que corresponde
//
        System.out.println("Mensaje desencriptado a String");
        System.out.println(rsa.descifrarStringRSAaLetras(desencriptarRSA, alfabeto));
//        //Aqui se vuelve el encriptamiento en Strings (No en RSA) y se devuelven las letras originales
    }



}


