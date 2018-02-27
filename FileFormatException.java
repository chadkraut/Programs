package exceptions;

/**
 * The file format exception is thrown when we are trying to read a file
 * but it is not in the format that you expect! 
 */
public class FileFormatException extends Exception {
    public FileFormatException(){
        super();
    }
    public FileFormatException(String s){
        super(s);
    }
}
