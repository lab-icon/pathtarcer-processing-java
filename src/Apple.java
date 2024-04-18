import processing.core.PApplet;
import processing.core.PVector;

class Apple extends PApplet {
    private final PathTracer pt;
    private PVector loc;

    public Apple(PathTracer pt){
        this.pt = pt;
    }
    public void location(){
        this.loc = isIn();
    }
    public void show(){
        pt.fill(255, 0, 0);
        pt.circle(this.loc.x, this.loc.y, 35);
    }

    public PVector getLoc(){
        return this.loc;
    }

    PVector isIn() {
        Object[] monitors = new Object[4];
        monitors[0] = new Monitor(481,0,400,640);
        monitors[1] = new Monitor(928,66,640,400);
        monitors[2] = new Monitor(351,572,640,360);
        monitors[3] = new Monitor(1047,440,400,640);

        float monitor = random(monitors.length);
        Monitor m = (Monitor) monitors[(int) monitor];
        float x = random(m.x() + 10,m.x()+m.width() - 10);
        float y = random(m.y() + 10,m.y()+m.height() - 10);
        return new PVector(x,y);
    }
}

//  void location() {
//    this.pos = null;
//    this.x = this.pos.x;
//    this.y = this.pos.y;
//    this.loc = createVector(this.x, this.y);
//  }

//  void show() {
//    fill(255, 0, 0);
//    rect(this.loc.x, this.loc.y, 20, 20);
//  }
//}

