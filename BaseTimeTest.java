import java.io.PrintStream;

public class BaseTimeTest {
	public static void main (String[] args) {
		PrintStream out = System.out;

		BaseTime t0 = new BaseTime();
		out.println(t0);

		BaseTime t1 = new BaseTime(13, 25, 46);
		out.println(t1);

		BaseTime t2 = new BaseTime(6, 0);
		out.println(t2);

		try {
			BaseTime tbad = new BaseTime(23, 59, 60);
			out.println(tbad);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
		}

		long ctime = System.currentTimeMillis();
		ctime /= 1000;
		ctime %= BaseTime.SECONDS_PER_DAY;
		out.println(ctime);
		BaseTime t3 = new BaseTime((int)ctime);
		out.println(t3);
		int h = t3.getHours() - 5;
		if (h < 0) h += 24;
		out.println(h + ":" + t3.getMinutes() + ":"
			+ t3.getSeconds());
		out.println(t3.getSecondsSinceMidnight());

		t3.setHours(12);
		t3.setMinutes(0);
		t3.setSeconds(0);
		out.println(t3);

		t2 = new BaseTime(13, 25, 46);
		if (t1.equals(t2)) out.println(t1 + " == " + t2);
		else			   out.println(t1 + " != " + t2);
		t2.setMinutes(37);
		out.println(t1 + " ? " + t2 + " => " + t1.compareTo(t2));

		out.println(t1 + " diff " + t2 + " => " + t1.diff(t2));

		t1 = new BaseTime(3, 14, 25);
		t2 = new BaseTime(5, 48, 45);
		out.println(t1 + " plus " + t2 + " => " + t1.plus(t2));
		out.print  (t1 + "   +  " + t2 + " => ");
		t1.add(t2);
		out.println(t1);
	}
}