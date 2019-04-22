package f2finterviews.raghavendra;

public abstract class DrinkFormula {

    private int waterQuantityInMl;
    private int flavorQuantityInMl;
    private int sugarQuantityInGrams;

    public DrinkFormula(int waterQuantityInMl, int flavorQuantityInMl, int sugarQuantityInGrams) {
        this.waterQuantityInMl = waterQuantityInMl;
        this.flavorQuantityInMl = flavorQuantityInMl;
        this.sugarQuantityInGrams = sugarQuantityInGrams;
    }

    public abstract int calculateCalorie();

}
