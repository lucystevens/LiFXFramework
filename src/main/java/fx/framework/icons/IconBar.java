package fx.framework.icons;

import fx.framework.basic.LiFXHBox;
import fx.framework.filler.FillType;
import fx.framework.filler.FillerRegion;
import fx.framework.launcher.FXApplication;
import fx.framework.shortcuts.Shortcut;
import fx.framework.shortcuts.ShortcutManager;
import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import fx.framework.themes.Theme;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * An icon bar, recommended for use at the top of the scene.
 * Supports theming.
 * @author Luke Stevens
 *
 */
public class IconBar extends LiFXHBox {
	
	/**
	 * Constructs a new IconBar with the default central alignment.
	 */
	public IconBar(){
		style(FXApplication.THEME);
		setAlignment(Pos.CENTER);
	}
	
	/*
	 * Styles the icon bar by binding the background colour
	 * to the primary background, adding a bottom border, and 
	 * binding that to the theme border colour.
	 */
	private void style(Theme theme){
		StyleBuilder.styleNode(this)
			.bindProperty(CSSProperty.BACKGROUND_COLOUR, theme.getPrimaryBackgroundColour())
			.replaceProperty(CSSProperty.BORDER_STYLE, "hidden hidden solid hidden")
			.replaceProperty(CSSProperty.BORDER_WIDTH, "2px")
			.bindProperty(CSSProperty.BORDER_COLOUR, theme.getBorderColour())
			.build();
	}
	
	/**
	 * Adds an icon to the left hand side of the icon bar.
	 * @param icon The {@link Icon} to add.
	 * @param onClick The handler for when the icon is clicked.
	 */
	public void addIcon(Icon icon, EventHandler<MouseEvent> onClick){ 
		Pane btn = new IconButton(icon, 30);
		btn.setOnMouseClicked(onClick);
		getChildren().add(btn);
	}
	
	/**
	 * Adds an icon to the left hand side of the icon bar, and defines a keyboard shortcut.
	 * @param icon The {@link Icon} to add.
	 * @param onClick The handler for when the icon is clicked.
	 * @param shortcut The series of keys that, along with CTRL, must be 
	 * simultaneously pressed to fire this icons event.
	 */
	public void addIcon(Icon icon, EventHandler<MouseEvent> onClick, KeyCode shortcut){ 
		addIcon(icon, onClick);
		ShortcutManager.registerShortcut(new Shortcut(s -> onClick.handle(null), this, KeyCode.CONTROL, shortcut));
	}
	
	/**
	 * Adds a {@link FillerRegion} to this IconBar, meaning any
	 * further children will be added to the right hand side of the bar.
	 */
	public void addFiller(){
		getChildren().add(new FillerRegion(FillType.HBOX));
	}
	
}
