package transit_manager;

public class Train
{
	String trainIdentifier;
	String conductorName;
	int cars;
	int passengers;
	double speed;
	MetroRoute route;
	MetroStation currentStation;
	public String getTrainIdentifier()
	{
		return trainIdentifier;
	}
	public Train(String identifier, String conductor, double mph, int trainCars, MetroRoute route1)
	{
		trainIdentifier = "";
		if(identifier != null)
		{
			trainIdentifier = identifier;
		}
		conductorName = "";
		if(conductor != null)
		{
			conductorName = conductor;
		}
		route = new MetroRoute(0, null, null, null);
		if (route1 != null)
		{
			route = route1;
		}
		speed = 0.0;
		if (mph >= 0)
		{
			speed = mph;
		}
		if (trainCars > 0)
		{
			cars = trainCars;
		}
		currentStation = route.firstStop;
	}
	public void thankTheConductor()
	{
		System.out.println("Thank you for your hard work " + conductorName + "!");
	}
	public int calculateCapacity()
	{
		int capacity = cars * 120;
		return capacity;
	}
	public int letPassengersOff()
	{
		int num = 0;
		for(int i = 0; passengers >= 0; i++)
		{
			passengers--;
			num = i;
		}
		return num;
	}
	public int letPassengersOn()
	{
		int num = 0;
		for(int i=0;passengers<=calculateCapacity() && currentStation.passengersWaiting>0; i++)
		{
			currentStation.passengersWaiting--;
			passengers++;
			num = i;
		}
		return num;
	}
	public double moveToNextStation()
	{
		if(currentStation == route.firstStop)
		{
			currentStation = route.lastStop;
		}
		else
		{
			currentStation = route.firstStop;
		}
		double minutes = (route.calculateDistance() / speed) * 60;
		return minutes;
	}
	public String toString()
	{
		return "Train " + trainIdentifier + " (" + conductorName + ") traveling on route #" + route.routeNumber +
				"\nCurrently stopped at " + currentStation.stationName 
				+ "\n" + passengers + " seats taken out of " + calculateCapacity();
	}
}
