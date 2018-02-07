package fx.framework.shortcuts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import fx.framework.launcher.FXApplication;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

/**
 * Helper class to manage registering, 
 * triggering and invalidating shortcuts.
 * 
 * @author Luke Stevens
 */
public class ShortcutManager {
	
	private static Map<KeyCode, Boolean> pressedKeys = new HashMap<>();
	private static List<Shortcut> listeners = new ArrayList<>();
	
	/*
	 * Adds event filters to the application stage
	 * to check every time a key is pressed
	 */
	static {
		FXApplication.getStage().getScene().addEventFilter(KeyEvent.KEY_PRESSED, ShortcutManager::keyPressed);
		FXApplication.getStage().getScene().addEventFilter(KeyEvent.KEY_RELEASED, ShortcutManager::keyReleased);
	}
	
	/*
	 * Called every time a key is pressed, adds
	 * the key to the map of pressed keys and 
	 * notfies shortcut listeners that keys have been pressed.
	 */
	private static void keyPressed(KeyEvent event){
		pressedKeys.put(event.getCode(), true);
		notifyListeners();
	}
	
	/*
	 * Called every time and key is released, removes
	 * the key from the map of pressed keys.
	 */
	private static void keyReleased(KeyEvent event){
		pressedKeys.put(event.getCode(), false);
	}
	
	/*
	 * Loops through all shortcuts to check if
	 * the required keys are pressed. If the 
	 * keys are pressed, the shortcut is fired
	 * and all pressed keys cleared.
	 */
	private static void notifyListeners(){
		for(Shortcut shortcut : listeners){
			if(isPressed(shortcut)) {
				shortcut.fireEvent();
				pressedKeys.clear();
				return;
			}
		}
	}
	
	/*
	 * Checks if the required keys are pressed for a
	 * shortcut.
	 */
	private static boolean isPressed(Shortcut shortcut){
		for(KeyCode key : shortcut.getKeys()){
			Boolean pressed = pressedKeys.get(key);
			if(pressed==null || !pressed.booleanValue()) return false;
		}
		return true;
	}
	
	/**
	 * Register a new shortcut
	 * @param shortcut Shortcut to register
	 */
	public static void registerShortcut(Shortcut shortcut){
		listeners.add(shortcut);
	}
	
	/**
	 * Invalidate and existing shortcut
	 * @param shortcut Shortcut to invalidate
	 */
	public static void invalidateShortcut(Shortcut shortcut){
		listeners.remove(shortcut);
	}

}
