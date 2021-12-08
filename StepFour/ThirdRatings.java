/**
 * Susan Kolim 
 * April 2021
 */ 

import java.util.*;

public class ThirdRatings {
    private ArrayList<EfficientRater> myRaters;
    
    public ThirdRatings() {
        // default constructor
        //this("data/ratings.csv");
        this("data/ratings_short.csv");
    }
    
    public ThirdRatings(String ratingsfile){
        FirstRatings fr = new FirstRatings();
        myRaters = fr.loadRaters(ratingsfile);
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
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies) {
            double avgById = getAverageByID(movieID,minimalRaters);
            if (avgById != 0){
                Rating newRating = new Rating(movieID, avgById);
                ratingList.add(newRating);
            }
        }
        return ratingList;
    }
    
    public ArrayList<Rating> getAverageRatingsByFilter(int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> ratingList = new ArrayList<Rating>();
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies) {
            double avgById = getAverageByID(movieID,minimalRaters);
            if (avgById != 0){
                Rating newRating = new Rating(movieID, avgById);
                ratingList.add(newRating);
            }
        }
        return ratingList;
    }
}
