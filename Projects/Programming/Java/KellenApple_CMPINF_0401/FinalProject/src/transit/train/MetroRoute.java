package transit.train;
import java.util.ArrayList;

import transit.bus.Bus;
import transit.bus.BusStop;
import transit.core.Route;
public class MetroRoute extends Route
{
	private ArrayList<Train> trains;
	public ArrayList<Train> getTrains()
	{
		return trains;
	}
	public MetroRoute(int routeNum, String routeDesc, MetroStation first)
	{
		super(routeNum, routeDesc, first);
		trains = new ArrayList<Train>();
	}
	public String toString()
	{
		String n = "Metro Route #" + routeNumber + ": " + routeDescription + "\n";
		MetroStation current = (MetroStation) firstStop;
		for (int i = 0; i < counter; i++) 
		{
			n += current.toString();
			current = (MetroStation) current.nextStop;
		}
		for (Train i : trains)
		{
			n += "\n" + i.toString();
		}
		return n;
	}
	public void addDriver(String name, double speed)
	{
		Train newTrain = new Train(name, speed, 3, this);
		trains.add(newTrain);
	}
	public void moveAll(int minutes)
	{
		for (Train i : trains)
		{
			i.move(minutes);
		}
	}
	public boolean isBus()
	{
		return false;
	}
	public void addStop(String stopName, double x, double y)
	{
		MetroStation newStop = new MetroStation(stopName, x, y);
		MetroStation current = (MetroStation) firstStop;
		while (current.nextStop != firstStop) 
        {
            current = (MetroStation) current.nextStop;
        }
		newStop.nextStop = firstStop;
        current.nextStop = newStop;
        counter++;
	}
}
