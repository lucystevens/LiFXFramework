package fx.framework.factories;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Convenience class for loading images into the application.
 * @author Luke Stevens
 */
public class ImageFactory {
	
	// TODO class not finished
	//private static Image IMAGE_NOT_FOUND = new Image(ImageFactory.class.getResourceAsStream("/images/not-found.png"));

	/**
	 * Creates a new ImageView with an image from the classpath
	 * @param filename The filename for an image stored in the <i>images</i>
	 * resource folder.
	 * @return An imageview holding the image.
	 */
	public static ImageView create(String filename){
		Image img = new Image(ImageFactory.class.getResourceAsStream("/images/" + filename));
		return new ImageView(img);
	}
	
	/**
	 * Creates a new ImageView with an image from the classpath with a defined size
	 * @param filename The filename for an image stored in the <i>images</i>
	 * resource folder.
	 * @param size The height and width of the image
	 * @return An imageview holding the image with equal height and width.
	 */
	public static ImageView create(String filename, double size){
		return create(filename, size, size);
	}
	
	public static ImageView create(String filename, double height, double width){
		ImageView iv = create(filename);
		iv.setFitHeight(height);
		iv.setFitWidth(width);
		iv.setSmooth(true);
		return iv;
	}

}
