package Model.Statement.ProcedurePack;

import Collection.Dictionary.MyDictionaryInterface;
import Model.Exception.MyException.MyException;
import Model.ProgramState;
import Model.Statement.StatementInterface;
import Model.Types.Type;

public class ReturnStatement implements StatementInterface {
    public String toString(){
        return "return";
    }
    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        state.returnMade();
        return null;
    }

    @Override
    public MyDictionaryInterface<String, Type> typeCheck(MyDictionaryInterface<String, Type> typeEnv) throws MyException {
        return null;
    }
}
