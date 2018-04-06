package org.tommyraid;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

/**
 *
 * Created by giovanni on 6/01/17.
 */
public class WorldOrtographic implements ApplicationListener {

    private final int WORLD_WIDTH = 100;
    private final int WORLD_HEIGHT = 100;

    private OrthographicCamera camera;
    private SpriteBatch spriteBatch;

    // Test assets to be drawn
    private Texture dropImage;
    private Array<Rectangle> platformBlocks;

    @Override
    public void create() {

        //
        // Constructs a new OrthographicCamera, using the given viewport
        // width and height Height is multiplied by aspect ratio.
        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(30, 30 * (h / w));
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
        camera.update();

        //
        // Initialize assets to draw
        dropImage = new Texture(Gdx.files.internal("droplet.png"));
        platformBlocks = new Array<Rectangle>();
        spriteBatch = new SpriteBatch();

        // Create platform blocks
        createDrops();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void render() {
        handleInput();
        camera.update();
        spriteBatch.setProjectionMatrix(camera.combined);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw a few drops TODO Replace with platform blocks
        drawDrops();
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void dispose() {

    }

    private void createDrops() {
        for (int i = 0; i < 1000; i += 10) {
            Rectangle rainDrop = new Rectangle();
            rainDrop.x = 10 + i * 10;
            rainDrop.y = 200;
            rainDrop.width = 64;
            rainDrop.height = 64;

            platformBlocks.add(rainDrop);
        }
    }

    private void drawDrops() {
        spriteBatch.begin();
        for (Rectangle rainDrop : platformBlocks) {
            spriteBatch.draw(dropImage, rainDrop.x, rainDrop.y);
        }
        spriteBatch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            camera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            camera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            camera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            camera.translate(0, 3, 0);
        }

        camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100/ camera.viewportWidth);

        float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
        float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

        camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
        camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
    }
}
