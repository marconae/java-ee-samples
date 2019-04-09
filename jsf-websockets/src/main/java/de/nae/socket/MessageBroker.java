package de.nae.socket;

import lombok.NonNull;
import lombok.extern.java.Log;

import javax.enterprise.context.ApplicationScoped;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.logging.Level;

@Log
@ApplicationScoped
public class MessageBroker {

    private Map<String, Queue<Session>> sessionMap = new ConcurrentHashMap<>();

    public void registerSession(@NonNull final String channel, @NonNull final Session session) {
        sessionMap.computeIfAbsent(channel, k -> new ConcurrentLinkedQueue<>());
        sessionMap.get(channel).add(session);
    }

    public void closeSession(@NonNull final String channel, @NonNull final Session session) {
        if(sessionMap.containsKey(channel))
            sessionMap.get(channel).remove(session);
    }

    public void sendMessage(@NonNull final String channel, @NonNull final String message) {
        if(!sessionMap.containsKey(channel))
            return;

        sessionMap.get(channel).forEach(session -> sendMessage(session, message));
    }

    private void sendMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendText(message);
        } catch (IOException e) {
            final String errorMessage = e.getMessage() != null ? e.getMessage() : "no message";
            log.log(Level.SEVERE, "Session[{0}]: Error sending message, reason: {1}",
                    new Object[] { session.getId(), errorMessage });
        }
    }

}
