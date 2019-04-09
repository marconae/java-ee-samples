package de.nae.web;

import de.nae.socket.MessageBroker;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.UUID;

@Named
public class SocketBean {

    @Inject
    private MessageBroker messageBroker;

    public void triggerSocket() {
        messageBroker.sendMessage("number", UUID.randomUUID().toString());
    }
}
