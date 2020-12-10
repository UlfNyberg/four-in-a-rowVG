package Tile;

import java.awt.*;

/**
 * Created by: Ulf Nyberg
 * Date: 2020-12-10
 * Time: 11:14
 * Project: four-in-a-rowVG
 * Copyright: MIT
 */
public class EmptyTile extends Tile {
    public EmptyTile(Point position) {
        super(position);
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(Color.BLACK);
        g.fillOval(0,0,g.getClipBounds().width,g.getClipBounds().height);
    }
}
