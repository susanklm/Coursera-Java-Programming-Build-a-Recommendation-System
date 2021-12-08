/**
 * Susan Kolim 
 * April 2021
 */

import java.util.*;

public class MovieRunnerAverage {
    public int getSmallestValue(ArrayList<Rating> rate, int from) {
        int minIdx = from;
        for (int i=from+1; i< rate.size(); i++) {
            if (rate.get(i).getValue() < rate.get(minIdx).getValue()) {
                minIdx = i;
            }
        }
        return minIdx;
    }   
    
     public void printAverageRatings(){
        //SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        int movSize = sr.getMovieSize();
        int RatSize = sr.getRaterSize();
        System.out.println("Movie size is "+movSize);
        System.out.println("Raters size is "+RatSize);
        //double avById = sr.getAverageByID("0068646", 5);
        //System.out.println("Average by ID is "+avById);
        ArrayList<Rating> ratingList = sr.getAverageRatings(3);
        /*for(Rating r : ratingList) {
            System.out.println(r);
        }*/
         for (int i = 0; i< ratingList.size(); i++){
            int minIdx = getSmallestValue(ratingList,i);
            Rating qi = ratingList.get(i);
            Rating qmin = ratingList.get(minIdx);
            ratingList.set(i,qmin);
            ratingList.set(minIdx,qi);
         }
         for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = sr.getTitle(movieID);
            System.out.println(avgRating +" "+title);
         }
    }
    public void getAverageRatingOneMovie(){
        SecondRatings sr = new SecondRatings("data/ratedmovies_short.csv", "data/ratings_short.csv");
        //SecondRatings sr = new SecondRatings("data/ratedmoviesfull.csv", "data/ratings.csv");
        //String title = "The Godfather";
        String title = "Her";
        //String title = "Braveheart";
        String movieID = sr.getID(title);
        //Change getAverageByID method to public so it can be used here to save running time
        double avgRating = sr.getAverageByID(movieID, 1);
        System.out.println("Average rating of the movie "+title+ " is " +avgRating);
        //Alternative mehtod if getAverageByID method is private
        /*ArrayList<Rating> ratingList = sr.getAverageRatings(1); 
        for(Rating r : ratingList) {
            String movID = r.getItem();
            if (movieID.contains(movID)){
                double avgRating = r.getValue();
                System.out.println("Average rating of the movie "+title+ " is " +avgRating);
            }
        }*/
   }
}
