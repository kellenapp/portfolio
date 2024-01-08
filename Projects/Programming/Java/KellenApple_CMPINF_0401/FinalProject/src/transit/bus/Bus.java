package transit.bus;
import transit.core.Vehicle;
public class Bus extends Vehicle
{
	int capacity;
	public Bus(String driver, double sp, BusRoute rt, BusStop stop)
	{
		super(driver, sp, rt, stop);
		capacity = 35;
	}
	public Bus(String driver, double sp, BusRoute rt)
	{
		super (driver, sp, rt);
		capacity = 35;
	}
	public int getCapacity()
	{
		return capacity;
	}
	public String toString() 
	{
		String s = "Bus " + identifier + " (" + driverName + ") traveling on route #" + route.getRouteNumber();
		if (isStopped == true)
		{
			s += "\nCurrently stopped at " + destination.getStopName() + "\n at location (" + xCoordinate + ", " + yCoordinate + ")";
		}
		else
		{
			s += "\nCurrently moving towards " + destination.getStopName() + "\n at location (" + xCoordinate + ", " + yCoordinate + ")";
		}
		s += "\n" + passengers + " seats taken out of " + capacity;
		return s;
	}
	public boolean isBus()
	{
		return true;
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
			// This is confusing so I will clarify parts
			// First: Test if need to move west or east in map
			if (xCoordinate > destination.getXCoordinate())
			{
				// See if we will make it to destination x Coordinate or not
				if (distance >= Math.abs(xCoordinate - destination.getXCoordinate()))
				{
					distanceTracker += Math.abs(xCoordinate - destination.getXCoordinate());
					distance -= Math.abs(xCoordinate - destination.getXCoordinate());
					xCoordinate = destination.getXCoordinate();
				}
				else
				{
					distanceTracker += distance;
					xCoordinate -= distance;
					distance = 0;
				}
			}
			// Are we moving east or west p2
			else if (xCoordinate < destination.getXCoordinate())
			{
				if (distance >= Math.abs(xCoordinate - destination.getXCoordinate()))
				{
					distanceTracker += Math.abs(xCoordinate - destination.getXCoordinate());
					distance -= Math.abs(xCoordinate - destination.getXCoordinate());
					xCoordinate = destination.getXCoordinate();
				}
				else
				{
					distanceTracker += distance;
					xCoordinate += distance;
					distance = 0;
				}
			}
			// If there is distance left, we move for Y
			if (distance > 0)
			{
				//North or South
				if (yCoordinate > destination.getYCoordinate())
				{
					//Will we make destination
					if (distance >= Math.abs(yCoordinate - destination.getYCoordinate()))
					{
						distanceTracker += Math.abs(yCoordinate - destination.getYCoordinate());
						distance -= Math.abs(yCoordinate - destination.getYCoordinate());
						yCoordinate = destination.getYCoordinate();
					}
					else
					{
						distanceTracker += distance;
						yCoordinate -= distance;
						distance = 0;
					}
				}
				//North or south p2
				else if (yCoordinate < destination.getYCoordinate())
				{
					if (distance >= Math.abs(yCoordinate - destination.getYCoordinate()))
					{
						distanceTracker += Math.abs(yCoordinate - destination.getYCoordinate());
						distance -= Math.abs(yCoordinate - destination.getYCoordinate());
						yCoordinate = destination.getYCoordinate();
					}
					else
					{
						distanceTracker += distance;
						yCoordinate += distance;
						distance = 0;
					}
				}
			}
			//If we made it to destination we are doing this
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
