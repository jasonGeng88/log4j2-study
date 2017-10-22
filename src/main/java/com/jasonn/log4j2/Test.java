package com.jasonn.log4j2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;

import java.util.UUID;

/**
 * Created by jason-geng on 10/22/17.
 */
public class Test {

    private static final Logger logger = LogManager.getLogger();

    public static void main(String[] args) {

        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                public void run() {
                    ThreadContext.put("id", UUID.randomUUID().toString()); // Add the fishtag;
                    logger.debug("Message 1");
                    logger.debug("Message 2");
                    ThreadContext.clearAll();
                }
            }).start();
        }

    }

}
