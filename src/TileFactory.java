import java.awt.*;
import java.awt.event.MouseAdapter;

/**
 * Created by: Ulf Nyberg
 * Date: 2020-12-10
 * Time: 11:14
 * Project: four-in-a-rowVG
 * Copyright: MIT
 */
public class TileFactory {
    private MouseAdapter mouseAdapter;

    public TileFactory(MouseAdapter ma){
        this.mouseAdapter = ma;
    }

    public Tile createEmptyTile(Point point){
        Tile emptyTile = new EmptyTile(point);
        emptyTile.addMouseListener(mouseAdapter);
        return emptyTile;
    }

    public Tile createPlayerTile(Point point, Color color, int player){
        return new PlayerTile(point, color, player);
    }
}
