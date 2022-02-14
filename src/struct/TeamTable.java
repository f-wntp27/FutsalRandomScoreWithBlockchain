package struct;

public class TeamTable {
    private Team team;
    private int played = 0;
    private int won = 0;
    private int loss = 0;
    private int draw = 0;
    private int goalfor = 0;
    private int goalagainst = 0;
    private int goaldiff = 0;
    private int point = 0;

    public TeamTable() {

    }
    public TeamTable(Team team) {
        this.team = team;
    }
    public TeamTable(Team team, int played, int won, int loss, int draw, int goalfor, int goalagainst, int goaldiff, int point) {
        this.team = team;
        this.played = played;
        this.won = won;
        this.loss = loss;
        this.draw = draw;
        this.goalfor = goalfor;
        this.goalagainst = goalagainst;
        this.goaldiff = goaldiff;
        this.point = point;
    }
    public TeamTable(TeamTable teamTable) {
        this(
            teamTable.team,
            teamTable.played, 
            teamTable.won, 
            teamTable.loss, 
            teamTable.draw,
            teamTable.goalfor,
            teamTable.goalagainst,
            teamTable.goaldiff, 
            teamTable.point);
    }

    public Team getTeam() {
        return team;
    }
    public void setTeam(Team team) {
        this.team = team;
    }

    public int getPlayed() {
        return played;
    }
    public void setPlayed(int played) {
        this.played = played;
    }

    public int getWon() {
        return won;
    }
    public void setWon(int won) {
        this.won = won;
    }

    public int getLoss() {
        return loss;
    }
    public void setLoss(int loss) {
        this.loss = loss;
    }

    public int getDraw() {
        return draw;
    }
    public void setDrawn(int draw) {
        this.draw = draw;
    }

    public int getGoalFor() {
        return goalfor;
    }
    public void setGoalFor(int goalfor) {
        this.goalfor = goalfor;
    }

    
    public int getGoalAgainst() {
        return goalagainst;
    }
    public void setGoalAgainst(int goalagainst) {
        this.goalagainst = goalagainst;
    }

    public int getGoalDiff() {
        return goaldiff;
    }
    public void setGoalDiff(int goaldiff) {
        this.goaldiff = goaldiff;
    }

    public int getPoint() {
        return point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}
