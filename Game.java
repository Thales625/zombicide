import java.awt.GridLayout;
import javax.swing.*;

public final class Game {
	int width, height;

	Matrix<Button> matrix;

	JFrame frame;
	ImageIcon heroIcon, lockedIcon, chestIcon;

	public Game(final int width, final int height) {
		this.width = width;
		this.height = height;

		this.matrix = new Matrix<>(width, height);

		this.heroIcon = new ImageIcon("./assets/hero.png");
		this.lockedIcon = new ImageIcon("./assets/locked.png");
		this.chestIcon = new ImageIcon("./assets/chest.png");
	}

	public void Start() {
		this.frame = new JFrame("Game");
		this.frame.setLayout(new GridLayout(this.height, this.width));

		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				final Button button = new Button(j, i);

				button.setEnabled(false);
				button.setIcon(this.lockedIcon);
				button.addActionListener(e -> {
					if (!button.isHero) {
						SetHeroPosition(button.y, button.x);
					}
				});

				// this.matrix[i][j] = button;
				this.matrix.Set(i, j, button);
				this.frame.add(this.matrix.Get(i, j));
				// this.frame.add(this.matrix[i][j]);
			}
		}

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);

		this.SetHeroPosition(2, 2);
	}

	private void ClearMatrix() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.matrix.Get(j, i).setIcon(this.lockedIcon);
				this.matrix.Get(j, i).setEnabled(false);
			}
		}
	}

	private void SetHeroPosition(final int x, final int y) {
		this.ClearMatrix();

		this.matrix.Get(x, y).setIcon(this.heroIcon);
		this.matrix.Get(x, y).setEnabled(true);

		for (int i : new int[]{y-1, y, y+1}) {
			if (i < 0 || i >= this.height) continue;

			for (int j : new int[]{x-1, x, x+1}) {
				if (j < 0 || j >= this.width) continue;

				this.matrix.Get(j, i).setEnabled(true);
			}
		}
	}
}