package movie_diary;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Diary
{
	private ArrayList<DiaryEntry> logs;
	private String fileName;
	public Diary(String fileName, Library library)
	{
		if (fileName != null)
		{
			this.fileName = fileName;
		}
		try 
		{
			logs = new ArrayList<DiaryEntry>();
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			String line = null;
			while ((line = br.readLine()) != null) 
			{
				String[] data = line.split(",");
				if(data[0].equals("Name") != true)
				{
					String filmName = data[0];
					int year = Integer.parseInt(data[1]);
					double rating;
					LocalDate date;
					if (data[3].equals(null))
					{
						date = LocalDate.now();
					}
					else
					{
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
						date = LocalDate.parse(data[3], formatter);
					}
					if (data[2].equals(""))
					{
						try
						{
							if (library.getFilm(filmName, year) != null)
							{
								DiaryEntry diaryEntry = new DiaryEntry(library.getFilm(filmName, year), date);
								logs.add(diaryEntry);
							}
						}
						catch (IllegalStateException e)
						{
							System.out.println(e.getMessage());
						}
					}
					else
					{
						rating = Double.parseDouble(data[2]);
						try
						{
							if (library.getFilm(filmName, year) != null)
							{
								DiaryEntry diaryEntry = new DiaryEntry(library.getFilm(filmName, year), rating, date);
									logs.add(diaryEntry);
							}
						}
						catch (IllegalStateException e)
						{
							System.out.println(e.getMessage());
						}
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
	public void logFilm(DiaryEntry log)
	{
		if (log != null)
		{
			logs.add(log);
		}
		try 
		{
			Writer output;
			output = new BufferedWriter(new FileWriter(fileName, true));
			int i = logs.size();
			output.append(logs.get(i-1).toCSV());
			output.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public void logFilm(Film film, double rating, LocalDate date)
	{
		DiaryEntry diaryEntry = new DiaryEntry(film, rating, date);
		logs.add(diaryEntry);
		try 
		{
			Writer output;
			output = new BufferedWriter(new FileWriter(fileName, true));
			int i = logs.size();
			output.append(logs.get(i-1).toCSV());
			output.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public void logFilm(Film film, LocalDate date)
	{
		DiaryEntry diaryEntry = new DiaryEntry(film, date);
		logs.add(diaryEntry);
		try 
		{
			Writer output;
			output = new BufferedWriter(new FileWriter(fileName, true));
			int i = logs.size();
			output.append(logs.get(i-1).toCSV());
			output.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public void logFilm(Film film, double rating)
	{
		DiaryEntry diaryEntry = new DiaryEntry(film, rating);
		logs.add(diaryEntry);
		try 
		{
			Writer output;
			output = new BufferedWriter(new FileWriter(fileName, true));
			int i = logs.size();
			output.append(logs.get(i-1).toCSV());
			output.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public void logFilm(Film film)
	{
		DiaryEntry diaryEntry = new DiaryEntry(film);
		logs.add(diaryEntry);
		try 
		{
			Writer output;
			output = new BufferedWriter(new FileWriter(fileName, true));
			int i = logs.size();
			output.append(logs.get(i-1).toCSV());
			output.close();
		} 
		catch (IOException e) 
		{
			System.out.println(e.getMessage());
		}
	}
	public String[] getMostLoggedDirectors()
	{
		HashMap<String, Integer> list = new HashMap<>();
		for (DiaryEntry i : logs)
		{
			int n = 1;
			Film y = i.getFilm();
			if (y != null)
			{
				if (list.containsKey(y.getDirector()))
				{
					n = list.get(y.getDirector()) + 1;
				}
				if (n > 1)
				{
					list.replace(y.getDirector(), n);
				}
				else
				{
					list.put(y.getDirector(), n);
				}
			}
		}
		int[] topValue = new int[10];
		String[] topKey = new String[10];
		for (Map.Entry<String, Integer> e : list.entrySet())
		{
			Arrays.sort(topValue);
			if (e.getValue() > topValue[0])
			{
				topValue[0] = e.getValue();
			}
			Arrays.sort(topValue);
		}
		Set<String> keys = list.keySet();
		for(String key: keys)
		{
			if (list.get(key) == topValue[0] && topKey[0] == null)
			{
				topKey[0] = key;
			}
			else if (list.get(key) == topValue[1] && topKey[1] == null)
			{
				topKey[1] = key;
			}
			else if (list.get(key) == topValue[2] && topKey[2] == null)
			{
				topKey[2] = key;
			}
			else if (list.get(key) == topValue[3] && topKey[3] == null)
			{
				topKey[3] = key;
			}
			else if (list.get(key) == topValue[4] && topKey[4] == null)
			{
				topKey[4] = key;
			}
			else if (list.get(key) == topValue[5] && topKey[5] == null)
			{
				topKey[5] = key;
			}
			else if (list.get(key) == topValue[6] && topKey[6] == null)
			{
				topKey[6] = key;
			}
			else if (list.get(key) == topValue[7] && topKey[7] == null)
			{
				topKey[7] = key;
			}
			else if (list.get(key) == topValue[8] && topKey[8] == null)
			{
				topKey[8] = key;
			}
			else if (list.get(key) == topValue[9] && topKey[9] == null)
			{
				topKey[9] = key;
			}
		}
		return topKey;
	}
	public String[] getMostLoggedActors()
	{
		HashMap<String, Integer> list = new HashMap<>();
		for (DiaryEntry i : logs)
		{
			int n = 1;
			Film y = i.getFilm();
			for (int x = 0; x < y.actors.length; x++)
			{
				if (list.containsKey(y.actors[x]))
				{
					n = list.get(y.actors[x]) + 1;
				}
				if (n > 1)
				{
					list.replace(y.actors[x], n);
				}
				else
				{
					list.put(y.actors[x], n);
				}
			}
		}
		int[] topValue = new int[10];
		String[] topKey = new String[10];
		for (Map.Entry<String, Integer> e : list.entrySet())
		{
			Arrays.sort(topValue);
			if (e.getValue() > topValue[0])
			{
				topValue[0] = e.getValue();
			}
			Arrays.sort(topValue);
		}
		Set<String> keys = list.keySet();
		for(String key: keys)
		{
			if (list.get(key) == topValue[0] && topKey[9] == null)
			{
				topKey[9] = key;
			}
			else if (list.get(key) == topValue[1] && topKey[8] == null)
			{
				topKey[8] = key;
			}
			else if (list.get(key) == topValue[2] && topKey[7] == null)
			{
				topKey[7] = key;
			}
			else if (list.get(key) == topValue[3] && topKey[6] == null)
			{
				topKey[6] = key;
			}
			else if (list.get(key) == topValue[4] && topKey[5] == null)
			{
				topKey[5] = key;
			}
			else if (list.get(key) == topValue[5] && topKey[4] == null)
			{
				topKey[4] = key;
			}
			else if (list.get(key) == topValue[6] && topKey[3] == null)
			{
				topKey[3] = key;
			}
			else if (list.get(key) == topValue[7] && topKey[2] == null)
			{
				topKey[2] = key;
			}
			else if (list.get(key) == topValue[8] && topKey[1] == null)
			{
				topKey[1] = key;
			}
			else if (list.get(key) == topValue[9] && topKey[0] == null)
			{
				topKey[0] = key;
			}
		}
		return topKey;
	}
	public String[] getHighestRatedDirectors()
	{
		HashMap<String, Double> list = new HashMap<>();
		HashMap<String, Double> average = new HashMap<>();
		for (DiaryEntry i : logs)
		{
			double x;
			double n = 1;
			Film y = i.getFilm();
			if (y != null && i.getRating() != -1)
			{
				x = i.getRating();
				if (average.containsKey(y.getDirector()) && list.containsKey(y.getDirector()))
				{
					x = average.get(y.getDirector()) + i.getRating();
					n = list.get(y.getDirector()) + 1;
				}
				average.put(y.getDirector(), x);
				if (n > 1)
				{
					list.replace(y.getDirector(), n);
					average.replace(y.getDirector(), x);
				}
				else
				{
					average.put(y.getDirector(), x);
					list.put(y.getDirector(), n);
				}
			}
		}
		double[] topValue = new double[10];
		String[] topKey = new String[10];
		for (Map.Entry<String, Double> e : average.entrySet())
		{
			Arrays.sort(topValue);
			if ((e.getValue() / list.get(e.getKey())) > topValue[0])
			{
				topValue[0] = e.getValue() / list.get(e.getKey());
			}
			Arrays.sort(topValue);
		}
		Set<String> keys = list.keySet();
		for(String key: keys)
		{
			if (list.get(key) == topValue[0] && topKey[9] == null)
			{
				topKey[9] = key + topValue[0];
			}
			else if (list.get(key) == topValue[1] && topKey[8] == null)
			{
				topKey[8] = key + topValue[1];
			}
			else if (list.get(key) == topValue[2] && topKey[7] == null)
			{
				topKey[7] = key + topValue[2];
			}
			else if (list.get(key) == topValue[3] && topKey[6] == null)
			{
				topKey[6] = key + topValue[3];
			}
			else if (list.get(key) == topValue[4] && topKey[5] == null)
			{
				topKey[5] = key + topValue[4];
			}
			else if (list.get(key) == topValue[5] && topKey[4] == null)
			{
				topKey[4] = key + topValue[5];
			}
			else if (list.get(key) == topValue[6] && topKey[3] == null)
			{
				topKey[3] = key;
			}
			else if (list.get(key) == topValue[7] && topKey[2] == null)
			{
				topKey[2] = key;
			}
			else if (list.get(key) == topValue[8] && topKey[1] == null)
			{
				topKey[1] = key;
			}
			else if (list.get(key) == topValue[9] && topKey[0] == null)
			{
				topKey[0] = key + topValue[9];
			}
		}
		return topKey;
	}
	public String[] getHighestRatedByReleaseYear(int year)
	{
			HashMap<String, Double> list = new HashMap<>();
			HashMap<String, Double> average = new HashMap<>();
			for (DiaryEntry i : logs)
			{
				double x;
				double n = 1;
				Film y = i.getFilm();
				if (y != null && i.getRating() != -1 && y.getYear() == year)
				{
					x = i.getRating();
					if (average.containsKey(y.getDirector()) && list.containsKey(y.getDirector()))
					{
						x = average.get(y.getDirector()) + i.getRating();
						n = list.get(y.getDirector()) + 1;
					}
					average.put(y.getDirector(), x);
					if (n > 1)
					{
						list.replace(y.getDirector(), n);
						average.replace(y.getDirector(), x);
					}
					else
					{
						average.put(y.getDirector(), x);
						list.put(y.getDirector(), n);
					}
				}
			}
			double[] topValue = new double[10];
			String[] topKey = new String[10];
			for (Map.Entry<String, Double> e : average.entrySet())
			{
				Arrays.sort(topValue);
				if ((e.getValue() / list.get(e.getKey())) > topValue[0])
				{
					topValue[0] = e.getValue() / list.get(e.getKey());
				}
				Arrays.sort(topValue);
			}
			Set<String> keys = list.keySet();
			for(String key: keys)
			{
				if (list.get(key) == topValue[0] && topKey[9] == null)
				{
					topKey[9] = key;
				}
				else if (list.get(key) == topValue[1] && topKey[8] == null)
				{
					topKey[8] = key;
				}
				else if (list.get(key) == topValue[2] && topKey[7] == null)
				{
					topKey[7] = key;
				}
				else if (list.get(key) == topValue[3] && topKey[6] == null)
				{
					topKey[6] = key;
				}
				else if (list.get(key) == topValue[4] && topKey[5] == null)
				{
					topKey[5] = key;
				}
				else if (list.get(key) == topValue[5] && topKey[4] == null)
				{
					topKey[4] = key;
				}
				else if (list.get(key) == topValue[6] && topKey[3] == null)
				{
					topKey[3] = key;
				}
				else if (list.get(key) == topValue[7] && topKey[2] == null)
				{
					topKey[2] = key;
				}
				else if (list.get(key) == topValue[8] && topKey[1] == null)
				{
					topKey[1] = key;
				}
				else if (list.get(key) == topValue[9] && topKey[0] == null)
				{
					topKey[0] = key;
				}
			}
			int h = 0;
			for (int i=0; i < topKey.length; i++)
			{
				if (topKey[i] != null)
				{
					h++;
				}
			}
			String[] answer = new String[h];
			for (int i=0; i < answer.length; i++)
			{
				if (topKey[0] != null)
				{
					answer[i] = topKey[0];
				}
				else if (topKey[1] != null)
				{
					answer[i] = topKey[1];
				}
				else if (topKey[2] != null)
				{
					answer[i] = topKey[2];
				}
				else if (topKey[3] != null)
				{
					answer[i] = topKey[3];
				}
				else if (topKey[4] != null)
				{
					answer[i] = topKey[4];
				}
				else if (topKey[5] != null)
				{
					answer[i] = topKey[5];
				}
				else if (topKey[6] != null)
				{
					answer[i] = topKey[6];
				}
				else if (topKey[7] != null)
				{
					answer[i] = topKey[7];
				}
				else if (topKey[8] != null)
				{
					answer[i] = topKey[8];
				}
				else if (topKey[9] != null)
				{
					answer[i] = topKey[9];
				}
			}
			return answer;
	}
	public String[] getRecentLogs()
	{
		String[] list = new String[5];
		int n = logs.size();
		for (int i = 0; i < list.length; i++)
		{
			list[i] = logs.get(n-1).toCSV();
			n--;
			if (n == 0)
			{
				break;
			}
		}
		return list;
	}
}
