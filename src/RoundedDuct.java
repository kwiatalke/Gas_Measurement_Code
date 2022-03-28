import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class RoundedDuct {
    private double diameter;
    private int measurementAmount;

    public double getDiameter() {
        return diameter;
    }

    public int getMeasurementAmount() {
        return measurementAmount;
    }

    public void setDiameter(double diameter) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = true;

        while (quit) {
            if (diameter <= 0) {
                System.out.println("Diameter must be greater than zero!");
                System.out.println("Set correct value of diameter: ");
                diameter = scanner.nextDouble();
            } else {
                quit = false;
            }
        }
        this.diameter = diameter;
    }

    public void setMeasurementAmount(int measurementAmount) {
        Scanner scanner = new Scanner(System.in);
        boolean quit = true;

        while (quit) {
            if ((measurementAmount == 3) || (measurementAmount == 4) || (measurementAmount == 5)) {
                quit = false;
            } else {
                System.out.println("Amount of measurement per radius must be in range 3 - 5!");
                System.out.println("Set correct amount of measurement: ");
                measurementAmount = scanner.nextInt();
            }
            this.measurementAmount = measurementAmount;
        }
    }

    public double[] coefficientPoint(int measurementAmount) {
        double[] coefficient = new double[]{};
        if (measurementAmount == 3) {
            coefficient = new double[]{0.375, 0.925, 0.936};
        } else if (measurementAmount == 4) {
            coefficient = new double[]{0.331, 0.612, 0.800, 0.952};
        } else if (measurementAmount == 5) {
            coefficient = new double[]{0.287, 0.570, 0.689, 0.847, 0.962};
        }
        return coefficient;
    }

    public ArrayList<Double> measurementCoordinate(double[] coefficient) {
        ArrayList<Double> coordinate = new ArrayList<>();
        double diameter = getDiameter();

        for (int i = 0; i < coefficient.length; i++) {
            double numberOne = (diameter / 2) - ((diameter / 2) * coefficient[i]);
            double numberTwo = (diameter / 2) + ((diameter / 2) * coefficient[i]);
            numberOne = Math.round(numberOne * 10d) / 10d;
            numberTwo = Math.round(numberTwo * 10d) / 10d;

            coordinate.add(numberOne);
            coordinate.add(numberTwo);
        }
        Collections.sort(coordinate);
        return coordinate;
    }

    public ArrayList<Double> pressureMeasurement(ArrayList<Double> measurementCoordinate) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Double> pressure = new ArrayList<>();

        for (int i = 0; i < measurementCoordinate.size(); i++) {
            System.out.println("Coordinate of measurement point number " + (i + 1) + " : " + measurementCoordinate.get(i));
            System.out.println("Measured pressure value: ");
            pressure.add(scanner.nextDouble());
        }
        return pressure;
   }
}
