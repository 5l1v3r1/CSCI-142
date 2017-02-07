import java.util.ArrayList;

/**
 * Created by ptnega on 06/02/2017.
 */
public class Circle implements Shape{
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    public double getRadius() {
        return this.radius;
    }

    @Override
    public String toString() {
        return "Circle{" +
                "radius=" + radius +
                "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Circle circle = (Circle) o;

        return Double.compare(circle.getRadius(), getRadius()) == 0;
    }

    @Override
    public int hashCode() {
        return 0;
    }

    @Override
    public double getArea() {
        return 0.0;
    }

    @Override
    public double getPerimeter() {
        return 0.0;
    }

 }
