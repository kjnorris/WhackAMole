import java.util.*;
	
public class WhackAMole {
	
	public int score, molesLeft, attemptsLeft;
	public char [][] moleGrid;
	
	WhackAMole (int numAttempts, int gridDimension) {
		int numMoles = 10;
		int x, y;
		Random rand = new Random();
		
		this.attemptsLeft = numAttempts;
		this.moleGrid = new char[gridDimension][gridDimension];
		this.score = 0;
		this.molesLeft = 10;
		
		for (int i = 0; i < moleGrid.length; i++) {
			for (int j = 0; j < moleGrid[i].length; j++) {
				moleGrid[i][j] = '*';
			}
		}
		
		while (numMoles > 0) {
			x = rand.nextInt(this.moleGrid.length);
			y = rand.nextInt(this.moleGrid[x].length);
			if (place(x, y)) {
				numMoles--;
			}
		}
	}
	
	public boolean place (int x, int y) {
		boolean retValue;
		if (this.moleGrid[x][y] != 'M') {
			this.moleGrid[x][y] = 'M';
			retValue = true;
		} else {
			retValue = false;
		}
		return retValue;
	}
	
	public void printGrid() {
		for (int i = 0; i < this.moleGrid.length; i++) {
			for (int j = 0; j < this.moleGrid[i].length; j++) {
				System.out.print(this.moleGrid[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
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
	
	public void whack(int x, int y) {
		this.attemptsLeft--;
		
		if (this.moleGrid[x][y] == 'M') {
			this.moleGrid[x][y] = 'W';
			this.molesLeft--;
			this.score++;
		}
	}
	
	public static void main(String[] args) {
		
		final int GRID_SIZE = 10;
		final int WHACKS_ALLOWED = 50;
		
		Scanner sc = new Scanner(System.in);
		int whackX, whackY;
		String[] splitArray;
		WhackAMole wam = new WhackAMole(WHACKS_ALLOWED, GRID_SIZE);
		
		sc.useDelimiter("\\D\\s*");
		
		while (wam.attemptsLeft > 0 && wam.molesLeft > 0) {
			System.out.println("There are " + wam.molesLeft + " moles left to whack!");
			System.out.println("You have " + wam.attemptsLeft + " swings remaining.");
			System.out.print("Enter the coordinates of your next strike: ");
			
			splitArray = sc.nextLine().split("\\s*(,|\\s)\\s*");
			
			whackX = Integer.parseInt(splitArray[0]);
			whackY = Integer.parseInt(splitArray[1]);

			if (whackX == -1 && whackY == -1) {
				wam.attemptsLeft = 0;
			} else if (whackX >= 0 && whackX <= 9 && whackY >= 0 && whackY <=9){
				wam.whack(whackX,  whackY);
				wam.printGridToUser();
			} else {
				System.out.println("Please enter coordinates between 0 and 9");
			}
		}

		System.out.println("\n");
		System.out.println("GAME OVER");
		wam.printGrid();
		System.out.println("Your final score was: " + wam.score);
	}


}
