package transit_manager;

public class BusStop
{
	String stopName;
	int stopNumber;
	double xCoordinate;
	double yCoordinate;
	int passengersWaiting;
	public String getStopName()
	{
		return stopName;
	}
	public int getPassengersWaiting()
	{
		return passengersWaiting;
	}
	public BusStop(String name, int stopNum, double xCoord, double yCoord)
	{
		stopName = "";
		if(name != null)
		{
			stopName = name;
		}
		stopNumber = stopNum;
		xCoordinate = xCoord;
		yCoordinate = yCoord;
	}
	public void gainPassengers()
	{
		int y = ((int)(Math.random()* 25) + 5);
		passengersWaiting += y;
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
	public double distance(BusStop other)
	{
		double q1 = xCoordinate;
		double p1 = yCoordinate;
		double q2 = other.xCoordinate;
		double p2 = other.yCoordinate;
		double distance = Math.sqrt((q2 - q1) * (q2 - q1) + (p2 - p1) * (p2 - p1));
		return distance;
	}
	public String  toString()
	{
		return "Stop #" + stopNumber + ": " + stopName +
			"\n" + passengersWaiting + " passengers waiting.";
	}
}
