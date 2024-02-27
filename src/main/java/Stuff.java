public class Stuff {
    private int id;
    private String fullname;
    private String profession;

    public Stuff(int id, String fullname, String profession) {
        this.id = id;
        this.fullname = fullname;
        this.profession = profession;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getProfession() {
        return profession;
    }

    public void setProfession(String profession) {
        this.profession = profession;
    }
}
