package net.v4lproik.displaycontent.api;

/**
 * Created by v4lproik on 23/02/15.
 */
public interface Plugin {
        public void registerFonctions(PluginInteractions callback);
        public void start();

        public String getName();
        public String getDescription();
        public String getVersion();
}
