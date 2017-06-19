package com.giancarlo.tesis.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.BaseDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giancarlo.tesis.Screens.PlayScreen;
import com.giancarlo.tesis.Sprites.Enemy_Demon;
import com.giancarlo.tesis.Sprites.Personaje;
import com.giancarlo.tesis.TesisMain;

/**
 * Created by Giancarlo on 22/08/2016.
 */
public class Question implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer worldtimer;
    private float timecount;
    private Integer score;
    private PlayScreen screen;

    Label countdown;
    Label worldlabel;
    TextField textField;
    TextButton opcion1,opcion2,opcion3;
    public static int personaje_pregunta = 0;
    //Este es el personaje que hace la pregunta y
    //deberia desaparecer cuando se solucione la misma.

    public Question(SpriteBatch spriteBatch, PlayScreen screen){
        score = 0;
        viewport = new FitViewport(TesisMain.V_WIDTH,TesisMain.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        this.screen= screen;
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        Image backImage = new Image(new Texture(Gdx.files.internal("skin/fondo.png")));

        worldlabel = new Label("Ejercicio 001:",new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        worldlabel.setTouchable(Touchable.enabled);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        opcion1 = new TextButton("A",skin);
        opcion2 = new TextButton("B",skin);
        opcion3 = new TextButton("C",skin);
        Listeners();

        table.add(worldlabel).expandX().padTop(50);
        table.row();
        table.add(opcion1);
        table.row();
        table.add(opcion2);
        table.row();
        table.add(opcion3);
        stage.addActor(backImage);
        stage.addActor(table);
    }
    public void Listeners(){
        worldlabel.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                Gdx.app.log("SCORE","touched");
                return true;
            }
        });
        opcion1.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                Gdx.app.log("Opcion A","touched");
                Enemy_Demon.instancias.get(personaje_pregunta).body.setActive(false);
                Enemy_Demon.instancias.remove(personaje_pregunta);
                screen.state = PlayScreen.State.Running;
                return true;
            }
        });
    }

    @Override
    public void dispose() { stage.dispose(); }

}
