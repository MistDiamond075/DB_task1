public class Processing_orders {
    private int id;
    private String repair_type;
    private int cost;
    private String repair_date;
    private boolean client_msg;
    private String get_tovar_date;
    private int payment;
    private Orders orders_id;
    private Orders orders_tovar_id;
    private Stuff stuff_id;

    public Processing_orders(int id, String repair_type, int cost, String repair_date, boolean client_msg, String get_tovar_date, int payment, Orders orders_id, Orders orders_tovar_id, Stuff stuff_id) {
        this.id = id;
        this.repair_type = repair_type;
        this.cost = cost;
        this.repair_date = repair_date;
        this.client_msg = client_msg;
        this.get_tovar_date = get_tovar_date;
        this.payment = payment;
        this.orders_id = orders_id;
        this.orders_tovar_id = orders_tovar_id;
        this.stuff_id = stuff_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepair_type() {
        return repair_type;
    }

    public void setRepair_type(String repair_type) {
        this.repair_type = repair_type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getRepair_date() {
        return repair_date;
    }

    public void setRepair_date(String repair_date) {
        this.repair_date = repair_date;
    }

    public boolean isClient_msg() {
        return client_msg;
    }

    public void setClient_msg(boolean client_msg) {
        this.client_msg = client_msg;
    }

    public String getGet_tovar_date() {
        return get_tovar_date;
    }

    public void setGet_tovar_date(String get_tovar_date) {
        this.get_tovar_date = get_tovar_date;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public Orders getOrders_id() {
        return orders_id;
    }

    public void setOrders_id(Orders orders_id) {
        this.orders_id = orders_id;
    }

    public Orders getOrders_tovar_id() {
        return orders_tovar_id;
    }

    public void setOrders_tovar_id(Orders orders_tovar_id) {
        this.orders_tovar_id = orders_tovar_id;
    }

    public Stuff getStuff_id() {
        return stuff_id;
    }

    public void setStuff_id(Stuff stuff_id) {
        this.stuff_id = stuff_id;
    }
}
