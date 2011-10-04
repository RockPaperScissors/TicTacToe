import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Game {
	// tracked by username, which is assumed to be unique
	//Statistics stats;
	
	// maintain a stack of player moves
	Stack<Move> moves = new Stack<Move>();
	
	Board board = new Board();
	
	Player player1;
	Player player2;
	Player current;	// store reference to whoever's turn it currently is
	
	public Game()
	{
		Scanner console = new Scanner(System.in);
		
		// player assignment
		// TODO: check for empty/invalid/long/short names and continue to re-prompt for new name if necessary
		System.out.print("Player 1 please enter your unique username: ");
		player1 = new Player(board, console.nextLine() + " (P1)");
		System.out.print("Player 2 please enter your unique username: ");
		player2 = new Player(board, console.nextLine() + " (P2)");
		
		// randomly assign who goes first and second
		Player first = current = new Random().nextBoolean() ? player1 : player2;
		Player second = current == player1 ? player2 : player1;
		System.out.println(current.getName() + " has been chosen to go first.");
		
		// let the first player choose their tile and give the second player what's left
		System.out.print(current.getName() + ", please choose 'X' or 'O' as your preferred tile icon: ");
		
		// TODO: fix so that if first player chooses something other than 'x' or 'o' it doesn't automatically give them 'o'
		// it should reprompt until valid selection is made		
		if (console.next().equalsIgnoreCase("x"))	
		{
			current.setIcon('X');
			second.setIcon('O');
		}
		else
		{
			current.setIcon('O');
			second.setIcon('X');
		}
		
		// let players know what tiles they have
		System.out.println(current.getName() + " has selected tile icon '" + current.getIcon() + "'");
		System.out.println(second.getName() + " has been automatically assigned tile icon '" + second.getIcon() + "'");
		
		
		Player winner = null;
		while (true)
		{
			// print the current board layout
			board.print();
			
			// main game loop, alternate turns selecting tiles
			while (board.hasFreeTile())
			{
				// add move to list of moves in game class
				moves.push(current.move());
				
				// print the current board layout
				board.print();
				
				// switch turns
				current = current == player1 ? player2 : player1;
				
				// check for winner
				winner = winner();
				if (winner != null) break;
			}
			
			// inform player the game has ended with the appropriate response of how it ended
			if (winner == null)
			{
				System.out.println("This game ended in a tie!");
			}
			else System.out.println(winner.getName() + " won!");
			
			// TODO: save statistics at the end as an entire? or after each move?
			// do we want to track abandoned game stats?
			// maybe store a serialized version of the entire game?
			
			// offer them to play again
			System.out.println("Play again? [y/n]: ");
			if (!console.nextLine().equalsIgnoreCase("y")) break;	// TODO: fix this, as it doesn't seem to wait for input
			
			// TODO: they opted to play again, reset board, moves stack, determine who goes first again (if the winner went first or its a tie, switch who goes first)
			board = new Board();
			
		}
	}
	
	public Player winner()
	{
		// horizontal scan
		for (int i = 0; i < Board.HEIGHT; i++)
		{
			if (board.getTile(0, i).getOwner() != null &&
					board.getTile(0, i).getOwner() == board.getTile(1, i).getOwner() && 
					board.getTile(1, i).getOwner() == board.getTile(2, i).getOwner())
				return board.getTile(i, 0).getOwner();
		}
		
		// vertical scan
		for (int i = 0; i < Board.WIDTH; i++)
		{
			if (board.getTile(i, 0).getOwner() != null &&
					board.getTile(i, 0).getOwner() == board.getTile(i, 1).getOwner() && 
					board.getTile(i, 1).getOwner() == board.getTile(i, 2).getOwner())
				return board.getTile(i, 0).getOwner();
		}
		
		// diagonal scan top left to bottom right
		if (board.getTile(0, 0).getOwner() != null && 
				board.getTile(0, 0).getOwner() == board.getTile(1, 1).getOwner() &&
				board.getTile(1, 1).getOwner() == board.getTile(2, 2).getOwner())
			return board.getTile(0, 0).getOwner();
		
		// diagonal scan bottom left to top right
		if (board.getTile(0, 2).getOwner() != null && 
				board.getTile(0, 2).getOwner() == board.getTile(1, 1).getOwner() &&
				board.getTile(1, 1).getOwner() == board.getTile(2, 0).getOwner())
			return board.getTile(0, 0).getOwner();	
		
		// no winner was found, the game is either unfinished, or it has ended in a draw
		return null;
	}

	public void dispose()
	{
		// todo: will probably have some cleaning up to do related to the statistics class
		
	}
	
}
