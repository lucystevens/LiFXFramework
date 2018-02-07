package fx.framework.filler;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

/**
 * A convenience class to use to more easily fill out 
 * spaces in HBox and VBoxs
 * @see FillType
 * @author Luke Stevens
 */
public class FillerRegion extends Region {

	/**
	 * Constructs a new FillerRegion
	 * @param type The filltype (either HBox or VBox) depending
	 * on what the FillerRegion will be used in
	 * @param priority The grow priority
	 */
	public FillerRegion(FillType type, Priority priority) {
		if(type==FillType.HBOX) HBox.setHgrow(this, priority);
		else VBox.setVgrow(this, priority);
	}
	
	/**
	 * Constructs a new FillerRegion with a default grow priority of <code>Priority.ALWAYS</code>
	 * @param type The filltype (either HBox or VBox) depending
	 * on what the FillerRegion will be used in
	 */
	public FillerRegion(FillType type){
		this(type, Priority.ALWAYS);
	}

}
