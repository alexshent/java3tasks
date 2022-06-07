package alexshent.assignment.second;

/**
 * User messages strings
 */
public class Messages {

    private Messages() {
    }

    public static final String MSG_TESTS_NUMBER_INPUT = "Please enter the number of tests (attempts). It must be positive number <= " +
            MaxValues.MAX_TEST_NUMBER;
    public static final String MSG_INVALID_TEST_NUMBER_INPUT = "The number of tests must be a positive number <= " + MaxValues.MAX_TEST_NUMBER;

    public static final String MSG_VERTEX_NUMBER_INPUT = "Enter the number of cities <= " + MaxValues.MAX_VERTEX_NUMBER;
    public static final String MSG_INVALID_VERTEX_NUMBER_INPUT = "The number of cities must be a positive number <= " + MaxValues.MAX_VERTEX_NUMBER;

    public static final String MSG_IO_ERROR = "Input reading error";
    public static final String MSG_INVALID_NUMBER = "Not a number";
    public static final String CITY_NAME_REQUIREMENTS = "City name must contain [a..Z] characters only and must be at most 10 characters long";
    public static final String MSG_INVALID_CITY_NAME = "Invalid city name. " + CITY_NAME_REQUIREMENTS;

    public static final String MSG_CITY_NAME_INPUT = "Please enter city name";
    public static final String MSG_NEIGHBOURS_NUMBER_INPUT = "Please enter the number of city neighbours. It must be a positive number <= %s ";

    public static final String MSG_NR_COST_INPUT = "Please enter nr cost (with space) , where  nr - index of a city connected to this city " +
            " (the index of the first city is 1, index must be >= 1 and <= %s) and [cost - positive integer number of the transportation cost]";

    public static final String MSG_INVALID_NR_COST_INPUT = "Index must be >= 1 and <= %s and cost must be > 0";
    public static final String MSG_INVALID_PAIR_NR_COST_INPUT = "It must be pair: nr cost";

    public static final String MSG_INVALID_NEIGHBOURS_NUMBER_INPUT = "Number of neighbours of city must be a positive number <= %s";
    public static final String MSG_PATH_NUMBER_INPUT = "Please enter the number of paths to find. It is a positive number <= " + MaxValues.MAX_PATH_NUMBER;

    public static final String MSG_SRC_DEST_CITY_INPUT = "Please enter NAME1 NAME2 (with space), where NAME1 - source city name (" + CITY_NAME_REQUIREMENTS + "), NAME2 - destination city name (" + CITY_NAME_REQUIREMENTS + ")";
    public static final String MSG_INCORRECT_CITIES_INPUT = "There are no such cities";
    public static final String MSG_INVALID_PAIR_SRC_DEST_INPUT = "Please enter two cities with space. " + CITY_NAME_REQUIREMENTS;

    public static final String MSG_INVALID_PATH_NUMBER_INPUT = "Number of path should be positive number <= " + MaxValues.MAX_PATH_NUMBER;
    public static final String MSG_COST_OUTPUT = "Cost = %s";
}
