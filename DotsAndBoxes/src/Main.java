public class Main {
    public static void main(String[] args) {
        int rows = 4;
        int cols = 4;
       SettingsImpl settingsImpl = new SettingsImpl(rows,cols);
       ButtonImpl buttonsImpl = new ButtonImpl(rows, cols);
       Controller controller = new Controller(settingsImpl, buttonsImpl);
    }
}