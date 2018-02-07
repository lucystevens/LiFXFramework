package fx.framework.menu;

import java.util.Arrays;

import fx.framework.basic.LiFXText;
import fx.framework.launcher.FXApplication;
import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import fx.framework.themes.Theme;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Option Menu for having a full-height vertical
 * menu with full-width buttons.<br>
 * 
 * Made up of OptionNode objects
 * 
 * @see OptionNode
 * @author Luke Stevens
 *
 */
public class OptionMenu extends VBox {
	
	private OptionNode selected;
	private OptionSelectEvent onSelect;
	
	/**
	 * @return A blank OptionMenu, which can be used as a spacer
	 * between OptionNodes
	 */
	public static Pane getSpacer(){
		return new OptionMenu("");
	}
	
	/**
	 * Constructs an OptionMenu with header text and a variable number of OptionNodes
	 * @param header The header to be displayed at the top of the menu.
	 * @param nodes Menu items to populate the menu with
	 */
	public OptionMenu(String header, OptionNode...nodes){
		setId("option-pane");
		setPrefWidth(180);
		style(FXApplication.THEME);
	    getChildren().add(new LiFXText(header, this));
	    Arrays.asList(nodes).forEach(this::addNode);
	}
	
	/**
	 * Adds a node, selecting it if there isn't one currently selected,
	 * and setting this menu as it's parent.
	 * @param option OptionNode to add to this menu
	 */
	public void addNode(OptionNode option){
		if(selected==null) setSelected(option);
    	getChildren().add(option);
    	option.setParent(this);
	}
	
	/**
	 * Sets a new option node as selected, deselecting the old one if necessary
	 * and firing the {@link OptionSelectEvent} if set.
	 * @param selected The OptionNode to select.
	 */
	void setSelected(OptionNode selected){
		if(this.selected!=null) this.selected.deselect();
		this.selected = selected;
		if(onSelect!=null) fireEvent();
	}
	
	/*
	 * Binds the menu colour to the primary input colour
	 * and the text to the contrasting colour.
	 */
	private void style(Theme theme){
		StyleBuilder style = StyleBuilder.styleNode(this);
		style.bindProperty(CSSProperty.BACKGROUND_COLOUR, theme.getPrimaryInputColour())
			 .bindContrastProperty(CSSProperty.TEXT_FILL, theme.getPrimaryInputColour(), theme.getLightTextColour(), theme.getDarkTextColour())
			 .build();
	}
	
	/*
	 * Gets the index of the selected node,
	 * and calls the OptionSelectEvent.
	 */
	private void fireEvent(){
		// int index = getChildren().indexOf(selected); TODO test this
		int index = -1;
		for(int i = 1; i<getChildren().size(); i++){
			OptionNode node = (OptionNode) getChildren().get(i);
			if(node.equals(selected)) index = i;
		}
		onSelect.selected(selected, index);
	}

	/**
	 * Gets the header text for this menu
	 * @return The LiFXText object or null if it does not exist.
	 */
	public LiFXText getText(){
		return getChildren().isEmpty() ? null : (LiFXText) getChildren().get(0);
	}
	
	/**
	 * Sets the header text for this OptionMenu
	 * @param text The header text
	 */
	public void setText(String text){
		getText().setText(text);
	}
	
	/**
	 * Sets the event to be fired when one of this
	 * menus OptioNnodes is selected.
	 * @param onSelect Event to be fired on select
	 */
	public void setOnSelected(OptionSelectEvent onSelect){
		this.onSelect = onSelect;
	}

}
