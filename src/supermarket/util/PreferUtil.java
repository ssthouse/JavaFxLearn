package supermarket.util;

import supermarket.controller.MainApp;

import java.io.File;
import java.util.prefs.Preferences;

/**
 * Created by ssthouse on 17/11/2016.
 */
public class PreferUtil {

    public static final String KEY_FILE_PATH = "filePath";

    public File getPersonFilePath() {
        Preferences prefer = Preferences.userNodeForPackage(MainApp.class);
        String filePath = prefer.get(KEY_FILE_PATH, null);
        if (filePath == null) {
            return null;
        }
        return new File(filePath);
    }

    public void setPresonFilePath(File personFile) {
        Preferences preferences = Preferences.userNodeForPackage(MainApp.class);
        if (personFile == null) {
            preferences.remove(KEY_FILE_PATH);
            return;
        }
        preferences.put(KEY_FILE_PATH, personFile.getPath());
        Log.println(personFile.getPath());
    }
}
