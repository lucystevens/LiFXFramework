package fx.framework.themes;

import javafx.scene.paint.Color;

public class RGBColour {

	public static final RGBColour BLACK = new RGBColour(0,0,0);
	public static final RGBColour WHITE = new RGBColour(255,255,255); 
	public static final RGBColour RED = new RGBColour(255,0,0); 
	public static final RGBColour GREEN = new RGBColour(0,255,0); 
	public static final RGBColour BLUE = new RGBColour(0,0,255);
	public static final RGBColour YELLOW = new RGBColour(255,255,0);
	
	public int R, G, B;
	
	public RGBColour(int r, int g, int b) {
		R = r; G = g; B = b;
	}
	
	public RGBColour(double r, double g, double b){
		this((int) r, (int) g, (int) b);
	}
	
	public RGBColour(Color color){
		this(color.getRed()*255d, color.getGreen()*255d, color.getBlue()*255d);
	}
	
	public RGBColour(int integerVal){
		R = integerVal/65536;
		integerVal -= (R*65536);
		G = integerVal/256;
		B = integerVal%256;
	}
	
	public RGBColour(String hex) {
		this(Integer.parseInt(hex.replace("#", ""), 16));
	}
	
	@Override
	public String toString() {
		return toHex();
	}
	
	public String toRGB(){
		return "rgb(" + R + ", " + G + ", " + B + ")";
	}
	
	public int toInteger(){
		return (R*65536) + (G*256) + B;
	}
	
	public String toHex(){
		String hex = Integer.toHexString(this.toInteger());
		while(hex.length()!=6){
			hex = "0" + hex;
		}
		return "#" + hex;
	}
	
	public Color toFXColour(){
		return new Color(R/255d, G/255d, B/255d, 1);
	}
	
	public RGBColour darker(){
		Color c = this.toFXColour();
		return new RGBColour(c.darker());
	}
	
	public RGBColour brighter(){
		Color c = this.toFXColour();
		return new RGBColour(c.brighter());
	}
	
	public boolean useDarkText(){
		return useDarkTextBasic();
	}
	
	public boolean useDarkTextBasic(){
		return (R*0.299d + G*0.587d + B*0.114d) > 186;
	}
	
	public boolean useDarkTextAdvanced(){
		double L = 0.2126 * calculateLuminance(R);
		L += 0.7152 * calculateLuminance(G);
		L += 0.0722 * calculateLuminance(B);
		return L > Math.sqrt(1.05 * 0.05) - 0.05;
	}
	
	private double calculateLuminance(double c){
		c = c/255d;
		return (c <= 0.03928) ? c/12.92 : Math.pow(((c+0.055)/1.055), 2.4);
	}

}
