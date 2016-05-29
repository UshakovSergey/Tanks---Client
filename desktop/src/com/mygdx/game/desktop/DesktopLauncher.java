package com.mygdx.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.mygdx.game.battleField.BattleField;

import java.util.Properties;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();

        Properties properties = new Resources().getProperties();
        config.width = Integer.valueOf(properties.getProperty("width"));
        config.height = Integer.valueOf(properties.getProperty("height"));
        config.title = properties.getProperty("title");

		new LwjglApplication(new BattleField(), config);
	}
}
