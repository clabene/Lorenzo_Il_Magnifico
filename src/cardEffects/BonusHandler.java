package cardEffects;

import java.util.ArrayList;

/**
 * Created by IBM on 30/05/2017.
 */
    public class BonusHandler {

        ArrayList<BonusOnFamilyMemberPlacement> bonusesOnFamilyMemberPlacement = new ArrayList<>();

        public ArrayList<BonusOnFamilyMemberPlacement> getBonusesOnFamilyMemberPlacement() {
            return bonusesOnFamilyMemberPlacement;
        }

    public void addBonus(Bonus bonus){
        if(bonus instanceof BonusOnFamilyMemberPlacement)
            bonusesOnFamilyMemberPlacement.add( (BonusOnFamilyMemberPlacement) bonus);
    }

}
