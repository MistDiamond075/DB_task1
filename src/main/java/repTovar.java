import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
    public void rAdd(Tovar tovar) {
        sql_request="insert into tovar values("+tovar.getId()+", "+tovar.getName()+", "+tovar.getFirm_name()+", "+tovar.getModel()+", "+tovar.getProperties()+", "+tovar.getGarant()+", "+tovar.isImage()+");";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
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
