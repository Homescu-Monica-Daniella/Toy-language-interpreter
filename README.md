# Toy-Language-Interpreter

Interpreter for a toy language using the Model-View-Controller architectural pattern and the Object-Oriented concepts. 

A `Type` or `Value` can be `Integer`, `String`, `Boolean` or `Reference` (recursive).

A `Statement` can be `VariableDeclaration`, `Assignment`, `Compound`, `Conditional`, `While`, `Print` or `NoOperation`.

An `Expression` can be `Value`, `Variable`, `Arithmetic`, `Logical` or `Relational`.

The interpreter uses three main structures:
- `ExecutionStack` (ADT Stack): keeps track of statements to execute the current program;
- `SymbolsTable` (ADT Dictionary): keeps the variables values;
- `Out` (ADT List): keeps all the messages printed.

All these three main structures denote the `ProgramState`. The interpreter can execute multiple programs but for each of them uses different ProgramState structures. At the begining, ExecutionStack contains the original program, and SymbolsTable and Out are empty. After the evaluation has started, ExecutionStack contains the remaining part of the program that must be evaluated, SymbolsTable contains the variables (form the variable declarations statements evaluated so far) with their assigned values, and Out contains the values printed so far.

The `FileTable` manages the files opened. FileTable is part of ProgramState and it is a dictionary mapping a String Value (containing the filename) to the `file descriptor` from Java language. The filename is a string that denotes the path to the file, the String Value created from the filename must be a unique key in FileTable. The file descriptor from Java language is an instance of the `BufferedReader` class. In order to manage the FileTable, `OpenReadFile`, `ReadFile`, `CloseReadFile` Statements are implemented. 

The `HeapTable` manages the heap memory. Heap is part of ProgramState and it is a dictionary of mappings (address, content) defined as a `HashMap` where the address is an integer (the index of a location in the heap) while the content is a Value. Heap manages the unicity of addresses, therefore it has a field that denotes the new free location. The addresses start from 1 and the address 0 is considered an invalid address (namely null). To manage the HeapTable, `HeapAllocation`, `HeapWriting` Statements and `HeapReading` Expression are implemented.

In order to support `concurrent programming`, the repository consists of a list of ProgramStates, where each ProgramState corresponds to a `thread`. Initially, only one program is introduced and the execution of that program will generate multiple ProgramStates using the `Fork` Statement. Each ProgramState is kept track of by a `static synchronized method` that manages the id. 

The `Garbage Collector` removes addresses which are not in use, taking into account that there is one Heap shared by multiple ProgramStates and multiple SymbolsTables (one for each ProgramState).

Execution is done only if the program passes the `Typechecker`. The method typecheck is implemented for all Statement and Expression classes and is called for the input program before creating its asscociated ProgramState.

The `Exceptions` class hierarchy is extended in order to treat special situations that can occur during the interpreter execution:
- exceptional situations for ADT operations (e.g. writing into a full collection, reading from an empty collection, etc)
- expressions evaluation: Division by zero, variable not defined in symbol table
- statements execution: trying to execute when the execution stack is empty
