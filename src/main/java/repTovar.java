import java.sql.*;
import java.util.LinkedList;

public class repTovar implements Repository<Tovar>{
   private Statement stmt;
   private  DB_connection dbconn;
   private String sql_request;
    private LinkedList<Tovar> tovarList;

public repTovar(){
    sql_request="";
    tovarList=new LinkedList<>();
    dbconn=new DB_connection();
    if(!dbconn.isConnected())System.exit(0);
    stmt=dbconn.getStatement();
}
    @Override
    public Integer rAdd(Tovar tovar) {
        int ret_id=0;
        sql_request="insert into tovar (id, name,firm_name,model,properties,garant,image) values (?,?,?,?,?,?,?);";
        PreparedStatement stmtup= null;
        try {
            stmtup = dbconn.getConn().prepareStatement(sql_request, Statement.RETURN_GENERATED_KEYS);{
            stmtup.setInt(1,tovar.getId());
            stmtup.setString(2,tovar.getName());
            stmtup.setString(3,tovar.getFirm_name());
            stmtup.setString(4,tovar.getModel());
            stmtup.setString(5,tovar.getProperties());
            stmtup.setInt(6,tovar.getGarant());
            stmtup.setBoolean(7,tovar.isImage());
            stmtup.executeUpdate();
            ResultSet generatedKeys=stmtup.getGeneratedKeys();
            if(generatedKeys.next()){
                tovar.setId(generatedKeys.getInt(1));
            }
        }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ret_id=tovar.getId();
        return ret_id;
}

    @Override
    public void rRemove(Tovar tovar) {
        sql_request="delete from tovar where id="+tovar.getId()+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rUpdate(Tovar tovar,int id,String column) {
    String change_column="";
    switch(column){
        case "id":{change_column+=tovar.getId();break;}
        case "name":{change_column+=tovar.getName();break;}
        case "firm_name":{change_column+=tovar.getFirm_name();break;}
        case "model":{change_column+=tovar.getModel();break;}
        case "properties":{change_column+=tovar.getProperties();break;}
        case "garant":{change_column+=tovar.getGarant();break;}
        case "image":{change_column+=tovar.isImage();break;}
    }
        sql_request="update tovar set "+column+"="+change_column+" where id="+id+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public LinkedList<Tovar> rList() {
        ResultSet res= null;
        try {
            res = stmt.executeQuery("select*from tovar;");
            while(res.next()) {
                Tovar tovar = new Tovar(res.getInt("id"), res.getString("name"), res.getString("firm_name"), res.getString("model"),
                        res.getString("model"), res.getInt("garant"), res.getBoolean("image"));
                tovarList.add(new Tovar(res.getRow(), res.getString("name"), res.getString("firm_name"), res.getString("model"),
                        res.getString("model"), res.getInt("garant"), res.getBoolean("image")));
            }
            stmt.close();
        }
        catch (SQLException e) {throw new RuntimeException(e);}
        if(!tovarList.isEmpty()){return tovarList;}
        return null;
    }
}
