package fignya;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Created by Viktor Aleksandrov on 06/09/16.
 */
public class Test20160906_2 {


    public static void main(String[] args) throws IOException {
//        down vote
//        accepted
//        It seems the latest version of Jsoup (1.6.2 - released March 28) includes some basic support for XML.

        byte[] encoded = Files.readAllBytes(Paths.get("/Users/demo/Downloads/READY/34KL2016.xml"));
        String html = new String(encoded, "utf-8");

//                String html = "<?xml version=\"1.0\" encoding=\"UTF-8\"><tests><test><id>xxx</id><status>xxx</status></test><test><id>xxx</id><status>xxx</status></test></tests></xml>";
        Document doc = Jsoup.parse(html, "", Parser.xmlParser());
        for (Element e : doc.select("record")) {

            Elements elementsTag20 = e.select("datafield");
            boolean foundTag20 = false;
            for (Element elementTag20 : elementsTag20) {


                String tag = elementTag20.attr("tag");

                if(tag.equals("020")) {
                    foundTag20 = true;
                    break;
                }

                //System.out.println(tag);
            }
            if (!foundTag20) {
                System.out.println(e);
            }

        }
    }
}
