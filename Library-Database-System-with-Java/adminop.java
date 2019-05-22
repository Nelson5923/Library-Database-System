package CSCI3170;

import java.util.Scanner;
import java.sql.*;
import java.util.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.text.ParseException;

public class adminop { 	

	public static void op1() {
		Connection conn = null;
		Statement stm = null;

      		try{
  			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();
     
            		String sql = "CREATE TABLE category " +
                  	 "(id INTEGER, " +
                   	" loan_period INTEGER, " + 
                   	" max_books INTEGER, " + 
                   	" PRIMARY KEY (id))"; 

            		String sql_2 = "CREATE TABLE user " +
                   	"(id varchar(10), " +
                   	" name varchar(25), " + 
                   	" address varchar(100), " + 
                   	" category_id Integer," +
                   	" PRIMARY KEY (id), " +
                   	" FOREIGN KEY (category_id) REFERENCES category(id))";

			String sql_3 = "CREATE TABLE book " +
                   	"(call_number varchar(8), " +
                   	" title varchar(30) NOT NULL, " + 
                   	" publish_date DATE, " + 
                   	" PRIMARY KEY (call_number))";

			String sql_4 = "CREATE TABLE copy " +
                   	"(call_number varchar(8), " +
                   	" copy_number Integer, " + 
                   	" PRIMARY KEY (call_number,copy_number), " +
                   	" FOREIGN KEY (call_number) REFERENCES book(call_number) ON DELETE CASCADE)"; 
                             
            		String sql_5 = "CREATE TABLE checkout_record " +
                   	"(user_id varchar(10), " +
                   	" call_number varchar(8) NOT NULL, " + 
                   	" copy_number Integer, " + 
                   	" checkout_date DATE," +
                   	" return_date DATE, " +
                   	" PRIMARY KEY (user_id,call_number,copy_number,checkout_date), " +
                   	" FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE CASCADE," +
                   	" FOREIGN KEY (call_number, copy_number) REFERENCES copy(call_number, copy_number) ON DELETE CASCADE)";

            		String sql_6 = "CREATE TABLE author " +
                   	"(name varchar(25), " +
                   	" call_number varchar(8), " + 
                   	" PRIMARY KEY (name, call_number), " + 
                   	" FOREIGN KEY (call_number) REFERENCES book(call_number))";

            		if ((stm.executeUpdate(sql) != -1)&&(stm.executeUpdate(sql_2) != -1)&&(stm.executeUpdate(sql_3) != -1)
            			&(stm.executeUpdate(sql_4) != -1)&&(stm.executeUpdate(sql_5) != -1)&&(stm.executeUpdate(sql_6) != -1)){
				System.out.println("Processing...Done! Database is initialized!");
            		} 
			else {
            			System.out.println("[Error]: Database cannot be initialized");
            		}			
		}
		catch(SQLException se){
      			//Handle errors for JDBC
      			se.printStackTrace();

   		}
		catch(Exception e){

           		e.printStackTrace();

   		}
		finally{
           		try{
         			if(stm!=null)
            			conn.close();
      			}
			catch(SQLException se){
      			}// do nothing
      			try{
         			if(conn!=null)
            			conn.close();
      			}
			catch(SQLException se){
         			se.printStackTrace();
      			}
   		}
	}

	public static void op2() {
		Connection conn = null;
		Statement stm = null;
	 
		try{
  			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
          		stm = conn.createStatement();
                	
            		String sql = "DROP TABLE category ";

            		String sql_2 = "DROP TABLE user ";
 
			String sql_3 = "DROP TABLE book ";
 
			String sql_4 = "DROP TABLE copy ";
                               
            		String sql_5 = "DROP TABLE checkout_record ";
 
            		String sql_6 = "DROP TABLE author ";
 
			if ((stm.executeUpdate(sql) != -1)&&(stm.executeUpdate(sql_2) != -1)&&(stm.executeUpdate(sql_3) != -1)
            		&&(stm.executeUpdate(sql_4) != -1)&&(stm.executeUpdate(sql_5) != -1)&&(stm.executeUpdate(sql_6) != -1)){
            			System.out.println("Processing...Done! Database is removed!");
            		} 
			else {
            			System.out.println("[Error]: Database cannot be removed");
            		}
  		}
		catch(SQLException se){

      			se.printStackTrace();

   		}
		catch(Exception e){
      			e.printStackTrace();
   		}
		finally{
      			try{
         			if(stm!=null)
            				conn.close();
      			}
				catch(SQLException se){
      				}// do nothing
      			try{
         			if(conn!=null)
            			conn.close();
      			}
			catch(SQLException se){
         			se.printStackTrace();
      			}
   		}
	}

