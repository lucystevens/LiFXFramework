package fx.framework.dialog;

import java.util.function.Consumer;

import com.jfoenix.controls.JFXDialog;

import fx.framework.basic.LiFXButton;
import fx.framework.basic.LiFXHBox;
import fx.framework.basic.LiFXText;
import fx.framework.basic.LiFXVBox;
import fx.framework.launcher.FXApplication;
import fx.framework.themes.RGBColour;
import fx.framework.themes.ThemeChanger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 * A convenience class for showing the user dialog
 * messages.
 * 
 * @author Luke Stevens
 */
public class DialogService {
	
	private static final String SHUTDOWN_MESSAGE = "The application has encountered a critical error and must close.";
	
	/**
	 * Presents the user with a confirm dialog,
	 * over the FXApplication root stackpane
	 * @param message The message to show to the user
	 * @param consumer A method that takes the button type
	 * pressed (YES or NO) and performs necessary actions
	 * based on which button the user selects.
	 */
	public static void showConfirmDialog(String message, Consumer<ButtonType> consumer){
		JFXDialog dialog = new JFXDialog();
		LiFXText warning = new LiFXText(message);
		
		// YES button, calls consumer and closes dialog
		Button yes = new LiFXButton("Yes");
		yes.setOnAction(ae -> {
			dialog.close();
			consumer.accept(ButtonType.YES);
		});
		
		// YES button, calls consumer and closes dialog
		Button no = new LiFXButton("No");
		no.setOnAction(ae -> {
			dialog.close();
			consumer.accept(ButtonType.NO);
		});
		
		// Adds buttons and text to dialog box
		LiFXHBox options = new LiFXHBox(Pos.CENTER, yes, no);
		dialog.setContent(new LiFXVBox(Pos.CENTER, warning, options));
		dialog.show(FXApplication.INSTANCE);
	}
	
	/**
	 * Base method for showing an error dialog. This is called
	 * by other methods for specific instances
	 * @param dialog The dialog box to be shown.
	 * @param handler The handler to fire when the OK button is pressed
	 * @param messages Messages to be presented to the user (each
	 * shown on a separate line)
	 */
	private static void showErrorDialog(JFXDialog dialog, EventHandler<ActionEvent> handler, String...messages){
		dialog.setDialogContainer(FXApplication.INSTANCE);
		LiFXVBox box = new LiFXVBox(Pos.CENTER);
		for(String message : messages){
			LiFXText warning = new LiFXText(message);
			warning.overrideFill(RGBColour.RED);
			box.getChildren().add(warning);
		}
		
		Button ok = new LiFXButton("OK");
		ok.setOnAction(handler);
		box.getChildren().add(ok);
		
		dialog.setContent(box);
		dialog.show(FXApplication.INSTANCE);
	}
	
	/**
	 * Shows a very basic error dialog with an 'OK' button to close.
	 * @param message The message to show.
	 */
	public static void showErrorDialog(String message){
		JFXDialog dialog = new JFXDialog();
		showErrorDialog(dialog, ae -> dialog.close(), message);
	}
	
	/**
	 * Shows a basic error dialog with a critical error warning. 
	 * Shuts the application down on close.
	 * @param message The message to show.
	 */
	public static void showCriticalErrorDialog(String message){
		JFXDialog dialog = new JFXDialog();
		showErrorDialog(dialog, ae -> System.exit(-1), SHUTDOWN_MESSAGE, message);
	}
	
	/**
	 * Shows a very basic error dialog with an 'OK' button to close.
	 * @param e Shows the error message from this exception.
	 */
	public static void showErrorDialog(Exception e){
		showErrorDialog(e.getClass().getSimpleName() + ": " + e.getMessage());
	}
	
	/**
	 * Shows a basic error dialog with a critical error warning. 
	 * Shuts the application down on close.
	 * @param e Shows the error message from this exception.
	 */
	public static void showCriticalErrorDialog(Exception e){
		showCriticalErrorDialog(e.getClass().getSimpleName() + ": " + e.getMessage());
	}
	
	/**
	 * Shows a new Stage for modifying the current theme.
	 * @see ThemeChanger
	 */
	public static void showThemeDialog(){
		new ThemeChanger().show();
	}

}
