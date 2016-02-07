package com.jaxforreal.jgame.item;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.jaxforreal.jgame.GameManager;

public class ItemConainer extends Group{

    public ItemConainer(int width, int height, GameManager gameManager) {
        //add containers
        for(int iterX = 0; iterX < width; iterX ++) {
            for(int iterY = 0; iterY < height; iterY ++) {
                Actor newSlot = new ContainerSlot(gameManager);
                newSlot.addAction(
                        Actions.moveTo(iterX * ContainerSlot.SIZE, iterY * ContainerSlot.SIZE));
            }
        }
    }
}
