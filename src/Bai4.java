import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Bai4 {
    public static void main(String[] args) throws IOException {
        Set<Point> set1 = new HashSet<>();
        Set<Point> set2 = new HashSet<>();
        Set<Point> set3 = new HashSet<>();
        Point pointA = new Point(800, 800);
        Point pointB = new Point(4000, 800);
        Point pointC = new Point(2400, 2400);
        while (set1.size() != 8000) {
            Point point = new Point((int) (Math.random() * 5000), (int) (Math.random() * 5000));
            if (pointA.getDistanceBetween(point) <= 400) set1.add(point);
        }
        while (set2.size() != 10000) {
            Point point = new Point((int) (Math.random() * 5000), (int) (Math.random() * 5000));
            if (pointB.getDistanceBetween(point) <= 500 && !set1.contains(point)) set2.add(point);
        }
        while (set3.size() != 12000) {
            Point point = new Point((int) (Math.random() * 5000), (int) (Math.random() * 5000));
            if (pointC.getDistanceBetween(point) <= 600 && !set1.contains(point) && !set2.contains(point)) set3.add(point);
        }
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("output_4.txt"));
        Set<Point> total = new HashSet<>(set1);
        total.addAll(set2);
        total.addAll(set3);
        total.forEach(point -> {
            try {
                bufferedWriter.write(point.x + "|" + point.y);
                bufferedWriter.newLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        bufferedWriter.flush();
        bufferedWriter.close();
    }
}

class Point {
    int x;
    int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public double getDistanceBetween(Point anotherPoint) {
        return Math.sqrt(Math.pow(this.x - anotherPoint.x, 2) + Math.pow(this.y - anotherPoint.y, 2));
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj.getClass() != this.getClass()) return false;
        Point anotherPoint = (Point) obj;
        return anotherPoint.x == this.x && anotherPoint.y == this.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
