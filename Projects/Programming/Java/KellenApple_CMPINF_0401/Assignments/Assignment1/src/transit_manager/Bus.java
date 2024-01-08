package transit_manager;

public class Bus
{
	String busIdentifier;
	String driverName;
	int capacity;
	private int passengers;
	double speed;
	BusRoute route;
	BusStop currentStop;
	public String getBusIdentifier()
	{
		return busIdentifier;
	}
	public Bus(String identifier, String driver, double mph, BusRoute route1)
	{
		busIdentifier = "";
		if(identifier != null)
		{
			busIdentifier = identifier;
		}
		driverName = "";
		if(driver != null)
		{
			driverName = driver;
		}
		route = new BusRoute(0, null, null, null);
		if (route1 != null)
		{
			route = route1;
		}
		speed = 0.0;
		if (mph >= 0)
		{
			speed = mph;
		}
		currentStop = route.firstStop;
		capacity = ((int)(Math.random()*10) + 25);
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
		for(int i=1;passengers<=capacity && currentStop.passengersWaiting>0; i++)
		{
			currentStop.passengersWaiting--;
			passengers++;
			num = i;
		}
		return num;
	}
	public double moveToNextStop()
	{
		if(currentStop == route.firstStop)
		{
			currentStop = route.lastStop;
		}
		else
		{
			currentStop = route.firstStop;
		}
		double minutes = (route.calculateDistance() / speed) * 60;
		return minutes;
	}
	public String toString()
	{
		return "Bus " + busIdentifier + " (" + driverName + ") traveling on route #" + route.routeNumber
				+ "\nCurrently stopped at " + currentStop.stopName 
				+ "\n" + passengers + " seats taken out of " + capacity;
	}
}
