JAVAC = javac
JAVA = java
SRC = src
BIN = bin
MAIN = matrix.Main

all:
	mkdir -p $(BIN)
	$(JAVAC) -d $(BIN) $(shell find $(SRC) -name "*.java")

run:
	$(JAVA) -cp $(BIN) $(MAIN)

clean:
	rm -rf $(BIN)
