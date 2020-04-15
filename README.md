# Triangle Shape Determiner

Given that the wording of the assignment was:

`It should take the lengths of the triangle's three sides as input, and return whether the triangle is equilateral, 
isosceles or scalene.`

The Triangle class can only be instantiated by taking the 3 side lengths of the triangle as parameters.

However, to get the result back one has to call:

    Triangle triangle = Triangle.of(2, 3, 4);
and then:

    triangle.getShape();

to achieve that result.