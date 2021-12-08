
/**
 * Susan Kolim 
 * April 2021
 */
public class GenreFilter implements Filter{
    private String genre;

    public GenreFilter(String Genre) {
	genre = Genre;
    }
	
    @Override
    public boolean satisfies(String id) {
	if (MovieDatabase.getGenres(id).contains(genre)){
            return true;
        }
        else{
            return false;
        }
    }
}
