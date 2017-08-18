package br.cefetmg.games;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Goomba {

	private float goombaSpeed = 20.0f;
	private float goombaX, goombaY, animationTime;

	private Animation<TextureRegion> goombaAnimation;

	int width, height;

	public Goomba(Texture goombaTexture) {
		this.width = 21;
		this.height = 24;
		TextureRegion[][] tr = TextureRegion.split(goombaTexture, width, height);
		this.goombaAnimation = new Animation<TextureRegion>(0.1f, tr[0]);
		this.goombaAnimation.setPlayMode(PlayMode.LOOP_PINGPONG);
		this.animationTime = 0.0f;
	}

	public void render(SpriteBatch batch) {
		batch.draw(goombaAnimation.getKeyFrame(animationTime), (int) goombaX, (int) goombaY);
	}

	public void update(float delta, Texture map) {
		float deltaX = 0.0f, deltaY = 0.0f;
		float totalSpeed = delta * goombaSpeed;
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			deltaX -= totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			deltaX += totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			deltaY += totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			deltaY -= totalSpeed;
		}

		float newGoombaX = goombaX + deltaX;
		float newGoombaY = goombaY + deltaY;
		if (newGoombaX >= 0.0f && newGoombaX <= map.getWidth() - width) {
			goombaX = newGoombaX;
		}
		if (newGoombaY >= 0.0f && newGoombaY <= map.getHeight() - height) {
			goombaY = newGoombaY;
		}

		animationTime += delta;
	}

}
