### Welcome to JBSE
JBSE is the Java Bytecode Symbolic Executor. Basically, it is a special-purpose Java Virtual Machine written in Java.

### What's for?
JBSE is meant to be used for analyzing Java code and assessing whether it is OK or not. This makes it similar in aim to tools like [Checkstyle](http://checkstyle.sourceforge.net), [PMD](http://pmd.sourceforge.net) or [Findbugs](http://findbugs.sourceforge.net), but the analogy ends here. While the above mentioned tools work by scanning the source code in search for a predefined set of code patterns, JBSE simulates the execution of the compiled classfiles (that's why it is a Java Virtual Machine). JBSE expects that you specify the verification properties of interest for your project as a set of *assumptions* and *assertions*. Assumptions specify the conditions that must be satisfied for an execution to be relevant for the analysis. Assertions specify the conditions that must satisfied for an execution to be correct. JBSE attempts to determine whether some inputs exist that satisfy all the assumptions and make fail at least one assertion. In this regard JBSE is more similar in spirit, implementation and mode of use to tools like [Symbolic PathFinder](http://babelfish.arc.nasa.gov/trac/jpf/wiki/projects/jpf-symbc), [Sireum/Kiasan](http://www.sireum.org) and [JNuke](http://fmv.jku.at/jnuke/).

### What are its distinctive features?
Specifying good verification properties is not always easy, especially when inputs are made of complex object configurations as customarily happen with object-oriented and heap-manipulating programs. To make the task simpler JBSE offers a comprehensive array of techniques and languages for expressing assumptions and assertions. None of the tools we are currently aware of offers all of them. This allows JBSE to execute and analyze a wide spectrum of Java programs, including programs that manipulate arrays.

Another distinctive feature of JBSE is its speed. According to our microbenchmarks it is among the fastest symbolic executors for Java bytecode, capable of analyzing the Siemens suite [tcas](http://sir.unl.edu) program in about 5 seconds. All the standard caveats about the (scarce) generality of micro benchmarks apply, but we have not yet encountered a case study where JBSE is meaningfully slower than other approaches.

### What are its limitations?
Determining whether a control-flow path in the program can be covered by some inputs is an intrinsically hard problem, much harder than scanning the source code for bug patterns. JBSE works by calculating, for all the paths in the program it explores, the associated *path conditions*, where a path condition is a formula on the program input variables whose solutions, if they exist, are the inputs that cover the path. JBSE checks whether the path conditions have solutions by invoking an external solver. Currently JBSE can interact with the SMT solvers [Z3](http://z3.codeplex.com) and [CVC4](http://cvc4.cs.nyu.edu/). The correctness, completeness and speed of JBSE strongly depend on those of the solver it relies upon. 

JBSE also has limitations on its own. JBSE implements a subset of the [Java Virtual Machine specification v.2](https://docs.oracle.com/javase/specs/jvms/se5.0/html/VMSpecTOC.doc.html). This means that JBSE does not recognize successive extensions to the JVM like, e.g., the invokedynamic bytecode, and that it does not even implement some more established Java constructs: multithreading and synchronization (JBSE only analyzes single-threaded code), native methods, reflection and dynamic class loading. For this reason JBSE is not able to execute most of the Java standard library classes, although we plan to progressively improve it under this aspect.

### Authors and Contributors
The contributions to JBSE extend over a very long period. The current maintainer of JBSE is Pietro Braione (@pietrobraione), with contributions by Giovanni Denaro especially on the rewriting engine. Marco Gaboardi wrote the initial implementation of JBSE. Diego Piazza contributed to the development of the SMTLIB2 interface. Esther Turati worked on the JUnit test suite generator. Last but not least, Andrea Mattavelli and Andrea Aquino contributed patches to existing code.

### Acknowledging our work
If you find JBSE useful and you want to cite it in your publication you can refer these papers:

P. Braione, G. Denaro, M. Pezzè. *Symbolic execution of programs with heap inputs*. In Proceedings of the 10th Joint Meeting of the European Software Engineering Conference and the ACM SIGSOFT Symposium on the Foundations of Software Engineering (ESEC/FSE 2015), pp 602-613. [doi:10.1145/2786805.2786842](http://dx.doi.org/10.1145/2786805.2786842).

P. Braione, G. Denaro, M. Pezzè. *Enhancing Symbolic Execution with Built-In Term Rewriting and Constrained Lazy Initialization*. In Proceedings of the 9th Joint Meeting of the European Software Engineering Conference and the ACM SIGSOFT Symposium on the Foundations of Software Engineering (ESEC/FSE 2013), pp 411-421. [doi:10.1145/2491411.2491433](http://dx.doi.org/10.1145/2491411.2491433).

Of course we invite you to star the project on Github.

### Disclaimer
JBSE is a research prototype offered as-is, and we disclaim any expressed or implied warranty on its correctness, quality, or even fitness for a particular purpose. In no event shall its authors and the organization they belong to be considered liable for any direct or consequent damage that may derive from its use, for any definition of liability. See the [license](https://github.com/pietrobraione/jbse/blob/master/LICENSE.txt) for more information.

### More information
A user manual is on the way but not yet available. In the meantime you can refer to the [README.md](https://github.com/pietrobraione/jbse/blob/master/README.md) file and the [examples project](https://github.com/pietrobraione/jbse-examples) for learning how to use JBSE, and to the publications section at the [homepage of Pietro Braione](https://sites.google.com/site/pietrobraione/home) for an overview of the JBSE techniques and internals.

### Support or Contact
For any issue contact Pietro Braione at *name*.*surname*@unimib.it.