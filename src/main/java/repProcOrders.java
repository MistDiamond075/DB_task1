import java.sql.*;
import java.util.LinkedList;

public class repProcOrders implements Repository<Processing_orders>{
    private Statement stmt;
    private  DB_connection dbconn;
    private String sql_request;
    private LinkedList<Processing_orders> prordersList;
    
   public repProcOrders(){
       sql_request="";
       prordersList=new LinkedList<>();
       dbconn=new DB_connection();
       if(!dbconn.isConnected())System.exit(0);
       stmt=dbconn.getStatement();
   }
    
    @Override
    public Integer rAdd(Processing_orders prorders) {
        int ret_id=0;
        sql_request="insert into prorders (id, repair_type,cost,repair_date,client_msg,payment,orders_id,orders_tovar_id,stuff_id) values (?,?,?,?,?,?,?,?,?);";
        PreparedStatement stmtup= null;
        try {
            stmtup = dbconn.getConn().prepareStatement(sql_request, Statement.RETURN_GENERATED_KEYS);{
                stmtup.setInt(1,prorders.getId());
                stmtup.setString(2,prorders.getRepair_type());
                stmtup.setInt(3,prorders.getCost());
                stmtup.setDate(4, Date.valueOf(prorders.getRepair_date()));
                stmtup.setBoolean(5,prorders.isClient_msg());
                stmtup.setInt(6,prorders.getPayment());
                stmtup.setInt(7,prorders.getOrders_id().getId());
                stmtup.setInt(7,prorders.getOrders_tovar_id().getId());
                stmtup.setInt(7,prorders.getStuff_id().getId());
                ResultSet generatedKeys=stmtup.getGeneratedKeys();
                if(generatedKeys.next()){
                    prorders.setId(generatedKeys.getInt(1));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        ret_id=prorders.getId();
        return ret_id;
    }

    @Override
    public void rRemove(Processing_orders prorders) {
        sql_request="delete from processing_orders where id="+prorders.getId()+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public void rUpdate(Processing_orders prorders,int id,String column) {
        String change_column="";
        switch(column){
            case "id":{change_column+=prorders.getId();break;}
            case "repair_type":{change_column+=prorders.getRepair_type();break;}
            case "cost":{change_column+=prorders.getCost();break;}
            case "repair_date":{change_column+=prorders.getRepair_date();break;}
            case "client_msg":{change_column+=prorders.isClient_msg();break;}
            case "payment":{change_column+=prorders.getPayment();break;}
            case "orders_id":{change_column+=prorders.getOrders_id().getId();break;}
            case "orders_tovar_id":{change_column+=prorders.getOrders_tovar_id().getId();break;}
            case "stuff_id":{change_column+=prorders.getStuff_id().getId();break;}
        }
        sql_request="update processing_orders set "+column+"="+change_column+" where id="+id+";";
        try {stmt.executeUpdate(sql_request);stmt.close();} catch (SQLException e) {throw new RuntimeException(e);}
        sql_request="";
    }

    @Override
    public LinkedList<Processing_orders> rList() {
        ResultSet res= null;
        repOrders rOrders=new repOrders();
        repStuff rStuff=new repStuff();
        LinkedList<Orders> ordersList=rOrders.rList();
        LinkedList<Stuff> stuffList=rStuff.rList();
        int k=0;
        int s=0;
        try {
            res = stmt.executeQuery("select*from stuff,orders,processing_orders where stuff.id=processing_orders.stuff_id and orders.id=processing_orders.orders_id and orders.tovar_id=processing_orders.orders_tovar_id;");
            while (res.next()){
                Processing_orders prord = new Processing_orders(res.getRow(), res.getString("repair_type"), res.getInt("cost"),
                        res.getString("repair_date"), res.getBoolean("client_msg"), res.getString("get_tovar_date"), res.getInt("payment"), ordersList.get(k),
                        ordersList.get(k), stuffList.get(s));
            prordersList.add(new Processing_orders(res.getRow(), res.getString("repair_type"), res.getInt("cost"),
                    res.getString("repair_date"), res.getBoolean("client_msg"), res.getString("get_tovar_date"), res.getInt("payment"), ordersList.get(k),
                    ordersList.get(k), stuffList.get(s)));
            s++;
            k++;
        }
            stmt.close();
        }catch (SQLException e) {throw new RuntimeException(e);}
        if(!prordersList.isEmpty()){return prordersList;}
        return null;
    }
}
