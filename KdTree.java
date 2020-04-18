import edu.princeton.cs.algs4.Point2D;

public class KdTree {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;
    private Node root;
    private Point2D champ;
    private Point2D comp;
    private int size;

    private class Node {
        Point2D value;
        Node left, right;
        boolean intercept;

        Node(Point2D p) {
            value = p;
            left = null;
            right = null;
        }
    }


    public KdTree() {
        root = null;
    }

    public boolean isEmpty() {

        return root == null;
    }

    public int size() {
        if (root == null) {
            return 0;
        } else {
            return getCount(root);
        }
    }

    private int getCount(Node node) {
        return size;
    }

    public void insert(Point2D p) {
        Node newPt = new Node(p);
        Node currPos = root;

        if (root == null) {
            newPt.intercept = VERTICAL;
            root = newPt;
            size += 1;
        }

        while (true) {
            if (p.equals(currPos.value)) return;

            if (currPos.intercept == VERTICAL) {

                if (p.x() < currPos.value.x()) {

                }
            }

        }

    }

}
