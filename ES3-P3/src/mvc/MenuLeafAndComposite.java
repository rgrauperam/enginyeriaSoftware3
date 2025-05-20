package mvc;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Fulla: ítem de menú
class MenuItemLeaf extends MenuComponent {
    private final JMenuItem item;

    public MenuItemLeaf(String name, ActionListener action) {
        super(name);
        item = new JMenuItem(name);
        item.addActionListener(action);
    }

    @Override
    public JMenuItem asMenuItem() {
        return item;
    }
}

// Compost: menú que pot contenir submenús o ítems
class MenuComposite extends MenuComponent {
    private final JMenu menu;
    private final List<MenuComponent> children = new ArrayList<>();

    public MenuComposite(String name) {
        super(name);
        menu = new JMenu(name);
    }

    @Override
    public void add(MenuComponent component) {
        children.add(component);
        if (component instanceof MenuItemLeaf leaf) {
            menu.add(leaf.asMenuItem());
        } else if (component instanceof MenuComposite comp) {
            menu.add(comp.asMenu());
        }
    }

    @Override
    public JMenu asMenu() {
        return menu;
    }
}