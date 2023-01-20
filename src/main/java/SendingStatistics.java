
import java.io.Serializable;

public class SendingStatistics implements Serializable {

    private MaxCategoryStat maxCategoryStat;
    private MaxYearCategory maxYearCategory;
    private MaxMonthCategory maxMonthCategory;
    private MaxDayCategory maxDayCategory;

    public void setMaxCategoryStat(String category, int sum) {
        this.maxCategoryStat = new MaxCategoryStat(category, sum);
    }

    public void setMaxYearCategory(String category, int sum) {
        this.maxYearCategory = new MaxYearCategory(category, sum);
    }

    public void setMaxMonthCategory(String category, int sum) {
        this.maxMonthCategory = new MaxMonthCategory(category, sum);
    }

    public void setMaxDayCategory(String category, int sum) {
        this.maxDayCategory = new MaxDayCategory(category, sum);
    }

    public MaxCategoryStat getMaxCategoryStat() {
        return maxCategoryStat;
    }

    public MaxYearCategory getMaxYearCategory() {
        return maxYearCategory;
    }

    public MaxMonthCategory getMaxMonthCategory() {
        return maxMonthCategory;
    }

    public MaxDayCategory getMaxDayCategory() {
        return maxDayCategory;
    }

    @Override
    public String toString() {
        return "Max category: " + maxCategoryStat.getCategory() + " Summ: " + maxCategoryStat.getSum();
    }
}

class MaxCategoryStat {
    private String category;
    private int sum;

    public MaxCategoryStat(String category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    public String getCategory() {
        return category;
    }

    public int getSum() {
        return sum;
    }

}

class MaxYearCategory {
    private String category;
    private int sum;

    public MaxYearCategory(String category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    public String getCategory() {
        return category;
    }

    public int getSum() {
        return sum;
    }
}

class MaxMonthCategory {
    private String category;
    private int sum;

    public MaxMonthCategory(String category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    public String getCategory() {
        return category;
    }

    public int getSum() {
        return sum;
    }
}

class MaxDayCategory {
    private String category;
    private int sum;

    public MaxDayCategory(String category, int sum) {
        this.category = category;
        this.sum = sum;
    }

    public String getCategory() {
        return category;
    }

    public int getSum() {
        return sum;
    }
}