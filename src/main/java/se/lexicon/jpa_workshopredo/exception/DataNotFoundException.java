package se.lexicon.jpa_workshopredo.exception;

public class DataNotFoundException extends Exception{

    private final String dataName;

    public DataNotFoundException(String message, String objectName)
    {
        super(message);
        this.dataName = objectName;
    }

    public String getObjectName()
    {
        return dataName;
    }

}
