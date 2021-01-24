package meteorGame;

import java.util.Random;

//Creates a randomized sample size for the meteor and sets a position beyond the screen
public class RandomPosition {
	
	private int num_arr = 80;
	private int x_y = 2;
	private int[][] pos = new int[num_arr][x_y];
	
	private Random rand = new Random();
	
	//Builds a random multidimensional array of meteors between specified pixels beyond the screen
	public RandomPosition() {
		
		for(int i=0; i<num_arr;i++) {
			for(int x=0; x<x_y; x++) {
				if(x == 0) {
					pos[i][x] = rand.nextInt(5000) + 2000;
				}
				if(x == 1) {
					pos[i][x] = rand.nextInt(1400);
				}
			}
		}
	}
	
	//Used to export the random array
	public int[][] getArray() {
		return pos;
	}
}
