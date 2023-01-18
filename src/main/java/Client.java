import com.google.gson.Gson;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.*;

public class Client {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8888;
    private static Collection<Expenses> expensesList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        Gson gsn = new Gson();
        expensesGena();
        int i = 1;
        for (Expenses item : expensesList) {
            try (Socket socket = new Socket(HOST, PORT);
                 PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                 BufferedReader bufferedReader = new BufferedReader((new InputStreamReader(socket.getInputStream())))) {
                i++;
                if (i < 99) {
                    if (gsn.toJson(item) != null) {
                        printWriter.println(gsn.toJson(item));
                    }
                } else {
                    printWriter.println("Q");
                    break;
                }
                String strIn = bufferedReader.readLine();
                SendingItem sendingItem = gsn.fromJson(strIn, SendingItem.class);
                System.out.println("Получено сообщение от сервера: " + i + " " + strIn);
                System.out.println("Дешифровка:" + sendingItem.toString());


            } catch (Exception exception) {
                exception.getStackTrace();
            }

        }


    }

    public static void expensesGena() {
        String[] titles = {"булка", "колбаса", "сухарики", "курица", "тапки", "шапка", "мыло", "акции", "кирпич"};
        String[] dates = {"2022.02.08", "2022.01.01", "2022.01.02", "2022.01.03", "2022.01.04",
                "2022.01.05", "2022.02.06", "2022.02.07", "2022.01.08"};
        Random random = new Random();
        for (int i = 0; i < 100; i++) {
            int a = random.nextInt(titles.length);
            int b = random.nextInt(dates.length);
            int c = random.nextInt(100);
            expensesList.add(new Expenses(titles[a], dates[b], c));
        }
        //expensesList.forEach(a -> System.out.println(a.getTitle() + " " + a.getDate() + " " + a.getSum()));


    }
}



