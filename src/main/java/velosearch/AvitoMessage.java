package velosearch;

/**
 * Created by demo on 05/08/16.
 */
public class AvitoMessage {

    private int id;
    private int dataType;
    private String urlDetails;
    private String titlePhoto;

    private String smallImageLink;
    private String publicationDate;
    private String title;
    private AvitoCategoryEnum avitoCategoryEnum;
    private String description;
    private int price;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDataType() {
        return dataType;
    }

    public void setDataType(int dataType) {
        this.dataType = dataType;
    }

    public String getUrlDetails() {
        return urlDetails;
    }

    public void setUrlDetails(String urlDetails) {
        this.urlDetails = urlDetails;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTitlePhoto() {
        return titlePhoto;
    }

    public void setTitlePhoto(String titlePhoto) {
        this.titlePhoto = titlePhoto;
    }

    public String getSmallImageLink() {
        return smallImageLink;
    }

    public void setSmallImageLink(String smallImageLink) {
        this.smallImageLink = smallImageLink;
    }

    public AvitoCategoryEnum getAvitoCategoryEnum() {
        return avitoCategoryEnum;
    }

    public void setAvitoCategoryEnum(AvitoCategoryEnum avitoCategoryEnum) {
        this.avitoCategoryEnum = avitoCategoryEnum;
    }


    @Override
    public String toString() {
        return "AvitoMessage{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", dataType=" + dataType +
                ", urlDetails='" + urlDetails + '\'' +
                ", titlePhoto='" + titlePhoto + '\'' +
                ", smallImageLink='" + smallImageLink + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", avitoCategoryEnum=" + avitoCategoryEnum +
                ", description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}
