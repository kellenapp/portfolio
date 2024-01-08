package transit.train;

import transit.core.Stop;

public class MetroStation extends Stop
{
	public MetroStation(String name, double x, double y)
	{
		super(name, x, y);
	}
	public void gainPassengers()
	{
		int y = ((int)(Math.random()* 180) + 20);
		passengersWaiting += y;
	}
	public boolean isBus()
	{
		return false;
	}
}
