import edu.princeton.cs.algs4.*;

import java.util.LinkedList;

public class PointSET {

    private int size;
    private SET<Point2D> points;


    public PointSET() {
        points = new SET<Point2D>();
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public int size() {
        return points.size();
    }

    public void insert(Point2D p) {
        points.add(p);
    }

    public boolean contains(Point2D p) {
        return points.contains(p);
    }

    public void draw() {
        for (Point2D point : points) {
            point.draw();
        }

    }

    public Iterable<Point2D> range(RectHV rect) {
        LinkedList<Point2D> pointsinRange = new LinkedList<Point2D>();
        for (Point2D point : points) {

            if (rect.contains(point)) {
                pointsinRange.add(point);
            }
        }
        return pointsinRange;

    }

    public Point2D nearest(Point2D p) {
        Point2D champ = null;

        for (Point2D point : points) {

            if (champ == null) champ = point;
            else {
                if (p.distanceTo(point) < p.distanceTo(champ)) {
                    champ = point;
                }
            }
        }
        return champ;
    }


    public static void main(String[] args) {

        PointSET pSet = new PointSET();

        pSet.insert(new Point2D(0.2, 0.4));
        pSet.insert(new Point2D(0.1, 0.5));
        pSet.insert(new Point2D(0.3, 0.3));
        pSet.insert(new Point2D(0.4, 0.2));

        StdDraw.setPenRadius(0.02);
        pSet.draw();

        RectHV rect = new RectHV(0.3, 0.1, 0.9, 0.9);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.setPenRadius(0.002);
        rect.draw();

        for (Point2D point : pSet.range(rect)) {
            StdOut.println(point.toString());

        }
    }
}


