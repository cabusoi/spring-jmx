package other;

import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jmx.export.MBeanExportException;
import org.springframework.jmx.export.MBeanExporter;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedOperationParameter;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Component;

@Component(Person.NAME)
@Scope("prototype")
@ManagedResource
public class Person {
	public static final String NAME = "Person";

	protected int age;
	public String name;

	public Person(Integer age, String name) {
		super();
		this.age = age;
		this.name = name;
	}

	@Autowired
	@Logged
	public void register(MBeanExporter exporter) throws MBeanExportException, MalformedObjectNameException {
		exporter.registerManagedResource(this, getObjectName());
	}

	public ObjectName getObjectName() {
		try {
			return new ObjectName(this.getClass().getPackage().getName() + ":type=" + NAME + ",name=" + this.name);
		} catch (MalformedObjectNameException ex) {
			throw new RuntimeException(ex);
		}
	}

	protected synchronized int getAge() {
		return age;
	}

	protected synchronized void setAge(Integer age) {
		this.age = age;
	}

	@ManagedAttribute
	public String getName() {
		return name;
	}

	@ManagedOperation
	@ManagedOperationParameter(name = "person-name", description = "person-name")
	public void setName(String name) {
		this.name = name;
	}

}
