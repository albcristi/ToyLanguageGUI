# Toy Language Interpreter
Java project that uses JavaFX

## Important steps in order to run the project
# Step 1:
[Download javafx](https://gluonhq.com/products/javafx/)

I have downloaded a version from the 'Latest Release' section

# Step 2:
Unzip the downloaded file somewhere you'll remember, since you 
will need to use after opening the project

# Step 3:
After opening the project, please add a new library to your project,
there you will need to browse for the lib folder from the folder where
the downloaded file is.

# Step 4:
In case there's an error building the project, use the following
VM option:

* windows:
   * --module-path "\path\to\javafx-sdk-14\lib" --add-modules javafx.controls,javafx.fxml
* linux/mac:
  * --module-path /path/to/javafx-sdk-14/lib --add-modules javafx.controls,javafx.fxml

--- [How to setup your javaFX project](https://openjfx.io/openjfx-docs/)
# Step 5:
Where psvm is? :D
The Main class can be found in the GUI pack

---

# About the Toy Language Interpreter
This is an JavaFX application that I have developed during my 'Advanced Programming Methods' class where we  
used Java as a main programming language. The aim of this application was to have a better understanding
of how a simple programming language is being interpeted.  
The so called 'Toy Language' will have the support the following:
* Types
   * IntType
   * BoolType
   * StringType
   * ReferenceType
* Values
  * IntValue
  * BoolValue
  * StringValue
  * ReferenceValue
* Expressions:
  * Arithmetic
  * Logic
  * Relational
  * Value
  * Variable
  * Read from Heap
* Statements:
   * File Statements:
        * open file
        * read file
        * close file
   * Heap Statements:
        * new statement
        * write statemnt
   * Procedure Statemts:
        * define statement
        * call statement
        * return statement
   * IF
   * SWITCH
   * WHILE
   * FORK
   * SLEEP
   * PRINT
   * etc..
