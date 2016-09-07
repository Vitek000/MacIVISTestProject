package fignya;

/**
 * Created by Viktor Aleksandrov on 30/08/16.
 */
class Test20160905 {

    public static void main(String[] args) {
        String categoryId = "1";
        String dateFrom = "01-01-1970";
        String dateTill = "01-01-2018";

        String sqlQuery =
                "select esi.ew_ref_ppv_product_id prodnum, shorttitle, b.author_raw, tld.title, " +
                        "p.peindex, p.publisher, p.country, p.city, price.price, " +
                        "TO_CHAR(i.office_date_entered, 'DD-MM-YYYY') as DATE_LOADED, TO_CHAR(p.datebegin, 'YYYY') " +
                        "as YEAR_PUB, concat('http://dlib.eastview.com/browse/book/', e.editionid) link, " +
                        "CASE WHEN bs.id is not null then concat('http://dlib.eastview.com/browse/books/" + categoryId + "?set=', bs.ID) else null END series_url " +
                        "from periodicedition_ru p, TITLE_LANG_DATA tld, book_en b, editioncategory e, issue i, edition_sys_info esi, edition_price price, BOOK_SERIE bs " +
                        "where bs.ID (+) = b.SERIE_ID and tld.EDITIONID (+) = p.EDITIONID and tld.LANG_ID (+) = p.LANG_ID and " +
                        "i.editionid(+) = p.editionid and esi.edition_id(+) = p.editionid and b.id(+) = p.editionid and " +
                        "p.editionid = e.editionid and e.editionid = price.editionid(+) and e.categoryid = " + categoryId +
                        " and issueid is not null  and " +
                        "i.office_date_entered > TO_DATE('" + dateFrom + "','DD-MM-YYYY') and i.office_date_entered < TO_DATE('" + dateTill + "','DD-MM-YYYY') " +
                        " order by prodnum";
        System.out.println(sqlQuery);
    }

}
