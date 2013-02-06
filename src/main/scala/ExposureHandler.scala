import Conversions._

/**
 * User: mthorpe
 * Date: 28/01/2013
 * Time: 23:39
 */
final class ExposureHandler(private val images:Array[Image]) {

  val minimized: Array[Image] = images.zipWithIndex.map{case (x,y) => ExposureHandler.minimize(x,y)}

//  minimized(0).image.foreach(x => x.foreach(println(_)))

  println()
  println(minimized(0)(0)(0))

  val reduced:Image = new Image(Array.ofDim(minimized(0).xDim,minimized(0).yDim))

  for (x <- 0 to minimized(0).xDim - 1; y <- 0 to minimized(0).yDim - 1) {
      reduced(x)(y) = ExposureHandler.weightedAverage(minimized.map(_.apply(x)(y)))
  }
}

object ExposureHandler {

  @inline def centerWeight(z:Double):Double = 16 * Math.pow(z,4) - 32 * Math.pow(z,3) + 16 * Math.pow(z,2)

  @inline def minimize(img:Image,i:Int):Image= new Image(img.image.map(arr => arr.map(px => px/(Math.pow(4,i.toDouble)))))

  //  F(x,y) = exp( SUM_over_i( log((1/Ei) * i) * w(i)) / SUM(w(i)))

  def weightedAverage(x:Array[HDRPixel]):HDRPixel = {

//    Math.exp(x.zipWithIndex.map{case (img,idx) => })
    new HDRPixel(0,0,0)
  }
}