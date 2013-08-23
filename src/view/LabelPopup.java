package view;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;

public class LabelPopup {

	// Define Show Popup ActionListener
	static class ShowPopupActionListener implements ActionListener {
		private Component component;

		ShowPopupActionListener(Component component) {
			this.component = component;
		}

		public synchronized void actionPerformed(ActionEvent actionEvent) {
			JLabel label = new JLabel("Hello, World");
			PopupFactory factory = PopupFactory.getSharedInstance();
			final Popup popup = factory.getPopup(component, label, 500, 500);
			popup.show();
			ActionListener hider = new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					popup.hide();
				}
			};
			// Hide popup in 3 seconds
			Timer timer = new Timer(3000, hider);
			timer.start();
		}
	}
}