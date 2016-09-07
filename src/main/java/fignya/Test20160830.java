package fignya;

/**
 * Created by Viktor Aleksandrov on 30/08/16.
 */
public class Test20160830 {

    public static void main(String[] args) {
        String s = 			"SELECT rec.id, " +
                "	get_record_title (rec.id) as title, " +
                "	get_record_sub_title (rec.id) as sub_title,  " +
                "	get_record_parallel_title (rec.id) as parallel_title," +
                "	get_record_authors (rec.id) as authors, " +
                "	get_record_responsibility (rec.id) as responsibility," +
                "	get_record_ext_responsibility (rec.id) as ext_responsibility," +
                "	get_record_subject_terms (rec.id) as subject_terms," +
                "	get_record_udk_code (rec.id) AS udc_code," +
                "	get_record_bbk_code (rec.id) as bbk_code," +
                "	get_record_eskl_code (rec.id)  as eskl_code," +
                "	get_record_rugasnti_code (rec.id) as rugasnti_code ," +
                "	get_record_sn_number (rec.id) as sn_number," +

                "	get_computed_record_type_id (rec.id) as record_type," + // don't know how
                "	get_record_letopis_id(rec.id)  as letopis_id," + // udb.edition_id
                "	get_record_letopis_date(rec.id) as letopis_date," + // never used?
                "	get_record_letopis_issue (rec.id) as letopis_issue," + // never used?

                "	GET_RECORD_SOURCE_TITLE (rec.id) as source_title," +
                "	get_record_source_year_m (rec.id) as source_year," +
                "	GET_RECORD_SOURCE_DATE_M(rec.id) as source_date," +

                "	get_record_source_issue_m(rec.id) as source_issue," +
                "	get_record_source_pages (rec.id) as source_pages," +

                "	get_record_publisher (rec.id) as publisher," + // ?
                "	get_record_publish_place (rec.id) as publish_place," +
                "	get_record_publish_date (rec.id) as publish_date," +

                "	get_record_geo_places (rec.id) as geo_place," +

                "	get_record_reviewed_material(rec.id) as reviewed_material," +

                "	get_record_udc_ids (rec.id) as udc_id," +
                "	get_record_quantity_info(rec.id) as quantity_info" +
                " FROM record rec";
        System.out.println(s);
    }

}
