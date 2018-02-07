package fx.framework.lists;

import com.jfoenix.controls.JFXListCell;

import fx.framework.basic.LiFXHBox;
import fx.framework.icons.Icon;
import fx.framework.icons.IconButton;
import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import fx.framework.themes.ObservableRGBColour;
import fx.framework.themes.RGBColour;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

@Deprecated
public class DeletableListCell<T> extends JFXListCell<T> {
	
	LiFXHBox hbox = new LiFXHBox(Pos.CENTER,0,0);
    Label label = new Label("");
   	Pane delete = new IconButton(Icon.TIMES, 20, new ObservableRGBColour(RGBColour.WHITE));

    public DeletableListCell() {
        super();
        Region filler = new Region();
        HBox.setHgrow(filler, Priority.ALWAYS);
        StyleBuilder.styleNode(label).replaceProperty(CSSProperty.FONT_SIZE, 16).build();
        hbox.getChildren().addAll(label, filler, delete);
        delete.setOnMouseClicked(event -> getListView().getItems().remove(getItem()));
        setPadding(new Insets(0, 0, 0, 10));
    }
    
    

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);
        setGraphic(null);

        if (item != null && !empty) {
            label.setText(item.toString());
            setGraphic(hbox);
        }
    }
}
