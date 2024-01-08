package movie_diary;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DiaryEntry
{
	private Film film;
	private double rating;
	private LocalDate date;
	public double getRating()
	{
		return rating;
	}
	public Film getFilm()
	{
		return film;
	}
	public DiaryEntry(Film film)
	{
		if (film != null)
		{
			this.film = film;
		}
		rating = -1;
		date = LocalDate.now();
	}
	public DiaryEntry(Film film, double rating)
	{
		if (film != null)
		{
			this.film = film;
		}
		if (rating != 0 && rating != 0.5 && rating != 1 && rating != 1.5 && rating != 2 && rating != 2.5 && rating != 3 && rating != 3.5 && rating != 4 && rating != 4.5 && rating != 5 && rating != -1)
		{
			throw new IllegalStateException ("Rating must be between 1 and 5!");
		}
		else
		{
			this.rating = rating;
		}
		date = LocalDate.now();
	}
	public DiaryEntry(Film film, LocalDate date)
	{
		if (film != null)
		{
			this.film = film;
		}
		if (date != null)
		{
			this.date = date;
		}
		rating = -1;
	}
	public DiaryEntry(Film film, double rating, LocalDate date)
	{
		if (film != null)
		{
			this.film = film;
		}
		if (rating != 0 && rating != 0.5 && rating != 1 && rating != 1.5 && rating != 2 && rating != 2.5 && rating != 3 && rating != 3.5 && rating != 4 && rating != 4.5 && rating != 5 && rating != -1)
		{
			throw new IllegalStateException ("Rating must be between 1 and 5!");
		}
		else
		{
			this.rating = rating;
		}
		if (date != null)
		{
			this.date = date;
		}
	}
	public String toString()
	{
		String answer;
		if (rating == -1)
		{
			answer = film.getFilmName() + " (" + film.getYear() + ") has no rating, and was entered into diary on " + date + ".";
		}
		else
		{
			answer = film.getFilmName() + " (" + film.getYear() + ") has a rating of " + rating + " and was entered on " + date + ".";
		}
		return answer;
	}
	public String toCSV()
	{
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/YYYY");
        String newDate = formatter.format(date);
        String csv;
        if (rating == -1)
        {
        	csv = film.getFilmName() + "," + film.getYear() + ",," + newDate + "\n";
        }
        else
        {
        	csv = film.getFilmName() + "," + film.getYear() + "," + rating + "," + newDate + "\n";
        }
        return csv;
	}
}
