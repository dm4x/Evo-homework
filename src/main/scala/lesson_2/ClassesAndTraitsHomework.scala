package lesson_2

sealed trait Shape extends Located with Bounded with Movable

sealed trait Located {
  def x: Double
  def y: Double
  def z: Option[Double]
}

sealed trait Bounded {
  def minX: Double
  def maxX: Double
  def minY: Double
  def maxY: Double
  def minZ: Option[Double]
  def maxZ: Option[Double]
}

sealed trait Movable {
  def move(dx: Double, dy: Double, dz: Double): Movable
}


final case class Point(x: Double, y: Double, z: Option[Double] = None) extends Shape {
  override def minX: Double = x
  override def maxX: Double = x
  override def minY: Double = y
  override def maxY: Double = y
  override def minZ: Option[Double] = z
  override def maxZ: Option[Double] = z

  /**
   * Here and later: I remember that Option.get is a bad practice,
   * but I checked it with pattern matching before getting value.
   */
  override def move(dx: Double, dy: Double, dz: Double = 0): Point = z match {
    case None => Point(x + dx, y + dy, None)
    case Some(z) => Point(x + dx, y + dy, Some(z + dz))
  }
}

final case class Sphere(centerX: Double, centerY: Double, centerZ: Option[Double] = None, radius: Double) extends Shape {
  override def x: Double = centerX
  override def y: Double = centerY
  override def z: Option[Double] = centerZ
  override def minX: Double = x - radius
  override def maxX: Double = x + radius
  override def minY: Double = y - radius
  override def maxY: Double = y + radius

  override def minZ: Option[Double] = z match {
    case None => None
    case Some(z) => Some(z - radius)
  }

  override def maxZ: Option[Double] = z match {
    case None => None
    case Some(z) => Some(z + radius)
  }

  override def move(dx: Double, dy: Double, dz: Double = 0): Sphere = centerZ match {
    case None => Sphere(centerX + dx, centerY + dy, None, radius)
    case Some(centerZ) => Sphere(centerX + dx, centerY + dy, Some(centerZ + dz), radius)
  }

  def area: Double = centerZ match {
    case None => Math.PI * radius * radius
    case _ => Math.PI * 4 * radius * radius
  }

  def volume: Double = centerZ match {
    case None => 0.0
    case _ => 4 / 3 * Math.PI * radius * radius * radius
  }
}

final case class Cube(x: Double, y: Double, z: Option[Double] = None, side: Double) extends Shape {
  override def minX: Double = x
  override def maxX: Double = x + side
  override def minY: Double = y
  override def maxY: Double = y + side
  override def minZ: Option[Double] = z

  override def maxZ: Option[Double] = z match {
    case None => None
    case Some(z) => Some(z + side)
  }

  override def move(dx: Double, dy: Double, dz: Double = 0): Cube = z match {
    case None => Cube(x + dx, y + dy, None, side)
    case Some(z) => Cube(x + dx, y + dy, Some(z + dz), side)
  }

  def area: Double = z match {
    case None => side * side
    case Some(_) => 6 * side * side
  }

  def volume: Double = z match {
    case None => 0.0
    case Some(_) => side * side * side
  }
}

final case class Cuboid(x: Double, y: Double, z: Option[Double] = None, height: Double, width: Double, depth: Double = 0) extends Shape {
  override def minX: Double = x
  override def maxX: Double = x + width
  override def minY: Double = y
  override def maxY: Double = y + height
  override def minZ: Option[Double] = z

  override def maxZ: Option[Double] = z match {
    case None => None
    case Some(z) => Some(z + depth)
  }

  override def move(dx: Double, dy: Double, dz: Double = 0): Cuboid = z match {
    case None => Cuboid(x + dx, y + dy, None, height, width, depth)
    case Some(z) => Cuboid(x + dx, y + dy, Some(z + dz), height, width, depth)
  }

  def area: Double = depth match {
    case 0 => height * width
    case _ => 2 * ((height * width) + (height * depth) + (width * depth))
  }

  def volume: Double = depth match {
    case 0 => 0.0
    case _ => height * width * depth
  }
}

final case class Triangle(A: Point, B: Point, C: Point) extends Shape {
  val coords = List(A, B, C)
  override def x: Double = A.x
  override def y: Double = A.y
  override def z: Option[Double] = A.z
  override def minX: Double = coords.map(_.x).min
  override def maxX: Double = coords.map(_.x).max
  override def minY: Double = coords.map(_.y).min
  override def maxY: Double = coords.map(_.y).max
  override def minZ: Option[Double] = coords.map(_.z).min
  override def maxZ: Option[Double] = coords.map(_.z).max

  override def move(dx: Double, dy: Double, dz: Double = 0): Triangle = z match {
    case None => Triangle(
      Point(x + dx, y + dy),
      Point(x + dx, y + dy),
      Point(x + dx, y + dy))
    case Some(z) => Triangle(
      Point(x + dx, y + dy, Some(z + dz)),
      Point(x + dx, y + dy, Some(z + dz)),
      Point(x + dx, y + dy, Some(z + dz)))
  }

  def area: Double = ???

}


// Homework
//
// Add additional 2D shapes such as triangle and square.
//
// In addition to the 2D shapes classes, add also 3D shapes classes
// (origin, point, sphere, cube, cuboid, 3D triangle - you can add
// others if you think they are a good fit).
//
// Add method `area` to 2D shapes.
//
// Add methods `surfaceArea` and `volume` to 3D shapes.
//
// If some of the implementation involves advanced math, it is OK
// to skip it (leave unimplemented), the primary intent of this
// exercise is modelling using case classes and traits, and not math.
