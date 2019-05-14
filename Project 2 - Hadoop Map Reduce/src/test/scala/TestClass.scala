import java.util

import org.scalatest._

class TestClass extends FlatSpec with Matchers {

  "ahmed_khan_hw2" should "Return Directory" in {
    ahmed_khan_hw2.getDirectory() should be (String)
  }

  "ahmed_khan_hw2" should "Create Professor List" in {
    ahmed_khan_hw2
      .getProfsList(ahmed_khan_hw2.getDirectory(), "/src/main/resources/profs.txt") should be (List[String])
  }

  "ahmed_khan_hw2" should "Create Key,Value pairs" in {
//    ahmed_khan_hw2
//      .createKeyValuePairs(Array("Ahmed Khan"), "AhmedKhan,Mark Grechanik") should be ((String, Array[String]))
  }

  "ahmed_khan_hw2" should "Create Java List" in {
    //    ahmed_khan_hw2.toJavaList(val list = List(1,2,3)) should be (List[String])
  }

  "ahmed_khan_hw2" should "Create Scala List" in {
    ahmed_khan_hw2.toScalaList(new util.ArrayList[String]())should be (List[String])
  }

}
