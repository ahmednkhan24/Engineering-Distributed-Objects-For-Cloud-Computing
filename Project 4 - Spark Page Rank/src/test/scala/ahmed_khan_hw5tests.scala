import org.scalatest.FunSuite

class ahmed_khan_hw5tests extends FunSuite {


  test("working directory is correct") {

    val expected = "/Users/ahmedkhan/IdeaProjects/ahmed_khan_hw5"

    val actual = ahmed_khan_hw5.getDirectory()

    assert(expected == actual)

  }


  test("can find a file by giving correct path") {

    val filePath = "/Users/ahmedkhan/IdeaProjects/ahmed_khan_hw5/src/main/resources/dblp.csv"

    assert(ahmed_khan_hw5.checkForFile(filePath))

  }


  test("Can log a message") {

    assert(ahmed_khan_hw5.logger.logInfoMessage("testing") == ())

  }


  test("can't find a file by giving incorrect path") {

    val filePath = "/src/main/resources"

    assert(!ahmed_khan_hw5.checkForFile(filePath))

  }


  test("can open and read from a file") {

    val dir = ahmed_khan_hw5.getDirectory()
    val filename = "/src/main/resources/sample.txt"

    val file_contents = ahmed_khan_hw5.readFromFile(dir + filename)

    assert(file_contents == List("hello world from sample.txt file :)"))

  }


  test("can merge two lists") {

    val list1 = List("Ahmed", "Khan")
    val list2 = List("Mark", "Grechanik")

    val expected = List("Ahmed", "Khan", "Mark", "Grechanik")

    val actual = ahmed_khan_hw5.mergeLists(list1, list2)

    assert(actual == expected)

  }


  test("can create key value pairs") {

    val string = "ahmed khan,mark grechanik"

    val tup1 = ("ahmed khan", List("mark grechanik"))
    val tup2 = ("mark grechanik", List("ahmed khan"))

    val expected = List(tup1, tup2)

    val actual = ahmed_khan_hw5.createKeyValuePairs(string, List())

    assert(actual == expected)

  }



}
