package edu.luc.cs474.rpc.tictactoe;
import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import edu.luc.cs474.rpc.tictactoe.Board;
import edu.luc.cs474.rpc.tictactoe.Player;
import edu.luc.cs474.rpc.tictactoe.Tile;

/**
 * TODO:
 * @author Team Rock Paper Scissors
 *
 */
public class UnitTests {

	/**
	 * TODO:
	 */
	private static Board board;
	
	/**
	 * TODO:
	 */
	private static Player player1;
	
	/**
	 * TODO:
	 */
	private static Player player2;
	
	// this is called before each test
	/**
	 * TODO:
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception
	{
		// assumes these are getting created properly unless one of their tests fail below
		board = new Board();
		player1 = new Player(board, "Player 1");
		player2 = new Player(board, "Player 2");
	}
	
	/**
	 * TODO:
	 * @throws Exception
	 */
    @Test
    public void tileTest() throws Exception
    {
    	// owner should be null upon creation
    	Tile tile = new Tile();
    	assertNull(tile.getOwner());
    	
    	// check that owner gets properly set and retrieved
    	assertTrue(tile.setOwner(player1));
    	assertEquals(tile.getOwner(), player1);
    	
    	// make sure it disallows null player to be set
    	assertFalse(tile.setOwner(null));
    	
    	// should still contain old player
    	assertEquals(tile.getOwner(), player1);
    	
    	// make sure it disallows another owner to be set
    	assertFalse(tile.setOwner(player2));
    	
    	// should still contain old player
    	assertEquals(tile.getOwner(), player1);
    	
    	// check that owner gets properly cleared
    	tile.clearOwner();
    	assertNull(tile.getOwner());
    }
    
    /**
     * TODO:
     * @throws Exception
     */
    @Test
    public void playerTest() throws Exception
    {
    	// verify that name was set properly in the set up
        assertTrue(player1.getName().equals("Player 1"));
    	
        // check the ability to change player names
        player1.setName("Player");
        assertTrue(player1.getName().equals("Player"));
        player1.setName("Player 1");
        assertTrue(player1.getName().equals("Player 1"));
        
        // icon should not be set yet
        assertEquals(player1.getIcon(), 0);
        
        // check icon getting and setting
        player1.setIcon('x');
        assertEquals(player1.getIcon(), 'x');
    }
    
    /**
     * TODO:
     * @throws Exception
     */
    @Test
    public void boardTest() throws Exception
    {
    	// verify the ability to retrieve tiles based on board indices
    	for (int i = 0; i < Board.WIDTH; i++)
    	{
    		for (int j = 0; j < Board.HEIGHT; j++)
    		{
    			assertNotNull(board.getTile(i, j));
    		}
    	}
    	
    	// verify the ability to retrieve tiles based on their user-friendly id
    	for (int i = 1; i <= 9; i++)
    	{
    		assertNotNull(board.getTile(i));
    	}
    	
    	// make sure specifying invalid coordinates return null tiles
    	assertNull(board.getTile(-1, 0));
    	assertNull(board.getTile(0, -1));
    	assertNull(board.getTile(3, 0));
    	assertNull(board.getTile(0, 3));
    	assertNull(board.getTile(3, 0));
    	assertNull(board.getTile(-1));
    	assertNull(board.getTile(0));
    	assertNull(board.getTile(10));

		// should have a free tile
    	assertTrue(board.hasFreeTile());
    	
    	// test move by coordinate
        assertNotSame(board.getTile(0, 0).getOwner(), player1);
        assertNotNull(player1.move(0, 0));
    	assertSame(board.getTile(0, 0).getOwner(), player1);
    	
    	// test move by id
        assertNotSame(board.getTile(2).getOwner(), player1);
        assertNotNull(player1.move(2));
    	assertSame(board.getTile(2).getOwner(), player1);
    	
    	// test duplicate moves
    	assertNull(player1.move(0, 0));
    	assertNull(player2.move(0, 0));
    	
		// should have plenty of free tiles still
    	assertTrue(board.hasFreeTile());
    	
    	// fill up the rest of the board, and we're not testing for win conditions
    	player1.move(3);
    	player1.move(4);
    	player1.move(5);
    	player1.move(6);
    	player1.move(7);
    	player1.move(8);
    	assertTrue(board.hasFreeTile());
    	player1.move(9);
    	
    	// all tiles should now be taken
    	assertFalse(board.hasFreeTile());
    }
    
    /**
     * TODO: 
     * @throws Exception
     */
    @Test
    public void gameWinTest() throws Exception
    {
    	
    	assertNull(board.getTile(1).getOwner());
    	
    	// todo: test win/tie conditions
    	
    	
    }
    
    /**
     * TODO: 
     * @throws Exception
     */
    @Test
    public void gameTieTest() throws Exception
    {
    	
    	assertNull(board.getTile(1).getOwner());
    	
    	// todo: test win/tie conditions
    	
    	
    }

    /**
     * TODO:
     * @throws Exception
     */
	@After
	public void tearDown() throws Exception
	{
		
	}
	
		
}
