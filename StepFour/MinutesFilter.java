
/**
 * Susan Kolim 
 * April 2021
 */
public class MinutesFilter implements Filter {
    private int minMin;
    private int minMax;
    public MinutesFilter(int min, int max){
        minMin = min;
        minMax = max;
    }
    
    @Override
    public boolean satisfies(String id) {
        if(MovieDatabase.getMinutes(id) >= minMin && MovieDatabase.getMinutes(id) <= minMax){
            return true;
        }
        else{
            return false;
        }
    }
}
