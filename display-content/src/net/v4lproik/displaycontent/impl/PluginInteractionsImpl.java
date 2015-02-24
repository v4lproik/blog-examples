package net.v4lproik.displaycontent.impl;

import net.v4lproik.displaycontent.api.PluginInteractions;
import net.v4lproik.displaycontent.mvc.models.DisplayModel;

/**
 * Created by v4lproik on 24/02/15.
 */
public class PluginInteractionsImpl implements PluginInteractions{

    public DisplayModel model;
    
    public PluginInteractionsImpl(DisplayModel model){
        this.model = model;
    }
    
    @Override
    public String[] getFileListing() {
        return model.getFileListing();
    }

    @Override
    public void setFileListing(String[] fileListing) {
        model.setFileListing(fileListing);
    }
}
