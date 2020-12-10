package Tile;

import javax.swing.*;
import java.awt.*;

/**
 * Created by: Ulf Nyberg
 * Date: 2020-12-10
 * Time: 11:14
 * Project: four-in-a-rowVG
 * Copyright: MIT
 */
public abstract class Tile extends JPanel {
    private Point position;

    public Tile(Point position) {
        this.position = position;
    }

    public Point getPosition() {
        return position;
    }
}
