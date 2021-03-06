package lesson_2_classes_and_traits

import org.scalatest.matchers.should.Matchers._
import org.scalatest.flatspec.AnyFlatSpec

class ClassesAndTraitsHomeworkTest extends AnyFlatSpec {

  "Point" should "be correct" in {
    val point = Point(1.0, 2.0, None)
    point.minX shouldEqual 1.0
    point.maxX shouldEqual 1.0
    point.minY shouldEqual 2.0
    point.maxY shouldEqual 2.0
    point.minZ shouldEqual None
    point.maxZ shouldEqual None
    point.move(10,10) shouldEqual Point(11.0, 12.0, None)
    point.move(23,7) shouldEqual Point(24.0, 9.0, None)
  }

  "Point3D" should "be correct" in {
    val point = Point(1.0, 2.0, Some(3.0))
    point.minX shouldEqual 1.0
    point.maxX shouldEqual 1.0
    point.minY shouldEqual 2.0
    point.maxY shouldEqual 2.0
    point.minZ shouldEqual Some(3.0)
    point.maxZ shouldEqual Some(3.0)
    point.move(10,10, 10) shouldEqual Point(11.0, 12.0, Some(13.0))
    point.move(23,7, 95) shouldEqual Point(24.0, 9.0, Some(98.0))
  }

  "Circle" should "be correct" in {
    val circle = Sphere(1.0, 2.0, None, 4.0)
    circle.minX shouldEqual -3.0
    circle.maxX shouldEqual 5.0
    circle.minY shouldEqual -2.0
    circle.maxY shouldEqual 6.0
    circle.minZ shouldEqual None
    circle.maxZ shouldEqual None
    circle.move(10,10) shouldEqual Sphere(11.0, 12.0, None, 4.0)
    circle.move(23,7) shouldEqual Sphere(24.0, 9.0, None, 4.0)
    circle.area shouldEqual Math.PI * 16.0
    circle.volume shouldEqual 0.0
  }

  "Sphere" should "be correct" in {
    val sphere = Sphere(1.0, 2.0, Some(3.0), 4.0)
    sphere.minX shouldEqual -3.0
    sphere.maxX shouldEqual 5.0
    sphere.minY shouldEqual -2.0
    sphere.maxY shouldEqual 6.0
    sphere.minZ shouldEqual Some(-1.0)
    sphere.maxZ shouldEqual Some(7.0)
    sphere.move(10,10, 10) shouldEqual Sphere(11.0, 12.0, Some(13.0), 4.0)
    sphere.move(23,7, 95) shouldEqual Sphere(24.0, 9.0, Some(98.0), 4.0)
    sphere.area shouldEqual Math.PI * 64.0
    sphere.volume shouldEqual 4 / 3 * Math.PI * 64.0
  }

  "Square" should "be correct" in {
    val square = Cube(1.0, 2.0, None, 4.0)
    square.minX shouldEqual 1.0
    square.maxX shouldEqual 5.0
    square.minY shouldEqual 2.0
    square.maxY shouldEqual 6.0
    square.minZ shouldEqual None
    square.maxZ shouldEqual None
    square.move(10,10) shouldEqual Cube(11.0, 12.0, None, 4.0)
    square.move(23,7) shouldEqual Cube(24.0, 9.0, None, 4.0)
    square.area shouldEqual 16.0
    square.volume shouldEqual 0.0
  }

  "Cube" should "be correct" in {
    val cube = Cube(1.0, 2.0, Some(3.0), 4.0)
    cube.minX shouldEqual 1.0
    cube.maxX shouldEqual 5.0
    cube.minY shouldEqual 2.0
    cube.maxY shouldEqual 6.0
    cube.minZ shouldEqual Some(3.0)
    cube.maxZ shouldEqual Some(7.0)
    cube.move(10,10, 10) shouldEqual Cube(11.0, 12.0, Some(13.0), 4.0)
    cube.move(23,7, 95) shouldEqual Cube(24.0, 9.0, Some(98.0), 4.0)
    cube.area shouldEqual 96.0
    cube.volume shouldEqual 64.0
  }

  "Rectangular" should "be correct" in {
    val rectangular = Cuboid(1.0, 2.0, None, 4.0, 5.0)
    rectangular.minX shouldEqual 1.0
    rectangular.maxX shouldEqual 6.0
    rectangular.minY shouldEqual 2.0
    rectangular.maxY shouldEqual 6.0
    rectangular.minZ shouldEqual None
    rectangular.maxZ shouldEqual None
    rectangular.move(10, 10) shouldEqual Cuboid(11.0, 12.0, None, 4.0, 5.0)
    rectangular.move(23, 7) shouldEqual Cuboid(24.0, 9.0, None, 4.0, 5.0)
    rectangular.area shouldEqual 20.0
    rectangular.volume shouldEqual 0.0
  }

  "Cuboid" should "be correct" in {
    val cuboid = Cuboid(1.0, 2.0, Some(3.0), 4.0, 5.0, 6.0)
    cuboid.minX shouldEqual 1.0
    cuboid.maxX shouldEqual 6.0
    cuboid.minY shouldEqual 2.0
    cuboid.maxY shouldEqual 6.0
    cuboid.minZ shouldEqual Some(3.0)
    cuboid.maxZ shouldEqual Some(9.0)
    cuboid.move(10, 10, 10) shouldEqual Cuboid(11.0, 12.0, Some(13.0), 4.0, 5.0, 6.0)
    cuboid.move(23, 7, 95) shouldEqual Cuboid(24.0, 9.0, Some(98.0), 4.0, 5.0, 6.0)
    cuboid.area shouldEqual 148.0
    cuboid.volume shouldEqual 120.0
  }

  "Triangle" should "be correct" in {
    val triangle = Triangle(Point(3.0, 3.0, None), Point(4.0, 4.0, None), Point(5.0, 5.0, None))
    triangle.minX shouldEqual 3.0
    triangle.maxX shouldEqual 5.0
    triangle.minY shouldEqual 3.0
    triangle.maxY shouldEqual 5.0
    triangle.minZ shouldEqual None
    triangle.maxZ shouldEqual None
    triangle.move(10, 10, 10) shouldEqual Triangle(Point(13.0, 13.0, None), Point(14.0, 14.0, None), Point(15.0, 15.0, None))
    triangle.move(23, 7, 95) shouldEqual Triangle(Point(26.0, 10.0, None), Point(27.0, 11.0, None), Point(28.0, 12.0, None))
  }

  "Triangle 3D" should "be correct" in {
    val triangle = Triangle(Point(3.0, 3.0, Some(3.0)), Point(4.0, 4.0, Some(4.0)), Point(5.0, 5.0, Some(5.0)))
    triangle.minX shouldEqual 3.0
    triangle.maxX shouldEqual 5.0
    triangle.minY shouldEqual 3.0
    triangle.maxY shouldEqual 5.0
    triangle.minZ shouldEqual Some(3.0)
    triangle.maxZ shouldEqual Some(5.0)
    triangle.move(10, 10, 10) shouldEqual Triangle(Point(13.0, 13.0, Some(13.0)), Point(14.0, 14.0, Some(14.0)), Point(15.0, 15.0, Some(15.0)))
    triangle.move(23, 7, 95) shouldEqual Triangle(Point(26.0, 10.0, Some(98.0)), Point(27.0, 11.0, Some(99.0)), Point(28.0, 12.0, Some(100.0)))
  }

}
