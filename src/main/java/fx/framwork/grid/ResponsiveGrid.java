package fx.framwork.grid;

import java.util.List;

import fx.framework.basic.LiFXHBox;
import fx.framework.basic.LiFXVBox;
import fx.framework.launcher.FXApplication;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;

public class ResponsiveGrid extends LiFXVBox {
	
	private double nodeWidth;
	private int nodesPerLine = 4;
	private List<Node> nodes;
	
	public ResponsiveGrid(List<Node> nodes){
		this.nodes = nodes;
		nodeWidth = (nodes.isEmpty()) ? 1 : nodes.get(0).getBoundsInParent().getWidth()+40;
		Scene scene = FXApplication.getStage().getScene();
		updateItemsPerLine(scene.getWidth());
		update();
		FXApplication.updateScreenWidthListener((ov, oldSW, newSW) -> updateItemsPerLine(newSW.doubleValue()));
	}
	
	private void updateItemsPerLine(double width){
		Node leftNode = FXApplication.getBorderPane().getLeft();
		double leftWidth = (leftNode==null) ? 0 : leftNode.getBoundsInParent().getWidth();
		
		Node rightNode = FXApplication.getBorderPane().getRight();
		double rightWidth = (rightNode==null) ? 0 : rightNode.getBoundsInParent().getWidth();
		
		int newNodesPerLine = Math.max(1, (int) ((width-leftWidth+rightWidth)/nodeWidth));
		
		if(newNodesPerLine != nodesPerLine){
			nodesPerLine = newNodesPerLine;
			update();
		}
	}
	
	public void add(Node node){
		nodes.add(node);
		update();
	}
	
	public void addAll(Node...nodes){
		for(Node node : nodes){
			this.nodes.add(node);
		}
		update();
	}
	
	public void update(){
		getChildren().clear();
		setPadding(new Insets(10));
		setSpacing(15);	
		for(int i = 0; i < nodes.size(); i += nodesPerLine) {
			LiFXHBox hbox = new LiFXHBox();
			for (int j = 0; j < nodesPerLine; j++) {
				if (i + j < nodes.size()) hbox.getChildren().add(nodes.get(i + j));
			}
			getChildren().add(hbox);
		}
	}
	
	public ScrollPane getScrollableNode(){
		return new ScrollPane(this);
	}

}
