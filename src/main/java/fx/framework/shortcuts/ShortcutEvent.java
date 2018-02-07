package fx.framework.shortcuts;

@FunctionalInterface
public interface ShortcutEvent {

	public void handle(Shortcut shortcut);
	
}
