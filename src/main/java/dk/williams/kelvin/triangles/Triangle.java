package dk.williams.kelvin.triangles;

import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter
@ToString
public final class Triangle {
    private final double abEdgeLength;
    private final double bcEdgeLength;
    private final double acEdgeLength;
    private final Shape shape;

    /**
     * The shape, or type, of a Triangle that can be exclusively determined by the lengths of the Triangle's edges.
     */
    public enum Shape {
        EQUILATERAL("Equilateral"),  // all 3 edges are the same length
        ISOSCELES("Isosceles"),      // only 2 of the edges are the same length
        SCALENE("Scalene");          // no edges are of the same length

        public final String value;

        Shape(String value) {
            this.value = value;
        }
    }

    private Triangle(double abEdgeLength, double bcEdgeLength, double acEdgeLength) {
        this.abEdgeLength = abEdgeLength;
        this.bcEdgeLength = bcEdgeLength;
        this.acEdgeLength = acEdgeLength;

        validateEdges();
        this.shape = determineShape();
    }

    /**
     * Creates a Triangle object composed of the edges AB, BC, and AC, where A, B, and C are the
     * vertices of a given Triangle.
     * <p>
     * Each parameter denotes the length of the given edge, irregardless of unit
     * (Each edge length should be given in the same unit).
     *
     * @param abEdgeLength length of the AB edge
     * @param bcEdgeLength length of the BC edge
     * @param acEdgeLength length of the AC edge
     *
     * @throws IllegalArgumentException if any of the edges are less than or equal to zero,
     *                                  or the given lengths of the edges cannot create a Triangle
     */
    public static Triangle of(double abEdgeLength, double bcEdgeLength, double acEdgeLength) {
        return new Triangle(abEdgeLength, bcEdgeLength, acEdgeLength);
    }

    private void validateEdges() {
        List<Double> edgeLengths = List.of(abEdgeLength, bcEdgeLength, acEdgeLength);

        checkHasLength(edgeLengths);
        checkLengthsValid(edgeLengths);
    }

    /*
     * The longest edge of the Triangle must be shorter than the sum of the two smaller edges.
     *
     * This actually also handles any negative or zero length edges, as any combination of negative or
     * zero edges would also fail this check.
     *
     * However, also running a negative or zero length check first results in a better error message.
     */
    private void checkLengthsValid(List<Double> edgeLengths) {
        var sortedEdges = edgeLengths.stream()
            .sorted()
            .collect(Collectors.toList());

        if (sortedEdges.size() == 3) {
            if (sortedEdges.get(2) < sortedEdges.get(0) + sortedEdges.get(1)) {
                // All ok
                return;
            }
        }

        throw new IllegalArgumentException(
            String.format("Longest edge has to be less than the sum of other two edges: [%1$f, %2$1f, %3$f]",
                abEdgeLength, bcEdgeLength, acEdgeLength)
        );
    }

    private void checkHasLength(List<Double> edgeLengths) {
        if (edgeLengths.stream().anyMatch(edge -> edge <= 0)) {
            throw new IllegalArgumentException(
                String.format("Edge lengths have to be greater than zero: [%1$f, %2$1f, %3$f]",
                    abEdgeLength, bcEdgeLength, acEdgeLength)
            );
        }
    }

    private Shape determineShape() {
        var edgeLengthFrequency = numberFrequency(List.of(abEdgeLength, bcEdgeLength, acEdgeLength));

        switch (edgeLengthFrequency.size()) {
            case 1:
                return Shape.EQUILATERAL;
            case 2:
                return Shape.ISOSCELES;
            case 3:
                return Shape.SCALENE;
            default:
                throw new IllegalStateException("Unexpected number of edges");
        }
    }

    private Map<Double, Long> numberFrequency(List<Double> numbers) {
        return numbers.stream()
            .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
            ));
    }
}
