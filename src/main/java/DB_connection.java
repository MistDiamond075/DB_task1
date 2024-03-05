import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DB_connection {
   private Statement stmt;
   private Connection conn;
   private boolean connected=false;

public DB_connection() {
        try {
            String url = "jdbc:sqlite:C:/Programms/sqlite/db_task01";
            conn = DriverManager.getConnection(url);
            stmt = conn.createStatement();
          //  System.out.println("Connection to SQLite has been established.");
            connected=true;
        } catch (
                SQLException e) {
            System.out.println(e.getMessage());
        }
    }
public boolean isConnected(){
    return connected;
}

public Statement getStatement(){
    return stmt;
}
}
