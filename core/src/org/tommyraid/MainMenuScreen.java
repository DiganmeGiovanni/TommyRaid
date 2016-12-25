package org.tommyraid;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 *
 * Created by giovanni on 25/12/16.
 */
public class MainMenuScreen implements Screen {

    private TommyRaid tommyRaidGame;
    private OrthographicCamera camera;

    private SpriteBatch batch;
    private BitmapFont bitmapFont;

    public MainMenuScreen(TommyRaid tommyRaidGame, SpriteBatch batch, BitmapFont bitmapFont) {
        this.tommyRaidGame = tommyRaidGame;
        this.batch = batch;
        this.bitmapFont = bitmapFont;

        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 400);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0.2f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.update();
        this.batch.setProjectionMatrix(camera.combined);

        this.batch.begin();
        this.bitmapFont.draw(this.batch, "Bienvenido a Tommy Raid!!! ", 100, 150);
        this.bitmapFont.draw(this.batch, "Toca la pantalla para comenzar!", 100, 100);
        this.batch.end();

        if (Gdx.input.isTouched()) {
            tommyRaidGame.setScreen(new GameScreen(this.batch, this.bitmapFont));
            dispose();
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
