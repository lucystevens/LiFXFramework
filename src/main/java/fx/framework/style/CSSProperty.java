package fx.framework.style;

/**
 * An enum to store currently used CSSProperties
 * for easy reference
 * 
 * @author Luke Stevens
 */
public enum CSSProperty {
	
	BACKGROUND_COLOUR("-fx-background-color"),
	BORDER_STYLE("-fx-border-style"),
	BORDER_WIDTH("-fx-border-width"),
	BORDER_COLOUR("-fx-border-color"),
	FONT_FAMILY("-fx-font-family"),
	FONT_SIZE("-fx-font-size"),
	PROMPT_TEXT_FILL("-fx-prompt-text-fill"),
	TEXT_FILL("-fx-text-fill");
	
	String style;
	
	CSSProperty(String style){
		this.style = style;
	}
	
	@Override
	public String toString() {
		return style;
	}
	
	/**
	 * Generates a CSS style value using the given
	 * value for this property
	 * @param value The value to set this property to
	 * @return A usable CSS style value
	 */
	public String style(String value){
		return style + ": " + value + ";";
	}
	
}
