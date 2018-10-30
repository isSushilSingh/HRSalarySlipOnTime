import java.sql.*;
import java.util.logging.*;
import java.io.*;
public class DBMail{
    public static void main(String args[]) {
        String dbURL = "jdbc:mysql://localhost:3306/hrsalaryslipontime";
        String username ="root";
        String password = "123123";
      
        Connection dbCon = null;
        Statement stmt = null;
        ResultSet rs = null; 
        String query;
        try {
			File folder = new File("PDFs");
			File[] listOfFiles = folder.listFiles();
			//String[] pdfFiles=new String[listOfFiles.length];
			for (int i = 0; i < listOfFiles.length; i++) {
				if (listOfFiles[i].isFile()) {
					query="select  emailId from  hrsalaryslipontime where empId=\""+listOfFiles[i].getName().substring(0, 6)+"\";";
					dbCon = DriverManager.getConnection(dbURL, username, password);
					stmt = dbCon.prepareStatement(query);
					rs = stmt.executeQuery(query);
					while(rs.next()){
						//send mail to rs.getString(1)
						System.out.println(rs.getString(1));
					}
				}
			}
        } catch (SQLException ex) {
			System.out.println(ex);
        }
    }  
}

//select emailId from  hrsalaryslipontime where empId='eps42';