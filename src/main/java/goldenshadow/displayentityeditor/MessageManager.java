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
        if (messageMap.containsKey(key)) {
            Object o = messageMap.get(key);
            if (o instanceof String) {
                s = (String) o;
            }
        } else {
            if (fallbackMap.containsKey(key)) {
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
        if (messageMap.containsKey(key)) {
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

}
