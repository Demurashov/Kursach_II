
import java.io.Serializable;

public class SendingStatistics implements Serializable {

    private MaxCategory maxCategory;
    private MaxYearCategory maxYearCategory;
    private MaxMonthCategory maxMonthCategory;
    private MaxDayCategory maxDayCategory;

    public void setMaxCategoryStat(String category, int sum) {
        this.maxCategory = new MaxCategory(category, sum);
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

    public MaxCategory getMaxCategoryStat() {
        return maxCategory;
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
        return "Max category: " + maxCategory.getCategory() + " Summ: " + maxCategory.getSum();
    }
}

class MaxCategory {
    private String category;
    private int sum;

    public MaxCategory(String category, int sum) {
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