package birthday;

import java.util.Date;
import java.util.List;

public class BirthdayRing {

	private final BirthdayDatabase bdb;
	private final NumberLookup nlu;
	private final PhoneDailer phd;

	public BirthdayRing(BirthdayDatabase bdb, NumberLookup nlu, PhoneDailer phd) {
		this.bdb = bdb;
		this.nlu = nlu;
		this.phd = phd;
	}

	public void run() {
		Date now = new Date();
		
		List<String> names = bdb.findBirthdays(now);
		
		for (String name : names) {
			String number = nlu.getNumber(name);
			phd.dialNumber(number);
		}
	}

}
