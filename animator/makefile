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

submission: text svg 
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

ptext: jar
	@echo Running provider text view...
	rm -f resources/text-transcript.txt
	$(RUN) -if assignment6_starter/buildings.txt -iv providertext -o resources/text-transcript.txt -speed 20
	@echo Finished running text view...

svg: jar
	@echo Running svg view...
	@rm -f resources/toh-at-20.svg
	#$(RUN) -if assignment6_starter/toh-8.txt -iv svg -o resources/toh-at-20.svg -speed 20
	$(RUN) -if assignment6_starter/rotationSVGdemo.txt -iv svg -o resources/rotation.svg -speed 20
	@echo Finished running svg view...

psvg: jar
	@echo Running svg view...
	@rm -f resources/toh-at-20.svg
	$(RUN) -if assignment6_starter/toh-3.txt -iv providersvg -o resources/toh-at-20.svg -speed 20
	@echo Finished running svg view...

visual: jar
	@echo Running visual view...
	@rm -f resources/toh-at-20.svg
	#$(RUN) -if assignment6_starter/toh-3.txt -iv visual -o resources/toh-at-20.svg -speed 20
	#$(RUN) -if assignment6_starter/rotationdemo.txt -iv visual -speed 20
	$(RUN) -if assignment6_starter/layerdemo.txt -iv visual -speed 20
	$(RUN) -if assignment6_starter/twolayerdemo.txt -iv visual -speed 20
	#$(RUN) -if assignment6_starter/toh-3.txt -iv visual -o resources/toh-at-20.svg 
	@echo Finished running visual view...

pvisual: jar
	@echo Running provider visual view...
	@rm -f resources/toh-at-20.svg
	$(RUN) -if assignment6_starter/toh-3.txt -iv providervisual -o resources/toh-at-20.svg -speed 20
	#$(RUN) -if assignment6_starter/toh-3.txt -iv visual -o resources/toh-at-20.svg 
	@echo Finished running provider visual view...

interactive: jar
	@echo Running interactive view...
	@rm -f animation.svg 
	#$(RUN) -if assignment6_starter/buildings.txt -iv interactive -speed 20
	$(RUN) -if assignment6_starter/rotationdemo.txt -iv interactive -speed 20
	#$(RUN) -if assignment6_starter/toh-3.txt -iv interactive
	@echo Finished running interactive view...

pinteractive: jar
	@echo Running interactive view...
	@rm -f animation.svg 
	$(RUN) -if assignment6_starter/buildings.txt -iv provider -speed 20
	#$(RUN) -if assignment6_starter/toh-3.txt -iv provider 
	@echo Finished running interactive view...

