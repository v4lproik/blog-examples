package net.v4lproik.displaycontent;

import net.v4lproik.displaycontent.api.Plugin;
import net.v4lproik.displaycontent.api.PluginInteractions;

import java.util.ArrayList;

/**
 * Created by v4lproik on 24/02/15.
 */
public class PluginRemoveTmpFiles implements Plugin{

    private PluginInteractions pluginInteractions;

    //general description
    private String name = "PluginRemoveTmpFiles";
    private String description = "Remove temporary files";
    private String version = "1.0";

    //debug variable
    private static final Boolean DEBUG = false;
    
    //temporary extensions variable
    private static final String[] extensions = {".tmp"};

    /**
     * Default constructor for debug purpose
     */
    public PluginRemoveTmpFiles(){
        //debug
        if(DEBUG) System.out.println("Instantiation Plugin " + name);
    }

    /**
     * Register the interface so the plugin can interact with the features implemented
     * within the main application
     * @param pluginInteractions
     */
    public void registerFonctions(PluginInteractions pluginInteractions) {
        this.pluginInteractions = pluginInteractions;
        if(DEBUG) System.out.println("Callback has been registered");
    }

    /**
     * Start the job
     */
    public void start(){
        String[] fileListing = pluginInteractions.getFileListing();
        ArrayList<String> tmp = new ArrayList<String>();
        
        if(fileListing == null)
            return;
        
        if(fileListing.length<1)
            return;
        
        //filter the list
        for(String file : fileListing){
            for(String extension : extensions){
                if(!file.endsWith(extension))
                    tmp.add(file);
            }
        }
        
        fileListing = new String[tmp.size()];
        
        //pass the new list to the main application
        pluginInteractions.setFileListing(tmp.toArray(fileListing));
        
    }

    @Override
    public String getVersion() {
        return version;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

}
