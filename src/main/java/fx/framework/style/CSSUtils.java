package fx.framework.style;

import java.util.List;

import javafx.scene.Parent;

/**
 * Helper class to get CSS values from elements and
 * stylesheets
 * 
 * @author Luke Stevens
 *
 */
public class CSSUtils {
	
	/**
	 * Attempts to get a specific CSS Property from a Parent element
	 * @param parent The element for search for the property.
	 * @param property The property to search for in the element CSS
	 * @return The CSS property value if exists, or null if cannot be found.
	 */
	public static String getCSSProperty(Parent parent, CSSProperty property){
		if(parent==null) return null;
		String value = getCSSProperty(parent.getStyle(), property);
		return (value==null) ? getCSSProperty(parent.getStyleClass(), property) : value;
	}
	
	/**
	 * Attempts to get a specific CSS Property from a list of style classes
	 * @param styleClasses A list of style classes
	 * @param property The property to search for in the style classes CSS
	 * @return The CSS property value if exists, or null if cannot be found.
	 */
	public static String getCSSProperty(List<String> styleClasses, CSSProperty property){
		String result = null;
		for(String styleClass : styleClasses){
			result = getCSSProperty(styleClass, property);
			if(result!=null) return result;
		}
		return result;
	}
	
	/**
	 * Attempts to get a specific CSS Property from a specific style line
	 * @param style A single CSS Property line
	 * @param property The property to search for in the style line
	 * @return The CSS property value if exists, or null if cannot be found.
	 */
	public static String getCSSProperty(String style, CSSProperty property){
		String prop = property + ":";
		int propertyIndex = style.indexOf(property + ":");
		if(propertyIndex==-1) return null;
		style = style.substring(propertyIndex);
		int endIndex = style.indexOf(";");
		if(endIndex!=-1) style = style.substring(0, endIndex);
		return style.replace(prop, "").trim();
	}

}
