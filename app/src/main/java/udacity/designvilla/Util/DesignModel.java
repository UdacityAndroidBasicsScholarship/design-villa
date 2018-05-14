package udacity.designvilla.Util;

public class DesignModel {
    private String author,image_url,xml,title;
    private int likes;

    DesignModel(){}

    public DesignModel(String author, String image_url, String xml, String title, int likes) {
        this.author = author;
        this.image_url = image_url;
        this.xml = xml;
        this.title = title;
        this.likes = likes;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getXml() {
        return xml;
    }

    public void setXml(String xml) {
        this.xml = xml;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }
}
