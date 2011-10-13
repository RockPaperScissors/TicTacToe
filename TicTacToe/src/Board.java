
/**
 * Represents a tic-tac-toe game board.
 * @author Team Rock Paper Scissors
 */
public class Board
{
	/**
	 * The width of the board.
	 */
	public static final int WIDTH = 3;
	
	/**
	 * The height of the board.
	 */
	public static final int HEIGHT = 3;
	
	/**
	 * A 2-dimensional array of tiles belonging to the board.
	 */
	private Tile[][] tiles = new Tile[WIDTH][HEIGHT];

	/**
	 * Creates a standard tic-tac-toe board consisting of 3x3 tiles.
	 */
	public Board()
	{
		// create a new board of specified width and height
		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				tiles[i][j] = new Tile();
	}

	/**
	 * Attempts to retrieve a tile given zero-based column and row indices.
	 * @param columnIndex	the column index.
	 * @param rowIndex		the row index.
	 * @return				the tile if found, otherwise null.
	 */
	public Tile getTile(int columnIndex, int rowIndex)
	{		
		// check bounds
		if (columnIndex < 0 || columnIndex >= WIDTH || 
				rowIndex < 0 || rowIndex >= HEIGHT)
			return null;
		
		return tiles[columnIndex][rowIndex];
	}

	/**
	 * Attempts to retrieve a tile by its id.
	 * @param id	the id of the tile.
	 * @return		the tile if found, otherwise null.
	 */
	public Tile getTile(int id)
	{
		// check for invalid tile id
		if (id < 1 || id > 9) return null;
		
		// return the tile
		return tiles[(id - 1) % WIDTH][(id - 1) / HEIGHT];
	}

	/**
	 * Checks if the board has any free tiles left.
	 * @return	true if a free tile is found.
	 */
	public boolean hasFreeTile()
	{
		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				if (tiles[i][j].getOwner() == null)
					return true;
		
		return false;
	}
	
	/**
	 * Prints the current board to the console as numbered tiles using 
	 * the layout below, where X and O indicate player-selected tiles.
	 * 
	 * |-----------|
	 * | 1 | 2 | 3 |
	 * |---+---+---|
	 * | 4 | 5 | 6 |
	 * |---+---+---|
	 * | 7 | 8 | 9 |
	 * |-----------|
	 */
	public void print()
	{
		System.out.println(" |-----------|");
		for (int i = 0; i < HEIGHT; i++)
		{
			System.out.print(" | ");
			for (int j = 0; j < WIDTH; j++)
			{
				Player p = tiles[j][i].getOwner();
				if (p != null) System.out.print(p.getIcon());
				else System.out.print(i * 3 + j + 1);
				System.out.print(" | ");
			}
		
			if (i < HEIGHT - 1)
			{
				System.out.println("\n |---+---+---|");
			}
		}
		System.out.println("\n |-----------|");
	}
}