package pathtracer;

public class Line {
    public final int direction;
    public final String orientation;
    public final int startX;
    public final int startY;
    public final int endX;
    public final int endY;

    Line(int direction, String orientation, int startX, int startY, int endX, int endY) {
        this.direction = direction;
        this.orientation = orientation;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    public boolean crossedLine(int x, int y, int xoff, int yoff, Line line) {
        if (line.direction == 0) {
            return crossedLineOnY(x, y, xoff, yoff, line);
        } else if (line.direction == 1) {
            return crossedLineOnX(x, y, xoff, yoff, line);
        } else {
            System.out.println("Error: Invalid line direction");
            return false;
        }
    }

    private boolean crossedLineOnY(int x, int y, int xoff, int yoff, Line line) {
        int minY = Math.min(line.startY, line.endY);
        int maxY = Math.max(line.startY, line.endY);
        if (y < maxY && y > minY && yoff < maxY && yoff > minY) {
            if (line.orientation.equals("direita") && x < line.startX && xoff > line.startX) {
                System.out.println("Crossed line horizontally to the left");
                return true;
            } else if (line.orientation.equals("esquerda") && x > line.startX && xoff < line.startX) {
                System.out.println("Crossed line horizontally to the right");
                return true;
            }
        }
        return false;
    }

    private boolean crossedLineOnX(int x, int y, int xoff, int yoff, Line line) {
        int minX = Math.min(line.startX, line.endX);
        int maxX = Math.max(line.startX, line.endX);
        if (x < maxX && x > minX && xoff < maxX && xoff > minX) {
            if (line.orientation.equals("emcima") && y > line.startY && yoff < line.startY) {
                System.out.println("Crossed line vertically to the bottom");
                return true;
            } else if (line.orientation.equals("embaixo") && y < line.startY && yoff > line.startY) {
                System.out.println("Crossed line vertically to the top");
                return true;
            }
        }
        return false;
    }
}

