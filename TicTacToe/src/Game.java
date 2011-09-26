import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class Game {

	//Statistics stats;
	
	// maintain a stack of player moves
	Stack<Move> moves = new Stack<Move>();
	
	Board board = new Board(Settings.BOARD_WIDTH, Settings.BOARD_HEIGHT);
	
	Player player1;
	Player player2;
	Player current;	// store reference to whoever's turn it currently is
	
	public Game()
	{
		Scanner console = new Scanner(System.in);
		
		// player assignment
		// TODO: check for empty/invalid names and continue to re-prompt for new name if necessary
		System.out.print("Player 1 please enter your name: ");
		player1 = new Player(board, console.nextLine() + " (P1)");
		System.out.print("Player 2 please enter your name: ");
		player2 = new Player(board, console.nextLine() + " (P2)");
		
		// randomly assign who goes first
		current = new Random().nextBoolean() ? player1 : player2;
		System.out.println(current.getName() + " has been chosen to go first.");
		Player second = current == player1 ? player2 : player1;
		
		// let the first player choose their tile
		System.out.print(current.getName() + ", please choose 'X' or 'O' as your preferred tile icon: ");
		if (console.next().equalsIgnoreCase("x"))	// TODO: fix so that if first player chooses something other than 'x' or 'o' it doesn't automatically give them 'o'
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
		
		// main game loop, alternate turns selecting tiles
		Player winner = null;
		while (true)
		{
			// print the current board layout
			board.print();
			
			while (board.hasFreeTile())
			{
				// add move to list of moves in game class
				moves.push(current.move());
				
				// print the current board layout
				board.print();
				
				// check for winner
				winner = winner();
				if (winner != null) break;
				
				// switch turns
				current = current == player1 ? player2 : player1;
			}
			
			// inform player the game has ended with the appropriate response of how it ended
			if (winner == null) winner = winner();
			if (winner == null)
			{
				System.out.println("This game ended in a tie!");
			}
			else
			{
				System.out.println(winner.getName() + " won!");
			}
			
			// offer them to play again
			System.out.println("Play again? [y/n]: ");
			if (!console.nextLine().equalsIgnoreCase("y")) break;
			
			// TODO: they opted to play again, reset board, moves stack, determine who goes first again (if the winner went first or its a tie, switch who goes first)
			
		}
	}
	
	public Player winner()
	{
		int width = board.getWidth();
		int height = board.getHeight();
		
		// TODO: check for winning player and return a reference if one is available
		
		return null;
	}

	public void dispose()
	{
		// todo: will probably have some cleaning up to do related to the statistics class
		
	}
	
}
