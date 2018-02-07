package fx.framework.basic;

import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

/**
 * Gridpane extension providing additional ease-of-use
 * constructors, and support for background-colour theming.
 * @author Luke Stevens
 */
public class LiFXGridPane extends GridPane {
	
	private static final Pos DEFAULT_ALIGNMENT = Pos.CENTER;

	/**
	 * Constructs a new LiFXGridPane with default values;<br>
	 * Center alignment of children within the GridPane<br>
	 * Horizontal and vertical gap between rows and columns of 10px.<br>
	 * 25px padding around the region's content.
	 */
	public LiFXGridPane(){
		this(10, 25);
	}

	/**
	 * Constructs a new LiFXGridPane with set values;<br>
	 * Default center alignment of children within the GridPane
	 * @param gap Horizontal and vertical gap between columns and rows
	 * @param padding The top, right, bottom, and left padding around the region's content.
	 */
	public LiFXGridPane(int gap, int padding){
		this(DEFAULT_ALIGNMENT, gap, padding);
	}
	
	/**
	 * Constructs a new LiFXGridPane with set values;
	 * @param pos Alignment of children within the GridPane
	 * @param gap Horizontal and vertical gap between columns and rows
	 * @param padding The top, right, bottom, and left padding around the region's content.
	 */
	public LiFXGridPane(Pos pos, int gap, int padding){
		this(pos, gap, gap, new Insets(padding));
	}
	
	/**
	 * Constructs a new LiFXGridPane with set values;<br>
	 * Default center alignment of children within the GridPane
	 * @param hGap Horizontal gap between columns.
	 * @param vGap Vertical gap between rows.
	 * @param insets The top, right, bottom, and left padding around the region's content.
	 */
	public LiFXGridPane(int hGap, int vGap, Insets insets){
		this(DEFAULT_ALIGNMENT, hGap, vGap, insets);
	}
	
	/**
	 * Constructs a new LiFXGridPane with set values;
	 * @param pos Alignment of children within the GridPane
	 * @param hGap Horizontal gap between columns.
	 * @param vGap Vertical gap between rows.
	 * @param insets Padding between each child.
	 */
	public LiFXGridPane(Pos pos, int hGap, int vGap, Insets insets){
		setAlignment(pos);
		setHgap(hGap);
		setVgap(vGap);
		setPadding(insets);
	}
	
	/**
	 * Binds the background colour of this gridpane to a {@link BackgroundColour}
	 * specified by the theme.<br>
	 * This will update the background colour as and when the user changes the theme.
	 * @param bg Background colour as defined by the theme.
	 */
	public void bindBackground(BackgroundColour bg){
		StyleBuilder.styleNode(this).bindProperty(CSSProperty.BACKGROUND_COLOUR, bg.colour()).build();
	}

}
