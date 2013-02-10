/**
 * User: mthorpe
 * Date: 29/01/2013
 * Time: 00:37
 */

final class Pixel(val r: Double, val g: Double, val b: Double) {

  def this() = this(0, 0, 0)

  override def toString: String = r.toString ++ "\t" ++ g.toString ++ "\t" ++ b.toString

  def /(x: Double) = new Pixel(r / x, g / x, b / x)

  def *(x: Double) = new Pixel(r * x, g * x, b * x)

  def *(that: Pixel) = new Pixel(this.r * that.r, this.g * that.g, this.b * that.b)

  def /(that: Pixel) = new Pixel(this.r / that.r, this.g / that.g, this.b / that.b)

  def +(that: Pixel): Pixel = new Pixel(this.r + that.r, this.g + that.g, this.b + that.b)

  def -(that: Pixel) = new Pixel(this.r - that.r, this.g - that.g, this.b - that.b)

  def pow(x: Double) = new Pixel(math.pow(r, x), math.pow(g, x), math.pow(b, x))

  def exp = new Pixel(math.exp(r), math.exp(g), math.exp(b))

  def normalise: Pixel = new Pixel(Pixel.norm(this.r), Pixel.norm(this.g), Pixel.norm(this.b))

  def NaN: Pixel = new Pixel(Pixel.NaN(this.r), Pixel.NaN(this.g), Pixel.NaN(this.b))
}

object Pixel {
  private def norm(d: Double): Double = {
    if (d < 0.005) 0
    else if (d > 0.92) 0
    else d
  }

  private def NaN(d: Double): Double = if (d.isNaN) 1.0 else d
}

final class Image(val image: Array[Array[Pixel]]) {

  def apply(x: Int, y: Int) = this.image(x)(y)

  def update(x: Int, y: Int, z: Pixel) = this.image(x)(y) = z

  def this(x: Int, y: Int) = this(Array.ofDim[Pixel](x, y))

  @inline val xDim = image.length
  @inline val yDim = if (xDim > 0) image(0).length else 0

  def foreach[U](f: Pixel => U) {
    this.image.foreach(_.foreach(f(_)))
  }

  def map(f: Pixel => Pixel): Image = new Image(this.image.map(_.map(f)))
}
