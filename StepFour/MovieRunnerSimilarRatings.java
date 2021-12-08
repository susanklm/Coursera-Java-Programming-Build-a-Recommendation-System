/**
 * Susan Kolim 
 * April 2021
 */
import java.util.*;
public class MovieRunnerSimilarRatings {
    public void printAverageRatings(){
        FourthRatings fr = new FourthRatings("ratings_short.csv");
        //FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = RaterDatabase.size();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        ArrayList<Rating> ratingList = fr.getAverageRatings(1);
        /*for(Rating r : ratingList) {
            System.out.println(r);
        }*/
        System.out.println("found "+ratingList.size()+ " movies");
        Collections.sort(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            System.out.println(avgRating +" "+title);
        }
    }
    
    public void printAverageRatingsByYearAfterAndGenre(){
        FourthRatings fr = new FourthRatings("ratings_short.csv");
        //FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmovies_short.csv");
        //MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = RaterDatabase.size();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        Filter f1 = new YearAfterFilter(1980);
        Filter f2 = new GenreFilter("Romance");
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> ratingList = fr.getAverageRatingsByFilter(1,allF);
        System.out.println("found "+ratingList.size()+ " movies");
        Collections.sort(ratingList);
        for(Rating r : ratingList) {
            double avgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            String genre = MovieDatabase.getGenres(movieID);
            System.out.println(avgRating +" "+title);
            System.out.println(genre);
        }
    }
    
    public void printSimilarRatings(){
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = RaterDatabase.size();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        //ArrayList<Rating> ratingList = fr.getSimilarities("1");
        //ArrayList<Rating> ratingList = fr.dotProduct("1","2");
        /*
        for(Rating r : ratingList) {
            System.out.println(r);
        }*/
        String id = "71";
        int minimalRaters = 5;
        int similarRaters = 20;
        ArrayList<Rating> ratingList = fr.getSimilarRatings(id, similarRaters, minimalRaters);
        //ArrayList<Rating> ratingList = fr.getSimilarRatings1line(id, similarRaters, minimalRaters);
        System.out.println("found "+ratingList.size()+ " movies");
        for(Rating r : ratingList) {
            double weightedAvgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            System.out.println(weightedAvgRating +" "+title);
        }
    }
    
    public void printSimilarRatingsByGenre(){
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = RaterDatabase.size();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        String id = "964";
        int minimalRaters = 5;
        int similarRaters = 20;
        Filter f = new GenreFilter("Mystery");
        ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter(id, similarRaters,
        minimalRaters, f);
        System.out.println("found "+ratingList.size()+ " movies");
        for(Rating r : ratingList) {
            double weightedAvgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            System.out.println(weightedAvgRating +" "+title);
        }
    }
    
    public void printSimilarRatingsByDirector(){
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = RaterDatabase.size();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        String id = "120";
        int minimalRaters = 2;
        int similarRaters = 10;
        //Filter f = new DirectorsFilter("Clint Eastwood,Sydney Pollack,David Cronenberg,Oliver Stone");
        Filter f = new DirectorsFilter("Clint Eastwood,J.J. Abrams,Alfred Hitchcock,Sydney Pollack,David Cronenberg,Oliver Stone,Mike Leigh");
        ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter(id, similarRaters,
        minimalRaters, f);
        System.out.println("found "+ratingList.size()+ " movies");
        for(Rating r : ratingList) {
            double weightedAvgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            System.out.println(weightedAvgRating +" "+title);
        }
    }
    
    public void printSimilarRatingsByGenreAndMinutes(){
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = RaterDatabase.size();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        String id = "168";
        int minimalRaters = 3;
        int similarRaters = 10;
        Filter f1 = new GenreFilter("Drama");
        Filter f2 = new MinutesFilter(80,160);
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter(id, similarRaters,
        minimalRaters, allF);
        System.out.println("found "+ratingList.size()+ " movies");
        for(Rating r : ratingList) {
            double weightedAvgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            System.out.println(weightedAvgRating +" "+title);
        }
    }
    
    public void printSimilarRatingsYearAfterAndMinutes(){
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int RatSize = RaterDatabase.size();
        int MovSize = MovieDatabase.size();
        System.out.println("Raters size is "+RatSize);
        System.out.println("Movies size is "+MovSize);
        String id = "314";
        int minimalRaters = 5;
        int similarRaters = 10;
        Filter f1 = new YearAfterFilter(1975);
        Filter f2 = new MinutesFilter(70,200);
        AllFilters allF = new AllFilters();
        allF.addFilter(f1);
        allF.addFilter(f2);
        ArrayList<Rating> ratingList = fr.getSimilarRatingsByFilter(id, similarRaters,
        minimalRaters, allF);
        System.out.println("found "+ratingList.size()+ " movies");
        for(Rating r : ratingList) {
            double weightedAvgRating = r.getValue();
            String movieID = r.getItem();
            String title = MovieDatabase.getTitle(movieID);
            System.out.println(weightedAvgRating +" "+title);
        }
    }
}
