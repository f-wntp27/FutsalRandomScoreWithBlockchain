package struct;

public class MatchMaking {
    private Team homeTeam;
    private Team awayTeam;
    private int homeScore;
    private int awayScore;

    public MatchMaking(Team homeTeam, Team awayTeam, int homeScore, int awayScore) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        // System.out.println(getHomeTeam().getName() + " (" + getHomeScore() + "-" + getAwayScore() + ") " + getAwayTeam().getName());
    }

    public Team getHomeTeam() {
        return homeTeam;
    }
    public void setHomeTeam(Team homeTeam) {
        this.homeTeam = homeTeam;
    }

    public Team getAwayTeam() {
        return awayTeam;
    }
    public void setAwayTeam(Team awayTeam) {
        this.awayTeam = awayTeam;
    }

    public int getHomeScore() {
        return homeScore;
    }
    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }
    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }
}

