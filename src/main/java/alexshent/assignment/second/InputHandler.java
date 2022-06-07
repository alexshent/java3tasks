package alexshent.assignment.second;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

/**
 * User input handler
 */
public class InputHandler {
    private final BufferedReader bufferedReader;
    private final Pattern cityNamePattern = Pattern.compile("[a-zA-Z]{1,10}");

    public InputHandler(BufferedReader bufferedReader) {
        this.bufferedReader = bufferedReader;
    }

    private void handleNeighbours(Graph graph, int vertexIndex, int vertexNeighboursNumber) throws IOException {
        for (int vertexNeighboursIndex = 0; vertexNeighboursIndex < vertexNeighboursNumber; vertexNeighboursIndex++) {
            int vertexNumber = graph.getVertexNumber();
            System.out.printf((Messages.MSG_NR_COST_INPUT) + "%n", vertexNumber);
            String[] nrCost = bufferedReader.readLine().split("\\s+");
            if (nrCost.length == 2) {
                int index = Integer.parseInt(nrCost[0]);
                int cost = Integer.parseInt(nrCost[1]);
                if (index >= 1 && index <= vertexNumber && cost > 0) {
                    graph.setCost(vertexIndex, index - 1, cost);
                } else {
                    System.out.printf((Messages.MSG_INVALID_NR_COST_INPUT) + "%n", vertexNumber);
                    vertexNeighboursIndex--;
                    continue;
                }
            } else {
                System.out.println(Messages.MSG_INVALID_PAIR_NR_COST_INPUT);
                vertexNeighboursIndex--;
                continue;
            }
        }
    }

    private void handlePaths(Graph graph, Map<Integer, Integer> sourceDest, Map<String, Integer> vertices) throws NumberFormatException, IOException {
        boolean isPathNumberCorrect = false;
        do {
            System.out.println(Messages.MSG_PATH_NUMBER_INPUT);
            int pathNumber = Integer.parseInt(bufferedReader.readLine());
            if (pathNumber > 0 && pathNumber <= MaxValues.MAX_PATH_NUMBER) {
                isPathNumberCorrect = true;
                for (int pathIndex = 0; pathIndex < pathNumber; pathIndex++) {
                    System.out.println(Messages.MSG_SRC_DEST_CITY_INPUT);
                    String[] sourceDestination = bufferedReader.readLine().split("\\s+");
                    boolean isSourceDestinationCorrect = false;
                    do {
                        if (sourceDestination.length == 2 && cityNamePattern.matcher(sourceDestination[0]).matches()
                                && cityNamePattern.matcher(sourceDestination[1]).matches()) {
                            isSourceDestinationCorrect = true;
                            if (!vertices.isEmpty()) {
                                Integer sourceIndex = vertices.get(sourceDestination[0]);
                                Integer destIndex = vertices.get(sourceDestination[1]);
                                if ((sourceIndex != null) && (destIndex != null)) {
                                    sourceDest.put(sourceIndex, destIndex);
                                } else {
                                    System.out.println(Messages.MSG_INCORRECT_CITIES_INPUT);
                                    pathIndex--;
                                    break;
                                }
                            }
                        } else {
                            System.out.println(Messages.MSG_INVALID_PAIR_SRC_DEST_INPUT);
                            pathIndex--;
                            break;
                        }
                    } while (!isSourceDestinationCorrect);
                }
            } else {
                System.out.println(Messages.MSG_INVALID_PATH_NUMBER_INPUT);
            }
        } while (!isPathNumberCorrect);
        graph.printContiguityMatrix(new PrintStream(System.out));
        Object[] sourcesIndexes = sourceDest.keySet().toArray();
        for (Object sourcesIndex : sourcesIndexes) {
            System.out.printf(
                    Messages.MSG_COST_OUTPUT + "%n",
                    graph.getMinCostBetweenVertices((int) sourcesIndex, (int) sourceDest.get(sourcesIndex))
            );
        }
    }

    public void handleVertices(int attemptsNumber) {
        for (int attemptsIndex = 0; attemptsIndex < attemptsNumber; attemptsIndex++) {
            System.out.println(Messages.MSG_VERTEX_NUMBER_INPUT);
            try {
                int vertexNumber = Integer.parseInt(bufferedReader.readLine());
                if (vertexNumber > 0 && vertexNumber <= MaxValues.MAX_VERTEX_NUMBER) {
                    Graph graph = new Graph(vertexNumber);
                    Map<String, Integer> vertices = new LinkedHashMap<>();
                    Map<Integer, Integer> sourceDest = new LinkedHashMap<>();
                    for (int vertexIndex = 0; vertexIndex < vertexNumber; vertexIndex++) {
                        boolean isCityNameCorrect;
                        do {
                            System.out.println(Messages.MSG_CITY_NAME_INPUT);
                            String cityName = bufferedReader.readLine();
                            isCityNameCorrect = cityNamePattern.matcher(cityName).matches();
                            if (isCityNameCorrect) {
                                vertices.put(cityName, vertexIndex);
                            } else {
                                System.out.println(Messages.MSG_INVALID_CITY_NAME);
                            }
                        } while (!isCityNameCorrect);
                        System.out.printf((Messages.MSG_NEIGHBOURS_NUMBER_INPUT) + "%n", vertexNumber - 1);
                        int vertexNeighboursNumber = Integer.parseInt(bufferedReader.readLine());
                        if (vertexNeighboursNumber > 0 && vertexNeighboursNumber <= vertexNumber - 1) {
                            handleNeighbours(graph, vertexIndex, vertexNeighboursNumber);
                        } else {
                            System.out.printf((Messages.MSG_INVALID_NEIGHBOURS_NUMBER_INPUT) + "%n", vertexNumber - 1);
                            vertexIndex--;
                            continue;
                        }
                    }
                    handlePaths(graph, sourceDest, vertices);
                } else {
                    System.out.println(Messages.MSG_INVALID_VERTEX_NUMBER_INPUT);
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println(Messages.MSG_INVALID_NUMBER);
                continue;
            } catch (IOException e) {
                System.out.println(Messages.MSG_IO_ERROR);
                continue;
            }
        }
    }
}
