import java.util.Scanner;

public class Problem610A {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.print("Podaj dlugosc kija: \n");
        int length = scan.nextInt();
        int number_of_cuts = 0;
        for(int i=1; i<=length/4; i++){
            if((length-2*i)%2 == 0 && i != (double)length/4) number_of_cuts++;
        }
        System.out.print(number_of_cuts);
    }
}
