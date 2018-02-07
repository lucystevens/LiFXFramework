package fx.framework.basic;

import fx.framework.launcher.FXApplication;
import fx.framework.style.CSSProperty;
import fx.framework.style.CSSUtils;
import fx.framework.themes.RGBColour;
import fx.framework.themes.Theme;
import fx.framework.themes.TypeFace;
import javafx.scene.Parent;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Text extension with additional methods
 * and functionality for theming.
 * 
 * @author Luke Stevens
 *
 */
public class LiFXText extends Text {
	
	private boolean updateColour = true;
	private Parent parent;
	
	/**
	 * Constructs a new LiFXText instance with certain values;
	 * @param msg The message to be displayed by the text.
	 * @param size The size of the font.
	 * @param parent The parent that this text will reside within.
	 */
	public LiFXText(String msg, double size, Parent parent){
		super(msg);
		this.parent = parent;
		setFont(Font.font(size));
		style(FXApplication.THEME, parent);
		FXApplication.THEME.addGlobalListener(this::updateStyle);
	}
	
	/**
	 * Constructs a new LiFXText instance with certain values;
	 * @param msg The message to be displayed by the text.
	 * @param size The size of the font.
	 */
	public LiFXText(String msg, double size){
		this(msg, size, null);
	}
	
	/**
	 * Constructs a new LiFXText instance with certain values;
	 * @param msg The message to be displayed by the text.
	 * @param parent The parent that this text will reside within.
	 */
	public LiFXText(String msg, Parent parent){
		this(msg, 24, parent);
	}

	/**
	 * Constructs a new LiFXText instance with a specific message;
	 * @param msg The message to be displayed by the text.
	 */
	public LiFXText(String msg){
		this(msg, 24, null);
	}
	
	/**
	 * Tries to get the background colour of the parent, defaulting to white
	 * if there isn't a colour explicitly defined in the css styles.
	 * @param parent The parent node to this Text.
	 * @return The RGBColour defining the background colour of the parent,
	 * or white if this cannot be determined.
	 */
	private RGBColour getBackingColour(Parent parent){
		String style = CSSUtils.getCSSProperty(parent, CSSProperty.BACKGROUND_COLOUR);
		return style==null ? RGBColour.WHITE : new RGBColour(style);
	}
	
	/**
	 * If the colour has not been overridden, then detect whether dark
	 * or light text should be used, based on the background colour of 
	 * the parent node.<br>
	 * Also updates the typeface to the theme typeface.
	 * @param theme
	 * @param parent
	 */
	private void style(Theme theme, Parent parent){
		if (updateColour){
			RGBColour backingColour = getBackingColour(parent);
			if(backingColour.useDarkText()) setFill(theme.getDarkTextColour().getValue());
			else setFill(theme.getLightTextColour().getValue());
		}
		setTypeFace(theme.getTypeface().getValue());
	}
	
	/*
	 * Calls style, with either the calculated parent
	 * or the parent this object was insantiated with.
	 */
	private void updateStyle(Theme theme){
		Parent p = getParent();
		style(theme, (p==null) ? this.parent : p);
	}
	
	/**
	 * Sets the size of the font
	 * @param size in pt.
	 */
	public void setSize(double size){
		String font = getFont().getName();
		setFont(Font.font(font, size));
	}
	
	/**
	 * Sets the typeface of the font
	 * @param type A pre-defined typeface
	 */
	public void setTypeFace(TypeFace type){
		double size = getFont().getSize();
		setFont(Font.font(type.font, size));
	}
	
	/**
	 * Sets the fill colour of this text<br>
	 * <i><b>Note:</b> If the text colour is changed in the theme
	 * it will override the colour set here. Use {@link #overrideFill(colour)}
	 * if the change must be permanent.
	 * @param colour The colour to set this text as.
	 */
	public void setFill(RGBColour colour){
		setFill(colour.toFXColour());
	}
	
	/**
	 * Sets the permanent fill colour of this text<br>
	 * <i><b>Note:</b> If the text colour is changed in the theme
	 * it will NOT override the colour set here. Use {@link #setFill(colour)}
	 * if the change is only temporary.
	 * @param colour The colour to set this text as.
	 */
	public void overrideFill(RGBColour colour){
		updateColour = false;
		setFill(colour);
	}

}
