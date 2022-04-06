/**
 * Abstract Car class
 */
package Assignment;

/**
 * Car class -- 1 class memeber + 1 method
 */
abstract class Car {
    private String name;

    /**
     * name Getter
     * @return returns String name
     */
    public String getName() {
        return name;
    }

    /**
     * name Setter
     * @param n supplies name to method
     */
    public void setName(String n){
        this.name = n;
    }
}
