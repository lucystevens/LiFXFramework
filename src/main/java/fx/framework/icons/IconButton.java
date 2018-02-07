package fx.framework.icons;

import fx.framework.launcher.FXApplication;
import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import fx.framework.themes.ObservableRGBColour;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

/**
 * A wrapper for further customising icons.
 * @see Icon
 * @author Luke Stevens
 *
 */
public class IconButton extends HBox {
	
	/**
	 * Constructs a basic IconButton with size of 24px, and using the primary background colour.
	 * @param icon The icon to display.
	 */
	public IconButton(Icon icon){
		this(icon, 24, FXApplication.THEME.getPrimaryBackgroundColour());
	}
	
	/**
	 * Constructs a basic IconButton with specified size, and using the primary background colour.
	 * @param icon The icon to display.
	 * @param size The font size of the icon in px.
	 */
	public IconButton(Icon icon, int size){
		this(icon, size, FXApplication.THEME.getPrimaryBackgroundColour());
	}
	
	/**
	 * Constructs a basic IconButton with a size of 24px, and a specified background colour.
	 * @param icon The icon to display.
	 * @param background The {@link ObservableRGBColour} of
	 * the background this icon will be used on. This ensures that
	 * the icons can change colour to contrast it if the theme is changed.
	 */
	public IconButton(Icon icon, ObservableRGBColour background){
		this(icon, 24, background);
	}
	
	/**
	 * Constructs a basic IconButton with specified size and background colour.
	 * @param icon The icon to display.
	 * @param size The font size of the icon in px.
	 * @param background The {@link ObservableRGBColour} of
	 * the background this icon will be used on. This ensures that
	 * the icons can change colour to contrast it if the theme is changed.
	 */
	public IconButton(Icon icon, int size, ObservableRGBColour background){
		// Sets padding, spacing, and alignment of the extended HBox
		setPadding(new Insets(10));
		setSpacing(0);
		setAlignment(Pos.CENTER);
		getChildren().add(icon.graphic(size, background));
		
		// Styles the background for normal use and when hovered
		StyleBuilder styler = StyleBuilder.styleNode(this);
		styler.bindProperty(CSSProperty.BACKGROUND_COLOUR, background).build();
		setOnMouseEntered(me -> styler.replaceProperty(CSSProperty.BACKGROUND_COLOUR, background.getValue().darker()).build());
		setOnMouseExited(me -> styler.replaceProperty(CSSProperty.BACKGROUND_COLOUR, background).build());
	}
	
}
