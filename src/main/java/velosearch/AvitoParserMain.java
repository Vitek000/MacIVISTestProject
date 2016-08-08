package velosearch;

import org.apache.commons.io.Charsets;
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
import java.nio.charset.Charset;

/**
 * Created by demo on 05/08/16.
 */
public class AvitoParserMain {


    public static void main(String[] args) throws IOException {
//        simpleSearch();
//        simpleSearch2();
//        simpleSearch3();

//        simpleSearch4();
        simpleSearch5();


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


    public static void simpleSearch4() throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("avito_page_search_example_orig.html");
        parsePage(IOUtils.toString(resourceAsStream, "utf-8"));

    }


    public static void simpleSearch5() throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("avito_page_search_example_orig.html");
        parsePage3(IOUtils.toString(resourceAsStream, "utf-8"));

    }


    private static void parsePage(String page) {

        Document doc = Jsoup.parse(page);
//        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");

        System.out.println("start parsePage!!!");
        Elements imports = doc.select("img");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        for (Element import_ : imports) {

            //System.out.println(import_);
            String newImport = import_.toString().replaceAll("src=\"//", "src=\"https://");
            newImport = newImport.replaceAll("data-srcpath=\"//", "data-srcpath=\"");
            stringBuilder.append(newImport);
            stringBuilder.append("\n");

        }

        stringBuilder.append("</html>");

        System.out.println(stringBuilder);

    }


    private static void parsePage2(String page) {

        Document doc = Jsoup.parse(page);
//        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");

        System.out.println("start parsePage!!!");
        Elements imports = doc.select("img");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        for (Element import_ : imports) {

            //System.out.println(import_);
            String newImport = import_.toString().replaceAll("src=\"//", "src=\"https://");
            newImport = newImport.replaceAll("data-srcpath=\"//", "data-srcpath=\"");
            stringBuilder.append(newImport);
            stringBuilder.append("\n");

        }

        stringBuilder.append("</html>");

        System.out.println(stringBuilder);

    }


    private static void parsePage3(String page) {

        Document doc = Jsoup.parse(page);
//        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");

        System.out.println("start parsePage!!!");
        Elements imports = doc.getElementsByClass("item");

//        System.out.println(imports.attr("id"));


        for (Element import_ : imports) {
            System.out.println("==========");
            System.out.println("id=" + import_.attr("id"));
            System.out.println("data-type=" + import_.attr("data-type"));

            Elements elementsBPhoto = import_.getElementsByClass("b-photo");
            for (Element elementBPhoto : elementsBPhoto) {
                Elements elementsPhotoWrapper = elementBPhoto.getElementsByClass("photo-wrapper");
                for (Element elementPhotoWrapper : elementsPhotoWrapper) {
                    System.out.println("href=" + elementPhotoWrapper.attr("href"));
                    System.out.println("title=" + elementPhotoWrapper.attr("title"));
                }
                Elements elementsPhotoImg = elementBPhoto.getElementsByTag("img");
                for (Element elementPhotoImg : elementsPhotoImg) {
                    System.out.println("src=" + elementPhotoImg.attr("src"));
                    System.out.println("alt=" + elementPhotoImg.attr("alt"));
                    break; // only first
                }
            }

            Elements elementsDescription = import_.getElementsByClass("description");
            for (Element elementDescription : elementsDescription) {
                Elements elementsItemDescriptionTitleLink = elementDescription.getElementsByClass("item-description-title-link");
                for (Element elementItemDescriptionTitleLink : elementsItemDescriptionTitleLink) {
                    System.out.println("href=" + elementItemDescriptionTitleLink.attr("href"));
                    System.out.println("title=" + elementItemDescriptionTitleLink.attr("title"));
                }

                Elements elementsAbout = elementDescription.getElementsByClass("about");
                for (Element elementAbout : elementsAbout) {
                    System.out.println("text=" + elementAbout.text());
                }

                Elements elementsData = elementDescription.getElementsByClass("data");
                for (Element elementData : elementsData) {
                    System.out.println("text=" + elementData.text());
                    Elements p = elementData.select("p");

                    int i = 0;
                    for (Element element : p) {
                        if(i==0) {
                            String text = element.text();
                            if(text.contains("|")) {
                                String[] arr = text.split("\\|");
                                System.out.println("category=" + arr[0]);
                                System.out.println("isCompany=" + arr[1]);
                            }
                        }
                        System.out.println("p=" + element.text());

                        i++;
                    }

                    Elements elementsClearfix = elementData.getElementsByClass("clearfix");
                    for (Element elementClearfix : elementsClearfix) {
                        Elements elementsDateC2 = elementClearfix.getElementsByClass("date");
                        for (Element elementDateC2 : elementsDateC2) {
                            System.out.println("publicationDate=" + elementDateC2.text());
                        }
                    }





                }
            }




        }

//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append("<html>");
//        for (Element import_ : imports) {
//
//            //System.out.println(import_);
//            String newImport = import_.toString().replaceAll("src=\"//", "src=\"https://");
//            newImport = newImport.replaceAll("data-srcpath=\"//", "data-srcpath=\"");
//            stringBuilder.append(newImport);
//            stringBuilder.append("\n");
//
//        }
//
//        stringBuilder.append("</html>");
//
//        System.out.println(stringBuilder);

        //System.out.println(imports);

    }




}
