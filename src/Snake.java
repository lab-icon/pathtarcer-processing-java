import processing.core.PApplet;
import processing.core.PVector;
import java.util.ArrayList;

class Snake extends PApplet {
    private float x, y;
    private float speed = 2;
    private boolean alive = true;
    private boolean start = false;
    private final ArrayList<PVector> body = new ArrayList<PVector>();
    private final PVector head;
    private final PathTracer pt;
    private static final int radius = 60;
    private float dist;
    private float bodySpeed;
    private int[] colors = new int[255];
    public Snake(PathTracer pt, int x, int y) {
        this.pt = pt;

        this.x = x;
        this.y = y;
        for(int i = 0; i < 5; i++) {
            this.body.add(new PVector(this.x - i * 12, this.y));
        }
        this.head = this.body.getFirst();
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    void setStart() {
        this.start = true;
    }

    ArrayList<PVector> getBody() {
        return this.body;
    }

    boolean getAlive() {
        return this.alive;
    }

    PVector getHead() {
        return this.head;
    }

    void show() {
        pt.pushMatrix();
        float radius = 60;
        ArrayList<PVector> pVectors = this.body;
        for (int i = 0; i < pVectors.size(); i++) {
            PVector pVector = pVectors.get(i);
            pt.fill(colors[i], colors[i+1], colors[i+2]);
            pt.circle(pVector.x, pVector.y, radius);
        }
        pt.push();
        pt.fill(0);
        pt.translate(this.body.get(0).x, this.body.get(0).y);
        pt.rotate(atan2(pt.mouseY - this.body.get(1).y, pt.mouseX - this.body.get(1).x));
        if (this.start) {
            pt.circle((float) (radius * 0.5), (float) (radius * 0.2), (float) (radius * 0.2));
            pt.circle((float) (radius * 0.5), (float) (-radius * 0.2), (float) (radius * 0.2));
        }
        pt.pop();
        pt.popMatrix();
    }

    void update() {
        if(radius > this.dist*2.5){
            bodySpeed = 0;
        }
        else{
            bodySpeed = this.speed / 50;
        }
//        println(bodySpeed);
        for (int i = this.body.size() - 1; i > 0; i--) {
            float dx = this.body.get(i - 1).x - this.body.get(i).x;
            float dy = this.body.get(i - 1).y - this.body.get(i).y;
            this.dist = sqrt(dx * dx + dy * dy);
            if (this.dist > 0) {
                this.body.get(i).x += dx * bodySpeed;
                this.body.get(i).y += dy * bodySpeed;
            }
        }
        this.body.set(0, new PVector(this.x, this.y));
    }

    void mouseChase() {
        if(this.start) {
            float dx = pt.mouseX - this.x;
            float dy = pt.mouseY - this.y;
            float dist = sqrt(dx * dx + dy * dy);
            if(dist > 5) {
                this.x += (dx / dist * this.speed);
                this.y += (dy / dist * this.speed);
            }
            else{
                this.x += this.speed;
                this.y += this.speed;
            }
        }
    }

    void kill() {
        this.start = false;
        this.speed = 0;
        this.alive = false;
    }

    void eat(Apple apple) {
      PVector head = this.body.getFirst();
      float d = dist(head.x, head.y, apple.getLoc().x, apple.getLoc().y);
      if(d < 35) {
        this.speed += random(0.05F, 0.5F);
        apple.location();
        this.body.add(new PVector(this.x, this.y));
      }
    }
    public void updateColors(){
        for(int i = 0; i < 255; i++) {
            colors[i] = (int) random(255);
        }
    }
}
