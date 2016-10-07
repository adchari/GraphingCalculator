/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphingcalculator;

import java.util.ArrayList;

/**
 *
 * @author ADChari && MLGRavoSan
 */
public class Calculator {
    private String[] numChars = new String[12];
    Calculator() {
        numChars[0] = ".";
        numChars[1] = "1";
        numChars[2] = "2";
        numChars[3] = "3";
        numChars[4] = "4";
        numChars[5] = "5";
        numChars[6] = "6";
        numChars[7] = "7";
        numChars[8] = "8";
        numChars[9] = "9";
        numChars[10] = "-";
        numChars[11] = "0";
    }
    public String simplifyInput(String input) {
        System.out.println("simplfyInput(String) is currently running.");
        if (input.equals("")) {
            System.out.println("Error Source: Calculator.simplifyInput(String)");
            return "ERROR: EMPTY/INVALID INPUT";
        }
        int leftPCount = 0;
        int rightPCount = 0;
        for (int i = 0; i < input.length(); i++) {
            if (input.substring(i, i + 1).equals("("))
                leftPCount++;
            else if (input.substring(i, i + 1).equals(")"))
                rightPCount++;
        }   
        if (leftPCount != rightPCount){ 
            System.out.println("Error Source: Calculator.simplifyInput(String)");
            return "ERROR: MISMATCHED PARENTHESES";
        }
        else if (leftPCount == 0) {
            System.out.println("No parentheses were found.");
            return solve(input);
        }
        else {
            return interpret(input, leftPCount);
        }
    }
    public String interpret(String input, int pCount) {
        int leftIndex = 0, rightIndex = 0;
        try {
            int count = pCount;
            if (count == 0) {
                return solve(input);
            }
            else {
                String n = "";
                for (int i = 0; i < input.length(); i++){
                    String s = input.substring(i, i + 1);//parentheses
                        if (s.equals("(")){
                            int total = 1;
                            leftIndex = i + 1;
                            for(int j = i + 1; j < input.length(); j++){
                                String q = input.substring(j, j + 1);
                                    if(q.equals("(")) {
                                        total++;
                                    }
                                    else if (q.equals(")")) {
                                        total--;
                                    }
                                    if (total == 0) {
                                        rightIndex = j - 1;
                                        break;
                                    }
                            }
                        }
                }
                System.out.println("Left Index:  " + leftIndex);
                System.out.println("Right Index: " + rightIndex);
                if (solve(input.substring(leftIndex, rightIndex + 1)).contains("ERROR"))
                    return solve(input.substring(leftIndex, rightIndex));                    
                if (leftIndex > 2 && input.substring(leftIndex - 2, leftIndex - 1).equals("√")) {
                    return interpret(input.substring(0, leftIndex - 2) + Double.toString(root(Double.parseDouble(solve(input.substring(leftIndex, rightIndex + 1))), 2)) + input.substring(rightIndex + 2), pCount - 1);
                }
                else {
                    System.out.println("Part 1: " + input.substring(0, leftIndex - 1));
                    System.out.println("Part 2: " + input.substring(leftIndex, rightIndex + 1));
                    System.out.println("Part 3: " + input.substring(rightIndex + 2));
                    return interpret(input.substring(0, leftIndex - 1) + solve(input.substring(leftIndex, rightIndex + 1)) + input.substring(rightIndex + 2), pCount - 1);
                }
            }
        }
//        catch (NumberFormatException error) {
//            System.out.println("Error Source: Calculator.interpret(String, int)");
//            return "ERROR: INVALID NUMERICAL INPUT";
//        }
        catch (NullPointerException error) {
            System.out.println("Error Source: Calculator.interpret(String, int)");
            return "ERROR: EMPTY/INVALID INPUT";
        }
//        catch (IndexOutOfBoundsException error) {
//            System.out.println("Error Source: Calculator.interpret(String, int)");
//            System.out.println("Left Index:  " + leftIndex);
//            System.out.println("Right Index: " + rightIndex);
//            return "ERROR: INVALID INPUT";
//        }
    }
    public String solve(String input) {
        System.out.println("Input: " + input);
        try {
            if (input.equals("")) //TO ADD: Pi, e, E, more...?
                return "";
            for (int i = 1; i < input.length(); i++) { //TO SUPPLEMENT/FIX: i = 1 start
                if (input.substring(i, i + 1).equals("^")) {
                    double base = 0, exponent = 0;
                    if (input.length() <= 3) {
                        base = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        exponent = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i) + 1));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(exponent(base, exponent)) + input.substring(parseRightNum(input, i) + 1));
                    }
                    else {
                        base = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        exponent = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i)));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(exponent(base, exponent)) + input.substring(parseRightNum(input, i)));
                    }
                }
            }
            for (int i = 1; i < input.length(); i++) {
                if (input.substring(i, i + 1).equals("*")) {
                    double factor1 = 0, factor2 = 0;
                    if (input.length() <= 3) {
                        factor1 = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        factor2 = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i) + 1));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(multiply(factor1, factor2)) + input.substring(parseRightNum(input, i) + 1));
                    }
                    else {
                        factor1 = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        factor2 = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i)));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(multiply(factor1, factor2)) + input.substring(parseRightNum(input, i)));
                    }
                }   
                else if (input.substring(i, i + 1).equals("/")) {
                    double dividend = 0, divisor = 0;
                    if (input.length() <= 3) {
                        dividend = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        divisor = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i) + 1));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(divide(dividend, divisor)) + input.substring(parseRightNum(input, i) + 1));
                    }
                    else {
                        dividend = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        divisor = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i)));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(divide(dividend, divisor)) + input.substring(parseRightNum(input, i)));
                    }
                }
            }
            for (int i = 1; i < input.length(); i++) {
                if (input.substring(i, i + 1).equals("+")) {
                    double add1 = 0, add2 = 0;
                    if (input.length() <= 3) {
                        add1 = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        add2 = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i) + 1));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(add(add1, add2)) + input.substring(parseRightNum(input, i) + 1));
                    }
                    else {
                        add1 = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        add2 = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i)));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(add(add1, add2)) + input.substring(parseRightNum(input, i)));
                    }
                }
                else if (input.substring(i, i + 1).equals("~")) {
                    double sub1 = 0, sub2 = 0;
                    if (input.length() <= 3) {
                        sub1 = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        sub2 = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i) + 1));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(subtract(sub1, sub2)) + input.substring(parseRightNum(input, i) + 1));
                    }
                    else {
                        sub1 = Double.parseDouble(input.substring(parseLeftNum(input, i) + 1, i));
                        sub2 = Double.parseDouble(input.substring(i + 1, parseRightNum(input, i)));
                        return solve(input.substring(0, parseLeftNum(input, i) + 1) + Double.toString(subtract(sub1, sub2)) + input.substring(parseRightNum(input, i)));
                    }
                }
            }
            System.out.println("No Change to " + input);
            return input;
        }
