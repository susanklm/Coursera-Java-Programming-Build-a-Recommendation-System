
/**
 * Susan Kolim 
 * April 2021
 */
import java.util.*;
public class DirectorsFilter implements Filter {
    private String[] directors;

    public DirectorsFilter(String Directors) {
        directors = Directors.split(",");
    }
         
    @Override
    public boolean satisfies(String id) {
        //boolean result = false;
        for (String name : directors){
            if (MovieDatabase.getDirector(id).contains(name)){
                return true;
            }
        }
        return false;
    }
}
