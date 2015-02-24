package net.v4lproik.displaycontent.mvc.models;

import net.v4lproik.displaycontent.api.Plugin;

import java.util.ArrayList;

/**
 * Created by v4lproik on 24/02/15.
 */
public class DisplayModel {

    String dirPath;
    String[] fileListing;
    
    String pluginsPath;
    ArrayList<Plugin> pluginListing;
    

    public DisplayModel(String dirPath, String[] fileList, String pluginsPath, ArrayList<Plugin> pluginsList) {
        this.fileListing = fileList;
        this.dirPath = dirPath;
        
        this.pluginsPath = pluginsPath;
        this.pluginListing = pluginsList;
    }

    public String[] getFileListing() {
        return fileListing;
    }

    public void setFileListing(String[] fileListing) {
        this.fileListing = fileListing;
    }

    public String getDirPath() {
        return dirPath;
    }

    public void setDirPath(String dirPath) {
        this.dirPath = dirPath;
    }

    public String getPluginsPath() {
        return pluginsPath;
    }

    public void setPluginsPath(String pluginsPath) {
        this.pluginsPath = pluginsPath;
    }

    public void setPluginListing(ArrayList<Plugin> pluginListing) {
        this.pluginListing = pluginListing;
    }

}
