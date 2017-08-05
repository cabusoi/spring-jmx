package other;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { PersonConfiguration.class })
public class PersonIT {

	@Autowired
	ApplicationContext context;

	@Test
	public void test() {
		Person p = context.getBean(Person.class, 40, "donald duck");
		Person p2 = context.getBean(Person.class, 40, "mickey mouse");
		Person p3 = context.getBean(Person.class, 40, "minnie");

		Person ps = spy(p);

		doNothing().when(ps).setAge((Integer) anyObject());
		ps.setName("woody woodpecker");
		ps.setAge(50);

		assertThat(ps.name, is("woody woodpecker"));
		assertThat(ps.age, is(40));

	}

}
