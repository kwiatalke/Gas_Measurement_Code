import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Coefficients coefficients = new Coefficients();
        RoundedDuct roundedDuct = new RoundedDuct();
        RectangleDuct rectangleDuct = new RectangleDuct();

        printFirstMenu();

        System.out.println("****************************************");
        System.out.println("Set a temperature of measured gas: ");
        coefficients.setGasTemperature(scanner.nextDouble());
        double gasTemperature = coefficients.getGasTemperature();

        System.out.println("****************************************");
        System.out.println("Set a constant coefficient of Prandtl Tube: ");
        coefficients.setPrandtlTubeCoefficient(scanner.nextDouble());
        double prandtlTube = coefficients.getPrandtlTubeCoefficient();

        System.out.println("****************************************");
        System.out.println("Set a value of static pressure: ");
        coefficients.setStaticPressure(scanner.nextInt());

        System.out.println("****************************************");
        System.out.println("Set a value of ambient pressure: ");
        coefficients.setAmbientPressure(scanner.nextInt());
        int absolutePressure = coefficients.getAmbientPressure() + coefficients.getStaticPressure();

        printSecondMenu();
        boolean quit = false;

        while (!quit) {
            System.out.println("Your choice: ");
            int choice = scanner.nextInt();

            if ((choice != 0) && (choice != 1) && (choice !=2)) {
                System.out.println("Incorrect value. Try again.");
            }
            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.println("Set a value of diameter: ");
                    roundedDuct.setDiameter(scanner.nextDouble());
                    double diameter = roundedDuct.getDiameter();
                    double area = Math.PI * Math.pow(((diameter / 1000) / 2), 2);

                    System.out.println("Set an amount of measurement per radius: ");
                    roundedDuct.setMeasurementAmount(scanner.nextInt());

                    double[] coefficient = roundedDuct.coefficientPoint(roundedDuct.getMeasurementAmount());

                    ArrayList<Double> measurementCoordinate = roundedDuct.measurementCoordinate(coefficient);

                    ArrayList<Double> pressureMeasurement = roundedDuct.pressureMeasurement(measurementCoordinate);

                    double velocityOfGas = gasVelocity(pressureMeasurement, prandtlTube, gasTemperature, absolutePressure);

                    double density = densityOfGas(gasTemperature, absolutePressure);

                    gasFlow(velocityOfGas, area, density);

                    quit = true;
                    break;

                case 2:
                    System.out.println("Set a value of first side: ");
                    rectangleDuct.setSideA(scanner.nextDouble());
                    double firstSide = rectangleDuct.getSideA();

                    System.out.println("Set a value of second side: ");
                    rectangleDuct.setSideB(scanner.nextDouble());
                    double secondSide = rectangleDuct.getSideB();
                    area = (firstSide / 1000) * (secondSide / 1000);

                    System.out.println("Set an amount of measurement per first side: ");
                    rectangleDuct.setMeasurementAmountA(scanner.nextInt());

                    System.out.println("Set an amount of measurement per second side: ");
                    rectangleDuct.setMeasurementAmountB(scanner.nextInt());

                    double[] coefficientA = rectangleDuct.coefficientPoint(rectangleDuct.getMeasurementAmountA());
                    double[] coefficientB = rectangleDuct.coefficientPoint(rectangleDuct.getMeasurementAmountB());

                    ArrayList<Double> measurementCoordinateA = rectangleDuct.measurementCoordinateA(firstSide, coefficientA);
                    ArrayList<Double> measurementCoordinateB = rectangleDuct.measurementCoordinateB(secondSide, coefficientB);

                    pressureMeasurement = rectangleDuct.pressureMeasurement(measurementCoordinateA, measurementCoordinateB);

                    velocityOfGas = gasVelocity(pressureMeasurement, prandtlTube, gasTemperature, absolutePressure);

                    density = densityOfGas(gasTemperature, absolutePressure);

                    gasFlow(velocityOfGas, area, density);

                    quit = true;
                    break;
            }
        }
    }

    private static void printFirstMenu() {
        System.out.println("The purpose of the program is +" +
                " to calculate the gas volume flow on the + " +
                "basis of pressure measurements");
        System.out.println("****************************************");
        System.out.println("For input data use the following units: ");
        System.out.println("Pressure - Pa");
        System.out.println("Temperature - °C");
        System.out.println("Now please insert following data.");
    }

    private static void printSecondMenu() {
        System.out.println("What is the shape of measured duct?");
        System.out.println("Available actions: ");
        System.out.println("0 - Quit");
        System.out.println("1 - Circle");
        System.out.println("2 - Rectangle");
    }

    private static double densityOfGas(double temperature, int pressure) {
        return 1.2928 * (pressure / 101325.0) * (273.15 / (temperature + 273.15));
    }

    private static double gasVelocity(ArrayList<Double> pressure, double prandtlTube, double gasTemperature, int absolutePressure) {
        double sumVelocity = 0;
        int count = 0;

        for (int i = 0; i < pressure.size(); i++) {
            double velocityCalculation = prandtlTube * (Math.pow(((573.87 * gasTemperature) + 156752.77) / absolutePressure, 0.5)) * (Math.pow(pressure.get(i), 0.5));
            sumVelocity = sumVelocity + velocityCalculation;
            count++;
        }
        return sumVelocity / count;
    }

    private static void gasFlow(double velocity, double area, double density) {
        Scanner scanner = new Scanner(System.in);

        double gasFlow = area * velocity; // result in m3/s
        double massFlow = gasFlow * density;

        System.out.println("Choose unit of result: ");
        System.out.println("0 - quit");
        System.out.println("1 - m3/s");
        System.out.println("2 - m3/min");
        System.out.println("3 - m3/h");
        System.out.println("4 - kg/s");
        System.out.println("5 - kg/min");
        System.out.println("6 - kg/h");

        boolean quit = false;

        while (!quit) {
            System.out.println("Your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 0:
                    quit = true;
                    break;
                case 1:
                    System.out.println("Gas flow = " + String.format("%.2f", gasFlow) + " m3/s");
                    break;
                case 2:
                    System.out.println("Gas flow = " + String.format("%.2f", gasFlow * 60) + " m3/min");
                    break;
                case 3:
                    System.out.println("Gas flow = " + String.format("%.2f", gasFlow * 3600) + " m3/h");
                    break;
                case 4:
                    System.out.println("Gas mass flow = " + String.format("%.2f", massFlow) + " kg/s");
                    break;
                case 5:
                    System.out.println("Gas mass flow = " + String.format("%.2f", massFlow * 60) + " kg/min");
                    break;
                case 6:
                    System.out.println("Gas mass flow = " + String.format("%.2f", massFlow * 3600) + " kg/h");
                    break;
            }
        }
    }
}