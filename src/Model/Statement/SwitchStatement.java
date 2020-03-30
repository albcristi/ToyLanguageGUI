package Model.Statement;

import Collection.Dictionary.MyDictionaryInterface;
import Model.Exception.MyException.MyException;
import Model.Expressions.Expression;
import Model.Expressions.RelationalExpression;
import Model.ProgramState;
import Model.Types.Type;

public class SwitchStatement implements  StatementInterface{
    /*
    . (2.75p). Define the new statement:
        switch(exp) (case exp1: stmt1) (case exp2: stmt2) (default: stmt3)
        It is a switch statement that executes either the statement stmt1 when
        exp==exp1, or the statement stmt2 when exp==exp2 or the statement stmt3
        otherwise.

        Its execution on the ExeStack is the following:
        - pop the statement
        - create the following statement:
         if(exp==exp1) then stmt1 else (if (exp==exp2) then stmt2 else stmt3)
        - push the new statement on the stack

        The typecheck method of switch statement verifies if exp, exp1 and exp2 have
        the same type and also typecheck the statements stmt1, stmt2 and stmt3.
     */
    private Expression exp, exp1,exp2;
    private StatementInterface stm1,stmt2,stmt3;

    public SwitchStatement(Expression exp, Expression exp1, Expression exp2, StatementInterface stm1, StatementInterface stmt2, StatementInterface stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stm1 = stm1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    public String toString(){
        return "switch("+exp+")( case "+exp1+": "+stm1+")"+"(case "+exp2+": "+stmt2+")(default: "+stmt3+")";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StatementInterface theStm = new IfStatement(new RelationalExpression(exp,exp1,"=="),stm1,new IfStatement(new RelationalExpression(
                exp,exp2,"=="),stmt2,stmt3));
        state.getExeStack().push(theStm);
        return null;
    }

    @Override
    public MyDictionaryInterface<String, Type> typeCheck(MyDictionaryInterface<String, Type> typeEnv) throws MyException {
        /*
        The typecheck method of switch statement verifies if exp, exp1 and exp2 have
        the same type and also typecheck the statements stmt1, stmt2 and stmt3.
         */
        if(!exp.typecheck(typeEnv).equals(exp1.typecheck(typeEnv)))
            throw new MyException("Expressions must have the same value!");
        if(!exp.typecheck(typeEnv).equals(exp2.typecheck(typeEnv)))
            throw new MyException("Expressions must have the same value");
        if(!exp1.typecheck(typeEnv).equals(exp2.typecheck(typeEnv))){
            throw new MyException("Expressions must have the same value!");
        }

        return stmt3.typeCheck(stmt2.typeCheck(stm1.typeCheck(typeEnv)));
    }
}
