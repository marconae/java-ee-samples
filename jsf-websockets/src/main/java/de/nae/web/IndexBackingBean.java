package de.nae.web;

import lombok.Getter;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class IndexBackingBean {

    @Inject
    private NumberBean numberBean;

    @Getter
    private String helloMessage = "Hello Message";

    public int getRandomNumber() {
        return numberBean.getRandomNumber();
    }

}
