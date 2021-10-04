package mj;

import javax.management.*;
import java.lang.management.ManagementFactory;

public class Manager implements ManagerMXBean {
    private Parameters p = new Parameters(0,1,2);

    @Override
    public Parameters getParameters() {
        return p;
    }

    @Override
    public void setParameters(Parameters p) {
        this.p=p;
    }

    public static void main(String[] args) throws InterruptedException, MalformedObjectNameException, NotCompliantMBeanException, InstanceAlreadyExistsException, MBeanRegistrationException {
        Manager impl = new Manager();
        ManagementFactory.getPlatformMBeanServer().registerMBean(impl,new ObjectName("pl.mj:name="+"Manager"));
        Thread.currentThread().join();
    }

}
