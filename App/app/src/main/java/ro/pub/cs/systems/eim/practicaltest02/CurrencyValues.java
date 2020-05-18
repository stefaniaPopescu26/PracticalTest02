package ro.pub.cs.systems.eim.practicaltest02;

public class CurrencyValues {
    private double eur;
    private double usd;

    public CurrencyValues() {
        this.eur = 0.0;
        this.usd = 0.0;
    }

    public CurrencyValues(double eur, double usd) {
        this.eur = eur;
        this.usd = usd;
    }

    public double getEur() {
        return eur;
    }

    public double getUsd() {
        return usd;
    }

    public void setEur(double eur) {
        this.eur = eur;
    }

    public void setUsd(double usd) {
        this.usd = usd;
    }
}
