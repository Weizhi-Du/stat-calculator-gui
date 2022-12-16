import javax.swing.*;
import java.awt.event.*;

// You only need to edit the designated parts of the actionPerformed() method
// You should leave the rest of the code as is

public class GUI implements ActionListener {
    JFrame frame;
    JPanel panel1, panel2, panel3, panel4, panel5, panelFn, panelFn2; //, panelKey;
    JButton button1, button2, button3, button4, button5,
            button6, button7, button8, button9, button0,
            buttonDivide, buttonTimes, buttonMinus, buttonPlus,
            buttonClear, buttonEquals, buttonLeft, buttonRight,
            buttonExponent, buttonDot, buttonComma, buttonProb,
            buttonCdf, buttonPdf, buttonInv, buttonPropZ, buttonZTest;
    JTextField field1;
    JTextArea area1;
//
//    String firstRow[] = {"Q","W","E","R","T","Y","U","I","O","P","_<"};
//    String secondRow[] = {"A","S","D","F","G","H","J","K","L"};
//    String thirdRow[] = {"Z","X","C","V","B","N","M",",","."};
//
//    JButton first[];
//    JButton second[];
//    JButton third[];

    public void actionPerformed(ActionEvent ae) {
        // buttonName will be equal to the String that labels
        // whatever button was pressed
        String buttonName = ae.getActionCommand();

        // Number buttons
        // This is just a shorthand for:
        // if (buttonName.equals("1") || buttonName.equals("2") || ...
        if ("1234567890".contains(buttonName)) {
            field1.setText(field1.getText() + buttonName);

        }
        // Clear button
        else if (buttonName.equals("AC")) {
            field1.setText("");
        }
        // Left parens
        else if (buttonName.equals("(")) {
            field1.setText(field1.getText() + "( ");
        }
        // Right parens
        else if (buttonName.equals(")")) {
            field1.setText(field1.getText() + " )");
        }
        // Plus button
        else if (buttonName.equals("+")) {
            field1.setText(field1.getText() + " + ");
        }
        // **************************************
        // Add code here for the other operations: -, *, /, ^
        // **************************************
        // else if (buttonName.equals("-")) {
        else if (buttonName.equals("-")) {
            field1.setText(field1.getText() + " - ");
        }
        else if (buttonName.equals("*")) {
            field1.setText(field1.getText() + " * ");
        }
        else if (buttonName.equals("/")) {
            field1.setText(field1.getText() + " / ");
        }
        else if (buttonName.equals("^")) {
            field1.setText(field1.getText() + " ^ ");
        }
        else if (buttonName.equals(".")) {
            field1.setText(field1.getText() + ".");
        }

        // **************************************
        // Add one line of code here to complete the Equals button
        // **************************************
        else if (buttonName.equals("=")) {
            // Expression will be equal to the current expression
            // that the user has inputted
            String expression = field1.getText();

            // Replace the 0 below with the code you'd use to calculate the answer
            // You will need to do ShuntingYard.methodName() to access a method from
            // your ShuntingYard.java file
            double answer = 0.0;
            answer = Calculator.evaluate(Calculator.convert(expression));
            // ** You only need to edit the line above **

            field1.setText("" + answer);
        }
        else if (buttonName.equals("Prob")) {
            field1.setText("Probability( ");
            area1.setText(
                    " Format: Probability( type,p,x,n )  \n   \n   " +
                    "* type * geompdf:1 geomcdf:2 binompdf:3 binomcdf:4  \n   " +
                    "* p * probability of success; double  \n   " +
                    "* x * number of the first success(geom)  \n   " +
                    "        number of successes(binom); Integer  \n   " +
                    "* n * number of trials (binom ONLY)"
            );
        }
        else if (buttonName.equals("Cdf")) {
            field1.setText("NormalCdf( ");
            area1.setText(
                    " Format: NormalCdf( mean,sd,lower,upper,type )  \n   \n   " +
                            "* mean * mean value of the distribution, double  \n   " +
                            "* sd * SD value of the distribution; double  \n   " +
                            "* lower * lower bound  \n   " +
                            "* upper * upper bound  \n   " +
                            "* type * Taylor series:1 Riemann Sum:2 "
            );
        }
        else if (buttonName.equals("Pdf")) {
            field1.setText("NormalPdf( ");
            area1.setText(
                    " Format: NormalPdf( x,mean,sd )  \n   \n   " +
                            "* x * the value of x, double  \n   " +
                            "* mean * mean value of the distribution, double  \n   " +
                            "* sd * SD value of the distribution; double  \n   "
            );
        }
        else if (buttonName.equals("Inv")) {
            field1.setText("InverseNormal( ");
            area1.setText(
                    " Format: InverseNormal( x,mean,area,dir )  \n   \n   " +
                            "* mean * mean value of the distribution, double  \n   " +
                            "* sd * SD value of the distribution; double  \n   " +
                            "* area * area of the region in decimals; double  \n   " +
                            "* dir * left:1 center:0 right:1; int "
            );
        }
        else if (buttonName.equals("1-Prop")) {
            field1.setText("OnePropZTest( ");
            area1.setText(
                    " Format: OnePropZTest( p0,x,n,alt )  \n   \n   " +
                            "* p0 * proportion parameter, double  \n   " +
                            "* x * number of successes, int  \n   " +
                            "* n * number of trials, int  \n   " +
                            "* alt * p<p0:-1 p>p0:1 p≠p0:0, int "
            );
        }
        else if (buttonName.equals("Z-Test")) {
            field1.setText("ZTest( ");
            area1.setText(
                    " Format: ZTest( µ0,sd,x,n,alt )  \n   \n   " +
                            "* µ0 * parameter mean, double  \n   " +
                            "* sd * parameter sd(sigma), double  \n   " +
                            "* x * number of successes, int  \n   " +
                            "* n * number of trials, int  \n   " +
                            "* alt * p<p0:-1 p>p0:1 p≠p0:0, int "
            );
        }
        else if (buttonName.equals(",")) {
            field1.setText(field1.getText() + ",");
        }
    }

