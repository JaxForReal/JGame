package com.jaxforreal.jgame;

import com.badlogic.gdx.utils.ArrayMap;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.ObjectMap;
import com.jaxforreal.jgame.tile.Tile;
import com.jaxforreal.jgame.tile.TileList;

public class ProceduralMapLoader {
    private GameManager gameManager;
    private TileList tileList;
    private DiamondSquareGenerator generator;
    private ArrayMap<Range, Tile> tileThresholds;

    public ProceduralMapLoader(GameManager gameManager) {
        this.gameManager = gameManager;
        tileList = new TileList(gameManager);

        tileThresholds = new ArrayMap<Range, Tile>(true, 16);
        tileThresholds.put(new Range(0, 33), tileList.WATER);
        tileThresholds.put(new Range(32, 66), tileList.GRASS);
        tileThresholds.put(new Range(65, 100), tileList.STONE);
    }

    public Map load(int size) {
        Map map = new Map(size, size);
        generator = new DiamondSquareGenerator(size, 50, 50, 2, gameManager.random.nextLong());
        float[][] gen = generator.generate();

        for (int iterX = 0; iterX < gen.length; iterX++) {
            for (int iterY = 0; iterY < gen.length; iterY++) {
                float val = gen[iterX][iterY];
                map.setTileAt(iterX, iterY, getTileFromValue(val).getClone());
            }
        }

        return map;
    }

    private Tile getTileFromValue(float value) {
        for(ObjectMap.Entry<Range, Tile> entry : tileThresholds) {
            if(entry.key.isValueWithin(value)){
                return entry.value;
            }
        }
        throw new GdxRuntimeException("No suitable tiles found for value: " + value);
    }

    private class Range {
        protected float lower;
        protected float upper;

        public Range(float lower, float upper) {
            this.lower = lower;
            this.upper = upper;
        }

        public boolean isValueWithin(float value) {
            return (value > lower) && (value < upper);
        }
    }
}