	public static void op3() {
		Scanner input_op3 = new Scanner(System.in); //Can not share User input (Scanner)

		System.out.printf("Type in the Source Data Folder Path: ");
		String folder_path = input_op3.next();

		add_category(folder_path);
		add_user(folder_path);
		add_book(folder_path);
		add_checkout(folder_path);

		Connection conn = null;
		Statement stm = null;
		String infl_1 = "add_data_category.txt";
		String infl_2 = "add_data_user.txt";
		String infl_3 = "add_data_book.txt";
		String infl_4 = "add_data_copy.txt";
		String infl_5 = "add_data_checkout.txt";
		String infl_6 = "add_data_author.txt";

		String line;

   		try {
    			BufferedReader br_1 = new BufferedReader(new FileReader(infl_1));
    			BufferedReader br_2 = new BufferedReader(new FileReader(infl_2));
    			BufferedReader br_3 = new BufferedReader(new FileReader(infl_3));
    			BufferedReader br_4 = new BufferedReader(new FileReader(infl_4));
    			BufferedReader br_5 = new BufferedReader(new FileReader(infl_5));
    			BufferedReader br_6 = new BufferedReader(new FileReader(infl_6));
    
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();

    			while ((line = br_1.readLine()) != null){
    				stm.executeUpdate(line);
    			}
    			while ((line = br_2.readLine()) != null){
    				stm.executeUpdate(line);
    			}
    			while ((line = br_3.readLine()) != null){
    				stm.executeUpdate(line);
    			}
    			while ((line = br_4.readLine()) != null){
    				stm.executeUpdate(line);
    			}
    			while ((line = br_5.readLine()) != null){
    				stm.executeUpdate(line);
    			}
    			while ((line = br_6.readLine()) != null){
    				stm.executeUpdate(line);
    			}
	
    			System.out.println("Processing...Data are successfully loaded!");

		} 
		catch (IOException e) {
			System.err.println("IO Error: Failed to read files");
		}
		catch(SQLException se){
  			se.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{
 				if(stm!=null)
    				conn.close();
			}
			catch(SQLException se){
			}// do nothing
			try{
 				if(conn!=null)
    				conn.close();
			}
			catch(SQLException se){
 				se.printStackTrace();
			}
		}
	}

	public static void op4() {
		System.out.println("Number of records in each table: ");
		op4_1();
		op4_2();
		op4_3();
		op4_4();
		op4_5();
		op4_6();
	}

