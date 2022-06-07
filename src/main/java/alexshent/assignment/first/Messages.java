package alexshent.assignment.first;

public class Messages {
    private Messages() {
    }

    public static final String MSG_INDEX_INPUT = "Please enter a Catalan number index <= " + (Long.MAX_VALUE - 2) / 4;
    public static final String MSG_INVALID_CATALAN_SEQUENCE_INDEX = "n = %s is not valid, n must be non-negative and can not be greater than " + (Long.MAX_VALUE - 2) / 4;
    public static final String MSG_RESULT = "%d %s\n";
    public static final String MSG_INVALID_NUMBER = "You have entered invalid number";
    public static final String MSG_IO_ERROR = "input reading error";
}
