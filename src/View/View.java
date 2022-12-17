package View;

import Repository.InterfaceRepository;
import Repository.Repository;
import Controller.Controller;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState.*;
import Model.Statements.*;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.InterfaceValue;
import Exceptions.ADTException;
import Exceptions.ExpressionEvaluationException;
import Exceptions.StatementExecutionException;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class View {

    private void printMenu() {
        System.out.println("----------------------");
        System.out.println("\n0 - exit");
        System.out.println("1 - run first program");
        System.out.println("2 - run second program");
        System.out.println("3 - run third program\n");
    }

    private InterfaceStatement firstProgram() {
        InterfaceStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))), new PrintStatement(new
                        VariableExpression("v"))));
        return ex1;
    }

    private InterfaceStatement secondProgram() {
        InterfaceStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntegerValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
                                        IntegerValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        return ex2;
    }

    private InterfaceStatement thirdProgram() {
        InterfaceStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(new ConditionalStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        return ex3;
    }

    private void runProgram(InterfaceStatement stmt, String logFilePath) throws ADTException, ExpressionEvaluationException, StatementExecutionException, IOException, InterruptedException {
        InterfaceStack<InterfaceStatement> exeStack = new Stack<>();
        InterfaceDictionary<String, InterfaceValue> symTable = new Dictionary<>();
        InterfaceList<InterfaceValue> out = new MyList<>();
        InterfaceDictionary<String, BufferedReader> fileTable = new Dictionary<>();
        InterfaceHeap heap = new Heap();
        ProgramState prgState = new ProgramState(exeStack, symTable, out, stmt, fileTable, heap);
        InterfaceRepository repo = new Repository(prgState, logFilePath);
        Controller ctrl = new Controller(repo);
        System.out.println("display flag: ");
        Scanner readFlag = new Scanner(System.in);
        boolean flg = readFlag.nextBoolean();
        System.out.println("\n");
        ctrl.setFlg(flg);
        ctrl.allStep();
        String res = prgState.getOut().toString();
        System.out.println("\nFinal result: " + res.substring(1, res.length() - 1) + "\n");
    }

    public void startMenu() {
        while (true) {
            printMenu();
            System.out.println("option: ");
            Scanner readOption = new Scanner(System.in);
            int op = readOption.nextInt();
            try {
                if (op == 0)
                    break;
                else {
                    System.out.println("log file path: ");
                    Scanner readPath = new Scanner(System.in);
                    String path = readPath.nextLine();
                    if (op == 1)
                        runProgram(firstProgram(), path);
                    else if (op == 2)
                        runProgram(secondProgram(), path);
                    else if (op == 3)
                        runProgram(thirdProgram(), path);
                }
            }
            catch (Exception exception) {
                System.out.println(exception.getMessage());
            }
        }
    }

}