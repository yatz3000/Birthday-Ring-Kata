package birthday;

import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;


public class BirthdayRingTest {
	private BirthdayDatabase bdb;
	private BirthdayRing birthdayRing;
	private NumberLookup nlu;
	private PhoneDailer phd;

	@Before
	public void setUp() {
		bdb = mock(BirthdayDatabase.class);
		nlu = mock(NumberLookup.class);
		phd = mock(PhoneDailer.class);
		birthdayRing = new BirthdayRing(bdb, nlu, phd);
	}
	
	@Test
	public void callsBirthdayDatabase() throws Exception {
		List<String> birthdays = new ArrayList<String>();
		when(bdb.findBirthdays(isA(Date.class))).thenReturn(birthdays);
		
		birthdayRing.run();
		
		verify(bdb).findBirthdays(isA(Date.class));
	}
	
	@Test
	public void callsNumberLookup() throws Exception {
		List<String> birthdays  = new ArrayList<String>();
		String name = "John";
		birthdays.add(name);
		when(bdb.findBirthdays(isA(Date.class))).thenReturn(birthdays);
		when(nlu.getNumber(name)).thenReturn(null);
		
		birthdayRing.run();
		
		verify(nlu).getNumber(name);
	}
	
	@Test
	public void callsPhoneDailer() throws Exception {
		List<String> birthdays  = new ArrayList<String>();
		String name = "John";
		birthdays.add(name);
		when(bdb.findBirthdays(isA(Date.class))).thenReturn(birthdays);
		String number = "555-5555";
		when(nlu.getNumber(name)).thenReturn(number);
		
		birthdayRing.run();
		
		verify(phd).dialNumber(number);
	}
}
