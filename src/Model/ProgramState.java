package Model;

import Collection.Dictionary.MyDictionary;
import Collection.Dictionary.MyDictionaryInterface;
import Collection.Heap.Heap;
import Collection.Heap.HeapInterface;
import Collection.List.MyListInterface;
import Collection.ProcedureTablePack.ProcedureTable;
import Collection.ProcedureTablePack.ProcedureTableInterface;
import Collection.Stack.MyStackInterface;
import Model.Exception.MyException.MyException;
import Model.Statement.StatementInterface;
import Model.Values.Value;

import java.io.BufferedReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ProgramState {

    private MyStackInterface<StatementInterface> exeStack;
    private MyDictionaryInterface<String, Value> symTable;
    private MyListInterface<Value> out;
    private StatementInterface originalProgram;
    private MyDictionaryInterface<String, BufferedReader> fileTable;
    private HeapInterface<Value> heap;
    private static Integer GlobalMaxThreadID=0;
    private Integer threadID;
    private Stack<MyDictionaryInterface<String,Value>> stackSymTable;
    private ProcedureTableInterface procTable;

    public ProgramState(MyStackInterface<StatementInterface> exeStack, MyDictionaryInterface<String, Value> symTable, MyListInterface<Value> out, StatementInterface originalProgram) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram; //should be  a deep copy..
        this.fileTable = new MyDictionary<>();
        this.heap=new Heap<Value>();
        this.threadID=generateThreadID();
        exeStack.push(originalProgram);
        stackSymTable = new Stack<>();
        stackSymTable.push(symTable);
        procTable=new ProcedureTable();
    }

    public ProgramState(MyStackInterface<StatementInterface> exeStack, MyDictionaryInterface<String, Value> symTable, MyListInterface<Value> out, StatementInterface originalProgram, MyDictionaryInterface<String, BufferedReader> fileTable, HeapInterface<Value> heap) {
        this.exeStack = exeStack;
        this.symTable = symTable;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heap = heap;
        exeStack.push(originalProgram);
        this.threadID=generateThreadID();
        stackSymTable = new Stack<>();
        stackSymTable.push(symTable);
        procTable=new ProcedureTable();
    }

    public ProgramState(MyStackInterface<StatementInterface> exeStack, MyListInterface<Value> out, StatementInterface originalProgram, MyDictionaryInterface<String, BufferedReader> fileTable, HeapInterface<Value> heap, Stack<MyDictionaryInterface<String, Value>> stackSymTable, ProcedureTableInterface procTable) {
        this.exeStack = exeStack;
        this.out = out;
        this.originalProgram = originalProgram;
        this.fileTable = fileTable;
        this.heap = heap;
        this.stackSymTable = stackSymTable;
        this.procTable = procTable;
        this.exeStack.push(originalProgram);
        this.symTable=stackSymTable.pop();
        stackSymTable.push(symTable);
        this.threadID=generateThreadID();
    }

    public MyStackInterface<StatementInterface> getExeStack() {
        return exeStack;
    }

    public void setExeStack(MyStackInterface<StatementInterface> exeStack) {
        this.exeStack = exeStack;
    }

    public MyDictionaryInterface<String, Value> getSymTable() {
        MyDictionaryInterface<String,Value> sym = stackSymTable.pop();
        stackSymTable.push(sym);
        return sym;
    }

    public void setSymTable(MyDictionaryInterface<String, Value> symTable) {
        this.symTable = symTable;
    }

    public MyListInterface<Value> getOut() {
        return out;
    }

    public void setOut(MyListInterface<Value> out) {
        this.out = out;
    }

    public StatementInterface getOriginalProgram() {
        return originalProgram;
    }

    public void setHeap(HeapInterface<Value> h){this.heap=h;}

    public void setOriginalProgram(StatementInterface originalProgram) {
        this.originalProgram = originalProgram;
    }

    public HeapInterface<Value> getHeap(){return this.heap;}
    public MyDictionaryInterface<String, BufferedReader> getFileTable(){
        return fileTable;
    }
    public String toString(){
        return "Thread ID:\n"+threadID+"\n"+"ExeStack:\n"+exeStack+"\nSymTable:\n"+symTable+"\nOut:\n"+out+"\nFileTable:\n"+fileTable+"\n"+
                "Heap:\n"+heap+"\n";
    }

    public boolean isNotCompleted(){
        return !exeStack.isEmpty();
    }

    public ProgramState oneStep(){
        if(exeStack.isEmpty())
            throw new MyException("Execution Stack is Empty!");
        StatementInterface currentStatement = exeStack.pop();
        return currentStatement.execute(this);
    }

    private synchronized Integer generateThreadID(){
        GlobalMaxThreadID++;
        return GlobalMaxThreadID;
    }

    public Integer getThreadID(){
        return this.threadID;
    }

    public Stack<MyDictionaryInterface<String,Value>> cloneSymStack(){
        Stack<MyDictionaryInterface<String,Value>>  cop = new Stack<>();
        ArrayList<MyDictionaryInterface<String,Value>> s= new ArrayList<>();
        ArrayList<MyDictionaryInterface<String,Value>> s1= new ArrayList<>();
        Integer i =0;
        while (!stackSymTable.isEmpty()){
            s.add(stackSymTable.pop().cloneDict());
            s1.add(s.get(i).cloneDict());
            i++;
        }

        for(i=s.size()-1;i>-1;--i){
            stackSymTable.push(s1.get(i));
            cop.push(s.get(i));
        }
        return cop;
    }

    public ProcedureTableInterface getProcTable(){return procTable;}

    public void  returnMade(){
        stackSymTable.pop();
        symTable=stackSymTable.pop();
        stackSymTable.push(symTable);
    }

    public void pushSymbolTable(MyDictionaryInterface<String,Value> local){
        symTable=local;
        stackSymTable.push(local);
    }
}
