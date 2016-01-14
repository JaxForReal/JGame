package com.jaxforreal.jgame.tile;

import com.badlogic.gdx.utils.ObjectMap;
import com.jaxforreal.jgame.MyCloneable;

/**
 * possibly use this for Tile and Entity getCone() ObjectMaps
 */
public class CloneMap<KType, VType extends MyCloneable> {
    private ObjectMap<KType, VType> objectMap;

    public CloneMap() {
        objectMap = new ObjectMap<KType, VType>();
    }

    public void put(KType key, VType value) {
        objectMap.put(key, value);
    }

    @SuppressWarnings("unchecked")
    public VType get(KType key) {
        return (VType) objectMap.get(key).getClone();
    }

}
