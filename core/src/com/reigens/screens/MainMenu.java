package com.reigens.screens;

import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenManager;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.reigens.MasterWarrior;
import com.reigens.tween.ActorAssessor;


/**
 * Created by Rich on 8/8/2014.
 */
public class MainMenu implements Screen
{
    private Stage stage;
    private TextureAtlas atlas;
    private Skin skin;
    private Table table;
    private TextButton buttonPlay, buttonExit;
  //  private BitmapFont blue, black, white;
    private Label heading;
    private TweenManager tweenManager;


    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        tweenManager.update(delta);

        stage.act(delta);
        stage.draw();

        //Table.drawDebug(stage);
    }

    @Override
    public void resize(int width, int height)
    {
        stage.getViewport().update(width, height, true);
        table.invalidateHierarchy();
        table.setSize(width, height);

    }

    @Override
    public void show()
    {
        stage = new Stage(new ScreenViewport());

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/button.pack");
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
/*

        blue = new BitmapFont(Gdx.files.internal("font/blue.fnt"), false);
        black = new BitmapFont(Gdx.files.internal("font/black.fnt"), false);
        white = new BitmapFont(Gdx.files.internal("font/white.fnt"), false);
*/

       /* //Creating buttons
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.up = skin.getDrawable("button.up");
        textButtonStyle.down = skin.getDrawable("button.down");
        textButtonStyle.pressedOffsetX = 1;
        textButtonStyle.pressedOffsetY = -1;
        textButtonStyle.font = black;*/

        //Creating Heading
        //  Label.LabelStyle headingStyle = new Label.LabelStyle(white, Color.WHITE);

        heading = new Label(MasterWarrior.TITLE, skin);
        heading.setFontScale(1.5f);


        buttonPlay = new TextButton("Play", skin);
        buttonPlay.addListener(new ClickListener()
        {
            @Override
            public void clicked(InputEvent event, float x, float y)
            {
                ((Game) Gdx.app.getApplicationListener()).setScreen(new Levels());
            }
        });
        buttonPlay.pad(15);


        buttonExit = new TextButton("Exit", skin);
        buttonExit.addListener(new ClickListener(){
            @Override
        public void clicked(InputEvent event, float x, float y)
            {Gdx.app.exit();
            }
        });
        buttonExit.pad(15);

        //Add to table
        table.add(heading);
        table.getCell(heading).spaceBottom(200);
        table.row();
        table.add(buttonPlay);
        table.getCell(buttonPlay).spaceBottom(50);
        table.row();
        table.add(buttonExit);
        // table.debug();//remove later
        stage.addActor(table);

        //Creating animations for menu
        tweenManager = new TweenManager();
        Tween.registerAccessor(Actor.class, new ActorAssessor());

        //Heading Color Animation
        Timeline.createSequence().beginSequence()
                .push(Tween.to(heading, ActorAssessor.RGB, .5f).target(0, 0, 1))
                .push(Tween.to(heading, ActorAssessor.RGB, .5f).target(0, 1, 0))
                .push(Tween.to(heading, ActorAssessor.RGB, .5f).target(1, 0, 0))
                .push(Tween.to(heading, ActorAssessor.RGB, .5f).target(1, 1, 0))
                .push(Tween.to(heading, ActorAssessor.RGB, .5f).target(0, 1, 1))
                .push(Tween.to(heading, ActorAssessor.RGB, .5f).target(1, 0, 1))
                .push(Tween.to(heading, ActorAssessor.RGB, .5f).target(1, 1, 1))
                .end().repeat(Tween.INFINITY, 0).start(tweenManager);

        //Heading and Buttons Fade in
        Timeline.createSequence().beginSequence()
                .push(Tween.set(buttonPlay, ActorAssessor.ALPHA).target(0))
                .push(Tween.set(buttonExit, ActorAssessor.ALPHA).target(0))
                .push(Tween.from(heading, ActorAssessor.ALPHA, .25f).target(0))
                .push(Tween.to(buttonPlay, ActorAssessor.ALPHA, 2f).target(1))
                .push(Tween.to(buttonExit, ActorAssessor.ALPHA, 2.5f).target(1))
                .end().start(tweenManager);

        //Table fade in
        Tween.from(table, ActorAssessor.ALPHA, .5f).target(0).start(tweenManager);
        Tween.from(table, ActorAssessor.Y, 2f).target(Gdx.graphics.getHeight() / 4).start(tweenManager);


    }

    @Override
    public void hide()
    {

    }

    @Override
    public void pause()
    {

    }

    @Override
    public void resume()
    {

    }

    @Override
    public void dispose()
    {
        stage.dispose();
        atlas.dispose();
        skin.dispose();
        // blue.dispose();
        //    black.dispose();
        // white.dispose();


    }
}
