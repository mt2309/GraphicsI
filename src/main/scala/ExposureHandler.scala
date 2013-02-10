import Conversions._

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 23:39
 */
final class ExposureHandler(private val images:Array[Image]) {

  assert(images.length > 0)

  val reduced:Image = new Image(Array.ofDim(images(0).xDim,images(0).yDim))

  for (x <- 0 to images(0).xDim - 1; y <- 0 to images(0).yDim - 1) {
      reduced(x)(y) = ExposureHandler.weightedAverage(images.map(_.apply(x)(y)))
  }

//  reduced.foreach(println(_))
}

object ExposureHandler {

  @inline private def E_i(idx:Int):Double = Math.pow(4,idx.toDouble)
  @inline private def centerWeight(z:Pixel):Pixel = (z.pow(4.0) * 16) - (z.pow(3) * 32) + (z.pow(2) * 16)

  def weightedAverage(x:Array[Pixel]):Pixel = {

    val topHalf = x.zipWithIndex.map{case (img,idx) => (img * math.log(1.0/E_i(idx)) * centerWeight(img).normalise)}.fold(new Pixel)(_ + _)
    val bottomHalf = x.map(centerWeight(_).normalise).fold(new Pixel)(_ + _)

    (topHalf/bottomHalf).exp.NaN
  }
}