package training.eshop.utilities;

import java.awt.*;

public class EshopLogo {
    public EshopLogo(String image_url, String description) {
        this.image_url = image_url;
        this.description = description;
    }

    String image_url;
    Color logo_color;
    String description;

    public String getImageUrl(){
        return image_url;
    }

    public String getImageDescrption(){
        return description;
    }
}
