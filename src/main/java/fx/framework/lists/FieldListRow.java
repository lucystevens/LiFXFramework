package fx.framework.lists;

import java.util.HashSet;
import java.util.Set;

import fx.framework.basic.LiFXText;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

@Deprecated
public class FieldListRow {
	
	private static final Image ADD_BUTTON = new Image(FieldListRow.class.getResourceAsStream("/images/add.png"), 15, 15, true, true);
	private static final Image REMOVE_BUTTON = new Image(FieldListRow.class.getResourceAsStream("/images/remove.png"), 15, 15, true, true);
	
	FieldList parent;
	Button button;
	TextField field;
	boolean active = true;

	public FieldListRow(FieldList parent) {
		this.parent = parent;
		button = new Button();
		button.setGraphic(new ImageView(ADD_BUTTON));
		button.setOnAction(this::addEvent);
		field = new TextField();
	}
	
	private void addEvent(ActionEvent me){
		LiFXText text = new LiFXText(field.getText());
		parent.getChildren().remove(field);
		parent.add(text, 0, getRow());
		
		FieldListRow newRow = new FieldListRow(parent);
		newRow.addToGridPane(getRow()+1);
		parent.list.add(newRow);
		
		button.setGraphic(new ImageView(REMOVE_BUTTON));
		button.setOnAction(this::removeEvent);
	}
	
	private void removeEvent(ActionEvent me){
	    Set<Node> deleteNodes = new HashSet<>();
	    int row = getRow();
	    for (Node child : parent.getChildren()) {
	        Integer rowIndex = GridPane.getRowIndex(child);
	        int r = rowIndex == null ? 0 : rowIndex;
	        if (r > row) GridPane.setRowIndex(child, r-1);
	        else if (r == row) deleteNodes.add(child);
	    }
	    parent.getChildren().removeAll(deleteNodes);
	    parent.list.remove(this);
	    active = false;
	}
	
	private int getRow(){
		return GridPane.getRowIndex(field);
	}
	
	public void addToGridPane(int row){
		parent.add(field, 0, row);
		parent.add(button, 1, row);
	}
	
	public String getValue(){
		return field.getText();
	}
	
	public boolean isActive(){
		return active;
	}

}
