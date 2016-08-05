import org.apache.commons.collections.MapUtils;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by demo on 17/06/16.
 */
public class CheckAliOrdersPayments {


    public static Map<String, Double> RESULT_MAP = new LinkedHashMap<>();

    public static void main(String[] args) throws IOException {

        String pathToFiles = "/Users/demo/Desktop/AliFinishedOrders2016-06-17/";
        String filePattern = "Мой AliExpress_ Управлять заказами_%02d.htm";

        int allPagesCount = 31;

        for (int pageNumber = 1; pageNumber <= allPagesCount; pageNumber++) {
            File file = new File(pathToFiles + String.format(filePattern, pageNumber));
            String readFileToString = FileUtils.readFileToString(file, Charset.forName("utf-8"));
            Map<String, Double> stringDoubleMap = grabPaymentsFromPageHtm(pageNumber, readFileToString);
            //System.out.println(stringDoubleMap);
            RESULT_MAP.putAll(stringDoubleMap);

        }


        System.out.println("SUM IS: " + getSumFromResultMap());
        System.out.println("AVG IS: " + getAvgFromResultMap());
        System.out.println("MAX IS: " + getMaxFromResultMap());


//        int pageNumber = 31;
//
//        File file = new File(pathToFiles + String.format(filePattern, pageNumber));
//        String readFileToString = FileUtils.readFileToString(file, Charset.forName("utf-8"));
//
//        //System.out.println(readFileToString);
//
////        grabPaymentsFromPageHtm(readFileToString);

//        System.out.println("aa,fff,fff".split(",").length);

//        Arrays.toString(RESULT_MAP.toString().split(".,"));

//        String s = Arrays.toString(RESULT_MAP.entrySet().toArray());
//        System.out.println(s);


        MapUtils.debugPrint(System.out, "RESULT_MAP", RESULT_MAP);
    }

    private static double getSumFromResultMap() {

        double sum = 0;
        for (Double aDouble : RESULT_MAP.values()) {
            //System.out.println(aDouble);
            sum += aDouble;
        }


        return sum;

    }

    private static double getAvgFromResultMap() {
        double sumFromResultMap = getSumFromResultMap();
        double avg = sumFromResultMap / RESULT_MAP.values().size();
        return avg;
    }


    private static double getMaxFromResultMap() {
        double max = -1;
        for (Double aDouble : RESULT_MAP.values()) {
            //System.out.println(aDouble);
             if(aDouble > max) {
                 max = aDouble;
             }

        }

        return max;
    }


//    public static Map<String, Integer> grabPaymentsFromPageHtm(String pageStr) {
//
//        Map<String, Double> result = new LinkedHashMap<>();
//
//
//        // надо
//        int startTag = pageStr.indexOf("<p class=\"amount-num\">");
//        startTag = startTag + "<p class=\"amount-num\">".length();
//        int endTag = pageStr.indexOf("</p>", startTag);
//
//        String substring = pageStr.substring(startTag, endTag);
//        substring = substring
//                .replaceAll("&nbsp;", "")
//                .replaceAll(" руб.", "")
//                .replaceAll(",", ".")
//                .trim();
//        System.out.println(substring);
//        System.out.println(Double.parseDouble(substring));
//
//        return null;
//    }


    public static Map<String, Double> grabPaymentsFromPageHtm(int pageNumber, String pageStr) {

        Map<String, Double> result = new LinkedHashMap<>();

        int startIndex = 0;
        int counter = 0;
        while (true) {
            startIndex = pageStr.indexOf("<p class=\"amount-num\">", startIndex);
            if (startIndex == -1) {
                break;
            }

            startIndex += "<p class=\"amount-num\">".length();
            int endIndex = pageStr.indexOf("</p>", startIndex);

            String substring = pageStr.substring(startIndex, endIndex);
            substring = substring
                    .replaceAll("&nbsp;", "")
                    .replaceAll(" руб.", "")
                    .replaceAll(",", ".")
                    .trim();

            // checkOrderStatus
            // need to find
            String text = "<td class=\"order-status\" rowspan=\"1\">\n" +
                    "                                <span class=\"f-left\">\n" +
                    "                                        \tЗавершено\n" +
                    "                                    </span>";



            int nextItemIndex = pageStr.indexOf("<tbody class=\"order-item-wraper last-tbody\"", endIndex);

            String itemBody;
            if(nextItemIndex != -1) {
                itemBody = pageStr.substring(endIndex, nextItemIndex);
            }
            else {
                itemBody = pageStr.substring(endIndex);
            }


            boolean isOk = itemBody.contains(text);
            if(!isOk) {
                System.out.println("skip sum " + substring);
            }


            try {

                double parseDouble = Double.parseDouble(substring);

                counter++;

                if(isOk) {
                    result.put(pageNumber + "_" + counter, parseDouble);
                }


            } catch (NumberFormatException e) {
                System.err.println("error parse:" + substring);
            }

            //System.out.println(result);
        }

        return result;
    }


}
