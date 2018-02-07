package fx.framework.lists;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXListCell;

import fx.framework.basic.LiFXHBox;
import fx.framework.filler.FillType;
import fx.framework.filler.FillerRegion;
import fx.framework.icons.Icon;
import fx.framework.icons.IconButton;
import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import fx.framework.style.TempStyle;
import fx.framework.themes.ObservableRGBColour;
import fx.framework.themes.RGBColour;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * A ListCell implementation that allows
 * items in the list to be removed and re-ordered.<br>
 * Inherits the google material design style from JFXListCell.
 * @author Luke Stevens
 *
 * @param <T> The object to be held in each cell
 */
public class LiFXCell<T> extends JFXListCell<T> {

	protected LiFXHBox hbox = new LiFXHBox(Pos.CENTER,0,0);
	protected Label label = new Label("");
	protected Pane delete = new IconButton(Icon.TIMES, 20, new ObservableRGBColour(RGBColour.WHITE));
	protected TempStyle dragStyle;

   	/**
   	 * Creates a new Cell, should only be called by LiFXListView
   	 * @param removable Sets whether items are removable
   	 * @param dragStyle Defines the style to be applied when items are dragged.
   	 * If null, then items will not be draggable.
   	 */
    protected LiFXCell(boolean removable, TempStyle dragStyle) {
        super();
        
        // Sets the style properties of the label and icon.
        StyleBuilder.styleNode(label).replaceProperty(CSSProperty.FONT_SIZE, 16).build();
        StyleBuilder.styleNode(delete).unbindProperty(CSSProperty.BACKGROUND_COLOUR).build();
        StyleBuilder.styleNode(delete.getChildren().get(0)).unbindProperty(CSSProperty.TEXT_FILL).build();
        
        hbox.getChildren().addAll(label, new FillerRegion(FillType.HBOX));
        setPadding(new Insets(0, 0, 0, 10));
        
        // Calls methods to apply settings for removing and dragging
        if(removable) removable();
        if(dragStyle!=null) draggable(dragStyle);
    }
    
    /*
     * Only called if items can be removed. Adds a remove 'x'
     * to the cell.
     */
    protected void removable(){
    	hbox.getChildren().add(delete);
        delete.setOnMouseClicked(event -> getListView().getItems().remove(getItem()));
    }
    
    /*
     * Only called if items can be dragged. Sets listeners
     * for all drag events.
     */
    protected void draggable(TempStyle dragStyle){
        cellRippler.ripplerDisabledProperty().set(true);
        this.dragStyle = dragStyle;

        // Listeners
        setOnDragDetected(this::onDragDetected);
        setOnDragOver(this::onDragOver);
        setOnDragEntered(this::onDragEntered);
        setOnDragExited(this::onDragExited);
        setOnDragDropped(this::onDragDropped);
        setOnDragDone(DragEvent::consume);
    }
    
    // ----------- DRAG EVENT LISTENERS ------------------
    
    /*
     * Copies the currently selected item to the dragboard
     * and consumes the event.
     */
    private void onDragDetected(MouseEvent event){
    	T item = getItem();
    	if(item!=null){
    		Dragboard dragboard = startDragAndDrop(TransferMode.MOVE);
    		ClipboardContent content = new ClipboardContent();
    		content.putString(item.toString());
    		dragboard.setContent(content);
    		event.consume();
    	}
    }
    
    private void onDragOver(DragEvent event){
    	if (toChange(event)) event.acceptTransferModes(TransferMode.MOVE);
        event.consume();
    }
    
    private void onDragEntered(DragEvent event){
        if (toChange(event)) dragStyle.addStyle(this);
    }
    
    private void onDragExited(DragEvent event){
        if (toChange(event)) dragStyle.removeStyle(this);
    }
    
    private void onDragDropped(DragEvent event){
    	T item = getItem();
    	if(item==null) return;

        Dragboard db = event.getDragboard();
        boolean success = false;

        if (db.hasString()) {
            ObservableList<T> items = getListView().getItems();
            T draggedItem = getItem(db.getString());
            int draggedIdx = items.indexOf(draggedItem);
            int thisIdx = items.indexOf(item);

            items.set(draggedIdx, item);
            items.set(thisIdx, draggedItem);

            List<T> itemscopy = new ArrayList<>(getListView().getItems());
            getListView().getItems().setAll(itemscopy);

            success = true;
        }
        event.setDropCompleted(success);
        event.consume();
    }
    
    // Gets the item from it's String representation
    private T getItem(String value){
    	for(T item : getListView().getItems()){
    		if(item.toString().equals(value)) return item;
    	}
    	return null;
    }
    
    // Checks whether the cell style should be changed
    private boolean toChange(DragEvent event){
    	return event.getGestureSource() != this && event.getDragboard().hasString();
    }
    
    /**
     * Styles the node according to the item passed to it
     * @param item The item in this cell
     * @return The node representing the cell
     */
    protected HBox getNode(T item){
    	String name = item.toString();
    	int cellLength = getCellTrimLength();
    	if(cellLength > 0 && cellLength < name.length()){
    		name = name.substring(0, cellLength) + "...";
    	}
        label.setText(name);
    	return hbox;
    }
    
    /**
     * @return If parent ListView is a {@link LiFXListView} then
     * then this returns the number of characters after which the
     * cell text should be trimmed, else will return -1;
     */
	protected int getCellTrimLength() {
		ListView<T> listview = getListView();
		if(listview instanceof LiFXListView){
			LiFXListView<T> lifxListView = (LiFXListView<T>) listview;
			return lifxListView.getCellTrimLength();
		}
		else return -1;
	}

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        if (item != null && !empty) {
            setGraphic(getNode(item));
        }
    }
}
