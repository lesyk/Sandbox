/**
 * @author lesyk
 *
 *Given a currency with denominations of 100, 50, 20, 10, 5 and 1 write a method MakeChange that takes 
 *an integer amount representing the total change to make and return an integer representing the smallest 
 *possible number of bills to return.  For example a call of MakeChange(135) would result in a return 
 *value of 4 (1 one hundred bill, 1 twenty bill, 1 ten bill and 1 five bill). As part of a final solution 
 *please provide unit tests done as well as any test cases ran.
 */

import java.util.Scanner;

public class ChangingMachine {
	
	/*
	 * @param amount is a number for which change needed to be calculated
	 * @return billsNumber is a minimum number of bills
	 * 
	 * Also, I thought to implement data class with property amount and array int[6] denominations, where 0s positions is hundreds,...
	 * in a way that after line 39 I could add transation.denomination[i] = multiplier, but it is not specified by the task, and could be considered as over-engineering
	 */
	public static int makeChange(int amount){
		if (amount < 0 || amount == 0) return 0;
		
		/* array of all denominations */
		/* array from bigger to smaller number, since we calculate smallest possible number (not biggest possible) */
		int[] denominations = {100,50,20,10,5,1};
		
		int billsNumber = 0, multiplier = 0;
		int remainingAmount = amount;
		
		//goes through each domination
		for(int i : denominations){
		// if remainingAmount equals to 44 but i is 50, there is no point to go further since multiplier will be 0
			// this will avoid empty cycle
			if (remainingAmount >= i) {
				// calculating multiplier
				multiplier = remainingAmount / i;
				// mod division
				remainingAmount = remainingAmount % i;
				billsNumber += multiplier;
			}
		}
		
		return billsNumber;
	}

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.printf("A: Do you have something?\n"
						+ "B: Yeah. How much do you have?\n"
						+ "A: ");
		while (!in.hasNextInt()) {
			//get non integer entry
			in.next();
			System.out.printf("B: Dude, ints only\n"
							+ "A: ");
		}
		int amount = in.nextInt();
		in.close();
		int change = makeChange(amount);
		System.out.printf("B: The smallest possible number of bills is: %d. So, I think we can find ...\n", change);
	}
}