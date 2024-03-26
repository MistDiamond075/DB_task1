import javax.persistence.EntityManager;
import java.sql.*;
import java.util.LinkedList;

public class repOrders implements Repository<Orders>{
    private Statement stmt;
    private  DB_connection dbconn;
    private String sql_request;
    private LinkedList<Orders> ordersList;
  public  repOrders(){
      sql_request="";
      ordersList=new LinkedList<>();
      dbconn=new DB_connection();
      if(!dbconn.isConnected())System.exit(0);
      stmt=dbconn.getStatement();
    }
    
    @Override
    public Integer rAdd(Orders orders) {
      int ret_id=0;
        sql_request="insert into orders (id, garant,date,phone,tovar_id,client_name) values (?,?,?,?,?,?);";
        PreparedStatement stmtup= null;
        try {
            stmtup = dbconn.getConn().prepareStatement(sql_request, Statement.RETURN_GENERATED_KEYS);{
                stmtup.setInt(1,orders.getId());
                stmtup.setBoolean(2,orders.isGarant());
                stmtup.setDate(3, Date.valueOf(orders.getDate()));
                stmtup.setInt(4,orders.getPhone());
                stmtup.setInt(5,orders.getTovar_id().getId());
                stmtup.setString(6,orders.getClient_name());
                stmtup.executeUpdate();
                ResultSet generatedKeys=stmtup.getGeneratedKeys();
                if(generatedKeys.next()){
                    orders.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ret_id=orders.getId();
        return ret_id;
    }

    @Override
    public void rRemove(Orders orders) {
        sql_request="delete from orders where id="+orders.getId()+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rUpdate(Orders orders,int id,String column) {
        String change_column="";
        switch(column){
            case "id":{change_column+=orders.getId();break;}
            case "garant":{change_column+=orders.isGarant();break;}
            case "date":{change_column+=orders.getDate();break;}
            case "phone":{change_column+=orders.getPhone();break;}
            case "tovar_id":{change_column+=orders.getTovar_id().getId();break;}
            case "client_name":{change_column+=orders.getClient_name();break;}
        }
        sql_request="update orders set "+column+"="+change_column+" where id="+id+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public LinkedList<Orders> rList() {
        ResultSet res= null;
        int t=0;
        repTovar rTovar=new repTovar();
        LinkedList<Tovar> tovarList=rTovar.rList();
        try{
            res=stmt.executeQuery("select*from stuff,orders,processing_orders where stuff.id=processing_orders.stuff_id and orders.id=processing_orders.orders_id and orders.tovar_id=processing_orders.orders_tovar_id;");
            while(res.next()) {
                Orders orders = new Orders(res.getInt("id"), res.getBoolean("garant"), res.getString("date"),
                        res.getInt("phone"), res.getString("client_name"), tovarList.get(t));
                ordersList.add(new Orders(res.getInt("id"), res.getBoolean("garant"), res.getString("date"),
                        res.getInt("phone"), res.getString("client_name"), tovarList.get(t)));
                t++;
            }
            stmt.close();
        }catch (SQLException e) {throw new RuntimeException(e);}
        if(!ordersList.isEmpty()){return ordersList;}
        return null;
    }
}
