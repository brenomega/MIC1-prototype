package frontend;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Component {

    private final String name;
    private final Image normalImage;
    private final Image activeImage;
    private final ImageView imageView;

    public Component(String name, String normalImagePath, String activeImagePath, double x, double y) {
        this.name = name;
        this.normalImage = new Image(normalImagePath);
        this.activeImage = new Image(activeImagePath);
        this.imageView = new ImageView(this.normalImage);
        this.imageView.setX(x);
        this.imageView.setY(y);
    }

    public void activate() {
        imageView.setImage(activeImage);
    }

    public void deactivate() {
        imageView.setImage(normalImage);
    }

    public ImageView getImageView() {
        return imageView;
    }
}
