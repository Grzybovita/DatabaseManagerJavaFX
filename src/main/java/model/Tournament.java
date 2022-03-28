package model;

import java.util.ArrayList;
import java.util.List;

public class Tournament {

    private int id;
    private String name;
    private String date;
    private int buyin;
    private int stack;
    private int blinds;
    private int guaranteed;
    private List<Player> playerList;

    public Tournament(int id, String name, String date, int buyin, int stack, int blinds, int guaranteed) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.buyin = buyin;
        this.stack = stack;
        this.blinds = blinds;
        this.guaranteed = guaranteed;
        playerList = new ArrayList<>();
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getBuyin() {
        return buyin;
    }

    public void setBuyin(int buyin) {
        this.buyin = buyin;
    }

    public int getStack() {
        return stack;
    }

    public void setStack(int stack) {
        this.stack = stack;
    }

    public int getBlinds() {
        return blinds;
    }

    public void setBlinds(int blinds) {
        this.blinds = blinds;
    }

    public int getGuaranteed() {
        return guaranteed;
    }

    public void setGuaranteed(int guaranteed) {
        this.guaranteed = guaranteed;
    }

    @Override
    public String toString() {
        return "Tournament{" +
                "name='" + name + '\'' +
                ", date='" + date + '\'' +
                ", buyin=" + buyin +
                ", stack=" + stack +
                ", blinds=" + blinds +
                ", guaranteed=" + guaranteed +
                '}';
    }
}
