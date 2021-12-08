/**
 * Susan Kolim 
 * April 2021
 */
import java.util.*;
public class MovieRunnerWithFilters {
    public int getSmallestValue(ArrayList<Rating> rate, int from) {
        int minIdx = from;
        for (int i=from+1; i< rate.size(); i++) {
            if (rate.get(i).getValue() < rate.get(minIdx).getValue()) {
                minIdx = i;
            }
        }
        return minIdx;
    }   
    
    public void sortByValue(ArrayList<Rating> ratingList){
        for (int i = 0; i< ratingList.size(); i++){
            int minIdx = getSmallestValue(ratingList,i);
            Rating qi = ratingList.get(i);
            Rating qmin = ratingList.get(minIdx);
            ratingList.set(i,qmin);
            ratingList.set(minIdx,qi);
        }
    }
    
    public void printAverageRatings(){
        //ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        int RatSize = tr.getRaterSize();
        //MovieDatabase.initialize("ratedmovies_short.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        //double avById = tr.getAverageByID("0068646", 5);
        //System.out.println("Average by ID is "+avById);
        ArrayList<Rating> ratingList = tr.getAverageRatings(1);
        /*for(Rating r : ratingList) {
            System.out.println(r);
        }*/
        System.out.println("found "+ratingList.size()+ " movies");
        //sortByValue(ratingList);
        Collections.sort(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            System.out.println(avgRating +" "+title);
        }
    }
    
    public void printAverageRatingsByYear(){
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = tr.getRaterSize();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        Filter f = new YearAfterFilter(2000);
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,f);
        System.out.println("found "+ratingList.size()+ " movies");
        sortByValue(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            int year = MovieDatabase.getYear(movieID);
            System.out.println(avgRating +" "+year+" "+title);
        }
    }
    
    public void printAverageRatingsByGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = tr.getRaterSize();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        Filter f = new GenreFilter("Crime");
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,f);
        System.out.println("found "+ratingList.size()+ " movies");
        sortByValue(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            String genre = MovieDatabase.getGenres(movieID);
            System.out.println(avgRating +" "+title);
            System.out.println(genre);
        }
    }
    
    public void printAverageRatingsByMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = tr.getRaterSize();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        Filter f = new MinutesFilter(110,170);
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,f);
        System.out.println("found "+ratingList.size()+ " movies");
        sortByValue(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            int minutes = MovieDatabase.getMinutes(movieID);
            System.out.println(avgRating +" "+" Time: "+minutes+" "+title);
        }
    }
    
    public void printAverageRatingsByDirectors(){
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = tr.getRaterSize();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        Filter f = new DirectorsFilter("Charles Chaplin,Michael Mann,Spike Jonze");
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,f);
        System.out.println("found "+ratingList.size()+ " movies");
        sortByValue(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            String directors = MovieDatabase.getDirector(movieID);
            System.out.println(avgRating +" "+title);
            System.out.println(directors);
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = tr.getRaterSize();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        Filter f1 = new YearAfterFilter(1980);
        Filter f2 = new GenreFilter("Romance");
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,allF);
        System.out.println("found "+ratingList.size()+ " movies");
        sortByValue(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            String genre = MovieDatabase.getGenres(movieID);
            System.out.println(avgRating +" "+title);
            System.out.println(genre);
        }
    }
    
    public void printAverageRatingsByDirectorsAndMinutes(){
        ThirdRatings tr = new ThirdRatings("data/ratings_short.csv");
        //ThirdRatings tr = new ThirdRatings("data/ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = tr.getRaterSize();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        Filter f1 = new MinutesFilter(30,170);
        Filter f2 = new DirectorsFilter("Spike Jonze,Michael Mann,Charles Chaplin,Francis Ford Coppola");
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> ratingList = tr.getAverageRatingsByFilter(1,allF);
        System.out.println("found "+ratingList.size()+ " movies");
        sortByValue(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            int minutes = MovieDatabase.getMinutes(movieID);
            String directors = MovieDatabase.getDirector(movieID);
            System.out.println(avgRating +" "+" Time: "+minutes+" "+title);
            System.out.println(directors);
        }
    }
}
