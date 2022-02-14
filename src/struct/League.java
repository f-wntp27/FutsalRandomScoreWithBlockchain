package struct;

import java.util.ArrayList;

public class League {
    private int matchNumber;
    private ArrayList<TeamTable> teamTables;
    private MatchMaking match;

    public League(int matchNumber, ArrayList<TeamTable> teamTables) {
        this.matchNumber = matchNumber;
        this.teamTables = teamTables;
    }
    public League(int matchNumber, ArrayList<TeamTable> teamTables, MatchMaking match) {
        this.matchNumber = matchNumber;
        this.teamTables = teamTables;
        this.match = match;
    }
    public League(League league) {
        this(league.matchNumber, league.teamTables, league.match);
    }

    public ArrayList<TeamTable> getTeamTables () {
        return teamTables;
    }
    public void setTeamTables(ArrayList<TeamTable> teamTables) {
        this.teamTables = teamTables;
    }

    public int getMatchNumber() {
        return matchNumber;
    }
    public void setMatchNumber(int matchNumber) {
        this.matchNumber = matchNumber;
    }

    public MatchMaking getMatchMaking() {
        return match;
    }
    public void setMatchMaking(MatchMaking match) {
        this.match = match;
    }

    public void updateLeagueCup() {   
        TeamTable homeTeamTable = findHomeTeamTable();
        int indexHomeTeamTable = findIndexHomeTeamTable();
        TeamTable awayTeamTable = findAwayTeamTable();
        int indexAwayTeamTable = findIndexAwayTeamTable();
        
        if (match.getHomeScore() > match.getAwayScore()) {
            // Home won
            TeamTable newHomeTeamTable = new TeamTable(
                homeTeamTable.getTeam(),
                homeTeamTable.getPlayed() + 1,
                homeTeamTable.getWon() + 1,
                homeTeamTable.getLoss(),
                homeTeamTable.getDraw(),
                homeTeamTable.getGoalFor() + match.getHomeScore(),
                homeTeamTable.getGoalAgainst() + match.getAwayScore(),
                homeTeamTable.getGoalDiff() + match.getHomeScore() - match.getAwayScore(),
                homeTeamTable.getPoint() + 3
            );
            // Away loss
            TeamTable newAwayTeamTable = new TeamTable(
                awayTeamTable.getTeam(),
                awayTeamTable.getPlayed() + 1,
                awayTeamTable.getWon(),
                awayTeamTable.getLoss() + 1,
                awayTeamTable.getDraw(),
                awayTeamTable.getGoalFor() + match.getAwayScore(),
                awayTeamTable.getGoalAgainst() + match.getHomeScore(),
                awayTeamTable.getGoalDiff() + match.getAwayScore() - match.getHomeScore(),
                awayTeamTable.getPoint()
            );

            teamTables.set(indexHomeTeamTable, newHomeTeamTable);
            teamTables.set(indexAwayTeamTable, newAwayTeamTable);
        } else if (match.getHomeScore() < match.getAwayScore()) {
            // Home loss
            TeamTable newHomeTeamTable = new TeamTable(
                homeTeamTable.getTeam(),
                homeTeamTable.getPlayed() + 1,
                homeTeamTable.getWon(),
                homeTeamTable.getLoss() + 1,
                homeTeamTable.getDraw(),
                homeTeamTable.getGoalFor() + match.getHomeScore(),
                homeTeamTable.getGoalAgainst() + match.getAwayScore(),
                homeTeamTable.getGoalDiff() + match.getHomeScore() - match.getAwayScore(),
                homeTeamTable.getPoint()
            );

            // Away won
            TeamTable newAwayTeamTable = new TeamTable(
                awayTeamTable.getTeam(),
                awayTeamTable.getPlayed() + 1,
                awayTeamTable.getWon() + 1,
                awayTeamTable.getLoss(),
                awayTeamTable.getDraw(),
                awayTeamTable.getGoalFor() + match.getAwayScore(),
                awayTeamTable.getGoalAgainst() + match.getHomeScore(),
                awayTeamTable.getGoalDiff() + match.getAwayScore() - match.getHomeScore(),
                awayTeamTable.getPoint() + 3
            );

            teamTables.set(indexHomeTeamTable, newHomeTeamTable);
            teamTables.set(indexAwayTeamTable, newAwayTeamTable);
        } else {
            // drawn
            TeamTable newHomeTeamTable = new TeamTable(
                homeTeamTable.getTeam(),
                homeTeamTable.getPlayed() + 1,
                homeTeamTable.getWon(),
                homeTeamTable.getLoss(),
                homeTeamTable.getDraw() + 1,
                homeTeamTable.getGoalFor() + match.getHomeScore(),
                homeTeamTable.getGoalAgainst() + match.getAwayScore(),
                homeTeamTable.getGoalDiff() + match.getHomeScore() - match.getAwayScore(),
                homeTeamTable.getPoint() + 1
            );

            TeamTable newAwayTeamTable = new TeamTable(
                awayTeamTable.getTeam(),
                awayTeamTable.getPlayed() + 1,
                awayTeamTable.getWon(),
                awayTeamTable.getLoss(),
                awayTeamTable.getDraw() + 1,
                awayTeamTable.getGoalFor() + match.getAwayScore(),
                awayTeamTable.getGoalAgainst() + match.getHomeScore(),
                awayTeamTable.getGoalDiff() + match.getAwayScore() - match.getHomeScore(),
                awayTeamTable.getPoint() + 1
            );

            teamTables.set(indexHomeTeamTable, newHomeTeamTable);
            teamTables.set(indexAwayTeamTable, newAwayTeamTable);
        }
        // System.out.println(this.tableTeams);
    }

    public TeamTable findHomeTeamTable() {
        TeamTable table = new TeamTable();
        for (TeamTable tableTeam : teamTables) {
            if (tableTeam.getTeam() == match.getHomeTeam()) {
                table = tableTeam;
            }
        }
        return table;
    }
    
    public int findIndexHomeTeamTable() {
        int index = -1;
        for (int i = 0; i < teamTables.size(); i++) {
            if (teamTables.get(i).getTeam() == match.getHomeTeam()) {
                index = i;
            }
        }
        return index;
    }
    public TeamTable findAwayTeamTable() {
        TeamTable table = new TeamTable();
        for (TeamTable tableTeam : teamTables) {
            if (tableTeam.getTeam() == match.getAwayTeam()) {
                table = tableTeam;
            }
        }
        return table;
    }
    public int findIndexAwayTeamTable() {
        int index = -1;
        for (int i = 0; i < teamTables.size(); i++) {
            if (teamTables.get(i).getTeam() == match.getAwayTeam()) {
                index = i;
            }
        }
        return index;
    }

    // PRINT DEBUG
    public void printLeague() {
        System.out.println("Team Name\tPlayed\tWon\tLoss\tDrawn\tPoint");
        for (TeamTable teamTable : teamTables) {
            System.out.println(teamTable.getTeam().getName() + "\t\t" + teamTable.getPlayed() + "\t" + teamTable.getWon() + "\t"
                + teamTable.getLoss() + "\t" + teamTable.getDraw() + "\t" + teamTable.getPoint());
        }
        System.out.println();
    }
}
