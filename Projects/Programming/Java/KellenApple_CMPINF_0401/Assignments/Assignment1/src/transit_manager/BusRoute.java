package transit_manager;

public class BusRoute
{
	int routeNumber;
	String routeDescription;
	BusStop firstStop;
	BusStop lastStop;
	public BusRoute(int routeNum, String description, BusStop first, BusStop last)
	{
		routeNumber = routeNum;
		if(description != null)
		{
			routeDescription = description;
		}
		firstStop = new BusStop(null, 0, 0.0, 0.0);
		if(first != null)
		{
			firstStop = first;
		}
		lastStop = new BusStop(null, 0, 0.0, 0.0);
		if (last != null)
		{
			lastStop = last;
		}
	}
	public double calculateDistance()
	{
		double q1 = firstStop.xCoordinate;
		double p1 = firstStop.yCoordinate;
		double q2 = lastStop.xCoordinate;
		double p2 = lastStop.yCoordinate;
		double distance = Math.sqrt(((q2 - q1) * (q2 - q1)) + ((p2 - p1) * (p2 - p1)));
		return distance;
	}
	public String toString()
	{
		return "Bus Route #" + routeNumber + ": " + routeDescription;
	}
}
