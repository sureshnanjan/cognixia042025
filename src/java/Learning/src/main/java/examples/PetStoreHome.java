package examples;

import java.util.ArrayList;
import java.util.List;

public class PetStoreHome {


    public PetStoreHome() {
        //this.title = title;
        //this.main = new ArrayList<PetCategory>()
        //this.main = new List<PetCategory>();
        //this.main.(new PetCategory(PetNames.bird));
        //this.main.(new PetCategory(PetNames.cat));
        //this.main.(new PetCategory(PetNames.dog));
        //this.main.(new PetCategory(PetNames.bird));
        //this.main.(new PetCategory(PetNames.reptile));
    }

    PetStoreLogo mylogo; // abstraction
    Searcher search;
    PetStoreAuthenticator sign_in;
    ShoppingCart cart;
    List<PetCategory>[] main;

    List<PetCategory>[] leftSide;
    Footer footer;


}
