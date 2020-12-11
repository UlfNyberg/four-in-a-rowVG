import Tile.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import static javax.swing.JOptionPane.*;
import static javax.swing.JOptionPane.YES_OPTION;

/**
 * Created by: Ulf Nyberg
 * Date: 2020-12-10
 * Time: 11:15
 * Project: four-in-a-rowVG
 * Copyright: MIT
 */
public class GameBoard extends MouseAdapter implements ActionListener {

    private final int ROWS = 6;
    private final int COLUMNS = 7;

    private GameGUI gameGUI;
    private int currentPlayer = 1;
    private Color player1Color = new Color(204, 6, 5);
    private Color player2Color = new Color(255, 255, 77);

    public int p1Score = 0;
    public int p2Score = 0;

    public List<List<Tile>> tileList;
    private List<List<Tile>> undoList;

    private TileFactory tileFactory;

    public GameBoard() {
        tileFactory = new TileFactory(this);
        initiateTileList();
        initiateGUI();
    }

    private void initiateGUI() {
        gameGUI = new GameGUI(tileList, this);
    }

    private void initiateTileList() {
        tileList = new ArrayList<List<Tile>>();

        for (int i = 0; i < ROWS; i++) {
            tileList.add(new ArrayList<>());
            for (int j = 0; j < COLUMNS; j++) {
                tileList.get(i).add(tileFactory.createEmptyTile(new Point(j, i)));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() instanceof EmptyTile) {
            System.out.println(((EmptyTile) e.getSource()).getPosition());

            placeTile(currentPlayer, ((EmptyTile) e.getSource()).getPosition());

        }
    }

    public void placeTile(int player, Point point) {
        undoList = copyList(tileList);
        Tile tile;
        if (player == 0) {
            tile = tileFactory.createPlayerTile(point, player1Color, player);
        } else {
            tile = tileFactory.createPlayerTile(point, player2Color, player);
        }

        for (int i = ROWS - 1; i >= 0; i--) {
            if (tileList.get(i).get(point.x) instanceof EmptyTile) {
                tileList.get(i).set(point.x, tile);
                break;
            }
        }
        if (player == 1) {
            SwingUtilities.invokeLater(() -> gameGUI.refreshGameGrid(tileList, player, player1Color));
            calculateVictory(currentPlayer);
            currentPlayer = 2;
            gameGUI.p1ScoreLabel.setFont(gameGUI.font2);
            gameGUI.p2ScoreLabel.setFont(gameGUI.font1);
        } else {
            SwingUtilities.invokeLater(() -> gameGUI.refreshGameGrid(tileList, player, player2Color));
            calculateVictory(currentPlayer);
            currentPlayer = 1;
            gameGUI.p1ScoreLabel.setFont(gameGUI.font1);
            gameGUI.p2ScoreLabel.setFont(gameGUI.font2);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == gameGUI.showRulesButton) {
            gameRules();
            System.out.println("Rules have been shown");
        } else if (e.getSource() == gameGUI.undoButton) {
            undo();
            System.out.println("Undo");
        } else if (e.getSource() == gameGUI.p1Color1) {
            player1Color = new Color(204, 6, 5);
        } else if (e.getSource() == gameGUI.p1Color2) {
            player1Color = new Color(0, 128, 255);
        } else if (e.getSource() == gameGUI.p1Color3) {
            player1Color = new Color(152, 68, 158);
        } else if (e.getSource() == gameGUI.p2Color1) {
            player2Color = new Color(255, 255, 77);
        } else if (e.getSource() == gameGUI.p2Color2) {
            player2Color = new Color(245, 195, 194);
        } else if (e.getSource() == gameGUI.p2Color3) {
            player2Color = new Color(11, 134, 55);
        }

    }

    public void calculateVictory(int currentPlayer) {

        if (CalculateVictory.getInstance().calculateVictory(currentPlayer, tileList)) {
            showMessageDialog(gameGUI, "Spelare " + currentPlayer + " vann!");
            playAgain();
            winnerPoint(currentPlayer);
        }else if(CalculateVictory.getInstance().calculateDraw(tileList)){
            showMessageDialog(gameGUI, "Ingen spelare vann...");
            playAgain();
        }
    }

    public void gameRules(){
        showMessageDialog(gameGUI, "Spelare turas om att lägga en bricka per spelare.\n" +
                "När en spelare får fyra brickor i sin egen färg irad, horisontellt, vertikalt eller diagonalt vinner den.\n" +
                "Om det inte finns lediga platser och ingen har vunnit blir det oavgjort.\n\n" +
                "Lycka till!", "Spelregler", INFORMATION_MESSAGE);
    }

    public void playAgain() {
        int response = showConfirmDialog(gameGUI, "Spela igen?", "Rematch", YES_NO_OPTION, QUESTION_MESSAGE);

        if (response == YES_OPTION) {
            initiateTileList();
            SwingUtilities.invokeLater(() -> gameGUI.refreshGameGrid(tileList, 3, Color.BLACK));
        }
        else {
            gameGUI.bannerShow();
            System.exit(1);
        }
    }

    public void undo() {
        if (undoList == null) {
            return;
        }
        tileList = copyList(undoList);
        undoList = null;
        SwingUtilities.invokeLater(() -> gameGUI.refreshGameGrid(tileList, 1, player1Color));
        if (currentPlayer == 1) {
            gameGUI.p1ScoreLabel.setFont(gameGUI.font2);
            gameGUI.p2ScoreLabel.setFont(gameGUI.font1);
            currentPlayer = 2;
        } else {
            gameGUI.p2ScoreLabel.setFont(gameGUI.font2);
            gameGUI.p1ScoreLabel.setFont(gameGUI.font1);
            currentPlayer = 1;
        }
    }

    private java.util.List<java.util.List<Tile>> copyList(java.util.List<java.util.List<Tile>> sourceList) {
        java.util.List<java.util.List<Tile>> copy = new ArrayList<>();
        java.util.List<Tile> copyTileList;
        for (List<Tile> tiles : sourceList) {
            copyTileList = new ArrayList<>();
            for (Tile tile : tiles) {
                Tile tileCopy;
                if (tile instanceof PlayerTile) {
                    tileCopy = tileFactory.createPlayerTile(tile.getPosition(), ((PlayerTile) tile).getColor(), ((PlayerTile) tile).getPlayer());
                } else {
                    tileCopy = tileFactory.createEmptyTile(tile.getPosition());
                }
                copyTileList.add(tileCopy);
            }
            copy.add(copyTileList);
        }
        return copy;
    }


    public void winnerPoint (int player){
        if (player == 1) {
            p1Score++;
            gameGUI.p1ScoreCounter.setText(String.valueOf(p1Score));
            if (p1Score >= 5) {
                JOptionPane.showMessageDialog(gameGUI, "Spelare 1, du vann spelet!");
                gameGUI.bannerShow();
                System.exit(1);
            }
        }
        else {
            p2Score++;
            gameGUI.p2ScoreCounter.setText(String.valueOf(p2Score));
            if (p2Score >= 5){
                JOptionPane.showMessageDialog(gameGUI, "Spelare 2, du vann spelet!");
                gameGUI.bannerShow();
                System.exit(1);
            }
        }
    }



    public static void main(String[] args) {
       GameBoard gb = new GameBoard();

    }
}
