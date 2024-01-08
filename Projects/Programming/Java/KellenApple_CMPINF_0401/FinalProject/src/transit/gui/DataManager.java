package transit.gui;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

import transit.core.*;
import transit.bus.*;
import transit.train.*;
public class DataManager
{
	public ArrayList<Route> routes = new ArrayList<Route>();
	public ArrayList<String> firstNames = new ArrayList<String>();
	public ArrayList<String> lastNames = new ArrayList<String>();
	public ArrayList<String> stopNames = new ArrayList<String>();
	public DataManager()
	{
		stopNames.add("Downtown");
		stopNames.add("Oakland");
		stopNames.add("Southside");
		stopNames.add("Lawrenceville");
		stopNames.add("Squirrel Hill");
		stopNames.add("Shadyside");
		stopNames.add("Mckee Rocks");
		stopNames.add("North Hills");
		stopNames.add("Westside");
		stopNames.add("Pittsford");
		stopNames.add("Greece");
		stopNames.add("Brighton");
		stopNames.add("Henrietta");
		stopNames.add("Spencerport");
		stopNames.add("Fairport");
		stopNames.add("Brockport");
		stopNames.add("Canandaigua");
		stopNames.add("Albion");
		firstNames.add("Billy");
		firstNames.add("Joe");
		firstNames.add("Nick");
		firstNames.add("Kai");
		firstNames.add("Earl");
		firstNames.add("Jake");
		firstNames.add("Carl");
		firstNames.add("Pop");
		firstNames.add("Rick");
		firstNames.add("Josep");
		firstNames.add("Ian");
		firstNames.add("Bella");
		firstNames.add("Jennie");
		firstNames.add("Carol");
		firstNames.add("Lilly");
		firstNames.add("Ella");
		firstNames.add("Riley");
		firstNames.add("Alex");
		firstNames.add("Pip");
		firstNames.add("Kit");
		firstNames.add("Cam");
		firstNames.add("Kel");
		firstNames.add("Brae");
		firstNames.add("Rin");
		lastNames.add(" Cherry");
		lastNames.add(" Priun");
		lastNames.add(" Xiao");
		lastNames.add(" Apple");
		lastNames.add(" Chieves");
		lastNames.add(" Smith");
		lastNames.add(" Booler");
		lastNames.add(" Ick");
		lastNames.add(" Orange");
		lastNames.add(" Teller");
		lastNames.add(" Johnson");
		lastNames.add(" Lee");
		lastNames.add(" Wethel");
		lastNames.add(" Hope");
		lastNames.add(" Unox");
		lastNames.add(" Velez");
		lastNames.add(" Cheron");
		BusRoute x = new BusRoute(1, "Route going from Downtown to Oakland.", new BusStop("Downtown", 0.5, 0));
		x.addStop("Oakland", 0, 0.2);
		x.addDriver("Bill", 15);
		MetroRoute y = new MetroRoute(8, "Route going from Station 1 to Station 2.", new MetroStation("Station 1", 0.3, -0.2));
		y.addStop("Station 2", 0, 0.4);
		y.addDriver("Terry", 20);
		BusRoute z = new BusRoute(3, "Route going from North Hills to the Highway", new BusStop("North Hills", 0.1, -0.1));
		z.addStop("highway", 0, -0.3);
		z.addDriver("Noor", 20);
		routes.add(x);
		routes.add(y);
		routes.add(z);
	}
	public void update() 
	{
		int x = 0;
		for (Route i : routes)
		{
			i.moveAll(1);
			x++;
			if (x%3 == 0)
			{
				i.gainPassengersAll();
			}
		}
	}
	public void clear()
	{
		routes.clear();
	}
	public void randomCity()
	{
		clear();
		randomBusRoute();
		randomMetroRoute();
		randomBusRoute();
		randomBusRoute();
		randomMetroRoute();
		randomMetroRoute();
	}
	public void randomBusRoute()
	{
		BusRoute route;
		int l = 0 + (int)(Math.random() * ((15) + 1));
		String name1 = stopNames.get(l);
		String name2 = stopNames.get(l + 1);
		String name3 = stopNames.get(l + 2);
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		double x1 = Double.parseDouble(df.format(Math.random() -0.5));
		double x2 = Double.parseDouble(df.format(Math.random() -0.5));
		double x3 = Double.parseDouble(df.format(Math.random() -0.5));
		double y1 = Double.parseDouble(df.format(Math.random() -0.5));
		double y2 = Double.parseDouble(df.format(Math.random() -0.5));
		double y3 = Double.parseDouble(df.format(Math.random() -0.5));
		System.out.println(x1 + " " +y1);
		System.out.println(x2 + " " + y2);
		System.out.println(x3 + " " + y3);
		BusStop stop1 = new BusStop(name1, x1, y1);
		int routeNum = (int) (Math.random() * ((100) + 1));
		route = new BusRoute(routeNum, "Bus Route connecting " + name1 + " through " + name2 + " to " + name3 + ".", stop1);
		route.addStop(name2, x2, y2);
		route.addStop(name3, x3, y3);
		for (int i = 0; i < 2; i++)
		{
			Random rand = new Random();
			int w = rand.nextInt(24);
			int z = rand.nextInt(17);
			double speed = rand.nextInt(10) + 1;
			String name = firstNames.get(w) + lastNames.get(z);
			route.addDriver(name, speed);
		}
		routes.add(route);
	}
	public Route getRoute(int routeNum)
	{
		for (Route i : routes) 
		{
			if (i.getRouteNumber() == routeNum)
			{
				return i;
			}
		}
		return null;
	}
	public void randomMetroRoute()
	{
		MetroRoute route;
		int l = 0 + (int)(Math.random() * ((15) + 1));
		String name1 = stopNames.get(l);
		String name2 = stopNames.get(l + 1);
		String name3 = stopNames.get(l + 2);
		DecimalFormat df = new DecimalFormat("#.#");
		df.setRoundingMode(RoundingMode.CEILING);
		double x1 = Double.parseDouble(df.format(Math.random() -0.5));
		double x2 = Double.parseDouble(df.format(Math.random() -0.5));
		double x3 = Double.parseDouble(df.format(Math.random() -0.5));
		double y1 = Double.parseDouble(df.format(Math.random() -0.5));
		double y2 = Double.parseDouble(df.format(Math.random() -0.5));
		double y3 = Double.parseDouble(df.format(Math.random() -0.5));
		MetroStation stop1 = new MetroStation(name1, x1, y2);
		int routeNum = (int) (Math.random() * ((100) + 1));
		route = new MetroRoute(routeNum, "Bus Route connecting " + name1 + " through " + name2 + " to " + name3 + ".", stop1);
		route.addStop(name2, x2, y2);
		route.addStop(name3, x3, y3);
		for (int i = 0; i < 3; i++)
		{
			Random rand = new Random();
			int w = rand.nextInt(24);
			int z = rand.nextInt(17);
			double speed = rand.nextInt(10) + 1;
			String name = firstNames.get(w) + lastNames.get(z);
			route.addDriver(name, speed);
		}
		routes.add(route);
	}
}
