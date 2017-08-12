package work.notech.poker.tools;

import java.util.ResourceBundle;

public class PropertiesLoader {
    String getProperties(String key){
        ResourceBundle resourceBundle = ResourceBundle.getBundle("config");
        return resourceBundle.getString(key);
    }

    public static void main(String[] args) {
        PropertiesLoader p = new PropertiesLoader();
        System.out.println(p.getProperties("PORT"));
        System.out.println(p.getProperties("MAX_GAME_NUM"));
        System.out.println(p.getProperties("MAX_CONNECT_NUM"));
    }
}
