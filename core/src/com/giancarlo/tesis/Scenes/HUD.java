package com.giancarlo.tesis.Scenes;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.giancarlo.tesis.Screens.PlayScreen;
import com.giancarlo.tesis.TesisMain;

public class HUD implements Disposable {
    public Stage stage;
    private Viewport viewport;
    private Integer worldtimer;

    Puntaje puntaje;

    Label worldlabel,worldlabel2;

    public HUD(SpriteBatch spriteBatch, PlayScreen screen){
        puntaje = Puntaje.getinstancia();
        viewport = new FitViewport(TesisMain.V_WIDTH,TesisMain.V_HEIGHT,new OrthographicCamera());
        stage = new Stage(viewport,spriteBatch);
        Gdx.input.setInputProcessor(stage);
        Table table = new Table();
        table.top();
        table.setFillParent(true);

        worldlabel = new Label("Puntaje: "+ puntaje.getPuntaje()
                ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));

        worldlabel2 = new Label("Errores: " + puntaje.getIncorrectas()
                ,new Label.LabelStyle(new BitmapFont(), Color.WHITE));
        //worldlabel.setWidth(100f);

        //worldlabel.setWrap(true);
        //worldlabel.setTouchable(Touchable.enabled);
        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        table.add().colspan(2).fillX();
        table.row();
        table.add(worldlabel).expandX();
        table.add(worldlabel2).expandX();
        stage.addActor(table);
    }
    public void update(){
        worldlabel.setText("Puntaje: "+ puntaje.getPuntaje());
        worldlabel2.setText(" Errores: " + puntaje.getIncorrectas());
    }

    @Override
    public void dispose() {
        stage.dispose();
    }
}
