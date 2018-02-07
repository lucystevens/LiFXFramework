package fx.framework.lists;

import java.util.ArrayList;
import java.util.List;

import com.jfoenix.controls.JFXListCell;

import fx.framework.style.TempStyle;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
@Deprecated
public class DraggableListCell<T> extends JFXListCell<T> {
	
	private TempStyle dragStyle;

    public DraggableListCell(TempStyle dragStyle) {
        setContentDisplay(ContentDisplay.TEXT_ONLY);
        setAlignment(Pos.CENTER);
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
    
    private T getItem(String value){
    	for(T item : getListView().getItems()){
    		if(item.toString().equals(value)) return item;
    	}
    	return null;
    }
    
    private boolean toChange(DragEvent event){
    	return event.getGestureSource() != this && event.getDragboard().hasString();
    }
}
