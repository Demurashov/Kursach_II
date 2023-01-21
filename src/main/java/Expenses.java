import java.io.Serializable;
import java.util.Date;

public class Expenses implements Serializable {
    private String title;
    private String date;
    private int sum;
    private String category = null;

    public Expenses(String title, String date, int sum) {
        this.title = title;
        this.date = date;
        this.sum = sum;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public int getSum() {
        return sum;
    }

    public String getCategory() {
        return category;
    }

    public String getYear() {
        String[] arrYear = date.split("\\.");
        if (arrYear.length > 0) {
            return arrYear[0];
        }
        return null;
    }

    public String getMonth() {
        String[] arrMonth = date.split("\\.");
        if (arrMonth.length > 1) {
            return arrMonth[1];
        }
        return null;
    }

    public String getDay() {
        String[] arrDay = date.split("\\.");
        if (arrDay.length > 2) {
            return arrDay[2];
        }
        return null;
    }
}
