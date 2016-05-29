package com.mygdx.game.desktop;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: US
 * Date: 22.05.16
 * Time: 15:55
 * To change this template use File | Settings | File Templates.
 */
public class Resources {

    private Properties properties = new Properties();

    public Resources() {
        InputStream in = getClass().getClassLoader().getResourceAsStream("com/mygdx/game/desktop/resources/config.properties");
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    public Properties getProperties() {
        return properties;
    }
}
