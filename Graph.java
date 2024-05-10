import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Graph<T> {
	private ArrayList<ArrayList<Arista<T>>> adj = new ArrayList<>();
	int pesoFinal;

	public Graph(int vertex) {
		for (int i = 0; i < vertex; i++)
			adj.add(new ArrayList<>());
	}

	public int getpesoFinal() {
		return pesoFinal;
	}

	public void addArista(int start, int fin, int peso) {
		adj.get(start).add(new Arista<>(fin, peso));
		adj.get(fin).add(new Arista<>(start, peso));
	}

	public void printAdj() {
		for (int i = 0; i < adj.size(); i++) {
			System.out.print("El vértice '" + intToChart(i) + "' conecta con los vértices: ");
			for (int j = 0; j < adj.get(i).size(); j++) {
				Arista<T> arista = adj.get(i).get(j);
				System.out.print("'" + intToChart(arista.fin) + "' (peso de " + arista.peso + ") ");
			}
			System.out.println();
		}
	}

	public char intToChart(int num) {
		num += 65;
		char ch = (char) num;
		return ch;
	}

	private int minDistance(int[] distancia, boolean[] visited) {
		int min = Integer.MAX_VALUE;
		int minIndex = -1;

		for (int vertex = 0; vertex < distancia.length; vertex++) {
			if (!visited[vertex] && distancia[vertex] <= min) {
				min = distancia[vertex];
				minIndex = vertex;
			}
		}

		return minIndex;
	}

	public ArrayList<Integer> dijkstra(int start, int fin) {
		int vertex = adj.size();
		int[] distancia = new int[vertex];
		int[] route = new int[vertex];
		boolean[] visited = new boolean[vertex];

		Arrays.fill(distancia, Integer.MAX_VALUE);
		Arrays.fill(route, -1);
		distancia[start] = 0;

		for (int counter = 0; counter < (vertex - 1); counter++) {
			int estimated = minDistance(distancia, visited);
			visited[estimated] = true;

			for (Arista<T> arista : adj.get(estimated)) {
				if (!visited[arista.fin] && distancia[estimated] != Integer.MAX_VALUE
						&& distancia[estimated] + arista.peso < distancia[arista.fin]) {
					distancia[arista.fin] = distancia[estimated] + arista.peso;
					route[arista.fin] = estimated;
				}
			}
		}
		pesoFinal = distancia[fin];
		return path(route, fin);
	}

	private ArrayList<Integer> path(int[] route, int fin) {
		ArrayList<Integer> path = new ArrayList<>();
		int current = fin;
		while (current != -1) {
			path.add(current);
			current = route[current];
		}
		Collections.reverse(path);

		return path;
	}

	public ArrayList<Character> path(ArrayList<Integer> route) {
		ArrayList<Character> graphPath = new ArrayList<>();

		route.forEach((n) -> graphPath.add(intToChart(n)));

		return graphPath;
	}
}
