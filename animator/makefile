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
	echo making submission
	@ rm -f resource/animator.jar
	@ cp $(JAR) resource/animator.jar
	@cd ..; pwd ;\
	./makeSubmission.sh animator;

text:
	@echo Running text view...
	rm -f resource/text-transcript.txt
	$(RUN) -if assignment6_starter/toh-3.txt -iv text -o resource/text-transcript.txt -speed 20
	@echo Finished running text view...

svg:
	@echo Running svg view...
	rm -f resource/toh-at-20.svg
	$(RUN) -if assignment6_starter/toh-8.txt -iv svg -o resource/toh-at-20.svg -speed 20
	@echo Finished running svg view...
