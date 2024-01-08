package movie_diary;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Film
{
	private String filmName;
	private int year;
	private int runtime;
	private String director;
	public String[] actors;
	public int getYear()
	{
		return year;
	}
	public String getFilmName()
	{
		return filmName;
	}
	public String getDirector()
	{
		return director;
	}
	public Film(String filmName, int year, int runtime)
	{
		actors = new String[0];
		director = "Alan Smithee";
		if (runtime < 0)
		{
			throw new IllegalStateException("Runtime cannot be negative!");
		}
		else
		{
			this.runtime = runtime;
		}
		if(year < 1880)
		{
			throw new IllegalStateException("Year must be after 1880!");
		}
		else
		{
			this.year = year;
		}
		if (filmName != null)
		{
			this.filmName = filmName;
		}
	}
	public Film(String filmName, int year, int runtime, String director)
	{
		actors = new String[0];
		if (runtime < 0.0)
		{
			throw new IllegalStateException("Runtime cannot be negative!");
		}
		else
		{
			this.runtime = runtime;
		}
		if (year < 1880)
		{
			throw new IllegalStateException("Year must be after 1880!");
		}
		else
		{
			this.year = year;
		}
		if (filmName != null)
		{
			this.filmName = filmName;
		}
		if (director != null)
		{
			this.director = director;
		}
	}
	public String getFormattedRuntime()
	{
		int hours = runtime / 60;
		int minutes = runtime % 60;
		String answer;
		if (hours > 0)
		{
			answer = hours + " hours " + minutes + " minutes.";
		}
		else
		{
			answer = minutes + " minutes.";
		}
		return answer;
	}
	public void addActor(String actorName)
	{
		List<String> arrList = new ArrayList<String>(Arrays.asList(actors));
		arrList.add(actorName);
		actors = arrList.toArray(actors);
	}
	public String toString()
	{
		return filmName + " (" + year + ") directed by " + director + ", " + getFormattedRuntime();
	}
}
