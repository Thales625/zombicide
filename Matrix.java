import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Matrix {
	private Block[][] mat;
	public final int[] dimension = new int[2];

	public Matrix(final int rows, final int cols) {
		this.dimension[0] = cols;
		this.dimension[1] = rows;

		this.mat = new Block[cols][rows];
	}

	public Matrix(final String path) { // matrix from .txt file
		try {
			ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(path)));

			String[] raw_dimension = lines.get(0).split(" ");

			this.dimension[0] = Integer.parseInt(raw_dimension[0]);
			this.dimension[1] = Integer.parseInt(raw_dimension[1]);

			this.mat = new Block[this.dimension[0]][this.dimension[1]];

			for (int j = 1; j < lines.size(); j++) {
				final String line = lines.get(j);
				final String[] raw_row = line.split(" ");

				for (int i = 0; i < raw_row.length; i++) {
					this.mat[j-1][i] = new Block(j-1, i);

					Entity type = Entity.None;

					switch (raw_row[i]) {
						case "-" -> { // none
							type = Entity.None;
						}
						case "W" -> { // wall
							type = Entity.Wall;
						}
						case "B" -> { // chest
							type = Entity.Chest;
						}
						case "Z" -> { // zombie
							type = Entity.Zombie;
						}
						case "ZR" -> { // crawling zombie
							type = Entity.CrawlingZombie;
						}
						case "ZC" -> { // zombie runner
							type = Entity.ZombieRunner;
						}
						case "ZG" -> { // giant zombie
							type = Entity.GiantZombie;
						}

						case "P" -> { // player
							// ignore
						}
						default -> throw new AssertionError();
					}

					this.mat[j-1][i].SetType(type);
					// this.mat[j-1][i].SetType(raw_row[i]);
				}
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler arquivo: " + e.getMessage());
		}
	}

	public Block Get(final int x, final int y) {
		return this.mat[x][y];
	}
}