package gameLogic;

public class Die {
    // Names of figures on a die
    enum FaceValue {
        DEMON,
        HORSE,
        KNIGHT,
        ROOK,
        SHIELD,
        SWORDS,
        LOST
    }
    
    Enum<FaceValue> faceValue;

    public Die() {
    }

    public void roll() {
        int randNum = (int) (Math.random()*6);
        switch(randNum) {
            case 0:
                faceValue = FaceValue.DEMON;
                break;
            case 1:
                faceValue = FaceValue.HORSE;
                break;
            case 2:
                faceValue = FaceValue.KNIGHT;
                break;
            case 3:
                faceValue = FaceValue.ROOK;
                break;
            case 4:
                faceValue = FaceValue.SHIELD;
                break;
            default:
                faceValue = FaceValue.SWORDS;
        }
    }

    public Enum<FaceValue> getFaceValue() {
        return faceValue;
    }

    public int getIntValue() {
        if (faceValue == FaceValue.DEMON) {
            return 5;
        }
        else if (faceValue == FaceValue.ROOK) {
            return 4;
        }
        else if (faceValue == FaceValue.KNIGHT) {
            return 3;
        }
        else if (faceValue == FaceValue.HORSE) {
            return 2;
        }
        else if (faceValue == FaceValue.SWORDS) {
            return 1;
        }
        else { // The lowest value has SHIELD
            return 0;
        }
    }

    public String printValue() {
        if (faceValue == FaceValue.DEMON) {
            return "DEMON";
        }
        else if (faceValue == FaceValue.HORSE) {
            return "HORSE";
        }
        else if (faceValue == FaceValue.KNIGHT) {
            return "KNIGHT";
        }
        else if (faceValue == FaceValue.ROOK) {
            return "ROOK";
        }
        else if (faceValue == FaceValue.SHIELD) {
            return "SHIELD";
        }
        else if (faceValue == FaceValue.SWORDS) {
            return "SWORDS";
        } else {
            return "LOST";
        }
    }
}