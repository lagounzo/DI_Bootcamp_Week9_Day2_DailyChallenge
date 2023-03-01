/**
 *Étant donné une liste de billets, trouvez l'itinéraire dans l'ordre en utilisant la liste donnée.
 *
 * Exemple:
 *
 * Input:
 * "Chennai" -> "Banglore"
 * "Bombay" -> "Delhi"
 * "Goa"    -> "Chennai"
 * "Delhi"  -> "Goa"
 * Sortir:
 *
 * Bombay->Delhi, Delhi->Goa, Goa->Chennai, Chennai->Banglore,
 * On peut supposer que la liste d'entrée des billets n'est pas cyclique et qu'il y a un billet de chaque ville à l'exception de la destination finale.
 *
 * Une solution consiste à construire un graphique et à effectuer un tri topologique du graphique. La complexité temporelle de cette solution est O(n).
 *
 * Nous pouvons également utiliser le hachage pour éviter de construire un graphe. L'idée est de trouver d'abord le point de départ.
 * Un point de départ ne serait jamais du côté « à » d'un ticket. Une fois que nous avons trouvé le point de départ, nous pouvons simplement
 * parcourir la carte donnée pour imprimer l'itinéraire dans l'ordre. Voici les étapes.
 *
 * Créez un HashMap d'une paire de tickets donnée. Laissez le HashMap créé être 'dataset'. Chaque entrée de 'dataset' est de la forme "from->to"
 * comme "Chennai" -> "Banglore"
 *
 *
 * Trouver le point de départ de l'itinéraire.
 *
 * a) Créez un HashMap inversé. Soit l'inverse soit 'reverseMap'
 * Les entrées de 'reverseMap' sont de la forme "to->form".
 * Voici 'reverseMap' pour l'exemple ci-dessus.
 * "Banglore" -> "Chennai"
 * "Delhi" -> "Bombay"
 * "Chennai" -> "Goa"
 * "Goa" -> "Delhi"
 *
 * b) Traverser 'ensemble de données'. Pour chaque clé de jeu de données, vérifiez si elle est là dans 'reverseMap'.
 * Si une clé n'est pas présente, alors nous avons trouvé le point de départ. Dans l'exemple ci-dessus, "Bombay" est le point de départ.
 *
 * Commencez par le point de départ trouvé ci-dessus et parcourez le « jeu de données »
 * pour imprimer l'itinéraire.
 *
 * Toutes les étapes ci-dessus nécessitent un temps O(n), donc la complexité temporelle globale est O(n).
 */

package exercice;

import java.util.HashMap;
import java.util.Map;

public class ItineraryFinder {

public static void printItinerary(String[][] tickets) {
    // Step 1: Create a HashMap of the given tickets
    Map<String, String> dataset = new HashMap<>();
    for (String[] ticket : tickets) {
        dataset.put(ticket[0], ticket[1]);
    }

    // Step 2: Find the starting point of the itinerary
    Map<String, String> reverseMap = new HashMap<>();
    for (String key : dataset.keySet()) {
        reverseMap.put(dataset.get(key), key);
    }
    String startPoint = null;
    for (String key : dataset.keySet()) {
        if (!reverseMap.containsKey(key)) {
            startPoint = key;
            break;
        }
    }

    // Step 3: Print the itinerary in order
    String nextPoint = dataset.get(startPoint);
    while (nextPoint != null) {
        System.out.print(startPoint + " -> " + nextPoint + ", ");
        startPoint = nextPoint;
        nextPoint = dataset.get(startPoint);
    }
    System.out.println();
}

public static void main(String[] args) {
    String[][] tickets = {
            {"Chennai", "Banglore"},
            {"Bombay", "Delhi"},
            {"Goa", "Chennai"},
            {"Delhi", "Goa"}
    };
    printItinerary(tickets); // Output: Bombay -> Delhi, Delhi -> Goa, Goa -> Chennai, Chennai -> Banglore,
}

}
