package other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableMBeanExport;
import org.springframework.jmx.export.MBeanExporter;

@Configuration
@ComponentScan
@EnableMBeanExport
public class PersonConfiguration {
	@Autowired
	private void configureExporter(MBeanExporter exporter) {
		exporter.addExcludedBean(Person.NAME);
	}

}
