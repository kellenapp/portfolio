package transit.core;

import java.awt.Color;
import java.util.ArrayList;

public abstract class Route
{
	protected int routeNumber;
	protected String routeDescription;
	public Stop firstStop;
	protected int counter;
	public ArrayList<Color> colors = new ArrayList<Color>();
	public Color color;
	public Route(int routeNum, String routeDesc, Stop first)
	{
		colors.add(color.pink);
		colors.add(color.yellow);
		colors.add(color.cyan);
		colors.add(color.green);
		colors.add(color.MAGENTA);
		colors.add(color.orange);
		colors.add(color.lightGray);
		colors.add(color.red.darker().darker());
		colors.add(color.green.darker().darker());
		routeNumber = routeNum;
		color = colors.get(0 + (int)(Math.random() * ((8) + 1)));
		if (routeDesc != null)
		{
			routeDescription = routeDesc;
		}
		if (first != null)
		{
			firstStop = first;
		}
		firstStop.nextStop = firstStop;
		counter = 1;
	}
	public int getRouteNumber()
	{
		return routeNumber;
	}
	public String getRouteDescription()
	{
		return routeDescription;
	}
	public void gainPassengersAll()
	{
		Stop current = firstStop;
		for (int i = 0; i <= counter; i++) 
		{
			current.gainPassengers();
			current = current.nextStop;
		}
	}
	public abstract void addDriver(String name, double speed);
	public abstract void addStop(String name, double x, double y);
	public abstract void moveAll(int minutes);
	public abstract boolean isBus();
}
