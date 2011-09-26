
public class Tile {

	private Player owner;
	
	public Player getOwner()
	{
		return owner;
	}
	
	public boolean setOwner(Player p)
	{
		// disallow null owner, use clearOwner() instead to better communicate your intentions since setOwner implies there should be an owner afterwards
		if (p == null) return false;
		
		// fail if the tile already has an owner
		if (owner != null) return false;
		
		// set the owner
		owner = p;
		
		return true;		
	}
	
	public void clearOwner()
	{
		owner = null;	
	}
}
