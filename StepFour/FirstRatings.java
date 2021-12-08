/**
 * Susan Kolim 
 * April 2021
 */ 
import edu.duke.*;
import java.util.*;
import java.io.*;
import org.apache.commons.csv.*;

public class FirstRatings {
    
    public ArrayList<Movie> loadMovies(String filename){
        ArrayList<Movie> mData = new ArrayList<Movie>();
        FileResource fr = new FileResource(filename); 
        /*
        for (CSVRecord record : fr.getCSVParser()) {
            System.out.print(record.get("id") + " ");
            System.out.print(record.get("title") + " ");
            System.out.print(record.get("year") + " ");
            System.out.print(record.get("country") + " ");
            System.out.print(record.get("genre") + " ");
            System.out.print(record.get("director") + " ");
            System.out.println(record.get("minutes") + " ");
        }*/
        for (CSVRecord record : fr.getCSVParser()) {
           Movie nmovie = new Movie(record.get("id"), record.get("title"), 
           record.get("year"), record.get("genre"), record.get("director"), 
           record.get("country"), record.get("poster"), 
           Integer.parseInt(record.get("minutes").trim()));   
           mData.add(nmovie);
        }
        return mData;
    }
    
    public ArrayList<EfficientRater> loadRaters(String filename){
        FileResource fr = new FileResource(filename); 
        ArrayList<EfficientRater> ratingList = new ArrayList<EfficientRater>();
        int prevID = 0;
        for (CSVRecord record : fr.getCSVParser()) {
            int prevIdx = ratingList.size() - 1;
            if (ratingList.size() == 0){
                prevID = -1;
            }
            else{
                prevID = Integer.parseInt(ratingList.get(prevIdx).getID());
            }
            int nextID = Integer.parseInt(record.get("rater_id"));
            if (prevID == nextID){
                ratingList.get(prevIdx).addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
            }
            else{
                EfficientRater newRating = new EfficientRater(record.get("rater_id"));
                newRating.addRating(record.get("movie_id"), Double.parseDouble(record.get("rating")));
                ratingList.add(newRating);
            }
        }        
        //System.out.println("ratingList size is "+ratingList.size()); for small file is 5
        return ratingList;
    }
          
    public void testLoadRaters(){
        String source = "data/ratings_short.csv";
        //String source = "data/ratings.csv";
        ArrayList<EfficientRater> raters = loadRaters(source);
        HashSet<String> raterIDs = new HashSet<String>();
        ArrayList<Integer> numRatingsList = new ArrayList<Integer>();
        int max = 0;
        String movieID = "1798709";
        ArrayList<String> raterForAMovie = new ArrayList<String>();
        ArrayList<String> movieList = new ArrayList<String>();
        ArrayList<String> maxRaterId = new ArrayList<String>();
        for (int i = 0; i < raters.size(); i++) {
           String raterID = raters.get(i).getID();
           raterIDs.add(raterID);
           int numRating = raters.get(i).numRatings();
           numRatingsList.add(numRating);
           if (numRating > max){
               max = numRating;
           }
           System.out.println("ID: " + raterID + ". Number of ratings: " + numRating);
           ArrayList<String> movieIDs = raters.get(i).getItemsRated(); 
           for (int j = 0; j < movieIDs.size(); j++) {
                System.out.println("For Movie with ID: " + movieIDs.get(j) + " the rating by rater " + raterID +
                                   " is " + raters.get(i).getRating(movieIDs.get(j)));
                if (movieIDs.get(j).contains(movieID)){
                    raterForAMovie.add(raterID);
                }
                if (!movieList.contains(movieIDs.get(j))){
                    movieList.add(movieIDs.get(j));
                }
           }
           System.out.println("\n");
        }
        System.out.println("The total number of raters is "+raterIDs.size());
        System.out.println("The maximum number of rating is "+max); 
        for (int i = 0; i < raters.size(); i++) {
           String raterID = raters.get(i).getID();
           int numRating = raters.get(i).numRatings();
           if (numRating == max){
               maxRaterId.add(raterID);
           }
        }
        System.out.println("The rater/s with the maximum number of rating is/are: ");
        for (String maxR : maxRaterId){
            System.out.println(maxR);
        }
        System.out.println("The total number of raters who rate movie ID " +movieID+ " is " 
        +raterForAMovie.size());
        System.out.println("The rater/s which rate the above movie is/are: ");
        for (String rateAMov : raterForAMovie){
            //System.out.println(rateAMov);
        }
        System.out.println("The total number of movies rated in this file is "+movieList.size());
        System.out.println("The list of movies rated in this file is: ");
        for (String movList : movieList){
            //System.out.println(movList);
        }
    }
    
    public void testLoadMovies(){
        String source = "data/ratedmovies_short.csv";
        //String source = "data/ratedmoviesfull.csv";
        ArrayList<Movie> mData = loadMovies(source);
        System.out.println("There are " + mData.size() + " movies in the file." );
        for(Movie m : mData) {
            System.out.println(m);
            //System.out.println(m.toString());
        }
        int genresCount = 0;
        String genres = "Comedy";
        for (Movie m: mData){
            if (m.getGenres().contains(genres)){
                genresCount += 1;
            }
        }
        System.out.println("There are " +genresCount+ " of " +genres+ " genres.");
        int minCount = 0;
        for (Movie m: mData){
            if (m.getMinutes()>150){
                minCount += 1;
            }
        }
        System.out.println("There are " +minCount+ " which is greater than 150 minutes");
        int startIndex = 0;
        int stopIndex = 0;
        HashMap<String, Integer> movByDirectorMap = new HashMap<String, Integer>();
        //It is faster to use split
        //String[] directors = movie.getDirector().split(",");
        for(Movie m : mData) {
            ArrayList<String> names = new ArrayList<String>();
            if (m.getDirector().contains(",")){
                stopIndex = m.getDirector().indexOf(",", 0);
                while (stopIndex != -1){
                    String name = m.getDirector().substring(startIndex, stopIndex);
                    //System.out.println(name);
                    names.add(name);
                    startIndex = stopIndex + 1;
                    //System.out.println("start " +startIndex);
                    stopIndex = m.getDirector().indexOf(",", startIndex);
                    //System.out.println("stop "+stopIndex);
                }
                String finalName = m.getDirector().substring(startIndex+1, m.getDirector().length());
                names.add(finalName);
            }
            else{
                names.add(m.getDirector());
            }
            startIndex = 0;
            for (int k = 0; k < names.size(); k++){
                //System.out.println(names.get(k));
                String dirName = names.get(k);
                if (movByDirectorMap.keySet().contains(dirName)){
                    movByDirectorMap.put(dirName, movByDirectorMap.get(dirName) +1);
                }
                else {
                    movByDirectorMap.put(dirName,1);
                }
            }
        }
        System.out.println("There are "+movByDirectorMap.size()+ " directors in the file."); 
        int max = 0;
        String maxDirName = "";
        for (String dirName : movByDirectorMap.keySet()){
            int maxEachDir = movByDirectorMap.get(dirName);
            if (maxEachDir > max){
                max = maxEachDir;
                maxDirName = dirName;
            }
        }
        System.out.println("The maximum number of movie by a director is " +max+
        ". And the name of the director is "+maxDirName);
    }
}
