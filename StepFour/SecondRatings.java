/**
 * Susan Kolim 
 * April 2021
 */ 

import java.util.*;
public class SecondRatings  {
    private ArrayList<Movie> myMovies;
    private ArrayList<EfficientRater> myRaters;
    
    public SecondRatings() {
        // default constructor
        //this("data/ratedmoviesfull.csv", "data/ratings.csv");
        this("data/ratedmovies_short.csv", "data/ratings_short.csv");
    }
    
    public SecondRatings(String moviefile, String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myMovies = fr.loadMovies(moviefile);
        myRaters = fr.loadRaters(ratingsfile);
    }
    
    public int getMovieSize(){
        return myMovies.size();
    }
    
    public int getRaterSize(){
        return myRaters.size();
    }
    
    public double getAverageByID(String id, int minimalRaters){
        double result = 0.0;
        double sum = 0.0;
        ArrayList<Double> ratingForAMovie = new ArrayList<Double>();
        for (int i = 0; i < myRaters.size(); i++) {
           ArrayList<String> movieIDs = myRaters.get(i).getItemsRated(); 
           for (int j = 0; j < movieIDs.size(); j++) {
               double rating = myRaters.get(i).getRating(movieIDs.get(j));
               if (movieIDs.get(j).contains(id)){
                    ratingForAMovie.add(rating);
               }
           }  
        }
        if (ratingForAMovie.size()< minimalRaters){
            return result;
        }
        else{
            for (int k = 0; k < ratingForAMovie.size(); k++){
                sum += ratingForAMovie.get(k);
                result = sum/ratingForAMovie.size();
            }
        }
        return result;
    }
    
    public ArrayList<Rating> getAverageRatings(int minimalRaters){
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        for (Movie m : myMovies) {
            String movieIDs = m.getID(); 
            double avgById = getAverageByID(movieIDs,minimalRaters);
            if (avgById != 0){
                Rating newRating = new Rating(movieIDs, avgById);
                ratingList.add(newRating);
            }
        }
        //If we want to use raters file instead. However,need to figure out how to add into ratingList
        /*for (int i = 0; i < myRaters.size(); i++) {
           ArrayList<String> movieIDs = myRaters.get(i).getItemsRated(); 
           for (int j = 0; j < movieIDs.size(); j++) {
               double avgById = getAverageByID(movieIDs.get(j),minimalRaters);
               if (!ratingList.contains(movieIDs.get(j))){
                   Rating nextRating = new Rating(movieIDs.get(j), avgById);
                   ratingList.add(nextRating);
               }     
           }  
        }*/
        return ratingList;
    }
    
    public String getTitle (String id){
        String result ="";
        for (Movie m : myMovies) {
            String movieIDs = m.getID(); 
            if (movieIDs.contains(id)){
                result = m.getTitle();
                //System.out.println(title +" "+movieIDs);
                return result;
            }
            else{
                result = "ID was not found";
            }
        }
        return result;
    }
    
    public String getID (String title){
        String result ="";
        for (Movie m : myMovies) {
            String movieTitle = m.getTitle(); 
            if (movieTitle.contains(title)){
                result = m.getID();
                return result;
            }
            else{
                result = "NO SUCH TITLE";
            }
        }
        return result;
    }
}