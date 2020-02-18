public class AssExTest{
	public static void main(String[] args){
		AssessmentSLL<String> sll = new AssessmentSLL<String>();

		sll.insert("dog");
		sll.insert("cog");
		sll.insert("log");
		sll.insert("chog");
		sll.insert("fog");
		sll.insert("auog");
		sll.deleteAlternate();
		sll.printFirstToLast();
	}
}