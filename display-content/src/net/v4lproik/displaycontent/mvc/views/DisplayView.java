package net.v4lproik.displaycontent.mvc.views;

import net.v4lproik.displaycontent.mvc.controllers.DisplayController;

/**
 * Created by v4lproik on 24/02/15.
 */
public class DisplayView {

    DisplayController controller;

    public DisplayView(DisplayController controller){
        this.controller = controller;
        initComponents();
        initActions();
    }

    private void initComponents(){

    }

    public void update(String who){
        System.out.println("[+] Directory Listing (by " + who + ") :");
        for(String file:controller.getModel().getFileListing()) {
            System.out.println(file);
        }
        System.out.println("");
    }

    private void initActions(){
        
    }
}
