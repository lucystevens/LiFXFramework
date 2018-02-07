package fx.framework.style;

import java.util.HashMap;
import java.util.Map;

import fx.framework.themes.ObservableRGBColour;
import fx.framework.themes.RGBColour;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;

/**
 * Builder class implementing the multion format<br>
 * Used to simplify adding, removing 
 * or binding styles to nodes.
 * 
 * @author Luke Stevens
 *
 */
public class StyleBuilder {
	
	private String style;
	private Node node;
	private Map<CSSProperty, ChangeListenerInfo<RGBColour>> listenerMap = new HashMap<>();
	private static Map<Node, StyleBuilder> styles = new HashMap<>();
	
	/**
	 * Gets a new style builder instance for a given node. 
	 * If an instance was already created, that instance is returned,
	 * otherwise a new instance is created.
	 * @param node The node to style.
	 * @return The style builder associated with that node.
	 */
	public static StyleBuilder styleNode(Node node){
		StyleBuilder style = styles.get(node);
		if(style==null) {
			synchronized (node) {
				if(style==null){
					style = new StyleBuilder(node);
					styles.put(node, style);
				}
			}
		}
		return style;
	}
	
	/*
	 * Constructs a new StyleBuidler given a node.
	 * Should only be called by the static styleNode
	 * method.
	 */
	private StyleBuilder(Node node){
		this.node = node;
		this.style = node.getStyle();
	}
	
	/*
	 * Adds a property style to the unbuilt style string
	 */
	private StyleBuilder addProperty(CSSProperty property, String value){
		style += property.style(value);
		return this;
	}
	
	/**
	 * Removes a specific property from the node
	 * @param property The property to remove
	 * @return This StyleBuilder instance
	 */
	public StyleBuilder removeProperty(CSSProperty property){
		String regex = property.style(".+?");
		style = style.replaceAll(regex, "");
		return this;
	}
	
	/**
	 * Replaces a CSS property with a different value
	 * @param property The property to update
	 * @param value The value to update the property with
	 * @return This StyleBuilder instance
	 */
	public StyleBuilder replaceProperty(CSSProperty property, String value){
		removeProperty(property);
		addProperty(property, value);
		return this;
	}
	
	/**
	 * Replaces a CSS property with a different value.<br>
	 * This is equivalent to calling <code>replaceProperty(property, object.toString())</code>
	 * @param property The property to update
	 * @param value The value to update the property with
	 * @return This StyleBuilder instance
	 */
	public StyleBuilder replaceProperty(CSSProperty property, Object value){
		return replaceProperty(property, value.toString());
	}
	
	public StyleBuilder bindProperty(CSSProperty property, ObservableRGBColour colour){
		ChangeListener<RGBColour> listener = (obs, oldVal, newVal) -> replaceProperty(property, newVal.toHex()).build();
		colour.addListener(listener);
		
		unbindProperty(property);
		listenerMap.put(property, new ChangeListenerInfo<>(colour, listener));
		replaceProperty(property, colour);
		return this;
	}
	
	public StyleBuilder bindContrastProperty(CSSProperty property, ObservableRGBColour contrastWith){
		contrastWith.addListener((obs, oldVal, newVal) -> replaceProperty(property, (newVal.useDarkText()) ? RGBColour.BLACK : RGBColour.WHITE).build());
		return replaceProperty(property, (contrastWith.getValue().useDarkText()) ? RGBColour.BLACK : RGBColour.WHITE);
	}
	
	public StyleBuilder bindContrastProperty(CSSProperty property, ObservableRGBColour contrastWith, ObservableRGBColour light, ObservableRGBColour dark){
		contrastWith.addListener((obs, oldVal, newVal) -> bindProperty(property, (newVal.useDarkText()) ? dark : light).build());
		return bindProperty(property, (contrastWith.getValue().useDarkText()) ? dark : light);
	}
	
	public StyleBuilder unbindProperty(CSSProperty property){
		ChangeListenerInfo<RGBColour> info = listenerMap.get(property);
		if(info!=null) info.removeListener();
		return this;
	}
	
	public void build(){
		node.setStyle(style);
	}

}
