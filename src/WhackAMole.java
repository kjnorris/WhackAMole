import java.util.*;
	
public class WhackAMole {

	// Set initial values for instance variables
	int score = 0; // Player's score - increments for each mole whacked
	int molesLeft = 0; // Moles unwhacked
	int attemptsLeft; // Number of whacks until the end of the game
	char [][] moleGrid; // Character array of all moles - whacked or not
	
	/* Constructor starts game 
	 * Accepts number of attempts and grid size arguments
	 */
	WhackAMole (int numAttempts, int gridDimension) {

		this.attemptsLeft = numAttempts; // Sets maximum number of whacks
		this.moleGrid = new char[gridDimension][gridDimension]; // Allocates blank grid
		this.score = 0; // Resets score to start game
		
		// Initialize grid with a blank in each space represneted by *
		for (int i = 0; i < moleGrid.length; i++) {
			for (int j = 0; j < moleGrid[i].length; j++) {
				moleGrid[i][j] = '*';
			}
		}
	}
	
	/* place method attempts to place a mole at a given location
	 * accepts an (x, y) coordinate
	 * If a mole is at the location, return false
	 * If there is no mole at the location:
	 *   - change location character to M
	 *   - increment molesLeft to reflect places mole
	 *   - return true
	 */
	public boolean place (int x, int y) {
		if (this.moleGrid[x][y] != 'M') {
			this.moleGrid[x][y] = 'M';
			this.molesLeft++;
			return true;
		} else {
			return false;
		}
	}
	
	/* whack method determines result of player's attempt to whack-a-mole
	 * Accepts the (x, y) coordinate where the player hits
	 * If there is a mole at the location:
	 *   - mark the location as W to represent a whacked mole
	 *   - decrement molesLeft
	 *   - increment score
	 *   - decrement attemptsLeft
	 * If there is no mole at the location:
	 *   - decrement attemptsLeft
	 */
	public void whack(int x, int y) {
		this.attemptsLeft--;
		
		if (this.moleGrid[x][y] == 'M') {
			this.moleGrid[x][y] = 'W';
			this.molesLeft--;
			this.score++;
		}
	}
	
	/* printGridToUser method displays masked grid
	 * Grid displays all moles whacked represented by W
	 * All other locations shown as blank represented by *
	 */
	public void printGridToUser() {
		for (int i = 0; i < this.moleGrid.length; i++) {
			for (int j = 0; j < this.moleGrid[i].length; j++) {
				if(this.moleGrid[i][j] == 'W') {
					System.out.print(this.moleGrid[i][j] + " ");
				} else {
					System.out.print("* ");
				}
			}
			System.out.print("\n");
		}
	}
	
	/* printGrid method displays complete grid
	 * Grid displays all unwhacked moles rep[resented by M
	 * Grid displays all moles whacked represented by W
	 * All other locations shown as blank represented by *
	 */
	public void printGrid() {
		for (int i = 0; i < this.moleGrid.length; i++) {
			for (int j = 0; j < this.moleGrid[i].length; j++) {
				System.out.print(this.moleGrid[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	/* main method
	 * Let's play a game of whack-a-mole
	 */
	public static void main(String[] args) {

		// Set temporary variables
		int x, y; // (x, y) location pairs used to place moles and swing
		int numMoles = 10; // Sets initial number of moles placed
		String[] splitArray; // Necessary to hanldle scanned input
		
		Random rand = new Random(); // Initialize random number generatos to plae moles
		Scanner sc = new Scanner(System.in); // Initialize Scanner to accept input
		
		/* Start a WhackAMole game
		 * Set maximum number of swings to 50
		 * Create a 10x10 board (100 squares)
		 */
		WhackAMole wam = new WhackAMole(50, 10); 
		
		/* Place moles randomly on game board
		 * Place numMoles moles on the board, decrement for each mole placed
		 * Generate random x based on number of rows
		 * Generate random y based on number of columns
		 * If place method succeeds, decrement numMoles.
		 * If place method fails, do nothing and try again.
		 */
		while (numMoles > 0) {
			x = rand.nextInt(wam.moleGrid.length);
			y = rand.nextInt(wam.moleGrid[x].length);
			if (wam.place(x, y)) {
				numMoles--;
			}
		}

		// Set delimiter to handle possible , in input
		sc.useDelimiter("\\D\\s*");
		
		/* Player swings at the moles trying to whack them all
		 * End condition is when all moles are whacked or all swings have been taken
		 */
		while (wam.attemptsLeft > 0 && wam.molesLeft > 0) {
			// Print current state of the game and request coordinate input
			System.out.println("There are " + wam.molesLeft + " moles left to whack!");
			System.out.println("You have " + wam.attemptsLeft + " swings remaining.");
			System.out.print("Enter the coordinates of your next strike: ");
			
			// Read the line and split it on a comma or a space
			splitArray = sc.nextLine().split("\\s*(,|\\s)\\s*");
			
			// Set (x, y) coordinates from input
			x = Integer.parseInt(splitArray[0]);
			y = Integer.parseInt(splitArray[1]);

			if (x == -1 && y == -1) { // Player enters (-1, -1) to resign
				wam.attemptsLeft = 0; // If resign, zero attemptsLeft to break loop
			} else if (x >= 0 && x <= 9 && y >= 0 && y <=9){ // Check for valid coordinates
				wam.whack(x,  y); // Swing at those coordinates!
				wam.printGridToUser(); // Show the updated masked grid
			} else { // Player did not enter valid coordinates, try again
				System.out.println("Please enter coordinates between 0 and 9");
			}
		}
		
		// Close input Scanner
		sc.close();

		// Print final score and show the complete, unmasked grid
		System.out.println("\n");
		System.out.println("GAME OVER");
		wam.printGrid();
		System.out.println("Your final score was: " + wam.score);
	}


}
