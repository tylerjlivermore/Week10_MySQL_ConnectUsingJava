//https://www.youtube.com/watch?v=CDYOj4bMYHY

package week10Project;

import java.sql.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Menu {
	
	private Scanner scanner = new Scanner(System.in);
	private List<String> options = Arrays.asList(
			"Display Players", 
			"Create Player", 
			"Update Player",
			"Delete Player");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			if (selection.equals("1")) {
				displayPlayers();
			} else if (selection.equals("2")) {
				createPlayer();
			} else if (selection.equals("3")) {
				updatePlayer();
			} else if (selection.equals("4")) {
				deletePlayer();
			}
			
			System.out.println("Press enter to continue.");
			scanner.nextLine();
		} while (!selection.equals("e"));
		System.out.println("Goodbye.");
	}
	
	private void printMenu() {
		System.out.println("Select an option:\n-------------------------");
		for (int i = 0; i < options.size(); i++) {
			System.out.println(i + 1 + ":" + options.get(i));
		}
	}
	
	private void displayPlayers() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/npb",
					"root",
					"password"); //change to your MySQL password
			System.out.println("Connection established.");
			
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("Select * from players");
			while (rs.next()) {
				System.out.println("Player ID: [" + rs.getInt(1) + "]");
				System.out.println("Player: " + rs.getString(2) + ", " + rs.getString(3));
				System.out.println("Team: " + rs.getString(4));
				System.out.println("Position: " + rs.getString(5));
				System.out.println("------------------------------------------");
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void createPlayer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/npb",
					"root",
					"password"); //change to your MySQL password
			System.out.println("Connection established.");
			
			PreparedStatement stmt = con.prepareStatement(
					"Insert Into players(id, lastname, firstname, team, pos) VALUES (?, ?, ?, ?, ?);"
					);
			stmt.setInt(1, 5);
			stmt.setString(2, "Laird");
			stmt.setString(3, "Brandon");
			stmt.setString(4, "Marines");
			stmt.setString(5, "DH");
			int i = stmt.executeUpdate();
			
			System.out.println(i + " records affected.");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void updatePlayer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/npb",
					"root",
					"password"); //change to your MySQL password
			System.out.println("Connection established.");
			
			PreparedStatement stmt = con.prepareStatement(
					"Update players set pos = ? where lastname = ?;");
			stmt.setString(1, "3B");
			stmt.setString(2, "Kurebayashi");
			int i = stmt.executeUpdate();
			
			System.out.println(i + " records affected.");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	private void deletePlayer() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/npb",
					"root",
					"password"); //change to your MySQL password
			System.out.println("Connection established.");
			
			PreparedStatement stmt = con.prepareStatement(
					"Delete from players where id = ?;"
					);
			stmt.setInt(1, 5);
			int i = stmt.executeUpdate();
			
			System.out.println(i + " records affected.");
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}