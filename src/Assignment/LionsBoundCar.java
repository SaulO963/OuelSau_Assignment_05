/**
 * Implements Cars going towards Lion's. These take almost no time to cross the bridge. Because they reach
 * the bridge so quickly, they tend to arrive at the bridge before the other Lions Bound Car has time to
 * leave - so they tend to "hog up" up by the bridge with how fast they leave and arrive.
 */
package Assignment;

import static java.lang.Thread.sleep;

/**
 * Lion's Bound Car, extends Car methods/class variables, implements Runnable
 */
public class LionsBoundCar extends Car implements Runnable{

    Bridge currentBridge;

    /**
     * Constructor associates car to certain Bridge object and sets name
     * @param b supplies Bridge object to link to
     * @param n supplies name to method
     */
    public LionsBoundCar(Bridge b, String n){
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
     * can cross. Car reaches bridge 100x faster than BishopsBoundCar, 4x faster than Bishops car
     * when crossing the bridge.
     */
    @Override
    public void run() {

        System.out.println(this.getName() + " thread started!");

        while(true){

            try {
                sleep((long)(Math.random() * 100));   //step 1, sleep rand time 0-.1 seconds
                if(currentBridge.carsOnBridge.isEmpty()){
                    System.out.println(this.getName() + ": wants to go; Bridge empty -> wait");
                    this.printStatus();
                }
                else if(currentBridge.firstCar() instanceof BishopsBoundCar){
                    System.out.println(this.getName() + ": wants to go; Opposite Car spotted -> wait");
                    this.printStatus();
                }
                else if(currentBridge.firstCar() instanceof LionsBoundCar){
                    System.out.println(this.getName() + ": wants to go; Same car spotted -> go");
                    this.printStatus();
                    currentBridge.LBSema.release();
                }
                currentBridge.LBSema.acquire(); //-1 permit
                currentBridge.addCar(this); //step 3, add ourselves to bridge
                this.printStatus();
                sleep((long)(Math.random() * 3000)); //step 4, sleep for 3 sec
                currentBridge.removeCar(this);
                this.printStatus();
                if(currentBridge.carsOnBridge.isEmpty()){
                    System.out.println("Bishop's bound may now go");
                    currentBridge.BBSema.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }


}
