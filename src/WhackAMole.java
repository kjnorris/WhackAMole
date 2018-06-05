import java.util.*;
	
public class WhackAMole {
	
	int score = 0;
	int molesLeft = 0;
	int attemptsLeft;
	char [][] moleGrid;
	
	WhackAMole (int numAttempts, int gridDimension) {

		this.attemptsLeft = numAttempts;
		this.moleGrid = new char[gridDimension][gridDimension];
		this.score = 0;
		
		for (int i = 0; i < moleGrid.length; i++) {
			for (int j = 0; j < moleGrid[i].length; j++) {
				moleGrid[i][j] = '*';
			}
		}
	}
	
	public boolean place (int x, int y) {
		if (this.moleGrid[x][y] != 'M') {
			this.moleGrid[x][y] = 'M';
			this.molesLeft++;
			return true;
		} else {
			return false;
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
	
	public void printGrid() {
		for (int i = 0; i < this.moleGrid.length; i++) {
			for (int j = 0; j < this.moleGrid[i].length; j++) {
				System.out.print(this.moleGrid[i][j] + " ");
			}
			System.out.print("\n");
		}
	}
	
	public static void main(String[] args) {

		int x, y;
		int numMoles = 10;
		String[] splitArray;
		
		Random rand = new Random();
		Scanner sc = new Scanner(System.in);
		
		WhackAMole wam = new WhackAMole(50, 10);
		
		while (numMoles > 0) {
			x = rand.nextInt(wam.moleGrid.length);
			y = rand.nextInt(wam.moleGrid[x].length);
			if (wam.place(x, y)) {
				numMoles--;
			}
		}

		sc.useDelimiter("\\D\\s*");
		
		while (wam.attemptsLeft > 0 && wam.molesLeft > 0) {
			System.out.println("There are " + wam.molesLeft + " moles left to whack!");
			System.out.println("You have " + wam.attemptsLeft + " swings remaining.");
			System.out.print("Enter the coordinates of your next strike: ");
			
			splitArray = sc.nextLine().split("\\s*(,|\\s)\\s*");
			
			x = Integer.parseInt(splitArray[0]);
			y = Integer.parseInt(splitArray[1]);

			if (x == -1 && y == -1) {
				wam.attemptsLeft = 0;
			} else if (x >= 0 && x <= 9 && y >= 0 && y <=9){
				wam.whack(x,  y);
				wam.printGridToUser();
			} else {
				System.out.println("Please enter coordinates between 0 and 9");
			}
		}
		
		sc.close();

		System.out.println("\n");
		System.out.println("GAME OVER");
		wam.printGrid();
		System.out.println("Your final score was: " + wam.score);
	}


}
