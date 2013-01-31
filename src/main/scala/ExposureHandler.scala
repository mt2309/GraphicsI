import Conversions._

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 23:39
 */
final class ExposureHandler(private val images:Array[Image]) {

  val minimized: Array[Image] = images.zipWithIndex.map{case (x,y) => minimize(x,y)}



  def minimize(img:Image,i:Int):Image= new Image(img.image.map(arr => arr.map(px => px/(Math.pow(4,i.toDouble)))))
}
