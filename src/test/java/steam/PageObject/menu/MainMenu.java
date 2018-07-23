package steam.PageObject.menu;

import framework.BaseEntity;

import java.io.UnsupportedEncodingException;

public class MainMenu extends BaseEntity {

    public String action;

    public enum Menu {
        GAMES("Games");

        private String menuSection;

        Menu(String category) {
            this.menuSection = category;
        }
    }

    public enum GamesType {
        ACTION("Action");

        private String type;

        GamesType(String type) {
            this.type = type;
        }

    }

    public MainMenu() {
        initItems();
    }

    public void initItems() {
        try {
            action = new String(language.getConfigProperty("gamesAction").getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
