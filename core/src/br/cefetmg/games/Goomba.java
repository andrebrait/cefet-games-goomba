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
	private float goombaX, goombaY, animationTime, frameTime;

	private Animation<TextureRegion> downAnimation, leftAnimation, upAnimation, rightAnimation;
	private Animation<TextureRegion> currentAnimation;

	int width, height;

	public Goomba(Texture goombaTexture) {
		this.width = 21;
		this.height = 24;
		this.frameTime = 0.1f;
		TextureRegion[][] tr = TextureRegion.split(goombaTexture, width, height);
		this.downAnimation = new Animation<TextureRegion>(frameTime, tr[0]);
		this.rightAnimation = new Animation<TextureRegion>(frameTime, tr[1]);
		this.upAnimation = new Animation<TextureRegion>(frameTime, tr[2]);
		this.leftAnimation = new Animation<TextureRegion>(frameTime, tr[3]);
		this.downAnimation.setPlayMode(PlayMode.LOOP_PINGPONG);
		this.rightAnimation.setPlayMode(PlayMode.LOOP_PINGPONG);
		this.upAnimation.setPlayMode(PlayMode.LOOP_PINGPONG);
		this.leftAnimation.setPlayMode(PlayMode.LOOP_PINGPONG);
		this.currentAnimation = downAnimation;
		this.animationTime = 0.0f;
	}

	public void render(SpriteBatch batch) {
		batch.draw(currentAnimation.getKeyFrame(animationTime), (int) goombaX, (int) goombaY);
	}

	public void update(float delta, Texture map) {
		float deltaX = 0.0f, deltaY = 0.0f;
		float totalSpeed = delta * goombaSpeed;
		if (Gdx.input.isKeyPressed(Keys.DPAD_LEFT)) {
			currentAnimation = leftAnimation;
			deltaX -= totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_RIGHT)) {
			currentAnimation = rightAnimation;
			deltaX += totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_UP)) {
			currentAnimation = upAnimation;
			deltaY += totalSpeed;
		}
		if (Gdx.input.isKeyPressed(Keys.DPAD_DOWN)) {
			currentAnimation = downAnimation;
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
