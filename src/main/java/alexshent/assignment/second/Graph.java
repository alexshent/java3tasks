package alexshent.assignment.second;

import java.io.PrintStream;
import java.util.AbstractMap.SimpleEntry;
import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Graph that represents road networks
 */

public class Graph {

	private final int[][] contiguityMatrix;
	
	private final int vertexNumber;
	
	private final Map <Entry<Integer,Integer>, Integer> cache = new HashMap<>();
	
	public Graph(int vertexNumber) {
		this.vertexNumber = vertexNumber;
		contiguityMatrix = new int[vertexNumber][vertexNumber];
	}
	
	public int getVertexNumber() {
		return vertexNumber;
	}
	
	public void setCost(int fromVertex, int toVertex, int cost) {
		contiguityMatrix[fromVertex][toVertex]= cost;
		cache.clear();
	}
	
	public void printContiguityMatrix(PrintStream printStream) {
		for (int i = 0; i < vertexNumber; i++) {
			for (int j = 0; j < vertexNumber; j++) {
				printStream.print(contiguityMatrix[i][j] + "  ");
			}
			printStream.println();
		}
	}

	/**
     * Calculate minimal cost from start vertex to each vertex in graph with positive weights using Dijkstra Algorithm with O(vertexNumber^2)
     * 
     * @param startVertex - index of start vertex
     */
	
	private int[] dijkstraAlgorithm(int startVertex) {
		final int maxCost = 200_000;
		BitSet unvisitedVertexes = new BitSet(vertexNumber);
		for (int i = 0; i < vertexNumber; i++) {
			unvisitedVertexes.set(i); 
		}
		int [] minDistances = new int[vertexNumber];
		for (int i = 0; i < vertexNumber; i++) {
			if (i != startVertex) {
				minDistances[i] = maxCost;
			}
		}
		while (!unvisitedVertexes.isEmpty()) {
			int minDistance = maxCost;
			int vertexWithMinDistance = -1;
			for (int i = unvisitedVertexes.nextSetBit(0); i != -1; i = unvisitedVertexes.nextSetBit(i+1)) {
				if (minDistances[i] < minDistance) {
					minDistance = minDistances[i];
					vertexWithMinDistance = i;
				}
			}
			unvisitedVertexes.clear(vertexWithMinDistance);
			for (int i = unvisitedVertexes.nextSetBit(0); i != -1; i = unvisitedVertexes.nextSetBit(i+1)) {
				int neighborVertex = contiguityMatrix[vertexWithMinDistance][i];
				if (neighborVertex != 0) {
					if (minDistances[i] > minDistances[vertexWithMinDistance] + neighborVertex) {
						minDistances[i] = minDistances[vertexWithMinDistance] + neighborVertex;
					}
				}
			}
		}
		return minDistances;
	}
	
	public int getMinCostBetweenVertices (int startVertex, int endVertex) {
		Entry<Integer,Integer> vertexesPairKey = new SimpleEntry<>(startVertex, endVertex);
		if (cache.containsKey(vertexesPairKey)) {
            return cache.get(vertexesPairKey);
        } else {
			int [] distancesToStartVertex = dijkstraAlgorithm(startVertex);
			int minDistanceValue = distancesToStartVertex[endVertex];
			cache.put(vertexesPairKey, minDistanceValue);
			return minDistanceValue;
        }
	}
}
