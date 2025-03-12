JAVAC = javac
JAVA = java
SRC = $(wildcard *.java)
CLASSES = $(SRC:.java=.class)
MAIN = Main
FLAGS = -Xlint:unchecked

all: $(CLASSES)

%.class: %.java
	$(JAVAC) $(FLAGS) $<

run: $(CLASSES)
	$(JAVA) $(MAIN)

clean:
	rm -f *.class

.PHONY: all run clean