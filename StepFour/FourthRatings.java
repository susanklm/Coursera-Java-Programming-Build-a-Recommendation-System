/**
 * Susan Kolim 
 * April 2021
 */ 

import java.util.*;
public class FourthRatings {
    public FourthRatings() {
       this("data/ratings_short.csv");
    }
    
    public FourthRatings(String ratingsfile){
        RaterDatabase.addRatings("data/"+ratingsfile);
    }
    
    public double getAverageByID(String id, int minimalRaters){
        double result = 0.0;
        double sum = 0.0;
        ArrayList<Double> ratingForAMovie = new ArrayList<Double>();
        for(Rater r : RaterDatabase.getRaters()){
           ArrayList<String> movieIDs = r.getItemsRated(); 
           for (int j = 0; j < movieIDs.size(); j++) {
               double rating = r.getRating(movieIDs.get(j));
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
        
    public double dotProduct(Rater me, Rater r){
        //translate rating from the scale 0 to 10 to the scale to 5 and -5
        double dotProduct = 0.0;
        ArrayList<String> meMovieIDs = me.getItemsRated();
        for (String movId : meMovieIDs){
            if (r.hasRating(movId)){
                double merating = me.getRating(movId)-5;
                double rrating = r.getRating(movId)-5;
                dotProduct += merating * rrating;
            }
        }
        return dotProduct;
    }  
           
    public ArrayList<Rating> getSimilarities(String id) {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters()) {
            if (r!=me) {
                double similarity = dotProduct(me,r);
                if (similarity > 0){
                    Rating newRating = new Rating(r.getID(), similarity);
                    list.add(newRating);
                }
            }
        }
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
    
    public ArrayList<Rating> getSimilarRatings(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(new TrueFilter());
        for (String movieID : movies) {
            double sum = 0.0;
            double weightedAvg = 0.0;
            int countRaters = 0;
            for(int k=0; k < numSimilarRaters; k++) {
                Rating r = list.get(k);
                double weight = r.getValue();
                String raterID = r.getItem();
                Rater rater = RaterDatabase.getRater(raterID);
                if (rater.hasRating(movieID)){
                    countRaters += 1;
                    sum += weight * rater.getRating(movieID);
                }
            }
            if (countRaters >= minimalRaters){
                weightedAvg = sum / countRaters;
                Rating newRating = new Rating(movieID, weightedAvg);
                result.add(newRating);
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
    
    public ArrayList<Rating> getSimilarRatingsByFilter(String id, int numSimilarRaters, 
    int minimalRaters, Filter filterCriteria){
        ArrayList<Rating> result = new ArrayList<Rating>();
        ArrayList<Rating> list = getSimilarities(id);
        ArrayList<String> movies = MovieDatabase.filterBy(filterCriteria);
        for (String movieID : movies) {
            double sum = 0.0;
            double weightedAvg = 0.0;
            int countRaters = 0;
            for(int k=0; k < numSimilarRaters; k++) {
                Rating r = list.get(k);
                double weight = r.getValue();
                String raterID = r.getItem();
                Rater rater = RaterDatabase.getRater(raterID);
                if (rater.hasRating(movieID)){
                    countRaters += 1;
                    sum += weight * rater.getRating(movieID);
                }
            }
            if (countRaters >= minimalRaters){
                weightedAvg = sum / countRaters;
                Rating newRating = new Rating(movieID, weightedAvg);
                result.add(newRating);
            }
        }
        Collections.sort(result, Collections.reverseOrder());
        return result;
    }
    
    public ArrayList<Rating> getSimilarRatings1line(String id, int numSimilarRaters, int minimalRaters){
        ArrayList<Rating> Similar1line = getSimilarRatingsByFilter(id, numSimilarRaters, 
        minimalRaters, new TrueFilter());
        return Similar1line;
    }
}

