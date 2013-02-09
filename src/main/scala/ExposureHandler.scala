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


}

object ExposureHandler {

  @inline private def E_i(idx:Int):Double = Math.pow(4,idx.toDouble)
  @inline private def centerWeight(z:Pixel):Pixel = (z.pow(4.0) * 16) - (z.pow(3) * 32) + (z.pow(2) * 16)

  @inline def minimize(img:Image,i:Int):Image= new Image(img.image.map(arr => arr.map(px => px/E_i(i))))

  //  F(x,y) = exp( SUM_over_i( log((1/Ei) * i) * w(i)) / SUM(w(i)))

  def weightedAverage(x:Array[Pixel]):Pixel = {

    val topHalf = x.zipWithIndex.map{case (img,idx) => (img * Math.log(1.0/E_i(idx)) * centerWeight(img))}.map(_.normalise).fold(new Pixel)(_ + _)
    val bottomHalf = x.map(centerWeight(_)).fold(new Pixel)(_ + _)

    (topHalf/bottomHalf).exp
  }
}