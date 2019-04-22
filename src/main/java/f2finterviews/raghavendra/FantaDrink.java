package f2finterviews.raghavendra;

public class FantaDrink extends DrinkFormula {

    public FantaDrink(int waterQuantityInMl, int flavorQuantityInMl, int sugarQuantityInGrams) {
        super(waterQuantityInMl, flavorQuantityInMl, sugarQuantityInGrams);
    }

    @Override
    public int calculateCalorie() {
        return 295;
    }
}
