package fx.framework.lists;

import java.util.function.Consumer;

import com.jfoenix.controls.JFXListView;

import fx.framework.style.TempStyle;
import javafx.scene.Node;

/**
 * A ListView implmentation that allows easy configuration 
 * of removable and draggable cells
 * 
 * @author Luke Stevens
 *
 * @param <T>
 */
public class LiFXListView<T> extends JFXListView<T> {
	
	public static TempStyle DEFAULT_STYLE;
	
	private int cellTrimLength = -1;
	
	/*
	 * Initialises the default dragstyle to set the colour
	 * and opacity when dragged over, and to revert it when not.
	 */
	static {
		Consumer<Node> add = node -> {
			node.setOpacity(0.3);
            node.setStyle("-fx-background-color: #99dcf7");
		};
		
		Consumer<Node> remove = node -> {
			node.setOpacity(1);
            node.setStyle(null);
		};
		
		DEFAULT_STYLE = new TempStyle(add, remove);
	}
	
	/**
	 * Constructs a new LiFXListView and sets
	 * the cell factory to use {@link LiFXCell}
	 * @param removable Whether the elements of this listview should be removeable
	 * @param dragStyle The style to use when cells are dragged over this cell. If this
	 * is null then cells will not be draggable.
	 */
    public LiFXListView(boolean removable, TempStyle dragStyle) {
        super();
		setCellFactory(param -> new LiFXCell<T>(removable, dragStyle));
    }
    
    /**
     * Constructs a new LiFXListView using the default dragstyle.<br>
     * See {@link LiFXListView#DEFAULT_STYLE}
     * @param removable Whether the elements of this listview should be removeable
     */
    public LiFXListView(boolean removable) {
        this(removable, DEFAULT_STYLE);
    }
    
    /**
     * Constructs a LiFXListView with removeable cells 
	 * @param dragStyle The style to use when cells are dragged over this cell. If this
	 * is null then cells will not be draggable.
     */
    public LiFXListView(TempStyle dragStyle) {
        this(true, dragStyle);
    }
    
    /**
     * Constructs a new LiFXListView with removeable cells using the
     * default dragstyle.<br> See {@link LiFXListView#DEFAULT_STYLE}
     */
    public LiFXListView() {
        this(true, DEFAULT_STYLE);
    }

    /**
     * Gets the number of characters after which the
     * cell text should be trimmed, defaults to -1 (no trim).
     * @return The cell trim length
     */
	public int getCellTrimLength() {
		return cellTrimLength;
	}

	/**
	 * Sets the number of characters after which the
     * cell text should be trimmed, if no trim is required
     * then set to -1.
	 * @param cellTrimLength The cell trim length
	 */
	public void setCellTrimLength(int cellTrimLength) {
		this.cellTrimLength = cellTrimLength;
	}  
    
}
