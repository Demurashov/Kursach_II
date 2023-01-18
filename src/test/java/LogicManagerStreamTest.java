import com.google.gson.Gson;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;

public class LogicManagerStreamTest {
    private static final SendingItem expected = new SendingItem("еда", 4);
    private static Expenses[] arrayIn = {new Expenses("булка", "02.02.2022", 1)
            , new Expenses("колбаса", "02.02.2022", 1), new Expenses("сухарики", "02.02.2022", 1)
            , new Expenses("курица", "02.02.2022", 1), new Expenses("тапки", "02.02.2022", 1)
            , new Expenses("шапка", "02.02.2022", 1), new Expenses("мыло", "02.02.2022", 1)
            , new Expenses("акции", "02.02.2022", 1)};
    private static LogicManagerStream lms=new LogicManagerStream();
    Gson gsn=new Gson();
    @Test
    public void maxCategoryFinderTest() throws IOException {
        SendingItem result=null;

        for (Expenses expens:arrayIn){
            String strjsonIn = gsn.toJson(expens);
            result=lms.maxCategoryFinder(strjsonIn);
        }
        Assertions.assertEquals(expected.getMaxCategory().getCategory(),result.getMaxCategory().getCategory());
        Assertions.assertEquals(expected.getMaxCategory().getSum(),result.getMaxCategory().getSum());
    }
}
