//package kinect;
//
//import processing.core.PApplet;
//import processing.core.PImage;
//
//import org.openkinect.processing.Kinect2;
//import processing.core.PVector;
//
//
//public class KinectTracker extends PApplet {
//
//    private PApplet pa;
//    private Kinect2 kinect2;
//    private int threshold = 750;
//    private PVector loc;
//    private PVector lerpedLoc;
//    private int[] depth;
//    private PImage display;
//
//    public KinectTracker(PApplet pa) {
//        this.pa = pa;
//        kinect2 = new Kinect2(pa);
//        kinect2.initDepth();
//        kinect2.initDevice();
//
//        display = createImage(kinect2.depthWidth, kinect2.depthHeight, RGB);
//
//        loc = new PVector(0, 0);
//        lerpedLoc = new PVector(0, 0);
//    }
//
//    public void track() {
//        depth = kinect2.getRawDepth();
//
//        if (depth != null) {
//            float sumX = 0;
//            float sumY = 0;
//            float count = 0;
//
//            for (int x = 0; x < kinect2.depthWidth; x++) {
//                for (int y = 0; y < kinect2.depthHeight; y ++) {
//                    int offset = kinect2.depthWidth - x - 1 + y * kinect2.depthWidth;
//
//                    int rawDepth = depth[offset];
//
//                    if(rawDepth > 0 && rawDepth < threshold) {
//                        sumX += x;
//                        sumY += y;
//                        count++;
//                    }
//                }
//            }
//
//            if (count != 0) {
//                loc = new PVector(sumX / count, sumY / count);
//            }
//
//            lerpedLoc.x = PApplet.lerp(lerpedLoc.x, loc.x, 0.3f);
//            lerpedLoc.y = PApplet.lerp(lerpedLoc.y, loc.y, 0.3f);
//        }
//    }
//
//    PVector getLerpedPos() {
//        return this.lerpedLoc;
//    }
//
//    PVector getPos() {
//        return this.loc;
//    }
//
//    public void display () {
//        PImage img = kinect2.getDepthImage();
//
//        if (depth != null && img != null) {
//            display.loadPixels();
//
//            for(int x = 0; x < kinect2.depthWidth; x++) {
//                for(int y = 0; y < kinect2.depthHeight; y++) {
//                    int offset = (kinect2.depthWidth - x - 1) + y * kinect2.depthWidth;
//                    int rawDepth = depth[offset];
//                    int pix = x + y * display.width;
//
//                    if (rawDepth < threshold && rawDepth > 0) {
//                        display.pixels[pix] = color(150, 50, 50);
//                    } else {
//                        display.pixels[pix] = img.pixels[offset];
//                    }
//                }
//            }
//            display.updatePixels();
//
//            pa.image(display, 0, 0);
//        }
//    }
//
//    int getThreshold() {
//        return this.threshold;
//    }
//
//    void setThreshold(int t) {
//        this.threshold = t;
//    }
//}