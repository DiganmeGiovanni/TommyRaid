package org.tommyraid.client;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.backends.gwt.GwtApplication;
import com.badlogic.gdx.backends.gwt.GwtApplicationConfiguration;
import org.tommyraid.samples.TommyRaidGame;

public class HtmlLauncher extends GwtApplication {

        @Override
        public GwtApplicationConfiguration getConfig () {
                return new GwtApplicationConfiguration(800, 400);
        }

        @Override
        public ApplicationListener createApplicationListener () {
                return new TommyRaidGame();
        }
}