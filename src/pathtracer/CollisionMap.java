package pathtracer;

import processing.data.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.Set;

import static processing.core.PApplet.loadJSONObject;

@SuppressWarnings("unchecked")
public interface CollisionMap {
    HashMap<String, Line> lines = new HashMap<>();

    default String[] mapper() {
        JSONObject json = loadJSONObject(new File("src/pathtracer/lines.json"));
        Set<String> keys = json.keys();
        String[] keysArray = keys.toArray(new String[0]);

        for (String key : keysArray) {
            JSONObject line = json.getJSONObject(key);
            int direction = line.getInt("direction");
            String orientation = line.getString("dentro");
            int startX = line.getInt("startX");
            int startY = line.getInt("startY");
            int endX = line.getInt("endX");
            int endY = line.getInt("endY");
            lines.put(key, new Line(direction, orientation, startX, startY, endX, endY));
        }

        return keysArray;
    }

}