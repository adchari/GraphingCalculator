/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphingcalculator;
import java.awt.event.KeyEvent;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.TextFlow;
/**
 *
 * @author ADChari && MLGRavoSan
 */
public class GUIController implements Initializable {
    //ArrayList<Double> memoryList = new ArrayList<>(); ?
    Calculator calc;
    //Canvas graphPane;
    //GraphicsContext graphDraw;
    @FXML
    TextArea calculatorPane;
    @FXML
    TextField operationPane;
    private int lastIndex = 0;
    private String lastOpResult;
    private double a, b, c, d, f, g, h, i, j, k, l, m, n, o, p, q, r, s, t, u, v, w;
    ArrayList<Double> varList = new ArrayList<>();
    public GUIController() {
        calculatorPane = new TextArea();
        operationPane = new TextField();
        calc = new Calculator();
        operationPane.requestFocus();
        recallMemory("src/resources/variables.txt");
        setVariables();
    }
    private void addVariables() {
        varList.clear();
        varList.add(a); varList.add(b); varList.add(c); varList.add(d);
        varList.add(f); varList.add(g); varList.add(h); varList.add(i);
        varList.add(j); varList.add(k); varList.add(l); varList.add(m);
        varList.add(n); varList.add(o); varList.add(p); varList.add(q);
        varList.add(r); varList.add(s); varList.add(t); varList.add(u);
        varList.add(v); varList.add(w);
    }
    private void setVariables() {
        a = varList.get(0); b = varList.get(1); c = varList.get(2); d = varList.get(3);
        f = varList.get(4); g = varList.get(5); h = varList.get(6); i = varList.get(7);
        j = varList.get(8); k = varList.get(9); l = varList.get(10); m = varList.get(11);
        n = varList.get(12); o = varList.get(13); p = varList.get(14); q = varList.get(15);
        r = varList.get(16); s = varList.get(17); t = varList.get(18); u = varList.get(19);
        v = varList.get(20); w = varList.get(21);
    }
    @FXML
    private void addPi() {
        addText("π");
    }
    @FXML
    private void adde() {
        addText("e");
    }
    @FXML
    private void addMinus() {
        addText("~");
    }
    @FXML
    private void addPlus() {
        addText("+");
    }
    @FXML
    private void addStar() {
        addText("*");
    }
    @FXML
    private void addSlash() {
        addText("/"); 
    }
    @FXML
    private void addExponent() {
        addText("^");
    }
    @FXML
    private void addRoot() {
        addText("√()");
    }
    @FXML
    private void addDecimal() {
        addText(".");
    }
    @FXML
    private void addLN() {
        addText("ln");
    }
    private void addText(String operand) {
        operationPane.appendText(operand);
        operationPane.requestFocus();
    }
    @FXML
    private void calculateInput() { //when you hit enter
        String rawData = operationPane.getText();
        //int j = 0;
        //for (int i = 0; i < rawData.length(); i++) {
            //if () {
               // 
            //}
        //}
        //String rawData = "(5+(5*2-1))";
        //String input = rawData.substring(lastIndex, rawData.length());
        calculatorPane.appendText(rawData);
        lastOpResult = calc.simplifyInput(rawData);
        System.out.println(lastOpResult);
        calculatorPane.appendText("\n  " + lastOpResult + "\n\n");
        operationPane.setText("");
        operationPane.requestFocus();
    }
    @FXML
    private void exitApp() {
        storeMemory("src/resources/variables.txt");
        System.exit(0);
    }
    @FXML
    private void displayInfo() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("JavaFX Graphing Calculator");
        alert.setContentText("This calculator was made by Ravi Patel and Adithya Chari using JavaFX in Netbeans and SceneBuilder.");
        alert.showAndWait();
    }
    @FXML
    private void displayHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Using this Calculator");
        alert.setHeaderText("How to Input Operations");
        alert.setContentText("IMPORTANT: Use a tilda (~) for subtraction, and a dash (-) for negative signs. "
                           + "\nThe Store function will automatically use the last calculation's answer."
                           + "\nMultiplication signs may NOT be omitted between adjacent factors."
                           + "\nOTHERWISE, treat like a normal calculator.");
        alert.showAndWait();
    }
    @FXML
    private void addOne() {
        addText("1");
    }
    @FXML
    private void addTwo() {
        addText("2");
    }
    @FXML
    private void addThree() {
        addText("3");
    }
    @FXML
    private void addFour() {
        addText("4");
    }
    @FXML
    private void addFive() {
        addText("5");
    }
    @FXML
    private void addSix() {
        addText("6");
    }
    @FXML
    private void addSeven() {
        addText("7");
    }
    @FXML
    private void addEight() {
        addText("8");
    }
    @FXML
    private void addNine() {
        addText("9");
    }
    @FXML
    private void addZero() {
        addText("0");
    }
    @FXML
    private void addRightP() {
        addText(")");
    }
    @FXML
    private void addLeftP() {
        addText("(");
    }
    @FXML
    private void addNPR() {
        addText("nPr");
    }
    @FXML
    private void addNCR() {
        addText("nCr");
    }
    @FXML
    private void addMemA() {
        try {
            a = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into a.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemB() {
        try {
            b = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into b.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemC() {
        try {
            c = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into c.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemD() {
        try {
            d = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into d.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemF() {
        try {
            f = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into f.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemG() {
        try {
            g = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into g.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemH() {
        try {
            h = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into h.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemI() {
        try {
            i = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into i.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemJ() {
        try {
            j = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into j.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemK() {
        try {
            k = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into k.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemL() {
        try {
            l = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into l.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemM() {
        try {
            m = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into m.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemN() {
        try {
            n = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into n.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemO() {
        try {
            o = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into o.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemP() {
        try {
            p = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into p.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemQ() {
        try {
            q = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into q.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemR() {
        try {
            r = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into r.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemS() {
        try {
            s = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into s.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemT() {
        try {
            t = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into t.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemU() {
        try {
            u = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into u.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void addMemV() {
        try {
            v = Double.parseDouble(lastOpResult);
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
            calculatorPane.appendText("\n" + lastOpResult + " stored into v.");
        }
    }
    @FXML
    private void addMemW() {
        try {
            w = Double.parseDouble(lastOpResult);
            calculatorPane.appendText("\n" + lastOpResult + " stored into w.");
        }
        catch (NullPointerException error) {
            calculatorPane.appendText("\nERROR: NO INPUT TO STORE");
        }
    }
    @FXML
    private void recallA() {
        operationPane.appendText(Double.toString(a));
    }
    @FXML
    private void recallB() {
        operationPane.appendText(Double.toString(b));
    }
    @FXML
    private void recallC() {
        operationPane.appendText(Double.toString(c));
    }
    @FXML
    private void recallD() {
        operationPane.appendText(Double.toString(d));
    }
    @FXML
    private void recallF() {
        operationPane.appendText(Double.toString(f));
    }
    @FXML
    private void recallG() {
        operationPane.appendText(Double.toString(g));
    }
    @FXML
    private void recallH() {
        operationPane.appendText(Double.toString(h));
    }
    @FXML
    private void recallI() {
        operationPane.appendText(Double.toString(i));
    }
    @FXML
    private void recallJ() {
        operationPane.appendText(Double.toString(j));
    }
    @FXML
    private void recallK() {
        operationPane.appendText(Double.toString(k));
    }
    @FXML
    private void recallL() {
        operationPane.appendText(Double.toString(l));
    }
    @FXML
    private void recallM() {
        operationPane.appendText(Double.toString(m));
    }
    @FXML
    private void recallN() {
        operationPane.appendText(Double.toString(n));
    }
    @FXML
    private void recallO() {
        operationPane.appendText(Double.toString(o));
    }
    @FXML
    private void recallP() {
        operationPane.appendText(Double.toString(p));
    }
    @FXML
    private void recallQ() {
        operationPane.appendText(Double.toString(q));
    }
    @FXML
    private void recallR() {
        operationPane.appendText(Double.toString(r));
    }
    @FXML
    private void recallS() {
        operationPane.appendText(Double.toString(s));
    }
    @FXML
    private void recallT() {
        operationPane.appendText(Double.toString(t));
    }
    @FXML
    private void recallU() {
        operationPane.appendText(Double.toString(u));
    }
    @FXML
    private void recallV() {
        operationPane.appendText(Double.toString(v));
    }
    @FXML
    private void recallW() {
        operationPane.appendText(Double.toString(w));
    }
    @FXML
    private void addSin() {
        addText("isin()");
    }
    @FXML
    private void addCos() {
        addText("icos()");
    }
    @FXML
    private void addTan() {
        addText("itan()");
    }
    @FXML
    private void addSec() {
        addText("isec()");
    }
    @FXML
    private void addCsc() {
        addText("icsc()");
    }
    @FXML
    private void addCot() {
        addText("icot()");
    }
    @FXML
    private void addInvSin() {
        addText("isin()");
    }
    @FXML
    private void addInvCos() {
        addText("icos()");
    }
    @FXML
    private void addInvTan() {
        addText("itan()");
    }
    @FXML
    private void addInvSec() {
        addText("isec()");
    }
    @FXML
    private void addInvCsc() {
        addText("icsc()");
    }
    @FXML
    private void addInvCot() {
        addText("icot()");
    }
    private void recallMemory(String filePath) {
        int i = 0;
        try {
            FileReader reader = new FileReader(filePath);
            Scanner in = new Scanner(reader);
            while (in.hasNextLine() && i < 22) {
                varList.add(Double.parseDouble(in.nextLine()));
                i++;
            }
            System.out.println(varList);
        }
        catch (FileNotFoundException error) {
            System.out.println("Error: File " + filePath + " not found");
        }
        catch (NumberFormatException error) {
            System.out.println("Error: Line " + i + 1 + " was not a valid number.");
        }
    }
    private void storeMemory(String filePath) {
        addVariables();
        try {
            PrintWriter out = new PrintWriter(filePath);
            for (int i = 0; i < varList.size(); i++) {
                out.println(varList.get(i));
            }
            out.close();
        }
        catch (FileNotFoundException error) {
            System.out.println("Error: File " + filePath + " not found");
        }
        catch (NumberFormatException error) {
            System.out.println("Error: Line " + i + 1 + " was not a valid number.");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
}