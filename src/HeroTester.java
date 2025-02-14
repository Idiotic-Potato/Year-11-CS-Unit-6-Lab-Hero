import org.junit.jupiter.api.Test;
import java.lang.reflect.Method;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HeroTester {
    public class Hero {
        private String name;
        private int hitPoints;
        private static final int Max_HP = 100;
        private static final int Attack_DAM  =10;

public Hero(String name){
    this.name = name;
    this.hitPoints = Max_HP;
}

public String getName(){
    return this.name;
}

public int getHitPoints(){
    return this.hitPoints;
}

public String toString() {
    return "Hero{name='" + name + "', hitPoints=" + hitPoints + "}";
}


public void attack(Hero opponent) {
    double rand = Math.random();
    if (rand < 0.5) {
        opponent.hitPoints = Math.max(0, opponent.hitPoints - Attack_DAM);
    } else {
        this.hitPoints = Math.max(0, this.hitPoints - Attack_DAM);
    }
}

public void senzuBean(){
    this.hitPoints = Max_HP;
}

private void fightUntilTheDeathHelper(Hero opponent){
    while (this.hitPoints > 0 && opponent.hitPoints > 0){
        this.attack(opponent);
    }
}

public String fightUntilTheDeath(Hero opponent){
    this.senzuBean();
    opponent.senzuBean();
    fightUntilTheDeathHelper(opponent);
    return this.name + " has " + this.hitPoints + " hit points remaining. " +
            opponent.name + " has " + opponent.hitPoints + " hit points remaining.";
}

private int[] nFightsToTheDeathHelper(Hero opponent, int n) {
int thisWins = 0;
int opponentWins = 0;
for(int i = 0;i<n;i++){
    this.senzuBean();
    opponent.senzuBean();
    fightUntilTheDeathHelper(opponent);
    if(this.hitPoints > 0){
        thisWins++;}
    else{
        opponentWins++;
}}

return new int[]{thisWins, opponentWins};

}
        public String nFightsToTheDeath(Hero opponent, int n) {
            int[] results = nFightsToTheDeathHelper(opponent, n);
            int thisWins = results[0];
            int opponentWins = results[1];

            StringBuilder sb = new StringBuilder();
            sb.append(this.name).append(": ").append(thisWins).append(" wins\n");
            sb.append(opponent.name).append(": ").append(opponentWins).append(" wins\n");

            if (thisWins > opponentWins) {
                sb.append(this.name).append(" wins!");
            } else if (opponentWins > thisWins) {
                sb.append(opponent.name).append(" wins!");
            } else {
                sb.append("OMG! It was actually a draw!");
            }

            return sb.toString();
        }



    public void dramaticFightToTheDeath(Hero opponent) {
        this.senzuBean();
        opponent.senzuBean();
        System.out.println("Dramatic fight between " + this.name + " and " + opponent.name + " begins!");

        while (this.hitPoints > 0 && opponent.hitPoints > 0) {
            this.attack(opponent);
            System.out.println(this.name + " has " + this.hitPoints + " hit points. " +
                    opponent.name + " has " + opponent.hitPoints + " hit points.");
        }

    }
    }

    private Hero hero1;
    private Hero hero2;

    public void setUp() {
        hero1 = new Hero("Andrew Tate");
        hero2 = new Hero("Taylor Swift");
    }

    public void tearDown() {
        hero1 = null;
        hero2 = null;
    }

    @Test
    public void TestConstructorName() {
        setUp();
        assertEquals("Andrew Tate", hero1.getName(), "Ensure that your constructor has been implemented correctly!");
        tearDown();
    }

    @Test
    public void TestConstructorHitPoints() {
        setUp();
        assertEquals(100, hero1.getHitPoints(), "Ensure that your constructor has been implemented correctly!");
        tearDown();
    }

    @Test
    public void TestAttack() {
        setUp();
        hero1.attack(hero2);
        assertEquals(false, hero1.getHitPoints() == hero2.getHitPoints(), "Ensure that your attack method has been implemented correctly!");
        tearDown();
    }

    @Test
    public void TestFightToTheDeath() {
        setUp();
        hero1.fightUntilTheDeath(hero2);
        assertTrue(hero1.getHitPoints() != hero2.getHitPoints(), "Ensure that your duelToTheDeath method has been implemented correctly!");
        tearDown();
    }

    @Test
    public void TestFightToTheDeathHpZero() {
        setUp();
        hero1.fightUntilTheDeath(hero2);
        if (hero1.getHitPoints() < hero2.getHitPoints()) assertTrue(hero1.getHitPoints() == 0, "Ensure that your duelToTheDeath method has been implemented correctly!");
        else assertTrue(hero2.getHitPoints() == 0, "Ensure that your duelToTheDeath method has been implemented correctly!");
        tearDown();
    }

    @Test
    public void TestFightToTheDeathNTimes() {
        setUp();

        int numberOfFights = 100;

        String result = hero1.nFightsToTheDeath(hero2, numberOfFights);
        int indexOfFirstColon = result.indexOf(":");
        int indexOfFirstColonEnd = result.indexOf("wins", indexOfFirstColon);
        int indexOfSecondColon = result.indexOf(":", indexOfFirstColon+1);
        int indexOfSecondColonEnd = result.indexOf("wins", indexOfSecondColon);

        int winsHero1 = Integer.parseInt(result.substring(indexOfFirstColon+2, indexOfFirstColonEnd-1));
        int winsHero2 = Integer.parseInt(result.substring(indexOfSecondColon+2, indexOfSecondColonEnd-1));

        assertEquals(numberOfFights, winsHero1+winsHero2, "Ensure that your nFightsToTheDeath method has been implemented correctly!");

        tearDown();
    }

    @Test
    public void TestToString() {
        setUp();
        String expected = "Hero{" +
                "name='" + hero1.getName() + '\'' +
                ", hitPoints=" + hero1.getHitPoints() +
                '}';
        assertEquals(expected, hero1.toString(), "Ensure that your toString method returns a String that is formatted like the document specifies!");
        tearDown();
    }

    @Test
    public void TestSenzuBean() {
        setUp();
        hero1.fightUntilTheDeath(hero2);
        hero1.fightUntilTheDeath(hero2);
        hero1.fightUntilTheDeath(hero2);
        tearDown();
    }

    @Test
    public void TestAllMethodsHaveBeenImplemented() {
        setUp();
        Method[] methods = hero1.getClass().getDeclaredMethods();
        assertEquals(10, methods.length, "Ensure that you have implemented all of the methods that are included in the description!");
        tearDown();
    }

    @Test
    public void TestSenzuBeanMethodHasBeenImplemented() {
        setUp();
        Method[] methods = hero1.getClass().getDeclaredMethods();
        boolean containsSenzuBean = false;
        for (Method method: methods) if (method.getName().equals("senzuBean")) containsSenzuBean = true;
        assertTrue(containsSenzuBean, "Ensure that you have implemented the senzuBean method!");
        tearDown();
    }

    @Test
    public void TestFightUntilTheDeathHelperHasBeenImplemented() {
        setUp();
        Method[] methods = hero1.getClass().getDeclaredMethods();
        boolean containsFightUntilTheDeathHelper = false;
        for (Method method: methods) if (method.getName().equals("fightUntilTheDeathHelper")) containsFightUntilTheDeathHelper = true;
        assertTrue(containsFightUntilTheDeathHelper, "Ensure that you have implemented the fightUntilTheDeathHelper method!");
        tearDown();
    }

    @Test
    public void TestFightUntilTheDeathNTimesHelperHasBeenImplemented() {
        setUp();
        Method[] methods = hero1.getClass().getDeclaredMethods();
        boolean containsNFightsToTheDeathHelper = false;
        for (Method method: methods) if (method.getName().equals("nFightsToTheDeathHelper")) containsNFightsToTheDeathHelper = true;
        assertTrue(containsNFightsToTheDeathHelper, "Ensure that you have implemented the nFightsUntilTheDeathHelper method!");
        tearDown();
    }

}
