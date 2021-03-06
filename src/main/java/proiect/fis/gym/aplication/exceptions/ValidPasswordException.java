package proiect.fis.gym.aplication.exceptions;

public class ValidPasswordException extends Exception{

    public ValidPasswordException(){ super("Please enter a valid password!\nThe password must contain:\n-at least one numeric character\n-at least one lowercase character,\n-at least one uppercase character\n-at least one special symbol among @#$%^&+=_\n-the password length must be between 8 and 20 characters"); }
}
