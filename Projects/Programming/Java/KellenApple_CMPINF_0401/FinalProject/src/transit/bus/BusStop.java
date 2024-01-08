package transit.bus;
import transit.core.Stop;
public class BusStop extends Stop
{
	public BusStop(String name, double x, double y)
	{
		super(name, x, y);
	}
	public void gainPassengers()
	{
		int y = ((int)(Math.random()* 25) + 5);
		passengersWaiting += y;
	}
	public boolean isBus()
	{
		return true;
	}
}
