/**
 * Name: Saul Ouellet
 * Date: Apr 8th 2022
 * Description: Program implements a 1 lane Bridge with cars going different directions. Uses
 * semaphores to control the flow of traffic.
 */
package Assignment;

/**
 * Main class
 */
public class Main {

    /**
     * main tests different Bridges that initialize their semaphores with different amounts of permits.
     * @param args supplies args to main
     */
    public static void main(String[] args){

        //Bridge Arches = new Bridge(0,0);
        /*
            nothing "kick-starts" the cycle, our crossing cycle needs at least 1 permit to begin,
            all cars are stuck waiting forever
        */

        Bridge Arches = new Bridge(1,0);
        /*
            Bridge begins with 1 permit in BBSema, so even if LionsBoundCar reaches bridge first,
            the first pass always goes to BishopsBoundCar. Our expected cycle works as such:
            Bishops cars will continuously come and go onto the bridge - adding and removing themselves
            until the random sleep times make it so that a Bishops car doesn't come onto the bridge
            fast enough, so the bridge is empty. Once empty, it allows for the LionsBoundCar to come in,
            where it too will indefinitely come and go on the bridge until random sleep times make it
            so that the bridge is empty. Each direction takes turns crossing the bridge, until it is
            empty, then it allows the other direction to go.
         */

        //Bridge Arches = new Bridge(0,1);
        /*
            Same as previous, except cycle allows first pass to LionsBoundCar
         */

        //Bridge Arches = new Bridge(1,1);
         /*
            If both Semaphores begin with a permit, Lion's Bound Cars will be on the bridge,
            and nothing will stop the Bishops Bound Car from going. There is an off balance
            of permits in the cycle, and until the bridge empties itself again, there will be
            2 types of cars on the bridge.
         */

        BishopsBoundCar b1 = new BishopsBoundCar(Arches, "B1");
        BishopsBoundCar b2 = new BishopsBoundCar(Arches, "B2");
        BishopsBoundCar b3 = new BishopsBoundCar(Arches, "B3");

        LionsBoundCar l1 = new LionsBoundCar(Arches, "L1");
        LionsBoundCar l2 = new LionsBoundCar(Arches, "L2");


        synchronized (Arches){
            new Thread(b1).start();
            new Thread(b2).start();
            new Thread(b3).start();
            new Thread(l1).start();
            new Thread(l2).start();
        }

    }

}