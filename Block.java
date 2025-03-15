import javax.swing.JButton;

public class Block extends JButton {
	public Entity type;
	public final int x, y; // position

	// constructors
	public Block(final int x, final int y) {
		super();
		this.x = x;
		this.y = y;
		initialize();
	}

	// methods
	private void initialize() {
		this.setEnabled(false);
	}

	public void SetType(Entity n_type) {
		type = n_type;
		System.out.println(n_type);
	}
}