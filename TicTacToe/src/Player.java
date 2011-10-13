import java.util.Scanner;

/**
 * TODO: 
 * @author Team Rock Paper Scissors
 *
 */
public class Player {

	/**
	 * TODO: 
	 */
	private Board board;	// need reference to the board so they know where to make their moves
	
	/**
	 * TODO: 
	 */
	private String name;
	
	/**
	 * TODO: 
	 */
	private char icon;
	
	/**
	 * TODO:
	 * @param board
	 * @param name
	 */
	public Player(Board board, String name)
	{
		this.board = board;
		this.name = name;
	}
	/**
	 * TODO: 
	 * @return
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * TODO:
	 * @param name
	 */
	public void setName(String name)
	{
		this.name = name;	
	}
	
	/**
	 * TODO:
	 * @return
	 */
	public char getIcon()
	{
		return icon;
	}
	
	/**
	 * TODO:
	 * @param icon
	 */
	public void setIcon(char icon)
	{
		this.icon = icon;
	}
	
	// allows the current player to attempt to select a tile at the specified coords
	/**
	 * TODO: 
	 */
	public Move move()
	{		
		int x = 0;
		int y = 0;
		boolean success = false;
		while (!success)
		{
			// prompt for current player to make their move
			System.out.print(name + " please make your move: ");
			Scanner console = new Scanner(System.in);
			int tileId = 0;
			try
			{
				tileId = console.nextInt();
			}
			catch (Exception ex) { }
			
			// enforce XY coordinates
			if (tileId > 0 && tileId < 10)
			{
				// attempt to set the requested tile
				Tile t = board.getTile(tileId);
				if (t != null)
				{
					success = t.setOwner(this);
				}
			}
			
			if (!success)
			{
				System.out.println("Invalid move, please try again.");
			}
		}

		return new Move(this, x, y);	// return their move information as a confirmation (and for stats)
	}


	/**
	 * TODO: 
	 */
	public Move move(int x, int y)
	{
		// attempt to set the requested tile
		Tile t = board.getTile(x, y);
		if (t != null) t.setOwner(this);
		else return null;
		return new Move(this, x, y);
	}
		
	// tile id
	/**
	 * TODO:
	 */
	public Move move(int id)
	{
		Tile t = board.getTile(id);
		if (t != null)
		{
			boolean success = t.setOwner(this);
			if (success) return new Move(this, (id - 1) % Board.WIDTH, (id - 1) % Board.HEIGHT);
			else return null;
		}
		else return null;
	}
	
}
