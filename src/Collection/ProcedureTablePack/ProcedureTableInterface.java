package Collection.ProcedureTablePack;

import Collection.Dictionary.MyDictionaryInterface;
import Model.Expressions.VariableExpression;
import Model.Statement.StatementInterface;
import javafx.util.Pair;

import java.util.ArrayList;

/*
to Heap, FileTable and Out tables and it is shared among different threads),
ProcTable that maps a procedure name to a pair of a list of variable names and
a statement.
 */
public interface ProcedureTableInterface extends MyDictionaryInterface<String,Pair<ArrayList<String>,StatementInterface>>{

}