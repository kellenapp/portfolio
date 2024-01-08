package transit.train;
import transit.core.Vehicle;
public class Train extends Vehicle
{
	private int cars;
	public Train(String driver, double sp, int cars, MetroRoute rt, MetroStation stop)
	{
		super(driver, sp, rt, stop);
		if (cars > 0)
		{
			this.cars = cars;
		}
	}
	public Train(String driver, double sp, int cars, MetroRoute rt)
	{
		super(driver, sp, rt);
		if (cars > 0)
		{
			this.cars = cars;
		}
	}
	public int getCapacity()
	{
		int capacity = cars * 120;
		return capacity;
	}
	public String toString()
	{
		String s = "Train " + identifier + " (" + driverName + ") traveling on route #" + route.getRouteNumber();
		if (isStopped == true)
		{
			s += "\nCurrently stopped at " + destination.getStopName() + "\n at location (" + xCoordinate + ", " + yCoordinate + ")";
		}
		else
		{
			s += "\n Currently moving towards " + destination.getStopName() + "\n at location (" + xCoordinate + ", " + yCoordinate + ")";
		}
		s += "\n" + passengers + " seats taken out of " + getCapacity();
		return s;
	}
	public boolean isBus()
	{
		return false;
	}
	public double move(int minutes)
	{
		if (isStopped == true)
		{
			letPassengersOn();
			destination = destination.nextStop;
		}
		if (minutes > 0)
		{
			isStopped = false;
			double time = minutes;
			double distance = (speed / 60) * time;
			double distanceTracker = 0;
			double dist = Math.sqrt(((destination.getXCoordinate() - xCoordinate) * (destination.getXCoordinate() - xCoordinate)) + ((destination.getYCoordinate() - yCoordinate) * (destination.getYCoordinate() - yCoordinate)));
			if (distance >= dist)
			{
				distanceTracker += dist;
				xCoordinate = destination.getXCoordinate();
				yCoordinate = destination.getYCoordinate();
				distance -= dist;
			}
			else
			{
				distanceTracker += distance;
				double ratio = distance / dist;
				double i = 1;
				xCoordinate = ((i - ratio) * xCoordinate + (ratio * destination.getXCoordinate()));
				yCoordinate = ((i - ratio) * yCoordinate + (ratio * destination.getYCoordinate()));
			}
			if (yCoordinate == destination.getYCoordinate() && xCoordinate == destination.getXCoordinate())
			{
				isStopped = true;
				letPassengersOff();
			}
			return distanceTracker;
		}
		else
		{
			return 0.0;
		}
	}
}
