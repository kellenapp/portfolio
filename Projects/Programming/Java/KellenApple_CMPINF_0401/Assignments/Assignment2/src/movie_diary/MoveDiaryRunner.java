package movie_diary;

import java.time.LocalDate;

import javax.swing.JOptionPane;

public class MoveDiaryRunner
{
	private static Library library;
    private static Diary diary;
    
    public static void logFilm()
    {
	String filmName = JOptionPane.showInputDialog("What's the name of the film?");
	int year = Integer.parseInt(JOptionPane.showInputDialog("What year did it release?"));
	Film toLog = library.getFilm(filmName, year);
	if(toLog == null) 
	{
	    JOptionPane.showMessageDialog(null, filmName + " (" + year + ") could not be found in the library.");
	    return;
	}
	
	String ratingStr = JOptionPane.showInputDialog("What rating do you give it? (0-5 stars, can be half stars; leave it blank if you'd prefer no rating)");
	String dateStr = JOptionPane.showInputDialog("When did you watch the film? (leave this blank if you want to log it for today)");
	
	if(ratingStr == null || ratingStr.length() < 1)
	{
	    if(dateStr == null || dateStr.length() < 1)
	    {
		diary.logFilm(toLog);
	    }
	    else
	    {
		diary.logFilm(toLog, LocalDate.parse(dateStr));
	    }
	}
	else if(dateStr == null || dateStr.length() < 1)
	{
	    diary.logFilm(toLog, Double.parseDouble(ratingStr));
	}
	else
	{
	    diary.logFilm(toLog, Double.parseDouble(ratingStr), LocalDate.parse(dateStr));
	}
    }
    
    public static void viewLogs()
    {
	//Grab the 5 most recent logs and show them to the user
	String[] recentLogs = diary.getRecentLogs();
	String logs = "";
	for(String log : recentLogs)
	{
	    logs += "\t" + log + "\n";
	}
	
	Object[] options = {"Most Logged Directors", "Most Logged Actors", "Highest Rated Directors", "Highest Rated Film by Year", "Go Back"};
        int n = JOptionPane.showOptionDialog(null,"RECENT LOGS:\n" + logs + "\nWhat would you like to do?" ,
        "Movie Diary Runner",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[2]);
        
        if(n == 0)
        {
            //Most Logged Directors
            String directors = "";
            for(String s : diary.getMostLoggedDirectors()) {directors += s + "\n";}
            JOptionPane.showMessageDialog(null, "Most Logged Directors are:\n" + directors);
        }
        else if(n == 1)
        {
            // Most Logged Actors
            String actors = "";
            for(String s : diary.getMostLoggedActors()) {actors += s + "\n";}
            JOptionPane.showMessageDialog(null, "Most Logged Actors are:\n" + actors);
        }
        else if(n == 2)
        {
            // Highest Rated Directors
            String directors = "";
            for(String s : diary.getHighestRatedDirectors()) {directors += s + "\n";}
            JOptionPane.showMessageDialog(null, "Highest Rated Directors are:\n" + directors);
        }
        else if(n == 3)
        {
            // Highest Rated Film by Year
            int year = Integer.parseInt(JOptionPane.showInputDialog("What year?"));
            String films = "";
            for(String s : diary.getHighestRatedByReleaseYear(year)) {films += s + "\n";}
            JOptionPane.showMessageDialog(null, "Highest Rated Films of " + year + " are:\n" + films);
        }
        
    }
    
    public static boolean mainPrompt() 
    {
	Object[] options = {"Log a film", "View Logs", "Quit"};
        int n = JOptionPane.showOptionDialog(null,"What would you like to do?",
        "Movie Diary Runner",
        JOptionPane.YES_NO_CANCEL_OPTION,
        JOptionPane.QUESTION_MESSAGE,
        null,
        options,
        options[2]);
        
        if(n == 0)
        {
            logFilm();
            return true;
        }
        else if(n ==1)
        {
            viewLogs();
            return true;
        }
        
        return n != 2;
    }

    public static void main(String[] args)
    {
	String library_fn = JOptionPane.showInputDialog(null, "What's the file name of your library?", "LIBRARY INSTANTIATION", 1);
	String diary_fn = JOptionPane.showInputDialog(null, "What's the file name of your diary?", "DIARY INSTANTIATION", 1);
	
	//Instantiate our library by reading in the library file
	library = new Library(library_fn);
	//Instantiate our diary by reading in the diary file
	diary = new Diary(diary_fn, library);
	
	boolean continuing = true;
	while(continuing)
	{
	    continuing = mainPrompt();
	}
    }

}
