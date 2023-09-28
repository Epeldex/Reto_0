package factories;

import interfaces.Viewable;
import view.Console;

public class ViewableFactory {
    private static Viewable view = new Console();

    public static Viewable getViewable() {
        return view;
    }

}
