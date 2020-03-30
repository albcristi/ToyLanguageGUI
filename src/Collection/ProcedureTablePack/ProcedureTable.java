package Collection.ProcedureTablePack;

import Collection.Dictionary.MyDictionaryInterface;
import Model.Exception.MyException.MyException;
import Model.Statement.StatementInterface;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class ProcedureTable implements ProcedureTableInterface {

    private ConcurrentHashMap<String,Pair<ArrayList<String>,StatementInterface>> table;

    public ProcedureTable(){
        this.table=new ConcurrentHashMap<>();
    }
    @Override
    public Pair<ArrayList<String>, StatementInterface> get(String s) {
        return table.get(s);
    }

    @Override
    public synchronized void add(String k, Pair<ArrayList<String>, StatementInterface> v) {
        if(table.containsKey(k))
            throw new MyException(k+" is already registered!\n");
        table.put(k,v);
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public synchronized void setDict(HashMap<String, Pair<ArrayList<String>, StatementInterface>> k) throws MyException {
        ConcurrentHashMap<String,Pair<ArrayList<String>,StatementInterface>> c = new ConcurrentHashMap<>();
        for(String key: k.keySet())
            c.put(key,k.get(key));
        this.table=c;
    }

    @Override
    public void remove(String k) {
        table.remove(k);
    }

    @Override
    public boolean containsKey(String s) {
        return table.containsKey(s);
    }

    @Override
    public Collection<Pair<ArrayList<String>, StatementInterface>> values() {
        return table.values();
    }

    @Override
    public boolean containsValue(Pair<ArrayList<String>, StatementInterface> val) {
        return table.containsValue(val);
    }

    @Override
    public Set<String> keySet() {
        return table.keySet();
    }

    @Override
    public MyDictionaryInterface<String, Pair<ArrayList<String>, StatementInterface>> cloneDict() throws MyException {
        return null;
    }

    @Override
    public void update(String k, Pair<ArrayList<String>, StatementInterface> v) {
        table.remove(k,v);
    }
}