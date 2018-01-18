import java.util.Arrays;
import java.util.Locale;
import java.util.concurrent.Semaphore;

public class Zad2 {
    static int[] array;
    static Semaphore sem = new Semaphore(0);

    public static void main(String[] args) { //dziala po kilku uruchomieniach
        initArray(1000000);
        try {
            odwracanieWatki(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    static void initArray(int size) {
        array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = i;
        }
    }

    static class odwracanie extends Thread{
        private final int start;
        private final int end;

        odwracanie(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            for(int i=start; i<end; i++){
                int temp = array[i];
                array[i] = array[end - i - 1];
                array[end - i - 1] = temp;
            }
            sem.release();
        }
    }

    static void odwracanieWatki(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        odwracanie threads[]= new odwracanie[cnt];
        int start = 0;
        int end = array.length/cnt;
        for(int i=0; i<cnt; i++) {
            threads[i] = new odwracanie(start, end);
            start += array.length/cnt;
            end += array.length/cnt;
        }

        double t1 = System.nanoTime()/1e6;
        for(odwracanie od: threads)
            od.start();
        sem.acquire();
        double t2 = System.nanoTime()/1e6;


        System.out.printf(Locale.US,"liczba wątków = %d \n t2-t1=%f \n", cnt, t2-t1);
        for(int i=0; i<20; i++)
            System.out.println(array[i]);
    }

}    