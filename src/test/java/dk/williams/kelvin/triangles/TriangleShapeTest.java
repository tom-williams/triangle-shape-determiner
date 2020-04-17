package dk.williams.kelvin.triangles;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TriangleShapeTest {
    @Test
    public void equilateralTriangle() {
        Triangle equilateralTriangle = Triangle.of(7.59, 7.59,7.59);

        assertEquals(Triangle.Shape.EQUILATERAL.value, equilateralTriangle.getShape().value);
    }

    @Test
    public void isoscelesTriangle() {
        Triangle isoscelesTriangle = Triangle.of(3, 5,3);

        assertEquals(Triangle.Shape.ISOSCELES.value, isoscelesTriangle.getShape().value);
    }

    @Test
    public void scaleneTriangle() {
        Triangle scaleneTriangle = Triangle.of(1.001, 1.00100001,1.0009);

        assertEquals(Triangle.Shape.SCALENE.value, scaleneTriangle.getShape().value);
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
