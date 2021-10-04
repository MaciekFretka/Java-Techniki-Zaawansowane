import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.SimpleBeanInfo;

public class MyTimerBeanInfo extends SimpleBeanInfo {
Class beanClass = MyTimer.class;
String iconColor16x16Filename;
String iconColor32x32Filename;
String iconMono16x16Filename;
String iconMono32x32Filename;

public MyTimerBeanInfo() {
	
}
public PropertyDescriptor[] getPropertyDescriptors() {
	try {
		PropertyDescriptor _alarms=new PropertyDescriptor("alarms",beanClass,"getAlarms","setAlarms");
		PropertyDescriptor _DefaultMessage=new PropertyDescriptor("DefaultMessage",beanClass,"getDefaultMessage","setDefaultMessage");
		PropertyDescriptor _sleep=new PropertyDescriptor("sleep",beanClass,"getsleep","setsleep");
		
		PropertyDescriptor[] pds=new PropertyDescriptor[] {_alarms,_DefaultMessage,_sleep};
		return pds;
	} catch (IntrospectionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
}
public java.awt.Image getIcon(int iconKind){
	switch(iconKind) {
	case BeanInfo.ICON_COLOR_16x16:
		return iconColor16x16Filename != null ? loadImage(iconColor16x16Filename) : null;
	case BeanInfo.ICON_COLOR_32x32:
		return iconColor32x32Filename != null ? loadImage(iconColor32x32Filename) : null;
	case BeanInfo.ICON_MONO_16x16:
		return iconMono16x16Filename != null ? loadImage(iconMono16x16Filename) : null;
	case BeanInfo.ICON_MONO_32x32:
		return iconMono32x32Filename != null ? loadImage(iconMono32x32Filename) : null;
	}
	return null;
}

}
