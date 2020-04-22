package operaciones;

public class Euclides {

    public static int mcd(int a, int b){
        //sea el mayor
        if(a < b) {
            int aux = a;
            a = b;
            b = aux;
        }
            while (b != 0) {
                int r = a % b;
                a = b;
                b = r;
            }
            return a;
    }

}
