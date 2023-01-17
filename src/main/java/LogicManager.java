import com.google.gson.Gson;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LogicManager {
    private static Map<String, String> catMap = new HashMap<>();
    private static Map<String, Integer> countMap = new HashMap<>();
    private static Map<String, SendingItem> countMap2 = new HashMap<>();
    //читаем TSV и загоняем данные из него в словарь категорий
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

    public void toCatCounter(String jsonStrIn) throws IOException {
        String valueCat;
        if (catMap == null || catMap.isEmpty()) {
            readTsv();
        }
        Expenses expenses= jsonToObj(jsonStrIn);//получение obj из строки json, и наименования покупки из obj
        String titleKey=expenses.getTitle();
        if (catMap.containsKey(titleKey)) {
            valueCat = catMap.get(titleKey); //получение наименования категории по ее ключу из словаря категорий
        } else {
            valueCat = "другое";
        }


        //countMap.merge(valueCat,1, (a,b)->a+b);//аналог setdefault в python
        //подсчет кол-ва категорий через словарь счетчиков
       if (countMap2.containsKey(valueCat)) {
          SendingItem sendingItem=countMap2.get(valueCat);
          sendingItem.getMaxCategory().addtSum(expenses.getSum());
           countMap2.put(valueCat, sendingItem);
       } else {
           countMap2.put(valueCat, new SendingItem(valueCat,expenses.getSum()));
       }

    }

    public Map<String, String> getCatMap() {
        return catMap;
    }

    public Map<String, Integer> getCountMap() {
        return countMap;
    }

    public Map<String, SendingItem> getCountMap2() {
        return countMap2;
    }
}
