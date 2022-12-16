import java.util.Arrays;
import java.util.Deque;
import java.util.Queue;
import java.util.LinkedList;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;


public class Calculator {


    // Returns true if the input String is "+", "-", "*", "/", or "^"; returns false otherwise
    // You can use isOperator to test if a String is an operator or not
    // e.g. isOperator("+") => true
    //      isOperator("5") => false
    public static boolean isOperator(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*") || op.equals("/") || op.equals("^");
    }

    // Returns true if the input String is an integer; returns false otherwise
    // You can use isNumeric to test if a String is an number or not
    // e.g. isNumeric("+") => false
    //      isNumeric("5") => true
    public static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e){
            return false;
        }
    }


    // Returns the precedence level of the operator; a higher int
    // represents a higher precedence level
    //
    // Precedence rankings:
    // 0: left paren
    // 1: add/sub
    // 2: multiply/divide
    // 3: exponent
    public static double getPrecedence(String str) {
        if (str.equals("(")) {
            return 1;
        }
        else if (str.equals("+") || str.equals("-")) {
            return 2;
        }
        else if (str.equals("*") || str.equals("/")) {
            return 3;
        }
        else if (str.equals("^")) {
            return 4;
        }
        else {
            return 0;
        }
    }

    // Returns true if the precedence of the *top* argument is
    // greater than or equal to the precedence of the *op* argument
    public static boolean precedenceCompare(String op, String top) {
        return getPrecedence(top) >= getPrecedence(op);
    }


    // Takes in a string containing a math expression in infix format and outputs a
    // queue containing the tokens in postfix format
    // To be completed in **Part 1**
    public static Queue<String> convert(String infix) {
        // We need to create two data structures: a queue for our output, and a stack for our operators
        Queue<String> outputQueue = new LinkedList<String>();
        Deque<String> operatorStack = new LinkedList<String>();

        String[] arr = infix.split(" ");

        // Go through the input array
        for (int i=0; i<arr.length; i++) {
            String next = arr[i];

            // Probability conversion
            if (next.equals("Probability(")) {
                String nextItem = arr[i+1];
                String[] input = nextItem.split(",");
                LinkedList<String> lst = new LinkedList<String> (Arrays.asList(input));
                int type = Integer.parseInt(lst.get(0));
                double para1 = Double.parseDouble(lst.get(1));
                int para2 = Integer.parseInt(lst.get(2));
                int para3 = 0;
                try {
                    para3 = Integer.parseInt(lst.get(3));
                } catch (ArrayIndexOutOfBoundsException e) { /* Intentionally left blank */ }
                switch (type) {
                    case 1:
                        outputQueue.add(geompdf(para1, para2) + "");
                        break;
                    case 2:
                        outputQueue.add(geomcdf(para1, para2) + "");
                        break;
                    case 3:
                        outputQueue.add(binompdf(para1, para2, para3) + "");
                        break;
                    case 4:
                        outputQueue.add(binomcdf(para1, para2, para3) + "");
                        break;
                }
                return outputQueue;
            }

            // Distribution conversion
            if (next.equals("NormalCdf(")) {
                String nextItem = arr[i+1];
                String[] input = nextItem.split(",");
                LinkedList<String> lst = new LinkedList<String> (Arrays.asList(input));
                int type = Integer.parseInt(lst.get(0));
                double para1 = Double.parseDouble(lst.get(1));
                double para2 = Double.parseDouble(lst.get(2));
                double para3 = Double.parseDouble(lst.get(3));
                double para4 = Double.parseDouble(lst.get(4));
                outputQueue.add(normalcdf(para1, para2, para3, para4, type) + "");
                return outputQueue;
            }

            if (next.equals("NormalPdf(")) {
                String nextItem = arr[i+1];
                String[] input = nextItem.split(",");
                LinkedList<String> lst = new LinkedList<String> (Arrays.asList(input));
                double para1 = Double.parseDouble(lst.get(0));
                double para2 = Double.parseDouble(lst.get(1));
                double para3 = Double.parseDouble(lst.get(2));
                outputQueue.add(normalpdf(para1, para2, para3) + "");
                return outputQueue;
            }

            if (next.equals("InverseNormal(")) {
                String nextItem = arr[i+1];
                String[] input = nextItem.split(",");
                LinkedList<String> lst = new LinkedList<String> (Arrays.asList(input));
                double para1 = Double.parseDouble(lst.get(0));
                double para2 = Double.parseDouble(lst.get(1));
                double para3 = Double.parseDouble(lst.get(2));
                int para4 = Integer.parseInt(lst.get(3));
                outputQueue.add(invNorm(para1, para2, para3, para4) + "");
                return outputQueue;
            }

            if (next.equals("OnePropZTest(")) {
                String nextItem = arr[i+1];
                String[] input = nextItem.split(",");
                LinkedList<String> lst = new LinkedList<String> (Arrays.asList(input));
                double para1 = Double.parseDouble(lst.get(0));
                double para2 = Double.parseDouble(lst.get(1));
                double para3 = Double.parseDouble(lst.get(2));
                int para4 = Integer.parseInt(lst.get(3));
                outputQueue.add(one_prop_z_test(para1, para2, para3, para4) + "");
                return outputQueue;
            }

            if (next.equals("ZTest(")) {
                String nextItem = arr[i+1];
                String[] input = nextItem.split(",");
                LinkedList<String> lst = new LinkedList<String> (Arrays.asList(input));
                double para1 = Double.parseDouble(lst.get(0));
                double para2 = Double.parseDouble(lst.get(1));
                double para3 = Double.parseDouble(lst.get(2));
                double para4 = Double.parseDouble(lst.get(3));
                int para5 = Integer.parseInt(lst.get(4));
                outputQueue.add(z_test(para1, para2, para3, para4, para5) + "");
                return outputQueue;
            }

            // Numeric steps (Case 1)
            if (isNumeric(next)) {
                outputQueue.add(next);
            }

            // Operator steps (Case 2)
            if (isOperator(next)) {
                // Step 1: pop op from stack to queue if not null && one_on_stack >= the current's precedence
                while (operatorStack.peek() != null && getPrecedence(operatorStack.peek()) >= getPrecedence(next)) {
                    outputQueue.add(operatorStack.pop());
                }
                // Step 2: push to the op stack
                operatorStack.push(next);
            }

            // Case 3: left paren
            if (next.equals("(")) {
                operatorStack.push("(");
            }

            // Case 4: right paren
            else if (next.equals(")")) {
                while (operatorStack.peek() != null && operatorStack.peek() != "(") {
                    outputQueue.add(operatorStack.pop());
                }
                if (operatorStack.peek().equals("(")) {
                    operatorStack.pop();
                }
            }
            

        }

        // See if there is any ops left on the stack after the for-loop
        while (!operatorStack.isEmpty()) {
            outputQueue.add(operatorStack.pop());
        }

        return outputQueue;
    }



    // Probability methods start here
    public static double nCk(int n, int k) {
        if (n < 0 || k < 0) { System.out.println("Negative value passed into nCk"); return -1; }
        if (k > n) { System.out.println("K greater than n in nCk: " + k + " > " + n); return -1; }
        if (k == 0) return 1;
        return ((n-(k-1)) / (double) (k)) * nCk(n, k-1);
    }

    public static double geompdf(double p, int x) {
        if (p < 0 || p > 1) { System.out.println("Probability out of range for geompdf: " + p); return -1; }
        if (x < 1) return 0;
        return p * Math.pow(1-p, x-1);
    }

    public static double geomcdf(double p, int x) {
        if (p < 0 || p > 1) { System.out.println("Probability out of range for geomcdf: " + p); return -1; }
        if (x < 1) return 0;
        return geompdf(p, x) + geomcdf(p, x-1);
    }

    public static double binompdf(double p, int x, int n) {
        if (p < 0 || p > 1) { System.out.println("Probability out of range for binompdf: " + p); return -1; }
        if (x > n) { System.out.println("X greater than n in binompdf: " + x + " > " + n); return -1; }
        if (x < 0 || n < 1) return 0;
        return Math.pow(p, x) * Math.pow(1-p, n-x) * nCk(n, x);
    }

    public static double binomcdf(double p, int x, int n) {
        if (p < 0 || p > 1) { System.out.println("Probability out of range for binomcdf: " + p); return -1; }
        if (x > n) { System.out.println("X greater than n in binomcdf: " + x + " > " + n); return -1; }
        if (x < 0 || n < 1) return 0;
        return binompdf(p, x, n) + binomcdf(p, x-1, n);
    }
    // Probability methods end here



    // Distribution methods start here
    public static BigDecimal factorial(BigDecimal i) {
        if (i.compareTo(BigDecimal.valueOf(2)) < 0) return BigDecimal.ONE;
        return i.multiply(factorial(i.subtract(BigDecimal.ONE)));
    }

    private static String normalcdf(double mean, double sd, double lower, double upper, int type) {

        double lowerzval = (lower - mean)/sd;
        double upperzval = (upper - mean)/sd;

        if (type == 1) {
            return TaylorCdf(lowerzval, upperzval) + "";
        } else {
            return RiemannCdf(lowerzval, upperzval) + "";
        }
    }

    public static BigDecimal TaylorCdf(double lowerz, double upperz) { return TaylorCdf(upperz).subtract(TaylorCdf(lowerz)); }

    public static BigDecimal TaylorCdf(double zval) {
        if (Math.abs(zval) > 7.2) zval = Math.signum(zval) * 7.2;
        final MathContext c = new MathContext(16, RoundingMode.DOWN);
        final BigDecimal errorBound = BigDecimal.valueOf(0.00000001);
        final double root2pi = Math.sqrt(2*Math.PI);

        BigDecimal delta = new BigDecimal(1);
        BigDecimal sum = new BigDecimal(0);

        //Taylor series approximation
        for (short n = 0; delta.abs().compareTo(errorBound) > 0; n++) {
            delta = BigDecimal.valueOf(-0.5).pow(n).multiply(BigDecimal.valueOf(zval).pow(2*n + 1))
                    .divide(factorial(BigDecimal.valueOf(n)).multiply(BigDecimal.valueOf(root2pi * (2*n + 1))), c);
            sum = sum.add(delta);
        } return sum;
    }

    public static double RiemannCdf(double lowerz, double upperz) { return RiemannCdf(upperz) - RiemannCdf(lowerz); }

    public static double RiemannCdf(double zval) {
        double rightzval = Math.abs(zval);
        boolean right = zval>0;
        double step = 0.00001;
        double sum = 0;
        for (double n = 0; n<rightzval; n+=step) {
            sum += step * normalpdf(n, 0, 1);
        } return sum * (right ? 1 : -1);
    }

    public static double normalpdf(double xval, double mean, double sd) {
        return Math.pow(Math.E, -0.5*Math.pow((xval-mean)/sd, 2))/(sd*Math.sqrt(2*Math.PI));
    }

    private static double invNorm(double mean, double sd, double area, int direction) {

        double adjustedArea = area - 0.5;

        if (direction == -1) {
            return Math.signum(adjustedArea) * invNorm(Math.abs(adjustedArea), mean, sd);
        }
        else if (direction == 1) {
            return (-1) * Math.signum(adjustedArea) * invNorm(Math.abs(adjustedArea), mean, sd);
        }
        else {
            return invNorm(area/2, mean, sd);
        }

    }

    public static double invNorm(double area, double mean, double sd) {
        double step = 0.00001 * sd; double xval;
        for (xval = 0; area>0; xval+=step) {
            area -= step * normalpdf(xval, mean, sd);
        } return xval + mean;
    }

    // Distribution methods end here



    // Test methods start here

    public static double one_prop_z_test(double p0, double x, double n, int altHypo) {
        double p_hat = x / n;
        double sd = Math.sqrt(p0 * (1 - p0) / n);
        double z = (p_hat - p0) / sd;

        double p_value = TaylorCdf(Math.abs(z), 100).doubleValue();
        if (altHypo == 0) {
            p_value *= 2;
        }

        return p_value;
    }

    public static double z_test(double mu0, double sigma, double x, double n, int altHypo) {
        double sd = sigma / Math.sqrt(n);
        double z = (x - mu0) / sd;

        double p_value = TaylorCdf(Math.abs(z), 100).doubleValue();
        if (altHypo == 0) p_value *= 2;

        return p_value;
    }

    // Test methods end here



    // Takes in a queue of tokens in postfix format, and evaluates the math expression
    // Returns the integer answer
    // To be completed in **Part 2**
    public static double evaluate(Queue<String> inputQueue)
    {
        Deque<String> evalStack = new LinkedList<String>();
        while (!inputQueue.isEmpty()) {
            String next = inputQueue.poll();

            // Case 1: Number
            if (isNumeric(next)) {
                evalStack.push(next);
            }

            // Case 2: Operator
            if (isOperator(next)) {
                double op1 = Double.parseDouble(evalStack.pop());
                double op2 = Double.parseDouble(evalStack.pop());

                // Evaluate
                if (next.equals("+")) {
                    evalStack.push(String.valueOf(op1 + op2));
                }
                else if (next.equals("-")) {
                    evalStack.push(String.valueOf(op2 - op1));
                }
                else if (next.equals("*")) {
                    evalStack.push(String.valueOf(op1 * op2));
                }
                else if (next.equals("/")) {
                    evalStack.push(String.valueOf(op2 / op1));
                }
                else if (next.equals("^")) {
                    evalStack.push(String.valueOf(Math.round(Math.pow(op2, op1))));
                }

            }

        }

        double result = Double.parseDouble(evalStack.pop());

        return result;
    }


    public static void main(String[] args) {}


}