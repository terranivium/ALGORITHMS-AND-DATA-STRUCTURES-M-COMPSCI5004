public class AssExTestClass{
	public static void main(String[] args){
		AssessmentSLL<String> sll1 = new AssessmentSLL<String>();
		AssessmentSLL<String> sll2 = new AssessmentSLL<String>();
		AssessmentSLL<String> sll3 = new AssessmentSLL<String>();

		sll1.insert("i");
		sll2.insert("h");
		sll2.insert("g");
		sll2.insert("f");
		sll2.insert("f");
		sll2.insert("e");
		sll3.insert("d");
		sll3.insert("c");
		sll3.insert("b");
		sll3.insert("a");

		sll1.merge(sll2, sll3);
		//sll1.printFirstToLast();
		//sll2.printFirstToLast();
		//sll3.printFirstToLast();
	}
}