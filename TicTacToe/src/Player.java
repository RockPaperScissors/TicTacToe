import java.util.Scanner;


public class Player {

	private Board board;	// need reference to the board so they know where to make their moves
	private String name;
	private char icon;
	
	public Player(Board board, String name)
	{
		this.board = board;
		this.name = name;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;	
	}
	
	public char getIcon()
	{
		return icon;
	}
	
	public void setIcon(char icon)
	{
		this.icon = icon;
	}
	
	// allows the current player to attempt to select a tile at the specified coords
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
			String coords = console.nextLine();
			
			// normalize coordinate string by stripping any non-alphabetical characters
			String coord = coords.toLowerCase().replaceAll("[^a-z]", "");
			
			// enforce XY coordinates
			if (coords.length() == 2)
			{
				// convert alphabetical coordinates to numerical
				x = coord.charAt(0) - 'a';
				y = coord.charAt(1) - 'a';
		
				// attempt to set the requested tile
				Tile t = board.getTile(x, y);
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
	
	// todo: have move(int x, y) and move(String xy) where the caller can avoid waiting for user input
	// useful for unit testing
	// then have move() call one of those to maximize reuse
	/*
	public Move move(int x, int y)
	{
		// attempt to set the requested tile
		Tile t = board.getTile(x, y);
		if (t != null) t.setOwner(this);
		else return null;
		return new Move(this, x, y);
	}
	*/
	
	
}
