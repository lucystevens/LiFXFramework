package fx.framework.lists;

import java.util.ArrayList;
import java.util.List;

import fx.framework.basic.LiFXGridPane;

@Deprecated
public class FieldList extends LiFXGridPane{
	
	protected List<FieldListRow> list = new ArrayList<>();

	public FieldList() {
		FieldListRow row = new FieldListRow(this);
		row.addToGridPane(1);
		list.add(row);
	}
	
	public List<String> getValues(){
		List<String> values = new ArrayList<>();
		for(FieldListRow row : list){
			if(row.isActive() && !row.getValue().isEmpty()) values.add(row.getValue());
		}
		return values;
	}

}
