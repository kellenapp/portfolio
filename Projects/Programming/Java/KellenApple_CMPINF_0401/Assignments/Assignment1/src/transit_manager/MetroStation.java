package transit_manager;

public class MetroStation
{
	String stationName;
	int stationNumber;
	double xCoordinate;
	double yCoordinate;
	int passengersWaiting;
	public String getStationName()
	{
		return stationName;
	}
	public int getPassengersWaiting()
	{
		return passengersWaiting;
	}
	public MetroStation(String name, int stationNum, double xCoord, double yCoord)
	{
		stationName = "";
		if(name != null)
		{
			stationName = name;
		}
		stationNumber = stationNum;
		xCoordinate = xCoord;
		yCoordinate = yCoord;
	}
	public void gainPassengers()
	{
		int y = ((int)(Math.random()* 180) + 20);
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
	public double distance(MetroStation other)
	{
		double q1 = xCoordinate;
		double p1 = yCoordinate;
		double q2 = other.xCoordinate;
		double p2 = other.yCoordinate;
		double distance = Math.sqrt((q2 - q1) * (q2 - q1) + (p2 - p1) * (p2 - p1));
		return distance;
	}
	public String toString()
	{
		return "Station #" + stationNumber + ": " + stationName +
				"\n" + passengersWaiting + " passengers waiting.";
	}
}
