package kinect;

import org.openkinect.processing.Kinect2;
import processing.core.PApplet;

public class KinectTest extends PApplet {
    public static void main(String[] args) {
        PApplet.main(new String[]{ "kinect.KinectTest" });
    }

    Kinect2 kinect2;

    @Override
    public void settings() {
        size(1024, 848, P2D);
    }

    @Override
    public void setup() {
        background(0);

        kinect2 = new Kinect2(this);
        kinect2.initVideo();
        kinect2.initDepth();
        kinect2.initIR();
        kinect2.initRegistered();
        // Start all data
        kinect2.initDevice();
    }

    @Override
    public void draw() {
        background(0);
        image(kinect2.getVideoImage(), (float) 0, (float) 0, (float) (kinect2.colorWidth*0.267), (float) (kinect2.colorHeight*0.267));
        image(kinect2.getDepthImage(), kinect2.depthWidth, 0);
        image(kinect2.getIrImage(), 0, kinect2.depthHeight);

        image(kinect2.getRegisteredImage(), kinect2.depthWidth, kinect2.depthHeight);
        fill(255);
        text("Framerate: " + (int)(frameRate), 10, 515);
    }
}
