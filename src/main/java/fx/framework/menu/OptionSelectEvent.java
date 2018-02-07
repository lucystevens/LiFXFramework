package fx.framework.menu;

/**
 * An event to be fired when an OptionNode in
 * an OptionMenu is selected.
 * 
 * @author Luke Stevens
 * @see OptionNode
 * @see OptionMenu
 */
@FunctionalInterface
public interface OptionSelectEvent {
	
	public void selected(OptionNode node, int index);

}
