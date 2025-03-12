import java.awt.GridLayout;
import javax.swing.*;

public final class Game {
	int width, height;

	JFrame frame;
	ImageIcon heroIcon, lockedIcon;
	Button[][] buttons;

	public Game(int width, int height) {
		this.width = width;
		this.height = height;

		this.heroIcon = new ImageIcon("./assets/hero.png");
		this.lockedIcon = new ImageIcon("./assets/locked.png");
	}

	public void Start() {
		this.frame = new JFrame("Game");
		this.frame.setLayout(new GridLayout(this.height, this.width));
		this.buttons = new Button[this.height][this.width];

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

				buttons[i][j] = button;
				this.frame.add(this.buttons[i][j]);
			}
		}

		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.pack();
		this.frame.setVisible(true);

		this.SetHeroPosition(2, 2);
	}

	public void ClearMatrix() {
		for (int i = 0; i < this.height; i++) {
			for (int j = 0; j < this.width; j++) {
				this.buttons[i][j].setIcon(this.lockedIcon);
				this.buttons[i][j].setEnabled(false);
			}
		}
	}

	public void SetHeroPosition(int x, int y) {
		this.ClearMatrix();

		this.buttons[x][y].setIcon(this.heroIcon);
		this.buttons[x][y].setEnabled(true);

		for (int i : new int[]{y-1, y, y+1}) {
			if (i < 0 || i >= this.height) continue;

			for (int j : new int[]{x-1, x, x+1}) {
				if (j < 0 || j >= this.width) continue;

				this.buttons[j][i].setEnabled(true);
			}
		}

	}
}