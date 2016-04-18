call S:\_common.bat
@java -classpath ..\lib\hsqldb.jar org.hsqldb.util.DatabaseManagerSwing -url jdbc:hsqldb:hsql://localhost/localDB -driver org.hsqldb.jdbcDriver