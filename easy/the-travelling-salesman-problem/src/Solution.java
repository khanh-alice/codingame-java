import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;

class Solution {

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int pointsCount = in.nextInt();
    Queue<Point> points = new ArrayDeque<>(pointsCount);

    for (int i = 0; i < pointsCount; i++) {
      int x = in.nextInt();
      int y = in.nextInt();

      points.add(new Point(x, y));
    }

    double totalDistance = 0;
    Point start = points.poll();
    Point currPoint = start;
    Queue<Point> currQueue = points;

    while (!currQueue.isEmpty()) {
      double min = Double.MAX_VALUE;
      Point nextPoint = null;
      Queue<Point> nextQueue = new ArrayDeque<>(pointsCount);

      while (!currQueue.isEmpty()) {
        Point point = currQueue.poll();
        double distance = point.distanceTo(currPoint);

        if (distance < min) {
          min = distance;
          if (nextPoint != null) nextQueue.add(nextPoint);
          nextPoint = point;
        } else {
          nextQueue.add(point);
        }
      }

      totalDistance += min;
      currPoint = nextPoint;
      currQueue = nextQueue;
    }

    totalDistance += currPoint.distanceTo(start);
    System.out.println(Math.round(totalDistance));
  }
}

class Point {

  int x, y;

  public Point(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public double distanceTo(Point point) {
    int deltaX = point.x - x;
    int deltaY = point.y - y;
    return Math.sqrt(deltaX * deltaX + deltaY * deltaY);
  }
}
