
public class Board {
	
	public static final int WIDTH = 3;
	public static final int HEIGHT = 3;
	private Tile[][] tiles = new Tile[WIDTH][HEIGHT];

	public Board()
	{
		// create a new board of specified width and height
		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				tiles[i][j] = new Tile();
	}

	public Tile getTile(int x, int y)
	{		
		// check bounds
		if (x < 0 || x >= WIDTH || y < 0 || y >= HEIGHT)
			return null;
		
		return tiles[x][y];
	}

	public boolean hasFreeTile()
	{
		for (int i = 0; i < WIDTH; i++)
			for (int j = 0; j < HEIGHT; j++)
				if (tiles[i][j].getOwner() == null)
					return true;
		
		return false;
	}
	
	//   A B C
	// A * * *
	// B * * *
	// C * * *
	public void print()
	{
		System.out.println("  A B C");
		for (int i = 0; i < HEIGHT; i++)
		{
			System.out.print((char)((byte)'A' + i));
			for (int j = 0; j < WIDTH; j++)
			{
				Player p = tiles[j][i].getOwner();
				if (p != null) System.out.print(" " + p.getIcon());
				else  System.out.print(" *");
			}
			System.out.println();
		}
	}
}

