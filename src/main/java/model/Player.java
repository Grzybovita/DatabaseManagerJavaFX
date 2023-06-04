package model;

import pokermanagerapp.PlayerDAO;

import java.util.HashMap;
import java.util.Map;

public class Player extends Person implements Removable {

    private String nick;
    private boolean telpush;
    private boolean emailpush;
    private ClubStatus clubStatus;
    private Map<String, Contact> contacts;

    public Player(int id, String name, String lastname, String nick, String telnumber, String email,
                  Address address, boolean telpush, boolean emailpush) {
        this.setId(id);
        this.setName(name);
        this.setLastname(lastname);
        this.setNick(nick);
        this.setTelnumber(telnumber);
        this.setEmail(email);
        this.setAddress(address);
        this.setTelpush(telpush);
        this.setEmailpush(emailpush);
        this.contacts = new HashMap<>();
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

    public void addContact(String qualifier, Contact contact) {
        contacts.put(qualifier, contact);
    }

    public void removeContact(String qualifier) {
        contacts.remove(qualifier);
    }

    public Contact getContact(String qualifier) {
        return contacts.get(qualifier);
    }

    public Map<String, Contact> getContacts() {
        return contacts;
    }


    @Override
    public String toString()
    {
        return "Player{" +
                "nick='" + nick + '\'' +
                ", telpush=" + telpush +
                ", emailpush=" + emailpush +
                ", clubStatus=" + clubStatus +
                '}';
    }

    @Override
    public void removable()
    {

    }
}
