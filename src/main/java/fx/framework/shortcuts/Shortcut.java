package fx.framework.shortcuts;

import javafx.scene.Parent;
import javafx.scene.input.KeyCode;

/**
 * A class representing a keyboard shortcut
 * that can be fired by pressing a number of
 * keys simultaneously
 * 
 * @author Luke Stevens
 */
public class Shortcut {
	
	private ShortcutEvent event;
	private KeyCode[] keys;
	private Parent parent;
	
	/**
	 * Constructs a new standard shortcut
	 * @param event The event to fire
	 * @param keys The keys to be pressed to trigger shortcut
	 */
	public Shortcut(ShortcutEvent event, KeyCode...keys){
		this(event, null, keys);
	}
	
	/**
	 * Constructs a new shortcut that will only fire if the parent 
	 * if visible on screen
	 * @param event The event to fire
	 * @param parent The shortcut parent to check visibility
	 * @param keys The keys to be pressed to trigger shortcut
	 */
	public Shortcut(ShortcutEvent event, Parent parent, KeyCode...keys){
		this.event = event;
		this.parent = parent;
		this.keys = keys;
	}
	
	// Simply returns the String representations of the keys
	// needed to trigger this shortcut
	@Override
	public String toString() {
		String result = "";
		for(KeyCode key : keys){
			result += key.getName() + ";";
		}
		return result.toUpperCase();
	}
	
	/**
	 * @return The keys that must be pressed to trigger this shortcut
	 */
	public KeyCode[] getKeys(){
		return keys;
	}
	
	/**
	 * Fires the event.<br>
	 * If a parent was defined, this checks whether
	 * it is visible on screen before firing.
	 */
	public void fireEvent(){
		if(parent==null || parent.computeAreaInScreen() > 0) event.handle(this);
	}
	
	

}
