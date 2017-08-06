package other;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.Set;

import javax.management.MBeanServer;
import javax.management.ObjectInstance;
import javax.management.ObjectName;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jmx.support.JmxUtils;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersonConfiguration.class })
public class PersonIT {

	@Autowired
	ApplicationContext context;

	@Test
	public void test() {
		Person p = newPerson(40, "donald duck");
		Person p2 = newPerson(40, "mickey mouse");
		Person p3 = newPerson(40, "minnie");

		Person ps = p;// spy(p);

		MBeanServer server = JmxUtils.locateMBeanServer();
		Set<ObjectName> queryNames = server.queryNames(p.getObjectName(), null);
		System.out.println(queryNames);
		Set<ObjectInstance> mBeans = server.queryMBeans(p.getObjectName(), null);
		assertThat(mBeans.size(), is(1));
		ObjectInstance objectInstance = (ObjectInstance) mBeans.toArray()[0];
		assertThat(objectInstance.getClassName(), is(Person.class.getName()));
		assertThat(objectInstance.getObjectName(), is(p.getObjectName()));

	}

	public Person newPerson(int age, String name) {
		return context.getBean(Person.class, age, name);
	}

}