    public GUI() {
        // 1. Create the frame (the window that will pop up)
        frame = new JFrame("Weizhi Instruments Calculator v0.1 Alpha");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        // 2. Choose what happens when you click the exit button
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 3. Create components and put them in the frame

        area1 = new JTextArea(8, 30);
        area1.setAlignmentX(SwingConstants.RIGHT);
        area1.setText("  ~ Welcome to the Calculator ~ \n * \n * \n * \n  Instructions will appear here");
        field1 = new JTextField(24);
        field1.setHorizontalAlignment(SwingConstants.RIGHT);

        panel1 = new JPanel();
        button7 = new JButton("7");
        panel1.add(button7);
        button8 = new JButton("8");
        panel1.add(button8);
        button9 = new JButton("9");
        panel1.add(button9);
        buttonDivide = new JButton("/");
        panel1.add(buttonDivide);

        panel2 = new JPanel();
        button4 = new JButton("4");
        panel2.add(button4);
        button5 = new JButton("5");
        panel2.add(button5);
        button6 = new JButton("6");
        panel2.add(button6);
        buttonTimes = new JButton("*");
        panel2.add(buttonTimes);

        panel3 = new JPanel();
        button1 = new JButton("1");
        panel3.add(button1);
        button2 = new JButton("2");
        panel3.add(button2);
        button3 = new JButton("3");
        panel3.add(button3);
        buttonMinus = new JButton("-");
        panel3.add(buttonMinus);

        panel4 = new JPanel();
        button0 = new JButton("0");
        panel4.add(button0);
        buttonClear = new JButton("AC");
        panel4.add(buttonClear);
        buttonEquals = new JButton("=");
        panel4.add(buttonEquals);
        buttonPlus = new JButton("+");
        panel4.add(buttonPlus);

        panel5 = new JPanel();
        buttonLeft = new JButton("(");
        panel5.add(buttonLeft);
        buttonRight = new JButton(")");
        panel5.add(buttonRight);
        buttonExponent = new JButton("^");
        panel5.add(buttonExponent);
        buttonDot = new JButton(".");
        panel5.add(buttonDot);

        panelFn = new JPanel();
        buttonCdf = new JButton("Cdf");
        panelFn.add(buttonCdf);
        buttonPdf = new JButton("Pdf");
        panelFn.add(buttonPdf);
        buttonInv = new JButton("Inv");
        panelFn.add(buttonInv);
        buttonComma = new JButton(",");
        panelFn.add(buttonComma);

        panelFn2 = new JPanel();
        buttonProb = new JButton("Prob");
        panelFn2.add(buttonProb);
        buttonPropZ = new JButton("1-Prop");
        panelFn2.add(buttonPropZ);
        buttonZTest = new JButton("Z-Test");
        panelFn2.add(buttonZTest);

//        panelKey = new JPanel();
//        JPanel panelKey1 = new JPanel();
//        JPanel panelKey2 = new JPanel();
//        JPanel panelKey3 = new JPanel();
//
//        panelKey.add(panelKey1, BorderLayout.NORTH);
//        panelKey.add(panelKey2, BorderLayout.CENTER);
//        panelKey.add(panelKey3, BorderLayout.SOUTH);
//
//        // First row
//        first = new JButton[firstRow.length];
//        JPanel pkey1 = new JPanel(new GridLayout(1, firstRow.length));
//        for (int i = 0; i < firstRow.length; i++)
//        {
//            JButton bt1 = new JButton(firstRow[i]);
//            first[i] = bt1;
//            pkey1.add(first[i]);
//            bt1.addActionListener(this);
//        }
//        panelKey1.add(pkey1);
//
//        // Second row
//        second = new JButton[secondRow.length];
//        JPanel pkey2 = new JPanel(new GridLayout(1, secondRow.length));
//        for (int i = 0; i < secondRow.length; i++)
//        {
//            JButton bt2 = new JButton(secondRow[i]);
//            second[i] = bt2;
//            pkey2.add(second[i]);
//            bt2.addActionListener(this);
//        }
//        panelKey2.add(pkey2);
//
//        third = new JButton[thirdRow.length];
//        //get the panel for the  row
//        JPanel pkey3 = new JPanel(new GridLayout(1, thirdRow.length));
//        for(int i = 0; i < thirdRow.length; i++)
//        {
//            JButton bt3 = new JButton(thirdRow[i]);
//            third[i] = bt3;
//            pkey3.add(third[i]);
//            bt3.addActionListener(this);
//        }
//        panelKey3.add(pkey3);


        // Add implemented actionListener method to each button
        button1.addActionListener(this);
        button2.addActionListener(this);
        button3.addActionListener(this);
        button4.addActionListener(this);
        button5.addActionListener(this);
        button6.addActionListener(this);
        button7.addActionListener(this);
        button8.addActionListener(this);
        button9.addActionListener(this);
        button0.addActionListener(this);

        buttonPlus.addActionListener(this);
        buttonMinus.addActionListener(this);
        buttonTimes.addActionListener(this);
        buttonDivide.addActionListener(this);
        buttonEquals.addActionListener(this);
        buttonLeft.addActionListener(this);
        buttonRight.addActionListener(this);
        buttonExponent.addActionListener(this);
        buttonClear.addActionListener(this);
        buttonDot.addActionListener(this);

        buttonProb.addActionListener(this);
        buttonCdf.addActionListener(this);
        buttonPdf.addActionListener(this);
        buttonInv.addActionListener(this);
        buttonComma.addActionListener(this);
        buttonPropZ.addActionListener(this);
        buttonZTest.addActionListener(this);



        frame.add(area1);
        frame.add(field1);
        frame.add(panel1);
        frame.add(panel2);
        frame.add(panel3);
        frame.add(panel4);
        frame.add(panel5);
        frame.add(panelFn);
        frame.add(panelFn2);
//        frame.add(pkey1);
//        frame.add(pkey2);
//        frame.add(pkey3);

        // 4. Size the frame
        frame.pack();

        // 5. Show the frame
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
    }
}
