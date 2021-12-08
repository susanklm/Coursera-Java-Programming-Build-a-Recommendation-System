
/**
 * Susan Kolim 
 * April 2021
 */
import java.util.*;
import java.util.Formatter;
public class RecommendationRunner implements Recommender {       
    public ArrayList<String> getItemsToRate(){
        Filter f = new YearAfterFilter(2000);
        ArrayList<String> movies = MovieDatabase.filterBy(f);  
        ArrayList<String> movieList = new ArrayList<String>();
        ArrayList<Integer> randomList = new ArrayList<Integer>(); 
        Random rand = new Random();
        int upperbound = movies.size();
        int numOfMovies = 20;
        //To prevent duplication of int_random
        for(int i = 0; i < numOfMovies; i++) {
           int int_random = rand.nextInt(upperbound); 
           if (!randomList.contains(int_random)){
               randomList.add(int_random);
           }
        }
        for(int i : randomList) {
            String movieID = movies.get(i);
            movieList.add(movieID);
        }
        return movieList;
    }
    
    public void printRecommendationsFor(String webRaterID){
        FourthRatings fr = new FourthRatings("ratings.csv");
        MovieDatabase.initialize("ratedmoviesfull.csv");
        int minimalRaters = 1;
        int similarRaters = 1;
        ArrayList<Rating> similarRatings = fr.getSimilarRatings(webRaterID, similarRaters, minimalRaters);
        int count = 0;
        System.out.println
        (
        "<style>"
        +" body { background-color: #99DDFF;}"
        + "h4{text-align: center; color: #004D99;}"
        + "#rating img{width: 50%; height: auto;}" 
        + "#rating table, th, td{ border: 1px solid black; border-collapse: collapse; text-align: center;background-color: #E6FBFF;}"
        + "#rating th, td{padding: 10px; font-size: 15px;}"
        + "#rating th {background-color: #B3F2FF;}"
        + "#rating table{margin-left: auto; margin-right: auto;}"
        + "</style>"
        );
        
        if (similarRatings.size() == 0){
            System.out.println("No matching movies found");
        }
        else{
            String body ="";
            String titleWeb = "<p><h1> List of recommended movies just for you </h1></p>";
            String notes ="<p><h4> Recommended movies is listed in accending order. The highly recommended movies are on the top</h4></p>"; 
            System.out.println(titleWeb);
            System.out.println(notes);
            String header = "<table id =\"rating\"> <tr> <th> # </th> <th> Poster </th> <th> Movie Title </th> <th> Year </th> <th> Genres </th></tr>)";
            for(Rating r : similarRatings){
                //double weightedAvgRating = r.getValue();
                String movieID = r.getItem();
                String title = MovieDatabase.getTitle(movieID);
                String genres = MovieDatabase.getGenres(movieID);
                int year = MovieDatabase.getYear(movieID);
                String poster = MovieDatabase.getPoster(movieID);
                //System.out.println(count+" "+weightedAvgRating +" "+title+ " "+genres);
                body += "<tr> <td>" +count+ 
                        " </td> <td> <img src =\"" +poster+ 
                        "\"> </td> <td>"+title+  
                        "</td> <td>" +year+ 
                        " </td> <td>" +genres+ 
                        " </td></tr>";
                count += 1;
                if (count > 15){
                    break;
                }
            }
            System.out.println(header + body + "</table>");
        }
    }
}
