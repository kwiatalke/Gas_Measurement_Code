import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RectangleDuct {
    private double sideA;
    private double sideB;
    private int measurementAmountA;
    private int measurementAmountB;

    public double getSideA() {
        return sideA;
    }

    public double getSideB() {
        return sideB;
    }

    public int getMeasurementAmountA() {
        return measurementAmountA;
    }

    public int getMeasurementAmountB() {
        return measurementAmountB;
    }

    public void setSideA(double sideA) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = true;

        while (quit) {
            if (sideA <= 0) {
                System.out.println("Side must be greater than zero!");
                System.out.println("Set correct value of side: ");
                sideA = scanner.nextDouble();
            } else {
                quit = false;
            }
        }
        this.sideA = sideA;
    }

    public void setSideB(double sideB) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = true;

        while (quit) {
            if (sideB <= 0) {
                System.out.println("Side must be greater than zero!");
                System.out.println("Set correct value of side: ");
                sideB = scanner.nextDouble();
            } else {
                quit = false;
            }
        }
        this.sideB = sideB;
    }

    public void setMeasurementAmountA(int measurementAmountA) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = true;

        while (quit) {
            if ((measurementAmountA == 5) || (measurementAmountA == 6) || (measurementAmountA == 7)) {
                quit = false;
            } else {
                System.out.println("Amount of measurement per side must be in range 5 - 7!");
                System.out.println("Set correct amount of measurement: ");
                measurementAmountA = scanner.nextInt();
            }
            this.measurementAmountA = measurementAmountA;
        }
    }

    public void setMeasurementAmountB(int measurementAmountB) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = true;

        while (quit) {
            if ((measurementAmountB == 5) || (measurementAmountB == 6) || (measurementAmountB == 7)) {
                quit = false;
            } else {
                System.out.println("Amount of measurement per side must be in range 5 - 7!");
                System.out.println("Set correct amount of measurement: ");
                measurementAmountB = scanner.nextInt();
            }
            this.measurementAmountB = measurementAmountB;
        }
    }

    public double[] coefficientPoint(int measurementAmount) {
        double[] coefficient = new double[]{};
        if (measurementAmount == 5) {
            coefficient = new double[]{0, 0.212, 0.462};
        } else if (measurementAmount == 6) {
            coefficient = new double[]{0.063, 0.265, 0.439};
        } else if (measurementAmount == 7) {
            coefficient = new double[]{0, 0.134, 0.297, 0.447};
        }
        return coefficient;
    }

    public ArrayList<Double> measurementCoordinateA(double sideA, double[] coefficient) {
        ArrayList<Double> coordinate = new ArrayList<>();

        for (int i = 0; i < coefficient.length; i++) {
            if (coefficient[i] > 0) {
                double numberOne = (sideA / 2) - (coefficient[i] * sideA);
                double numberTwo = (sideA / 2) + (coefficient[i] * sideA);
                numberOne = Math.round(numberOne * 10d) / 10d;
                numberTwo = Math.round(numberTwo * 10d) / 10d;
                coordinate.add(numberOne);
                coordinate.add(numberTwo);
            } else if (coefficient[i] == 0) {
                double numberThree = (sideA / 2);
                numberThree = Math.round(numberThree * 10d) / 10d;
                coordinate.add(numberThree);
            }
        }
        Collections.sort(coordinate);
        return coordinate;
    }

    public ArrayList<Double> measurementCoordinateB(double sideB, double[] coefficient) {
        ArrayList<Double> coordinate = new ArrayList<>();

        for (int i = 0; i < coefficient.length; i++) {
            if (coefficient[i] > 0) {
                double numberOne = (sideB / 2) - (coefficient[i] * sideB);
                double numberTwo = (sideB / 2) + (coefficient[i] * sideB);
                numberOne = Math.round(numberOne * 10d) / 10d;
                numberTwo = Math.round(numberTwo * 10d) / 10d;
                coordinate.add(numberOne);
                coordinate.add(numberTwo);
            } else if (coefficient[i] == 0) {
                double numberThree = (sideB / 2);
                numberThree = Math.round(numberThree * 10d) / 10d;
                coordinate.add(numberThree);
            }
        }
        Collections.sort(coordinate);
        return coordinate;
    }

    public ArrayList<Double> pressureMeasurement(ArrayList<Double> measurementCoordinateA, ArrayList<Double> measurementCoordinateB) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> pressure = new ArrayList<>();
        int count = 1;

        for (int i = 0; i < measurementCoordinateA.size(); i++) {
            for (int j = 0; j < measurementCoordinateB.size(); j++) {
                System.out.println("Coordinate of measurement point number " + count + " : " + measurementCoordinateA.get(i) + " x " + measurementCoordinateB.get(j));
                System.out.println("Measured pressure value: ");
                pressure.add(scanner.nextDouble());
            }
        }
        return pressure;
    }
}
