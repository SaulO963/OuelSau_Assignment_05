/**
 * Implements Cars going towards Bishop's. These take longer to cross the bridge. When it is their turn
 * they tend to "hog up" the Bridge by how slow they're cross, normally leaving enough time for another
 * Bishops car to want to cross (and thus take more time to cross)
 */
package Assignment;

import static java.lang.Thread.sleep;

/**
 * Bishops's Bound Car, extends Car methods/class variables, implements Runnable
 */
public class BishopsBoundCar extends Car implements Runnable{

    Bridge currentBridge;

    /**
     * Constructor associates car to certain Bridge object and sets name
     * @param b supplies Bridge object to link to
     * @param n supplies name to method
     */
    public BishopsBoundCar(Bridge b, String n){
        this.currentBridge = b;
        this.setName(n);
    }

    /**
     * Prints name of Car and Current cars on Bridge
     */
    public void printStatus(){
        System.out.print(this.getName());
        currentBridge.printCarsOnBridge();
    }

    /**
     * Run method simulates crossing the bridge, utilizes Semaphores to keep track of when car
     * can cross. Car reaches bridge 100x slower than LionsBoundCar, takes 4x longer than
     * Lion's Bound Car to cross the bridge.
     */
    @Override
    public void run() {

        System.out.println(this.getName() + " thread started!");

        while(true){

            try {
                sleep((long)(Math.random() * 10000));   //step 1, sleep rand time 0-10 seconds
                //step 2a, check if Bridge is empty, wait
                if(currentBridge.carsOnBridge.isEmpty()){
                    System.out.println(this.getName() + ": wants to go; Bridge Empty -> wait");
                    this.printStatus();
                }
                //step 2b, check if bridge has opposite type car on it, if so wait
                else if(currentBridge.firstCar() instanceof LionsBoundCar){
                    System.out.println(this.getName() + ": wants to go; Opposite car spotted -> wait");
                    this.printStatus();
                }
                //step 2c, check if bridge has same type car on it, if so go!
                else if(currentBridge.firstCar() instanceof BishopsBoundCar){
                    System.out.println(this.getName() + ": wants to go; Same car spotted -> go");
                    this.printStatus();
                    currentBridge.BBSema.release(); //release the permit!
                }
                currentBridge.BBSema.acquire(); //wait for permit here, or go if you have one
                currentBridge.addCar(this); //step 3, passes Semaphore check, cross bridge
                this.printStatus();
                sleep((long)(Math.random() * 12000)); //step 4, cross bridge (sleep for 12 sec)
                currentBridge.removeCar(this); //step 5, get off of bridge
                this.printStatus();
                if(currentBridge.carsOnBridge.isEmpty()){    //step 6, if no cars, other side can go
                    System.out.println("Lion's bound may now go.");
                    currentBridge.LBSema.release();
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }

}
