public class Orders {
    private int id;
    private boolean garant;
    private String date;
    private int phone;
    private String client_name;
    private Tovar tovar_id;

    public Orders(int id, boolean garant, String date, int phone, String client_name, Tovar tovar_id) {
        this.id = id;
        this.garant = garant;
        this.date = date;
        this.phone = phone;
        this.client_name = client_name;
        this.tovar_id = tovar_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGarant() {
        return garant;
    }

    public void setGarant(boolean garant) {
        this.garant = garant;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public String getClient_name() {
        return client_name;
    }

    public void setClient_name(String client_name) {
        this.client_name = client_name;
    }

    public Tovar getTovar_id() {
        return tovar_id;
    }

    public void setTovar_id(Tovar tovar_id) {
        this.tovar_id = tovar_id;
    }
}
