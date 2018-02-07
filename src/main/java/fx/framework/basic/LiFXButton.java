package fx.framework.basic;

import com.jfoenix.controls.JFXButton;

import fx.framework.launcher.FXApplication;
import fx.framework.shortcuts.Shortcut;
import fx.framework.shortcuts.ShortcutManager;
import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import fx.framework.themes.Theme;
import javafx.scene.input.KeyCode;

/**
 * Simple Button extension with Google material design styling, theme binding and shortcuts.
 * @author Luke Stevens
 */
public class LiFXButton extends JFXButton {
	
	private Shortcut shortcut;
	private boolean disableShortcut = false;
	
	/**
	 * Constructs a raised style button with text
	 * @param text The text to add to the centre of the button
	 */
	public LiFXButton(String text){
		this(text, ButtonType.RAISED);
	}
	
	/**
	 * Constructs a button with text.
	 * @param text The text to add to the centre of the button
	 * @param type The type of the Button (raised or flat)
	 * @see ButtonType
	 */
	public LiFXButton(String text, ButtonType type){
		super(text);
		style(FXApplication.THEME);
		setButtonType(type);
	}
	
	/*
	 * Binds properties for background colour and text fill.
	 * Also adds listeners to change style on hover.
	 */
	private void style(Theme theme){
		StyleBuilder style = StyleBuilder.styleNode(this);
		style.bindProperty(CSSProperty.BACKGROUND_COLOUR, theme.getPrimaryInputColour())
			 .bindContrastProperty(CSSProperty.TEXT_FILL, theme.getPrimaryInputColour(), theme.getLightTextColour(), theme.getDarkTextColour())
			 .build();
		
		setOnMouseEntered(me -> style.replaceProperty(CSSProperty.BACKGROUND_COLOUR, theme.getHoverInputColour()).build());
		setOnMouseExited(me -> style.replaceProperty(CSSProperty.BACKGROUND_COLOUR, theme.getPrimaryInputColour()).build());
	}
	
	/**
	 * Sets the shortcut keys required to fire this button using the keyboard.<br>
	 * Please note that all these keys will have to be pressed simultaneously for the 
	 * button to fire.
	 * @param keys The keys to be bound to this button.
	 */
	public void setShortcut(KeyCode...keys){
		this.shortcut = new Shortcut(s -> fire(), keys);
		ShortcutManager.registerShortcut(shortcut);
	}
	
	/**
	 * Invalidates the shortcut set for this button,
	 * allowing it to be re-used for another button.
	 */
	public void invalidateShortcut(){
		ShortcutManager.invalidateShortcut(shortcut);
	}
	
	/**
	 * Sets whether this shortcut should be disabled after the button
	 * is fired e.g. in circumstances where clicking the button may
	 * close a window or otherwise remove it from the scene.
	 * @param disableShortcut Whether this shortcut should be
	 * disabled after the button is fired
	 */
	public void setDisableShortcutAfterFire(boolean disableShortcut){
		this.disableShortcut = disableShortcut;
		
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void fire() {
		super.fire();
		if(disableShortcut) invalidateShortcut();
	}

}
