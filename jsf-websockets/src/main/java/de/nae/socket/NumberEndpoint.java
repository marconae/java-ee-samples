package de.nae.socket;

import lombok.extern.java.Log;

import javax.inject.Inject;
import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.util.logging.Level;

@Log
@ServerEndpoint("/number")
public class NumberEndpoint {

    @Inject
    private MessageBroker messageBroker;

    @OnOpen
    public void open(Session session, EndpointConfig config) {
        log.log(Level.INFO, "OnOpen for Session {0}", session.getId());
        messageBroker.registerSession("number", session);
    }

    @OnMessage
    public void message(String message, Session session) {
        log.log(Level.INFO, "OnMessage for Session {}", session.getId());
        log.log(Level.INFO, "Message: {}", message);
    }

    @OnClose
    public void close(Session session, CloseReason closeReason) {
        log.log(Level.INFO, "OnClose for Session {} with reason {}", new Object[] { session.getId(), closeReason.getReasonPhrase() });
        messageBroker.closeSession("number", session);
    }
}
