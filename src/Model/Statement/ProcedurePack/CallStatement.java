package Model.Statement.ProcedurePack;

import Collection.Dictionary.MyDictionary;
import Collection.Dictionary.MyDictionaryInterface;
import Model.Exception.MyException.MyException;
import Model.Expressions.Expression;
import Model.ProgramState;
import Model.Statement.StatementInterface;
import Model.Types.Type;
import Model.Values.Value;

import java.util.ArrayList;
import java.util.List;

public class CallStatement implements StatementInterface {
    private String name;
    private List<Expression> params;

    public CallStatement(String name, List<Expression> params) {
        this.name = name;
        this.params = params;
    }

    public String toString(){
        return "call "+name+params.toString();
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        /*check ProcTable and extract the list of the variables v1,..vn and
        the body statement for the procedure fname. If the procedure
        fname does not exist in ProcTable print an error message and
        terminate the execution of the program.*/

        if(state.getProcTable().get(name)==null)
            throw new MyException("Procedure "+name+" is not registered!\n");

        ArrayList<String> paramsNames = state.getProcTable().get(name).getKey();
        /*
        – use the current SymTable and evaluate the current arguments of
        the procedure, namely eval(exp1), ..., eval(expn)

         */
        ArrayList<Value> evals = new ArrayList<>();
        for(Expression exp: params){
            evals.add(exp.eval(state.getSymTable(),state.getHeap()));
        }

        /*
        create a new SymTable that contains the mappings of the formal
        variables vi to the value of the current arguments expi
        (namely vi->eval(expi))
         */
        MyDictionaryInterface<String,Value> symTable= new MyDictionary<>();
        Integer index = 0;
        for(index=0;index<paramsNames.size();++index)
            symTable.add(paramsNames.get(index),evals.get(index));

        /*
        – push new SymTable on the stack of SymTables
         */
        state.pushSymbolTable(symTable);
        state.getExeStack().push(new ReturnStatement());
        state.getExeStack().push(state.getProcTable().get(name).getValue());
        return null;
    }

    @Override
    public MyDictionaryInterface<String, Type> typeCheck(MyDictionaryInterface<String, Type> typeEnv) throws MyException {
        return null;
    }
}
