public class BaseTime implements Comparable
{
	public static final int HOURS_PER_DAY = 24;
	public static final int MINUTES_PER_HOUR = 60;
	public static final int MINUTES_PER_DAY = MINUTES_PER_HOUR * HOURS_PER_DAY;
	public static final int SECONDS_PER_MINUTE = 60;
	public static final int SECONDS_PER_HOUR = SECONDS_PER_MINUTE * MINUTES_PER_HOUR;
	public static final int SECONDS_PER_DAY = SECONDS_PER_MINUTE * MINUTES_PER_DAY;

	private int hours;
	private int minutes;
	private int seconds;

	public BaseTime()
	{
		this(0, 0, 0);
	}

	public BaseTime(int ssm)
	{
		this(ssm / SECONDS_PER_HOUR, ssm % SECONDS_PER_HOUR / SECONDS_PER_MINUTE, ssm % SECONDS_PER_HOUR % SECONDS_PER_MINUTE);
	}

	public BaseTime(int hours, int minutes)
	{
		this(hours, minutes, 0);
	}

	public BaseTime(int hours, int minutes, int seconds)
	{
		this.setHours(hours);
		this.setMinutes(minutes);
		this.setSeconds(seconds);
	}

	public BaseTime(BaseTime that) throws IllegalArgumentException
	{
		if (that == null)
			throw new IllegalArgumentException("The other BaseTime cannot be null.");
		new BaseTime(that.getHours(), that.getMinutes(), that.getSeconds());
	}

	public int getSecondsSinceMidnight()
	{
		return (this.getHours() * SECONDS_PER_HOUR) + (this.getMinutes() * SECONDS_PER_MINUTE) + this.getSeconds();
	}

	public int getHours()
	{
		return this.hours;
	}

	public int getMinutes()
	{
		return this.minutes;
	}

	public int getSeconds()
	{
		return this.seconds;
	}

	public void setSecondsSinceMidnight(int ssm)
	{
		this.validateSecondsSinceMidnight(ssm);
		this.setHours(ssm / SECONDS_PER_HOUR);
		this.setMinutes(ssm % SECONDS_PER_HOUR / SECONDS_PER_MINUTE);
		this.setSeconds(ssm % SECONDS_PER_HOUR % SECONDS_PER_MINUTE);
	}

	public void setHours(int hours)
	{
		this.validateHours(hours);
		this.hours = hours;
	}

	public void setMinutes(int minutes)
	{
		this.validateMinutes(minutes);
		this.minutes = minutes;
	}

	public void setSeconds(int seconds)
	{
		this.validateSeconds(seconds);
		this.seconds = seconds;
	}

	private void validateSecondsSinceMidnight(int ssm) throws IllegalArgumentException
	{
		if (ssm < 0 || ssm > SECONDS_PER_DAY - 1)
			throw new IllegalArgumentException("Seconds since midnight must be between 0 and " + (SECONDS_PER_DAY - 1) + ". (Number used was " + ssm + ".)");
			
	}

	private void validateHours(int hours) throws IllegalArgumentException
	{
		if (hours < 0 || hours > HOURS_PER_DAY - 1)
			throw new IllegalArgumentException("Hours must be between 0 and " + (HOURS_PER_DAY - 1) + ". (Number used was " + hours + ".)");
	}

	private void validateMinutes(int minutes) throws IllegalArgumentException
	{
		if (minutes < 0 || minutes > MINUTES_PER_HOUR - 1)
			throw new IllegalArgumentException("Minutes must be between 0 and " + (MINUTES_PER_HOUR - 1) + ". (Number used was " + minutes + ".)");
	}

	private void validateSeconds(int seconds) throws IllegalArgumentException
	{
		if(seconds < 0 || seconds > SECONDS_PER_MINUTE - 1)
			throw new IllegalArgumentException("Seconds must be between 0 and " + (SECONDS_PER_MINUTE - 1) + ". (Number used was " + seconds + ".)");
	}

	public String toString()
	{
		return String.format("%02d:%02d:%02d", hours, minutes, seconds);
	}

	public boolean equals(Object other)
	{
		boolean ret = false;
		if (other != null && other instanceof BaseTime)
		{
			BaseTime that = (BaseTime)other;
			if (this.getSecondsSinceMidnight() == that.getSecondsSinceMidnight())
				ret = true;
		}
		return ret;
	}

	public int compareTo(Object other) throws IllegalArgumentException
	{
		if (other == null)
			throw new IllegalArgumentException("The other Object cannot be null.");
		if (!(other instanceof BaseTime))
			throw new IllegalArgumentException("The other Object must be a BaseTime.");
		BaseTime that = (BaseTime)other;
		return this.getSecondsSinceMidnight() - that.getSecondsSinceMidnight();
	}

	public BaseTime diff(BaseTime that) throws IllegalArgumentException
	{
		if (that == null)
			throw new IllegalArgumentException("The other BaseTime cannot be null.");
		return new BaseTime(Math.abs(this.getSecondsSinceMidnight() - that.getSecondsSinceMidnight()));
	}

	public BaseTime plus(BaseTime that) throws IllegalArgumentException
	{
		if (that == null)
			throw new IllegalArgumentException("The other BaseTime cannot be null.");
		return new BaseTime(this.getSecondsSinceMidnight() + that.getSecondsSinceMidnight());
	}

	public void add(BaseTime that) throws IllegalArgumentException
	{
		if (that == null)
			throw new IllegalArgumentException("The other BaseTime cannot be null.");
		setSecondsSinceMidnight(this.getSecondsSinceMidnight() + that.getSecondsSinceMidnight());
	}
}