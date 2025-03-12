import javax.swing.JButton;

public class Button extends JButton {
	boolean isHero = false;

	int x, y;

	public Button(String text) {
		super(text);
		initialize();
	}

	public Button(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		initialize();
	}

	public Button() {
		super();
		initialize();
	}

	private void initialize() {
	}
}