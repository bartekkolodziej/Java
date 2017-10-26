import java.util.Scanner;

public class Fibo {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj liczbe (od 1 do 45): \n");
        int i = scan.nextInt();
        if(i >=1 && i <= 45){
            int [] fibo = new int[i];
            fibo[0] = 1;
            fibo[1] = 1;
            for(int j=2; j<i; j++) {
                fibo[j] = fibo[j - 1] + fibo[j - 2];
                System.out.print(fibo[j] +"\n");
            }
        }else System.out.print("Nieprawidlowe dane");
    }
}
