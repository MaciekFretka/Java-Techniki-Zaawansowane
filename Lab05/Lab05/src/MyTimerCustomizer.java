import java.awt.Dimension;
import java.awt.Label;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.Customizer;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class MyTimerCustomizer extends Panel implements Customizer, KeyListener{
private MyTimer target;
private TextField defmessagefield;
private PropertyChangeSupport support = new PropertyChangeSupport(this);
	@Override
	public void setObject(Object bean) {
		// TODO Auto-generated method stub
		target = (MyTimer) bean;
		Label t1=new Label("Domyœlna wiadomoœæ:");
		add(t1);
		defmessagefield=new TextField(String.valueOf(target.getDefaultMessage()),20);
		add(defmessagefield);
		defmessagefield.addKeyListener(this);
	}
	public Dimension getPreferredSize() {
		return new Dimension(225,50);
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source==defmessagefield) {
			String txt = defmessagefield.getText();
			
				target.setDefaultMessage(txt);
			support.firePropertyChange("",null,null);
			
		}
	}
public void addPropertyChangeListener(PropertyChangeListener l) {
	support.addPropertyChangeListener(l);
}
public void removePropertyChangeListener(PropertyChangeListener l) {
	support.removePropertyChangeListener(l);
}
}
