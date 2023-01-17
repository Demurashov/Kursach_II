import com.google.gson.Gson;
import com.univocity.parsers.tsv.TsvParser;
import com.univocity.parsers.tsv.TsvParserSettings;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

public class Server {
    public static final int PORT = 8888;


    public static void main(String[] args) throws IOException {
        //char[]ch=HOST.toCharArray();
        //System.out.println(ch[ch.length-1]);
        //LogicManager logicManager=new LogicManager();
        Gson gsn = new Gson();
        LogicManagerStream logicManagerStream = new LogicManagerStream();
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     PrintWriter printWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                ) {

                    System.out.println("Подключен клиент:" + clientSocket.getPort());
                    String in = bufferedReader.readLine();
                    if (in != null && in.equals("Q")) {
                        System.out.println("Получена команда на отключение!");
                        break;
                    } else if (in == null) {
                        continue;
                    }
                    System.out.println("Получено сообщение от клиента: " + in);
                    SendingItem sendingItem = logicManagerStream.toCatCounter(in);
                    System.out.println("Отправлено:" + sendingItem.toString());
                    printWriter.println(gsn.toJson(sendingItem));
                }
            }
            logicManagerStream.getCountMap().forEach((a, b) -> System.out.println(a + " " + b));
        } catch (Exception exception) {
            exception.getStackTrace();
        }
    }

}
