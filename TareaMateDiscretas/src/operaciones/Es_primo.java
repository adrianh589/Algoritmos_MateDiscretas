package operaciones;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Es_primo {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int es_primo(int n) {

        double raiz = Math.floor(Math.sqrt(n));
        int num = 2;

        for (int d = num; d <= raiz; d++) {
            if (n % d == 0) {
                //System.out.println("raiz: "+raiz+"\nn: "+n+"\nd: "+d);
                return d;
            }
        }
        //System.out.println("raiz: "+raiz+"\nn: "+n+"\nd: "+num);
        return 0;
    }

    public static void imprimirPrimo() throws IOException {
        while (true){
            System.out.println("Introduzca el numero");
            int n = Integer.parseInt(br.readLine());

            if (es_primo(n) == 0) {
                System.out.println("El numero " + n + " es primo");
            } else {
                System.err.println("El numero " + n + " NO es primo");
            }
        }
    }

}
