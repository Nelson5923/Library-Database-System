package CSCI3170;
import java.util.Scanner;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.ParseException;

public class lbuop {  

	public static void op1_1() {

		Scanner input = new Scanner(System.in); 
		String search_keyword;
		System.out.printf("Type in the Search Keyword: ");
		search_keyword = input.next();

		Connection conn = null;
		PreparedStatement p_stm = null;
		ResultSet rs = null;
		int temp1 = 0;
		int temp2 = 0;

      		try{

			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
			p_stm = conn.prepareStatement("SELECT call_number ,title FROM book WHERE call_number = ?");
			p_stm.setString(1, search_keyword);	
			rs = p_stm.executeQuery();

			System.out.printf("|Call Number|Title|Author|Available No. of Copies|\n");

			while(rs.next()){
            			System.out.printf("|%s|%s|",
						rs.getString(1),
						rs.getString(2));
			}

			p_stm = conn.prepareStatement("SELECT name FROM author WHERE call_number = ?");
			p_stm.setString(1, search_keyword);
			rs = p_stm.executeQuery();
			while(rs.next()){
				if(rs.isLast()){
					System.out.printf("%s|",rs.getString(1));
				}
				else{
            				System.out.printf("%s, ",rs.getString(1));
				}
			}
			
			p_stm = conn.prepareStatement("SELECT COUNT(*) FROM checkout_record WHERE call_number = ? AND return_date IS NULL");
			p_stm.setString(1, search_keyword);
			rs = p_stm.executeQuery();
			if(rs.next()){
				temp1 = rs.getInt(1);
			}

			p_stm = conn.prepareStatement("SELECT copy_number FROM copy WHERE call_number = ?");
			p_stm.setString(1, search_keyword);
			rs = p_stm.executeQuery();
			if(rs.next()){
				temp2 = rs.getInt(1);
				System.out.printf("%d|\n", temp2 - temp1);
			}
			System.out.println("End of Query");

		}
		catch(SQLException e1){ 
			e1.printStackTrace();
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

	public static void op1_2() {

		Scanner input = new Scanner(System.in); 
		String search_keyword;
		System.out.printf("Type in the Search Keyword: ");
		search_keyword = input.next();

		Connection conn = null;
		PreparedStatement p_stm_1 = null;
		ResultSet rs_1 = null;
		PreparedStatement p_stm_2 = null;
		ResultSet rs_2 = null;
		PreparedStatement p_stm_3 = null;
		ResultSet rs_3 = null;
		PreparedStatement p_stm_4 = null;
		ResultSet rs_4 = null;

		int temp1 = 0;
		int temp2 = 0;
		int exit = 1;

      		try{ 
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);

			p_stm_1 = conn.prepareStatement("SELECT call_number ,title FROM book WHERE title LIKE ? ORDER BY call_number ASC");
			p_stm_1.setString(1, "%" + search_keyword + "%");	
			rs_1 = p_stm_1.executeQuery();

			System.out.printf("|Call Number|Title|Author|Available No. of Copies|\n");

			while(rs_1.next()){
				exit = 0; //there are tuples in result set
            			System.out.printf("|%s|%s|",rs_1.getString(1),rs_1.getString(2));

				p_stm_2 = conn.prepareStatement("SELECT name FROM author WHERE call_number = ? ORDER BY call_number ASC");
				p_stm_2.setString(1,rs_1.getString(1));
				rs_2 = p_stm_2.executeQuery();
				while(rs_2.next()){
							if(rs_2.isLast()){
								System.out.printf("%s|",rs_2.getString(1));
							}
							else{
            							System.out.printf("%s, ",rs_2.getString(1));
							}
				}

				p_stm_3 = conn.prepareStatement("SELECT COUNT(*) FROM checkout_record WHERE return_date IS NULL"
								+ " AND call_number = ? ORDER BY call_number ASC");
				p_stm_3.setString(1,rs_1.getString(1));
				rs_3 = p_stm_3.executeQuery();

				while(rs_3.next()){ //Number of Occupied Copy;
						temp1 = rs_3.getInt(1);
				}

				p_stm_4 = conn.prepareStatement("SELECT copy_number FROM copy WHERE call_number = ? ORDER BY call_number ASC");
				p_stm_4.setString(1,rs_1.getString(1));
				rs_4 = p_stm_4.executeQuery();

				while(rs_4.next()){ //Number of Copy 
					temp2 = rs_4.getInt(1);
					System.out.printf("%d|\n", temp2 - temp1);
				}				
			}
			System.out.println("End of Query");
			
		}
		catch(SQLException e1){ 
			//e1.printStackTrace();
			System.out.println(e1.toString());  
			System.exit(1);
      		}
		finally{
         		try {
            			rs_1.close();
            			p_stm_1.close();
				if(exit == 0){
          				rs_2.close();
            				p_stm_2.close();
          				rs_3.close();
            				p_stm_3.close();
          				rs_4.close();
            				p_stm_4.close();
				}
            			conn.close();
         		} 
			catch (SQLException e2) {
				System.out.println(e2.toString()); 
				System.exit(1);
         		}
      		}
	}

	public static void op1_3() {

		Scanner input = new Scanner(System.in); 
		String search_keyword;
		System.out.printf("Type in the Search Keyword: ");
		search_keyword = input.next();

		Connection conn = null;
		PreparedStatement p_stm_1 = null;
		ResultSet rs_1 = null;
		PreparedStatement p_stm_2 = null;
		ResultSet rs_2 = null;
		PreparedStatement p_stm_3 = null;
		ResultSet rs_3 = null;
		PreparedStatement p_stm_4 = null;
		ResultSet rs_4 = null;
		PreparedStatement p_stm_5 = null;
		ResultSet rs_5 = null;

		int temp1 = 0;
		int temp2 = 0;
		int exit = 1;

      		try{ 
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);

			p_stm_1 = conn.prepareStatement("SELECT DISTINCT call_number FROM author WHERE name LIKE ? ORDER BY call_number ASC");
			p_stm_1.setString(1, "%" + search_keyword + "%");	
			rs_1 = p_stm_1.executeQuery();

			System.out.printf("|Call Number|Title|Author|Available No. of Copies|\n");

			while(rs_1.next()){
				exit = 0;
            			System.out.printf("|%s|",rs_1.getString(1));

				/* GET TITLE NAME */
				p_stm_5 = conn.prepareStatement("SELECT title FROM book WHERE call_number = ? ORDER BY call_number ASC");
				p_stm_5.setString(1,rs_1.getString(1));
				rs_5 = p_stm_5.executeQuery();
				
				while(rs_5.next()){
					System.out.printf("%s|",rs_5.getString(1));
				}
				
				/* GET AUTHOR NAME */
				p_stm_2 = conn.prepareStatement("SELECT name FROM author WHERE call_number = ? ORDER BY call_number ASC");
				p_stm_2.setString(1,rs_1.getString(1));
				rs_2 = p_stm_2.executeQuery();
				while(rs_2.next()){
							if(rs_2.isLast()){
								System.out.printf("%s|",rs_2.getString(1));
							}
							else{
            							System.out.printf("%s, ",rs_2.getString(1));
							}
				}
				
				/* GET the Number of Occupied Copy */
				p_stm_3 = conn.prepareStatement("SELECT COUNT(*) FROM checkout_record WHERE return_date IS NULL"
								+ " AND call_number = ? ORDER BY call_number ASC");
				p_stm_3.setString(1,rs_1.getString(1));
				rs_3 = p_stm_3.executeQuery();

				while(rs_3.next()){ 
						temp1 = rs_3.getInt(1);
				}

				/* GET THE Number of Copy */
				p_stm_4 = conn.prepareStatement("SELECT copy_number FROM copy WHERE call_number = ? ORDER BY call_number ASC");
				p_stm_4.setString(1,rs_1.getString(1));
				rs_4 = p_stm_4.executeQuery();

				while(rs_4.next()){ 
					temp2 = rs_4.getInt(1);
					System.out.printf("%d|\n", temp2 - temp1);
				}
			}
			System.out.println("End of Query");
		}
		catch(SQLException e1){ 
			//e1.printStackTrace();
			System.out.println(e1.toString());  
			System.exit(1);
      		}
		finally{
         		try {
            			rs_1.close();
            			p_stm_1.close();
				if(exit == 0){
          				rs_2.close();
            				p_stm_2.close();
          				rs_3.close();
            				p_stm_3.close();
          				rs_4.close();
            				p_stm_4.close();
					rs_5.close();
            				p_stm_5.close();
				}
            			conn.close();
         		} 
			catch (SQLException e2) {
				System.out.println(e2.toString()); 
				System.exit(1);
         		}
      		}
		
	}

