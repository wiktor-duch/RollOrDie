package fileHandling;

public class Save implements java.io.Serializable {
    private static final long serialVersionUID = 979678546908067891L;
    private String saveName;
    private String playerName;
    private int gamesWon;
    private int gamesLost;
    private int money;
    private int totalMoneyEarned;

    public Save(String saveName, String playerName, int gamesWon, int gamesLost, int money, int totalMoneyEarned) {
        this.saveName = saveName;
        this.playerName = playerName;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.money = money;
        this.totalMoneyEarned = totalMoneyEarned;
    }

    public void updateMoney(int money) {
        // if player lost money
        if (money < 0 ) { 
            this.money += money;
        }
        else {
            this.money += money;
            this.totalMoneyEarned += money;
        }
    }

    public void updateGamesStatistics(int game) {
        if (game > 0) {
            this.gamesWon += 1;
        } 
        else {
            this.gamesLost += 1;
        }
    }


    // setters
    public void setSaveName (String saveName) {
        this.saveName = saveName;
    }

    public void setPlayerName (String playerName) {
        this.playerName = playerName;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public void setTotalMoneyEarned(int totalMoneyEarned) {
        this.totalMoneyEarned = totalMoneyEarned;
    }

    // getters
    public String getSaveName () {
        return saveName;
    }

    public String getPlayerName () {
        return playerName;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public int getMoney() {
        return money;
    }

    public int getTotalMoneyEarned() {
        return totalMoneyEarned;
    }
}