	public static void op4_1() {
		Connection conn = null;
		Statement stm = null;

      		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();
         	
            		String sql = "select COUNT(*) from category";
            		ResultSet rs = stm.executeQuery(sql);
            
		    	while(rs.next()){
				int count = rs.getInt("COUNT(*)");
		         	//Display values
		         	System.out.println("category: " + count);
		    	}
		    	rs.close();
		}
		catch(SQLException se){
      			se.printStackTrace();
   		}
		catch(Exception e){
      			e.printStackTrace();
   		}
		finally{
      			try{
         			if(stm!=null)
           			conn.close();
      			}
			catch(SQLException se){
      			}// do nothing
      			try{
         			if(conn!=null)
            			conn.close();
      			}
			catch(SQLException se){
         			se.printStackTrace();
      			}
   		}
	}	

	public static void op4_2() {
		Connection conn = null;
		Statement stm = null;

      		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();
         	
            		String sql = "select COUNT(*) from user";
            		ResultSet rs = stm.executeQuery(sql);

		    	while(rs.next()){
		         	int count = rs.getInt("COUNT(*)");
				//Display values
		         	System.out.println("user: " + count);
		    	}
		    	rs.close();
		}
		catch(SQLException se){
      			se.printStackTrace();
   		}
		catch(Exception e){
      			e.printStackTrace();
   		}
		finally{
      			try{
         			if(stm!=null)
           			conn.close();
      			}
			catch(SQLException se){
      			}// do nothing
      			try{
         			if(conn!=null)
            			conn.close();
      			}
			catch(SQLException se){
        			se.printStackTrace();
      			}
   		}
   	}
    	
	public static void op4_3() {
		Connection conn = null;
		Statement stm = null;

      		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();
         	
            		String sql = "select COUNT(*) from book";
           		ResultSet rs = stm.executeQuery(sql);

		    	while(rs.next()){
				int count = rs.getInt("COUNT(*)");
		         	//Display values
		         	System.out.println("book: " + count);
		    	}
		    	rs.close();
		}
		catch(SQLException se){
      			se.printStackTrace();
   		}
		catch(Exception e){
      			e.printStackTrace();
   		}
		finally{
      			try{
         			if(stm!=null)
           			conn.close();
      			}
			catch(SQLException se){
      			}// do nothing
      			try{
         			if(conn!=null)
            			conn.close();
      			}
			catch(SQLException se){
         			se.printStackTrace();
      			}
   		}
   	}

    public static void op4_4() {
		Connection conn = null;
		Statement stm = null;

      		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();
         	
            		String sql = "select COUNT(*) from copy";
            		ResultSet rs = stm.executeQuery(sql);

			while(rs.next()){
				int count = rs.getInt("COUNT(*)");
				//Display values
				System.out.println("copy: " + count);
		    	}
		    	rs.close();
		}

		catch(SQLException se){
      			se.printStackTrace();
   		}
		catch(Exception e){
      			e.printStackTrace();
   		}
		finally{
      			try{
         			if(stm!=null)
           				conn.close();
      			}
			catch(SQLException se){
      			}// do nothing
      			try{
         			if(conn!=null)
            				conn.close();
      			}
			catch(SQLException se){
         			se.printStackTrace();
      			}//end finally try
   		}//end try
   	}

    public static void op4_5() {

		Connection conn = null;
		Statement stm = null;

      		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();
         	
            		String sql = "select COUNT(*) from checkout_record";
            		ResultSet rs = stm.executeQuery(sql);

		    	while(rs.next()){
				int count = rs.getInt("COUNT(*)");
				//Display values
		         System.out.println("checkout_record: " + count);
		    	}
		    	rs.close();
		}
		catch(SQLException se){
      			se.printStackTrace();
   		}
		catch(Exception e){
      			e.printStackTrace();
   		}
		finally{
      			try{
         			if(stm!=null)
           				conn.close();
      			}
			catch(SQLException se){
      			}// do nothing
      			try{
         			if(conn!=null)
            			conn.close();
      			}
			catch(SQLException se){
         			se.printStackTrace();
      			}
   		}
   	}

    public static void op4_6() {
		Connection conn = null;
		Statement stm = null;

      		try{
			conn = DriverManager.getConnection(db_info.DB_URL,db_info.DB_USER,db_info.DB_PASSWD);
         		stm = conn.createStatement();
         	
            		String sql = "select COUNT(*) from author";
            		ResultSet rs = stm.executeQuery(sql);

		    	while(rs.next()){
				int count = rs.getInt("COUNT(*)");
				//Display values
				System.out.println("author: " + count);
		    	}
		    	rs.close();
		}
		catch(SQLException se){
      			se.printStackTrace();
   		}
		catch(Exception e){
      			e.printStackTrace();
   		}
		finally{
      			try{
         		if(stm!=null)
           			conn.close();
      			}
			catch(SQLException se){
      			}// do nothing
      			try{
         			if(conn!=null)
            			conn.close();
      			}
			catch(SQLException se){
         		se.printStackTrace();
      			}
   		}
    }

	public static void add_category(String args) {

		String folder_path = args; 
		String inputFile = folder_path + "/category.txt";
	
	    	String outputFile = "add_data_category.txt";
	    	String line;
	    	String[] item;

		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			FileWriter ow = new FileWriter(outputFile);
			BufferedWriter bw = new BufferedWriter(ow);
	      		while ((line = br.readLine()) != null){
	          		item = line.split("\t");

	          	bw.write("Insert into category values (");
	          	bw.write(item[0]);
	          	bw.write(",");
	          	bw.write(item[1]);
	          	bw.write(",");
	          	bw.write(item[2]);
	          	bw.write(")");
	          	bw.newLine();
	      		}

	      //close files.
			br.close(); 
	      		bw.close();
	    	}
	    	catch(FileNotFoundException ex) {
	      		System.out.println("Unable to open file '" + inputFile + "'");                
	    	}
	    	catch (IOException e) {
	      		System.err.println("IO Error: Failed to read '" + inputFile + "'");
	    	}
	}

	public static void add_user(String args) {
	  	
		String folder_path = args; 
		String inputFile = folder_path + "/user.txt";
	
	    	String outputFile = "add_data_user.txt";
	    	String line;
	    	String[] item;

		try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
	      		FileWriter ow = new FileWriter(outputFile);
	      		BufferedWriter bw = new BufferedWriter(ow);
	      		while ((line = br.readLine()) != null){
	          		item = line.split("\t");

	          		bw.write("Insert into user values ('");
	          		bw.write(item[0]);
	          		bw.write("','");
	          		bw.write(item[1]);
	          		bw.write("','");
	          
	          		//Search for "'" and add one more "'"

	          		String targetstr = item[2]; 
	          		int foundindex = targetstr.indexOf('\'');
	
	          		if (foundindex != -1){
	          			String str = new String(targetstr);
	          			StringBuilder newstr = new StringBuilder(str);
   					newstr.insert(foundindex, '\'');
   					item[2] = newstr.toString();
	          		}
	          		//End Search
				bw.write(item[2]);
				bw.write("',");
				bw.write(item[3]);
				bw.write(")");
				bw.newLine();
	      		}

	      		//close files.
	      		br.close(); 
	      		bw.close();
	    	}
		catch(FileNotFoundException ex) {
			System.out.println("Unable to open file '" + inputFile + "'");                
	    	}
	    	catch (IOException e) {
	      		System.err.println("IO Error: Failed to read '" + inputFile + "'");
	    	}
	}

  
	public static void add_book(String args) {

	  	String folder_path = args; 
		String inputFile = folder_path + "/book.txt";
	
	    	String outputFile = "add_data_book.txt";
	    	String outputFile_2 = "add_data_copy.txt";
	    	String outputFile_3 = "add_data_author.txt";
	    	String line;
	    	String[] item;
	    	String[] author_part;
		SimpleDateFormat sdfdate1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfdate2 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate1;

	    	try {
			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			FileWriter ow = new FileWriter(outputFile);
			FileWriter ow_2 = new FileWriter(outputFile_2);
			FileWriter ow_3 = new FileWriter(outputFile_3);
			BufferedWriter bw = new BufferedWriter(ow);
			BufferedWriter bw_2 = new BufferedWriter(ow_2);
			BufferedWriter bw_3 = new BufferedWriter(ow_3);

			while ((line = br.readLine()) != null){
				item = line.split("\t");

				bw.write("Insert into book values ('");
				bw.write(item[0]);
				bw.write("','");
				bw.write(item[2]);
				bw.write("',");
				
				if(item[4].equals("null")){
					item[4] = "NULL";
					bw.write(item[4]);
					bw.write(")");						
				}
				else{
					bw.write("'");
					parsedDate1 = sdfdate1.parse(item[4]);
					item[4] = sdfdate2.format(parsedDate1);

					bw.write(item[4]);
					bw.write("')");
				}

				bw.newLine();

				bw_2.write("Insert into copy values ('");
				bw_2.write(item[0]);
				bw_2.write("',");
				bw_2.write(item[1]);
				bw_2.write(")");
				bw_2.newLine();          

				//Split author name from text data
				author_part = item[3].split(","); 
				for (int i = 0 ; i< author_part.length ; i++){
					bw_3.write("Insert into author values ('");
					bw_3.write(author_part[i]);
					bw_3.write("','");
					bw_3.write(item[0]);
					bw_3.write("')");
					bw_3.newLine();  
				}
			}
			//close files.
			br.close(); 
			bw.close();
			bw_2.close();
			bw_3.close();
	    	}
	    	catch(FileNotFoundException ex) {
	      		System.out.println("Unable to open file '" + inputFile + "'");                
	    	}
	    	catch (IOException e) {
	      		System.err.println("IO Error: Failed to read '" + inputFile + "'");
	    	}
		catch(ParseException p1){
			System.out.printf("[Error]: The Date Format is wrong\n");
			return;
		}
	}

	public static void add_checkout(String args) {
	    
	  	String folder_path = args; 
		String inputFile = folder_path + "/checkout.txt";
	
	    	String outputFile = "add_data_checkout.txt";
	    	String line;
	    	String[] item;
		SimpleDateFormat sdfdate1 = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat sdfdate2 = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parsedDate1;
		java.util.Date parsedDate2;

		try {

			BufferedReader br = new BufferedReader(new FileReader(inputFile));
			FileWriter ow = new FileWriter(outputFile);
			BufferedWriter bw = new BufferedWriter(ow);

	      		while ((line = br.readLine()) != null){
	          		item = line.split("\t");

	          		bw.write("Insert into checkout_record values ('");
	          		bw.write(item[2]);
	          		bw.write("','");
	          		bw.write(item[0]);
	          		bw.write("',");
	          		bw.write(item[1]);
	          		bw.write(",'");
				
				parsedDate1 = sdfdate1.parse(item[3]);
				item[3] = sdfdate2.format(parsedDate1);
				bw.write(item[3]);
	          		bw.write("',");
				
				if(item[4].equals("null")){
					item[4] = "NULL";
					bw.write(item[4]);
					bw.write(")");
				}
				else{
					parsedDate2 = sdfdate1.parse(item[4]);
					item[4] = sdfdate2.format(parsedDate2);
					bw.write("'");
					bw.write(item[4]);
					bw.write("')");	
				}

	          		bw.newLine();  
	      		}

	      		//close files.
	      		br.close(); 
	      		bw.close();
	    	}
		catch(FileNotFoundException ex) {
	      		System.out.println("Unable to open file '" + inputFile + "'");                
		}
	    	catch (IOException e) {
	      		System.err.println("IO Error: Failed to read '" + inputFile + "'");
	    	}
		catch(ParseException p1){
			System.out.printf("[Error]: The Date Format is wrong\n");
			return;
		}
	}

}