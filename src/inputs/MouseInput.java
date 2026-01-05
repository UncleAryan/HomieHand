package inputs;

import core.Panel;
import framework.GameState;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInput implements MouseListener, MouseMotionListener {
	private int mouseX, mouseY;
	private Panel panel;

	public MouseInput(Panel panel) {
		this.panel = panel;
		mouseX = 0;
		mouseY = 0;
	}

	public int getMouseX() {
		return mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void mouseDragged(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseMoved(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseClicked(MouseEvent e) {
		updateMousePosition(e);
		
		switch(GameState.state) {
		case MENU:
			break;
		case PAUSE:
			break;
		case PLAY:
			break;
		case SETTINGS:
			if(panel.getSettingsMenu().getHoveringOverLeftFPSOptionsButton()) {
				panel.getSettingsMenu().setFPSSettingIndex(panel.getSettingsMenu().getFPSSettingIndex() - 1);
				panel.setFPS(panel.getSettingsMenu().getFPSValue());
				panel.FPSChanged(true);
			}
			if(panel.getSettingsMenu().getHoveringOverRightFPSOptionsButton()) {
				panel.getSettingsMenu().setFPSSettingIndex(panel.getSettingsMenu().getFPSSettingIndex() + 1);
				panel.setFPS(panel.getSettingsMenu().getFPSValue());
				panel.FPSChanged(true);
			}
			if(panel.getSettingsMenu().getHoveringOverBackButton()) {
				GameState.state = GameState.MENU;
			}
			break;
		default:
			break;
		}
	}

	public void mousePressed(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseReleased(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseEntered(MouseEvent e) {
		updateMousePosition(e);
	}

	public void mouseExited(MouseEvent e) {
		updateMousePosition(e);
	}

	private void updateMousePosition(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();

		if (GameState.state == GameState.SETTINGS) {
			if (mouseX >= panel.getSettingsMenu().getLeftFPSOptionsArrowButtonX()
				&& mouseX <= panel.getSettingsMenu().getLeftFPSOptionsArrowButtonX() + panel.getSettingsMenu().getScaledArrowButtonWidth()
				&& mouseY >= panel.getSettingsMenu().getFPSOptionsArrowButtonY()
				&& mouseY <= panel.getSettingsMenu().getFPSOptionsArrowButtonY() + panel.getSettingsMenu().getScaledArrowButtonHeight()) {
				panel.getSettingsMenu().hoveringOverLeftFPSOptionsButton(true);
			} else {
				panel.getSettingsMenu().hoveringOverLeftFPSOptionsButton(false);
			}

			if (mouseX >= panel.getSettingsMenu().getRightFPSOptionsArrowButtonX()
			&& mouseX <= panel.getSettingsMenu().getRightFPSOptionsArrowButtonX() + panel.getSettingsMenu().getScaledArrowButtonWidth()
			&& mouseY >= panel.getSettingsMenu().getFPSOptionsArrowButtonY()
			&& mouseY <= panel.getSettingsMenu().getFPSOptionsArrowButtonY() + panel.getSettingsMenu().getScaledArrowButtonHeight()) {
				panel.getSettingsMenu().hoveringOverRightFPSOptionsButton(true);
			} else {
				panel.getSettingsMenu().hoveringOverRightFPSOptionsButton(false);
			}
			
			if (mouseX >= panel.getSettingsMenu().getBackButtonsX()
			 && mouseX <= panel.getSettingsMenu().getBackButtonsX() + panel.getSettingsMenu().getScaledBackButtonsWidth()
			 && mouseY >= panel.getSettingsMenu().getBackButtonsY()
			 && mouseY <= panel.getSettingsMenu().getBackButtonsY() + panel.getSettingsMenu().getScaledBackButtonsHeight()) {
				panel.getSettingsMenu().hoveringOverBackButton(true);
			} else {
				panel.getSettingsMenu().hoveringOverBackButton(false);
			}
		}
	}
}
