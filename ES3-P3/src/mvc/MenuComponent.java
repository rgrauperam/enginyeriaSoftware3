package mvc;

import javax.swing.*;

// Component base del patró Composite
public abstract class MenuComponent {
    protected String name;

    public MenuComponent(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void add(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public void remove(MenuComponent component) {
        throw new UnsupportedOperationException();
    }

    public JMenuItem asMenuItem() {
        throw new UnsupportedOperationException();
    }

    public JMenu asMenu() {
        throw new UnsupportedOperationException();
    }
}