public class Bred {
    public static void main(String[] args) {
        SendingStatistics sm=new SendingStatistics();
        sm.setMaxCategoryStat("хавчик",2);
        System.out.println(sm.toString());
        Expenses expenses=new Expenses("булка","2022.08.12",100);
        System.out.println(expenses.getYear());
        System.out.println(expenses.getDate());
        String[]date="2022.08.12".split("\\.");
        System.out.println(date[0]);
    }

}
