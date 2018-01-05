package notifications;

import exerciseCreator.Outcome;

public interface Notifier {
    void sendResults(Outcome outcome);
}
