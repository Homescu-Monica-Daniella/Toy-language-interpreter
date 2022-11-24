package View;

import Controller.Controller;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.RelationalExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState.Dictionary;
import Model.ProgramState.List;
import Model.ProgramState.ProgramState;
import Model.ProgramState.Stack;
import Model.Statements.*;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Types.StringType;
import Model.Values.BooleanValue;
import Model.Values.IntegerValue;
import Model.Values.StringValue;
import Repository.InterfaceRepository;
import Repository.Repository;

public class Interpreter {

    public static void main(String[] args) {

        InterfaceStatement ex1 = new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))), new PrintStatement(new
                        VariableExpression("v"))));
        ProgramState prg1 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex1, new Dictionary<>());
        InterfaceRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        InterfaceStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntegerValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
                                        IntegerValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState prg2 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex2, new Dictionary<>());
        InterfaceRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);

        InterfaceStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(new ConditionalStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        ProgramState prg3 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex3, new Dictionary<>());
        InterfaceRepository repo3 = new Repository(prg3, "log3.txt");
        Controller ctr3 = new Controller(repo3);

        InterfaceStatement ex4 = new CompoundStatement(new VariableDeclarationStatement("varf", new StringType()),
                new CompoundStatement(new AssignmentStatement("varf", new ValueExpression(new StringValue("test.in"))),
                        new CompoundStatement(new OpenReadFileStatement(new VariableExpression("varf")),
                                new CompoundStatement(new VariableDeclarationStatement("varc", new IntegerType()),
                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                        new CompoundStatement(new ReadFileStatement(new VariableExpression("varf"), "varc"),
                                                                new CompoundStatement(new PrintStatement(new VariableExpression("varc")),
                                                                        new CloseReadFileStatement(new VariableExpression("varf"))))))))));
        ProgramState prg4 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex4, new Dictionary<>());
        InterfaceRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);

        InterfaceStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("a", new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntegerValue(2))),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntegerValue(1))),
                                        new ConditionalStatement(new RelationalExpression("<=", new VariableExpression("a"),
                                                new VariableExpression("b")),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));
        ProgramState prg5 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex5, new Dictionary<>());
        InterfaceRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.show();

    }

}
