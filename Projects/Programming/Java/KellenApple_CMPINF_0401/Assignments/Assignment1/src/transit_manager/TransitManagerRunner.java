package transit_manager;

public class TransitManagerRunner
{
	public static void main(String[] args)
	{
	System.out.println("BUS TESTING =============================\n");
	TestBuses();
	System.out.println("METRO TESTING =============================\n");
	}
	public static void TestBuses()
	{
		BusStop stopA = new BusStop("Oakland", 12342, 0, 0);
		BusStop stopB = new BusStop("Downtown", 87236, -2.3, 0.2);
		BusRoute routeA = new BusRoute(71, "Connects Oakland with downtown.", stopA, stopB);
		Bus busA = new Bus("KLJF3", "Jerry", 22, routeA);

		stopA.gainPassengers();
		busA.letPassengersOn();
		busA.letPassengersOff();
		System.out.println(busA);
	}
}
