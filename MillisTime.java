public class MillisTime extends BaseTime
{
	public static final int MILLIS_PER_SECOND = 1000;
	public static final int MILLIS_PER_MINUTE = MILLIS_PER_SECOND * SECONDS_PER_MINUTE;
	public static final int MILLIS_PER_HOUR = MILLIS_PER_SECOND * SECONDS_PER_HOUR;
	public static final int MILLIS_PER_DAY = MILLIS_PER_SECOND * SECONDS_PER_DAY;

	private int millis;

	public MillisTime()
	{
		super(0, 0, 0);
		this.setMillis(0);
	}

	public MillisTime(int msm)
	{
		this(msm / 1000 / SECONDS_PER_HOUR, msm / 1000 % SECONDS_PER_HOUR / SECONDS_PER_MINUTE, msm / 1000 % SECONDS_PER_HOUR % SECONDS_PER_MINUTE);
		this.setMillis(msm % SECONDS_PER_HOUR % SECONDS_PER_MINUTE % MILLIS_PER_SECOND);
	}

	public MillisTime(int hours, int minutes)
	{
		super(hours, minutes, 0);
		this.setMillis(0);
	}

	public MillisTime(int hours, int minutes, int seconds)
	{
		super(hours, minutes, seconds);
		this.setMillis(0);
	}

	public MillisTime(int hours, int minutes, int seconds, int millis)
	{
		super(hours, minutes, seconds);
		this.setMillis(millis);
	}

	public MillisTime(MillisTime that) throws IllegalArgumentException
	{
		if (that == null)
			throw new IllegalArgumentException("The other MillisTime cannot be null.");
		new MillisTime(that.getHours(), that.getMinutes(), that.getSeconds(), that.getMillis());
	}

	public int getMilliSecondsSinceMidnight()
	{
		return this.getSecondsSinceMidnight() * 1000 + this.millis;
	}

	public int getMillis()
	{
		return this.millis;
	}

	public void setMilliSecondsSinceMidnight(int msm)
	{
		this.setSecondsSinceMidnight(msm / 1000);
		this.setMillis(msm % MILLIS_PER_HOUR % MILLIS_PER_MINUTE % MILLIS_PER_SECOND);
	}

	public void setMillis(int millis)
	{
		this.validateMillis(millis);
		this.millis = millis;
	}

	private void validateMillisSinceMidnight(int msm)
	{
		if (msm < 0 || msm > MILLIS_PER_DAY - 1)
			throw new IllegalArgumentException("Milliseconds since midnight must be between 0 and " + (MILLIS_PER_DAY - 1) + ". (Number used was " + msm + ".)");
	}

	private void validateMillis(int millis)
	{
		if(millis < 0 || millis > MILLIS_PER_SECOND - 1)
			throw new IllegalArgumentException("Milliseconds must be between 0 and " + (MILLIS_PER_SECOND - 1) + ". (Number used was " + millis + ".)");
	}

	public String toString()
	{
		return String.format("%02d:%02d:%02d.%03d", this.getHours(), this.getMinutes(), this.getSeconds(), millis);
	}

	public boolean equals(Object other)
	{
		boolean ret = false;
		if (other != null && other instanceof MillisTime)
		{
			MillisTime that = (MillisTime)other;
			if (this.getSecondsSinceMidnight() == that.getSecondsSinceMidnight() && this.getMillis() == that.getMillis())
				ret = true;
		}
		return ret;
	}

	public int compareTo(Object other)
	{
		if (other == null)
			throw new IllegalArgumentException("The other Object cannot be null.");
		if (!(other instanceof BaseTime))
			throw new IllegalArgumentException("The other Object must be a MillisTime.");
		MillisTime that = (MillisTime)other;
		return (this.getSecondsSinceMidnight() * 1000 + this.getMillis()) - (that.getSecondsSinceMidnight() * 1000 + that.getMillis());
	}
}