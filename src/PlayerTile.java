import java.awt.*;

/**
 * Created by: Ulf Nyberg
 * Date: 2020-12-10
 * Time: 11:15
 * Project: four-in-a-rowVG
 * Copyright: MIT
 */
public class PlayerTile extends Tile{

    private int player;
    private Color color;

    public PlayerTile(Point position, Color color, int player) {
        super(position);
        this.player = player;
        this.color = color;
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(color);
        g.fillOval(0,0,g.getClipBounds().width,g.getClipBounds().height);
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getPlayer() {
        return player;
    }
}
