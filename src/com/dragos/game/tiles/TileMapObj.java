package com.dragos.game.tiles;

import com.dragos.game.tiles.blocks.Block;
import com.dragos.game.graphics.Sprite;
import com.dragos.game.util.Vector2f;
import com.dragos.game.tiles.blocks.HoleBlock;
import com.dragos.game.tiles.blocks.ObjBlock;
import java.awt.Graphics2D;
import java.util.HashMap;

public class TileMapObj  extends TileMap{

    public static HashMap<String,Block> tmo_blocks;

    public TileMapObj(String data, Sprite sprite, int width, int height, int tileWidth, int tileHeight, int tileColumns ){
        Block tempBlock;
        tmo_blocks = new HashMap<String, Block>();

        String[] block = data.split(",");
        for(int i = 0; i < (width * height); ++i){
            int temp = Integer.parseInt(block[i].replaceAll("\\s+",""));
            //System.out.println(temp);
            if(temp!=0){
                if(temp != 28 && temp != 52 ){
                    tempBlock = new HoleBlock(sprite.getSprite( (int) (( temp - 1 ) % tileColumns),(int)((temp-1) / tileColumns)), new Vector2f((int)(i%width)*tileWidth,(int)(i/width)*tileHeight),tileWidth,tileHeight);

                } else{
                    tempBlock = new ObjBlock(sprite.getSprite( (int) (( temp - 1 ) % tileColumns),(int)((temp-1) / tileColumns)), new Vector2f((int)(i%width)*tileWidth,(int)(i/width)*tileHeight),tileWidth,tileHeight);

                    System.out.println( (int)(i%width)*tileWidth+" "+(int)(i/height)*tileHeight);
                }
                tmo_blocks.put(String.valueOf((int)(i%width))+","+String.valueOf((int)(i/height)),tempBlock);
            }
        }
    }

    public void render(Graphics2D g){
        for(Block block: tmo_blocks.values()){
            block.render(g);
        }
    }
}