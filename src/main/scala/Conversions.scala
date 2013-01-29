/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 23:46
 */
object Conversions {
  implicit def image2Array(x:Image):Array[HDRPixel] = x.image
}
