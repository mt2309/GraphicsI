/**
 * User: mthorpe
 * Date: 29/01/2013
 * Time: 00:37
 */

final class HDRPixel(val r:Double,val g:Double,val b:Double) {
  override def toString:String = r.toString ++ "\t" ++ g.toString ++ "\t" ++ b.toString
  def /(x:Double):HDRPixel = new HDRPixel(r/x,g/x,b/x)
}

final class Image(val image:Array[Array[HDRPixel]]) {

  def this(x:Int,y:Int) = this(Array.ofDim[HDRPixel](x,y))

  @inline val xDim = image.length
  @inline val yDim = if (xDim > 0) image(0).length else 0
}
