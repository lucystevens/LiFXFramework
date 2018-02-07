package fx.framework.themes;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObservableRGBColour implements ObservableValue<RGBColour> {
	
	private RGBColour colour;
	private List<InvalidationListener> invalidationListeners = new ArrayList<>();
	private List<ChangeListener<? super RGBColour>> changeListeners = new ArrayList<>();
	
	public ObservableRGBColour(){
		super();
	}
	
	public ObservableRGBColour(RGBColour colour) {
		super();
		this.colour = colour;
	}
	@Override
	public String toString() {
		return colour.toHex();
	}

	@Override
	public void addListener(InvalidationListener listener) {
		invalidationListeners.add(listener);
	}

	@Override
	public void removeListener(InvalidationListener listener) {
		invalidationListeners.remove(listener);
	}

	@Override
	public void addListener(ChangeListener<? super RGBColour> listener) {
		changeListeners.add(listener);
	}

	@Override
	public void removeListener(ChangeListener<? super RGBColour> listener) {
		changeListeners.remove(listener);
	}

	@Override
	public RGBColour getValue() {
		return colour;
	}
	
	public void setValue(RGBColour colour){
		for(ChangeListener<? super RGBColour> listener : changeListeners){
			listener.changed(this, this.colour, colour);
		}
		this.colour = colour;
	}

}
