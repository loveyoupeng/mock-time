# mock-time
mock java.lang.System.currentTimeMillis for testing propose 

start process with agent attached to change the behavior of System.currentTimeMillis().

to enforce everything works you need adding following jvm param to disable jvm inline "-XX:CompileCommand=dontinline java.lang.System::currentTimeMillis"
