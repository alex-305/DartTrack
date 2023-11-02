import java.util.Random;
public class RandomMessage {

public String generateMessage() {
    String msg = "\0";
    Random random = new Random();
    int randomInt = (random.nextInt(5)+1);
    switch(dartTrack.getMultValue()) {
        case 1:
            msg = random1x(randomInt);
            break;
        case 2:
            msg = random2x(randomInt);
            break;
        case 3:
            msg = random3x(randomInt);
            break;
    }
    return msg;
}

public String generateNextTurn() {
    String msg = "\0";
    Random random = new Random();
    int randomInt = (random.nextInt(5)+1);

    switch(randomInt) {
        case 1:
            msg = "Player " + (dartTrack.getTurn()+1) + "'s turn!";
            break;
        case 2:
            msg = "Now it's Player " + (dartTrack.getTurn()+1) + "'s time to shine!";
            break;
        case 3:
            msg = "Player " + (dartTrack.getTurn()+1) + ", you're up!";
            break;
        case 4:
            msg = "It's your move Player " + (dartTrack.getTurn()+1) + '!';
            break;
        case 5:
            msg = "It's Player " + (dartTrack.getTurn()+1) + "'s turn now!";
            break;
    }
    return msg;
}


public static RandomMessage getInstance() {
    
    if(randomMessage == null) {
        randomMessage = new RandomMessage();
    }
    return randomMessage;
}


private static RandomMessage randomMessage;
private static DartTrack dartTrack = DartTrack.getInstance();

private RandomMessage() {

}


private String random3x(int randomInt) {
    String msg = "\0";
    switch(randomInt) {
        case 1:
            msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " hit a whopping 3x " + dartTrack.getValueToMult());
            break;
        case 2:
            msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " hit the 3x " + dartTrack.getValueToMult() + '!');
            break;
        case 3:
            msg = "Woah! Player " + (dartTrack.getPreviousTurn()+1) + " got a " + dartTrack.getValueToMult() + "x of " + dartTrack.getValueToMult();
            break;
        case 4:
            msg = "Player " + (dartTrack.getPreviousTurn()+1) + " shot a 3x " + dartTrack.getValueToMult() + '!';
            break;
        case 5:
            msg = "Player " + (dartTrack.getPreviousTurn()+1) + " hit a 3x " + dartTrack.getValueToMult() + '!';
            break;
    }
    return msg;
}

private String random2x(int randomInt) {
    String msg = "\0";
    switch(randomInt) {
        case 1:
            msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " got " + dartTrack.getValueToMult() + " with 2x multiplier");
            break;
        case 2:
            msg = "Player " + (dartTrack.getPreviousTurn()+1) + " got a 2x " + dartTrack.getValueToMult();
            break;
        case 3:
            msg = "Player " + (dartTrack.getPreviousTurn()+1) + " hit the 2x " + dartTrack.getValueToMult();
            break;
        case 4:
            msg = "Player " + (dartTrack.getPreviousTurn()+1) + " hit " + dartTrack.getValueToMult() + " with 2x multiplier";
            break;
        case 5:
            msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " shot the 2x " + dartTrack.getValueToMult());
            break;
    }
    return msg;

}

    private String random1x(int randomInt) {
        String msg = "\0";
        switch(randomInt) {
            case 1:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " hit the " + dartTrack.getValueToMult());
                break;
            case 2:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " hit " + dartTrack.getValueToMult());
                break;
            case 3:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " shot the " + dartTrack.getValueToMult());
                break;
            case 4:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " got a " + dartTrack.getValueToMult());
                break;
            case 5:
                msg = ("Player " + (dartTrack.getPreviousTurn()+1) + " scored " + dartTrack.getValueToMult() + " points");
                break;
        }
        return msg;
    }

}