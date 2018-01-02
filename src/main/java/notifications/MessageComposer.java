package notifications;

import exerciseCreator.Outcome;

public class MessageComposer {
    Outcome outcome;
    String opening = "";
    String closing = "";

    public MessageComposer(Outcome outcome) {
        this.outcome = outcome;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public String getOpening() {
        return opening;
    }

    public void setOpening(String opening) {
        this.opening = opening;
    }

    public String getClosing() {
        return closing;
    }

    public void setClosing(String closing) {
        this.closing = closing;
    }

    public String composeMessage() {
        return "Szanowny " + outcome.getFirstName() + " " + outcome.getLastName() + "\n" +
                "Twoje zgłoszenie zostało ocenione. \n" +
                getOpening() + "\n" +
                "Ocena: " + outcome.getGrade() + "\n" +
                "Komentarze: " + outcome.getComments() + "\n" +
                getClosing();
    }
}
