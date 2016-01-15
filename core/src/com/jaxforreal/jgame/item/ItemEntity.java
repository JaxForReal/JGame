package com.jaxforreal.jgame.item;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.RepeatAction;
import com.jaxforreal.jgame.Util;
import com.jaxforreal.jgame.entity.Entity;

public class ItemEntity extends Entity {
    private ItemData itemData;

    public ItemEntity(ItemData itemData) {
        super(itemData.gameManager);
        this.itemData = itemData;
        addAction(Actions.repeat(RepeatAction.FOREVER, Actions.sequence(
                Actions.moveBy(0, 20, 1),
                Actions.moveBy(0, -20, 1)
        )));

        setScale(0.5f);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Util.drawWithActorProperties(batch, this, itemData.texture);
    }

    @Override
    public Entity getClone() {
        return new ItemEntity(itemData);
    }
}
