import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

public class repStuff implements Repository<Stuff>{
    private Statement stmt;
    private  DB_connection dbconn;
    private String sql_request;
    private LinkedList<Stuff> stuffList;
    
    public repStuff(){
        sql_request="";
        stuffList=new LinkedList<>();
        dbconn=new DB_connection();
        if(!dbconn.isConnected())System.exit(0);
        stmt=dbconn.getStatement();
    }
    @Override
    public void rAdd(Stuff stuff) {
        sql_request="insert into stuff values("+stuff.getId()+", "+stuff.getFullname()+", "+stuff.getProfession()+");";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rRemove(Stuff stuff) {
        sql_request="delete from stuff where id="+stuff.getId()+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rUpdate(Stuff stuff,int id,String column) {
        String change_column="";
        switch(column){
            case "id":{change_column+=stuff.getId();break;}
            case "fullname":{change_column+=stuff.getFullname();break;}
            case "profession":{change_column+=stuff.getProfession();break;}
        }
        sql_request="update stuff set "+column+"="+change_column+" where id="+id+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public LinkedList<Stuff> rList() {
        ResultSet res= null;
        try {
            res = stmt.executeQuery("select*from stuff;");
        while(res.next()) {
            Stuff stuff = new Stuff(res.getInt("id"), res.getString("fullname"), res.getString("profession"));
            stuffList.add(new Stuff(res.getInt("id"), res.getString("fullname"), res.getString("profession")));
        }
        stmt.close();
        } catch (SQLException e) {throw new RuntimeException(e);}
        if(!stuffList.isEmpty()){return stuffList;}
        return null;
    }
}
