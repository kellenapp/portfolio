package transit.core;

public abstract class Route
{
	protected int routeNumber;
	protected String routeDescription;
	public Stop firstStop;
	protected int counter;
	public Route(int routeNum, String routeDesc, Stop first)
	{
		routeNumber = routeNum;
		if (routeDesc != null)
		{
			routeDescription = routeDesc;
		}
		if (first != null)
		{
			firstStop = first;
		}
		firstStop.nextStop = firstStop;
		counter = 1;
	}
	public int getRouteNumber()
	{
		return routeNumber;
	}
	public String getRouteDescription()
	{
		return routeDescription;
	}
	public void gainPassengersAll()
	{
		Stop current = firstStop;
		for (int i = 0; i <= counter; i++) 
		{
			current.gainPassengers();
			current = current.nextStop;
		}
	}
	public abstract void addDriver(String name, double speed);
	public abstract void addStop(String name, double x, double y);
	public abstract void moveAll(int minutes);
}
