public class Tovar {
    private int id;
    private String name;
    private String firm_name;
    private String model;
    private String  properties;
    private int garant;
    private boolean image;

    public Tovar(int id,String name,String firm_name,String model,String properties,int garant,boolean image) {
        this.id=id;
        this.name=name;
        this.firm_name=firm_name;
        this.model=model;
        this.properties=properties;
        this.garant=garant;
        this.image=image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirm_name() {
        return firm_name;
    }

    public void setFirm_name(String firm_name) {
        this.firm_name = firm_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getProperties() {
        return properties;
    }

    public void setProperties(String properties) {
        this.properties = properties;
    }

    public int getGarant() {
        return garant;
    }

    public void setGarant(int garant) {
        this.garant = garant;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }
}
