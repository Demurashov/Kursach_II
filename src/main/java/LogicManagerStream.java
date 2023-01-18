import com.google.gson.Gson;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LogicManagerStream implements Serializable {
    private  Map<String, String> catMap = new HashMap<>();
    private  ArrayList<Expenses> arrayListExpenses = new ArrayList<>();
    private  Map<String, Integer> countMap = new HashMap<>();

    public LogicManagerStream loadFromBinFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
          LogicManagerStream lms = (LogicManagerStream) objectInputStream.readObject();
          return lms;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null ;
    }
    public void saveBin(File file) {
        try (FileOutputStream fileOutStr = new FileOutputStream(file);
             ObjectOutputStream objOutStr = new ObjectOutputStream(fileOutStr)) {
            objOutStr.writeObject(this);
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
    }

    public void readTsv() throws IOException {
        TsvParserSettings settings = new TsvParserSettings();
        TsvParser parser = new TsvParser(settings);
        List<String[]> allRows = parser.parseAll(new FileReader("categories.tsv"));
        catMap = allRows.stream()
                .collect(Collectors.toMap(e -> e[0], e -> e[1]));

    }

    public Expenses jsonToObj(String jsonStrIn) {
        //String strIn="{"title": "булка", "date": "2022.02.08", "sum": 200}";
        Gson gson = new Gson();
        Expenses expenses = gson.fromJson(jsonStrIn, Expenses.class);
        return expenses;
    }

    public SendingItem maxCategoryFinder(String jsonStrIn) throws IOException {
        String valueCat;
        Expenses expenses = jsonToObj(jsonStrIn);//получение obj из строки json, и наименования покупки из obj
        String titleKey = expenses.getTitle();
        if (catMap == null || catMap.isEmpty()) {
            readTsv();
        }
        if (catMap.containsKey(titleKey)) {
            valueCat = catMap.get(titleKey); //получение наименования категории по ее ключу из словаря категорий
        } else {
            valueCat = "другое";
        }
        expenses.setCategory(valueCat);
        arrayListExpenses.add(expenses);
        countMap = arrayListExpenses.stream()
                .collect(Collectors.groupingBy(Expenses::getCategory, Collectors.summingInt(Expenses::getSum)));
        Optional<Map.Entry<String, Integer>> mapMax = countMap.entrySet().stream().collect(Collectors.maxBy(Comparator.comparing((a) -> a.getValue())));
//        System.out.println(mapMax.toString());
//        System.out.println();
//        System.out.println("Ключ: " + mapMax.get().getKey() + "Знач: " + mapMax.get().getValue());
        SendingItem sendingItem = new SendingItem(mapMax.get().getKey(), mapMax.get().getValue());
        return sendingItem;
    }


    public Map<String, Integer> getCountMap() {
        return countMap;
    }

}