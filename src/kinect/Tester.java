//package kinect;
//
//import processing.core.PApplet;
//import processing.core.PVector;
//
//public class Tester extends PApplet {
//    public static void main(String[] args) {
//        PApplet.main(new String[] { Tester.class.getName() });
//    }
//
//    KinectTracker tracker;
//
//    @Override
//    public void settings() {
//        size(512, 424);
//    }
//
//    @Override
//    public void setup() {
//        tracker = new KinectTracker(this);
//    }
//
//    @Override
//    public void draw() {
//        tracker.track();
//        tracker.display();
//
//        PVector v1 = tracker.getPos();
//        fill(50, 100, 250, 200);
//        noStroke();
//        ellipse(v1.x, v1.y, 20, 20);
//
//        PVector v2 = tracker.getLerpedPos();
//        fill(100, 250, 50,200);
//        noStroke();
//        ellipse(v2.x, v2.y, 20, 20);
//
//        int t = tracker.getThreshold();
//        fill(255);
//        text("Threshold: " + t + "framerate: " + (int)(frameRate) + " "  +
//                "CIMA para aumentar o threshold, BAIXO para diminuir ", 10, 400);
//    }
//
//    @Override
//    public void keyPressed() {
//        int t = tracker.getThreshold();
//        if (key == CODED) {
//            if (keyCode == UP) {
//                t += 5;
//            } else if (keyCode == DOWN) {
//                t -= 5;
//            }
//            tracker.setThreshold(t);
//        }
//    }
//}
