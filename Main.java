import java.util.ArrayList;

public class Main {

	public char intToChart(int num) {
		num += 65;
		char ch = (char) num;
		return ch;
	}

	public static void main(String[] args) {
		// Grafo
		Graph<Integer> grafo = new Graph<>(8);

		// A = 0
		grafo.addArista(0, 1, 3); // A - B
		grafo.addArista(0, 5, 2); // A - F

		// B = 1
		grafo.addArista(1, 2, 2); // B - C
		grafo.addArista(1, 3, 3); // B - D
		grafo.addArista(1, 4, 3); // B - E

		// C = 2, H = 7
		grafo.addArista(2, 4, 4); // C - E
		grafo.addArista(2, 7, 5); // C - H

		// D = 3
		grafo.addArista(3, 4, 2); // D - E
		grafo.addArista(3, 5, 1); // D - F

		// E = 4
		grafo.addArista(4, 6, 2); // E - G

		// F = 5
		grafo.addArista(5, 6, 1); // F - G

		// G = 6, H = 7
		grafo.addArista(6, 7, 5); // G - H

		grafo.printAdj();
		int start = 0, end = 4; // A to E
		ArrayList<Integer> path = grafo.dijkstra(start, end);
		ArrayList<Character> graphPath = grafo.path(path);

		System.out.println("\nCamino con menor peso desde el vértice " + grafo.intToChart(start) + " hasta el vértice "
				+ grafo.intToChart(end) + " es: " + graphPath + " con un peso de " + grafo.getpesoFinal() + "\n");
	}
}
