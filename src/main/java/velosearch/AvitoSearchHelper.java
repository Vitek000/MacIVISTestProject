package velosearch;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by demo on 08/08/16.
 */
public class AvitoSearchHelper {

    public static final List<String> MONTHES_RU =
            Arrays.asList(
                    "января",
                    "февраля",
                    "марта",
                    "апреля",
                    "мая",
                    "июня",
                    "июля",
                    "августа",
                    "сентября",
                    "октября",
                    "ноября",
                    "декабря");


    public static Date parseAvitoDate(String strDate) {

        Calendar calendar = Calendar.getInstance();
        String startWord;


        if (strDate.startsWith("Сегодня") || strDate.startsWith("Вчера")) {
            if (strDate.startsWith("Сегодня")) {
                startWord = "Сегодня";
            } else if (strDate.startsWith("Вчера")) {
                startWord = "Вчера";
                calendar.add(Calendar.DAY_OF_MONTH, -1);
            } else {
                startWord = "";
            }

            String substringHour = strDate.substring(startWord.length(), strDate.indexOf(":"));
            int hour = Integer.parseInt(substringHour.trim());
            String substringMinute = strDate.substring(strDate.indexOf(":") + 1);
            int minute = Integer.parseInt(substringMinute);

            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);
        }
        else {
            String[] tokens = strDate.split(" ");
            int dayOfMonth = Integer.parseInt(tokens[0]);
            int month = MONTHES_RU.indexOf(tokens[1]);
            String substringHour = tokens[2].substring(0, tokens[2].indexOf(":"));
            int hour = Integer.parseInt(substringHour.trim());
            String substringMinute = tokens[2].substring(tokens[2].indexOf(":") + 1);
            int minute = Integer.parseInt(substringMinute);

            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            calendar.set(Calendar.HOUR_OF_DAY, hour);
            calendar.set(Calendar.MINUTE, minute);

        }

        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        return calendar.getTime();


    }
}
