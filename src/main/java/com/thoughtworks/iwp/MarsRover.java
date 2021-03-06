package com.thoughtworks.iwp;

public class MarsRover {
    private Point coordinate;
    private Compass compass;
    private final Point plateauMaxPoint;

    public MarsRover(Point coordinate, Compass compass, Point plateauMaxPoint) {
        this.coordinate = coordinate;
        this.compass = compass;
        this.plateauMaxPoint = plateauMaxPoint;
    }

    public String getPosition() {
        return coordinate.toString() + " " + compass;
    }

    public void doInstruction(String message) {
        for (Character character : message.toCharArray()) {
            switch (character) {
                case 'L':
                    compass = CompassGenerator.rotateLeft(compass);
                    break;
                case 'R':
                    compass = CompassGenerator.rotateRight(compass);
                    break;
                case 'M': {
                    Point temp = increaseCoordinate();
                    if (isWithinPlateau(temp)) {
                        coordinate = temp;
                        break;
                    } else throw new MovedOutOfPlateau();
                }
            }
        }
    }

    private boolean isWithinPlateau(Point temp) {
        return plateauMaxPoint.isGreater(temp) && new Point(0, 0).isLesser(temp);
    }

    private Point increaseCoordinate() {
        switch (compass) {
            case N:
                return coordinate.increaseY();
            case E:
                return coordinate.increaseX();
            case W:
                return coordinate.decreaseX();
            case S:
                return coordinate.decreaseY();
        }
        return coordinate;
    }
}
