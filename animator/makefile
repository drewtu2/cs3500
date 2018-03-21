JC = javac
JAR = out/artifacts/animator_jar/animator.jar	# Compiled by IntellIJ
JAR = build/animator.jar						# Compiled by us

BUILD_DIR = ./build

RUN = java -jar $(JAR)

jar:
	mkdir -p $(BUILD_DIR) 
	find src -name *.java > $(BUILD_DIR)/sources.txt
	$(JC) -d $(BUILD_DIR) @$(BUILD_DIR)/sources.txt
	cd build/; jar cvfm animator.jar ../src/META-INF/* *

submission: jar text svg
	@echo making submission
	@rm -f resources/animator.jar
	@cp $(JAR) resources/animator.jar
	@cd ..; pwd ;\
	./makeSubmission.sh animator;

text: jar
	@echo Running text view...
	rm -f resources/text-transcript.txt
	$(RUN) -if assignment6_starter/toh-3.txt -iv text -o resources/text-transcript.txt -speed 20
	@echo Finished running text view...

svg: jar
	@echo Running svg view...
	@rm -f resources/toh-at-20.svg
	$(RUN) -if assignment6_starter/toh-8.txt -iv svg -o resources/toh-at-20.svg -speed 20
	@echo Finished running svg view...

visual: jar
	@echo Running svg view...
	@rm -f resources/toh-at-20.svg
	$(RUN) -if assignment6_starter/toh-3.txt -iv visual -o resources/toh-at-20.svg -speed 20
	@echo Finished running visual view...
