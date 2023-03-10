import com.google.gson.Gson;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class LogicManagerStream implements Serializable {
    private Map<String, String> catMap = new HashMap<>();
    private ArrayList<Expenses> arrayListExpenses = new ArrayList<>();
    private Map<String, Integer> countMap = new HashMap<>();

    public LogicManagerStream loadFromBinFile(File file) {
        try (FileInputStream fileInputStream = new FileInputStream(file);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            LogicManagerStream lms = (LogicManagerStream) objectInputStream.readObject();
            return lms;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return null;
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
        Gson gson = new Gson();
        Expenses expenses = gson.fromJson(jsonStrIn, Expenses.class);
        return expenses;
    }

    public Map<String, Integer> getCountMap() {
        return countMap;
    }

    public SendingStatistics maxCategoryStatFinder(String jsonStrIn) throws IOException {
        String valueCat;
        Expenses expenses = jsonToObj(jsonStrIn);//?????????????????? obj ???? ???????????? json, ?? ???????????????????????? ?????????????? ???? obj
        String titleKey = expenses.getTitle();
        if (catMap == null || catMap.isEmpty()) {
            readTsv();
        }
        if (catMap.containsKey(titleKey)) {
            valueCat = catMap.get(titleKey); //?????????????????? ???????????????????????? ?????????????????? ???? ???? ?????????? ???? ?????????????? ??????????????????
        } else {
            valueCat = "????????????";
        }
        expenses.setCategory(valueCat);
        arrayListExpenses.add(expenses);//?????????????? ???????????? ????????????????

        //?????????????? max ???????????????? ???? ???????? ????????????
        countMap = arrayListExpenses.stream()
                .collect(Collectors.groupingBy(Expenses::getCategory, Collectors.summingInt(Expenses::getSum)));
        Optional<Map.Entry<String, Integer>> mapMax = countMap.entrySet().stream().max(Map.Entry.comparingByValue());
        SendingStatistics sendingStatistics = new SendingStatistics();
        sendingStatistics.setMaxCategoryStat(mapMax.get().getKey(), mapMax.get().getValue());
        //?????????????? max ???????????????? ???? ??????

        countMap = arrayListExpenses.stream()
                .filter((a) -> a.getYear().equals(expenses.getYear()))
                .collect(Collectors.groupingBy(Expenses::getCategory, Collectors.summingInt(Expenses::getSum)));
        Optional<Map.Entry<String, Integer>> mapMaxYear = countMap.entrySet().stream().max(Map.Entry.comparingByValue());
        sendingStatistics.setMaxYearCategory(mapMaxYear.get().getKey(), mapMaxYear.get().getValue());

        //?????????????? max ???????????????? ???? ??????????
        countMap = arrayListExpenses.stream()
                .filter((a) -> a.getYear().equals(expenses.getYear()))
                .filter((a) -> a.getMonth().equals(expenses.getMonth()))
                .collect(Collectors.groupingBy(Expenses::getCategory, Collectors.summingInt(Expenses::getSum)));
        Optional<Map.Entry<String, Integer>> mapMaxMonth = countMap.entrySet().stream().max(Map.Entry.comparingByValue());
        sendingStatistics.setMaxMonthCategory(mapMaxMonth.get().getKey(), mapMaxMonth.get().getValue());

        //?????????????? max ???????????????? ???? ????????
        countMap = arrayListExpenses.stream()
                .filter((a) -> a.getYear().equals(expenses.getYear()))
                .filter((a) -> a.getMonth().equals(expenses.getMonth()))
                .filter((a) -> a.getDay().equals(expenses.getDay()))
                .collect(Collectors.groupingBy(Expenses::getCategory, Collectors.summingInt(Expenses::getSum)));
        Optional<Map.Entry<String, Integer>> mapMaxDay = countMap.entrySet().stream().max(Map.Entry.comparingByValue());
        sendingStatistics.setMaxDayCategory(mapMaxDay.get().getKey(), mapMaxDay.get().getValue());
        return sendingStatistics;
    }

}