package histoire;
import personnages.Chef;
import personnages.Druide;
import personnages.Gaulois;
import personnages.VillageSansChefException;
import villagegaulois.*;
public class ScenarioCasDegrade {
	
	public void testLibererEtalVide() {
		Etal etal = new Etal();
		etal.libererEtal();
	}
	public void testAcheteurNull() {
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);		
		
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine, "fleurs", 20);
		Etal etalFleur = village.rechercherEtal(bonemine);
		
		Gaulois asterix = new Gaulois("Astérix", 8);
		System.out.println(etalFleur.acheterProduit(15, null));
	}
	
	public void testQuantiteNegative() {
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);		
		
		Gaulois bonemine = new Gaulois("Bonemine", 7);
		village.ajouterHabitant(bonemine);
		village.installerVendeur(bonemine, "fleurs", 20);
		Etal etalFleur = village.rechercherEtal(bonemine);
		
		Gaulois asterix = new Gaulois("Astérix", 8);
		System.out.println(etalFleur.acheterProduit(-1, asterix));
	}
	
	public void testEtalDoitEtreOccupee() {
		Village village = new Village("le village des irréductibles", 10, 5);
		Chef abraracourcix = new Chef("Abraracourcix", 10, village);
		village.setChef(abraracourcix);		
		
		
		Gaulois asterix = new Gaulois("Astérix", 8);
		Etal etalFleur = new Etal();
		System.out.println(etalFleur.acheterProduit(15, asterix));
	}
	public void testVillageSansChef() throws VillageSansChefException {
		Village village = new Village("le village des irréductibles", 10, 5);
		village.afficherVillageois();
		
		Druide druide = new Druide("Panoramix", 2, 5, 10);
		Gaulois obelix = new Gaulois("Obélix", 25);
		Gaulois asterix = new Gaulois("Astérix", 8);

		village.ajouterHabitant(asterix);
		village.ajouterHabitant(obelix);
		village.ajouterHabitant(druide);
		
		village.afficherVillageois();
		
		
	}
	public static void main(String[] args) throws VillageSansChefException{
		ScenarioCasDegrade scenario= new ScenarioCasDegrade();
		
//		scenario.testLibererEtalVide();
//		scenario.testAcheteurNull();
//		scenario.testQuantiteNegative();
//		scenario.testEtalDoitEtreOccupee();
		scenario.testVillageSansChef();

		System.out.println("Fin du test");
	}

}
