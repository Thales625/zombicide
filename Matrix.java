import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Matrix<T> {
	private T[][] mat;
	private int[] dimension;

	public Matrix(int rows, int cols) {
		this.mat = (T[][]) new Object[rows][cols];
		this.dimension = new int[] {rows, cols};
	}

	public Matrix(String path) {
		try {
			ArrayList<String> lines = new ArrayList<>(Files.readAllLines(Paths.get(path)));

			String[] dim = lines.get(0).split(" ");

			this.dimension = new int[2];
			this.dimension[0] = Integer.parseInt(dim[0]);
			this.dimension[1] = Integer.parseInt(dim[1]);

			this.mat = (T[][]) new Object[dimension[0]][dimension[1]];

			for (int i = 1; i < lines.size(); i++) {
				String line = lines.get(i);

				String[] raw_row = line.split(" ");
				T[] row = (T[]) new Object[raw_row.length];
				for (int j = 0; j < raw_row.length; j++) {
					row[j] = (T) raw_row[j];
				}

				System.arraycopy(row, 0, this.mat[i-1], 0, row.length);
			}
		} catch (IOException e) {
			System.err.println("Erro ao ler arquivo: " + e.getMessage());
		}
	}

	public void Set(final int x, final int y, T value) {
		this.mat[x][y] = value;
	}

	public T Get(final int x, final int y) {
		return this.mat[x][y];
	}

	@Override
	public String toString() {
		String text = "";
		for (T[] row : this.mat) {
			for (int j = 0; j < this.mat[0].length; j++) {
				text += row[j] + " ";
			}
			text += "\n";
		}

		return text;
	}
}