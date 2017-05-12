package com.giancarlo.tesis.Scenes;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.Viewport;

/**
 * Created by Giancarlo on 08/05/2017.
 */

public class Question implements Disposable {

    public Stage stage;
    private Viewport viewport;
    private String Pregunta;
    private float timecount;
    private Integer score;

    Label pregunta;
    Label worldlabel;
    TextField textField;

    @Override
    public void dispose() {

    }
}
