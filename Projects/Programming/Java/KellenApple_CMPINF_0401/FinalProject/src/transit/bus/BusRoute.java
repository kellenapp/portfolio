package transit.bus;
import transit.core.Route;
import java.util.ArrayList;
public class BusRoute extends Route
{
	private ArrayList<Bus> buses;
	public ArrayList<Bus> getBuses()
	{
		return buses;
	}
	public BusRoute(int routeNum, String routeDesc, BusStop first)
	{
		super(routeNum, routeDesc, first);
		buses = new ArrayList<Bus>();
	}
	public String toString()
	{
		String n = "Bus Route #" + routeNumber + ": " + routeDescription + "\n";
		BusStop current = (BusStop) firstStop;
		for (int i = 0; i < counter; i++) 
		{
			n += current.toString();
			current = (BusStop) current.nextStop;
		}
		for (Bus i : buses)
		{
			n += "\n" + i.toString();
		}
		return n;
	}
	public void addDriver(String name, double speed)
	{
		Bus newBus = new Bus(name, speed, this);
		buses.add(newBus);
	}
	public void moveAll(int minutes)
	{
		for (Bus i : buses)
		{
			i.move(minutes);
		}
	}
	public void addStop(String stopName, double x, double y)
	{
		BusStop newStop = new BusStop(stopName, x, y);
		BusStop current = (BusStop) firstStop;
		while (current.nextStop != firstStop) 
        {
            current = (BusStop) current.nextStop;
        }
		newStop.nextStop = firstStop;
        current.nextStop = newStop;
        counter++;
	}
	public boolean isBus()
	{
		return true;
	}
}
