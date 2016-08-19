package velosearch;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by demo on 05/08/16.
 */
public class AvitoParserMain {


    public static void main(String[] args) throws IOException {
//        simpleSearch();
//        simpleSearch2();
//        simpleSearch3();

//        simpleSearch4();
//        simpleSearchWithTestFile1();
        simpleSearchFromAvitoSite1();


    }


    public static void log(String msg) {
        // System.out.println(msg);
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
                log(line);
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

            log(page);

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

            //log(page);

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


    public static void simpleSearchWithTestFile1() throws IOException {
        InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("avito_page_search_example_orig.html");
        parsePage3(IOUtils.toString(resourceAsStream, "utf-8"));

    }
    public static void simpleSearchFromAvitoSite1() throws IOException {
        //InputStream resourceAsStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("avito_page_search_example_orig.html");

        URL url;
        InputStream is = null;

        try {
            String searchWord = "Велосипед";
            url = new URL("https://www.avito.ru/moskva?q=" + searchWord);
            is = url.openStream();  // throws an IOException
            //String page = IOUtils.toString(is, "utf-8");

            //log(page);

            parsePage3(IOUtils.toString(is, "utf-8"));


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

        log("start parsePage!!!");
        Elements imports = doc.select("img");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        for (Element import_ : imports) {

            //log(import_);
            String newImport = import_.toString().replaceAll("src=\"//", "src=\"https://");
            newImport = newImport.replaceAll("data-srcpath=\"//", "data-srcpath=\"");
            stringBuilder.append(newImport);
            stringBuilder.append("\n");

        }

        stringBuilder.append("</html>");

        log(stringBuilder.toString());

    }


    private static void parsePage2(String page) {

        Document doc = Jsoup.parse(page);
//        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");

        log("start parsePage!!!");
        Elements imports = doc.select("img");

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("<html>");
        for (Element import_ : imports) {

            //log(import_);
            String newImport = import_.toString().replaceAll("src=\"//", "src=\"https://");
            newImport = newImport.replaceAll("data-srcpath=\"//", "data-srcpath=\"");
            stringBuilder.append(newImport);
            stringBuilder.append("\n");

        }

        stringBuilder.append("</html>");

        log(stringBuilder.toString());

    }


    private static void parsePage3(String page) {

        Document doc = Jsoup.parse(page);
//        Elements links = doc.select("a[href]");
//        Elements media = doc.select("[src]");
//        Elements imports = doc.select("link[href]");

        log("start parsePage!!!");
        Elements imports = doc.getElementsByClass("item");

//        log(imports.attr("id"));

        List<AvitoMessage> messages = new ArrayList<>();

        for (Element import_ : imports) {

            AvitoMessage avitoMessage = new AvitoMessage();

            log("==========");
            String idStr = import_.attr("id");
            Integer id = Integer.parseInt(idStr.replace("i", ""));
            avitoMessage.setId(id);
            log("id=" + id);
            String dataTypeStr = import_.attr("data-type");
            int dataType = Integer.parseInt(dataTypeStr);
            avitoMessage.setDataType(dataType);
            log("data-type=" + dataTypeStr);

            Elements elementsBPhoto = import_.getElementsByClass("b-photo");
            for (Element elementBPhoto : elementsBPhoto) {
                Elements elementsPhotoWrapper = elementBPhoto.getElementsByClass("photo-wrapper");
                for (Element elementPhotoWrapper : elementsPhotoWrapper) {
                    String urlDetails = elementPhotoWrapper.attr("href");
                    avitoMessage.setUrlDetails(urlDetails);
                    log("href=" + urlDetails);
                    String titlePhoto = elementPhotoWrapper.attr("title");
                    avitoMessage.setTitlePhoto(titlePhoto);
                    log("title=" + titlePhoto);
                }
                Elements elementsPhotoImg = elementBPhoto.getElementsByTag("img");
                for (Element elementPhotoImg : elementsPhotoImg) {
                    String smallImageLink = elementPhotoImg.attr("src");
                    avitoMessage.setSmallImageLink(smallImageLink);
                    log("src=" + smallImageLink);
                    String title = elementPhotoImg.attr("alt");
                    avitoMessage.setTitle(title);
                    log("alt=" + title);
                    break; // only first
                }
            }

            Elements elementsDescription = import_.getElementsByClass("description");
            for (Element elementDescription : elementsDescription) {
                Elements elementsItemDescriptionTitleLink = elementDescription.getElementsByClass("item-description-title-link");
                for (Element elementItemDescriptionTitleLink : elementsItemDescriptionTitleLink) {
                    log("href2=" + elementItemDescriptionTitleLink.attr("href"));
                    log("title2=" + elementItemDescriptionTitleLink.attr("title"));
                }

                Elements elementsAbout = elementDescription.getElementsByClass("about");
                for (Element elementAbout : elementsAbout) {
                    String priceStr = elementAbout.text();
                    int price = -1;
                    try {
//                        price = Integer.parseInt(priceStr.replace(" руб.", "").replaceAll(" ", "").trim());

                        // replace all non digit
                        price = Integer.parseInt(priceStr.replaceAll("[^\\d]", ""));

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.exit(-1);
                    }
                    avitoMessage.setPrice(price);
                    log("priceStr=" + priceStr);
                    log("price=" + price);
                }

                Elements elementsData = elementDescription.getElementsByClass("data");
                for (Element elementData : elementsData) {
                    log("text=" + elementData.text());
                    Elements p = elementData.select("p");

                    int i = 0;
                    for (Element element : p) {
                        if (i == 0) {
                            String text = element.text();
                            String category = text;
                            if (text.contains("|")) {
                                String[] arr = text.split("\\|");
                                category = arr[0].trim();

                                log("isCompany=" + arr[1]);

                            }
                            avitoMessage.setAvitoCategoryEnum(AvitoCategoryEnum.findByName(category));
                            log("category=" + category);

                        }
                        log("p=" + element.text());

                        i++;
                    }

                    Elements elementsClearfix = elementData.getElementsByClass("clearfix");
                    for (Element elementClearfix : elementsClearfix) {
                        Elements elementsDateC2 = elementClearfix.getElementsByClass("date");
                        for (Element elementDateC2 : elementsDateC2) {
                            log("publicationDate=" + elementDateC2.text());
                            log("publicationDateDate=" + AvitoSearchHelper.parseAvitoDate(
                                    elementDateC2.text()));
                        }
                    }
                }
            }

            messages.add(avitoMessage);
        }


        //System.out.println(messages);
        for (AvitoMessage message : messages) {
            //System.out.println(message);
        }

        List<AvitoMessage> filteredMessages = filterMessages(messages);

    }

    private static List<AvitoMessage> filterMessages(List<AvitoMessage> messages) {
        List<AvitoMessage> result = new ArrayList<>();

        for (AvitoMessage message : messages) {

            if (filterByCategory(message.getAvitoCategoryEnum()) &&
                    filterByPrice(message.getPrice())
                    &&
                    filterByTitle(message.getTitle())
                    ) {
                result.add(message);
            }
        }

        for (AvitoMessage avitoMessage : result) {
            System.out.println(avitoMessage);
        }
        return result;
    }

    private static boolean filterByCategory(AvitoCategoryEnum avitoCategoryEnum) {
        return true;
    }

    private static boolean filterByTitle(String title) {

        Set<String> partlyGoodTitles = new HashSet<>();
        partlyGoodTitles.add("Merida");
        partlyGoodTitles.add("Мерида");

        for (String partlyGoodTitle : partlyGoodTitles) {
            if (StringUtils.containsIgnoreCase(title, partlyGoodTitle)) {
                return true;
            }
        }


        Set<String> partlyBadTitles = new HashSet<>();
        partlyBadTitles.add("BMW");
        partlyBadTitles.add("Детский велосипед");
        partlyBadTitles.add("Stels");
        partlyBadTitles.add("Minerva");
        partlyBadTitles.add("Велосипед для девочки");
        partlyBadTitles.add("Велосипеды Aist");
        partlyBadTitles.add("Велобагажник");
        partlyBadTitles.add("Велокрепление");
        partlyBadTitles.add("Бутылка");
        partlyBadTitles.add("Лонгборд");
        partlyBadTitles.add("Настенный кронштейн");
        partlyBadTitles.add("Энциклопедия");
        partlyBadTitles.add("Светодиодные фонарики");
        partlyBadTitles.add("Стелс Навигатор");
        partlyBadTitles.add("на литых дисках");
        partlyBadTitles.add("Личинки Thule");
        partlyBadTitles.add("Велосипед деревянный");

        for (String partlyBadTitle : partlyBadTitles) {
            if (StringUtils.containsIgnoreCase(title, partlyBadTitle)) {
                return false;
            }
        }

        return true;
    }

    private static boolean filterByPrice(int price) {
        boolean ok = price < 50000;
        return ok;
    }


    /*

-- Подумать об использовании базы DB2
http://www.h2database.com/html/main.html

     */


}
