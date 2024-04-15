package com.dragos.game.states;

import com.dragos.game.GamePanel;
import com.dragos.game.entity.Player;
import com.dragos.game.graphics.Font;
import com.dragos.game.graphics.Sprite;
import com.dragos.game.tiles.TileManager;
import com.dragos.game.util.KeyHandler;
import com.dragos.game.util.MouseHandler;
import com.dragos.game.util.Vector2f;
import com.dragos.game.tiles.TileManager;
import java.awt.Graphics2D;

public class PlayState extends GameState {
    private Font font;
    private Player player;
    private TileManager tm;

    public static Vector2f map;

    public PlayState(GameStateManager gsm){
        super(gsm);
        map = new Vector2f();
        Vector2f.setWorldVar(map.x, map.y);

        tm = new TileManager("tile/Test.xml");
        font = new Font("font/ZeldaFont.png", 16, 16);
        player = new Player(new Sprite("entity/Caracter.png"), new Vector2f(0 + (GamePanel.vidth / 2), 0 + (GamePanel.height / 2) ),80);
    }


    public void update() {
        Vector2f.setWorldVar(map.x, map.y);
        player.update();
    }

    public void input(MouseHandler mouse, KeyHandler key) {
        player.input(mouse,key);

    }

    public void render(Graphics2D g) {

        tm.render(g);
        Sprite.drawArray(g,font,  "DEMO", new Vector2f(0,0),32,32,16,0);
        player.render(g);
    }

}
