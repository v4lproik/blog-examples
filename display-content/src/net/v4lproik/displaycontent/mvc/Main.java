package net.v4lproik.displaycontent.mvc;

import net.v4lproik.displaycontent.mvc.controllers.DisplayController;

public class Main {

    public static void main(String[] args) {

        String pluginsPath = "";
        String dirPath = "";

        if (args.length > 1) {
            dirPath = args[0];
            pluginsPath = args[1];
        } else {
            System.out.println("Usage: java -jar app.jar <Folder path> <Plugins directory>\nProgram exit...");
            System.exit(1);
        }
        
        new DisplayController(pluginsPath, dirPath);
    }
}
