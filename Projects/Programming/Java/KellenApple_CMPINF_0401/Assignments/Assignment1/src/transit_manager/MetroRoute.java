package transit_manager;

public class MetroRoute
{
	int routeNumber;
	String routeDescription;
	MetroStation firstStop;
	MetroStation lastStop;
	public MetroRoute(int routeNum, String description, MetroStation first, MetroStation last) 
	{
		routeNumber = routeNum;
		if(description != null)
		{
			routeDescription = description;
		}
		firstStop = new MetroStation(null, 0, 0.0, 0.0);
		if(first != null)
		{
			firstStop = first;
		}
		lastStop = new MetroStation(null, 0, 0.0, 0.0);
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
		return "Metro Route #" + routeNumber + ": " + routeDescription;
	}
}
