package transit.core;

public abstract class Vehicle
{
	protected String identifier;
	protected String driverName;
	protected int passengers;
	protected double speed;
	protected double xCoordinate;
	protected double yCoordinate;
	protected Route route;
	public Stop destination;
	protected boolean isStopped;
	public String getIdentifier()
	{
		return identifier;
	}
	public String getDriverName()
	{
		return driverName;
	}
	public int getPassengers()
	{
		return passengers;
	}
	public double getSpeed()
	{
		return speed;
	}
	public double getXCoordinate()
	{
		return xCoordinate;
	}
	public double getYCoordinate()
	{
		return yCoordinate;
	}
	public Route getRoute()
	{
		return route;
	}
	public boolean getIsStopped()
	{
		return isStopped;
	}
	public Vehicle (String driver, double sp, Route rt)
	{
		isStopped = true;
		if (driver != null)
		{
			driverName = driver;
			identifier = "" + driverName.hashCode();
		}
		if (sp > 0)
		{
			speed = sp;
		}
		if (rt != null)
		{
			route = rt;
		}
		destination = route.firstStop;
		xCoordinate = destination.xCoordinate;
		yCoordinate = destination.yCoordinate;
	}
	public Vehicle(String driver, double sp, Route rt, Stop stop)
	{
		isStopped = true;
		if (driver != null)
		{
			driverName = driver;
			identifier = "" + driverName.hashCode();
		}
		if (sp > 0)
		{
			speed = sp;
		}
		if (rt != null)
		{
			route = rt;
		}
		if (stop != null)
		{
			destination = stop;
		}
		xCoordinate = destination.xCoordinate;
		yCoordinate = destination.yCoordinate;
	}
	public void thankTheDriver()
	{
		System.out.println("Thank you for your hard work " + driverName + "!");
	}
	public int letPassengersOff()
	{
		int num = 0;
		for(int i = 1; passengers > 0; i++)
		{
			passengers--;
			num = i;
		}
		return num;
	}
	public int letPassengersOn()
	{
		int num = 0;
		for(int i=1;passengers<getCapacity() && destination.passengersWaiting>0; i++)
		{
			destination.passengersWaiting--;
			passengers++;
			num = i;
		}
		return num;
	}
	public abstract double move(int minutes);
	public abstract int getCapacity();
	public abstract boolean isBus();
}
