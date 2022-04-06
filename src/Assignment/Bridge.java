/**
 * Implements Bridge class that cars will go across
 */
package Assignment;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Semaphore;

/**
 * Bridge Class
 */
public class Bridge {

    /**
     * Constructor initializes 2 semaphores with given amount of permits for each
     * @param BBSemaPermit supplies permit num for BBSema
     * @param LBSemaPermit supplies permit num for LBSema
     */
    public Bridge(int BBSemaPermit, int LBSemaPermit){
        this.BBSema = new Semaphore(BBSemaPermit);
        this.LBSema = new Semaphore(LBSemaPermit);
    }

    /**
     * carsOnBridge
     */
    public ArrayList<Car> carsOnBridge = new ArrayList<>();
    Semaphore BBSema;
    Semaphore LBSema;

    /**
     * Adds Car object to carsOnBridge ArrayList
     * @param c supplies Car object to method
     */
    public void addCar(Car c){
        carsOnBridge.add(c);
        System.out.println(c.getName() + " added");

    }

    /**
     * Removes Car object from carsOnBridge ArrayList
     * @param c supplies Car object to method
     */
    public void removeCar(Car c){

        Iterator<Car> iter = carsOnBridge.iterator();
        while(iter.hasNext()){
            if(iter.next() == c){
                carsOnBridge.remove(c);
                System.out.println(c.getName() + " removed");
                return;
            }
        }
        System.out.println(c.getName() + " not on bridge");
    }

    /**
     * Prints all of the current cars on bridge
     */
    public void printCarsOnBridge(){

        Iterator<Car> iter = carsOnBridge.iterator();
        System.out.println(":  === Cars on Bridge ===  ");
        System.out.print("     ");
        while(iter.hasNext()){
            System.out.print(iter.next().getName() + ", ");
        }
        System.out.println("\n     ======================");

    }

    /**
     * Checks to see the first type of Car on the bridge
     * @return returns first Car Object in carsOnBridge ArrayList
     */
    public Car firstCar(){
        Iterator<Car> iter = carsOnBridge.iterator();
        return iter.next();
    }

}
