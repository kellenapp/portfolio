package movie_diary;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Library
{
	private ArrayList<Film> films;
	public Library(String fileName)
	{
		try 
		{
			films = new ArrayList<Film>();
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) 
			{
				String[] data = line.split(",");
				if(data[0].equals("title") != true)
				{
					String filmName = data[0];
					int year = Integer.parseInt(data[1]);
					String director;
					if (data[2].equals("")) 
						{
						director= "Alan Smithee";
						}
					else
					{
						director = data[2];
					}
					int runtime = Integer.parseInt(data[3]);
					try
					{
						Film film = new Film(filmName, year, runtime, director);
						for (int i = 4; i < data.length; i++) 
						{
							data[i] = data[i].replace("\"", "");
							data[i] = data[i].replace("[", "");
							data[i] = data[i].replace("]", "");
							data[i] = data[i].replace("\'", "");
							data[i] = data[i].trim();
							film.addActor(data[i]);
						}
						films.add(film);
					}
					catch (IllegalStateException e)
					{
						System.out.println(e.getMessage());
					}
				}
			}
			br.close();
			fr.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public Film getFilm(String name, int year)
	{
		for (Film i : films)
		{
			if (i.getFilmName().equals(name) && i.getYear() == year)
			{
				return i;
			}
		}
		return null;
		
	}
}
