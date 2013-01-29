/**
 * User: mthorpe
 * Date: 29/01/2013
 * Time: 00:37
 */

class HDRPixel(val r:Float,val g:Float,val b:Float)
class Image(val image:Array[Array[HDRPixel]]) {

  def this(x:Int,y:Int) = this(Array.ofDim[HDRPixel](x,y))

  val xDim = image.length
  val yDim = image(0).length
}
