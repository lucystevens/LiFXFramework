package fx.framework.launcher;

import java.util.Arrays;
import java.util.List;

import fx.framework.style.CSSProperty;
import fx.framework.style.StyleBuilder;
import fx.framework.themes.Theme;
import fx.framework.themes.TypeFace;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * The base class for FXFramework.<br><br>
 * This is an implementation of StackPane that forms the root for 
 * a JavaFX application built with this framework, allowing dialog
 * panes to be shown over the main window.<br><br>
 * To create a new application using this, you must extend an instance 
 * of {@link Application} and implement the <code>start</code> method as usual.<br>
 * Within the <code>start</code> method, call {@link FXApplication#start(Stage)} 
 * passing the created stage, and any other necessary parameters.
 * @author Luke Stevens
 */
public class FXApplication extends StackPane {
	
	public static double SCREEN_WIDTH = 1000;
	public static double SCREEN_HEIGHT = 750;
	public static FXApplication INSTANCE;
	public static Theme THEME = new Theme();
	
	private ChangeListener<? super Number> screenWidthListener;
	private Stage stage;
	
	/*
	 * Constructs a new FXApplication using the given stage.
	 * This ...
	 * 	- Binds the background colour of the application to the secondary theme colour.
	 * 	- Detects whether the Application is wrapped in a Jar or not, and sets the URI prefix accordingly.
	 * 	- Loads all fonts and stylesheets
	 * 	- Creates a new scene with this as the root.
	 */
	private FXApplication(Stage stage){
		super(new BorderPane());
		StyleBuilder.styleNode(this).bindProperty(CSSProperty.BACKGROUND_COLOUR, THEME.getSecondaryBackgroundColour()).build();
		this.stage = stage;
		loadFonts();
		Scene scene = new Scene(this, SCREEN_WIDTH, SCREEN_HEIGHT);
		loadStylesheets(scene);
		stage.setScene(scene);
	}
	
	/*
	 * Constructs a new FXApplication using the given stage, but also setting the window title.
	 */
	private FXApplication(Stage stage, String title){
		this(stage);
		stage.setTitle(title);
	}
	
	/*
	 * Constructs a new FXApplication using the given stage, but also setting the window title and icon.
	 */
	private FXApplication(Stage stage, String title, String icon){
		this(stage, title);
		Image iconImg = new Image(this.getClass().getResourceAsStream(icon));
		stage.getIcons().add(iconImg);
	}
	
	/**
	 * Constructs a new instance of FXApplication
	 * @param stage The stage passed into the overridden method from {@link Application#start(Stage)}
	 */
	public static void start(Stage stage){
		INSTANCE = new FXApplication(stage);
	}
	
	/**
	 * Constructs a new instance of FXApplication
	 * @param stage The stage passed into the overridden method from {@link Application#start(Stage)}
	 * @param title The title of the application window
	 */
	public static void start(Stage stage, String title){
		INSTANCE = new FXApplication(stage, title);
	}
	
	/**
	 * Constructs a new instance of FXApplication
	 * @param stage The stage passed into the overridden method from {@link Application#start(Stage)}
	 * @param title The title of the application window
	 * @param icon The resource url of the icon to use for the application
	 */
	public static void start(Stage stage, String title, String icon){
		INSTANCE = new FXApplication(stage, title, icon);
	}
	
	/*
	 * Loops through all Typefaces, and loads in all the ones that
	 * aren't system defaults.
	 */
	private void loadFonts(){
		for(TypeFace font : TypeFace.values()){
			if(font.needsLoading()) Font.loadFont(FXApplication.class.getResource(font.filename()).toExternalForm(), 24);
		}
	}
	
	/*
	 * Loads all defined stylesheets and adds to the scene
	 */
	private void loadStylesheets(Scene scene){
		List<String> stylesheets = Arrays.asList("lifx-components.css", "previews.css", "jfx-components.css");
		stylesheets.forEach(s -> scene.getStylesheets().add("/css/" + s));
	}
	
	/**
	 * Removes the current screen width listener
	 */
	public static void removeCurrentScreenWidthListener(){
		if(INSTANCE.screenWidthListener!=null) INSTANCE.stage.getScene().widthProperty().removeListener(INSTANCE.screenWidthListener);
	}
	
	/**
	 * Updates the current screen width listener.<br> There may only be one listener
	 * added this way at any one time, so this will also remove the current screen width listener.
	 * @param listener The change listener to add to the current scene.
	 */
	public static void updateScreenWidthListener(ChangeListener<? super Number> listener){
		removeCurrentScreenWidthListener();
		INSTANCE.screenWidthListener = listener;
		INSTANCE.stage.getScene().widthProperty().addListener(listener);
	}
	
	/**
	 * Shows the stage.<br>
	 * Equivalent to calling {@link Stage#show()}
	 */
	public static void show(){
		INSTANCE.stage.show();
	}
	
	/**
	 * Gets the Stage
	 * @return The Stage
	 */
	public static Stage getStage(){
		return INSTANCE.stage;
	}
	
	/**
	 * Gets the {@link BorderPane} at the root of this StackPane
	 * @return BorderPane
	 */
	public static BorderPane getBorderPane(){
		return INSTANCE.getChildren().isEmpty() ? null : (BorderPane) INSTANCE.getChildren().get(0);
	}
	
	/**
	 * Sets the top node of the root BorderPane.
	 * @param node The node to set as top of the BorderPane
	 */
	public static void setTop(Node node){
		getBorderPane().setTop(node);
	}
	
	/**
	 * Sets the bottom node of the root BorderPane.
	 * @param node The node to set as bottom of the BorderPane
	 */
	public static void setBottom(Node node){
		getBorderPane().setBottom(node);
	}
	
	/**
	 * Sets the left node of the root BorderPane.
	 * @param node The node to set as left of the BorderPane
	 */
	public static void setLeft(Node node){
		getBorderPane().setLeft(node);
	}
	
	/**
	 * Sets the right node of the root BorderPane.
	 * @param node The node to set as right of the BorderPane
	 */
	public static void setRight(Node node){
		getBorderPane().setRight(node);
	}
	
	/**
	 * Sets the centre node of the root BorderPane.
	 * @param node The node to set as centre of the BorderPane
	 */
	public static void setCentre(Node node){
		getBorderPane().setCenter(node);
	}

}
