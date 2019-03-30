import java.io.PrintStream;

public class MillisTimeTest {
	public static void main(String[] args) {
		PrintStream out = System.out;

		MillisTime t0 = new MillisTime();
		out.println(t0);

		MillisTime t1 = new MillisTime(13, 25, 46, 320);
		out.println(t1);

		MillisTime t2 = new MillisTime(6, 0);
		out.println(t2);

		try {
			MillisTime tbad = new MillisTime(23, 59, 59, 1000);
			out.println(tbad);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		long ctime = System.currentTimeMillis();
		ctime %= MillisTime.MILLIS_PER_DAY;
		out.println(ctime);
		MillisTime t3 = new MillisTime((int)ctime);
		out.println(t3);
		int h = t3.getHours() - 5;
		if (h < 0) h += 24;
		out.println(h + ":" + t3.getMinutes() + ":"
			+ t3.getSeconds() + '.' + t3.getMillis());
		out.println(t3.getMilliSecondsSinceMidnight());

		t3.setHours(12);
		t3.setMinutes(0);
		t3.setSeconds(0);
		t3.setMillis(0);
		out.println(t3);

		t2 = new MillisTime(13, 25, 46, 320);
		if (t1.equals(t2)) out.println(t1 + " == " + t2);
		else			   out.println(t1 + " != " + t2);
		t2.setMinutes(37);
		out.println(t1 + " ? " + t2 + " => " + t1.compareTo(t2));

		out.println(t1 + " diff " + t2 + " => " + t1.diff(t2));

		t1 = new MillisTime(3, 14, 25, 700);
		t2 = new MillisTime(5, 48, 45, 800);
		out.println(t1 + " plus " + t2 + " => " + t1.plus(t2));
		out.print  (t1 + "   +  " + t2 + " => ");
		t1.add(t2);
		out.println(t1);
	}
}