package com.dragos.game.states;

import com.dragos.game.util.KeyHandler;
import com.dragos.game.util.MouseHandler;

import java.awt.Graphics2D;

public abstract class GameState {


    private GameStateManager gsm;
    public GameState(GameStateManager gsm){
        this.gsm = gsm;

    }

    public abstract void update();
    public abstract void input(MouseHandler mouse, KeyHandler key);
    public abstract  void render(Graphics2D g);




}
