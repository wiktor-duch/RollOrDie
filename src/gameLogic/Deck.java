package gameLogic;

import java.util.ArrayList;

public class Deck {
    private ArrayList<Die> dices;

    public Deck() {
        ArrayList<Die> dices = new ArrayList<Die>();
        for (int i=0; i<5; i++) {
            Die d = new Die();
            d.roll();
            dices.add(d);
        }
        this.dices = dices;
    }

    public ArrayList<Die> getDices() {
        return dices;
    }

    public void printDeck() {
        String line1 = "";
        String line2 = "";
        String line3 = "";
        for (int i = 0; i<5; i++) {
            line1 = String.join("", line1, " ┌───┐");
            if (dices.get(i).getFaceValue() == Die.FaceValue.LOST) {
                line2 = String.join("", line2, " │   │");
            }
            else if (dices.get(i).getFaceValue() == Die.FaceValue.DEMON) {
                line2 = String.join("", line2, " │ ♛ │");
            }
            else if (dices.get(i).getFaceValue() == Die.FaceValue.ROOK) {
                line2 = String.join("", line2, " │ ♜ │");
            }
            else if (dices.get(i).getFaceValue() == Die.FaceValue.KNIGHT) {
                line2 = String.join("", line2, " │ ♝ │");
            }
            else if (dices.get(i).getFaceValue() == Die.FaceValue.HORSE) {
                line2 = String.join("", line2, " │ ♘ │");
            }
            else if (dices.get(i).getFaceValue() == Die.FaceValue.SWORDS) {
                line2 = String.join("", line2, " │ ⚔ │");
            }
            else if (dices.get(i).getFaceValue() == Die.FaceValue.SHIELD) {
                line2 = String.join("", line2, " │ ♔ │");
            }
            
            line3 = String.join("", line3, " └───┘");
        }
        System.out.println(line1);
        System.out.println(line2);
        System.out.println(line3);
    }

    public void updateDeck(int position, Die newDie) {
        dices.set(position, newDie);
    }

    public void reroll(int position) {
        Die d = dices.get(position);
        d.roll();
        dices.set(position, d);
    }

    public Die getDie(int position) {
        return dices.get(position);
    }

    public static void main(String[] args) {
        Deck d = new Deck();
        d.printDeck();
        d.reroll(1);
        d.printDeck();
        d.reroll(2);
        d.printDeck();
    }
}
