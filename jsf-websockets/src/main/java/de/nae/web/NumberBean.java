package de.nae.web;

import javax.ejb.Stateless;
import java.util.Random;

@Stateless
public class NumberBean {

    public int getRandomNumber() {
        return new Random().nextInt(100);
    }

}
