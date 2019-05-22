package CSCI3170;
import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;


public class lbop { 

	/*
	CREATE TABLE checkout_record( 
  		user_id char(10), 
  		call_number char(8) not null,
  		copy_number integer,
  		checkout_date date,
		return_date date null,
		PRIMARY KEY(user_id, call_number, copy_number, checkout_date))
	*/

	/* DELETE FROM TABLE Delete all records from a table  
	 * CHECK Constraint doesn't work in Mysql
	 * SQL CHECK NULL Attribute USE IS NULL OR IS NOT NULL not == Null
	*/

	/* Debug
	1. INSERT user000001 S5583200 1
	2. INSERT user000002 S5583200 1
	3. INSERT user000003 S5583200 2
	*/

	/*
	INSERT INTO checkout_record VALUES('user000001','S5583200',1,'2017-09-15',null);
	INSERT INTO checkout_record VALUES('user000006','QA762011',1,'2016-03-26',null);
	INSERT INTO checkout_record VALUES('user000020','QA76D3L5',4,'2016-03-26',null);
	INSERT INTO checkout_record VALUES('user000008','D3184199',1,'2016-03-23',null);
	INSERT INTO checkout_record VALUES('user000015','QA76D3L5',2,'2016-03-20',null);
	INSERT INTO checkout_record VALUES('user000012','Q335L520',2,'2016-02-21',null);

	//Counter Example
	INSERT INTO checkout_record VALUES('user000004','QA76D3L5',9,'2016-01-23','2016-01-25');
	INSERT INTO checkout_record VALUES('user000009','Q335L520',7,'2017-10-15','2017-10-25');
	INSERT INTO checkout_record VALUES('user000015','QA76D3L5',8,'2016-03-20','2016-03-26'); 
	INSERT INTO checkout_record VALUES('user000004','QA76D3L5',3,'2016-01-24',null);
	INSERT INTO checkout_record VALUES('user000004','QA76D3L5',3,'2016-10-24',null);

	*/

	private static java.sql.Date getCurrentDate() {
    		java.util.Date today = new java.util.Date();
    		return new java.sql.Date(today.getTime());
	}
	
	/*
	public static void show_table() {
		System.out.printf("\n\n");
		Connection conn = null;
		Statement stm = null;
		ResultSet rs = null;
		SimpleDateFormat sdfdate = new SimpleDateFormat("dd/MM/yyyy");
		Date date;

      		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();
         		rs = stm.executeQuery("SELECT * FROM checkout_record");
			System.out.printf("|User Id|Call number|Copy number|Check out date"
				+ "|Return date|\n");
			while(rs.next()){
            			System.out.printf("|%s|%s|%d|%s|",
            			rs.getString(1),
            			rs.getString(2),
            			rs.getInt(3),
            			sdfdate.format(rs.getDate(4)));
				date = rs.getDate(5);
				if (date == null)
					System.out.printf("\n");
				else
					System.out.printf("%s|\n",sdfdate.format(rs.getDate(5))); 
			}
		}
		catch(SQLException e1){ 
			e1.printStackTrace();
			System.out.println(e1.toString());  
			System.exit(1);
      		}
		finally{
         		try {
            			rs.close();
            			stm.close();
            			conn.close();
         		} 
			catch (SQLException e2) {
				System.out.println(e2.toString()); 
				System.exit(1);
         		}
      		}

	}
	*/

