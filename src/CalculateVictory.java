import Tile.*;

import java.util.List;

import static javax.swing.JOptionPane.showMessageDialog;

/**
 * Created by Elliot Åberg Fält
 * Date: 2020-12-10
 * Time: 14:14
 * Project: four-in-a-rowVG
 * Copyright: MIT
 */
public class CalculateVictory {

    private static final CalculateVictory SINGLE_INSTANCE = new CalculateVictory();

    private CalculateVictory() {

    }

    public static CalculateVictory getInstance() {
        return SINGLE_INSTANCE;
    }

    public boolean calculateVictory(int currentPlayer, List<List<Tile>> tileList) {

        if (calculateHorizontal(currentPlayer, tileList) || calculateVertical(currentPlayer, tileList)
                || calculateDiagonalSE(currentPlayer, tileList) || calculateDiagonalSW(currentPlayer, tileList)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean calculateDraw(List<List<Tile>> tileList) {

        for (int x = 0; x < tileList.get(0).size(); x++) {
            for (int y = 0; y < tileList.size(); y++) {
                if (tileList.get(y).get(x) instanceof EmptyTile) {
                    return false;
                }
            }
        }
        return true;
    }

    private boolean calculateHorizontal(int currentPlayer, List<List<Tile>> tileList) {
        int consecutiveTiles;
        int columns = tileList.get(0).size();
        int rows = tileList.size();
        for (int x = 0; x < columns - 3; x++) {
            for (int y = 0; y < rows; y++) {
                consecutiveTiles = 0;
                if (tileList.get(y).get(x) instanceof PlayerTile && ((PlayerTile) tileList.get(y).get(x)).getPlayer() == currentPlayer) {
                    consecutiveTiles++;
                    for (int i = 1; i < 4; i++) {
                        if (tileList.get(y).get(x + i) instanceof PlayerTile) {
                            if (((PlayerTile) tileList.get(y).get(x + i)).getPlayer() == currentPlayer) {
                                consecutiveTiles++;
                            } else {
                                consecutiveTiles = 0;
                                break;
                            }
                        } else {
                            consecutiveTiles = 0;
                            break;
                        }
                    }
                    if (consecutiveTiles == 4) {
                        //win
                        System.out.println(currentPlayer + " vann horisontellt!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean calculateVertical(int currentPlayer, List<List<Tile>> tileList) {
        int consecutiveTiles;
        int columns = tileList.get(0).size();
        int rows = tileList.size();

        for (int x = 0; x < columns; x++) {
            for (int y = 0; y < rows - 3; y++) {
                consecutiveTiles = 0;
                if (tileList.get(y).get(x) instanceof PlayerTile && ((PlayerTile) tileList.get(y).get(x)).getPlayer() == currentPlayer) {

                    consecutiveTiles++;
                    for (int i = 1; i < 4; i++) {
                        if (tileList.get(y + i).get(x) instanceof PlayerTile) {
                            if (((PlayerTile) tileList.get(y + i).get(x)).getPlayer() == currentPlayer) {
                                consecutiveTiles++;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (consecutiveTiles == 4) {
                        //win
                        System.out.println(currentPlayer + " vann vertikalt!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean calculateDiagonalSE(int currentPlayer, List<List<Tile>> tileList) {
        int consecutiveTiles;
        int columns = tileList.get(0).size();
        int rows = tileList.size();

        for (int x = 0; x < columns - 3; x++) {
            for (int y = 0; y < rows - 3; y++) {
                consecutiveTiles = 0;
                if (tileList.get(y).get(x) instanceof PlayerTile && ((PlayerTile) tileList.get(y).get(x)).getPlayer() == currentPlayer) {

                    consecutiveTiles++;
                    for (int i = 1; i < 4; i++) {
                        if (tileList.get(y + i).get(x + i) instanceof PlayerTile) {
                            if (((PlayerTile) tileList.get(y + i).get(x + i)).getPlayer() == currentPlayer) {
                                consecutiveTiles++;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (consecutiveTiles == 4) {
                        //win
                        System.out.println(currentPlayer + " vann SE!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean calculateDiagonalSW(int currentPlayer, List<List<Tile>> tileList) {
        int consecutiveTiles;
        int columns = tileList.get(0).size();
        int rows = tileList.size();
        for (int x = 3; x < columns; x++) {
            for (int y = 0; y < rows - 3; y++) {
                consecutiveTiles = 0;
                if (tileList.get(y).get(x) instanceof PlayerTile && ((PlayerTile) tileList.get(y).get(x)).getPlayer() == currentPlayer) {

                    consecutiveTiles++;
                    for (int i = 1; i < 4; i++) {
                        if (tileList.get(y + i).get(x - i) instanceof PlayerTile) {
                            if (((PlayerTile) tileList.get(y + i).get(x - i)).getPlayer() == currentPlayer) {
                                consecutiveTiles++;
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (consecutiveTiles == 4) {
                        //win
                        System.out.println(currentPlayer + " vann SW!");
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /*
    Month christmas = Month.DECEMBER;
    Month summer = Month.JULY;
    LocalDate currentTime = LocalDateTime.now(ZoneId.of("Europe/Paris"));

    if (currentTime.getMonth() == christmas)
    Banner 3
    else if (currentTime.getMonth() == summer)
    Banner 1
    else
    Banner 2

     */
}
