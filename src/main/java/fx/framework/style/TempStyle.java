package fx.framework.style;

import java.util.function.Consumer;

import javafx.scene.Node;

public class TempStyle {
	
	private Consumer<Node> add;
	private Consumer<Node> remove;
	
	public TempStyle (Consumer<Node> add, Consumer<Node> remove){
		this.add = add;
		this.remove = remove;
	}
	
	public void addStyle(Node node){
		add.accept(node);
	}
	
	public void removeStyle(Node node){
		remove.accept(node);
	}

}
