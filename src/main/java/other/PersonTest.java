package other;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class PersonTest {

	@Test
	public void test() {
		Person p = new Person(40, "cristi");
		Person ps = spy(p);
		Person pm = mock(Person.class);

		doNothing().when(ps).setAge((Integer) anyObject());
		ps.setName("alexandru");
		ps.setAge(50);

		assertThat(ps.name, is("alexandru"));
		assertThat(ps.age, is(40));

	}

}
