package villagegaulois;

import personnages.Chef;
import personnages.Gaulois;
import personnages.VillageSansChefException;

public class Village {
	private String nom;
	private Chef chef;
	private Gaulois[] villageois;
	private int nbVillageois = 0;
	private Marche marche;

	public Village(String nom, int nbVillageoisMaximum,int nbEtals) {
		this.nom = nom;
		marche=new Marche(nbEtals);
		villageois = new Gaulois[nbVillageoisMaximum];
	}
	private static class Marche{
		private Etal[] etals;
		public Marche(int nbEtal) {
			etals = new Etal[nbEtal];
			for(int i=0;i<nbEtal;i++) {
				etals[i]=new Etal();
			}
		}

		public void utiliserEtal(int indiceEtal, Gaulois vendeur,String produit, int nbProduit) {
			etals[indiceEtal].occuperEtal(vendeur,produit,nbProduit);			
		}

		public int trouverEtalLibre() {
			for(int i=0;i<etals.length;i++) {
				if(!etals[i].isEtalOccupe()) {
					return i;
				}				
			}
			return -1;
		}

		public Etal[] trouverEtals(String produit) {
			int nbReponse=0;
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()&&etals[i].contientProduit(produit)) {
					nbReponse++;
				}
			}

			Etal[] etalReponse=new Etal[nbReponse];
			int occ=0;
			for(int i=0;i<etals.length;i++) {	
				if(etals[i].isEtalOccupe()&&etals[i].contientProduit(produit)) {
					etalReponse[occ]=etals[i];
					occ++;
				}
			}
			
			return etalReponse;
		}

		public Etal trouverVendeur(Gaulois gaulois) {
			for(int i=0;i<etals.length;i++) {
				if(etals[i].getVendeur()==gaulois) {
					return etals[i];
				}
			}
			return null;
		}

		public String afficherMarche() {
			int etalsVide=0;
			StringBuilder chaine = new StringBuilder();
			for(int i=0;i<etals.length;i++) {
				if(etals[i].isEtalOccupe()) {
					chaine.append(etals[i].afficherEtal());
				}else {
					etalsVide++;
				}
			}
			chaine.append("Il reste "+etalsVide+" étals non utilisés dans le marché");
			return chaine.toString();

		}
	}

	public String getNom() {
		return nom;
	}

	public void setChef(Chef chef) {
		this.chef = chef;
	}

	public void ajouterHabitant(Gaulois gaulois) {
		if (nbVillageois < villageois.length) {
			villageois[nbVillageois] = gaulois;
			nbVillageois++;
		}
	}

	public Gaulois trouverHabitant(String nomGaulois) {
		if (nomGaulois.equals(chef.getNom())) {
			return chef;
		}
		for (int i = 0; i < nbVillageois; i++) {
			Gaulois gaulois = villageois[i];
			if (gaulois.getNom().equals(nomGaulois)) {
				return gaulois;
			}
		}
		return null;
	}

	public String afficherVillageois() throws VillageSansChefException{
		if(chef==null) {
			throw new VillageSansChefException("Pas de chef au village");
		}
		StringBuilder chaine = new StringBuilder();
		if (nbVillageois < 1) {
			chaine.append("Il n'y a encore aucun habitant au village du chef "
					+ chef.getNom() + ".\n");
		} else {
			chaine.append("Au village du chef " + chef.getNom()
			+ " vivent les légendaires gaulois :\n");
			for (int i = 0; i < nbVillageois; i++) {
				chaine.append("- " + villageois[i].getNom() + "\n");
			}
		}
		return chaine.toString();
	}
	public String installerVendeur(Gaulois vendeur, String produit,int nbProduit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append(vendeur.getNom()+" cherche un endroit pour vendre "+ nbProduit+" "+produit);
		chaine.append("\n");
		int indiceEtalVide=marche.trouverEtalLibre();
		marche.utiliserEtal(indiceEtalVide, vendeur, produit, nbProduit);
		chaine.append("Le vendeur "+vendeur.getNom()+" vend des "+produit+" a l'etal n°"+(indiceEtalVide+1));
		chaine.append("\n");
		return chaine.toString();
	}
	public String rechercherVendeursProduit(String produit) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Les vendeurs qui proposent des "+produit+" sont :");
		chaine.append("\n");

		int nbEtalReponse=marche.trouverEtals(produit).length;
		Etal[] etalReponse=marche.trouverEtals(produit);

		for(int i=0;i<nbEtalReponse;i++) {

			chaine.append("- "+etalReponse[i].getVendeur().getNom());
			chaine.append("\n");

		}
		return chaine.toString();
	}
	
	public Etal rechercherEtal(Gaulois vendeur) {
		return marche.trouverVendeur(vendeur);
	}
	
	public String partirVendeur(Gaulois vendeur) {
		StringBuilder chaine = new StringBuilder();
		chaine.append("\n");
		chaine.append(rechercherEtal(vendeur).libererEtal());
		return chaine.toString();
	}
	
	public String afficherMarche() {
		StringBuilder chaine = new StringBuilder();
		chaine.append("Le marché du village \""+this.nom+"\" possède plusieurs étals :");
		chaine.append("\n");
		chaine.append(marche.afficherMarche());
		return chaine.toString();
	}


}