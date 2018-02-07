package fx.framework.style;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.util.Pair;

/**
 * Pair implementation for holding
 * and observable value and a listener
 * for it together in the sam object.
 * 
 * @author Luke Stevens
 *
 * @param <T>
 */
public class ChangeListenerInfo<T> extends Pair<ObservableValue<T>, ChangeListener<T>> {

	private static final long serialVersionUID = -5932136452280546933L;

	/**
	 * Constructs a new ChangeListenerInfo object
	 * @param key The observable value
	 * @param value The change listener
	 */
	public ChangeListenerInfo(ObservableValue<T> key, ChangeListener<T> value) {
		super(key, value);
	}
	
	/**
	 * Removes this listener from this observable value
	 */
	public void removeListener(){
		super.getKey().removeListener(super.getValue());
	}

}
