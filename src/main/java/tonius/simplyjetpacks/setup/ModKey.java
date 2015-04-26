package tonius.simplyjetpacks.setup;

public enum ModKey {
    
    TOGGLE_PRIMARY("toggle.primary", false, 176, 0, 176, 20, 176, 40),
    TOGGLE_SECONDARY("toggle.secondary", false, 196, 0, 196, 20, 196, 40),
    MODE_PRIMARY("mode.primary", false, 216, 0, 216, 20, 216, 40),
    MODE_SECONDARY("mode.secondary", true, 236, 0, 236, 20, 236, 40),
    OPEN_PACK_GUI(null, false, 0, 0, 0, 0, 0, 0);
    
    public final String name;
    public final boolean alwaysShowInChat;
    public final int sheetX;
    public final int sheetY;
    public final int hoverX;
    public final int hoverY;
    public final int disabledX;
    public final int disabledY;
    
    private ModKey(String tooltip, boolean alwaysShowInChat, int sheetX, int sheetY, int hoverX, int hoverY, int disabledX, int disabledY) {
        this.name = tooltip;
        this.alwaysShowInChat = alwaysShowInChat;
        this.sheetX = sheetX;
        this.sheetY = sheetY;
        this.hoverX = hoverX;
        this.hoverY = hoverY;
        this.disabledX = disabledX;
        this.disabledY = disabledY;
    }
    
}
