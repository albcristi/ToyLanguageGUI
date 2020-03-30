package Model.Statement.ProcedurePack;

import Collection.Dictionary.MyDictionaryInterface;
import Model.Exception.MyException.MyException;
import Model.ProgramState;
import Model.Statement.StatementInterface;
import Model.Types.Type;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class DefineProcedureStatement implements StatementInterface {
    private String name;
    private StatementInterface body;
    private List<String> params;

    public DefineProcedureStatement(String name, StatementInterface body, List<String> params) {
        this.name = name;
        this.body = body;
        this.params = params;
    }

    @Override
    public String toString() {
        return "procedure "+name+params.toString()+"{"+body+"}";
    }

    @Override
    public synchronized ProgramState execute(ProgramState state) throws MyException {
        ArrayList<String> s = new ArrayList<>(params);
        state.getProcTable().add(name,new Pair<>(s,body));
        return null;
    }

    @Override
    public MyDictionaryInterface<String, Type> typeCheck(MyDictionaryInterface<String, Type> typeEnv) throws MyException {
        return null;
    }
}
