package transit.core;

public abstract class Stop
{
	protected String stopName;
	protected int stopNumber;
	protected double xCoordinate;
	protected double yCoordinate;
	protected int passengersWaiting;
	public Stop nextStop;
	public String getStopName()
	{
		return stopName;
	}
	public int getStopNumber()
	{
		return stopNumber;
	}
	public double getXCoordinate()
	{
		return xCoordinate;
	}
	public double getYCoordinate()
	{
		return yCoordinate;
	}
	public int getPassengersWaiting()
	{
		return passengersWaiting;
	}
	public Stop(String name, double x, double y)
	{
		if (name != null)
		{
			stopName = name;
		}
		xCoordinate = x;
		yCoordinate = y;
		stopNumber = stopName.hashCode();
		nextStop = this;
	}
	public boolean losePassengers(int numPassengers)
	{
		if(numPassengers <= passengersWaiting)
		{
			passengersWaiting -= numPassengers;
			return true;
		}
		else
		{
			return false;
		}
	}
	public String toString()
	{
		return "Stop #" + stopNumber + ": " + stopName + 
				"\n" + passengersWaiting + " passengers waiting.\n";
	}
	public abstract void gainPassengers();
}
