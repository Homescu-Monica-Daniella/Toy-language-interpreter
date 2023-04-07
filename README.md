# Toy-Language-Interpreter

Interpreter for a toy language using the Model-View-Controller architectural pattern and the Object-Oriented concepts. 

A `Program State` consists of the following main structures:
- `Execution Stack` (ADT Stack): keeps track of statements to execute the current program
- `Table of Symbols` (ADT Dictionary): keeps the variables values
- `Output` (ADT List): keeps all the messages printed
- `File Table` (ADT Dictionary): manages the files opened
- `Heap Table` (ADT Heap): manages the heap memory

The `Garbage Collector` removes addresses which are not reffered from Table of Symbols and from other Heap Table entries. 

Execution is done only if the program passes the `Typechecker`.

A `Type` can be Integer, String, Boolean or Reference.

A `Value` can be Integer, String, Boolean or Reference.

A `Statement` can be VariableDeclaration, Assignment, Compound, Conditional, While, Print, NoOperation, OpenReadFile, ReadFile, CloseReadFile, HeapAllocation, HeapWriting or Fork.

An `Expression` can be Value, Variable, Arithmetic, Logical, Relational or HeapReading.
