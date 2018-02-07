package fx.framework.themes;

import com.jfoenix.controls.JFXColorPicker;
import com.jfoenix.controls.JFXComboBox;

import fx.framework.basic.LiFXButton;
import fx.framework.basic.LiFXGridPane;
import fx.framework.basic.LiFXText;
import fx.framework.launcher.FXApplication;
import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import javafx.collections.FXCollections;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ThemeChanger extends Stage {
	
	private Theme theme = FXApplication.THEME;
	
	public ThemeChanger(){
		Scene scene = new Scene(createNode(), 600, 600);
		setScene(scene);
	}
	
	private Parent createNode(){
		LiFXGridPane grid = new LiFXGridPane(20, 40);
		
		LiFXText primaryInputColourText = new LiFXText("Primary Input Colour");
		JFXColorPicker primaryInputColourPicker = new JFXColorPicker(theme.getPrimaryInputColour().getValue().toFXColour());
		primaryInputColourPicker.setOnAction(ae -> theme.setPrimaryInputColour(new RGBColour(primaryInputColourPicker.getValue())));
		grid.addRow(0, primaryInputColourText, primaryInputColourPicker);
		
		LiFXText hoverInputColourText  = new LiFXText("Hover Input Colour");
		JFXColorPicker hoverInputColourPicker  = new JFXColorPicker(theme.getHoverInputColour().getValue().toFXColour());
		hoverInputColourPicker.setOnAction(ae -> theme.setHoverInputColour(new RGBColour(hoverInputColourPicker.getValue())));
		grid.addRow(1, hoverInputColourText, hoverInputColourPicker);
		
		LiFXText lightTextColourText = new LiFXText("Lighter Text Colour");
		JFXColorPicker lightTextColourPicker = new JFXColorPicker(theme.getLightTextColour().getValue().toFXColour());
		lightTextColourPicker.setOnAction(ae -> theme.setLightTextColour(new RGBColour(lightTextColourPicker.getValue())));
		grid.addRow(2, lightTextColourText, lightTextColourPicker);
		
		LiFXText darkTextColourText = new LiFXText("Darker Text Colour");
		JFXColorPicker darkTextColourPicker = new JFXColorPicker(theme.getDarkTextColour().getValue().toFXColour());
		darkTextColourPicker.setOnAction(ae -> theme.setDarkTextColour(new RGBColour(darkTextColourPicker.getValue())));
		grid.addRow(3, darkTextColourText, darkTextColourPicker);
		
		LiFXText primaryBackgroundColourText = new LiFXText("Primary Background Colour");
		JFXColorPicker primaryBackgroundColourPicker = new JFXColorPicker(theme.getPrimaryBackgroundColour().getValue().toFXColour());
		primaryBackgroundColourPicker.setOnAction(ae -> theme.setPrimaryBackgroundColour(new RGBColour(primaryBackgroundColourPicker.getValue())));
		grid.addRow(4, primaryBackgroundColourText, primaryBackgroundColourPicker);
		
		LiFXText secondaryBackgroundColourText = new LiFXText("Secondary Background Colour");
		JFXColorPicker secondaryBackgroundColourPicker = new JFXColorPicker(theme.getSecondaryBackgroundColour().getValue().toFXColour());
		secondaryBackgroundColourPicker.setOnAction(ae -> theme.setSecondaryBackgroundColour(new RGBColour(secondaryBackgroundColourPicker.getValue())));
		grid.addRow(5, secondaryBackgroundColourText, secondaryBackgroundColourPicker);
		
		LiFXText borderColourText = new LiFXText("Border Colour");
		JFXColorPicker borderColourPicker = new JFXColorPicker(theme.getBorderColour().getValue().toFXColour());
		borderColourPicker.setOnAction(ae -> theme.setBorderColour(new RGBColour(borderColourPicker.getValue())));
		grid.addRow(6, borderColourText, borderColourPicker);
		
		LiFXText typefaceText = new LiFXText("Typeface");
		JFXComboBox<TypeFace> typefacePicker = new JFXComboBox<>(FXCollections.observableArrayList(TypeFace.values()));
		typefacePicker.setValue(FXApplication.THEME.getTypeface().getValue());
		typefacePicker.setOnAction(ae -> theme.setTypeface(typefacePicker.getValue()));
		grid.addRow(7, typefaceText, typefacePicker);
		
		LiFXButton save = new LiFXButton("Save");
		StyleBuilder.styleNode(save).replaceProperty(CSSProperty.FONT_SIZE, 18).build();
		save.setOnAction(ae -> this.close());
		
		LiFXButton reset = new LiFXButton("Reset");
		StyleBuilder.styleNode(reset).replaceProperty(CSSProperty.FONT_SIZE, 18).build();
		reset.setOnAction(ae -> theme.setToDefault());
		grid.addRow(9, save, reset);

		return grid;
	}

}
