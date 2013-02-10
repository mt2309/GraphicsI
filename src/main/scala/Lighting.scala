/**
 * User: mthorpe
 * Date: 09/02/2013
 * Time: 22:39
 */
final class Lighting(image: Image) {

  // Check we have a square image
  assert(image.xDim == image.yDim)

  private lazy val center = image.xDim / 2
  private lazy val radius = image.xDim / 2

  val litImage = new Image(image.xDim, image.yDim)

  for (x <- 0 to image.xDim - 1; y <- 0 to image.yDim - 1)
    litImage.image(x)(y) = image.image(x)(y)


  private def inCircle(x: Int, y: Int): Boolean = math.pow(x - center, 2) + math.pow(y - center, 2) < math.pow(radius, 2)


}