//        catch (NumberFormatException error) {
//            System.out.println("Error Source: Calculator.solve(String)");
//            return "ERROR: INVALID NUMERICAL INPUT";
//        }
        catch (NullPointerException error) {
            System.out.println("Error Source: Calculator.solve(String)");
            return "ERROR: EMPTY/INVALID INPUT";
        }
//        catch (IndexOutOfBoundsException error) {
//            System.out.println("Error Source: Calculator.solve(String)");
//            return "ERROR: INVALID INPUT";
//        }
    }
    public int parseLeftNum(String input, int index) {
        for (int i = index - 1; i >= 0; i--) {
            boolean test = true;
            for (String chars: numChars) {
                if (input.substring(i, i + 1).equals(chars))
                    test = false;
            }
            if (test)
                return i;
        }
        return -1;
    }
    public int parseRightNum(String input, int index) {
        for (int i = index + 1; i < input.length(); i++) {
            boolean test = true;
            for (String chars: numChars) {
                if (input.substring(i, i + 1).equals(chars))
                    test = false;
            }
            if (test) {
                System.out.println("parseRightNum Index is " + i);
                return i;
            }
        }
        return input.length() - 1;
    }
    public boolean checkBounds(double a) {
        return ((a == Double.NaN) || (a == Double.POSITIVE_INFINITY) || (a == Double.NEGATIVE_INFINITY));
    }
    public double add(double a, double b){
        return (a+b);
    }
    public double subtract(double a, double b){
        return (a-b);
    }
    public double multiply(double a, double b){
        return (a*b);
    }
    public double divide(double a, double b){
        return (a/b);
    }
    public double factorial(int a){
        switch (a) {
            case 0:
                return 1;
            case 1:
                return 1;
            default:
                return a*factorial(a-1);
        }
    }
    public double exponent(double a, double b){
        return Math.pow(a, b);
    }
    public double root(double a, double b){
        return Math.pow(a, (1/b));
    }
    public double E() {
        return Math.E;
    }
    public double etothe(double p){
        return Math.pow(Math.E, p);
    }
    public double texponentiate(double p){
        return Math.pow(10, p);
    }
    public double ln(double p){
        return Math.log(p);
    }
    public double log(double p){
        return Math.log10(p);
    }
    public double toABS(double a) {
        return Math.abs(a);
    }
    public double sine(double a) {
        return Math.sin(a);
    }
    public double cosine(double a) {
        return Math.cos(a);
    }
    public double tangent(double a) {
        return Math.tan(a);
    }
    public double cosecant(double a) {
        return 1/Math.sin(a);
    }
    public double secant(double a) {
        return 1/Math.cos(a);
    }
    public double cotangent(double a) {
        return 1/Math.tan(a);
    }
    public double invSine(double a) {
        return Math.asin(a);
    }
    public double invCosine(double a) {
        return Math.acos(a);
    }
    public double invTangent(double a) {
        return Math.atan(a);
    }
    public double invCosecant(double a) {
        return Math.asin(1.0/a);
    }
    public double invSecant(double a) {
        return Math.acos(1.0/a);
    }
    public double invCotangent(double a) {
        return Math.PI/2.0 - Math.atan(a); //Prevents errors with angles of 0 degrees
    }
    public double toDegrees(double radmeasure) {
        return Math.toDegrees(radmeasure); //Return as product of Pi?
    }
    public double toRadians(double angmeasure) {
        return Math.toRadians(angmeasure);
    }
    public String toDMS(double decdegrees) {
        String initial = Double.toString(decdegrees);
        String degrees = initial.substring(0, initial.indexOf("."));
        initial = initial.substring(initial.indexOf(".") + 1);
        int minutes = (int)(Double.parseDouble(initial)/60.0);
        Double seconds = Double.parseDouble(initial) - Double.parseDouble(initial)/60.0;
        return degrees + "° " + minutes + "' " + seconds + "\"";
    }
    public double toDegrees(String dmsMeasure) {
        Integer degrees = Integer.parseInt(dmsMeasure.substring(0, dmsMeasure.indexOf("°")));
        Integer minutes = Integer.parseInt(dmsMeasure.substring(dmsMeasure.indexOf("°") + 1, dmsMeasure.indexOf("'")));
        Integer seconds = Integer.parseInt(dmsMeasure.substring(dmsMeasure.indexOf("'") + 1));
        return seconds/60.0 + minutes/60.0 + degrees;
    }
    public double logBase(double base, double argument) {
        return Math.log(argument)/Math.log(base);
    }
    public String summation(int start, int end, String operand) {
        String newOp = operand;
        for(int i = 0; i< newOp.length();i++){
            if (newOp.substring(i, i+1).equals("n")){
                String before = newOp.substring(0, i);
                String after = newOp.substring(i+1, newOp.length());
                newOp = before + start + after;
            }
        }
        if (start == end) {
            return solve(newOp);
        } 
        else {
            return solve(newOp) + summation(start+1, end, operand);
        }
    }
    public double nPr(int n, int r){
        double ans = factorial(n);
        ans/= factorial(n-r);
        return ans;
    }
    public double nCr(int n, int r){
        double ans = factorial(n);
        ans/= factorial(n-r);
        ans /= factorial(r);
        return ans;
    }
    public double lim(String fn, double val){
        double left = limleft(fn, val);
        double right = limright(fn, val);
        if(left == Double.NaN || right == Double.NaN){
            return Double.NaN;
        }
        if(Math.abs(left - right) < .001){
            return (right + left)/2.0;
        }
        return Double.NaN;
    }
    public double limleft(String fn, double val){
        double fval = solveFn(fn, val-1, "x");
        for(double i = val - 0.5; i<=val; i+= Math.abs(i-val)/2){
            double newfval = solveFn(fn, i, "x");
            if(Double.isInfinite(newfval)){
                return newfval;
            }
            else if(Double.isNaN(newfval)){
                return newfval;
            }
            else{
                if(Math.abs(newfval-fval) <= .00001){//round to 4 decimal places and return
                    newfval *= 10000.0;
                    newfval = (double) (Math.round(newfval));
                    newfval /= 10000.0;
                    return newfval;
                }
                else{
                    fval = newfval;
                }
            }
        }
        return Double.NaN;
    }
    public double limright(String fn, double val){
        double fval = solveFn(fn, val+1, "x");
        for(double i = val + 0.5; i>=val; i-= Math.abs(i-val)/2){
            double newfval = solveFn(fn, i, "x");
            if(Double.isInfinite(newfval)){
                return newfval;
            }
            else if(Double.isNaN(newfval)){
                return newfval;
            }
            else{
                if(Math.abs(newfval-fval) <= .00001){
                    newfval *= 10000.0;
                    newfval = (double) (Math.round(newfval));
                    newfval /= 10000.0;
                    return newfval;
                }
                else{
                    fval = newfval;
                }
            }
        }
        return Double.NaN;
    }
    public double solveFn(String fn, double val, String var){
        String newOp = fn;
        for(int j = 0; j< newOp.length();j++){//will change so i can solve
            if(newOp.substring(j, j+1).equals(var)){
                String before = newOp.substring(0, j);
                String after = newOp.substring(j+1, newOp.length());
                newOp = before + val + after;
            }
        }
        return Double.parseDouble(solve(newOp));
    }
    public double slope(double x1, double y1, double x2, double y2){
        return (y2 - y1)/(x2-x1);
    }
    public double round(double val){
        val *=10000.0;
        Math.round(val);
        val/= 10000.0;
        return val;      
    }
    public double productation(int start, int end, String operand) {
        String newOp = operand;
        for (int i = 0; i < newOp.length(); i++){
            if (newOp.substring(i, i+1).equals("n")){
                String before = newOp.substring(0, i);
                String after = newOp.substring(i+1, newOp.length());
                newOp = before + start + after;
            }
        }
        if (start == end) {
            return Double.parseDouble(solve(newOp));
        } 
        else {
            return Double.parseDouble(solve(newOp)) * Double.parseDouble(summation(start+1, end, operand));
        }
    }
    public ArrayList<Double> getGraphX(){
        ArrayList<Double> x = new ArrayList<>();
        for(double i = -10; i<= 10; i+=.1){
            x.add(i);
        }
        for(double d: x){
            d+=10;
            d*= 100;
        }
        return x;
    }
    public ArrayList<Double> getGraphY(String fn){
        ArrayList<Double> Y = new ArrayList<>();
        for(double i = -10; i<= 10; i+=.1){
            if(solveFn(fn, i, "x") >=10){
                Y.add(10.0);
            }
            else if(solveFn(fn, i, "x") <= -10){
                Y.add(-10.0);
            }
            else{
                Y.add(solveFn(fn, i, "x"));
            }
        }
        for(double d: Y){
            d+=10;
            d*= 100;
        }
        return Y;
    }
    public double integrate(String fn, double a, double b){
        int n = 100;
        double ans;
        double trapold = 0;
        double trapnew = 0;
        double midold = 0;
        double midnew = 0;
        do{
            trapold = trapnew;
            midold = midnew;
            trapnew = trapApprox(fn, a, b, n);
            midnew = midApprox(fn, a, b, n);
            n = n*2;
        } while((Math.abs(trapold - trapnew)> .00000001) && (Math.abs(midold - midnew)> .00000001));
        ans = midnew + midnew + trapnew;
        ans /= 3.0;
        ans *= 10000.0;
        ans = (double) (Math.round(ans));
        ans /= 10000.0;
        return ans;
    }
    public double trapApprox(String fn, double a, double b, int n){
        double dx = (b-a)/n;
        double ans = 0;
        for(int i = 0; i<=n; i++){
            if(i!= 0 && i!= n-1){
                ans += solveFn(fn,(a + dx*i),"x");
                ans += solveFn(fn,(a + dx*i),"x");
            }
            else{
                ans += solveFn(fn,(a + dx*i),"x");
            }
        } 
        ans /= 2.0;
        ans *= dx;
        return ans;
    }    
    public double midApprox(String fn, double a, double b, int n){
        double dx = (b-a)/n;
        double ans = 0;
        for(int i = 0; i<n; i++){
            ans +=solveFn(fn,((a + dx/2) + dx*i),"x");
        } 
        ans *= dx;
        return ans;
    }
}