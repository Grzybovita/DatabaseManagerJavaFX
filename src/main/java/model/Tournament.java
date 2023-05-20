package model;

import pokermanagerapp.TournamentDAO;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Tournament {
    private static String defaultName = "Unnamed Tournament";
    private int id;
    private String name;
    private String date;
    private int buyin;
    private int stack;
    private int blinds;
    private int guaranteed;
    private List<Player> playerList;
    //private boolean isOlderThanOneYear = isOlderThanXYears(1);
    public Tournament(int id, String name, String date, int buyin, int stack, int blinds, int guaranteed) {
        this.setId(id);
        if (name != null)
        {
            this.setName(name);
        }
        else
        {
            this.setName(defaultName);
        }
        this.setDate(date);
        this.setBuyin(buyin);
        this.setStack(stack);
        this.setBlinds(blinds);
        this.setGuaranteed(guaranteed);
        this.setPlayerList(TournamentDAO.getPlayersListInTournament(id));
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

    public List<Player> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(List<Player> playerList) {
        this.playerList = playerList;
    }

    /*public boolean isOlderThanOneYear() {
        return isOlderThanOneYear;
    }

    public void setOlderThanOneYear(boolean olderThanOneYear) {
        isOlderThanOneYear = olderThanOneYear;
    }*/

    /*public boolean isOlderThanXYears(int years)
    {
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        String formattedDateCurrent = currentDate.format(formatter);
        String formattedDateTournament = this.getDate();
        LocalDate date1 = LocalDate.parse(formattedDateCurrent, formatter);
        LocalDate date2 = LocalDate.parse(formattedDateTournament, formatter);

        Period period = Period.between(date2, date1);

        if (period.getYears() > 1) {
            System.out.println("The difference between the dates is more than 1 year.");
            return true;
        } else {
            System.out.println("The difference between the dates is less than or equal to 1 year.");
            return false;
        }
    }*/

    /*public boolean isOlderThanXYears()
    {
        System.out.println("No parameter entered - checking if older than default (1 year)");
        return isOlderThanXYears(1);
    }*/

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
