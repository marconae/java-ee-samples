package de.nae;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import java.util.Random;

@ApplicationScoped
public class PushBean {

    private static final Random RND = new Random();

    @Inject
    @Push(channel = "test-channel")
    private PushContext pushContext;

    public void sendMessage() {
        final String msg = String.format("Hello Socket %d", RND.nextInt());
        pushContext.send(msg);
    }
}
