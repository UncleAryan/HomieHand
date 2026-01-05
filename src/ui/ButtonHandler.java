package ui;

import java.awt.*;
import java.util.ArrayList;

public class ButtonHandler {
    private ArrayList<Button> buttons;
    private Pointer pointer;
    private Button hoveringOver, initialButton;
    private boolean hasSelected;

    public ButtonHandler() {
        buttons = new ArrayList<>();
        pointer = new Pointer();
        hasSelected = false;
    }

    public void addButton(Button button) {
        buttons.add(button);

        if (button.getType() == UIType.START_BUTTON && !hasSelected) {
            hoveringOver = button;
        }
    }

    public void tick(int mouseX, int mouseY) {
        // no buttons are being hovered over initially
        for (Button button : buttons) {
            button.setHovered(false);
        }

        Button mostRecentlySelected = null;
        for (Button button : buttons) {
            if (button.contains(mouseX, mouseY)) {
                mostRecentlySelected = button;
                break;
            }
        }

        // update which button should have the pointer
        if (mostRecentlySelected != null) {
            hoveringOver = mostRecentlySelected;
            hasSelected = true;
        }

        if (hoveringOver != null) {
            hoveringOver.setHovered(true);
        }
    }

    public void render(Graphics g) {
        // no buttons are being hovered over initially
        for (Button button : buttons) {
            button.render(g);
        }

        // only render if button is being hovered over
        if (hoveringOver != null) {
            pointer.render(g, hoveringOver);
        }
    }
}
