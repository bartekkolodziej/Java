import java.util.Locale;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Mean {
    static BlockingQueue<Double> results = new ArrayBlockingQueue<Double>(100);
    static double[] array;
    static void initArray(int size){
        array = new double[size];
        for(int i=0;i<size;i++){
            array[i]= Math.random()*size/(i+1);
        }
    }

    public static void main(String[] args) {
        initArray(128000000);
        for(int cnt:new int[]{1,2,4,8,16,32,64,128}){
            try {
                parallelMean(cnt);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MeanCalc extends Thread{
        private final int start;
        private final int end;
        double mean = 0;

        MeanCalc(int start, int end){
            this.start = start;
            this.end=end;
        }
        public void run(){
            for(int i=start; i<end; i++)
                mean += array[i];
            mean = mean/(end-start);

            try {
                results.put(mean);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf(Locale.US,"%d-%d mean=%f\n",start,end,mean);
        }
    }

    static void parallelMean(int cnt) throws InterruptedException {
        // utwórz tablicę wątków
        MeanCalc threads[]=new MeanCalc[cnt];
        int start = 0;
        int end = array.length/cnt;
        for(int i=0; i<cnt; i++) {
            threads[i] = new MeanCalc(start, end);
            start += array.length/cnt;
            end += array.length/cnt;
        }

        double t1 = System.nanoTime()/1e6;
        for(MeanCalc th: threads)
            th.start();
        double t2 = System.nanoTime()/1e6;
        // czekaj na ich zakończenie używając metody ''join''


        double mean = 0;
        for(int i=0; i<cnt; i++)
            mean += results.take();
        mean = mean/cnt;
        double t3 = System.nanoTime()/1e6;
        System.out.printf(Locale.US,"size = %d cnt=%d >  t2-t1=%f t3-t1=%f mean=%f\n",
                array.length,
                cnt,
                t2-t1,
                t3-t1,
                mean);
    }


}