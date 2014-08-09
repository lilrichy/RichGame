package com.reigens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.reigens.screens.Splash;

public class MasterWarrior extends Game
{
    public static final String TITLE = "Master Warrior";
    public static final String VERSION = "0.0.0.2";

	
	@Override
	public void create () {
	setScreen(new Splash());
	}

    @Override
    public void dispose()
    {
    super.dispose();
    }

	@Override
	public void render () {
	super.render();
	}


    @Override
    public void resize(int width, int height)
    {
super.resize(width, height);
    }



    @Override
    public void pause()
    {
super.pause();
    }

    @Override
    public void resume()
    {
super.resume();
    }


}
