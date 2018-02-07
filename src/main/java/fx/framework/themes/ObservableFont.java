package fx.framework.themes;

import java.util.ArrayList;
import java.util.List;

import javafx.beans.InvalidationListener;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

public class ObservableFont implements ObservableValue<TypeFace> {
	
	private TypeFace type;
	private List<InvalidationListener> invalidationListeners = new ArrayList<>();
	private List<ChangeListener<? super TypeFace>> changeListeners = new ArrayList<>();
	
	public ObservableFont(){
		super();
	}
	
	public ObservableFont(TypeFace type) {
		super();
		this.type = type;
	}
	
	@Override
	public String toString() {
		return type.font;
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
	public void addListener(ChangeListener<? super TypeFace> listener) {
		changeListeners.add(listener);
	}

	@Override
	public void removeListener(ChangeListener<? super TypeFace> listener) {
		changeListeners.remove(listener);
	}

	@Override
	public TypeFace getValue() {
		return type;
	}
	
	public void setValue(TypeFace type){
		for(ChangeListener<? super TypeFace> listener : changeListeners){
			listener.changed(this, this.type, type);
		}
		this.type = type;
	}

}
