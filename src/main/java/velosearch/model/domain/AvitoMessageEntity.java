package velosearch.model.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by Viktor Aleksandrov on 19/08/16.
 */
@Entity
@Table(name = "AVITO_MESSAGE")
public class AvitoMessageEntity {

    @Id
    @Column(name = "ID")
    private long id;

    @Column(name = "TYPE_NAME")
    private String title;






    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
