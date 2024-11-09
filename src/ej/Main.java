package ej;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ej.blocs.IBloc;
import ej.blocs.Mur;
import ej.blocs.Porte;
import ej.blocs.Toit;
import ej.blocs.Type;
import ej.exceptions.IllegalBlocException;
import ej.kits.KitDemarrage;

public class Main {

	private static Logger logger = LogManager.getLogger(Main.class);	

	public static void main(String[] args) throws IOException {

		logger.info("Lancement du programme Epicrafter's Journey.");
		
		try {
			// Le programme commence avec un Kit de démarrage.
			KitDemarrage kit = new KitDemarrage(constructionSetBlocs());
			System.out.println("Vous possédez un kit de démarrage !");
			System.out.println
			
					("Que souhaitez vous afficher ? " 
					+ "\n\t1 - Les idées de constructions"
					+ "\n\t2 - Le nombre de blocs pour chaque type de blocs présent dans le kit.");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			String reponse = reader.readLine();
			
			if (reponse.equals("1")) {
				
				// Il affiche les mots clés associés au Kit pour donner des idées à l'utilisateur.
				System.out.println("Voici quelques idées de constructions avec le Kit de démarrage.");
				Set<String> motsCles = kit.getMotsCles();
				for(String mot : motsCles) {
					System.out.println(mot);
				}
					
			} else if(reponse.equals("2")) {
				
				// Il affiche à l'utilisateur le nombre de blocs en fonction du type à contenu par le Kit.
				System.out.println("Voici le nombre de blocs de chaque type contenu dans le Kit de démarrage : ");
				Map<Type, Integer> quantiteBloc = new TreeMap<Type, Integer>(); // La TreeMap permet de trier les entrées par ordre alphabétique de la clé.
				for (IBloc bloc : kit.getBlocs()) {
					Type type = Type.valueOf(bloc.getClass().getSimpleName().toUpperCase());  // valueOf en Java est utilisée pour convertir une chaîne de caractères (ou d'autres types) en une instance d'un type spécifique
					int quantite = quantiteBloc.getOrDefault(type, 0) + 1; // Quantite existante + 1.
																		// getOrDefault en Java est utilisée avec les collections de type Map pour obtenir la valeur associée à une clé spécifique.
					quantiteBloc.put(type, quantite); //put() permet d'ajouter une paire clé-valeur à la Map. Si la clé existe déjà, la valeur associée est mise à jour avec la nouvelle valeur fournie.
				}
		
			Set<Type> types = quantiteBloc.keySet(); //nous obtenons l'ensembles des clés de la Map précédente
			for(Type type : types) {
				System.out.println(type.toString() + " " + quantiteBloc.get(type));
			}	
			
			}else {
				System.out.println("La valeur saisie n'est pas valide - tapez 1 ou 2.");
			}

					
		} catch (IllegalBlocException e) {
			System.out.println("Impossible de construire le Kit de démarrage.");
			
		} catch(IOException ze) {
			logger.error("Impossible de récupérer la saisie utilisateur.");
		}
		
		logger.info("Arret du programme Epicrafter's Journey.");		
	}
	
	private static Set<IBloc> constructionSetBlocs() throws IllegalBlocException {
		Set<IBloc> blocs = new LinkedHashSet<IBloc>();

		// Le kit contient 4 blocs Mur.
		blocs.add(new Mur(3, 2, 2, true));
		blocs.add(new Mur(3, 2, 2, true));
		blocs.add(new Mur(2, 1, 2, false));
		blocs.add(new Mur(2, 1, 2, false));

		// Le kit contient 1 bloc Porte.
		blocs.add(new Porte(1, 2, 2, true));

		// Le kit contient 4 blocs Toit.
		blocs.add(new Toit(3, 1, 1));
		blocs.add(new Toit(3, 1, 1));
		blocs.add(new Toit(3, 1, 1));
		blocs.add(new Toit(3, 1, 1));

		return blocs;
	}

}