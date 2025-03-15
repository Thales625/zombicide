import java.awt.GridLayout;
import javax.swing.*;

public final class Game {
	private final Matrix matrix;
	private final int width, height;
	private JFrame frame;

	public Game(final String name, final String matrix_path) {
		this.matrix = new Matrix(matrix_path);
		this.frame = new JFrame(name);

		this.height = this.matrix.dimension[0];
		this.width = this.matrix.dimension[1];
	}

	public void Start() {
		this.frame.setLayout(new GridLayout(this.matrix.dimension[0], this.matrix.dimension[1]));

		// construct matrix
		for (int j = 0; j < this.height; j++) {
			for (int i = 0; i < this.width; i++) {
				this.frame.add(this.matrix.Get(i, j));
			}
		}

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);

		this.SetHeroPosition(2, 2);
	}

	private void ClearMatrix() {
		for (int j = 0; j < this.height; j++) {
			for (int i = 0; i < this.width; i++) {
				// this.matrix.Get(i, j).setIcon(this.lockedIcon);
				this.matrix.Get(i, j).setEnabled(false);
			}
		}
	}

	private void SetHeroPosition(final int x, final int y) {
		this.ClearMatrix();

		// this.matrix.Get(x, y).setIcon(this.heroIcon);
		this.matrix.Get(x, y).setEnabled(true);

		for (int j : new int[]{y-1, y, y+1}) {
			if (j < 0 || j >= this.height) continue;

			for (int i : new int[]{x-1, x, x+1}) {
				if (i < 0 || i >= this.width) continue;

				this.matrix.Get(i, j).setEnabled(true);
			}
		}
	}
}