package model;

public class Player {

    private int id;
    private String name;
    private String lastname;
    private String nick;
    private String telnumber;
    private String email;
    private String address;
    private String city;
    private String postalcode;
    private boolean telpush;
    private boolean emailpush;

    public Player(int id, String name, String lastname, String nick, String telnumber, String email,
                  String address, String city, String postalcode, boolean telpush, boolean emailpush) {
        this.id = id;
        this.name = name;
        this.lastname = lastname;
        this.nick = nick;
        this.telnumber = telnumber;
        this.email = email;
        this.address = address;
        this.city = city;
        this.postalcode = postalcode;
        this.telpush = telpush;
        this.emailpush = emailpush;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getTelnumber() {
        return telnumber;
    }

    public void setTelnumber(String telnumber) {
        this.telnumber = telnumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(String postalcode) {
        this.postalcode = postalcode;
    }

    public boolean isTelpush() {
        return telpush;
    }

    public void setTelpush(boolean telpush) {
        this.telpush = telpush;
    }

    public boolean isEmailpush() {
        return emailpush;
    }

    public void setEmailpush(boolean emailpush) {
        this.emailpush = emailpush;
    }
}
