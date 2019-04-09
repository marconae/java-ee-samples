package de.nae;

import lombok.Getter;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
public class IndexBean implements Serializable {

    private static final long serialVersionUID = -2412709636222988281L;

    @Getter
    private String message = "Message 0";

    private int messageCounter = 0;

    @Inject
    private PushBean pushBean;

    public void testAction() {
        messageCounter++;
        message = "Message " + messageCounter;
        pushBean.sendMessage();
    }

}
