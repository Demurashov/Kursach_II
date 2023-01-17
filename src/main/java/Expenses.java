import java.util.Date;

public class Expenses {
    private String title;
    private String date;
    private int sum;
    private String category=null;
    public Expenses(String title,String date,int sum){
        this.title=title;
        this.date=date;
        this.sum=sum;
    }
    public void setCategory(String category){
        this.category=category;
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
    public String getCategory(){
        return category;
    }

}
