package tests;

public interface TestGroup {

	void initializeStateForNextTest();
	Test[] getTests();

}
