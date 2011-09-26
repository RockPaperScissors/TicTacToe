
public class Board {
	
	private int width;
	private int height;
	private Tile[][] tiles;

	public Board(int width, int height)
	{
		// check for invalid board dimensions
		if (width < Settings.MIN_BOARD_WIDTH || width > Settings.MAX_BOARD_WIDTH ||
				height < Settings.MIN_BOARD_HEIGHT || width > Settings.MAX_BOARD_HEIGHT)
		{
			throw new IllegalArgumentException("Invalid Board Dimensions");
		}
		
		// create a new board of specified width and height
		this.width = width;
		this.height = height;
		tiles = new Tile[width][height];
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
				tiles[i][j] = new Tile();
	}

	public Tile getTile(int x, int y)
	{		
		// check bounds
		if (x < 0 || x >= width || y < 0 || y >= height)
			return null;
		
		return tiles[x][y];
	}
	
	public int getWidth()
	{
		return width;
	}
	
	public int getHeight()
	{
		return height;
	}
	
	public boolean hasFreeTile()
	{
		for (int i = 0; i < width; i++)
			for (int j = 0; j < height; j++)
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
		for (int i = 0; i < height; i++)
		{
			System.out.print((char)((byte)'A' + i));
			for (int j = 0; j < width; j++)
			{
				Player p = tiles[j][i].getOwner();
				if (p != null) System.out.print(" " + p.getIcon());
				else  System.out.print(" *");
			}
			System.out.println();
		}
	}
}

