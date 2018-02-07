package fx.framework.inplace;

import javafx.scene.Node;

@FunctionalInterface
public interface FieldExtractor {
	
	public Object extractValue (Node n);

}
