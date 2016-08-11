package velosearch;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Vitek000 on 11.08.2016.
 */
public class AvitoDateParserTest {

    @Test
    public void getTodayDate() {
        String what = "Сегодня 4:52";

        Date resultDate = AvitoSearchHelper.parseAvitoDate(what);

        Calendar cal = GregorianCalendar.getInstance();
        // cal.set(Calendar.HOUR, 0);   // As jarnbjo pointed out this isn't enough
        cal.set(Calendar.HOUR_OF_DAY, 4);
        cal.set(Calendar.MINUTE, 52);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Assert.assertEquals("", cal.getTime(), resultDate);
    }

    @Test
    public void getYesterdayDate() {
        String what = "Вчера 4:52";

        Date resultDate = AvitoSearchHelper.parseAvitoDate(what);

        Calendar cal = GregorianCalendar.getInstance();
        // cal.set(Calendar.HOUR, 0);   // As jarnbjo pointed out this isn't enough
        cal.add(Calendar.DAY_OF_MONTH, -1);
        cal.set(Calendar.HOUR_OF_DAY, 4);
        cal.set(Calendar.MINUTE, 52);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Assert.assertEquals("", cal.getTime(), resultDate);
    }


    @Test
    public void getWithMonthDate() {
        String what = "6 апреля 14:19";

        Date resultDate = AvitoSearchHelper.parseAvitoDate(what);

        Calendar cal = GregorianCalendar.getInstance();
        // cal.set(Calendar.HOUR, 0);   // As jarnbjo pointed out this isn't enough
        cal.set(Calendar.DAY_OF_MONTH, 6);
        cal.set(Calendar.MONTH, Calendar.APRIL);
        cal.set(Calendar.HOUR_OF_DAY, 14);
        cal.set(Calendar.MINUTE, 19);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);

        Assert.assertEquals("", cal.getTime(), resultDate);
    }



}
