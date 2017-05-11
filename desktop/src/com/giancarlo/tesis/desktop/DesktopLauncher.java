package com.giancarlo.tesis.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.giancarlo.tesis.TesisMain;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "juego";
		config.width = 1200;
		config.height = 624;
		new LwjglApplication(new TesisMain(), config);
	}
}
