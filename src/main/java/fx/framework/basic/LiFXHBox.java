package fx.framework.basic;

import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.HBox;

/**
 * HBox extension providing additional ease-of-use
 * constructors, and support for background-colour theming.
 * @author Luke Stevens
 */
public class LiFXHBox extends HBox {
	
	/**
	 * Constructs a new LiFXHBox with default values;<br>
	 * Center alignment of children within the HBox<br>
	 * 10px padding around the region's content.<br>
	 * 15px horizontal space between each child
	 */
	public LiFXHBox(){
		this(10, 15);
	}
	
	/**
	 * Constructs a new LiFXHBox with set values;<br>
	 * Default center alignment of children within the HBox
	 * @param pad The top, right, bottom, and left padding around the region's content.
	 * @param space The amount of horizontal space between each child in the hbox.
	 */
	public LiFXHBox(int pad, int space){
		setPadding(new Insets(pad));
		setSpacing(space);
		setAlignment(Pos.CENTER);
	}
	
	/**
	 * Constructs a new LiFXHBox with set values;
	 * @param pos Alignment of children within the HBox
	 * @param pad The top, right, bottom, and left padding around the region's content.
	 * @param space The amount of horizontal space between each child in the hbox.
	 */
	public LiFXHBox(Pos position, int pad, int space){
		this(pad, space);
		setAlignment(position);
	}

	/**
	 * Constructs a new LiFXHBox with a set of children to
	 * initally populate with.
	 * @param nodes A List of Nodes to be added to this HBox as children.
	 */
	public LiFXHBox(Node...nodes){
		this(10, 15);
		getChildren().addAll(nodes);
	}
	
	/**
	 * Constructs a new LiFXHBox with a set of children to
	 * initally populate with and their alignment.
	 * @param pos Alignment of children within the HBox
	 * @param nodes A List of Nodes to be added to this HBox as children.
	 */
	public LiFXHBox(Pos position, Node...nodes){
		this(position, 10, 15);
		getChildren().addAll(nodes);
	}
	
	/**
	 * Removes all children from this HBox, and replaces them.
	 * @param nodes A new list of nodes to add to this HBox.
	 */
	public void overwrite(Node...nodes){
		getChildren().clear();
		getChildren().addAll(nodes);
	}
	
	/**
	 * Binds the background colour of this hbox to a {@link BackgroundColour}
	 * specified by the theme.<br>
	 * This will update the background colour as and when the user changes the theme.
	 * @param bg Background colour as defined by the theme.
	 */
	public void bindBackground(BackgroundColour bg){
		StyleBuilder.styleNode(this).bindProperty(CSSProperty.BACKGROUND_COLOUR, bg.colour()).build();
	}


}
