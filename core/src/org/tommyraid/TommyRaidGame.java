package org.tommyraid;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.TimeUtils;

import java.util.Iterator;

public class TommyRaidGame extends ApplicationAdapter {

	private OrthographicCamera orthographicCamera;
	private SpriteBatch spriteBatch;

	private Texture dropImage;
	private Texture bucketImage;
	private Sound dropSound;
	private Music rainMusic;

	private Rectangle bucket;
	private Array<Rectangle> rainDrops;
	private long lastDropTime;


	@Override
	public void create () {
		dropImage = new Texture(Gdx.files.internal("droplet.png"));
		bucketImage = new Texture(Gdx.files.internal("bucket.png"));

		dropSound = Gdx.audio.newSound(Gdx.files.internal("drop.wav"));
		rainMusic = Gdx.audio.newMusic(Gdx.files.internal("rain.mp3"));

		// Start sounds playback
		rainMusic.setLooping(true);
		rainMusic.play();

		orthographicCamera = new OrthographicCamera();
		orthographicCamera.setToOrtho(false, 800, 400);

		spriteBatch = new SpriteBatch();

		bucket = new Rectangle();
		bucket.x = 800/2 - 64/2;
		bucket.y = 20;
		bucket.width = 64;
		bucket.height= 64;

        rainDrops = new Array<Rectangle>();
        spawnRaindrop();
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(0, 0, 0.2f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		orthographicCamera.update();

		//
        // Bucket management
		if (Gdx.input.isTouched()) {
			Vector3 touchPosition = new Vector3();
			touchPosition.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			orthographicCamera.unproject(touchPosition);
			bucket.x = touchPosition.x - 64/2;
		}

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) bucket.x -= 200 * Gdx.graphics.getDeltaTime();
		if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) bucket.x += 200 * Gdx.graphics.getDeltaTime();

		if (bucket.x < 0) bucket.x = 0;
		if (bucket.x > 800 - 64) bucket.x = 800 - 64;

		//
		// Raindrops management

        // check if we need to create a new raindrop
        if(TimeUtils.nanoTime() - lastDropTime > 1000000000) spawnRaindrop();

        Iterator<Rectangle> iterator = rainDrops.iterator();
        while (iterator.hasNext()) {
            Rectangle rainDrop = iterator.next();
            rainDrop.y -= 200 * Gdx.graphics.getDeltaTime();
            if (rainDrop.y + 64 < 0) {
                iterator.remove();
            }

            //
            // Play drop sound when bucket catches drop
            if (rainDrop.overlaps(bucket)) {
                dropSound.play();
                iterator.remove();
            }
        }

        spriteBatch.begin();
        spriteBatch.draw(bucketImage, bucket.x, bucket.y);
        System.out.println(rainDrops.size);
        for(Rectangle raindrop: rainDrops) {
            spriteBatch.draw(dropImage, raindrop.x, raindrop.y);
        }
        spriteBatch.end();
	}
	
	@Override
	public void dispose () {
		spriteBatch.dispose();
		bucketImage.dispose();
		dropImage.dispose();

		dropSound.dispose();
		rainMusic.dispose();
	}

    private void spawnRaindrop() {
        Rectangle raindrop = new Rectangle();
        raindrop.x = MathUtils.random(0, 800-64);
        raindrop.y = 480;
        raindrop.width = 64;
        raindrop.height = 64;
        rainDrops.add(raindrop);
        lastDropTime = TimeUtils.nanoTime();
    }
}
