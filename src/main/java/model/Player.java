package model;

import pokermanagerapp.PlayerDAO;

public class Player extends Person {

    private String nick;
    private boolean telpush;
    private boolean emailpush;
    private ClubStatus clubStatus;

    public Player(int id, String name, String lastname, String nick, String telnumber, String email,
                  String address, String city, String postalcode, boolean telpush, boolean emailpush) {
        this.setId(id);
        this.setName(name);
        this.setLastname(lastname);
        this.setNick(nick);
        this.setTelnumber(telnumber);
        this.setEmail(email);
        this.setAddress(address);
        this.setCity(city);
        this.setPostalcode(postalcode);
        this.setTelpush(telpush);
        this.setEmailpush(emailpush);
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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

    public static int getCount()
    {
        return PlayerDAO.getAllPlayers().size();
    }

    @Override
    public String toString() {
        return "Player{" +
                "nick='" + nick + '\'' +
                ", telpush=" + telpush +
                ", emailpush=" + emailpush +
                ", clubStatus=" + clubStatus +
                '}';
    }
}
