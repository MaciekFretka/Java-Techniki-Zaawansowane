import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.awt.event.ActionEvent;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;

public class MyBean {

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		 MyTimer timer = new MyTimer();
	        JFrame frame = new JFrame("App");
	        frame.setContentPane(timer);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public MyBean() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		 MyTimer timer = new MyTimer();
	        JFrame frame = new JFrame("App");
	        frame.setContentPane(timer);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	}
}
