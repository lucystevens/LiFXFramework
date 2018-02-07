package fx.framework.themes;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Theme {
	
	private List<ThemeChangeListener> themeListeners = new ArrayList<>();
	
	private ObservableRGBColour primaryInputColour = new ObservableRGBColour();
	private ObservableRGBColour hoverInputColour  = new ObservableRGBColour();
	
	private ObservableRGBColour lightTextColour = new ObservableRGBColour();
	private ObservableRGBColour darkTextColour = new ObservableRGBColour();
	
	private ObservableRGBColour primaryBackgroundColour = new ObservableRGBColour();
	private ObservableRGBColour secondaryBackgroundColour = new ObservableRGBColour();
	private ObservableRGBColour borderColour = new ObservableRGBColour();
	
	private ObservableFont typeface = new ObservableFont();
	
	private Properties props;
	
	public Theme(){
		setToDefault();
	}
	
	public ObservableRGBColour getContrastingTextColour(RGBColour background){
		return (background.useDarkText()) ? darkTextColour : lightTextColour;
	}
	
	public void setToDefault(){
		if(props!=null) loadDefaultsFromProperties();
		else{
			setPrimaryInputColour(new RGBColour(46, 117, 182));
			setHoverInputColour(new RGBColour(31, 78, 121));
			
			setLightTextColour(RGBColour.WHITE);
			setDarkTextColour(RGBColour.BLACK);
			
			setPrimaryBackgroundColour(RGBColour.WHITE);
			setSecondaryBackgroundColour(new RGBColour(244, 244, 244));
			setBorderColour(RGBColour.BLACK);
			
			setTypeface(TypeFace.ROBOTO_REGULAR);
		}
	}
	
	private void loadDefaultsFromProperties(){
		setPrimaryInputColour(new RGBColour(props.getProperty("lifx.theme.primaryInputColour", "#2e75b6")));
		setHoverInputColour(new RGBColour(props.getProperty("lifx.theme.hoverInputColour", "#1f4e79")));
		
		setLightTextColour(new RGBColour(props.getProperty("lifx.theme.lightTextColour", "#ffffff")));
		setDarkTextColour(new RGBColour(props.getProperty("lifx.theme.darkTextColour", "#000000")));
		
		setPrimaryBackgroundColour(new RGBColour(props.getProperty("lifx.theme.primaryBackgroundColour", "#ffffff")));
		setSecondaryBackgroundColour(new RGBColour(props.getProperty("lifx.theme.secondaryBackgroundColour", "#f4f4f4")));
		setBorderColour(new RGBColour(props.getProperty("lifx.theme.borderColour", "#000000")));
		
		setTypeface(TypeFace.valueOf(props.getProperty("lifx.theme.typeface", "ROBOTO_REGULAR")));
	}
	
	public void importFromProperties(Properties props){
		this.props = props;
		setToDefault();
	}
	
	public Properties export(){
		Properties props = new Properties();
		props.put("lifx.theme.primaryInputColour", primaryInputColour.getValue().toHex());
		props.put("lifx.theme.hoverInputColour", hoverInputColour.getValue().toHex());
		props.put("lifx.theme.lightTextColour", lightTextColour.getValue().toHex());
		props.put("lifx.theme.darkTextColour", darkTextColour.getValue().toHex());
		props.put("lifx.theme.primaryBackgroundColour", primaryBackgroundColour.getValue().toHex());
		props.put("lifx.theme.secondaryBackgroundColour", secondaryBackgroundColour.getValue().toHex());
		props.put("lifx.theme.borderColour", borderColour.getValue().toHex());
		props.put("lifx.theme.typeface", typeface.getValue().name());
		return props;
	}
	
	
	
	private void notifyListeners(){
		for (ThemeChangeListener listener : themeListeners) {
			listener.changed(this);
		}
	}
	
	public void addGlobalListener(ThemeChangeListener listener){
		themeListeners.add(listener);
	}
	
	public ObservableRGBColour getPrimaryInputColour() {
		return primaryInputColour;
	}
	
	public void setPrimaryInputColour(RGBColour primaryInputColour) {
		this.primaryInputColour.setValue(primaryInputColour);
		notifyListeners();
	}
	
	public ObservableRGBColour getHoverInputColour() {
		return hoverInputColour;
	}
	
	public void setHoverInputColour(RGBColour hoverInputColour) {
		this.hoverInputColour.setValue(hoverInputColour);
		notifyListeners();
	}
	
	public ObservableRGBColour getLightTextColour() {
		return lightTextColour;
	}
	
	public void setLightTextColour(RGBColour lightTextColour) {
		this.lightTextColour.setValue(lightTextColour);
		notifyListeners();
	}
	
	public ObservableRGBColour getDarkTextColour() {
		return darkTextColour;
	}
	
	public void setDarkTextColour(RGBColour darkTextColour) {
		this.darkTextColour.setValue(darkTextColour);
		notifyListeners();
	}
	
	public ObservableRGBColour getPrimaryBackgroundColour() {
		return primaryBackgroundColour;
	}
	
	public void setPrimaryBackgroundColour(RGBColour primaryBackgroundColour) {
		this.primaryBackgroundColour.setValue(primaryBackgroundColour);
		notifyListeners();
	}
	
	public ObservableRGBColour getSecondaryBackgroundColour() {
		return secondaryBackgroundColour;
	}
	public void setSecondaryBackgroundColour(RGBColour secondaryBackgroundColour) {
		this.secondaryBackgroundColour.setValue(secondaryBackgroundColour);
		notifyListeners();
	}
	
	public ObservableRGBColour getBorderColour() {
		return borderColour;
	}
	
	public void setBorderColour(RGBColour borderColour) {
		this.borderColour.setValue(borderColour);
		notifyListeners();
	}

	public ObservableFont getTypeface() {
		return typeface;
	}

	public void setTypeface(TypeFace typeface) {
		this.typeface.setValue(typeface);
		notifyListeners();
	}
	
	
}
