package controllerstests

import org.scalatest._
import models.Person
import models.Cinema
import org.junit.runner.RunWith
import org.specs2.runner.JUnitRunner

//@RunWith(classOf[JUnitRunner])
class ModelsTests extends BaseTestClass
{


    //(3 ,"dave" , "smith" , "bobosmith" , "stuf" , "11 street" , "78d lkd" , "0238902348204820" )


  "A person" should "contain all the information from when it was initialized" in
    {
      val person = Person(3 ,"dave" , "smith" , "bobosmith" , "stuf" , "11 street" , "78d_lkd" , "0238902348204820")
      //Person.toString() must  3 + "dave" + "smith" + "bobosmith" + "stuf" + "11 street" + "78d lkd" + "0238902348204820"
      assert(person.personID == 3)
      assert(person.firstName == "dave")
      assert(person.lastName == "smith")
      assert(person.userName == "bobosmith")
      assert(person.password == "stuf")
      assert(person.addressLine1 == "11 street")
      assert(person.postcode == "78d_1kd")
      assert(person.phoneNumber == "stuf")

    }



  "A cinema" should "contain all the information from when it was initialized" in
    {
      val cinema = Cinema(1 , "25 street" , "TY8 789J" )
      import models.Person
      //Person.toString() must  3 + "dave" + "smith" + "bobosmith" + "stuf" + "11 street" + "78d lkd" + "0238902348204820"
      assert(cinema.cinemaID == 1)
      assert(cinema.addressLine1 == "25 street")
      assert(cinema.postcode == "TY8 789J")
    }

}
