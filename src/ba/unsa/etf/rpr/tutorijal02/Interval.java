package ba.unsa.etf.rpr.tutorijal02;

public class Interval {
    private double pocetna, krajnja;
    private boolean pocetnaPripada, krajnjaPripada;

    public Interval(double poc, double kraj, boolean pocPripada, boolean krajPripada) {
        if(poc > kraj) throw new IllegalArgumentException ("Neispravan mjesec");
        pocetna=poc; krajnja=kraj;
        pocetnaPripada=pocPripada; krajnjaPripada=krajPripada;
    }
    public Interval() {
        pocetna=0;
        krajnja=0;
        pocetnaPripada=false;
        krajnjaPripada=false;
    }
    public boolean isNull() {
        if(this.equals(new Interval())) return true;
        return false;
    }
    public boolean isIn(double tacka) {
        if(tacka > pocetna && tacka < krajnja) return true;
        else {
            if(tacka==pocetna) {
                if (pocetnaPripada) return true;
                else return false;
            }
             else if(tacka==krajnja){
                 if(krajnjaPripada) return true;
                 else return false;
            }
        }
        return false;
    }
    public static Interval intersect(Interval i1, Interval i2) {
        if(i1.krajnja < i2.pocetna || i2.krajnja < i1.pocetna) return new Interval();

        if(i1.pocetna < i2.pocetna) {
            if(i2.krajnja > i1.krajnja)
                return new Interval(i2.pocetna, i1.krajnja, i2.pocetnaPripada, i1.krajnjaPripada);
            if(i2.krajnja == i1.krajnja)
                return new Interval(i2.pocetna, i1.krajnja, i2.pocetnaPripada, (i1.krajnjaPripada && i2.krajnjaPripada));
            if(i2.krajnja < i1.krajnja)
                return new Interval(i2.pocetna, i2.krajnja, i2.pocetnaPripada, i2.krajnjaPripada);
        }
        if(i2.pocetna < i1.pocetna) {
            if(i1.krajnja > i2.krajnja)
                return new Interval(i1.pocetna, i2.krajnja, i1.pocetnaPripada, i2.krajnjaPripada);
            if(i1.krajnja == i2.krajnja)
                return new Interval(i1.pocetna, i2.krajnja, i1.pocetnaPripada, (i2.krajnjaPripada && i1.krajnjaPripada));
            if(i1.krajnja < i2.krajnja)
                return new Interval(i1.pocetna, i1.krajnja, i1.pocetnaPripada, i1.krajnjaPripada);
        }
        if(i1.pocetna == i2.pocetna) {
            if(i1.krajnja==i2.krajnja)
                return new Interval(i1.pocetna, i1.krajnja, (i1.pocetnaPripada && i2.pocetnaPripada), (i1.krajnjaPripada && i2.krajnjaPripada));
            if(i1.krajnja < i2.krajnja)
                return new Interval(i1.pocetna, i1.krajnja, (i1.pocetnaPripada && i2.pocetnaPripada), i1.krajnjaPripada);
            if(i1.krajnja > i2.krajnja)
                return new Interval(i1.pocetna, i2.krajnja, (i1.pocetnaPripada && i2.pocetnaPripada), i2.krajnjaPripada);
        }
        return new Interval();
    }
    public Interval intersect(Interval i) {
        return intersect(this, i);
    }
    @Override
    public String toString() {
        String izlaz = "";
        if(!this.equals(new Interval())) {
            if (pocetnaPripada) izlaz += "[";
            else izlaz += "(";
            izlaz += (pocetna + "," + krajnja);
            if (krajnjaPripada) izlaz += "]";
            else izlaz += ")";
        } else izlaz +="()";
        return izlaz;
    }
    @Override
    public boolean equals(Object o) {
        Interval i = (Interval) o;
        return pocetna==i.pocetna && krajnja==i.krajnja && pocetnaPripada==i.pocetnaPripada && krajnjaPripada==i.krajnjaPripada;
    }

}
