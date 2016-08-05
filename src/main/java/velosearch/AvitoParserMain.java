package velosearch;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * Created by demo on 05/08/16.
 */
public class AvitoParserMain {


    public static void main(String[] args) {
//        simpleSearch2();
        simpleSearch3();




    }


    public static void simpleSearch() {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            String searchWord = "Велосипед";
            url = new URL("https://www.avito.ru/moskva?q=" + searchWord);
            is = url.openStream();  // throws an IOException
            br = new BufferedReader(new InputStreamReader(is));

            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }



    public static void simpleSearch2() {
        URL url;
        InputStream is = null;
        BufferedReader br;
        String line;

        try {
            String searchWord = "Велосипед";
            url = new URL("https://www.avito.ru/moskva?q=" + searchWord);
            is = url.openStream();  // throws an IOException
            String page = IOUtils.toString(is, "utf-8");

            System.out.println(page);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }




    public static void simpleSearch3() {
        URL url;
        InputStream is = null;

        try {
            String searchWord = "Велосипед";
            url = new URL("https://www.avito.ru/moskva?q=" + searchWord);
            is = url.openStream();  // throws an IOException
            String page = IOUtils.toString(is, "utf-8");

            //System.out.println(page);

            parsePage(page);


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) is.close();
            } catch (IOException ioe) {
                // nothing to see here
            }
        }
    }

    private static void parsePage(String page) {

        Document doc = Jsoup.parse(page);
//        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");
        Elements imports = doc.select("div[class=data]");

        for (Element import_ : imports) {
            System.out.println("======");
            System.out.println(import_);
            System.out.println("======");
        }




    }

}
