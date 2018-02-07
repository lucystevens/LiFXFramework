package fx.framework.menu;

import fx.framework.launcher.FXApplication;
import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import fx.framework.themes.Theme;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

/**
 * Element to populate {@link OptionMenu}
 * 
 * @author Luke Stevens
 */
public class OptionNode extends Button {
	
	private OptionMenu parent;
	private boolean selected;
	
	/**
	 * Constructs an OptionNode with a given text String.
	 * @param text The text to display for this option
	 */
	public OptionNode(String text){
		super(text);
		setId("option-node");
		style(FXApplication.THEME);
	}
	
	/*
	 * Called when added to an option menu.
	 * Binds prefwidth to parents pref width
	 * and stores reference to parent
	 */
	void setParent(OptionMenu parent){
		this.parent = parent;
		prefWidthProperty().bind(parent.prefWidthProperty());
	}
	
	/*
	 * Called when this node is deselected.
	 * Restyles background colour back to standard.
	 */
	void deselect(){
		selected = false;
		StyleBuilder.styleNode(this)
			.replaceProperty(CSSProperty.BACKGROUND_COLOUR,
				FXApplication.THEME.getPrimaryInputColour()).build();
	}
	
	/*
	 * Styles this node
	 */
	private void style(Theme theme){
		StyleBuilder style = StyleBuilder.styleNode(this);
		style.bindProperty(CSSProperty.BACKGROUND_COLOUR, theme.getPrimaryInputColour())
			 .bindContrastProperty(CSSProperty.TEXT_FILL, theme.getPrimaryInputColour(), theme.getLightTextColour(), theme.getDarkTextColour())
			 .build();
		
		setOnMouseEntered(me -> {
			if(!selected) style.replaceProperty(CSSProperty.BACKGROUND_COLOUR, theme.getHoverInputColour()).build();
		});
		setOnMouseExited(me -> {
			if(!selected) style.replaceProperty(CSSProperty.BACKGROUND_COLOUR, theme.getPrimaryInputColour()).build();
		});
	}
	
	/**
	 * Sets the action to perform when selected
	 * @param event EventHandler to call when selected
	 */
	public void onSelect(EventHandler<ActionEvent> event){
		setOnAction(ae -> {
			if(!selected){
				event.handle(ae);
				StyleBuilder.styleNode(this)
					.replaceProperty(CSSProperty.BACKGROUND_COLOUR,
						FXApplication.THEME.getPrimaryInputColour().getValue().darker()).build();
				selected = true;
				parent.setSelected(this);
			}
		});
	}

}
