package net.v4lproik.displaycontent.mvc.controllers;

import net.v4lproik.displaycontent.api.Plugin;
import net.v4lproik.displaycontent.api.PluginInteractions;
import net.v4lproik.displaycontent.impl.PluginInteractionsImpl;
import net.v4lproik.displaycontent.mvc.Main;
import net.v4lproik.displaycontent.mvc.models.DisplayModel;
import net.v4lproik.displaycontent.mvc.views.DisplayView;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * Created by v4lproik on 24/02/15.
 */
public class DisplayController{
    
    DisplayModel model;
    DisplayView view;

    public DisplayController(String pluginsPath, String dirPath){
        String[] fileList = new File(dirPath).list();
        ArrayList<Plugin> pluginList = loadPlugins(pluginsPath);
        
        //init the model & view
        this.model = new DisplayModel(dirPath, fileList, pluginsPath, pluginList);
        this.view = new DisplayView(this);
        
        //display the directory listing by this application
        view.update("DisplayControllerCore");
                
        //Initialize the different plugins and start their jobs
        for(Plugin plugin:pluginList){
            //set the model to update
            PluginInteractions InPluginFonctions = new PluginInteractionsImpl(model);
            plugin.registerFonctions(InPluginFonctions);
            plugin.start();
            view.update(plugin.getName());
        }
    }

    /**
     * Call the function responsible for gathering the different available plugins
     * @return the plugins' instances
     */
    private ArrayList<Plugin> loadPlugins(String pluginsPath) {
        ArrayList<Plugin> myPlugins = new ArrayList<Plugin>();
        try {
            myPlugins = getAllPlugins(pluginsPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myPlugins;
    }

    private static ArrayList<Plugin> getAllPlugins(String pluginsPath) throws IOException {

        ArrayList<Plugin> myPlugins = new ArrayList<Plugin>();

        File f = new File(pluginsPath);

        if (!f.exists()) {
            throw new IOException(pluginsPath + " does not exist");
        }

        if (!f.isDirectory()) {
            throw new IOException(pluginsPath + " is not a directory");
        }

        for (File file : f.listFiles()) {
            if(!file.isFile()){
                throw new IOException("Only .jar file should be stored within the plugin folder " + pluginsPath);
            }
        }

        for (File file : f.listFiles()) {

            URLClassLoader ucl = null;
            try {
                ucl = new URLClassLoader(new URL[]{file.toURI().toURL()},
                        Main.class.getClassLoader());
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Enumeration<JarEntry> jarEntries = null;
            try {
                jarEntries = new JarFile(file).entries();
            }catch (IOException e) {
                e.printStackTrace();
            }
            while (jarEntries.hasMoreElements()) {
                JarEntry jarEntry = jarEntries.nextElement();
                if (jarEntry.getName().endsWith(".class")) {
                    String className = jarEntry.getName().replace("/",
                            ".").substring(0,
                            jarEntry.getName().indexOf(".class"));
                    Class c = null;
                    try {
                        c = Class.forName(className, true, ucl);
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                    for (Class i : c.getInterfaces()) {
                        if ("net.v4lproik.displaycontent.api.Plugin".equals(i.getName())) {
                            try {
                                Plugin plugin = (Plugin) c.newInstance();
                                myPlugins.add(plugin);
                            } catch (InstantiationException e) {
                                e.printStackTrace();
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        return myPlugins;
    }

    public DisplayModel getModel() {
        return model;
    }
}
