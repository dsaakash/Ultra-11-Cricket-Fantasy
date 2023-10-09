package com.example.ultra11.Adapters;

public class YourMatchData {
    private String matchTitle;
    private String date;
    private String time;
    private int team1ImageResource; // Replace with the type that suits your image handling
    private int team2ImageResource; // Replace with the type that suits your image handling

    private  String contest_prize;

    public YourMatchData(String matchTitle, String date, String time, int team1ImageResource, int team2ImageResource,String contest_prize) {
        this.matchTitle = matchTitle;
        this.date = date;
        this.team1ImageResource = team1ImageResource;
        this.team2ImageResource = team2ImageResource;
        this.contest_prize = contest_prize;
    }

    public String getMatchTitle() {
        return matchTitle;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getTeam1ImageResource() {
        return team1ImageResource;
    }

    public int getTeam2ImageResource() {
        return team2ImageResource;
    }

    public String getContest_prize() {
        return contest_prize;
    }

    public void setContest_prize(String contest_prize) {
        this.contest_prize = contest_prize;
    }
}
