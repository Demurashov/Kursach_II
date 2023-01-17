public class SendingItem {
    private MaxCategory maxCategory;
    public SendingItem(String category,int sum){
        MaxCategory maxCategory=new MaxCategory(category,sum);
        this.maxCategory=maxCategory;
    }
    public MaxCategory getMaxCategory(){
        return maxCategory;
    }
    @Override
    public String toString() {
        return "Max category: "+maxCategory.getCategory()+" Summ: "+maxCategory.getSum();
    }
}
class MaxCategory{
    private String category;
    private int sum;
    MaxCategory(String category,int sum){
        this.category=category;
        this.sum=sum;
    }
    public void setSum(int in){
        sum=in;
    }
    public void addtSum(int in) {
         sum=sum+in;
    }
    public String getCategory(){
        return category;
    }
    public int getSum(){
        return sum;
    }

}