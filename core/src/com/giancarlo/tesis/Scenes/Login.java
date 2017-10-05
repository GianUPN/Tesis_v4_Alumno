package com.giancarlo.tesis.Scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giancarlo.tesis.Screens.PlayScreen;
import com.giancarlo.tesis.Sprites.Enemy_Demon;
import com.giancarlo.tesis.TesisMain;

/**
 * Created by Giancarlo on 05/10/2017.
 */

public class Login implements Disposable {

    public Stage stage;
    private Viewport viewport;
    private Integer worldtimer;
    private float timecount;
    private Integer score;
    private PlayScreen screen;
    private  Pregunta pregunta;
    Label countdown;
    Label worldlabel;
    TextField txt_login,txt_pass;
    TextButton btn_login;

    public static int personaje_pregunta = 0;
    //Este es el login, agregado para evitar
    // poner a los usuarios uno por unos

    public Login(SpriteBatch spriteBatch, PlayScreen screen){
        score = 0;
        viewport = new FitViewport(TesisMain.V_WIDTH,TesisMain.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        this.screen= screen;
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.top();
        table.setFillParent(true);
        Image backImage = new Image(new Texture(Gdx.files.internal("skin/fondo.png")));
        this.pregunta = pregunta;

        worldlabel = new Label("Iniciar Sesión:"
                ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldlabel.setWidth(100f);

        //worldlabel.setWrap(true);
        worldlabel.setTouchable(Touchable.enabled);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        txt_login = new TextField("Usuario",skin);
        txt_pass = new TextField("password",skin);
        txt_pass.setPasswordMode(true);
        txt_pass.setPasswordCharacter('*');
        btn_login = new TextButton("Ingresar",skin);
        Listeners();

        table.add(worldlabel).padTop(15).center();
        table.row();
        table.add(txt_login);
        table.row();
        table.add(txt_pass);
        table.row();
        table.add(btn_login);
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
        btn_login.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {

                Gdx.app.log("Login","touched");
                screen.state = PlayScreen.State.Running;
                return true;
            }
        });
        txt_login.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                txt_login.setText("");
                return true;
            }
        });
        txt_pass.addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y,
                                     int pointer, int button) {
                txt_pass.setText("");
                return true;
            }
        });
    }

    @Override
    public void dispose() {
            stage.dispose();
    }
}
