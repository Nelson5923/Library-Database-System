package CSCI3170;
import java.util.Scanner;
import java.sql.*;

public class csci3170_g41{

	public static void main(String[] args) {
		int user_Input;
        	int[] exit; 
		exit = new int[10];
		while(exit[0] == 0){
		System.out.printf("-----Main menu-----\n"
			+ "What kinds of operation would you like to perform?\n"
			+ "1. Operations for administrator\n"
			+ "2. Operations library user\n"
			+ "3. Operations for librarian\n"
			+ "4. Exit this program\n"
			+ "Enter Your Choice: ");

        	Scanner input = new Scanner(System.in);
        	user_Input = input.nextInt();
        
        	if(user_Input == 1){
			exit[1] = 0;
			while(exit[1] == 0){
				System.out.printf("\n" + "-----Operations for administrator menu-----\n"
            				+ "What kinds of operation would you like to perform?\n"
					+ "1. Create all tables\n"
           				+ "2. Delete all tables\n"
            				+ "3. Load data\n"
            				+ "4. Show number of records in each table\n"
					+ "5. Return to the main menu\n"
					+ "Enter Your Chioces: ");

            				user_Input = input.nextInt();

				if(user_Input == 1){
					adminop.op1();
				}
				else if(user_Input == 2){
					adminop.op2();
				}
				else if(user_Input == 3){
					adminop.op3();
				}
				else if(user_Input == 4){
					adminop.op4();
				}
				else if(user_Input == 5){
					System.out.printf("\n");
					exit[1] = 1;
				}
				else{
					System.out.printf("[Error]: Wrong Command.\n");
				}
			}					
        	}
        	else if(user_Input == 2){
			exit[2] = 0;
			while(exit[2] == 0){
            			System.out.printf("\n-----Operations for library user menu-----\n"
					+ "What kinds of operation would you like to perform?\n"
					+ "1. Search for Books\n"
 					+ "2. Show checkout records of a user\n"
 					+ "3. Return to the main menu\n"
					+ "Enter Your Chioces: ");
            	
				user_Input = input.nextInt();
            
				if(user_Input == 1){
                			System.out.printf("Choose the Search criterion:\n"
						+ "1. call number\n"
						+ "2. title\n"		
						+ "3. author\n"		
						+ "Choose the search criterion: ");

					user_Input = input.nextInt();
					if(user_Input == 1){
							lbuop.op1_1();
					}
					else if(user_Input == 2){
							lbuop.op1_2();
					}
					else if(user_Input == 3){
							lbuop.op1_3();
					}
            			}
            			else if(user_Input == 2){
					lbuop.op2();
            			}
            			else if(user_Input == 3){
					System.out.printf("\n");
					exit[2] = 1;
        			}
				else{
					System.out.printf("[Error]: Wrong Command.\n");
				}
			}
		}
		else if(user_Input == 3){
			exit[3] = 0;
			while(exit[3] == 0){
            			System.out.printf("\n-----Operations for administrator menu-----\n"
					+ "What kinds of operation would you like to perform?\n"
                                	+ "1. Book Borrowing\n"
                                	+ "2. Book Returning\n"
					+ "3. List all un-returned book copies which are checked-out within a period\n"
					+ "4. Return to the main menu\n"
					+ "Enter Your Chioces: ");
            			user_Input = input.nextInt(); 
				if(user_Input == 1){
					lbop.op1();
					System.out.printf("\n");
				}
				else if(user_Input == 2){
					lbop.op2();
					System.out.printf("\n");
				}
				else if(user_Input == 3){
					lbop.op3();
					System.out.printf("\n");
				}
				else if(user_Input == 4){;
					System.out.printf("\n");
					exit[3] = 1;
				}
				else{
					System.out.printf("[Error]: Wrong Command.\n");
				}
			}		 
        	}
        	else if(user_Input == 4){
			exit[0] = 1;
        	}
		else{
			System.out.printf("[Error]: Wrong Command.\n\n");
		}
		}
	}
}
