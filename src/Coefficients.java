public class Coefficients {
    private double gasTemperature;
    private double prandtlTubeCoefficient;
    private int ambientPressure;
    private int staticPressure;

    public double getGasTemperature() {
        return gasTemperature;
    }

    public void setGasTemperature(double gasTemperature) {
        this.gasTemperature = gasTemperature;
    }

    public double getPrandtlTubeCoefficient() {
        return prandtlTubeCoefficient;
    }

    public void setPrandtlTubeCoefficient(double prandtlTubeCoefficient) {
        this.prandtlTubeCoefficient = prandtlTubeCoefficient;
    }

    public int getAmbientPressure() {
        return ambientPressure;
    }

    public void setAmbientPressure(int ambientPressure) {
        this.ambientPressure = ambientPressure;
    }

    public int getStaticPressure() {
        return staticPressure;
    }

    public void setStaticPressure(int staticPressure) {
        this.staticPressure = staticPressure;
    }
}
