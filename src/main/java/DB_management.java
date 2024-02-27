import java.sql.*;
import java.util.ArrayList;

public class DB_management {
    protected Statement stmt=null;
    protected Connection conn = null;
    private ArrayList<Processing_orders> proc_ordersList;
    private ArrayList<Orders> ordersList;
    private ArrayList<Stuff> stuffList;
    private ArrayList<Tovar> tovarList;
public DB_management(){
    try {
        String url = "jdbc:sqlite:C:/Programms/sqlite/db_task01";
        conn = DriverManager.getConnection(url);
        stmt=conn.createStatement();
        System.out.println("Connection to SQLite has been established.");
    } catch (SQLException e) {System.out.println(e.getMessage());} /*finally {
        try {if (conn != null) {
            conn.close();
            }
        } catch (SQLException ex) {System.out.println(ex.getMessage());}
    }*/
    proc_ordersList=new ArrayList<>();
    ordersList=new ArrayList<>();
    stuffList=new ArrayList<>();
    tovarList=new ArrayList<>();
}
private void getDataFromDB(){
    ResultSet res=null;
    try {
        res=stmt.executeQuery("select*from tovar,orders where tovar.id=orders.tovar_id;");
        while(res.next()) {
            Tovar tovar = new Tovar(res.getInt("id"), res.getString("name"), res.getString("firm_name"), res.getString("model"),
                    res.getString("model"), res.getInt("garant"), res.getBoolean("image"));

            tovarList.add(new Tovar(res.getRow(), res.getString("name"), res.getString("firm_name"), res.getString("model"),
                    res.getString("model"), res.getInt("garant"), res.getBoolean("image")));
        }
         res=stmt.executeQuery("select*from stuff,orders,processing_orders where stuff.id=processing_orders.stuff_id and orders.id=processing_orders.orders_id and orders.tovar_id=processing_orders.orders_tovar_id;");
        int k=0;
        int s=0;
        int t=0;
    while(res.next()){
        Stuff stuff=new Stuff(res.getInt("id"),res.getString("fullname"),res.getString("profession"));
        stuffList.add(new Stuff(res.getInt("id"),res.getString("fullname"),res.getString("profession")));
        Orders orders=new Orders(res.getInt("id"),res.getBoolean("garant"),res.getString("date"),
                res.getInt("phone"),res.getString("client_name"),tovarList.get(t));
        ordersList.add(new Orders(res.getInt("id"),res.getBoolean("garant"),res.getString("date"),
                res.getInt("phone"),res.getString("client_name"),tovarList.get(t)));
        Processing_orders prord=new Processing_orders(res.getRow(),res.getString("repair_type"),res.getInt("cost"),
     res.getString("repair_date"),res.getBoolean("client_msg"),res.getString("get_tovar_date"),res.getInt("payment"),ordersList.get(k),
        ordersList.get(k),stuffList.get(s));
        proc_ordersList.add(new Processing_orders(res.getRow(),res.getString("repair_type"),res.getInt("cost"),
                res.getString("repair_date"),res.getBoolean("client_msg"),res.getString("get_tovar_date"),res.getInt("payment"),ordersList.get(k),
                ordersList.get(k),stuffList.get(s)));
    }
    } catch (SQLException e) {throw new RuntimeException(e);}
}
public void shDBdata(){
    getDataFromDB();
    System.out.println("tovar->orders");
    System.out.println("==================");
    for(Orders orders: ordersList){
        System.out.println(orders.getId()+" "+orders.isGarant()+" "+orders.getPhone()+" "+orders.getClient_name()+" "+orders.getDate()+" "+orders.getTovar_id().getId()+" | ");
    }
    System.out.println("==================");
    for(Tovar tovar:tovarList){
        System.out.println(tovar.getId()+" "+tovar.getName()+" "+tovar.getGarant()+" "+tovar.getFirm_name()+" "+tovar.getModel()+" "+tovar.getProperties()+" "+tovar.isImage());
    }
    System.out.println("==================");
    System.out.println("suff,orders->processing_orders");
    System.out.println("==================");
    for(Orders orders:ordersList){
        System.out.println(orders.getId()+" "+orders.isGarant()+" "+orders.getPhone()+" "+orders.getClient_name()+" "+orders.getDate()+" "+orders.getTovar_id().getId()+" | ");
    }
    System.out.println("==================");
    for(Stuff stuff:stuffList){
        System.out.println(stuff.getId()+" "+stuff.getFullname()+" "+stuff.getProfession());
    }
    System.out.println("==================");
    for(Processing_orders prord:proc_ordersList){
        System.out.println(prord.getId()+" "+prord.getCost()+" "+prord.getPayment()+" "+prord.getOrders_id()+" "+prord.getOrders_tovar_id()+" "+prord.getRepair_date()+" "+prord.getRepair_type()+" "+
                prord.getStuff_id()+" "+prord.isClient_msg()+" "+prord.getGet_tovar_date());
    }
}
}
