all:run
	java tes_main test.s
run:comp
	javac tes_main.java
comp:  
	rm *.class

