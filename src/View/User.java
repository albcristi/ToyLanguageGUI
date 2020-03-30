package View;
/*
import Collection.Dictionary.MyDictionary;
import Collection.List.MyList;
import Collection.Stack.MyStack;
import ControllerPack.Controller;
import Model.Exception.MyException.MyException;
import Model.Expressions.ArithmeticExpression;
import Model.Expressions.ValueExpression;
import Model.Expressions.VariableExpression;
import Model.ProgramState;
import Model.Statement.*;
import Model.Statement.FilePack.closeRFileStatement;
import Model.Statement.FilePack.openRFileStatement;
import Model.Statement.FilePack.readFileStatement;
import Model.Types.BoolType;
import Model.Types.IntType;
import Model.Types.StringType;
import Model.Values.BoolValue;
import Model.Values.IntValue;
import Model.Values.StringValue;
import RepositoryPack.Repository;
import RepositoryPack.RepositoryInterface;

import java.util.Scanner;

public class User {
    public User(){}

    ///todo: HARD CODE SOME EXAMPLES
    private StatementInterface ex1(){
        return new CompStatement(new VariableDeclStatement("v",new IntType()),
                new CompStatement(new AssignStatement("v",new ValueExpression(new IntValue(2))),
                        new PrintStatement(new VariableExpression("v"))));
    }

    private StatementInterface ex2() {
        return new CompStatement( new VariableDeclStatement("a",new IntType()),
                new CompStatement(new VariableDeclStatement("b",new IntType()),
                        new CompStatement(new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)),3),1)),
                                new CompStatement(new AssignStatement("b",new ArithmeticExpression(new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)),1)), new PrintStatement(new VariableExpression("b"))))));

    }

    private StatementInterface ex3(){
        return new CompStatement(new VariableDeclStatement("a",new BoolType()),
                new CompStatement(new VariableDeclStatement("v", new IntType()),
                        new CompStatement(new AssignStatement("a", new ValueExpression(new BoolValue(true))),
                                new CompStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
    }

    private StatementInterface ex4(){
        //example that wil declare a var
        //and will print it
        //int a; print(a);
        return  new CompStatement(new VariableDeclStatement("a",new IntType()),new PrintStatement(new VariableExpression("a")));

    }

    private StatementInterface ex5(){
        //example that will throw an exception
        return new CompStatement(new VariableDeclStatement("v",new IntType()),
                new CompStatement(new AssignStatement("v",new ValueExpression(new BoolValue())),
                        new PrintStatement(new VariableExpression("v"))));
    }

    private StatementInterface ex6(){
        return new CompStatement( new VariableDeclStatement("a",new IntType()),
                new CompStatement(new VariableDeclStatement("b",new BoolType()),
                        new CompStatement(new AssignStatement("a", new ArithmeticExpression(new ValueExpression(new IntValue(2)),new
                                ArithmeticExpression(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)),3),1)),
                                new CompStatement(new AssignStatement("b",new ArithmeticExpression(new VariableExpression("a"), new
                                        ValueExpression(new IntValue(1)),1)), new PrintStatement(new VariableExpression("b"))))));
    }

    private StatementInterface ex7(){
        return new CompStatement(new VariableDeclStatement("a",new IntType()),
                new CompStatement(new VariableDeclStatement("v", new IntType()),
                        new CompStatement(new AssignStatement("a", new ValueExpression(new IntValue(5))),
                                new CompStatement(new IfStatement(new VariableExpression("a"),new AssignStatement("v",new ValueExpression(new
                                        IntValue(2))), new AssignStatement("v", new ValueExpression(new IntValue(3)))), new PrintStatement(new
                                        VariableExpression("v"))))));
    }

    private StatementInterface ex8(){

        return new CompStatement(new VariableDeclStatement("a",new IntType()),new CompStatement(new NopStatement(),
                new CompStatement(new AssignStatement("a",new ArithmeticExpression(new ValueExpression(new IntValue(10)),new VariableExpression("a"),1)),
                        new PrintStatement(new VariableExpression("a")))));
    }

    private StatementInterface ex9(){

        return new CompStatement(new VariableDeclStatement("a",new IntType()),new CompStatement(new NopStatement(),new CompStatement(
                new VariableDeclStatement("b",new BoolType()), new CompStatement( new AssignStatement("b",new ValueExpression(new BoolValue(true))),
                new CompStatement(new IfStatement(new VariableExpression("b"),new CompStatement(new AssignStatement("a",new ValueExpression(new IntValue(10))),new AssignStatement("b",new ValueExpression(new BoolValue(false)))),
                        new CompStatement(new AssignStatement("a",new ValueExpression(new IntValue(-10))),new AssignStatement("b",new ValueExpression(new BoolValue(false))))),
                        new CompStatement(new PrintStatement(new VariableExpression("a")),new PrintStatement(new VariableExpression("b"))))))));
    }

    private StatementInterface ex10(){
        return new CompStatement(new VariableDeclStatement("a",new IntType()),new CompStatement(new PrintStatement(new VariableExpression("b")),new NopStatement()));
    }

    private StatementInterface ex11(){

        return new CompStatement(new VariableDeclStatement("s",new StringType()),
                new CompStatement(new AssignStatement("s",new ValueExpression(new StringValue("ana"))),new PrintStatement(new VariableExpression("s"))));
    }

    private StatementInterface ex12Files(){
        return new CompStatement(new VariableDeclStatement("varf",new StringType()),
                new CompStatement(new AssignStatement("varf",new ValueExpression(new StringValue("test.in"))),
                        new CompStatement(new VariableDeclStatement("varc",new IntType()),
                                new CompStatement(new openRFileStatement(new VariableExpression("varf")),
                                        new CompStatement(new readFileStatement(new VariableExpression("varf"),"varc"),
                                                new CompStatement( new PrintStatement(new VariableExpression("varc")),
                                                new CompStatement(new readFileStatement(new VariableExpression("varf"),"varc"),
                                                        new CompStatement(new PrintStatement(new VariableExpression("varc")),new closeRFileStatement(new VariableExpression("varf"))))))))));
    }
    private void menu(){
        System.out.println("1 - Execute program: >>> int v; v=2;Print(v)");
        System.out.println("2 - Execute program: >>> int a;int b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("3 - Execute program: >>> bool a; int v; a=true;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("4 - Execute program: >>> int a; Print(a);");
        System.out.println("5 - Execute program: >>> int v; v=false;Print(v)");
        System.out.println("6 - Execute program: >>> int a;bool b; a=2+3*5;b=a+1;Print(b)");
        System.out.println("7 - Execute program: >>> int a; int v; a=5;(If a Then v=2 Else v=3);Print(v)");
        System.out.println("8 - Execute program: >>> int a; nop;a=a+10;print(a)");
        System.out.println("9 - Execute program: >>> int a; nop;bool b;b=true;IF(B) THEN(a=10;b=false) ELSE(a=-10;b=true;)Print(a);Print(b);");
        System.out.println("10- Execute program: >>> int a; print(b); nop;");
        System.out.println("x - Exit");
    }
    public void exe(){
        Scanner scan = new Scanner(System.in);
        String command;
        StatementInterface statement;
        while(true){
            try {
                this.menu();
                System.out.println("Your command:");
                command=scan.nextLine();
                switch (command) {
                    case "x":
                        scan.close();
                        return;
                    case "1":
                        statement = ex1();
                        break;
                    case "2":
                        statement = ex2();
                        break;
                    case "3":
                        statement = ex3();
                        break;
                    case "4":
                        statement = ex4();
                        break;
                    case "5":
                        statement = ex5();
                        break;
                    case "6":
                        statement = ex6();
                        break;
                    case "7":
                        statement = ex7();
                        break;
                    case "8":
                        statement=ex8();
                        break;
                    case "9":
                        statement=ex9();
                        break;
                    case "10":
                        statement=ex10();
                        break;
                    case "11":
                        statement=ex11();
                        break;
                    case "12":
                        statement=ex12Files();
                        break;
                    default:
                        throw new RuntimeException("Invalid Command\n");
                }
                ProgramState prg = new ProgramState(new MyStack<>(),
                                new MyDictionary<>(),
                                new MyList<>(),statement);
                System.out.println("\tPROGRAM EXECUTION IS: \n");
                String logFile="log1.txt";
                RepositoryInterface repo = new Repository(prg,logFile);
                Controller con = new Controller(repo);
                con.allStep();

            }
            catch (MyException exc){
                System.out.println("---------------------------------EXCEPTION-----------------------------------");
                System.out.println(exc.getMessage());
                System.out.println("-----------------------------------------------------------------------------");
            }
            catch (RuntimeException exc){
                System.out.println("----------------------------------EXCEPTION-----------------------------------");
                System.out.println(exc.getMessage());
                System.out.println("-----------------------------------------------------------------------------");
            }
        }

    }
}
*/