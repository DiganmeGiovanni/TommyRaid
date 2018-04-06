package org.tommyraid.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import org.tommyraid.WorldOrtographic;
import org.tommyraid.samples.OrthographicCameraExample;
import org.tommyraid.samples.TommyRaidGame;
import org.tommyraid.samples.dropper.TommyRaid;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Tommy Raid";
		config.width = 800;
		config.height = 400;

		//new LwjglApplication(new OrthographicCameraExample(), config);
        new LwjglApplication(new OrthographicCameraExample(), config);
	}
}
