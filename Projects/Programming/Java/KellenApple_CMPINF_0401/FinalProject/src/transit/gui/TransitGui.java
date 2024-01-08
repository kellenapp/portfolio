package transit.gui;

import java.awt.BasicStroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Stroke;
import javax.swing.JOptionPane;
import transit.core.*;
import transit.bus.*;
import transit.train.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.Random;
import java.util.concurrent.TimeUnit;
public class TransitGui extends JFrame
{
	JButton buttonAddRoute = new JButton("Add a route!");
	JButton buttonAddStop = new JButton ("Add stop to a route!");
	JButton buttonAddDriver = new JButton ("Add driver to a route!");
	JButton buttonRandomCity = new JButton ("Random City!");
	JButton buttonSpeed = new JButton ("Speed up!");
	JButton buttonSlow = new JButton ("Slow Down!");
	public int pixelForNames;
	public int frameRate;
	DataManager manager;
	public TransitGui() throws InterruptedException
	{
		super("Transit Manager");
		pixelForNames = 230;
		this.setBounds(50, 50, 800, 800);
		this.setLayout(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		// make buttons before setVisible
		buttonAddRoute.setBounds(90, 20, 120, 40);
	    buttonAddRoute.setBackground(Color.lightGray);
	    buttonAddRoute.setFont(new Font("Arial", Font.PLAIN, 14));
	    this.add(buttonAddRoute);
	    buttonAddStop.setBounds(225, 20, 160, 40);
	    buttonAddStop.setBackground(Color.lightGray);
	    buttonAddStop.setFont(new Font("Arial", Font.PLAIN, 14));
	    this.add(buttonAddStop);
	    buttonAddDriver.setBounds(400, 20, 180, 40);
	    buttonAddDriver.setBackground(Color.lightGray);
	    buttonAddDriver.setFont(new Font("Arial", Font.PLAIN, 14));
	    this.add(buttonAddDriver);
	    buttonRandomCity.setBounds(595, 20, 140, 40);
	    buttonRandomCity.setBackground(Color.lightGray);
	    buttonRandomCity.setFont(new Font("Arial", Font.PLAIN, 14));
	    this.add(buttonRandomCity);
	    buttonSpeed.setBounds(600, 70, 120, 40);
	    buttonSpeed.setBackground(Color.lightGray);
	    buttonSpeed.setFont(new Font("Arial", Font.PLAIN, 14));
	    this.add(buttonSpeed);
	    buttonSlow.setBounds(600, 120, 120, 40);
	    buttonSlow.setBackground(Color.lightGray);
	    buttonSlow.setFont(new Font("Arial", Font.PLAIN, 14));
	    this.add(buttonSlow);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    buttonSpeed.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed (ActionEvent e)
	    	{
	    		frameRate -= 100;
	    	}
	    });
	    buttonSlow.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed (ActionEvent e)
	    	{
	    		frameRate += 100;
	    	}
	    });
	    buttonRandomCity.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		manager.randomCity();
	    	}
	    	
	    });
	    buttonAddDriver.addActionListener(new ActionListener() 
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		String[] possibleValues;
	    		String str = "";
	    		for (Route i : manager.routes)
	    		{
	    			str += i.getRouteNumber() + ",";
	    		}
	    		possibleValues = str.split(",");
	    		Object selected = JOptionPane.showInputDialog(null,"Choose a route to add to!", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0]);
	    		int routeNum = 1;
	    		for (String i : possibleValues)
	    		{
	    			if(i.equals(selected))
	    			{
	    				routeNum = Integer.parseInt(i);
	    			}
	    		}
	    		String name = JOptionPane.showInputDialog("Enter a name for the driver!");
	    		double sp = Double.parseDouble(JOptionPane.showInputDialog("Enter a speed!"));
	    		manager.getRoute(routeNum).addDriver(name, sp);
	    	}
	    });
	    buttonAddStop.addActionListener(new ActionListener()
	    {
	    	public void actionPerformed(ActionEvent e)
	    	{
	    		String[] possibleValues;
	    		String str = "";
	    		for (Route i : manager.routes)
	    		{
	    			str += i.getRouteNumber() + ",";
	    		}
	    		possibleValues = str.split(",");
	    		Object selected = JOptionPane.showInputDialog(null,"Choose a route to add to!", "Input",JOptionPane.INFORMATION_MESSAGE, null,possibleValues, possibleValues[0]);
	    		int routeNum = 1;
	    		for (String i : possibleValues)
	    		{
	    			if(i.equals(selected))
	    			{
	    				routeNum = Integer.parseInt(i);
	    			}
	    		}
	    		String stopName = JOptionPane.showInputDialog("Please enter a name for the stop!");
				double x = Double.parseDouble(JOptionPane.showInputDialog("Please enter an x coordinate for the stop!"));
				double y = Double.parseDouble(JOptionPane.showInputDialog("Please enter an y coordinate for the stop!"));
				manager.getRoute(routeNum).addStop(stopName, x, y);
	    	}
	    });
	    buttonAddRoute.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				int routeNum = Integer.parseInt(JOptionPane.showInputDialog("Enter a route number! (No negatives or decimals)"));
				String busOrTrain = JOptionPane.showInputDialog("If it's a bus route type \"bus\", if it's a metro route type \"metro\" !");
				String stopName = JOptionPane.showInputDialog("Please enter a name for the first stop on the route!");
				double x = Double.parseDouble(JOptionPane.showInputDialog("Please enter an x coordinate for the first stop!"));
				double y = Double.parseDouble(JOptionPane.showInputDialog("Please enter an y coordinate for the first stop!"));
				String desc = JOptionPane.showInputDialog("Please enter a description for the route!");
				Route route;
				if(busOrTrain.equals("bus"))
				{
					BusStop stop = new BusStop(stopName, x, y);
					route = new BusRoute(routeNum, desc, stop);
				}
				else
				{
					MetroStation stop = new MetroStation(stopName, x, y);
					route = new MetroRoute(routeNum, desc, stop);
				}
				boolean h = true;
				while (h == true)
				{
					int a = JOptionPane.showConfirmDialog(null,"More stops?","Would you like to add another stop?", JOptionPane.YES_NO_OPTION);
					if(a==JOptionPane.YES_OPTION)
					{  
						String stopName1 = JOptionPane.showInputDialog("Please enter a name for the stop!");
						double x1 = Double.parseDouble(JOptionPane.showInputDialog("Please enter an x coordinate for the stop!"));
						double y1 = Double.parseDouble(JOptionPane.showInputDialog("Please enter an y coordinate for the stop!"));
						route.addStop(stopName1, x1, y1);
					}  
					else
					{
						h = false;
					}
				}
				manager.routes.add(route);
			}
		});
	    frameRate = 1000;
	    manager = new DataManager();
		this.setVisible(true);
		this.paint(getGraphics());
		loop();
	}
	public void paint(Graphics g)
	{
		super.paint(g);
		this.paintGrid(g);
		for(Route i: manager.routes)
		{
			this.paintRoute(i, g);
		}
	}
	public int getXPixel(double n)
	{
		return (int) ((n * 500) + 350);
	}
	public int getYPixel(double n) 
	{
		return (int) ((-n * 500) + 350);
	}
	public void paintRoute(Route route, Graphics g)
	{
		Stop current = route.firstStop;
		while (current.nextStop != route.firstStop) 
        {
			int x = getXPixel(current.getXCoordinate());
			int y = getYPixel(current.getYCoordinate());
			int xNext = getXPixel(current.nextStop.getXCoordinate());
			int yNext = getYPixel(current.nextStop.getYCoordinate());
			g.setColor(route.color);
			if(route.isBus() == true)
			{
				g.drawLine(x, y, xNext, y);
				g.drawLine(xNext, y, xNext, yNext);
			}
			else
			{
				g.drawLine(x, y, xNext, yNext);
			}
			paintStop(current, g);
            current = current.nextStop;
        }
		if (current != route.firstStop)
		{
			int x = getXPixel(current.getXCoordinate());
			int y = getYPixel(current.getYCoordinate());
			int xNext = getXPixel(current.nextStop.getXCoordinate());
			int yNext = getYPixel(current.nextStop.getYCoordinate());
			g.setColor(route.color);
			if(route.isBus() == true)
			{
				g.drawLine(x, y, xNext, y);
				g.drawLine(xNext, y, xNext, yNext);
			}
			else if (route.isBus()== false)
			{
				g.drawLine(x, y, xNext, yNext);
			}
			paintStop(current, g);
		}
		if (route.isBus() == true)
		{
			BusRoute busRoute = (BusRoute) route;
			for (Bus i: busRoute.getBuses())
			{
				paintVehicle(i, g);
			}
		}
		else if (route.isBus() == false)
		{
			MetroRoute metroRoute = (MetroRoute) route;
			for (Train i : metroRoute.getTrains())
			{
				paintVehicle(i, g);
			}
		}
	}
	public void paintStop(Stop stop, Graphics g)
	{
		int x = getXPixel(stop.getXCoordinate());
		int y = getYPixel(stop.getYCoordinate());
		if (stop.isBus() == true)
		{
			g.setColor(Color.blue);
		}
		else if (stop.isBus() == false)
		{
			g.setColor(Color.red);
		}
		g.drawOval(x - 5, y - 5, 10, 10);
		g.setColor(Color.black);
		g.setFont(new Font("Arial", Font.PLAIN, 14));
		g.drawString(stop.getStopName(), x + 5, y - 5);
	}
	public void paintVehicle(Vehicle vehicle, Graphics g)
	{
		int x = getXPixel(vehicle.getXCoordinate());
		int y = getYPixel(vehicle.getYCoordinate());
		if (vehicle.isBus() == true)
		{
			g.setColor(Color.blue);
			g.drawRoundRect(x - 10, y - 5, 20, 10, 3, 3);
			g.drawString(vehicle.getDriverName() + " on #" + vehicle.getRoute().getRouteNumber(), x, y);
		}
		else 
		{
			g.setColor(Color.red);
			g.drawRoundRect(x - 15, y - 5, 30, 10, 3, 3);
			g.drawString(vehicle.getDriverName() + " on #" + vehicle.getRoute().getRouteNumber(), x, y);
		}
	}
	public void paintGrid(Graphics g)
	{
		g.setColor(Color.black);
		g.drawLine(100, 100, 100, 600);
		g.drawLine(600, 100,600, 600);
		g.drawLine(100, 100, 600, 100);
		g.drawLine(100, 600, 600, 600);
		g.drawLine(150,100 ,150 ,600);
		g.drawLine(200,100 ,200 ,600);
		g.drawLine(250,100 ,250 ,600);
		g.drawLine(300,100 ,300 ,600);
		g.drawLine(350,100 ,350 ,600);
		g.drawLine(400,100 ,400 ,600);
		g.drawLine(450,100 ,450 ,600);
		g.drawLine(500,100 ,500 ,600);
		g.drawLine(550,100 ,550 ,600);
		g.drawLine(100, 150, 600, 150);
		g.drawLine(100, 200, 600, 200);
		g.drawLine(100, 250, 600, 250);
		g.drawLine(100, 300, 600, 300);
		g.drawLine(100, 350, 600, 350);
		g.drawLine(100, 400, 600, 400);
		g.drawLine(100, 450, 600, 450);
		g.drawLine(100, 500, 600, 500);
		g.drawLine(100, 550, 600, 550);
	}
	public void update()
	{
		manager.update();
	}
	public void loop() throws InterruptedException
	{
		Graphics g = this.getGraphics();
		while (true)
		{
			update();
			this.paint(g);
			synchronized (TimeUnit.MILLISECONDS)
			{
				TimeUnit.MILLISECONDS.wait(frameRate);
			}
		}
	}
	public static void main(String[] args) throws InterruptedException
	{
		TransitGui x = new TransitGui();
	}
}
