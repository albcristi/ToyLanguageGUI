package Model.Statement;

import Collection.Dictionary.MyDictionaryInterface;
import Model.Exception.MyException.MyException;
import Model.ProgramState;
import Model.Types.IntType;
import Model.Types.Type;
import Model.Values.IntValue;
import Model.Values.Value;

public class SleepStatement implements StatementInterface {

    Value number;

    public SleepStatement (Value val){
        this.number=val;
    }

    public String toString(){
        return "sleep("+number+")";
    }
    @Override
    public synchronized ProgramState execute(ProgramState state) throws MyException {
        if(!number.getType().equals(new IntType()))
            throw new MyException("Sleep Statement takes an integer value as parameter!\n");
        Integer no = ((IntValue)number).getValue();

        if(no>0)
            state.getExeStack().push(new SleepStatement(new IntValue(no-1)));
        return null;
    }

    @Override
    public MyDictionaryInterface<String, Type> typeCheck(MyDictionaryInterface<String, Type> typeEnv) throws MyException {
        return null;
    }
}
