package edu.luc.cs474.rpc.tictactoe;

/**
 * 
 * @author Team Rock Paper Scissors
 *
 */
public class Tile
{
	/***
	 * TODO:
	 */
	private Player owner;
	

	/**
	 * TODO:
	 * @return
	 */
	public Player getOwner()
	{
		return owner;
	}
	
	/**
	 * TODO:
	 * @param player
	 * @return
	 */
	public boolean setOwner(Player player)
	{
		// disallow null owner, use clearOwner() instead to better communicate your intentions since setOwner implies there should be an owner afterwards
		if (player == null) return false;
		
		// fail if the tile already has an owner
		if (owner != null) return false;
		
		// set the owner
		owner = player;
		
		// success
		return true;		
	}
	
	// 60% of the time this works all the time
	/**
	 * TODO:
	 */
	public void clearOwner()
	{
		owner = null;	
	}
}
