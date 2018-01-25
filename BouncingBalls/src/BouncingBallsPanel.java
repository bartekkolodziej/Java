import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BouncingBallsPanel extends JPanel {

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        for(Ball b: balls){
            g.setColor(b.color);
            g.fillOval((int)b.x, (int)b.y, (int)b.d, (int)b.d );
        }
    }

    boolean stop=true;

    static class Ball{
        Ball(double x, double y, double vx, double vy, int d, Color color){
            this.x = x;
            this.y = x;
            this.vx = vx;
            this.vy = vy;
            this.d = d;
            this.color = color;
        }
        double x;
        double y;
        int d;
        double vx;
        double vy;
        Color color;
    }

    List<Ball> balls = new ArrayList<>();

    class AnimationThread extends Thread{
        public void run(){
            for(;;){
                if(stop == true){
                    try {
                        this.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for(Ball b: balls){
                    if(b.x >= getWidth() -b.d || b.x <= 0)
                        b.vx = -b.vx;
                    if(b.y >= getHeight() -b.d || b.y <= 0)
                        b.vy = -b.vy;

                    for(Ball b2: balls){
                        if(Math.abs(b.x - b2.x) <= b2.d &&
                                Math.abs(b.y - b2.y) <= b2.d){
                            b.vx = -b.vx;
                            b.vy = -b.vy;
                            b2.vx = -b2.vx;
                            b2.vy = -b2.vy;
                        }
                    }

                    b.x += b.vx * 1/30;
                    b.y += b.vy * 1/30;
                }

                repaint();
            }
        }
    }

    BouncingBallsPanel(){
        setBorder(BorderFactory.createStrokeBorder(new BasicStroke(3.0f)));
    }

    void onStart(){
        new AnimationThread().start();
        stop = false;
        System.out.println("Start or resume animation thread");
    }

    void onStop(){
        System.out.println("Suspend animation thread");
    }

    void onPlus(){
        Random rand = new Random();
        balls.add(new Ball(rand.nextDouble() * getWidth(),
                rand.nextDouble() * getHeight(), rand.nextDouble()/1000,
                rand.nextDouble() /1000, 50, Color.red));

        System.out.println("Add a ball");
    }

    void onMinus(){
        System.out.println("Remove a ball");
    }
}