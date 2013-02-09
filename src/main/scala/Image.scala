/**
 * User: mthorpe
 * Date: 29/01/2013
 * Time: 00:37
 */

final class HDRPixel(val r:Double,val g:Double,val b:Double) {

  def this() = this(0,0,0)

  override def toString:String = r.toString ++ "\t" ++ g.toString ++ "\t" ++ b.toString
  def /(x:Double) = new HDRPixel(r/x,g/x,b/x)
  def *(x:Double) = new HDRPixel(r*x,g*x,b*x)

  def *(that:HDRPixel)= new HDRPixel(this.r * that.r, this.g * that.g, this.b * that.b)
  def /(that:HDRPixel) = new HDRPixel(this.r / that.r, this.g / that.g, this.b / that.b)

  def +(that:HDRPixel):HDRPixel = new HDRPixel(this.r + that.r, this.g + that.g, this.b + that.b)
  def -(that:HDRPixel) = new HDRPixel(this.r - that.r, this.g - that.g, this.b - that.b)

  def pow(x:Double) = new HDRPixel(Math.pow(r,x),Math.pow(g,x),Math.pow(b,x))
  def exp = new HDRPixel(Math.exp(r), Math.exp(g), Math.exp(b))

  def normalise:HDRPixel = new HDRPixel(HDRPixel.norm(this.r), HDRPixel.norm(this.g), HDRPixel.norm(this.b))
}

object HDRPixel {
  private def norm(d:Double):Double = {
    if (d < 0.005) 0
    else if (d > 0.92) 0
    else d
  }
}

final class Image(val image:Array[Array[HDRPixel]]) {

  def this(x:Int,y:Int) = this(Array.ofDim[HDRPixel](x,y))

  @inline val xDim = image.length
  @inline val yDim = if (xDim > 0) image(0).length else 0

  def foreach[U](f: HDRPixel => U):Unit = this.image.foreach(_.foreach(f(_)))
}
