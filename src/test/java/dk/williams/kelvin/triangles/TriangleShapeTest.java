package dk.williams.kelvin.triangles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangleShapeTest {
    @Test
    public void equilateralTriangle() {
        Triangle equilateralTriangle = Triangle.of(7.59, 7.59,7.59);

        assertEquals(Triangle.Shape.EQUILATERAL.name, equilateralTriangle.getShape().name);
    }

    @Test
    public void isoscelesTriangle() {
        Triangle isoscelesTriangle = Triangle.of(3, 5,3);

        assertEquals(Triangle.Shape.ISOSCELES.name, isoscelesTriangle.getShape().name);
    }

    @Test
    public void scaleneTriangle() {
        Triangle scaleneTriangle = Triangle.of(1.001, 1.00100001,1.0009);

        assertEquals(Triangle.Shape.SCALENE.name, scaleneTriangle.getShape().name);
    }

    @Test(expected = IllegalArgumentException.class)
    public void negativeLengthArgumentForTriangle() {
        Triangle.of(-1, -0.4,-1.5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void edgeTooLong() {
        Triangle.of(1, 2, 3);
    }


}
