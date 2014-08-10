package com.reigens.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.reigens.MasterWarrior;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = MasterWarrior.TITLE + "   v" + MasterWarrior.VERSION;
        config.vSyncEnabled = true;
        config.useGL30 = true;
        config.width = 1280;
        config.height = 720;

        new LwjglApplication(new MasterWarrior(), config);
	}
}