	public static void op2() {

		Scanner input = new Scanner(System.in); 
		String UID;
		System.out.printf("Enter The User ID: ");
		UID = input.next();

		Connection conn = null;
		PreparedStatement p_stm_1 = null;
		ResultSet rs_1 = null;
		PreparedStatement p_stm_2 = null;
		ResultSet rs_2 = null;
		PreparedStatement p_stm_3 = null;
		ResultSet rs_3 = null;
		PreparedStatement p_stm_4 = null;

		SimpleDateFormat sdfdate = new SimpleDateFormat("dd/MM/yyyy");
		Date date;

		int temp1 = 0;
		int temp2 = 0;
		int exit = 1;

      		try{ 
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);

			p_stm_1 = conn.prepareStatement("SELECT DISTINCT call_number, copy_number, checkout_date, return_date"
							+ " FROM checkout_record WHERE user_id = ? ORDER BY checkout_date DESC");
			p_stm_1.setString(1, UID);	
			rs_1 = p_stm_1.executeQuery();

			System.out.printf("Loan Reocrd: \n");
			System.out.printf("|CallNum|CopyNum|Title|Author|Check-out|Returned?|\n");

			while(rs_1.next()){
				exit = 0;
            			System.out.printf("|%s|%s|",rs_1.getString(1),rs_1.getString(2));

				/* GET TITLE NAME */
				p_stm_3 = conn.prepareStatement("SELECT title FROM book WHERE call_number = ?");
				p_stm_3.setString(1,rs_1.getString(1));
				rs_3 = p_stm_3.executeQuery();
				
				while(rs_3.next()){
					System.out.printf("%s|",rs_3.getString(1));
				}
				
				/* GET AUTHOR NAME */
				p_stm_2 = conn.prepareStatement("SELECT name FROM author WHERE call_number = ?");
				p_stm_2.setString(1,rs_1.getString(1));
				rs_2 = p_stm_2.executeQuery();
				while(rs_2.next()){
							if(rs_2.isLast()){
								System.out.printf("%s|",rs_2.getString(1));
							}
							else{
            							System.out.printf("%s, ",rs_2.getString(1));
							}
				}
				
				/* GET DATE*/
				System.out.printf("%s|",sdfdate.format(rs_1.getDate(3)));

				date = rs_1.getDate(4);
				if (date == null)
					System.out.printf("No|\n");
				else
					System.out.printf("Yes|\n"); 

			}
			System.out.println("End of Query");
		}
		catch(SQLException e1){ 
			//e1.printStackTrace();
			System.out.println(e1.toString());  
			System.exit(1);
      		}
		finally{
         		try {
            			rs_1.close();
            			p_stm_1.close();
				if (exit == 0){
          				rs_2.close();
            				p_stm_2.close();
          				rs_3.close();
            				p_stm_3.close();
				}
				conn.close();
         		} 
			catch (SQLException e2) {
				System.out.println(e2.toString()); 
				System.exit(1);
         		}
      		}
	}
}