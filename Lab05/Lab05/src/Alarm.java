import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;

 class Alarm extends TimerTask implements VetoableChangeListener,PropertyChangeListener,Serializable{
	 Date time;
	    List<Boolean> schedule = new ArrayList<>();
	    int sleep;
	    String message;
	   // private VetoableChangeSupport vetoes = new VetoableChangeSupport (this);
	    Alarm(){};
	    Alarm(Date date,String message, int sleep,List<Boolean> schedule){
	        time=date;
	        this.message=message;
	        this.sleep=sleep;
	        this.schedule=schedule;
	        System.out.println(this.schedule);
	        
	        
	    }
	    
	    public static int getDayNumber(Date date) {
	    	Calendar cal = Calendar.getInstance();
	    	cal.setTime(date);
	    	return cal.get(Calendar.DAY_OF_WEEK)-2;
	    	
	    }
		@Override
		public void run() {
			// TODO Auto-generated method stub
			Boolean dream=true;
		
				if(schedule.get(getDayNumber(new Date()))) {
					int result = JOptionPane.showConfirmDialog(null, message,"Drzemka",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
					if(result == JOptionPane.YES_OPTION) {
						LocalDateTime dreamtime = LocalDateTime.now().plusMinutes(sleep);
						Date dreamtimeasdata = Date.from(dreamtime.atZone(ZoneId.systemDefault()).toInstant());
						new Timer().schedule(new Alarm(dreamtimeasdata,message,sleep,schedule),dreamtimeasdata);
				}else if (result == JOptionPane.NO_OPTION) {
					dream=false;
				}else {
					dream=false;
				}
				}	
			
		
			
		}

		@Override
		public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
			// TODO Auto-generated method stub
		if(evt.getPropertyName()=="message") {
			if(evt.getNewValue().toString().length()>100) {
				JOptionPane.showMessageDialog(null, "Wiadomoœæ zbyt d³uga");
				throw new PropertyVetoException("Wiadomoœæ zbyt d³uga",evt);
			}
		}else if(evt.getPropertyName()=="sleep") {
			int temp=(int) evt.getNewValue();
			if(temp<0 || temp >59) {
				JOptionPane.showMessageDialog(null, "Niepoprawny czas drzemki");
				throw new PropertyVetoException("Niepoprawny czas drzemki",evt);
			}
		}
		}

		@Override
		public void propertyChange(PropertyChangeEvent evt) {
			// TODO Auto-generated method stub
			
		}
}
