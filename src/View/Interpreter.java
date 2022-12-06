package View;

import Controller.Controller;
import Model.Expressions.*;
import Model.ProgramState.*;
import Model.Statements.*;
import Model.Types.BooleanType;
import Model.Types.IntegerType;
import Model.Types.ReferenceType;
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
        ProgramState prg1 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex1, new Dictionary<>(), new Heap());
        InterfaceRepository repo1 = new Repository(prg1, "log1.txt");
        Controller ctr1 = new Controller(repo1);

        InterfaceStatement ex2 = new CompoundStatement(new VariableDeclarationStatement("a",new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("b",new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ArithmeticExpression('+',new ValueExpression(new IntegerValue(2)),new
                                ArithmeticExpression('*',new ValueExpression(new IntegerValue(3)), new ValueExpression(new IntegerValue(5))))),
                                new CompoundStatement(new AssignmentStatement("b",new ArithmeticExpression('+',new VariableExpression("a"), new ValueExpression(new
                                        IntegerValue(1)))), new PrintStatement(new VariableExpression("b"))))));
        ProgramState prg2 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex2, new Dictionary<>(), new Heap());
        InterfaceRepository repo2 = new Repository(prg2, "log2.txt");
        Controller ctr2 = new Controller(repo2);

        InterfaceStatement ex3 = new CompoundStatement(new VariableDeclarationStatement("a", new BooleanType()),
                new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new BooleanValue(true))),
                                new CompoundStatement(new ConditionalStatement(new VariableExpression("a"),
                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(2))),
                                        new AssignmentStatement("v", new ValueExpression(new IntegerValue(3)))),
                                        new PrintStatement(new VariableExpression("v"))))));
        ProgramState prg3 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex3, new Dictionary<>(), new Heap());
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
        ProgramState prg4 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex4, new Dictionary<>(), new Heap());
        InterfaceRepository repo4 = new Repository(prg4, "log4.txt");
        Controller ctr4 = new Controller(repo4);

        InterfaceStatement ex5 = new CompoundStatement(new VariableDeclarationStatement("a", new IntegerType()),
                new CompoundStatement(new VariableDeclarationStatement("b", new IntegerType()),
                        new CompoundStatement(new AssignmentStatement("a", new ValueExpression(new IntegerValue(2))),
                                new CompoundStatement(new AssignmentStatement("b", new ValueExpression(new IntegerValue(1))),
                                        new ConditionalStatement(new RelationalExpression("<=", new VariableExpression("a"),
                                                new VariableExpression("b")),new PrintStatement(new VariableExpression("a")),
                                                new PrintStatement(new VariableExpression("b")))))));
        ProgramState prg5 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex5, new Dictionary<>(), new Heap());
        InterfaceRepository repo5 = new Repository(prg5, "log5.txt");
        Controller ctr5 = new Controller(repo5);

        InterfaceStatement ex6 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntegerType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntegerType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new VariableExpression("v")), new PrintStatement(new VariableExpression("a")))))));
        ProgramState prg6 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex6, new Dictionary<>(), new Heap());
        InterfaceRepository repo6 = new Repository(prg6, "log6.txt");
        Controller ctr6 = new Controller(repo6);

        InterfaceStatement ex7 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntegerType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntegerType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                                new PrintStatement(new ArithmeticExpression('+',new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))), new ValueExpression(new IntegerValue(5)))))))));
        ProgramState prg7 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex7, new Dictionary<>(), new Heap());
        InterfaceRepository repo7 = new Repository(prg7, "log7.txt");
        Controller ctr7 = new Controller(repo7);

        InterfaceStatement ex8 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntegerType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement( new PrintStatement(new HeapReadingExpression(new VariableExpression("v"))),
                                new CompoundStatement(new HeapWritingStatement("v", new ValueExpression(new IntegerValue(30))),
                                        new PrintStatement(new ArithmeticExpression('+', new HeapReadingExpression(new VariableExpression("v")), new ValueExpression(new IntegerValue(5))))))));
        ProgramState prg8 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex8, new Dictionary<>(), new Heap());
        InterfaceRepository repo8 = new Repository(prg8, "log8.txt");
        Controller ctr8 = new Controller(repo8);

        InterfaceStatement ex9 = new CompoundStatement(new VariableDeclarationStatement("v", new ReferenceType(new IntegerType())),
                new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(20))),
                        new CompoundStatement(new VariableDeclarationStatement("a", new ReferenceType(new ReferenceType(new IntegerType()))),
                                new CompoundStatement(new HeapAllocationStatement("a", new VariableExpression("v")),
                                        new CompoundStatement(new HeapAllocationStatement("v", new ValueExpression(new IntegerValue(30))),
                                                new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a")))))))));
        ProgramState prg9 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex9, new Dictionary<>(), new Heap());
        InterfaceRepository repo9 = new Repository(prg9, "log9.txt");
        Controller ctr9 = new Controller(repo9);

        InterfaceStatement ex10 = new CompoundStatement(new VariableDeclarationStatement("v", new IntegerType()),
                new CompoundStatement(new AssignmentStatement("v", new ValueExpression(new IntegerValue(4))),
                        new CompoundStatement(new WhileStatement(new RelationalExpression(">", new VariableExpression("v"), new ValueExpression(new IntegerValue(0))),
                                new CompoundStatement(new PrintStatement(new VariableExpression("v")), new AssignmentStatement("v",new ArithmeticExpression('-', new VariableExpression("v"), new ValueExpression(new IntegerValue(1)))))),
                                new PrintStatement(new VariableExpression("v")))));
        ProgramState prg10 = new ProgramState(new Stack<>(), new Dictionary<>(), new List<>(), ex10, new Dictionary<>(), new Heap());
        InterfaceRepository repo10 = new Repository(prg10, "log10.txt");
        Controller ctr10 = new Controller(repo10);

        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1", ex1.toString(), ctr1));
        menu.addCommand(new RunExample("2", ex2.toString(), ctr2));
        menu.addCommand(new RunExample("3", ex3.toString(), ctr3));
        menu.addCommand(new RunExample("4", ex4.toString(), ctr4));
        menu.addCommand(new RunExample("5", ex5.toString(), ctr5));
        menu.addCommand(new RunExample("6", ex6.toString(), ctr6));
        menu.addCommand(new RunExample("7", ex7.toString(), ctr7));
        menu.addCommand(new RunExample("8", ex8.toString(), ctr8));
        menu.addCommand(new RunExample("9", ex9.toString(), ctr9));
        menu.addCommand(new RunExample("10", ex10.toString(), ctr10));
        menu.show();

    }

}
