package fx.framework.inplace;

import java.lang.reflect.Field;

import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

@Deprecated
public abstract class Inplace<T extends Node> {
	
	GridPane parent;
	protected Field field;
	protected Object object;
	
	Text text = new Text();
	protected T input;
	protected boolean edited = false;
	
	EventHandler<? super MouseEvent> textEvent = this::showInput;
	
	public Inplace(GridPane parent, Field field, Object object){
		this.parent = parent;
		this.field = field;
		this.object = object;
		text.setOnMouseClicked(textEvent);
	}
	
	void showInput(MouseEvent me){
		int col = GridPane.getColumnIndex(text);
		int row = GridPane.getRowIndex(text);
		parent.getChildren().remove(text);
		parent.add(getInput(), col, row);
		edited = true;
	}
	
	protected void showText(){
		int col = GridPane.getColumnIndex(input);
		int row = GridPane.getRowIndex(input);
		parent.getChildren().remove(input);
		parent.add(getText(), col, row);
		edited = false;
	}
	
	protected String getFieldValue(){
		try {
			return String.valueOf(field.get(object));
		} catch (IllegalArgumentException | IllegalAccessException e) {
			return e.getMessage();
		}
	}
	
	// Override to 
	public abstract void save();
	public abstract T getInput();
	
	public void setInput(T input) {
		this.input = input;
	}

	public Text getText() {
		text.setText(getFieldValue());
		return text;
	}

	public void setText(Text text) {
		text.setOnMouseClicked(textEvent);
		this.text = text;
	}

}
