package TP4;
import java.util.List; 
import java.util.ArrayList;

class Personne{
    private String nom;
    private String prenom;
    public Personne( String n, String p) {this.nom = n; this.prenom = p;}
    public String toString() {return this.prenom + " " + this.nom + " ";  }
    public String getNom() {return this.nom;}
    public String getPrenom() {return this.prenom;}
}

class Depense{
    private Personne pers;
    private double montant; 
    private String produit;
    public Depense(Personne p, double mont, String prod ){
	this.pers = p; this.montant = mont; this.produit = prod;
    }
    public Personne getPersonne() {return this.pers;}
    public double getMontant() {return this.montant;}
    public String getProduit() {return this.produit;}

	public String toString() {
		String res = new String();
		res += "{" + this.getPersonne();
		res += " : " + this.getProduit();
		res +=  " , " + this.getMontant() + "euros";
		return res;
 	}
}


class WeekEnd{
    private List<Personne> listeAmis;
    private List<Depense> listeDepenses;

    public WeekEnd(){
		this.listeAmis = new ArrayList<>();
		this.listeDepenses = new ArrayList<>();
    }
	
    public List<Personne> getAmis() {
		return this.listeAmis;
    }

    public List<Depense> getDepenses() {
		return this.listeDepenses;
    }
    
    public void ajouterPersonne(Personne p){this.listeAmis.add(p);}
    
	public void ajouterDepense(Depense d){this.listeDepenses.add(d); }
    
	// totalDepensesPersonne prend en entrée une personne et renvoie la somme des depenses de cette personne.
    public double totalDepensesPersonne(Personne p){
		double s = 0.0;
		for(Depense d : this.listeDepenses) {
			if((p.getNom()).equals(d.getPersonne().getNom()))
			s += d.getMontant();
		}
		return s;
    }

    // totalDepenses renvoie la somme de toutes les dépenses. 
    public double totalDepenses(){
		double s = 0.0; 
		for(Depense d : this.listeDepenses) 
			s += d.getMontant();
		return s;
    }

    // depensesMoyenne renvoie la moyenne des dépenses pour une personne 
    public double depensesMoyenne(){
		double s = totalDepenses(); 
		int nb = this.listeAmis.size(); 
		if(nb != 0) 
			return s/nb;
		else 
			return s; 
    }

    //depenseProduit prend en entrée un produit, et renvoie la somme des dépenses pour ce produit. (par exemple du pain peut avoir été acheté plusieurs fois..)
    public double depenseProduit(String p){
		double s = 0.0; 
		for(Depense d : this.listeDepenses) 
			if (p.equals(d.getProduit()))
			s+= d.getMontant();
		return s;
    } 

    // avoirPersonne prend en entrée une personne et renvoie l'avoir de cette personne pour le week end.
    public double avoirPersonne(Personne p){
		double m = depensesMoyenne(); 
		double avoir = m - totalDepensesPersonne(p); 
		return avoir; 
    }
}


public class UIWeekEnd {

    public static void main(String[] args) {

    Personne pierre = new Personne("Durand","Pierre"); 
	System.out.println(pierre.getNom()); 
	Personne paul = new Personne("Dupond","Paul"); 
	Personne marie = new Personne("Dumond","Marie");
	Personne anne = new Personne("Dunon","Anne");  
	Depense d1 = new Depense(pierre, 12, "pain"); 	
	Depense d2 = new Depense(paul, 100, "pizza"); 
	Depense d3 = new Depense(pierre, 70, "essence");
	Depense d4 = new Depense(marie, 15, "vin");  
	Depense d5 = new Depense(paul, 10, "vin"); 
    WeekEnd we = new Weekend();
	we.ajouterPersonne(pierre); 
	we.ajouterPersonne(paul);
	we.ajouterPersonne(marie);
	we.ajouterPersonne(anne); 
	we.ajouterDepense(d1);   
	we.ajouterDepense(d2); 
	we.ajouterDepense(d4);   
	we.ajouterDepense(d3);
	we.ajouterDepense(d5);

    AppWeekEnd app = new AppWeekEnd(we);
    app.run();
    }
    }
        
    class AppWeekEnd {
    
        private WeekEnd we;
        private boolean quitter;
        private Personne personne_selectionnee;
    
        AppWeekEnd(WeekEnd we) {
        this.we = we;
        this.quitter = false;
        this.personne_selectionnee = null;
        }
    
        public void run() {
        bienvenue();
        boolean continuer = true;
        while(!quitter) {
            menu();
        }
        au_revoir();
        }
    
        public void menu() {
        boolean commande_faite = false;
        while(!commande_faite) {
            System.out.println("Que voulez vous faire?");
            System.out.println("P : afficher les personnes du week-end");
            System.out.println("Q: quitter");
            System.out.println("D: afficher les dépenses du week-end");
            System.out.println("T: afficher le total des dépenses du week-end");
            System.out.println("M: afficher la dépense moyenne par personne pour le week-end.");
            String commande_brute = System.console().readLine();
            String commande = commande_brute.strip().toLowerCase();
            if(commande.equals("q")) {
            quitter = true;
            commande_faite = true;
            } 
            else if (commande.equals("p")){
                List<Personne> personnes = this.we.getAmis();
                System.out.println("les participants sont :");
                for ( Personne p : personnes){
                    System.out.println("\t" +p );
                }
                System.out.println("");
            } 
            else if (commande.equals("d")){
                List<Depense> depense = this.we.getDepenses();
                System.out.println("les dépenses sont :");
                for ( Depense d : depense){
                    System.out.println("\t" +d );
                }
                System.out.println("");
            }
            else if (commande.equals("t")){
                double totalDepenses = this.we.totalDepenses();
                System.out.println("Total des dépenses :");
                for ( Depense p : depense){
                    System.out.println("\t" +t );
                }
                System.out.println("");
            }
            else if (commande.equals("m")){
                double depensesMoyenne = this.we.depensesMoyenne();
                System.out.println("La moyenne des dépenses :");
                for ( Depense d : depense){
                    System.out.println("\t" +m );
                }
                System.out.println("");
            }
    }
}
        /// Affiche un message de bienvenue
        public void bienvenue() {
        System.out.println("╭────────────────────────────────────────────────────────────────────────────────────╮");
        System.out.println("│ Bienvenue! En week-end comme dans la semaine, les bons comptes font les bons amis. │");
        System.out.println("╰────────────────────────────────────────────────────────────────────────────────────╯");
        
        }
        /// Affiche un message d'au revoir
        public void au_revoir() {
            System.out.println("Au revoir !!");
        
        
    
        }
    
    
}
