import java.sql.PreparedStatement;
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
    public Integer rAdd(Stuff stuff) {
        int ret_id=0;
        sql_request="insert into stuff (id, fullname,profession) values (?,?,?);";
        PreparedStatement stmtup= null;
        try {
            stmtup = dbconn.getConn().prepareStatement(sql_request, Statement.RETURN_GENERATED_KEYS);{
                stmtup.setInt(1,stuff.getId());
                stmtup.setString(2,stuff.getFullname());
                stmtup.setString(3,stuff.getProfession());
                stmtup.executeUpdate();
                ResultSet generatedKeys=stmtup.getGeneratedKeys();
                if(generatedKeys.next()){
                    stuff.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ret_id=stuff.getId();
        return ret_id;
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
