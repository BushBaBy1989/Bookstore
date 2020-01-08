import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Bookstore {

	public static void main(String[] args) {
		try (
				Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebookstore?useSSL=false", "myuser", "125");
				Statement stmt = conn.createStatement();
		) {
			Scanner input = new Scanner(System.in);
            int selection = 99;
            while(selection != 0) {
                System.out.println("Please enter the number corresponding to the action you would like to take:\n"
                        + "1. Enter book\n"
                        + "2. Update book\n"
                        + "3. Delete book\n"
                        + "4. Search books\n"
                        + "0. Exit");
                selection = input.nextInt();
                switch(selection) {
                	case 1:
                		System.out.println("Enter ID: ");
                        int id = input.nextInt();
                		System.out.println("Enter Title: ");
                        String title = input.next();
                        System.out.println("Enter Author: ");
                        String author = input.next();
                        System.out.println("Enter Qty: ");
                        int qty = input.nextInt();

                        String strUpdate = "insert into books values (" + id + ", '" + title + "', '" + author + "', " + qty + ");";
            			System.out.println("The SQL query is: " + strUpdate);
            			int countUpdated = stmt.executeUpdate(strUpdate);
            			System.out.println(countUpdated + " records affected.");
                        
                		break;
                	case 2:
                		System.out.println("Enter ID: ");
                        int idUp = input.nextInt();
                        System.out.println("Enter new Qty: ");
                        int newQty = input.nextInt();

                        strUpdate = "update books set qty = " + newQty + " where id = " + idUp + ";";
            			System.out.println("The SQL query is: " + strUpdate);
            			int countUpdated1 = stmt.executeUpdate(strUpdate);
            			System.out.println(countUpdated1 + " records affected.");
                        
                		break;
                	case 3:
                		System.out.print("Please enter the id of the book you would like to delete from the system: ");
                        int idDel = input.nextInt();

                        strUpdate = "delete from books where id = "+ idDel + ";";
            			System.out.println("The SQL query is: " + strUpdate);
            			int countUpdated2 = stmt.executeUpdate(strUpdate);
            			System.out.println(countUpdated2 + " records affected.");
                        
                		break;
                	case 4:
                		String strSelect = "select title, author, qty from books";
            			System.out.println("\nThe SQL query is: " + strSelect);
            			System.out.println();
            			ResultSet rset = stmt.executeQuery(strSelect);
            			System.out.println("The records selected are:");
            			int rowCount = 0;
            			while(rset.next()) {
            				String titleRead = rset.getString("title");
            				String authorRead = rset.getString("author");
            				int qtyRead = rset.getInt("qty");
            				System.out.println(titleRead + ", " + authorRead + ", "+ qtyRead);
            				++rowCount;
            			}
            			System.out.println("Total number of records = " + rowCount);
                		
                		break;
                	case 0:
                		System.out.println("Exiting");
                		break;
                	default:
                		System.out.println("ivalid selection");
                }
            }
		} catch(SQLException ex) {
			ex.printStackTrace();
		}

	}

}
