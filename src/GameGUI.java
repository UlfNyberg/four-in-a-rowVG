import Tile.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by: Ulf Nyberg
 * Date: 2020-12-10
 * Time: 11:14
 * Project: four-in-a-rowVG
 * Copyright: MIT
 */
public class GameGUI extends JFrame {

    JPanel southPanel = new JPanel();
    JPanel gridPanel = new JPanel();
    JPanel southWestPanel = new JPanel();
    JPanel southEastPanel = new JPanel();
    JPanel southCenterPanel = new JPanel();
    JPanel bottomPanel = new JPanel();
    JPanel scorePanel = new JPanel();


    public JLabel p1ScoreLabel = new JLabel("SPELARE 1 :");
    public JLabel p2ScoreLabel = new JLabel("SPELARE 2 :");
    public JLabel p1ScoreCounter = new JLabel("0");
    public JLabel p2ScoreCounter = new JLabel("0");
    public JLabel spaceLabel = new JLabel("   -   ");

    public Font font1 = new Font("SansSerif", Font.BOLD, 14);
    public Font font2 = new Font("SansSerif", Font.PLAIN, 14);

    public JButton showRulesButton = new JButton("Spelregler");
    public JButton undoButton = new JButton("Ångra");

    public ButtonGroup p1ColorButtongroup = new ButtonGroup();
    public ButtonGroup p2ColorButtongroup = new ButtonGroup();

    public JRadioButton p1Color1 = new JRadioButton("Rött");
    public JRadioButton p1Color2 = new JRadioButton("Blå");
    public JRadioButton p1Color3 = new JRadioButton("Lila");
    public JRadioButton p2Color1 = new JRadioButton("Gult");
    public JRadioButton p2Color2 = new JRadioButton("Rosa");
    public JRadioButton p2Color3 = new JRadioButton("Grönt");

    private Color gridColor = new Color(137,207,240);

    public GameGUI(java.util.List<java.util.List<Tile>> tileList, ActionListener al){

        setLayout(new BorderLayout());
        southPanel.setLayout(new BorderLayout());
        gridPanel.setLayout(new GridLayout(6,7,2,2));
        bottomPanel.setLayout(new BorderLayout());

        gridPanel.setBackground(gridColor);
        southEastPanel.setBackground(gridColor);
        southWestPanel.setBackground(gridColor);
        southCenterPanel.setBackground(gridColor);
        bottomPanel.setBackground(gridColor);
        southPanel.setBackground(gridColor);
        scorePanel.setBackground(gridColor);

        add(bottomPanel, BorderLayout.SOUTH);
        add(gridPanel, BorderLayout.CENTER);

        bottomPanel.add(southPanel, BorderLayout.SOUTH);
        bottomPanel.add(scorePanel, BorderLayout.NORTH);

        p1ColorButtongroup.add(p1Color1);
        p1ColorButtongroup.add(p1Color2);
        p1ColorButtongroup.add(p1Color3);
        p2ColorButtongroup.add(p2Color1);
        p2ColorButtongroup.add(p2Color2);
        p2ColorButtongroup.add(p2Color3);


        p1Color1.setSelected(true);
        p2Color1.setSelected(true);

        scorePanel.add(p1ScoreLabel);
        scorePanel.add(p1ScoreCounter);
        scorePanel.add(spaceLabel);
        scorePanel.add(p2ScoreLabel);
        scorePanel.add(p2ScoreCounter);

        p1ScoreLabel.setFont(font1);
        p2ScoreLabel.setFont(font2);
        p1ScoreCounter.setFont(font1);
        p2ScoreCounter.setFont(font1);

        p1ScoreLabel.setBounds(10,10,1,1);

        southPanel.add(southWestPanel, BorderLayout.WEST);
        southPanel.add(southEastPanel, BorderLayout.EAST);
        southPanel.add(southCenterPanel, BorderLayout.CENTER);

        southWestPanel.add(p1Color1);
        southWestPanel.add(p1Color2);
        southWestPanel.add(p1Color3);
        southEastPanel.add(p2Color1);
        southEastPanel.add(p2Color2);
        southEastPanel.add(p2Color3);

        southCenterPanel.add(showRulesButton);
        southCenterPanel.add(undoButton);

        initiateGameGrid(tileList);

        showRulesButton.addActionListener(al);
        undoButton.addActionListener(al);
        p1Color1.addActionListener(al);
        p1Color2.addActionListener(al);
        p1Color3.addActionListener(al);
        p2Color1.addActionListener(al);
        p2Color2.addActionListener(al);
        p2Color3.addActionListener(al);

        setTitle("Fyra-i-rad");
        setSize(600,600);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void initiateGameGrid(java.util.List<java.util.List<Tile>> tileList) {
        refreshGameGrid(tileList, 2, Color.black);
    }

    public void refreshGameGrid (java.util.List<java.util.List<Tile>> tileList, int player, Color color){
        gridPanel.removeAll();
        for (List<Tile> tileColumn : tileList) {
            for (Tile tile : tileColumn) {
                gridPanel.add(tile);
                if (tile instanceof PlayerTile) {
                    if (((PlayerTile) tile).getPlayer() == player) {
                        ((PlayerTile) tile).setColor(color);
                    }
                }
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

}
