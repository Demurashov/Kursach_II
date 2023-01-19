import com.google.gson.Gson;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.util.ArrayList;

public class LogicManagerStreamTest {
    private static Expenses[] arrayIn = {new Expenses("булка", "10.02.2022", 1)
            , new Expenses("колбаса", "2022.02.10", 1)
            , new Expenses("сухарики", "2022.03.10", 1)
            , new Expenses("курица", "2022.03.10", 1)
            , new Expenses("булка", "2022.02.10", 1)
            , new Expenses("тапки", "2023.02.10", 1)
            , new Expenses("шапка", "2023.02.10", 1)
            , new Expenses("мыло", "2022.03.10", 1)
            , new Expenses("акции", "2022.03.10", 1)
            , new Expenses("тапки", "2023.03.10", 1)
            , new Expenses("шапка", "2023.03.11", 1)};

    LogicManagerStream lms = new LogicManagerStream();
    Gson gsn = new Gson();

    @Test
    public void maxCategoryFinderTest() throws IOException {
        SendingItem result = null;

        for (Expenses expens : arrayIn) {
            String strjsonIn = gsn.toJson(expens);
            result = lms.maxCategoryFinder(strjsonIn);
        }
        Assertions.assertEquals("еда", result.getMaxCategory().getCategory());
        Assertions.assertEquals(5, result.getMaxCategory().getSum());
    }

    @Test
    public void maxCategoryStatFinderTest() throws IOException {
        SendingStatistics result = null;

        for (Expenses expens : arrayIn) {
            String strjsonIn = gsn.toJson(expens);
            result = lms.maxCategoryStatFinder(strjsonIn);
        }
        Assertions.assertEquals("еда", result.getMaxCategoryStat().getCategory());
        Assertions.assertEquals(5, result.getMaxCategoryStat().getSum());
        //год
        Assertions.assertEquals("одежда",result.getMaxYearCategory().getCategory());
        Assertions.assertEquals(4,result.getMaxYearCategory().getSum());
        //месяц
        Assertions.assertEquals("одежда",result.getMaxMonthCategory().getCategory());
        Assertions.assertEquals(2,result.getMaxMonthCategory().getSum());
        //день
        Assertions.assertEquals("одежда",result.getMaxDayCategory().getCategory());
        Assertions.assertEquals(1,result.getMaxDayCategory().getSum());
    }
}
