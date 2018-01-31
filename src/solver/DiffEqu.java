/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package solver;

/**
 *
 * @author d14125
 */
public class DiffEqu {
    public int a;
    public int b;
    public int omega;
    DiffEqu(int a, int b, int omega){
        this.a = a;
        this.b = b;
        this. omega = omega;
    }
    
    String printEqu(){
        return "x\"+" + a+"x\'+" +b+"x=" + getFunc();
    }
    
    String getFunc() {
        return "sin("+ omega +")";
    }
    
    public static void main(String[] args) {
        DiffEqu equ = new DiffEqu(2, 3, 5);
        System.out.print(equ.printEqu());
    }
}
