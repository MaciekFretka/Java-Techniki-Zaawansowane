import javax.swing.JPanel;
import javax.swing.JSpinner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.AbstractListModel;
import javax.swing.JRadioButton;

public class MyTimer extends JPanel implements Serializable {
	private JTextField textField_1;
	private VetoableChangeSupport vetoes = new VetoableChangeSupport(this);
	private PropertyChangeSupport changes = new PropertyChangeSupport(this);
	private String DefaultMessage="Pora wstawaæ!";
	Alarm[] alarms=new Alarm[100];
	int sleep=0;
	public int getSleep() {
		return sleep;
	}
	public void setSleep(int sleep) {
		this.sleep = sleep;
	}

	String message=DefaultMessage;
	Date date;
	public String getDefaultMessage() {
		return DefaultMessage;
	}
	public void setDefaultMessage(String defaultMessage) {
		DefaultMessage = defaultMessage;
	}
	/**
	 * Create the panel.
	 */
	public Alarm[] getAlarms() {
		return alarms;
	}
	public void setAlarms(Alarm[] alarms) {
		this.alarms=alarms;
	}
	public Alarm getAlarm(int index) {
		return alarms[index];
	}
	
	public void setAlarm(int index,Alarm newalarm) {
		alarms[index]=newalarm;
	}
	
	public void addPropertyChangeListener(PropertyChangeListener p) {
		changes.addPropertyChangeListener(p);
	}
	public void removePropertyChangeListener(PropertyChangeListener p) {
		changes.removePropertyChangeListener(p);
	}
	public void addVetoableChangeListener (VetoableChangeListener v) {
		vetoes.addVetoableChangeListener(v);
	}
	
	public void removeVetoableChangeListener (VetoableChangeListener v) {
		vetoes.removeVetoableChangeListener(v);
	}
	
	public MyTimer() {
		setLayout(null);
		JSpinner spinner;// = new JSpinner();
		
		date = new Date(); 
		SpinnerDateModel sm = new SpinnerDateModel(date,null,null,Calendar.HOUR_OF_DAY);   
		spinner = new JSpinner(sm);   
		JSpinner.DateEditor de = new JSpinner.DateEditor(spinner,"HH:mm:ss");  
		spinner.setEditor(de);    
		spinner.setBounds(235, 208, 117, 20);
		add(spinner);
		spinner.setBounds(130, 90, 110, spinner.getPreferredSize().height);
		
		
		JLabel lblNewLabel = new JLabel("Ustaw godzin\u0119:");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(137, 60, 105, 20);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Czas drzemki");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(143, 120, 99, 13);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Wiadomo\u015B\u0107:");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(303, 64, 87, 13);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField();
		textField_1.setBounds(290, 90, 115, 167);
		add(textField_1);
		textField_1.setColumns(10);
		
		JSpinner spinner_1 = new JSpinner();
		spinner_1.setBounds(137, 143, 105, 20);
		add(spinner_1);
		

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Poniedzia\u0142ek");
		rdbtnNewRadioButton.setBounds(137, 179, 103, 21);
		add(rdbtnNewRadioButton);
		
		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("Wtorek");
		rdbtnNewRadioButton_1.setBounds(137, 202, 103, 21);
		add(rdbtnNewRadioButton_1);
		
		JRadioButton rdbtnNewRadioButton_2 = new JRadioButton("\u015Aroda");
		rdbtnNewRadioButton_2.setBounds(137, 225, 103, 21);
		add(rdbtnNewRadioButton_2);
		
		JRadioButton rdbtnNewRadioButton_3 = new JRadioButton("Czwartek");
		rdbtnNewRadioButton_3.setBounds(137, 248, 103, 21);
		add(rdbtnNewRadioButton_3);
		
		JRadioButton rdbtnNewRadioButton_4 = new JRadioButton("Pi\u0105tek");
		rdbtnNewRadioButton_4.setBounds(137, 271, 103, 21);
		add(rdbtnNewRadioButton_4);
		
		JRadioButton rdbtnNewRadioButton_5 = new JRadioButton("Sobota");
		rdbtnNewRadioButton_5.setBounds(137, 294, 103, 21);
		add(rdbtnNewRadioButton_5);
		
		JRadioButton rdbtnNewRadioButton_6 = new JRadioButton("Niedziela");
		rdbtnNewRadioButton_6.setBounds(137, 317, 103, 21);
		add(rdbtnNewRadioButton_6);

		
		JButton btnNewButton = new JButton("Ustaw");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
					List<Boolean> schedule = new ArrayList<>();
					 Alarm alarm = new Alarm();
					 addVetoableChangeListener(alarm);
					 addPropertyChangeListener(alarm);
					schedule.add(rdbtnNewRadioButton.isSelected());
					schedule.add(rdbtnNewRadioButton_1.isSelected());
					schedule.add(rdbtnNewRadioButton_2.isSelected());
					schedule.add(rdbtnNewRadioButton_3.isSelected());
					schedule.add(rdbtnNewRadioButton_4.isSelected());
					schedule.add(rdbtnNewRadioButton_5.isSelected());
					schedule.add(rdbtnNewRadioButton_6.isSelected());
					SimpleDateFormat formater= new SimpleDateFormat("HH:mm");
			        String spinnerValue = formater.format((spinner.getValue()));
			        Date alarmdate=new Date();
			        Date temp;
					temp = new SimpleDateFormat("HH:mm").parse(spinnerValue);
					
					alarmdate.setHours(temp.getHours());
					alarmdate.setMinutes(temp.getMinutes());
					
					if(textField_1.getText().isEmpty()) {
						//W³aœciwoœæ prosta
						message=DefaultMessage;
					}else {
						//W³aœciwoœæ ograniczona
						  message = textField_1.getText();
						 changes.firePropertyChange("message",DefaultMessage,message);
						 vetoes.fireVetoableChange("message",DefaultMessage,message);
					}
				
					 
					 
					 
					  sleep = (int) spinner_1.getValue();
					 vetoes.fireVetoableChange("sleep","0",sleep);
					 alarm = new Alarm(alarmdate,message,sleep,schedule);
					 new Timer().schedule(alarm,alarmdate);
				
				} catch (ParseException | PropertyVetoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		       
		      
			}
		});
		btnNewButton.setBounds(130, 355, 85, 21);
		add(btnNewButton);
		
		
	}
	
	
}
