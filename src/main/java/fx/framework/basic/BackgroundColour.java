package fx.framework.basic;

import fx.framework.launcher.FXApplication;
import fx.framework.themes.ObservableRGBColour;

/**
 * Basic enum class to represent whether background colours should be bound to
 * the Primary or Secondary Theme colours.
 * @see Theme
 * @author Luke Stevens
 */
public enum BackgroundColour {
	
	PRIMARY, SECONDARY;
	
	/**
	 * Gets the {@link ObservableRGBColour} that this enum represents
	 * @return The background colour from the current Theme
	 */
	ObservableRGBColour colour(){
		if(this == PRIMARY) return FXApplication.THEME.getPrimaryBackgroundColour();
		else return FXApplication.THEME.getSecondaryBackgroundColour(); 
	}

}