	public static void op1() {
		Scanner input_op1 = new Scanner(System.in); //Can not share User input (Scanner)
		String user_id,call_num;
		int copy_num;

		System.out.printf("Enter The User ID: ");
		user_id = input_op1.next();
		System.out.printf("Enter The Call Number: ");
		call_num = input_op1.next();
		System.out.printf("Enter The Copy Number: ");
		copy_num = input_op1.nextInt();
		
		Connection conn = null;
		PreparedStatement p_stm = null;
		ResultSet rs = null;

		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
			/* To see if any people borrow the book. */
			p_stm = conn.prepareStatement("SELECT * FROM checkout_record WHERE " 
			+ "call_number IS NOT NULL AND copy_number IS NOT NULL AND return_date IS NULL"
			+ " AND call_number = ? AND copy_number = ?");
			p_stm.setString(1, call_num);
			p_stm.setInt(2, copy_num);	
			rs = p_stm.executeQuery();
			
			if(!rs.isBeforeFirst()){ /* If there are no people borrowed the book */
				p_stm = conn.prepareStatement("INSERT INTO checkout_record VALUES(?,?,?,?,?)");
				p_stm.setString(1, user_id);
				p_stm.setString(2, call_num);
				p_stm.setInt(3, copy_num);
				p_stm.setDate(4, getCurrentDate());
				p_stm.setNull(5, java.sql.Types.DATE);
				p_stm.execute();
				System.out.printf("Book checkout performed successfully!!!");
				//show_table();
			}
			else{
				System.out.printf("[Error]: The book is borrowed.");
			}

      		}
		catch(SQLException e1){ 
			//e1.printStackTrace();
			System.out.println(e1.toString());  
			System.exit(1);
      		}
		finally{
         		try {
            			rs.close();
            			p_stm.close();
            			conn.close();
         		} 
			catch (SQLException e2) {
				System.out.println(e2.toString()); 
				System.exit(1);
         		}
      		}
       }

	public static void op2(){
	
		Scanner input_op2 = new Scanner(System.in);
		String user_id,call_num;
		int copy_num;

		System.out.printf("Enter The User ID: ");
		user_id = input_op2.next();
		System.out.printf("Enter The Call Number: ");
		call_num = input_op2.next();
		System.out.printf("Enter The Copy Number: ");
		copy_num = input_op2.nextInt();
		
		Connection conn = null;
		PreparedStatement p_stm = null;
		ResultSet rs = null;

		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
			/* To see if any people borrow the book. */
			p_stm = conn.prepareStatement("SELECT * FROM checkout_record WHERE " 
			+ "call_number IS NOT NULL AND copy_number IS NOT NULL AND return_date IS NULL"
			+ " AND call_number = ? AND copy_number = ?"
			+ " AND user_id = ?");
			p_stm.setString(1, call_num);
			p_stm.setInt(2, copy_num);
			p_stm.setString(3, user_id);	
			rs = p_stm.executeQuery();
			
			if(rs.isBeforeFirst()){ /* If there are people borrowed the book */
				p_stm = conn.prepareStatement("UPDATE checkout_record SET return_date = ? WHERE "
				+ "call_number IS NOT NULL AND copy_number IS NOT NULL AND return_date IS NULL"
				+ " AND call_number = ? AND copy_number = ?"
				+ " AND user_id = ?");
				p_stm.setDate(1, getCurrentDate());
				p_stm.setString(2, call_num);
				p_stm.setInt(3, copy_num);
				p_stm.setString(4, user_id);
				p_stm.execute();
				System.out.printf("Book returning performed successfully!!!");
				//show_table();
			}
			else{
				System.out.printf("[Error]: An matching checkout is not found. "
				+ "The book have not been borrowed yet!");
			}

      		}
		catch(SQLException e1){ 
			//e1.printStackTrace();
			System.out.println(e1.toString());  
			System.exit(1);
      		}
		finally{
         		try {
            			rs.close();
            			p_stm.close();
            			conn.close();
         		} 
			catch (SQLException e2) {
				System.out.println(e2.toString()); 
				System.exit(1);
         		}
      		}
    	} 


	public static void op3(){

		Scanner input_op3 = new Scanner(System.in);
		SimpleDateFormat sdfdate = new SimpleDateFormat("dd/MM/yyyy");

		String start_day, end_day;

		System.out.println("Type in the starting date [dd/mm/YYYY]: ");
		start_day = input_op3.next();
		
		System.out.println("Type in the ending date [dd/mm/YYYY]: ");
		end_day = input_op3.next();

		/* Set up the Connection */
		Connection conn = null;
		PreparedStatement p_stm = null;
		ResultSet rs = null;


      		try{

			/* Change the Date Format */
			Date parsedDate1 = sdfdate.parse(start_day);
			Date parsedDate2 = sdfdate.parse(end_day);
			java.sql.Date sqlDate1 = new java.sql.Date(parsedDate1.getTime());
			java.sql.Date sqlDate2 = new java.sql.Date(parsedDate2.getTime());

			/* Execute the Query */
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		p_stm = conn.prepareStatement("SELECT DISTINCT user_id, call_number, copy_number, "
			+ "checkout_date FROM checkout_record "
			+ "WHERE checkout_date BETWEEN ? AND ? AND return_date IS NULL "
			+ "ORDER BY checkout_date DESC");
			p_stm.setDate(1, sqlDate1);
			p_stm.setDate(2, sqlDate2);	
			rs = p_stm.executeQuery();
			
			/* Get the Result */
			System.out.printf("|User Id|Call number|Copy number|Check out date|\n");
			while(rs.next()){
            			System.out.printf("|%s|%s|%d|%s|\n",
            			rs.getString(1),
            			rs.getString(2),
            			rs.getInt(3),
            			sdfdate.format(rs.getDate(4)));
			}
			System.out.printf("End Of Ouery"); 
		}
		catch(SQLException e1){ 
			//e1.printStackTrace();
			System.out.println(e1.toString());  
			System.exit(1);
      		}
		catch(ParseException p1){
			System.out.printf("[Error]: The Date Format is wrong\n");
			return;
		}
		finally{ /* Close the Connection */
         		try {
            			rs.close();
            			p_stm.close();
            			conn.close();
         		} 
			catch (SQLException e2) {
				System.out.println(e2.toString()); 
				System.exit(1);
         		}
      		}
	}
}  