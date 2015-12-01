/**
 * 
 * @author Antonin Riche
 *
 */

public class EquipeGuerriers {
	
	public static int MAX_GUERRIER = 10;
	
	private String nom;
	private Guerrier[] guerriers;
	private int nbr = 0;
	
	
	public EquipeGuerriers(String nom) {
		this.nom = nom;
		guerriers = new Guerrier[MAX_GUERRIER];
	}
	
	public int nombreGuerriersEnJeu(){
		return nbr;
	}
	
	boolean estPresent(Guerrier g){
		if(g == null)throw new IllegalArgumentException();
		for(int i = 0 ; i < nbr ; i++){
			if(guerriers[i].equals(g))return true;
		}
		return false;
	}
	
	Guerrier selectionner(String nom){
		if(nom == null)throw new IllegalArgumentException();
		for(int i = 0 ; i < nbr ; i++){
			if(guerriers[i].getNom().equals(nom))return guerriers[i];
		}
		return null;
	}	
	
	boolean mourrir(Guerrier g){
		if(nom == null)throw new IllegalArgumentException();
		if(!estPresent(g))return false;
		int i;
		for(i = 0 ; i < nbr ; i++){
			if(guerriers[i].getNom().compareTo(g.getNom())>0)break;
		}
		for(int j = nbr; j >= i ; j--){
			guerriers[j] = guerriers[j-1];
		}
		nbr--;
		return true;
	}
	
	public String toString(){
		String str = "Equipe "+nom+" :\n";
		for(int i = 0 ; i < nbr ; i++){
			str+="\t"+guerriers[i].toString()+"\n";
		}
		return str;
	}
	
	boolean mettreEnJeu(Guerrier g){
		if(g.getPointsDeVie() <= 0 && !estPresent(g))return false;
		int i;
		for(i = 0 ; i < nbr ; i++){
			if(guerriers[i].getNom().compareTo(g.getNom())>0)break;
		}
		for(int j = nbr+1; j > i ; j--){
			guerriers[j] = guerriers[j-1];
		}
		guerriers[i] = g;
		nbr++;
		return true;
	}
	
	private int nombreGuerriersSelonForce(int min, int max){
		int value = 0;
		for(int i = 0 ; i < nbr ; i++){
			if(guerriers[i].donnerForceDeFrappe() >= min && guerriers[i].donnerForceDeFrappe() <= max){
				value++;
			}
		}
		return value;
	}
	
	public Guerrier[] tableGuerriersSelonForce(int min, int max){
		if(min > max)throw new IllegalArgumentException();
		Guerrier[] tab = new Guerrier[nombreGuerriersSelonForce(min,max)];
		int j = 0;
		for(int i = 0 ; i < nbr ; i++){
			if(guerriers[i].donnerForceDeFrappe() >= min && guerriers[i].donnerForceDeFrappe() <= max){
				tab[j] = guerriers[i];
				j++;
			}
		}
		return tab;
	}
	
	private int nombreGuerriersAvecPrivileges(int[] privileges){
		int value = 0;
		for(int i = 0 ; i < nbr ; i++){
			if(guerriers[i].possedeTous(privileges)){
				value++;
			}
		}
		return value;
	}
	
	public Guerrier[] tableGuerriersAvecPrivileges(int[] privileges){
		
		Guerrier[] tab = new Guerrier[nombreGuerriersAvecPrivileges(privileges)];
		int j = 0;
		for(int i = 0 ; i < nbr ; i++){
			if(guerriers[i].possedeTous(privileges)){
				tab[j] = guerriers[i];
				j++;
			}
		}
		return tab;
	}
	
	private int nombreGuerriersSansPrivileges(int[] privileges){
		int value = 0;
		for(int i = 0 ; i < nbr ; i++){
			if(guerriers[i].possedeAucun(privileges)){
				value++;
			}
		}
		return value;
	}
	
	public Guerrier[] tableGuerriersSansPrivileges(int[] privileges){
		
		Guerrier[] tab = new Guerrier[nombreGuerriersAvecPrivileges(privileges)];
		int j = 0;
		for(int i = 0 ; i < nbr ; i++){
			if(guerriers[i].possedeAucun(privileges)){
				tab[j] = guerriers[i];
				j++;
			}
		}
		return tab;
	}
	
}
