JC = javac
JAR = out/artifacts/animator_jar/animator.jar
RUN = java -jar $(JAR)

easy_animator:
	$(JC) $(JFLAGS) src/cs3500/*

submission: text svg
	echo making submission
	@ rm -f resource/animator.jar
	@ cp out/artifacts/animator_jar/animator.jar resource/animator.jar
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
	$(RUN) -if assignment6_starter/toh-3.txt -iv svg -o resource/toh-at-20.svg -speed 20
	@echo Finished running svg view...
