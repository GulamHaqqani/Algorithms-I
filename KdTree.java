import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;

import java.util.LinkedList;

public class KdTree {
    private static final boolean VERTICAL = true;
    private static final boolean HORIZONTAL = false;
    private Node root;
    private Point2D champ;
    private Point2D comp;
    private int size;
    private LinkedList<Point2D> pointsinRange;

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

        if (p == null) throw new IllegalArgumentException("Point cannot be null");

        if (root == null) {
            newPt.intercept = VERTICAL;
            root = newPt;
            size += 1;
            return;
        }

        while (true) {
            if (p.equals(currPos.value)) {
                return;
            }

            if (currPos.intercept == VERTICAL) {

                if (p.x() < currPos.value.x()) {
                    if (currPos.left == null) {
                        newPt.intercept = HORIZONTAL;
                        currPos.left = newPt;
                        size += 1;
                    } else {
                        currPos = currPos.left;
                    }
                } else {

                    if (currPos.right == null) {
                        newPt.intercept = HORIZONTAL;
                        currPos.right = newPt;
                        size += 1;
                    } else {
                        currPos = currPos.right;
                    }
                }
            } else {

                if (p.y() < currPos.value.y()) {
                    if (currPos.left == null) {
                        newPt.intercept = VERTICAL;
                        currPos.left = newPt;
                        size += 1;
                    } else {
                        currPos = currPos.left;
                    }

                } else {
                    if (currPos.right == null) {
                        newPt.intercept = VERTICAL;
                        currPos.right = newPt;
                        size += 1;
                    } else {
                        currPos = currPos.right;
                    }
                }
            }

        }

    }

    public boolean contains(Point2D p) {
        Node currPos = root;

        if (p == null) throw new IllegalArgumentException("Points cannot be null");

        while (currPos != null) {
            if (p.equals(currPos.value)) {
                return true;
            }
            if (currPos.intercept == VERTICAL) {
                if (p.x() < currPos.value.x()) {
                    currPos = currPos.left;
                } else currPos = currPos.right;

            } else {
                if (p.y() < currPos.value.y()) {
                    currPos = currPos.left;
                } else currPos = currPos.right;
            }
        }
        return false;

    }

    public Iterable<Point2D> range(RectHV rect) {
        pointsinRange = new LinkedList<Point2D>();

        search(root, rect);
        return pointsinRange;

    }

    public void search(Node node, RectHV rect) {

        if (node == null) throw new IllegalArgumentException("Points cannot be empty");

        if (node.intercept == VERTICAL) {

            if (node.value.x() < rect.xmin()) {
                search(node.right, rect);
            } else if (node.value.x() > rect.xmax()) {
                search(node.left, rect);

            } else {
                search(node.left, rect);
                search(node.right, rect);

                if (rect.contains(node.value)) {
                    pointsinRange.add(node.value);
                }
            }
        } else {


            if (node.value.y() < rect.ymin()) {
                search(node.right, rect);
            } else if (node.value.y() > rect.ymax()) {
                search(node.left, rect);
            } else {
                search(node.left, rect);
                search(node.right, rect);

                if (rect.contains(node.value)) {
                    pointsinRange.add(node.value);
                }
            }
        }

    }


    public static void main(String[] args) {
        KdTree tree = new KdTree();
        tree.insert(new Point2D(0.4, 0.2));

        StdOut.println(tree.contains(new Point2D(0.4, 0.2)));
        StdOut.println("Is the tree empty?:" + tree.isEmpty());
        StdOut.println("The size of the tree is:" + tree.size());
    }


}
