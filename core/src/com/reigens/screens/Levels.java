package com.reigens.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


/**
 * Created by Rich on 8/8/2014.
 */
public class Levels implements Screen
{

    private Stage stage;
    private Table table;
    private TextureAtlas atlas;
    private Skin skin;
    private List list;
    private ScrollPane scrollPane;
    private TextButton playButton, backButton;

    @Override
    public void render(float delta)
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        stage.act(delta);
        stage.draw();

     //   Table.drawDebug(stage);
    }

    @Override
    public void resize(int width, int height)
    {

    }

    @Override
    public void show()
    {
        stage = new Stage();

        Gdx.input.setInputProcessor(stage);

        atlas = new TextureAtlas("ui/atlas.pack");
        skin = new Skin(Gdx.files.internal("ui/menuSkin.json"), atlas);

        table = new Table(skin);
        table.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        table.debug();


        list = new List(skin);
        list.setItems(new String[]{"one", "two", "three"});

        scrollPane = new ScrollPane(list, skin);

        playButton = new TextButton("Play", skin);
        playButton.pad(15);

        backButton = new TextButton("Back", skin, "small");
        backButton.addListener(new ClickListener(){
            @Override
        public void clicked(InputEvent event, float x, float y) {
                ((Game)Gdx.app.getApplicationListener()).setScreen(new MainMenu());
            }
        });
        backButton.pad(10);

        table.add().width(table.getWidth() / 3);
        table.add("Select Level").width(table.getWidth() / 3);
        table.add().width(table.getWidth() / 3).row();
        table.add(scrollPane).left().expandY();
        table.add(playButton);
        table.add(backButton).bottom().right();


        stage.addActor(table);


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



    }
}
