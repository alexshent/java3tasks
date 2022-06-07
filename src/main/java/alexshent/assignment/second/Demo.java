package alexshent.assignment.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Demo with user input
 */
public class Demo {
    public static void main(String[] args) {
        boolean attemptsNumberIsCorrect = false;
        do {
            System.out.println(Messages.MSG_TESTS_NUMBER_INPUT);
            try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {
                // read number of tests
                int attemptsNumber = Integer.parseInt(bufferedReader.readLine());
                if (attemptsNumber > 0 && attemptsNumber <= MaxValues.MAX_TEST_NUMBER) {
                    // read vertices
                    attemptsNumberIsCorrect = true;
                    new InputHandler(bufferedReader).handleVertices(attemptsNumber);
                } else {
                    System.out.println(Messages.MSG_INVALID_TEST_NUMBER_INPUT);
                }
            } catch (NumberFormatException e) {
                System.out.println(Messages.MSG_INVALID_NUMBER);
            } catch (IOException e) {
                System.out.println(Messages.MSG_IO_ERROR);
            }
        } while (!attemptsNumberIsCorrect);
    }
}
