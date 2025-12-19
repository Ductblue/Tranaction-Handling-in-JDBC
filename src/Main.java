import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        String url="jdbc:mysql://localhost:3306/mydatabase";
        String username="root";
        String password="Shreyansh@1975";
        String withdrawquery="update accounts set balance=balance-? where account_number=?;";
        String depositquery="update accounts set balance=balance+? where account_number=?;";

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        try{
            Connection con= DriverManager.getConnection(url,username,password);
            System.out.println("Connection Builds successfully");
            con.setAutoCommit(false);

            try{
                PreparedStatement withdrawstatement=con.prepareStatement(withdrawquery);
                PreparedStatement depositstatement=con.prepareStatement(depositquery);
                withdrawstatement.setDouble(1,500.00);
                withdrawstatement.setString(2,"account456");
                depositstatement.setDouble(1,500.00);
                depositstatement.setString(2,"account123");
                int rowaw=withdrawstatement.executeUpdate();
                int rowad=depositstatement.executeUpdate();
                if(rowad>0 && rowaw>0){
                    con.commit();
                    System.out.println("Transaction successful!");}
                else{
                    con.rollback();
                    System.out.println("Transaction failed!!");
                }
            }
            catch (SQLException e){
                System.out.println(e.getMessage());
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
