package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LevelLoader {
    Path levelsFile;

    public LevelLoader(Path levels) {
        levelsFile = levels;
    }

    public GameObjects getLevel(int level) {
        level = level % 60 == 0 ? 60 : level % 60;
        Set<Wall> wallSet = new HashSet<>();
        Set<Box> boxeSet = new HashSet<>();
        Set<Home> homeSet = new HashSet<>();
        Player player = null;
        try (BufferedReader br = new BufferedReader(new FileReader(levelsFile.toFile()))) {
            while (true) {
                if (br.readLine().contains("Maze: " + level)) {
                    for (int i = 0; i < 6; i++) {
                        br.readLine();
                    }
                    break;
                }
            }
            String s;
            int x0 = Model.FIELD_CELL_SIZE / 2;
            int y0 = Model.FIELD_CELL_SIZE / 2;
            while(!(s = br.readLine()).isEmpty()){
                char[] c = s.toCharArray();
                int x = x0;
                for (int i = 0; i < c.length; i++) {
                    switch (c[i]) {
                        case ' ' :
                            break;
                        case '@' :
                            player = new Player(x, y0);
                            break;
                        case 'X' :
                            wallSet.add(new Wall(x, y0));
                            break;
                        case '*' : 
                            boxeSet.add(new Box(x, y0));
                            break;
                        case '.' : 
                            homeSet.add(new Home(x, y0));
                            break;
                        case '&' :
                            homeSet.add(new Home(x,y0));
                            boxeSet.add(new Box(x, y0));
                    }
                    x += Model.FIELD_CELL_SIZE;
                }
                y0 += Model.FIELD_CELL_SIZE;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        GameObjects gameObjects = new GameObjects(wallSet, boxeSet, homeSet, player);
        return gameObjects;
    }
}
