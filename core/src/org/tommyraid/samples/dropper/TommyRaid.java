package org.tommyraid.samples.dropper;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * Created by giovanni on 25/12/16.
 */
public class TommyRaid extends Game {

    private SpriteBatch batch;
    private BitmapFont bitmapFont;

    @Override
    public void create() {
        batch = new SpriteBatch();

        // Using defaults arial [Configured by Bitmap font]
        bitmapFont = new BitmapFont();
        this.setScreen(new MainMenuScreen(this, batch, bitmapFont));
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();

        batch.dispose();
        bitmapFont.dispose();
    }
}
