package udacity.designvilla.adapter_view;

public class TemplateHolder extends udacity.designvilla.ImageUrlId {
    private String image_url;

    public TemplateHolder() {
    }

    public TemplateHolder(String image_url) {
        this.image_url = image_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
