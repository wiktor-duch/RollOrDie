package gameLogic;

import java.util.ArrayList;

import gameLogic.Die.FaceValue;

public class Poker {
    enum Ranking {
        NOTHING,
        PAIR,
        TWO_PAIRS,
        THREE_OF_A_KIND,
        LOW_STRAIGHT,
        HIGH_STRAIGHT,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND
    }

    public int compareDeck(Deck d1, Deck d2) {
        int highest1 = getIntValue(findHighestRanking(d1));
        int highest2 = getIntValue(findHighestRanking(d2));
        if (highest1 > highest2) {
            return 1;
        }
        else if (highest1 < highest2) {
            return -1;
        }
        else {
            return 0;
        }
        
    }

    public Ranking findHighestRanking(Deck deck) {
        // Convert deck to an array
        int dicesValues[] = new int[6];
        for (int i=0; i<6; i++) {
            dicesValues[i] = 0; // Initialize all values to zero
        }
        ArrayList<Die> dices = deck.getDices();
        for (int i=0; i<5; i++) {
            int value = dices.get(i).getIntValue();
            dicesValues[value] += 1; // Count the occurences of the given value
        }

        // Find highest value in the array
        int highest = 0;
        int pairsNum = 0; // Keep count of the number of pairs
        for (int i=0; i<6; i++) {
            highest = Math.max(highest, dicesValues[i]);
            if (dicesValues[i] == 2) {
                pairsNum += 1;
            }
        }

        // Return the highest rank
        if (highest == 5) {
            return Ranking.FIVE_OF_A_KIND;
        } 
        else if (highest == 4) {
            return Ranking.FOUR_OF_A_KIND;
        }
        else if (highest == 3 && pairsNum == 1) {
            return Ranking.FULL_HOUSE;
        }
        else if (highest == 3) {
            return Ranking.THREE_OF_A_KIND;
        }
        else if (highest == 2 && pairsNum == 2) {
            return Ranking.TWO_PAIRS;
        }
        else if (highest == 2) {
            return Ranking.TWO_PAIRS;
        }
        else {
            // Check for straights
            boolean lowStraightFound = true;
            for (int i=0; i<5; i++) {
                if (dicesValues[i] == 0) { // There is no Low Straights
                    lowStraightFound = false;
                    break;
                }
            }

            boolean highStraightFound = true;
            for (int i=0; i<5; i++) {
                if (dicesValues[i+1] == 0) { // There is no High Straight
                    highStraightFound = false;
                    break;
                }
            }
            
            if (lowStraightFound == true) {
                return Ranking.LOW_STRAIGHT;
            }
            else if (highStraightFound == true) {
                return Ranking.HIGH_STRAIGHT;
            }
            return Ranking.NOTHING;
        }
    }

    private int getIntValue(Enum<Ranking> rank) {
        if (rank == Ranking.PAIR) {
            return 1;
        }
        else if (rank == Ranking.TWO_PAIRS) {
            return 2;
        }
        else if (rank == Ranking.THREE_OF_A_KIND) {
            return 3;
        }
        else if (rank == Ranking.LOW_STRAIGHT) {
            return 4;
        }
        else if (rank == Ranking.HIGH_STRAIGHT) {
            return 5;
        }
        else if (rank == Ranking.FULL_HOUSE) {
            return 6;
        }
        else if (rank == Ranking.LOW_STRAIGHT) {
            return 7;
        }
        else if (rank == Ranking.HIGH_STRAIGHT) {
            return 8;
        }
        else {
            return 0;
        }
    }
}
