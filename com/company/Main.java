package com.company;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {

    public static String pathway = "F://NETOLOGY/Games/savegames";

    private static void openZip(String path, String paths) {
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(path))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName();
                FileOutputStream fout = new FileOutputStream(paths + "/" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static GameProgress openProgress(String path) {
        GameProgress gameProgress = null;

        try (FileInputStream fis = new FileInputStream(path);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return gameProgress;
    }

    public static void main(String[] args) {
        openZip(pathway + "/saveGames.zip", pathway);

        System.out.println(openProgress(pathway + "/saveGame.dat"));
        System.out.println(openProgress(pathway + "/saveGame1.dat"));
        System.out.println(openProgress(pathway + "/saveGame2.dat"));


    }
}
