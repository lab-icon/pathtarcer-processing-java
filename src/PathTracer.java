import processing.core.PApplet;
import processing.core.PVector;

public class PathTracer extends PApplet implements CollisionMap {

    Snake snake;
    Apple apple;

    static final int initialX = 700;
    static final int initialY = 500;

    boolean breaking = false;

    public static void main(String[] args) {
        PApplet.main(new String[] { "PathTracer" });
    }

    @Override
    public void settings() {
        fullScreen();
    }

    @Override
    public void setup() {
        reset();
    }

    @Override
    public void draw() {
        background(0);
        mapMonitor();
        onKeyPress();

        snake.show();
        snake.update();
        snake.mouseChase();
        snake.eat(apple);

        apple.show();

        String[] keys = mapper();
        for (String key : keys) {
            Line line = lines.get(key);
            if (line.crossedLine((int) snake.getX(), (int) snake.getY(), (int) snake.getBody().get(2).x, (int) snake.getBody().get(2).y, line)) {
                breaking = true;
                snake.kill();
                break;
            }
        }

    }

    void mapMonitor() {
        push();
        noStroke();
        fill(255,209,0);

        // SUPERIOR ESQUERDO
        int mx1 = round(481);
        int my1 = round(0);
        int mw1 = round(400);
        int mh1 = round(640);
        rect(mx1,my1,mw1,mh1);

        // SUPERIOR DIREITO
        int mx2 = round(928);
        int my2 = round(66);
        int mw2 = round(640);
        int mh2 = round(400);
        rect(mx2,my2,mw2,mh2);


        // INFERIOR ESQUERDO
        int mx3 = round(351);
        int my3 = round(572);
        int mw3 = round(640);
        int mh3 = round(360);
        rect(mx3,my3,mw3,mh3);

        // INFERIOR DIREITO
        int mx4 = round(1047);
        int my4 = round(440);
        int mw4 = round(400);
        int mh4 = round(640);
        rect(mx4,my4,mw4,mh4);

        fill(255,209,0); // cor dos monitores
        int mbx1 = round(879);
        int mby1 = round(66);
        int mbw1 = round(60);
        int mbh1 = round(400);
        rect(mbx1,mby1,mbw1,mbh1); // borda de cima

        int mbx2 = round(989);
        int mby2 = round(572);
        int mbw2 = round(60);
        int mbh2 = round(360);
        rect(mbx2,mby2,mbw2,mbh2); // borda de baixo

        pop();
    }

    void onKeyPress() {
        if (keyCode == ENTER) {
            snake.setStart();
            if (!snake.getAlive()){
                reset();
            }
        }
    }

    void reset() {
        snake = new Snake(this, initialX,initialY);
        apple = new Apple(this);
        snake.updateColors();
        apple.location();
        breaking = false;
    }

}
