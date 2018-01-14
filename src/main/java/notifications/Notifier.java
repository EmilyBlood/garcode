package notifications;

import exerciseCreator.executor.Outcome;

public interface Notifier {

    void sendResults(Outcome outcome);
}
