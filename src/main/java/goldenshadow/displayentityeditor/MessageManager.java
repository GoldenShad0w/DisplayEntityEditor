package goldenshadow.displayentityeditor;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MessageManager {

    private final HashMap<String, Object> fallbackMap;
    private final HashMap<String, Object> messageMap;

    public MessageManager() throws IOException {
        Yaml yaml = new Yaml();
        InputStream fallbackFileStream = DisplayEntityEditor.class.getClassLoader().getResourceAsStream("messages.yml");
        File f = new File(DisplayEntityEditor.getPlugin().getDataFolder().getAbsolutePath() + "/messages.yml");
        InputStream configStream = new FileInputStream(f);
        assert fallbackFileStream != null;
        fallbackMap = yaml.load(fallbackFileStream);
        messageMap = yaml.load(configStream);
        configStream.close();
        fallbackFileStream.close();
    }

    public String getString(String key) {
        String s = "";
        if (messageMap.containsKey(key) && DisplayEntityEditor.getPlugin().getConfig().getBoolean("use-messages-file")) {
            Object o = messageMap.get(key);
            if (o instanceof String) {
                s = (String) o;
            }
        } else {
            if (fallbackMap.containsKey(key) && !key.equals("file_version")) { //file version should never be gotten from fallback
                Object o = fallbackMap.get(key);
                if (o instanceof String) {
                    s = (String) o;
                }
            }
        }
        return s;
    }

    public List<String> getList(String key) {
        Object o = null;
        if (messageMap.containsKey(key) && DisplayEntityEditor.getPlugin().getConfig().getBoolean("use-messages-file")) {
            o = messageMap.get(key);
        } else if (fallbackMap.containsKey(key)) {
            o = fallbackMap.get(key);
        }

        List<String> returnList = new ArrayList<>();
        if (o instanceof ArrayList<?> list) {
            for (Object ob : list) {
                if (ob instanceof String s) {
                    returnList.add(s);
                }
            }
        }
        return returnList;
    }

    /**
     * Used to check if all messages are in the messages.yml being used
     * @return True if everything is fine, false if the file should be updated
     */
    public boolean isMessageMapComplete() {
        for (String key : fallbackMap.keySet()) {
            if (!messageMap.containsKey(key)) return false;
        }
        return true;
    }

}
