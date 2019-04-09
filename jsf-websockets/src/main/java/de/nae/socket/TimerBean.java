package de.nae.socket;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;
import java.util.UUID;
import java.util.logging.Level;

@Log
@Singleton
public class TimerBean {

    @Inject
    private MessageBroker messageBroker;

    @Schedule(second = "*/50", minute = "*", hour = "*", persistent = false)
    public void sendMessage() {
        final String message = "Ping " + UUID.randomUUID().toString();
        log.log(Level.INFO, "Sending {}", message);
        messageBroker.sendMessage("number", message);
    }
}
